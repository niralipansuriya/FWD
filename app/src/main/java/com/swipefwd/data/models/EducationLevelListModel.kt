package com.swipefwd.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class EducationLevelListModel(
    @SerializedName("count")
    var count: Int? = 0,
    @SerializedName("items")
    var items: ArrayList<LevelModel>? = arrayListOf(),
    @SerializedName("limit")
    var limit: Int? = 0,
    @SerializedName("previous")
    var previous: String? = "",
    @SerializedName("next")
    var next: String? = ""
) {

    @Entity
    data class LevelModel(

        @SerializedName("id")
        var id: Int? = 0,
        @PrimaryKey
        @SerializedName("name")
        var name: String = ""
    ) {
        override fun toString(): String {
            return name!!
        }
    }
}
