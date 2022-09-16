package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LinkedInTokenModel(
    @SerializedName("access_token")
    val accessToken: String = "",
    @SerializedName("expires_in")
    val expiresIn: Long = 0L
):Serializable