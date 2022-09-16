package com.swipefwd.xmpp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log

import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_CONNECTED
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_CONTACT_ADDED
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_CONTACT_ADD_ERROR
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_CONTACT_REMOVED
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_CONTACT_RENAMED
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_CONVERSATIONS_CLEARED
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_CONVERSATIONS_CLEAR_ERROR
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_DISCONNECTED
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_MESSAGE_ADDED
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_MESSAGE_DELETED
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_MESSAGE_SENT
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_ROSTER_CHANGED
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_ROSTER_LIST
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_ROSTER_STATUS_CHANGED
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_TYPING
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.BROADCAST_TYPING_STOP
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.PARAM_ALIAS
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.PARAM_FROM
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.PARAM_INCOMING
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.PARAM_MESSAGE
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.PARAM_MESSAGE_ID
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.PARAM_REMOTE_ACCOUNT
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.PARAM_ROSTERS_LIST
import com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter.namespace
import com.swipefwd.xmpp.database.ChatListener
import com.swipefwd.xmpp.database.Message
import com.swipefwd.xmpp.database.Rosters
import java.util.ArrayList

/**
 * Broadcast receiver to sublass to create a receiver for
 * [XmppService] events.
 *
 * It provides the boilerplate code to properly handle broadcast messages coming from the
 * XMPP service and dispatch them to the proper handler method.
 */
open class XmppServiceBroadcastEventReceiver(var chatListener: ChatListener) : BroadcastReceiver() {

    lateinit var list: ArrayList<XmppRosterEntry>
    override fun onReceive(context: Context, intent: Intent) {
        if (intent == null) return
        var action = intent.action
        if (action == null) return
        action = action.replace(namespace!!, "")
        if (BROADCAST_CONNECTED == action) {
            onXmppConnected()
        } else if (BROADCAST_DISCONNECTED == action) {
            onXmppDisconnected()
        } else if (BROADCAST_ROSTER_CHANGED == action) {
            onRosterChanged()
        } else if (BROADCAST_MESSAGE_ADDED == action) {
            onMessageAdded(
                intent.getStringExtra(PARAM_REMOTE_ACCOUNT),
                intent.getSerializableExtra(PARAM_MESSAGE) as Message,
                intent.getBooleanExtra(PARAM_INCOMING, false)
            )
        }
        else if (BROADCAST_ROSTER_STATUS_CHANGED == action) {
            onStatusChanges(intent.getSerializableExtra(PARAM_REMOTE_ACCOUNT) as XmppRosterEntry?)
        }
        else if (BROADCAST_CONTACT_ADDED == action) {
            onContactAdded(intent.getStringExtra(PARAM_REMOTE_ACCOUNT))
        } else if (BROADCAST_CONTACT_ADD_ERROR == action) {
            onContactAddError(intent.getStringExtra(PARAM_REMOTE_ACCOUNT))
        } else if (BROADCAST_CONVERSATIONS_CLEARED == action) {
            onConversationsCleared(intent.getStringExtra(PARAM_REMOTE_ACCOUNT))
        } else if (BROADCAST_CONVERSATIONS_CLEAR_ERROR == action) {
            onConversationsClearError(intent.getStringExtra(PARAM_REMOTE_ACCOUNT))
        } else if (BROADCAST_TYPING == action) {
            onConversationTyping(intent.getStringExtra(PARAM_FROM))
        } else if (BROADCAST_TYPING_STOP == action) {
            onConversationTypingStop(intent.getStringExtra(PARAM_FROM))
        } else if (BROADCAST_CONTACT_RENAMED == action) {
            onContactRenamed(
                intent.getStringExtra(PARAM_REMOTE_ACCOUNT),
                intent.getStringExtra(PARAM_ALIAS)
            )
        } else if (BROADCAST_CONTACT_REMOVED == action) {
            onContactRemoved(intent.getStringExtra(PARAM_REMOTE_ACCOUNT))
        } else if (BROADCAST_MESSAGE_SENT == action) {
            val msgId = intent.getLongExtra(PARAM_MESSAGE_ID, -1)
            val message = intent.getSerializableExtra(PARAM_MESSAGE) as Message
            if (msgId > 0) {
                onMessageSent(msgId, message)
            }
        } else if (BROADCAST_MESSAGE_DELETED == action) {
            val msgId = intent.getLongExtra(PARAM_MESSAGE_ID, -1)
            if (msgId > 0) {
                onMessageDeleted(msgId)
            }
        } else if (BROADCAST_ROSTER_LIST == action) {
            try{
                var rosterList =
                    intent.getSerializableExtra(PARAM_ROSTERS_LIST) as ArrayList<XmppRosterEntry>
                if (rosterList.size > 0) {
                    setRosterList(rosterList)
                }
            }catch (e:Exception)
            {

            }

        }
    }

    private fun onConversationTypingStop(from: String?) {
        chatListener.onTypingStop(from)
    }

    private fun onConversationTyping(from: String?) {
        chatListener.onTyping(from)
    }
    private fun onStatusChanges(from: XmppRosterEntry?) {
        from?.let { chatListener.onRosterStatusChanges(it) }
    }

