package com.swipefwd.ui.home.message

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.viewModelScope
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.launch

class MessageViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }
    fun <T> saveData(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            fwdDataStore.savePreference(key, value)
        }
    }
    val phoneNumber = fwdDataStore.getString(PreferenceKeys.PREF_PHONE_NUMBER)

    val getProfileImage = fwdDataStore.getString(PreferenceKeys.PREF_PROFILE_IMAGE)

    val getFirstName = fwdDataStore.getString(PreferenceKeys.PREF_FIRST_NAME)
}