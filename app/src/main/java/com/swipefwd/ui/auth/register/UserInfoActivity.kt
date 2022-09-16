package com.swipefwd.ui.auth.register

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.swipefwd.R
import com.swipefwd.data.models.SocialMediaUserModel
import com.swipefwd.data.models.UserImagesResponseModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityUserInfoActivityBinding
import com.swipefwd.databinding.DialogSocialLoginBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.video.VideoTutorial
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.dpToPx
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class UserInfoActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_KEY_OTP = "extra.key.otp"
    }

    private lateinit var binding: ActivityUserInfoActivityBinding
    private val infoViewModel: UserInfoViewModel by viewModels {
        viewModelFactory { UserInfoViewModel(this, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(this)
    }
    private var facebookIds = ArrayList<String>()

    // user_type -> 1:dater, 2:Matchmaker, 3:Both
    private var userType = 3
    private var accountType = AppUtils.AccountTypes.Dater
    private var phoneNumber = ""
    private var countryCode: String? = ""
    private var isConnection = 0 //1 == connector && 0 == dater
    private var socialUser = SocialMediaUserModel()
    private lateinit var snackBar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoActivityBinding.inflate(layoutInflater)
        infoViewModel.otpCode = intent.getStringExtra(EXTRA_KEY_OTP)
        setContentView(binding.root)

        var userAccountType = AppUtils.getUserType(this)
        if (userAccountType == 1) {
            isConnection = 0
        } else if (userAccountType == 2) {
            isConnection = 1
        }

        init()
        initObserver()
    }

    private fun initObserver() {
        infoViewModel.apply {
            showLoading.observe(this@UserInfoActivity) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }

            errorMessage.observe(this@UserInfoActivity) {
                Log.e("TAG", "ERROR=== $it")
                showSnackBar(it)
            }

            registerData.observe(this@UserInfoActivity) { loginModel ->
                if (socialUser.socialType!!.isNotEmpty()) {
                    userSettings(loginModel.user_id, loginModel.token)
                    infoViewModel.saveUserInfo(
                        PreferenceKeys.PREF_FIRST_NAME,
                        socialUser.firstName.toString()
                    )
                    infoViewModel.saveUserInfo(
                        PreferenceKeys.PREF_LAST_NAME,
                        socialUser.lastName.toString()
                    )
                    infoViewModel.saveUserInfo(
                        PreferenceKeys.PREF_PROFILE_IMAGE,
                        socialUser.profilePicture.toString()
                    )
                }
                infoViewModel.saveUserInfo(PreferenceKeys.PREF_IS_NEW_USER, true)
                infoViewModel.saveUserInfo(PreferenceKeys.PREF_TOKEN, loginModel.token)
                infoViewModel.saveUserInfo(PreferenceKeys.PREF_USER_ID, loginModel.user_id)
                infoViewModel.saveUserInfo(PreferenceKeys.PREF_PROFILE_FLAG, false)
                infoViewModel.saveUserInfo(PreferenceKeys.PREF_PREFERENCE_FLAG, false)
                infoViewModel.saveUserInfo(PreferenceKeys.PREF_ACCOUNT_TYPE, accountType)
                infoViewModel.saveUserInfo(
                    PreferenceKeys.PREF_PHONE_NUMBER,
                    countryCode + phoneNumber
                )
//                infoViewModel.saveUserInfo(PreferenceKeys.PREF_COUNTRY_CODE, countryCode)
                infoViewModel.saveUserInfo(
                    PreferenceKeys.PREF_INVITE_EXPIRED,
                    loginModel.invitation_expired
                )
                if (accountType == AppUtils.AccountTypes.Matchmaker && loginModel.gender != null) {
                    infoViewModel.saveUserInfo(
                        PreferenceKeys.INVITER_GENDER,
                        loginModel.gender.toString()
                    )
                }
                if (socialUser.socialType == AppConstants.SOCIAL_FACEBOOK) {
                    //we are only getting dob & gender from facebook account
                    val gender = when (socialUser.gender) {
                        AppConstants.FB_MALE -> "0"
                        AppConstants.FB_FEMALE -> "1"
                        else -> "2"
                    }
                    infoViewModel.saveUserInfo(PreferenceKeys.PREF_GENDER, gender)
                    try {
                        if (socialUser.dob != null && socialUser.dob!!.isNotEmpty()) {
                            val date = socialUser.dob?.split("/")?.get(1)
                            val month = socialUser.dob?.split("/")?.get(0)
                            val year = socialUser.dob?.split("/")?.get(2)
                            val convertedDob = "$year-$month-$date"
                            infoViewModel.saveUserInfo(
                                PreferenceKeys.PREF_CONVERTED_DOB,
                                convertedDob
                            ) //"$mYear-$mMonth-$mDay"
                        }
                    } catch (e: Exception) {

                    }


                    infoViewModel.saveUserInfo(
                        PreferenceKeys.PREF_DOB,
                        socialUser.dob!!
                    ) //09/18/1984

                    this@UserInfoActivity.sendFacebookIds(loginModel.token)
                }
                Handler(Looper.getMainLooper()).postDelayed({
//                    startActivity(
//                        Intent(this@UserInfoActivity , UserProfileActivity::class.java)
//                    )
                    startActivity(
                        Intent(this@UserInfoActivity, VideoTutorial::class.java)
                    )
                    finish()
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                }, 1000)
            }


//            registerError.observe(this@UserInfoActivity, {
//                Log.e("TAG", "ERROR=== $it")
//                //showSnackBar(it.message)
//                /*val accountType = when (it.data.userType) {
//                    "1" -> CM.AccountTypes.Dater
//                    "2" -> CM.AccountTypes.Matchmaker
//                    else -> CM.AccountTypes.Dater
//                }*/
//                val accountType = when (isConnection) {
//                    1 -> {
//                        AppUtils.AccountTypes.Matchmaker
//                    }
//                    else -> {
//                        AppUtils.AccountTypes.Dater
//                    }
//                }
//                saveUserInfo(PreferenceKeys.PREF_ACCOUNT_TYPE, accountType)
//                saveUserInfo(PreferenceKeys.PREF_ADV_PROFILE_FLAG, it.advanceprofile)
//                saveUserInfo(PreferenceKeys.PREF_PREFERENCE_FLAG, it.preference)
//                saveUserInfo(PreferenceKeys.PREF_PROFILE_FLAG, it.profile)
//                Handler(Looper.getMainLooper()).postDelayed({
//                    when {
//                        !it.profile -> {
//                            saveUserInfo(PreferenceKeys.PREF_LOGIN_FLAG, false)
//                            nextActivity(UserProfileActivity::class.java)
//                        }
//                        !it.preference -> {
//                            saveUserInfo(PreferenceKeys.PREF_LOGIN_FLAG, false)
//                            nextActivity(PreferencesActivity::class.java)
//                        }
//                        else -> {
//                            saveUserInfo(PreferenceKeys.PREF_LOGIN_FLAG, true)
//                            saveUserInfo(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
//                            if (it.data.is_agree) {
//                                nextActivity(TabManagerActivity::class.java)
//                            } else {
//                                nextActivity(AgreementActivity::class.java)
//                            }
//                            finish()
//                            finishAffinity()
//                        }
//                    }
//                    //finish()
//                }, 1000)
//            })


        }
    }

    private fun userSettings(userId: Int, token: String) {
        val jsonObject = JsonObject().apply {
            when (socialUser.socialType) {
                AppConstants.SOCIAL_GOOGLE -> {
                    addProperty("is_google_connect", true)
                    addProperty("google_name", socialUser.emailId)
                    //addProperty("googleid", socialUser.socialId)
                }
                AppConstants.SOCIAL_LINKEDIN -> {
                    addProperty("is_linkedin_connect", true)
                    addProperty("linkedin_name", socialUser.firstName + " " + socialUser.lastName)
                    //addProperty("linkedinid", socialUser.socialId)
                }
                AppConstants.SOCIAL_FACEBOOK -> {
                    addProperty("is_facebook_connect", true)
                    addProperty("facebook_name", socialUser.firstName + " " + socialUser.lastName)
                    //addProperty("facebookid", socialUser.socialId)
                }
            }
        }
        infoViewModel.userSettingsUpdate(
            "Bearer $token", userId, jsonObject
        )
    }

    private fun registerUser() {
        val apiRequest = JsonObject().apply {
            addProperty("user_type", userType)
            addProperty("social_type", socialUser.socialType)
            addProperty("social_id", socialUser.socialId)
            addProperty("country_code", countryCode)
            addProperty("phone_number", phoneNumber)
//            addProperty("code", infoViewModel.otpCode)
        }
        Log.d("API_REQUEST", apiRequest.toString())
        infoViewModel.registerUser(apiRequest)
    }

    private fun init() {
        infoViewModel.saveUserInfo(PreferenceKeys.PREF_CURRENT_SCREEN, "1")
        lifecycleScope.launch {
            infoViewModel.saveUserInfo(
                PreferenceKeys.PREF_REMAINING_CONNECTOR_CONNECTION,
                10
            )
            infoViewModel.saveUserInfo(
                PreferenceKeys.PREF_REMAINING_INVITATION,
                10
            )
            phoneNumber = if (intent.getStringExtra("phoneNumber") == null) {
                infoViewModel.getPhoneNumber.firstOrNull() ?: ""
            } else {
                intent.getStringExtra("phoneNumber")!!
            }
            val fbIds = infoViewModel.getFbIds.firstOrNull() ?: ""
            if (fbIds.isNotEmpty()) {
                val type: Type =
                    object : TypeToken<List<String?>?>() {}.type
                facebookIds =
                    Gson().fromJson(
                        fbIds,
                        type
                    ) as ArrayList<String>
            }
            val socialMediaUser = infoViewModel.socialMediaUser.firstOrNull() ?: ""
            if (socialMediaUser.isNotEmpty()) {
                socialUser = Gson().fromJson(socialMediaUser, SocialMediaUserModel::class.java)
                val list = ArrayList<UserImagesResponseModel.Item>()
                list.add(
                    UserImagesResponseModel.Item(
                        isProfile = true,
                        imageUrl = socialUser.profilePicture
                    )
                )
                infoViewModel.saveUserInfo(
                    PreferenceKeys.PREF_IMAGES,
                    Gson().toJson(list)
                )
            }
            if (infoViewModel.showNotificationDialog.firstOrNull()!!) {
                openNotificationDialog()
            }
        }
//        countryCode = intent.getStringExtra("countryCode") ?: ""
        //temp  isConnection = intent.getIntExtra("isConnection", 0)

        lifecycleScope.launch {
            countryCode = infoViewModel.getCountryCode.firstOrNull() ?: ""
        }

        infoViewModel.userTypeDataError.observe(this)
        {
            showSnackBar(it.message)
        }


        binding.apply {
            txtContinue.setOnClickListener {
//                registerUser()
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                openNotificationDialog()

            }
            when (isConnection) {
                1 -> {
                    //for connector
                    userType = 2
                    accountType = AppUtils.AccountTypes.Matchmaker
                    binding.rbFriends.changeViewOnSelection(1)
                }
                else -> {
                    //for Dater
                    userType = 1
                    accountType = AppUtils.AccountTypes.Dater
                    binding.rbMe.changeViewOnSelection(0)
                }
            }
            layoutMe.setOnClickListener {
                if (::snackBar.isInitialized) {
                    snackBar.dismiss()
                }
                infoViewModel.setUserAccountType(1)
                when (isConnection) {
                    0 -> {
                        rbMe.changeViewOnSelection(0)
                        viewFriends.visibility = View.GONE
                        txtUserFriends.visibility = View.GONE
                        layoutFriends.apply {
                            val layoutParams = this.layoutParams as RelativeLayout.LayoutParams
                            layoutParams.bottomMargin = 62.dpToPx()
                            this.layoutParams = layoutParams
                        }
                        layoutFriends.apply {
                            background =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.corner_white_bg,
                                    theme
                                )
                            alpha = 1f
                            isClickable = true
                        }
                        rbFriends.apply {
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.radio, 0)
                            setTextColor(
                                ResourcesCompat.getColor(
                                    resources,
                                    R.color.colorPagerDesc,
                                    theme
                                )
                            )
                            background =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.corner_white_bg,
                                    theme
                                )
                        }
                        txtContinue.apply {
                            isClickable = true
                            isEnabled = true
                        }
                    }

                }
            }
            layoutFriends.setOnClickListener {
                infoViewModel.setUserAccountType(2)

                when (isConnection) {
                    0 -> {
                        //show toast here
                   //temp     showSnackBar(getString(R.string.connector_selection))
                        viewMe.visibility = View.GONE
                        txtUserMe.visibility = View.GONE
                        layoutFriends.apply {
                            val layoutParams = this.layoutParams as RelativeLayout.LayoutParams
                            layoutParams.bottomMargin = 38.dpToPx()
                            this.layoutParams = layoutParams
                        }
                        layoutMe.apply {
                            background =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.corner_white_bg,
                                    theme
                                )
                        }
                        rbMe.apply {
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.radio, 0)
                            setTextColor(
                                ResourcesCompat.getColor(
                                    resources,
                                    R.color.colorPagerDesc,
                                    theme
                                )
                            )
                            background =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.corner_white_bg,
                                    theme
                                )
                        }
                        txtContinue.apply {
                            isClickable = false
                            isEnabled = false
                        }
                        viewFriends.visibility = View.VISIBLE
                        txtUserFriends.apply {
                            visibility = View.VISIBLE
                            setTextColor(
                                ContextCompat.getColor(
                                    this@UserInfoActivity,
                                    R.color.white
                                )
                            )
                            background =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.corner_button_gradient_bg,
                                    theme
                                )
                        }
                        layoutFriends.apply {
                            background =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.corner_button_gradient_bg,
                                    theme
                                )
                            alpha = 0.4f
                            isClickable = false
                        }
                        rbFriends.apply {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.radio_disable,
                                0
                            )
                            setTextColor(
                                ResourcesCompat.getColor(
                                    resources,
                                    R.color.white,
                                    theme
                                )
                            )
                            background =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.corner_button_gradient_bg,
                                    theme
                                )
                        }
                    }
                    else -> {
                        rbFriends.changeViewOnSelection(1)
                        viewMe.visibility = View.GONE
                        txtUserMe.visibility = View.GONE
                        layoutFriends.apply {
                            val layoutParams = this.layoutParams as RelativeLayout.LayoutParams
                            layoutParams.bottomMargin = 38.dpToPx()
                            this.layoutParams = layoutParams
                        }
                        layoutMe.apply {
                            background =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.corner_white_bg,
                                    theme
                                )
                            alpha = 1f
                            isClickable = true
                        }
                        rbMe.apply {
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.radio, 0)
                            setTextColor(
                                ResourcesCompat.getColor(
                                    resources,
                                    R.color.colorPagerDesc,
                                    theme
                                )
                            )
                            background =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.corner_white_bg,
                                    theme
                                )
                        }
                        txtContinue.apply {
                            isClickable = true
                            isEnabled = true
                        }
                    }
                }
            }
        }
    }

    private fun AppCompatTextView.changeViewOnSelection(selectedIndex: Int) {
        when (selectedIndex) {
            0 -> {
                background =
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.corner_button_gradient_bg,
                        theme
                    )
                setTextColor(ResourcesCompat.getColor(resources, R.color.white, theme))
                setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.radio_enable, 0)

                binding.apply {
                    viewMe.visibility = View.VISIBLE
                    layoutFriends.apply {
                        val layoutParams = this.layoutParams as RelativeLayout.LayoutParams
                        layoutParams.bottomMargin = 62.dpToPx()
                        this.layoutParams = layoutParams
                    }
                    txtUserMe.apply {
                        visibility = View.VISIBLE
                        background =
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.corner_button_gradient_bg,
                                theme
                            )
                    }
                    layoutMe.background =
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.corner_button_gradient_bg,
                            theme
                        )
                }
            }
            1 -> {
                background =
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.corner_button_gradient_bg,
                        theme
                    )
                setTextColor(ResourcesCompat.getColor(resources, R.color.white, theme))
                setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.radio_enable, 0)
                binding.apply {
                    viewFriends.visibility = View.VISIBLE
                    txtUserFriends.apply {
                        visibility = View.VISIBLE
                        background =
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.corner_button_gradient_bg,
                                theme
                            )
                    }
                    layoutFriends.background =
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.corner_button_gradient_bg,
                            theme
                        )
                }
            }
        }
    }

    private fun sendFacebookIds(token: String) {
        val jsonArray = JsonArray()
        facebookIds.forEach {
            jsonArray.add(it)
        }
        val apiRequest = JsonObject().apply {
            add("facebook_ids", jsonArray)
        }
        infoViewModel.sendFacebookIds("Bearer $token", apiRequest)
    }

    private fun showSnackBar(message: String) {
        snackBar = Snackbar.make(binding.layoutMain, message, Snackbar.LENGTH_LONG)
        val layout = snackBar.view as Snackbar.SnackbarLayout
        layout.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                android.R.color.transparent,
                theme
            )
        )
        val customSnackBarView = layoutInflater.inflate(
            R.layout.layout_custom_toast,
            findViewById(R.id.customToastContainer), false
        )
        customSnackBarView.findViewById<AppCompatTextView>(R.id.toastText).text = message
        customSnackBarView.findViewById<AppCompatImageView>(R.id.ivCancel).apply {
            setOnClickListener {
                AppUtils.snackBarCloseAnimation(this.context, snackBar)
            }
        }
        val params = layout.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        layout.layoutParams = params
        layout.removeAllViews()
        layout.addView(customSnackBarView, 0)
