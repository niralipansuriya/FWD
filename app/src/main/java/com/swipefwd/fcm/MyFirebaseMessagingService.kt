package com.swipefwd.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "Firebase"

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.e(TAG , "DEVICE_TOKEN == $p0")
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.e(TAG, "PUSH NOTIFICATION == $p0")
        Log.e(TAG, "PUSH NOTIFICATION == ${p0.data}")
    }
}