package com.swipefwd.ui.auth.sociallogin.linkedin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinkedInLoginResult(
    val resultStatus: Boolean,
    val profileDetails: LinkedInUserDetails?,
    val message: String
) : Parcelable
