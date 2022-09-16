package com.swipefwd.ui.tutorial.onboard

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.swipefwd.utils.datastore.InternalAppDataStore

class OnBoardingViewModel(private val internalAppDataStore: InternalAppDataStore) : ViewModel() {
    suspend fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        internalAppDataStore.savePreference(key, value)
    }
}