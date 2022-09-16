package com.swipefwd.ui.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.swipefwd.ui.home.wallet.WalletFragment
import com.swipefwd.utils.AppUtils

class PagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    private val accountType: String
) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> when (accountType) {
                AppUtils.AccountTypes.Matchmaker -> {
                    FragmentMatchmakerActivity()
                }
                else -> {
                    InviteListFragment()
                }
            }
            else -> WalletFragment()
        }
    }
}