package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class InstagramProfileModel(
    @SerializedName("id") val id: String? = "",
    @SerializedName("media") val media: Media? = Media(),
    @SerializedName("username") val username: String? = "",
    @SerializedName("account_type") val accountType: String? = "",

) {
    data class Media(
        @SerializedName("data") val data: ArrayList<Data>? = arrayListOf(),
        @SerializedName("paging") val paging: Paging? = Paging(),
    )

    data class Data(
        @SerializedName("id") val id: String? = "",
    )

    data class Paging(
        @SerializedName("cursors") val cursors: Cursors? = Cursors(),
        @SerializedName("next") val next: String? = "",
        @SerializedName("previous") val previous: String? = "",
    )

    data class Cursors(
        @SerializedName("after") val after: String? = "",
        @SerializedName("before") val before: String? = "",
    )
}