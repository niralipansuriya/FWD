package com.swipefwd.ui.auth.login

import OTPModel
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.swipefwd.BuildConfig
import com.swipefwd.R
import com.swipefwd.base.AppBaseViewModel
import com.swipefwd.base.ResultState
import com.swipefwd.data.models.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.ui.auth.LogInNavigationModel
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LoginViewModel(
    private val appRepository: AppRepository,
    private val internalAppDataStore: InternalAppDataStore,
    private val appDatabase: AppDatabase
) : AppBaseViewModel(internalAppDataStore, appDatabase) {
    var socialId = ""
    var socialEmail = ""
    var firstName = ""
    var lastName = ""
    var birthDate = ""
    var gender = ""
    var socialType = ""
    var socialTypeLogin = 0
    var originalProfile = ""
    var socialFlag = 0
    var friendsDenied = false
    private var socialMediaModel = SocialMediaUserModel()
    private var facebookIds = ArrayList<String>()
    val getTimeoutOTP = internalAppDataStore.getString(PreferenceKeys.TIMEOUT_OTP)
    ////////////////////////////////////////////////////////////////////////////////////////////
    fun <T> saveLoginData(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            internalAppDataStore.savePreference(key, value)
        }
    }

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading


    private var _facebookData = MutableLiveData<CommonResponseModel>()
    var facebookData: LiveData<CommonResponseModel> = _facebookData
    private var _facebookError = MutableLiveData<CommonResponseModel>()
    var facebookError: LiveData<CommonResponseModel> = _facebookError

    fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            internalAppDataStore.savePreference(key, value)
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
                            this@LoginViewModel
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
                            AppUtils.callLogout(this@LoginViewModel)
                        } else {
                            _facebookError.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        }
                    }
                }
        }
    }


    /** ##############################################################**/
    ////////////////////////////////////////////////////////////////////////////
    private var _loginUserData = MutableLiveData<ResultState<LogInNavigationModel>>()
    private var _socialLoginData = MutableLiveData<ResultState<OTPModel>>()
    fun loginUserResult(): LiveData<ResultState<LogInNavigationModel>> = _loginUserData
    fun socialLoginResult(): LiveData<ResultState<OTPModel>> = _socialLoginData

    private var _userRegistrationRequired = MutableLiveData<Unit>()
    fun userRegistrationRequired(): LiveData<Unit> = _userRegistrationRequired

    private var loginUserJob: Job? = null

    /**this function is called on facebook,google and linked in login
     * if user logged in successfully then user will redirect to the dashboard screen
     * if there is response code 400 then it means user not registered and user will goes to the
     * PhoneNumber screen to start the new registration process
     */
    fun loginUser() {
        loginUserJob?.cancel()

        socialMediaModel.emailId = socialEmail
        socialMediaModel.firstName = firstName
        socialMediaModel.lastName = lastName
        socialMediaModel.profilePicture = originalProfile
        socialMediaModel.socialId = socialId
        socialMediaModel.socialType = socialType
        socialMediaModel.dob = birthDate
        socialMediaModel.gender = gender


        //exception handler
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            val errorMessage = throwable.message
            if (errorMessage.isNullOrBlank()) {
                _loginUserData.value =
                    ResultState.error(R.string.error_something_went_wrong)
                _socialLoginData.value =
                    ResultState.error(R.string.error_something_went_wrong)
                return@CoroutineExceptionHandler
            }
            _loginUserData.value = ResultState.error(errorMessage)
            _socialLoginData.value = ResultState.error(errorMessage)
        }


        /*val request = JsonObject().apply {
            addProperty("social_type", socialType)
            addProperty("social_id", socialId)
            addProperty("phone_number", "")
            addProperty("country_code", "")
        }
        */

        val request = JsonObject().apply {
            addProperty("signup_type", socialTypeLogin)
            addProperty("social_id", socialMediaModel.emailId)
            addProperty("is_auth", 1)//for authentication

        }

        //start loading
        _loginUserData.value = ResultState.Loading
        _socialLoginData.value = ResultState.Loading
        loginUserJob = viewModelScope.launch(exceptionHandler) {
            clearSharedPreferences()
            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.getOtpApi(headers,request)
         //   appRepository.updateMobile(headers,request)
                .catch { t -> throw  t }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    when {
                        response.isSuccessful -> {

                            println("responseBody---->>>>>${response.body()}")
                           val responseBody = response.body() ?: throw Exception()

                            _socialLoginData.value = ResultState.Success(responseBody)
                            onSocialResult(responseBody)
                           // _loginUserData.value = ResultState.Success(responseBody)

                         //temp   onLoginSuccess(responseBody)
                        }
                        response.code() == 401 -> {
                            throw Exception()
                        }
                        response.code() == 400 -> {
                            Log.i("test", "400 code3")

                            saveSocialUser()
                            /** if user not registered then response code will be 400 **/
                            _userRegistrationRequired.value = Unit
                        }
                        else -> {
                            val status =
                                ErrorUtils.parseError<LoginResponseModel>(response)
                                    ?: throw Exception()
                            onLoginError(status)
                            throw Exception(status.message)
                        }
                    }
                }
        }
    }


    private suspend fun onSocialResult(otpModel: OTPModel)
    {

        AppUtils.saveSocialMediaData(AppController.context!!,Gson().toJson(socialMediaModel))
        AppUtils.storeAuthToken(AppController.context!!,otpModel.data.token)
        AppUtils.storeSignupType(AppController.context!!,otpModel.data.signup_type)
        removePreference()
        AppUtils.storeIsVerified(AppController.context!!,otpModel.data.is_verified)
        AppUtils.storeAlternateLogin(AppController.context!!,otpModel.data.is_alternat_login)
        AppUtils.storeUserId(AppController.context!!,otpModel.data.userDetails.user_id)
        AppUtils.storePrefFlag(AppController.context!!,otpModel.data.preference)
        AppUtils.storeProfileFlag(AppController.context!!,otpModel.data.is_basic_profile)
        AppUtils.storeAdvanceProfileFlag(AppController.context!!,otpModel.data.is_advance_profile)
        AppUtils.storeGender(AppController.context!!,otpModel.data.gender)
        AppUtils.storeInvitationExpired(AppController.context!!,otpModel.data.invitation_expired)
        AppUtils.storeUserType(AppController.context!!,otpModel.data.userDetails.user_type)
        AppUtils.storeFirstName(AppController.context!!,otpModel.data.userDetails.first_name)
        AppUtils.storeLastName(AppController.context!!,otpModel.data.userDetails.last_name)
        AppUtils.storeProfileImage(AppController.context!!,otpModel.data.userDetails.profile_image)
        AppUtils.storePhoneNumber(AppController.context!!,otpModel.data.userDetails.phone_number)
        AppUtils.storeAgreement(AppController.context!!,otpModel.data.userDetails.is_agree)
        AppUtils.storeRemainingInvitation(AppController.context!!,otpModel.data.userDetails.remain_invitation)
        AppUtils.storeRemainConnection(AppController.context!!,otpModel.data.userDetails.remaim_connection)
        AppUtils.storeInstaUserName(AppController.context!!,otpModel.data.userDetails.instagram_name)
        AppUtils.storeFacebook(AppController.context!!,otpModel.data.userDetails.facebook)
        AppUtils.storeLinkedInId(AppController.context!!,otpModel.data.userDetails.linkedin)
        AppUtils.storeGoogle(AppController.context!!,otpModel.data.userDetails.google)
        AppUtils.storeDaterDisable(AppController.context!!,otpModel.data.userDetails.is_dater_disabled)
        AppUtils.storeConnectorDisable(AppController.context!!,otpModel.data.userDetails.is_connector_disabled)
    }

   // private suspend fun onLoginSuccess(loginModel: LoginResponseModel) {
