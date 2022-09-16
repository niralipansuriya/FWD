package com.swipefwd.data.models

import com.google.gson.annotations.SerializedName

data class InstitutesModel(
    @SerializedName("html_attributions") val htmlAttributions: ArrayList<Any>? = arrayListOf(),
    @SerializedName("next_page_token") val nextPageToken: String? = "",
    @SerializedName("results") val results: ArrayList<Result>? = arrayListOf(),
    @SerializedName("status") val status: String? = ""
) {
    data class Result(
        @SerializedName("business_status") val businessStatus: String? = "",
        @SerializedName("formatted_address") val formattedAddress: String? = "",
        @SerializedName("geometry") val geometry: Geometry? = Geometry(),
        @SerializedName("icon") val icon: String? = "",
        @SerializedName("name") val name: String? = "",
        @SerializedName("opening_hours") val openingHours: OpeningHours? = OpeningHours(),
        @SerializedName("permanently_closed") val permanentlyClosed: Boolean? = false,
        @SerializedName("photos") val photos: ArrayList<Photo>? = arrayListOf(),
        @SerializedName("place_id") val placeId: String? = "",
        @SerializedName("plus_code") val plusCode: PlusCode? = PlusCode(),
        @SerializedName("rating") val rating: Double? = 0.0,
        @SerializedName("reference") val reference: String? = "",
        @SerializedName("types") val types: ArrayList<String>? = arrayListOf(),
        @SerializedName("user_ratings_total") val userRatingsTotal: Int? = 0
    ) {
        data class Geometry(
            @SerializedName("location") val location: Location? = Location(),
            @SerializedName("viewport") val viewport: Viewport? = Viewport()
        )

        data class Location(
            @SerializedName("lat") val lat: Double? = 0.0,
            @SerializedName("lng") val lng: Double? = 0.0
        )

        data class Viewport(
            @SerializedName("northeast") val northeast: Northeast? = Northeast(),
            @SerializedName("southwest") val southwest: Southwest? = Southwest()
        ) {
            data class Northeast(
                @SerializedName("lat") val lat: Double? = 0.0,
                @SerializedName("lng") val lng: Double? = 0.0
            )

            data class Southwest(
                @SerializedName("lat") val lat: Double? = 0.0,
                @SerializedName("lng") val lng: Double? = 0.0
            )
        }
    }

    data class OpeningHours(
        @SerializedName("open_now") val openNow: Boolean? = false
    )

    data class Photo(
        @SerializedName("height") val height: Int? = 0,
        @SerializedName("html_attributions") val htmlAttributions: ArrayList<String>? = arrayListOf(),
        @SerializedName("photo_reference") val photoReference: String? = "",
        @SerializedName("width") val width: Int? = 0
    )

    data class PlusCode(
        @SerializedName("compound_code") val compoundCode: String? = "",
        @SerializedName("global_code") val globalCode: String? = ""
    )
}