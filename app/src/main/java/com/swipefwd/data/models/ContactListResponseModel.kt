package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class ContactListResponseModel(
    @SerializedName("code") val code: Int? = null,
    @SerializedName("status") val status: String? = "",
    @SerializedName("message") val message: String? = "",
    @SerializedName("data") val data: Data? = null,
) {
    data class Data(
        @SerializedName("left_invivitation") val leftInvivitation: Int? = 0,
        @SerializedName("exist_user") val existUser: List<ExistUser>? = arrayListOf(),
        @SerializedName("non_exist_user") val nonExistUser: List<NonExistUser>? = arrayListOf()
    )

    data class NonExistUser(
        @SerializedName("mobile") val mobile: String? = "",
        @SerializedName("country_code") val countryCode: String? = "",
        @SerializedName("name") val name: String? = "",
        @SerializedName("freind_count") val freindCount: Int? = 0
    )

    data class ExistUser(
        @SerializedName("mobile") val mobile: String? = "",
        @SerializedName("country_code") val countryCode: String? = "",
        @SerializedName("first_name") val firstName: String? = "",
        @SerializedName("freind_count") val freindCount: Int? = 0,
        @SerializedName("_id") val id: Int? = 0,
        @SerializedName("profile_pic") val profilePic: String? = ""
    )
}