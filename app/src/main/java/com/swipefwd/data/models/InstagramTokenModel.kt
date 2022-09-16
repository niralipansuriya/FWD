package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class InstagramTokenModel(
    @SerializedName("access_token") val accessToken: String? = "",
    @SerializedName("user_id") val userId: Long? = 0
)