package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class VaccinationModel(
    @SerializedName("code")
    var code: Int? = 0,

    @SerializedName("data")
    var vaccinationData: VaccinationData,

    @SerializedName("status")
    var status: String? = "",

    @SerializedName("message")
    var message: String? = ""
) {
    data class VaccinationData(
        @SerializedName("vaccination")
        var vaccination: ArrayList<VaccinationLevel>? = arrayListOf()
    ) {
        data class VaccinationLevel(
            @SerializedName("value")
            var value: String? = "",
            @SerializedName("_id")
            var _id: Int? = 0,
            var isSelected: Boolean = false
        )
    }
}
