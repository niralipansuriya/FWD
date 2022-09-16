package com.swipefwd.data.models


/** this class will hold the phone number that is suspended for get otp after
 * filling invalid otp 3 times */
data class SuspendedPhoneNumber(
    val countryCode: String,
    val phoneNumber: String,
    val suspendStartTime: Long,
    val isFiveMins: Boolean
) {
    fun isEqual(_phoneNumber: String, _countryCode: String): Boolean {
        if (countryCode.trim() != _countryCode.trim()) {
            return false
        }
        if (phoneNumber.trim() != _phoneNumber.trim()) {
            return false
        }
        return true
    }
}
