package com.swipefwd.ui.auth.otpverify

import OTPModel
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.BuildConfig
import com.swipefwd.R
import com.swipefwd.base.AppBaseViewModel
import com.swipefwd.base.ResultState
import com.swipefwd.data.models.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.data.source.remote.api.ErrorUtils
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
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class OtpViewModel(
    private val appRepository: AppRepository,
    val appPreferences: InternalAppDataStore,
    private val appDataBase: AppDatabase
) : AppBaseViewModel(appPreferences, appDataBase) {
    companion object {
        private const val TAG = "OtpViewModel"
        const val MAX_WRONG_OTP_FILL_CHANCE = 2
        private const val RESEND_TIME_OUT = 30000L
        //   private const val WRONG_OTP_FILLED_TIME_OUT = 300000L
    }

    var firstName: String = ""
    var counter = 1
    var countryCode = ""
    var phoneNumber = ""
    var isoCode = ""
    var isConnection: Int? = 0
    var socialUser = SocialMediaUserModel()
    var facebookIds = ArrayList<String>()

    var recoverEmail = ""
    var recoverPhoneNumber = ""

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    var isOtpExpired: Boolean = false
    var userFilledOtp: String? = null
    //timer functionality
    /** wrong otp submitted count **/
    var noOfTimeWrongOtpSubmit = 0

    fun isWrongOtpFillLimitExceed() = noOfTimeWrongOtpSubmit >= MAX_WRONG_OTP_FILL_CHANCE


    private var isPhoneNumberSuspended: Boolean = false
    private var _phoneNumberSuspended = MutableLiveData<Boolean>()
    fun phoneNumberSuspended(): LiveData<Boolean> = _phoneNumberSuspended
    private fun wrongOtpSubmitResponse() {
        noOfTimeWrongOtpSubmit++
        /** if wrong otp submit limit for one number is exceeds than this will suspended **/
        if (isWrongOtpFillLimitExceed()) {
            val suspendedPhoneNumber = SuspendedPhoneNumber(
                countryCode = countryCode,
                phoneNumber = phoneNumber,
                suspendStartTime = SystemClock.uptimeMillis(),
                false
            )
            viewModelScope.launch {
                appPreferences.saveSuspendedPhoneNumber(suspendedPhoneNumber)
                isPhoneNumberSuspended = true
                //  startSuspendedPhoneNumberTimer()
                _phoneNumberSuspended.value = true
            }
        }
    }

    private var _dataEmail = MutableLiveData<OTPModel>()
    var dataEmail: LiveData<OTPModel> = _dataEmail
    private var _emailError = MutableLiveData<OTPModel>()
    var emailError: LiveData<OTPModel> = _emailError

    private var timer: CountDownTimer? = null
    private var _timerRefreshed = MutableLiveData<String>()
    fun timerRefreshed(): LiveData<String> = _timerRefreshed
    private var _timerStopped = MutableLiveData<Unit>()
    fun timerStopped(): LiveData<Unit> = _timerStopped
    private fun startTimer(timerSecond: Long) {
        //cancel timer old timer
        timer?.cancel()
        timer = object : CountDownTimer(timerSecond, 1000) {
            override fun onTick(p0: Long) {
                val timer = p0 / 1000
                val minutes = (timer / 60).toString()
                val seconds = (timer % 60).toString()
                val remainingTime = if (seconds.toInt() <= 9) {
                    "$minutes:0$seconds"
                } else {
                    "$minutes:$seconds"
                }
                _timerRefreshed.value = remainingTime
            }

            override fun onFinish() {
                isOtpExpired = true
                noOfTimeWrongOtpSubmit = 0
                _timerStopped.value = Unit
                // cancel()
            }
        }.start()
    }

    fun startResendCodeTimer() {
        startTimer(RESEND_TIME_OUT)
    }


    fun startSuspendedPhoneNumberTimer() {
        startTimer(AppConstants.PHONE_NUMBER_SUSPEND_TIME)
    }

    fun stopTimer() {
        timer?.cancel()
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    val getFbIds = appPreferences.getString(PreferenceKeys.PREF_FACEBOOK_IDS)
    val socialMediaUser = appPreferences.getString(PreferenceKeys.PREF_SOCIAL_MEDIA_USER)

    fun <T> saveLoginData(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            appPreferences.savePreference(key, value)
        }
    }

    private var _errorMessage = MutableLiveData<String>()
    fun errorMessage(): LiveData<String> = _errorMessage
    private var _showLoading = MutableLiveData<Boolean>()
    fun showLoading(): LiveData<Boolean> = _showLoading


    private var _getOtpError = MutableLiveData<OTPModel>()
    fun getOtpError(): LiveData<OTPModel> = _getOtpError

    private var _loginError = MutableLiveData<LoginResponseModel>()
    fun loginError(): LiveData<LoginResponseModel> = _loginError

    private var _settingsData = MutableLiveData<CommonResponseModel>()
    private var _settingsError = MutableLiveData<CommonResponseModel>()


    private var _userRegistrationRequired = MutableLiveData<Unit>()
    fun userRegistrationRequired(): LiveData<Unit> = _userRegistrationRequired

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //resend otp
    private var _getOTPData = MutableLiveData<ResultState<OTPModel>>()
    fun getOTPData(): LiveData<ResultState<OTPModel>> = _getOTPData
    private var getOtpJob: Job? = null
    private var resendOtp: Job? = null
    private var resendOtpEmail: Job? = null
    fun getOtp() {
        if (isPhoneNumberSuspended) {
            _phoneNumberSuspended.value = true
        }

        getOtpJob?.cancel()
        /*  val jsonObject = JsonObject().apply {
              addProperty("country_code", countryCode)
              addProperty("phone_number", phoneNumber)
          } */

        val jsonObject = JsonObject().apply {
            addProperty("country_code", countryCode)
            addProperty("mobile", phoneNumber)
            addProperty("signup_type", 0)
        }
        //show loaded
        _showLoading.postValue(true)
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _showLoading.postValue(false)
            val errorMessage = throwable.message
            if (errorMessage.isNullOrBlank()) {
                _getOTPData.value =
                    ResultState.error(R.string.error_something_went_wrong)
                return@CoroutineExceptionHandler
            }
            _getOTPData.value = ResultState.error(errorMessage)
        }

        getOtpJob = viewModelScope.launch(exceptionHandler) {
            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)


            appRepository.getOtpApi(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(e.localizedMessage, this@OtpViewModel)
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    when {
                        response.isSuccessful -> {
                            println("otp verified---------->>>>>>${response.body()}")
                            val responseBody = response.body() ?: throw Exception()
                            isOtpExpired = false
                            _getOTPData.value = ResultState.Success(responseBody)
                        }
                        response.code() == 401 -> {
                            logoutUser()
                        }
                        else -> {
//                            //if any error then this block will get executed
//                            wrongOtpSubmitResult()
                            val status = ErrorUtils.parseError<OTPModel>(response)
                            throw Exception(status?.message)
                        }
                    }
                }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //verify otp
    private var _verifyOtpResponseData = MutableLiveData<ResultState<OTPModel>>()
    private var _verifyOtpResponseEmailData = MutableLiveData<ResultState<OTPModel>>()
    private var _resendOtp = MutableLiveData<ResultState<ResendOtp>>()
    private var _resendOtpEmail = MutableLiveData<ResultState<ResendOtp>>()
    fun verifyOtpResponseData(): LiveData<ResultState<OTPModel>> = _verifyOtpResponseData
    fun verifyOtpEmailResponseData(): LiveData<ResultState<OTPModel>> = _verifyOtpResponseEmailData
    private var verifyOtpJob: Job? = null
    private var verifyOtpEmailJob: Job? = null
    fun verifyOtp() {
        if (isPhoneNumberSuspended) {
            _phoneNumberSuspended.value = true
        }


        verifyOtpJob?.cancel()
        val userFilledOtp = userFilledOtp
        println("userfilled code------->>$userFilledOtp")
        println("userfilled OTP------->>$userFilledOtp")
        if (userFilledOtp == null) {
            println("userfilled OTP null------->>$userFilledOtp")

            _verifyOtpResponseData.value = ResultState.error(R.string.invalid_otp)
            return
        }
        if (isOtpExpired) {
            println("userfilled OTP expires------->>$userFilledOtp")

            noOfTimeWrongOtpSubmit++
            _verifyOtpResponseData.value = ResultState.error(R.string.invalid_otp)
            return
        }
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _showLoading.postValue(false)
            val errorMessage = throwable.message
            if (errorMessage.isNullOrBlank()) {
                _verifyOtpResponseData.value =
                    ResultState.error(R.string.error_something_went_wrong)
                return@CoroutineExceptionHandler
            }
            _verifyOtpResponseData.value = ResultState.error(errorMessage)
        }
        //start loading
        _showLoading.value = true

        //request json object
        /* val jsonObject = JsonObject().apply {
             addProperty("country_code", countryCode)
             addProperty("phone_number", phoneNumber)
             addProperty("code", userFilledOtp)
         } */
        val jsonObject = JsonObject().apply {
            addProperty("otp", userFilledOtp)

        }
        verifyOtpJob = viewModelScope.launch(exceptionHandler) {
            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
            appRepository.verifyOtpApi(headers, jsonObject)
                .catch { t -> throw  t }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    when {
                        response.isSuccessful -> {
                            println("_verifyOtpResponseData-----------${response.body()}")
                            val responseBody = response.body() ?: throw Exception()
                            //temp    _verifyOtpResponseData.value = ResultState.Success(responseBody)
                            if (response.body() != null) {
                                response.body()!!.data?.let {
                                    if (it.is_verified) {
                                        onLoginSuccessResult(response.body()!!, false)
//                                        _verifyOtpResponseData.value = ResultState.Success(response.body()!!)
                                    } else {
                                        _userRegistrationRequired.value = Unit
                                    }
                                }
                            } else {
                                _userRegistrationRequired.value = Unit
                            }

                        }

                        response.code() == 401 -> {
                            logoutUser()
                        }

                        else -> {
                            wrongOtpSubmitResponse()
                            val status = ErrorUtils.parseError<OTPModel>(response)
                            throw Exception(status?.message)
                        }
                    }
                }
        }
    }

    fun verifyOtpEmail() {
        if (isPhoneNumberSuspended) {
            _phoneNumberSuspended.value = true
        }


        verifyOtpEmailJob?.cancel()
        val userFilledOtp = userFilledOtp
        println("userfilled code------->>$userFilledOtp")
        println("userfilled OTP------->>$userFilledOtp")
        if (userFilledOtp == null) {
            println("userfilled OTP null------->>$userFilledOtp")

            _verifyOtpResponseEmailData.value = ResultState.error(R.string.invalid_otp)
            return
        }
        if (isOtpExpired) {
            println("userfilled OTP expires------->>$userFilledOtp")

            noOfTimeWrongOtpSubmit++
            _verifyOtpResponseEmailData.value = ResultState.error(R.string.invalid_otp)
            return
        }
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _showLoading.postValue(false)
            val errorMessage = throwable.message
            if (errorMessage.isNullOrBlank()) {
                _verifyOtpResponseEmailData.value =
                    ResultState.error(R.string.error_something_went_wrong)
                return@CoroutineExceptionHandler
            }
            _verifyOtpResponseEmailData.value = ResultState.error(errorMessage)
        }
        //start loading
        _showLoading.value = true


        val jsonObject = JsonObject().apply {
            addProperty("otp", userFilledOtp)
            addProperty("mobile", phoneNumber)
            addProperty("country_code", countryCode)
            addProperty("email", recoverEmail)

        }
        verifyOtpEmailJob = viewModelScope.launch(exceptionHandler) {
            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.verifyOtpEmail(headers, jsonObject)
                .catch { t -> throw  t }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    when {
                        response.isSuccessful -> {
                            println("_verifyOtpResponseData-----------${response.body()}")
                            val responseBody = response.body() ?: throw Exception()
                            if (response.body() != null) {
                                response.body()!!.data?.let {
                                    if (it.is_verified) {
                                        onLoginSuccessResult(response.body()!!, true)
                                    } else {
                                        _userRegistrationRequired.value = Unit
                                    }
                                }
                            } else {
                                _userRegistrationRequired.value = Unit
                            }

                        }

                        response.code() == 401 -> {
                            logoutUser()
                        }

                        else -> {
                            wrongOtpSubmitResponse()
                            val status = ErrorUtils.parseError<OTPModel>(response)
                            throw Exception(status?.message)
                        }
                    }
                }
        }
    }

    fun loginUserWithEmailAndMobile() {
        var jsonObject = JsonObject()
        jsonObject.addProperty("mobile", phoneNumber)
        jsonObject.addProperty("country_code", countryCode)
        jsonObject.addProperty("email", recoverEmail)

        _showLoading.postValue(true)
        viewModelScope.launch {
            //  appRepository.recoverEmailApi(token, jsonObject)

            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.verifyOtpEmail(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@OtpViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@OtpViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        println("email recover sucessfully---->>>>>>>>${response.body()}")
                        _dataEmail.postValue(response.body())
                    } else {
                        /* _errorMessage.postValue(
                             AppUtils.setApiMessage(
                                 response.body()!!.message.toString(),
                                 this@UserProfileViewModel
                             )
                         )*/
                        _emailError.postValue(ErrorUtils.parseError<OTPModel>(response))
                    }
                }
        }
    }

    fun resendOtp() {
        if (isPhoneNumberSuspended) {
            _phoneNumberSuspended.value = true
        }

        resendOtp?.cancel()

        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _showLoading.postValue(false)
            val errorMessage = throwable.message
            if (errorMessage.isNullOrBlank()) {
                _resendOtp.value =
                    ResultState.error(R.string.error_something_went_wrong)
                return@CoroutineExceptionHandler
            }
            _resendOtp.value = ResultState.error(errorMessage)
        }
        //start loading
        _showLoading.value = true

        resendOtp = viewModelScope.launch(exceptionHandler) {
            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
            appRepository.resendOtp(headers)
                .catch { t -> throw  t }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    when {
                        response.isSuccessful -> {
                            println("_verifyOtpResponseData-----------${response.body()}")
                            isOtpExpired = false

                            startResendCodeTimer() //added by nirali
                            val responseBody = response.body() ?: throw Exception()
                            //temp    _verifyOtpResponseData.value = ResultState.Success(responseBody)
                            //  _userRegistrationRequired.value = Unit

                        }

                        response.code() == 401 -> {
                            logoutUser()
                        }

                        else -> {
                            wrongOtpSubmitResponse()
                            val status = ErrorUtils.parseError<ResendOtp>(response)
                            throw Exception(status?.message)
                        }
                    }
                }
        }
    }
    fun resendOtpEmail() {
        if (isPhoneNumberSuspended) {
            _phoneNumberSuspended.value = true
        }

        resendOtpEmail?.cancel()

        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _showLoading.postValue(false)
            val errorMessage = throwable.message
            if (errorMessage.isNullOrBlank()) {
                _resendOtpEmail.value =
                    ResultState.error(R.string.error_something_went_wrong)
                return@CoroutineExceptionHandler
            }
            _resendOtpEmail.value = ResultState.error(errorMessage)
        }
        //start loading
        _showLoading.value = true
        val jsonObject = JsonObject().apply {
            addProperty("country_code", countryCode)
            addProperty("mobile", phoneNumber)
        }
        resendOtpEmail = viewModelScope.launch(exceptionHandler) {
            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.sendOtpEmail(headers, jsonObject)
                .catch { t -> throw  t }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    when {
                        response.isSuccessful -> {
                            println("_verifyOtpResponseData-----------${response.body()}")
                            isOtpExpired = false

                            startResendCodeTimer() //added by nirali

                        }

                        response.code() == 401 -> {
                            logoutUser()
                        }

                        else -> {
                            wrongOtpSubmitResponse()
                            val status = ErrorUtils.parseError<ResendOtp>(response)
                            throw Exception(status?.message)
                        }
                    }
                }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //Login functionality
    private var _loginData = MutableLiveData<LoginResponseModel>()
    fun loginData(): LiveData<LoginResponseModel> = _loginData
    private var loginCoroutineJob: Job? = null
    fun loginUser() {
        loginCoroutineJob?.cancel()
        val request = JsonObject().apply {
            addProperty("social_type", socialUser.socialType)
            addProperty("social_id", socialUser.socialId)
            addProperty("phone_number", phoneNumber)
            addProperty("country_code", countryCode)
            addProperty("firstName", firstName)
            addProperty("code", userFilledOtp)
            //TODO user type value ?
            addProperty("user_type", "")
        }
        _showLoading.postValue(true)
        //this exception handler will handle the exception occurs in the login operation
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _showLoading.postValue(false)
            var errorMessage = throwable.message ?: SOMETHING_WENT_WRONG_ERROR
            if (errorMessage.isBlank()) {
                errorMessage = SOMETHING_WENT_WRONG_ERROR
            }
            _errorMessage.value = errorMessage
        }
        loginCoroutineJob = viewModelScope.launch(exceptionHandler) {
            appRepository.loginUserApi(request)
                .catch { t -> throw  t }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    when {
                        response.isSuccessful -> {
                            val responseBody =
                                response.body() ?: throw Exception("null response body")
                            onLoginSuccessResult(responseBody)
                        }
                        response.code() == 400 -> {
                            _userRegistrationRequired.value = Unit
                        }
                        else -> {
                            val status =
                                ErrorUtils.parseError<LoginResponseModel>(response)
                                    ?: throw Exception()
                            throw Exception(status.message)
                        }
                    }
                }
        }
    }


    //Recover Account Api
    private var _recoverAccountData = MutableLiveData<RecoverAccountModel>()
    fun recoverAccountData(): LiveData<RecoverAccountModel> = _recoverAccountData
    private var recoverAccountCoroutineJob: Job? = null
    fun recoverAccount() {
        recoverAccountCoroutineJob?.cancel()
        val request = JsonObject().apply {
            addProperty("email", recoverEmail)
            addProperty("phone_number", countryCode + phoneNumber)
        }
        _showLoading.postValue(true)
        //this exception handler will handle the exception occurs in the login operation
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _showLoading.postValue(false)
            var errorMessage = throwable.message ?: SOMETHING_WENT_WRONG_ERROR
            if (errorMessage.isBlank()) {
                errorMessage = SOMETHING_WENT_WRONG_ERROR
            }
            _errorMessage.value = errorMessage
        }
        recoverAccountCoroutineJob = viewModelScope.launch(exceptionHandler) {
            appRepository.recoverAccountEmailApi(request)
                .catch { t -> throw  t }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        Log.i("TAG", "recoverAccount: success")
                        val responseBody = response.body() ?: throw Exception("null response body")
                        _recoverAccountData.postValue(responseBody)
                    } else {
                        val status =
                            ErrorUtils.parseError<RecoverAccountModel>(response)
                                ?: throw Exception()
                        throw Exception(status.message)
                    }
                }
        }
    }


    private suspend fun onLoginSuccessResult(loginModel: LoginResponseModel) {
        removePreference()
        saveLoginData(PreferenceKeys.IS_PROFILE_VERIFIED, loginModel.data.is_verified)
        saveLoginData(PreferenceKeys.PREF_RECOVERY_EMAIL, loginModel.data.recoveryEmail)
        var dt = Date()
        var c = Calendar.getInstance()
        val mDateFormat = SimpleDateFormat("MM/dd/yyyy")
        Handler(Looper.getMainLooper()).post {
            dt = Date()
            c = Calendar.getInstance()
            c.time = dt
            c.add(Calendar.DATE, 4)
            dt = c.time
        }
        val plusFour = dt
        val stringPlusFour = mDateFormat.format(plusFour)
        Handler(Looper.getMainLooper()).post {
            dt = Date()
            c = Calendar.getInstance()
            c.time = dt
            c.add(Calendar.DATE, 7)
            dt = c.time
        }
        val plusSeven = dt
        val stringPlusSeven = mDateFormat.format(plusSeven)
        saveLoginData(PreferenceKeys.SHOW_VERIFICATION_REMINDER_DATE, stringPlusFour)
        Log.d("verification_reminder_date", stringPlusFour)
        Log.e("pppppp", loginModel.token)
        saveLoginData(PreferenceKeys.SHOW_EMAIL_REMINDER_DATE, stringPlusSeven)
        saveLoginData(PreferenceKeys.PREF_TOKEN, loginModel.token)
        saveLoginData(PreferenceKeys.PREF_PROFILE_FLAG, loginModel.profile)
        saveLoginData(PreferenceKeys.PREF_PREFERENCE_FLAG, loginModel.preference)
        saveLoginData(PreferenceKeys.PREF_ADV_PROFILE_FLAG, loginModel.advanceprofile)
        saveLoginData(PreferenceKeys.PREF_USER_ID, loginModel.data.userId)
        saveLoginData(PreferenceKeys.PREF_FIRST_NAME, loginModel.data.first_name)
        saveLoginData(PreferenceKeys.PREF_LAST_NAME, loginModel.data.last_name)
        saveLoginData(PreferenceKeys.PREF_PROFILE_IMAGE, loginModel.data.profile_image)
        saveLoginData(PreferenceKeys.PREF_PHONE_NUMBER, loginModel.data.phone_number)
        saveLoginData(PreferenceKeys.PREF_IS_AGREE, loginModel.data.is_agree)
        saveLoginData(
            PreferenceKeys.PREF_DATER_DISABLED,
            loginModel.data.is_dater_disabled
        )
        saveLoginData(
            PreferenceKeys.PREF_CONNECTOR_DISABLED,
            loginModel.data.is_connector_disabled
        )
        saveLoginData(
            PreferenceKeys.PREF_REMAINING_INVITATION,
            loginModel.data.remain_invitation
        )
        saveLoginData(
            PreferenceKeys.PREF_REMAINING_CONNECTOR_CONNECTION,
            loginModel.data.remain_connection
        )
        saveLoginData(
            PreferenceKeys.PREF_INSTA_NAME,
            loginModel.data.instargram_name
        )
        _loginData.postValue(loginModel)
    }

    private suspend fun onLoginSuccessResult(loginModel: OTPModel, isDeepLink: Boolean) {
        removePreference()
        saveLoginData(PreferenceKeys.IS_PROFILE_VERIFIED, loginModel.data.is_verified)
//        saveLoginData(PreferenceKeys.PREF_RECOVERY_EMAIL, loginModel.data.recoveryEmail)
        var dt = Date()
        var c = Calendar.getInstance()
        val mDateFormat = SimpleDateFormat("MM/dd/yyyy")
        Handler(Looper.getMainLooper()).post {
            dt = Date()
            c = Calendar.getInstance()
            c.time = dt
            c.add(Calendar.DATE, 4)
            dt = c.time
        }
        val plusFour = dt
        val stringPlusFour = mDateFormat.format(plusFour)
        Handler(Looper.getMainLooper()).post {
            dt = Date()
            c = Calendar.getInstance()
            c.time = dt
            c.add(Calendar.DATE, 7)
            dt = c.time
        }
        val plusSeven = dt
        val stringPlusSeven = mDateFormat.format(plusSeven)

        Log.d("verification_reminder_date", stringPlusFour)
//       saveLoginData(PreferenceKeys.SHOW_EMAIL_REMINDER_DATE, stringPlusSeven)

//       AppUtils.storeSignupType(AppController.context!!,loginModel.data.signup_type)
        AppUtils.storeAuthToken(AppController.context!!, loginModel.data.token)
        AppUtils.storeIsVerified(AppController.context!!, loginModel.data.is_verified)
        AppUtils.storePrefFlag(AppController.context!!, loginModel.data.preference)
        AppUtils.storeProfileFlag(AppController.context!!, loginModel.data.is_basic_profile)
        AppUtils.storeAdvanceProfileFlag(
            AppController.context!!,
            loginModel.data.is_advance_profile
        )
        AppUtils.storeGender(AppController.context!!, loginModel.data.gender)
        AppUtils.storeInvitationExpired(AppController.context!!, loginModel.data.invitation_expired)
        AppUtils.storeUserId(AppController.context!!, loginModel.data.userDetails.user_id)
        AppUtils.storeUserType(AppController.context!!, loginModel.data.userDetails.user_type)
        AppUtils.storeFirstName(AppController.context!!, loginModel.data.userDetails.first_name)
        AppUtils.storeLastName(AppController.context!!, loginModel.data.userDetails.last_name)
        AppUtils.storeProfileImage(
            AppController.context!!,
            loginModel.data.userDetails.profile_image
        )
        AppUtils.storePhoneNumber(AppController.context!!, loginModel.data.userDetails.phone_number)
        AppUtils.storeAlternateLogin(AppController.context!!, loginModel.data.is_alternat_login)
        AppUtils.storeAgreement(AppController.context!!, loginModel.data.userDetails.is_agree)
        AppUtils.storeRemainingInvitation(
            AppController.context!!,
            loginModel.data.userDetails.remain_invitation
        )
        AppUtils.storeRemainConnection(
            AppController.context!!,
            loginModel.data.userDetails.remaim_connection
        )
        AppUtils.storeInstaUserName(
            AppController.context!!,
            loginModel.data.userDetails.instagram_name
        )
        AppUtils.storeFacebook(AppController.context!!, loginModel.data.userDetails.facebook)
        AppUtils.storeLinkedInId(AppController.context!!, loginModel.data.userDetails.linkedin)
        AppUtils.storeGoogle(AppController.context!!, loginModel.data.userDetails.google)
        AppUtils.storeDaterDisable(AppController.context!!, loginModel.data.userDetails.is_dater_disabled)
        AppUtils.storeConnectorDisable(AppController.context!!, loginModel.data.userDetails.is_connector_disabled)

        if (isDeepLink) {
            _verifyOtpResponseEmailData.postValue(ResultState.Success(loginModel))

        } else {
            _verifyOtpResponseData.postValue(ResultState.Success(loginModel))

        }
    }


    fun userSettingsUpdate(token: String, id: Int, jsonObject: JsonObject) {
        viewModelScope.launch {
            appRepository.userSettingsUpdateApi(token, id, jsonObject)
                .catch { e ->
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@OtpViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.isSuccessful) {
                        _settingsData.postValue(response.body())


                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@OtpViewModel)
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
}

