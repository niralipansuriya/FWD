package com.swipefwd.animations

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.BounceInterpolator

class AnimationBounceDown {
    fun doBounceDownAnimation(targetView: View, repeatCount: Int = 0, duration: Long = 250) {
        val interpolator: BounceInterpolator = object : BounceInterpolator() {
            override fun getInterpolation(v: Float): Float {
                return getPowIn(v , 2.0) //Add getPowIn(v,3); for more down animation
            }
        }
        val animator = ObjectAnimator.ofFloat(targetView , "translationY" , 0f , 30f , 0f)
        animator.interpolator = interpolator
        animator.startDelay = 0
        animator.duration = 250
        animator.repeatCount = 0
        animator.start()
    }

    fun getPowIn(elapsedTimeRate: Float , pow: Double): Float {
        return Math.pow(elapsedTimeRate.toDouble() , pow).toFloat()
    }
}