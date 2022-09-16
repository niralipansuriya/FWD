package com.swipefwd.ui.swiping.dater_both

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.swipefwd.data.source.remote.api.ErrorUtils
import com.swipefwd.data.models.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SwipeProfileViewModel(context: Context, private val appRepository: AppRepository) :
    BaseViewModel(context) {
    private val fwdDataStore by lazy { InternalAppDataStore(context) }

    val getGender = fwdDataStore.getString(PreferenceKeys.PREF_GENDER)
    val getRecoveryEmail = fwdDataStore.getString(PreferenceKeys.PREF_RECOVERY_EMAIL)
    val getEmailDialogDisplayDate = fwdDataStore.getString(PreferenceKeys.SHOW_EMAIL_REMINDER_DATE)
    val getVerifyDialogDisplayDate = fwdDataStore.getString(PreferenceKeys.SHOW_VERIFICATION_REMINDER_DATE)
    // val getCreateDate = fwdDataStore.getString(PreferenceKeys.PROFILE_CREATE_DATE)
    val getIsProfileVerified = fwdDataStore.getBoolean(PreferenceKeys.IS_PROFILE_VERIFIED)

    val getAuthToken = fwdDataStore.getString(PreferenceKeys.PREF_TOKEN)

    val getUserId = fwdDataStore.getInt(PreferenceKeys.PREF_USER_ID)

    val getAccountType = fwdDataStore.getString(PreferenceKeys.PREF_ACCOUNT_TYPE)

    val isInterested = fwdDataStore.getBoolean(PreferenceKeys.PREF_INTERESTED)

    val isNotInterested = fwdDataStore.getBoolean(PreferenceKeys.PREF_NOT_INTERESTED)

    val getIsDarkModeTip = fwdDataStore.getBoolean(PreferenceKeys.PREF_DARK_MODE_TOOL_TIP)

    val getProfileImage = fwdDataStore.getString(PreferenceKeys.PREF_PROFILE_IMAGE)

    val phoneNumber = fwdDataStore.getString(PreferenceKeys.PREF_PHONE_NUMBER)

    val getFirstName = fwdDataStore.getString(PreferenceKeys.PREF_FIRST_NAME)

    val getShowTip = fwdDataStore.getBoolean(PreferenceKeys.PREF_FWD_TOOL_TIP)
    val getGreenShowTip = fwdDataStore.getBoolean(PreferenceKeys.PREF_FWD_GREEN_TOOL_TIP)

    val isOnboardingTutorialShown = fwdDataStore.getBoolean(PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED)

    fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            fwdDataStore.savePreference(key, value)
        }
    }

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    private var _error = MutableLiveData<CommonResponseModel>()
    var error: LiveData<CommonResponseModel> = _error

    private var _tribeData =
        MutableLiveData<DaterConnectionResponseModel>()
    var tribeData: LiveData<DaterConnectionResponseModel> =
        _tribeData

    private var _tribeError =
        MutableLiveData<DaterConnectionResponseModel>()
    var tribeError: LiveData<DaterConnectionResponseModel> =
        _tribeError

    private var _swipeProfileMatchesData = MutableLiveData<SwipingProfileMatchesModel>()
    var swipeProfileMatchesData: LiveData<SwipingProfileMatchesModel> = _swipeProfileMatchesData

    private var _swipeProfileData = MutableLiveData<ArrayList<SwipingCustomModel>>()
    var swipeProfileData: LiveData<ArrayList<SwipingCustomModel>> = _swipeProfileData


    private var _swipeUserProfileData = MutableLiveData<ArrayList<SwipingProfileModel>>()
    var swipeUserProfileData: LiveData<ArrayList<SwipingProfileModel>> = _swipeUserProfileData


    private val swipingProfileList = ArrayList<SwipingProfileModel>()
    private val swipingList = ArrayList<SwipingCustomModel>()
    private var fbProfileList = ArrayList<SwipingProfileModel>()

    private var _swipeLeftRightData = MutableLiveData<SwipeLeftRightResponseModel>()
    var swipeLeftRightData: LiveData<SwipeLeftRightResponseModel> = _swipeLeftRightData

    private var _swipeLeftRightError = MutableLiveData<SwipeLeftRightResponseModel>()
    var swipeLeftRightError: LiveData<SwipeLeftRightResponseModel> = _swipeLeftRightError

    private var _facebookData = MutableLiveData<ArrayList<SwipingProfileModel>>()
    var facebookData: LiveData<ArrayList<SwipingProfileModel>> = _facebookData

    private var _facebookError = MutableLiveData<FacebookMutualFriendsListModel>()
    var facebookError: LiveData<FacebookMutualFriendsListModel> = _facebookError


    private var _matchesData =
        MutableLiveData<MatchesResponseModel>()
    var matchesData: LiveData<MatchesResponseModel> =
        _matchesData


    private var _matchesError =
        MutableLiveData<MatchesResponseModel>()
    var matchesError: LiveData<MatchesResponseModel> =
        _matchesError

    private var _matchesBool =
        MutableLiveData<Boolean>()
    var matchesBool: LiveData<Boolean> =
        _matchesBool


    private fun setModel(
        it: SwipingProfileMatchesModel.SwipeLeftRightResponseModelItem,
        isInDarkMode: Boolean
    ) {
        val model = SwipingProfileModel.ProfileModel()
        model.bio = it.bio
        if (!it.castes.isNullOrEmpty()) {
            model.caste = it.castes?.get(0)?.name
        }

        if (!it.childrens.isNullOrEmpty()) {
            model.children = it.childrens?.get(0)?.name
        }

        model.dob = it.dob
        if (!it.educations.isNullOrEmpty()) {
            model.education = it.educations?.get(0)?.name
        }

        model.firstName = it.firstName
        model.graduationYear = it.graduationYear ?: ""
        model.height = it.height
        model.height_in = it.heightIn
        model.id = it.refUser
        model.instagramId = it.instagramId
        model.instagramName = it.instagramName
        model.instagramPosts = it.instagramPosts
        model.institution = it.institution

        if (!it.instagram.isNullOrEmpty()) {
            it.instagram!!.forEach {
                if (it != null) {
                    model.images.instagram.add(it)
                }
            }
        }
        if (!it.images.isNullOrEmpty()) {
            it.images!!.forEach {
                if (it != null) {
                    model.images.uploads.add(it)
                }
            }
        }
        if (!it.language.isNullOrEmpty()) {
            it.language?.forEach {
                model.languages?.add(it.name)
            }
        }
        model.lastName = it.lastName
        model.latitude = it.latitude
        model.location = it.location
        model.longitude = it.longitude
        model.occupationCompany = it.occupationCompany
        model.occupationTitle = it.occupationTitle
        model.phoneNumber = it.phoneNumber
        model.profilePictureUrl = it.profilePictureUrl
        model.recoveryEmail = it.recoveryEmail
        model.relationshipInterest = it.relationship?.firstOrNull()?.name
        model.religion = it.religions?.firstOrNull()?.name
        model.smoking = it.smokings?.firstOrNull()?.name
        model.gender = it.gender
        model.sunsign = it.sunsign_name
        model.sunsign_url = it.sunsign_url
        model.vaccinationStatus = it.vaccines?.firstOrNull()?.name
        model.children = it.childrens?.firstOrNull()?.name
        val greenProfileModel =
            ArrayList<SwipingProfileModel.ProfileModel>()
        if (!it.suggestedBy.isNullOrEmpty()) {
            it.suggestedBy?.forEach {
                val suggestedModel = SwipingProfileModel.ProfileModel()
                suggestedModel.firstName = it.firstName
                suggestedModel.lastName = it.lastName
                suggestedModel.profilePictureUrl = it.profilePictureUrl
                suggestedModel.id = it.id
                //suggestedModel.images=
                greenProfileModel.add(suggestedModel)
            }
        }
        if (!it.mutualFriends.isNullOrEmpty()) {
            it.mutualFriends!!.forEach {
                model.mutualFriends!!.add(
                    SwipingProfileModel.ProfileModel.MutualFriend(
                        it.image,
                        it.name
                    )
                )
            }
        }

        var type = ""
        if (it.profileType == "basic" || it.profileType == "liked" || it.profileType == "superliked ") {
            type = AppConstants.PROFILE_NORMAL
        } else if (it.profileType == "green") {
            type = AppConstants.PROFILE_GREEN
        } else if (it.profileType == "black" || it.profileType == "blurple") {
            type = AppConstants.PROFILE_BLURPLE
        }
        if ((it.profileType.equals("basic") || it.profileType.equals("blurple") || it.profileType.equals(
                "black"
            ) || it.profileType.equals(
                "green"
            ) || it.profileType.equals("liked") || it.profileType.equals("superliked")) && !isInDarkMode
        ) {

            swipingList.add(
                SwipingCustomModel(
                    connectorIds = ArrayList(),
                    id = it.id!!,
                    profileName = type,
                    userProfileModel = model,
                    connectorProfileModel = greenProfileModel,
                    hasLiked = it.liked!!,
                    hasSuperLiked = it.superliked!!
                )
            )
        } else if ((it.profileType.equals("basic") || it.profileType.equals("black")) && isInDarkMode) {
            swipingList.add(
                SwipingCustomModel(
                    connectorIds = ArrayList(),
                    id = it.id!!,
                    profileName = type,
                    userProfileModel = model,
                    connectorProfileModel = greenProfileModel,
                    hasLiked = it.liked!!,
                    hasSuperLiked = it.superliked!!
                )
            )
        }


    }


    private fun setProfileModel(
        it: SwipingProfileMatchesModel.SwipeLeftRightResponseModelItem,
        isInDarkMode: Boolean
    ) {
        val model = SwipingProfileModel.ProfileModel()
        model.bio = it.bio
        if (!it.castes.isNullOrEmpty()) {
            model.caste = it.castes?.get(0)?.name
        }

        if (!it.childrens.isNullOrEmpty()) {
            model.children = it.childrens?.get(0)?.name
        }

        model.dob = it.dob
        if (!it.educations.isNullOrEmpty()) {
            model.education = it.educations?.get(0)?.name
        }

        model.firstName = it.firstName
        model.graduationYear = it.graduationYear ?: ""
        model.height = it.height
        model.height_in = it.heightIn
        model.id = it.refUser
        model.instagramId = it.instagramId
        model.instagramName = it.instagramName
        model.instagramPosts = it.instagramPosts
        model.institution = it.institution

        if (!it.instagram.isNullOrEmpty()) {
            it.instagram!!.forEach {
                if (it != null) {
                    model.images.instagram.add(it)
                }
            }
        }
        if (!it.images.isNullOrEmpty()) {
            it.images!!.forEach {
                if (it != null) {
                    model.images.uploads.add(it)
                }
            }
        }
        if (!it.language.isNullOrEmpty()) {
            it.language?.forEach {
                model.languages?.add(it.name)
            }
        }
        model.lastName = it.lastName
        model.latitude = it.latitude
        model.location = it.location
        model.longitude = it.longitude
        model.occupationCompany = it.occupationCompany
        model.occupationTitle = it.occupationTitle
        model.phoneNumber = it.phoneNumber
        model.profilePictureUrl = it.profilePictureUrl
        model.recoveryEmail = it.recoveryEmail
        model.relationshipInterest = it.relationship?.firstOrNull()?.name
        model.religion = it.religions?.firstOrNull()?.name
        model.smoking = it.smokings?.firstOrNull()?.name
        model.gender = it.gender
        model.sunsign = it.sunsign_name
        model.vaccinationStatus = it.vaccines?.firstOrNull()?.name
        model.children = it.childrens?.firstOrNull()?.name
        val greenProfileModel =
            ArrayList<SwipingProfileModel.ProfileModel>()
        if (!it.suggestedBy.isNullOrEmpty()) {
            it.suggestedBy?.forEach {
                val suggestedModel = SwipingProfileModel.ProfileModel()
                suggestedModel.firstName = it.firstName
                suggestedModel.lastName = it.lastName
                suggestedModel.profilePictureUrl = it.profilePictureUrl
                suggestedModel.id = it.id
                //suggestedModel.images=
                greenProfileModel.add(suggestedModel)
            }
        }
        if (!it.mutualFriends.isNullOrEmpty()) {
            it.mutualFriends!!.forEach {
                model.mutualFriends!!.add(
                    SwipingProfileModel.ProfileModel.MutualFriend(
                        it.image,
                        it.name
                    )
                )
            }
        }

        var type = ""
        if (it.profileType == "basic" || it.profileType == "liked" || it.profileType == "superliked ") {
            type = AppConstants.PROFILE_NORMAL
        } else if (it.profileType == "green") {
            type = AppConstants.PROFILE_GREEN
        } else if (it.profileType == "black" || it.profileType == "blurple") {
            type = AppConstants.PROFILE_BLURPLE
        }
        swipingProfileList.add(SwipingProfileModel(model, "", true))

    }

    fun getSwipingProfileIds(userId: Int?, isInDarkMode: Boolean, token: String) {
        if (isInDarkMode) {
            _showLoading.postValue(true)
        }
        viewModelScope.launch {
            appRepository.swipingMatchesApi(userId, token)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SwipeProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SwipeProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        _swipeProfileMatchesData.postValue(response.body())
                        if (responseData!!.isNotEmpty()) {
                            responseData.forEach {
                                setModel(it, isInDarkMode)
                            }
                            _swipeProfileData.postValue(swipingList)

                        }
//                        else {
//                            _showLoading.postValue(false)
//                        }


                    } else {
                        _showLoading.postValue(false)
                        _error.postValue(
                            ErrorUtils.parseError<CommonResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }


    private fun getSwipingProfileFromIds(idList: ArrayList<SwipingCustomModel>, token: String) {
        viewModelScope.launch {
            try {
                idList.map {
                    appRepository.swipingProfileApi(it.id, token)
                }.asFlow()
                    .flattenMerge().onCompletion {
                        if (swipingList.isNotEmpty()) {
                            swipingList.onEach { swipingCustomModel ->
                                swipingProfileList.find {
                                    it.data?.id == swipingCustomModel.id
                                }?.data?.let {
                                    swipingCustomModel.userProfileModel = it
                                }
                            }
                            _showLoading.postValue(false)
                            _swipeProfileData.postValue(swipingList)
                        } else {
                            _showLoading.postValue(false)
                            _swipeUserProfileData.postValue(swipingProfileList)
                        }
                    }
                    .collect {
                        it.body()?.let { profileModel -> swipingProfileList.add(profileModel) }
                    }
            } catch (e: Exception) {
                _showLoading.postValue(false)
                e.printStackTrace()
            }
        }
    }

    fun swipeUserLeftRight(jsonObject: JsonObject, token: String) {
        viewModelScope.launch {
            appRepository.swipingLeftRightApi(jsonObject, token)
                .catch { e ->
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SwipeProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SwipeProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _swipeLeftRightData.postValue(response.body())
                    } else {
                        _swipeLeftRightError.postValue(
                            ErrorUtils.parseError<SwipeLeftRightResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun swipeUserRewind(jsonObject: JsonObject, token: String, userId: Int) {
        viewModelScope.launch {
            appRepository.swipingRewindApi(jsonObject, token, userId)
                .catch { e ->
                    _errorMessage.postValue(e.localizedMessage)
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SwipeProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        _swipeLeftRightData.postValue(response.body())
                    } else {
                        _swipeLeftRightError.postValue(
                            ErrorUtils.parseError<SwipeLeftRightResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun matchesListApi(id: Int, token: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.matchesListApi(id, token)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SwipeProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.code() == 401) {
                        AppUtils.callLogout(this@SwipeProfileViewModel)
                        return@collect
                    }
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        if (!responseData.isNullOrEmpty()) {
                            responseData.forEach {
                                setProfileModel(it, false)
                            }
                            _swipeUserProfileData.postValue(swipingProfileList)
                        } else {
                            _matchesBool.postValue(false)
                        }
                    } else {
                        _matchesError.postValue(
                            ErrorUtils.parseError<MatchesResponseModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun getFacebookIdsListApi(userId: Int, daterId: Int, token: String) {
        viewModelScope.launch {
            appRepository.getFacebookListIds(userId, daterId, token)
                .catch { e ->
                    _errorMessage.postValue(
                        AppUtils.setApiMessage(
                            e.localizedMessage,
                            this@SwipeProfileViewModel
                        )
                    )
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    if (response.isSuccessful) {
                        val friendsModel = response.body()?.data
                        if (friendsModel != null) {
                            val mutualFriendList = friendsModel.mutual
                            try {
                                mutualFriendList?.let {
                                    if (it.isNotEmpty()) {
                                        it.asFlow()
                                            .catch { e ->
                                                e.printStackTrace()
                                            }
                                            .flowOn(Dispatchers.IO)
                                            .transform { id ->
                                                appRepository.swipingProfileApi(id!!, token)
                                                    .collect { profile ->
                                                        emit(profile)
                                                    }
                                            }
                                            .onCompletion {
                                                _facebookData.postValue(fbProfileList)
                                            }
                                            .collect {
                                                it.body()?.let { profileModel ->
                                                    fbProfileList.add(profileModel)
                                                }
                                            }
                                    }
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    } else {
                        _facebookError.postValue(
                            ErrorUtils.parseError<FacebookMutualFriendsListModel>(
                                response
                            )
                        )
                    }
                }
        }
    }

    fun tribeConnectionListApi(token: String) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            appRepository.tribeConnectionListApi(token)
                .catch { e ->
                    _showLoading.postValue(false)
                    _errorMessage.postValue(AppUtils.setApiMessage(e.localizedMessage,this@SwipeProfileViewModel))
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    _showLoading.postValue(false)
                    if (response.isSuccessful) {
                        _tribeData.postValue(response.body())
                    } else {
                        if (response.code() == 401) {
                            AppUtils.callLogout(this@SwipeProfileViewModel)
                        } else {
                            _tribeError.postValue(
                                ErrorUtils.parseError<DaterConnectionResponseModel>(
                                    response
                                )
                            )
                        }

                    }
                }
        }
    }
}