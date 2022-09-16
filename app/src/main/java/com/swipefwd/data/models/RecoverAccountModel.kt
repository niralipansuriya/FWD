package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

class RecoverAccountModel (
        @SerializedName("email")
        var email: String? = "",
        @SerializedName("message")
        var message: String? = "",
        @SerializedName("response")
        var response: String? = "",
        @SerializedName("status")
        var status: Boolean
)