package com.swipefwd.ui.home.wallet

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.databinding.ActivityBuyCoinsBinding
import com.swipefwd.utils.AppUtils.getMultipliersBy

class BuyCoinsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBuyCoinsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyCoinsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            imgBack.setOnClickListener {
                onBackPressed()
            }
            btnBuyFwdCoins.setOnClickListener {
                //in app purchase screen
                layoutBuyCoins.visibility = View.GONE
                layoutAuthentication.visibility = View.VISIBLE
                val countDownTimer = object : CountDownTimer(5000, 1000) {
                    override fun onFinish() {
                        cancel()
                        layoutAuthentication.visibility = View.GONE
                        layoutPurchase.visibility = View.VISIBLE
                    }

                    override fun onTick(millisUntilFinished: Long) {
                    }
                }
                countDownTimer.start()
            }
            btnDone.setOnClickListener {
                onBackPressed()
            }
            //set data in wheel view
            pickerCoins.data = (1..10).getMultipliersBy(5)
            pickerMoney.data = (1..7).getMultipliersBy(70)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}