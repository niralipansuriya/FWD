package com.swipefwd.base

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.swipefwd.R
import com.swipefwd.ui.auth.login.LoginActivity

/**
 * this class is the base activity class
 * other activities can extend this class
 * all the common method related to activity are declare and define in this app */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB
    abstract fun onCreateViewBinding(): VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration)
        binding = onCreateViewBinding()
        setContentView(binding.root)
    }


    protected fun logoutUser() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finishAffinity()
//        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
    }
    private fun adjustFontScale(configuration: Configuration?) {
        configuration?.let {
            it.fontScale = 1.0F
            val metrics: DisplayMetrics = resources.displayMetrics
            val wm: WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(metrics)
            metrics.scaledDensity = configuration.fontScale * metrics.density

            baseContext.applicationContext.createConfigurationContext(it)
            baseContext.resources.displayMetrics.setTo(metrics)

        }
    }
    fun applyScreenTransition(){
        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
//comment by krupa 16/08
//     overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
       // overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left)
    }
}
