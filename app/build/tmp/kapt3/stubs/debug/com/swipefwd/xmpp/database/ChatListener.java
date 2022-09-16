package com.swipefwd.xmpp.database;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u0010\u001a\u00020\u0003H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0012\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0016\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H&J\u0016\u0010\u0017\u001a\u00020\u00032\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u0019H&\u00a8\u0006\u001a"}, d2 = {"Lcom/swipefwd/xmpp/database/ChatListener;", "", "onMessageAdded", "", "remoteAccount", "", "message", "Lcom/swipefwd/xmpp/database/Message;", "incoming", "", "onMessageDeleted", "messageId", "", "onMessageSent", "onRemoveContact", "contact", "onRosterChanged", "onRosterStatusChanges", "xmppRosterEntry", "Lcom/swipefwd/xmpp/XmppRosterEntry;", "onTyping", "from", "onTypingStop", "setRosterList", "list", "Ljava/util/ArrayList;", "app_debug"})
public abstract interface ChatListener {
    
    public abstract void setRosterList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> list);
    
    public abstract void onMessageSent(long messageId, @org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.database.Message message);
    
    public abstract void onMessageDeleted(long messageId);
    
    public abstract void onMessageAdded(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount, @org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.database.Message message, boolean incoming);
    
    public abstract void onRosterStatusChanges(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.XmppRosterEntry xmppRosterEntry);
    
    public abstract void onRosterChanged();
    
    public abstract void onRemoveContact(@org.jetbrains.annotations.Nullable()
    java.lang.String contact);
    
    public abstract void onTyping(@org.jetbrains.annotations.Nullable()
    java.lang.String from);
    
    public abstract void onTypingStop(@org.jetbrains.annotations.Nullable()
    java.lang.String from);
}