package com.swipefwd.ui.home.settings

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.swipefwd.R
import com.swipefwd.databinding.ActivityAccountSuspendBinding
import com.swipefwd.utils.CustomTypefaceSpan

class AccountSuspendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountSuspendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountSuspendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {

            imgCancel.setOnClickListener {
                onBackPressed()
            }

            val typeface = ResourcesCompat.getFont(this@AccountSuspendActivity, R.font.medium)

            val span1 = SpannableString(txtSuspend1.text.toString())
            val span2 = SpannableString(txtSuspend2.text.toString())
            typeface?.let {
                span1.setSpan(
                    CustomTypefaceSpan("", it),
                    72,
                    txtSuspend1.text.length - 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                span2.setSpan(
                    CustomTypefaceSpan("", it),
                    75,
                    txtSuspend2.text.length - 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            span1.setSpan(
                object : ClickableSpan() {
                    override fun onClick(view: View) {
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false
                    }
                }, 72, txtSuspend1.text.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            span1.setSpan(object : ClickableSpan() {
                override fun onClick(view: View) {
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }, 72, txtSuspend1.text.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            txtSuspend1.setText(span1, TextView.BufferType.SPANNABLE)
            txtSuspend1.highlightColor = Color.TRANSPARENT
            txtSuspend1.movementMethod = LinkMovementMethod.getInstance()

            span2.setSpan(
                object : ClickableSpan() {
                    override fun onClick(view: View) {
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false
                    }
                }, 75,
                txtSuspend2.text.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            span2.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.white,
                        theme
                    )
                ), 75, txtSuspend2.text.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            txtSuspend2.setText(span2, TextView.BufferType.SPANNABLE)
            txtSuspend2.highlightColor = Color.TRANSPARENT
            txtSuspend2.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}