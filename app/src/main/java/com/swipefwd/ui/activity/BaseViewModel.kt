package com.swipefwd.ui.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

open class BaseViewModel(var context: Context) : ViewModel() {

    private val fwdDataStore1 by lazy { InternalAppDataStore(context) }

    var isNotInterested1 = false
    var isInterested1 = false
    var getGreenShowTip1 = false
    var isAccountTip = false
    var isSMSsent = false
    var userId = 0

    init {
        viewModelScope.launch {
            fwdDataStore1.getBoolean(PreferenceKeys.PREF_NOT_INTERESTED).collect {
                isNotInterested1 = it
            }
            fwdDataStore1.getBoolean(PreferenceKeys.PREF_INTERESTED).collect {
                isInterested1 = it
            }
            fwdDataStore1.getBoolean(PreferenceKeys.PREF_FWD_GREEN_TOOL_TIP).collect {
                getGreenShowTip1 = it
            }
            fwdDataStore1.getBoolean(PreferenceKeys.PREF_SMS_SENT).collect {
                isSMSsent = it
            }
            fwdDataStore1.getInt(PreferenceKeys.PREF_USER_ID).collect {
                userId = it
            }
            fwdDataStore1.getBoolean(PreferenceKeys.PREF_ACCOUNT_TOOL_TIP).collect {
                isAccountTip = it
            }
        }
    }
}