package com.swipefwd.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.swipefwd.data.models.*
import com.swipefwd.ui.profile.UserProfileDao
import com.swipefwd.xmpp.database.ChatDao
import com.swipefwd.xmpp.database.Message
import com.swipefwd.xmpp.database.Rosters

@Database(
    entities = [
        AstroSignListModel.AstroSignModel::class,
        CastListModel.CastModel::class,
        ChildrenListModel.ChildrenModel::class,
        LanguageListModel.LanguageModel::class,
        EducationLevelListModel.LevelModel::class,
        ReligionListModel.ReligionModel::class,
        SmokingListModel.SmokingModel::class,
        RelationshipListModel.RelationshipModel::class,
        CovidVaccineListModel.CovidModel::class, Rosters::class,
        Message::class,
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun chatDao(): ChatDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "fwd_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
