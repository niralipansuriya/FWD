package com.swipefwd.xmpp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class Message : Serializable {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id = 0
    var chatUsers: String = ""
    var sender: String = ""
    var senderName: String = ""
    var recipientName: String = ""
    var recipient: String = ""
    var pending: Int? = 0
    var read: Int? = 0
    var message: String? = ""
    var messageType: String? = ""
    var timeStamp: Long? = 0
    var avatar: String? = ""
}
