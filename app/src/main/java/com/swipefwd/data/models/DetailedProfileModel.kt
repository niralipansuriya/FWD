package com.swipefwd.data.models

data class DetailedProfileModel(
    val code: Int,
    val `data`: DetailedData,
    val message: String,
    val status: String
)

data class DetailedData(
    val astrological_sign: String,
    val bio: String,
    val children: String,
    val dob: String,
    val education_level: String,
    val first_name: String,
    val gender: Int,
    val graduation_year: Int,
    val height: Int,
    val institute: String,
    val is_vaccinated: String,
    val language: String,
    val last_name: String,
    // val latitude: Int,
    val latitude: Double,
    val location_name: String,
    // val longitude: Int,
    val longitude: Double,
    val occupation: String,
    val profile_complete_per: Int,
    val profile_image: String,
    val relation_interest: String,
    val religion: String,
    val smoke: String,
    val travel_latitude: Double,
    val travel_location_name: String,
    val travel_longitude: Double,
    val user_id: Int,
    val user_type: Int
)