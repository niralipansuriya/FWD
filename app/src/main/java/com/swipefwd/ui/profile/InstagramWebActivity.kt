package com.swipefwd.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.databinding.ActivityInstagramWebBinding
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.ProgressBarHandler

class InstagramWebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInstagramWebBinding
    private val progressBarHandler by lazy {
        ProgressBarHandler(this)
    }

    private val url =
//        "https://api.instagram.com/oauth/authorize?client_id=${AppUtils.InstaKeys.instaClientId}&client_secret=${AppUtils.InstaKeys.instaClientSecret}&redirect_uri=${AppUtils.InstaKeys.instaRedirectURI}&scope=user_profile,user_media&response_type=code"
        "https://api.instagram.com/oauth/authorize/?client_id=${AppUtils.InstaKeys.instaClientId}&client_secret=${AppUtils.InstaKeys.instaClientSecret}&redirect_uri=${AppUtils.InstaKeys.instaRedirectURI}&scope=user_profile&response_type=code"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstagramWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {

        Log.e("TAG", "INSTA URL == $url")

        binding.apply {
            webView.settings.javaScriptEnabled = true
            CookieManager.getInstance().removeAllCookies(null)
            CookieManager.getInstance().flush()

            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    super.shouldOverrideUrlLoading(view, request)
                    progressBarHandler.dismiss()
                    Log.e("TAG_URL", "REQUEST === ${request?.url}")
                    val code = request?.url?.getQueryParameter("code")
                    Log.e("TAG", "CODE === $code")
                    if (code != null) {
                        val intent = Intent()
                        intent.putExtra("instagramCode", code)
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                    return false
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    progressBarHandler.dismiss()
                    Log.e("TAG", "ERROR === ${request.hashCode()}")
                    Log.e("TAG", "ERROR === ${request?.url}")
                    Log.e("TAG", "ERROR === ${error?.errorCode}")
                    Log.e("TAG", "ERROR === ${error?.description}")
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBarHandler.dismiss()
                }
            }
            progressBarHandler.show()
            webView.loadUrl(url)
        }
    }
}