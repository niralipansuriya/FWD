package com.swipefwd.ui.home.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.R
import com.swipefwd.databinding.ActivityUpdateAppBinding
import com.swipefwd.utils.AppUtils.setShaderView

class UpdateAppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            imgCancel.setOnClickListener {
                onBackPressed()

            }
            this@UpdateAppActivity.setShaderView(
                btnUpdate,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            btnUpdate.setOnClickListener {
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$packageName")
                        )
                    )
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                        )
                    )
//                    overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                }
            }
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_OK)
        finish()
    }
}