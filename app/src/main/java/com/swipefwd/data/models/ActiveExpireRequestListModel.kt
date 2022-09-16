package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class ActiveExpireRequestListModel(
    @SerializedName("expire") val expire: List<Expire> = listOf(),
    @SerializedName("invitation_expired") val invitation_expired: List<Expire> = listOf(),
    @SerializedName("pending") val pending: List<Pending> = listOf(),
    @SerializedName("invitation_pending") val invitation_pending: List<Pending> = listOf(),
    @SerializedName("response") val response: String? = "",
    @SerializedName("detail") val errorDetail: String? = "",
    @SerializedName("status") val status: Boolean? = false
) {
    data class Expire(
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("image") val image: String? = "",
        @SerializedName("is_popup_display") val isPopupDisplay: Boolean? = false,
        @SerializedName("receiver_id") val receiverId: Int? = 0,
        @SerializedName("sender_id") val senderId: Int? = 0,
        @SerializedName("sender_user_type") val senderUserType: Int? = 0,
        @SerializedName("stage") val stage: String? = "",
        @SerializedName("username") val username: String? = ""
    )

    data class Pending(
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("image") val image: String? = "",
        @SerializedName("is_popup_display") val isPopupDisplay: Boolean? = false,
        @SerializedName("receiver_id") val receiverId: Int? = 0,
        @SerializedName("sender_id") val senderUserId: Int? = 0,
        @SerializedName("sender_user_type") val senderUserType: Int? = 0,
        @SerializedName("stage") val stage: String? = "",
        @SerializedName("username") val username: String? = "",
        @SerializedName("mobile_number") val mobileNumber: String? = "",
        var isSelected: Boolean = false
    )
}