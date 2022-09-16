package com.swipefwd.ui.splash

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.gson.Gson
import com.swipefwd.Injection
import com.swipefwd.R
import com.swipefwd.base.AppBaseViewModel
import com.swipefwd.base.BaseActivity
import com.swipefwd.base.ResultState
import com.swipefwd.base.getResolvedString
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.SplashActivityBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.auth.login.LoginActivity
import com.swipefwd.ui.auth.phone.PhoneNumberActivity
import com.swipefwd.ui.auth.register.UserInfoActivity
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.home.settings.AccountActivity
import com.swipefwd.ui.home.settings.UpdateAppActivity
import com.swipefwd.ui.profile.AdvancePreferenceActivity
import com.swipefwd.ui.profile.PreferencesActivity
import com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel
import com.swipefwd.ui.updateuserprofile.UserProfileActivity
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.ui.video.VideoTutorial
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.changeStatusBarColor
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.json.JSONObject

/** this is splash activity opened on the launch of the app **/
class SplashActivity : BaseActivity<SplashActivityBinding>() {
    companion object {
        private const val SPLASH_SCREEN_DELAY = 8000L
        private const val TAG = "SplashActivity"
    }

    var deviceID: String = ""

    var isNewUserFromEmail = false
    var isUserInactive: Boolean? = null
    var isUnSubscribeFlow = false
    private var startForResult: ActivityResultLauncher<Intent>? = null
    private val splashViewModel: SplashViewModel by viewModels {
        viewModelFactory {
            SplashViewModel(
                this,
                Injection.getAppRepository(),
                Injection.getInternalAppDataStore(applicationContext),
                packageManager,
                packageName
            )
        }
    }
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(applicationContext, AppRepository()) }
    }

    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(applicationContext, AppRepository()) }
    }

    var accountType = ""

    override fun onCreateViewBinding() = SplashActivityBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Splash)
        lifecycleScope.launch {
            accountType = swipeViewModel.getAccountType.firstOrNull() ?: ""
        }

        deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        println("deviceID-------<<<<-------${deviceID}")

        AppUtils.storeDeviceId(this, deviceID)


        changeStatusBarColor()

        //    var appSignature = AppSignatureHelper(this)
        //     Log.e("printHash",appSignature.appSignatures.toString())
        lifecycleScope.launch {
            when {
                !AppUtils.isNetworkAvailable(this@SplashActivity) -> {

                }
                else -> {
                    splashViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        if (authToken.isNotEmpty())
                            splashViewModel.inactivityStatus("Bearer $authToken")
                    }
                }
            }
        }

        splashViewModel.apply {
            inactivityResponseData().observe(this@SplashActivity) { responseModel ->
                if (responseModel.response == "success") {
                    isUserInactive = responseModel.is_user_active
                    //   showSnackBar(binding.layoutMain,Gson().toJson(responseModel))
                }
            }
        }

    }


    override fun onResume() {
        super.onResume()
       // getDeepLinkData()

    }
    override fun onStart() {
        super.onStart()

        println("onStart ========================")

        getDeepLinkData() //nirali


        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    redirectScreen()
                }
            }
        ////////////////////////////////////////////////////////////////////////////////////////////
        //observer
        splashViewModel.isUpdateRequiredResult().observe(this, { onUpdateRequiredResult(it) })

        ////////////////////////////////////////////////////////////////////////////////////////////
        //after delay/// check for update
        Handler(Looper.getMainLooper()).postDelayed({ checkForUpdates() }, SPLASH_SCREEN_DELAY)
    }


    private fun getDeepLinkData() {
//        val uri: Uri? = intent.data
//        try {
//            if (uri != null) {
//                val parameters: List<String> = uri.pathSegments
//                var recoveryEmail: String? = ""
//                if(parameters.isNotEmpty()) {
//                    recoveryEmail = parameters[parameters.size - 1]
//                }
//                splashViewModel.setRecoverEmailId(recoveryEmail)
//            }
//        }
//        catch(e: Exception) {
//        }
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener(this) {
                println("addOnSuccessListener sucess -->>>>>>>")

                if (it != null) {
                    println("addOnSuccessListener-->>>>>>>${it.link}")


                    if (it.link.toString().contains("unSubscribe")) {
                        isUnSubscribeFlow = true
                    }
                    else {
                        val recoveryEmail: String =
                            it.link?.getQueryParameter("email").orEmpty()
                        println("recoveryEmail-->>>>>>>${recoveryEmail}")
                        println("recoveryEmail link-->>>>>>>${it.link}")
                        if (recoveryEmail != "") {
                            lifecycleScope.launch {

                                AppBaseViewModel().removePreference()
                                AppBaseViewModel().clearDatabase()

                                splashViewModel.setRecoverEmailId(recoveryEmail)

                            }
                        }
                        if (recoveryEmail == "") {
                            val pendingData = Gson().toJson(it)
                            if (pendingData != null) {
                                if ((JSONObject(pendingData.toString())).getJSONObject("dynamicLinkData")
                                        .getString("dynamicLink")
                                        .toString() == "https://swipefwdapp.page.link/newaccount"
                                ) {
//                            Log.e("pending data" , (JSONObject(pendingData.toString())).getJSONObject("dynamicLinkData").getString("dynamicLink").toString())
                                    lifecycleScope.launch {
                                        AppBaseViewModel().removePreference()
                                        AppBaseViewModel().clearDatabase()
//                                    logoutUser()
                                    }
                                    isNewUserFromEmail = true

                                    println("isNewUserFromEmail-->>>>>>>${isNewUserFromEmail}")

                                }
                            }
                        }
                    }
                }
            }.addOnFailureListener {
                println("addOnFailureListener ------->>>${it.message}")
                it.printStackTrace()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun onUpdateRequiredResult(result: ResultState<Boolean>) {
        when (result) {
            is ResultState.Success -> onUpdateRequiredStatus(result.data)
            is ResultState.Error -> onUpdateCheckError(result.getResolvedString(this))
            is ResultState.Loading -> {
            }
        }
    }

    private fun onUpdateRequiredStatus(status: Boolean) {
        if (status) {
            startForResult?.launch(
                Intent(
                    this@SplashActivity,
                    UpdateAppActivity::class.java
                )
            )
        } else {
            redirectScreen()
        }
    }

    private fun onUpdateCheckError(error: String?) {
        AppUtils.showMessageOK(
            this,
            getString(R.string.app_name),
            getString(R.string.common_retry),
            error ?: getString(R.string.error_something_went_wrong)
        ) { _, _ ->
            checkForUpdates()
        }
    }

    private fun checkForUpdates() {
        if (!AppUtils.isNetworkAvailable(this)) {
            openFailNetworkDialog() { checkForUpdates() }
//            AppUtils.showMessageOK(
//                this,
//                getString(R.string.app_name),
//                getString(R.string.common_retry),
//                getString(R.string.no_internet)
//            ) { _, _ ->
//                checkForUpdates()
//            }
        } else {
            splashViewModel.checkForUpdates()
        }
    }


    private fun redirectScreen() {
        lifecycleScope.launch {
//            swipeViewModel.savePreference(PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED, splashViewModel.isOnBoardTutorialCompleted())
            if (isUserInactive != null && !isUserInactive!!) {
                println("LoginActivity from isUserInactive------------------------")

                AppUtils.callLogout(splashViewModel)
                nextActivity(LoginActivity::class.java)
                finish()
            } else {
                val loginFlag = splashViewModel.getLoginFlag.firstOrNull() ?: false

                if (loginFlag && isUnSubscribeFlow) {
                    nextActivity(AccountActivity::class.java)
                    finish()
                }

                else if (splashViewModel.isForRecoverAccount())
                {
                    println("isForRecoverAccount 2222222222------------>>>>>>>>>>>")
                    val intent =
                        Intent(this@SplashActivity, PhoneNumberActivity::class.java)
                    intent.putExtra(
                        PhoneNumberActivity.EXTRA_KEY_RECOVER_ACCOUNT_EMAIL,
                        splashViewModel.getRecoveryEmail()
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)
                    finishAffinity()
                    applyScreenTransitions()
                }

                else {


                    when (splashViewModel.getCurrentScreen.firstOrNull() ?: "0") {
                        "1" -> {
                            nextActivity(UserInfoActivity::class.java)
                            finish()
                        }
                        "2" -> {
                            nextActivity(UserProfileActivity::class.java)
                            finish()
                        }
                        "3" -> {
                            nextActivity(PreferencesActivity::class.java)
                            finish()
                        }
                        "4" -> {
                            nextActivity(AdvancePreferenceActivity::class.java)
                            finish()
                        }
//                "5" -> {
//                    nextActivity(EmailActivity::class.java)
//                    finish()
//                }
//                "6" -> {
//                    nextActivity(AgreementActivity::class.java)
//                    finish()
//                }
                        "7" -> {
                            nextActivity(VideoTutorial::class.java)
                            finish()
                        }
                        "8" -> {

                            nextActivity(TabManagerActivity::class.java)
                            finish() //commented by nirali
                        }
                        "9" ->
                        {
                            println("LoginActivity from 9------------------------")

                            nextActivity(LoginActivity::class.java)
                            finish()
                        }
                        else -> {
                            when {
                                isNewUserFromEmail -> {
                                    println("isNewUserFromEmail------------------------")
                                   val intent =
                                        Intent(this@SplashActivity, LoginActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                                    startActivity(intent)
                                    finishAffinity()
                                    applyScreenTransitions()


                                }
                              /*  splashViewModel.isForRecoverAccount() -> {
                                    println("isForRecoverAccount 2222222222------------>>>>>>>>>>>")
                                    val intent =
                                        Intent(this@SplashActivity, PhoneNumberActivity::class.java)
                                    intent.putExtra(
                                        PhoneNumberActivity.EXTRA_KEY_RECOVER_ACCOUNT_EMAIL,
                                        splashViewModel.getRecoveryEmail()
                                    )
                                    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                                    startActivity(intent)
                                    finishAffinity()
                                    applyScreenTransitions()
                                }*/ //nirali
                                loginFlag -> {

                                    lifecycleScope.launch {
//                                if (splashViewModel.isOnBoardTutorialCompleted() || profileViewModel.isOnBoardTutorialCompleted()) {
//                                    when (accountType) {
//                                        AppUtils.AccountTypes.Dater -> {
//                                            nextActivity(LoadingActivity::class.java)
//                                        }
//                                        else -> {
//                                            nextActivity(TabManagerActivity::class.java)
//                                        }
//                                    }
                                        nextActivity(LoadingActivity::class.java)
                                        finish()
//                                } else {
//                                    nextActivity(OnBoardingActivity::class.java)
//                                    nextActivity(VideoTutorial::class.java)
//                                    finish()
//                                }
                                    }
                                }
                                else -> {
                                    println("isForRecoverAccount elsssssssseeeeeee------------>>>>>>>>>>>")

                                    nextActivity(LoginActivity::class.java)
                                    finish()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //    private fun afterDeepLinkDataReceived(){
//        startForResult =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//                if (result.resultCode == Activity.RESULT_OK) {
//                    redirectScreen()
//                }
//            }
//        ////////////////////////////////////////////////////////////////////////////////////////////
//        //observer
//        splashViewModel.isUpdateRequiredResult().observe(this, { onUpdateRequiredResult(it) })
//
//        ////////////////////////////////////////////////////////////////////////////////////////////
//        //after delay/// check for update
//        Handler(Looper.getMainLooper()).postDelayed({ checkForUpdates() }, SPLASH_SCREEN_DELAY)
//    }
    private fun Activity.nextActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
        overridePendingTransition(R.anim.enter_anim_splash, R.anim.leave_anim_splash)
    }

    private fun applyScreenTransitions() {
        overridePendingTransition(R.anim.enter_anim_splash, R.anim.leave_anim_splash)
    }
}