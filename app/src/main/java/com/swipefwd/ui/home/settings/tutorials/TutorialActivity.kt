package com.swipefwd.ui.home.settings.tutorials

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.swipefwd.R
import com.swipefwd.databinding.ActivityTutorialBinding

open class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding
    private val mActivity by lazy {
        this@TutorialActivity
    }
    private val pagerAdapter by lazy {
        TutorialPagerAdapter(supportFragmentManager, lifecycle)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            ivBack.setOnClickListener {
                onBackPressed()
            }
            pager.adapter = pagerAdapter
            TabLayoutMediator(tabTutorial, pager) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.user_matchmaker)
                    else -> getString(R.string.user_dater)
                }
            }.attach()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}