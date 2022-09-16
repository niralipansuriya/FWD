package com.swipefwd.ui.tutorial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.swipefwd.R
import com.swipefwd.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    private val pagerAdapter by lazy {
        TutorialPagerAdapter(supportFragmentManager, lifecycle)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTabs()

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun initTabs() {
        binding.pager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabTutorial, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.user_matchmaker)
                else -> getString(R.string.user_dater)
            }
        }.attach()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, TutorialActivity::class.java)
            context.startActivity(intent)
        }
    }

}
