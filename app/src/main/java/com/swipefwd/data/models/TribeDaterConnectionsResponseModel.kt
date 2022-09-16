package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class TribeDaterConnectionsResponseModel(
    @SerializedName("message") val message: String? = "",
    @SerializedName("status") val status: String? = "",
    @SerializedName("data") val data: TribeModel? = null,
    @SerializedName("code") val code: Int? = null
) {
    data class TribeModel(
        @SerializedName("tribe") val tribe: List<Tribe>? = arrayListOf(),
        @SerializedName("tribelength") val tribelength: Int? = null
    ){
        data class Tribe(
            @SerializedName("connector_id") val connectorId: Int? = null,
            @SerializedName("profile_pic") val profilePic: String? = "",
            @SerializedName("first_name") val firstName: String? = "",
            var daterIndex: Int = 0,
            var isAvailable: Boolean = false,
            var presenceMode: Int? = 0
        )
    }
}