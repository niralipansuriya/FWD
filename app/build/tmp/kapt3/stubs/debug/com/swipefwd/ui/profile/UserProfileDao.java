package com.swipefwd.ui.profile;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\b\u0010\u0004\u001a\u00020\u0003H\'J\b\u0010\u0005\u001a\u00020\u0003H\'J\b\u0010\u0006\u001a\u00020\u0003H\'J\b\u0010\u0007\u001a\u00020\u0003H\'J\b\u0010\b\u001a\u00020\u0003H\'J\b\u0010\t\u001a\u00020\u0003H\'J\b\u0010\n\u001a\u00020\u0003H\'J\b\u0010\u000b\u001a\u00020\u0003H\'J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\'J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rH\'J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rH\'J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\rH\'J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\rH\'J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\rH\'J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\rH\'J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\rH\'J\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\rH\'J\u0016\u0010\u001f\u001a\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100\rH\'J\u0016\u0010!\u001a\u00020\u00032\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00120\rH\'J\u0016\u0010#\u001a\u00020\u00032\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00140\rH\'J\u0016\u0010%\u001a\u00020\u00032\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00160\rH\'J\u0016\u0010&\u001a\u00020\u00032\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00180\rH\'J\u0016\u0010(\u001a\u00020\u00032\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001a0\rH\'J\u0016\u0010*\u001a\u00020\u00032\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\'J\u0016\u0010,\u001a\u00020\u00032\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001c0\rH\'J\u0016\u0010.\u001a\u00020\u00032\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e0\rH\'J \u00100\u001a\u00020\u00032\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\'J \u00107\u001a\u00020\u00032\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\'\u00a8\u00068"}, d2 = {"Lcom/swipefwd/ui/profile/UserProfileDao;", "", "deleteAllAstroSign", "", "deleteAllCast", "deleteAllChildren", "deleteAllLanguage", "deleteAllLevel", "deleteAllRelationship", "deleteAllReligion", "deleteAllSmoking", "deleteAllVaccineStatus", "getAllAstroSign", "", "Lcom/swipefwd/data/models/AstroSignListModel$AstroSignModel;", "getAllCast", "Lcom/swipefwd/data/models/CastListModel$CastModel;", "getAllChildren", "Lcom/swipefwd/data/models/ChildrenListModel$ChildrenModel;", "getAllLanguage", "Lcom/swipefwd/data/models/LanguageListModel$LanguageModel;", "getAllLevel", "Lcom/swipefwd/data/models/EducationLevelListModel$LevelModel;", "getAllRelationship", "Lcom/swipefwd/data/models/RelationshipListModel$RelationshipModel;", "getAllReligion", "Lcom/swipefwd/data/models/ReligionListModel$ReligionModel;", "getAllSmoking", "Lcom/swipefwd/data/models/SmokingListModel$SmokingModel;", "getAllVaccineStatus", "Lcom/swipefwd/data/models/CovidVaccineListModel$CovidModel;", "insertAllCast", "castList", "insertAllChildren", "childrenList", "insertAllLanguage", "languageList", "insertAllLevel", "insertAllRelationship", "relationshipList", "insertAllReligion", "religionList", "insertAllSigns", "signList", "insertAllSmoking", "smokingList", "insertAllVaccineStatus", "statusList", "updateReligion", "id", "", "name", "", "isSelected", "", "updateSign", "app_debug"})
public abstract interface UserProfileDao {
    
    /**
     * AstroSign List
     */
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAllSigns(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.AstroSignListModel.AstroSignModel> signList);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM AstroSignModel")
    public abstract java.util.List<com.swipefwd.data.models.AstroSignListModel.AstroSignModel> getAllAstroSign();
    
    @androidx.room.Query(value = "Update AstroSignModel set name=:name,isSelected=:isSelected where id =:id")
    public abstract void updateSign(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, boolean isSelected);
    
    @androidx.room.Query(value = "DELETE FROM AstroSignModel")
    public abstract void deleteAllAstroSign();
    
    /**
     * Cast List
     */
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAllCast(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.CastListModel.CastModel> castList);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM CastModel")
    public abstract java.util.List<com.swipefwd.data.models.CastListModel.CastModel> getAllCast();
    
    @androidx.room.Query(value = "DELETE FROM CastModel")
    public abstract void deleteAllCast();
    
    /**
     * Children List
     */
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAllChildren(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.ChildrenListModel.ChildrenModel> childrenList);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM ChildrenModel")
    public abstract java.util.List<com.swipefwd.data.models.ChildrenListModel.ChildrenModel> getAllChildren();
    
    @androidx.room.Query(value = "DELETE FROM ChildrenModel")
    public abstract void deleteAllChildren();
    
    /**
     * Language List
     */
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAllLanguage(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.LanguageListModel.LanguageModel> languageList);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM LanguageModel")
    public abstract java.util.List<com.swipefwd.data.models.LanguageListModel.LanguageModel> getAllLanguage();
    
    @androidx.room.Query(value = "DELETE FROM LanguageModel")
    public abstract void deleteAllLanguage();
    
    /**
     * Education Level List
     */
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAllLevel(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.EducationLevelListModel.LevelModel> languageList);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM LevelModel")
    public abstract java.util.List<com.swipefwd.data.models.EducationLevelListModel.LevelModel> getAllLevel();
    
    @androidx.room.Query(value = "DELETE FROM LevelModel")
    public abstract void deleteAllLevel();
    
    /**
     * Religion List
     */
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAllReligion(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.ReligionListModel.ReligionModel> religionList);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM ReligionModel")
    public abstract java.util.List<com.swipefwd.data.models.ReligionListModel.ReligionModel> getAllReligion();
    
    @androidx.room.Query(value = "Update ReligionModel set name=:name,isSelected=:isSelected where id =:id")
    public abstract void updateReligion(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, boolean isSelected);
    
    @androidx.room.Query(value = "DELETE FROM ReligionModel")
    public abstract void deleteAllReligion();
    
    /**
     * Smoking List
     */
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAllSmoking(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.SmokingListModel.SmokingModel> smokingList);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM SmokingModel")
    public abstract java.util.List<com.swipefwd.data.models.SmokingListModel.SmokingModel> getAllSmoking();
    
    @androidx.room.Query(value = "DELETE FROM SmokingModel")
    public abstract void deleteAllSmoking();
    
    /**
     * Relationship List
     */
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAllRelationship(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.RelationshipListModel.RelationshipModel> relationshipList);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM RelationshipModel")
    public abstract java.util.List<com.swipefwd.data.models.RelationshipListModel.RelationshipModel> getAllRelationship();
    
    @androidx.room.Query(value = "DELETE FROM RelationshipModel")
    public abstract void deleteAllRelationship();
    
    /**
     * Vaccine Status List
     */
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAllVaccineStatus(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.CovidVaccineListModel.CovidModel> statusList);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM CovidModel")
    public abstract java.util.List<com.swipefwd.data.models.CovidVaccineListModel.CovidModel> getAllVaccineStatus();
    
    @androidx.room.Query(value = "DELETE FROM CovidModel")
    public abstract void deleteAllVaccineStatus();
}