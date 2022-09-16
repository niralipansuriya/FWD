package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class UserImagesResponseModel(
    @SerializedName("count") val count: Int? = 0,
    @SerializedName("items") val items: ArrayList<Item>? = arrayListOf(),
    @SerializedName("limit") val limit: Int? = 0,
    @SerializedName("next") var next: String? = "",
    @SerializedName("previous") var previous: String? = ""
) {
    data class Item(
        @SerializedName("id") var id: Int? = 0,
        @SerializedName("image") var image: String? = "",
        @SerializedName("is_profile") var isProfile: Boolean? = false,
        @SerializedName("is_instagram") var is_instagram: Boolean? = false,
        @SerializedName("user_picture") var userPicture: Int? = 0,
        @SerializedName("image_url") var imageUrl: String? = "",
        @SerializedName("is_more") var is_more: Boolean? = false,
        var isInAppropriate :Boolean=false
    )
}