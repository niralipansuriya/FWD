package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("data")
    val data: Data = Data(),
    @SerializedName("message")
    val message: String = "",
    @SerializedName("profile")
    val profile: Boolean = false,
    @SerializedName("response")
    val response: String = "",
    @SerializedName("status")
    val status: Boolean = false,
    @SerializedName("token")
    val token: String = "",
    @SerializedName("preference")
    val preference: Boolean = false,
    @SerializedName("user_id")
    val user_id: Int = 0,
    @SerializedName("advanceprofile")
    val advanceprofile: Boolean = false,
    @SerializedName("setting")
    val setting: Boolean = false,
    @SerializedName("invitation_expired")
    val invitation_expired: Boolean = false,
    @SerializedName("gender")
    val gender: String = ""
) {
    data class Data(
        @SerializedName("email")
        val recoveryEmail : String = "",
        @SerializedName("user_id")
        val userId: Int = 0,
        @SerializedName("user_type")
        val userType: String = "",
        @SerializedName("first_name")
        val first_name: String = "",
        @SerializedName("last_name")
        val last_name: String = "",
        @SerializedName("profile_image")
        val profile_image: String = "",
        @SerializedName("phone_number")
        val phone_number: String = "",
        @SerializedName("remain_invitation")
        val remain_invitation: Int = 10,
        @SerializedName("is_agree")
        val is_agree: Boolean = false,
        @SerializedName("remain_connection")
        val remain_connection: Int = 5,
        @SerializedName("instargram_name")
        val instargram_name: String = "",
        @SerializedName("facebook")
        val facebook_id: String = "",
        @SerializedName("google")
        val google_id: String = "",
        @SerializedName("apple")
        val apple_id: String = "",
        @SerializedName("linkdin")
        val linkdin_id: String = "",
        @SerializedName("is_dater_disabled")
        val is_dater_disabled: Boolean = false,
        @SerializedName("is_connector_disabled")
        val is_connector_disabled: Boolean = false,
        @SerializedName("is_verified") val is_verified: Boolean = false
    )
}
