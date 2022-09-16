package com.swipefwd.ui.home.message

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.swipefwd.utils.AppUtils

class MessagePagerAdapter(val getAccountType: String, fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = if (AppUtils.AccountTypes.Matchmaker == getAccountType) {
        1
    } else {
        2
    }

    override fun createFragment(position: Int): Fragment {

        if (AppUtils.AccountTypes.Matchmaker == getAccountType) {
            return MessageTribeFragment()

        } else {
            return when (position) {
                0 -> MessageMatchesFragment()
                else -> MessageTribeFragment()
            }
        }

    }
}