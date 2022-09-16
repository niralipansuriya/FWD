package com.swipefwd.ui.auth.sociallogin.google

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GoogleLoginResult(
    val resultStatus: Boolean,
    val profileDetails: GoogleUserDetails?,
    val message: String
) : Parcelable