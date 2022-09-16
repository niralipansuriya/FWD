package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class DaterConnectionResponseModel(
    @SerializedName("response") val response: String? = "",
    @SerializedName("status") val status: Boolean? = false,
    @SerializedName("completed") val completed: List<User>? = listOf(),
    @SerializedName("pending") val pending: List<User>? = listOf(),
    @SerializedName("expire") val expire: List<User>? = arrayListOf(),
    @SerializedName("invitation") val invitation: List<User>? = arrayListOf(),
    @SerializedName("profile_photo") val profile_photo: Boolean? = false,
    @SerializedName("is_request_pending") val is_request_pending: Boolean? = false
) {
    data class User(
        @SerializedName("id") val primaryKeyId: Int? = 0,
        @SerializedName("username") var username: String? = "",
        @SerializedName("image") val image: String? = "",
        @SerializedName("userid") var userid: String? = "0",
        @SerializedName("phone_number") var phone_number: String? = "",
        @SerializedName("gender") var gender: String = "M",
        @SerializedName("is_popup_display") var is_popup_display: Boolean? = false,
        @SerializedName("stage") var stage: String? = "",
        var daterIndex: Int = 0,
        var isAvailable: Boolean = false,
        var presenceMode: Int? = 0
    )
}
/*



data:{
    "response":"
    "status":false
    "profile_photo":false
    "is_request_pending":false
    "completed":[
        {
            "id":0
            "username":""
            "image":""
            "userid":""
            "phone_number":""
            "gender":""
            "is_popup_display":false
            "stage":""
        }
    ]
    "pending":[
        {
            "id":0
            "username":""
            "image":""
            "userid":""
            "phone_number":""
            "gender":""
            "is_popup_display":false
            "stage":""
        }
    ]
    "expire":[
        {
            "id":0
            "username":""
            "image":""
            "userid":""
            "phone_number":""
            "gender":""
            "is_popup_display":false
            "stage":""
        }
    ]
    "invitation":[
        {
            "id":0
            "username":""
            "image":""
            "userid":""
            "phone_number":""
            "gender":""
            "is_popup_display":false
            "stage":""
        }
    ]

}*/
