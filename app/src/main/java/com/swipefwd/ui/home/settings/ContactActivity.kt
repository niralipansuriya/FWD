package com.swipefwd.ui.home.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.R
import com.swipefwd.databinding.ActivityContactBinding
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.showSnackBar

class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            edtMsg.setOnEditorActionListener { _, _, _ ->
                if (edtMsg.text.toString().isEmpty()) {
                    showSnackBar(layoutMain, "Please write something")
                } else {
                    onBackPressed()
                }
                false
            }

            imgCancel.setOnClickListener {
                onBackPressed()
            }
            this@ContactActivity.setShaderView(
                txtDesc,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
        }
    }
}