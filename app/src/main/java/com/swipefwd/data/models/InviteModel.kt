package com.swipefwd.data.models
import com.google.gson.annotations.SerializedName


data class InviteModel(
    @SerializedName("id")
    var id: Id = Id(),
    @SerializedName("message")
    var message: String = "",
    @SerializedName("response")
    var response: String = "",
    @SerializedName("status")
    var status: Boolean =false
){
    data class Id(
        @SerializedName("phone_number")
        var phoneNumber: String = "",
        @SerializedName("ref_profile_suggest")
        var refProfileSuggest: String = "",
        @SerializedName("ref_sender")
        var refSender: Int = 0
    )
}

