package com.swipefwd.utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2


class CustomViewPager(context: Context?, attrs: AttributeSet?) :
    ViewPager(context!!, attrs) {
    private var setEnabled = true
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (setEnabled) {
            super.onTouchEvent(event)
        } else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (setEnabled) {
            super.onInterceptTouchEvent(event)
        } else false
    }

    fun setPagingEnabled(enabled: Boolean) {
        this.setEnabled = enabled
    }
}