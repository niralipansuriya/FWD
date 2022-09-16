package com.swipefwd.ui.home.settings

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.mukesh.countrypicker.CountryPicker
import com.skydoves.balloon.balloon
import com.swipefwd.R
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.*
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInLoginActivity
import com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInLoginResult
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.settings.deleteaccount.AccountDeleteSelectionActivity
import com.swipefwd.ui.profile.EmailActivity
import com.swipefwd.ui.profile.InstagramWebActivity
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.ToolTipFactory
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class AccountActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "AccountActivity"
    }

    private lateinit var binding: ActivityAccountBinding
    private val toolTipBinding by balloon<ToolTipFactory>()
    private val mActivity by lazy {
        this@AccountActivity
    }
    private val settingsViewModel: SettingsViewModel by viewModels {
        viewModelFactory { SettingsViewModel(mActivity, AppRepository()) }
    }
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var isFacebookConnected = false
    private var isInstagramConnected = false
    private var isLinkedInConnected = false
    private var userId = 0
    private var callbackManager: CallbackManager? = null
    private var instagramStartForResult: ActivityResultLauncher<Intent>? = null
    private var instaImagesList = ArrayList<String>()
    private var instagramId = ""
    private var instagramUserName = ""
    private var fb_name = ""
    private var fb_id = ""
    private var isoCode = ""
    private var linkedInId = ""
    private var linkedInName = ""
    private var linkedInStartForResult: ActivityResultLauncher<Intent>? = null
    private lateinit var mSignInClient: GoogleSignInClient
    private var googleStartForResult: ActivityResultLauncher<Intent>? = null
    private var googleId = ""
    private var googleName = ""
    private var isGoogleConnected = false
    private var socialFlag = 0
    private var countryCode = ""
    private var recoveryEmail = ""
    var lastKey = ""


    private var linkedInLogInActivityContract = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { onLinkedLoginResult(it) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instagramStartForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val code = result.data?.getStringExtra("instagramCode") ?: ""
                    Log.e("TAG", "INSTA OK == $code")
                    profileViewModel.getInstagramToken(code)
                }
            }
        googleStartForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    if (task.isSuccessful) {
                        handleSignInResult(task.result)
                    }
                }
            }
        init()
    }


    /** this function is called when linkedin account select for the login */
    private fun onLinkedLoginResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val linkedLoginResult = result.data?.getParcelableExtra(
                LinkedInLoginActivity.EXTRA_RESULT_DATA
            ) as? LinkedInLoginResult
            if (linkedLoginResult == null) {
//                openErrorAlertDialog()
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
                //               openErrorAlertDialog()
                return
            }
