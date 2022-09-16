package com.swipefwd.ui.profile

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.BuildConfig
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.data.models.CommonResponseModel
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
import java.util.HashMap

class AdvancePreferenceViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            fwdDataStore.savePreference(key, value)
        }
    }

    val getPreferenceSubmitted = fwdDataStore.getBoolean(PreferenceKeys.PREF_PREFERENCE_FLAG)

    val getLanguage = fwdDataStore.getString(PreferenceKeys.PREF_PREF_LANGUAGE)

    val getCast = fwdDataStore.getString(PreferenceKeys.PREF_PREF_CAST)

    val getEducation = fwdDataStore.getString(PreferenceKeys.PREF_PREF_EDUCATION)

    val getChildren = fwdDataStore.getString(PreferenceKeys.PREF_PREF_CHILDREN)

    val getReligion = fwdDataStore.getString(PreferenceKeys.PREF_PREF_RELIGION)

    val getSmoking = fwdDataStore.getString(PreferenceKeys.PREF_PREF_SMOKING)

    val getRelationship = fwdDataStore.getString(PreferenceKeys.PREF_PREF_RELATIONSHIP)

    val getAstroSign = fwdDataStore.getString(PreferenceKeys.PREF_PREF_ASTROLOGY_SIGN)

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    val getStartHeight = fwdDataStore.getString(PreferenceKeys.PREF_PREF_START_HEIGHT)

    val getEndHeight = fwdDataStore.getString(PreferenceKeys.PREF_PREF_END_HEIGHT)

    val getGender = fwdDataStore.getString(PreferenceKeys.PREF_PREF_GENDER)

    val getStartAge = fwdDataStore.getInt(PreferenceKeys.PREF_START_AGE)

    val getEndAge = fwdDataStore.getInt(PreferenceKeys.PREF_END_AGE)

    val getMaxDistance = fwdDataStore.getInt(PreferenceKeys.PREF_MAX_DISTANCE)

    val getIsVerified = fwdDataStore.getBoolean(PreferenceKeys.PREF_IS_VERIFIED)

    val getUserId = fwdDataStore.getInt(PreferenceKeys.PREF_USER_ID)

    val getStartHeightInches = fwdDataStore.getInt(PreferenceKeys.PREF_PREF_START_HEIGHT_INCHES)

    val getEndHeightInches = fwdDataStore.getInt(PreferenceKeys.PREF_PREF_END_HEIGHT_INCHES)

    val getIsHeightFeet = fwdDataStore.getBoolean(PreferenceKeys.PREF_PREF_IS_HEIGHT_FEET)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<CommonResponseModel>()
    var data: LiveData<CommonResponseModel> = _data

   private var _advanceData = MutableLiveData<CommonResponseModel>()
    var advanceData: LiveData<CommonResponseModel> = _advanceData

    private var _error = MutableLiveData<CommonResponseModel>()
    var error: LiveData<CommonResponseModel> = _error


    private var _updateData = MutableLiveData<CommonResponseModel>()
    var updateData: LiveData<CommonResponseModel> = _updateData

    private var _updateError = MutableLiveData<CommonResponseModel>()
    var updateError: LiveData<CommonResponseModel> = _updateError

    fun userPreferenceSubmit(jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            val headers = HashMap<String, String>()
            headers["language"] = BuildConfig.Language
            headers["app_version"] = BuildConfig.VERSION_NAME
            headers["device_type"] = BuildConfig.DEVICE_TYPE
            headers["device_id"] = AppUtils.getDeviceId(AppController.context!!)
            headers["auth_token"] = AppUtils.getAuthToken(AppController.context!!)
            appRepository.userPreferenceSubmitApi(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@AdvancePreferenceViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if(response.code() == 401)
                    {
                        AppUtils.callLogout(this@AdvancePreferenceViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _data.postValue(response.body())
                    } else {
                        _error.postValue(ErrorUtils.parseError<CommonResponseModel>(response))
                    }
                }
        }
    }

    fun userAdvancePreferenceSubmit(jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            val headers = HashMap<String, String>()
            headers["language"] = BuildConfig.Language
            headers["app_version"] = BuildConfig.VERSION_NAME
            headers["device_type"] = BuildConfig.DEVICE_TYPE
            headers["device_id"] = AppUtils.getDeviceId(AppController.context!!)
            headers["auth_token"] = AppUtils.getAuthToken(AppController.context!!)
            appRepository.userAdvancePreferenceSubmitApi(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@AdvancePreferenceViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if(response.code() == 401)
                    {
                        AppUtils.callLogout(this@AdvancePreferenceViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _advanceData.postValue(response.body())
                    } else {
                        _error.postValue(ErrorUtils.parseError<CommonResponseModel>(response))
                    }
                }
        }
    }

    fun userPreferenceUpdate(token: String, id: Int, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userPreferenceUpdateApi(token, id, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@AdvancePreferenceViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if(response.code() == 401)
                    {
                        AppUtils.callLogout(this@AdvancePreferenceViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _updateData.postValue(response.body())
                    } else {
                        _updateError.postValue(
                            ErrorUtils.parseError<CommonResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }
}