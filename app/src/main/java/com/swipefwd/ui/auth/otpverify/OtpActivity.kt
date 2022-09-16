package com.swipefwd.ui.auth.otpverify

import OTPModel
import android.app.Activity
import android.app.Dialog
import android.content.*
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.telephony.PhoneNumberUtils
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.auth.api.phone.SmsRetrieverClient
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.swipefwd.Injection
import com.swipefwd.R
import com.swipefwd.animations.SlideView
import com.swipefwd.base.BaseActivity
import com.swipefwd.base.ResultState
import com.swipefwd.base.getResolvedString
import com.swipefwd.data.models.LoginResponseModel
import com.swipefwd.data.models.RecoverAccountModel
import com.swipefwd.data.models.SocialMediaUserModel
import com.swipefwd.data.models.SuspendedPhoneNumber
import com.swipefwd.databinding.DialogAccountRecoveryBinding
import com.swipefwd.databinding.DialogAlternateLoginAddedBinding
import com.swipefwd.databinding.DialogTimeOutBinding
import com.swipefwd.databinding.OtpActivityBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.auth.login.LoginActivity
import com.swipefwd.ui.auth.register.UserInfoActivity
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.profile.AdvancePreferenceActivity
import com.swipefwd.ui.profile.PreferencesActivity
import com.swipefwd.ui.updateuserprofile.UserProfileActivity
import com.swipefwd.utils.*
import com.swipefwd.utils.AppUtils.changeStatusBarColor
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBarMargin
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.utils.otpView.OTPListener
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class OtpActivity : BaseActivity<OtpActivityBinding>() {
    companion object {
        const val EXTRA_KEY_SNACK_BAR_MESSAGE = "snackbarMsg"
        const val EXTRA_KEY_COUNTRY_CODE = "countryCode"
        const val EXTRA_KEY_PHONE_NUMBER = "phoneNumber"
        const val EXTRA_KEY_EMAIL = "email"
        const val EXTRA_KEY_ISO_CODE = "isoCode"
        const val EXTRA_KEY_OTP = "otp"
        const val EXTRA_KEY_FIRST_NAME = "extra.key.firstname"
        const val IS_FROM_DEEP_LINK = "fromDeepLink"
    }

    private val otpViewModel: OtpViewModel by viewModels {
        viewModelFactory {
            OtpViewModel(
                Injection.getAppRepository(),
                Injection.getInternalAppDataStore(applicationContext),
                Injection.getAppDataBase(applicationContext)
            )
        }
    }

    private val progressBarHandler by lazy { ProgressBarHandler(this) }
    private val mActivity by lazy { this@OtpActivity }
    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    private var isFromDeepLink = false
    lateinit var mAnimationShake: Animation
    private var isWrongOTPEntered = false
    private val SMS_VERIFICATION_REQUEST = 2

    //    private val mAnimation = AnimationBounceUp()
    private val mAnimation = SlideView()
    private var snackbar: Snackbar? = null
    private var isRecoveryFlow = false
    var mySMSBroadcastReceiver: MySMSBroadcastReceiver? = null
    private lateinit var smsClient: SmsRetrieverClient
    private fun initSmsListener() {
        smsClient.startSmsRetriever()
            .addOnSuccessListener {
                println("API Started===")
                //You can perform your tasks here

            }.addOnFailureListener { failure ->
                failure.printStackTrace()
//                Toast.makeText(this, failure.message, Toast.LENGTH_SHORT).show()
            }
    }

    private val smsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Get SMS message content
                result.data?.let {
                    val message = it.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                    println(message)
                    if (!message.isNullOrBlank()) getOtpFromMessage(message)
                }
            }
        }

    private val smsVerificationBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            println("smsVerificationBroadcastReceiver ->>>>>>>>>>>..")
            if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
                println("SMS_RETRIEVED_ACTION ->>>>>>>>>>>..")

                val extras = intent.extras
                val retrieveSMSStatus = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

                when (retrieveSMSStatus.statusCode) {
                    CommonStatusCodes.SUCCESS -> {
                        println("CommonStatusCodes ->>>>>>>>>>>..")

                        println("Got SMS")

                        // Retrieve sms consent intent
                        val smsConsentIntent =
                            extras.getParcelable<Intent>(SmsRetriever.EXTRA_CONSENT_INTENT)
                        try {
                            // Display sms consent dialog
                            smsLauncher.launch(smsConsentIntent)
                           // startActivityForResult(smsConsentIntent, SMS_VERIFICATION_REQUEST)
                        } catch (e: ActivityNotFoundException) {
                            e.printStackTrace()
                        }
                    }
                    CommonStatusCodes.TIMEOUT -> {
                        // Handle timeout error
                        println("TimeOut === ")
                    }
                }
            }
        }
    }

    private fun getOtpFromMessage(message: String) {
        val otpPattern: Pattern = Pattern.compile("(|^)\\d{4}")
        val matcher: Matcher = otpPattern.matcher(message)
        if (matcher.find()) {
            binding.otpView.setOTP(matcher.group(0))
        }
    }

    override fun onStart() {
        super.onStart()
        /*   val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
           registerReceiver(smsVerificationBroadcastReceiver, intentFilter)*/
    }

    override fun onStop() {
        super.onStop()
       //nirali unregisterReceiver(smsVerificationBroadcastReceiver)
    }

    override fun onCreateViewBinding() = OtpActivityBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeStatusBarColor()
        otpViewModel.firstName = intent?.getStringExtra(EXTRA_KEY_FIRST_NAME) ?: ""
        otpViewModel.phoneNumber = intent.getStringExtra(EXTRA_KEY_PHONE_NUMBER) ?: ""
        otpViewModel.countryCode = intent.getStringExtra(EXTRA_KEY_COUNTRY_CODE) ?: ""
        otpViewModel.isoCode = intent.getStringExtra(EXTRA_KEY_ISO_CODE) ?: ""
        otpViewModel.recoverPhoneNumber = intent.getStringExtra(EXTRA_KEY_PHONE_NUMBER) ?: ""
        otpViewModel.recoverEmail = intent?.getStringExtra(EXTRA_KEY_EMAIL) ?: ""
        isFromDeepLink = intent?.getBooleanExtra(IS_FROM_DEEP_LINK, false) ?: false

        smsClient = SmsRetriever.getClient(this)
        initSmsListener()

