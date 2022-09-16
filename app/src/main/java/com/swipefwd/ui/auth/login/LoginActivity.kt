package com.swipefwd.ui.auth.login


import OTPModel
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.facebook.AccessToken
import com.facebook.FacebookSdk
import com.facebook.GraphRequest
import com.facebook.LoggingBehavior
import com.google.gson.Gson
import com.swipefwd.Injection
import com.swipefwd.R
import com.swipefwd.base.BaseActivity
import com.swipefwd.base.ResultState
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.DialogAlternateLoginAddedBinding
import com.swipefwd.databinding.DialogSocialLoginBinding
import com.swipefwd.databinding.DialogVerificationProblemBinding
import com.swipefwd.databinding.LoginActivityBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.auth.LogInNavigationModel
import com.swipefwd.ui.auth.phone.PhoneNumberActivity
import com.swipefwd.ui.auth.register.UserInfoActivity
import com.swipefwd.ui.auth.sociallogin.facebook.FacebookLoginActivity
import com.swipefwd.ui.auth.sociallogin.facebook.FacebookLoginResult
import com.swipefwd.ui.auth.sociallogin.google.GoogleLogInActivity
import com.swipefwd.ui.auth.sociallogin.google.GoogleLoginResult
import com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInLoginActivity
import com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInLoginResult
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.main.WebViewActivity
import com.swipefwd.ui.profile.AccountRecoveryActivity
import com.swipefwd.ui.profile.AdvancePreferenceActivity
import com.swipefwd.ui.profile.PreferencesActivity
import com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel
import com.swipefwd.ui.updateuserprofile.UserProfileActivity
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.changeStatusBarColor
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.CustomTypefaceSpan
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
import com.yuyakaido.android.cardstackview.internal.DisplayUtil
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class LoginActivity : BaseActivity<LoginActivityBinding>() {
    companion object {
        private const val TAG = "LoginActivity"
        private const val GOOGLE_SOCIAL_FLAG = 1
        private const val LINKED_IN_SOCIAL_FLAG = 2
        private const val FACEBOOK_SOCIAL_FLAG = 3
        var globalTimeStampTimeout = ""
    }

    private var accountType = ""
    var timeStampTimeout = ""
    var deviceID: String = ""

    private val progressBarHandler by lazy { ProgressBarHandler(this) }
    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory {
            LoginViewModel(
                Injection.getAppRepository(),
                Injection.getInternalAppDataStore(applicationContext),
                Injection.getAppDataBase(applicationContext)
            )
        }
    }

    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(applicationContext, AppRepository()) }
    }

    private val mActivity by lazy { this@LoginActivity }
    private val dialogs: Vector<Dialog> = Vector<Dialog>()

    //facebook login  activity contract
    private val facebookLogInActivityContract = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { onFacebookLogInResult(it) }

    //google login  activity contract
    private val googleLogInActivityContract = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { onGoogleLogInResult(it) }


    private var linkedInLogInActivityContract = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { onLinkedLoginResult(it) }


    override fun onCreateViewBinding() = LoginActivityBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //////////////////////////////////////////////////////////////////////////////////////////
        lifecycleScope.launch {
            accountType = swipeViewModel.getAccountType.firstOrNull() ?: ""
        }
        deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        AppUtils.storeDeviceId(this, deviceID)

        //////////////////////////////////////////////////////////////////////////////////////////
        changeStatusBarColor()
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id))
        FacebookSdk.setIsDebugEnabled(true)
        FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
        //////////////////////////////////////////////////////////////////////////////////////////
        //on google login click
