package com.swipefwd.xmpp;

import java.lang.System;

/**
 * Xmpp Account configuration.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0019\u0018\u0000 72\u00020\u0001:\u00017B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u00106\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR(\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u00048FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R\u001a\u0010\'\u001a\u00020\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R\u001a\u0010*\u001a\u00020\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010!\"\u0004\b,\u0010#R\u001c\u0010-\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0006\"\u0004\b/\u0010\bR\u001c\u00100\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0006\"\u0004\b2\u0010\bR\u001c\u00103\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0006\"\u0004\b5\u0010\b\u00a8\u00068"}, d2 = {"Lcom/swipefwd/xmpp/XmppAccount;", "", "()V", "firstName", "", "getFirstName", "()Ljava/lang/String;", "setFirstName", "(Ljava/lang/String;)V", "host", "getHost", "setHost", "lastName", "getLastName", "setLastName", "map", "", "getMap", "()Ljava/util/Map;", "setMap", "(Ljava/util/Map;)V", "name", "getName", "setName", "password", "getPassword", "setPassword", "personalMessage", "getPersonalMessage", "setPersonalMessage", "port", "", "getPort", "()I", "setPort", "(I)V", "presenceMode", "getPresenceMode", "setPresenceMode", "presenceType", "getPresenceType", "setPresenceType", "priority", "getPriority", "setPriority", "resourceName", "getResourceName", "setResourceName", "serviceName", "getServiceName", "setServiceName", "xmppJid", "getXmppJid", "setXmppJid", "toString", "Companion", "app_debug"})
public final class XmppAccount {
    @org.jetbrains.annotations.Nullable()
    private java.lang.String xmppJid;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String serviceName = "chat.swipefwd.com";
    @org.jetbrains.annotations.Nullable()
    private java.lang.String password;
    @org.jetbrains.annotations.Nullable()
    private java.util.Map<java.lang.String, java.lang.String> map;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String host = "chat.swipefwd.com";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String firstName = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String lastName = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String name = "";
    private int port = 5222;
    private int priority = 0;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String resourceName = "Smack";
    @org.jetbrains.annotations.Nullable()
    private java.lang.String personalMessage;
    
    /**
     * Sets the presence mode for this account.
     * @param presenceMode integer value indicating the presence mode. Use constants defined in this
     * class, for example [XmppAccount.PRESENCE_MODE_AVAILABLE].
     */
    private int presenceMode = 0;
    private int presenceType = 0;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.xmpp.XmppAccount.Companion Companion = null;
    public static final int PRESENCE_MODE_CHAT = 0;
    public static final int PRESENCE_MODE_AVAILABLE = 1;
    public static final int PRESENCE_MODE_AWAY = 2;
    public static final int PRESENCE_MODE_XA = 3;
    public static final int PRESENCE_MODE_DND = 4;
    
    public XmppAccount() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getXmppJid() {
        return null;
    }
    
    public final void setXmppJid(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getServiceName() {
        return null;
    }
    
    public final void setServiceName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPassword() {
        return null;
    }
    
    public final void setPassword(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.Map<java.lang.String, java.lang.String> getMap() {
        return null;
    }
    
    public final void setMap(@org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getHost() {
        return null;
    }
    
    public final void setHost(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFirstName() {
        return null;
    }
    
    public final void setFirstName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastName() {
        return null;
    }
    
    public final void setLastName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getPort() {
        return 0;
    }
    
    public final void setPort(int p0) {
    }
    
    public final int getPriority() {
        return 0;
    }
    
    public final void setPriority(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getResourceName() {
        return null;
    }
    
    public final void setResourceName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final void setPersonalMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPersonalMessage() {
        return null;
    }
    
    public final int getPresenceMode() {
        return 0;
    }
    
    public final void setPresenceMode(int p0) {
    }
    
    public final int getPresenceType() {
        return 0;
    }
    
    public final void setPresenceType(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final com.swipefwd.xmpp.XmppAccount fromJson(@org.jetbrains.annotations.Nullable()
    java.lang.String json) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/swipefwd/xmpp/XmppAccount$Companion;", "", "()V", "PRESENCE_MODE_AVAILABLE", "", "PRESENCE_MODE_AWAY", "PRESENCE_MODE_CHAT", "PRESENCE_MODE_DND", "PRESENCE_MODE_XA", "fromJson", "Lcom/swipefwd/xmpp/XmppAccount;", "json", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final com.swipefwd.xmpp.XmppAccount fromJson(@org.jetbrains.annotations.Nullable()
        java.lang.String json) {
            return null;
        }
    }
}