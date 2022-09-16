package com.swipefwd.xmpp.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ChatDao_Impl implements ChatDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Message> __insertionAdapterOfMessage;

  private final EntityInsertionAdapter<Rosters> __insertionAdapterOfRosters;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllRosters;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePendingMessageFlag;

  private final SharedSQLiteStatement __preparedStmtOfSetAllUserToOffline;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMessageByUser;

  private final SharedSQLiteStatement __preparedStmtOfDeleteRoster;

  public ChatDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Message` (`id`,`chatUsers`,`sender`,`senderName`,`recipientName`,`recipient`,`pending`,`read`,`message`,`messageType`,`timeStamp`,`avatar`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
        if (value.getChatUsers() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getChatUsers());
        }
        if (value.getSender() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSender());
        }
        if (value.getSenderName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSenderName());
        }
        if (value.getRecipientName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRecipientName());
        }
        if (value.getRecipient() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRecipient());
        }
        if (value.getPending() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getPending());
        }
        if (value.getRead() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getRead());
        }
        if (value.getMessage() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getMessage());
        }
        if (value.getMessageType() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getMessageType());
        }
        if (value.getTimeStamp() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindLong(11, value.getTimeStamp());
        }
        if (value.getAvatar() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getAvatar());
        }
      }
    };
    this.__insertionAdapterOfRosters = new EntityInsertionAdapter<Rosters>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Rosters` (`id`,`xmppJid`,`avatar`,`name`,`type`,`email`,`pendingMessageCount`,`isAvailable`,`presenceMode`,`personalMessage`,`lastMessage`,`lastMessgeTimeStamp`,`isTyping`,`profile_url`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Rosters value) {
        stmt.bindLong(1, value.getId());
        if (value.getXmppJid() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getXmppJid());
        }
        if (value.getAvatar() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindBlob(3, value.getAvatar());
        }
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getType());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmail());
        }
        if (value.getPendingMessageCount() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, value.getPendingMessageCount());
        }
        final int _tmp = value.isAvailable() ? 1 : 0;
        stmt.bindLong(8, _tmp);
        if (value.getPresenceMode() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getPresenceMode());
        }
        if (value.getPersonalMessage() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getPersonalMessage());
        }
        if (value.getLastMessage() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getLastMessage());
        }
        stmt.bindLong(12, value.getLastMessgeTimeStamp());
        final int _tmp_1 = value.isTyping() ? 1 : 0;
        stmt.bindLong(13, _tmp_1);
        if (value.getProfile_url() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getProfile_url());
        }
      }
    };
    this.__preparedStmtOfDeleteAllRosters = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Rosters";
        return _query;
      }
    };
    this.__preparedStmtOfUpdatePendingMessageFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update Message SET pending=0 WHERE id =?";
        return _query;
      }
    };
    this.__preparedStmtOfSetAllUserToOffline = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update Rosters SET isAvailable=0";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllMessageByUser = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Message WHERE recipient = ? AND sender =?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteRoster = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Rosters WHERE xmppJid = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertMessage(final Message message) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage.insert(message);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertRosters(final List<Rosters> rosters) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRosters.insert(rosters);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertRoster(final Rosters roster) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRosters.insert(roster);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllRosters() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllRosters.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllRosters.release(_stmt);
    }
  }

  @Override
  public void updatePendingMessageFlag(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePendingMessageFlag.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdatePendingMessageFlag.release(_stmt);
    }
  }

  @Override
  public void setAllUserToOffline() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetAllUserToOffline.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetAllUserToOffline.release(_stmt);
    }
  }

  @Override
  public void deleteAllMessageByUser(final String user) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllMessageByUser.acquire();
    int _argIndex = 1;
    if (user == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, user);
    }
    _argIndex = 2;
    if (user == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, user);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllMessageByUser.release(_stmt);
    }
  }

  @Override
  public void deleteRoster(final String user) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteRoster.acquire();
    int _argIndex = 1;
    if (user == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, user);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteRoster.release(_stmt);
    }
  }

  @Override
  public List<Rosters> getAllRosters() {
    final String _sql = "SELECT * FROM Rosters";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfXmppJid = CursorUtil.getColumnIndexOrThrow(_cursor, "xmppJid");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfPendingMessageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pendingMessageCount");
      final int _cursorIndexOfIsAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "isAvailable");
      final int _cursorIndexOfPresenceMode = CursorUtil.getColumnIndexOrThrow(_cursor, "presenceMode");
      final int _cursorIndexOfPersonalMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "personalMessage");
      final int _cursorIndexOfLastMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessage");
      final int _cursorIndexOfLastMessgeTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessgeTimeStamp");
      final int _cursorIndexOfIsTyping = CursorUtil.getColumnIndexOrThrow(_cursor, "isTyping");
      final int _cursorIndexOfProfileUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "profile_url");
      final List<Rosters> _result = new ArrayList<Rosters>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Rosters _item;
        _item = new Rosters();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpXmppJid;
        if (_cursor.isNull(_cursorIndexOfXmppJid)) {
          _tmpXmppJid = null;
        } else {
          _tmpXmppJid = _cursor.getString(_cursorIndexOfXmppJid);
        }
        _item.setXmppJid(_tmpXmppJid);
        final byte[] _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getBlob(_cursorIndexOfAvatar);
        }
        _item.setAvatar(_tmpAvatar);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        _item.setType(_tmpType);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final Integer _tmpPendingMessageCount;
        if (_cursor.isNull(_cursorIndexOfPendingMessageCount)) {
          _tmpPendingMessageCount = null;
        } else {
          _tmpPendingMessageCount = _cursor.getInt(_cursorIndexOfPendingMessageCount);
        }
        _item.setPendingMessageCount(_tmpPendingMessageCount);
        final boolean _tmpIsAvailable;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsAvailable);
        _tmpIsAvailable = _tmp != 0;
        _item.setAvailable(_tmpIsAvailable);
        final Integer _tmpPresenceMode;
        if (_cursor.isNull(_cursorIndexOfPresenceMode)) {
          _tmpPresenceMode = null;
        } else {
          _tmpPresenceMode = _cursor.getInt(_cursorIndexOfPresenceMode);
        }
        _item.setPresenceMode(_tmpPresenceMode);
        final String _tmpPersonalMessage;
        if (_cursor.isNull(_cursorIndexOfPersonalMessage)) {
          _tmpPersonalMessage = null;
        } else {
          _tmpPersonalMessage = _cursor.getString(_cursorIndexOfPersonalMessage);
        }
        _item.setPersonalMessage(_tmpPersonalMessage);
        final String _tmpLastMessage;
        if (_cursor.isNull(_cursorIndexOfLastMessage)) {
          _tmpLastMessage = null;
        } else {
          _tmpLastMessage = _cursor.getString(_cursorIndexOfLastMessage);
        }
        _item.setLastMessage(_tmpLastMessage);
        final long _tmpLastMessgeTimeStamp;
        _tmpLastMessgeTimeStamp = _cursor.getLong(_cursorIndexOfLastMessgeTimeStamp);
        _item.setLastMessgeTimeStamp(_tmpLastMessgeTimeStamp);
        final boolean _tmpIsTyping;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsTyping);
        _tmpIsTyping = _tmp_1 != 0;
        _item.setTyping(_tmpIsTyping);
        final String _tmpProfile_url;
        if (_cursor.isNull(_cursorIndexOfProfileUrl)) {
          _tmpProfile_url = null;
        } else {
          _tmpProfile_url = _cursor.getString(_cursorIndexOfProfileUrl);
        }
        _item.setProfile_url(_tmpProfile_url);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Rosters getRoster(final String jid) {
    final String _sql = "SELECT * FROM Rosters WHERE xmppJid LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (jid == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, jid);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfXmppJid = CursorUtil.getColumnIndexOrThrow(_cursor, "xmppJid");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfPendingMessageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pendingMessageCount");
      final int _cursorIndexOfIsAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "isAvailable");
      final int _cursorIndexOfPresenceMode = CursorUtil.getColumnIndexOrThrow(_cursor, "presenceMode");
      final int _cursorIndexOfPersonalMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "personalMessage");
      final int _cursorIndexOfLastMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessage");
      final int _cursorIndexOfLastMessgeTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessgeTimeStamp");
      final int _cursorIndexOfIsTyping = CursorUtil.getColumnIndexOrThrow(_cursor, "isTyping");
      final int _cursorIndexOfProfileUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "profile_url");
      final Rosters _result;
      if(_cursor.moveToFirst()) {
        _result = new Rosters();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpXmppJid;
        if (_cursor.isNull(_cursorIndexOfXmppJid)) {
          _tmpXmppJid = null;
        } else {
          _tmpXmppJid = _cursor.getString(_cursorIndexOfXmppJid);
        }
        _result.setXmppJid(_tmpXmppJid);
        final byte[] _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getBlob(_cursorIndexOfAvatar);
        }
        _result.setAvatar(_tmpAvatar);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        _result.setType(_tmpType);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _result.setEmail(_tmpEmail);
        final Integer _tmpPendingMessageCount;
        if (_cursor.isNull(_cursorIndexOfPendingMessageCount)) {
          _tmpPendingMessageCount = null;
        } else {
          _tmpPendingMessageCount = _cursor.getInt(_cursorIndexOfPendingMessageCount);
        }
        _result.setPendingMessageCount(_tmpPendingMessageCount);
        final boolean _tmpIsAvailable;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsAvailable);
        _tmpIsAvailable = _tmp != 0;
        _result.setAvailable(_tmpIsAvailable);
        final Integer _tmpPresenceMode;
        if (_cursor.isNull(_cursorIndexOfPresenceMode)) {
          _tmpPresenceMode = null;
        } else {
          _tmpPresenceMode = _cursor.getInt(_cursorIndexOfPresenceMode);
        }
        _result.setPresenceMode(_tmpPresenceMode);
        final String _tmpPersonalMessage;
        if (_cursor.isNull(_cursorIndexOfPersonalMessage)) {
          _tmpPersonalMessage = null;
        } else {
          _tmpPersonalMessage = _cursor.getString(_cursorIndexOfPersonalMessage);
        }
        _result.setPersonalMessage(_tmpPersonalMessage);
        final String _tmpLastMessage;
        if (_cursor.isNull(_cursorIndexOfLastMessage)) {
          _tmpLastMessage = null;
        } else {
          _tmpLastMessage = _cursor.getString(_cursorIndexOfLastMessage);
        }
        _result.setLastMessage(_tmpLastMessage);
        final long _tmpLastMessgeTimeStamp;
        _tmpLastMessgeTimeStamp = _cursor.getLong(_cursorIndexOfLastMessgeTimeStamp);
        _result.setLastMessgeTimeStamp(_tmpLastMessgeTimeStamp);
        final boolean _tmpIsTyping;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsTyping);
        _tmpIsTyping = _tmp_1 != 0;
        _result.setTyping(_tmpIsTyping);
        final String _tmpProfile_url;
        if (_cursor.isNull(_cursorIndexOfProfileUrl)) {
          _tmpProfile_url = null;
        } else {
          _tmpProfile_url = _cursor.getString(_cursorIndexOfProfileUrl);
        }
        _result.setProfile_url(_tmpProfile_url);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getAllMessageByRoster(final String users) {
    final String _sql = "SELECT * FROM Message WHERE chatUsers LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (users == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, users);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfChatUsers = CursorUtil.getColumnIndexOrThrow(_cursor, "chatUsers");
      final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "senderName");
      final int _cursorIndexOfRecipientName = CursorUtil.getColumnIndexOrThrow(_cursor, "recipientName");
      final int _cursorIndexOfRecipient = CursorUtil.getColumnIndexOrThrow(_cursor, "recipient");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfMessageType = CursorUtil.getColumnIndexOrThrow(_cursor, "messageType");
      final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timeStamp");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        _item = new Message();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpChatUsers;
        if (_cursor.isNull(_cursorIndexOfChatUsers)) {
          _tmpChatUsers = null;
        } else {
          _tmpChatUsers = _cursor.getString(_cursorIndexOfChatUsers);
        }
        _item.setChatUsers(_tmpChatUsers);
        final String _tmpSender;
        if (_cursor.isNull(_cursorIndexOfSender)) {
          _tmpSender = null;
        } else {
          _tmpSender = _cursor.getString(_cursorIndexOfSender);
        }
        _item.setSender(_tmpSender);
        final String _tmpSenderName;
        if (_cursor.isNull(_cursorIndexOfSenderName)) {
          _tmpSenderName = null;
        } else {
          _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        }
        _item.setSenderName(_tmpSenderName);
        final String _tmpRecipientName;
        if (_cursor.isNull(_cursorIndexOfRecipientName)) {
          _tmpRecipientName = null;
        } else {
          _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
        }
        _item.setRecipientName(_tmpRecipientName);
        final String _tmpRecipient;
        if (_cursor.isNull(_cursorIndexOfRecipient)) {
          _tmpRecipient = null;
        } else {
          _tmpRecipient = _cursor.getString(_cursorIndexOfRecipient);
        }
        _item.setRecipient(_tmpRecipient);
        final Integer _tmpPending;
        if (_cursor.isNull(_cursorIndexOfPending)) {
          _tmpPending = null;
        } else {
          _tmpPending = _cursor.getInt(_cursorIndexOfPending);
        }
        _item.setPending(_tmpPending);
        final Integer _tmpRead;
        if (_cursor.isNull(_cursorIndexOfRead)) {
          _tmpRead = null;
        } else {
          _tmpRead = _cursor.getInt(_cursorIndexOfRead);
        }
        _item.setRead(_tmpRead);
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        _item.setMessage(_tmpMessage);
        final String _tmpMessageType;
        if (_cursor.isNull(_cursorIndexOfMessageType)) {
          _tmpMessageType = null;
        } else {
          _tmpMessageType = _cursor.getString(_cursorIndexOfMessageType);
        }
        _item.setMessageType(_tmpMessageType);
        final Long _tmpTimeStamp;
        if (_cursor.isNull(_cursorIndexOfTimeStamp)) {
          _tmpTimeStamp = null;
        } else {
          _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
        }
        _item.setTimeStamp(_tmpTimeStamp);
        final String _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
        }
        _item.setAvatar(_tmpAvatar);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getLastMessage() {
    final String _sql = "SELECT * FROM Message ORDER BY id DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfChatUsers = CursorUtil.getColumnIndexOrThrow(_cursor, "chatUsers");
      final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "senderName");
      final int _cursorIndexOfRecipientName = CursorUtil.getColumnIndexOrThrow(_cursor, "recipientName");
      final int _cursorIndexOfRecipient = CursorUtil.getColumnIndexOrThrow(_cursor, "recipient");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfMessageType = CursorUtil.getColumnIndexOrThrow(_cursor, "messageType");
      final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timeStamp");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        _item = new Message();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpChatUsers;
        if (_cursor.isNull(_cursorIndexOfChatUsers)) {
          _tmpChatUsers = null;
        } else {
          _tmpChatUsers = _cursor.getString(_cursorIndexOfChatUsers);
        }
        _item.setChatUsers(_tmpChatUsers);
        final String _tmpSender;
        if (_cursor.isNull(_cursorIndexOfSender)) {
          _tmpSender = null;
        } else {
          _tmpSender = _cursor.getString(_cursorIndexOfSender);
        }
        _item.setSender(_tmpSender);
        final String _tmpSenderName;
        if (_cursor.isNull(_cursorIndexOfSenderName)) {
          _tmpSenderName = null;
        } else {
          _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        }
        _item.setSenderName(_tmpSenderName);
        final String _tmpRecipientName;
        if (_cursor.isNull(_cursorIndexOfRecipientName)) {
          _tmpRecipientName = null;
        } else {
          _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
        }
        _item.setRecipientName(_tmpRecipientName);
        final String _tmpRecipient;
        if (_cursor.isNull(_cursorIndexOfRecipient)) {
          _tmpRecipient = null;
        } else {
          _tmpRecipient = _cursor.getString(_cursorIndexOfRecipient);
        }
        _item.setRecipient(_tmpRecipient);
        final Integer _tmpPending;
        if (_cursor.isNull(_cursorIndexOfPending)) {
          _tmpPending = null;
        } else {
          _tmpPending = _cursor.getInt(_cursorIndexOfPending);
        }
        _item.setPending(_tmpPending);
        final Integer _tmpRead;
        if (_cursor.isNull(_cursorIndexOfRead)) {
          _tmpRead = null;
        } else {
          _tmpRead = _cursor.getInt(_cursorIndexOfRead);
        }
        _item.setRead(_tmpRead);
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        _item.setMessage(_tmpMessage);
        final String _tmpMessageType;
        if (_cursor.isNull(_cursorIndexOfMessageType)) {
          _tmpMessageType = null;
        } else {
          _tmpMessageType = _cursor.getString(_cursorIndexOfMessageType);
        }
        _item.setMessageType(_tmpMessageType);
        final Long _tmpTimeStamp;
        if (_cursor.isNull(_cursorIndexOfTimeStamp)) {
          _tmpTimeStamp = null;
        } else {
          _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
        }
        _item.setTimeStamp(_tmpTimeStamp);
        final String _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
        }
        _item.setAvatar(_tmpAvatar);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getPendingMessage() {
    final String _sql = "SELECT * FROM Message WHERE pending LIKE 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfChatUsers = CursorUtil.getColumnIndexOrThrow(_cursor, "chatUsers");
      final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "senderName");
      final int _cursorIndexOfRecipientName = CursorUtil.getColumnIndexOrThrow(_cursor, "recipientName");
      final int _cursorIndexOfRecipient = CursorUtil.getColumnIndexOrThrow(_cursor, "recipient");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfMessageType = CursorUtil.getColumnIndexOrThrow(_cursor, "messageType");
      final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timeStamp");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        _item = new Message();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpChatUsers;
        if (_cursor.isNull(_cursorIndexOfChatUsers)) {
          _tmpChatUsers = null;
        } else {
          _tmpChatUsers = _cursor.getString(_cursorIndexOfChatUsers);
        }
        _item.setChatUsers(_tmpChatUsers);
        final String _tmpSender;
        if (_cursor.isNull(_cursorIndexOfSender)) {
          _tmpSender = null;
        } else {
          _tmpSender = _cursor.getString(_cursorIndexOfSender);
        }
        _item.setSender(_tmpSender);
        final String _tmpSenderName;
        if (_cursor.isNull(_cursorIndexOfSenderName)) {
          _tmpSenderName = null;
        } else {
          _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        }
        _item.setSenderName(_tmpSenderName);
        final String _tmpRecipientName;
        if (_cursor.isNull(_cursorIndexOfRecipientName)) {
          _tmpRecipientName = null;
        } else {
          _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
        }
        _item.setRecipientName(_tmpRecipientName);
        final String _tmpRecipient;
        if (_cursor.isNull(_cursorIndexOfRecipient)) {
          _tmpRecipient = null;
        } else {
          _tmpRecipient = _cursor.getString(_cursorIndexOfRecipient);
        }
        _item.setRecipient(_tmpRecipient);
        final Integer _tmpPending;
        if (_cursor.isNull(_cursorIndexOfPending)) {
          _tmpPending = null;
        } else {
          _tmpPending = _cursor.getInt(_cursorIndexOfPending);
        }
        _item.setPending(_tmpPending);
        final Integer _tmpRead;
        if (_cursor.isNull(_cursorIndexOfRead)) {
          _tmpRead = null;
        } else {
          _tmpRead = _cursor.getInt(_cursorIndexOfRead);
        }
        _item.setRead(_tmpRead);
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        _item.setMessage(_tmpMessage);
        final String _tmpMessageType;
        if (_cursor.isNull(_cursorIndexOfMessageType)) {
          _tmpMessageType = null;
        } else {
          _tmpMessageType = _cursor.getString(_cursorIndexOfMessageType);
        }
        _item.setMessageType(_tmpMessageType);
        final Long _tmpTimeStamp;
        if (_cursor.isNull(_cursorIndexOfTimeStamp)) {
          _tmpTimeStamp = null;
        } else {
          _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
        }
        _item.setTimeStamp(_tmpTimeStamp);
        final String _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
        }
        _item.setAvatar(_tmpAvatar);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Rosters> isRosterThere(final String remoteAcoount) {
    final String _sql = "SELECT * FROM Rosters WHERE xmppJid LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (remoteAcoount == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, remoteAcoount);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfXmppJid = CursorUtil.getColumnIndexOrThrow(_cursor, "xmppJid");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfPendingMessageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "pendingMessageCount");
      final int _cursorIndexOfIsAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "isAvailable");
      final int _cursorIndexOfPresenceMode = CursorUtil.getColumnIndexOrThrow(_cursor, "presenceMode");
      final int _cursorIndexOfPersonalMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "personalMessage");
      final int _cursorIndexOfLastMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessage");
      final int _cursorIndexOfLastMessgeTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessgeTimeStamp");
      final int _cursorIndexOfIsTyping = CursorUtil.getColumnIndexOrThrow(_cursor, "isTyping");
      final int _cursorIndexOfProfileUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "profile_url");
      final List<Rosters> _result = new ArrayList<Rosters>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Rosters _item;
        _item = new Rosters();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpXmppJid;
        if (_cursor.isNull(_cursorIndexOfXmppJid)) {
          _tmpXmppJid = null;
        } else {
          _tmpXmppJid = _cursor.getString(_cursorIndexOfXmppJid);
        }
        _item.setXmppJid(_tmpXmppJid);
        final byte[] _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getBlob(_cursorIndexOfAvatar);
        }
        _item.setAvatar(_tmpAvatar);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        _item.setType(_tmpType);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final Integer _tmpPendingMessageCount;
        if (_cursor.isNull(_cursorIndexOfPendingMessageCount)) {
          _tmpPendingMessageCount = null;
        } else {
          _tmpPendingMessageCount = _cursor.getInt(_cursorIndexOfPendingMessageCount);
        }
        _item.setPendingMessageCount(_tmpPendingMessageCount);
        final boolean _tmpIsAvailable;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsAvailable);
        _tmpIsAvailable = _tmp != 0;
        _item.setAvailable(_tmpIsAvailable);
        final Integer _tmpPresenceMode;
        if (_cursor.isNull(_cursorIndexOfPresenceMode)) {
          _tmpPresenceMode = null;
        } else {
          _tmpPresenceMode = _cursor.getInt(_cursorIndexOfPresenceMode);
        }
        _item.setPresenceMode(_tmpPresenceMode);
        final String _tmpPersonalMessage;
        if (_cursor.isNull(_cursorIndexOfPersonalMessage)) {
          _tmpPersonalMessage = null;
        } else {
          _tmpPersonalMessage = _cursor.getString(_cursorIndexOfPersonalMessage);
        }
        _item.setPersonalMessage(_tmpPersonalMessage);
        final String _tmpLastMessage;
        if (_cursor.isNull(_cursorIndexOfLastMessage)) {
          _tmpLastMessage = null;
        } else {
          _tmpLastMessage = _cursor.getString(_cursorIndexOfLastMessage);
        }
        _item.setLastMessage(_tmpLastMessage);
        final long _tmpLastMessgeTimeStamp;
        _tmpLastMessgeTimeStamp = _cursor.getLong(_cursorIndexOfLastMessgeTimeStamp);
        _item.setLastMessgeTimeStamp(_tmpLastMessgeTimeStamp);
        final boolean _tmpIsTyping;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsTyping);
        _tmpIsTyping = _tmp_1 != 0;
        _item.setTyping(_tmpIsTyping);
        final String _tmpProfile_url;
        if (_cursor.isNull(_cursorIndexOfProfileUrl)) {
          _tmpProfile_url = null;
        } else {
          _tmpProfile_url = _cursor.getString(_cursorIndexOfProfileUrl);
        }
        _item.setProfile_url(_tmpProfile_url);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getUnreadCountByUser(final String rosterId) {
    final String _sql = "SELECT * FROM Message WHERE read LIKE 1 AND recipient LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (rosterId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, rosterId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfChatUsers = CursorUtil.getColumnIndexOrThrow(_cursor, "chatUsers");
      final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "senderName");
      final int _cursorIndexOfRecipientName = CursorUtil.getColumnIndexOrThrow(_cursor, "recipientName");
      final int _cursorIndexOfRecipient = CursorUtil.getColumnIndexOrThrow(_cursor, "recipient");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfMessageType = CursorUtil.getColumnIndexOrThrow(_cursor, "messageType");
      final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timeStamp");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        _item = new Message();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpChatUsers;
        if (_cursor.isNull(_cursorIndexOfChatUsers)) {
          _tmpChatUsers = null;
        } else {
          _tmpChatUsers = _cursor.getString(_cursorIndexOfChatUsers);
        }
        _item.setChatUsers(_tmpChatUsers);
        final String _tmpSender;
        if (_cursor.isNull(_cursorIndexOfSender)) {
          _tmpSender = null;
        } else {
          _tmpSender = _cursor.getString(_cursorIndexOfSender);
        }
        _item.setSender(_tmpSender);
        final String _tmpSenderName;
        if (_cursor.isNull(_cursorIndexOfSenderName)) {
          _tmpSenderName = null;
        } else {
          _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        }
        _item.setSenderName(_tmpSenderName);
        final String _tmpRecipientName;
        if (_cursor.isNull(_cursorIndexOfRecipientName)) {
          _tmpRecipientName = null;
        } else {
          _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
        }
        _item.setRecipientName(_tmpRecipientName);
        final String _tmpRecipient;
        if (_cursor.isNull(_cursorIndexOfRecipient)) {
          _tmpRecipient = null;
        } else {
          _tmpRecipient = _cursor.getString(_cursorIndexOfRecipient);
        }
        _item.setRecipient(_tmpRecipient);
        final Integer _tmpPending;
        if (_cursor.isNull(_cursorIndexOfPending)) {
          _tmpPending = null;
        } else {
          _tmpPending = _cursor.getInt(_cursorIndexOfPending);
        }
        _item.setPending(_tmpPending);
        final Integer _tmpRead;
        if (_cursor.isNull(_cursorIndexOfRead)) {
          _tmpRead = null;
        } else {
          _tmpRead = _cursor.getInt(_cursorIndexOfRead);
        }
        _item.setRead(_tmpRead);
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        _item.setMessage(_tmpMessage);
        final String _tmpMessageType;
        if (_cursor.isNull(_cursorIndexOfMessageType)) {
          _tmpMessageType = null;
        } else {
          _tmpMessageType = _cursor.getString(_cursorIndexOfMessageType);
        }
        _item.setMessageType(_tmpMessageType);
        final Long _tmpTimeStamp;
        if (_cursor.isNull(_cursorIndexOfTimeStamp)) {
          _tmpTimeStamp = null;
        } else {
          _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
        }
        _item.setTimeStamp(_tmpTimeStamp);
        final String _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
        }
        _item.setAvatar(_tmpAvatar);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Message getLastMessageByRoster(final String users) {
    final String _sql = "SELECT * FROM Message WHERE chatUsers =? ORDER BY id DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (users == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, users);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfChatUsers = CursorUtil.getColumnIndexOrThrow(_cursor, "chatUsers");
      final int _cursorIndexOfSender = CursorUtil.getColumnIndexOrThrow(_cursor, "sender");
      final int _cursorIndexOfSenderName = CursorUtil.getColumnIndexOrThrow(_cursor, "senderName");
      final int _cursorIndexOfRecipientName = CursorUtil.getColumnIndexOrThrow(_cursor, "recipientName");
      final int _cursorIndexOfRecipient = CursorUtil.getColumnIndexOrThrow(_cursor, "recipient");
      final int _cursorIndexOfPending = CursorUtil.getColumnIndexOrThrow(_cursor, "pending");
      final int _cursorIndexOfRead = CursorUtil.getColumnIndexOrThrow(_cursor, "read");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfMessageType = CursorUtil.getColumnIndexOrThrow(_cursor, "messageType");
      final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timeStamp");
      final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
      final Message _result;
      if(_cursor.moveToFirst()) {
        _result = new Message();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpChatUsers;
        if (_cursor.isNull(_cursorIndexOfChatUsers)) {
          _tmpChatUsers = null;
        } else {
          _tmpChatUsers = _cursor.getString(_cursorIndexOfChatUsers);
        }
        _result.setChatUsers(_tmpChatUsers);
        final String _tmpSender;
        if (_cursor.isNull(_cursorIndexOfSender)) {
          _tmpSender = null;
        } else {
          _tmpSender = _cursor.getString(_cursorIndexOfSender);
        }
        _result.setSender(_tmpSender);
        final String _tmpSenderName;
        if (_cursor.isNull(_cursorIndexOfSenderName)) {
          _tmpSenderName = null;
        } else {
          _tmpSenderName = _cursor.getString(_cursorIndexOfSenderName);
        }
        _result.setSenderName(_tmpSenderName);
        final String _tmpRecipientName;
        if (_cursor.isNull(_cursorIndexOfRecipientName)) {
          _tmpRecipientName = null;
        } else {
          _tmpRecipientName = _cursor.getString(_cursorIndexOfRecipientName);
        }
        _result.setRecipientName(_tmpRecipientName);
        final String _tmpRecipient;
        if (_cursor.isNull(_cursorIndexOfRecipient)) {
          _tmpRecipient = null;
        } else {
          _tmpRecipient = _cursor.getString(_cursorIndexOfRecipient);
        }
        _result.setRecipient(_tmpRecipient);
        final Integer _tmpPending;
        if (_cursor.isNull(_cursorIndexOfPending)) {
          _tmpPending = null;
        } else {
          _tmpPending = _cursor.getInt(_cursorIndexOfPending);
        }
        _result.setPending(_tmpPending);
        final Integer _tmpRead;
        if (_cursor.isNull(_cursorIndexOfRead)) {
          _tmpRead = null;
        } else {
          _tmpRead = _cursor.getInt(_cursorIndexOfRead);
        }
        _result.setRead(_tmpRead);
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        _result.setMessage(_tmpMessage);
        final String _tmpMessageType;
        if (_cursor.isNull(_cursorIndexOfMessageType)) {
          _tmpMessageType = null;
        } else {
          _tmpMessageType = _cursor.getString(_cursorIndexOfMessageType);
        }
        _result.setMessageType(_tmpMessageType);
        final Long _tmpTimeStamp;
        if (_cursor.isNull(_cursorIndexOfTimeStamp)) {
          _tmpTimeStamp = null;
        } else {
          _tmpTimeStamp = _cursor.getLong(_cursorIndexOfTimeStamp);
        }
        _result.setTimeStamp(_tmpTimeStamp);
        final String _tmpAvatar;
        if (_cursor.isNull(_cursorIndexOfAvatar)) {
          _tmpAvatar = null;
        } else {
          _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
        }
        _result.setAvatar(_tmpAvatar);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
