package com.swipefwd.ui.swiping.dater_both

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.R
import com.swipefwd.databinding.ActivityNoProfilesBinding
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.utils.AppUtils.nextActivity

class NoProfilesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoProfilesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoProfilesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            imgHome.setOnClickListener {
                nextActivity(TabManagerActivity::class.java)
                finish()
                finishAffinity()
            }
            imgChat.setOnClickListener {
                startActivity(
                    Intent(this@NoProfilesActivity, TabManagerActivity::class.java)
                        .putExtra("message", "message")
                )
                finishAffinity()
//                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            btnGotIt.setOnClickListener {
                imgHome.performClick()
            }
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_OK)
        finish()
    }
}