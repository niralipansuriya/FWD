package com.swipefwd.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.databinding.ActivityWebViewBinding
import com.swipefwd.utils.ProgressBarHandler

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding
    private val progressBarHandler by lazy {
        ProgressBarHandler(this)
    }
    private var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        clickListeners()
    }

    private fun clickListeners() {
        binding.apply {
            btnCancel.setOnClickListener {
                this@WebViewActivity.onBackPressed()
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        url = intent.getStringExtra("url")!!
        binding.apply {
            webView.settings.javaScriptEnabled = true
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?,
                ): Boolean {
                    super.shouldOverrideUrlLoading(view, request)
                    progressBarHandler.dismiss()
                    return false
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?,
                ) {
                    super.onReceivedError(view, request, error)
                    Log.d("TAG:", "CANCEL: $error")
                    progressBarHandler.dismiss()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Log.d("TAG:", "onPageFinished:")
                    progressBarHandler.dismiss()
                }
            }
            progressBarHandler.show()
            webView.loadUrl(url)
        }
    }
}