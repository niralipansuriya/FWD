package com.swipefwd.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.launch

class SelectOccupationViewModel(context: Context): ViewModel()
{
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getOccupation = fwdDataStore.getString(PreferenceKeys.PREF_OCCUPATION)

    fun saveOccupation(value:String)
    {
        viewModelScope.launch {
            //fwdDataStore.savePreference(PreferenceKeys.PREF_OCCUPATION,value)
            AppUtils.storeOccupation(AppController.context!!,value)
        }
    }
}