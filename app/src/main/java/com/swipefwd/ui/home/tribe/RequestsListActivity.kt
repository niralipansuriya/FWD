package com.swipefwd.ui.home.tribe

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.JsonObject
import com.swipefwd.R
import com.swipefwd.databinding.ActivityRequestsListBinding
import com.swipefwd.databinding.DialogSinglePlayerLimitBinding
import com.swipefwd.databinding.DialogUserAddedBinding
import com.swipefwd.data.models.ActiveExpireRequestListModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.RoundedImageView
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.xmpp.XmppServiceCommand
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class RequestsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestsListBinding
    private val mActivity by lazy {
        this
    }
    private var mAdapter: ActiveTribeRequestAdapter? = null
    private var mExpiredAdapter: ExpiredTribeRequestAdapter? = null
    private val homeViewModel: HomeFragmentViewModel by viewModels {
        viewModelFactory { HomeFragmentViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var connectorSize = 1

    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            ivBack.setOnClickListener {
                onBackPressed()
            }
            btnAddToTribe.setOnClickListener {
                lifecycleScope.launch {
                    Log.d(
                        "tAG:",
                        "count==> ${homeViewModel.getRemainingConnector.firstOrNull() ?: 5}"
                    )
                    if (connectorSize > homeViewModel.getRemainingConnector.firstOrNull() ?: 5) {
                        openSinglePlayerLimitDialog()
                    } else {
                        openAddedUserDialog()
                    }
                }
            }
            ivSearchClose.setOnClickListener {
                searchRequest.setText("")
                txtNoRecordFound.visibility = View.GONE
            }
            searchRequest.addTextChangedListener(object : TextWatcher {
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
                    ivSearchClose.visibility = if (s.toString().isNotEmpty()) {
                        View.VISIBLE
                    } else {
                        View.INVISIBLE
                    }
                    mAdapter?.search(s.toString()) {
                        if (it) {
                            rvActiveList.visibility = View.GONE
                        } else {
                            rvActiveList.visibility = View.VISIBLE
                        }
                    }
                    mExpiredAdapter?.search(s.toString()) {
                        if (it) {
                            rvExpiredList.visibility = View.GONE
                        } else {
                            rvExpiredList.visibility = View.VISIBLE
                        }
                    }
                }
            })
            initObservers()
        }
    }

    private fun openAddedUserDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val userAddedBinding = DialogUserAddedBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(userAddedBinding.root)
            userAddedBinding.apply {
                txtHeader.text = getString(R.string.user_added_dater)
                txtMessage.text = getString(R.string.user_added_dater_message)
                val imageViewList: ArrayList<RoundedImageView> =
                    arrayListOf(img1, img2, img3, img4, img5)
                val selectedUsers = mAdapter?.getSelectedUser()
                val visibleViews: List<RoundedImageView> = imageViewList.take(selectedUsers!!.size)

                visibleViews.onEachIndexed { i, view ->
                    view.visibility = View.VISIBLE
                    if (selectedUsers[i].username != "") {
                        val textDrawable = mActivity.createPlaceholderImage(
                            selectedUsers[i].username!!,
                            100,
                            R.color.blue_gradient_2,
                            R.color.blue_gradient_3
                        )

                        Glide.with(mActivity)
                            .load(selectedUsers[i].image).diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(textDrawable)
                            .into(imageViewList[i])
                    }
                }
                btnContinue.setOnClickListener {
                    userAddedBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        addActivePendingRequestList(selectedUsers)
                    }
                }
            }
            setZoomDialogWindowAttributes()
            show()
            userAddedBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    override fun onResume() {
        super.onResume()
        getActivePendingRequestList()
    }

    private fun openSinglePlayerLimitDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val binding = DialogSinglePlayerLimitBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.txtGotIt.setOnClickListener {
                binding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            try {
                setZoomDialogWindowAttributes()
                show()
                binding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getActivePendingRequestList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getActivePendingRequestList()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getActivePendingRequestList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        homeViewModel.getActiveExpiredRequestList(
                            "Bearer $authToken"
                        )
                    }
                }
            }
        }
    }

    private fun addActivePendingRequestList(selectedUsers: List<ActiveExpireRequestListModel.Pending>?) {
        selectedUsers?.onEach {
            XmppServiceCommand.addContactToRoster(
                this@RequestsListActivity,
                it.mobileNumber + "" + AppConstants.XMPP_EXTENSION,
                it.username
            )
            val apiRequest = JsonObject().apply {
                addProperty("id", it.id)
                addProperty("stage", AppConstants.STAGE_COMPLETED)
            }
            when {
                !AppUtils.isNetworkAvailable(mActivity) -> {
                    openFailNetworkDialog(){addActivePendingRequestList(selectedUsers)}
//                    AppUtils.showMessageOK(
//                        mActivity,
//                        getString(R.string.app_name),
//                        getString(R.string.common_retry),
//                        getString(R.string.no_internet)
//                    ) { _, _ ->
//                        addActivePendingRequestList(selectedUsers)
//                    }
                }
                else -> {
                    lifecycleScope.launch {
                        homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            homeViewModel.addActiveExpiredRequestList(
                                "Bearer $authToken", apiRequest
                            )
                        }
                    }
                }
            }
        }
    }

    private fun initObservers() {
        homeViewModel.apply {
            showLoading.observe(mActivity, {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            })
            errorMessage.observe(mActivity, {
                Log.e("TAG", "ERROR === $it")
                mActivity.showSnackBar(binding.layoutMain, it)
            })
            activePendingData.observe(mActivity, { listResponse ->
                if (listResponse.response == "success") {
                    binding.apply {
                        if (listResponse.pending.isEmpty() && listResponse.expire.isEmpty()) {
                            txtNoRecordFound.visibility = View.VISIBLE
                        } else {
                            if (listResponse.pending.isNotEmpty()) {
                                txtActiveRequest.apply {
                                    visibility = View.VISIBLE
                                    text =
                                        getString(
                                            R.string.active_request,
                                            listResponse.pending.filter { it.username != "" }.size.toString()
                                        )
                                }
                                rvActiveList.apply {
                                    visibility = View.VISIBLE
                                    mAdapter =
                                        ActiveTribeRequestAdapter(
                                            mActivity,
                                            listener = { bool, size ->
                                                run {
                                                    connectorSize = size
                                                    if (bool) {
                                                        btnAddToTribe.visibility = View.VISIBLE
                                                    } else {
                                                        btnAddToTribe.visibility = View.GONE
                                                    }
                                                }
                                            },
                                            listenerSize = {
                                                openSinglePlayerLimitDialog()
                                            })
                                    mAdapter?.addAll(listResponse.pending.filter { it.username != "" }
                                        .toCollection(ArrayList()))
                                    adapter = mAdapter
                                    setDivider()
                                }
                            }
                            if (listResponse.expire.isNotEmpty()) {
                                txtExpiredRequest.apply {
                                    visibility = View.VISIBLE
                                    text =
                                        getString(
                                            R.string.expired_request,
                                            listResponse.expire.size.toString()
                                        )
                                }
                                rvExpiredList.apply {
                                    visibility = View.VISIBLE
                                    mExpiredAdapter = ExpiredTribeRequestAdapter().apply {
                                        addAll(listResponse.expire.toCollection(ArrayList()))
                                    }
                                    adapter = mExpiredAdapter
                                    setDivider()
                                }
                            }
                        }
                    }
                }
            })
            activePendingError.observe(mActivity, {
                Log.e("TAG", "ERROR === $it")
                mActivity.showSnackBar(binding.layoutMain, it.response.toString())
            })
            inviteData.observe(mActivity, {
                if (it.response == "success") {
                    savePreference(
                        PreferenceKeys.PREF_REMAINING_CONNECTOR_CONNECTION,
                        it.remain_connection ?: 5
                    )
                    finish()
                }
            })
        }
    }
    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if(it.isShowing)
            {
                it.dismiss()
            }
        }
    }
}