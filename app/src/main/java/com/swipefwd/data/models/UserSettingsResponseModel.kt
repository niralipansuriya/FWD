package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class UserSettingsResponseModel(
    @SerializedName("data") val data: Data? = Data(),
    @SerializedName("response") val response: String? = "",
    @SerializedName("status") val status: Boolean? = false
) {
    data class Data(
        @SerializedName("created_at") val createdAt: String? = "",
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("is_apple_connect") val isAppleConnect: Boolean? = false,
        @SerializedName("is_connector_visible") val isConnectorVisible: Boolean? = true,
        @SerializedName("is_dater_visible") val isDaterVisible: Boolean? = true,
        @SerializedName("is_facebook_connect") val isFacebookConnect: Boolean? = false,
        @SerializedName("is_google_connect") val isGoogleConnect: Boolean? = false,
        @SerializedName("is_instagram_connect") val isInstagramConnect: Boolean? = false,
        @SerializedName("is_linkedin_connect") val isLinkedinConnect: Boolean? = false,
        @SerializedName("is_newconnection_notify") val isNewconnectionNotify: Boolean? = false,
        @SerializedName("is_newfwd_notify") val isNewfwdNotify: Boolean? = false,
        @SerializedName("is_dater_disabled") val isDaterDisabled: Boolean? = false,
        @SerializedName("is_connector_disabled") val isConnectorDisabled: Boolean? = false,
        @SerializedName("is_newmatchmaker_notify") val isNewmatchmakerNotify: Boolean? = false,
        @SerializedName("is_newmessage_notify") val isNewmessageNotify: Boolean? = false,
        @SerializedName("is_dater") val is_dater: Boolean? = true,
        @SerializedName("is_connector") val is_connector: Boolean? = true,
        @SerializedName("ref_user") val refUser: Int? = 0,
        @SerializedName("updated_at") val updatedAt: String? = "",
        @SerializedName("instagram_name") val instagram_name: String? = "",
        @SerializedName("facebook_name") val facebook_name: String? = "",
        @SerializedName("linkedin_name") val linkedin_name: String? = "",
        @SerializedName("google_name") val google_name: String? = "",
        @SerializedName("recovery_email") val recovery_email: String? = ""
    )
}