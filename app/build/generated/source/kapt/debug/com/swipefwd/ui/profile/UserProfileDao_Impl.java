package com.swipefwd.ui.profile;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.swipefwd.data.models.AstroSignListModel;
import com.swipefwd.data.models.CastListModel;
import com.swipefwd.data.models.ChildrenListModel;
import com.swipefwd.data.models.CovidVaccineListModel;
import com.swipefwd.data.models.EducationLevelListModel;
import com.swipefwd.data.models.LanguageListModel;
import com.swipefwd.data.models.RelationshipListModel;
import com.swipefwd.data.models.ReligionListModel;
import com.swipefwd.data.models.SmokingListModel;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserProfileDao_Impl implements UserProfileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AstroSignListModel.AstroSignModel> __insertionAdapterOfAstroSignModel;

  private final EntityInsertionAdapter<CastListModel.CastModel> __insertionAdapterOfCastModel;

  private final EntityInsertionAdapter<ChildrenListModel.ChildrenModel> __insertionAdapterOfChildrenModel;

  private final EntityInsertionAdapter<LanguageListModel.LanguageModel> __insertionAdapterOfLanguageModel;

  private final EntityInsertionAdapter<EducationLevelListModel.LevelModel> __insertionAdapterOfLevelModel;

  private final EntityInsertionAdapter<ReligionListModel.ReligionModel> __insertionAdapterOfReligionModel;

  private final EntityInsertionAdapter<SmokingListModel.SmokingModel> __insertionAdapterOfSmokingModel;

  private final EntityInsertionAdapter<RelationshipListModel.RelationshipModel> __insertionAdapterOfRelationshipModel;

  private final EntityInsertionAdapter<CovidVaccineListModel.CovidModel> __insertionAdapterOfCovidModel;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSign;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllAstroSign;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCast;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllChildren;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllLanguage;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllLevel;

  private final SharedSQLiteStatement __preparedStmtOfUpdateReligion;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllReligion;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllSmoking;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllRelationship;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllVaccineStatus;

  public UserProfileDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAstroSignModel = new EntityInsertionAdapter<AstroSignListModel.AstroSignModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `AstroSignModel` (`icon`,`id`,`name`,`isSelected`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AstroSignListModel.AstroSignModel value) {
        if (value.getIcon() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getIcon());
        }
        if (value.getId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        final int _tmp = value.isSelected() ? 1 : 0;
        stmt.bindLong(4, _tmp);
      }
    };
    this.__insertionAdapterOfCastModel = new EntityInsertionAdapter<CastListModel.CastModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `CastModel` (`id`,`name`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CastListModel.CastModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
    this.__insertionAdapterOfChildrenModel = new EntityInsertionAdapter<ChildrenListModel.ChildrenModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ChildrenModel` (`id`,`name`,`isSelected`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ChildrenListModel.ChildrenModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final int _tmp = value.isSelected() ? 1 : 0;
        stmt.bindLong(3, _tmp);
      }
    };
    this.__insertionAdapterOfLanguageModel = new EntityInsertionAdapter<LanguageListModel.LanguageModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `LanguageModel` (`id`,`name`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, LanguageListModel.LanguageModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
    this.__insertionAdapterOfLevelModel = new EntityInsertionAdapter<EducationLevelListModel.LevelModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `LevelModel` (`id`,`name`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EducationLevelListModel.LevelModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
    this.__insertionAdapterOfReligionModel = new EntityInsertionAdapter<ReligionListModel.ReligionModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ReligionModel` (`id`,`name`,`isSelected`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ReligionListModel.ReligionModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final int _tmp = value.isSelected() ? 1 : 0;
        stmt.bindLong(3, _tmp);
      }
    };
    this.__insertionAdapterOfSmokingModel = new EntityInsertionAdapter<SmokingListModel.SmokingModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `SmokingModel` (`id`,`name`,`isSelected`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SmokingListModel.SmokingModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final int _tmp = value.isSelected() ? 1 : 0;
        stmt.bindLong(3, _tmp);
      }
    };
    this.__insertionAdapterOfRelationshipModel = new EntityInsertionAdapter<RelationshipListModel.RelationshipModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `RelationshipModel` (`id`,`name`,`isSelected`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RelationshipListModel.RelationshipModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        final int _tmp = value.isSelected() ? 1 : 0;
        stmt.bindLong(3, _tmp);
      }
    };
    this.__insertionAdapterOfCovidModel = new EntityInsertionAdapter<CovidVaccineListModel.CovidModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `CovidModel` (`id`,`name`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CovidVaccineListModel.CovidModel value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
    this.__preparedStmtOfUpdateSign = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update AstroSignModel set name=?,isSelected=? where id =?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllAstroSign = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM AstroSignModel";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllCast = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM CastModel";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllChildren = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ChildrenModel";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllLanguage = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM LanguageModel";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllLevel = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM LevelModel";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateReligion = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update ReligionModel set name=?,isSelected=? where id =?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllReligion = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ReligionModel";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllSmoking = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM SmokingModel";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllRelationship = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM RelationshipModel";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllVaccineStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM CovidModel";
        return _query;
      }
    };
  }

  @Override
  public void insertAllSigns(final List<AstroSignListModel.AstroSignModel> signList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAstroSignModel.insert(signList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllCast(final List<CastListModel.CastModel> castList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCastModel.insert(castList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllChildren(final List<ChildrenListModel.ChildrenModel> childrenList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChildrenModel.insert(childrenList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllLanguage(final List<LanguageListModel.LanguageModel> languageList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLanguageModel.insert(languageList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllLevel(final List<EducationLevelListModel.LevelModel> languageList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfLevelModel.insert(languageList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllReligion(final List<ReligionListModel.ReligionModel> religionList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfReligionModel.insert(religionList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllSmoking(final List<SmokingListModel.SmokingModel> smokingList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSmokingModel.insert(smokingList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllRelationship(
      final List<RelationshipListModel.RelationshipModel> relationshipList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRelationshipModel.insert(relationshipList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllVaccineStatus(final List<CovidVaccineListModel.CovidModel> statusList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCovidModel.insert(statusList);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateSign(final int id, final String name, final boolean isSelected) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSign.acquire();
    int _argIndex = 1;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    _argIndex = 2;
    final int _tmp = isSelected ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateSign.release(_stmt);
    }
  }

  @Override
  public void deleteAllAstroSign() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllAstroSign.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllAstroSign.release(_stmt);
    }
  }

  @Override
  public void deleteAllCast() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCast.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllCast.release(_stmt);
    }
  }

  @Override
  public void deleteAllChildren() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllChildren.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllChildren.release(_stmt);
    }
  }

  @Override
  public void deleteAllLanguage() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllLanguage.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllLanguage.release(_stmt);
    }
  }

  @Override
  public void deleteAllLevel() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllLevel.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllLevel.release(_stmt);
    }
  }

  @Override
  public void updateReligion(final int id, final String name, final boolean isSelected) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateReligion.acquire();
    int _argIndex = 1;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    _argIndex = 2;
    final int _tmp = isSelected ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateReligion.release(_stmt);
    }
  }

  @Override
  public void deleteAllReligion() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllReligion.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllReligion.release(_stmt);
    }
  }

  @Override
  public void deleteAllSmoking() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllSmoking.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllSmoking.release(_stmt);
    }
  }

  @Override
  public void deleteAllRelationship() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllRelationship.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllRelationship.release(_stmt);
    }
  }

  @Override
  public void deleteAllVaccineStatus() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllVaccineStatus.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllVaccineStatus.release(_stmt);
    }
  }

  @Override
  public List<AstroSignListModel.AstroSignModel> getAllAstroSign() {
    final String _sql = "SELECT * FROM AstroSignModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final List<AstroSignListModel.AstroSignModel> _result = new ArrayList<AstroSignListModel.AstroSignModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final AstroSignListModel.AstroSignModel _item;
        final String _tmpIcon;
        if (_cursor.isNull(_cursorIndexOfIcon)) {
          _tmpIcon = null;
        } else {
          _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
        }
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final boolean _tmpIsSelected;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp != 0;
        _item = new AstroSignListModel.AstroSignModel(_tmpIcon,_tmpId,_tmpName,_tmpIsSelected);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<CastListModel.CastModel> getAllCast() {
    final String _sql = "SELECT * FROM CastModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final List<CastListModel.CastModel> _result = new ArrayList<CastListModel.CastModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CastListModel.CastModel _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item = new CastListModel.CastModel(_tmpId,_tmpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<ChildrenListModel.ChildrenModel> getAllChildren() {
    final String _sql = "SELECT * FROM ChildrenModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final List<ChildrenListModel.ChildrenModel> _result = new ArrayList<ChildrenListModel.ChildrenModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ChildrenListModel.ChildrenModel _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final boolean _tmpIsSelected;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp != 0;
        _item = new ChildrenListModel.ChildrenModel(_tmpId,_tmpName,_tmpIsSelected);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<LanguageListModel.LanguageModel> getAllLanguage() {
    final String _sql = "SELECT * FROM LanguageModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final List<LanguageListModel.LanguageModel> _result = new ArrayList<LanguageListModel.LanguageModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final LanguageListModel.LanguageModel _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item = new LanguageListModel.LanguageModel(_tmpId,_tmpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<EducationLevelListModel.LevelModel> getAllLevel() {
    final String _sql = "SELECT * FROM LevelModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final List<EducationLevelListModel.LevelModel> _result = new ArrayList<EducationLevelListModel.LevelModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final EducationLevelListModel.LevelModel _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item = new EducationLevelListModel.LevelModel(_tmpId,_tmpName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<ReligionListModel.ReligionModel> getAllReligion() {
    final String _sql = "SELECT * FROM ReligionModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final List<ReligionListModel.ReligionModel> _result = new ArrayList<ReligionListModel.ReligionModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ReligionListModel.ReligionModel _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final boolean _tmpIsSelected;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp != 0;
        _item = new ReligionListModel.ReligionModel(_tmpId,_tmpName,_tmpIsSelected);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<SmokingListModel.SmokingModel> getAllSmoking() {
    final String _sql = "SELECT * FROM SmokingModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final List<SmokingListModel.SmokingModel> _result = new ArrayList<SmokingListModel.SmokingModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final SmokingListModel.SmokingModel _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final boolean _tmpIsSelected;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp != 0;
        _item = new SmokingListModel.SmokingModel(_tmpId,_tmpName,_tmpIsSelected);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<RelationshipListModel.RelationshipModel> getAllRelationship() {
    final String _sql = "SELECT * FROM RelationshipModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfIsSelected = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelected");
      final List<RelationshipListModel.RelationshipModel> _result = new ArrayList<RelationshipListModel.RelationshipModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RelationshipListModel.RelationshipModel _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final boolean _tmpIsSelected;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsSelected);
        _tmpIsSelected = _tmp != 0;
        _item = new RelationshipListModel.RelationshipModel(_tmpId,_tmpName,_tmpIsSelected);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<CovidVaccineListModel.CovidModel> getAllVaccineStatus() {
    final String _sql = "SELECT * FROM CovidModel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final List<CovidVaccineListModel.CovidModel> _result = new ArrayList<CovidVaccineListModel.CovidModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CovidVaccineListModel.CovidModel _item;
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item = new CovidVaccineListModel.CovidModel(_tmpId,_tmpName);
        _result.add(_item);
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
