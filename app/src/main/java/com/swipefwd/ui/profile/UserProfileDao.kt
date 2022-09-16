package com.swipefwd.ui.profile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.swipefwd.data.models.*

@Dao
interface UserProfileDao {
    /** AstroSign List */
    @Insert(onConflict = REPLACE)
     fun insertAllSigns(signList: List<AstroSignListModel.AstroSignModel>)

    @Query("SELECT * FROM AstroSignModel")
     fun getAllAstroSign(): List<AstroSignListModel.AstroSignModel>

    @Query("Update AstroSignModel set name=:name,isSelected=:isSelected where id =:id")
    fun updateSign(
        id: Int,
        name: String,
        isSelected: Boolean
    )

    @Query("DELETE FROM AstroSignModel")
     fun deleteAllAstroSign()

    /** Cast List */
    @Insert(onConflict = REPLACE)
     fun insertAllCast(castList: List<CastListModel.CastModel>)

    @Query("SELECT * FROM CastModel")
     fun getAllCast(): List<CastListModel.CastModel>

    @Query("DELETE FROM CastModel")
     fun deleteAllCast()

    /**
     * Children List
     */
    @Insert(onConflict = REPLACE)
     fun insertAllChildren(childrenList: List<ChildrenListModel.ChildrenModel>)

    @Query("SELECT * FROM ChildrenModel")
     fun getAllChildren(): List<ChildrenListModel.ChildrenModel>

    @Query("DELETE FROM ChildrenModel")
     fun deleteAllChildren()

    /**
     * Language List
     */
    @Insert(onConflict = REPLACE)
     fun insertAllLanguage(languageList: List<LanguageListModel.LanguageModel>)

    @Query("SELECT * FROM LanguageModel")
     fun getAllLanguage(): List<LanguageListModel.LanguageModel>

    @Query("DELETE FROM LanguageModel")
     fun deleteAllLanguage()

    /**
     * Education Level List
     */
    @Insert(onConflict = REPLACE)
     fun insertAllLevel(languageList: List<EducationLevelListModel.LevelModel>)

    @Query("SELECT * FROM LevelModel")
     fun getAllLevel(): List<EducationLevelListModel.LevelModel>

    @Query("DELETE FROM LevelModel")
     fun deleteAllLevel()


/*    @Insert(onConflict = REPLACE)
     fun insertAllEducation(languageList: List<EducationDetailModel.DataEducatiton.EducationData>)

    @Query("SELECT * FROM EducationData")
     fun getAlleducation(): List<EducationDetailModel.DataEducatiton.EducationData>

    @Query("DELETE FROM EducationData")
     fun deleteAllEducation()*/

    /**
     * Religion List
     */
    @Insert(onConflict = REPLACE)
     fun insertAllReligion(religionList: List<ReligionListModel.ReligionModel>)

    @Query("SELECT * FROM ReligionModel")
     fun getAllReligion(): List<ReligionListModel.ReligionModel>

    @Query("Update ReligionModel set name=:name,isSelected=:isSelected where id =:id")
    fun updateReligion(
        id: Int,
        name: String,
        isSelected: Boolean
    )

    @Query("DELETE FROM ReligionModel")
     fun deleteAllReligion()

    /**
     * Smoking List
     */
    @Insert(onConflict = REPLACE)
     fun insertAllSmoking(smokingList: List<SmokingListModel.SmokingModel>)

    @Query("SELECT * FROM SmokingModel")
     fun getAllSmoking(): List<SmokingListModel.SmokingModel>

    @Query("DELETE FROM SmokingModel")
     fun deleteAllSmoking()

    /**
     * Relationship List
     */
    @Insert(onConflict = REPLACE)
     fun insertAllRelationship(relationshipList: List<RelationshipListModel.RelationshipModel>)

    @Query("SELECT * FROM RelationshipModel")
     fun getAllRelationship(): List<RelationshipListModel.RelationshipModel>

    @Query("DELETE FROM RelationshipModel")
     fun deleteAllRelationship()

    /**
     * Vaccine Status List
     */
    @Insert(onConflict = REPLACE)
     fun insertAllVaccineStatus(statusList: List<CovidVaccineListModel.CovidModel>)

    @Query("SELECT * FROM CovidModel")
     fun getAllVaccineStatus(): List<CovidVaccineListModel.CovidModel>

    @Query("DELETE FROM CovidModel")
     fun deleteAllVaccineStatus()
}