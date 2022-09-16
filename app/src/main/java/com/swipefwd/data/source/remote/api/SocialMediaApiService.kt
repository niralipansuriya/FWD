package com.swipefwd.data.source.remote.api

import com.swipefwd.data.models.*
import com.swipefwd.utils.AppUtils
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface SocialMediaApiService {
    @GET(LINKEDIN_TOKEN_URL)
    suspend fun getLinkedInTokenAsync(
        @Query("code") code: String,
        @Query("grant_type") grant_type: String = "authorization_code",
        @Query("client_id") client_id: String = AppUtils.LinkedInKeys.linkedInClientId,
        @Query("client_secret") client_secret: String = AppUtils.LinkedInKeys.linkedInClientSecret,
        @Query("redirect_uri") redirect_uri: String = AppUtils.LinkedInKeys.linkedInRedirectURI,
    ): Response<LinkedInTokenModel>

    @GET(LINKEDIN_PROFILE_URL)
    suspend fun getLinkedInProfileAsync(
        @Header("Authorization") Authorization: String,
        @Query("projection") projection: String = "(id,firstName,lastName,phone-numbers,profilePicture(displayImage~:playableStreams))",
    ): Response<LinkedInProfileModel>

    @GET(LINKEDIN_EMAIL_URL)
    suspend fun getLinkedInEmailAsync(
        @Header("Authorization") Authorization: String,
        @Query("q") q: String = "members",
        @Query("projection") projection: String = "(elements*(handle~))",
    ): Response<LinkedInEmailModel>

    /** ##################### ##################### ##################### #####################
     * #####################
     * #####################
     * ####################################################################################  **/

    //  Instagram
    @FormUrlEncoded
    @POST(INSTAGRAM_TOKEN_URL)
    fun getInstagramTokenAsync(
        @Field("code") code: String,
        @Field("grant_type") grant_type: String = "authorization_code",
        @Field("client_id") client_id: String = AppUtils.InstaKeys.instaClientId,
        @Field("client_secret") client_secret: String = AppUtils.InstaKeys.instaClientSecret,
        @Field("redirect_uri") redirect_uri: String = AppUtils.InstaKeys.instaRedirectURI,
    ): Deferred<Response<InstagramTokenModel>>

    //    ref: https://developers.facebook.com/docs/instagram-basic-display-api/reference/user
    @GET("{user-id}")
    fun getInstagramProfileAsync(
        @Path("user-id") user_id: Long,
        @Query("access_token") access_token: String,
        @Query("fields") fields: String = "id,username,account_type",
    ): Deferred<Response<InstagramProfileModel>>

    //    ref: https://developers.facebook.com/docs/instagram-basic-display-api/reference/media/
    @GET("{media-id}")
    fun getInstagramMediaAsync(
        @Path("media-id") media_id: String,
        @Query("access_token") access_token: String,
        @Query("fields") fields: String = "id,username"
    ): Deferred<Response<InstagramMediaModel>>


    companion object {
        private const  val serialVersionUID = -91L
    }
}