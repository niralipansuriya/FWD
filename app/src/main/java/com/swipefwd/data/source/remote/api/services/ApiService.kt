package com.swipefwd.data.source.remote.api.services

import BasicProfileDetails
import BasicProfileUpload
import OTPModel
import ProfilePhotosModel
import com.google.gson.JsonObject
import com.swipefwd.data.models.*
import com.swipefwd.data.models.walletModels.GoogleAddressModel
import com.swipefwd.data.source.remote.api.*
import com.swipefwd.xmpp.ChatImageModel
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST(LOGIN_URL)
    fun loginUserAsync(
        @Body jsonObject: JsonObject
    ): Deferred<Response<LoginResponseModel>>

    @POST(REGISTER_URL)
    fun registerUserAsync(
        @Body jsonObject: JsonObject
    ): Deferred<Response<LoginResponseModel>>

    @POST(SIGNUP_OR_SIGNIN)
    fun getOtpAsync(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<OTPModel>>

    @POST(UPDAte_MOBILE)
    fun updateMobile(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<OTPModel>>

    @POST(SOCIAL_SIGNUP)
    fun getSocialsignUp(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<OTPModel>>

    /* @POST(USER_PREFERENCE_SUBMIT_URL)
       fun userPreferenceSubmitAsync(
           @Header("Authorization") Authorization: String,
           @Body jsonObject: JsonObject
       ): Deferred<Response<CommonResponseModel>>
   */
    @POST(USER_PREFERENCE)
    fun userPreferenceSubmitAsync(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(USER_ADVANCE_PREFERENCE)
    fun userAdvancePreferenceSubmitAsync(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>


    @POST(BASIC_PROFILE_SET)
    fun sendBasicProfileData(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<BasicProfileDetails>>


    @POST(DETAILED_PROFILE_SET)
    fun setDetailedProfile(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<DetailedProfileModel>>


    @Multipart
    @POST(GET_POST_USER_PROFILE_PICTURE_URL)
    fun userProfilePictureAsync(
        @Header("Authorization") Authorization: String,
        @Part("is_profile") is_profile: RequestBody,
        @Part image_file: MultipartBody.Part? = null
    ): Deferred<Response<CommonResponseModel>>

    @Multipart
    @POST(PROFILE_PIC_UPLOAD)
    fun userBasicProfileSet(
        @HeaderMap headers: Map<String, String>,
        @Part profile_pic: MultipartBody.Part? = null,
    ): Deferred<Response<BasicProfileUpload>>


    @Multipart
    @POST(UPLOAD_DETAILED_PROFILE_PHOTOS)
    fun uplodProfilePhotos(
        @HeaderMap headers: Map<String, String>,
        @Part("position") positionPhotos: RequestBody,
        @Part profile_pic: MultipartBody.Part? = null,
    ): Deferred<Response<ProfilePhotosModel>>

/*    fun getOtpAsync(
        @Header("language") language :String,@Header("app_version") app_version :String,@Header("device_type") device_type :String,
        @Header("device_id") device_id :String,@Header("auth_token")  auth_token :String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<OTPModel>>*/

/*    @POST(GET_OTP_URL)
    fun getOtpAsync(
        @Body jsonObject: JsonObject
    ): Deferred<Response<OTPModel>>*/

    /*    @POST(VERIFY_OTP_URL)
        fun verifyOtpAsync(
            @Body jsonObject: JsonObject
        ): Deferred<Response<OTPModel>>
        */
    @POST(OTP_VERIFY)
    fun verifyOtpAsync(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<OTPModel>>

    @POST(SET_EMAIL)
    fun setEmailUserApi(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<SetEmailModel>>

    @POST(VERIFY_OTP_EMAIL)
    fun verifyOtpEmail(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<OTPModel>>

    @POST(SEND_OTP_EMAIL)
    fun sendOtpEmail(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<ResendOtp>>

    @POST(DATE_LIST)
    fun getDateList(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<DateGetModel>>

    @POST(UPDATE_DATE_LIST)
    fun updateDateList(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<UpdateDateModel>>

    @POST(SET_USER_TYPE)
    fun setUserTypeApi(
        @HeaderMap headers: Map<String, String>, @Body jsonObject: JsonObject
    ): Deferred<Response<UserTypeModel>>

    @POST(RESEND_OTP_API)
    fun resendOtp(
        @HeaderMap headers: Map<String, String>,
    ): Deferred<Response<ResendOtp>>

    @POST(SET_LOCATION)
    fun setLocation(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<LocationModel>>

    @GET(LANGUAGE_URL)
    fun languageListAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<LanguageListModel>>

    @POST(LANGUAGE_API)
    fun getLanguageApi(
        @Body jsonObject: JsonObject
    ): Deferred<Response<LanguageDataModel>>

    @GET(EDUCATION_URL)
    fun educationLevelListAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<EducationLevelListModel>>


    @POST(GET_EDUCATION)
    fun getEducation(
        @Body jsonObject: JsonObject

    ): Deferred<Response<EducationDetailModel>>

    @POST(ASTROLOGICAL_SIGN)
    fun getAstrologicalSign(
        @Body jsonObject: JsonObject

    ): Deferred<Response<AstrologicalModel>>

    @POST(DELETE_USER)
    fun deleteUserAccount(
        @HeaderMap headers: Map<String, String>,
    ): Deferred<Response<DeleteAccountModel>>


    @POST(RELATIONSHIP_STATUS)
    fun getRelationship(
        @Body jsonObject: JsonObject
    ): Deferred<Response<RelationshipModel>>

    @POST(CHILDREN_API)
    fun getChildren(
        @Body jsonObject: JsonObject

    ): Deferred<Response<ChildrenModel>>


    @POST(RELIGION_API)
    fun getReligion(
        @Body jsonObject: JsonObject

    ): Deferred<Response<ReligionModel>>

    @POST(SMOKING_API)
    fun getSmokingApi(
        @Body jsonObject: JsonObject

    ): Deferred<Response<SmokingDataModel>>

    @POST(VACCINATION_API)
    fun getVaccination(
        @Body jsonObject: JsonObject

    ): Deferred<Response<VaccinationModel>>

    @GET(ASTROLOGY_SIGN_URL)
    fun astrologySignListAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<AstroSignListModel>>

    @GET(CAST_URL)
    fun castListAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<CastListModel>>

    @GET(CHILDREN_URL)
    fun childrenListAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<ChildrenListModel>>

    @GET(RELIGION_URL)
    fun religionListAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<ReligionListModel>>

    @GET(SMOKING_URL)
    fun smokingListAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<SmokingListModel>>

    @GET(PROFILE_RELATIONSHIP_URL)
    fun relationshipListAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<RelationshipListModel>>

    @POST(USER_PROFILE_SUBMIT_URL)
    fun userProfileSubmitAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(REFRESH_TOKEN)
    fun refreshToken(
        @HeaderMap headers: Map<String, String>,
    ): Deferred<Response<RefreshTokenModel>>

    //  @POST(REFRESH_TOKEN)
    //  @FormUrlEncoded
    @POST()
    fun callWebservice(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        //  ): Call<JSONObject>
        //): Call<String>
    ): Call<RefreshTokenModel>


    @POST(DELETE_USER_PROFILE_PICTURE_URL)
    fun userProfilePictureDeleteAsync(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<ProfilePhotosModel.DataProfile.UserPhotos>>


    @POST(DELETE_USER_IMAGES)
    fun userPictureDeleteAsync(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<ProfilePhotosModel.DataProfile.UserPhotos>>

    @GET
    fun getInstitutesAsync(@Url url: String): Deferred<Response<InstitutesModel>>

    @GET(PREFERENCE_RELATIONSHIP_URL)
    fun preferenceRelationshipListAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<RelationshipListModel>>

    @GET(COVID_VACCINATION_URL)
    fun covidVaccineListAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<CovidVaccineListModel>>

    @POST(USER_PREFERENCE_SUBMIT_URL)
    fun userPreferenceSubmitAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @PATCH(GET_UPDATE_PROFILE_URL)
    fun userProfileUpdateAsync(
        @Header("Authorization") Authorization: String,
        @Path("id") id: Int,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>


    @PATCH(GET_UPDATE_PREFERENCE_URL)
    fun userPreferenceUpdateAsync(
        @Header("Authorization") Authorization: String,
        @Path("id") id: Int,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(USER_RECOVER_EMAIL_URL)
    fun recoverEmailAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>


    @POST(EMAIL_RECOVERY)
    fun recoverEmail(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(ACCOUNT_RECOVER_EMAIL_URL)
    fun recoverAccountAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(SEND_CONTACT_URL)
    fun sendContactAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<FwdContactListResponseModel>>

    @POST(SYNC_CONTACTS)
    fun sendContactAsync(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<ContactListResponseModel>>


    @POST(INVITE_CONTACT_URL)
    fun inviteContactAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(SEND_CONNECTION_URL)
    fun tribeConnectionAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(INVITE_CONNECTOR)
    fun inviteConnector(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: JsonObject
    ): Deferred<Response<InviteConnectorResponseModel>>

    @GET(CONNECTION_URL)
    fun tribeConnectionListAsync(
        @Header("Authorization") Authorization: String
    ): Deferred<Response<DaterConnectionResponseModel>>

    @POST(HOME_TRIBE_CONNECTIONS)
    fun tribeConnectionListAsync(
        @HeaderMap headers: Map<String, String>
    ): Deferred<Response<TribeDaterConnectionsResponseModel>>

    @POST(CHANGE_USER_TYPE_URL)
    fun changeUserTypeAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @GET(SWIPING_MATCHES_URL)
    fun swipingMatchesListAsync(
        @Header("Authorization") Authorization: String,
        //@Header("x-api-key") xApiKey: String = CV.SWIPE_KEY,
        @Path("id") id: Int?
    ): Deferred<Response<SwipingProfileMatchesModel>>

    @GET(SWIPING_PROFILES_URL)
    fun swipingProfileListAsync(

        @Header("Authorization") Authorization: String,
        //@Header("x-api-key") xApiKey: String = CV.SWIPE_KEY,
        @Path("id") id: Int
    ): Deferred<Response<SwipingProfileModel>>

    @POST(SWIPE_LEFT_RIGHT_REWIND_URL)
    fun swipingLeftRightAsync(
        @Header("Authorization") Authorization: String,
        /*@Header("x-api-key") xApiKey: String = CV.SWIPE_KEY,*/
        @Body jsonObject: JsonObject
    ): Deferred<Response<SwipeLeftRightResponseModel>>

    @HTTP(method = "DELETE", path = SWIPE_LEFT_RIGHT_REWIND, hasBody = true)
    fun swipingRewindAsyncOld(
        @Header("Authorization") Authorization: String,
        /*@Header("x-api-key") xApiKey: String = CV.SWIPE_KEY,*/
        @Body jsonObject: JsonObject,
        @Path("id") id: Int
    ): Deferred<Response<SwipeLeftRightResponseModel>>


    @POST(SWIPE_LEFT_RIGHT_REWIND)
    fun swipingRewindAsync(
        @Header("Authorization") Authorization: String,
        /*@Header("x-api-key") xApiKey: String = CV.SWIPE_KEY,*/
    ): Deferred<Response<SwipeLeftRightResponseModel>>


    @GET(GET_UPDATE_PROFILE_URL)
    fun getUserProfileAsync(
        @Header("Authorization") Authorization: String,
        @Path("id") id: Int
    ): Deferred<Response<UserProfileResponseModel>>


    @GET(GET_MATCHES_URL)
    fun getMatchesListAsync(
        @Header("Authorization") Authorization: String,
        /*@Header("x-api-key") xApiKey: String = CV.SWIPE_KEY,*/
        @Path("id") id: Int
    ): Deferred<Response<SwipingProfileMatchesModel>>

    @GET(GET_POST_USER_PROFILE_PICTURE_URL)
    fun getUserProfileImagesAsync(
        @Header("Authorization") Authorization: String,
        @Query("page") page: String
    ): Deferred<Response<UserImagesResponseModel>>

    @GET(GET_UPDATE_PREFERENCE_URL)
    fun getUserPreferenceAsync(
        @Header("Authorization") Authorization: String,
        @Path("id") id: Int
    ): Deferred<Response<UserPreferenceResponseModel>>

    @PATCH(GET_UPDATE_SETTINGS_URL)
    fun userSettingsUpdateAsync(
        @Header("Authorization") Authorization: String,
        @Path("id") id: Int,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @GET(GET_UPDATE_SETTINGS_URL)
    fun getUserSettingsAsync(
        @Header("Authorization") Authorization: String,
        @Path("id") id: Int
    ): Deferred<Response<UserSettingsResponseModel>>

    @POST(SEND_INSTAGRAM_IMAGES_URL)
    fun sendInstaImagesAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<InstagramUploadResponseModel>>

    @POST(UPDATE_USER_TOKEN)
    fun updateDeviceToken(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<DeviceTokenResponse>>

    @POST(REMOVE_TRIBE_DATER)
    fun removeTribeDaterAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(SUGGESTION_PROFILE_URL)
    fun suggestionProfileAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<InviteModel>>

    @GET(USER_PLANS_URL)
    fun getUserPlansAsync(
        @Header("Authorization") Authorization: String
    ): Deferred<Response<SettingSubscriptionModel>>

    @POST(PLAN_PURCHASE_URL)
    fun planPurchaseAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @PATCH(POPUP_SHOW_URL)
    fun popupShowAsync(
        @Header("Authorization") Authorization: String,
        @Path("id") id: Int
    ): Deferred<Response<CommonResponseModel>>

    @POST(BOOSTER_PURCHASE_URL)
    fun boosterPurchaseAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>


    @PATCH(GET_UPDATE_SETTINGS_URL)
    fun deleteAccountAsync(
        @Header("Authorization") Authorization: String,
        @Path("id") userId: Int,
        @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(USER_LOGOUT_URL)
    fun userLogoutAsync(
        @Header("Authorization") Authorization: String
    ): Deferred<Response<CommonResponseModel>>


    @GET(GET_ADD_ACTIVE_PENDING_REQUEST_URL)
    fun getActivePendingRequestAsync(
        @Header("Authorization") Authorization: String
    ): Deferred<Response<ActiveExpireRequestListModel>>

    @POST(GET_ADD_ACTIVE_PENDING_REQUEST_URL)
    fun addActivePendingRequestAsync(
        @Header("Authorization") Authorization: String, @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @GET(GET_ACTIVITY_PENDING_EXPIRE_URL)
    fun getActivityPendingRequestAsync(
        @Header("Authorization") Authorization: String
    ): Deferred<Response<ActiveExpireRequestListModel>>

    @GET(INACTIVITY)
    fun getInactivtyRequestAsync(
        @Header("Authorization") Authorization: String
    ): Deferred<Response<InactivityModel>>

    @POST(SEND_FACEBOOK_ID_URL)
    fun sendFacebookIdsAsync(
        @Header("Authorization") Authorization: String, @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @GET(FACEBOOK_FRIENDS_LIST_URL)
    fun getFacebookIdsAsync(
        @Header("Authorization") Authorization: String,
        /*@Header("x-api-key") xApiKey: String = CV.SWIPE_KEY,*/
        @Path("id") userId: Int,
        @Query("id") daterId: Int
    ): Deferred<Response<FacebookMutualFriendsListModel>>

    @Multipart
    @POST(IMAGE_MODERATION_URL)
    fun imageModerationAsync(
        @Header("Authorization") Authorization: String,
        /*@Header("x-api-key") xApiKey: String = CV.SWIPE_KEY,*/
        @Part photo: MultipartBody.Part? = null
    ): Deferred<Response<ImageModerationResponseModel>>

    @Multipart
    @POST(UPLOAD_CHAT_IMAGE)
    fun uploadChatImage(
        @Part photo: MultipartBody.Part? = null
    ): Deferred<Response<ChatImageModel>>

    @POST(SEND_RECEIPT_URL)
    fun sendReceiptAsync(
        @Header("Authorization") Authorization: String, @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(UPDATE_TRAVEL_LOCATION_URL)
    fun updateTravelLocationAsync(
        @Header("Authorization") Authorization: String, @Body jsonObject: JsonObject
    ): Deferred<Response<CommonResponseModel>>

    @POST(TRAVEL_LOCATION)
    fun travelLocationAPi(
        @HeaderMap headers: Map<String, String>, @Body jsonObject: JsonObject
    ): Deferred<Response<LocationModel>>


    @POST(RECOVER_ACCOUNT_URL)
    fun recoverAccountEmailAsync(
        @Body jsonObject: JsonObject
    ): Deferred<Response<RecoverAccountModel>>

    @POST(GOOGLE_ADDRESS)
    fun getAddressFromLatLngAsync(
        @Query("key") key: String,
        @Query("latlng") latlng: String
    ): Deferred<Response<GoogleAddressModel>>

    @Multipart
    @POST(GESTURE_VERIFICATION)
    fun submitGestureVerification(
        @Part("user_id") requestBodyUserId: RequestBody,
        @Part("gender") requestBodyGender: RequestBody,
        @Part photo: MultipartBody.Part? = null
    ): Deferred<Response<GestureVerificationModel>>


    @Multipart
    @POST(VERIFY_PROFILE)
    fun gestureProfileVerification(
        @HeaderMap headers: Map<String, String>,

        // @Part("user_id") requestBodyUserId: RequestBody,
        @Part("gender") requestBodyGender: RequestBody,
        @Part photo: MultipartBody.Part? = null
    ): Deferred<Response<GestureVerificationProfileModel>>
//    @Multipart
//    @POST(IMAGE_MODERATION_URL)
//    fun imageModerationAsync(
////        @Header("Authorization") Authorization: String,
//        /*@Header("x-api-key") xApiKey: String = CV.SWIPE_KEY,*/
//        @Part photo: MultipartBody.Part? = null
//    ): Deferred<Response<ImageModerationResponseModel>>
}