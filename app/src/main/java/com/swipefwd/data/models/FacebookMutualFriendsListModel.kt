package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class FacebookMutualFriendsListModel(
    @SerializedName("data") val data: FriendsModel? = FriendsModel(),
    @SerializedName("response") val response: String? = "",
    @SerializedName("status") val status: Boolean? = false
) {
    data class FriendsModel(
        @SerializedName("mutual") val mutual: List<Int?>? = listOf()
    )
}