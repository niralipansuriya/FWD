package com.swipefwd.xmpp;

import java.lang.System;

/**
 * Implementation of the XMPP Connection.
 * @author gotev (Aleksandar Gotev)
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00ca\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 \u0080\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002\u0080\u0001B\u0015\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0018\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010)J\u0016\u0010+\u001a\u00020\'2\u0006\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020)J\u0018\u0010.\u001a\u00020\'2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0016J\u0018\u00103\u001a\u00020\'2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000202H\u0016J\u000e\u00107\u001a\u00020\'2\u0006\u0010(\u001a\u00020)J\u0006\u00108\u001a\u00020\'J\u0010\u00109\u001a\u00020\'2\u0006\u0010/\u001a\u000200H\u0016J\b\u0010:\u001a\u00020\'H\u0016J\u0014\u0010;\u001a\u00020\'2\n\u0010<\u001a\u00060=j\u0002`>H\u0016J\b\u0010?\u001a\u00020\'H\u0002J\u0006\u0010@\u001a\u00020\'J\u0016\u0010A\u001a\u00020\'2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020)0CH\u0016J\u0016\u0010D\u001a\u00020\'2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020)0CH\u0016J\u0016\u0010E\u001a\u00020\'2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020)0CH\u0016J\u0014\u0010F\u001a\u0004\u0018\u00010\u001f2\b\u0010(\u001a\u0004\u0018\u00010)H\u0002J\u0012\u0010G\u001a\u0004\u0018\u00010\u001f2\u0006\u0010H\u001a\u00020)H\u0002J\b\u0010I\u001a\u00020\'H\u0002J\u0018\u0010J\u001a\u00020\"2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NH\u0002J\u0010\u0010O\u001a\u00020P2\b\u0010(\u001a\u0004\u0018\u00010)J\u0014\u0010Q\u001a\u0004\u0018\u00010R2\b\u0010(\u001a\u0004\u0018\u00010)H\u0002J\u0010\u0010S\u001a\u00020)2\u0006\u0010T\u001a\u00020)H\u0002J\b\u0010U\u001a\u00020\'H\u0016J\u0010\u0010V\u001a\u00020\'2\u0006\u0010W\u001a\u00020XH\u0016J\u0018\u0010Y\u001a\u00020\'2\u0006\u00104\u001a\u0002052\u0006\u0010-\u001a\u00020ZH\u0016J\b\u0010[\u001a\u00020\'H\u0002J\u0010\u0010\\\u001a\u00020\'2\u0006\u0010]\u001a\u00020^H\u0016J\u0014\u0010_\u001a\u00020\'2\n\u0010<\u001a\u00060=j\u0002`>H\u0016J\b\u0010`\u001a\u00020\'H\u0016J\u000e\u0010a\u001a\u00020\'2\u0006\u0010(\u001a\u00020)J\u0018\u0010b\u001a\u00020\'2\u0006\u0010(\u001a\u00020)2\b\u0010c\u001a\u0004\u0018\u00010)J\u0006\u0010d\u001a\u00020\'J2\u0010e\u001a\u00020\'2\u0006\u0010(\u001a\u00020)2\u0006\u0010f\u001a\u00020)2\u0006\u0010g\u001a\u0002022\b\b\u0002\u0010h\u001a\u0002022\u0006\u0010i\u001a\u00020)H\u0002J\u0018\u0010j\u001a\u00020\'2\u0006\u0010H\u001a\u00020)2\b\u0010k\u001a\u0004\u0018\u00010)J \u0010l\u001a\u00020\'2\u0006\u0010,\u001a\u00020)2\u0006\u0010m\u001a\u00020)2\u0006\u0010n\u001a\u00020oH\u0002J\u0006\u0010p\u001a\u00020\'J\u000e\u0010q\u001a\u00020\'2\u0006\u0010T\u001a\u00020)J\u0010\u0010r\u001a\u00020\'2\b\u0010,\u001a\u0004\u0018\u00010)J\u0010\u0010s\u001a\u00020\'2\b\u0010,\u001a\u0004\u0018\u00010)J\u0010\u0010t\u001a\u00020\'2\u0006\u0010T\u001a\u00020)H\u0002J\u0018\u0010u\u001a\u00020\'2\u0006\u0010H\u001a\u00020)2\b\u0010k\u001a\u0004\u0018\u00010)J\u001a\u0010v\u001a\u00020\'2\b\u0010w\u001a\u0004\u0018\u00010)2\b\u0010x\u001a\u0004\u0018\u00010)J\b\u0010y\u001a\u00020\'H\u0002J$\u0010z\u001a\u00020\'2\b\u0010k\u001a\u0004\u0018\u00010)2\b\u0010w\u001a\u0004\u0018\u00010)2\b\u0010x\u001a\u0004\u0018\u00010)J\u0006\u0010{\u001a\u00020\'J \u0010{\u001a\u00020\'2\u0006\u0010|\u001a\u00020^2\u0006\u0010}\u001a\u00020^2\b\u0010~\u001a\u0004\u0018\u00010)J\u0010\u0010\u007f\u001a\u00020\'2\b\u0010M\u001a\u0004\u0018\u00010)R\u0019\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R(\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\"\u0018\u00010!j\n\u0012\u0004\u0012\u00020\"\u0018\u0001`#8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b$\u0010%\u00a8\u0006\u0081\u0001"}, d2 = {"Lcom/swipefwd/xmpp/XmppServiceConnection;", "Lorg/jivesoftware/smack/ConnectionListener;", "Lorg/jivesoftware/smackx/ping/PingFailedListener;", "Lorg/jivesoftware/smack/chat/ChatMessageListener;", "Lorg/jivesoftware/smack/chat/ChatManagerListener;", "Lorg/jivesoftware/smack/roster/RosterListener;", "mAccount", "Lcom/swipefwd/xmpp/XmppAccount;", "context", "Landroid/content/Context;", "(Lcom/swipefwd/xmpp/XmppAccount;Landroid/content/Context;)V", "builder", "Lorg/jivesoftware/smack/tcp/XMPPTCPConnectionConfiguration$Builder;", "kotlin.jvm.PlatformType", "getBuilder", "()Lorg/jivesoftware/smack/tcp/XMPPTCPConnectionConfiguration$Builder;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "fileTransferManager", "Lorg/jivesoftware/smackx/filetransfer/FileTransferManager;", "mConnection", "Lorg/jivesoftware/smack/tcp/XMPPTCPConnection;", "mDatabase", "Lcom/swipefwd/utils/AppDatabase;", "getMDatabase", "()Lcom/swipefwd/utils/AppDatabase;", "mDatabase$delegate", "Lkotlin/Lazy;", "mOwnAvatar", "", "rosterEntries", "Ljava/util/ArrayList;", "Lcom/swipefwd/xmpp/XmppRosterEntry;", "Lkotlin/collections/ArrayList;", "getRosterEntries", "()Ljava/util/ArrayList;", "addContact", "", "remoteAccount", "", "alias", "addMessageAndProcessPending", "destinationJID", "message", "authenticated", "connection", "Lorg/jivesoftware/smack/XMPPConnection;", "resumed", "", "chatCreated", "chat", "Lorg/jivesoftware/smack/chat/Chat;", "createdLocally", "clearConversationsWith", "connect", "connected", "connectionClosed", "connectionClosedOnError", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "createConnection", "disconnect", "entriesAdded", "addresses", "", "entriesDeleted", "entriesUpdated", "getAvatarFor", "getCachedAvatar", "xmppJID", "getOfflineMsg", "getRosterEntryFor", "roster", "Lorg/jivesoftware/smack/roster/Roster;", "entry", "Lorg/jivesoftware/smack/roster/RosterEntry;", "getRosterInfo", "Lcom/swipefwd/xmpp/database/Rosters;", "getUserInfo", "Lorg/jivesoftware/smackx/vcardtemp/packet/VCard;", "getXmppJid", "destination", "pingFailed", "presenceChanged", "presence", "Lorg/jivesoftware/smack/packet/Presence;", "processMessage", "Lorg/jivesoftware/smack/packet/Message;", "rebuildRoster", "reconnectingIn", "seconds", "", "reconnectionFailed", "reconnectionSuccessful", "removeContact", "renameContact", "newAlias", "rosterToActivity", "saveMessage", "messageText", "incoming", "isFile", "messageType", "sendImage", "filePath", "sendMessage", "textMessage", "databaseMessage", "Lcom/swipefwd/xmpp/database/Message;", "sendPendingMessages", "sendSubscriptionRequestTo", "sendTyping", "sendTypingStop", "sendUnsubscriptionRequestTo", "sendVoice", "setName", "firstName", "lastName", "setOffline", "setOwnAvatar", "setPresence", "presenceMode", "presenceType", "personalMessage", "singleEntryUpdated", "Companion", "app_debug"})
public final class XmppServiceConnection implements org.jivesoftware.smack.ConnectionListener, org.jivesoftware.smackx.ping.PingFailedListener, org.jivesoftware.smack.chat.ChatMessageListener, org.jivesoftware.smack.chat.ChatManagerListener, org.jivesoftware.smack.roster.RosterListener {
    private final com.swipefwd.xmpp.XmppAccount mAccount = null;
    @org.jetbrains.annotations.NotNull()
    private android.content.Context context;
    private byte[] mOwnAvatar;
    private org.jivesoftware.smack.tcp.XMPPTCPConnection mConnection;
    private org.jivesoftware.smackx.filetransfer.FileTransferManager fileTransferManager;
    private final org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder builder = null;
    private final kotlin.Lazy mDatabase$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.xmpp.XmppServiceConnection.Companion Companion = null;
    private static final java.lang.String TAG = "connection";
    
    public XmppServiceConnection(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.XmppAccount mAccount, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    public final org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder getBuilder() {
        return null;
    }
    
    private final com.swipefwd.utils.AppDatabase getMDatabase() {
        return null;
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class, org.jivesoftware.smack.XMPPException.class, org.jivesoftware.smack.SmackException.class})
    public final void connect() throws java.io.IOException, org.jivesoftware.smack.XMPPException, org.jivesoftware.smack.SmackException {
    }
    
    public final void disconnect() {
    }
    
    private final void setOffline() {
    }
    
    private final void getOfflineMsg() {
    }
    
    private final void createConnection() {
    }
    
    public final void singleEntryUpdated(@org.jetbrains.annotations.Nullable()
    java.lang.String entry) {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.jivesoftware.smack.SmackException.NotConnectedException.class})
    public final void addMessageAndProcessPending(@org.jetbrains.annotations.NotNull()
    java.lang.String destinationJID, @org.jetbrains.annotations.NotNull()
    java.lang.String message) throws org.jivesoftware.smack.SmackException.NotConnectedException {
    }
    
    private final void saveMessage(java.lang.String remoteAccount, java.lang.String messageText, boolean incoming, boolean isFile, java.lang.String messageType) {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.jivesoftware.smack.SmackException.NotConnectedException.class})
    private final void sendMessage(java.lang.String destinationJID, java.lang.String textMessage, com.swipefwd.xmpp.database.Message databaseMessage) throws org.jivesoftware.smack.SmackException.NotConnectedException {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.jivesoftware.smack.SmackException.NotConnectedException.class})
    public final void sendPendingMessages() throws org.jivesoftware.smack.SmackException.NotConnectedException {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.jivesoftware.smack.SmackException.NotConnectedException.class})
    public final void sendTyping(@org.jetbrains.annotations.Nullable()
    java.lang.String destinationJID) throws org.jivesoftware.smack.SmackException.NotConnectedException {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.jivesoftware.smack.SmackException.NotConnectedException.class})
    public final void sendTypingStop(@org.jetbrains.annotations.Nullable()
    java.lang.String destinationJID) throws org.jivesoftware.smack.SmackException.NotConnectedException {
    }
    
    public final void rosterToActivity() {
    }
    
    public final void setPresence(int presenceMode, int presenceType, @org.jetbrains.annotations.Nullable()
    java.lang.String personalMessage) {
    }
    
    public final void setPresence() {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.jivesoftware.smack.SmackException.NotConnectedException.class, org.jivesoftware.smack.XMPPException.XMPPErrorException.class, org.jivesoftware.smack.SmackException.NoResponseException.class})
    public final void setOwnAvatar(@org.jetbrains.annotations.Nullable()
    java.lang.String filePath, @org.jetbrains.annotations.Nullable()
    java.lang.String firstName, @org.jetbrains.annotations.Nullable()
    java.lang.String lastName) throws org.jivesoftware.smack.SmackException.NotConnectedException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {org.jivesoftware.smack.SmackException.NotConnectedException.class, org.jivesoftware.smack.XMPPException.XMPPErrorException.class, org.jivesoftware.smack.SmackException.NoResponseException.class})
    public final void setName(@org.jetbrains.annotations.Nullable()
    java.lang.String firstName, @org.jetbrains.annotations.Nullable()
    java.lang.String lastName) throws org.jivesoftware.smack.SmackException.NotConnectedException, org.jivesoftware.smack.XMPPException.XMPPErrorException, org.jivesoftware.smack.SmackException.NoResponseException {
    }
    
    private final byte[] getAvatarFor(java.lang.String remoteAccount) {
        return null;
    }
    
    private final org.jivesoftware.smackx.vcardtemp.packet.VCard getUserInfo(java.lang.String remoteAccount) {
        return null;
    }
    
    public final void addContact(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String alias) {
    }
    
    public final void removeContact(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteAccount) {
    }
    
    public final void renameContact(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String newAlias) {
    }
    
    public final void clearConversationsWith(@org.jetbrains.annotations.NotNull()
    java.lang.String remoteAccount) {
    }
    
    @java.lang.Override()
    public void connected(@org.jetbrains.annotations.NotNull()
    org.jivesoftware.smack.XMPPConnection connection) {
    }
    
    @java.lang.Override()
    public void authenticated(@org.jetbrains.annotations.NotNull()
    org.jivesoftware.smack.XMPPConnection connection, boolean resumed) {
    }
    
    @java.lang.Override()
    public void connectionClosed() {
    }
    
    @java.lang.Override()
    public void connectionClosedOnError(@org.jetbrains.annotations.NotNull()
    java.lang.Exception e) {
    }
    
    @java.lang.Override()
    public void reconnectionSuccessful() {
    }
    
    @java.lang.Override()
    public void reconnectingIn(int seconds) {
    }
    
    @java.lang.Override()
    public void reconnectionFailed(@org.jetbrains.annotations.NotNull()
    java.lang.Exception e) {
    }
    
    @java.lang.Override()
    public void pingFailed() {
    }
    
    @java.lang.Override()
    public void chatCreated(@org.jetbrains.annotations.NotNull()
    org.jivesoftware.smack.chat.Chat chat, boolean createdLocally) {
    }
    
    @java.lang.Override()
    public void processMessage(@org.jetbrains.annotations.NotNull()
    org.jivesoftware.smack.chat.Chat chat, @org.jetbrains.annotations.NotNull()
    org.jivesoftware.smack.packet.Message message) {
    }
    
    @java.lang.Override()
    public void entriesAdded(@org.jetbrains.annotations.NotNull()
    java.util.Collection<java.lang.String> addresses) {
    }
    
    @java.lang.Override()
    public void entriesUpdated(@org.jetbrains.annotations.NotNull()
    java.util.Collection<java.lang.String> addresses) {
    }
    
    @java.lang.Override()
    public void entriesDeleted(@org.jetbrains.annotations.NotNull()
    java.util.Collection<java.lang.String> addresses) {
    }
    
    @java.lang.Override()
    public void presenceChanged(@org.jetbrains.annotations.NotNull()
    org.jivesoftware.smack.packet.Presence presence) {
    }
    
    private final java.lang.String getXmppJid(java.lang.String destination) {
        return null;
    }
    
    private final java.util.ArrayList<com.swipefwd.xmpp.XmppRosterEntry> getRosterEntries() {
        return null;
    }
    
    private final void rebuildRoster() {
    }
    
    private final com.swipefwd.xmpp.XmppRosterEntry getRosterEntryFor(org.jivesoftware.smack.roster.Roster roster, org.jivesoftware.smack.roster.RosterEntry entry) {
        return null;
    }
    
    public final void sendSubscriptionRequestTo(@org.jetbrains.annotations.NotNull()
    java.lang.String destination) {
    }
    
    private final void sendUnsubscriptionRequestTo(java.lang.String destination) {
    }
    
    private final byte[] getCachedAvatar(java.lang.String xmppJID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.xmpp.database.Rosters getRosterInfo(@org.jetbrains.annotations.Nullable()
    java.lang.String remoteAccount) {
        return null;
    }
    
    public final void sendImage(@org.jetbrains.annotations.NotNull()
    java.lang.String xmppJID, @org.jetbrains.annotations.Nullable()
    java.lang.String filePath) {
    }
    
    public final void sendVoice(@org.jetbrains.annotations.NotNull()
    java.lang.String xmppJID, @org.jetbrains.annotations.Nullable()
    java.lang.String filePath) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/xmpp/XmppServiceConnection$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}