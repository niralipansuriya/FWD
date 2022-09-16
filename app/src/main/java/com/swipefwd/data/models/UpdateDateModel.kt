package com.swipefwd.data.models

data class UpdateDateModel(
    val code: Int,
    val `data`: UpdateData,
    val message: String,
    val status: String
)

data class UpdateData(
    val date: String
)