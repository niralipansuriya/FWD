package com.swipefwd.ui.activity

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.data.models.ActiveExpireRequestListModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ActivityViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    val getAccountType = fwdDataStore.getString(PreferenceKeys.PREF_ACCOUNT_TYPE)

    val getUserId = fwdDataStore.getInt(PreferenceKeys.PREF_USER_ID)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _activePendingData = MutableLiveData<ActiveExpireRequestListModel>()
    var activePendingData: LiveData<ActiveExpireRequestListModel> = _activePendingData

    private var _activePendingError = MutableLiveData<ActiveExpireRequestListModel>()
    var activePendingError: LiveData<ActiveExpireRequestListModel> = _activePendingError


    fun <T> saveData(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            fwdDataStore.savePreference(key, value)
        }
    }

    fun getActivityExpiredRequestList(token: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getActivityPendingRequestApi(token)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@ActivityViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _activePendingData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@ActivityViewModel)
                        } else {
                            _activePendingError.postValue(
                                ErrorUtils.parseError<ActiveExpireRequestListModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }
}