//            loginViewModel.socialType = AppConstants.SOCIAL_LINKEDIN
//            loginViewModel.socialId = profile.clientId
//            loginViewModel.originalProfile = profile.profileImage ?: ""
//            loginViewModel.firstName = profile.firstName ?: ""
//            loginViewModel.lastName = profile.lastName ?: ""
//            loginViewModel.socialEmail = profile.email
//            loginViewModel.socialFlag = LoginActivity.LINKED_IN_SOCIAL_FLAG
//            loginViewModel.gender = ""
//            loginUser()
        } else {
            Log.i(TAG, "linked in  user get details failed")
            //  openErrorAlertDialog()
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////


    private fun init() {
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id))
        FacebookSdk.setIsDebugEnabled(true)
        FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
        callbackManager = CallbackManager.Factory.create()

        val scopeBirthday = Scope("https://www.googleapis.com/auth/user.birthday.read")
        val scopeGender = Scope("https://www.googleapis.com/auth/user.gender.read")

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            //  .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestId()
            .requestProfile()
            .requestScopes(Scope(Scopes.PLUS_ME), scopeBirthday, scopeGender)
            .build()
        mSignInClient = GoogleSignIn.getClient(this, gso)

       // val people =

        binding.apply {
            lifecycleScope.launch {
                userId = settingsViewModel.getUserId.firstOrNull() ?: 0
                val phone = settingsViewModel.getPhoneNumber.firstOrNull() ?: ""
                val locale = resources?.configuration?.locale
                val country2 =
                    let { CountryPicker.Builder().with(mActivity).build() }
                val country1 = locale?.let { country2?.getCountryByLocale(locale) }
                if (country1 != null) {
                    isoCode = country1.code ?: ""
                    countryCode = country1.dialCode ?: ""
                }
                var countryPhone = ""
                countryPhone = if (phone.startsWith("+", false)) {
                    PhoneNumberUtils.formatNumber(phone, isoCode).replace(" ", "-")
                } else {
                    PhoneNumberUtils.formatNumber("$countryCode$phone", isoCode).replace(" ", "-")
                }
                edtPhone.setText(countryPhone)
                instagramId = profileViewModel.getInstaId.firstOrNull() ?: ""
                instagramUserName = profileViewModel.getInstaName.firstOrNull() ?: ""
                if (instagramUserName.isNotEmpty()) {
                    txtInsta.apply {
                        setText(instagramUserName)
                        setBackgroundResource(R.drawable.corner_blue_border_bg)
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.color_instagram,
                            0,
                            R.drawable.cancel_light_grey,
                            0
                        )
                    }
                }
                toggleDating.setOnClickListener {
                    lastKey = toggleDating.tag.toString()
                    if (!toggleDating.isChecked) {
                        toggleDating.isChecked = false
                        userSettings(lastKey)
                    } else {
                        openDisableDaterDialog()
                    }
                }
                toggleMatchMaking.setOnClickListener {
                    lastKey = toggleMatchMaking.tag.toString()
                    if (!toggleMatchMaking.isChecked) {
                        toggleMatchMaking.isChecked = false
                        userSettings(lastKey)
                    } else {
                        openDisableConnectorDialog()
                    }
                }
                toggleConnection.setOnClickListener {
                    lastKey = toggleConnection.tag.toString()
                    userSettings(lastKey)
                }
                toggleFwds.setOnClickListener {
                    lastKey = toggleFwds.tag.toString()
                    userSettings(lastKey)
                }
                toggleMatches.setOnClickListener {
                    lastKey = toggleMatches.tag.toString()
                    userSettings(lastKey)
                }
                toggleMessages.setOnClickListener {
                    lastKey = toggleMessages.tag.toString()
                    userSettings(lastKey)
                }
            }

            getUserSettingsDetails()

            imgInfo.setOnClickListener {
                val spannable = SpannableString(getString(R.string.account_visibility_tip))
                spannable.setSpan(
                    ForegroundColorSpan(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.color_orange,
                            theme
                        )
                    ),
                    228,
                    spannable.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                toolTipBinding.getContentView().apply {
                    findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                        getString(R.string.account_visibility)
                    findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
                    findViewById<AppCompatTextView>(R.id.txtTipDesc).apply {
                        setText(spannable, TextView.BufferType.SPANNABLE)
                        highlightColor = Color.TRANSPARENT
                        movementMethod = LinkMovementMethod.getInstance()
                    }
                    findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {
                        var animationTipClose = AnimationUtils.loadAnimation(mActivity , R.anim.tip_slide_up)
                        toolTipBinding.getContentView().startAnimation(animationTipClose)
                        animationTipClose.setAnimationListener(object : Animation.AnimationListener{
                            override fun onAnimationStart(p0: Animation?) {

                            }

                            override fun onAnimationEnd(p0: Animation?) {
                                toolTipBinding.dismiss()
                            }

                            override fun onAnimationRepeat(p0: Animation?) {

                            }

                        })
                    }
                }
                toolTipBinding.getContentView().startAnimation(
                    AnimationUtils.loadAnimation(
                        mActivity ,
                        R.anim.tip_slide_down
                    )
                )
               // toolTipBinding.showAlignTop(it)
                toolTipBinding.showAlignBottom(it)
            }

            btnClose.setOnClickListener {
                if(!isTaskRoot)
                 onBackPressed()
                else {
                    nextActivity(LoadingActivity::class.java)
                }
            }

            btnDeleteAccount.setOnClickListener {

                nextActivity(AccountDeleteSelectionActivity::class.java)
            }

            txtFb.setOnClickListener {
                if (txtFb.text.toString().isEmpty()) {
                    LoginManager.getInstance().logOut()
                    doFacebookLogin()
                } else {
                    openUnlinkFbDialog()
                }
            }

            txtInsta.setOnClickListener {
                //instagram integration
                if (txtInsta.text.toString().isEmpty()) {
                    instagramStartForResult?.launch(
                        Intent(
                            mActivity,
                            InstagramWebActivity::class.java
                        )
                    )
                } else {
                    openUnlinkInstaDialog()
                }
            }
            txtLinkedin.setOnClickListener {
                if (txtLinkedin.text.toString().trim().isEmpty()) {
                    val intent = Intent(this@AccountActivity, LinkedInLoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                    linkedInLogInActivityContract.launch(intent)
                } else {
                    openUnlinkLinkedInDialog()
                }
            }
            txtGoogle.setOnClickListener {
                if (txtGoogle.text.toString().trim().isEmpty()) {
                    mSignInClient.signOut()
                    googleStartForResult?.launch(mSignInClient.signInIntent)
                } else {
                    openUnlinkGoogleDialog()
                }
            }
            txtEmail.setOnClickListener {
                if (txtEmail.text.toString().isEmpty()) {
                    startActivity(
                        Intent(
                            mActivity,
                            EmailActivity::class.java
                        ).putExtra("EditProfile", true)
                    )
//                    overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                } else {
                    openUnlinkEmailDialog()
                }
            }
            initObservers()
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            recoveryEmail = settingsViewModel.getEmailCode.firstOrNull() ?: ""
         //   settingsViewModel.soc
            if (recoveryEmail.isNotEmpty()) {
                binding.txtEmail.apply {
                    setText(recoveryEmail)
                    setBackgroundResource(R.drawable.corner_blue_border_bg)
                    setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.cancel_light_grey,
                        0
                    )
                }
            }
        }
    }

    // google login
    private fun handleSignInResult(acct: GoogleSignInAccount) {
        socialFlag = 3
        googleId = acct.id ?: ""
        googleName = acct.email ?: ""
        isGoogleConnected = true
        userSettings("is_google_connect")
    }

    // facebook login
    private fun doFacebookLogin() {
        LoginManager.getInstance()
            .logInWithReadPermissions(
                this@AccountActivity,
                listOf("email", "public_profile", "user_friends", "user_birthday" ,"user_gender")
            )
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d("TAG", loginResult.accessToken.token)
                    val request = GraphRequest.newMeRequest(loginResult.accessToken)
                    { _, graphResponse ->
                        try {
                            graphResponse?.jsonObject?.let { response ->
                                socialFlag = 1
                                if (response.getString("first_name").isNotEmpty()) {
                                    isFacebookConnected = true
                                    fb_name =
                                        response.getString("first_name") + " " + response.getString(
                                            "last_name"
                                        )
                                    fb_id = response.getString("id")
                                }
                                getFbFriendsList(loginResult.accessToken)
                                userSettings("is_facebook_connect")
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    val parameters = Bundle()
                    parameters.putString("fields", "id,first_name,last_name,email")
                    request.parameters = parameters
                    request.executeAsync()
                }

                override fun onCancel() {
                    Log.d("Facebook", "FB_LoginResponse==> Cancel")
                }

                override fun onError(exception: FacebookException) {
                    Log.d("Facebook", "Exception_FB_LoginResponse==>" + exception.message)
                }
            })
    }

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
            val friendsArray = friends["data"] as JSONArray
            Log.d("Facebook", "FRIEND_LIST: $friendsArray")
            sendFacebookIds(friendsArray)
            // facebook use paging if have "next" this mean you still have friends if not start load fbFriends list
            val next = friends.getJSONObject("paging")
                .getString("next")
            Log.d("Facebook", "NEXT_FRIEND_LIST: $next")
        } catch (e1: JSONException) {
            e1.printStackTrace()
        }
    }

    private fun sendFacebookIds(friendsArray: JSONArray) {
        val facebookIds = ArrayList<String>()
        for (i in 0 until friendsArray.length()) {
            val obj = friendsArray.getJSONObject(i)
            val userId = obj.getString("id")
            facebookIds.add(userId.toString())
        }
        val jsonArray = JsonArray()
        facebookIds.forEach {
            jsonArray.add(it)
        }
        val apiRequest = JsonObject().apply {
            add("facebook_ids", jsonArray)
        }
        lifecycleScope.launch {
            settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                settingsViewModel.sendFacebookIds(
                    "Bearer $authToken", apiRequest
                )
            }
        }
    }

    private fun openUnlinkFbDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val fbBinding = DialogUnlinkFbBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(fbBinding.root)
            fbBinding.btnFbCancel.setOnClickListener {
                fbBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            fbBinding.imgDialogGradient.setOnTouchListener { v, event ->
                if (event.action === MotionEvent.ACTION_DOWN) {
                    fbBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dialog.dismiss()
                    }
                    true
                }
                false
            }
