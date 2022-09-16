package com.swipefwd.utils.customprogressbar

import android.graphics.Color
import android.graphics.Paint

class PercentStyle {
    private var align: Paint.Align? = null
    var textSize = 0f
    var isPercentSign = false

    /**
     * With this you can set a custom text which should get displayed right
     * behind the number of the progress. Per default it displays a *%*.
     *
     * @param customText
     * The custom text you want to display.
     * @since 1.4.0
     */
    var customText = "%"

    /**
     * Set the color of the text that display the current progress. This will
     * also change the color of the text that normally represents a *%*.
     *
     * @param textColor
     * the color to set the text to.
     * @since 1.4.0
     */
    var textColor: Int = Color.BLACK

    constructor() {
        // do nothing
    }

    constructor(align: Paint.Align?, textSize: Float, percentSign: Boolean) : super() {
        this.align = align
        this.textSize = textSize
        isPercentSign = percentSign
    }

    fun getAlign(): Paint.Align? {
        return align
    }

    fun setAlign(align: Paint.Align?) {
        this.align = align
    }
}