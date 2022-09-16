package com.swipefwd.xmpp;

import java.lang.System;

/**
 * Triggers xmpp service commands.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\"\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u00042\b\u0010/\u001a\u0004\u0018\u00010\u0004J\u0018\u00100\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0004J\u0018\u00101\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0004J\u0016\u00102\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010$\u001a\u00020%J\u0016\u00103\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u00104\u001a\u000205J\u000e\u00106\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0018\u00107\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0004J\u0018\u00108\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0004J\"\u00109\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u00042\b\u0010:\u001a\u0004\u0018\u00010\u0004J\"\u0010;\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u00042\b\u0010<\u001a\u0004\u0018\u00010\u0004J\"\u0010=\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u00042\b\u0010>\u001a\u0004\u0018\u00010\u0004J\u000e\u0010?\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u000e\u0010@\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0018\u0010A\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0004J\u0018\u0010B\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0004J\"\u0010C\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u00042\b\u0010<\u001a\u0004\u0018\u00010\u0004J\u0018\u0010D\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010E\u001a\u0004\u0018\u00010\u0004J\"\u0010F\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010G\u001a\u0004\u0018\u00010\u00042\b\u0010H\u001a\u0004\u0018\u00010\u0004J(\u0010I\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020K2\b\u0010M\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020%X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)\u00a8\u0006N"}, d2 = {"Lcom/swipefwd/xmpp/XmppServiceCommand;", "", "()V", "ACTION_ADD_CONTACT", "", "ACTION_CLEAR_CONVERSATIONS", "ACTION_CONNECT", "ACTION_DELETE_MESSAGE", "ACTION_DISCONNECT", "ACTION_REFRESH_CONTACT", "ACTION_REGISTER", "ACTION_REMOVE_CONTACT", "ACTION_RENAME_CONTACT", "ACTION_SEND", "ACTION_SEND_IMAGE", "ACTION_SEND_PENDING_MESSAGES", "ACTION_SEND_ROSTER", "ACTION_SEND_VOICE", "ACTION_SET_AVATAR", "ACTION_SET_NAME", "ACTION_SET_PRESENCE", "ACTION_STOP_TYPING", "ACTION_SUBSCRIPTION", "ACTION_TYPING", "PARAM_ACCOUNT", "PARAM_ALIAS", "PARAM_AVATAR_PATH", "PARAM_FIRST_NAME", "PARAM_IMAGE", "PARAM_LAST_NAME", "PARAM_MESSAGE", "PARAM_MESSAGE_ID", "PARAM_PERSONAL_MESSAGE", "PARAM_PRESENCE_MODE", "PARAM_PRESENCE_TYPE", "PARAM_REMOTE_ACCOUNT", "account", "Lcom/swipefwd/xmpp/XmppAccount;", "getAccount", "()Lcom/swipefwd/xmpp/XmppAccount;", "setAccount", "(Lcom/swipefwd/xmpp/XmppAccount;)V", "addContactToRoster", "", "context", "Landroid/content/Context;", "remoteAccount", "alias", "addSendSubscription", "clearConversations", "connect", "deleteMessage", "messageId", "", "disconnect", "refreshContact", "removeContactFromRoster", "renameContact", "newAlias", "sendImage", "filePath", "sendMessage", "message", "sendPendingMessages", "sendRosterToActivity", "sendTyping", "sendTypingStop", "sendVoice", "setAvatar", "avatarPath", "setName", "firstName", "lastName", "setPresence", "preseceType", "", "presenceMode", "personalMsg", "app_debug"})
public final class XmppServiceCommand {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.xmpp.XmppServiceCommand INSTANCE = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CONNECT = ".xmpp.connect";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_DISCONNECT = ".xmpp.disconnect";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SEND = ".xmpp.send";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_ADD_CONTACT = ".xmpp.addcontact";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_REMOVE_CONTACT = ".xmpp.removecontact";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CLEAR_CONVERSATIONS = ".xmpp.clearconversations";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_RENAME_CONTACT = ".xmpp.renamecontact";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_REFRESH_CONTACT = ".xmpp.refreshcontact";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SEND_PENDING_MESSAGES = ".xmpp.sendpending";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SET_AVATAR = ".xmpp.setavatar";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SET_NAME = ".xmpp.setname";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_DELETE_MESSAGE = ".xmpp.deletemessage";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SET_PRESENCE = ".xmpp.setpresence";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SEND_ROSTER = ".xmpp.sendRoster";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SUBSCRIPTION = ".xmpp.subscription";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_TYPING = ".xmpp.typing";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP_TYPING = ".xmpp.typing_stop";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SEND_IMAGE = ".xmpp.send_image";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_SEND_VOICE = ".xmpp.send_voice";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_REGISTER = ".xmpp.register";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_ACCOUNT = "account";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_REMOTE_ACCOUNT = "remoteAccount";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_IMAGE = "imagePath";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_MESSAGE = "message";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_MESSAGE_ID = "message_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_ALIAS = "alias";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_PRESENCE_MODE = "presenceMode";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_PRESENCE_TYPE = "presenceType";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_PERSONAL_MESSAGE = "personalMessage";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_AVATAR_PATH = "avatarPath";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_FIRST_NAME = "firstName";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_LAST_NAME = "lastName";
    public static com.swipefwd.xmpp.XmppAccount account;
    
    private XmppServiceCommand() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppAccount getAccount() {
        return null;
    }
    
    public final void setAccount(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.XmppAccount p0) {
    }
    
    /**
     * Triggers service connection.
     * @param context application context
     */
    public final void connect(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.XmppAccount account) {
    }
    
    /**
     * Triggers disconnection.
     * @param context application context
     */
    public final void disconnect(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * Sends a message to a remote account.
     * @param context application context
     * @param remoteAccount xmpp JID
     * @param message message
     */
    public final void sendMessage(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String message) {
    }
    
    /**
     * Deletes a message from the XMPP service's database.
     * @param context application context
     * @param messageId unique id of the message to delete
     */
    public final void deleteMessage(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long messageId) {
    }
    
    /**
     * Adds a new contact to currently connected user's roster.
     * @param context application context
     * @param remoteAccount xmpp JID to add
     * @param alias alis to give to the xmpp JID
     */
    public final void addContactToRoster(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String alias) {
    }
    
    public final void addSendSubscription(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    /**
     * Removes a contact from the currently connected user's roster.
     * @param context application context
     * @param remoteAccount xmpp JID to remove
     */
    public final void removeContactFromRoster(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    /**
     * Gives a new alias to an existing xmpp JID which is conained in the current user's roster.
     * @param context application context
     * @param remoteAccount xmpp JID
     * @param newAlias new alias to give to the xmpp JID
     */
    public final void renameContact(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String newAlias) {
    }
    
    /**
     * Refreshes a contact in the roster.
     * @param context application context
     * @param remoteAccount xmpp JID to refresh
     */
    public final void refreshContact(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    /**
     * Deletes all the messages exchanged with a given xmpp JID.
     * @param context application context
     * @param remoteAccount xmpp JID
     */
    public final void clearConversations(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    /**
     * Triggers the sending of the pending messages.
     * @param context application context
     */
    public final void sendPendingMessages(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * Sets the avatar for the currently logged in user.
     * @param context application context
     * @param avatarPath absolute path to the file which contains the new avatar
     */
    public final void setAvatar(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String avatarPath) {
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String firstName, @org.jetbrains.annotations.Nullable()
    java.lang.String lastName) {
    }
    
    /**
     * Sets the presence for the currently logged in user.
     * @param context application context
     * @param presenceMode presence mode to assign
     * @param personalMsg personal message
     */
    public final void setPresence(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int preseceType, int presenceMode, @org.jetbrains.annotations.Nullable()
    java.lang.String personalMsg) {
    }
    
    public final void sendRosterToActivity(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void sendTyping(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    public final void sendTypingStop(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    public final void sendImage(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String filePath) {
    }
    
    public final void sendVoice(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String filePath) {
    }
}