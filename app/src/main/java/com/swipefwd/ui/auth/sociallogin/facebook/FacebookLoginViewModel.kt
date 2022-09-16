package com.swipefwd.ui.auth.sociallogin.facebook

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.swipefwd.base.ResultState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FacebookLoginViewModel : ViewModel() {
    companion object {
        private const val TAG = "facebook_login"
    }

    private var friendPermissionStatus = true

    private var loginResult: LoginResult? = null
    fun setLoginResult(loginResult: LoginResult?) {
        this.loginResult = loginResult
    }

    /////////////////////////////////////////////////////////////////////
    private val _userDetailsData = MutableLiveData<ResultState<FacebookUserDetails>>()
    fun userDetails(): LiveData<ResultState<FacebookUserDetails>> = _userDetailsData

    fun createUserDetails() {
        val account = this.loginResult
        if (account == null) {
            _userDetailsData.value = ResultState.error("facebook login result object is null")
            return
        }
        val accessToken = account.accessToken
        if (accessToken == null) {
            _userDetailsData.value = ResultState.error("facebook access token is null")
            return
        }

        /** check the recent denied permission**/
        val deniedPermissions: Set<String> = account.recentlyDeniedPermissions
        if (deniedPermissions.contains("user_friends")) {
            friendPermissionStatus = false
        }
        //loading starts here
        _userDetailsData.value = ResultState.Loading
        //this exception handler will handle the exception occurs in the facebook user details
        // getting operation
        val exceptionHandler = CoroutineExceptionHandler { _, _ ->
            _userDetailsData.value =
                ResultState.error("facebook graph request coroutine exception handler")
        }
        viewModelScope.launch(exceptionHandler) {
            val facebookUserDetails = getFacebookUserDetails(accessToken)
            _userDetailsData.value = ResultState.Success(facebookUserDetails)
        }
    }


    /** this function request the user details from facebook based on the access token */
    private suspend fun getFacebookUserDetails(accessToken: AccessToken): FacebookUserDetails =
        suspendCoroutine { continuation ->
            val request =
                GraphRequest.newMeRequest(accessToken) { jsonObject, _ ->
                    try {
                        if (jsonObject == null) {
                            throw Exception("json object is null")
                        }
                        if (jsonObject.has("id")) {
                            val clientId = jsonObject.optString("id")
                            val email = jsonObject.optString("email")
                            val firstName = jsonObject.optString("first_name")
                            val lastName = jsonObject.optString("last_name")
                            val birthday = jsonObject.optString("birthday")
                            val gender = jsonObject.optString("gender")


                            val requestPicture = GraphRequest.newGraphPathRequest(
                                accessToken,
                                "/${clientId}/picture?type=square&height=150&width=150&redirect=0") {
                                Log.i("Facebook", "Picture JSON ${it.jsonObject}")
                                var profileImage = getFacebookProfileUrl(clientId)
                                if(it.jsonObject?.has("data")!!) {
                                    profileImage = it.jsonObject?.getJSONObject("data")?.getString("url")!!
                                }
                                println("profileImage graph-------->>>$profileImage")
                                val facebookUserDetails = FacebookUserDetails(
                                    clientId = clientId,
                                    firstName = if (firstName.isBlank()) null else firstName,
                                    lastName = if (lastName.isBlank()) null else lastName,
                                    birthDate = if (birthday.isBlank()) null else birthday,
                                    gender = if (gender.isBlank()) null else gender,
                                    email = if (email.isBlank()) null else email,
                                    profileImage = if (profileImage.isBlank()) null else profileImage,
                                    friendPermissionStatus = friendPermissionStatus
                                )
                                continuation.resume(facebookUserDetails)
                            }
                            requestPicture.executeAsync()

                         /*   if (jsonObject.has("picture")) {
                                val profilePicUrl: String =
                                    jsonObject.getJSONObject("picture").getJSONObject("data")
                                        .getString("url")
                            }
                            val profileImage = getFacebookProfileUrl(clientId)
                            println("profileImage graph-------->>>$profileImage")

                            if (clientId.isBlank()) {
                                throw Exception("fb error blank client id")
                            }*/
                           /* val facebookUserDetails = FacebookUserDetails(
                                clientId = clientId,
                                firstName = if (firstName.isBlank()) null else firstName,
                                lastName = if (lastName.isBlank()) null else lastName,
                                birthDate = if (birthday.isBlank()) null else birthday,
                                gender = if (gender.isBlank()) null else gender,
                                email = if (email.isBlank()) null else email,
                                profileImage = if (profileImage.isBlank()) null else profileImage,
                                friendPermissionStatus = friendPermissionStatus
                            )*/
                            //continuation.resume(facebookUserDetails)
                        } else {
                            throw Exception("json data id not available")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.e(TAG, "fb graph request error")
                        throw Exception("fb graph request error")
                    }
                }
            val parameters = Bundle()
           // parameters.putString("fields", "birthday,gender,first_name,last_name,email,id") //picture.type(large)
            parameters.putString("fields", "birthday,gender,first_name,last_name,email,id,picture") //picture.type(large)
            request.parameters = parameters
            request.executeAsync()
        }


    private fun getFacebookProfileUrl(clientId: String): String {
        return "https://graph.facebook.com/$clientId/picture?height=1000"
       // return "https://graph.facebook.com/$clientId/picture"
     //   return "https://graph.facebook.com/$clientId?fields=picture.type(large)"
    }

}