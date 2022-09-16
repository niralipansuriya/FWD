package com.swipefwd.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import com.skydoves.balloon.Balloon

object AnimationSlideVisibility {
    fun slideDown(view: View, toolTip: Balloon) {
        view.animate()
            .translationY(view.height.toFloat())
            .alpha(0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    // superfluous restoration
                    view.visibility = View.GONE
                    view.alpha = 1f
                    view.translationY = 0f
                    toolTip.dismiss()
                }
            })
    }

    fun slideUp(view: View, toolTip: Balloon) {
        view.visibility = View.VISIBLE
        view.alpha = 0f
        if (view.height > 0) {
            slideUpNow(view, toolTip)
        } else {
            // wait till height is measured
            view.post(Runnable { slideUpNow(view, toolTip) })
        }
    }

    private fun slideUpNow(view: View, toolTip: Balloon) {
        view.translationY = view.height.toFloat()
        view.animate()
            .translationY(0F)
            .alpha(1f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.visibility = View.VISIBLE
                    view.alpha = 1f
//                    toolTip.dismiss()
                }
            })
    }
}