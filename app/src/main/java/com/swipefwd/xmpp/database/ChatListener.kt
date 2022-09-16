package com.swipefwd.xmpp.database

import com.swipefwd.xmpp.XmppRosterEntry
import java.util.*

interface ChatListener {
    fun setRosterList(list: ArrayList<XmppRosterEntry>)
    fun onMessageSent(messageId: Long, message: Message)
    fun onMessageDeleted(messageId: Long)
    fun onMessageAdded(remoteAccount: String?, message: Message, incoming: Boolean)
    fun onRosterStatusChanges(xmppRosterEntry: XmppRosterEntry)
    fun onRosterChanged()
    fun onRemoveContact(contact: String?)
    fun onTyping(from: String?)
    fun onTypingStop(from: String?)
}