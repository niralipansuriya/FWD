package com.swipefwd.xmpp;

import java.lang.System;

/**
 * Main XMPP Service.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u001b\u001a\u00020\u000eH\u0002J\u0010\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010 \u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010!\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\"\u001a\u00020\u000eH\u0016J\b\u0010#\u001a\u00020\u000eH\u0016J \u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010&\u001a\u00020%2\u0006\u0010\'\u001a\u00020%H\u0016J\u0010\u0010(\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/swipefwd/xmpp/XmppService;", "Lcom/swipefwd/xmpp/BackgroundService;", "()V", "account", "Lcom/swipefwd/xmpp/XmppAccount;", "getAccount", "()Lcom/swipefwd/xmpp/XmppAccount;", "setAccount", "(Lcom/swipefwd/xmpp/XmppAccount;)V", "mConnection", "Lcom/swipefwd/xmpp/XmppServiceConnection;", "mPrefs", "Landroid/content/SharedPreferences;", "handleAddContact", "", "intent", "Landroid/content/Intent;", "handleClearConversations", "handleConnect", "handleDeleteMessage", "handleDisconnect", "handleName", "handleRefreshContact", "handleRemoveContact", "handleRenameContact", "handleSendImage", "handleSendMessage", "handleSendPendingMessages", "handleSendVoice", "handleSetAvatar", "handleSetPresence", "handleSubscription", "handleTyping", "handleTypingStop", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "sendRosterToActivity", "Companion", "app_debug"})
public final class XmppService extends com.swipefwd.xmpp.BackgroundService {
    private android.content.SharedPreferences mPrefs;
    private com.swipefwd.xmpp.XmppServiceConnection mConnection;
    public com.swipefwd.xmpp.XmppAccount account;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.xmpp.XmppService.Companion Companion = null;
    private static final java.lang.String TAG = null;
    
    /**
     * Name of the database used as a persistent storage for the exchanged messages.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DATABASE_NAME = "messages.db";
    
    /**
     * Name of the shared preferences file.
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREFS_NAME = "messages-prefs.conf";
    private static final java.lang.String KEY_CONFIGURED_ACCOUNT = "configuredAccount";
    
    /**
     * Connection connect timeout in milliseconds.
     */
    @kotlin.jvm.JvmField()
    public static int CONNECT_TIMEOUT = 20000000;
    
    /**
     * Sets the number of milliseconds to wait for a response from
     * the server.
     */
    private static int PACKET_REPLY_TIMEOUT = 500000;
    
    /**
     * Default ping interval.
     */
    @kotlin.jvm.JvmField()
    public static int DEFAULT_PING_INTERVAL = 600;
    
    /**
     * Use stream management (XEP-0198).
     */
    @kotlin.jvm.JvmField()
    public static boolean USE_STREAM_MANAGEMENT = true;
    
    /**
     * Stream management resumption time in seconds.
     */
    @kotlin.jvm.JvmField()
    public static int STREAM_MANAGEMENT_RESUMPTION_TIME = 30;
    
    /**
     * Custom [SSLContext] to use for the connection.
     */
    @org.jetbrains.annotations.Nullable()
    @kotlin.jvm.JvmField()
    public static javax.net.ssl.SSLContext CUSTOM_SSL_CONTEXT;
    
    /**
     * Custom [HostnameVerifier] to use for the connection.
     */
    @org.jetbrains.annotations.Nullable()
    @kotlin.jvm.JvmField()
    public static javax.net.ssl.HostnameVerifier CUSTOM_HOSTNAME_VERIFIER;
    @kotlin.jvm.Volatile()
    private static volatile boolean connected = false;
    @kotlin.jvm.Volatile()
    private static volatile com.swipefwd.xmpp.XmppAccount xmppAccount;
    @kotlin.jvm.Volatile()
    private static volatile java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> rosterEntries;
    
    public XmppService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppAccount getAccount() {
        return null;
    }
    
    public final void setAccount(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.XmppAccount p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void handleConnect(com.swipefwd.xmpp.XmppAccount account) {
    }
    
    private final void handleDisconnect() {
    }
    
    private final void handleSendMessage(android.content.Intent intent) {
    }
    
    private final void handleDeleteMessage(android.content.Intent intent) {
    }
    
    private final void handleSendPendingMessages() {
    }
    
    private final void handleTyping(android.content.Intent intent) {
    }
    
    private final void handleTypingStop(android.content.Intent intent) {
    }
    
    private final void handleAddContact(android.content.Intent intent) {
    }
    
    private final void handleSubscription(android.content.Intent intent) {
    }
    
    private final void handleSendImage(android.content.Intent intent) {
    }
    
    private final void handleSendVoice(android.content.Intent intent) {
    }
    
    private final void handleRemoveContact(android.content.Intent intent) {
    }
    
    private final void handleRenameContact(android.content.Intent intent) {
    }
    
    private final void handleRefreshContact(android.content.Intent intent) {
    }
    
    private final void handleClearConversations(android.content.Intent intent) {
    }
    
    private final void handleSetAvatar(android.content.Intent intent) {
    }
    
    private final void handleName(android.content.Intent intent) {
    }
    
    private final void handleSetPresence(android.content.Intent intent) {
    }
    
    private final void sendRosterToActivity(android.content.Intent intent) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @kotlin.jvm.JvmStatic()
    public static final java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> getRosterEntries() {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void setRosterEntries(@org.jetbrains.annotations.Nullable()
    java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> entries) {
    }
    
    @kotlin.jvm.Synchronized()
    @kotlin.jvm.JvmStatic()
    public static final synchronized void setConnected(boolean newConnectedStatus) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void setXmppAccount(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.XmppAccount tempXmppAccount) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final com.swipefwd.xmpp.XmppAccount getXmppAccount() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aH\u0007J\b\u0010\u001f\u001a\u00020\u001dH\u0007J\u0006\u0010 \u001a\u00020\u0017J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0017H\u0007J\u0018\u0010$\u001a\u00020\"2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aH\u0007J\u0010\u0010&\u001a\u00020\"2\u0006\u0010\'\u001a\u00020\u001dH\u0007R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0015*\u0004\u0018\u00010\n0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/swipefwd/xmpp/XmppService$Companion;", "", "()V", "CONNECT_TIMEOUT", "", "CUSTOM_HOSTNAME_VERIFIER", "Ljavax/net/ssl/HostnameVerifier;", "CUSTOM_SSL_CONTEXT", "Ljavax/net/ssl/SSLContext;", "DATABASE_NAME", "", "DEFAULT_PING_INTERVAL", "KEY_CONFIGURED_ACCOUNT", "PACKET_REPLY_TIMEOUT", "getPACKET_REPLY_TIMEOUT", "()I", "setPACKET_REPLY_TIMEOUT", "(I)V", "PREFS_NAME", "STREAM_MANAGEMENT_RESUMPTION_TIME", "TAG", "kotlin.jvm.PlatformType", "USE_STREAM_MANAGEMENT", "", "connected", "rosterEntries", "Ljava/util/ArrayList;", "Lcom/swipefwd/xmpp/XmppRosterEntry;", "xmppAccount", "Lcom/swipefwd/xmpp/XmppAccount;", "getRosterEntries", "getXmppAccount", "isConnected", "setConnected", "", "newConnectedStatus", "setRosterEntries", "entries", "setXmppAccount", "tempXmppAccount", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final int getPACKET_REPLY_TIMEOUT() {
            return 0;
        }
        
        public final void setPACKET_REPLY_TIMEOUT(int p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        @kotlin.jvm.JvmStatic()
        public final java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> getRosterEntries() {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        public final void setRosterEntries(@org.jetbrains.annotations.Nullable()
        java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> entries) {
        }
        
        @kotlin.jvm.Synchronized()
        public final synchronized boolean isConnected() {
            return false;
        }
        
        @kotlin.jvm.Synchronized()
        @kotlin.jvm.JvmStatic()
        public final synchronized void setConnected(boolean newConnectedStatus) {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void setXmppAccount(@org.jetbrains.annotations.NotNull()
        com.swipefwd.xmpp.XmppAccount tempXmppAccount) {
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final com.swipefwd.xmpp.XmppAccount getXmppAccount() {
            return null;
        }
    }
}