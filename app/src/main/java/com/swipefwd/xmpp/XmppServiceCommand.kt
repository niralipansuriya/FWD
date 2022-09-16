package com.swipefwd.xmpp

import android.content.Context
import android.content.Intent
import com.swipefwd.xmpp.XmppService

/**
 * Triggers xmpp service commands.
 */
object XmppServiceCommand {
    internal const val ACTION_CONNECT = ".xmpp.connect"
    internal const val ACTION_DISCONNECT = ".xmpp.disconnect"
    internal const val ACTION_SEND = ".xmpp.send"
    internal const val ACTION_ADD_CONTACT = ".xmpp.addcontact"
    internal const val ACTION_REMOVE_CONTACT = ".xmpp.removecontact"
    internal const val ACTION_CLEAR_CONVERSATIONS = ".xmpp.clearconversations"
    internal const val ACTION_RENAME_CONTACT = ".xmpp.renamecontact"
    internal const val ACTION_REFRESH_CONTACT = ".xmpp.refreshcontact"
    internal const val ACTION_SEND_PENDING_MESSAGES = ".xmpp.sendpending"
    internal const val ACTION_SET_AVATAR = ".xmpp.setavatar"
    internal const val ACTION_SET_NAME = ".xmpp.setname"
    internal const val ACTION_DELETE_MESSAGE = ".xmpp.deletemessage"
    internal const val ACTION_SET_PRESENCE = ".xmpp.setpresence"
    internal const val ACTION_SEND_ROSTER = ".xmpp.sendRoster"
    internal const val ACTION_SUBSCRIPTION = ".xmpp.subscription"
    internal const val ACTION_TYPING = ".xmpp.typing"
    internal const val ACTION_STOP_TYPING = ".xmpp.typing_stop"
    internal const val ACTION_SEND_IMAGE = ".xmpp.send_image"
    internal const val ACTION_SEND_VOICE = ".xmpp.send_voice"
    internal const val ACTION_REGISTER = ".xmpp.register"
    internal const val PARAM_ACCOUNT = "account"
    internal const val PARAM_REMOTE_ACCOUNT = "remoteAccount"
    internal const val PARAM_IMAGE = "imagePath"
    internal const val PARAM_MESSAGE = "message"
    internal const val PARAM_MESSAGE_ID = "message_id"
    internal const val PARAM_ALIAS = "alias"
    internal const val PARAM_PRESENCE_MODE = "presenceMode"
    internal const val PARAM_PRESENCE_TYPE = "presenceType"
    internal const val PARAM_PERSONAL_MESSAGE = "personalMessage"
    internal const val PARAM_AVATAR_PATH = "avatarPath"
    internal const val PARAM_FIRST_NAME = "firstName"
    internal const val PARAM_LAST_NAME = "lastName"

    lateinit var account: XmppAccount

    /**
     * Triggers service connection.
     * @param context application context
     */
    fun connect(context: Context, account: XmppAccount) {
        this.account = account
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_CONNECT
        intent.putExtra(PARAM_ACCOUNT, account.toString())
        context.startService(intent)
    }

