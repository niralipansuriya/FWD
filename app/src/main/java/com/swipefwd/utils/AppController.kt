package com.swipefwd.utils

import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.swipefwd.BuildConfig
import com.swipefwd.xmpp.XmppService
import com.swipefwd.xmpp.XmppServiceCommand
import org.jivesoftware.smack.packet.Presence
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class AppController : Application(), LifecycleObserver {

    companion object {
        var context: Context? = null

        const val ACTION_INTENT_TYPING = "ACTION_TYPING"
        const val ACTION_ONLINE_OFFLINE = "ACTION_STATUS"
        const val ACTION_INTENT_MESSAGE_INCOMING = "ACTION_INTENT_MESSAGE_INCOMING"
        const val VOICE_FILE_NAME_PREFIX = "VOICE_"
        const val VOICE_FILE_NAME_SUFFIX = ".3gp"
        const val ACTION_INTENT_IMAGE_INCOMING = "ACTION_INTENT_IMAGE_INCOMING"
        const val ACTION_INTENT_VOICE_INCOMING = "ACTION_INTENT_VOICE_INCOMING"
        const val ACTION_INTENT_VIDEO_INCOMING = "ACTION_INTENT_VIDEO_INCOMING"
        var psstBoolean :Boolean= false
    }

    override fun onCreate() {
        super.onCreate()

        Firebase.initialize(this)
        context  =applicationContext
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        getKeyHash("SHA")
        //https://base64.guru/converter/decode/hex
    }

    private fun getKeyHash(hashStretagy: String) {
        val info: PackageInfo
        try {
            info = packageManager.getPackageInfo(
                BuildConfig.APPLICATION_ID,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                var md: MessageDigest
                md = MessageDigest.getInstance(hashStretagy)
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.e("KeyHash  -->>>>>>>>>>>>", something)

                // Notification.registerGCM(this);
            }
        } catch (e1: PackageManager.NameNotFoundException) {
            Log.e("name not found", e1.toString())
        } catch (e: NoSuchAlgorithmException) {
            Log.e("no such an algorithm", e.toString())
        } catch (e: Exception) {
            Log.e("exception", e.toString())
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        try{
            Log.e("background", "yes")
            XmppServiceCommand.disconnect(this)
        }catch (e:java.lang.Exception)
        {
        }

        //XmppServiceCommand.connect(this, XmppService.getXmppAccount())
        /*XmppServiceCommand.setPresence(
            this, Presence.Type.unavailable.ordinal,
            Presence.Mode.available.ordinal,
            "dnd"
        )*/
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        try{
            Log.e("forground", "yes")
            XmppServiceCommand.connect(this, XmppService.getXmppAccount())
        }catch (e:Exception)
        {

        }

    }
}