package com.swipefwd.ui.auth.sociallogin.linkedin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.Injection
import com.swipefwd.base.ResultState
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.ProgressBarHandler


/** this activity is deal with the linkedin  login */
class LinkedInLoginActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "LinkedInLoginActivity"
        const val EXTRA_RESULT_DATA = "linked_in_login_result_data"
    }

    private val progressLoader by lazy { ProgressBarHandler(this) }
    private val linkedLoginViewModel: LinkedInLoginViewModel by viewModels {
        viewModelFactory { LinkedInLoginViewModel(Injection.getAppRepository()) }
    }

    //linkedIn login activity contract
    private var linkedInWebLoginActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { onLinkedInWebLoginResult(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /////////////////////////////////////////////////////////////////////////////
        //google detail observer
        linkedLoginViewModel.userDetails().observe(this) {
            onLinkedInUserDetailsResult(it)
        }
        /////////////////////////////////////////////////////////////////////////////
        //init linkedIn login process
        initLoginProcess()
    }

    override fun finish() {
        super.finish()
        //off exit transition
        overridePendingTransition(0, 0)
    }


    private fun initLoginProcess() {
        linkedInWebLoginActivityLauncher.launch(
            Intent(this, LinkedInWebActivity::class.java)
        )
    }

    private fun onLinkedInWebLoginResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val linkedInResult = result.data?.getParcelableExtra(
                LinkedInWebActivity.EXTRA_RESULT_DATA) as? LinkedInWebActivityResult
            Log.d("LINKED IN RESULT: ", "$linkedInResult \n ${result.data}")
            if (linkedInResult == null) {
                onError("result null")
                return
            }
            onLinkedInWebLoginDetailsResult(linkedInResult)
        } else {
            Log.i(TAG,"RESULT NOT OK")
           onCancelled()
        }
    }


    private fun onLinkedInWebLoginDetailsResult(result: LinkedInWebActivityResult){
        if(result.resultStatus && !result.code.isNullOrBlank()){
            linkedLoginViewModel.getLinkedInProfileDetails(result.code)
        }else{
            onError("result code null")
        }
    }
    private fun onLinkedInUserDetailsResult(result: ResultState<LinkedInUserDetails>) {
        when (result) {
            is ResultState.Success -> {
                progressLoader.dismiss()
                sendSuccessResult(result.data)
            }
            is ResultState.Error -> {
                progressLoader.dismiss()
                onError("linked in details ResultState error")
            }
            is ResultState.Loading -> progressLoader.show()
        }
    }

    private fun sendSuccessResult(userDetails: LinkedInUserDetails){
        Log.d(TAG, "result $userDetails")
        val intent = Intent()
        intent.putExtra(
            EXTRA_RESULT_DATA, LinkedInLoginResult(
                resultStatus = true,
                profileDetails = userDetails,
                message = "get linkedin user details successfully"
            )
        )
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun onCancelled() {
        Log.i(TAG, "onCancelled")
        val intent = Intent()
        setResult(RESULT_CANCELED, intent)
        finish()
    }

    private fun onError(errorMessage: String? = null) {
        Log.i(TAG, "onError $errorMessage")
        val intent = Intent()
        intent.putExtra(
            EXTRA_RESULT_DATA, LinkedInLoginResult(
                resultStatus = false,
                profileDetails = null,
                message = errorMessage ?: "get linkedin user details failed"
            )
        )
        setResult(RESULT_OK, intent)
        finish()
    }
}
