package com.swipefwd.ui.home.settings

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.BuildConfig
import com.swipefwd.data.models.*
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    val getUserId = fwdDataStore.getInt(PreferenceKeys.PREF_USER_ID)

    val getFirstName = fwdDataStore.getString(PreferenceKeys.PREF_FIRST_NAME)

    val getPhoneNumber = fwdDataStore.getString(PreferenceKeys.PREF_PHONE_NUMBER)

    val getProfileImage = fwdDataStore.getString(PreferenceKeys.PREF_PROFILE_IMAGE)
    val getAccountType = fwdDataStore.getString(PreferenceKeys.PREF_ACCOUNT_TYPE)
    val getEmailCode = fwdDataStore.getString(PreferenceKeys.PREF_RECOVERY_EMAIL)
    val isNotInterested = fwdDataStore.getBoolean(PreferenceKeys.PREF_NOT_INTERESTED)
    val isInterested = fwdDataStore.getBoolean(PreferenceKeys.PREF_INTERESTED)
    val getGreenShowTip = fwdDataStore.getBoolean(PreferenceKeys.PREF_FWD_GREEN_TOOL_TIP)
    val getAccountTipShow = fwdDataStore.getBoolean(PreferenceKeys.PREF_ACCOUNT_TOOL_TIP)
    val isSmsSent = fwdDataStore.getBoolean(PreferenceKeys.PREF_SMS_SENT)

    fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            fwdDataStore.savePreference(key, value)
        }
    }

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading
    private var _deleteAccount = MutableLiveData<DeleteAccountModel>()
    var deleteAccount: LiveData<DeleteAccountModel> = _deleteAccount
    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _userPreferenceData = MutableLiveData<UserPreferenceResponseModel>()
    var userPreferenceData: LiveData<UserPreferenceResponseModel> = _userPreferenceData

    private var _userPreferenceError = MutableLiveData<UserPreferenceResponseModel>()
    var userPreferenceError: LiveData<UserPreferenceResponseModel> = _userPreferenceError

    private var _settingsData = MutableLiveData<CommonResponseModel>()
    var settingsData: LiveData<CommonResponseModel> = _settingsData

    private var _settingsError = MutableLiveData<CommonResponseModel>()
    var settingsError: LiveData<CommonResponseModel> = _settingsError

    private var _logoutData = MutableLiveData<CommonResponseModel>()
    var logoutData: LiveData<CommonResponseModel> = _logoutData

    private var _getSettingsData = MutableLiveData<UserSettingsResponseModel>()
    var getSettingsData: LiveData<UserSettingsResponseModel> = _getSettingsData

    private var _getSettingsError = MutableLiveData<UserSettingsResponseModel>()
    var getSettingsError: LiveData<UserSettingsResponseModel> = _getSettingsError

    private var _getPlansData = MutableLiveData<SettingSubscriptionModel>()
    var getPlansData: LiveData<SettingSubscriptionModel> = _getPlansData

    private var _getPlansError = MutableLiveData<SettingSubscriptionModel>()
    var getPlansError: LiveData<SettingSubscriptionModel> = _getPlansError

    private var _planPurchaseData = MutableLiveData<CommonResponseModel>()
    var planPurchaseData: LiveData<CommonResponseModel> = _planPurchaseData

    private var _planPurchaseError = MutableLiveData<CommonResponseModel>()
    var planPurchaseError: LiveData<CommonResponseModel> = _planPurchaseError
    private var _errorDeleteAccount = MutableLiveData<DeleteAccountModel>()


    private var _facebookData = MutableLiveData<CommonResponseModel>()

    private var _facebookError = MutableLiveData<CommonResponseModel>()

    fun deleteDatabase(context: Context, userId: Int) {
        viewModelScope.launch {
            AppDatabase.getInstance(context).apply {
                userProfileDao().apply {
                    deleteAllLanguage()
                    deleteAllLevel()
                    deleteAllAstroSign()
                    deleteAllCast()
                    deleteAllChildren()
                    deleteAllReligion()
                    deleteAllSmoking()
                    deleteAllRelationship()
                    deleteAllVaccineStatus()
                }
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        chatDao().apply {
                            deleteAllRosters()
                            deleteAllMessageByUser(userId.toString())
                        }
                    }
                }
            }
        }
    }

    fun getUserPreferenceDetails(token: String, userID: Int) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getUserPreferenceApi(token, userID)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)

                    if (response.isSuccessful) {
                        _userPreferenceData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SettingsViewModel)
                        } else {
                            _userPreferenceError.postValue(
                                ErrorUtils.parseError<UserPreferenceResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }
    fun deleteUserAccount() {
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.deleteUserAccount(headers)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SettingsViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        println("delete account --------->>>>${response.body()}")
                        _deleteAccount.postValue(response.body())

                    } else {

                        _errorDeleteAccount.postValue(ErrorUtils.parseError<DeleteAccountModel>(response))
                    }
                }
        }
    }

    fun userSettingsUpdate(token: String, id: Int, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userSettingsUpdateApi(token, id, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _settingsData.postValue(response.body())
                    } else {

                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SettingsViewModel)
                        } else {
                            _settingsError.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun getUserSettings(token: String, userID: Int) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getUserSettingsApi(token, userID)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _getSettingsData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SettingsViewModel)
                        } else {
                            _getSettingsError.postValue(
                                ErrorUtils.parseError<UserSettingsResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun getUserPlansList(token: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userPlansApi(token)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _getPlansData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SettingsViewModel)
                        } else {
                            _getPlansError.postValue(
                                ErrorUtils.parseError<SettingSubscriptionModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun planPurchase(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.planPurchaseApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _planPurchaseData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SettingsViewModel)
                        } else {
                            _planPurchaseError.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun boosterPurchase(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.boosterPurchaseApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _planPurchaseData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SettingsViewModel)
                        } else {
                            _planPurchaseError.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun sendReceipt(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.sendReceipt(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _planPurchaseData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SettingsViewModel)
                        } else {
                            _planPurchaseError.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }


    fun deleteAccount(
        token: String,
        type: String,
        userId: Int,
        reason: Int = 0,
        other: String = ""
    ) {
        var jsonObject = JsonObject()
        if (type == AppUtils.AccountTypes.Dater) {
            jsonObject.addProperty("is_dater", false)
        } else if (type == AppUtils.AccountTypes.Matchmaker) {
            jsonObject.addProperty("is_connector", false)
        }
        if (reason != 0) {
            jsonObject.addProperty("reason", reason)
        }
        jsonObject.addProperty("other", other)
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.deleteAccount(token, userId, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _planPurchaseData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SettingsViewModel)
                        } else {
                            _settingsError.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun userLogout(token: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userLogoutApi(token)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _logoutData.postValue(response.body())


                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SettingsViewModel)
                        } else {
                            _settingsError.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }

    fun sendFacebookIds(token: String, jsonObject: JsonObject) {
        //  _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.sendFacebookIdApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SettingsViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _facebookData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SettingsViewModel)
                        } else {
                            _facebookError.postValue(ErrorUtils.parseError<CommonResponseModel>(response))
                        }

                    }
                }
        }
    }
}