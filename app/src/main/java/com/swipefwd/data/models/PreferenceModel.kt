package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName


data class PreferenceModel(
    @SerializedName("gender")
    var gender: String? = "",
    @SerializedName("age_start")
    var age_start: Int? = 0,
    @SerializedName("age_end")
    var age_end: Int? = 0,
    @SerializedName("min_distance")
    var min_distance: Int? = 0,
    @SerializedName("max_distance")
    var max_distance: Int? = 0
)