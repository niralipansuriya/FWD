package com.swipefwd.ui.tutorial.onboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.swipefwd.Injection
import com.swipefwd.R
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityOnboardingBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.splash.SplashViewModel
import com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.internalAppDataStore
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class OnBoardingActivity  : AppCompatActivity()  {

    private val binding by lazy {
        ActivityOnboardingBinding.inflate(layoutInflater)
    }

    private val mActivity by lazy {
        this@OnBoardingActivity
    }

    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }

    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(applicationContext, AppRepository()) }
    }

    private val viewModel: OnBoardingViewModel by viewModels {
        viewModelFactory {
            OnBoardingViewModel(
                Injection.getInternalAppDataStore(applicationContext)
            )
        }
    }



    private val onBoardingWarehouse by lazy {
        OnBoardingDataStore.create()
    }

    private val onboardPagerAdapter by lazy {
        OnBoardPagerAdapter(this@OnBoardingActivity, onBoardingWarehouse)
    }

    var accountType = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN , "7")
        lifecycleScope.launch {
            accountType = swipeViewModel.getAccountType.firstOrNull() ?: ""
        }
        initPager()
        binding.btnSkip.setOnClickListener {
           onTutorialCompleted()
        }
        binding.btnContinue.setOnClickListener {
            binding.btnSkip.performClick()
        }
    }

    private fun onTutorialCompleted(){
        profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN , "0")
        lifecycleScope.launch {
            viewModel.savePreference(PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED, true)
            nextActivity(LoadingActivity::class.java)
            finish()
            finishAffinity()
        }
    }

    private fun initPager() {
        binding.pager.adapter = onboardPagerAdapter
        binding.layoutIndicator.setViewPager(binding.pager)
        binding.pager.addOnPageChangeListener(viewPagerOnPageChangeListener)
    }

    private var viewPagerOnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            if (position == (binding.pager.adapter?.count)?.minus(1)) {
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
            val intent = Intent(context, OnBoardingActivity::class.java)
            context.startActivity(intent)
        }
    }

}