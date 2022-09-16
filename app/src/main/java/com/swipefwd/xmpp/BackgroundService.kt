package com.swipefwd.xmpp

import android.app.Service
import android.content.Intent
import android.os.*

/**
 * Service with a background worker thread.
 */
open class BackgroundService : Service() {
    private var mWorkerThread: HandlerThread? = null
    private var mHandler: Handler? = null
    private var mWakeLock: PowerManager.WakeLock? = null
    override fun onCreate() {
        super.onCreate()
        val pm = getSystemService(POWER_SERVICE) as PowerManager
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, javaClass.simpleName)
        mWakeLock!!.acquire()
        mWorkerThread = HandlerThread(
            javaClass.simpleName,
            Process.THREAD_PRIORITY_BACKGROUND
        )
        mWorkerThread!!.start()
        mHandler = Handler(mWorkerThread!!.looper)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mWorkerThread!!.quitSafely()
        mWakeLock!!.release()
    }

    protected fun enqueueJob(job: Runnable?) {
        mHandler!!.post(job!!)
    }
}