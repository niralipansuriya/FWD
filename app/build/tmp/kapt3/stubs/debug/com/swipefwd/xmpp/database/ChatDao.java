package com.swipefwd.xmpp.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\b\u0010\u0006\u001a\u00020\u0003H\'J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\u0005H\'J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\tH\'J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\tH\'J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\'J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\tH\'J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0005H\'J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0014\u001a\u00020\u0005H\'J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\nH\'J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\rH\'J\u0016\u0010\u0019\u001a\u00020\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\tH\'J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\t2\u0006\u0010\u001c\u001a\u00020\u0005H\'J\b\u0010\u001d\u001a\u00020\u0003H\'J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 H\'\u00a8\u0006!"}, d2 = {"Lcom/swipefwd/xmpp/database/ChatDao;", "", "deleteAllMessageByUser", "", "user", "", "deleteAllRosters", "deleteRoster", "getAllMessageByRoster", "", "Lcom/swipefwd/xmpp/database/Message;", "users", "getAllRosters", "Lcom/swipefwd/xmpp/database/Rosters;", "getLastMessage", "getLastMessageByRoster", "getPendingMessage", "getRoster", "jid", "getUnreadCountByUser", "rosterId", "insertMessage", "message", "insertRoster", "roster", "insertRosters", "rosters", "isRosterThere", "remoteAcoount", "setAllUserToOffline", "updatePendingMessageFlag", "id", "", "app_debug"})
public abstract interface ChatDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insertMessage(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.database.Message message);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insertRosters(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.xmpp.database.Rosters> rosters);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    public abstract void insertRoster(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.database.Rosters roster);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Rosters")
    public abstract java.util.List<com.swipefwd.xmpp.database.Rosters> getAllRosters();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Rosters WHERE xmppJid LIKE :jid")
    public abstract com.swipefwd.xmpp.database.Rosters getRoster(@org.jetbrains.annotations.NotNull()
    java.lang.String jid);
    
    @androidx.room.Query(value = "DELETE FROM Rosters")
    public abstract void deleteAllRosters();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Message WHERE chatUsers LIKE :users")
    public abstract java.util.List<com.swipefwd.xmpp.database.Message> getAllMessageByRoster(@org.jetbrains.annotations.NotNull()
    java.lang.String users);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Message ORDER BY id DESC LIMIT 1")
    public abstract java.util.List<com.swipefwd.xmpp.database.Message> getLastMessage();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Message WHERE pending LIKE 1")
    public abstract java.util.List<com.swipefwd.xmpp.database.Message> getPendingMessage();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Rosters WHERE xmppJid LIKE :remoteAcoount")
    public abstract java.util.List<com.swipefwd.xmpp.database.Rosters> isRosterThere(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteAcoount);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Message WHERE read LIKE 1 AND recipient LIKE :rosterId")
    public abstract java.util.List<com.swipefwd.xmpp.database.Message> getUnreadCountByUser(@org.jetbrains.annotations.NotNull()
    java.lang.String rosterId);
    
    @androidx.room.Query(value = "Update Message SET pending=0 WHERE id =:id")
    public abstract void updatePendingMessageFlag(int id);
    
    @androidx.room.Query(value = "Update Rosters SET isAvailable=0")
    public abstract void setAllUserToOffline();
    
    @androidx.room.Query(value = "DELETE FROM Message WHERE recipient = :user AND sender =:user")
    public abstract void deleteAllMessageByUser(@org.jetbrains.annotations.NotNull()
    java.lang.String user);
    
    @androidx.room.Query(value = "DELETE FROM Rosters WHERE xmppJid = :user")
    public abstract void deleteRoster(@org.jetbrains.annotations.NotNull()
    java.lang.String user);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM Message WHERE chatUsers =:users ORDER BY id DESC LIMIT 1")
    public abstract com.swipefwd.xmpp.database.Message getLastMessageByRoster(@org.jetbrains.annotations.NotNull()
    java.lang.String users);
}