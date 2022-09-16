package com.swipefwd.data.models
data class UserTypeModel(
    val code: Int,
    val `data`: Data,
    val message: String,
    val status: String
)

data class Data(
    val user_type: Int
)