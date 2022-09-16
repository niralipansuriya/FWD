package com.swipefwd.ui.auth.sociallogin.facebook

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.swipefwd.base.ResultState
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.ProgressBarHandler


/** this activity is deal with the fetching the facebook profile details */
class FacebookLoginActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "FacebookLoginActivity"
        const val EXTRA_KEY_SOCIAL_USER_DETAILS_MODEL = "social_user_details"
        const val EXTRA_RESULT_DATA = "facebook_login_result_data"
    }

    private val facebookLoginViewModel: FacebookLoginViewModel by viewModels {
        viewModelFactory { FacebookLoginViewModel() }
    }

    private lateinit var callbackManager: CallbackManager
    private val progressLoader by lazy { ProgressBarHandler(this) }
    private val requestedPermission =
        listOf("email", "public_profile", "user_friends", "user_birthday" ,"user_gender")
//        listOf("email", "public_profile", "user_friends", "user_birthday" ,"user_gender","user_mobile_phone")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ////////////////////////////////////////////////////////////////////////////////////////
        FacebookSdk.fullyInitialize()/*.sdkInitialize(applicationContext)*/
        AppEventsLogger.activateApp(application)
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS)
        //logout from previous login
//        LoginManager.getInstance().logOut()
        //create callback manager
        callbackManager = CallbackManager.Factory.create()
        //set permission
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback)
        ////////////////////////////////////////////////////////////////////////////////////////
        //set callback

        LoginManager.getInstance().logInWithReadPermissions(this, requestedPermission)
        ////////////////////////////////////////////////////////////////////////////////////////
        //printHashKey(this)
        ////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////
        //observer
        facebookLoginViewModel.userDetails().observe(this) {
            onFacebookUserDetailsResult(it)
        }
    }

    override fun finish() {
        super.finish()
        //off exit transition
        overridePendingTransition(0, 0)
    }

    /* this function is for print hash key */
//    private fun printHashKey(context: Context) {
//        try {
//            val info = context.packageManager.getPackageInfo(
//                context.packageName,
//                PackageManager.GET_SIGNATURES
//            )
//            for (signature in info.signatures) {
//                val message = MessageDigest.getInstance("SHA")
//                message.update(signature.toByteArray());
//                val hashKey = String(Base64.encode(message.digest(), 0))
//                Log.i("printHashKey", "printHashKey() Hash Key: $hashKey")
//            }
//        } catch (e: NoSuchAlgorithmException) {
//            Log.e("printHashKey", "printHashKey()", e)
//        } catch (e: Exception) {
//            Log.e("printHashKey", "printHashKey()", e)
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private val facebookCallback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult) {
            facebookLoginViewModel.setLoginResult(result)
            facebookLoginViewModel.createUserDetails()
        }

        override fun onCancel() {
            onCancelled()
        }

        override fun onError(error: FacebookException) {
            onError()
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    private fun onFacebookUserDetailsResult(result: ResultState<FacebookUserDetails>) {
        when (result) {
            is ResultState.Success -> {
                progressLoader.dismiss()
                sendSuccessResult(result.data)
            }
            is ResultState.Error -> {
                progressLoader.dismiss()
                onError()
            }
            is ResultState.Loading -> progressLoader.show()
            else -> throw RuntimeException("invalid result state")
        }
    }

    private fun sendSuccessResult(userDetails: FacebookUserDetails) {
        val intent = Intent()
        intent.putExtra(
            EXTRA_RESULT_DATA, FacebookLoginResult(
                resultStatus = true,
                profileDetails = userDetails,
                message = "get facebook user details successfully"
            )
        )
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun onCancelled() {
        val intent = Intent()
        setResult(RESULT_CANCELED, intent)
        finish()
    }

    private fun onError(errorMessage: String? = null) {
        val intent = Intent()
        intent.putExtra(
            EXTRA_RESULT_DATA, FacebookLoginResult(
                resultStatus = false,
                profileDetails = null,
                message = errorMessage ?: "get facebook user details failed"
            )
        )
        setResult(RESULT_OK, intent)
        finish()
    }

}