//        binding.imgNext.setOnClickListener { onNextButtonClick() }
        binding.otpView.otpListener = otpListener
        binding.otpView.setOnTouchListener { v, event ->
            if (isWrongOTPEntered) {
                isWrongOTPEntered = false
                binding.otpView.setOTP("")
            }
            false
        }

        otpViewModel.onLogOutUser().observe(this) { onLogOutUser(it) }
        otpViewModel.showLoading().observe(this) { showLoading(it) }
        otpViewModel.getOTPData().observe(this) { onResendOtpResult(it) }
        otpViewModel.verifyOtpResponseData().observe(this) { submitOtpResult(it) }
        otpViewModel.verifyOtpEmailResponseData().observe(this) { submitOtpEmailResult(it) }
        otpViewModel.errorMessage().observe(this) {
            onError(it)
        }
        otpViewModel.getOtpError().observe(this) {}
        otpViewModel.loginData().observe(this) { it -> onLoginResult(it) }
        otpViewModel.recoverAccountData().observe(this) { it -> onRecoverResult(it) }
        otpViewModel.loginError().observe(this) { onLoginErrorResult() }
        otpViewModel.timerRefreshed().observe(this) { onTimerRefreshed(it) }
        otpViewModel.timerStopped().observe(this) { onTimerStopped() }

        //if user registration required
        otpViewModel.userRegistrationRequired().observe(this) { onUserRegistrationRequired() }
        ///////////////////////////////////////////////////////////////////////////////////////////
        mAnimationShake = AnimationUtils.loadAnimation(this, R.anim.shake_animation)
        ///////////////////////////////////////////////////////////////////////////////////////////
        init()
      /*nirali  val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        registerReceiver(smsVerificationBroadcastReceiver, intentFilter)*/
        ///////////////////////////////////////////////////////////////////////////////////////////
    }

    private fun onError(message: String) {
        snackbar = showSnackBarMargin(v = binding.layoutMain, message = message)
    }


    private fun onUserRegistrationRequired() {
        showLoading(false)
        otpViewModel.saveLoginData(PreferenceKeys.PREF_COUNTRY_CODE, otpViewModel.countryCode)
        startActivity(
            Intent(mActivity, UserInfoActivity::class.java)
                .putExtra("phoneNumber", otpViewModel.phoneNumber)
                .putExtra("countryCode", otpViewModel.countryCode)
                .putExtra("isConnection", 0)
                .putExtra(UserInfoActivity.EXTRA_KEY_OTP, otpViewModel.userFilledOtp)

        )
        finishAffinity()
        overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
    }


    private fun onRecoverResult(it: RecoverAccountModel?) {
        Log.i("TAG", "onRecoverResult: ")
        lifecycleScope.launch {
            isRecoveryFlow = true
            loginUser()
        }
    }

    private fun onLogOutUser(result: ResultState<Unit>) {
        when (result) {
            is ResultState.Loading -> showLoading(true)
            is ResultState.Success -> {
                showLoading(false)
                logoutUser()
            }
            is ResultState.Error -> {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBarHandler.show()
        } else {
            progressBarHandler.dismiss()
        }
    }

    private fun onVerifyOtpError(error: String?) {
        binding.apply {
            otpView.showError()
            otpView.startAnimation(mAnimationShake)
        }

        snackbar = showSnackBarMargin(
            v = binding.layoutMain,
            message = error ?: getString(R.string.invalid_otp),
        )
        isWrongOTPEntered = true
    }

    private fun onNextButtonClick() {
        if (otpViewModel.isWrongOtpFillLimitExceed()) {
            otpViewModel.stopTimer()
            openTimeOutDialog()
        } else {

            if (isFromDeepLink) {
                verifyOTPEmail()
            } else {
                verifyOTP()

            }
        }
    }

    private fun onTimerRefreshed(timeValue: String) {
        //   binding.txtResend.visibility = View.GONE
        binding.txtResend.visibility = View.VISIBLE
        // binding.txtTimer.visibility = View.VISIBLE
        binding.txtTimer.visibility = View.GONE
        binding.txtTimer.text =
            resources.getString(R.string.resend_timer, timeValue)
    }

    private fun onTimerStopped() {
        binding.txtResend.visibility = View.VISIBLE
        binding.rlResend.setOnClickListener {

            if (isFromDeepLink) {
                otpViewModel.resendOtpEmail()

            } else {
                otpViewModel.resendOtp()

            }
        }
        binding.txtTimer.visibility = View.GONE
    }

    private val otpListener = object : OTPListener {
        override fun onInteractionListener() {
//            binding.imgNext.isEnabled = false
            snackbar?.takeIf { it.isShown }.run {
                snackbar?.dismiss()
                snackbar = null
            }
        }

        override fun onOTPComplete(otp: String) {
            val inputMethodManager =
                ContextCompat.getSystemService(this@OtpActivity, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(binding.otpView.windowToken, 0)
            onNextButtonClick()
//            binding.imgNext.isEnabled = true
        }

        override fun onDoneClick() {
            val inputMethodManager =
                ContextCompat.getSystemService(this@OtpActivity, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(binding.otpView.windowToken, 0)
            onNextButtonClick()
//            binding.imgNext.performClick()
        }
    }

    private fun openAlternateLoginDialog(
        signUpType: Int,
        phone: String,
        google: String,
        faceBook: String,
        linkedIn: String
    ) {
        println("signUpType------------>>${signUpType}")
        println("faceBook------------>>${faceBook}")
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val alternateLoginBinding = DialogAlternateLoginAddedBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(alternateLoginBinding.root)
            alternateLoginBinding.apply {
                btnLetsGo.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.common_continue)
                    setOnClickListener {
                        nextActivity(TabManagerActivity::class.java)
                        finish()
                        finishAffinity()
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                        }
                    }
                }
                Glide.with(this@OtpActivity).load(
                    when (signUpType) {
                        AppConstants.SOCIAL_TYPE_LINKEDIN -> R.drawable.ic_linked_in
                        AppConstants.SOCIAL_TYPE_FACEBOOK -> R.drawable.ic_facebook
                        else -> R.drawable.ic_google
                    }
                    /* when(loginViewModel.socialType){
                         AppConstants.SOCIAL_LINKEDIN->R.drawable.ic_linked_in
                         AppConstants.SOCIAL_FACEBOOK->R.drawable.ic_facebook
                         else->R.drawable.ic_google
                     }*/
                ).into(imgSocialLogo)
                /*txtPhoneNumber.text = getString(R.string.linkedin_dialog_title)
                txtUserName.text = getString(R.string.linkedin_dialog_content)*/

                when (signUpType) {
                    AppConstants.SOCIAL_TYPE_LINKEDIN -> txtPhoneNumber.text = linkedIn
                    AppConstants.SOCIAL_TYPE_FACEBOOK -> txtPhoneNumber.text = faceBook
                    // else -> txtPhoneNumber.text = google
                    else -> txtPhoneNumber.text = phone
                }
                //   txtUserName.text = phone
                txtUserName.text = google

            }
            setZoomDialogWindowAttributes()
            show()
            alternateLoginBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    private fun submitOtpResult(result: ResultState<OTPModel>) {
        when (result) {

            is ResultState.Success -> {
                otpViewModel.isConnection = result.data.code
                /*   if (isFromDeepLink) {


                       otpViewModel.loginUserWithEmailAndMobile()
                   } else {*/
                //otp  verified
                if (result.data.data.is_alternat_login == 1) {

                    println("alternate login --------------->>>>>>>>>11111111111111111111")
                    AppUtils.storeIsVerified(this, true)
                    openAlternateLoginDialog(
                        result.data.data.signup_type,
                        result.data.data.userDetails.phone_number,
                        result.data.data.userDetails.google,
                        result.data.data.userDetails.facebook,
                        result.data.data.userDetails.linkedin
                    )


                } else {
                    println("alternate login --------------->>>>>>>>>0000000000000000")

                    AppUtils.storeIsVerified(this, true)
                    lifecycleScope.launch {
                        val accountType = when (result.data.data.userDetails.user_type) {
                            AppConstants.USER_DATER -> AppUtils.AccountTypes.Dater
                            AppConstants.USER_MATCHMAKER -> AppUtils.AccountTypes.Matchmaker
                            else -> AppUtils.AccountTypes.Dater
                        }
                        otpViewModel.apply {
                            AppUtils.storeAccountType(this@OtpActivity, accountType)
                            otpViewModel.stopTimer()
                            if (!socialUser.socialType.isNullOrBlank()) {
                                userSettings(
                                    result.data.data.userDetails.user_id,
                                    result.data.data.token
                                )
                            }
                            Handler(Looper.getMainLooper()).postDelayed({
                                when (accountType) {
                                    AppUtils.AccountTypes.Dater -> {
                                        when {
                                            !result.data.data.is_basic_profile && !result.data.data.preference -> {
                                                println("not filled pref and profile--------------->>>>>>>>>")
                                                val intent = Intent(
                                                    this@OtpActivity,
                                                    UserInfoActivity::class.java
                                                )
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                intent.putExtra(
                                                    "phoneNumber",
                                                    otpViewModel.phoneNumber
                                                )
                                                intent.putExtra(
                                                    "countryCode",
                                                    otpViewModel.countryCode
                                                )
                                                intent.putExtra("isConnection", 0)
//                                                        .putExtra("isConnection", otpViewModel.isConnection)
                                                intent.putExtra(
                                                    UserInfoActivity.EXTRA_KEY_OTP,
                                                    otpViewModel.userFilledOtp
                                                )
                                                startActivity(intent)

                                                finish()
                                                //  finishAffinity()
                                                overridePendingTransition(
                                                    R.anim.enter_anim,
                                                    R.anim.leave_anim
                                                )
                                            }
                                            !result.data.data.is_basic_profile -> {
                                                println("not basic profile--------------->>>>>>>>>")

                                                AppUtils.storeLoginFlag(this@OtpActivity, 0)
                                                saveLoginData(
                                                    PreferenceKeys.PREF_LOGIN_FLAG,
                                                    false
                                                )
                                                val intent = Intent(
                                                    this@OtpActivity,
                                                    UserProfileActivity::class.java
                                                )
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                startActivity(intent)
                                                finish()
                                                overridePendingTransition(
                                                    R.anim.enter_anim,
                                                    R.anim.leave_anim
                                                )
                                                /*  startActivity(
                                                      Intent(
                                                          this@OtpActivity,
                                                          UserProfileActivity::class.java
                                                      )
                                                  )
                                                  finishAffinity()*/

                                            }
                                            !result.data.data.preference -> {
                                                println("not filled pref--------------->>>>>>>>>")

                                                AppUtils.storeLoginFlag(this@OtpActivity, 0)
                                                saveLoginData(
                                                    PreferenceKeys.PREF_LOGIN_FLAG,
                                                    false
                                                )

                                                val intent = Intent(
                                                    this@OtpActivity,
                                                    PreferencesActivity::class.java
                                                )
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                startActivity(intent)
                                                finish()

                                                overridePendingTransition(
                                                    R.anim.enter_anim,
                                                    R.anim.leave_anim
                                                )
//                                                nextActivity(PreferencesActivity::class.java)
                                                /* startActivity(
                                                     Intent(
                                                         this@OtpActivity,
                                                         PreferencesActivity::class.java
                                                     )
                                                 )
                                                 finishAffinity()
                                                 overridePendingTransition(
                                                     R.anim.enter_anim,
                                                     R.anim.leave_anim
                                                 )*/
                                            }
                                            !result.data.data.is_advance_profile -> {
                                                println("not filled advance profile--------------->>>>>>>>>")

                                                AppUtils.storeLoginFlag(this@OtpActivity, 0)
                                                saveLoginData(
                                                    PreferenceKeys.PREF_LOGIN_FLAG,
                                                    false
                                                )
//                                                nextActivity(PreferencesActivity::class.java)
                                                val intent = Intent(
                                                    this@OtpActivity,
                                                    AdvancePreferenceActivity::class.java
                                                )
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                                startActivity(intent)
                                                finish()
                                                overridePendingTransition(
                                                    R.anim.enter_anim,
                                                    R.anim.leave_anim
                                                )
                                                /* startActivity(
                                                     Intent(
                                                         this@OtpActivity,
                                                         AdvancePreferenceActivity::class.java
                                                     )
                                                 )
                                                 finishAffinity()
                                                 overridePendingTransition(
                                                     R.anim.enter_anim,
                                                     R.anim.leave_anim
                                                 )*/
                                            }
                                            else -> {
                                                println("elsssssssssse --------------->>>>>>>>>")

                                                AppUtils.storeLoginFlag(this@OtpActivity, 1)
                                                saveLoginData(
                                                    PreferenceKeys.PREF_LOGIN_FLAG,
                                                    true
                                                )
                                                saveLoginData(
                                                    PreferenceKeys.PREF_CURRENT_SCREEN,
                                                    "0"
                                                )
                                                if (isRecoveryFlow) {
                                                    openAddedUserDialog()
                                                } else {
                                                    println("move next-- --------------->>>>>>>>>")

                                                    moveToNext()
                                                }
                                            }
                                        }
                                    }
                                    AppUtils.AccountTypes.Matchmaker -> {
                                        when {
                                            !result.data.data.is_basic_profile -> {
                                                saveLoginData(
                                                    PreferenceKeys.PREF_LOGIN_FLAG,
                                                    false
                                                )
                                                AppUtils.storeLoginFlag(this@OtpActivity, 0)
                                                nextActivity(UserProfileActivity::class.java)
                                            }
                                            else -> {
                                                saveLoginData(
                                                    PreferenceKeys.PREF_LOGIN_FLAG,
                                                    true
                                                )
                                                AppUtils.storeLoginFlag(this@OtpActivity, 0)
                                                saveLoginData(
                                                    PreferenceKeys.PREF_CURRENT_SCREEN,
                                                    "0"
                                                )
                                                nextActivity(LoadingActivity::class.java)
                                                finishAffinity()
                                                overridePendingTransition(
                                                    R.anim.enter_anim,
                                                    R.anim.leave_anim
                                                )
                                            }
                                        }
                                    }
                                }
                            }, 500)
                        }
                    }
                }

//                    loginUser()
                // }
            }
            is ResultState.Error -> onVerifyOtpError(result.getResolvedString(this))
            is ResultState.Loading -> {
            }
        }
    }

    private fun submitOtpEmailResult(result: ResultState<OTPModel>) {
        when (result) {

            is ResultState.Success -> {
                otpViewModel.isConnection = result.data.code
                if (result.data.code == 1) {
                    val accountType = when (result.data.data.userDetails.user_type) {
                        AppConstants.USER_DATER -> AppUtils.AccountTypes.Dater
                        AppConstants.USER_MATCHMAKER -> AppUtils.AccountTypes.Matchmaker
                        else -> AppUtils.AccountTypes.Dater
                    }

                    AppUtils.storeIsVerified(this, true)
                    moveToNext()

                }
            }
            is ResultState.Error -> onVerifyOtpError(result.getResolvedString(this))
            is ResultState.Loading -> {
            }
        }
    }

    private fun onResendOtpResult(result: ResultState<OTPModel>) {
        when (result) {
            is ResultState.Success -> {
                showLoading(false)
//                otpViewModel.startResendCodeTimer()
                /* if (result.data.code!=1){

                 }*/
                /* showSnackBarMargin(
                     v = binding.layoutMain,
                     message = result.data.message + " " + "${otpViewModel.countryCode}${otpViewModel.phoneNumber}"
                 )*/
            }
            is ResultState.Error -> {
                showLoading(false)
                result.getResolvedString(this@OtpActivity)?.let {
                    onError(it)
                }
            }
            is ResultState.Loading -> {
                showLoading(true)
            }
        }
    }


    private fun onLoginResult(loginModel: LoginResponseModel) {
        Log.i("TAG", "onLoginResult: ")
        lifecycleScope.launch {
            val accountType = when (loginModel.data.userType) {
                AppConstants.USER_TYPE_DATER -> AppUtils.AccountTypes.Dater
                AppConstants.USER_TYPE_MATCH_MAKER -> AppUtils.AccountTypes.Matchmaker
                else -> AppUtils.AccountTypes.Dater
            }
            otpViewModel.apply {
                saveLoginData(PreferenceKeys.PREF_ACCOUNT_TYPE, accountType)
                otpViewModel.stopTimer()
                if (!socialUser.socialType.isNullOrBlank()) {
                    userSettings(loginModel.data.userId, loginModel.token)
                }
                Handler(Looper.getMainLooper()).postDelayed({
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            when {
                                !loginModel.profile -> {
                                    saveLoginData(PreferenceKeys.PREF_LOGIN_FLAG, false)
                                    startActivity(
                                        Intent(
                                            this@OtpActivity,
                                            UserProfileActivity::class.java
                                        )
                                    )
                                    finish()
                                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)

                                }
                                !loginModel.preference -> {
                                    saveLoginData(PreferenceKeys.PREF_LOGIN_FLAG, false)
                                    startActivity(
                                        Intent(
                                            this@OtpActivity,
                                            PreferencesActivity::class.java
                                        )
                                    )
                                    finish()
                                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                                }
                                else -> {
                                    saveLoginData(PreferenceKeys.PREF_LOGIN_FLAG, true)
                                    saveLoginData(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
//                                    if (loginModel.data.is_agree) {
//                                        nextActivity(LoadingActivity::class.java)
//                                    } else {
//                                        nextActivity(AgreementActivity::class.java)
//                                    }
                                    if (isRecoveryFlow) {
                                        openAddedUserDialog()
                                    } else {
                                        moveToNext()
                                    }
                                }
                            }
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            when {
                                !loginModel.profile -> {
                                    saveLoginData(PreferenceKeys.PREF_LOGIN_FLAG, false)
                                    nextActivity(UserProfileActivity::class.java)
                                }
                                else -> {
                                    saveLoginData(PreferenceKeys.PREF_LOGIN_FLAG, true)
                                    saveLoginData(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
//                                    if (loginModel.data.is_agree) {
//                                        nextActivity(LoadingActivity::class.java)
//                                    } else {
//                                        nextActivity(AgreementActivity::class.java)
//                                    }
                                    nextActivity(LoadingActivity::class.java)
                                    finishAffinity()
                                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                                }
                            }
                        }
                    }
                }, 500)
            }
        }
    }


    private fun userSettings(userId: Int, token: String) {
        val jsonObject = JsonObject().apply {
            when (otpViewModel.socialUser.socialType) {
                AppConstants.SOCIAL_GOOGLE -> {
                    addProperty("is_google_connect", true)
                    addProperty("google_name", otpViewModel.socialUser.emailId)
                    //addProperty("googleid", socialUser.socialId)
                }
                AppConstants.SOCIAL_LINKEDIN -> {
                    addProperty("is_linkedin_connect", true)
                    addProperty(
                        "linkedin_name",
                        otpViewModel.socialUser.firstName + " " + otpViewModel.socialUser.lastName
                    )
                    //addProperty("linkedinid", socialUser.socialId)
                }
                AppConstants.SOCIAL_FACEBOOK -> {
                    addProperty("is_facebook_connect", true)
                    addProperty(
                        "facebook_name",
                        otpViewModel.socialUser.firstName + " " + otpViewModel.socialUser.lastName
                    )
                    //addProperty("facebookid", socialUser.socialId)
                }
            }
        }
        otpViewModel.userSettingsUpdate(
            "Bearer $token", userId, jsonObject
        )
    }


    private fun onLoginErrorResult() {
        otpViewModel.stopTimer()
//        startActivity(
//            Intent(mActivity, UserInfoActivity::class.java)
//                .putExtra("phoneNumber", otpViewModel.phoneNumber)
//                .putExtra("countryCode", otpViewModel.countryCode)
//                .putExtra("isConnection", otpViewModel.isConnection)
//                .putExtra(UserInfoActivity.EXTRA_KEY_OTP,otpViewModel.userFilledOtp)
//        )
//        finish()
    }

    private fun recoverAccount() {
        if (!AppUtils.isNetworkAvailable(this)) {
            openFailNetworkDialog() { recoverAccount() }
//            AppUtils.showMessageOK(
//                this,
//                getString(R.string.app_name),
//                getString(R.string.common_retry),
//                getString(R.string.no_internet)
//            ) { _, _ ->
//                recoverAccount()
//            }
        } else {
            otpViewModel.recoverAccount()
        }
    }


    private fun loginUser() {
        if (!AppUtils.isNetworkAvailable(this)) {
            openFailNetworkDialog() { loginUser() }
//            AppUtils.showMessageOK(
//                this,
//                getString(R.string.app_name),
//                getString(R.string.common_retry),
//                getString(R.string.no_internet)
//            ) { _, _ ->
//                loginUser()
//            }
        } else {
            otpViewModel.loginUser()
        }
    }

    private fun moveToNext() {
//        nextActivity(LoadingActivity::class.java)
        nextActivity(TabManagerActivity::class.java)
        finishAffinity()
    }

    private fun getOtp() = otpViewModel.getOtp()
    private fun verifyOTP() {
        if (!AppUtils.isNetworkAvailable(this)) {
            openFailNetworkDialog() { verifyOTP() }
//            AppUtils.showMessageOK(
//                this,
//                getString(R.string.app_name),
//                getString(R.string.common_retry),
//                getString(R.string.no_internet)
//            ) { _, _ ->
//                verifyOTP()
//            }
        } else {
            otpViewModel.userFilledOtp = binding.otpView.otp
            otpViewModel.verifyOtp()
        }
    }

    private fun verifyOTPEmail() {
        if (!AppUtils.isNetworkAvailable(this)) {
            openFailNetworkDialog() { verifyOTPEmail() }

        } else {
            otpViewModel.userFilledOtp = binding.otpView.otp
            otpViewModel.verifyOtpEmail()
        }
    }

    private fun init() {
        startSmsListener()
        startOTPBroadCastListener()
        binding.otpRelative.setOnClickListener {
            snackbar?.takeIf { it.isShown }.run {
                snackbar?.dismiss()
                snackbar = null
            }
        }
        lifecycleScope.launch {
            otpViewModel.apply {

                emailError.observe(this@OtpActivity)
                {
                    snackbar = showSnackBarMargin(v = binding.layoutMain, message = it.message)

                }
                dataEmail.observe(this@OtpActivity)
                {
                    AppUtils.storeIsVerified(this@OtpActivity, true)

                    AppUtils.storeAuthToken(AppController.context!!, it.data.token)
                    AppUtils.storeIsVerified(AppController.context!!, it.data.is_verified)
                    AppUtils.storePrefFlag(AppController.context!!, it.data.preference)
                    AppUtils.storeProfileFlag(AppController.context!!, it.data.is_basic_profile)
                    AppUtils.storeAdvanceProfileFlag(
                        AppController.context!!,
                        it.data.is_advance_profile
                    )
                    AppUtils.storeGender(AppController.context!!, it.data.gender)
                    AppUtils.storeInvitationExpired(
                        AppController.context!!,
                        it.data.invitation_expired
                    )
                    AppUtils.storeUserId(AppController.context!!, it.data.userDetails.user_id)
                    AppUtils.storeUserType(AppController.context!!, it.data.userDetails.user_type)
                    AppUtils.storeFirstName(AppController.context!!, it.data.userDetails.first_name)
                    AppUtils.storeLastName(AppController.context!!, it.data.userDetails.last_name)
                    AppUtils.storeProfileImage(
                        AppController.context!!,
                        it.data.userDetails.profile_image
                    )
                    AppUtils.storePhoneNumber(
                        AppController.context!!,
                        it.data.userDetails.phone_number
                    )
                    AppUtils.storeAlternateLogin(AppController.context!!, it.data.is_alternat_login)
                    AppUtils.storeAgreement(AppController.context!!, it.data.userDetails.is_agree)
                    AppUtils.storeRemainingInvitation(
                        AppController.context!!,
                        it.data.userDetails.remain_invitation
                    )
                    AppUtils.storeRemainConnection(
                        AppController.context!!,
                        it.data.userDetails.remaim_connection
                    )
                    AppUtils.storeInstaUserName(
                        AppController.context!!,
                        it.data.userDetails.instagram_name
                    )
                    AppUtils.storeFacebook(AppController.context!!, it.data.userDetails.facebook)
                    AppUtils.storeLinkedInId(AppController.context!!, it.data.userDetails.linkedin)
                    AppUtils.storeGoogle(AppController.context!!, it.data.userDetails.google)
                    AppUtils.storeDaterDisable(
                        AppController.context!!,
                        it.data.userDetails.is_dater_disabled
                    )
                    AppUtils.storeConnectorDisable(
                        AppController.context!!,
                        it.data.userDetails.is_connector_disabled
                    )
                    moveToNext()

                }
            }

            val fbIds = otpViewModel.getFbIds.firstOrNull() ?: ""
            if (fbIds.isNotEmpty()) {
                val type: Type =
                    object : TypeToken<List<String?>?>() {}.type
                otpViewModel.facebookIds =
                    Gson().fromJson(
                        fbIds,
                        type
                    ) as ArrayList<String>
            }
            val socialMediaUser = otpViewModel.socialMediaUser.firstOrNull() ?: ""
            if (socialMediaUser.isNotEmpty()) {
                otpViewModel.socialUser =
                    Gson().fromJson(socialMediaUser, SocialMediaUserModel::class.java)
            }
        }

        binding.apply {
//            imgNext.isEnabled = false
            otpView.requestFocus()
            otpView.requestFocusOTP()
            val inputMethodManager =
                ContextCompat.getSystemService(this@OtpActivity, InputMethodManager::class.java)!!
            inputMethodManager.showSoftInput(binding.otpView, 0)
            txtMobile.text =
                PhoneNumberUtils.formatNumber(
                    "${otpViewModel.countryCode}${otpViewModel.phoneNumber}",
                    otpViewModel.isoCode
                ).replace(" ", "-")

            val span = SpannableString(txtResend.text)
            span.setSpan(object : ClickableSpan() {
                override fun onClick(p0: View) {
                    otpView.setOTP("")
                    otpViewModel.stopTimer()
                    //temp    getOtp() //commented by nirali

                    if (isFromDeepLink) {
                        otpViewModel.resendOtpEmail()

                    } else {
                        otpViewModel.resendOtp()

                    }
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = true
                }
            }, txtResend.length() - 6, txtResend.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            span.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.whiteColor,
                        theme
                    )
                ),
                txtResend.length() - 6, txtResend.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            txtResend.setText(span, TextView.BufferType.SPANNABLE)
            txtResend.highlightColor = Color.TRANSPARENT
            txtResend.movementMethod = LinkMovementMethod.getInstance()
            imgBack.setOnClickListener {
                onBackPressed()
            }
            /*KeyboardUtils.addKeyboardToggleListener(
                mActivity,
                object : KeyboardUtils.SoftKeyboardToggleListener {
                    override fun onToggleSoftKeyboard(isVisible: Boolean) {
                        if (isVisible) {
//                            imgNext.visibility = View.GONE
                        } else {
                            Handler(Looper.getMainLooper()).postDelayed({
//                                imgNext.visibility = View.VISIBLE
                            }, 100)
                        }
                    }
                })*/
        }
    }


    private fun openTimeOutDialog() {
//        val dialog = Dialog(this, R.style.SlideDialogTheme)
        // val dialog =  BottomSheetDialog(this, R.style.SlideDialogTheme)
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val timeOutBinding = DialogTimeOutBinding.inflate(layoutInflater)
        //temp   val bottomBehaviour = BottomSheetBehavior.from(timeOutBinding.bottomSheet)
        binding.txtTimer.visibility = View.GONE
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(timeOutBinding.root)
            timeOutBinding.apply {
                imgDialogGradient.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        this@OtpActivity,
                        R.drawable.rectangle
                    )
                )
                txtOk.setOnClickListener {
                    timeOutBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dialog.dismiss()
                        binding.apply {
                            otpView.setOTP("")
                            // otpViewModel.startSuspendedPhoneNumberTimer()
                            otpViewModel.noOfTimeWrongOtpSubmit = 0
                        }
                    }
                }
            }
            dialog.setOnDismissListener {
                //temp   bottomBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        saveTimeoutOtp()
                        mActivity.onBackPressed()
                    },
                    700
                )
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                /*temp  if (isShowing) {
                      bottomBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                      bottomBehaviour.peekHeight = Resources.getSystem().displayMetrics.heightPixels
                  }*/
                timeOutBinding.imgDialogGradient.setDialogFadeInAnimation()
                //  mAnimation.slide(timeOutBinding.clTimeOut, 100, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        lifecycleScope.launch {
            val suspendedPhoneNumber = SuspendedPhoneNumber(
                countryCode = "",
                phoneNumber = "",
                suspendStartTime = SystemClock.uptimeMillis(),
                true
            )
            otpViewModel.appPreferences.saveSuspendedPhoneNumber(suspendedPhoneNumber)
        }
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = Resources.getSystem().displayMetrics.heightPixels
        bottomSheet.layoutParams = layoutParams
    }


    override fun onBackPressed() {
        println("onBackPressed--->>>>>>>>>")
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right)
    }

    override fun onPause() {
        super.onPause()
        //  overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)

        dialogs.forEach {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

//    private fun doBounceDownAnimation(targetView: View) {
//        val interpolator: BounceInterpolator = object : BounceInterpolator() {
//            override fun getInterpolation(v: Float): Float {
//                return getPowIn(v , 2.0) //Add getPowIn(v,3); for more down animation
//            }
//        }
//        val animator = ObjectAnimator.ofFloat(targetView , "translationY" , 0f , 25f , 0f)
//        animator.interpolator = interpolator
//        animator.startDelay = 100
//        animator.duration = 300
//        animator.repeatCount = 1
//        animator.start()
//    }
//
//    private fun getPowIn(elapsedTimeRate: Float , pow: Double): Float {
//        return Math.pow(elapsedTimeRate.toDouble() , pow).toFloat()
//    }

//    private fun doBounceUpAnimation(targetView: View) {
//        val interpolator: BounceInterpolator = object : BounceInterpolator() {
//            override fun getInterpolation(v: Float): Float {
//                return getPowOut(v , 3.0) //Add getPowOut(v,3); for more up animation
//            }
//        }
//        val animator = ObjectAnimator.ofFloat(targetView , "translationY" , 0f , 30f , 0f)
//        animator.interpolator = interpolator
//        animator.startDelay = 0
//        animator.duration = 500
//        animator.repeatCount = 1
//        animator.start()
//    }
//
//    private fun getPowOut(elapsedTimeRate: Float , pow: Double): Float {
//        return (1.toFloat() - Math.pow((1 - elapsedTimeRate).toDouble() , pow)).toFloat()
//    }


    private fun setOtpCode(code: String) {
        binding.otpView.setOTP(code)
    }

    private fun startSmsListener() {
        val client = SmsRetriever.getClient(this)
        val task = client.startSmsRetriever()

        task.addOnSuccessListener {
        }

        task.addOnFailureListener {
        }
    }

    private fun startOTPBroadCastListener() {
        mySMSBroadcastReceiver = MySMSBroadcastReceiver()
        registerReceiver(
            mySMSBroadcastReceiver,
            IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        )
        mySMSBroadcastReceiver?.init(object : MySMSBroadcastReceiver.OTPReceiveListener {
            override fun onOTPReceived(otp: String?) {
                println("onOTPReceived -------------${otp}")
                setOtpCode(otp!!)
            }

            override fun onOTPTimeOut() {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mySMSBroadcastReceiver != null) unregisterReceiver(mySMSBroadcastReceiver)
    }

    fun saveTimeoutOtp() {
        LoginActivity.globalTimeStampTimeout = SystemClock.uptimeMillis().toString()
        lifecycleScope.launch {
            otpViewModel.saveLoginData(
                PreferenceKeys.TIMEOUT_OTP,
                SystemClock.uptimeMillis().toString()
            )
        }
    }

    private fun openAddedUserDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val accountRecoveryBinding = DialogAccountRecoveryBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
//            window?.setBackgroundDrawable(getDrawable(R.drawable.blue_gradient_bg))
            setCancelable(false)
            setContentView(accountRecoveryBinding.root)
            accountRecoveryBinding.apply {
                btnContinue.setOnClickListener {
                    accountRecoveryBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        moveToNext()
                    }
                }
            }
            setZoomDialogWindowAttributes()
            show()
            accountRecoveryBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }
}

