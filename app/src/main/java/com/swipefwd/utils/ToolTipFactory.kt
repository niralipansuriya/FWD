package com.swipefwd.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import com.skydoves.balloon.overlay.BalloonOverlayOval
import com.swipefwd.R

class ToolTipFactory : Balloon.Factory() {

    override fun create(context: Context, lifecycle: LifecycleOwner?): Balloon {
        return createBalloon(context) {
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            setLayout(R.layout.layout_custom_tool_tip)
            setArrowSize(10)
            setCornerRadius(0f)
            setWidthRatio(1f)
            setArrowColor(ContextCompat.getColor(context, R.color.blue_gradient_2))
            setArrowOrientation(ArrowOrientation.TOP)
            setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            setArrowPosition(0.5f)
            setElevation(6)
            setIsVisibleOverlay(true) // sets the visibility of the overlay for highlighting an anchor.
            setOverlayColorResource(R.color.white_80) // background color of the overlay using a color resource.
            setOverlayPadding(6f) // sets a padding value of the overlay shape internally.
            setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE) // default is fade.
            setOverlayShape(BalloonOverlayOval)
            setDismissWhenOverlayClicked(false)
            setBalloonAnimation(BalloonAnimation.OVERSHOOT)
            setDismissWhenTouchOutside(false)
            setDismissWhenShowAgain(false)
            setLifecycleOwner(lifecycle)
        }
    }
}