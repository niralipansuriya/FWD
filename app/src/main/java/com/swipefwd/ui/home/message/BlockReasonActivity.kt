package com.swipefwd.ui.home.message

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.R
import com.swipefwd.databinding.ActivityBlockReasonBinding
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.showSnackBar

class BlockReasonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlockReasonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlockReasonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            edtReason.setOnEditorActionListener { _, _, _ ->
                if (edtReason.text.toString().isEmpty()) {
                    showSnackBar(layoutMain, "Please provide reason")
                } else {
                    setResult(RESULT_OK)
                    finish()
                }
                false
            }
            this@BlockReasonActivity.setShaderView(
                txtTitle,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )

            imgCancel.setOnClickListener {
                onBackPressed()
            }
        }
    }
}