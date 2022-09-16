package com.swipefwd.xmpp

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import com.swipefwd.utils.AppDatabase
import com.swipefwd.xmpp.Logger.Companion.debug
import com.swipefwd.xmpp.Logger.Companion.error
import com.swipefwd.xmpp.Logger.Companion.info
import com.swipefwd.xmpp.Utils.getBytes
import com.swipefwd.xmpp.Utils.getChatUsers
import com.swipefwd.xmpp.Utils.getScaledBitmap
import com.swipefwd.xmpp.XmppService.Companion.getRosterEntries
import com.swipefwd.xmpp.XmppService.Companion.getXmppAccount
import com.swipefwd.xmpp.XmppService.Companion.setConnected
import com.swipefwd.xmpp.XmppService.Companion.setRosterEntries
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastContactAddError
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastContactAdded
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastContactRemoved
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastContactRenamed
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastConversationsClearError
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastConversationsCleared
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastMessageAdded
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastMessageSent
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastRoasterList
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastRosterChanged
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastRosterStatusChanged
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastTyping
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.broadcastTypingStop
import com.swipefwd.xmpp.database.Rosters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jivesoftware.smack.*
import org.jivesoftware.smack.SmackException.NoResponseException
import org.jivesoftware.smack.SmackException.NotConnectedException
import org.jivesoftware.smack.XMPPException.XMPPErrorException
import org.jivesoftware.smack.chat.Chat
import org.jivesoftware.smack.chat.ChatManager
import org.jivesoftware.smack.chat.ChatManagerListener
import org.jivesoftware.smack.chat.ChatMessageListener
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.packet.Presence
import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.roster.RosterEntry
import org.jivesoftware.smack.roster.RosterListener
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jivesoftware.smackx.chatstates.ChatState
import org.jivesoftware.smackx.chatstates.packet.ChatStateExtension
import org.jivesoftware.smackx.filetransfer.FileTransferManager
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer
import org.jivesoftware.smackx.iqregister.AccountManager
import org.jivesoftware.smackx.offline.OfflineMessageManager
import org.jivesoftware.smackx.ping.PingFailedListener
import org.jivesoftware.smackx.ping.PingManager
import org.jivesoftware.smackx.receipts.DeliveryReceiptManager
import org.jivesoftware.smackx.vcardtemp.VCardManager
import org.jivesoftware.smackx.vcardtemp.packet.VCard
import java.io.File
import java.io.IOException
import java.util.*
import java.util.Collections.*
import javax.net.SocketFactory
import kotlin.collections.ArrayList


/**
 * Implementation of the XMPP Connection.
 * @author gotev (Aleksandar Gotev)
 */
