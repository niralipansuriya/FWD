package com.swipefwd.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SmokingListModel(
    @SerializedName("count")
    var count: Int? = 0,
    @SerializedName("items")
    var items: ArrayList<SmokingModel>? = arrayListOf(),
    @SerializedName("limit")
    var limit: Int? = 0,
    @SerializedName("next")
    var next: String? = "",
    @SerializedName("previous")
    var previous: String? = ""
) {
    @Entity
    data class SmokingModel(
        @PrimaryKey
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = "",
        var isSelected: Boolean = false
    )
}
