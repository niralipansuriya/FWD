package com.swipefwd.ui.home.settings

import android.animation.Animator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.R
import com.swipefwd.databinding.ActivityPurchaseSuccessBinding

class PurchaseSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_FullScreen)
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            if (intent.hasExtra("planType")) {
                when (intent.getStringExtra("planType")) {
                    getString(R.string.elite) -> {
                        lottiePurchase.setAnimation("elite.json")
                    }
                    else -> {
                        lottiePurchase.setAnimation("premier.json")
                    }
                }
            }
            lottiePurchase.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {
                    onBackPressed()
                }

                override fun onAnimationRepeat(animation: Animator) {
                }

                override fun onAnimationStart(animation: Animator) {
                }
            })
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}