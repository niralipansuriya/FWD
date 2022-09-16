package com.swipefwd.ui.auth.sociallogin.google

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoogleUserDetails(
    val clientId: String,
    val firstName: String?,
    val lastName:String?,
    val profileImage:String?,
    val email: String,
   // val dob: String?,//uncommented by nirali
    // val gender: String?//uncommented by nirali
):Parcelable
