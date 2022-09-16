package com.swipefwd.xmpp

import android.content.Context
import android.content.Intent
import com.swipefwd.xmpp.database.Message
import java.util.*

/**
 * Emits the xmpp service broadcast intents.
 */
object XmppServiceBroadcastEventEmitter {
    const val BROADCAST_CONNECTED = ".xmpp.connected"
    const val BROADCAST_DISCONNECTED = ".xmpp.disconnected"
    const val BROADCAST_ROSTER_CHANGED = ".xmpp.rosterchanged"
    const val BROADCAST_ROSTER_STATUS_CHANGED = ".xmpp.rosterstatuschanged"
    const val BROADCAST_MESSAGE_ADDED = ".xmpp.messageadded"
    const val BROADCAST_MESSAGE_DELETED = ".xmpp.messagedeleted"
    const val BROADCAST_CONTACT_ADDED = ".xmpp.contactadded"
    const val BROADCAST_CONTACT_ADD_ERROR = ".xmpp.contactadderror"
    const val BROADCAST_CONTACT_REMOVED = ".xmpp.contactremoved"
    const val BROADCAST_CONVERSATIONS_CLEARED = ".xmpp.conversationscleared"
    const val BROADCAST_CONVERSATIONS_CLEAR_ERROR = ".xmpp.conversationsclearerror"
    const val BROADCAST_CONTACT_RENAMED = ".xmpp.contactrenamed"
    const val BROADCAST_MESSAGE_SENT = ".xmpp.messagesent"
    const val BROADCAST_ROSTER_LIST = ".xmpp.rosterList"
    const val BROADCAST_TYPING = ".xmpp.typing"
    const val BROADCAST_TYPING_STOP = ".xmpp.typing_stop"
    const val PARAM_REMOTE_ACCOUNT = "remoteAccount"
    const val PARAM_ALIAS = "alias"
    const val PARAM_MESSAGE_ID = "messageId"
    const val PARAM_ROSTERS_LIST = "rosterList"
    const val PARAM_INCOMING = "incoming"
    const val PARAM_FROM = "from"
    const val PARAM_MESSAGE = "message"
    const val PARAM_TYPING = "typing"
    const val PARAM_TYPING_STOP = "stop"
    private var mContext: Context? = null
    var namespace: String? = null
        private set

    @Synchronized
    fun initialize(context: Context?, namespace: String?) {
        mContext = context
        XmppServiceBroadcastEventEmitter.namespace = namespace
    }

    @JvmStatic
    @Synchronized
    fun broadcastRosterChanged() {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_ROSTER_CHANGED
        mContext!!.sendBroadcast(broadcast)
    }


    @JvmStatic
    @Synchronized
    fun broadcastRosterStatusChanged(xmppRosterEntry: XmppRosterEntry) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_ROSTER_STATUS_CHANGED
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, xmppRosterEntry)
        mContext!!.sendBroadcast(broadcast)
    }


    @Synchronized
    fun broadcastXmppConnected() {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_CONNECTED
        mContext!!.sendBroadcast(broadcast)
    }

    @Synchronized
    fun broadcastXmppDisconnected() {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_DISCONNECTED
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastMessageAdded(remoteAccount: String?, incoming: Boolean, message: Message) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_MESSAGE_ADDED
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        broadcast.putExtra(PARAM_INCOMING, incoming)
        broadcast.putExtra(PARAM_MESSAGE, message)
        mContext!!.sendBroadcast(broadcast)
    }

    @Synchronized
    fun broadcastMessageDeleted(messageId: Long) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_MESSAGE_DELETED
        broadcast.putExtra(PARAM_MESSAGE_ID, messageId)
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastContactAdded(remoteAccount: String?) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_CONTACT_ADDED
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastContactAddError(remoteAccount: String?) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_CONTACT_ADD_ERROR
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastContactRemoved(remoteAccount: String?) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_CONTACT_REMOVED
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastConversationsCleared(remoteAccount: String?) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_CONVERSATIONS_CLEARED
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastConversationsClearError(remoteAccount: String?) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_CONVERSATIONS_CLEAR_ERROR
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastContactRenamed(remoteAccount: String?, newAlias: String?) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_CONTACT_RENAMED
        broadcast.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        broadcast.putExtra(PARAM_ALIAS, newAlias)
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastMessageSent(messageId: Long, message: Message) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_MESSAGE_SENT
        broadcast.putExtra(PARAM_MESSAGE_ID, messageId)
        broadcast.putExtra(PARAM_MESSAGE, message)
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastRoasterList(rosterList: ArrayList<XmppRosterEntry>?) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_ROSTER_LIST
        broadcast.putExtra(PARAM_ROSTERS_LIST, rosterList)
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastTyping(from: String) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_TYPING
        broadcast.putExtra(PARAM_FROM, from)
        mContext!!.sendBroadcast(broadcast)
    }

    @JvmStatic
    @Synchronized
    fun broadcastTypingStop(from: String) {
        val broadcast = Intent()
        broadcast.action =
            namespace + BROADCAST_TYPING_STOP
        broadcast.putExtra(PARAM_FROM, from)
        mContext!!.sendBroadcast(broadcast)
    }


}