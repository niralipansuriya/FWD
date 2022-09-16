package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class ChildrenModel(

    @SerializedName("code")
    var code: Int? = 0,

    @SerializedName("data")
    var childrenData: ChildrenData,

    @SerializedName("status")
    var status: String? = "",

    @SerializedName("message")
    var message: String? = ""
) {
    data class ChildrenData(
        @SerializedName("children")
        var children: ArrayList<ChildrenLevel>? = arrayListOf()
    ) {
        data class ChildrenLevel(
            @SerializedName("value")
            var value: String? = "",
            @SerializedName("_id")
            var _id: Int? = 0,
            var isSelected: Boolean = false
        )
    }
}