    /**
     * Triggers disconnection.
     * @param context application context
     */
    fun disconnect(context: Context) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_DISCONNECT
        context.startService(intent)
    }

    /**
     * Sends a message to a remote account.
     * @param context application context
     * @param remoteAccount xmpp JID
     * @param message message
     */
    fun sendMessage(context: Context, remoteAccount: String?, message: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_SEND
        intent.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        intent.putExtra(PARAM_MESSAGE, message)
        context.startService(intent)
    }

    /**
     * Deletes a message from the XMPP service's database.
     * @param context application context
     * @param messageId unique id of the message to delete
     */
    fun deleteMessage(context: Context, messageId: Long) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_DELETE_MESSAGE
        intent.putExtra(PARAM_MESSAGE_ID, messageId)
        context.startService(intent)
    }

    /**
     * Adds a new contact to currently connected user's roster.
     * @param context application context
     * @param remoteAccount xmpp JID to add
     * @param alias alis to give to the xmpp JID
     */
    fun addContactToRoster(
        context: Context, remoteAccount: String?,
        alias: String?
    ) {
        val addContact = Intent(context, XmppService::class.java)
        addContact.action = ACTION_ADD_CONTACT
        addContact.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        addContact.putExtra(PARAM_ALIAS, alias)
        context.startService(addContact)
    }


    fun addSendSubscription(context: Context, remoteAccount: String?) {
        val addContact = Intent(context, XmppService::class.java)
        addContact.action = ACTION_SUBSCRIPTION
        addContact.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        context.startService(addContact)
    }

    /**
     * Removes a contact from the currently connected user's roster.
     * @param context application context
     * @param remoteAccount xmpp JID to remove
     */
    fun removeContactFromRoster(context: Context, remoteAccount: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_REMOVE_CONTACT
        intent.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        context.startService(intent)
    }

    /**
     * Gives a new alias to an existing xmpp JID which is conained in the current user's roster.
     * @param context application context
     * @param remoteAccount xmpp JID
     * @param newAlias new alias to give to the xmpp JID
     */
    fun renameContact(context: Context, remoteAccount: String?, newAlias: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_RENAME_CONTACT
        intent.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        intent.putExtra(PARAM_ALIAS, newAlias)
        context.startService(intent)
    }

    /**
     * Refreshes a contact in the roster.
     * @param context application context
     * @param remoteAccount xmpp JID to refresh
     */
    fun refreshContact(context: Context, remoteAccount: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_REFRESH_CONTACT
        intent.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        context.startService(intent)
    }

    /**
     * Deletes all the messages exchanged with a given xmpp JID.
     * @param context application context
     * @param remoteAccount xmpp JID
     */
    fun clearConversations(context: Context, remoteAccount: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_CLEAR_CONVERSATIONS
        intent.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        context.startService(intent)
    }

    /**
     * Triggers the sending of the pending messages.
     * @param context application context
     */
    fun sendPendingMessages(context: Context) {
        val intent = Intent(
            context, XmppService::class.java
        )
        intent.action = ACTION_SEND_PENDING_MESSAGES
        context.startService(intent)
    }

    /**
     * Sets the avatar for the currently logged in user.
     * @param context application context
     * @param avatarPath absolute path to the file which contains the new avatar
     */
    fun setAvatar(context: Context, avatarPath: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_SET_AVATAR
        intent.putExtra(PARAM_AVATAR_PATH, avatarPath)
        context.startService(intent)
    }

    fun setName(context: Context, firstName: String?, lastName: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_SET_NAME
        intent.putExtra(PARAM_FIRST_NAME, firstName)
        intent.putExtra(PARAM_LAST_NAME, lastName)
        context.startService(intent)
    }

    /**
     * Sets the presence for the currently logged in user.
     * @param context application context
     * @param presenceMode presence mode to assign
     * @param personalMsg personal message
     */
    fun setPresence(context: Context, preseceType: Int, presenceMode: Int, personalMsg: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_SET_PRESENCE
        intent.putExtra(PARAM_PRESENCE_MODE, presenceMode)
        intent.putExtra(PARAM_PRESENCE_TYPE, preseceType)
        intent.putExtra(PARAM_PERSONAL_MESSAGE, personalMsg)
        context.startService(intent)
    }


    fun sendRosterToActivity(context: Context) {
        val intent = Intent(context, XmppService::class.java)
        intent.action = ACTION_SEND_ROSTER
        context.startService(intent)
    }


    fun sendTyping(context: Context, remoteAccount: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        intent.action = ACTION_TYPING
        context.startService(intent)
    }

    fun sendTypingStop(context: Context, remoteAccount: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        intent.action = ACTION_STOP_TYPING
        context.startService(intent)
    }


    fun sendImage(context: Context, remoteAccount: String?, filePath: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        intent.putExtra(PARAM_IMAGE, filePath)
        intent.action = ACTION_SEND_IMAGE
        context.startService(intent)
    }
    fun sendVoice(context: Context, remoteAccount: String?, filePath: String?) {
        val intent = Intent(context, XmppService::class.java)
        intent.putExtra(PARAM_REMOTE_ACCOUNT, remoteAccount)
        intent.putExtra(PARAM_IMAGE, filePath)
        intent.action = ACTION_SEND_VOICE
        context.startService(intent)
    }


}