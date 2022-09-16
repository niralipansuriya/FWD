package com.swipefwd.data.models

data class DateGetModel(
    val code: Int,
    val `data`: DataDate,
    val message: String,
    val status: String
)

data class DataDate(
    val date: String,
    val type: String,
    val user_id: Int
)