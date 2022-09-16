package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class InviteConnectorResponseModel(
    @SerializedName("message") val message: String? = "",
    @SerializedName("status") val status: String? = "",
    @SerializedName("data") val data: Data? = null,
    @SerializedName("code") val code: Int? = null
) {
    data class Data(
        @SerializedName("left_invitation") val leftInvitation: Int? = null,
        @SerializedName("connector_info") val connectorInfo: List<ConnectorInfo>? = arrayListOf()
    ){
        data class ConnectorInfo(
            @SerializedName("_id") val id: Int? = null,
            @SerializedName("connector_id") val profilePic:Int? = null,
            @SerializedName("dater_id") val daterId: Int? = null,
            @SerializedName("is_active") val isActive: Int? = null,
            @SerializedName("created_date") val createdDate: String? = "",
            @SerializedName("modified_date") val modifiedDate: String? = "",
            @SerializedName("is_accepted") val isAccepted: Int? = null,
            @SerializedName("invitation_expire") val invitationExpire: Int? = null
        )
    }
}