/*temp
    private suspend fun onLoginSuccess(loginModel: OTPModel) {
        removePreference()
        saveLoginData(
            PreferenceKeys.PREF_SOCIAL_MEDIA_USER,
            Gson().toJson(socialMediaModel)
        )

        if (socialType == AppConstants.SOCIAL_FACEBOOK) {
            val gender = when (socialMediaModel.gender) {
                AppConstants.FB_MALE -> "0"
                AppConstants.FB_FEMALE -> "1"
                else -> "2"
            }

            when (loginModel.data.userDetails.user_type) {
                "1" -> {
                    //dater
                    saveLoginData(
                        PreferenceKeys.PREF_FACEBOOK_FRD_PERMISSION,
                        friendsDenied
                    )
                }
                "2" -> {
                    //connector
                    saveLoginData(
                        PreferenceKeys.PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION,
                        friendsDenied
                    )
                }
            }
            saveLoginData(PreferenceKeys.PREF_GENDER, gender)
            if(!socialMediaModel.dob.isNullOrEmpty()) {
                val date = socialMediaModel.dob?.split("/")?.get(1)
                val month = socialMediaModel.dob?.split("/")?.get(0)
                val year = socialMediaModel.dob?.split("/")?.get(2)
                val convertedDob = "$year-$month-$date"
                saveLoginData(
                    PreferenceKeys.PREF_CONVERTED_DOB,
                    convertedDob
                ) //"$mYear-$mMonth-$mDay"
            }
//            val date = socialMediaModel.dob?.split("/")?.get(1)
//            val month = socialMediaModel.dob?.split("/")?.get(0)
//            val year = socialMediaModel.dob?.split("/")?.get(2)
//            val convertedDob = "$year-$month-$date"

            saveLoginData(
                PreferenceKeys.PREF_DOB,
                socialMediaModel.dob!!
            ) //09/18/1984
//            saveLoginData(
//                PreferenceKeys.PREF_CONVERTED_DOB,
//                convertedDob
//            ) //"$mYear-$mMonth-$mDay"
        }
        ////////////////////////////////////////////////////////////////////////////////
        saveLoginData(PreferenceKeys.IS_PROFILE_VERIFIED, loginModel.data.is_verified)
        saveLoginData(PreferenceKeys.PREF_RECOVERY_EMAIL, loginModel.data.recoveryEmail)
        var dt = Date()
        var c= Calendar.getInstance()
        val mDateFormat = SimpleDateFormat("MM/dd/yyyy")
        Handler(Looper.getMainLooper()).post {
            dt = Date()
            c = Calendar.getInstance()
            c.time = dt
            c.add(Calendar.DATE, 4)
            dt = c.time
        }
        val plusFour=dt
        val stringPlusFour=mDateFormat.format(plusFour)
        Handler(Looper.getMainLooper()).post {
            dt = Date()
            c = Calendar.getInstance()
            c.time = dt
            c.add(Calendar.DATE, 7)
            dt = c.time
        }
        val plusSeven=dt
        val stringPlusSeven=mDateFormat.format(plusSeven)
        saveLoginData(PreferenceKeys.SHOW_VERIFICATION_REMINDER_DATE,stringPlusFour)
        Log.d("verification_reminder_date",stringPlusFour)
        saveLoginData(PreferenceKeys.PREF_RECOVERY_EMAIL, socialEmail)
        saveLoginData(PreferenceKeys.PREF_TOKEN, loginModel.data.token)
        saveLoginData(PreferenceKeys.PREF_PROFILE_FLAG, loginModel.data.is_basic_profile)
        saveLoginData(PreferenceKeys.PREF_ADV_PROFILE_FLAG, loginModel.data.is_advance_profile)
        saveLoginData(PreferenceKeys.PREF_PREFERENCE_FLAG, loginModel.data.preference)
        saveLoginData(PreferenceKeys.PREF_USER_ID, loginModel.data.userDetails.user_id)
        saveLoginData(PreferenceKeys.PREF_FIRST_NAME, loginModel.data.userDetails.first_name)
        saveLoginData(PreferenceKeys.PREF_LAST_NAME, loginModel.data.userDetails.last_name)
        saveLoginData(PreferenceKeys.PREF_DOB, birthDate)
        saveLoginData(PreferenceKeys.PREF_GENDER, gender)
        saveLoginData(PreferenceKeys.PREF_PROFILE_IMAGE, loginModel.data.userDetails.profile_image)
        saveLoginData(PreferenceKeys.PREF_PHONE_NUMBER, loginModel.data.userDetails.phone_number)
        saveLoginData(PreferenceKeys.PREF_IS_AGREE, loginModel.data.userDetails.is_agree)
        ////////////////////////////////////////////////////////////////////////////////
        saveLoginData(
            PreferenceKeys.PREF_DATER_DISABLED,
            loginModel.data.userDetails.is_dater_disabled
        )
        saveLoginData(
            PreferenceKeys.PREF_CONNECTOR_DISABLED,
            loginModel.data.userDetails.is_connector_disabled
        )
        if (socialType.isNotEmpty()) {
            Log.d("TAG:", "FB: $facebookIds")

            val jsonArray = JsonArray()
            facebookIds.forEach { jsonArray.add(it) }
            val apiRequest = JsonObject().apply {
                add("facebook_ids", jsonArray)
            }

        */
