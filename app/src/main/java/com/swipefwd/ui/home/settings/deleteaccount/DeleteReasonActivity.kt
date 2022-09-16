package com.swipefwd.ui.home.settings.deleteaccount

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.swipefwd.R
import com.swipefwd.databinding.ActivityDeleteReasonBinding
import com.swipefwd.databinding.DialogDeleteAccount2Binding
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.home.settings.SettingsViewModel
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DeleteReasonActivity : AppCompatActivity() {
    private val mActivity by lazy {
        this@DeleteReasonActivity
    }
    lateinit var binding: ActivityDeleteReasonBinding
    private val settingsViewModel: SettingsViewModel by viewModels {
        viewModelFactory { SettingsViewModel(mActivity, AppRepository()) }
    }
    var type = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteReasonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("type")) {
            type = intent.getStringExtra("type")!!
        }
        init()
    }

    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }


    private fun init() {
        binding.apply {

            edtReason.setOnEditorActionListener { _, _, _ ->
                if (edtReason.text.toString().isEmpty()) {
                    showSnackBar(layoutMain, "Please provide reason")
                } else {
                    openDeleteAccountDialog()
                }
                false
            }

            imgCancel.setOnClickListener {
                onBackPressed()
            }
            imgBack.setOnClickListener {
                onBackPressed()
            }
            this@DeleteReasonActivity.setShaderView(
                txtDesc1,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            this@DeleteReasonActivity.setShaderView(
                txtDesc2,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            this@DeleteReasonActivity.setShaderView(
                txtDesc3,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            settingsViewModel.apply {
                errorMessage.observe(mActivity, {
                    Log.e("TAG", "ERROR === $it")
                })
                planPurchaseData.observe(mActivity, { model ->
                    nextActivity(AccountDeletedActivity::class.java)
                    finish()
                })
                showLoading.observe(mActivity, {
                    if (it) {
                        progressBarHandler.show()
                    } else {
                        progressBarHandler.dismiss()
                    }
                })
            }
        }
    }

    private fun openDeleteAccountDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val deleteAccount2Binding = DialogDeleteAccount2Binding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(deleteAccount2Binding.root)
            deleteAccount2Binding.btnDeleteCancel.setOnClickListener {
                deleteAccount2Binding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    this@DeleteReasonActivity.onBackPressed()
                }
            }
            deleteAccount2Binding.btnDeleteYes.setOnClickListener {

                deleteAccount2Binding.imgDialogGradient.setDialogFadeOutAnimation {
                    lifecycleScope.launch {
                        settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            settingsViewModel.deleteAccount(
                                "Bearer $authToken",
                                type,
                                settingsViewModel.getUserId.firstOrNull() ?: 0,5,binding.edtReason.text.toString()
                            )
                        }
                        dismiss()
                    }


                }

            }
            try {
                setBottomDialogWindowAttributes()
                show()
                deleteAccount2Binding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}