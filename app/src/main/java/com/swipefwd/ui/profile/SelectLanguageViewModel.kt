package com.swipefwd.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.data.models.LanguageDataModel
import com.swipefwd.data.models.LanguageListModel
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

class SelectLanguageViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<ArrayList<LanguageListModel.LanguageModel>>()
    var data: LiveData<ArrayList<LanguageListModel.LanguageModel>> = _data

    private var _error = MutableLiveData<LanguageListModel>()
    var error: LiveData<LanguageListModel> = _error

    private var _dataLanguage =
        MutableLiveData<ArrayList<LanguageDataModel.LanguageData.Language>>()
    var dataLanguage: LiveData<ArrayList<LanguageDataModel.LanguageData.Language>> = _dataLanguage

    private var _errorLanguage = MutableLiveData<LanguageDataModel>()
    var errorLanguage: LiveData<LanguageDataModel> = _errorLanguage

    private val languageList = ArrayList<LanguageListModel.LanguageModel>()
    private val languageDataList = ArrayList<LanguageDataModel.LanguageData.Language>()

    val getLanguage = fwdDataStore.getString(PreferenceKeys.PREF_LANGUAGE)
    val getPrefLanguage = fwdDataStore.getString(PreferenceKeys.PREF_PREF_LANGUAGE)

    fun saveLanguage(value: String) {
        viewModelScope.launch {
            // fwdDataStore.savePreference(PreferenceKeys.PREF_LANGUAGE, value)
            AppUtils.storeLanguae(AppController.context!!, value)

        }
    }

    fun savePrefLanguage(value: String) {
        viewModelScope.launch {
            //  fwdDataStore.savePreference(PreferenceKeys.PREF_PREF_LANGUAGE, value)
            AppUtils.storeLanguagePref(AppController.context!!, value)
        }
    }

    fun languageListApi() {

        val apiRequest = JsonObject().apply {
            addProperty("is_preference", AppUtils.getProfileOrPref(AppController.context!!))

        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getLanguageApi(apiRequest)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectLanguageViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectLanguageViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        languageDataList.addAll(
                            response.body()?.languageData!!.langauge ?: arrayListOf()
                        )
                        if (response.body()?.languageData!!.langauge!!.isNotEmpty()) {
                            _dataLanguage.postValue(languageDataList)
                        } else {
                            languageListApi()
                        }
                    } else {
                        _errorLanguage.postValue(ErrorUtils.parseError<LanguageDataModel>(response))
                    }
                }
        }
    }

    fun languageList(token: String, page: Int = 1) {
        if (page == 1) {
            languageList.clear()
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.languageListApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SelectLanguageViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SelectLanguageViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        languageList.addAll(response.body()?.items ?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {
                            _data.postValue(languageList)
                        } else {
                            languageList(token, page + 1)
                        }
                    } else {
                        _error.postValue(ErrorUtils.parseError<LanguageListModel>(response))
                    }
                }
        }
    }
}