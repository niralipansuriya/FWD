package com.swipefwd.utils.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.swipefwd.data.models.SuspendedPhoneNumber
import com.swipefwd.utils.AppUtils.internalAppDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class InternalAppDataStore(val context: Context) {

    fun getInt(key: Preferences.Key<Int>): Flow<Int> = context.internalAppDataStore.data
        .catch { e ->
            e.printStackTrace()
        }
        .map { preferences ->
            preferences[key] ?: 0
        }

    fun getLong(key: Preferences.Key<Long>): Flow<Long> = context.internalAppDataStore.data
        .catch { e ->
            e.printStackTrace()
        }
        .map { preferences ->
            preferences[key] ?: 0L
        }

    fun getFloat(key: Preferences.Key<Float>): Flow<Float> = context.internalAppDataStore.data
        .catch { e ->
            e.printStackTrace()
        }
        .map { preferences ->
            preferences[key] ?: 0f
        }

    fun getDouble(key: Preferences.Key<Double>): Flow<Double> = context.internalAppDataStore.data
        .catch { e ->
            e.printStackTrace()
        }
        .map { preferences ->
            preferences[key] ?: 0.0
        }

    fun getBoolean(key: Preferences.Key<Boolean>): Flow<Boolean> = context.internalAppDataStore.data
        .catch { e ->
            e.printStackTrace()
        }
        .map { preferences ->
            preferences[key] ?: false
        }

    fun getString(key: Preferences.Key<String>): Flow<String> = context.internalAppDataStore.data
        .catch { e ->
            e.printStackTrace()
        }
        .map { preferences ->
            preferences[key] ?: ""
        }

    suspend fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        context.internalAppDataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun <T> removePreference(key: Preferences.Key<T>) {
        context.internalAppDataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    suspend fun removeAll() {
        context.internalAppDataStore.edit { preferences ->
            preferences.clear()
        }
    }


    //////////////////////////////////////////////////////////////////////
    suspend fun fetchSuspendedPhoneNumber(): SuspendedPhoneNumber? {
        val jsonObject =
            getString(stringPreferencesKey(KEY_SUSPENDED_PHONE_NUMBER)).firstOrNull() ?: return null
        return Gson().fromJson(jsonObject, SuspendedPhoneNumber::class.java)
    }

    suspend fun saveSuspendedPhoneNumber(data: SuspendedPhoneNumber) {
        savePreference(stringPreferencesKey(KEY_SUSPENDED_PHONE_NUMBER), Gson().toJson(data))
    }

    suspend fun removeSuspendedPhoneNumber() {
        removePreference(stringPreferencesKey(KEY_SUSPENDED_PHONE_NUMBER))
    }

    /** ###############################################################################*/
    companion object {
        private const val KEY_SUSPENDED_PHONE_NUMBER = "key.phone.suspended"
    }

}