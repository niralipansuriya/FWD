package com.swipefwd.ui.swiping.dater_both

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SwipePagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    private val imageList: ArrayList<String>
) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = imageList.size

    override fun createFragment(position: Int): Fragment =
        SwipeImageFragment.newInstance(imageList[position])
}