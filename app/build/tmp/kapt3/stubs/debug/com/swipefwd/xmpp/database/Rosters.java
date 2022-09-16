package com.swipefwd.xmpp.database;

import java.lang.System;

@androidx.room.Entity()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u001d\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000eR\u001a\u0010\u001f\u001a\u00020 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\f\"\u0004\b\'\u0010\u000eR\u001e\u0010(\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010.\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\f\"\u0004\b0\u0010\u000eR\u001e\u00101\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010-\u001a\u0004\b2\u0010*\"\u0004\b3\u0010,R\u001c\u00104\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\f\"\u0004\b6\u0010\u000eR\u001c\u00107\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\f\"\u0004\b9\u0010\u000eR\u001c\u0010:\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\f\"\u0004\b<\u0010\u000e\u00a8\u0006="}, d2 = {"Lcom/swipefwd/xmpp/database/Rosters;", "Ljava/io/Serializable;", "()V", "avatar", "", "getAvatar", "()[B", "setAvatar", "([B)V", "email", "", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "id", "", "getId", "()I", "setId", "(I)V", "isAvailable", "", "()Z", "setAvailable", "(Z)V", "isTyping", "setTyping", "lastMessage", "getLastMessage", "setLastMessage", "lastMessgeTimeStamp", "", "getLastMessgeTimeStamp", "()J", "setLastMessgeTimeStamp", "(J)V", "name", "getName", "setName", "pendingMessageCount", "getPendingMessageCount", "()Ljava/lang/Integer;", "setPendingMessageCount", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "personalMessage", "getPersonalMessage", "setPersonalMessage", "presenceMode", "getPresenceMode", "setPresenceMode", "profile_url", "getProfile_url", "setProfile_url", "type", "getType", "setType", "xmppJid", "getXmppJid", "setXmppJid", "app_debug"})
public final class Rosters implements java.io.Serializable {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private int id = 0;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String xmppJid = "";
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(typeAffinity = androidx.room.ColumnInfo.BLOB)
    private byte[] avatar;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String name = "";
    @org.jetbrains.annotations.Nullable()
    private java.lang.String type = "";
    @org.jetbrains.annotations.Nullable()
    private java.lang.String email = "";
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer pendingMessageCount = 0;
    private boolean isAvailable = false;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer presenceMode = 0;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String personalMessage = "";
    @org.jetbrains.annotations.Nullable()
    private java.lang.String lastMessage = "";
    private long lastMessgeTimeStamp = 0L;
    private boolean isTyping = false;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String profile_url = "";
    
    public Rosters() {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    public final void setId(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getXmppJid() {
        return null;
    }
    
    public final void setXmppJid(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final byte[] getAvatar() {
        return null;
    }
    
    public final void setAvatar(@org.jetbrains.annotations.Nullable()
    byte[] p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getType() {
        return null;
    }
    
    public final void setType(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEmail() {
        return null;
    }
    
    public final void setEmail(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getPendingMessageCount() {
        return null;
    }
    
    public final void setPendingMessageCount(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    public final boolean isAvailable() {
        return false;
    }
    
    public final void setAvailable(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getPresenceMode() {
        return null;
    }
    
    public final void setPresenceMode(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPersonalMessage() {
        return null;
    }
    
    public final void setPersonalMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastMessage() {
        return null;
    }
    
    public final void setLastMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final long getLastMessgeTimeStamp() {
        return 0L;
    }
    
    public final void setLastMessgeTimeStamp(long p0) {
    }
    
    public final boolean isTyping() {
        return false;
    }
    
    public final void setTyping(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getProfile_url() {
        return null;
    }
    
    public final void setProfile_url(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
}