    /**
     * Register this receiver.<br></br>
     * If you use this receiver in an [android.app.Activity], you have to call this method inside
     * [android.app.Activity.onResume], after `super.onResume();`.<br></br>
     * If you use it in a [android.app.Service], you have to
     * call this method inside [android.app.Service.onCreate], after `super.onCreate();`.
     *
     * @param context context in which to register this receiver
     */
    fun register(context: Context) {
        val intentFilter = IntentFilter()
        val namespace = namespace
        intentFilter.addAction(namespace + BROADCAST_CONNECTED)
        intentFilter.addAction(namespace + BROADCAST_DISCONNECTED)
        intentFilter.addAction(namespace + BROADCAST_ROSTER_CHANGED)
        intentFilter.addAction(namespace + BROADCAST_ROSTER_STATUS_CHANGED)
        intentFilter.addAction(namespace + BROADCAST_MESSAGE_ADDED)
        intentFilter.addAction(namespace + BROADCAST_CONTACT_ADDED)
        intentFilter.addAction(namespace + BROADCAST_CONTACT_REMOVED)
        intentFilter.addAction(namespace + BROADCAST_CONTACT_ADD_ERROR)
        intentFilter.addAction(namespace + BROADCAST_CONVERSATIONS_CLEARED)
        intentFilter.addAction(namespace + BROADCAST_CONVERSATIONS_CLEAR_ERROR)
        intentFilter.addAction(namespace + BROADCAST_CONTACT_RENAMED)
        intentFilter.addAction(namespace + BROADCAST_MESSAGE_SENT)
        intentFilter.addAction(namespace + BROADCAST_MESSAGE_DELETED)
        intentFilter.addAction(namespace + BROADCAST_ROSTER_LIST)
        intentFilter.addAction(namespace + BROADCAST_TYPING_STOP)
        intentFilter.addAction(namespace + BROADCAST_TYPING)
        context.registerReceiver(this, intentFilter)
    }

    /**
     * Unregister this receiver.<br></br>
     * If you use this receiver in an [android.app.Activity], you have to call this method inside
     * [android.app.Activity.onPause], after `super.onPause();`.<br></br>
     * If you use it in a [android.app.Service], you have to
     * call this method inside [android.app.Service.onDestroy].
     *
     * @param context context in which to unregister this receiver
     */
    fun unregister(context: Context) {
        try {
            context.unregisterReceiver(this)
        } catch (ignored: Exception) {
        }
    }


    /**
     * Called when the XMPP connection to the server has been established.
     */
    fun onXmppConnected() {


    }

    /**
     * Called when the service is not connected to the XMPP server.
     */
    fun onXmppDisconnected() {}

    /**
     * Called when the roster has been changed (contacts added, removed or change of
     * presence status). To get the current roster, use: [XmppService.getRosterEntries]
     */
    fun onRosterChanged() {

        chatListener.onRosterChanged()

    }

    /**
     * Called when a message has been added to the conversation with a contact
     * (either scheduled for sending or received)
     * @param remoteAccount xmpp JID of the contact
     * @param incoming true if the message is incoming, false if it's scheduled for sending
     */
    open fun onMessageAdded(remoteAccount: String?, message: Message, incoming: Boolean) {

        chatListener.onMessageAdded(remoteAccount, message, incoming)

    }

    /**
     * Called when a contact has been successfully added to the roster, as a result of
     * [XmppServiceCommand.addContactToRoster]
     * @param remoteAccount xmpp JID of the contact
     */
    fun onContactAdded(remoteAccount: String?) {}

    /**
     * Called when a contact has been successfully removed from the roster, as a result of
     * [XmppServiceCommand.removeContactFromRoster]
     * @param remoteAccount xmpp JID of the contact
     */
    fun onContactRemoved(remoteAccount: String?) {


        chatListener.onRemoveContact(remoteAccount)
    }

    /**
     * Called when a contact has been successfully renamed in the roster, as a result of
     * [XmppServiceCommand.renameContact]
     * @param remoteAccount xmpp JID of the contact
     */
    fun onContactRenamed(remoteAccount: String?, newAlias: String?) {}

    /**
     * Called when an error happened while trying to add a contact to the roster.
     * @param remoteAccount xmpp JID of the contact
     */
    fun onContactAddError(remoteAccount: String?) {}

    /**
     * Called when all the messages exchanged (sent and received) with a contact have been deleted,
     * as a result of [XmppServiceCommand.clearConversations]
     * @param remoteAccount xmpp JID of the contact
     */
    open fun onConversationsCleared(remoteAccount: String?) {}

    /**
     * Called when an error happened during the deletion of the messages exchanged with a contact.
     * @param remoteAccount xmpp JID of the contact
     */
    fun onConversationsClearError(remoteAccount: String?) {}

    /**
     * Called when a message has been successfully sent.
     * @param messageId unique ID of the sent message
     */
    open fun onMessageSent(messageId: Long, message: Message) {

        chatListener.onMessageSent(messageId, message)

    }

    open fun onMessageDeleted(messageId: Long) {

        chatListener.onMessageDeleted(messageId)
    }


    open fun setRosterList(list: ArrayList<XmppRosterEntry>) {
        chatListener.setRosterList(list)
    }


}