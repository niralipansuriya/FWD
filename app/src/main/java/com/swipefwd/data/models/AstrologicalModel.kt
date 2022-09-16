package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class AstrologicalModel(
    @SerializedName("code")
    var code: Int? = 0,
    @SerializedName("data")
    var astrologicalData: AstrologicalData,
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("message")
    var message: String? = ""
) {
    data class AstrologicalData(
        @SerializedName("astrological_sign")
        var astrological_sign: ArrayList<AstrologicalSignLevel>? = arrayListOf()
    ) {
        data class AstrologicalSignLevel(
            @SerializedName("value")
            var value: String? = "",
            @SerializedName("logo")
            var logo: String? = "",
            @SerializedName("_id")
            var _id: Int? = 0,
            var isSelected: Boolean = false
        )
    }
}
