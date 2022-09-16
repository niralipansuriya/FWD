package com.swipefwd.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.swipefwd.R
import com.swipefwd.databinding.FragmentInviteListBinding
import com.swipefwd.data.models.ActiveExpireRequestListModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class InviteListFragment : Fragment() {

    private var _binding: FragmentInviteListBinding? = null
    private val binding get() = _binding!!
    private val activityViewModel: ActivityViewModel by viewModels {
        viewModelFactory { ActivityViewModel(requireContext(), AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(requireActivity())
    }
    private var mPendingAdapter: InvitesPendingAdapter? = null
    private var mExpiredAdapter: InvitesExpiredAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInviteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getActivePendingRequestList()
        initObservers()
    }

    private fun init() {
        binding.apply {
            ivClose.setOnClickListener {
                searchContact.setText("")
            }
            searchContact.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    ivClose.visibility = if (s.toString().isNotEmpty()) {
                        View.VISIBLE
                    } else {
                        View.INVISIBLE
                    }
                    mPendingAdapter?.search(s.toString()) {
                        if (it) {
                            rvActiveList.visibility = View.GONE
                            viewPending.visibility = View.GONE
                            txtActiveRequest.visibility = View.GONE
                        } else {
                            rvActiveList.visibility = View.VISIBLE
                            viewPending.visibility = View.VISIBLE
                            txtActiveRequest.visibility = View.VISIBLE
                        }
                    }
                    mExpiredAdapter?.search(s.toString()) {
                        if (it) {
                            rvExpiredList.visibility = View.GONE
                            viewExpired.visibility = View.GONE
                            txtExpiredRequest.visibility = View.GONE
                        } else {
                            rvExpiredList.visibility = View.VISIBLE
                            viewExpired.visibility = View.VISIBLE
                            txtExpiredRequest.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getActivePendingRequestList() {
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog(){getActivePendingRequestList()}
//                AppUtils.showMessageOK(
//                    requireActivity(),
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getActivePendingRequestList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    activityViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        activityViewModel.getActivityExpiredRequestList(
                            "Bearer $authToken"
                        )
                    }
                }
            }
        }
    }

    private fun initObservers() {
        activityViewModel.apply {
            showLoading.observe(requireActivity(), {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            })
            errorMessage.observe(requireActivity(), {
                Log.e("TAG", "ERROR === $it")
                requireActivity().showSnackBar(binding.layoutMain, it)
            })
            activePendingData.observe(requireActivity(), { listResponse ->
                if (listResponse.response == "success") {
                    try {
                        val pendingList =
                            listResponse.pending + listResponse.invitation_pending
                        val expiredList =
                            listResponse.expire + listResponse.invitation_expired
                        binding.apply {
                            if (pendingList.isEmpty() && expiredList.isEmpty()) {
                                txtNoRecordFound.visibility = View.VISIBLE
                            } else {
                                if (pendingList.isNotEmpty()) {
                                    layoutSearch.visibility = View.VISIBLE
                                    txtActiveRequest.apply {
                                        visibility = View.VISIBLE
                                        text =
                                            getString(
                                                R.string.pending_request,
                                                pendingList.size.toString()
                                            )
                                    }
                                    rvActiveList.apply {
                                        viewPending.visibility = View.VISIBLE
                                        visibility = View.VISIBLE
                                        mPendingAdapter =
                                            InvitesPendingAdapter(requireActivity()).apply {
                                                addAll(pendingList as ArrayList<ActiveExpireRequestListModel.Pending>)
                                            }
                                        adapter = mPendingAdapter
                                        setDivider()
                                    }
                                }

                                if (expiredList.isNotEmpty()) {
                                    layoutSearch.visibility = View.VISIBLE
                                    txtExpiredRequest.apply {
                                        visibility = View.VISIBLE
                                        text =
                                            getString(
                                                R.string.expired_invites,
                                                expiredList.size.toString()
                                            )
                                    }
                                    rvExpiredList.apply {
                                        viewExpired.visibility = View.VISIBLE
                                        visibility = View.VISIBLE
                                        mExpiredAdapter =
                                            InvitesExpiredAdapter(requireActivity()).apply {
                                                addAll(expiredList as ArrayList<ActiveExpireRequestListModel.Expire>)
                                            }
                                        adapter = mExpiredAdapter
                                        setDivider()
                                    }
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        binding.txtNoRecordFound.visibility = View.VISIBLE
                    }
                }
            })
            activePendingError.observe(requireActivity(), {
                Log.e("TAG", "ERROR === $it")
                requireActivity().showSnackBar(binding.layoutMain, it.errorDetail.toString())
            })
        }
    }
}