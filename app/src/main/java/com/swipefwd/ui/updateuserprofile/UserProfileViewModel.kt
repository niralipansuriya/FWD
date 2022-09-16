package com.swipefwd.ui.updateuserprofile

import BasicProfileDetails
import BasicProfileUpload
import ProfilePhotosModel
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.BuildConfig
import com.swipefwd.base.ResultState
import com.swipefwd.data.models.*
import com.swipefwd.data.models.walletModels.GoogleAddressModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.xmpp.ChatImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class UserProfileViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            fwdDataStore.savePreference(key, value)
        }
    }

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)
    val getLanguage = fwdDataStore.getString(PreferenceKeys.PREF_LANGUAGE)
    val getCast = fwdDataStore.getString(PreferenceKeys.PREF_CAST)
    val getOccupation = fwdDataStore.getString(PreferenceKeys.PREF_OCCUPATION)
    val getEducation = fwdDataStore.getString(PreferenceKeys.PREF_EDUCATION)
    val getTravelLocation = fwdDataStore.getString(PreferenceKeys.PREF_TRAVEL_LOCATION)
    val getChildren = fwdDataStore.getString(PreferenceKeys.PREF_CHILDREN)
    val getReligion = fwdDataStore.getString(PreferenceKeys.PREF_RELIGION)
    val getSmoking = fwdDataStore.getString(PreferenceKeys.PREF_SMOKING)
    val getRelationship = fwdDataStore.getString(PreferenceKeys.PREF_RELATIONSHIP)
    val getVaccineStatus = fwdDataStore.getString(PreferenceKeys.PREF_COVID)
    val getAstroSign = fwdDataStore.getString(PreferenceKeys.PREF_ASTROLOGY_SIGN)
    val getAccountType = fwdDataStore.getString(PreferenceKeys.PREF_ACCOUNT_TYPE)
    val getSocialMediaUserModel = fwdDataStore.getString(PreferenceKeys.PREF_SOCIAL_MEDIA_USER)
    val getFirstName = fwdDataStore.getString(PreferenceKeys.PREF_FIRST_NAME)
    val getLastName = fwdDataStore.getString(PreferenceKeys.PREF_LAST_NAME)
    val getDob = fwdDataStore.getString(PreferenceKeys.PREF_DOB)
    val getConvertedDob = fwdDataStore.getString(PreferenceKeys.PREF_CONVERTED_DOB)
    val getGender = fwdDataStore.getString(PreferenceKeys.PREF_GENDER)
    val getGenderPref = AppUtils.getGender(AppController.context!!)
    val getArea = fwdDataStore.getString(PreferenceKeys.PREF_AREA)
    val getBio = fwdDataStore.getString(PreferenceKeys.PREF_BIO)
    val getHeight = fwdDataStore.getString(PreferenceKeys.PREF_HEIGHT)
    val getIsHeightFeet = fwdDataStore.getBoolean(PreferenceKeys.PREF_IS_HEIGHT_FEET)

    val getAllImages = fwdDataStore.getString(PreferenceKeys.PREF_IMAGES)
    val getIsVerified = fwdDataStore.getBoolean(PreferenceKeys.PREF_IS_VERIFIED)
    val getInstagramImages = fwdDataStore.getString(PreferenceKeys.PREF_INSTA_IMAGES)

    val isDobDialogOpen = fwdDataStore.getBoolean(PreferenceKeys.PREF_IS_DOB_DIALOG_OPEN)

    val isOnBoardTutorialCompletedStatus =
        fwdDataStore.getBoolean(PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED)

    suspend fun isOnBoardTutorialCompleted(): Boolean {
        return isOnBoardTutorialCompletedStatus.firstOrNull() ?: false
    }

    val getUserId = fwdDataStore.getInt(PreferenceKeys.PREF_USER_ID)

    val getProfileSubmitted = fwdDataStore.getBoolean(PreferenceKeys.PREF_PROFILE_FLAG)

    val getPreferenceSubmitted = fwdDataStore.getBoolean(PreferenceKeys.PREF_PREFERENCE_FLAG)

    val getAdvProfileSubmitted = fwdDataStore.getBoolean(PreferenceKeys.PREF_ADV_PROFILE_FLAG)

    val getRecoveryEmail = fwdDataStore.getString(PreferenceKeys.PREF_RECOVERY_EMAIL)

    val getInstaId = fwdDataStore.getString(PreferenceKeys.PREF_INSTA_ID)

    val getInstaName = fwdDataStore.getString(PreferenceKeys.PREF_INSTA_NAME)

    val getIsInstaProfile = fwdDataStore.getBoolean(PreferenceKeys.PREF_IS_SHOW_PROFILE)

    val isDisableAgreement = fwdDataStore.getBoolean(PreferenceKeys.PREF_IS_AGREE)

    val getProfileImage = fwdDataStore.getString(PreferenceKeys.PREF_PROFILE_IMAGE)

    val getDaterDisabled = fwdDataStore.getBoolean(PreferenceKeys.PREF_DATER_DISABLED)

    val getConnectorDisabled = fwdDataStore.getBoolean(PreferenceKeys.PREF_CONNECTOR_DISABLED)

    val getInviterGender = fwdDataStore.getString(PreferenceKeys.INVITER_GENDER)

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _data = MutableLiveData<CommonResponseModel>()
    var data: LiveData<CommonResponseModel> = _data


    private var _dataEmail = MutableLiveData<SetEmailModel>()
    var dataEmail: LiveData<SetEmailModel> = _dataEmail

    private var _getDateData = MutableLiveData<DateGetModel>()
    var dateData: LiveData<DateGetModel> = _getDateData


    private var _loactionData = MutableLiveData<LocationModel>()
    var locationData: LiveData<LocationModel> = _loactionData

    private var _basicProfileData = MutableLiveData<BasicProfileDetails>()
    var basicProfileData: LiveData<BasicProfileDetails> = _basicProfileData

    private var _detailedProfileData = MutableLiveData<DetailedProfileModel>()
    var detailedProfileData: LiveData<DetailedProfileModel> = _detailedProfileData

    private var _uploadData = MutableLiveData<CommonResponseModel>()
    var uploadData: LiveData<CommonResponseModel> = _uploadData


    private var _profileUploadData = MutableLiveData<BasicProfileUpload>()
    var profileUploadData: LiveData<BasicProfileUpload> = _profileUploadData

    private var _detailPhotosUploadData = MutableLiveData<ProfilePhotosModel>()
    var detailPhotosUploadData: LiveData<ProfilePhotosModel> = _detailPhotosUploadData

    private var _deleteAccount = MutableLiveData<DeleteAccountModel>()
    var deleteAccount: LiveData<DeleteAccountModel> = _deleteAccount

    private var _uploadError = MutableLiveData<CommonResponseModel>()
    var uploadError: LiveData<CommonResponseModel> = _uploadError

    private var _emailError = MutableLiveData<SetEmailModel>()
    var emailError: LiveData<SetEmailModel> = _emailError

    private var _profileUploadError = MutableLiveData<BasicProfileUpload>()
    var profileUploadError: LiveData<BasicProfileUpload> = _profileUploadError

    private var _deleteData = MutableLiveData<ProfilePhotosModel.DataProfile.UserPhotos>()
    var deleteData: LiveData<ProfilePhotosModel.DataProfile.UserPhotos> = _deleteData

    private var _deleteMoreImage = MutableLiveData<ProfilePhotosModel.DataProfile.UserPhotos>()
    var deleteMoreImage: LiveData<ProfilePhotosModel.DataProfile.UserPhotos> = _deleteMoreImage

    private var _error = MutableLiveData<CommonResponseModel>()
    private var _errorDeleteAccount = MutableLiveData<DeleteAccountModel>()
    var error: LiveData<CommonResponseModel> = _error


    private var _locationError = MutableLiveData<LocationModel>()
    var locationError: LiveData<LocationModel> = _locationError

    private var _errorBasicDetails = MutableLiveData<BasicProfileDetails>()
    var errorBasicDetails: LiveData<BasicProfileDetails> = _errorBasicDetails

    private var _errorDetailedProfile = MutableLiveData<DetailedProfileModel>()
    var errorDetailedProfile: LiveData<DetailedProfileModel> = _errorDetailedProfile

    private var _instaProfile = MutableLiveData<InstagramProfileModel>()
    var instaProfile: LiveData<InstagramProfileModel> = _instaProfile

    private var _instaImages = MutableLiveData<ArrayList<String>>()
    var instaImages: LiveData<ArrayList<String>> = _instaImages

    private val instaImageList = ArrayList<String>()

    private var imagesList = ArrayList<UserImagesResponseModel.Item>()

    fun <T> removePreference(key: Preferences.Key<T>) {
        viewModelScope.launch {
            fwdDataStore.removePreference(key)
        }
    }

    private var _updateData = MutableLiveData<CommonResponseModel>()
    var updateData: LiveData<CommonResponseModel> = _updateData

    private var _updateError = MutableLiveData<CommonResponseModel>()
    var updateError: LiveData<CommonResponseModel> = _updateError

    private var _userProfileData = MutableLiveData<UserProfileResponseModel>()
    var userProfileData: LiveData<UserProfileResponseModel> = _userProfileData

    private var _userProfileError = MutableLiveData<CommonResponseModel>()
    var userProfileError: LiveData<CommonResponseModel> = _userProfileError

    private var _imagesData = MutableLiveData<ArrayList<UserImagesResponseModel.Item>>()
    var imagesData: LiveData<ArrayList<UserImagesResponseModel.Item>> = _imagesData

    private var _imagesError = MutableLiveData<UserImagesResponseModel>()
    var imagesError: LiveData<UserImagesResponseModel> = _imagesError

    private var _sendInstaImagesData = MutableLiveData<InstagramUploadResponseModel>()
    var sendInstaImagesData: LiveData<InstagramUploadResponseModel> = _sendInstaImagesData

    private var _sendInstaImagesError = MutableLiveData<InstagramUploadResponseModel>()
    var sendInstaImagesError: LiveData<InstagramUploadResponseModel> = _sendInstaImagesError

    private var _settingsData = MutableLiveData<CommonResponseModel>()
    var settingsData: LiveData<CommonResponseModel> = _settingsData

    private var _travelData = MutableLiveData<CommonResponseModel>()
    var travelData: LiveData<CommonResponseModel> = _travelData

    private var _travelLocationData = MutableLiveData<LocationModel>()
    var travelLocationData: LiveData<LocationModel> = _travelLocationData

    private var _profileVerificationData = MutableLiveData<GestureVerificationModel>()
    var profileVerificationData: LiveData<GestureVerificationModel> = _profileVerificationData

    private var _profileGestureVerificationData = MutableLiveData<GestureVerificationProfileModel>()
    var profileGestureVerificationData: LiveData<GestureVerificationProfileModel> =
        _profileGestureVerificationData

    private var _travelError = MutableLiveData<CommonResponseModel>()
    var travelError: LiveData<CommonResponseModel> = _travelError

    private var _travelLocationError = MutableLiveData<LocationModel>()
    var travelLocationError: LiveData<LocationModel> = _travelLocationError

    private var _resultProfile = MutableLiveData<LinkedInProfileModel>()
    var resultProfile: LiveData<LinkedInProfileModel> = _resultProfile

    private var _imageVerification = MutableLiveData<ImageModerationResponseModel>()
    var imageVerification: LiveData<ImageModerationResponseModel> = _imageVerification


    private var _chatImageUrl = MutableLiveData<ChatImageModel>()
    var chatImageUrl: LiveData<ChatImageModel> = _chatImageUrl

    private var _instagramToken = MutableLiveData<String>()
    var instagramToken: LiveData<String> = _instagramToken


    private var _chatImageError = MutableLiveData<ImageModerationResponseModel>()
    var chatImageError: LiveData<ImageModerationResponseModel> = _chatImageError


    private var _imageVerificationError = MutableLiveData<ImageModerationResponseModel>()
    var imageVerificationError: LiveData<ImageModerationResponseModel> = _imageVerificationError

    private var _addressData = MutableLiveData<GoogleAddressModel>()
    var addressData: LiveData<GoogleAddressModel> = _addressData

    private var _addressError = MutableLiveData<CommonResponseModel>()
    var addressError: LiveData<CommonResponseModel> = _addressError


    private var _gestureError = MutableLiveData<GestureVerificationProfileModel>()
    var gestureError: LiveData<GestureVerificationProfileModel> = _gestureError

    private var _locationDataSubmit = MutableLiveData<ResultState<LocationModel>>()

    fun locationSaveUpdate(): LiveData<ResultState<LocationModel>> = _locationDataSubmit

    fun userProfileSubmit(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userProfileSubmitApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
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

    fun saveLocation(jsonObject: JsonObject) {
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.setLocation(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        println("reponse is sucessfull save location----->>>>>>${response.body()}")
                        // _loactionData.postValue(response.body())
                        val responseBody = response.body() ?: throw Exception()

                        _locationDataSubmit.value = ResultState.Success(responseBody)
                        //  _locationDataSubmit.postValue(response.body())
                    } else {
                        println("error in save location ----->>>>>>${response.message()}")
                        println("error in save location 2222----->>>>>>${response.errorBody()}")
                        println("error in save location 333----->>>>>>${response.code()}")

                        _locationError.postValue(ErrorUtils.parseError<LocationModel>(response))
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
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        //AppUtils.clearSharedPreferences(AppController.context!!)

                        println("delete account --------->>>>${response.body()}")
                        _deleteAccount.postValue(response.body())

                    } else {

                        _errorDeleteAccount.postValue(ErrorUtils.parseError<DeleteAccountModel>(response))
                    }
                }
        }
    }

    fun sendBasicProfileData(jsonObject: JsonObject) {
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.sendBasicProfileData(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _basicProfileData.postValue(response.body())
                    } else {
                        _errorBasicDetails.postValue(
                            ErrorUtils.parseError<BasicProfileDetails>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun sendDetailedProfileData(jsonObject: JsonObject) {
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.setDetailedProfile(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        println("detailed Profile data API call ----------->>>>>$response")
                        _detailedProfileData.postValue(response.body())
                    } else {
                        _errorDetailedProfile.postValue(
                            ErrorUtils.parseError<DetailedProfileModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun setAdavanceProfile(jsonObject: JsonObject) {
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.sendBasicProfileData(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _basicProfileData.postValue(response.body())
                    } else {
                        _errorBasicDetails.postValue(
                            ErrorUtils.parseError<BasicProfileDetails>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun uploadProfilePicture(imageFile: File) {
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userBasicProfileSet(headers, imageFile)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    when {
                        response.isSuccessful -> {
//                              _uploadData.postValue(response.body())
                            _profileUploadData.postValue(response.body())

                            AppUtils.storeProfileImage(
                                AppController.context!!,
                                Uri.parse(response.body()!!.data.profile_image).toString()
                            )

                            println("uploaded profile photo--------->>>>>>>${Uri.parse(response.body()!!.data.profile_image)}")
                        }
                        response.code() == 504 -> {
                            AppUtils.openFailNetworkDialog(context)
                        }
                        else -> {
                            _profileUploadError.postValue(
                                ErrorUtils.parseError<BasicProfileUpload>(
                                    response
                                )
                            )
                        }
                    }
                }
        }
    }


    fun uploadProfilePhotos(position: String, imageFile: File) {
        println("file size in more photos------>>>>${imageFile.length()}")
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userProfilePhotosUpload(headers, position, imageFile)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    when {
                        response.isSuccessful -> {
//                              _uploadData.postValue(response.body())
                            _detailPhotosUploadData.postValue(response.body())

                            println("uploaded profile photos--------->>>>>>>${Uri.parse(response.body()!!.dataProfile.photo)}")
                        }
                        response.code() == 504 -> {
                            AppUtils.openFailNetworkDialog(context)
                        }
                        else -> {
                            _profileUploadError.postValue(
                                ErrorUtils.parseError<BasicProfileUpload>(
                                    response
                                )
                            )
                        }
                    }
                }
        }
    }

    fun userProfilePicture(token: String, isProfile: String, imageFile: File) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userProfilePictureApi(token, isProfile, imageFile)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    when {
                        response.isSuccessful -> {
                            _uploadData.postValue(response.body())
                        }
                        response.code() == 504 -> {
                            AppUtils.openFailNetworkDialog(context)
                        }
                        else -> {
                            var errorResponse = JSONObject(response.errorBody()?.string())
                            if (errorResponse.has("message"))
                                _errorMessage.postValue(errorResponse.getString("message"))
                            else
                                _uploadError.postValue(
                                    ErrorUtils.parseError<CommonResponseModel>(
                                        response
                                    )
                                )
                        }
                    }
                }
        }
    }

    fun userProfilePictureDelete(isProfileVerified: Int) {
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        val jsonObj = JsonObject().apply {
            addProperty("is_profile_verified", isProfileVerified)
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userProfilePictureDeleteApi(headers, jsonObj)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _deleteData.postValue(response.body())
                    } else {
                        _error.postValue(ErrorUtils.parseError<CommonResponseModel>(response))
                    }
                }
        }
    }

    fun userProfileImagesDelete(position: Int) {
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        val jsonObj = JsonObject().apply {
            addProperty("position", position)
        }
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userPictureDeleteAsync(headers, jsonObj)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _deleteMoreImage.postValue(response.body())
                    } else {
                        _error.postValue(ErrorUtils.parseError<CommonResponseModel>(response))
                    }
                }
        }
    }

    fun userProfileUpdate(token: String, id: Int, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.userProfileUpdateApi(token, id, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
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

    fun recoverEmail(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            //  appRepository.recoverEmailApi(token, jsonObject)

            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.recoverEmailAddress(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        println("email recover sucessfully---->>>>>>>>${response.body()}")
                        _data.postValue(response.body())
                    } else {
                        /* _errorMessage.postValue(
                             AppUtils.setApiMessage(
                                 response.body()!!.message.toString(),
                                 this@UserProfileViewModel
                             )
                         )*/
                        _error.postValue(ErrorUtils.parseError<CommonResponseModel>(response))
                    }
                }
        }
    }
    fun setEmail( jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            //  appRepository.recoverEmailApi(token, jsonObject)

            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.setEmailUserApi(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
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
                        _emailError.postValue(ErrorUtils.parseError<SetEmailModel>(response))
                    }
                }
        }
    }
    fun getDateList( jsonObject: JsonObject) {
        _showLoading.postValue(false)
        viewModelScope.launch {
            //  appRepository.recoverEmailApi(token, jsonObject)

            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.getDateList(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        println("get date list response-->>>>>>>>${response.body()}")
                        _getDateData.postValue(response.body())
                    } else {
                        /* _errorMessage.postValue(
                             AppUtils.setApiMessage(
                                 response.body()!!.message.toString(),
                                 this@UserProfileViewModel
                             )
                         )*/
                       // _emailError.postValue(ErrorUtils.parseError<SetEmailModel>(response))
                    }
                }
        }
    }

    fun updateDateList( jsonObject: JsonObject) {
        _showLoading.postValue(false)
        viewModelScope.launch {
            //  appRepository.recoverEmailApi(token, jsonObject)

            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.updateDateList(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        println("date updated sucessfully---->>>>>>>>${response.body()}")
                       // _getDateData.postValue(response.body())
                    } else {
                        /* _errorMessage.postValue(
                             AppUtils.setApiMessage(
                                 response.body()!!.message.toString(),
                                 this@UserProfileViewModel
                             )
                         )*/
                       // _emailError.postValue(ErrorUtils.parseError<SetEmailModel>(response))
                    }
                }
        }
    }

    fun recoverAccount(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            val headers = HashMap<String, String>()
            headers.put("language", BuildConfig.Language)
            headers.put("app_version", BuildConfig.VERSION_NAME)
            headers.put("device_type", BuildConfig.DEVICE_TYPE)
            headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
            headers.put("auth_token", BuildConfig.DEFALUT_TOKEN)
            appRepository.recoverEmailAddress(headers, jsonObject)
                //    appRepository.recoverAccountApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.message.toString(),
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        println("email recover sucessfully---->>>>>>>>${response.body()}")

                        _data.postValue(response.body())
                    } else {
                        /*_errorMessage.postValue(
                            AppUtils.setApiMessage(
                                response.message().toString(),
                                this@UserProfileViewModel
                            )
                        )*/
// comment by krupa 17/08
                        _error.postValue(ErrorUtils.parseError<CommonResponseModel>(response))
//                        val status = ErrorUtils.parseError<CommonResponseModel>(response)?: throw Exception()
//                        throw Exception(status.message)
                    }
                }
        }
    }


    fun getInstagramToken(code: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.instagramTokenApi(code)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        response.body()?.let { data ->
                            println("getInstagramToken------------->>>>>${data.accessToken}")
                            println("getInstagramToken sucess------------->>>>>${response.body()}")

                            _instagramToken.postValue(data.accessToken ?: "")
                            getInstagramProfile(data.userId ?: 0L, data.accessToken ?: "")
                        }

                    } else {
                        println("getInstagramToken error------------->>>>>${response.body()}")

                        _showLoading.postValue(false)
                        //_error.postValue(ErrorUtils.parseError<CommonResponseModel>(response))
                    }
                }
        }
    }

    private fun getInstagramProfile(userId: Long, token: String) {
        viewModelScope.launch {
            appRepository.instagramProfileAPi(userId, token)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        println("getInstagramProfile sucess------------->>>>>${response.body()}")

                        _instaProfile.postValue(response.body())
                        _showLoading.postValue(false)
                    } else {
                        println("getInstagramProfile error------------->>>>>${response.body()}")

                        _showLoading.postValue(false)
                        //_error.postValue(ErrorUtils.parseError<CommonResponseModel>(response))
                    }
                }
        }
    }

    fun getInstagramMedia(mediaList: ArrayList<InstagramProfileModel.Data>, token: String) {
        viewModelScope.launch {
            mediaList.take(10) // taking only 10 images for user
                .asFlow()
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .map {
                    var mediaUrl = ""
                    appRepository.instagramMediaApi(it.id ?: "", token)
                        .map { response ->
                            response.body()?.let { media ->
                                if (media.mediaType != "VIDEO") {
                                    media.mediaUrl
                                } else {
                                    null
                                }
                            }
                        }.collect { string ->
                            mediaUrl = string ?: ""
                        }
                    mediaUrl
                }
                .onCompletion {
                    _showLoading.postValue(false)
                    _instaImages.postValue(instaImageList)
                }
                .collect {
                    instaImageList.add(it)
                }
        }
    }

    fun getUserProfileDetails(isShowLoader: Boolean = true, token: String, userID: Int) {
        if (isShowLoader)
            _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getUserProfileApi(token, userID)
                .catch { e ->
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _userProfileData.postValue(response.body())
                    } else {
                        try {
                            _userProfileError.postValue(
                                ErrorUtils.parseError<CommonResponseModel>(
                                    response
                                )
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            imagesList(token = token)
        }
    }

    private fun imagesList(token: String, page: Int = 1) {
        if (page == 1) {
            imagesList.clear()
        }
        viewModelScope.launch {
            appRepository.getUserImagesApi(token, page.toString())
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        imagesList.addAll(response.body()?.items ?: arrayListOf())
                        if (response.body()?.next.isNullOrBlank()) {

                            _imagesData.postValue(imagesList)
                        } else {
                            val query = Uri.parse(response.body()?.next).getQueryParameter("page")
                            Log.d("TAG:", "PAGE: $query")
                            imagesList(token, query!!.toInt())
                        }
                    } else {
                        _imagesError.postValue(
                            ErrorUtils.parseError<UserImagesResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun uploadInstaImages(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.sendInstaImagesApi(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }

                    if (response.isSuccessful) {
                        _sendInstaImagesData.postValue(response.body())
                    } else {
                        _sendInstaImagesError.postValue(
                            ErrorUtils.parseError<InstagramUploadResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun updateDeviceToken(token: String, jsonObject: JsonObject) {

        viewModelScope.launch {
            appRepository.updateDeviceToken(token, jsonObject)
                .catch { e ->

                }
                .flowOn(Dispatchers.IO)
                .collect { response ->

                    if (response.isSuccessful) {

                    } else {


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
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _settingsData.postValue(response.body())
                    } else {
                        _error.postValue(
                            ErrorUtils.parseError<CommonResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun sendProfileVerification(photo: MultipartBody.Part, token: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.sendProfileVerification(photo = photo, token = token)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _imageVerification.postValue(response.body())
                    } else {
                        _imageVerificationError.postValue(
                            ErrorUtils.parseError<ImageModerationResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }


    fun sendChatImage(photo: MultipartBody.Part) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.uploadChatImage(photo = photo)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _chatImageUrl.postValue(response.body())
                    } else {
                        _chatImageError.postValue(
                            ErrorUtils.parseError<ImageModerationResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun updateTravelLocation(token: String, jsonObject: JsonObject) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.updateTravelLocation(token, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _travelData.postValue(response.body())
                    } else {
                        _travelError.postValue(
                            ErrorUtils.parseError<CommonResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun travelLocationApi(jsonObject: JsonObject) {
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.travelLocationApi(headers, jsonObject)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _travelLocationData.postValue(response.body())
                    } else {
                        _travelLocationError.postValue(
                            ErrorUtils.parseError<LocationModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun sendImageGestureVerification(
        requestBodyUserId: RequestBody,
        requestBodyGender: RequestBody,
        photo: MultipartBody.Part
    ) {
        _showLoading.postValue(false)
        viewModelScope.launch {
            appRepository.sendImageGestureVerification(
                requestBodyUserId = requestBodyUserId,
                requestBodyGender = requestBodyGender,
                photo = photo
            )
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                    Log.e("GESTURE_VERIFICATION", "RESPONSE ERROR: $e")
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    when {
                        response.isSuccessful -> {
                            _profileVerificationData.postValue(response.body())
                            Log.e("GESTURE_VERIFICATION", "RESPONSE SUCCESSFUL: ${response.body()}")
                            //                        _imageVerification.postValue(response.body())
                        }
                        response.code() == 504 -> {
                            AppUtils.openFailNetworkDialog(context)
                        }
                        else -> {
                            var errorResponse = JSONObject(response.errorBody()?.string())
                            if (errorResponse.has("error"))
                                _profileVerificationData.postValue(
                                    GestureVerificationModel(
                                        errorResponse.getString("error")
                                    )
                                )

                            Log.e("GESTURE_VERIFICATION", "RESPONSE UNSUCCESSFUL: $response")
                        }
                    }
                }
        }
    }

    fun imageGestureVerification(
        requestBodyGender: RequestBody,
        photo: MultipartBody.Part
    ) {
        val headers = HashMap<String, String>()
        headers.put("language", BuildConfig.Language)
        headers.put("app_version", BuildConfig.VERSION_NAME)
        headers.put("device_type", BuildConfig.DEVICE_TYPE)
        headers.put("device_id", AppUtils.getDeviceId(AppController.context!!))
        headers.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.gestureProfileVerification(headers, requestBodyGender = requestBodyGender, photo = photo)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                    Log.e("GESTURE_VERIFICATION", "RESPONSE ERROR: $e")
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    when {
                        response.isSuccessful -> {
                            _profileGestureVerificationData.postValue(response.body())
                            Log.e("GESTURE_VERIFICATION", "RESPONSE SUCCESSFUL: ${response.body()}")
                            //                        _imageVerification.postValue(response.body())
                        }
                        response.code() == 504 -> {
                            AppUtils.openFailNetworkDialog(context)
                        }
                        else -> {
                          /*  var errorResponse = JSONObject(response.errorBody()?.string())
                            if (errorResponse.has("error"))*/

                                //Log.e("GESTURE_VERIFICATION_ERROR", "RESPONSE SUCCESSFUL: ${errorResponse}")

                                _gestureError.postValue(
                                    ErrorUtils.parseError<GestureVerificationProfileModel>(
                                        response
                                    )
                                )


                            Log.e("GESTURE_VERIFICATION", "RESPONSE UNSUCCESSFUL: $response")
                        }
                    }
                }
        }
    }

    fun getAddressFromLatLong(key: String, latlng: String) {
        //       _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.getAddressFromLatLong(key, latlng)
                .catch { e ->
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@UserProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
//                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@UserProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _addressData.postValue(response.body())
                    } else {
                        _addressError.postValue(
                            ErrorUtils.parseError<CommonResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }


}