package com.swipefwd.xmpp;

import java.lang.System;

/**
 * Emits the xmpp service broadcast intents.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010&\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010\'\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0004H\u0007J\u001c\u0010(\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u00042\b\u0010)\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010*\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010+\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0004H\u0007J\"\u0010,\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u00042\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0007J\u000e\u00101\u001a\u00020$2\u0006\u00102\u001a\u000203J\u0018\u00104\u001a\u00020$2\u0006\u00102\u001a\u0002032\u0006\u0010/\u001a\u000200H\u0007J\u0018\u00105\u001a\u00020$2\u000e\u00106\u001a\n\u0012\u0004\u0012\u000208\u0018\u000107H\u0007J\b\u00109\u001a\u00020$H\u0007J\u0010\u0010:\u001a\u00020$2\u0006\u0010;\u001a\u000208H\u0007J\u0010\u0010<\u001a\u00020$2\u0006\u0010=\u001a\u00020\u0004H\u0007J\u0010\u0010>\u001a\u00020$2\u0006\u0010=\u001a\u00020\u0004H\u0007J\u0006\u0010?\u001a\u00020$J\u0006\u0010@\u001a\u00020$J\u001a\u0010A\u001a\u00020$2\b\u0010B\u001a\u0004\u0018\u00010\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010 \u001a\u0004\u0018\u00010\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"\u00a8\u0006C"}, d2 = {"Lcom/swipefwd/xmpp/XmppServiceBroadcastEventEmitter;", "", "()V", "BROADCAST_CONNECTED", "", "BROADCAST_CONTACT_ADDED", "BROADCAST_CONTACT_ADD_ERROR", "BROADCAST_CONTACT_REMOVED", "BROADCAST_CONTACT_RENAMED", "BROADCAST_CONVERSATIONS_CLEARED", "BROADCAST_CONVERSATIONS_CLEAR_ERROR", "BROADCAST_DISCONNECTED", "BROADCAST_MESSAGE_ADDED", "BROADCAST_MESSAGE_DELETED", "BROADCAST_MESSAGE_SENT", "BROADCAST_ROSTER_CHANGED", "BROADCAST_ROSTER_LIST", "BROADCAST_ROSTER_STATUS_CHANGED", "BROADCAST_TYPING", "BROADCAST_TYPING_STOP", "PARAM_ALIAS", "PARAM_FROM", "PARAM_INCOMING", "PARAM_MESSAGE", "PARAM_MESSAGE_ID", "PARAM_REMOTE_ACCOUNT", "PARAM_ROSTERS_LIST", "PARAM_TYPING", "PARAM_TYPING_STOP", "mContext", "Landroid/content/Context;", "<set-?>", "namespace", "getNamespace", "()Ljava/lang/String;", "broadcastContactAddError", "", "remoteAccount", "broadcastContactAdded", "broadcastContactRemoved", "broadcastContactRenamed", "newAlias", "broadcastConversationsClearError", "broadcastConversationsCleared", "broadcastMessageAdded", "incoming", "", "message", "Lcom/swipefwd/xmpp/database/Message;", "broadcastMessageDeleted", "messageId", "", "broadcastMessageSent", "broadcastRoasterList", "rosterList", "Ljava/util/ArrayList;", "Lcom/swipefwd/xmpp/XmppRosterEntry;", "broadcastRosterChanged", "broadcastRosterStatusChanged", "xmppRosterEntry", "broadcastTyping", "from", "broadcastTypingStop", "broadcastXmppConnected", "broadcastXmppDisconnected", "initialize", "context", "app_debug"})
public final class XmppServiceBroadcastEventEmitter {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.xmpp.XmppServiceBroadcastEventEmitter INSTANCE = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_CONNECTED = ".xmpp.connected";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_DISCONNECTED = ".xmpp.disconnected";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_ROSTER_CHANGED = ".xmpp.rosterchanged";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_ROSTER_STATUS_CHANGED = ".xmpp.rosterstatuschanged";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_MESSAGE_ADDED = ".xmpp.messageadded";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_MESSAGE_DELETED = ".xmpp.messagedeleted";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_CONTACT_ADDED = ".xmpp.contactadded";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_CONTACT_ADD_ERROR = ".xmpp.contactadderror";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_CONTACT_REMOVED = ".xmpp.contactremoved";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_CONVERSATIONS_CLEARED = ".xmpp.conversationscleared";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_CONVERSATIONS_CLEAR_ERROR = ".xmpp.conversationsclearerror";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_CONTACT_RENAMED = ".xmpp.contactrenamed";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_MESSAGE_SENT = ".xmpp.messagesent";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_ROSTER_LIST = ".xmpp.rosterList";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_TYPING = ".xmpp.typing";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BROADCAST_TYPING_STOP = ".xmpp.typing_stop";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_REMOTE_ACCOUNT = "remoteAccount";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_ALIAS = "alias";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_MESSAGE_ID = "messageId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_ROSTERS_LIST = "rosterList";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_INCOMING = "incoming";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_FROM = "from";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_MESSAGE = "message";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_TYPING = "typing";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PARAM_TYPING_STOP = "stop";
    private static android.content.Context mContext;
    @org.jetbrains.annotations.Nullable()
    private static java.lang.String namespace;
    
    private XmppServiceBroadcastEventEmitter() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNamespace() {
        return null;
    }
    
    @kotlin.jvm.Synchronized()
    public final synchronized void initialize(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String namespace) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastRosterChanged() {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastRosterStatusChanged(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.XmppRosterEntry xmppRosterEntry) {
    }
    
    @kotlin.jvm.Synchronized()
    public final synchronized void broadcastXmppConnected() {
    }
    
    @kotlin.jvm.Synchronized()
    public final synchronized void broadcastXmppDisconnected() {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastMessageAdded(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount, boolean incoming, @org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.database.Message message) {
    }
    
    @kotlin.jvm.Synchronized()
    public final synchronized void broadcastMessageDeleted(long messageId) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastContactAdded(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastContactAddError(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastContactRemoved(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastConversationsCleared(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastConversationsClearError(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastContactRenamed(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String newAlias) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastMessageSent(long messageId, @org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.database.Message message) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastRoasterList(@org.jetbrains.annotations.Nullable()
    java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> rosterList) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastTyping(@org.jetbrains.annotations.NotNull()
    java.lang.String from) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void broadcastTypingStop(@org.jetbrains.annotations.NotNull()
    java.lang.String from) {
    }
}