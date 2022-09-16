package com.swipefwd.animations

import android.animation.ObjectAnimator
import android.app.Dialog
import android.os.Handler
import android.view.View
import android.view.animation.BounceInterpolator


class AnimationBounceUpAndSlideUp {
    val mInterpolator: BounceInterpolator = object : BounceInterpolator() {
        override fun getInterpolation(v: Float): Float {
            return getPowOut(v , 3.0) //Add getPowOut(v,3); for more up animation
        }
    }

    fun doBounceUpAnimation(targetView: View,dialog:Dialog) {
        val animator = ObjectAnimator.ofFloat(targetView , "translationY" , 0f , 30f , -1600f)
        animator.interpolator = mInterpolator
        animator.startDelay = 0
        animator.duration = 1200
        animator.repeatCount = 0
        animator.start()
        Handler(targetView.context.mainLooper).postDelayed(object:Runnable{
            override fun run() {
                dialog.dismiss()
            }

        },1000)
    }

    fun getPowOut(elapsedTimeRate: Float , pow: Double): Float {
        return (1.toFloat() - Math.pow((1 - elapsedTimeRate).toDouble() , pow)).toFloat()
    }
}