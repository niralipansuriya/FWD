package com.swipefwd


import android.annotation.SuppressLint
import android.content.Context
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.datastore.InternalAppDataStore

object Injection {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("StaticFieldLeak")
    private lateinit var internalAppDataStoreInstance: InternalAppDataStore
    /* this will return the internalAppDataStoreInstance object */
    fun getInternalAppDataStore(context: Context): InternalAppDataStore {
        if (!Injection::internalAppDataStoreInstance.isInitialized) {
            internalAppDataStoreInstance = InternalAppDataStore(context)
        }
        return internalAppDataStoreInstance
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private lateinit var appRepositoryInstance: AppRepository
    /* this will return the appRepositoryInstance object */
    fun getAppRepository(): AppRepository {
        if (!Injection::appRepositoryInstance.isInitialized) {
            appRepositoryInstance = AppRepository()
        }
        return appRepositoryInstance
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //return app data base object
    private lateinit var appDataBaseInstance: AppDatabase
    /* this will return the appDataBaseInstance object */
    fun getAppDataBase(context: Context): AppDatabase {
        if (!Injection::appDataBaseInstance.isInitialized) {
            appDataBaseInstance = AppDatabase.getInstance(context)
        }
        return appDataBaseInstance
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
}