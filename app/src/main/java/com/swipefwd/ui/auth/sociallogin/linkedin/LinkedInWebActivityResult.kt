package com.swipefwd.ui.auth.sociallogin.linkedin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LinkedInWebActivityResult(
    val resultStatus: Boolean,
    val code: String?,
    val message: String
) : Parcelable
