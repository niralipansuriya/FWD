package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class InstagramMediaModel(
    @SerializedName("id") val id: String? = "",
    @SerializedName("media_type") val mediaType: String? = "",
    @SerializedName("media_url") val mediaUrl: String? = "",
    @SerializedName("username") val username: String? = "",
)