package com.swipefwd.ui.auth.sociallogin.facebook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FacebookUserDetails(
    val clientId: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val birthDate: String? = null,
    val gender: String? = null,
    val email: String? = null,
    val profileImage: String? = null,
    val friendPermissionStatus:Boolean = false
) : Parcelable

