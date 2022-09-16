package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class InstagramUploadResponseModel(
    @SerializedName("image_url") val imageUrl: ArrayList<String>? = arrayListOf(),
    @SerializedName("message") val message: String? = "",
    @SerializedName("response") val response: String? = "",
    @SerializedName("status") val status: Boolean? = false,
    @SerializedName("detail") val detail: String? = ""
)