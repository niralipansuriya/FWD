package com.swipefwd.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.data.models.ReligionListModel
import com.swipefwd.data.models.ReligionModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SelectReligionViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<ArrayList<ReligionListModel.ReligionModel>>()
    var data: LiveData<ArrayList<ReligionListModel.ReligionModel>> = _data

    private var _error = MutableLiveData<ReligionListModel>()
    var error: LiveData<ReligionListModel> = _error

    private var _dataReligion = MutableLiveData<ArrayList<ReligionModel.ReligionData.ReligionLevel>>()
    var dataReligion: LiveData<ArrayList<ReligionModel.ReligionData.ReligionLevel>> = _dataReligion

    private var _errorReligion = MutableLiveData<ReligionModel>()
    var errorReligion: LiveData<ReligionModel> = _errorReligion

    private val religionList = ArrayList<ReligionListModel.ReligionModel>()
    private val religionDataList = ArrayList<ReligionModel.ReligionData.ReligionLevel>()

    val getReligion = fwdDataStore.getString(PreferenceKeys.PREF_RELIGION)
    val getPrefReligion = fwdDataStore.getString(PreferenceKeys.PREF_PREF_RELIGION)

    fun saveReligion(value: String) {
        viewModelScope.launch {
            AppUtils.storeReligion(AppController.context!!,value)
            //fwdDataStore.savePreference(PreferenceKeys.PREF_RELIGION, value)
        }
    }

    fun saveMultipleReligion(value: String) {
        viewModelScope.launch {
//            fwdDataStore.savePreference(PreferenceKeys.PREF_PREF_RELIGION, value)
            AppUtils.storeReligions(AppController.context!!,value)
        }
    }

    fun religionList(token: String, page: Int = 1) {
        if (page == 1) {
            religionList.clear()
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.religionListApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectReligionViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectReligionViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        religionList.addAll(response.body()?.items ?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {

                            _data.postValue(religionList)
                        } else {
                            religionList(token, page + 1)
                        }
                    } else {
                        _error.postValue(ErrorUtils.parseError<ReligionListModel>(response))
                    }
                }
        }
    }
    fun religionDataListApi() {
        val apiRequest = JsonObject().apply {
            addProperty("is_preference", AppUtils.getProfileOrPref(AppController.context!!))

        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getReligionApi(apiRequest)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectReligionViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectReligionViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        religionDataList.addAll(response.body()?.religiondata!!.religion ?: arrayListOf())
                        if (response.body()?.religiondata!!.religion!!.isNotEmpty()) {

                            _dataReligion.postValue(religionDataList)
                        } else {
                            religionDataListApi()
                        }
                    } else {
                        _errorReligion.postValue(ErrorUtils.parseError<ReligionModel>(response))
                    }
                }
        }
    }
}