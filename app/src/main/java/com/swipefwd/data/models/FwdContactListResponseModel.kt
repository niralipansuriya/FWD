package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class FwdContactListResponseModel(
    @SerializedName("response") val response: String? = "",
    @SerializedName("status") val status: Boolean? = false,
    @SerializedName("Users") val users: ArrayList<User>? = arrayListOf(),
    @SerializedName("pending") val pending: ArrayList<User>? = arrayListOf(),
    @SerializedName("expired") val expired_invitation: ArrayList<ExpiredInvitationModel>? = arrayListOf()
) {
    data class User(
        @SerializedName("image") val image: String? = "",
        @SerializedName("is_profile") val isProfile: Boolean? = false,
        @SerializedName("username") val userNumber: String? = "",
        @SerializedName("user_id") val user_id: Int? = 0,
        @SerializedName("invited") val invited: Boolean? = false,
        @SerializedName("reinvited") val reinvited: Boolean? = false,
        @SerializedName("stage") val stage: String? = "",
    )

    data class ExpiredInvitationModel(
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("invited_by") val invited_by: Int? = 0,
        @SerializedName("phone_number") val phone_number: String? = "",
        @SerializedName("branch_link") val branch_link: String? = "",
        @SerializedName("name") val name: String? = "",
        @SerializedName("stage") val stage: String? = ""
    )
}