package com.swipefwd.utils.chat

import org.jivesoftware.smack.util.StringUtils

object StringUtilsCompat : StringUtils() {
    /**
     * Returns the name portion of a XMPP address. For example, for the
     * address "matt@jivesoftware.com/Smack", "matt" would be returned. If no
     * username is present in the address, the empty string will be returned.
     *
     * @param XMPPAddress the XMPP address.
     * @return the name portion of the XMPP address.
     */
    fun parseName(XMPPAddress: String?): String? {
        if (XMPPAddress == null) {
            return null
        }
        val atIndex = XMPPAddress.lastIndexOf("@")
        return if (atIndex <= 0) {
            ""
        } else {
            XMPPAddress.substring(0, atIndex)
        }
    }

    /**
     * Returns the server portion of a XMPP address. For example, for the
     * address "matt@jivesoftware.com/Smack", "jivesoftware.com" would be returned.
     * If no server is present in the address, the empty string will be returned.
     *
     * @param XMPPAddress the XMPP address.
     * @return the server portion of the XMPP address.
     */
    fun parseServer(XMPPAddress: String?): String? {
        if (XMPPAddress == null) {
            return null
        }
        val atIndex = XMPPAddress.lastIndexOf("@")
        // If the String ends with '@', return the empty string.
        if (atIndex + 1 > XMPPAddress.length) {
            return ""
        }
        val slashIndex = XMPPAddress.indexOf("/")
        return if (slashIndex > 0 && slashIndex > atIndex) {
            XMPPAddress.substring(atIndex + 1, slashIndex)
        } else {
            XMPPAddress.substring(atIndex + 1)
        }
    }

    /**
     * Returns the resource portion of a XMPP address. For example, for the
     * address "matt@jivesoftware.com/Smack", "Smack" would be returned. If no
     * resource is present in the address, the empty string will be returned.
     *
     * @param XMPPAddress the XMPP address.
     * @return the resource portion of the XMPP address.
     */
    fun parseResource(XMPPAddress: String?): String? {
        if (XMPPAddress == null) {
            return null
        }
        val slashIndex = XMPPAddress.indexOf("/")
        return if (slashIndex + 1 > XMPPAddress.length || slashIndex < 0) {
            ""
        } else {
            XMPPAddress.substring(slashIndex + 1)
        }
    }

    /**
     * Returns the XMPP address with any resource information removed. For example,
     * for the address "matt@jivesoftware.com/Smack", "matt@jivesoftware.com" would
     * be returned.
     *
     * @param XMPPAddress the XMPP address.
     * @return the bare XMPP address without resource information.
     */
    fun parseBareAddress(XMPPAddress: String?): String? {
        if (XMPPAddress == null) {
            return null
        }
        val slashIndex = XMPPAddress.indexOf("/")
        return when {
            slashIndex < 0 -> {
                XMPPAddress
            }
            slashIndex == 0 -> {
                ""
            }
            else -> {
                XMPPAddress.substring(0, slashIndex)
            }
        }
    }

    /**
     * Returns true if jid is a full JID (i.e. a JID with resource part).
     *
     * @param jid
     * @return true if full JID, false otherwise
     */
    fun isFullJID(jid: String?): Boolean {
        return !(parseName(jid)!!.isEmpty() || parseServer(jid)!!.isEmpty() || parseResource(
            jid
        )!!.isEmpty())
    }
}