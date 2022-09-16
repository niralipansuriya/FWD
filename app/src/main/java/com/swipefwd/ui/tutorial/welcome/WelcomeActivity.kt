package com.swipefwd.ui.tutorial.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.swipefwd.R
import com.swipefwd.databinding.ActivityWelcomeBinding
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.datastore.PreferenceKeys


class WelcomeActivity : AppCompatActivity() {

    private val lstOfGreets by lazy {
        GreetingsProvider.create()
    }

    private val welcomePagerAdapter by lazy {
        WelcomePagerAdapter(this@WelcomeActivity, lstOfGreets)
    }

    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initPager()

        binding.btnSkip.setOnClickListener {
          onTutorialCompleted()
        }

        binding.btnContinue.setOnClickListener {
            binding.btnSkip.performClick()
        }
    }

    private fun onTutorialCompleted(){
        nextActivity(TabManagerActivity::class.java)
        finish()
        finishAffinity()
    }


    private fun initPager() {
        binding.pagerIntro.adapter = welcomePagerAdapter
        binding.layoutIndicator.setViewPager(binding.pagerIntro)
        binding.pagerIntro.addOnPageChangeListener(viewPagerOnPageChangeListener)
    }


    private var viewPagerOnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            if (position == (binding.pagerIntro.adapter?.count)?.minus(1)) {
                //change Skip button to Continue at last position of pager
                binding.btnSkip.visibility = View.INVISIBLE
                binding.btnContinue.visibility = View.VISIBLE
            } else {
                binding.btnSkip.visibility = View.VISIBLE
                binding.btnContinue.visibility = View.GONE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, WelcomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}
