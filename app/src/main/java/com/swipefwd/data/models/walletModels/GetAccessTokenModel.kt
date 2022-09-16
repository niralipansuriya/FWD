package com.swipefwd.data.models.walletModels


import com.google.gson.annotations.SerializedName

data class GetAccessTokenModel(
    @SerializedName("access_token") val accessToken: String? = "",
    @SerializedName("expires_in") val expiresIn: Int? = 0,
    @SerializedName("scope") val scope: String? = "",
    @SerializedName("token_type") val tokenType: String? = "",
    @SerializedName("message") val message: String? = ""
)