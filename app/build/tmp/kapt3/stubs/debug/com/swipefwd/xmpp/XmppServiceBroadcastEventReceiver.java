package com.swipefwd.xmpp;

import java.lang.System;

/**
 * Broadcast receiver to sublass to create a receiver for
 * [XmppService] events.
 *
 * It provides the boilerplate code to properly handle broadcast messages coming from the
 * XMPP service and dispatch them to the proper handler method.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u0010\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u0010\u0010\u0014\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u001a\u0010\u0015\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0012J\u0012\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0012H\u0002J\u0012\u0010\u0019\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010\u001a\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u0012\u0010\u001b\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\"\u0010\u001c\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#H\u0016J\u0018\u0010$\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020)H\u0016J\u0006\u0010*\u001a\u00020\u0010J\u0012\u0010+\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\nH\u0002J\u0006\u0010,\u001a\u00020\u0010J\u0006\u0010-\u001a\u00020\u0010J\u000e\u0010.\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\'J\u0016\u0010/\u001a\u00020\u00102\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J\u000e\u00100\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\'R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u00061"}, d2 = {"Lcom/swipefwd/xmpp/XmppServiceBroadcastEventReceiver;", "Landroid/content/BroadcastReceiver;", "chatListener", "Lcom/swipefwd/xmpp/database/ChatListener;", "(Lcom/swipefwd/xmpp/database/ChatListener;)V", "getChatListener", "()Lcom/swipefwd/xmpp/database/ChatListener;", "setChatListener", "list", "Ljava/util/ArrayList;", "Lcom/swipefwd/xmpp/XmppRosterEntry;", "getList", "()Ljava/util/ArrayList;", "setList", "(Ljava/util/ArrayList;)V", "onContactAddError", "", "remoteAccount", "", "onContactAdded", "onContactRemoved", "onContactRenamed", "newAlias", "onConversationTyping", "from", "onConversationTypingStop", "onConversationsClearError", "onConversationsCleared", "onMessageAdded", "message", "Lcom/swipefwd/xmpp/database/Message;", "incoming", "", "onMessageDeleted", "messageId", "", "onMessageSent", "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "onRosterChanged", "onStatusChanges", "onXmppConnected", "onXmppDisconnected", "register", "setRosterList", "unregister", "app_debug"})
public class XmppServiceBroadcastEventReceiver extends android.content.BroadcastReceiver {
    @org.jetbrains.annotations.NotNull()
    private com.swipefwd.xmpp.database.ChatListener chatListener;
    public java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> list;
    
    public XmppServiceBroadcastEventReceiver(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.database.ChatListener chatListener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.database.ChatListener getChatListener() {
        return null;
    }
    
    public final void setChatListener(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.database.ChatListener p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> getList() {
        return null;
    }
    
    public final void setList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> p0) {
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    private final void onConversationTypingStop(java.lang.String from) {
    }
    
    private final void onConversationTyping(java.lang.String from) {
    }
    
    private final void onStatusChanges(com.swipefwd.xmpp.XmppRosterEntry from) {
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
    public final void register(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
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
    public final void unregister(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * Called when the XMPP connection to the server has been established.
     */
    public final void onXmppConnected() {
    }
    
    /**
     * Called when the service is not connected to the XMPP server.
     */
    public final void onXmppDisconnected() {
    }
    
    /**
     * Called when the roster has been changed (contacts added, removed or change of
     * presence status). To get the current roster, use: [XmppService.getRosterEntries]
     */
    public final void onRosterChanged() {
    }
    
    /**
     * Called when a message has been added to the conversation with a contact
     * (either scheduled for sending or received)
     * @param remoteAccount xmpp JID of the contact
     * @param incoming true if the message is incoming, false if it's scheduled for sending
     */
    public void onMessageAdded(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount, @org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.database.Message message, boolean incoming) {
    }
    
    /**
     * Called when a contact has been successfully added to the roster, as a result of
     * [XmppServiceCommand.addContactToRoster]
     * @param remoteAccount xmpp JID of the contact
     */
    public final void onContactAdded(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    /**
     * Called when a contact has been successfully removed from the roster, as a result of
     * [XmppServiceCommand.removeContactFromRoster]
     * @param remoteAccount xmpp JID of the contact
     */
    public final void onContactRemoved(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    /**
     * Called when a contact has been successfully renamed in the roster, as a result of
     * [XmppServiceCommand.renameContact]
     * @param remoteAccount xmpp JID of the contact
     */
    public final void onContactRenamed(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String newAlias) {
    }
    
    /**
     * Called when an error happened while trying to add a contact to the roster.
     * @param remoteAccount xmpp JID of the contact
     */
    public final void onContactAddError(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    /**
     * Called when all the messages exchanged (sent and received) with a contact have been deleted,
     * as a result of [XmppServiceCommand.clearConversations]
     * @param remoteAccount xmpp JID of the contact
     */
    public void onConversationsCleared(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    /**
     * Called when an error happened during the deletion of the messages exchanged with a contact.
     * @param remoteAccount xmpp JID of the contact
     */
    public final void onConversationsClearError(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    /**
     * Called when a message has been successfully sent.
     * @param messageId unique ID of the sent message
     */
    public void onMessageSent(long messageId, @org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.database.Message message) {
    }
    
    public void onMessageDeleted(long messageId) {
    }
    
    public void setRosterList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> list) {
    }
}