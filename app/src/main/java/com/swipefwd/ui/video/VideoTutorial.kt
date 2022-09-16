package com.swipefwd.ui.video

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.VideoView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.swipefwd.Injection
import com.swipefwd.R
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityVideoTutorialBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel
import com.swipefwd.ui.tutorial.onboard.OnBoardingViewModel
import com.swipefwd.ui.updateuserprofile.UserProfileActivity
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class VideoTutorial : AppCompatActivity() {

    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(applicationContext, AppRepository()) }
    }

    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity , AppRepository()) }
    }

    private val viewModel: OnBoardingViewModel by viewModels {
        viewModelFactory {
            OnBoardingViewModel(
                Injection.getInternalAppDataStore(applicationContext)
            )
        }
    }

    private val userViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(this, AppRepository()) }
    }

    private val mActivity by lazy {
        this@VideoTutorial
    }

    private lateinit var binding: ActivityVideoTutorialBinding
    var timer : CountDownTimer? = null
    var DURATION : Long = 30000
    var accountType = ""
    var inviterGender = "M"
    var userType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN , "7")
        binding.tutorialNext.visibility = View.VISIBLE
        lifecycleScope.launch {
            userType = AppUtils.getUserType(this@VideoTutorial)
            inviterGender = userViewModel.getInviterGender.firstOrNull() ?: ""
            Log.d("INVITER_GENDER_5", inviterGender)

            if (userType == 1)
            {
                accountType = AppUtils.AccountTypes.Dater
            }
            else if (userType ==2)
            {
                accountType = AppUtils.AccountTypes.Matchmaker

            }
            else{

            }
         //   accountType = swipeViewModel.getAccountType.firstOrNull() ?: ""

            inviterGender = "M"
           // accountType = AppUtils.AccountTypes.Dater
            init(accountType)
        }
    }

    private fun init(accountType: String) {
        videoPlay(accountType)
        countDownTimer()
    }

    private fun videoPlay(accountType: String) {
        val videoView: VideoView = findViewById<View>(R.id.videoView) as VideoView
        var path = ""
        Log.d("INVITER_GENDER_5", inviterGender)
        when(accountType){
            AppUtils.AccountTypes.Dater -> {
                Log.d("INVITER_GENDER_5" , "DATER")
                path = "android.resource://" + packageName + "/" + R.raw.onboarding_tutorial_dater
                //path = "android.resource://" + packageName + "/" + R.raw.maleconnector
            }
            AppUtils.AccountTypes.Matchmaker -> {
                path = when(inviterGender){
                    "F" ->
                        "android.resource://" + packageName + "/" + R.raw.onboarding_tutorial_connector_female
                     //   "android.resource://" + packageName + "/" + R.raw.maleconnector
                    else ->
                        "android.resource://" + packageName + "/" + R.raw.onboarding_tutorial_connector_male
                      //  "android.resource://" + packageName + "/" + R.raw.maleconnector
                }
                DURATION = 26000
            }
        }
        videoView.setVideoURI(Uri.parse(path))
        videoView.setZOrderOnTop(true)
        videoView.start()
        videoView.visibility = View.VISIBLE
        videoView.setOnCompletionListener {

        }
        binding.tutorialNext.setOnClickListener {
            if (AppUtils.isClickedRecently()) return@setOnClickListener
            profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN , "2")
            lifecycleScope.launch {
                viewModel.savePreference(PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED, true)
                profileViewModel.savePreference(PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED, true)
                startActivity(Intent(this@VideoTutorial,UserProfileActivity::class.java))
//                finish()
                finishAffinity()
                overridePendingTransition(R.anim.enter_anim_splash,  R.anim.leave_anim_splash)
            }
        }

        binding.btnNextBlack.setOnClickListener {
            if (AppUtils.isClickedRecently()) return@setOnClickListener
            profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN , "2")
            lifecycleScope.launch {
                viewModel.savePreference(PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED, true)
                profileViewModel.savePreference(PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED, true)
                startActivity(Intent(this@VideoTutorial,UserProfileActivity::class.java))
//                finish()
                finishAffinity()
                overridePendingTransition(R.anim.enter_anim_splash,  R.anim.leave_anim_splash)
            }
        }
    }

    private fun countDownTimer() {
         timer = object: CountDownTimer(DURATION, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                binding.tutorialNext.visibility = View.VISIBLE
                profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN , "2")
                lifecycleScope.launch {
                    viewModel.savePreference(
                        PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED ,
                        true
                    )
                }
            }
        }
        timer?.start()
    }

    override fun onPause() {
        super.onPause()
        if (timer!=null) {
            timer?.cancel()
            timer = null
        }
    }


    override fun onRestart() {
        super.onRestart()
        videoPlay(accountType)
        countDownTimer()
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        finish()
    }
}