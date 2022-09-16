package com.swipefwd.ui.home.settings.deleteaccount

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.swipefwd.R
import com.swipefwd.databinding.ActivityAccountDeletedBinding
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.auth.login.LoginActivity
import com.swipefwd.utils.AppUtils.removePreference
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.home.settings.SettingsViewModel

class AccountDeletedActivity : AppCompatActivity() {
    lateinit var binding: ActivityAccountDeletedBinding
    private val deletedViewModel: SettingsViewModel by viewModels {
        viewModelFactory { SettingsViewModel(this, AppRepository()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountDeletedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.imgCancel.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        deletedViewModel.removePreference(context = this@AccountDeletedActivity)
       // nextActivity(LoginActivity::class.java)
        var intent = Intent(this,LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        overridePendingTransition(R.anim.right_to_left,  R.anim.left_to_right)
    }
}