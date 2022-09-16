package com.swipefwd.utils.chat;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u00a8\u0006\f"}, d2 = {"Lcom/swipefwd/utils/chat/StringUtilsCompat;", "Lorg/jivesoftware/smack/util/StringUtils;", "()V", "isFullJID", "", "jid", "", "parseBareAddress", "XMPPAddress", "parseName", "parseResource", "parseServer", "app_debug"})
public final class StringUtilsCompat extends org.jivesoftware.smack.util.StringUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.chat.StringUtilsCompat INSTANCE = null;
    
    private StringUtilsCompat() {
        super();
    }
    
    /**
     * Returns the name portion of a XMPP address. For example, for the
     * address "matt@jivesoftware.com/Smack", "matt" would be returned. If no
     * username is present in the address, the empty string will be returned.
     *
     * @param XMPPAddress the XMPP address.
     * @return the name portion of the XMPP address.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String parseName(@org.jetbrains.annotations.Nullable()
    java.lang.String XMPPAddress) {
        return null;
    }
    
    /**
     * Returns the server portion of a XMPP address. For example, for the
     * address "matt@jivesoftware.com/Smack", "jivesoftware.com" would be returned.
     * If no server is present in the address, the empty string will be returned.
     *
     * @param XMPPAddress the XMPP address.
     * @return the server portion of the XMPP address.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String parseServer(@org.jetbrains.annotations.Nullable()
    java.lang.String XMPPAddress) {
        return null;
    }
    
    /**
     * Returns the resource portion of a XMPP address. For example, for the
     * address "matt@jivesoftware.com/Smack", "Smack" would be returned. If no
     * resource is present in the address, the empty string will be returned.
     *
     * @param XMPPAddress the XMPP address.
     * @return the resource portion of the XMPP address.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String parseResource(@org.jetbrains.annotations.Nullable()
    java.lang.String XMPPAddress) {
        return null;
    }
    
    /**
     * Returns the XMPP address with any resource information removed. For example,
     * for the address "matt@jivesoftware.com/Smack", "matt@jivesoftware.com" would
     * be returned.
     *
     * @param XMPPAddress the XMPP address.
     * @return the bare XMPP address without resource information.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String parseBareAddress(@org.jetbrains.annotations.Nullable()
    java.lang.String XMPPAddress) {
        return null;
    }
    
    /**
     * Returns true if jid is a full JID (i.e. a JID with resource part).
     *
     * @param jid
     * @return true if full JID, false otherwise
     */
    public final boolean isFullJID(@org.jetbrains.annotations.Nullable()
    java.lang.String jid) {
        return false;
    }
}