package com.swipefwd.ui.home.wallet

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.data.models.walletModels.GetAccessTokenModel
import com.swipefwd.data.models.walletModels.GetOperatorByIsoModel
import com.swipefwd.data.models.walletModels.MakeRechargeModel
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

class WalletViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }
    fun <T> saveData(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            fwdDataStore.savePreference(key, value)
        }
    }

    val getPhoneNumber = fwdDataStore.getString(PreferenceKeys.PREF_PHONE_NUMBER)
    val getAccountType = fwdDataStore.getString(PreferenceKeys.PREF_ACCOUNT_TYPE)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _tokenData = MutableLiveData<GetAccessTokenModel>()
    var tokenData: LiveData<GetAccessTokenModel> = _tokenData

    private var _error = MutableLiveData<GetAccessTokenModel>()
    var error: LiveData<GetAccessTokenModel> = _error

    private var _isoData = MutableLiveData<GetOperatorByIsoModel>()
    var isoData: LiveData<GetOperatorByIsoModel> = _isoData

    private var _isoError = MutableLiveData<GetAccessTokenModel>()
    var isoError: LiveData<GetAccessTokenModel> = _isoError

    private var _rechargeData = MutableLiveData<MakeRechargeModel>()
    var rechargeData: LiveData<MakeRechargeModel> = _rechargeData

    fun getAccessToken(jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getAccessTokenApi(jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@WalletViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    //_showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _tokenData.postValue(response.body())
                    } else {

                        _showLoading.postValue(false)

                        if (response.code() == 401) {
                            AppUtils.callLogout(this@WalletViewModel)
                        } else {
                            _error.postValue(
                                ErrorUtils.parseError<GetAccessTokenModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun getOperatorByIso(
        token: String,
        isoCode: String,
        includeBundles: Boolean,
        includeData: Boolean,
        includePin: Boolean,
        suggestedAmounts: Boolean,
        suggestedAmountsMap: Boolean
    ) {
        // _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getOperatorByIso(
                token,
                isoCode,
                includeBundles,
                includeData,
                includePin,
                suggestedAmounts,
                suggestedAmountsMap
            )
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@WalletViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _isoData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@WalletViewModel)
                        } else {
                            _isoError.postValue(
                                ErrorUtils.parseError<GetAccessTokenModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun makeRecharge(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.makeRecharge(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@WalletViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _rechargeData.postValue(response.body())
                    } else {
                        _showLoading.postValue(false)
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@WalletViewModel)
                        } else {
                            _error.postValue(
                                ErrorUtils.parseError<GetAccessTokenModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }
}