//            fbBinding.pullDown.setOnTouchListener { v, event ->
//                if (event.action === MotionEvent.ACTION_DOWN) {
//                    fbBinding.imgDialogGradient.setDialogFadeOutAnimation {
//                        dialog.dismiss()
//                    }
//                    true
//                }
//                false
//            }
            fbBinding.btnFbYes.setOnClickListener {
                fbBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    isFacebookConnected = false
                    fb_name = ""
                    fb_id = ""
                    mActivity.binding.apply {
                        txtFb.apply {
                            setText("")
                            setBackgroundResource(R.drawable.grey_border_bg)
                            setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.facebook_gray,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                        }
                    }
                    userSettings("is_facebook_connect")
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                fbBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openDisableDaterDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val disableBinding = DialogDisableDaterBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(disableBinding.root)
            disableBinding.apply {
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                        }
                        true
                    }
                    false
                }
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dialog.dismiss()
//                        }
//                        true
//                    }
//                    false
//                }
                btnCancel.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        binding.toggleDating.isChecked = false
                    }
                }
                btnYes.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        binding.toggleDating.isChecked = true
                        userSettings(lastKey)
                    }
                }
                try {
                    setBottomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun openDisableConnectorDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val disableBinding = DialogDisableConnectorBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(disableBinding.root)
            disableBinding.apply {
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                        }
                        true
                    }
                    false
                }
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dialog.dismiss()
//                        }
//                        true
//                    }
//                    false
//                }
                btnCancel.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        binding.toggleMatchMaking.isChecked = false
                    }
                }
                btnYes.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        binding.toggleMatchMaking.isChecked = true
                        userSettings(lastKey)
                    }
                }
                try {
                    setBottomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun openUnlinkInstaDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val instagramBinding = DialogUnlinkInstagramBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(instagramBinding.root)
            instagramBinding.imgDialogGradient.setOnTouchListener { v, event ->
                if (event.action === MotionEvent.ACTION_DOWN) {
                    instagramBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dialog.dismiss()
                    }
                    true
                }
                false
            }
