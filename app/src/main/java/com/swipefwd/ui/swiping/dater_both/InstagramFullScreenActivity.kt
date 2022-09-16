package com.swipefwd.ui.swiping.dater_both

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.swipefwd.R
import com.swipefwd.databinding.ActivityInstagramFullScreenBinding
import com.swipefwd.data.models.SwipingProfileModel

class InstagramFullScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInstagramFullScreenBinding
    private val mActivity by lazy {
        this@InstagramFullScreenActivity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstagramFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        //changeStatusBarColor()
        binding.apply {
            val image = intent.getStringExtra("image") as String
            val user = intent.getStringExtra("user")
            var profileModel = SwipingProfileModel.ProfileModel()
            if (!user.isNullOrEmpty()) {
                profileModel = Gson().fromJson(
                    user,
                    SwipingProfileModel.ProfileModel::class.java
                ) as SwipingProfileModel.ProfileModel
            }
            ivCancel.setOnClickListener {
                onBackPressed()
            }
            if (profileModel.firstName!!.isNotEmpty()) {
                txtUserName.text = getString(R.string.user_instagram, profileModel.firstName)
            }
            try {
                Picasso.get().load(profileModel.profilePictureUrl)
                    .error(R.mipmap.ic_launcher)
                    .into(ivUser, object : Callback {
                        override fun onSuccess() {
                        }

                        override fun onError(e: Exception?) {
                        }
                    })
            } catch (e: Exception) {
                ivUser.setImageDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.ic_launcher))
            }

            try {
                Picasso.get().load(image)
                    .error(R.mipmap.ic_launcher)
                    .into(ivImage, object : Callback {
                        override fun onSuccess() {
                        }

                        override fun onError(e: Exception?) {
                        }
                    })
            } catch (e: Exception) {
                ivImage.setImageDrawable(ContextCompat.getDrawable(mActivity, R.mipmap.ic_launcher))
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}