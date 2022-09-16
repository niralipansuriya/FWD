package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class SwipeLeftRightResponseModel(
    @SerializedName("data") val data: Data? = Data(),
    @SerializedName("response") val response: String? = "",
    @SerializedName("status") val status: Boolean? = false
) {
    data class Data(
        @SerializedName("Code") val code: String? = "",
        @SerializedName("Message") val message: String? = ""
    )
}