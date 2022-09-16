package com.swipefwd.xmpp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface ChatDao {
    @Insert(onConflict = IGNORE)
    fun insertMessage(message: Message)

    @Insert(onConflict = IGNORE)
     fun insertRosters(rosters: List<Rosters>)

    @Insert(onConflict = IGNORE)
     fun insertRoster(roster: Rosters)


    @Query("SELECT * FROM Rosters")
    fun getAllRosters(): List<Rosters>

    @Query("SELECT * FROM Rosters WHERE xmppJid LIKE :jid")
     fun getRoster(jid: String): Rosters


    @Query("DELETE FROM Rosters")
     fun deleteAllRosters()

    @Query("SELECT * FROM Message WHERE chatUsers LIKE :users")
     fun getAllMessageByRoster(users: String): List<Message>

    @Query("SELECT * FROM Message ORDER BY id DESC LIMIT 1")
     fun getLastMessage(): List<Message>

    @Query("SELECT * FROM Message WHERE pending LIKE 1")
     fun getPendingMessage(): List<Message>


    @Query("SELECT * FROM Rosters WHERE xmppJid LIKE :remoteAcoount")
     fun isRosterThere(remoteAcoount: String): List<Rosters>


    @Query("SELECT * FROM Message WHERE read LIKE 1 AND recipient LIKE :rosterId")
     fun getUnreadCountByUser(rosterId: String): List<Message>

    @Query("Update Message SET pending=0 WHERE id =:id")
    fun updatePendingMessageFlag(id: Int)

    @Query("Update Rosters SET isAvailable=0")
     fun setAllUserToOffline()

    @Query("DELETE FROM Message WHERE recipient = :user AND sender =:user")
    fun deleteAllMessageByUser(user: String)

    @Query("DELETE FROM Rosters WHERE xmppJid = :user")
    fun deleteRoster(user: String)


    @Query("SELECT * FROM Message WHERE chatUsers =:users ORDER BY id DESC LIMIT 1")
    fun getLastMessageByRoster(users: String): Message
}