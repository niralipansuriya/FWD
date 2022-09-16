package com.swipefwd.xmpp

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.swipefwd.xmpp.Logger.Companion.error
import com.swipefwd.xmpp.Logger.Companion.info
import com.swipefwd.xmpp.XmppAccount.Companion.fromJson
import org.jivesoftware.smack.SmackConfiguration
import java.util.*
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext

/**
 * Main XMPP Service.
 */
class XmppService : BackgroundService() {
    // end configurable values
    private var mPrefs: SharedPreferences? = null
    private var mConnection: XmppServiceConnection? = null
    lateinit var account: XmppAccount

    override fun onCreate() {
        super.onCreate()
        enqueueJob {
            mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            SmackConfiguration.setDefaultPacketReplyTimeout(PACKET_REPLY_TIMEOUT)
            info(TAG, "initializing database")

        }
    }

    override fun onDestroy() {
        info(TAG, "Destroying service")


        if (mConnection != null) {
            mConnection!!.disconnect()
            mConnection = null
        }
        connected = false
        rosterEntries = null
        super.onDestroy()
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        enqueueJob {
            when (intent.action) {
                XmppServiceCommand.ACTION_CONNECT -> {
                    handleConnect(fromJson(intent.getStringExtra(XmppServiceCommand.PARAM_ACCOUNT)))
                }
                XmppServiceCommand.ACTION_DISCONNECT -> {
                    handleDisconnect()
                }
                XmppServiceCommand.ACTION_SEND -> {
                    handleSendMessage(intent)
                }
                XmppServiceCommand.ACTION_DELETE_MESSAGE -> {
                    handleDeleteMessage(intent)
                }
                XmppServiceCommand.ACTION_SEND_PENDING_MESSAGES -> {
                    handleSendPendingMessages()
                }
                XmppServiceCommand.ACTION_ADD_CONTACT -> {
                    handleAddContact(intent)
                }
                XmppServiceCommand.ACTION_REMOVE_CONTACT -> {
                    handleRemoveContact(intent)
                }
                XmppServiceCommand.ACTION_RENAME_CONTACT -> {
                    handleRenameContact(intent)
                }
                XmppServiceCommand.ACTION_REFRESH_CONTACT -> {
                    handleRefreshContact(intent)
                }
                XmppServiceCommand.ACTION_CLEAR_CONVERSATIONS -> {
                    handleClearConversations(intent)
                }
                XmppServiceCommand.ACTION_SET_AVATAR -> {
                    handleSetAvatar(intent)
                }
                XmppServiceCommand.ACTION_SET_NAME -> {
                    handleName(intent)
                }
                XmppServiceCommand.ACTION_SET_PRESENCE -> {
                    handleSetPresence(intent)
                }
                XmppServiceCommand.ACTION_SEND_ROSTER -> {
                    sendRosterToActivity(intent)
                }
                XmppServiceCommand.ACTION_TYPING -> {
                    handleTyping(intent)
                }
                XmppServiceCommand.ACTION_STOP_TYPING -> {
                    handleTypingStop(intent)
                }
                XmppServiceCommand.ACTION_SUBSCRIPTION -> {
                    handleSubscription(intent)
                }
                XmppServiceCommand.ACTION_SEND_IMAGE -> {
                    handleSendImage(intent)
                }
                XmppServiceCommand.ACTION_SEND_VOICE -> {
                    handleSendVoice(intent)
                }
            }
        }
        return START_STICKY
    }

    private fun handleConnect(account: XmppAccount) {
        try {
            this.account = account
            if (mConnection != null) {
                mConnection!!.disconnect()
            }
            Log.e("account", account.xmppJid.toString())
            mConnection = XmppServiceConnection(account, this)
            mConnection!!.connect()
            mConnection!!.sendPendingMessages()
            mPrefs!!.edit().putString(KEY_CONFIGURED_ACCOUNT, account.toString()).apply()
        } catch (exc: Exception) {
            error(TAG, "Error while connecting", exc)
            XmppServiceBroadcastEventEmitter.broadcastXmppDisconnected()
        }
    }

    private fun handleDisconnect() {
        if (mConnection != null) {
            Log.e("discount", "discount")
            mConnection!!.disconnect()

        }
        mPrefs!!.edit().remove(KEY_CONFIGURED_ACCOUNT).apply()
        stopSelf()
    }

    private fun handleSendMessage(intent: Intent) {
        val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
        val message = intent.getStringExtra(XmppServiceCommand.PARAM_MESSAGE)
        try {

            if (mConnection != null) {
                mConnection!!.addMessageAndProcessPending(
                    remoteAccount!!,
                    message!!
                )
            } else {
                handleConnect(getXmppAccount())
                handleSendMessage(intent)
            }

        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleSendMessage(intent)
        }
    }

