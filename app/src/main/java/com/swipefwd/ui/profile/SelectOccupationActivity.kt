package com.swipefwd.ui.profile

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.swipefwd.R
import com.swipefwd.databinding.ActivityOccupationBinding
import com.swipefwd.data.models.OccupationModel
import com.swipefwd.utils.AppUtils.openProfileFinishDialog
import com.swipefwd.utils.AppUtils.showKeyboard
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.AppConstants
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SelectOccupationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOccupationBinding
    private var model = OccupationModel()
    private val mActivity by lazy {
        this@SelectOccupationActivity
    }
    private val occupationViewModel: SelectOccupationViewModel by viewModels {
        viewModelFactory { SelectOccupationViewModel(mActivity) }
    }
    private var isFromSettings = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOccupationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("EditProfile")) {
            isFromSettings = intent.getBooleanExtra("EditProfile", false)
        }
        init()
    }

    private fun init() {
        binding.apply {
            txtIndex.text = getString(R.string.sequence_profile, "2")
            lifecycleScope.launch {
               // val occupation = occupationViewModel.getOccupation.firstOrNull()
                val occupation = AppUtils.getOccupation(this@SelectOccupationActivity)
                if (!occupation.isNullOrBlank()) {
                    val occupationModel = Gson().fromJson<Any>(
                        occupation,
                        OccupationModel::class.java
                    ) as OccupationModel
                    txtOccupationName.setText(occupationModel.title)
                    txtCompanyName.setText(occupationModel.company)
                    binding.txtSubmit.isEnabled = true
                    binding.txtSubmit.isClickable = true
                }
            }
            txtSubmit.setOnClickListener {
                model.title = txtOccupationName.text.toString().trim()
                model.company = txtCompanyName.text.toString().trim()
                occupationViewModel.saveOccupation(Gson().toJson(model))
                startActivity(
                    Intent(
                        mActivity,
                        SelectSchoolActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                finish()
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            txtSkip.setOnClickListener {
                startActivity(
                    Intent(
                        mActivity,
                        SelectSchoolActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                finish()
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            ivClose.setOnClickListener {
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
//                        if (isFromSettings) {
//                            onBackPressed()
//                        } else {
                            mActivity.openProfileFinishDialog()
//                        }
                    }
                    AppConstants.SCREEN_PREFERENCE -> {
//                        onBackPressed()
                            mActivity.openProfileFinishDialog(getString(R.string.preference_finish_content))

                    }
                }
            }
            txtOccupationName.apply {
               //temp this.showKeyboard()
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        AppUtils.hideUnderline(s)
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (count == 0) {
                            txtOccupationName.setBackgroundResource(R.drawable.grey_border_bg)
                        } else {
                            txtOccupationName.setBackgroundResource(R.drawable.corner_blue_border_bg)
                        }
                        if (txtCompanyName.text.toString().trim() == "") {
                            if (count == 0) {
                                binding.txtSubmit.isEnabled = false
                                binding.txtSubmit.isClickable = false
                            } else {
                                binding.txtSubmit.isEnabled = true
                                binding.txtSubmit.isClickable = true
                            }
                        }
                        if (s != null) {
                            if (s.length >= 25) {
                                mActivity.showSnackBar(
                                    binding.layoutMain,
                                    getString(R.string.character_limit)
                                )
                            }
                        }
                    }
                })
            }
            txtCompanyName.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    txtSubmit.performClick()
                    true
                }
                false
            }
            txtCompanyName.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    AppUtils.hideUnderline(s)
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (count == 0) {
                        txtCompanyName.setBackgroundResource(R.drawable.grey_border_bg)
                    } else {
                        txtCompanyName.setBackgroundResource(R.drawable.corner_blue_border_bg)
                    }
                    if (txtOccupationName.text.toString().trim() == "") {
                        if (count == 0) {
                            binding.txtSubmit.isEnabled = false
                            binding.txtSubmit.isClickable = false
                        } else {
                            binding.txtSubmit.isEnabled = true
                            binding.txtSubmit.isClickable = true
                        }
                    }
                    if (s != null) {
                        if (s.length >= 25) {
                            mActivity.showSnackBar(
                                binding.layoutMain,
                                getString(R.string.character_limit)
                            )
                        }
                    }
                }
            })
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}