package com.swipefwd.ui.home.message

import android.graphics.Color
import android.os.Bundle
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
import com.swipefwd.databinding.ActivityRematchBinding

class RematchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRematchBinding
    private val plansAdapter by lazy {
        RematchPlansAdapter(this)
    }
    private val images by lazy {
        arrayOf(
            R.drawable.pager1_white,
            R.drawable.time,
            R.drawable.deck,
            R.drawable.superlike_rematch,
            R.drawable.lightgrayfwd_logo
        )
    }
    private val title by lazy {
        arrayOf(
            resources.getString(R.string.pager_1),
            resources.getString(R.string.pager_2),
            resources.getString(R.string.pager_3),
            resources.getString(R.string.pager_4),
            resources.getString(R.string.pager_5)
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRematchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            planPager.adapter = RematchPagerAdapter(this@RematchActivity, images, title)
            layoutIndicator.setViewPager(planPager)

            //Plan adapter
            rvPlansList.adapter = plansAdapter

            txtContinue.setOnClickListener {
                onBackPressed()
            }
            val span = SpannableString(txtPlanMessage.text)
            span.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.color_resend,
                        theme
                    )
                ),
                140,
                182,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            span.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.color_resend,
                        theme
                    )
                ),
                140,
                182,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            span.setSpan(object : ClickableSpan() {
                override fun onClick(view: View) {
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }, 140, 182, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            txtPlanMessage.setText(span, TextView.BufferType.SPANNABLE)
            txtPlanMessage.highlightColor = Color.TRANSPARENT
            txtPlanMessage.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}