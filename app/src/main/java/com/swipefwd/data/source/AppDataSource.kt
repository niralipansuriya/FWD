package com.swipefwd.data.source

import BasicProfileDetails
import BasicProfileUpload
import OTPModel
import ProfilePhotosModel
import com.google.gson.JsonObject
import com.swipefwd.data.models.*
import com.swipefwd.data.models.walletModels.GetAccessTokenModel
import com.swipefwd.data.models.walletModels.GetOperatorByIsoModel
import com.swipefwd.data.models.walletModels.GoogleAddressModel
import com.swipefwd.data.models.walletModels.MakeRechargeModel
import com.swipefwd.xmpp.ChatImageModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File

interface AppDataSource {
    suspend fun linkedInTokenApi(code: String): Response<LinkedInTokenModel>
    suspend fun linkedInProfileApi(token: String): Response<LinkedInProfileModel>
    suspend fun linkedInEmailApi(token: String): Response<LinkedInEmailModel>

    /** #######################################################################**/


    suspend fun loginUserApi(jsonObject: JsonObject): Flow<Response<LoginResponseModel>>
    suspend fun getEducationApi(
        jsonObject: JsonObject
    ): Flow<Response<EducationDetailModel>>

    suspend fun getLanguageApi( jsonObject: JsonObject): Flow<Response<LanguageDataModel>>
    suspend fun getAstrologicalSign( jsonObject: JsonObject): Flow<Response<AstrologicalModel>>
    suspend fun deleteUserAccount(headers: HashMap<String, String>): Flow<Response<DeleteAccountModel>>
    suspend fun getRelationshipApi( jsonObject: JsonObject): Flow<Response<RelationshipModel>>
    suspend fun getChildrenApi( jsonObject: JsonObject): Flow<Response<ChildrenModel>>
    suspend fun getVaccinationApi( jsonObject: JsonObject): Flow<Response<VaccinationModel>>
    suspend fun getReligionApi( jsonObject: JsonObject): Flow<Response<ReligionModel>>
    suspend fun getSmokingApi( jsonObject: JsonObject): Flow<Response<SmokingDataModel>>
    suspend fun recoverAccountEmailApi(jsonObject: JsonObject): Flow<Response<RecoverAccountModel>>
    suspend fun registerUserApi(jsonObject: JsonObject): Flow<Response<LoginResponseModel>>

    // suspend fun getOtpApi(jsonObject: JsonObject): Flow<Response<OTPModel>>
    suspend fun getOtpApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<OTPModel>>

