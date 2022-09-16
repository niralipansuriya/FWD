package com.swipefwd.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.swipefwd.R
import com.swipefwd.databinding.FragmentActivityBinding
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppUtils
import com.swipefwd.factory.viewModelFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ActivityFragment : Fragment() {

    private val pagerAdapter by lazy {
        activity?.let { PagerAdapter(childFragmentManager, lifecycle, accountType) }
    }
    private val activityViewModel: ActivityViewModel by viewModels {
        viewModelFactory { ActivityViewModel(requireContext(), AppRepository()) }
    }
    private var accountType = ""
    private var _binding: FragmentActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleScope.launch {
                accountType = activityViewModel.getAccountType.firstOrNull() ?: ""
            }
            pager.adapter = pagerAdapter
            TabLayoutMediator(tabMessages, pager) { tab, position ->
                tabMessages.tabMode = TabLayout.MODE_SCROLLABLE
                tabMessages.setSelectedTabIndicatorColor(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.white
                    )
                )
                when (accountType) {
                    AppUtils.AccountTypes.Matchmaker -> {
                        tab.text = when (position) {
                            0 -> "    ${activity?.getString(R.string.connector_connection, "6")}"
                            else -> activity?.getString(R.string.wallet)
                        }
                    }
                    AppUtils.AccountTypes.Dater -> {
                        tab.text = when (position) {
                            0 -> "    ${activity?.getString(R.string.screen_invites)}"
                            else -> activity?.getString(R.string.wallet)
                        }
                    }
                }
            }.attach()
            arguments?.let {
                pager.postDelayed({
                    pager.currentItem = it.getInt(ARG_POSITION, 0)
                }, 200)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val ARG_POSITION = "position"

        @JvmStatic
        fun newInstance(pos: Int) =
            ActivityFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, pos)
                }
            }
    }
}