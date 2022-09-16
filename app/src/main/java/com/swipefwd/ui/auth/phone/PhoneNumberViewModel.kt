package com.swipefwd.ui.auth.phone

import OTPModel
import android.os.CountDownTimer
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
import com.swipefwd.base.BaseActivity
import com.swipefwd.base.ResultState
import com.swipefwd.data.models.ResendOtp
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
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class PhoneNumberViewModel(
    private val appRepository: AppRepository,
    private val internalAppDataStore: InternalAppDataStore,
    private val appDatabase: AppDatabase
) : AppBaseViewModel(internalAppDataStore, appDatabase) {
    var isFromDeepLink = false
    private var recoveryEmailId: String? = null
    fun setRecoverEmailId(id: String?) {
        if (id.isNullOrBlank()) return
        isFromDeepLink = true
        this.recoveryEmailId = id
    }

    fun isForRecoverAccount(): Boolean {
        return recoveryEmailId != null
    }

    fun getRecoveryEmail() = recoveryEmailId


    var firstName: String = ""


    //////////////////////////////////////////////////////////////////////////////////////

    var phoneNumber = ""
    var countryCode: String? = null
    var isoCode: String? = null
    var backpress = false
    var previousNumber = ""
    var signUpType = 0
    var socialId = ""

    //////////////////////////////////////////////////////////////////////////////////////
    fun <T> saveData(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            internalAppDataStore.savePreference(key, value)
        }
    }

    private val previousFilledPhoneNumber =
        internalAppDataStore.getString(PreferenceKeys.PREF_PHONE_NUMBER)

    /** this will check if previous filled phone number is same as current phone number **/
    suspend fun isPhoneDifferentFromPreviousFilled(): Boolean {
        return phoneNumber != previousFilledPhoneNumber.firstOrNull() ?: ""
    }


    //////////////////////////////////////////////////////////////////////////////////////
    //resend otp
    private var _getOtpData = MutableLiveData<ResultState<OTPModel>>()
    private var _getOtpEmailData = MutableLiveData<ResultState<ResendOtp>>()
    fun getOtpResult(): LiveData<ResultState<OTPModel>> = _getOtpData
    fun getOtpEmailResult(): LiveData<ResultState<ResendOtp>> = _getOtpEmailData
    private var getOtpJob: Job? = null
    private var getOtpEmailJob: Job? = null

    fun getOtp(isSocialLogin: Boolean) {
        getOtpJob?.cancel()
        /*  val apiRequest = JsonObject().apply {
              addProperty("country_code", countryCode)
              addProperty("phone_number", phoneNumber)
              addProperty("is_recovery", false)
          }  */
        println("phoneNumber----->>>${phoneNumber}")
        println("countryCode----->>>${countryCode}")
        val apiRequest :JsonObject
        if (signUpType == 0)
        {
            apiRequest = JsonObject().apply {
                addProperty("country_code", countryCode)
                addProperty("mobile", phoneNumber)
                //addProperty("signup_type", 0)
                addProperty("signup_type", signUpType)
               // addProperty("social_id", socialId)
                addProperty("is_auth", 1)//for authentication
            }
        }
        else
        {
             apiRequest = JsonObject().apply {
                addProperty("country_code", countryCode)
                addProperty("mobile", phoneNumber)
                //addProperty("signup_type", 0)
                addProperty("signup_type", signUpType)
                addProperty("social_id", socialId)
                addProperty("is_auth", 0)//for set
            }
        }



        //start loading
        _getOtpData.value = ResultState.Loading
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            val errorMessage = throwable.message
            if (errorMessage.isNullOrBlank()) {
                _getOtpData.value =
                    ResultState.error(R.string.error_something_went_wrong)
                return@CoroutineExceptionHandler
            }
            throwable.printStackTrace()
            _getOtpData.value = ResultState.error(errorMessage)
        }
        getOtpJob = viewModelScope.launch(exceptionHandler) {
            // appRepository.getOtpApi(apiRequest)
            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.getOtpApi(headers, apiRequest)
                .catch { t -> throw t }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    when {
                        response.isSuccessful -> {


                            println("response------>>>>${response.body()}")
                            val responseBody = response.body() ?: throw Exception()
                            storeData(responseBody)

                            _getOtpData.value = ResultState.Success(responseBody)


                        }
                        response.code() == 401 -> {
                            logoutUser()
                        }
                        else -> {
                            val status = ErrorUtils.parseError<OTPModel>(response)
                            println("errorrrrr ------>>>>${status?.message}")

                            throw Exception(status?.message)
                        }
                    }
                }
        }
    }
    fun getOtpEamil() {
        getOtpEmailJob?.cancel()

        println("phoneNumber----->>>${phoneNumber}")
        println("countryCode----->>>${countryCode}")


          val  apiRequest = JsonObject().apply {
                addProperty("country_code", countryCode)
                addProperty("mobile", phoneNumber)
            }


        //start loading
        _getOtpEmailData.value = ResultState.Loading
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            val errorMessage = throwable.message
            if (errorMessage.isNullOrBlank()) {
                _getOtpEmailData.value =
                    ResultState.error(R.string.error_something_went_wrong)
                return@CoroutineExceptionHandler
            }
            throwable.printStackTrace()
            _getOtpEmailData.value = ResultState.error(errorMessage)
        }
        getOtpEmailJob = viewModelScope.launch(exceptionHandler) {
            // appRepository.getOtpApi(apiRequest)
            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.sendOtpEmail(headers, apiRequest)
                .catch { t -> throw t }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    when {
                        response.isSuccessful -> {

                            println("response------>>>>${response.body()}")
                            val responseBody = response.body() ?: throw Exception()

                            _getOtpEmailData.value = ResultState.Success(responseBody)


                        }
                        response.code() == 401 -> {
                            logoutUser()
                        }
                        else -> {
                            val status = ErrorUtils.parseError<ResendOtp>(response)
                            println("errorrrrr ------>>>>${status?.message}")

                            throw Exception(status?.message)
                        }
                    }
                }
        }
    }

    fun storeData(otpModel: OTPModel) {
        AppUtils.storeSignupType(AppController.context!!,otpModel.data.signup_type)

        AppUtils.storeAuthToken(AppController.context!!, otpModel.data.token)
        AppUtils.storeIsVerified(AppController.context!!, otpModel.data.is_verified)
        AppUtils.storeUserId(AppController.context!!, otpModel.data.userDetails.user_id)
        AppUtils.storePrefFlag(AppController.context!!, otpModel.data.preference)
        AppUtils.storeProfileFlag(AppController.context!!, otpModel.data.is_basic_profile)
        AppUtils.storeAdvanceProfileFlag(AppController.context!!, otpModel.data.is_advance_profile)
        AppUtils.storeGender(AppController.context!!, otpModel.data.gender)
        AppUtils.storeInvitationExpired(AppController.context!!, otpModel.data.invitation_expired)
        AppUtils.storeUserType(AppController.context!!, otpModel.data.userDetails.user_type)
        AppUtils.storeFirstName(AppController.context!!, otpModel.data.userDetails.first_name)
        AppUtils.storeLastName(AppController.context!!, otpModel.data.userDetails.last_name)
        AppUtils.storeProfileImage(AppController.context!!, otpModel.data.userDetails.profile_image)
        AppUtils.storePhoneNumber(AppController.context!!, otpModel.data.userDetails.phone_number)
        AppUtils.storeAgreement(AppController.context!!, otpModel.data.userDetails.is_agree)
        AppUtils.storeRemainingInvitation(
            AppController.context!!,
            otpModel.data.userDetails.remain_invitation
        )
        AppUtils.storeRemainConnection(
            AppController.context!!,
            otpModel.data.userDetails.remaim_connection
        )
        AppUtils.storeInstaUserName(
            AppController.context!!,
            otpModel.data.userDetails.instagram_name
        )
        AppUtils.storeFacebook(AppController.context!!, otpModel.data.userDetails.facebook)
        AppUtils.storeLinkedInId(AppController.context!!, otpModel.data.userDetails.linkedin)
        AppUtils.storeGoogle(AppController.context!!, otpModel.data.userDetails.google)
        AppUtils.storeDaterDisable(
            AppController.context!!,
            otpModel.data.userDetails.is_dater_disabled
        )
        AppUtils.storeConnectorDisable(
            AppController.context!!,
            otpModel.data.userDetails.is_connector_disabled
        )
      /*  AppUtils.storeBio(AppController.context!!,otpModel.data.userDetails.bio)
        AppUtils.storeHeight(AppController.context!!,otpModel.data.userDetails.height)
        AppUtils.storeEducation(AppController.context!!,otpModel.data.userDetails.education_level)
        AppUtils.storeInstitute(AppController.context!!,otpModel.data.userDetails.institute)
        AppUtils.storeGraduationYear(AppController.context!!,otpModel.data.userDetails.graduation_year)

      *//*  var lang = otpModel.data.userDetails.language
        if (lang.isNotBlank() && lang.contains('['))
        {
            lang = lang.replace("[", " ")
            lang = lang.replace("]", " ")

        }

        AppUtils.storeLanguae(AppController.context!!,lang)*//*
        AppUtils.storeLanguae(AppController.context!!,otpModel.data.userDetails.language)
        AppUtils.storeOccupation(AppController.context!!,otpModel.data.userDetails.occupation)
        AppUtils.storeAstrologicalSign(AppController.context!!,otpModel.data.userDetails.astrological_sign)
        AppUtils.storeChildren(AppController.context!!,otpModel.data.userDetails.children)
        AppUtils.storeReligion(AppController.context!!,otpModel.data.userDetails.religion)
        AppUtils.storeVaccination(AppController.context!!,otpModel.data.userDetails.is_vaccinated)
        AppUtils.storeSmoking(AppController.context!!,otpModel.data.userDetails.smoke)
        AppUtils.storeRelationship(AppController.context!!,otpModel.data.userDetails.relation_interest)*/
    }

    //////////////////////////////////////////////////////////////////////////////////////
    private var phoneNumberSuspendedStatus = false
    fun isPhoneNumberSuspend() = phoneNumberSuspendedStatus

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
                phoneNumberSuspendedStatus = true
                _timerRefreshed.value = remainingTime
            }

            override fun onFinish() {
                phoneNumberSuspendedStatus = false
                _timerStopped.value = Unit
            }
        }.start()
    }

    fun checkIfPhoneSuspend(timestampTimeout: String, globalTimeStampTimeout: String) {

        viewModelScope.launch {
            val suspendedPhoneNumber = internalAppDataStore.fetchSuspendedPhoneNumber()

            if (suspendedPhoneNumber != null) {
                val upTime = SystemClock.uptimeMillis()
                val startTime = suspendedPhoneNumber.suspendStartTime
                val time = upTime - startTime
                val isSuspended = time < AppConstants.PHONE_NUMBER_SUSPEND_TIME

                if (isSuspended) {
                    if (suspendedPhoneNumber.isFiveMins)
                        startTimer(AppConstants.PHONE_NUMBER_SUSPEND_TIME * 10 - time)
                    else {
                        startTimer(AppConstants.PHONE_NUMBER_SUSPEND_TIME - time)
                    }

                } else {
                    internalAppDataStore.removeSuspendedPhoneNumber()
                }
            } else if (globalTimeStampTimeout.isNotEmpty()) {

                val upTime = SystemClock.uptimeMillis()
                val startTime = globalTimeStampTimeout.toLong()
                val time = upTime - startTime

                var isNotExpired = time < AppConstants.PHONE_NUMBER_SUSPEND_TIME * 10
                if (isNotExpired)
                    startTimer(AppConstants.PHONE_NUMBER_SUSPEND_TIME * 10 - time)
            } else if (timestampTimeout.isNotEmpty()) {
                val upTime = SystemClock.uptimeMillis()
                val startTime = timestampTimeout.toLong()
                val time = upTime - startTime
                var isInTimeout = time < AppConstants.PHONE_NUMBER_SUSPEND_TIME
                if (isInTimeout)
                    startTimer((AppConstants.PHONE_NUMBER_SUSPEND_TIME * 10) - time)
            }
        }
    }

    fun stopTimer() {
        timer?.cancel()
    }

    companion object {

    }

}