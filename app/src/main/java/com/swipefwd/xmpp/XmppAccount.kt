package com.swipefwd.xmpp

import com.google.gson.Gson

/**
 * Xmpp Account configuration.
 */
class XmppAccount {
    var xmppJid: String? = null
    var serviceName: String? = "chat.swipefwd.com"
    var password: String? = null
    var map: MutableMap<String, String>? = null
    var host: String? = "chat.swipefwd.com"

    var firstName: String = ""
    var lastName: String = ""
    var name: String = ""
    var port = 5222
    var priority = 0
    var resourceName: String? = "Smack"
    var personalMessage: String? = null
        get() = if (field == null) "" else field

    /**
     * Sets the presence mode for this account.
     * @param presenceMode integer value indicating the presence mode. Use constants defined in this
     * class, for example [XmppAccount.PRESENCE_MODE_AVAILABLE].
     */
    var presenceMode = 0
    var presenceType = 0
    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {
        // presence mode constants
        const val PRESENCE_MODE_CHAT = 0
        const val PRESENCE_MODE_AVAILABLE = 1
        const val PRESENCE_MODE_AWAY = 2
        const val PRESENCE_MODE_XA = 3
        const val PRESENCE_MODE_DND = 4

        @JvmStatic
        fun fromJson(json: String?): XmppAccount {
            return Gson().fromJson(json, XmppAccount::class.java)
        }
    }
}