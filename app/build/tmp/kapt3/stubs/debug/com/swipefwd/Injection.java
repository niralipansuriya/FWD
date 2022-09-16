package com.swipefwd;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00020\b8\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/swipefwd/Injection;", "", "()V", "appDataBaseInstance", "Lcom/swipefwd/utils/AppDatabase;", "appRepositoryInstance", "Lcom/swipefwd/data/source/AppRepository;", "internalAppDataStoreInstance", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "getAppDataBase", "context", "Landroid/content/Context;", "getAppRepository", "getInternalAppDataStore", "app_debug"})
public final class Injection {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.Injection INSTANCE = null;
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    private static com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStoreInstance;
    private static com.swipefwd.data.source.AppRepository appRepositoryInstance;
    private static com.swipefwd.utils.AppDatabase appDataBaseInstance;
    
    private Injection() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.utils.datastore.InternalAppDataStore getInternalAppDataStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.source.AppRepository getAppRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.utils.AppDatabase getAppDataBase(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}