    suspend fun getDateList(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<DateGetModel>>

    suspend fun updateDateList(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<UpdateDateModel>>

    suspend fun setEmailUserApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<SetEmailModel>>

    suspend fun verifyOtpEmail(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<OTPModel>>

    suspend fun sendOtpEmail(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<ResendOtp>>

    suspend fun setUserTypeApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<UserTypeModel>>

    suspend fun resendOtp(headers: HashMap<String, String>): Flow<Response<ResendOtp>>
    suspend fun sendBasicProfileData(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<BasicProfileDetails>>

    suspend fun setDetailedProfile(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<DetailedProfileModel>>

    suspend fun updateMobile(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<OTPModel>>

    suspend fun socialLogin(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<OTPModel>>

    //suspend fun verifyOtpApi(jsonObject: JsonObject): Flow<Response<OTPModel>>
    suspend fun verifyOtpApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<OTPModel>>

    suspend fun setLocation(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<LocationModel>>

    suspend fun travelLocationApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<LocationModel>>

    suspend fun tribeConnectionListApi(header: HashMap<String, String>): Flow<Response<TribeDaterConnectionsResponseModel>>
    suspend fun sendContactApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<ContactListResponseModel>>

    suspend fun inviteConnector(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<InviteConnectorResponseModel>>

    suspend fun languageListApi(
        token: String,
        page: String
    ): Flow<Response<LanguageListModel>>

    suspend fun educationLevelListApi(
        token: String,
        page: String
    ): Flow<Response<EducationLevelListModel>>

    suspend fun astrologySignListApi(
        token: String,
        page: String
    ): Flow<Response<AstroSignListModel>>

    suspend fun castListApi(
        token: String, page: String
    ): Flow<Response<CastListModel>>

    suspend fun childrenListApi(
        token: String, page: String
    ): Flow<Response<ChildrenListModel>>

    suspend fun religionListApi(
        token: String, page: String
    ): Flow<Response<ReligionListModel>>

    suspend fun smokingListApi(
        token: String, page: String
    ): Flow<Response<SmokingListModel>>

    suspend fun relationshipListApi(
        token: String, page: String
    ): Flow<Response<RelationshipListModel>>

    suspend fun covidVaccineListApi(
        token: String, page: String
    ): Flow<Response<CovidVaccineListModel>>

    suspend fun userProfileSubmitApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun deleteAccount(
        token: String, userId: Int, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun userProfilePictureApi(
        token: String, isProfile: String, imageFile: File
    ): Flow<Response<CommonResponseModel>>

    /* suspend fun userProfilePictureDeleteApi(
         token: String, id: Int
     ): Flow<Response<CommonResponseModel>>*/

    suspend fun userProfilePictureDeleteApi(
        token: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<ProfilePhotosModel.DataProfile.UserPhotos>>

    suspend fun userPictureDeleteAsync(
        token: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<ProfilePhotosModel.DataProfile.UserPhotos>>

    suspend fun instituteListApi(url: String): Flow<Response<InstitutesModel>>

    suspend fun preferenceRelationshipListApi(
        token: String, page: String
    ): Flow<Response<RelationshipListModel>>

    /*  suspend fun userPreferenceSubmitApi(
          token: String, jsonObject: JsonObject
      ): Flow<Response<CommonResponseModel>>*/
    suspend fun userPreferenceSubmitApi(
        headers: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun userAdvancePreferenceSubmitApi(
        headers: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun instagramTokenApi(code: String): Flow<Response<InstagramTokenModel>>

    suspend fun instagramProfileAPi(
        userId: Long,
        token: String
    ): Flow<Response<InstagramProfileModel>>

    suspend fun instagramMediaApi(
        mediaId: String,
        token: String
    ): Flow<Response<InstagramMediaModel>>

    suspend fun userProfileUpdateApi(
        token: String, id: Int, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun userPreferenceUpdateApi(
        token: String, id: Int, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun recoverEmailApi(
        headers: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>


    suspend fun userBasicProfileSet(
        headers: HashMap<String, String>, file: File
    ): Flow<Response<BasicProfileUpload>>

    suspend fun userProfilePhotosUpload(
        headers: HashMap<String, String>, position: String, file: File
    ): Flow<Response<ProfilePhotosModel>>

    fun refreshToken(
        headers: HashMap<String, String>
    ): Flow<Response<RefreshTokenModel>>


    fun callWebservice(
        headers: HashMap<String, String>
    ): Flow<Response<RefreshTokenModel>>

    suspend fun recoverEmailAddress(
        headers: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun recoverAccountApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun sendContactApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<FwdContactListResponseModel>>

    suspend fun inviteContactApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun tribeConnectionApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun tribeConnectionListApi(
        token: String
    ): Flow<Response<DaterConnectionResponseModel>>


    suspend fun matchesListApi(
        id: Int, token: String
    ): Flow<Response<SwipingProfileMatchesModel>>

    suspend fun changeUserTypeApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun swipingMatchesApi(
        id: Int?, token: String
    ): Flow<Response<SwipingProfileMatchesModel>>

    suspend fun swipingProfileApi(
        id: Int, token: String
    ): Flow<Response<SwipingProfileModel>>

    suspend fun swipingLeftRightApi(
        jsonObject: JsonObject, token: String
    ): Flow<Response<SwipeLeftRightResponseModel>>

    suspend fun swipingRewindApi(
        jsonObject: JsonObject, token: String, userId: Int
    ): Flow<Response<SwipeLeftRightResponseModel>>

    suspend fun getUserProfileApi(
        token: String,
        id: Int
    ): Flow<Response<UserProfileResponseModel>>

    suspend fun getUserImagesApi(
        token: String, page: String
    ): Flow<Response<UserImagesResponseModel>>

    suspend fun getUserPreferenceApi(
        token: String,
        id: Int
    ): Flow<Response<UserPreferenceResponseModel>>

    suspend fun userSettingsUpdateApi(
        token: String,
        id: Int,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun getUserSettingsApi(
        token: String,
        id: Int
    ): Flow<Response<UserSettingsResponseModel>>

    suspend fun sendInstaImagesApi(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<InstagramUploadResponseModel>>

    suspend fun updateDeviceToken(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<DeviceTokenResponse>>

    suspend fun removeTribeDaterApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun suggestionProfileApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<InviteModel>>

    suspend fun userPlansApi(
        token: String
    ): Flow<Response<SettingSubscriptionModel>>

    suspend fun planPurchaseApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun popupShowApi(
        token: String, id: Int
    ): Flow<Response<CommonResponseModel>>

    suspend fun boosterPurchaseApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun userLogoutApi(
        token: String
    ): Flow<Response<CommonResponseModel>>

    suspend fun getActivePendingRequestApi(
        token: String
    ): Flow<Response<ActiveExpireRequestListModel>>

    suspend fun addActivePendingRequestApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun getActivityPendingRequestApi(
        token: String
    ): Flow<Response<ActiveExpireRequestListModel>>

    suspend fun sendFacebookIdApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    /**
     * Wallet API
     */
    suspend fun getAccessTokenApi(jsonObject: JsonObject): Flow<Response<GetAccessTokenModel>>
    suspend fun getOperatorByIso(
        token: String,
        isoCode: String,
        includeBundles: Boolean,
        includeData: Boolean,
        includePin: Boolean,
        suggestedAmounts: Boolean, suggestedAmountsMap: Boolean
    ): Flow<Response<GetOperatorByIsoModel>>

    suspend fun makeRecharge(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<MakeRechargeModel>>

    suspend fun getFacebookListIds(
        userId: Int,
        daterId: Int, token: String
    ): Flow<Response<FacebookMutualFriendsListModel>>

    suspend fun sendProfileVerification(
        photo: MultipartBody.Part,
        token: String
    ): Flow<Response<ImageModerationResponseModel>>

    suspend fun uploadChatImage(
        photo: MultipartBody.Part
    ): Flow<Response<ChatImageModel>>

    suspend fun sendReceipt(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun updateTravelLocation(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>>

    suspend fun sendImageGestureVerification(
        requestBodyUserId: RequestBody,
        requestBodyGender: RequestBody,
        photo: MultipartBody.Part
    ): Flow<Response<GestureVerificationModel>>

    suspend fun gestureProfileVerification(
        headers: HashMap<String, String>,
        requestBodyGender: RequestBody,
        photo: MultipartBody.Part
    ): Flow<Response<GestureVerificationProfileModel>>

    suspend fun getAddressFromLatLong(
        key: String,
        latlng: String
    ): Flow<Response<GoogleAddressModel>>

    suspend fun getInactivity(token: String): Flow<Response<InactivityModel>>
}