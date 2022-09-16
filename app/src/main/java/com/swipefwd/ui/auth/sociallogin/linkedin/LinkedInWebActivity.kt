package com.swipefwd.ui.auth.sociallogin.linkedin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.databinding.ActivityLinkedInWebBinding
import com.swipefwd.utils.AppKeys
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.ProgressBarHandler

class LinkedInWebActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_RESULT_DATA = "result_data"
    }

    private lateinit var binding: ActivityLinkedInWebBinding
    private val url =
        "https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id=" +
                AppKeys.LinkedInKeys.linkedInClientId +
                "&scope=r_liteprofile%20r_emailaddress&state=codeforswipefwd&redirect_uri=" +
                AppKeys.LinkedInKeys.linkedInRedirectURI
    private val progressBarHandler by lazy {
        ProgressBarHandler(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinkedInWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //////////////////////////////////////////////////////////////////////////////
        init()
        progressBarHandler.show()
        binding.webView.loadUrl(url)
    }

    private fun init() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                super.shouldOverrideUrlLoading(view, request)
                progressBarHandler.dismiss()
                val code = request?.url?.getQueryParameter("code")
                val segments = request?.url?.pathSegments
                if (segments != null && segments.any { it == "login-cancel" }) {
                    onCancelled()
                }
                if (code != null) {
                    sendSuccessResult(code)
                }
                return false
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?,
            ) {
                super.onReceivedError(view, request, error)
                progressBarHandler.dismiss()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBarHandler.dismiss()
            }
        }
    }


    private fun sendSuccessResult(code: String) {
        val intent = Intent()
        intent.putExtra(
            EXTRA_RESULT_DATA, LinkedInWebActivityResult(
                resultStatus = true,
                code = code,
                message = "get linkedin user code successfully"
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
            EXTRA_RESULT_DATA, LinkedInWebActivityResult(
                resultStatus = false,
                code = null,
                message = errorMessage ?: "get linkedin code details failed"
            )
        )
        setResult(RESULT_OK, intent)
        finish()
    }

//    companion object {
//        private const val TAG = "LinkedInWebActivity"
//        const val EXTRA_RESULT_DATA = "result_data"
//    }
//
//    private lateinit var binding: ActivityLinkedInWebBinding
//    private val url =
//        "https://www.linkedin.com/oauth/v2/authorization?response_type=code&client_id=${AppUtils.LinkedInKeys.linkedInClientId}&scope=r_liteprofile%20r_emailaddress&state=codeforswipefwd&redirect_uri=${AppUtils.LinkedInKeys.linkedInRedirectURI}"
//    private val progressBarHandler by lazy {
//        ProgressBarHandler(this)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLinkedInWebBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        //////////////////////////////////////////////////////////////////////////////
////        binding.webView.webViewClient =
//        ///////////////////////////////////////////////////////////////////////////////////////////
////        progressBarHandler.show()
////        binding.webView.loadUrl(url)
//
//
//        init()
//    }
//
//
////    private fun init() {
////        binding.apply {
////            webView.webViewClient = object : WebViewClient() {
////                override fun shouldOverrideUrlLoading(
////                    view: WebView?,
////                    request: WebResourceRequest?,
////                ): Boolean {
////                    super.shouldOverrideUrlLoading(view, request)
////                    progressBarHandler.dismiss()
////                    Log.e("apple:", "REQUEST === ${request?.url}")
////                    Log.e("apple:", "CODE === ${request?.url?.getQueryParameter("code")}")
////                    Log.e("apple:", "LAST SEGMENT=== ${request?.url?.lastPathSegment}")
////                    val code = request?.url?.getQueryParameter("code")
////                    if (request?.url?.pathSegments!!.any {
////                            it == "login-cancel"
////                        }) {
////                        onBackPressed()
////                    }
////                    if (code != null) {
////                        val intent = Intent()
////                        intent.putExtra("linkedInCode", code)
////                        setResult(RESULT_OK, intent)
////                        finish()
////                    }
////
////                    return false
////                }
////
////                override fun onReceivedError(
////                    view: WebView?,
////                    request: WebResourceRequest?,
////                    error: WebResourceError?,
////                ) {
////                    super.onReceivedError(view, request, error)
////                    Log.d("apple:", "CANCEL: $error")
////                    progressBarHandler.dismiss()
////                }
////
////                override fun onPageFinished(view: WebView?, url: String?) {
////                    super.onPageFinished(view, url)
////                    Log.d("apple:", "onPageFinished:")
////                    progressBarHandler.dismiss()
////                }
////            }
////
////            progressBarHandler.show()
////            webView.loadUrl(url)
////        }
////    }
//
//
//    private fun init() {
//        binding.webView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(
//                view: WebView?,
//                request: WebResourceRequest?
//            ): Boolean {
//                super.shouldOverrideUrlLoading(view, request)
//                progressBarHandler.dismiss()
//
////                val requestUrl = request?.url
////                if (requestUrl == null) {
////                    onError("request url is null")
////                    return false
////                }
//                val code = request?.url?.getQueryParameter("code")
//                val segments = request?.url?.pathSegments
//                if (segments != null && segments.any { it == "login-cancel" }) {
//                    Log.i(TAG, "Login - cancel")
//                    onCancelled()
//                }
//
//
////                if (request?.url?.pathSegments!!.any {
////                        it == "login-cancel"
////                    }) {
////                    onBackPressed()
////                }
//                if (code != null) {
//                    //  val intent = Intent()
//                    //   intent.putExtra("linkedInCode", code)
//                    //    setResult(RESULT_OK, intent)
//
//                    sendSuccessResult(code)
//
//                    //    finish()
//                }
//
//
////                sendSuccessResult(code)
//
//
//                return false
//            }
//
//            override fun onReceivedError(
//                view: WebView?,
//                request: WebResourceRequest?,
//                error: WebResourceError?,
//            ) {
//                super.onReceivedError(view, request, error)
//                progressBarHandler.dismiss()
//            }
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                progressBarHandler.dismiss()
//            }
//
//        }
//        progressBarHandler.show()
//        binding.webView.loadUrl(url)
//    }
//
//
//    private fun sendSuccessResult(code: String) {
//        Log.i(TAG, "onSuccessResult")
//        Log.d(TAG, "result $code")
//        val intent = Intent()
//        intent.putExtra(
//            LinkedInLoginActivity.EXTRA_RESULT_DATA, LinkedInWebActivityResult(
//                resultStatus = true,
//                code = code,
//                message = "get linkedin user code successfully"
//            )
//        )
//        setResult(RESULT_OK, intent)
//        finish()
//    }
//
//    private fun onCancelled() {
//        Log.i(TAG, "onCancelled")
//        val intent = Intent()
//        setResult(RESULT_CANCELED, intent)
//        finish()
//    }
//
//
//    private fun onError(errorMessage: String? = null) {
//        Log.i(TAG, "onError $errorMessage")
//        val intent = Intent()
//        intent.putExtra(
//            EXTRA_RESULT_DATA, LinkedInWebActivityResult(
//                resultStatus = false,
//                code = null,
//                message = errorMessage ?: "get linkedin code details failed"
//            )
//        )
//        setResult(RESULT_OK, intent)
//        finish()
//    }

}