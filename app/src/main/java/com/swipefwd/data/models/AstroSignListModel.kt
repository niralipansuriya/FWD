package com.swipefwd.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class AstroSignListModel(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("items")
    val items: ArrayList<AstroSignModel>? = arrayListOf(),
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("next")
    val next: String? = "",
    @SerializedName("previous")
    val previous: String? = ""
) {

    @Entity
    data class AstroSignModel(
        @SerializedName("icon")
        var icon: String? = "",
        @SerializedName("id")
        @PrimaryKey
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = "",
        var isSelected: Boolean = false
    )
}
