package com.swipefwd.animations

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.app.Activity
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.balloon
import com.skydoves.balloon.createBalloon

class SlideView {
    fun slide(view: View, currentPadding: Int, newPadding: Int){

        val slideAnimator = ValueAnimator.ofInt(currentPadding, newPadding).setDuration(800)

        /* We use an update listener which listens to each tick
         * and manually updates the height of the view  */

        slideAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            view.setPadding(0, 0, 0, value)
            view.requestLayout()
        }

        /*  We use an animationSet to play the animation  */

        val animationSet = AnimatorSet()
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.play(slideAnimator);
        animationSet.start()
    }

    fun slideBalloon(activity: Activity, balloonBuilder: Balloon.Builder?, balloon: Balloon, anchorView: View, currentHeight: Int, newHeight: Int){

        val slideAnimator = ValueAnimator.ofInt(500, 1000).setDuration(3000)

        /* We use an update listener which listens to each tick
         * and manually updates the height of the view  */

        slideAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
//            balloonBuilder!!.setHeight(1000)
            balloon.getContentView().layoutParams.height = value
            balloon.getContentView().requestLayout()
        }

        /*  We use an animationSet to play the animation  */

        val animationSet = AnimatorSet()
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.play(slideAnimator);
        animationSet.start()
    }
}