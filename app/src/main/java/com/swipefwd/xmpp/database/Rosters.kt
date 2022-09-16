package com.swipefwd.xmpp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Rosters : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var xmppJid: String? = "" //phonenumber@chat.swipefwd.com

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var avatar: ByteArray? = null
    var name: String? = ""
    var type: String? = ""
    var email: String? = ""
    var pendingMessageCount: Int? = 0
    var isAvailable: Boolean = false
    var presenceMode: Int? = 0
    var personalMessage: String? = ""
    var lastMessage: String? = ""
    var lastMessgeTimeStamp: Long = 0
    var isTyping: Boolean = false
    var profile_url: String? = ""

}

