package com.swipefwd.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.data.models.CastListModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SelectCastViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    val getCast = fwdDataStore.getString(PreferenceKeys.PREF_CAST)
    val getPrefCast = fwdDataStore.getString(PreferenceKeys.PREF_PREF_CAST)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<ArrayList<CastListModel.CastModel>>()
    var data: LiveData<ArrayList<CastListModel.CastModel>> = _data

    private var _error = MutableLiveData<CastListModel>()
    var error: LiveData<CastListModel> = _error

    private var castList = ArrayList<CastListModel.CastModel>()

    fun saveCast(value: String) {
        viewModelScope.launch {
            fwdDataStore.savePreference(PreferenceKeys.PREF_CAST, value)
        }
    }
    fun saveMultipleCast(value: String) {
        viewModelScope.launch {
            fwdDataStore.savePreference(PreferenceKeys.PREF_PREF_CAST, value)
        }
    }

    fun castList(token: String, page: Int = 1) {
        if (page == 1) {
            castList.clear()
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.castListApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@SelectCastViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if(response.code() == 401)
                    {
                        AppUtils.callLogout(this@SelectCastViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        castList.addAll(response.body()?.items ?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {

                            _data.postValue(castList)
                        } else {
                            castList(token, page + 1)
                        }
                    } else {
                        _error.postValue(ErrorUtils.parseError<CastListModel>(response))
                    }
                }
        }
    }
}