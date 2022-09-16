package com.swipefwd.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.data.models.AstroSignListModel
import com.swipefwd.data.models.AstrologicalModel
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

class SelectAstroSignViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<ArrayList<AstroSignListModel.AstroSignModel>>()
    var data: LiveData<ArrayList<AstroSignListModel.AstroSignModel>> = _data


    private var _dataAstrological = MutableLiveData<ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>>()
    var dataAstrological: LiveData<ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>> = _dataAstrological

    private var _errorAstrological = MutableLiveData<AstrologicalModel>()
    var errorAstrological: LiveData<AstrologicalModel> = _errorAstrological


    private var _error = MutableLiveData<AstroSignListModel>()
    var error: LiveData<AstroSignListModel> = _error

    private val astroList = ArrayList<AstroSignListModel.AstroSignModel>()
    private val astroDataList =
        ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>()

    val getSign = fwdDataStore.getString(PreferenceKeys.PREF_ASTROLOGY_SIGN)
    val getPrefSign = fwdDataStore.getString(PreferenceKeys.PREF_PREF_ASTROLOGY_SIGN)

    fun saveAstroSign(value: String) {
        viewModelScope.launch {
       //     fwdDataStore.savePreference(PreferenceKeys.PREF_ASTROLOGY_SIGN, value)
            AppUtils.storeAstrologicalSign(AppController.context!!,value)
        }
    }

    fun saveMultipleAstroSign(value: String) {
        viewModelScope.launch {
//            fwdDataStore.savePreference(PreferenceKeys.PREF_PREF_ASTROLOGY_SIGN, value)
            AppUtils.storeAstrologicalSigns(AppController.context!!,value)
        }
    }

    fun astroSignList(token: String, page: Int = 1) {
        if (page == 1) {
            astroList.clear()
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.astrologySignListApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectAstroSignViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectAstroSignViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        astroList.addAll(response.body()?.items ?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {
                            _showLoading.postValue(false)
                            _data.postValue(astroList)
                        } else {
                            astroSignList(token, page + 1)
                        }
                    } else {
                        _showLoading.postValue(false)
                        _error.postValue(ErrorUtils.parseError<AstroSignListModel>(response))
                    }
                }
        }
    }

    fun astrologicalApi() {
        val apiRequest = JsonObject().apply {
            addProperty("is_preference", AppUtils.getProfileOrPref(AppController.context!!))

        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getAstrologicalSign(apiRequest)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectAstroSignViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectAstroSignViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        astroDataList.addAll(
                            response.body()?.astrologicalData!!.astrological_sign ?: arrayListOf()
                        )
                        if (response.body()?.astrologicalData!!.astrological_sign!!.isNotEmpty()) {
                            _showLoading.postValue(false)
                            _dataAstrological.postValue(astroDataList)
                        } else {
                            astrologicalApi()
                        }
                    } else {
                        _showLoading.postValue(false)
                        _errorAstrological.postValue(ErrorUtils.parseError<AstrologicalModel>(response))
                    }
                }
        }
    }
}