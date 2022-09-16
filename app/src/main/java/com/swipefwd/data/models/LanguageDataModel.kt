package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class LanguageDataModel(
    @SerializedName("code")
    var code: Int? = 0,

    @SerializedName("data")
    var languageData: LanguageData,

    @SerializedName("status")
    var status: String? = "",

    @SerializedName("message")
    var message: String? = ""
) {
    data class LanguageData(
        @SerializedName("langauge")
        var langauge: ArrayList<Language>? = arrayListOf()
    ) {
        data class Language(
            @SerializedName("value")
            var value: String? = "",
            @SerializedName("_id")
            var _id: Int? = 0,
            var isSelected: Boolean = false

        )
    }
}

