package com.swipefwd.ui.profile

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.gson.JsonObject
import com.swipefwd.R
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityAccountRecoveryBinding
import com.swipefwd.ui.home.AgreementActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.changeStatusBarColor
import com.swipefwd.utils.AppUtils.dpToPx
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.showSnackBarMargin
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AccountRecoveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountRecoveryBinding
    private val mActivity by lazy {
        this
    }
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var emailId = ""
    private var isFromSettings = false
    var isKeyboardOpened = false
    private var mCustomSnackbar: Snackbar? = null
    private var sharingLink: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountRecoveryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isFromSettings = intent.hasExtra("EditProfile")
        init()
        initObservers()
    }

    private fun init() {
        changeStatusBarColor()
        binding.apply {
            Log.d("TAG:", "isFromSettings: $isFromSettings")
            //imgNext.isEnabled = false
           // imgNext1.isEnabled = false
            imgBack.setOnClickListener {
                onBackPressed()
            }
            edtEmail.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    binding.imgNext.performClick()
                    true
                }
                false
            }
//            txtSkip.setOnClickListener {
//                if (isFromSettings) {
//                    imgBack.performClick()
//                } else {
//                    profileViewModel.removePreference(PreferenceKeys.PREF_RECOVERY_EMAIL)
//                    profileViewModel.savePreference(PreferenceKeys.PREF_LOGIN_FLAG, true)
//                    profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
//                    lifecycleScope.launch {
//                        if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                            nextActivity(AgreementActivity::class.java)
//                            finish()
//                        } else {
//                            nextActivity(TabManagerActivity::class.java)
//                            finish()
//                            finishAffinity()
//                        }
//                    }
//                }
//            }

            binding.edtEmail.viewTreeObserver.addOnGlobalLayoutListener {
                if (binding.edtEmail.hasFocus()) {
                    val r = Rect()
                    binding.edtEmail.getWindowVisibleDisplayFrame(r)
                    val screenHeight = binding.edtEmail.rootView.height
                    val keypadHeight = screenHeight - r.bottom
                    if (keypadHeight > screenHeight * 0.15) {
                        if (!isKeyboardOpened) {
                            Log.e("print","visible")
                            isKeyboardOpened = true
                            binding.imgNext.visibility = View.GONE
                            binding.imgNext1.visibility = View.VISIBLE
                        }
                    } else {
                        if (isKeyboardOpened) {
                            Log.e("print","invisible")
                            isKeyboardOpened = false
                            binding.imgNext1.visibility = View.GONE
                            binding.imgNext.visibility = View.VISIBLE
                        }
                    }
                }
            }
            imgNext.setOnClickListener {
                recoveryAccount()
            }
            imgNext1.setOnClickListener {
                recoveryAccount()
            }
            binding.edtEmail.setOnClickListener {
                if(mCustomSnackbar!=null && mCustomSnackbar?.isShown!!) {
                    AppUtils.snackBarCloseAnimation(mActivity, mCustomSnackbar!!)
                }
            }
            edtEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    emailId = s.toString().trim()
                }

                override fun afterTextChanged(s: Editable?) {
                   /* imgNext.isEnabled = !s.isNullOrBlank()
                    imgNext1.isEnabled = !s.isNullOrBlank()*/



               /*     if (emailId.isValidEmail()) {
                        imgNext.isEnabled = true
                        imgNext1.isEnabled = true
                    } else{
                        imgNext.isEnabled = false
                        imgNext1.isEnabled = false
                    }*/
                }
            })
        }
    }

