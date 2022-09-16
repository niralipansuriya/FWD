package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class GestureVerificationProfileModel(
    @SerializedName("code") val code: Int? = 0,
    @SerializedName("message") val message: String? = "",
    @SerializedName("status") val status: String? = "",
    @SerializedName("data") val data: DataVerification
) {
    data class DataVerification(
        @SerializedName("is_profile_verified") val is_profile_verified: Int? = 0,
        @SerializedName("image_url") val image_url: String? = "",
        @SerializedName("user_id") val user_id: Int? = 0
    )
}

