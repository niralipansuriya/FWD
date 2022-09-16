package com.swipefwd.ui.auth.sociallogin.google

import OTPModel
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.gson.JsonObject
import com.swipefwd.BuildConfig
import com.swipefwd.R
import com.swipefwd.base.AppBaseViewModel
import com.swipefwd.base.ResultState
import com.swipefwd.data.source.AppRepository
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*


class GoogleLogInViewModel(
    private val appRepository: AppRepository,
    private val internalAppDataStore: InternalAppDataStore,
    private val appDatabase: AppDatabase
) : AppBaseViewModel(internalAppDataStore, appDatabase) {
    var googleSignInClient: GoogleSignInClient? = null
    private var googleAccountDetails: GoogleSignInAccount? = null
    fun setAccountDetails(googleSignInAccount: GoogleSignInAccount?) {
        this.googleAccountDetails = googleSignInAccount
    }

    /////////////////////////////////////////////////////////////////////
    private val _userDetailsData = MutableLiveData<ResultState<GoogleUserDetails>>()
    fun userDetails(): LiveData<ResultState<GoogleUserDetails>> = _userDetailsData
    fun createUserDetails() {
        val account = this.googleAccountDetails
        if (account == null) {
            _userDetailsData.value = ResultState.error("google account object is null")
            return
        }
        val clientId = account.id
        if (clientId == null) {
            _userDetailsData.value = ResultState.error("client id is null")
            return
        }
        val email = account.email
        if (email == null) {
            _userDetailsData.value = ResultState.error("email id is null")
            return
        }
        val firstName = account.givenName
        val lastName = account.familyName
        val profileImage = account.photoUrl?.toString()
        //val dob = account.grantedScopes.toString()
        val scopes: Set<Scope> = account.grantedScopes
        for (scope in scopes) {

            println("scopes from google------->>>${scope.scopeUri}")
        }
        //  val gender = account.grantedScopes.toString()
        //set success value
        val collectionScope = Collections.singleton(Scopes.PLUS_ME)
        val googleAuthorizationCodeFlow = GoogleAuthorizationCodeFlow(
            NetHttpTransport(),
            GsonFactory.getDefaultInstance(),
            "406001238441-luocj5cf8n3eviqv18nnu93r9c6r22km.apps.googleusercontent.com",
            "Oh4o76eb7XyvV8mbB2r3DJeW",
            collectionScope
        )

        AsyncTask.execute {
            val googleTokenResponse = googleAuthorizationCodeFlow.newTokenRequest(account.serverAuthCode).execute()
            Log.i("GoogleLogin", "AccessToken ${googleTokenResponse.accessToken}") //heaer
            Log.i("GoogleLogin", "Token ${account.idToken}") //acess token

        }

        // Log.i("GoogleLogin","Server Auth ${account.serverAuthCode}")

        _userDetailsData.value = ResultState.Success(
            GoogleUserDetails(
                clientId = clientId,
                email = email,
                firstName = firstName,
                lastName = lastName,
                profileImage = profileImage
                // dob = dob,
                //gender = gender
            )
        )
    }

    private var _socialData = MutableLiveData<ResultState<OTPModel>>()

    fun googleLogin(email: String) {


        println("email google account id--------->>>$email")
        val apiRequest = JsonObject().apply {
            addProperty("social_id", email)
            addProperty("signup_type", 1)
        }
        //start loading
        _socialData.value = ResultState.Loading
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            val errorMessage = throwable.message
            if (errorMessage.isNullOrBlank()) {
                _socialData.value =
                    ResultState.error(R.string.error_something_went_wrong)
                return@CoroutineExceptionHandler
            }
            _socialData.value = ResultState.error(errorMessage)
        }
        viewModelScope.launch(exceptionHandler) {
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
                            val responseBody = response.body() ?: throw Exception()
                            _socialData.value = ResultState.Success(responseBody)
                            AppUtils.storeAuthToken(
                                AppController.context!!,
                                response.body()!!.data.token
                            )
                            AppUtils.storeIsVerified(
                                AppController.context!!,
                                response.body()!!.data.is_verified
                            )
                            AppUtils.storeIsProfileSetup(
                                AppController.context!!,
                                response.body()!!.data.is_basic_profile
                            )

                            println("response------>>>>${response.body()}")
                        }
                        response.code() == 401 -> {
                            logoutUser()
                        }
                        else -> {
                            val status = ErrorUtils.parseError<OTPModel>(response)
                            throw Exception(status?.message)
                        }
                    }
                }
        }
    }

}