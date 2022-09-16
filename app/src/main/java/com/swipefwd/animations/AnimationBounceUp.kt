package com.swipefwd.animations

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.BounceInterpolator

class AnimationBounceUp {
    fun doBounceUpAnimation(targetView: View, repeatCount: Int = 1, duration: Long = 500) {
        val interpolator: BounceInterpolator = object : BounceInterpolator() {
            override fun getInterpolation(v: Float): Float {
                return getPowOut(v , 3.0) //Add getPowOut(v,3); for more up animation
            }
        }
        val animator = ObjectAnimator.ofFloat(targetView , "translationY" , 0f , 30f , 0f)
        animator.interpolator = interpolator
        animator.startDelay = 0
        animator.duration = duration
        animator.repeatCount = repeatCount
        animator.start()
    }

    fun getPowOut(elapsedTimeRate: Float , pow: Double): Float {
        return (1.toFloat() - Math.pow((1 - elapsedTimeRate).toDouble() , pow)).toFloat()
    }
}