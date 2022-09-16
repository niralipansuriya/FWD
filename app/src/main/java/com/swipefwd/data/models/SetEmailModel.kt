package com.swipefwd.data.models

data class SetEmailModel(
    val code: Int,
    val `data`: SetEmailData,
    val message: String,
    val status: String
)

data class SetEmailData(
    val email: String
)