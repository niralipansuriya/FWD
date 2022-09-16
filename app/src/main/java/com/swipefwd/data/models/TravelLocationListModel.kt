package com.swipefwd.data.models

data class TravelLocationListModel(
    var city: String = "",
    var country: String = "",
    val placeId: String = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0
)
