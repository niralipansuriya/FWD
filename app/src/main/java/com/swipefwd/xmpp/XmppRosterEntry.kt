package com.swipefwd.xmpp

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.Serializable

/**
 * Represents an entry in the roster.
 */
class XmppRosterEntry : Comparable<XmppRosterEntry>, Serializable {
    var xmppJID: String? = null
        private set
    var avatar: ByteArray? = null
        private set
    var alias: String? = null
        private set
    var isAvailable = false
        private set
    var presenceMode = 0
        private set
    var personalMessage: String? = null
        private set
    var unreadMessages: Long = 0
        private set
    var name: String? = null
        private set
    var type: String? = null
        private set


    fun setName(name: String?): XmppRosterEntry {
        this.name = name
        return this
    }

    fun setType(type: String?): XmppRosterEntry {
        this.type = type
        return this
    }

    fun setXmppJID(xmppJID: String?): XmppRosterEntry {
        this.xmppJID = xmppJID
        return this
    }

    fun setAlias(alias: String?): XmppRosterEntry {
        this.alias = alias
        return this
    }

    fun setAvailable(available: Boolean = false): XmppRosterEntry {
        isAvailable = available
        return this
    }

    fun setPresenceMode(presenceMode: Int = 0): XmppRosterEntry {
        this.presenceMode = presenceMode
        return this
    }

    fun setPersonalMessage(personalMessage: String? = ""): XmppRosterEntry {
        this.personalMessage = personalMessage
        return this
    }

    fun getAvatarDrawable(context: Context): Drawable? {
        return if (avatar == null || avatar!!.size < 3) null else BitmapDrawable(
            context.resources,
            BitmapFactory.decodeByteArray(avatar, 0, avatar!!.size)
        )
    }

    fun setAvatar(avatar: ByteArray?): XmppRosterEntry {
        this.avatar = avatar
        return this
    }

    fun setUnreadMessages(unreadMessages: Long): XmppRosterEntry {
        this.unreadMessages = unreadMessages
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as XmppRosterEntry
        return xmppJID == that.xmppJID
    }

    override fun hashCode(): Int {
        return xmppJID.hashCode()
    }

    override fun compareTo(another: XmppRosterEntry): Int {
        if (unreadMessages > another.unreadMessages) return -1
        if (unreadMessages < another.unreadMessages) return 1
        var cmp = comparePresence(another)
        if (cmp == 0) {
            var nameA = alias
            if (nameA == null || nameA.isEmpty()) {
                nameA = xmppJID
            }
            var nameB = another.alias
            if (nameB == null || nameB.isEmpty()) {
                nameB = another.xmppJID
            }
            cmp = nameA!!.compareTo(nameB!!)
        }
        return cmp
    }

    private fun comparePresence(other: XmppRosterEntry): Int {
        if (isAvailable && !other.isAvailable) {
            return -1
        }
        if (!isAvailable && other.isAvailable) {
            return 1
        }
        if (presenceMode < other.presenceMode) {
            return -1
        }
        return if (presenceMode > other.presenceMode) {
            1
        } else 0
    }
}