package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class ImageModerationResponseModel(
    @SerializedName("data") val data: ResponseModel? = ResponseModel(),
    @SerializedName("response") val response: String? = "",
    @SerializedName("Code") val code: String? = "",
    @SerializedName("InnerCode") val innerCode: String? = "",
    @SerializedName("message") val message: String? = "",
    @SerializedName("status") val status: Boolean? = false
) {
    data class ResponseModel(
        @SerializedName("appropriate") val appropriate: Boolean? = false
    )
}