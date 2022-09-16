package com.swipefwd.ui.home.message

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout.MODE_FIXED
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import com.google.android.material.tabs.TabLayoutMediator
import com.swipefwd.R
import com.swipefwd.databinding.FragmentMessagesBinding
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MessagesFragment : Fragment() {
    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!
    private val fwdDataStore by lazy { activity?.let { InternalAppDataStore(it) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            lifecycleScope.launch {
                val getAccountType =
                    fwdDataStore!!.getString(PreferenceKeys.PREF_ACCOUNT_TYPE).firstOrNull()
                val pagerAdapter =
                    MessagePagerAdapter(getAccountType!!, childFragmentManager, lifecycle)
                pagerMessages.adapter = pagerAdapter
                pagerMessages.isUserInputEnabled = false
                if (AppUtils.AccountTypes.Matchmaker == getAccountType) {
                    txtHeader.apply {
                        gravity = Gravity.START
                        setPadding(80,0,0,0)
                    }
                } else {
                    txtHeader.apply {
                        gravity = Gravity.CENTER
                        setPadding(0,0,0,0)
                    }
                }
                TabLayoutMediator(tabMessages, pagerMessages) { tab, position ->
                    if (AppUtils.AccountTypes.Matchmaker == getAccountType) {
                        tabMessages.tabMode = MODE_SCROLLABLE
                        tabMessages.setSelectedTabIndicatorColor(
                            ContextCompat.getColor(
                                requireActivity(),
                                R.color.white
                            )
                        )
                        tab.text = "    (Your Tribe)"
                    } else {
                        tabMessages.tabMode = MODE_FIXED
                        tab.text = when (position) {
                            0 -> activity?.getString(R.string.matches)
                            else -> activity?.getString(R.string.tribe)
                        }
                    }
                }.attach()
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}