//        binding.cardGoogle.setOnClickListener { openGoogleLoginDialog() }
        binding.btnFacebook.setOnClickListener {
            lifecycleScope.launch {
                if (AppUtils.isClickedRecently()) return@launch
                loginViewModel.resetViewModelData()
                loginViewModel.removePreference()
                loginViewModel.socialFlag = FACEBOOK_SOCIAL_FLAG
                openFacebookLoginDialog()
            }
        }
        binding.cardLinkedIn.setOnClickListener {
            lifecycleScope.launch {
                if (AppUtils.isClickedRecently()) return@launch
                loginViewModel.resetViewModelData()
                loginViewModel.removePreference()
                loginViewModel.socialFlag = LINKED_IN_SOCIAL_FLAG
                openLinkedInDialog()
            }
        }
        binding.cardPhone.setOnClickListener {
            lifecycleScope.launch {
                if (AppUtils.isClickedRecently()) return@launch
                loginViewModel.resetViewModelData()
                loginViewModel.removePreference()
                proceedToPhoneNumberScreen(false)
            }
        }
        binding.cardGoogle.setOnClickListener {
            lifecycleScope.launch {
                if (AppUtils.isClickedRecently()) return@launch
                loginViewModel.resetViewModelData()
                loginViewModel.removePreference()
                loginViewModel.socialFlag = GOOGLE_SOCIAL_FLAG
                openGoogleLoginDialog()
            }
        }
        /////////////////////////////////////////////////////////////////////
        ////observer
        loginViewModel.showLoading.observe(this) { showLoading(it) }
        ///////////////////////
        loginViewModel.errorMessage.observe(this) {
            Log.e("TAG", "ERROR === $it")
        }

        /////////////////////////////////////////////////////////////////////////////////////
        /** login data observer **/
        loginViewModel.loginUserResult().observe(this) { onLoginUserResult(it) }
        loginViewModel.socialLoginResult().observe(this) { onSocialLoginResult(it) }
        //if user registration required
        loginViewModel.userRegistrationRequired().observe(this) { onUserRegistrationRequired() }
        //////////////////////////////////////////////////////////////////////////////////////////
        init()
    }

    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if (it.isShowing) it.dismiss()
        }
    }

    private fun onUserRegistrationRequired() {
        showLoading(false)
        proceedToPhoneNumberScreen(true)
    }


    private fun proceedToPhoneNumberScreen(isSocialLogin: Boolean) {
        println("socialTypeLogin------------${loginViewModel.socialTypeLogin}")
        startActivity(
            Intent(this, PhoneNumberActivity::class.java)
                .putExtra(PhoneNumberActivity.EXTRA_KEY_FIRST_NAME, loginViewModel.firstName)
                .putExtra(PhoneNumberActivity.EXTRA_KEY_TIME_STAMP, timeStampTimeout)
                .putExtra(PhoneNumberActivity.EXTRA_KEY_SOCIAL_LOGIN, isSocialLogin)
                .putExtra("signup_type", loginViewModel.socialTypeLogin)
                .putExtra("social_id", loginViewModel.socialEmail)
        )
        overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBarHandler.show()
        } else {
            progressBarHandler.dismiss()
        }
    }
    /** ########################################################################**/
    /**  LOGIN SECTION **/
    private fun loginUser() {
        if (!AppUtils.isNetworkAvailable(this)) {
            openFailNetworkDialog { }
//            AppUtils.showMessageOK(
//                this ,
//                getString(R.string.app_name) ,
//                getString(R.string.common_retry) ,
//                getString(R.string.no_internet)
//            ) { _ , _ ->
//                loginUser()
//            }
        } else {
            loginViewModel.loginUser()
        }
    }

    private fun onLoginUserResult(result: ResultState<LogInNavigationModel>) {
        when (result) {
            is ResultState.Loading -> showLoading(true)
            is ResultState.Success -> {
                showLoading(false)
                onLoginSuccessResult(result.data)
            }
            is ResultState.Error -> {
                showLoading(false)
            }
        }
    }

    private fun onSocialLoginResult(result: ResultState<OTPModel>) {
        when (result) {
            is ResultState.Loading -> showLoading(true)
            is ResultState.Success -> {
                showLoading(false)
                onSocialLoginResult(result.data)
            }
            is ResultState.Error -> {
                showLoading(false)
            }
        }
    }


    private fun onSocialLoginResult(otpModel: OTPModel) {

        if (otpModel.data.is_verified)//if social login and user has already verified otp
        {
            /* val intent =  Intent(this, UserInfoActivity::class.java) //if  user authorized then move to home screen
               startActivity(intent)*/

            AppUtils.storeIsVerified(this, true)
            lifecycleScope.launch {
                val accountType = when (otpModel.data.userDetails.user_type) {
                    AppConstants.USER_DATER -> AppUtils.AccountTypes.Dater
                    AppConstants.USER_MATCHMAKER -> AppUtils.AccountTypes.Matchmaker
                    else -> AppUtils.AccountTypes.Dater
                }
                otpModel.apply {
                    AppUtils.storeAccountType(this@LoginActivity, accountType)

                    Handler(Looper.getMainLooper()).postDelayed({
                        when (accountType) {
                            AppUtils.AccountTypes.Dater -> {
                                when {
                                    !otpModel.data.is_basic_profile && !otpModel.data.preference -> {
                                        println("not filled pref and profile--------------->>>>>>>>>")

                                        //  saveLoginData(PreferenceKeys.PREF_COUNTRY_CODE, otpViewModel.countryCode)
                                        startActivity(
                                            Intent(mActivity, UserInfoActivity::class.java)
                                            /*.putExtra("phoneNumber", otpModel.data.userDetails.phone_number)
                                            .putExtra("countryCode", otpModel.data.userDetails)
                                            .putExtra("isConnection", 0)
//                                                        .putExtra("isConnection", otpViewModel.isConnection)
                                            .putExtra(UserInfoActivity.EXTRA_KEY_OTP,otpViewModel.userFilledOtp)*/
                                        )
                                        finishAffinity()
                                        overridePendingTransition(
                                            R.anim.enter_anim,
                                            R.anim.leave_anim
                                        )
                                    }
                                    !otpModel.data.is_basic_profile -> {
                                        println("not basic profile--------------->>>>>>>>>")

                                        AppUtils.storeLoginFlag(this@LoginActivity, 0)
                                        startActivity(
                                            Intent(
                                                this@LoginActivity,
                                                UserProfileActivity::class.java
                                            )
                                        )
                                        finishAffinity()
                                        overridePendingTransition(
                                            R.anim.enter_anim,
                                            R.anim.leave_anim
                                        )
                                    }
                                    !otpModel.data.preference -> {
                                        println("not filled pref--------------->>>>>>>>>")

                                        AppUtils.storeLoginFlag(this@LoginActivity, 0)
//                                                nextActivity(PreferencesActivity::class.java)
                                        startActivity(
                                            Intent(
                                                this@LoginActivity,
                                                PreferencesActivity::class.java
                                            )
                                        )
                                        finishAffinity()
                                        overridePendingTransition(
                                            R.anim.enter_anim,
                                            R.anim.leave_anim
                                        )
                                    }
                                    !otpModel.data.is_advance_profile -> {
                                        println("not filled advance profile--------------->>>>>>>>>")

                                        AppUtils.storeLoginFlag(this@LoginActivity, 0)

                                        startActivity(
                                            Intent(
                                                this@LoginActivity,
                                                AdvancePreferenceActivity::class.java
                                            )
                                        )
                                        finishAffinity()
                                        overridePendingTransition(
                                            R.anim.enter_anim,
                                            R.anim.leave_anim
                                        )
                                    }
                                    else -> {
                                        println("elsssssssssse --------------->>>>>>>>>")

                                        AppUtils.storeLoginFlag(this@LoginActivity, 1)


                                        moveToNext()

                                    }
                                }
                            }
                            AppUtils.AccountTypes.Matchmaker -> {
                                when {
                                    !otpModel.data.is_basic_profile -> {
                                        AppUtils.storeLoginFlag(this@LoginActivity, 0)
                                        nextActivity(UserProfileActivity::class.java)
                                    }
                                    else -> {
                                        AppUtils.storeLoginFlag(this@LoginActivity, 0)
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
        } else {
            proceedToPhoneNumberScreen(true)

        }


        /* AppUtils.storeIsVerified(this,otpModel.data.is_verified)
         AppUtils.storeUserId(this,otpModel.data.userDetails.user_id)
         AppUtils.storePrefFlag(this,otpModel.data.preference)
         AppUtils.storeProfileFlag(this,otpModel.data.is_basic_profile)
         AppUtils.storeAdvanceProfileFlag(this,otpModel.data.is_advance_profile)
         AppUtils.storeGender(this,otpModel.data.gender)
         AppUtils.storeInvitationExpired(this,otpModel.data.invitation_expired)
         AppUtils.storeUserType(this,otpModel.data.userDetails.user_type)
         AppUtils.storeFirstName(this,otpModel.data.userDetails.first_name)
         AppUtils.storeLastName(this,otpModel.data.userDetails.last_name)
         AppUtils.storeProfileImage(this,otpModel.data.userDetails.profile_image)
         AppUtils.storePhoneNumber(this,otpModel.data.userDetails.phone_number)
         AppUtils.storeAgreement(this,otpModel.data.userDetails.is_agree)
         AppUtils.storeRemainingInvitation(this,otpModel.data.userDetails.remain_invitation)
         AppUtils.storeRemainConnection(this,otpModel.data.userDetails.remaim_connection)
         AppUtils.storeInstaUserName(this,otpModel.data.userDetails.instagram_name)
         AppUtils.storeFacebook(this,otpModel.data.userDetails.facebook)
         AppUtils.storeLinkedInId(this,otpModel.data.userDetails.linkedin)
         AppUtils.storeGoogle(this,otpModel.data.userDetails.google)
         AppUtils.storeDaterDisable(this,otpModel.data.userDetails.is_dater_disabled)
         AppUtils.storeConnectorDisable(this,otpModel.data.userDetails.is_connector_disabled)*/

        /*nextActivity(PhoneNumberActivity::class.java)
        finishAffinity()*/

    }

    private fun moveToNext() {
//        nextActivity(LoadingActivity::class.java)
        nextActivity(TabManagerActivity::class.java)
        finishAffinity()
    }

    private fun onLoginSuccessResult(loginNavigation: LogInNavigationModel) {
        //clear

        lifecycleScope.launch {
            Handler(Looper.getMainLooper()).postDelayed({
                when (loginNavigation) {
                    LogInNavigationModel.Profile ->
                        nextActivity(UserProfileActivity::class.java)
                    LogInNavigationModel.Preferences ->
                        nextActivity(PreferencesActivity::class.java)
                    LogInNavigationModel.Dashboard -> {
//                        if(accountType==AppUtils.AccountTypes.Dater){
//                            nextActivity(LoadingActivity::class.java)
//                        } else {
//                            nextActivity(TabManagerActivity::class.java)
//                        }
                        nextActivity(LoadingActivity::class.java)
                    }
                    LogInNavigationModel.Agreement ->
//                        nextActivity(AgreementActivity::class.java)
                        nextActivity(LoadingActivity::class.java)
                }
                finishAffinity()
            }, 500)
        }
    }

    /** ########################################################################**/


    /** ########################################################################**/
    private fun init() {
        loginViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "9")

        binding.apply {
            val span = SpannableString(txtTermsPolicy.text)
            val typeface = ResourcesCompat.getFont(mActivity, R.font.bold)
            typeface?.let {
                span.setSpan(
                    CustomTypefaceSpan("", it),
                    57,
                    70,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                span.setSpan(
                    CustomTypefaceSpan("", it),
                    74,
                    89,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            span.setSpan(object : ClickableSpan() {
                override fun onClick(view: View) {
                    //privacy policy
                    if (AppUtils.isClickedRecently()) return
                    startActivity(
                        Intent(
                            mActivity,
                            WebViewActivity::class.java
                        ).putExtra("url", AppConstants.PRIVACY_POLICY)
                    )
//                    overridePendingTransition(R.anim.slide_in_left,  R.anim.slide_out_right)
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }, 74, 89, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            span.setSpan(object : ClickableSpan() {
                override fun onClick(view: View) {
                    //terms of use
                    if (AppUtils.isClickedRecently()) return
                    startActivity(
                        Intent(
                            mActivity,
                            WebViewActivity::class.java
                        ).putExtra("url", AppConstants.TERM_CONDITION)
                    )
//                    overridePendingTransition(R.anim.slide_in_left,  R.anim.slide_out_right)
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }, 57, 70, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            span.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.whiteColor,
                        theme
                    )
                ),
                57,
                70,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            span.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.whiteColor,
                        theme
                    )
                ),
                74,
                89,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            txtTermsPolicy.setText(span, TextView.BufferType.SPANNABLE)
            txtTermsPolicy.highlightColor = Color.TRANSPARENT
            txtTermsPolicy.movementMethod = LinkMovementMethod.getInstance()
            //trouble signing in
            val span1 = SpannableString(txtTroubleSignIn.text)
            val typeface1 = ResourcesCompat.getFont(mActivity, R.font.bold)
            typeface1?.let {
                span.setSpan(
                    CustomTypefaceSpan("", it),
                    8,
                    18,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            span1.setSpan(object : ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = true
                }

                override fun onClick(widget: View) {
                }
            }, 8, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            span1.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.whiteColor,
                        theme
                    )
                ),
                8,
                18,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            txtTroubleSignIn.apply {
                setOnClickListener {
                    if (AppUtils.isClickedRecently()) return@setOnClickListener
                    nextActivity(AccountRecoveryActivity::class.java)
                }
                setText(span1, TextView.BufferType.SPANNABLE)
                movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }


    private fun openErrorAlertDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val fbBinding = DialogSocialLoginBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(fbBinding.root)
            fbBinding.apply {
                view.visibility = View.GONE
                btnCancel.visibility = View.GONE
                btnContinue.visibility = View.GONE
               // viewTop.visibility = View.INVISIBLE //commented by nirali
                btnOk.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                        }
                    }
                }
                txtTitle.text = getString(R.string.login_failure)
                txtContent.text = getString(R.string.failure_message)
            }
            setZoomDialogWindowAttributes()
            show()
            fbBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun getFbFriendsList(accessToken: AccessToken) {
        GraphRequest.newMyFriendsRequest(
            accessToken
        ) { _, response ->
            response.let { graphResponse ->
                graphResponse?.jsonObject?.let { jsonObject ->
                    parseResponse(jsonObject)
                }
            }
        }.executeAsync()
    }

    private fun parseResponse(friends: JSONObject) {
        try {
            val facebookIds = ArrayList<String>()
            val friendsArray: JSONArray = friends["data"] as JSONArray
            Log.d("Facebook", "FRIEND_LIST: $friendsArray")
            for (i in 0 until friendsArray.length()) {
                val obj = friendsArray.getJSONObject(i)
                val userId = obj.getString("id")
                facebookIds.add(userId.toString())
            }
            Log.d("Facebook", "Ids: $facebookIds")
            loginViewModel.saveLoginData(
                PreferenceKeys.PREF_FACEBOOK_IDS,
                Gson().toJson(facebookIds)
            )
            // facebook use paging if have "next" this mean you still have friends if not start load fbFriends list
            val next = friends.getJSONObject("paging")
                .getString("next")
            Log.d("Facebook", "NEXT_FRIEND_LIST: $next")
        } catch (e1: JSONException) {
            e1.printStackTrace()
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    /** LINKEDIN SOCIAL LOGIN SECTION **/
    private fun openLinkedInDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val linkedInBinding = DialogSocialLoginBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(linkedInBinding.root)
            linkedInBinding.apply {
                view.visibility = View.GONE
                btnContinue.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.common_continue)
                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                            openLinkedInView()
                        }
                    }
                }
                btnOk.apply {
                    visibility = View.GONE
                }
                viewTop.visibility = View.VISIBLE
                btnCancel.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.common_cancel)
                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                        }
                    }
                }
                txtTitle.text = getString(R.string.linkedin_dialog_title)
                txtContent.text = getString(R.string.linkedin_dialog_content)
            }
            setZoomDialogWindowAttributes()
            show()
            linkedInBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }


    private fun openLinkedInView() {
        if (!AppUtils.isNetworkAvailable(mActivity)) {
//            openFailNetworkDialog(){openLinkedInView()}
            openFailNetworkDialog { }
//            AppUtils.showMessageOK(
//                this@LoginActivity ,
//                getString(R.string.app_name) ,
//                getString(R.string.common_retry) ,
//                getString(R.string.no_internet)
//            ) { _ , _ ->
//                openLinkedInView()
//            }
        } else {
            Log.i(TAG, "click on linkedIn label")
            val intent = Intent(this, LinkedInLoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            linkedInLogInActivityContract.launch(intent)
        }
    }


    /** this function is called when linkedin account select for the login */
    private fun onLinkedLoginResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val linkedLoginResult = result.data?.getParcelableExtra(
                LinkedInLoginActivity.EXTRA_RESULT_DATA
            ) as? LinkedInLoginResult
            if (linkedLoginResult == null) {
                openErrorAlertDialog()
                return
            }
            onLinkedInUserDetailsResult(linkedLoginResult)
        } else {
            Log.i(TAG, "linkedin login cancel ")
        }
    }

    /** this function is called on the linked in login result */
    private fun onLinkedInUserDetailsResult(result: LinkedInLoginResult) {
        if (result.resultStatus) {
            val profile = result.profileDetails
            if (profile == null) {
                openErrorAlertDialog()
                return
            }
            loginViewModel.socialType = AppConstants.SOCIAL_LINKEDIN
            loginViewModel.socialTypeLogin = AppConstants.SOCIAL_TYPE_LINKEDIN
            loginViewModel.socialId = profile.clientId
            loginViewModel.originalProfile = profile.profileImage ?: ""
            loginViewModel.firstName = profile.firstName ?: ""
            loginViewModel.lastName = profile.lastName ?: ""
            loginViewModel.socialEmail = profile.email
            loginViewModel.socialFlag = LINKED_IN_SOCIAL_FLAG
            loginViewModel.gender = ""
            loginUser()
        } else {
            Log.i(TAG, "linked in  user get details failed")
            openErrorAlertDialog()
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /** GOOGLE SOCIAL LOGIN SECTION **/
    private fun openGoogleLoginDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val dialogBinding = DialogSocialLoginBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(dialogBinding.root)
            dialogBinding.apply {
                view.visibility = View.GONE
                btnContinue.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.common_continue)
                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                            onGoogleLoginContinueClickListener()
                        }
                    }
                }
                btnOk.visibility = View.GONE
                viewTop.visibility = View.VISIBLE
                btnCancel.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.common_cancel)
                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                        }
                    }
                }
                txtTitle.text = getString(R.string.google_dialog_title)
                txtContent.text = getString(R.string.google_dialog_content)
            }
            setZoomDialogWindowAttributes()
            show()
            dialogBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    /** this function is called on click on the google login label */
    private fun onGoogleLoginContinueClickListener() {
        if (!AppUtils.isNetworkAvailable(mActivity)) {
//            openFailNetworkDialog(){onGoogleLoginContinueClickListener()}
            openFailNetworkDialog { }
//            AppUtils.showMessageOK(
//                this@LoginActivity ,
//                getString(R.string.app_name) ,
//                getString(R.string.common_retry) ,
//                getString(R.string.no_internet)
//            ) { _ , _ ->
//                onGoogleLoginContinueClickListener()
//            }
        } else {
            Log.i(TAG, "click on google label")
            /*binding.apply {
                layoutTop.visibility = View.INVISIBLE
                layoutBottom.visibility = View.INVISIBLE
            }*/
            val intent = Intent(this, GoogleLogInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            googleLogInActivityContract.launch(intent)
        }
    }

    /** this function is called when google account select for the login */
    private fun onGoogleLogInResult(result: ActivityResult) {
        /* binding.apply {
             layoutTop.visibility = View.VISIBLE
             layoutBottom.visibility = View.VISIBLE
         }*/
        if (result.resultCode == Activity.RESULT_OK) {
            val googleLoginResult = result.data?.getParcelableExtra(
                GoogleLogInActivity.EXTRA_RESULT_DATA
            ) as? GoogleLoginResult
            if (googleLoginResult == null) {
                openErrorAlertDialog()
                return
            }
            onGoogleUserDetailsResult(googleLoginResult)
        } else {
            Log.i(TAG, "google login cancel ")
        }
    }

    /** this function is called on the google login result */
    private fun onGoogleUserDetailsResult(result: GoogleLoginResult) {
        if (result.resultStatus) {
            println("onGoogleUserDetailsResult..........${result.profileDetails}")
            val profile = result.profileDetails
            if (profile == null) {
                openErrorAlertDialog()
                return
            }
            loginViewModel.socialType = AppConstants.SOCIAL_GOOGLE
            loginViewModel.socialTypeLogin = AppConstants.SOCIAL_TYPE_GOOGLE
            loginViewModel.socialId = profile.clientId
            loginViewModel.originalProfile = profile.profileImage ?: ""
            loginViewModel.firstName = profile.firstName ?: ""
            loginViewModel.lastName = profile.lastName ?: ""
            loginViewModel.socialEmail = profile.email
            loginViewModel.socialFlag = GOOGLE_SOCIAL_FLAG
            loginViewModel.gender = ""
            loginUser()
        } else {
            Log.i(TAG, "google user get details failed")
            openErrorAlertDialog()
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    /** FACEBOOK SOCIAL LOGIN SECTION **/
    private fun openFacebookLoginDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val dialogBinding = DialogSocialLoginBinding.inflate(layoutInflater)
        dialog.apply {

            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(dialogBinding.root)
            dialogBinding.apply {
                view.visibility = View.GONE
                btnContinue.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.common_continue)
                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                            onFacebookLoginContinueClickListener()
                        }
                    }
                }
                btnOk.apply {
                    visibility = View.GONE
                }
                viewTop.visibility = View.VISIBLE
                btnCancel.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.common_cancel)
                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation { dismiss() }
                    }
                }
                txtTitle.text = getString(R.string.fb_dialog_title)
                txtContent.text = getString(R.string.fb_dialog_content)
            }
            setZoomDialogWindowAttributes()
            show()
            dialogBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    /** this function is called on click on the facebook login label */
    private fun onFacebookLoginContinueClickListener() {
        if (!AppUtils.isNetworkAvailable(mActivity)) {
//            openFailNetworkDialog(){onFacebookLoginContinueClickListener()}
            openFailNetworkDialog { }
//            AppUtils.showMessageOK(
//                this@LoginActivity ,
//                getString(R.string.app_name) ,
//                getString(R.string.common_retry) ,
//                getString(R.string.no_internet)
//            ) { _ , _ ->
//                onFacebookLoginContinueClickListener()
//            }
        } else {
            Log.i(TAG, "click on facebook label")
            val intent = Intent(this, FacebookLoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            facebookLogInActivityContract.launch(intent)
        }
    }

    /** this function is called when facebook account select for the login */
    private fun onFacebookLogInResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val facebookLoginResult = result.data?.getParcelableExtra(
                FacebookLoginActivity.EXTRA_RESULT_DATA
            ) as? FacebookLoginResult
            if (facebookLoginResult == null) {
                openErrorAlertDialog()
                return
            }
            onFacebookUserDetailsResult(facebookLoginResult)
        } else {
            Log.i(TAG, "facebook login cancel ")
        }
    }

    /** this function is called on the facebook login result */
    private fun onFacebookUserDetailsResult(result: FacebookLoginResult) {
        if (result.resultStatus) {
            val profile = result.profileDetails
            if (profile == null) {
                openErrorAlertDialog()
                return
            }

            println("profile.profileImage-------->>${profile.profileImage}")

            loginViewModel.socialType = AppConstants.SOCIAL_FACEBOOK
            loginViewModel.socialTypeLogin = AppConstants.SOCIAL_TYPE_FACEBOOK
            loginViewModel.socialId = profile.clientId
            loginViewModel.originalProfile = profile.profileImage ?: ""
            loginViewModel.firstName = profile.firstName ?: ""
            loginViewModel.lastName = profile.lastName ?: ""
            loginViewModel.socialEmail = profile.email ?: ""
            loginViewModel.socialFlag = FACEBOOK_SOCIAL_FLAG
            loginViewModel.gender = profile.gender ?: ""
            loginViewModel.birthDate = profile.birthDate ?: ""
            loginViewModel.friendsDenied = profile.friendPermissionStatus

            val dob = profile.birthDate ?: ""
            var age = ""
            val dobArray = dob.split("/")
            if (dobArray.size > 2) {
                val mMonth = dob.split("/")[0]
                val mDay = dob.split("/")[1]
                val mYear = dob.split("/")[2]
                age = AppUtils.getAgeFromCurrentDate(
                    mYear.toInt(),
                    mMonth.toInt(),
                    mDay.toInt()
                )
            }

            if (age.isNotEmpty() && age.toInt() < 18) {
                openDobProblemDialog()
            } else {
                loginUser()
            }
        } else {
            Log.i(TAG, "facebook user get details failed")
            openErrorAlertDialog()
        }
    }

    private fun openDobProblemDialog() {
        val dialog = Dialog(mActivity, R.style.SlideDialogThemePsst)
        dialogs.add(dialog)
        val binding = DialogVerificationProblemBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(binding.root)
            binding.apply {

                logoOrangeVerificationProblem.layoutParams.height =
                    DisplayUtil.dpToPx(mActivity, 100F)
                logoOrangeVerificationProblem.layoutParams.width =
                    DisplayUtil.dpToPx(mActivity, 100F)
                txtMessage.setPadding(50, 0, 50, 0)
                logoOrangeVerificationProblem.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.age_image_new
                    )
                )
                txtDoItLater2.visibility = View.GONE
                txtMessage.text = getString(R.string.less_dob_message)
                txtChangeProfile.text = getString(R.string.log_out)
                textTitle.text = getString(R.string.over_limit_dob)
                txtChangeProfile.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                }
            }
            try {
                if (!dialog.isShowing) {
                    setBottomDialogWindowAttributes()
                    binding.imgDialogGradient.setDialogFadeInAnimation()
                    show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getTimeoutOtp()
    }

    private fun getTimeoutOtp() {
        lifecycleScope.launch {
            timeStampTimeout = loginViewModel.getTimeoutOTP.firstOrNull() ?: ""
            if (timeStampTimeout.isNotEmpty())
                globalTimeStampTimeout = timeStampTimeout

        }
    }

    private fun openAlternateLoginDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val alternateLoginBinding = DialogAlternateLoginAddedBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(alternateLoginBinding.root)
            alternateLoginBinding.apply {
                btnLetsGo.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.common_continue)
                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                            openLinkedInView()
                        }
                    }
                }
                Glide.with(this@LoginActivity).load(
                    when (loginViewModel.socialType) {
                        AppConstants.SOCIAL_LINKEDIN -> R.drawable.ic_linked_in
                        AppConstants.SOCIAL_FACEBOOK -> R.drawable.ic_facebook
                        else -> R.drawable.ic_google
                    }
                ).into(imgSocialLogo)
                txtPhoneNumber.text = getString(R.string.linkedin_dialog_title)
                txtUserName.text = getString(R.string.linkedin_dialog_content)
            }
            setZoomDialogWindowAttributes()
            show()
            alternateLoginBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

}