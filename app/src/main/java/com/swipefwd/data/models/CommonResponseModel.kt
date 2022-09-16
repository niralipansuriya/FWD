package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class CommonResponseModel(
    @SerializedName("message") val message: String? = "",
    @SerializedName("protocol") val protocol: String? = "",
    @SerializedName("url") val url: String? = "",
    @SerializedName("detail") val detail: String? = "",
    @SerializedName("response") val response: String? = "",
    @SerializedName("status") val status: Boolean? = false,
    @SerializedName("image_url") val image_url: String? = "",
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("profile") val profile: Boolean? = false,
    @SerializedName("preference") val preference: Boolean? = false,
    @SerializedName("advanceprofile") val advanceprofile: Boolean? = false,
    @SerializedName("count") val count: Int? = 0,
    @SerializedName("is_popup") val is_popup: Boolean? = false,
    @SerializedName("remain_connection") val remain_connection: Int? = 0,
    @SerializedName("code") val code: Int? = 0, //for instagram response
    @SerializedName("error_type") val error_type: String? = "", //for instagram response
)