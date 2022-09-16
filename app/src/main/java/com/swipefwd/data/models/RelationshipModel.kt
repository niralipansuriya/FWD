package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class RelationshipModel(
    @SerializedName("code")
    var code: Int? = 0,
    @SerializedName("data")
    var relationshipData: RelationShipData,
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("message")
    var message: String? = ""
) {
    data class RelationShipData(
        @SerializedName("relationship_level")
        var relationship_level: ArrayList<RelationshipLevel>? = arrayListOf())
    {
        data class RelationshipLevel(
            @SerializedName("value")
            var value: String? = "",
            @SerializedName("_id")
            var _id: Int? = 0,
            var isSelected: Boolean = false

        )
    }
}
