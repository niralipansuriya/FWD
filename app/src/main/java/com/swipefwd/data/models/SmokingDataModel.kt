package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class SmokingDataModel(
    @SerializedName("code")
    var code: Int? = 0,

    @SerializedName("data")
    var smokingData: SmokingData,

    @SerializedName("status")
    var status: String? = "",

    @SerializedName("message")
    var message: String? = ""
) {
    data class SmokingData(
        @SerializedName("smoking")
        var smoking: ArrayList<Smoking>? = arrayListOf()
    ) {
        data class Smoking(
            @SerializedName("value")
            var value: String? = "",
            @SerializedName("_id")
            var _id: Int? = 0,
            var isSelected: Boolean = false

        )
    }
}
