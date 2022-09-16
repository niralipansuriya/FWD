package com.swipefwd.utils

import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Point
import android.graphics.Shader

class GradientManager(context: Context?, private val mSize: Point) {
     val randomLinearGradient: LinearGradient
         get() = LinearGradient(
            0F, 0F, mSize.x.toFloat(), mSize.y.toFloat(), intArrayOf(
                Color.parseColor("#6C67EF"),
                Color.parseColor("#6C67EF"),
                Color.parseColor("#6C67EF"),
                Color.parseColor("#5EA3FB"),
                Color.parseColor("#5EA3FB"),
                Color.parseColor("#5EA3FB"),
                Color.parseColor("#5EA3FB")
            ), null,
            Shader.TileMode.MIRROR
        )

}