package com.swipefwd.ui.home.wallet

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.swipefwd.R
import com.swipefwd.databinding.ActivityRedeemCoinsBinding
import com.swipefwd.utils.AppUtils.getMultipliersBy

class RedeemCoinsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRedeemCoinsBinding
    private val mActivity by lazy {
        this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRedeemCoinsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            //set data in wheel view
            pickerCoins.data = (1..10).getMultipliersBy(5)
            pickerMoney.data = (1..7).getMultipliersBy(70)
            imgBack.setOnClickListener {
                onBackPressed()
            }
            btnRedeemCoins.setOnClickListener {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                );
                layoutSuccess.visibility = View.VISIBLE
                layoutRedeemCoin.visibility = View.GONE
                Glide.with(mActivity)
                    .asGif()
                    .load(R.drawable.flash1).listener(object : RequestListener<GifDrawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<GifDrawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: GifDrawable?,
                            model: Any?,
                            target: Target<GifDrawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            resource?.setLoopCount(1)
                            resource?.registerAnimationCallback(object :
                                Animatable2Compat.AnimationCallback() {
                                override fun onAnimationEnd(drawable: Drawable?) {
                                    //redirect screen after gif animation complete
                                    onBackPressed()
                                }
                            })
                            return false
                        }
                    })
                    .into(binding.ivStar)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}