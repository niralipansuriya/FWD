package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class ReligionModel(

    @SerializedName("code")
    var code: Int? = 0,

    @SerializedName("data")
    var religiondata: ReligionData,

    @SerializedName("status")
    var status: String? = "",

    @SerializedName("message")
    var message: String? = ""
) {
    data class ReligionData(
        @SerializedName("religion")
        var religion: ArrayList<ReligionLevel>? = arrayListOf()
    ) {
        data class ReligionLevel(
            @SerializedName("value")
            var value: String? = "",
            @SerializedName("_id")
            var _id: Int? = 0,
            var isSelected: Boolean = false
        )
    }
}
