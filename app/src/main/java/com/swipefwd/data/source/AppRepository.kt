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
import com.swipefwd.data.source.remote.api.*
import com.swipefwd.modules.NetworkModule
import com.swipefwd.utils.AppConstants.RELOADLY_AUDIENCE_SANDBOX
import com.swipefwd.xmpp.ChatImageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import retrofit2.http.Body
import java.io.File

class AppRepository : AppDataSource {

    override suspend fun linkedInTokenApi(code: String): Response<LinkedInTokenModel> {
        return NetworkModule.getSocialMediaService(LINKEDIN_BASE_URL).getLinkedInTokenAsync(code)
    }

    override suspend fun linkedInProfileApi(token: String): Response<LinkedInProfileModel> =
        NetworkModule.getSocialMediaService(LINKEDIN_BASE_URL)
            .getLinkedInProfileAsync(token)

    override suspend fun linkedInEmailApi(token: String): Response<LinkedInEmailModel> =
        NetworkModule.getSocialMediaService(LINKEDIN_BASE_URL).getLinkedInEmailAsync(token)

    /** #####  #
     * #### ##### ##### ##### ##### ##### ##### ###
     * #### ##### ##### ##### ##### ##### ##### ###
     * #### ##### ##### ##### ##### ##### ##### ##### ##### ##### ##### ##### **/


    override suspend fun loginUserApi(jsonObject: JsonObject): Flow<Response<LoginResponseModel>> =
        flow {
            emit(NetworkModule.newApi(NEW_BASE_URL).loginUserAsync(jsonObject).await())
        }

    override suspend fun recoverAccountEmailApi(jsonObject: JsonObject): Flow<Response<RecoverAccountModel>> =
        flow {
            emit(NetworkModule.newApi(NEW_BASE_URL).recoverAccountEmailAsync(jsonObject).await())
        }

    override suspend fun registerUserApi(jsonObject: JsonObject): Flow<Response<LoginResponseModel>> =
        flow {
            emit(NetworkModule.newApi(NEW_BASE_URL).registerUserAsync(jsonObject).await())
        }


    override suspend fun getOtpApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<OTPModel>> =
        flow {
            emit(
                NetworkModule.apiService.getOtpAsync(
                    headers,
                    jsonObject
                ).await()
            )
        }

