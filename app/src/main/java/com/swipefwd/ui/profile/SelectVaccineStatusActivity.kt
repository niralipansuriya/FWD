package com.swipefwd.ui.profile

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.swipefwd.R
import com.swipefwd.databinding.ActivityVaccineStatusBinding
import com.swipefwd.data.models.CovidVaccineListModel
import com.swipefwd.data.models.VaccinationModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.openProfileFinishDialog
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SelectVaccineStatusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVaccineStatusBinding
  //  private var selectedStatus = CovidVaccineListModel.CovidModel()
    private var selectedStatus = VaccinationModel.VaccinationData.VaccinationLevel()
    private val mActivity by lazy {
        this@SelectVaccineStatusActivity
    }
    private val adapter by lazy {
        SelectVaccineStatusAdapter {
            selectedStatus = it
        }
    }
    private val vaccineStatusViewModel: SelectVaccineViewModel by viewModels {
        viewModelFactory { SelectVaccineViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private val mDatabase by lazy {
        AppDatabase.getInstance(mActivity)
    }
    private var isFromSettings = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccineStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("EditProfile")) {
            isFromSettings = intent.getBooleanExtra("EditProfile", false)
        }
        init()
    }

    private fun init() {
        binding.apply {
            txtIndex.text = getString(R.string.sequence_profile, "7")
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
            txtSkip.setOnClickListener {
                startActivity(
                    Intent(
                        mActivity,
                        SelectSmokeActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                finish()
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            txtSubmit.setOnClickListener {
                vaccineStatusViewModel.saveCovidStatus(Gson().toJson(selectedStatus))
                startActivity(
                    Intent(
                        mActivity,
                        SelectSmokeActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                finish()
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            val span = SpannableString(txtTitle.text)

            span.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.light_orange,
                        theme
                    )
                ),
                20,
                21,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            txtTitle.setText(span, TextView.BufferType.SPANNABLE)

            val spanMessage = SpannableString(txtMessage.text)
            spanMessage.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.light_orange,
                        theme
                    )
                ),
                0,
                1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            txtMessage.setText(spanMessage, TextView.BufferType.SPANNABLE)
            rcvCovidStatus.adapter = adapter
            var vaccineStatusDbList: ArrayList<CovidVaccineListModel.CovidModel>
            lifecycleScope.launch {
                vaccineStatusDbList =
                    mDatabase.userProfileDao()
                        .getAllVaccineStatus() as ArrayList<CovidVaccineListModel.CovidModel>
                if (vaccineStatusDbList.isNotEmpty()) {
                   //temp bindAdapter(vaccineStatusDbList)
                } else {
                    getVaccineStatusList()
                    initObservers()
                }
            }
        }
    }

    private fun getVaccineStatusList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getVaccineStatusList()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getVaccineStatusList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    vaccineStatusViewModel.vaccinationApi()

                    /*vaccineStatusViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        *//*vaccineStatusViewModel.vaccineStatusList(
                            "Bearer $authToken"
                        )*//*
                    }*/
                }
            }
        }
    }

  //  private fun bindAdapter(list: ArrayList<CovidVaccineListModel.CovidModel>) {
    private fun bindAdapter(list: ArrayList<VaccinationModel.VaccinationData.VaccinationLevel>) {
        adapter.addAll(list)
        lifecycleScope.launch {
          //  val vaccineStatus = vaccineStatusViewModel.getVaccine.firstOrNull()
            val vaccineStatus = AppUtils.getVaccination(this@SelectVaccineStatusActivity)
            if (!vaccineStatus.isNullOrBlank()) {
                val vaccineStatusModel = Gson().fromJson<Any>(
                    vaccineStatus,
                   VaccinationModel.VaccinationData.VaccinationLevel::class.java
                ) as VaccinationModel.VaccinationData.VaccinationLevel
                adapter.setSelected(vaccineStatusModel)
            }
        }
    }

    private fun initObservers() {
        vaccineStatusViewModel.apply {
            showLoading.observe(mActivity, {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            })
            errorMessage.observe(mActivity, {
                Log.e("TAG", "ERROR === $it")
            })
           /* data.observe(mActivity, { vaccineStatusList ->
                lifecycleScope.launch {
                    bindAdapter(vaccineStatusList)
                    mDatabase.userProfileDao().insertAllVaccineStatus(vaccineStatusList)
                }
            })*/
            vaccinationData.observe(mActivity, { vaccineStatusList ->
                lifecycleScope.launch {
                    bindAdapter(vaccineStatusList)
                    //mDatabase.userProfileDao().insertAllVaccineStatus(vaccineStatusList)
                }
            })


            error.observe(mActivity, {
                Log.e("TAG", "ERROR=== $it")
            })
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}