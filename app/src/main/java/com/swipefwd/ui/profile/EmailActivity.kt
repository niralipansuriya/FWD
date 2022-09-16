package com.swipefwd.ui.profile

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import com.swipefwd.R
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityEmailBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.changeStatusBarColor
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.showSnackBarMargin
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class EmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmailBinding
    private val mActivity by lazy {
        this@EmailActivity
    }
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var emailId = ""
    private var isFromSettings = false
    private var mCustomSnackbar: Snackbar? = null
    private var isFromLoading = false
    private var isKeyboardOpened = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isFromSettings = intent.hasExtra("EditProfile")
        isFromLoading = intent.hasExtra("LoadingActivity")
        init()
        initObservers()
    }

    private fun init() {
        profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "5")
        changeStatusBarColor()
        binding.apply {
            val contentView = layoutMain
            contentView.viewTreeObserver.addOnGlobalLayoutListener {
                val r = Rect()
                contentView.getWindowVisibleDisplayFrame(r)
                val screenHeight = contentView.rootView.height
                val keypadHeight = screenHeight - r.bottom
                if (keypadHeight > screenHeight * 0.15) {
                    if (!isKeyboardOpened) {
                        isKeyboardOpened = true
                        txtSkip.visibility = View.GONE
                        txtSkipBottom.visibility = View.VISIBLE
                        /*Handler(Looper.getMainLooper()).postDelayed({
                            binding.scrollview.fullScroll(View.FOCUS_DOWN)
                        }, 100)*/
                    }
                } else {
                    if (isKeyboardOpened) {
                        isKeyboardOpened = false
                        txtSkip.visibility = View.VISIBLE
                        txtSkipBottom.visibility = View.INVISIBLE
                    }
                }
            }
            Log.d("TAG:", "isFromSettings: $isFromSettings")
            imgNext.isEnabled = false
            imgBack.setOnClickListener {
                if (isFromLoading) {
                    startActivity(
                        Intent(
                            mActivity,
                            LoadingActivity::class.java
                        )
                    )
                    finish()
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                } else
                    onBackPressed()
            }
            edtEmail.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    binding.imgNext.performClick()
                    true
                }
                false
            }
            txtSkip.setOnClickListener {
                when {
                    isFromLoading -> {
                        imgBack.performClick()
                    }
                    isFromSettings -> {
                        imgBack.performClick()
                    }
                    else -> {
                        profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "8")

                        /*         profileViewModel.removePreference(PreferenceKeys.PREF_RECOVERY_EMAIL)
                                 profileViewModel.savePreference(PreferenceKeys.PREF_LOGIN_FLAG, true)
                                 profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
                                 lifecycleScope.launch {
         //                            if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
         //                                nextActivity(AgreementActivity::class.java)
         //                                finish()
         //                            } else {
         ////                                nextActivity(TabManagerActivity::class.java)
         //                                nextActivity(LoadingActivity::class.java)
         //                                finish()
         //                                finishAffinity()
         //                            }
                                     nextActivity(LoadingActivity::class.java)
                                     finish()
                                 }*/
                        finish()
                    }
                }
            }
            txtSkipBottom.setOnClickListener {
                txtSkip.performClick()
            }
            edtEmail.setOnClickListener {
                if (mCustomSnackbar != null && mCustomSnackbar!!.isShown) {
                    AppUtils.snackBarCloseAnimation(mActivity, mCustomSnackbar!!)
                }
            }
            imgNext.setOnClickListener {
                recoveryEmailId()
            }
            edtEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    emailId = s.toString().trim()
                }

                override fun afterTextChanged(s: Editable?) {
                    imgNext.isEnabled = !s.isNullOrBlank()
                }
            })
        }
    }


    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launch {
                emailId = profileViewModel.getRecoveryEmail.firstOrNull() ?: ""
                binding.edtEmail.setText(emailId)
            }
        }, 500)
    }

    private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun recoveryEmailId() {
        if (emailId.isValidEmail()) {
            val apiRequest = JsonObject().apply {
                //addProperty("recover_email" , binding.edtEmail.text.toString().trim())
                //  addProperty("email" , binding.edtEmail.text.toString().trim())
                addProperty("email", binding.edtEmail.text.toString().trim())
            }
            when {
                !AppUtils.isNetworkAvailable(mActivity) -> {
                    openFailNetworkDialog() { recoveryEmailId() }

                }
                else -> {
                    lifecycleScope.launch {
                        /*profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            profileViewModel.recoverEmail(
                                "Bearer $authToken" , apiRequest
                            )
                        }*/ //commented by nirali

                        profileViewModel.setEmail(apiRequest)
                    }
                }
            }
        } else {
            mCustomSnackbar = showSnackBarMargin(
                v = binding.layoutMain,
                message = (getString(R.string.check_email))
            )
        }
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
                Log.e("TAG", "ERROR === $it")
                showSnackBarMargin(
                    v = binding.layoutMain,
                    message = it
                )
            }

            dataEmail.observe(mActivity)
            { emailModel ->
                if (emailModel.code == 1) {
                    AppUtils.storeEmail(this@EmailActivity, emailModel.data.email)
                    profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "8")

                    finish()
                }
            }
            data.observe(mActivity) { responseModel ->
                if (responseModel.response == "success") {
                    profileViewModel.savePreference(PreferenceKeys.PREF_RECOVERY_EMAIL, emailId)
                    if (isFromSettings) {
                        binding.imgBack.performClick()
                    } else if (isFromLoading) {
                        binding.imgBack.performClick()
                    } else {
                        //profileViewModel.savePreference(PreferenceKeys.PREF_LOGIN_FLAG, true)
                        //  profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
                        lifecycleScope.launch {
//                        if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                            nextActivity(AgreementActivity::class.java)
//                            finish()
//                        } else {
////                            nextActivity(TabManagerActivity::class.java)
//                            nextActivity(LoadingActivity::class.java)
//                            finish()
//                            finishAffinity()
//                        }
                            /* nextActivity(LoadingActivity::class.java)
                             finish()*/
                        }
                    }
                }
            }
            error.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
                showSnackBarMargin(
                    v = binding.layoutMain,
                    message = it.message.toString()
                )
            }

            emailError.observe(mActivity)
            {
                profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "8")

                if (it.code == 0) {

                    showSnackBarMargin(
                        v = binding.layoutMain,
                        message = it.message.toString()
                    )
                }
            }
        }
    }
}