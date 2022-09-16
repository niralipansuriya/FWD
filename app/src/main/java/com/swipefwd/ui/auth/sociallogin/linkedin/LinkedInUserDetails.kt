package com.swipefwd.ui.auth.sociallogin.linkedin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LinkedInUserDetails(
    val clientId: String,
    val firstName: String?,
    val lastName:String?,
    val profileImage:String?,
    val email: String
): Parcelable
