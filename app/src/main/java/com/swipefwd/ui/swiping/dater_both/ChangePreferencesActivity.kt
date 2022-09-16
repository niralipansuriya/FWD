package com.swipefwd.ui.swiping.dater_both

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.swipefwd.R
import com.swipefwd.databinding.ActivityChangePreferencesBinding
import com.swipefwd.data.models.DaterConnectionResponseModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.profile.PreferencesActivity
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.factory.viewModelFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ChangePreferencesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePreferencesBinding
    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(mActivity, AppRepository()) }
    }
    private val mActivity by lazy {
        this@ChangePreferencesActivity
    }
    private var accountType = ""
    private var fName = ""
    private var recommendingUser = DaterConnectionResponseModel.User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            val userDetail = intent.getStringExtra("selectedUser")
            if (userDetail != null) {
                recommendingUser = Gson().fromJson<Any>(
                    userDetail,
                    DaterConnectionResponseModel.User::class.java
                ) as DaterConnectionResponseModel.User
            }
            lifecycleScope.launch {
                accountType = swipeViewModel.getAccountType.firstOrNull() ?: ""
                fName = swipeViewModel.getFirstName.firstOrNull() ?: ""
                when (accountType) {
                    AppUtils.AccountTypes.Matchmaker -> {
                        txt2.text = getString(R.string.run_out_of_message)

                        if (recommendingUser.username != "") {
                            btnPrferences.text =
                                getString(
                                    R.string.message_user,
                                    recommendingUser.username!!.split(" ")[0]
                                )
                            txt1.text = getString(
                                R.string.run_out_of,
                                recommendingUser.username!!.split(" ")[0]
                            )
                            val textDrawable = mActivity.createPlaceholderImage(
                                recommendingUser.username!!,
                                100,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3
                            )

                            Glide.with(mActivity)
                                .load(recommendingUser.image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(textDrawable)
                                .into(imgUser)
                        }
                    }
                    AppUtils.AccountTypes.Dater -> {
                        txt1.text = getString(R.string.change_preference_text1)
                        txt2.text = getString(R.string.change_preference_text2)
                        btnPrferences.text = getString(R.string.preferences)

                        val image = swipeViewModel.getProfileImage.firstOrNull()
                        val textDrawable = mActivity.createPlaceholderImage(
                            fName,
                            100,
                            R.color.blue_gradient_2,
                            R.color.blue_gradient_3
                        )

                        Glide.with(mActivity)
                            .load(image)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(textDrawable)
                            .into(imgUser)
                    }
                }
            }
            imgHome.setOnClickListener {
                nextActivity(TabManagerActivity::class.java)
                finish()
                finishAffinity()
            }
            btnPrferences.setOnClickListener {
                when (accountType) {
                    AppUtils.AccountTypes.Matchmaker -> {
                        imgHome.performClick()
                    }
                    AppUtils.AccountTypes.Dater -> {
                        startActivity(
                            Intent(
                                this@ChangePreferencesActivity,
                                PreferencesActivity::class.java
                            ).putExtra("EditProfile", true)
                        )
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                        //finish()
                    }
                }
            }
        }
    }
}