package com.swipefwd.utils;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.swipefwd.ui.profile.UserProfileDao;
import com.swipefwd.ui.profile.UserProfileDao_Impl;
import com.swipefwd.xmpp.database.ChatDao;
import com.swipefwd.xmpp.database.ChatDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserProfileDao _userProfileDao;

  private volatile ChatDao _chatDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AstroSignModel` (`icon` TEXT, `id` INTEGER, `name` TEXT, `isSelected` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CastModel` (`id` INTEGER, `name` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ChildrenModel` (`id` INTEGER, `name` TEXT, `isSelected` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LanguageModel` (`id` INTEGER, `name` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `LevelModel` (`id` INTEGER, `name` TEXT NOT NULL, PRIMARY KEY(`name`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ReligionModel` (`id` INTEGER, `name` TEXT, `isSelected` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SmokingModel` (`id` INTEGER, `name` TEXT, `isSelected` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `RelationshipModel` (`id` INTEGER, `name` TEXT, `isSelected` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CovidModel` (`id` INTEGER, `name` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Rosters` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `xmppJid` TEXT, `avatar` BLOB, `name` TEXT, `type` TEXT, `email` TEXT, `pendingMessageCount` INTEGER, `isAvailable` INTEGER NOT NULL, `presenceMode` INTEGER, `personalMessage` TEXT, `lastMessage` TEXT, `lastMessgeTimeStamp` INTEGER NOT NULL, `isTyping` INTEGER NOT NULL, `profile_url` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Message` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `chatUsers` TEXT NOT NULL, `sender` TEXT NOT NULL, `senderName` TEXT NOT NULL, `recipientName` TEXT NOT NULL, `recipient` TEXT NOT NULL, `pending` INTEGER, `read` INTEGER, `message` TEXT, `messageType` TEXT, `timeStamp` INTEGER, `avatar` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a7834397154bd3470fc210ead724ac54')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `AstroSignModel`");
        _db.execSQL("DROP TABLE IF EXISTS `CastModel`");
        _db.execSQL("DROP TABLE IF EXISTS `ChildrenModel`");
        _db.execSQL("DROP TABLE IF EXISTS `LanguageModel`");
        _db.execSQL("DROP TABLE IF EXISTS `LevelModel`");
        _db.execSQL("DROP TABLE IF EXISTS `ReligionModel`");
        _db.execSQL("DROP TABLE IF EXISTS `SmokingModel`");
        _db.execSQL("DROP TABLE IF EXISTS `RelationshipModel`");
        _db.execSQL("DROP TABLE IF EXISTS `CovidModel`");
        _db.execSQL("DROP TABLE IF EXISTS `Rosters`");
        _db.execSQL("DROP TABLE IF EXISTS `Message`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsAstroSignModel = new HashMap<String, TableInfo.Column>(4);
        _columnsAstroSignModel.put("icon", new TableInfo.Column("icon", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAstroSignModel.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAstroSignModel.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAstroSignModel.put("isSelected", new TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAstroSignModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAstroSignModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAstroSignModel = new TableInfo("AstroSignModel", _columnsAstroSignModel, _foreignKeysAstroSignModel, _indicesAstroSignModel);
        final TableInfo _existingAstroSignModel = TableInfo.read(_db, "AstroSignModel");
        if (! _infoAstroSignModel.equals(_existingAstroSignModel)) {
          return new RoomOpenHelper.ValidationResult(false, "AstroSignModel(com.swipefwd.data.models.AstroSignListModel.AstroSignModel).\n"
                  + " Expected:\n" + _infoAstroSignModel + "\n"
                  + " Found:\n" + _existingAstroSignModel);
        }
        final HashMap<String, TableInfo.Column> _columnsCastModel = new HashMap<String, TableInfo.Column>(2);
        _columnsCastModel.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCastModel.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCastModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCastModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCastModel = new TableInfo("CastModel", _columnsCastModel, _foreignKeysCastModel, _indicesCastModel);
        final TableInfo _existingCastModel = TableInfo.read(_db, "CastModel");
        if (! _infoCastModel.equals(_existingCastModel)) {
          return new RoomOpenHelper.ValidationResult(false, "CastModel(com.swipefwd.data.models.CastListModel.CastModel).\n"
                  + " Expected:\n" + _infoCastModel + "\n"
                  + " Found:\n" + _existingCastModel);
        }
        final HashMap<String, TableInfo.Column> _columnsChildrenModel = new HashMap<String, TableInfo.Column>(3);
        _columnsChildrenModel.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChildrenModel.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChildrenModel.put("isSelected", new TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysChildrenModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesChildrenModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoChildrenModel = new TableInfo("ChildrenModel", _columnsChildrenModel, _foreignKeysChildrenModel, _indicesChildrenModel);
        final TableInfo _existingChildrenModel = TableInfo.read(_db, "ChildrenModel");
        if (! _infoChildrenModel.equals(_existingChildrenModel)) {
          return new RoomOpenHelper.ValidationResult(false, "ChildrenModel(com.swipefwd.data.models.ChildrenListModel.ChildrenModel).\n"
                  + " Expected:\n" + _infoChildrenModel + "\n"
                  + " Found:\n" + _existingChildrenModel);
        }
        final HashMap<String, TableInfo.Column> _columnsLanguageModel = new HashMap<String, TableInfo.Column>(2);
        _columnsLanguageModel.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLanguageModel.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLanguageModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLanguageModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLanguageModel = new TableInfo("LanguageModel", _columnsLanguageModel, _foreignKeysLanguageModel, _indicesLanguageModel);
        final TableInfo _existingLanguageModel = TableInfo.read(_db, "LanguageModel");
        if (! _infoLanguageModel.equals(_existingLanguageModel)) {
          return new RoomOpenHelper.ValidationResult(false, "LanguageModel(com.swipefwd.data.models.LanguageListModel.LanguageModel).\n"
                  + " Expected:\n" + _infoLanguageModel + "\n"
                  + " Found:\n" + _existingLanguageModel);
        }
        final HashMap<String, TableInfo.Column> _columnsLevelModel = new HashMap<String, TableInfo.Column>(2);
        _columnsLevelModel.put("id", new TableInfo.Column("id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLevelModel.put("name", new TableInfo.Column("name", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLevelModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLevelModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLevelModel = new TableInfo("LevelModel", _columnsLevelModel, _foreignKeysLevelModel, _indicesLevelModel);
        final TableInfo _existingLevelModel = TableInfo.read(_db, "LevelModel");
        if (! _infoLevelModel.equals(_existingLevelModel)) {
          return new RoomOpenHelper.ValidationResult(false, "LevelModel(com.swipefwd.data.models.EducationLevelListModel.LevelModel).\n"
                  + " Expected:\n" + _infoLevelModel + "\n"
                  + " Found:\n" + _existingLevelModel);
        }
        final HashMap<String, TableInfo.Column> _columnsReligionModel = new HashMap<String, TableInfo.Column>(3);
        _columnsReligionModel.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReligionModel.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReligionModel.put("isSelected", new TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReligionModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReligionModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReligionModel = new TableInfo("ReligionModel", _columnsReligionModel, _foreignKeysReligionModel, _indicesReligionModel);
        final TableInfo _existingReligionModel = TableInfo.read(_db, "ReligionModel");
        if (! _infoReligionModel.equals(_existingReligionModel)) {
          return new RoomOpenHelper.ValidationResult(false, "ReligionModel(com.swipefwd.data.models.ReligionListModel.ReligionModel).\n"
                  + " Expected:\n" + _infoReligionModel + "\n"
                  + " Found:\n" + _existingReligionModel);
        }
        final HashMap<String, TableInfo.Column> _columnsSmokingModel = new HashMap<String, TableInfo.Column>(3);
        _columnsSmokingModel.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmokingModel.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmokingModel.put("isSelected", new TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSmokingModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSmokingModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSmokingModel = new TableInfo("SmokingModel", _columnsSmokingModel, _foreignKeysSmokingModel, _indicesSmokingModel);
        final TableInfo _existingSmokingModel = TableInfo.read(_db, "SmokingModel");
        if (! _infoSmokingModel.equals(_existingSmokingModel)) {
          return new RoomOpenHelper.ValidationResult(false, "SmokingModel(com.swipefwd.data.models.SmokingListModel.SmokingModel).\n"
                  + " Expected:\n" + _infoSmokingModel + "\n"
                  + " Found:\n" + _existingSmokingModel);
        }
        final HashMap<String, TableInfo.Column> _columnsRelationshipModel = new HashMap<String, TableInfo.Column>(3);
        _columnsRelationshipModel.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRelationshipModel.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRelationshipModel.put("isSelected", new TableInfo.Column("isSelected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRelationshipModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRelationshipModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRelationshipModel = new TableInfo("RelationshipModel", _columnsRelationshipModel, _foreignKeysRelationshipModel, _indicesRelationshipModel);
        final TableInfo _existingRelationshipModel = TableInfo.read(_db, "RelationshipModel");
        if (! _infoRelationshipModel.equals(_existingRelationshipModel)) {
          return new RoomOpenHelper.ValidationResult(false, "RelationshipModel(com.swipefwd.data.models.RelationshipListModel.RelationshipModel).\n"
                  + " Expected:\n" + _infoRelationshipModel + "\n"
                  + " Found:\n" + _existingRelationshipModel);
        }
        final HashMap<String, TableInfo.Column> _columnsCovidModel = new HashMap<String, TableInfo.Column>(2);
        _columnsCovidModel.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCovidModel.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCovidModel = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCovidModel = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCovidModel = new TableInfo("CovidModel", _columnsCovidModel, _foreignKeysCovidModel, _indicesCovidModel);
        final TableInfo _existingCovidModel = TableInfo.read(_db, "CovidModel");
        if (! _infoCovidModel.equals(_existingCovidModel)) {
          return new RoomOpenHelper.ValidationResult(false, "CovidModel(com.swipefwd.data.models.CovidVaccineListModel.CovidModel).\n"
                  + " Expected:\n" + _infoCovidModel + "\n"
                  + " Found:\n" + _existingCovidModel);
        }
        final HashMap<String, TableInfo.Column> _columnsRosters = new HashMap<String, TableInfo.Column>(14);
        _columnsRosters.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("xmppJid", new TableInfo.Column("xmppJid", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("avatar", new TableInfo.Column("avatar", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("pendingMessageCount", new TableInfo.Column("pendingMessageCount", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("isAvailable", new TableInfo.Column("isAvailable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("presenceMode", new TableInfo.Column("presenceMode", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("personalMessage", new TableInfo.Column("personalMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("lastMessage", new TableInfo.Column("lastMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("lastMessgeTimeStamp", new TableInfo.Column("lastMessgeTimeStamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("isTyping", new TableInfo.Column("isTyping", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRosters.put("profile_url", new TableInfo.Column("profile_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRosters = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRosters = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRosters = new TableInfo("Rosters", _columnsRosters, _foreignKeysRosters, _indicesRosters);
        final TableInfo _existingRosters = TableInfo.read(_db, "Rosters");
        if (! _infoRosters.equals(_existingRosters)) {
          return new RoomOpenHelper.ValidationResult(false, "Rosters(com.swipefwd.xmpp.database.Rosters).\n"
                  + " Expected:\n" + _infoRosters + "\n"
                  + " Found:\n" + _existingRosters);
        }
        final HashMap<String, TableInfo.Column> _columnsMessage = new HashMap<String, TableInfo.Column>(12);
        _columnsMessage.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("chatUsers", new TableInfo.Column("chatUsers", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("sender", new TableInfo.Column("sender", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("senderName", new TableInfo.Column("senderName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("recipientName", new TableInfo.Column("recipientName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("recipient", new TableInfo.Column("recipient", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("pending", new TableInfo.Column("pending", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("read", new TableInfo.Column("read", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("message", new TableInfo.Column("message", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("messageType", new TableInfo.Column("messageType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("timeStamp", new TableInfo.Column("timeStamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMessage.put("avatar", new TableInfo.Column("avatar", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMessage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMessage = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMessage = new TableInfo("Message", _columnsMessage, _foreignKeysMessage, _indicesMessage);
        final TableInfo _existingMessage = TableInfo.read(_db, "Message");
        if (! _infoMessage.equals(_existingMessage)) {
          return new RoomOpenHelper.ValidationResult(false, "Message(com.swipefwd.xmpp.database.Message).\n"
                  + " Expected:\n" + _infoMessage + "\n"
                  + " Found:\n" + _existingMessage);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a7834397154bd3470fc210ead724ac54", "3bf541254d8d5bf38a6b02f0207479f8");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "AstroSignModel","CastModel","ChildrenModel","LanguageModel","LevelModel","ReligionModel","SmokingModel","RelationshipModel","CovidModel","Rosters","Message");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `AstroSignModel`");
      _db.execSQL("DELETE FROM `CastModel`");
      _db.execSQL("DELETE FROM `ChildrenModel`");
      _db.execSQL("DELETE FROM `LanguageModel`");
      _db.execSQL("DELETE FROM `LevelModel`");
      _db.execSQL("DELETE FROM `ReligionModel`");
      _db.execSQL("DELETE FROM `SmokingModel`");
      _db.execSQL("DELETE FROM `RelationshipModel`");
      _db.execSQL("DELETE FROM `CovidModel`");
      _db.execSQL("DELETE FROM `Rosters`");
      _db.execSQL("DELETE FROM `Message`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserProfileDao.class, UserProfileDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ChatDao.class, ChatDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public UserProfileDao userProfileDao() {
    if (_userProfileDao != null) {
      return _userProfileDao;
    } else {
      synchronized(this) {
        if(_userProfileDao == null) {
          _userProfileDao = new UserProfileDao_Impl(this);
        }
        return _userProfileDao;
      }
    }
  }

  @Override
  public ChatDao chatDao() {
    if (_chatDao != null) {
      return _chatDao;
    } else {
      synchronized(this) {
        if(_chatDao == null) {
          _chatDao = new ChatDao_Impl(this);
        }
        return _chatDao;
      }
    }
  }
}
