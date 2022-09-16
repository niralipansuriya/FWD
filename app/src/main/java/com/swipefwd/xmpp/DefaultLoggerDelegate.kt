package com.swipefwd.xmpp

import android.util.Log
import com.swipefwd.xmpp.Logger.LoggerDelegate

/**
 * Default logger delegate implementation which logs in LogCat with [Log].
 * Log tag is set to **UploadService** for all the logs.
 */
class DefaultLoggerDelegate : LoggerDelegate {
    override fun error(tag: String?, message: String?) {
        Log.e(TAG, "$tag - $message")
    }

    override fun error(tag: String?, message: String?, exception: Throwable?) {
        Log.e(TAG, "$tag - $message", exception)
    }

    override fun debug(tag: String?, message: String?) {
        Log.d(TAG, "$tag - $message")
    }

    override fun info(tag: String?, message: String?) {
        Log.i(TAG, "$tag - $message")
    }

    companion object {
        private const val TAG = "XmppService"
    }
}