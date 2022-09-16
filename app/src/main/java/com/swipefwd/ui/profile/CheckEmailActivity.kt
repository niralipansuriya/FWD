package com.swipefwd.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.swipefwd.R
import com.swipefwd.databinding.ActivityAccountRecoveryBinding
import com.swipefwd.databinding.ActivityCheckEmailBinding
import com.swipefwd.ui.auth.login.LoginActivity
import com.swipefwd.ui.auth.phone.PhoneNumberActivity
import com.swipefwd.utils.AppUtils.changeStatusBarColor
import com.swipefwd.utils.AppUtils.nextActivity

class CheckEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckEmailBinding
    private lateinit var email:String
    private val mActivity by lazy {
        this
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        email=intent.getStringExtra("email").toString()
        init()
    }

    fun init(){
        changeStatusBarColor()
        binding.apply {
            emailMessageDetail.text="$email"
            imgBack.setOnClickListener {
//                nextActivity(LoginActivity::class.java)
                //nextActivity(LoginActivity::class.java)
                finish()
            }
            txtUseDifferentEmail.setOnClickListener {
               // onBackPressed()
                nextActivity(AccountRecoveryActivity::class.java)
                finish()
            }
        }
    }
}