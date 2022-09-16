package com.swipefwd.utils.customprogressbar

import android.content.Context
import android.util.TypedValue


object CalculationUtil {
    fun convertDpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            context.resources.displayMetrics
        ).toInt()
    }
}