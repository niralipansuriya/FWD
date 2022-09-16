//package com.swipefwd.ui.main
//
//import android.app.Activity
//import android.os.Bundle
//import android.view.View
//import androidx.appcompat.app.AppCompatActivity
//import androidx.viewpager.widget.ViewPager
//import com.swipefwd.R
//import com.swipefwd.databinding.ActivityOnboardingBinding
//import com.swipefwd.ui.auth.register.UserInfoActivity
//import com.swipefwd.utils.AppUtils.nextActivity
//
//class OnBoardingActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityOnboardingBinding
//    private lateinit var mActivity: Activity
//    private val images by lazy {
//        arrayOf(
//            R.drawable.avo_zip,
//            R.drawable.avo_age,
//            R.drawable.avo_gen,
//            R.drawable.avo_zip
//        )
//    }
//    private val title by lazy {
//        arrayOf(
//            resources.getString(R.string.pager_title_1),
//            resources.getString(R.string.pager_title_2),
//            resources.getString(R.string.pager_title_3),
//            resources.getString(R.string.pager_title_4)
//        )
//    }
//    private val desc by lazy {
//        arrayOf(
//            resources.getString(R.string.pager_desc_1),
//            resources.getString(R.string.pager_desc_2),
//            resources.getString(R.string.pager_desc_3),
//            resources.getString(R.string.pager_desc_4)
//        )
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityOnboardingBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        init()
//    }
//
//    private fun init() {
//        mActivity = this@OnBoardingActivity
//        binding.apply {
//            btnSkip.setOnClickListener {
//                nextActivity(UserInfoActivity::class.java)
//                finish()
//            }
//            btnContinue.setOnClickListener {
//                btnSkip.performClick()
//            }
//
//            pager.adapter = IntroPagerAdapter(mActivity, images, title, desc)
//            layoutIndicator.setViewPager(pager)
//            pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//                override fun onPageScrolled(
//                    position: Int,
//                    positionOffset: Float,
//                    positionOffsetPixels: Int
//                ) {
//                }
//
//                override fun onPageSelected(position: Int) {
//                    if (position == (pager.adapter?.count)?.minus(1)) {
//                        //change Skip button to Continue at last position of pager
//                        btnSkip.visibility = View.INVISIBLE
//                        btnContinue.visibility = View.VISIBLE
//                    } else {
//                        btnSkip.visibility = View.VISIBLE
//                        btnContinue.visibility = View.GONE
//                    }
//                }
//
//                override fun onPageScrollStateChanged(state: Int) {
//                }
//            })
//        }
//    }
//}