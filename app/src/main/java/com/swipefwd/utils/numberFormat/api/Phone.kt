package com.swipefwd.utils.numberFormat.api

data class Phone(
    val nationalNumber: Long?,
    val countryCode: Int?,
    val rawInput: String?,
    val numberOfLeadingZeros: Int?
)
