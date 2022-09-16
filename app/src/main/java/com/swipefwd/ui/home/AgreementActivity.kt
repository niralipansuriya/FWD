package com.swipefwd.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import com.swipefwd.R
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityAgreementBinding
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.changeStatusBarColor
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.tutorial.onboard.OnBoardingActivity
import com.swipefwd.ui.video.VideoTutorial
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AgreementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgreementBinding
    private val mActivity by lazy {
        this@AgreementActivity
    }
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private var userId = 0
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    var accountType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgreementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN , "6")
        changeStatusBarColor()
        lifecycleScope.launch {
            accountType = profileViewModel.getAccountType.firstOrNull() ?: ""
            userId = profileViewModel.getUserId.firstOrNull() ?: 0
        }
        binding.apply {
            mActivity.setShaderView(
                btnAgree,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )

            btnAgree.setOnClickListener {
                updateAgreement()
            }
        }
        initObservers()
    }

    private fun initObservers() {
        profileViewModel.apply {
            showLoading.observe(mActivity) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(mActivity) {
                Log.e("TAG" , "ERROR === $it")
                mActivity.showSnackBar(binding.layoutMain , it)
            }
            updateData.observe(mActivity) {
                onUserProfileUpdateSuccess()
            }
        }
    }

    private fun onUserProfileUpdateSuccess(){
        profileViewModel.savePreference(PreferenceKeys.PREF_IS_AGREE, true)
//        nextActivity(OnBoardingActivity::class.java)
//        nextActivity(VideoTutorial::class.java)
//        when (accountType) {
//            AppUtils.AccountTypes.Dater -> {
//                nextActivity(LoadingActivity::class.java)
//            }
//            else -> {
//                nextActivity(TabManagerActivity::class.java)
//            }
//        }
        nextActivity(LoadingActivity::class.java)
        finish()
        finishAffinity()
    }

    private fun updateAgreement() {
        val apiRequest = JsonObject().apply {
            addProperty("is_agree", true)
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){updateAgreement()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    updateAgreement()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        profileViewModel.userProfileUpdate(
                            "Bearer $authToken", userId, apiRequest
                        )
                    }
                }
            }
        }
    }
}