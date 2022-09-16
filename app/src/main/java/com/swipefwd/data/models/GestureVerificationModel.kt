package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class GestureVerificationModel(
    @SerializedName("error") val error: String? = "" ,
    @SerializedName("message") val message: String? = "",
    @SerializedName("is_verified") val is_verified: Boolean = false
)