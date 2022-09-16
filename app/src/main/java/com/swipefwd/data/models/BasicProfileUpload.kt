package com.swipefwd.data.models

data class BasicProfileUpload(
    val code: Int,
    val `data`: DataProfile,
    val message: String,
    val status: String
)

data class DataProfile(
    val profile_image: String,
    val user_id: Int
)