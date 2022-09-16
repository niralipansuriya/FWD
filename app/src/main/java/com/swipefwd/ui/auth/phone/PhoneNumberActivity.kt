package com.swipefwd.ui.auth.phone

import OTPModel
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.mukesh.countrypicker.Country
import com.mukesh.countrypicker.CountryPicker
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy
import com.swipefwd.Injection
import com.swipefwd.R
import com.swipefwd.base.BaseActivity
import com.swipefwd.base.ResultState
import com.swipefwd.base.getResolvedString
import com.swipefwd.data.models.ResendOtp
import com.swipefwd.databinding.DialogCountryCodeBinding
import com.swipefwd.databinding.PhoneNumberActivityBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.auth.login.LoginActivity
import com.swipefwd.ui.auth.otpverify.OtpActivity
import com.swipefwd.ui.main.CountryAdapter
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.changeStatusBarColor
import com.swipefwd.utils.AppUtils.dpToPx
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.phoneNumberValidation
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.showSnackBarMargin
import com.swipefwd.utils.AppUtils.slideView
import com.swipefwd.utils.KeyboardUtils
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.utils.numberFormat.PhoneNumberKit
import kotlinx.coroutines.launch
import java.util.*


class PhoneNumberActivity : BaseActivity<PhoneNumberActivityBinding>() {
    companion object {
        const val EXTRA_KEY_FIRST_NAME = "extra.key.firstname"
        const val EXTRA_KEY_RECOVER_ACCOUNT_EMAIL = "extra.key.recover.account.email"
        const val EXTRA_KEY_SOCIAL_LOGIN = "extra.key.social.login"
        const val EXTRA_KEY_TIME_STAMP = "extra.key.timestamp"
        const val SIGNUP_TYPE = "signup_type"
    }

    private var mCustomSnackbar: Snackbar? = null
    var isOnSameScreen = true
    var isRecoverFlow = false

    var signUpType = 0
    var socialID = ""
    private val progressBarHandler by lazy { ProgressBarHandler(this) }
    private val mActivity by lazy { this@PhoneNumberActivity }
    private val phoneViewModel: PhoneNumberViewModel by viewModels {
        viewModelFactory {
            PhoneNumberViewModel(
                Injection.getAppRepository(),
                Injection.getInternalAppDataStore(applicationContext),
                Injection.getAppDataBase(applicationContext)
            )
        }
    }
    var mToggleKeyboard = false
    var formattedFinalNumber = StringBuilder("")
    var formattedDefaultNumber: String? = null
    var formattedDefaultNumberSize = 0
    var isSocialLogin = false
    var timestampTimeout = ""
    var isKeyboardOpened = false
    var textChangedListener: MaskedTextChangedListener? = null
    var phoneNumberKit: PhoneNumberKit? = null
    private var maxCardHeight = 402.dpToPx()

    //  ALLOW IF USING DIALOG    var isDialogKeyboardOpened = false
    private var otpActivity: ActivityResultLauncher<Intent>? = null
    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    override fun onCreateViewBinding() = PhoneNumberActivityBinding.inflate(layoutInflater)


    override fun onResume() {
        super.onResume()
        if (!isOnSameScreen) {
            isOnSameScreen = true
            phoneViewModel.checkIfPhoneSuspend(
                timestampTimeout,
                LoginActivity.globalTimeStampTimeout
            )
        }
        val s = binding.edtMobile.text
        if (s!!.isNotEmpty()) binding.edtMobile.letterSpacing = 0.2f else binding.edtMobile.letterSpacing = 0f
        val number = s.toString().trim().replace(Regex("[^0-9]"), "")
        val completeNumber = phoneViewModel.countryCode + number

        if (!phoneViewModel.countryCode.isNullOrEmpty() && number.isNotBlank()) {
            if (number.length in 7..15 && mActivity.phoneNumberValidation(completeNumber)){
                binding.imgNext.isEnabled = true
                binding.imgNext1.isEnabled = true
            } else {
                binding.imgNext.isEnabled = false
                binding.imgNext1.isEnabled = false
            }
        } else {
            binding.imgNext.isEnabled = false
            binding.imgNext1.isEnabled = false
        }
    }

