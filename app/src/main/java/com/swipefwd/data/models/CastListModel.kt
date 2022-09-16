package com.swipefwd.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CastListModel(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("items")
    val items: ArrayList<CastModel>? = arrayListOf(),
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("next")
    val next: String? = "",
    @SerializedName("previous")
    val previous: String? = ""
) {

    @Entity
    data class CastModel(
        @PrimaryKey
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )
}
