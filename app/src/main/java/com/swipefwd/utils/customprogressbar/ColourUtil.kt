package com.swipefwd.utils.customprogressbar

import android.R.color;

object ColourUtil {
    var mColourArray = ArrayList<Int>()
    fun getmColourArray(): ArrayList<Int> {
        mColourArray.add(color.holo_blue_bright)
        mColourArray.add(color.holo_blue_dark)
        mColourArray.add(color.holo_blue_light)
        mColourArray.add(color.holo_green_dark)
        mColourArray.add(color.holo_green_light)
        mColourArray.add(color.holo_orange_dark)
        mColourArray.add(color.holo_orange_light)
        mColourArray.add(color.holo_purple)
        mColourArray.add(color.holo_red_dark)
        mColourArray.add(color.holo_red_light)
        return mColourArray
    }
}