    override fun onStart() {
        super.onStart()
        phoneViewModel.checkIfPhoneSuspend(timestampTimeout, LoginActivity.globalTimeStampTimeout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firstName = intent?.getStringExtra(EXTRA_KEY_FIRST_NAME) ?: ""
        phoneViewModel.firstName = firstName
        isSocialLogin = intent?.getBooleanExtra(EXTRA_KEY_SOCIAL_LOGIN, false) ?: false
        timestampTimeout = intent?.getStringExtra(EXTRA_KEY_TIME_STAMP) ?: ""
        val recoverAccountEmail = intent?.getStringExtra(EXTRA_KEY_RECOVER_ACCOUNT_EMAIL) ?: ""

        if (intent?.hasExtra("signup_type")!!) {

            signUpType = intent.getIntExtra("signup_type", 0)

            println("signUpType-------->>>>>$signUpType")
            phoneViewModel.signUpType = signUpType
        }

        if (intent?.hasExtra("social_id")!!) {
            socialID = intent.getStringExtra("social_id")!!

            phoneViewModel.socialId = socialID
        }

        phoneViewModel.setRecoverEmailId(recoverAccountEmail)
        if (phoneViewModel.isForRecoverAccount()) {
            binding.imgBack.visibility = View.INVISIBLE
        }
        initPhoneFormat()
        /////////////////////////////////////////////////////////////////////////
        binding.edtMobile.requestFocus()
        otpActivity =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    //detect if user has pressed back from otp screen
                    phoneViewModel.backpress = true
                    phoneViewModel.previousNumber =
                        result.data?.getStringExtra("previousNumber") ?: ""


                }
            }

        isOnSameScreen = true
        binding.edtMobile.setOnClickListener {
            if (mCustomSnackbar != null && mCustomSnackbar?.isShown!!) {
                AppUtils.snackBarCloseAnimation(mActivity, mCustomSnackbar!!)
            }
        }

        binding.edtMobile.addTextChangedListener(object :
            PhoneNumberFormattingTextWatcher(phoneViewModel.isoCode ?: "") {
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                onPhoneTextChange(s)

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                if (mCustomSnackbar != null && mCustomSnackbar!!.isShown) {
                    AppUtils.snackBarCloseAnimation(mActivity, mCustomSnackbar!!)
                }
            }
        })

        ////////////////////////////////////////////////////////////////////////////////////////////
        changeStatusBarColor()
        binding.cardHolder.visibility = GONE
        binding.countryCardContainer.visibility = INVISIBLE
        binding.edtMobile.viewTreeObserver.addOnGlobalLayoutListener {
            if (binding.edtMobile.hasFocus()) {
                val r = Rect()
                binding.edtMobile.getWindowVisibleDisplayFrame(r)
                val screenHeight = binding.edtMobile.rootView.height
                val keypadHeight = screenHeight - r.bottom
                if (keypadHeight > screenHeight * 0.15) {
                    if (!isKeyboardOpened) {
                        isKeyboardOpened = true
                        binding.imgNext.visibility = GONE
                        binding.imgNext1.visibility = VISIBLE
                    }
                } else {
                    if (isKeyboardOpened) {
                        isKeyboardOpened = false
                        binding.imgNext1.visibility = GONE
                        binding.imgNext.visibility = VISIBLE
                    }
                }
            }
        }
        binding.imgNext.isEnabled = false
        binding.imgNext1.isEnabled = false
        binding.edtMobile.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.imgNext.performClick()
                true
            }
            false
        }
        //set default country code
        setDefaultCountryCode()
        //add keyboard toggle listener
        addKeyboardToggleListener()
        ////////////////////////////////////////////////////////////////////////////////////////////
        //click listener
        binding.imgBack.setOnClickListener { this.onBackPressed() }
        binding.imgNext.setOnClickListener { onNextButtonClick() }
        binding.imgNext1.setOnClickListener { onNextButtonClick() }
        binding.txtCode.setOnClickListener {
            binding.cardHolder.visibility = VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                binding.phoneScrollView.fullScroll(FOCUS_DOWN)
                showCountryCodeCard()
                setCountryCodeView()
            }, 70)
