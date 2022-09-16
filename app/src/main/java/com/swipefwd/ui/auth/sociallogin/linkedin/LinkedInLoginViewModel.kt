package com.swipefwd.ui.auth.sociallogin.linkedin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swipefwd.R
import com.swipefwd.base.ResultState
import com.swipefwd.data.source.AppRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LinkedInLoginViewModel(private val appRepository: AppRepository) : ViewModel() {
    companion object {
        private const val TAG = "LinkedInLoginViewModel"
    }

    private val _userDetailsData = MutableLiveData<ResultState<LinkedInUserDetails>>()
    fun userDetails(): LiveData<ResultState<LinkedInUserDetails>> = _userDetailsData
    private var linkedInTokenRequestJob: Job? = null
    fun getLinkedInProfileDetails(code: String) {
        linkedInTokenRequestJob?.cancel()
        //exception handler
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            val errorMessage = throwable.message
            if (errorMessage.isNullOrBlank()) {
                _userDetailsData.value =
                    ResultState.error(R.string.error_something_went_wrong)
                return@CoroutineExceptionHandler
            }
            _userDetailsData.value = ResultState.error(errorMessage)
        }
        //start loading
        _userDetailsData.value = ResultState.Loading
        linkedInTokenRequestJob = viewModelScope.launch(exceptionHandler) {
            val tokenRequestResponse = appRepository.linkedInTokenApi(code)
            if (!tokenRequestResponse.isSuccessful) {
                throw Exception("linkedin token response failed")
            }
            val tokenResponse =
                tokenRequestResponse.body() ?: throw Exception("null response body")
            val token = getResolvedAccessToken(tokenResponse.accessToken)
            val profileResponse = appRepository.linkedInProfileApi(token)
            Log.d("TOKENNNNNN", token)
            if (!profileResponse.isSuccessful) {
                throw Exception("linkedin profile response failed")
            }
            val profileDetails =
                profileResponse.body() ?: throw Exception("null profile response body")
            val emailResponse = appRepository.linkedInEmailApi(token)
            if (!emailResponse.isSuccessful) {
                throw Exception("linkedin profile response failed")
            }
            val emailDetails = emailResponse.body() ?: throw Exception("null email response body")

            val firstName = profileDetails.firstName.localized.enUS
            val lastName = profileDetails.lastName.localized.enUS
            val profileId = profileDetails.id
            val emailId = emailDetails.elements[0].handle.emailAddress
            /* Given sizes from Linkedin for Profile
            100*100
            200*200
            400*400
            800*800*/
            val profileImageUrl: String? =
                if (profileDetails.profilePicture.displayImage.elements.size > 0) {
                    profileDetails.profilePicture.displayImage.elements[3].identifiers[0].identifier //800*800 size
                } else null
            _userDetailsData.value =
                ResultState.Success(
                    LinkedInUserDetails(
                        clientId = profileId,
                        firstName = firstName,
                        lastName = lastName,
                        profileImage = profileImageUrl,
                        email = emailId
                    )
                )

        }
    }

    private fun getResolvedAccessToken(token: String): String {
        return "Bearer $token"
    }
}