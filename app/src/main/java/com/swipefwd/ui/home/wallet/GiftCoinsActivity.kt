package com.swipefwd.ui.home.wallet

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.swipefwd.databinding.ActivityGiftCoinsBinding
import com.swipefwd.utils.AppUtils.getMultipliersBy
import com.swipefwd.utils.AppUtils.nextActivity

class GiftCoinsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGiftCoinsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGiftCoinsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            imgBack.setOnClickListener {
                onBackPressed()
            }
            btnGiftCoins.setOnClickListener {
                requestRuntimePermission()
            }
            //set data in wheel view
            pickerCoins.data = (1..10).getMultipliersBy(5)
            pickerMoney.data = (1..7).getMultipliersBy(70)
        }
    }

    private fun requestRuntimePermission() {
        Dexter.withContext(this)
            .withPermission(Manifest.permission.READ_CONTACTS)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    nextActivity(UserListForGiftCoinsActivity::class.java)
                    finish()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied) {
                        val intent =
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts(
                            "package",
                            packageName, null
                        )
                        intent.data = uri
                        try {
                            startActivity(intent)
                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}