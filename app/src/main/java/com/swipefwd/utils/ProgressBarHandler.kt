package com.swipefwd.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import com.swipefwd.R
import com.swipefwd.utils.customLoader.LoadingView
import pl.droidsonroids.gif.GifImageView


// class to show/hide progressbar
class ProgressBarHandler(private var context: Activity) {

    companion object : SingletonHolder<ProgressBarHandler, Activity>(::ProgressBarHandler)

    private val dialog: Dialog = Dialog(context)
  //  lateinit private var loadingView : LoadingView
 //   lateinit private var loadingView : GifImageView

    fun show() {
        if (!dialog.isShowing && !context.isFinishing) {
            dialog.show()
          //  loadingView.start()
         //   loadingView.visibility = View.VISIBLE
        }
    }

    fun dismiss() {
        try {
            if (dialog.isShowing && !context.isFinishing) {
            //    loadingView.stop()
            //    loadingView.visibility = View.GONE
                dialog.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    init {
        if (dialog.window != null) {
            dialog.window!!.requestFeature(1)
        }
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_progress)
        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(
                Color.TRANSPARENT
            )
        )
        dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

     //   loadingView = dialog.findViewById<LoadingView>(R.id.loadingView)
     //   loadingView = dialog.findViewById<GifImageView>(R.id.loadingView)
    }
}