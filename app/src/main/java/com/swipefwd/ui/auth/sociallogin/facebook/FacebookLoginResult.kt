package com.swipefwd.ui.auth.sociallogin.facebook

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FacebookLoginResult(
    val resultStatus:Boolean,
    val profileDetails: FacebookUserDetails?,
    val message:String
):Parcelable