/* temp   if (facebookIds.isNotEmpty()) {
                sendFacebookIds("Bearer ${loginModel.token}", apiRequest)
            }*//*

        }
        saveLoginData(
            PreferenceKeys.PREF_REMAINING_INVITATION,
            loginModel.data.userDetails.remain_invitation
        )
        saveLoginData(
            PreferenceKeys.PREF_REMAINING_CONNECTOR_CONNECTION,
            loginModel.data.userDetails.remaim_connection
        )
        saveLoginData(
            PreferenceKeys.PREF_LINKEDIN_ID,
            loginModel.data.userDetails.linkedin
        )
        saveLoginData(
            PreferenceKeys.PREF_INSTA_NAME,
            loginModel.data.userDetails.instagram_name
        )

        ////////////////////////////////////////////////////////////////////////////////
        val accountType = when (loginModel.data.userDetails.user_type) {
            AppConstants.USER_TYPE_DATER -> AppUtils.AccountTypes.Dater
            AppConstants.USER_TYPE_MATCH_MAKER -> AppUtils.AccountTypes.Matchmaker
            else -> AppUtils.AccountTypes.Dater
        }
        saveLoginData(PreferenceKeys.PREF_ACCOUNT_TYPE, accountType)
        ////////////////////////////////////////////////////////////////////////////////
        val list = ArrayList<UserImagesResponseModel.Item>()
        list.add(
            UserImagesResponseModel.Item(
                isProfile = true,
                imageUrl = originalProfile
            )
        )
        saveLoginData(PreferenceKeys.PREF_IMAGES, Gson().toJson(list))
        ////////////////////////////////////////////////////////////////////////////////
        when {
            !loginModel.data.is_basic_profile -> {
                saveLoginData(PreferenceKeys.PREF_LOGIN_FLAG, false)
                _loginUserData.value = ResultState.Success(LogInNavigationModel.Profile)
            }
            !loginModel.data.preference -> {
                saveLoginData(PreferenceKeys.PREF_LOGIN_FLAG, false)
                _loginUserData.value = ResultState.Success(LogInNavigationModel.Preferences)
            }
            else -> {
                saveLoginData(PreferenceKeys.PREF_LOGIN_FLAG, true)
                saveLoginData(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
                if (loginModel.data.userDetails.is_agree) {
                    _loginUserData.value = ResultState.Success(LogInNavigationModel.Dashboard)

                } else {
                    _loginUserData.value = ResultState.Success(LogInNavigationModel.Agreement)
                }
            }
        }
    }
*/



    private fun saveSocialUser() {
        saveLoginData(
            PreferenceKeys.PREF_SOCIAL_MEDIA_USER,
            Gson().toJson(socialMediaModel)
        )

        if (socialType == AppConstants.SOCIAL_FACEBOOK) {
            val gender = when (socialMediaModel.gender) {
                AppConstants.FB_MALE -> "0"
                AppConstants.FB_FEMALE -> "1"
                else -> "2"
            }
            saveLoginData(
                PreferenceKeys.PREF_FACEBOOK_FRD_PERMISSION,
                friendsDenied
            )
            saveLoginData(
                PreferenceKeys.PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION,
                friendsDenied
            )
            saveLoginData(PreferenceKeys.PREF_GENDER, gender)
            try
            {
                val date = socialMediaModel.dob?.split("/")?.get(1)
                val month = socialMediaModel.dob?.split("/")?.get(0)
                val year = socialMediaModel.dob?.split("/")?.get(2)
                val convertedDob = "$year-$month-$date"
                saveLoginData(
                    PreferenceKeys.PREF_DOB,
                    socialMediaModel.dob!!
                )
                saveLoginData(
                    PreferenceKeys.PREF_CONVERTED_DOB,
                    convertedDob
                )
            }
            catch (e: Exception)
            {
                print(e.message)
            }
           //"$mYear-$mMonth-$mDay"
        }
        saveLoginData(PreferenceKeys.PREF_RECOVERY_EMAIL, socialEmail)
    }


    private fun onLoginError(loginModel: LoginResponseModel) {
        saveLoginData(
            PreferenceKeys.PREF_SOCIAL_MEDIA_USER,
            Gson().toJson(socialMediaModel)
        )

        if (socialType == AppConstants.SOCIAL_FACEBOOK) {
            val gender = when (socialMediaModel.gender) {
                AppConstants.FB_MALE -> "0"
                AppConstants.FB_FEMALE -> "1"
                else -> "2"
            }
            saveLoginData(
                PreferenceKeys.PREF_FACEBOOK_FRD_PERMISSION,
                friendsDenied
            )
            saveLoginData(
                PreferenceKeys.PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION,
                friendsDenied
            )
            saveLoginData(PreferenceKeys.PREF_GENDER, gender)
            val date = socialMediaModel.dob?.split("/")?.get(1)
            val month = socialMediaModel.dob?.split("/")?.get(0)
            val year = socialMediaModel.dob?.split("/")?.get(2)
            val convertedDob = "$year-$month-$date"
            saveLoginData(
                PreferenceKeys.PREF_DOB,
                socialMediaModel.dob!!
            )
            saveLoginData(
                PreferenceKeys.PREF_CONVERTED_DOB,
                convertedDob
            ) //"$mYear-$mMonth-$mDay"
        }
        saveLoginData(PreferenceKeys.PREF_RECOVERY_EMAIL, socialEmail)
    }

    private suspend fun clearSharedPreferences() {
        internalAppDataStore.removeAll()
    }

    fun resetViewModelData() {
        socialId = ""
        socialEmail = ""
        firstName = ""
        lastName = ""
        birthDate = ""
        gender = ""
        socialType = ""
        originalProfile = ""
        socialFlag = 0
        friendsDenied = false
        socialMediaModel = SocialMediaUserModel()
        facebookIds = ArrayList<String>()
    }

}