class XmppServiceConnection(
    private val mAccount: XmppAccount,
    var context: Context
) : ConnectionListener, PingFailedListener, ChatMessageListener, ChatManagerListener,
    RosterListener {
    private var mOwnAvatar: ByteArray? = null
    private var mConnection: XMPPTCPConnection? = null
    private var fileTransferManager: FileTransferManager? = null

    val builder = XMPPTCPConnectionConfiguration.builder()
    private val mDatabase by lazy {
        AppDatabase.getInstance(context)
    }


    @Throws(IOException::class, XMPPException::class, SmackException::class)
    fun connect() {
        if (mConnection == null) {
            createConnection()
        }
        if (!mConnection!!.isConnected) {
            Thread {
                try {
                info(TAG, "Connecting to " + mAccount.host + ":" + mAccount.port)
                mConnection!!.connect()
                val roster = Roster.getInstanceFor(mConnection)
                roster.subscriptionMode = Roster.SubscriptionMode.accept_all
                roster.isRosterLoadedAtLogin = true
                roster.removeRosterListener(this)
                roster.addRosterListener(this)
                if (!mConnection!!.isAuthenticated) {
                    info(TAG, "Authenticating " + mAccount.xmppJid)
                    try {
                        mConnection!!.login()
                        setName(mAccount.firstName, mAccount.lastName)
                        getOfflineMsg()

                        //mConnection!!.login(mAccount.xmppJid, mAccount.password)
                    } catch (e: Exception) {
                        try {
                            val accountManager = AccountManager.getInstance(mConnection)
                            accountManager.sensitiveOperationOverInsecureConnection(true)
                            accountManager.createAccount(
                                mAccount.xmppJid,
                                mAccount.password
                            )
                            mConnection!!.login()
                            setName(mAccount.firstName, mAccount.lastName)
                            getOfflineMsg()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        /*setPresence(
                            Presence.Mode.available.ordinal,
                            Presence.Mode.available.ordinal,
                            "test"
                        )*/
                    }


                    fileTransferManager = FileTransferManager.getInstanceFor(mConnection)
                    OutgoingFileTransfer.setResponseTimeout(30000);


                    var reconnectionManager = ReconnectionManager.getInstanceFor(mConnection)
                    reconnectionManager.enableAutomaticReconnection()
                    reconnectionManager.setFixedDelay(600)



                    PingManager.setDefaultPingInterval(XmppService.DEFAULT_PING_INTERVAL)
                    val pingManager = PingManager.getInstanceFor(mConnection)
                    pingManager.registerPingFailedListener(this)
                    val chatManager = ChatManager.getInstanceFor(mConnection)
                    chatManager.removeChatListener(this)
                    chatManager.addChatListener(this)

                    val receipts = DeliveryReceiptManager.getInstanceFor(mConnection)
                    receipts.autoReceiptMode = DeliveryReceiptManager.AutoReceiptMode.always
                    receipts.autoAddDeliveryReceiptRequests()
                }
                mOwnAvatar = getAvatarFor("")
                }catch (e: Exception){}
            }.start()


        }

    }

    fun disconnect() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                //mDatabase.chatDao().setAllUserToOffline()
                mConnection!!.disconnect()
            }
        }
    }

    private fun setOffline() {
        mConnection?.sendStanza(Presence(Presence.Type.unavailable));
    }

    private fun getOfflineMsg() {
        val mOfflineMessageManager = OfflineMessageManager(mConnection)
        var messageList = mOfflineMessageManager.messages
        mConnection?.sendStanza(Presence(Presence.Type.available));

        if (messageList.isNotEmpty()) {
            messageList.forEach { message ->
                val msg_xml = message.toXML().toString()
                if (msg_xml.contains(ChatState.composing.toString())) {
                    Log.e("is typing", "typing")
                    broadcastTyping(message.from)
                } else if (msg_xml.contains(ChatState.paused.toString())) {
                    Log.e("is typing", "stoped typing")
                    broadcastTypingStop(message.from)
                } else if (msg_xml.contains(ChatState.gone.toString())) {

                    if (message.type == Message.Type.chat || message.type == Message.Type.normal) {
                        if (message.body != null) {
                            Log.e("message", "message")
                            if (message.subject.equals("image")) {
                                saveMessage(message.from, message.body, true, true, "image")
                            } else if (message.subject.equals("voice")) {
                                saveMessage(message.from, message.body, true, true, "voice")
                            } else {
                                saveMessage(message.from, message.body, true, false, "text")
                            }

                        }
                    }
                }
            }
        }


    }

    private fun createConnection() {
        Log.e(TAG, "creating new connection to " + mAccount.host + ":" + mAccount.port)
        builder
            .setSocketFactory(SocketFactory.getDefault())
            .setServiceName(mAccount.serviceName)
            .setResource(mAccount.resourceName)
            .setHost(mAccount.host)
            .setPort(mAccount.port)
            .setSendPresence(false)
            .setUsernameAndPassword(mAccount.xmppJid, mAccount.password)
            .setCompressionEnabled(false)
            .setConnectTimeout(XmppService.CONNECT_TIMEOUT)
            .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
        //SASLAuthentication.unBlacklistSASLMechanism("PLAIN");
        //SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");

        mConnection = XMPPTCPConnection(builder.build())
        mConnection!!.setUseStreamManagement(XmppService.USE_STREAM_MANAGEMENT);
        mConnection!!.setUseStreamManagementResumption(XmppService.USE_STREAM_MANAGEMENT);
        mConnection!!.setPreferredResumptionTime(XmppService.STREAM_MANAGEMENT_RESUMPTION_TIME);

        mConnection!!.addConnectionListener(this)
    }

    fun singleEntryUpdated(entry: String?) {
        if (entry == null || entry.isEmpty()) return
        val entries: MutableList<String> = ArrayList(1)
        entries.add(entry)
        entriesUpdated(entries)
    }

    @Throws(NotConnectedException::class)
    fun addMessageAndProcessPending(destinationJID: String, message: String) {
        saveMessage(destinationJID, message, false, false, "text")
        sendPendingMessages()
    }

    private fun saveMessage(
        remoteAccount: String,
        messageText: String,
        incoming: Boolean,
        isFile: Boolean = false,
        messageType: String
    ) {
        try {

            val message = com.swipefwd.xmpp.database.Message()
            val chatUsers = getChatUsers(mAccount.xmppJid.toString(), remoteAccount)
            Log.e("chatUser", chatUsers)
            message.chatUsers = chatUsers
            if (isFile) {

                if (messageType == "image") {
                    message.messageType = "image"
                } else if (messageType == "voice") {
                    message.messageType = "voice"
                }

            } else {
                message.messageType = "text"
            }
            if (incoming) {
                message.message = messageText
                message.recipient = mAccount.xmppJid.toString()
                message.sender = remoteAccount
                message.timeStamp = System.currentTimeMillis()
                mDatabase.chatDao().insertMessage(message)
            } else {
                message.message = messageText
                message.recipient = remoteAccount
                message.sender = mAccount.xmppJid.toString()
                message.pending = 1
                message.timeStamp = System.currentTimeMillis()

                mDatabase.chatDao().insertMessage(message)
            }
            if (incoming) {
                singleEntryUpdated(remoteAccount);
            }

            broadcastMessageAdded(remoteAccount, incoming, message)

        } catch (exc: Exception) {
            error(
                TAG, "Error while saving " + (if (incoming) "incoming" else "outgoing") +
                        " message " + (if (incoming) "from " else "to ") + remoteAccount, exc
            )
        }
    }

    @Throws(NotConnectedException::class)
    private fun sendMessage(
        destinationJID: String,
        textMessage: String,
        databaseMessage: com.swipefwd.xmpp.database.Message
    ) {
        debug(TAG, "Sending message to $destinationJID")
        val chat = ChatManager.getInstanceFor(mConnection).createChat(destinationJID, this)
        var message = Message()
        message.to = destinationJID
        if (databaseMessage.messageType == "image") {
            message.subject = "image"
        } else if (databaseMessage.messageType == "voice") {
            message.subject = "voice"
        } else {
            message.subject = "text"
        }
        message.addExtension(ChatStateExtension(ChatState.gone))
        message.body = textMessage

        mConnection!!.sendStanza(message)
        //chat.sendMessage(message)
    }

    @Throws(NotConnectedException::class)
    fun sendPendingMessages() {

        debug(TAG, "Sending pending messages for " + mAccount.xmppJid)
        val list = mDatabase.chatDao().getPendingMessage()
        for (message in list) {
            message.recipient.let { sendMessage(it, message.message!!, message) }
            mDatabase.chatDao().updatePendingMessageFlag(message.id)
            broadcastMessageSent(message.id.toLong(), message)
        }

    }

    @Throws(NotConnectedException::class)
    fun sendTyping(destinationJID: String?) {

        val chat = ChatManager.getInstanceFor(mConnection).createChat(destinationJID, this)
        var message = Message()
        message.addExtension(ChatStateExtension(ChatState.composing))
        message.body = ""
        chat.sendMessage(message)
    }

    @Throws(NotConnectedException::class)
    fun sendTypingStop(destinationJID: String?) {

        val chat = ChatManager.getInstanceFor(mConnection).createChat(destinationJID, this)
        var message = Message()
        message.addExtension(ChatStateExtension(ChatState.paused))
        message.body = ""
        chat.sendMessage(message)
    }


    fun rosterToActivity() {

        broadcastRoasterList(rosterEntries)


    }


    fun setPresence(presenceMode: Int, presenceType: Int, personalMessage: String?) {
        mAccount.presenceMode = presenceMode
        mAccount.presenceType = presenceType
        mAccount.personalMessage = personalMessage
        setPresence()
    }


    fun setPresence() {
        Log.e("presence mode", mAccount.presenceMode.toString())
        val presMode: Presence.Mode = when (mAccount.presenceMode) {
            XmppAccount.PRESENCE_MODE_CHAT -> Presence.Mode.chat
            XmppAccount.PRESENCE_MODE_AVAILABLE -> Presence.Mode.available
            XmppAccount.PRESENCE_MODE_AWAY -> Presence.Mode.away
            XmppAccount.PRESENCE_MODE_XA -> Presence.Mode.xa
            XmppAccount.PRESENCE_MODE_DND -> Presence.Mode.dnd
            else -> Presence.Mode.available
        }
        val presType: Presence.Type = when (mAccount.presenceType) {
            0 -> Presence.Type.available
            1 -> Presence.Type.unavailable
            else -> Presence.Type.unavailable
        }

    }

    @Throws(NotConnectedException::class, XMPPErrorException::class, NoResponseException::class)
    fun setOwnAvatar(filePath: String?, firstName: String?, lastName: String?) {
        val manager = VCardManager.getInstanceFor(mConnection)
        var vcard: VCard? = null
        try {
            vcard = manager.loadVCard()
        } catch (exc: Exception) {
        }
        if (vcard == null) {
            vcard = VCard()
        }
        vcard.firstName = firstName
        vcard.lastName = lastName
        val newAvatar = BitmapFactory.decodeFile(filePath)
        val scaled = getScaledBitmap(newAvatar, 128)
        mOwnAvatar = getBytes(scaled)
        vcard.avatar = mOwnAvatar
        manager.saveVCard(vcard)
        var file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
    }

    @Throws(NotConnectedException::class, XMPPErrorException::class, NoResponseException::class)
    fun setName(firstName: String?, lastName: String?) {
        val manager = VCardManager.getInstanceFor(mConnection)
        var vcard: VCard? = null
        try {
            vcard = manager.loadVCard()
        } catch (exc: Exception) {
        }
        if (vcard == null) {
            vcard = VCard()
        }
        vcard.firstName = firstName
        vcard.lastName = lastName
        manager.saveVCard(vcard)
    }

    private fun getAvatarFor(remoteAccount: String?): ByteArray? {
        return try {
            val manager = VCardManager.getInstanceFor(mConnection)
            val data: ByteArray
            var card: VCard? = if (remoteAccount == null || remoteAccount.isEmpty()) {
                manager.loadVCard()
            } else {
                manager.loadVCard(remoteAccount)
            }
            if (card == null) {
                card = VCard()
            }
            data = card.avatar

            if (data != null && data.size > 0) {
                data
            } else null
        } catch (exc: Exception) {
            debug(TAG, "Can't get vCard for $remoteAccount")
            null
        }
    }

    private fun getUserInfo(remoteAccount: String?): VCard? {
        return try {
            val manager = VCardManager.getInstanceFor(mConnection)
            var card: VCard? = if (remoteAccount == null || remoteAccount.isEmpty()) {
                manager.loadVCard()
            } else {
                manager.loadVCard(remoteAccount)
            }
            if (card == null) {
                card = VCard()
            }
            return card
        } catch (exc: Exception) {
            debug(TAG, "Can't get vCard for $remoteAccount")
            null
        }
    }


    fun addContact(remoteAccount: String, alias: String?) {
        try {

            Roster.getInstanceFor(mConnection).createEntry(remoteAccount, alias, null)
            sendSubscriptionRequestTo(remoteAccount)
            rebuildRoster()
            broadcastContactAdded(remoteAccount)
        } catch (exc: Exception) {
            error(TAG, "Error while adding contact: $remoteAccount", exc)
            broadcastContactAddError(remoteAccount)
        }
    }

    fun removeContact(remoteAccount: String) {
        try {
            val roster = Roster.getInstanceFor(mConnection)
            val entry = roster.getEntry(remoteAccount)
            roster.removeEntry(entry)
            clearConversationsWith(remoteAccount)
            broadcastContactRemoved(remoteAccount)
        } catch (exc: Exception) {
            error(TAG, "Error while removing contact: $remoteAccount", exc)
        }
    }

    fun renameContact(remoteAccount: String, newAlias: String?) {
        try {
            val roster = Roster.getInstanceFor(mConnection)
            val entry = roster.getEntry(remoteAccount)
            entry.name = newAlias
            broadcastContactRenamed(remoteAccount, newAlias)
        } catch (exc: Exception) {
            error(TAG, "Error while renaming contact: $remoteAccount", exc)
        }
    }

    fun clearConversationsWith(remoteAccount: String) {
        try {

            debug(
                TAG, "Clearing conversations with " + remoteAccount
                        + " for " + mAccount.xmppJid
            )

            mDatabase.chatDao().deleteRoster(remoteAccount)
            mDatabase.chatDao().deleteAllMessageByUser(remoteAccount)
            broadcastConversationsCleared(remoteAccount)

        } catch (exc: Exception) {
            error(TAG, "Error while clearing conversations with $remoteAccount", exc)
            broadcastConversationsClearError(remoteAccount)
        }
    }

    // Connection listener implementation
    override fun connected(connection: XMPPConnection) {
        debug(TAG, "connected")
        setConnected(true)
    }

    override fun authenticated(connection: XMPPConnection, resumed: Boolean) {
        if (resumed) {
            debug(TAG, "authenticated after resumption")
        } else {
            debug(TAG, "authenticated")
        }
        setConnected(true)
        try {
            sendPendingMessages()
            Roster.getInstanceFor(mConnection).reload()
        } catch (exc: Exception) {
            XmppServiceCommand.connect(context, getXmppAccount())
            info(TAG, "Failed to automatically send pending messages on authentication")
        }
    }

    override fun connectionClosed() {
        setConnected(false)
        debug(TAG, "connection closed")

    }

    override fun connectionClosedOnError(e: Exception) {
        error(TAG, "connection closed on error", e)
        setConnected(false)
    }

    override fun reconnectionSuccessful() {
        debug(TAG, "reconnection is successful")
        setConnected(true)
        try {
            sendPendingMessages()
            Roster.getInstanceFor(mConnection).reload()
        } catch (exc: Exception) {
            info(TAG, "Failed to automatically send pending messages after reconnection")
        }
    }

    override fun reconnectingIn(seconds: Int) {
        debug(TAG, "Reconnection will happen in " + seconds + "s")
        setConnected(false)
    }

    override fun reconnectionFailed(e: Exception) {
        error(TAG, "reconnection failed", e)
        setConnected(false)
    }

    // Connection listener implementation
    override fun pingFailed() {
        try {
            XmppServiceCommand.connect(context, getXmppAccount())
            setConnected(true)
        } catch (e: Exception) {
            setConnected(false)
        }


    }

    override fun chatCreated(chat: Chat, createdLocally: Boolean) {
        debug(TAG, "chat created")
        chat.addMessageListener(this)
    }

    override fun processMessage(chat: Chat, message: Message) {
        Log.e("test", "hello")


        Log.e("from", message.from)
        val msg_xml = message.toXML().toString()
        if (msg_xml.contains(ChatState.composing.toString())) {
            Log.e("is typing", "typing")
            broadcastTyping(message.from)
        } else if (msg_xml.contains(ChatState.paused.toString())) {
            Log.e("is typing", "stoped typing")
            broadcastTypingStop(message.from)
        } else if (msg_xml.contains(ChatState.gone.toString())) {

            if (message.type == Message.Type.chat || message.type == Message.Type.normal) {
                if (message.body != null) {
                    Log.e("message", "message")
                    if (message.subject.equals("image")) {
                        saveMessage(message.from, message.body, true, true, "image")
                    } else if (message.subject.equals("voice")) {
                        saveMessage(message.from, message.body, true, true, "voice")
                    } else {
                        saveMessage(message.from, message.body, true, false, "text")
                    }

                }
            }
        }


    }


    override fun entriesAdded(addresses: Collection<String>) {


        for (destination in addresses) {
            var destinations = getXmppJid(destination)
            sendSubscriptionRequestTo(destinations)

        }

        //rebuildRoster()
        entriesUpdated(addresses)
    }

    override fun entriesUpdated(addresses: Collection<String>) {
        if (addresses == null || addresses.isEmpty()) {
            return
        }

        val roster = Roster.getInstanceFor(mConnection)
        if (roster == null) {
            info(TAG, "entriesUpdated - No roster instance, skipping rebuild roster")
            return
        }

        val entries = getRosterEntries()
        if (entries == null || entries.isEmpty()) {
            info(TAG, "entriesUpdated - No roster entries. Skipping rebuild roster")
            return
        }

        for (destination in addresses) {
            var destinations = getXmppJid(destination)
            val entry = roster.getEntry(destinations)
            val xmppRosterEntry = getRosterEntryFor(roster, entry)
            val index = entries.indexOf(xmppRosterEntry)
            if (index < 0) {
                entries.add(xmppRosterEntry)
            } else {
                entries[index] = xmppRosterEntry
            }
        }

        sort(entries)
        broadcastRosterChanged()
    }

    override fun entriesDeleted(addresses: Collection<String>) {
        if (addresses.isEmpty()) {
            return
        }
        for (destination in addresses) {
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    val destinations = getXmppJid(destination)
                    // sendUnsubscriptionRequestTo(destinations)
                    mDatabase.chatDao().deleteRoster(destinations)
                    val roster = Roster.getInstanceFor(mConnection)
                    if (roster == null) {
                        info(TAG, "presenceChanged - No roster instance, skipping rebuild roster")
                        return@withContext
                    }
                    val entries = rosterEntries
                    if (entries == null || entries.isEmpty()) {
                        info(TAG, "presenceChanged - No roster entries. Skipping rebuild roster")
                        return@withContext
                    }

                    val index = entries.indexOf(XmppRosterEntry().setXmppJID(destination))
                    if (index >= 0) {
                        entries.removeAt(index)
                    }
                }
            }
        }
        broadcastRosterChanged()
    }

    override fun presenceChanged(presence: Presence) {
        val roster = Roster.getInstanceFor(mConnection)
        if (roster == null) {
            info(TAG, "presenceChanged - No roster instance, skipping rebuild roster")
            return
        }
        val entries = rosterEntries
        if (entries == null || entries.isEmpty()) {
            info(TAG, "presenceChanged - No roster entries. Skipping rebuild roster")
            return
        }
        val from = getXmppJid(presence.from)
        val index = entries.indexOf(XmppRosterEntry().setXmppJID(from))
        if (index < 0) {
            info(TAG, "Presence from $from which is not in the roster. Skipping rebuild roster")
            return
        }

        val rosterPresence = roster.getPresence(from)
        Log.e("presence", rosterPresence.isAvailable.toString())
        Log.e("from", from)
        entries[index]
            .setAvailable(rosterPresence.isAvailable)
            .setPresenceMode(rosterPresence.mode.ordinal)
            .setPersonalMessage(rosterPresence.status)
        //sort(entries)
        broadcastRosterStatusChanged(entries[index])
    }

    // end roster listener implementation
    private fun getXmppJid(destination: String): String {
        return if (destination.contains("/")) {
            destination.split("/".toRegex()).toTypedArray()[0]
        } else destination
    }

    private val rosterEntries: ArrayList<XmppRosterEntry>?
        private get() {
            val entries = getRosterEntries()
            if (entries == null || entries.isEmpty()) {
                rebuildRoster();
            }
            return getRosterEntries()
        }

    private fun rebuildRoster() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                val roster = Roster.getInstanceFor(mConnection)
                if (roster == null) {
                    info(TAG, "no roster, skipping rebuild roster")
                    return@withContext
                }

                val entries = roster.entries
                val newRoster = ArrayList<XmppRosterEntry>(entries.size)
                for (entry in entries) {

                    val newEntry = getRosterEntryFor(roster, entry)
                    newRoster.add(newEntry)
                }
                newRoster.sort()
                setRosterEntries(newRoster)


                val list = ArrayList<Rosters>()
                newRoster.forEach {

                    val model = Rosters()
                    model.name = it.alias
                    model.avatar = it.avatar
                    model.xmppJid = it.xmppJID
                    model.type = it.alias
                    model.pendingMessageCount = it.unreadMessages.toInt()
                    model.isAvailable = it.isAvailable
                    model.personalMessage = it.personalMessage
                    model.presenceMode = it.presenceMode
                    list.add(model)

                }

                Log.e("list", list.size.toString())

                mDatabase.chatDao().deleteAllRosters()
                mDatabase.chatDao().insertRosters(list)
                broadcastRosterChanged()
            }
        }
    }

    private fun getRosterEntryFor(roster: Roster, entry: RosterEntry): XmppRosterEntry {
        val newEntry = XmppRosterEntry()
        newEntry.setXmppJID(entry.user)
            .setType(entry.type.name)
            .setAvatar(getCachedAvatar(entry.user))

        if (newEntry.avatar == null) {
            newEntry.setAvatar(getAvatarFor(entry.user))
        }

        val vCard = getUserInfo(entry.user)
        try {
            newEntry.setName(vCard?.firstName + " " + vCard?.lastName)
            newEntry.setAlias(vCard?.firstName + " " + vCard?.lastName)
        } catch (e: Exception) {

        }
        val presence = roster.getPresence(entry.user)
        Log.e("test", roster.toString())

        newEntry.setAvailable(presence.isAvailable)
            .setPresenceMode(presence.mode.ordinal)
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                newEntry.setUnreadMessages(
                    mDatabase.chatDao().getUnreadCountByUser(entry.user).size.toLong()

                )
            }
        }
        return newEntry
    }

    fun sendSubscriptionRequestTo(destination: String) {
        debug(TAG, "Sending subscription request to $destination")
        try {
            val subscribe = Presence(Presence.Type.subscribe)
            subscribe.to = destination
            mConnection!!.sendStanza(subscribe)
        } catch (exc: Exception) {
            error(TAG, "Can't send subscription request", exc)
        }
    }

    private fun sendUnsubscriptionRequestTo(destination: String) {
        debug(TAG, "Sending un-subscription request to $destination")
        try {
            val subscribe = Presence(Presence.Type.unsubscribe)
            subscribe.to = destination
            mConnection!!.sendStanza(subscribe)
        } catch (exc: Exception) {
            error(TAG, "Can't send subscription request", exc)
        }
    }

    private fun getCachedAvatar(xmppJID: String): ByteArray? {
        val rosterEntries = getRosterEntries()
        if (rosterEntries == null || rosterEntries.isEmpty()) return null
        val search = XmppRosterEntry().setXmppJID(xmppJID)
        val index = rosterEntries.indexOf(search)
        return if (index < 0) null else rosterEntries[index].avatar
    }

    companion object {
        private const val TAG = "connection"
    }


    public fun getRosterInfo(remoteAccount: String?): Rosters {
        val roster = Roster.getInstanceFor(mConnection)
        var destinations = getXmppJid(remoteAccount!!)
        val entry = roster.getEntry(destinations)
        val xmppRosterEntry = getRosterEntryFor(roster, entry)
        var model = Rosters()
        model.name = xmppRosterEntry.alias
        model.xmppJid = xmppRosterEntry.xmppJID
        model.type = xmppRosterEntry.alias
        model.pendingMessageCount = xmppRosterEntry.unreadMessages.toInt()
        model.isAvailable = xmppRosterEntry.isAvailable
        model.personalMessage = xmppRosterEntry.personalMessage
        model.presenceMode = xmppRosterEntry.presenceMode

        return model
    }


    public fun sendImage(xmppJID: String, filePath: String?) {
        saveMessage(xmppJID, filePath!!, false, true, "image")
        sendPendingMessages()

    }


    public fun sendVoice(xmppJID: String, filePath: String?) {
        saveMessage(xmppJID, filePath!!, false, true, "voice")
        sendPendingMessages()

    }


}