    private fun handleDeleteMessage(intent: Intent) {
        val messageID = intent.getLongExtra(XmppServiceCommand.PARAM_MESSAGE_ID, -1)
        try {
            //MessagesProvider(database).deleteMessage(messageID).execute()
            XmppServiceBroadcastEventEmitter.broadcastMessageDeleted(messageID)
        } catch (exc: Exception) {
            error(TAG, "Error while deleting message with ID=$messageID", exc)
        }
    }

    private fun handleSendPendingMessages() {
        try {
            if (mConnection != null) {
                mConnection!!.sendPendingMessages()
            } else {
                handleConnect(getXmppAccount())
                handleSendPendingMessages()
            }


        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleSendPendingMessages()
        }
    }


    private fun handleTyping(intent: Intent) {
        try {
            val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
            if (mConnection != null) {
                mConnection!!.sendTyping(remoteAccount)

            } else {
                handleConnect(getXmppAccount())
                handleTyping(intent)
            }


        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleTyping(intent)
        }
    }

    private fun handleTypingStop(intent: Intent) {
        try {
            val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
            if (mConnection != null) {
                mConnection!!.sendTypingStop(remoteAccount)
            } else {
                handleConnect(getXmppAccount())
                handleTypingStop(intent)
            }
        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleTypingStop(intent)
        }
    }

    private fun handleAddContact(intent: Intent) {
        val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
        val alias = intent.getStringExtra(XmppServiceCommand.PARAM_ALIAS)
        try {
            if (mConnection != null) {
                mConnection!!.addContact(remoteAccount!!, alias)
            } else {
                handleConnect(getXmppAccount())
                handleAddContact(intent)
            }
        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleAddContact(intent)
        }
    }


    private fun handleSubscription(intent: Intent) {
        val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
        try {
            if (mConnection != null) {
                mConnection!!.sendSubscriptionRequestTo(remoteAccount!!)
            } else {
                handleConnect(getXmppAccount())
                handleSubscription(intent)
            }


        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleSubscription(intent)
        }
    }

    private fun handleSendImage(intent: Intent) {
        val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
        val filePath = intent.getStringExtra(XmppServiceCommand.PARAM_IMAGE)
        try {
            if (mConnection != null) {
                mConnection!!.sendImage(remoteAccount!!, filePath)
            } else {
                handleConnect(getXmppAccount())
                handleSendImage(intent)
            }


        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleSendImage(intent)
        }
    }

    private fun handleSendVoice(intent: Intent) {
        val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
        val filePath = intent.getStringExtra(XmppServiceCommand.PARAM_IMAGE)
        try {
            if (mConnection != null) {
                mConnection!!.sendVoice(remoteAccount!!, filePath)
            } else {
                handleConnect(getXmppAccount())
                handleSendVoice(intent)
            }


        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleSendVoice(intent)
        }
    }

    private fun handleRemoveContact(intent: Intent) {
        val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
        try {
            if (mConnection != null) {
                mConnection!!.removeContact(remoteAccount!!)
            } else {
                handleConnect(getXmppAccount())
                handleRemoveContact(intent)
            }

        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleRemoveContact(intent)
        }
    }

    private fun handleRenameContact(intent: Intent) {
        val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
        val alias = intent.getStringExtra(XmppServiceCommand.PARAM_ALIAS)
        try {

            if (mConnection != null) {
                mConnection!!.renameContact(remoteAccount!!, alias)
            } else {
                handleConnect(getXmppAccount())
                handleRemoveContact(intent)
            }
        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleRemoveContact(intent)
        }
    }

    private fun handleRefreshContact(intent: Intent) {
        val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
        if (mConnection != null) {
            mConnection!!.singleEntryUpdated(remoteAccount)
        } else {
            handleConnect(getXmppAccount())
            handleRefreshContact(intent)
        }
    }

    private fun handleClearConversations(intent: Intent) {
        val remoteAccount = intent.getStringExtra(XmppServiceCommand.PARAM_REMOTE_ACCOUNT)
        if (mConnection != null) mConnection!!.clearConversationsWith(remoteAccount!!)
    }

    private fun handleSetAvatar(intent: Intent) {
        val avatarPath = intent.getStringExtra(XmppServiceCommand.PARAM_AVATAR_PATH)
        val firstName = intent.getStringExtra(XmppServiceCommand.PARAM_FIRST_NAME)
        val lastName = intent.getStringExtra(XmppServiceCommand.PARAM_LAST_NAME)
        try {

            if (mConnection != null) {
                mConnection!!.setOwnAvatar(avatarPath, firstName, lastName)
            } else {
                handleConnect(getXmppAccount())
                handleSetAvatar(intent)
            }

        } catch (exc: Exception) {
            //  handleConnect(getXmppAccount())
            //  handleSetAvatar(intent)
        }
    }