//            instagramBinding.pullDown.setOnTouchListener { v, event ->
//                if (event.action === MotionEvent.ACTION_DOWN) {
//                    instagramBinding.imgDialogGradient.setDialogFadeOutAnimation {
//                        dialog.dismiss()
//                    }
//                    true
//                }
//                false
//            }
            instagramBinding.btnInstaCancel.setOnClickListener {
                instagramBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            instagramBinding.btnInstaYes.setOnClickListener {
                instagramBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    isInstagramConnected = false
                    instagramId = ""
                    instagramUserName = ""
                    mActivity.binding.apply {
                        txtInsta.apply {
                            setText(instagramUserName)
                            setBackgroundResource(R.drawable.grey_border_bg)
                            setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.instagram_gray,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                        }
                        profileViewModel.removePreference(PreferenceKeys.PREF_INSTA_ID)
                        profileViewModel.removePreference(PreferenceKeys.PREF_INSTA_NAME)
                    }
                    userSettings("is_instagram_connect")
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                instagramBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openUnlinkLinkedInDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val linkedInBinding = DialogUnlinkLinekdinBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(linkedInBinding.root)
            linkedInBinding.apply {
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                        }
                        true
                    }
                    false
                }
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dialog.dismiss()
//                        }
//                        true
//                    }
//                    false
//                }
                btnCancel.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                }
                btnYes.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        isLinkedInConnected = false
                        linkedInId = ""
                        linkedInName = ""
                        mActivity.binding.apply {
                            txtLinkedin.apply {
                                setText(linkedInName)
                                setBackgroundResource(R.drawable.grey_border_bg)
                                setCompoundDrawablesWithIntrinsicBounds(
                                    R.drawable.linkedin_gray,
                                    0,
                                    R.drawable.right_arrow,
                                    0
                                )
                            }
                            profileViewModel.removePreference(PreferenceKeys.PREF_LINKEDIN_ID)
                            profileViewModel.removePreference(PreferenceKeys.PREF_LINKEDIN_NAME)
                        }
                        userSettings("is_linkedin_connect")
                    }
                }

                try {
                    setBottomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun openUnlinkGoogleDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val googleBinding = DialogUnlinkGoogleBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(googleBinding.root)
            googleBinding.apply {
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                        }
                        true
                    }
                    false
                }
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                        }
                        true
                    }
                    false
                }
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dialog.dismiss()
//                        }
//                        true
//                    }
//                    false
//                }
                btnCancel.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                }
                btnYes.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        isGoogleConnected = false
                        googleId = ""
                        googleName = ""
                        mActivity.binding.apply {
                            txtGoogle.apply {
                                setText(googleName)
                                setBackgroundResource(R.drawable.grey_border_bg)
                                setCompoundDrawablesWithIntrinsicBounds(
                                    R.drawable.google_gray,
                                    0,
                                    R.drawable.right_arrow,
                                    0
                                )
                            }
                        }
                        userSettings("is_google_connect")
                    }
                }

                try {
                    setBottomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun openUnlinkEmailDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val emailBinding = DialogUnlinkEmailBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(emailBinding.root)
            emailBinding.apply {
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                        }
                        true
                    }
                    false
                }
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dialog.dismiss()
//                        }
//                        true
//                    }
//                    false
//                }
                btnCancel.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                }
                btnYes.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        recoveryEmailId()
                    }
                }

                try {
                    setBottomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun userSettings(key: String) {
        val jsonObject = JsonObject().apply {
            binding.apply {
                when (key) {
                    "is_facebook_connect" -> {
                        addProperty("is_facebook_connect", isFacebookConnected)
                        addProperty("facebook_name", fb_name)
                        addProperty("facebookid", fb_id)
                    }
                    "is_instagram_connect" -> {
                        addProperty("is_instagram_connect", isInstagramConnected)
                        addProperty("instagram_name", instagramUserName)
                    }
                    "is_linkedin_connect" -> {
                        addProperty("is_linkedin_connect", isLinkedInConnected)
                        addProperty("linkedin_name", linkedInName)
                        addProperty("linkedinid", linkedInId)
                    }
                    "is_google_connect" -> {
                        addProperty("is_google_connect", isGoogleConnected)
                        addProperty("google_name", googleName)
                        addProperty("googleid", googleId)
                    }
                    getString(R.string.user_dater) -> {
                        addProperty("is_dater_disabled", toggleDating.isChecked)
                    }
                    getString(R.string.user_matchmaker) -> {
                        addProperty("is_connector_disabled", toggleMatchMaking.isChecked)
                    }
                    getString(R.string.add_new_connection) -> {
                        addProperty("is_newconnection_notify", toggleConnection.isChecked)
                    }
                    getString(R.string.new_fwd) -> {
                        addProperty("is_newfwd_notify", toggleFwds.isChecked)
                    }
                    getString(R.string.new_matches) -> {
                        addProperty("is_newmatchmaker_notify", toggleMatches.isChecked)
                    }
                    getString(R.string.new_messages) -> {
                        addProperty("is_newmessage_notify", toggleMessages.isChecked)
                    }
                }
            }
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){userSettings(key)}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    userSettings(key)
//                }
            }
            else -> {
                lifecycleScope.launch {
                    settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        settingsViewModel.userSettingsUpdate(
                            "Bearer $authToken", userId, jsonObject
                        )
                    }
                }
            }
        }
    }

    private fun getUserSettingsDetails() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getUserSettingsDetails()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getUserSettingsDetails()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        settingsViewModel.getUserSettings(
                            "Bearer $authToken", userId
                        )
                    }
                }
            }
        }
    }

    private fun initObservers() {
        settingsViewModel.apply {
            showLoading.observe(mActivity) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(mActivity) {
                Log.e("TAG" , "ERROR === $it")
            }
            settingsError.observe(mActivity) {
                Log.e("TAG" , "ERROR=== $it")
                binding.apply {
                    mActivity.showSnackBar(layoutMain , it.message.toString())
                    if (lastKey == getString(R.string.user_dater)) {
                        toggleDating.isChecked = !toggleDating.isChecked
                        profileViewModel.savePreference(
                            PreferenceKeys.PREF_DATER_DISABLED ,
                            toggleDating.isChecked
                        )

                    } else if (lastKey == getString(R.string.user_matchmaker)) {
                        toggleMatchMaking.isChecked = !toggleMatchMaking.isChecked
                        profileViewModel.savePreference(
                            PreferenceKeys.PREF_CONNECTOR_DISABLED ,
                            toggleMatchMaking.isChecked
                        )
                    }
                }
            }
            settingsData.observe(mActivity) {
                if (it.response == "success") {
                    binding.apply {
                        when (socialFlag) {
                            1 -> {
                                if (isFacebookConnected) {
                                    txtFb.apply {
                                        setText(fb_name)
                                        setBackgroundResource(R.drawable.corner_blue_border_bg)
                                        setCompoundDrawablesWithIntrinsicBounds(
                                            R.drawable.color_facebook ,
                                            0 ,
                                            R.drawable.cancel_light_grey ,
                                            0
                                        )
                                    }
                                }
                            }
                            2 -> {
                                if (isInstagramConnected) {
                                    txtInsta.apply {
                                        setText(instagramUserName)
                                        setBackgroundResource(R.drawable.corner_blue_border_bg)
                                        setCompoundDrawablesWithIntrinsicBounds(
                                            R.drawable.color_instagram ,
                                            0 ,
                                            R.drawable.cancel_light_grey ,
                                            0
                                        )
                                    }
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_INSTA_ID ,
                                        instagramId
                                    )
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_INSTA_NAME ,
                                        instagramUserName
                                    )
                                }
                            }
                            3 -> {
                                if (isGoogleConnected) {
                                    txtGoogle.apply {
                                        setText(googleName)
                                        setBackgroundResource(R.drawable.corner_blue_border_bg)
                                        setCompoundDrawablesWithIntrinsicBounds(
                                            R.drawable.google_account ,
                                            0 ,
                                            R.drawable.cancel_light_grey ,
                                            0
                                        )
                                    }
                                }
                            }
                            4 -> {
                                if (isLinkedInConnected) {
                                    txtLinkedin.apply {
                                        setText(linkedInName)
                                        setBackgroundResource(R.drawable.corner_blue_border_bg)
                                        setCompoundDrawablesWithIntrinsicBounds(
                                            R.drawable.linkedin_blue ,
                                            0 ,
                                            R.drawable.cancel_light_grey ,
                                            0
                                        )
                                    }
                                }
                            }
                        }
                        if (lastKey == getString(R.string.user_dater)) {

                            if (!toggleDating.isChecked) {
                                profileViewModel.savePreference(
                                    PreferenceKeys.PREF_DATER_DISABLED ,
                                    false
                                )
                                disableDialog(false)
                            } else {
                                profileViewModel.savePreference(
                                    PreferenceKeys.PREF_DATER_DISABLED ,
                                    true
                                )
                                disableDialog(true)
                            }

                        }
                        if (lastKey == getString(R.string.user_matchmaker)) {
                            if (!toggleMatchMaking.isChecked) {
                                profileViewModel.savePreference(
                                    PreferenceKeys.PREF_CONNECTOR_DISABLED ,
                                    false
                                )
                                disableDialog(false)
                            } else {
                                profileViewModel.savePreference(
                                    PreferenceKeys.PREF_CONNECTOR_DISABLED ,
                                    true
                                )
                                disableDialog(true)
                            }
                        }
                    }

                }
            }
            getSettingsError.observe(mActivity) {
                Log.e("TAG" , "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain , it.response.toString())
            }
            getSettingsData.observe(mActivity) {
                Log.e("TAG" , "DATA=== ${it.data}")
                it.data.let { data ->
                    val result = data!!
                    if (it != null) {
                        isFacebookConnected = result.isFacebookConnect!!
                        isInstagramConnected = result.isInstagramConnect!!
                        isLinkedInConnected = result.isLinkedinConnect!!
                        isGoogleConnected = result.isGoogleConnect!!
                        binding.apply {
                            if (isInstagramConnected) {
                                instagramUserName = result.instagram_name ?: ""
                                txtInsta.apply {
                                    setText(instagramUserName)
                                    setBackgroundResource(R.drawable.corner_blue_border_bg)
                                    setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.color_instagram ,
                                        0 ,
                                        R.drawable.cancel_light_grey ,
                                        0
                                    )
                                }
                                profileViewModel.savePreference(
                                    PreferenceKeys.PREF_INSTA_NAME ,
                                    instagramUserName
                                )
                            } else {
                                txtInsta.apply {
                                    setText("")
                                    setBackgroundResource(R.drawable.grey_border_bg)
                                    setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.instagram_gray ,
                                        0 ,
                                        R.drawable.right_arrow ,
                                        0
                                    )
                                }
                            }
                            if (isFacebookConnected) {
                                fb_name = result.facebook_name ?: ""
                                txtFb.apply {
                                    setText(fb_name)
                                    setBackgroundResource(R.drawable.corner_blue_border_bg)
                                    setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.color_facebook ,
                                        0 ,
                                        R.drawable.cancel_light_grey ,
                                        0
                                    )
                                }
                            } else {
                                txtFb.apply {
                                    setText("")
                                    setBackgroundResource(R.drawable.grey_border_bg)
                                    setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.facebook_gray ,
                                        0 ,
                                        R.drawable.right_arrow ,
                                        0
                                    )
                                }
                            }
                            if (isLinkedInConnected) {
                                linkedInName = result.linkedin_name ?: ""
                                txtLinkedin.apply {
                                    setText(linkedInName)
                                    setBackgroundResource(R.drawable.corner_blue_border_bg)
                                    setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.linkedin_blue ,
                                        0 ,
                                        R.drawable.cancel_light_grey ,
                                        0
                                    )
                                }
                            } else {
                                txtLinkedin.apply {
                                    setText("")
                                    setBackgroundResource(R.drawable.grey_border_bg)
                                    setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.linkedin_gray ,
                                        0 ,
                                        R.drawable.right_arrow ,
                                        0
                                    )
                                }
                            }
                            if (isGoogleConnected) {
                                googleName = result.google_name ?: ""
                                txtGoogle.apply {
                                    setText(googleName)
                                    setBackgroundResource(R.drawable.corner_blue_border_bg)
                                    setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.google_account ,
                                        0 ,
                                        R.drawable.cancel_light_grey ,
                                        0
                                    )
                                }
                            }
                            else {
                                /*txtGoogle.apply {
                                    setText("")
                                    setBackgroundResource(R.drawable.grey_border_bg)
                                    setCompoundDrawablesWithIntrinsicBounds(
                                        R.drawable.google_gray ,
                                        0 ,
                                        R.drawable.right_arrow ,
                                        0
                                    )
                                }*/
                            }
                            recoveryEmail = result.recovery_email ?: ""
                            if (recoveryEmail.isNotEmpty()) {
                                txtEmail.apply {
                                    setText(recoveryEmail)
                                    setBackgroundResource(R.drawable.corner_blue_border_bg)
                                    setCompoundDrawablesWithIntrinsicBounds(
                                        0 ,
                                        0 ,
                                        R.drawable.cancel_light_grey ,
                                        0
                                    )
                                }
                            } else {
                                /*txtEmail.apply {
                                    setText("")
                                    setBackgroundResource(R.drawable.grey_border_bg)
                                    setCompoundDrawablesWithIntrinsicBounds(
                                        0 ,
                                        0 ,
                                        R.drawable.right_arrow ,
                                        0
                                    )
                                }*/
                            }
                            rlDating.visibility = if (result.is_dater == true) {
                                View.VISIBLE
                            } else {
                                View.GONE
                            }
                            viewDater.visibility = if (result.is_dater == true) {
                                View.VISIBLE
                            } else {
                                View.GONE
                            }
                            rlMatchMaking.visibility = if (result.is_connector == true) {
                                View.VISIBLE
                            } else {
                                View.GONE
                            }
                            viewConnector.visibility = if (result.is_connector == true) {
                                View.VISIBLE
                            } else {
                                View.GONE
                            }
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_DATER_DISABLED ,
                                result.isDaterDisabled!!
                            )

                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_CONNECTOR_DISABLED ,
                                result.isConnectorDisabled!!
                            )
                            toggleDating.isChecked = result.isDaterDisabled
                            toggleMatchMaking.isChecked = result.isConnectorDisabled
                            toggleConnection.isChecked = result.isNewconnectionNotify ?: false
                            toggleFwds.isChecked = result.isNewfwdNotify ?: false
                            toggleMatches.isChecked = result.isNewmatchmakerNotify ?: false
                            toggleMessages.isChecked = result.isNewmessageNotify ?: false
                            /*toggleDating.setCustomChecked(
                                result.isDaterDisabled,
                                this@AccountActivity
                            )
                            toggleMatchMaking.setCustomChecked(
                                result.isConnectorDisabled,
                                this@AccountActivity
                            )
                            toggleConnection.setCustomChecked(
                                result.isNewconnectionNotify!!,
                                this@AccountActivity
                            )
                            toggleFwds.setCustomChecked(
                                result.isNewfwdNotify!!,
                                this@AccountActivity
                            )
                            toggleMatches.setCustomChecked(
                                result.isNewmatchmakerNotify!!,
                                this@AccountActivity
                            )
                            toggleMessages.setCustomChecked(
                                result.isNewmessageNotify!!,
                                this@AccountActivity
                            )*/
                        }
                    }
                }
            }
        }
        profileViewModel.apply {
            showLoading.observe(mActivity) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            instaProfile.observe(mActivity) { profile ->
                Log.e("TAG" , "INSTA PROFILE  === $profile")
                if (profile.accountType != "PERSONAL") {
                    showSnackBar(
                        binding.layoutMain ,
                        getString(R.string.insta_authentication) ,
                    )
                } else {
                    socialFlag = 2
                    isInstagramConnected = true
                    instagramId = profile.id ?: ""
                    instagramUserName = profile.username ?: ""
                    userSettings("is_instagram_connect")
                }
            }
            instaImages.observe(mActivity) { mediaList ->
                instaImagesList.clear()
                instaImagesList.addAll(mediaList)
                Log.e("TAG" , "MEDIA URL === ${mediaList.size}")
                uploadInstagramImages()
            }

            error.observe(mActivity) {
                Log.e("TAG" , "ERROR=== $it")
                if (it.code == 400 && it.error_type == "OAuthException") {
                    //if error from instagram connection then show failure dialog
                    openFailDialog()
                } else {
                    mActivity.showSnackBar(binding.layoutMain , it.message.toString())
                }
            }
            resultProfile.observe(this@AccountActivity) {
                socialFlag = 4
                isLinkedInConnected = true
                linkedInId = it.id
                linkedInName = it.firstName.localized.enUS + " " + it.lastName.localized.enUS
                userSettings("is_linkedin_connect")
            }
            data.observe(mActivity) { responseModel ->
                if (responseModel.response == "success") {
                    mActivity.binding.apply {
                        txtEmail.apply {
                            setText("")
                            setBackgroundResource(R.drawable.grey_border_bg)
                            setCompoundDrawablesWithIntrinsicBounds(
                                0 ,
                                0 ,
                                R.drawable.right_arrow ,
                                0
                            )
                        }
                        profileViewModel.removePreference(PreferenceKeys.PREF_RECOVERY_EMAIL)
                    }
                }
            }
        }
    }

    private fun openFailDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        val fbBinding = DialogSocialLoginBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(fbBinding.root)
            fbBinding.apply {
                view.visibility = View.GONE
                btnCancel.visibility = View.GONE
                btnContinue.visibility = View.GONE
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

    private fun uploadInstagramImages() {
        val jsonArray = JsonArray()
        instaImagesList.forEach {
            jsonArray.add(it)
        }
        val apiRequest = JsonObject().apply {
            add("instagram", jsonArray)
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){uploadInstagramImages()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    uploadInstagramImages()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        profileViewModel.uploadInstaImages(
                            "Bearer $authToken", apiRequest
                        )
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    private fun recoveryEmailId() {
        val apiRequest = JsonObject().apply {
            addProperty("email", "")
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){recoveryEmailId()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    recoveryEmailId()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        profileViewModel.recoverEmail(
                            "Bearer $authToken", apiRequest
                        )
                    }
                }
            }
        }
    }

    private fun disableDialog(isChecked: Boolean) {
        val dialog = Dialog(this, R.style.NoConnectorDialogTheme)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            val binding =
                DialogNoConnectorBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setOnCancelListener {
                dismiss()
            }
            binding.apply {
                if (lastKey == getString(R.string.user_dater)) {
                    if (isChecked) {
                        txtMessage.text =
                            "Your Dater Account has\nbeen disabled\n\nReactive your account\nby switching the toggle\nbutton"
                    } else {
                        txtMessage.text = "Your Dater Account has\nbeen reactivated"
                    }
                } else {
                    if (isChecked) {
                        txtMessage.text =
                            "Your Connector Account\nhas been disabled\n\nReactive your account\nby switching the toggle\nbutton"
                    } else {
                        txtMessage.text = "Your Connector Account has\nbeen reactivated"
                    }
                }
            }
            try {
                show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        /*
        val dialog = Dialog(this, R.style.NoConnectorDialogTheme)
        val fbBinding = DialogDisableBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            setContentView(fbBinding.root)
            setOnCancelListener {
                dismiss()
            }
            if (lastKey == getString(R.string.user_dater)) {
                if (isChecked) {
                    fbBinding.txtMessage.text =
                        "Your Dater Account has been disabled \n\n Reactive your account by switching the toggle button"
                } else {
                    fbBinding.txtMessage.text = "Your Dater Account has been reactivated"
                }
            } else {
                if (isChecked) {
                    fbBinding.txtMessage.text =
                        "Your Connector Account has been disabled \n\n Reactive your account by switching the toggle button"
                } else {
                    fbBinding.txtMessage.text = "Your Connector Account has been reactivated"
                }
            }
            show()
        }*/
    }
}