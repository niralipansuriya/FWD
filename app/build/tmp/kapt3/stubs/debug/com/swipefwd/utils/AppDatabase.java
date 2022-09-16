package com.swipefwd.utils;

import java.lang.System;

@androidx.room.Database(entities = {com.swipefwd.data.models.AstroSignListModel.AstroSignModel.class, com.swipefwd.data.models.CastListModel.CastModel.class, com.swipefwd.data.models.ChildrenListModel.ChildrenModel.class, com.swipefwd.data.models.LanguageListModel.LanguageModel.class, com.swipefwd.data.models.EducationLevelListModel.LevelModel.class, com.swipefwd.data.models.ReligionListModel.ReligionModel.class, com.swipefwd.data.models.SmokingListModel.SmokingModel.class, com.swipefwd.data.models.RelationshipListModel.RelationshipModel.class, com.swipefwd.data.models.CovidVaccineListModel.CovidModel.class, com.swipefwd.xmpp.database.Rosters.class, com.swipefwd.xmpp.database.Message.class}, version = 2)
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Lcom/swipefwd/utils/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "chatDao", "Lcom/swipefwd/xmpp/database/ChatDao;", "userProfileDao", "Lcom/swipefwd/ui/profile/UserProfileDao;", "Companion", "app_debug"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.AppDatabase.Companion Companion = null;
    private static com.swipefwd.utils.AppDatabase INSTANCE;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.swipefwd.ui.profile.UserProfileDao userProfileDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.swipefwd.xmpp.database.ChatDao chatDao();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/swipefwd/utils/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/swipefwd/utils/AppDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.AppDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}