//        snackBar.show()
        AppUtils.snackBarOpenAnimation(this, snackBar)
    }

    override fun onBackPressed() {
    }

    private fun openNotificationDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        val dialogBinding = DialogSocialLoginBinding.inflate(layoutInflater)
        infoViewModel.saveUserInfo(PreferenceKeys.PREF_SHOW_NOTIFICATION_DIALOG, true)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(dialogBinding.root)
            dialogBinding.apply {
                imgDialogGradient.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        this@UserInfoActivity,
                        R.drawable.rectangle
                    )
                )
                txtTitle.text = getString(R.string.send_notifications)
                txtContent.text = getString(R.string.send_notifications_body)
                txtTitle.setTextColor(getResources().getColor(R.color.black))
                txtContent.setTextColor(getResources().getColor(R.color.black))
                view.visibility = View.VISIBLE
                btnContinue.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.common_ok)
                    setOnClickListener {
                        infoViewModel.saveUserInfo(
                            PreferenceKeys.PREF_SHOW_NOTIFICATION_DIALOG,
                            false
                        )
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                            nextActivity(VideoTutorial::class.java)
                            //temp registerUser()
                        }
                    }
                }
                btnOk.apply {
                    visibility = View.GONE
                }
                viewTop.visibility = View.VISIBLE
                btnCancel.apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.do_not_allow)
                    setOnClickListener {
                        infoViewModel.saveUserInfo(
                            PreferenceKeys.PREF_SHOW_NOTIFICATION_DIALOG,
                            false
                        )
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                            nextActivity(VideoTutorial::class.java)

                            //temp  registerUser()
                        }
                    }
                }
            }
            setZoomDialogWindowAttributes()
            show()
            dialogBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    private fun Activity.nextActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
        finish()
        overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
    }

}