    override suspend fun sendBasicProfileData(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<BasicProfileDetails>> =
        flow {
            emit(
                NetworkModule.apiService.sendBasicProfileData(
                    headers,
                    jsonObject
                ).await()
            )
        }

    override suspend fun setDetailedProfile(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<DetailedProfileModel>> =
        flow {
            emit(
                NetworkModule.apiService.setDetailedProfile(
                    headers,
                    jsonObject
                ).await()
            )
        }

    override suspend fun updateMobile(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<OTPModel>> =
        flow {
            emit(
                NetworkModule.apiService.updateMobile(
                    headers,
                    jsonObject
                ).await()
            )
        }

    override suspend fun socialLogin(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<OTPModel>> =
        flow {
            emit(
                NetworkModule.apiService.getSocialsignUp(
                    headers,
                    jsonObject
                ).await()
            )
        }

/*  override suspend fun getOtpApi(jsonObject: JsonObject): Flow<Response<OTPModel>> =
      flow {
          emit(NetworkModule.apiService.getOtpAsync(jsonObject).await())
      }*/

    override suspend fun verifyOtpApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<OTPModel>> =
        flow {
            emit(NetworkModule.apiService.verifyOtpAsync(headers, jsonObject).await())
        }

    override suspend fun setEmailUserApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<SetEmailModel>> =
        flow {
            emit(NetworkModule.apiService.setEmailUserApi(headers, jsonObject).await())
        }

    override suspend fun verifyOtpEmail(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<OTPModel>> =
        flow {
            emit(NetworkModule.apiService.verifyOtpEmail(headers, jsonObject).await())
        }
    override suspend fun sendOtpEmail(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<ResendOtp>> =
        flow {
            emit(NetworkModule.apiService.sendOtpEmail(headers, jsonObject).await())
        }

    override suspend fun getDateList(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<DateGetModel>> =
        flow {
            emit(NetworkModule.apiService.getDateList(headers, jsonObject).await())
        }
    override suspend fun updateDateList(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<UpdateDateModel>> =
        flow {
            emit(NetworkModule.apiService.updateDateList(headers, jsonObject).await())
        }

    override suspend fun setUserTypeApi(
        headers: HashMap<String, String>, @Body jsonObject: JsonObject
    ): Flow<Response<UserTypeModel>> =
        flow {
            emit(NetworkModule.apiService.setUserTypeApi(headers, jsonObject).await())
        }

    override suspend fun resendOtp(
        headers: HashMap<String, String>,
    ): Flow<Response<ResendOtp>> =
        flow {
            emit(NetworkModule.apiService.resendOtp(headers).await())
        }

    override suspend fun setLocation(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<LocationModel>> =
        flow {
            emit(NetworkModule.apiService.setLocation(headers, jsonObject).await())
        }

    override suspend fun languageListApi(
        token: String, page: String
    ): Flow<Response<LanguageListModel>> =
        flow {
            emit(NetworkModule.apiService.languageListAsync(token, page).await())
        }

    override suspend fun educationLevelListApi(
        token: String, page: String
    ): Flow<Response<EducationLevelListModel>> =
        flow {
            emit(NetworkModule.apiService.educationLevelListAsync(token, page).await())
        }

    override suspend fun getEducationApi(
        jsonObject: JsonObject
    ): Flow<Response<EducationDetailModel>> =
        flow {
            emit(NetworkModule.apiService.getEducation(jsonObject).await())
        }

    override suspend fun getLanguageApi(
        jsonObject: JsonObject
    ): Flow<Response<LanguageDataModel>> =
        flow {
            emit(NetworkModule.apiService.getLanguageApi(jsonObject).await())
        }

    override suspend fun getAstrologicalSign(
        jsonObject: JsonObject
    ): Flow<Response<AstrologicalModel>> =
        flow {
            emit(NetworkModule.apiService.getAstrologicalSign(jsonObject).await())
        }

    override suspend fun deleteUserAccount(
        headers: HashMap<String, String>
    ): Flow<Response<DeleteAccountModel>> =
        flow {
            emit(NetworkModule.apiService.deleteUserAccount(headers).await())
        }

    override suspend fun getRelationshipApi(
        jsonObject: JsonObject
    ): Flow<Response<RelationshipModel>> =
        flow {
            emit(NetworkModule.apiService.getRelationship(jsonObject).await())
        }

    override suspend fun getChildrenApi(
        jsonObject: JsonObject
    ): Flow<Response<ChildrenModel>> =
        flow {
            emit(NetworkModule.apiService.getChildren(jsonObject).await())
        }

    override suspend fun getVaccinationApi(
        jsonObject: JsonObject
    ): Flow<Response<VaccinationModel>> =
        flow {
            emit(NetworkModule.apiService.getVaccination(jsonObject).await())
        }

    override suspend fun getReligionApi(
        jsonObject: JsonObject
    ): Flow<Response<ReligionModel>> =
        flow {
            emit(NetworkModule.apiService.getReligion(jsonObject).await())
        }

    override suspend fun getSmokingApi(
        jsonObject: JsonObject
    ): Flow<Response<SmokingDataModel>> =
        flow {
            emit(NetworkModule.apiService.getSmokingApi(jsonObject).await())
        }

    override suspend fun astrologySignListApi(
        token: String, page: String
    ): Flow<Response<AstroSignListModel>> =
        flow {
            emit(NetworkModule.apiService.astrologySignListAsync(token, page).await())
        }

    override suspend fun castListApi(
        token: String, page: String
    ): Flow<Response<CastListModel>> =
        flow {
            emit(NetworkModule.apiService.castListAsync(token, page).await())
        }

    override suspend fun childrenListApi(
        token: String, page: String
    ): Flow<Response<ChildrenListModel>> =
        flow {
            emit(NetworkModule.apiService.childrenListAsync(token, page).await())
        }


    override suspend fun religionListApi(
        token: String, page: String
    ): Flow<Response<ReligionListModel>> =
        flow {
            emit(NetworkModule.apiService.religionListAsync(token, page).await())
        }

    override suspend fun smokingListApi(
        token: String, page: String
    ): Flow<Response<SmokingListModel>> =
        flow {
            emit(NetworkModule.apiService.smokingListAsync(token, page).await())
        }

    override suspend fun relationshipListApi(
        token: String, page: String
    ): Flow<Response<RelationshipListModel>> =
        flow {
            emit(NetworkModule.apiService.relationshipListAsync(token, page).await())
        }

    override suspend fun covidVaccineListApi(
        token: String, page: String
    ): Flow<Response<CovidVaccineListModel>> =
        flow {
            emit(NetworkModule.apiService.covidVaccineListAsync(token, page).await())
        }

    override suspend fun userProfileSubmitApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(NetworkModule.apiService.userProfileSubmitAsync(token, jsonObject).await())
        }

//    override suspend fun userProfilePictureApi(
//        token: String, _isProfile: String, file: File,
//    ): Flow<Response<CommonResponseModel>> =
//        flow {
//            val mediaTypeImage: MediaType = "image/*".toMediaTypeOrNull()!!
//            val requestBody: RequestBody = file.asRequestBody(mediaTypeImage)
//            val imageBody: MultipartBody.Part =
//                MultipartBody.Part.createFormData("image", file.name, requestBody)
//            val isProfile: RequestBody = _isProfile.toRequestBody("text/plain".toMediaTypeOrNull())
//
//            emit(
//                NetworkModule.apiService.userProfilePictureAsync(token, isProfile, imageBody)
//                    .await()
//            )
//        }

    override suspend fun userProfilePictureDeleteApi(
        token: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<ProfilePhotosModel.DataProfile.UserPhotos>> =
        flow {
            emit(
                NetworkModule.apiService.userProfilePictureDeleteAsync(token, jsonObject)
                    .await()
            )
        }

    override suspend fun userPictureDeleteAsync(
        token: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<ProfilePhotosModel.DataProfile.UserPhotos>> =
        flow {
            emit(
                NetworkModule.apiService.userPictureDeleteAsync(token, jsonObject)
                    .await()
            )
        }

    override suspend fun instituteListApi(url: String): Flow<Response<InstitutesModel>> =
        flow {
            emit(NetworkModule.apiService.getInstitutesAsync(url).await())
        }

    override suspend fun preferenceRelationshipListApi(
        token: String, page: String
    ): Flow<Response<RelationshipListModel>> =
        flow {
            emit(NetworkModule.apiService.preferenceRelationshipListAsync(token, page).await())
        }

    override suspend fun userPreferenceSubmitApi(
        headers: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(NetworkModule.apiService.userPreferenceSubmitAsync(headers, jsonObject).await())
        }

    override suspend fun userAdvancePreferenceSubmitApi(
        headers: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.userAdvancePreferenceSubmitAsync(headers, jsonObject)
                    .await()
            )
        }

    override suspend fun instagramTokenApi(code: String): Flow<Response<InstagramTokenModel>> =
        flow {
            emit(
                NetworkModule.getSocialMediaService(INSTAGRAM_BASE_TOKEN)
                    .getInstagramTokenAsync(code).await()
            )
        }

    override suspend fun instagramProfileAPi(
        userId: Long,
        token: String
    ): Flow<Response<InstagramProfileModel>> =
        flow {
            emit(
                NetworkModule.getSocialMediaService(INSTAGRAM_BASE_PROFILE)
                    .getInstagramProfileAsync(userId, token).await()
            )
        }

    override suspend fun instagramMediaApi(
        mediaId: String, token: String
    ): Flow<Response<InstagramMediaModel>> =
        flow {
            emit(
                NetworkModule.getSocialMediaService(INSTAGRAM_BASE_PROFILE)
                    .getInstagramMediaAsync(mediaId, token).await()
            )
        }

    override suspend fun userProfileUpdateApi(
        token: String, id: Int, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.userProfileUpdateAsync(token, id, jsonObject)
                    .await()
            )
        }

    override suspend fun userPreferenceUpdateApi(
        token: String, id: Int, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.userPreferenceUpdateAsync(token, id, jsonObject)
                    .await()
            )
        }

    override suspend fun recoverEmailApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun userProfilePictureApi(
        token: String, _isProfile: String, file: File,
    ): Flow<Response<CommonResponseModel>> =
        flow {
            val mediaTypeImage: MediaType = "image/*".toMediaTypeOrNull()!!
            val requestBody: RequestBody = file.asRequestBody(mediaTypeImage)
            val imageBody: MultipartBody.Part =
                MultipartBody.Part.createFormData("image", file.name, requestBody)
            val isProfile: RequestBody = _isProfile.toRequestBody("text/plain".toMediaTypeOrNull())

            emit(
                NetworkModule.apiService.userProfilePictureAsync(token, isProfile, imageBody)
                    .await()
            )
        }

    override suspend fun userBasicProfileSet(
        headers: HashMap<String, String>, file: File,
    ): Flow<Response<BasicProfileUpload>> =
        flow {
            val mediaTypeImage: MediaType = "image/*".toMediaTypeOrNull()!!
            val requestBody: RequestBody = file.asRequestBody(mediaTypeImage)
            val imageBody: MultipartBody.Part =
                MultipartBody.Part.createFormData("profile_pic", file.name, requestBody)

            emit(
                NetworkModule.apiService.userBasicProfileSet(headers, imageBody)
                    .await()
            )
        }

    override suspend fun userProfilePhotosUpload(
        headers: HashMap<String, String>,
        position: String,
        file: File
    ): Flow<Response<ProfilePhotosModel>> =
        flow {
            val mediaTypeImage: MediaType = "image/*".toMediaTypeOrNull()!!
            val requestBody: RequestBody = file.asRequestBody(mediaTypeImage)
            val positionPhotos: RequestBody =
                position.toRequestBody("text/plain".toMediaTypeOrNull())

            val imageBody: MultipartBody.Part =
                MultipartBody.Part.createFormData("photos", file.name, requestBody)

            emit(
                NetworkModule.apiService.uplodProfilePhotos(headers, positionPhotos, imageBody)
                    .await()
            )
        }


    override fun refreshToken(headers: HashMap<String, String>): Flow<Response<RefreshTokenModel>> {
        TODO("Not yet implemented")
    }

    override fun callWebservice(headers: HashMap<String, String>): Flow<Response<RefreshTokenModel>> {
        TODO("Not yet implemented")
    }


    /*   override suspend fun recoverEmailApi(
           token: String, jsonObject: JsonObject
       ): Flow<Response<CommonResponseModel>> =
           flow {
               emit(
                   NetworkModule.apiService.recoverEmailAsync(token, jsonObject)
                       .await()
               )
           }*/

    override suspend fun recoverEmailAddress(
        headers: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.recoverEmail(headers, jsonObject)
                    .await()
            )
        }

/* override suspend fun callWebservice(
        headers: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<RefreshTokenModel>> =
        flow {
            emit(
                NetworkModule.apiService.callWebservice(REFRESH_TOKEN, jsonObject)
            )
        }*/

    override suspend fun recoverAccountApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.recoverAccountAsync(token, jsonObject)
                    .await()
            )
        }

    override suspend fun sendContactApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<FwdContactListResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.sendContactAsync(token, jsonObject)
                    .await()
            )
        }

    override suspend fun sendContactApi(
        headers: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<ContactListResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.sendContactAsync(headers, jsonObject)
                    .await()
            )
        }

    override suspend fun inviteContactApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.inviteContactAsync(token, jsonObject)
                    .await()
            )
        }

    override suspend fun tribeConnectionApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.tribeConnectionAsync(token, jsonObject)
                    .await()
            )
        }

    override suspend fun inviteConnector(
        headers: HashMap<String, String>, jsonObject: JsonObject
    ): Flow<Response<InviteConnectorResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.inviteConnector(headers, jsonObject)
                    .await()
            )
        }

    override suspend fun tribeConnectionListApi(
        token: String
    ): Flow<Response<DaterConnectionResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.tribeConnectionListAsync(token)
                    .await()
            )
        }

    override suspend fun tribeConnectionListApi(
        header: HashMap<String, String>
    ): Flow<Response<TribeDaterConnectionsResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.tribeConnectionListAsync(header)
                    .await()
            )
        }

    override suspend fun matchesListApi(
        id: Int, token: String
    ): Flow<Response<SwipingProfileMatchesModel>> =
        flow {
            emit(
                NetworkModule.getSwipeProfileService(SWIPE_BASE_URL)
                    .getMatchesListAsync(id = id, Authorization = token)
                    .await()
            )
        }

    override suspend fun changeUserTypeApi(
        token: String, jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.changeUserTypeAsync(token, jsonObject)
                    .await()
            )
        }

    override suspend fun swipingMatchesApi(
        id: Int?, token: String
    ): Flow<Response<SwipingProfileMatchesModel>> =
        flow {
            emit(
                NetworkModule.getSwipeProfileService(SWIPE_BASE_URL)
                    .swipingMatchesListAsync(token, id = id)
                    .await()
            )
        }

    override suspend fun swipingProfileApi(
        id: Int, token: String
    ): Flow<Response<SwipingProfileModel>> =
        flow {
            emit(
                NetworkModule.getSwipeProfileService(SWIPE_BASE_URL)
                    .swipingProfileListAsync(id = id, Authorization = token)
                    .await()
            )
        }

    override suspend fun swipingLeftRightApi(
        jsonObject: JsonObject, token: String
    ): Flow<Response<SwipeLeftRightResponseModel>> =
        flow {
            emit(
                NetworkModule.newApi(NEW_BASE_URL)
                    .swipingLeftRightAsync(jsonObject = jsonObject, Authorization = token)
                    .await()
            )
        }

    override suspend fun swipingRewindApi(
        jsonObject: JsonObject, token: String, userId: Int
    ): Flow<Response<SwipeLeftRightResponseModel>> =
        flow {
            emit(
                NetworkModule.newApi(NEW_BASE_URL)
                    .swipingRewindAsync(Authorization = token)
                    .await()
            )
        }

    override suspend fun getUserProfileApi(
        token: String,
        id: Int
    ): Flow<Response<UserProfileResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.getUserProfileAsync(token, id)
                    .await()
            )
        }

    override suspend fun getUserImagesApi(
        token: String,
        page: String
    ): Flow<Response<UserImagesResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.getUserProfileImagesAsync(token, page)
                    .await()
            )
        }

    override suspend fun getUserPreferenceApi(
        token: String,
        id: Int
    ): Flow<Response<UserPreferenceResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.getUserPreferenceAsync(token, id)
                    .await()
            )
        }

    override suspend fun userSettingsUpdateApi(
        token: String,
        id: Int,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.userSettingsUpdateAsync(token, id, jsonObject)
                    .await()
            )
        }

    override suspend fun getUserSettingsApi(
        token: String,
        id: Int
    ): Flow<Response<UserSettingsResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.getUserSettingsAsync(token, id)
                    .await()
            )
        }

    override suspend fun sendInstaImagesApi(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<InstagramUploadResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.sendInstaImagesAsync(token, jsonObject)
                    .await()
            )
        }

    override suspend fun updateDeviceToken(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<DeviceTokenResponse>> =
        flow {
            emit(
                NetworkModule.apiService.updateDeviceToken(token, jsonObject)
                    .await()
            )
        }


    /*  override suspend fun updateDeviceToken(
          header:HashMap<String,String>
      ): Flow<Response<RefreshTokenModel>> =
          flow {
              emit(
                  NetworkModule.apiService.refreshToken(header)
                      .await()
              )
          }
  */

    override suspend fun removeTribeDaterApi(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.removeTribeDaterAsync(token, jsonObject)
                    .await()
            )
        }

    override suspend fun suggestionProfileApi(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<InviteModel>> = flow {
        emit(
            NetworkModule.newApi(NEW_BASE_URL).suggestionProfileAsync(token, jsonObject)
                .await()
        )
    }

    override suspend fun userPlansApi(token: String): Flow<Response<SettingSubscriptionModel>> =
        flow {
            emit(
                NetworkModule.apiService.getUserPlansAsync(token)
                    .await()
            )
        }

    override suspend fun planPurchaseApi(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> = flow {
        emit(
            NetworkModule.apiService.planPurchaseAsync(token, jsonObject)
                .await()
        )
    }

    override suspend fun popupShowApi(token: String, id: Int): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.popupShowAsync(token, id)
                    .await()
            )
        }

    override suspend fun boosterPurchaseApi(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> = flow {
        emit(
            NetworkModule.apiService.boosterPurchaseAsync(token, jsonObject)
                .await()
        )
    }

    override suspend fun deleteAccount(
        token: String,
        userId: Int,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> = flow {
        emit(
            NetworkModule.apiService.deleteAccountAsync(token, userId, jsonObject)
                .await()
        )
    }

    override suspend fun userLogoutApi(token: String): Flow<Response<CommonResponseModel>> = flow {
        emit(
            NetworkModule.apiService.userLogoutAsync(token)
                .await()
        )
    }

    override suspend fun getActivePendingRequestApi(token: String): Flow<Response<ActiveExpireRequestListModel>> =
        flow {
            emit(
                NetworkModule.apiService.getActivePendingRequestAsync(token)
                    .await()
            )
        }

    override suspend fun addActivePendingRequestApi(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.addActivePendingRequestAsync(token, jsonObject)
                    .await()
            )
        }

    override suspend fun getActivityPendingRequestApi(token: String): Flow<Response<ActiveExpireRequestListModel>> =
        flow {
            emit(
                NetworkModule.apiService.getActivityPendingRequestAsync(token)
                    .await()
            )
        }

    override suspend fun sendFacebookIdApi(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> =
        flow {
            emit(
                NetworkModule.apiService.sendFacebookIdsAsync(token, jsonObject)
                    .await()
            )
        }

    /**
     * Wallet - reloadly API
     */
    override suspend fun getAccessTokenApi(jsonObject: JsonObject): Flow<Response<GetAccessTokenModel>> =
        flow {
            emit(
                NetworkModule.getWalletService(RELOADLY_AUTH_TOKEN).getAccessTokenAsync(jsonObject)
                    .await()
            )
        }

    override suspend fun getOperatorByIso(
        token: String,
        isoCode: String,
        includeBundles: Boolean,
        includeData: Boolean,
        includePin: Boolean,
        suggestedAmounts: Boolean,
        suggestedAmountsMap: Boolean
    ): Flow<Response<GetOperatorByIsoModel>> = flow {
        emit(
            NetworkModule.getWalletService(RELOADLY_AUDIENCE_SANDBOX)
                .getOperatorByIsoAsync(
                    token,
                    isoCode,
                    includeBundles,
                    includeData,
                    includePin,
                    suggestedAmounts,
                    suggestedAmountsMap
                )
                .await()
        )
    }

    override suspend fun makeRecharge(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<MakeRechargeModel>> = flow {
        emit(
            NetworkModule.getWalletService(RELOADLY_AUDIENCE_SANDBOX)
                .makeRechargeAsync(
                    token,
                    jsonObject
                )
                .await()
        )
    }

    override suspend fun getFacebookListIds(
        userId: Int,
        daterId: Int, token: String
    ): Flow<Response<FacebookMutualFriendsListModel>> =
        flow {
            emit(
                NetworkModule.getSwipeProfileService(SWIPE_BASE_URL)
                    .getFacebookIdsAsync(userId = userId, daterId = daterId, Authorization = token)
                    .await()
            )
        }

    override suspend fun sendProfileVerification(
        photo: MultipartBody.Part,
        token: String
    ): Flow<Response<ImageModerationResponseModel>> =
        flow {
            emit(
                NetworkModule.getSwipeProfileService(SWIPE_BASE_URL)
                    .imageModerationAsync(photo = photo, Authorization = token)
                    .await()
            )
        }


    override suspend fun uploadChatImage(photo: MultipartBody.Part): Flow<Response<ChatImageModel>> =
        flow {
            emit(
                NetworkModule.getSwipeProfileService(CHAT_IMAGE_URL)
                    .uploadChatImage(photo = photo)
                    .await()
            )
        }

    override suspend fun sendReceipt(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> = flow {
        emit(
            NetworkModule.apiService.sendReceiptAsync(token, jsonObject)
                .await()
        )
    }

    override suspend fun updateTravelLocation(
        token: String,
        jsonObject: JsonObject
    ): Flow<Response<CommonResponseModel>> = flow {
        emit(
            NetworkModule.apiService.updateTravelLocationAsync(token, jsonObject)
                .await()
        )
    }

    override suspend fun travelLocationApi(
        headers: HashMap<String, String>,
        jsonObject: JsonObject
    ): Flow<Response<LocationModel>> = flow {
        emit(
            NetworkModule.apiService.travelLocationAPi(headers, jsonObject)
                .await()
        )
    }

    override suspend fun sendImageGestureVerification(
        requestBodyUserId: RequestBody,
        requestBodyGender: RequestBody,
        photo: MultipartBody.Part
    ): Flow<Response<GestureVerificationModel>> =
        flow {
            emit(
                NetworkModule.getSwipeProfileService(SWIPE_BASE_URL)
                    .submitGestureVerification(
                        requestBodyUserId = requestBodyUserId,
                        requestBodyGender = requestBodyGender,
                        photo = photo
                    )
                    .await()
            )
        }

    override suspend fun gestureProfileVerification(
        headers: HashMap<String, String>,
        requestBodyGender: RequestBody,
        photo: MultipartBody.Part
    ): Flow<Response<GestureVerificationProfileModel>> =
        flow {
            emit(
                NetworkModule.apiService.gestureProfileVerification(
                    headers,
                    requestBodyGender,
                    photo
                ).await()
            )
        }

    override suspend fun getAddressFromLatLong(
        key: String,
        latlng: String
    ): Flow<Response<GoogleAddressModel>> =
        flow {
            emit(
                NetworkModule.newApi(GOOGLE_BASE_URL).getAddressFromLatLngAsync(key, latlng).await()
            )
        }

    override suspend fun getInactivity(token: String): Flow<Response<InactivityModel>> =
        flow {
            emit(
                NetworkModule.apiService.getInactivtyRequestAsync(token)
                    .await()
            )
        }
}