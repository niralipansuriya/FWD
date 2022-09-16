package com.swipefwd.data.models

data class RefreshTokenModel(
    val code: Int,
    val `data`: DataClass,
    val message: String,
    val status: String
)

data class DataClass(
    val token: String
)