package com.swipefwd.xmpp;

import java.lang.System;

/**
 * Represents an entry in the roster.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0000H\u0002J\u0011\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010$\u001a\u00020\r2\b\u0010%\u001a\u0004\u0018\u00010&H\u0096\u0002J\u0010\u0010\'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*J\b\u0010+\u001a\u00020\u0014H\u0016J\u0010\u0010,\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u0010\u0010-\u001a\u00020\u00002\b\b\u0002\u0010.\u001a\u00020\rJ\u0010\u0010/\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0010\u00100\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005J\u0012\u00101\u001a\u00020\u00002\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005J\u0010\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u0014J\u0010\u00103\u001a\u00020\u00002\b\u0010\u0018\u001a\u0004\u0018\u00010\u0005J\u000e\u00104\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001aJ\u0010\u00105\u001a\u00020\u00002\b\u0010\u001e\u001a\u0004\u0018\u00010\u0005R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\t@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\r@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\"\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\bR\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u0014@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\bR\u001e\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0004\u001a\u00020\u001a@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\"\u0010\u001e\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\b\u00a8\u00066"}, d2 = {"Lcom/swipefwd/xmpp/XmppRosterEntry;", "", "Ljava/io/Serializable;", "()V", "<set-?>", "", "alias", "getAlias", "()Ljava/lang/String;", "", "avatar", "getAvatar", "()[B", "", "isAvailable", "()Z", "name", "getName", "personalMessage", "getPersonalMessage", "", "presenceMode", "getPresenceMode", "()I", "type", "getType", "", "unreadMessages", "getUnreadMessages", "()J", "xmppJID", "getXmppJID", "comparePresence", "other", "compareTo", "another", "equals", "o", "", "getAvatarDrawable", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "hashCode", "setAlias", "setAvailable", "available", "setAvatar", "setName", "setPersonalMessage", "setPresenceMode", "setType", "setUnreadMessages", "setXmppJID", "app_debug"})
public final class XmppRosterEntry implements java.lang.Comparable<com.swipefwd.xmpp.XmppRosterEntry>, java.io.Serializable {
    @org.jetbrains.annotations.Nullable()
    private java.lang.String xmppJID;
    @org.jetbrains.annotations.Nullable()
    private byte[] avatar;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String alias;
    private boolean isAvailable = false;
    private int presenceMode = 0;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String personalMessage;
    private long unreadMessages = 0L;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String name;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String type;
    
    public XmppRosterEntry() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getXmppJID() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final byte[] getAvatar() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAlias() {
        return null;
    }
    
    public final boolean isAvailable() {
        return false;
    }
    
    public final int getPresenceMode() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPersonalMessage() {
        return null;
    }
    
    public final long getUnreadMessages() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppRosterEntry setName(@org.jetbrains.annotations.Nullable()
    java.lang.String name) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppRosterEntry setType(@org.jetbrains.annotations.Nullable()
    java.lang.String type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppRosterEntry setXmppJID(@org.jetbrains.annotations.Nullable()
    java.lang.String xmppJID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppRosterEntry setAlias(@org.jetbrains.annotations.Nullable()
    java.lang.String alias) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppRosterEntry setAvailable(boolean available) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppRosterEntry setPresenceMode(int presenceMode) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppRosterEntry setPersonalMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String personalMessage) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.drawable.Drawable getAvatarDrawable(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppRosterEntry setAvatar(@org.jetbrains.annotations.Nullable()
    byte[] avatar) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.XmppRosterEntry setUnreadMessages(long unreadMessages) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object o) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public int compareTo(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.XmppRosterEntry another) {
        return 0;
    }
    
    private final int comparePresence(com.swipefwd.xmpp.XmppRosterEntry other) {
        return 0;
    }
}