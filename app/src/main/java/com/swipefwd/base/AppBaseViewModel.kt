package com.swipefwd.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class AppBaseViewModel() : ViewModel() {
    private var internalAppDataStore: InternalAppDataStore? = null
    private var appDatabase: AppDatabase? = null
    constructor(
        internalAppDataStore: InternalAppDataStore,
        appDatabase: AppDatabase
    ) : this() {
        this.internalAppDataStore = internalAppDataStore
        this.appDatabase = appDatabase
    }

    companion object {
        const val SOMETHING_WENT_WRONG_ERROR = "Something went wrong"
    }

    var isNotInterested = false
    var isInterested = false
    var isGreenShowTip = false
    var isAccountTip = false
    var isSmsSent = false
    var userId = 0

    init {
        viewModelScope.launch {
            val internalAppDataStore = internalAppDataStore?: return@launch
            internalAppDataStore.getBoolean(PreferenceKeys.PREF_NOT_INTERESTED).collect {
                isNotInterested = it
            }
            internalAppDataStore.getBoolean(PreferenceKeys.PREF_INTERESTED).collect {
                isInterested = it
            }
            internalAppDataStore.getBoolean(PreferenceKeys.PREF_FWD_GREEN_TOOL_TIP).collect {
                isGreenShowTip = it
            }
            internalAppDataStore.getBoolean(PreferenceKeys.PREF_SMS_SENT).collect {
                isSmsSent = it
            }
            internalAppDataStore.getInt(PreferenceKeys.PREF_USER_ID).collect {
                userId = it
            }
            internalAppDataStore.getBoolean(PreferenceKeys.PREF_ACCOUNT_TOOL_TIP).collect {
                isAccountTip = it
            }
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    /** call logout **/
    private var _logOutUser = MutableLiveData<ResultState<Unit>>()
    fun onLogOutUser(): LiveData<ResultState<Unit>> = _logOutUser
    suspend fun logoutUser(): Boolean {
        //start loading
        _logOutUser.value = ResultState.Loading
        removePreference()
        clearDatabase()
        _logOutUser.value = ResultState.Success(Unit)
        return true
    }

    /** remove preference **/
    suspend fun removePreference() {
        val internalAppDataStore = internalAppDataStore?: return
        internalAppDataStore.removeAll()
        internalAppDataStore.savePreference(PreferenceKeys.PREF_NOT_INTERESTED, isNotInterested)
        internalAppDataStore.savePreference(PreferenceKeys.PREF_FWD_GREEN_TOOL_TIP, isGreenShowTip)
        internalAppDataStore.savePreference(PreferenceKeys.PREF_INTERESTED, isInterested)
        internalAppDataStore.savePreference(PreferenceKeys.PREF_ACCOUNT_TOOL_TIP, isAccountTip)
        internalAppDataStore.savePreference(PreferenceKeys.PREF_SMS_SENT, isSmsSent)
    }

    /** this function will clear the data base **/
    suspend fun clearDatabase() {
        val appDatabase = appDatabase?: return
        withContext(Dispatchers.IO) {
            appDatabase.apply {
                userProfileDao().apply {
                    deleteAllLanguage()
                    deleteAllLevel()
                    deleteAllAstroSign()
                    deleteAllCast()
                    deleteAllChildren()
                    deleteAllReligion()
                    deleteAllSmoking()
                    deleteAllRelationship()
                    deleteAllVaccineStatus()
                }
                chatDao().apply {
                    deleteAllRosters()
                    deleteAllMessageByUser(userId.toString())
                }
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////

}