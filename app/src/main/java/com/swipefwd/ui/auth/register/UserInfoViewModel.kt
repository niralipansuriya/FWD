package com.swipefwd.ui.auth.register

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.BuildConfig
import com.swipefwd.data.models.CommonResponseModel
import com.swipefwd.data.models.LoginResponseModel
import com.swipefwd.data.models.UserTypeModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.setApiMessage
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UserInfoViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    fun <T> saveUserInfo(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            fwdDataStore.savePreference(key, value)
        }
    }

    val socialMediaUser = fwdDataStore.getString(PreferenceKeys.PREF_SOCIAL_MEDIA_USER)
    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    val getCurrentScreen = fwdDataStore.getString(PreferenceKeys.PREF_CURRENT_SCREEN)

    val getPhoneNumber = fwdDataStore.getString(PreferenceKeys.PREF_PHONE_NUMBER)
    val getCountryCode = fwdDataStore.getString(PreferenceKeys.PREF_COUNTRY_CODE)
    val isFaceBookPermissionDenied = fwdDataStore.getBoolean(PreferenceKeys.PREF_FACEBOOK_FRD_PERMISSION)
    val isConnectorFaceBookPermissionDenied = fwdDataStore.getBoolean(PreferenceKeys.PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION)

    val getFbIds = fwdDataStore.getString(PreferenceKeys.PREF_FACEBOOK_IDS)

    val showNotificationDialog = fwdDataStore.getBoolean(PreferenceKeys.PREF_SHOW_NOTIFICATION_DIALOG)

    fun <T> removePreference(key: Preferences.Key<T>) {
        viewModelScope.launch {
            fwdDataStore.removePreference(key)
        }
    }

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _registerData = MutableLiveData<LoginResponseModel>()
    var registerData: LiveData<LoginResponseModel> = _registerData

    private var _userTypeData = MutableLiveData<UserTypeModel>()
    var userTypeData: LiveData<UserTypeModel> = _userTypeData

    private var _registerError = MutableLiveData<LoginResponseModel>()
    var registerError: LiveData<LoginResponseModel> = _registerError

    private var _userTypeDataError = MutableLiveData<UserTypeModel>()
    var userTypeDataError: LiveData<UserTypeModel> = _userTypeDataError

    private var _settingsData = MutableLiveData<CommonResponseModel>()

    private var _settingsError = MutableLiveData<CommonResponseModel>()

    private var _facebookData = MutableLiveData<CommonResponseModel>()

    private var _facebookError = MutableLiveData<CommonResponseModel>()

    fun registerUser(jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.registerUserApi(jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(setApiMessage(e.localizedMessage,this@UserInfoViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _registerData.postValue(response.body())
                    } else {
                        AppUtils.callLogout(this@UserInfoViewModel)
//                        if (response.code() == 401) {
//                            AppUtils.callLogout(this@UserInfoViewModel)
//                        } else {
//                            _registerError.postValue(ErrorUtils.parseError<LoginResponseModel>(response))
//                        }
                    }
                }
        }
    }

    fun userSettingsUpdate(token: String, id: Int, jsonObject: JsonObject) {
        viewModelScope.launch {
            appRepository.userSettingsUpdateApi(token, id, jsonObject)
                .catch { e ->
                    _errorMessage.postValue(setApiMessage(e.localizedMessage,this@UserInfoViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.isSuccessful) {
                        _settingsData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@UserInfoViewModel)
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
    fun setUserAccountType(userType :Int) {
        val headers = HashMap<String, String>()
        headers["language"] = BuildConfig.Language
        headers["app_version"] = BuildConfig.VERSION_NAME
        headers["device_type"] = BuildConfig.DEVICE_TYPE
        headers["device_id"] = AppUtils.getDeviceId(AppController.context!!)
        headers["auth_token"] = AppUtils.getAuthToken(AppController.context!!)
        val apiRequest = JsonObject().apply {
            addProperty("user_type", userType)
        }
        viewModelScope.launch {
            appRepository.setUserTypeApi(headers,apiRequest)
                .catch { e ->
                    _errorMessage.postValue(setApiMessage(e.localizedMessage,this@UserInfoViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.isSuccessful) {

                       // _settingsData.postValue(response.body())
                        _userTypeData.postValue(response.body())

                        AppUtils.storeUserType(AppController.context!!,response.body()!!.data.user_type)

                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@UserInfoViewModel)
                        } else {

                            _userTypeDataError.postValue(ErrorUtils.parseError<UserTypeModel> (response))
                           /* _settingsError.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )*/
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
                    _errorMessage.postValue(setApiMessage(e.localizedMessage,this@UserInfoViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _facebookData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@UserInfoViewModel)
                        } else {
                            _facebookError.postValue(ErrorUtils.parseError<CommonResponseModel>(response))
                        }

                    }
                }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////
    var otpCode:String? = null

    ///////////////////////////////////////////////////////////////////////////////////////////
}