//    override fun onResume() {
//        super.onResume()
//        Handler(Looper.getMainLooper()).postDelayed({
//            lifecycleScope.launch {
//                emailId = profileViewModel.getRecoveryEmail.firstOrNull() ?: ""
//                binding.edtEmail.setText(emailId)
//            }
//        }, 500)
//    }

    private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    private fun createLinks(email: String) :String {


        FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(createShareUri(email))
            .setDomainUriPrefix("https://swipefwdapp.page.link")
            .setAndroidParameters(DynamicLink.AndroidParameters.Builder("com.swipefwd").build())
            .buildShortDynamicLink()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    sharingLink = it.result!!.shortLink.toString()

                    apiCall(sharingLink)
                    println("sharingLink-------->>>${sharingLink}")
                } else {
                    it.exception?.printStackTrace()
                }
            }

        return sharingLink
    }
    private fun createShareUri(email: String): Uri {
        val builder = Uri.Builder()
        builder.scheme("https")
            .authority("swipefwdapp.page.link")
            .appendPath("recoveraccount")
            .appendQueryParameter("email", email)
        return builder.build()
    }
    private fun recoveryAccount() {
        val inputMethodManager =
            ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
        inputMethodManager.hideSoftInputFromWindow(binding.edtEmail.windowToken, 0)
        isKeyboardOpened = false

        if (emailId.isValidEmail()) {
          val link =   createLinks(binding.edtEmail.text.toString().trim())

            println("sharing link isValidEmail--------->>>>${link}")

           /* val apiRequest = JsonObject().apply {
                addProperty("email", binding.edtEmail.text.toString().trim())
               // addProperty("deeplink", binding.edtEmail.text.toString().trim())
            }
            when {
                !AppUtils.isNetworkAvailable(mActivity) -> {
                    openFailNetworkDialog(){recoveryAccount()}
//                    AppUtils.showMessageOK(
//                        mActivity,
//                        getString(R.string.app_name),
//                        getString(R.string.common_retry),
//                        getString(R.string.no_internet)
//                    ) { _, _ ->
//                        recoveryAccount()
//                    }
                }
                else -> {
                    lifecycleScope.launch {
                        profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            profileViewModel.recoverAccount(
                                "Bearer $authToken", apiRequest
                            )
                        }
                    }
                }
            }*/
        } else {
            mCustomSnackbar =   showSnackBarMargin( v = binding.layoutMain,
                message = getString(R.string.check_email))
        }
    }


    fun apiCall(deepLink :String)
    {
        println("deepLink---------->>>${deepLink}")
        val apiRequest = JsonObject().apply {
            addProperty("email", binding.edtEmail.text.toString().trim())
            addProperty("deeplinkEmail", deepLink)
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){recoveryAccount()}
//                    AppUtils.showMessageOK(
//                        mActivity,
//                        getString(R.string.app_name),
//                        getString(R.string.common_retry),
//                        getString(R.string.no_internet)
//                    ) { _, _ ->
//                        recoveryAccount()
//                    }
            }
            else -> {
                lifecycleScope.launch {
                    profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        profileViewModel.recoverAccount(
                            "Bearer $authToken", apiRequest
                        )
                    }
                }
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right)
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
                mCustomSnackbar =     showSnackBarMargin( v = binding.layoutMain,
                    message = it)
            }
            data.observe(mActivity) { responseModel ->
//                if (responseModel.response == "success") {
                if (responseModel.code == 1 && responseModel.message =="Success") {
                    profileViewModel.savePreference(PreferenceKeys.PREF_RECOVERY_EMAIL, emailId)
                    var intent =
                        Intent(this@AccountRecoveryActivity, CheckEmailActivity::class.java)
                    intent.putExtra("email", binding.edtEmail.text.toString().trim())
                    startActivity(intent)
                    finish()
//                    if (isFromSettings) {
//                        binding.imgBack.performClick()
//                    } else {
//                        profileViewModel.savePreference(PreferenceKeys.PREF_LOGIN_FLAG, true)
//                        profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
//                        lifecycleScope.launch {
//                            if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                                nextActivity(AgreementActivity::class.java)
//                                finish()
//                            } else {
//                                nextActivity(TabManagerActivity::class.java)
//                                finish()
//                                finishAffinity()
//                            }
//                        }
//                    }
                }  else if(responseModel.code == 0) {
                    showSnackBarMargin( v = binding.layoutMain,
                        message = responseModel.message.toString(),isFromRecovery = true, listener = object:View.OnClickListener{
                            override fun onClick(v: View?) {
                                onBackPressed()
                            }
                        })
                }
            }
            error.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
                val message = if(it!=null){ it.message.toString() } else{ getString(R.string.error_something_went_wrong) }
                showSnackBarMargin( v = binding.layoutMain,
                    message = message)
            }
        }
    }
}