    private fun handleName(intent: Intent) {

        val firstName = intent.getStringExtra(XmppServiceCommand.PARAM_FIRST_NAME)
        val lastName = intent.getStringExtra(XmppServiceCommand.PARAM_LAST_NAME)
        try {

            if (mConnection != null) {
                mConnection!!.setName(firstName, lastName)
            } else {
                handleConnect(getXmppAccount())
                handleName(intent)
            }

        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleName(intent)
        }
    }

    private fun handleSetPresence(intent: Intent) {
        val presenceMode = intent.getIntExtra(XmppServiceCommand.PARAM_PRESENCE_MODE, -1)
        val presenceType = intent.getIntExtra(XmppServiceCommand.PARAM_PRESENCE_TYPE, -1)
        val personalMessage = intent.getStringExtra(XmppServiceCommand.PARAM_PERSONAL_MESSAGE)
        try {
            if (mConnection != null) {
                mConnection!!.setPresence(presenceMode, presenceType, personalMessage)
            } else {
                handleConnect(getXmppAccount())
                handleSetPresence(intent)
            }

        } catch (exc: Exception) {
            handleConnect(getXmppAccount())
            handleSetPresence(intent)
        }
    }

    private fun sendRosterToActivity(intent: Intent) {
        try {
            if (mConnection != null) {
                mConnection!!.rosterToActivity()
            } else {
                handleConnect(getXmppAccount())
                sendRosterToActivity(intent)
            }
        } catch (exc: Exception) {
          //  handleConnect(getXmppAccount())
          //  sendRosterToActivity(intent)
        }
    }

    companion object {
        private val TAG = XmppService::class.java.simpleName
        // configurable values
        /**
         * Name of the database used as a persistent storage for the exchanged messages.
         */
        const val DATABASE_NAME = "messages.db"

        /**
         * Name of the shared preferences file.
         */
        const val PREFS_NAME = "messages-prefs.conf"
        private const val KEY_CONFIGURED_ACCOUNT = "configuredAccount"

        /**
         * Connection connect timeout in milliseconds.
         */
        @JvmField
        var CONNECT_TIMEOUT = 20000000

        /**
         * Sets the number of milliseconds to wait for a response from
         * the server.
         */
        var PACKET_REPLY_TIMEOUT = 500000

        /**
         * Default ping interval.
         */
        @JvmField
        var DEFAULT_PING_INTERVAL = 600 //10 minutes;

        /**
         * Use stream management (XEP-0198).
         */
        @JvmField
        var USE_STREAM_MANAGEMENT = true

        /**
         * Stream management resumption time in seconds.
         */
        @JvmField
        var STREAM_MANAGEMENT_RESUMPTION_TIME = 30

        /**
         * Custom [SSLContext] to use for the connection.
         */
        @JvmField
        var CUSTOM_SSL_CONTEXT: SSLContext? = null

        /**
         * Custom [HostnameVerifier] to use for the connection.
         */
        @JvmField
        var CUSTOM_HOSTNAME_VERIFIER: HostnameVerifier? = null


        @Volatile
        private var connected = false

        @Volatile
        private var xmppAccount: XmppAccount? = null

        @Volatile
        private var rosterEntries: ArrayList<XmppRosterEntry>? = null

        @JvmStatic
        fun getRosterEntries(): ArrayList<XmppRosterEntry>? {
            synchronized(XmppService::class.java) { return rosterEntries }
        }

        @JvmStatic
        fun setRosterEntries(entries: ArrayList<XmppRosterEntry>?) {
            synchronized(XmppService::class.java) { rosterEntries = entries }
        }

        @Synchronized
        fun isConnected(): Boolean {
            return connected
        }

        @JvmStatic
        @Synchronized
        fun setConnected(newConnectedStatus: Boolean) {
            connected = newConnectedStatus
            if (newConnectedStatus) {
                XmppServiceBroadcastEventEmitter.broadcastXmppConnected()
            } else {
                XmppServiceBroadcastEventEmitter.broadcastXmppDisconnected()
            }
        }

        @JvmStatic
        fun setXmppAccount(tempXmppAccount: XmppAccount) {
            this.xmppAccount = tempXmppAccount
        }

        @JvmStatic
        fun getXmppAccount(): XmppAccount {
            return xmppAccount!!
        }


    }


}