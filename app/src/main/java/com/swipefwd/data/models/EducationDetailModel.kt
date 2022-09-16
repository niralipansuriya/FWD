package com.swipefwd.data.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/*
data class EducationDetailModel(
    val code: Int,
    val `data`: EducationData,
    val message: String,
    val status: String
)

data class EducationData(
    val education: List<Education>
)

data class Education(
    val education_level: String
)*/

data class EducationDetailModel(
    @SerializedName("code")
    var code: Int? = 0,
    /*@SerializedName("items")
    var items: ArrayList<LevelModel>? = arrayListOf(),*/
    @SerializedName("data")
    var data: DataEducatiton,
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("status")
    var status: String? = ""
) {

    data class DataEducatiton(

        @SerializedName("education")
        var education: ArrayList<EducationData>? = arrayListOf()
    ) {
        @Entity
        data class EducationData(
            @SerializedName("_id")
            var _id: Int? = 0,
            @SerializedName("education_level")
            var education_level: String? = ""
        )



    }

}