//            binding.phoneScrollView.fullScroll(FOCUS_DOWN)
//            showCountryCodeCard()
//            setCountryCodeView()
//            Handler(Looper.getMainLooper()).postDelayed({
//                binding.phoneScrollView.fullScroll(FOCUS_DOWN)
//            }, 370)
//        }, 100)
            //    openCountryCodeDialog()
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        //observer
        phoneViewModel.getOtpResult().observe(this, { onGetOtpResult(it) })
        phoneViewModel.getOtpEmailResult().observe(this, { onGetOtpEmailResult(it) })
        //timer
        phoneViewModel.timerRefreshed().observe(this, { onTimerRefreshed(it) })
        phoneViewModel.timerStopped().observe(this, { onTimerStopped() })
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

    }

    override fun onBackPressed() {
        if (binding.countryCardContainer.isVisible) {
            hideCountryCodeCard()
        } else {
//            super.onBackPressed()
            onBackPressedDispatcher.onBackPressed()
            overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right)
        }
    }

    private fun showCountryCodeCard() {
        binding.countryCardContainer.visibility = VISIBLE
        binding.layoutNumber.visibility = INVISIBLE
        binding.imgNext.visibility = GONE
        binding.imgNext1.visibility = GONE
        slideView(binding.countryCardContainer, 0, maxCardHeight, 600)
        val inputMethodManager =
            ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(binding.edtMobile.windowToken, 0)
        isKeyboardOpened = false
    }

    private fun onPhoneTextChange(s: Editable?) {
        if (s!!.isNotEmpty()) binding.edtMobile.letterSpacing = 0.2f else binding.edtMobile.letterSpacing = 0f
        val number = s.toString().trim().replace(Regex("[^0-9]"), "")
        val completeNumber = phoneViewModel.countryCode + number

        if (!phoneViewModel.countryCode.isNullOrEmpty() && number.isNotBlank()) {
            if (number.length in 7..15 && mActivity.phoneNumberValidation(completeNumber)){
                binding.imgNext.isEnabled = true
                binding.imgNext1.isEnabled = true
            } else {
                binding.imgNext.isEnabled = false
                binding.imgNext1.isEnabled = false
            }
        } else {
            binding.imgNext.isEnabled = false
            binding.imgNext1.isEnabled = false
        }
        /* comment by krupa
        if (number.isNotBlank()) {
            binding.imgNext.isEnabled = true
            binding.imgNext1.isEnabled = true
            binding.edtMobile.letterSpacing = 0.2f
        } else {
            binding.imgNext.isEnabled = false
            binding.imgNext1.isEnabled = false
            binding.edtMobile.letterSpacing = 0f
        }*/
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBarHandler.show()
        } else {
            progressBarHandler.dismiss()
        }
    }


    //set time value
    private fun onTimerRefreshed(timeValue: String) {

        binding.imgNext.isEnabled = false
        binding.imgNext1.isEnabled = false
        binding.timerTextView.visibility = View.VISIBLE
        binding.timerTextView.text = resources.getString(R.string.resend_timer, timeValue)
    }

    private fun onTimerStopped() {
        binding.imgNext.isEnabled = true
        binding.imgNext1.isEnabled = true
        binding.timerTextView.visibility = View.GONE
    }


    private fun onNextButtonClick() {
        val inputMethodManager =
            ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(binding.edtMobile.windowToken, 0)
        isKeyboardOpened = false

        phoneViewModel.phoneNumber =
            binding.edtMobile.text.toString().trim().replace(Regex("[^0-9]"), "")
        var completeNumber = phoneViewModel.countryCode + phoneViewModel.phoneNumber

        if (!phoneViewModel.countryCode.isNullOrEmpty()) {
            if (phoneViewModel.phoneNumber.isNotEmpty() && phoneViewModel.phoneNumber.length > 6 && phoneViewModel.phoneNumber.length < 16 && mActivity.phoneNumberValidation(
                    completeNumber
                )
            ) {
                lifecycleScope.launch {
                    if (phoneViewModel.backpress) {
                        if (phoneViewModel.isPhoneDifferentFromPreviousFilled()) {
                            phoneViewModel.saveData(PreferenceKeys.PREF_OTP_COUNTER, 0)
                            phoneViewModel.saveData(
                                PreferenceKeys.PREF_PREVIOUS_NUMBER, true
                            )
                        }
                    }
                }
                phoneViewModel.saveData(
                    PreferenceKeys.PREF_PHONE_NUMBER, phoneViewModel.phoneNumber
                )


                if (phoneViewModel.isFromDeepLink)
                {
                    getOtpEmail()
                }
                else
                {
                    getOtp()
                }

            } else {
                mCustomSnackbar = showSnackBarMargin(
                    v = binding.layoutMain,
                    message = getString(R.string.invalid_number)
                )
            }
        } else {
            mCustomSnackbar = showSnackBarMargin(
                v = binding.layoutMain,
                message = getString(R.string.provide_country)
            )
        }
    }


    private fun getOtp() {
        if (!AppUtils.isNetworkAvailable(this)) {
            openFailNetworkDialog { nextActivity(LoginActivity::class.java) }
//            AppUtils.showMessageOK(
//                this,
//                getString(R.string.app_name),
//                getString(R.string.common_retry),
//                getString(R.string.no_internet)
//            ) { _, _ ->
//                getOtp()
//            }
        } else {
            phoneViewModel.getOtp(isSocialLogin)
        }
    }


    fun getOtpEmail()
    {

        if (!AppUtils.isNetworkAvailable(this)) {
            openFailNetworkDialog { nextActivity(LoginActivity::class.java) }
        } else {
            phoneViewModel.getOtpEamil()
        }
    }
    private fun onGetOtpResult(result: ResultState<OTPModel>) {

        println("onGetOtpResult------->>>>${result}")
        when (result) {
            is ResultState.Success -> {
                println("onGetOtpResult Success------->>>>${result}")

                showLoading(false)
                onGetOtpSuccess(result.data)
            }
            is ResultState.Error -> {
                println("onGetOtpResult Error------->>>>${result}")

                showLoading(false)
                onGetOtpResultError(result.getResolvedString(this))
            }
            is ResultState.Loading -> showLoading(true)
        }
    }
    private fun onGetOtpEmailResult(result: ResultState<ResendOtp>) {

        println("onGetOtpResult------->>>>${result}")
        when (result) {
            is ResultState.Success -> {
                println("onGetOtpResult Success------->>>>${result}")

                showLoading(false)
                onGetOtpEmailSuccess(result.data)
            }
            is ResultState.Error -> {
                println("onGetOtpResult Error------->>>>${result}")

                showLoading(false)
                onGetOtpResultError(result.getResolvedString(this))
            }
            is ResultState.Loading -> showLoading(true)
        }
    }

    private fun onGetOtpResultError(error: String?) {
        val message = error ?: getString(R.string.error_something_went_wrong)
        showSnackBarMargin(v = binding.layoutMain, message = message)
    }

    private fun onGetOtpSuccess(data: OTPModel) {

        // if (data.data.userDetails.is_verified == 0) {
        if (!data.data.is_verified) {
            isOnSameScreen = false
            otpActivity?.launch(
                Intent(this, OtpActivity::class.java)
                    .putExtra(OtpActivity.EXTRA_KEY_SNACK_BAR_MESSAGE, data.message)
                    .putExtra(OtpActivity.EXTRA_KEY_COUNTRY_CODE, phoneViewModel.countryCode)
                    .putExtra(OtpActivity.EXTRA_KEY_PHONE_NUMBER, phoneViewModel.phoneNumber)
                    .putExtra(OtpActivity.EXTRA_KEY_ISO_CODE, phoneViewModel.isoCode)
                    .putExtra(OtpActivity.EXTRA_KEY_OTP, data.code)
                    .putExtra(OtpActivity.EXTRA_KEY_FIRST_NAME, phoneViewModel.firstName)
                    .putExtra(OtpActivity.IS_FROM_DEEP_LINK, phoneViewModel.isFromDeepLink)
                    .putExtra(OtpActivity.EXTRA_KEY_EMAIL, phoneViewModel.getRecoveryEmail())
            )
            overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
        }
        /*  else if(data.data.userDetails.is_verified == 1 && data.data.userDetails.is_profile_setup==0){

          }*/

    }
    private fun onGetOtpEmailSuccess(data: ResendOtp) {

        // if (data.data.userDetails.is_verified == 0) {
        if (data.code==1) {
            isOnSameScreen = false
            otpActivity?.launch(
                Intent(this, OtpActivity::class.java)
                    .putExtra(OtpActivity.EXTRA_KEY_SNACK_BAR_MESSAGE, data.message)
                    .putExtra(OtpActivity.EXTRA_KEY_COUNTRY_CODE, phoneViewModel.countryCode)
                    .putExtra(OtpActivity.EXTRA_KEY_PHONE_NUMBER, phoneViewModel.phoneNumber)
                    .putExtra(OtpActivity.EXTRA_KEY_ISO_CODE, phoneViewModel.isoCode)
                    .putExtra(OtpActivity.EXTRA_KEY_OTP, data.code)
                    .putExtra(OtpActivity.EXTRA_KEY_FIRST_NAME, phoneViewModel.firstName)
                    .putExtra(OtpActivity.IS_FROM_DEEP_LINK, phoneViewModel.isFromDeepLink)
                    .putExtra(OtpActivity.EXTRA_KEY_EMAIL, phoneViewModel.getRecoveryEmail())
            )
            overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
        }
        /*  else if(data.data.userDetails.is_verified == 1 && data.data.userDetails.is_profile_setup==0){

          }*/

    }

    private fun setDefaultCountryCode() {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            resources?.configuration?.locales?.get(0)
        } else {
            //noinspection deprecation
            resources?.configuration?.locale
        } ?: Locale.US
        val countryPicker = CountryPicker.Builder().with(this).build()
        val localeCountry = countryPicker?.getCountryByLocale(locale)
        phoneViewModel.countryCode = localeCountry?.dialCode
        phoneViewModel.isoCode = localeCountry?.code
        binding.txtCode.text = phoneViewModel.countryCode ?: ""
        attachFormatNumber()
    }

    private fun addKeyboardToggleListener() {
        KeyboardUtils.addKeyboardToggleListener(
            this,
            object : KeyboardUtils.SoftKeyboardToggleListener {
                override fun onToggleSoftKeyboard(isVisible: Boolean) {
                    if (!binding.edtMobile.hasFocus()) {
                        if (isVisible) {
                            if (binding.countryCardContainer.isVisible) {
                                binding.phoneScrollView.fullScroll(FOCUS_DOWN)
                                binding.edtSearch.requestFocus()
                            }
                        } else {
                            if (!binding.countryCardContainer.isVisible) {
                                Handler(Looper.getMainLooper()).postDelayed({
                                    //   binding.phoneScrollView.fullScroll(FOCUS_UP)
                                    //   binding.imgNext1.visibility = GONE
                                }, 100)

                            }
                        }
                    }

                }
            })
    }

    private fun hideCountryCodeCard() {
        slideView(binding.countryCardContainer, maxCardHeight, 0, 600)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.apply {
                binding.countryCardContainer.visibility = INVISIBLE
                cardHolder.visibility = GONE
                binding.layoutNumber.visibility = VISIBLE
                binding.imgNext.visibility = VISIBLE
                binding.imgNext1.visibility = GONE
            }
        }, 300)

        val inputMethodManager =
            ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(binding.countryCardContainer.windowToken, 0)
        isKeyboardOpened = false
    }

    private fun setCountryCodeView() {
        val topCountryList: ArrayList<Country> =
            ArrayList(arrayListOf(*CountryPicker.TOPCOUNTRIES))
        val countryList: ArrayList<Country> =
            ArrayList(arrayListOf(*CountryPicker.COUNTRIES)).apply {
                this.sortBy {
                    it.name
                }
            }
        var combinedCountryList: ArrayList<Country> = ArrayList<Country>()
        combinedCountryList.addAll(topCountryList)
        combinedCountryList.addAll(countryList)
        val adapter =
            CountryAdapter(this, combinedCountryList, topCountryList, countryList) { country ->
                hideCountryCodeCard()
//            isDialogKeyboardOpened=false
                // view.visibility = View.VISIBLE
                //  binding.edtMobile.requestFocus()
                mActivity.apply {
                    binding.edtMobile.setText("")
                    phoneViewModel.countryCode = country.dialCode
                    phoneViewModel.isoCode = country.code
                    binding.txtCode.text = phoneViewModel.countryCode
                    attachFormatNumber()
                }
            }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
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
            }

            override fun afterTextChanged(s: Editable?) {
                adapter.search(s.toString()) {
                    // do nothing
                    if (it) {
//                        binding.
                    }
                }
            }

        })
        binding.rvCountry.adapter = adapter
    }


    override fun onPause() {
        super.onPause()
        if (!isOnSameScreen)
            phoneViewModel.stopTimer()
        dialogs.forEach {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        phoneViewModel.stopTimer()
    }

    private fun marginBottom(view: View, bottom: Int) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val p: ViewGroup.MarginLayoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, 0, bottom)
            view.requestLayout()
        }
    }

    private fun initPhoneFormat() {
        phoneNumberKit = PhoneNumberKit.Builder(this)
            .setIconEnabled(false)
            .build()
    }

    private fun attachFormatNumber() {
        formattedDefaultNumber = phoneNumberKit?.getFormattedNumber(phoneViewModel.isoCode.toString())
        setupListener(binding.edtMobile, formattedDefaultNumber.toString())
    }

    private fun setupListener(editText: EditText, pattern: String) {
        editText.removeTextChangedListener(textChangedListener)
        textChangedListener = MaskedTextChangedListener.installOn(
            editText,
            pattern,
            emptyList(),
            AffinityCalculationStrategy.WHOLE_STRING,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {


                }
            }
        )
    }

}