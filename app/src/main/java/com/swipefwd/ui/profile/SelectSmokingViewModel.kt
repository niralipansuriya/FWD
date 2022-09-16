package com.swipefwd.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.data.models.SmokingDataModel
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.data.models.SmokingListModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SelectSmokingViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<ArrayList<SmokingListModel.SmokingModel>>()
    var data: LiveData<ArrayList<SmokingListModel.SmokingModel>> = _data

    private var _smokingData = MutableLiveData<ArrayList<SmokingDataModel.SmokingData.Smoking>>()
    var smokingData: LiveData<ArrayList<SmokingDataModel.SmokingData.Smoking>> = _smokingData

    private var _error = MutableLiveData<SmokingListModel>()
    var error: LiveData<SmokingListModel> = _error

    private var _errorSmoking = MutableLiveData<SmokingDataModel>()
    var errorSmoking: LiveData<SmokingDataModel> = _errorSmoking

    private val smokingList = ArrayList<SmokingListModel.SmokingModel>()
    private val smokingDataList = ArrayList<SmokingDataModel.SmokingData.Smoking>()

    val getSmoke = fwdDataStore.getString(PreferenceKeys.PREF_SMOKING)
    val getPrefSmoke = fwdDataStore.getString(PreferenceKeys.PREF_PREF_SMOKING)

    fun saveSmoking(value: String) {
        viewModelScope.launch {
            AppUtils.storeSmoking(AppController.context!!,value)
            //fwdDataStore.savePreference(PreferenceKeys.PREF_SMOKING, value)
        }
    }
    fun savePrefSmoking(value: String) {
        viewModelScope.launch {
//            fwdDataStore.savePreference(PreferenceKeys.PREF_PREF_SMOKING, value)
            AppUtils.storeSmokingPref(AppController.context!!,value)
        }
    }

    fun smokingList(token: String, page: Int = 1) {
        if (page == 1) {
            smokingList.clear()
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.smokingListApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@SelectSmokingViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->

                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectSmokingViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        smokingList.addAll(response.body()?.items ?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {

                            _data.postValue(smokingList)
                        } else {
                            smokingList(token, page + 1)
                        }
                    } else {
                        _error.postValue(ErrorUtils.parseError<SmokingListModel>(response))
                    }
                }
        }
    }

    fun getSmokingListApi() {
        val apiRequest = JsonObject().apply {
            addProperty("is_preference", AppUtils.getProfileOrPref(AppController.context!!))

        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getSmokingApi(apiRequest)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@SelectSmokingViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->

                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectSmokingViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {

                        smokingDataList.addAll(response.body()?.smokingData!!.smoking ?: arrayListOf())
                        if (response.body()?.smokingData!!.smoking!!.isNotEmpty()) {
                            _smokingData.postValue(smokingDataList)
                        } else {
                            getSmokingListApi()
                        }
                    } else {
                        _errorSmoking.postValue(ErrorUtils.parseError<SmokingDataModel>(response))
                    }
                }
        }
    }
}