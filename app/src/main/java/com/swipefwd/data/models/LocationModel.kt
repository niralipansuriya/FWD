package com.swipefwd.data.models

data class LocationModel(
    val code: Int,
    val `data`: LocationData,
    val message: String,
    val status: String
)

data class LocationData(
    val lat: Double,
    val location_name: String,
    val long: Double,
    val user_id: Int
)