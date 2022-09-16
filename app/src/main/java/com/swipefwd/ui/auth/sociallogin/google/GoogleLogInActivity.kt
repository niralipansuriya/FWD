package com.swipefwd.ui.auth.sociallogin.google

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.swipefwd.Injection
import com.swipefwd.base.ResultState
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.auth.login.LoginActivity
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.ProgressBarHandler


/** this activity is deal with the google login */
class GoogleLogInActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "GoogleLogInActivity"
        const val EXTRA_RESULT_DATA = "google_login_result_data"
    }

    private val progressLoader by lazy { ProgressBarHandler(this) }
    private val googleLogInViewModel: GoogleLogInViewModel by viewModels {
        viewModelFactory {
            GoogleLogInViewModel(
                Injection.getAppRepository(),
                Injection.getInternalAppDataStore(applicationContext),
                Injection.getAppDataBase(applicationContext)
            )
        }
    }
    var mGoogleApiClient: GoogleApiClient? = null

    //google login activity contract
    private val googleLoginActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { onGoogleLoginActivityResult(it) }

    // var httpTransport = NetHttpTransport()
    //  var jacksonFactory = JacksonFactory()

//    private val httpTransport: HttpTransport = NetHttpTransport()
//    private val trans

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //////////////////////////////////////////////////////////////////////////
        //google detail observer
        googleLogInViewModel.userDetails().observe(this) {
            onGoogleUserDetailsResult(it)
        }
        /////////////////////////////////////////////////////////////////////////////
        //init google login process
        initGoogleLoginProcess()
    }

    override fun finish() {
        super.finish()
        //off exit transition
        overridePendingTransition(0, 0)
    }

    private fun googleLoginApi() {
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

        }
    }

    /**
     * this function will be called on the launching of the activity
     * in this function first of all check for google play services availability
     * if available than proceed further, otherwise this activity will finish
     */
    private fun initGoogleLoginProcess() {
        val available = GoogleApiAvailability().isGooglePlayServicesAvailable(this)
        when {
            available == ConnectionResult.SUCCESS -> {
                //init google sign in option
                    initGoogleSignInClient()
                //open google login popup
                openGoogleLoginDialog()
            }
            GoogleApiAvailability.getInstance().isUserResolvableError(available) -> {
                val dialog = GoogleApiAvailability.getInstance()
                    .getErrorDialog(this, available, 111)
                //check is dialog null or not
                if (dialog == null) {
                    onError()
                    return
                }
                dialog.setOnCancelListener {
                    onCancelled()
                    return@setOnCancelListener
                }
                dialog.setOnDismissListener {
                    onCancelled()
                    return@setOnDismissListener
                }
                dialog.show()
            }
            else -> {
                onCancelled()
                return
            }
        }
    }

    //GOOGLE SOCIAL LOGIN SECTION

    private fun initGoogleSignInClient() {
        Log.e("printResult", "google")
        val scopeBirthday = Scope("https://www.googleapis.com/auth/user.birthday.read") //uncommented by nirali
        val scopeGender = Scope("https://www.googleapis.com/auth/user.gender.read")//uncommented by nirali
        val scopePhoneNumber = Scope("https://www.googleapis.com/auth/user.phonenumbers.read")//uncommented by nirali
        val googleSingOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestId()
            .requestIdToken("406001238441-luocj5cf8n3eviqv18nnu93r9c6r22km.apps.googleusercontent.com")
            .requestServerAuthCode("406001238441-luocj5cf8n3eviqv18nnu93r9c6r22km.apps.googleusercontent.com")
            .requestProfile()
//            .requestScopes(Scope(Scopes.PLUS_ME))
            .requestScopes(Scope(Scopes.PLUS_ME), scopeBirthday, scopeGender, scopePhoneNumber)//uncommented by nirali
            .requestEmail()
            .build()
        googleLogInViewModel.googleSignInClient = GoogleSignIn.getClient(this, googleSingOption)
        // val serverClientId = getString(R.string.server_client_id)
        val account = GoogleSignIn.getLastSignedInAccount(this)  //Check last Signed account
        if (account != null) {
            googleLogInViewModel.googleSignInClient?.signOut()?.addOnCompleteListener(this) {
                Log.e(TAG, "google logout")
            }
        }
//        var people = People.Builder(httpTransport, jacksonFactory, )

    }

/*
    private fun initGoogleSignInClient() {
        Log.e("printResult", "google")
     //   val scopeBirthday = Scope("https://www.googleapis.com/auth/user.birthday.read") //uncommented by nirali
     //  val scopeGender = Scope("https://www.googleapis.com/auth/user.gender.read")//uncommented by nirali
        val googleSingOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestId()
            .requestProfile()
            .requestScopes(Scope(Scopes.PLUS_ME))
           // .requestScopes(Scope(Scopes.PLUS_ME), scopeBirthday, scopeGender)//uncommented by nirali
            .requestEmail()
            .build()
        googleLogInViewModel.googleSignInClient = GoogleSignIn.getClient(this, googleSingOption)
        // val serverClientId = getString(R.string.server_client_id)
        val account = GoogleSignIn.getLastSignedInAccount(this)  //Check last Signed account
        if (account != null) {
            googleLogInViewModel.googleSignInClient?.signOut()?.addOnCompleteListener(this) {
                Log.e(TAG, "google logout")
            }
        }
//        var people = People.Builder(httpTransport, jacksonFactory, )


    }
*/


    private fun openGoogleLoginDialog() {
        if (!AppUtils.isNetworkAvailable(this)) {
            onError()
            return
        }
        val googleSignInClient = googleLogInViewModel.googleSignInClient
        if (googleSignInClient == null) {
            //send error log message to firebase
            // firebaseCrashlyticsLog("google sign in client null")

            onError()
            return
        }
        //launch google sign in client
        googleLoginActivityLauncher.launch(googleSignInClient.signInIntent)
    }


    private fun onGoogleLoginActivityResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            if (!task.isSuccessful && task.isCanceled && !task.isComplete) {
                onError()
                return
            }
            handleGoogleSignInResult(task)
        } else {
            onCancelled()
            return
        }
    }


    private fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            googleLogInViewModel.setAccountDetails(task.getResult(ApiException::class.java))
            googleLogInViewModel.createUserDetails()

              // val person: com.google.android.gms.plus.model.people.Person? = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient)
            //   Log.e("printGender",Gson().toJson(person))
        } catch (e: ApiException) {
            //send error log message to firebase
            //  firebaseCrashlyticsLog("google signInResult:failed code=" + e.statusCode)
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            onError()
        }
    }

    private fun onGoogleUserDetailsResult(result: ResultState<GoogleUserDetails>) {
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
        }
    }

    private fun sendSuccessResult(userDetails: GoogleUserDetails) {
        val intent = Intent()
        intent.putExtra(
            EXTRA_RESULT_DATA, GoogleLoginResult(
                resultStatus = true,
                profileDetails = userDetails,
                message = "get google user details successfully"
            )
        )
        setResult(RESULT_OK, intent)


       /*temp if (userDetails.email.isNotEmpty()) { // social login google api call
            googleLogInViewModel.googleLogin(userDetails.email)

        }*/
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
            EXTRA_RESULT_DATA, GoogleLoginResult(
                resultStatus = false,
                profileDetails = null,
                message = errorMessage ?: "get google user details failed"
            )
        )
        setResult(RESULT_OK, intent)
        finish()
    }

}
