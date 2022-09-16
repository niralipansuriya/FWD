package com.swipefwd.ui.home.settings.deleteaccount

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.swipefwd.R
import com.swipefwd.databinding.ActivityAccountDeleteSelectionBinding
import com.swipefwd.databinding.DialogDeleteAccountBinding
import com.swipefwd.databinding.DialogDeleteAccountReasonsBinding
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.home.settings.SettingsViewModel
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setDivider
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AccountDeleteSelectionActivity : AppCompatActivity() {
    private val mActivity by lazy {
        this@AccountDeleteSelectionActivity
    }
    private val settingsViewModel: SettingsViewModel by viewModels {
        viewModelFactory { SettingsViewModel(mActivity, AppRepository()) }
    }

    private lateinit var binding: ActivityAccountDeleteSelectionBinding
    private var isCheckedDater = false
    private var isCheckedConnector = false

    var isDater = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountDeleteSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {

        binding.apply {
            btnClose.setOnClickListener {
                onBackPressed()
            }
            btnBack.setOnClickListener {
                onBackPressed()
            }
            btnDeleteCancel.setOnClickListener {
                onBackPressed()
            }
            btnDeleteYes.setOnClickListener {
                if (isCheckedDater || isCheckedConnector) {
                    openDeleteAccountDialog()
                }

            }
            userDater.setOnClickListener {
                if (isCheckedDater) {
                    userDater.isChecked = false
                    isCheckedDater = false
                    isCheckedConnector = true
                    userConnector.isChecked = true
                } else {
                    userDater.isChecked = true
                    isCheckedDater = true
                    isCheckedConnector = false
                    userConnector.isChecked = false
                }
            }
            userConnector.setOnClickListener {
                if (isCheckedConnector) {
                    userConnector.isChecked = false
                    isCheckedConnector = false
                    isCheckedDater = true
                    userDater.isChecked = true
                } else {
                    userConnector.isChecked = true
                    isCheckedConnector = true
                    isCheckedDater = false
                    userDater.isChecked = false
                }
            }
            settingsViewModel.apply {
                settingsError.observe(mActivity) {
                    mActivity.showSnackBar(binding.layoutMain , it.message.toString())
                }
                planPurchaseData.observe(mActivity) { model ->
                    nextActivity(AccountDeletedActivity::class.java)
                    finish()
                }
                showLoading.observe(mActivity) {
                    if (it) {
                        progressBarHandler.show()
                    } else {
                        progressBarHandler.dismiss()
                    }
                }
            }


        }

    }

    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }

    private fun openDeleteAccountDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val deleteAccountBinding = DialogDeleteAccountBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(deleteAccountBinding.root)
            deleteAccountBinding.btnDeleteCancel.setOnClickListener {
                deleteAccountBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            deleteAccountBinding.btnDeleteYes.setOnClickListener {
                deleteAccountBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    openDeleteReasonDialog()
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                deleteAccountBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openDeleteReasonDialog() {
        val reasonList = arrayListOf(
            getString(R.string.met_someone), getString(R.string.need_break), getString(
                R.string.dissatisfied_service
            ), getString(R.string.billing_issue), getString(
                R.string.common_other
            )
        )
        var reason = getString(R.string.met_someone)

        val reasonAdapter = DeleteReasonAdapter(this) {
            reason = it
        }

        val dialog = Dialog(this, R.style.SlideDialogTheme)

        val deleteReasonsBinding = DialogDeleteAccountReasonsBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(deleteReasonsBinding.root)
            deleteReasonsBinding.btnReasonDelete.setOnClickListener {
                deleteReasonsBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    var type = ""
                    if (isCheckedDater) {
                        type = AppUtils.AccountTypes.Dater
                    } else {
                        type = AppUtils.AccountTypes.Matchmaker
                    }
                    when (reason) {
                        getString(R.string.common_other) -> {
                            val intent = Intent(
                                this@AccountDeleteSelectionActivity,
                                DeleteReasonActivity::class.java
                            )
                            intent.putExtra("type", type)
                            startActivity(intent)
                            overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                            // nextActivity(DeleteReasonActivity::class.java)
                        }
                        else -> {
                            dismiss()
                            lifecycleScope.launch {
                                settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                                    settingsViewModel.deleteAccount(
                                        "Bearer $authToken",
                                        type,
                                        settingsViewModel.getUserId.firstOrNull() ?: 0,
                                        reasonAdapter.selectedPosition + 1,
                                        ""
                                    )
                                }
                            }

                        }
                    }
                }
            }
            deleteReasonsBinding.rvReason.apply {
                setDivider()
                adapter = reasonAdapter
            }
            reasonAdapter.addAll(reasonList)
            try {
                setBottomDialogWindowAttributes()
                show()
                deleteReasonsBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}