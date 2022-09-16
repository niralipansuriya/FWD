package com.swipefwd.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swipefwd.R
import com.swipefwd.databinding.ActivitySelectReligionBinding
import com.swipefwd.data.models.ReligionListModel
import com.swipefwd.data.models.ReligionModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openProfileFinishDialog
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class SelectReligionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectReligionBinding
    private val mActivity by lazy {
        this@SelectReligionActivity
    }
    var isSelectedAll = false
    private val adapter by lazy {
        SelectReligionAdapter()
    }
    private val multipleAdapter by lazy {
        SelectMultipleReligionAdapter(this@SelectReligionActivity) {
            if (!it) {
                isSelectedAll = false
                binding.selectAll.background = ContextCompat.getDrawable(
                    this@SelectReligionActivity,
                    R.drawable.grey_border_bg
                )
                binding.txtAll.setTextColor(
                    ContextCompat.getColor(
                        this@SelectReligionActivity,
                        R.color.black
                    )
                )
            } else {
                isSelectedAll = true
                binding.selectAll.background = ContextCompat.getDrawable(
                    this@SelectReligionActivity,
                    R.drawable.astro_gradient_enable_selector
                )
                binding.txtAll.setTextColor(
                    ContextCompat.getColor(
                        this@SelectReligionActivity,
                        R.color.white
                    )
                )
            }
        }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private val mDatabase by lazy {
        AppDatabase.getInstance(mActivity)
    }
    private val religionViewModel: SelectReligionViewModel by viewModels {
        viewModelFactory { SelectReligionViewModel(mActivity, AppRepository()) }
    }
    private var isFromSettings = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectReligionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("EditProfile")) {
            isFromSettings = intent.getBooleanExtra("EditProfile", false)
        }
        init()
    }

    private fun init() {
        binding.apply {
            when (AppConstants.SCREEN_NAME) {
                AppConstants.SCREEN_PROFILE -> {
                    selectAll.visibility = View.GONE
                    txtIndex.text = getString(R.string.sequence_profile, "6")
                    txtTitle.text = getString(R.string.religion_hint)
                    txtDate.visibility = View.GONE
                    rcvReligion.adapter = adapter
                }
                else -> {
                   // selectAll.visibility = View.VISIBLE
                    selectAll.visibility = View.GONE
                    txtIndex.text = getString(R.string.sequence_preference, "5")
                    txtTitle.text = getString(R.string.religion)
                  //  txtDate.visibility = View.VISIBLE
                    txtDate.visibility = View.GONE
                    rcvReligion.adapter = multipleAdapter
                }
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
//                        if (isFromSettings) {
//                            onBackPressed()
//                        } else {
                            mActivity.openProfileFinishDialog(getString(R.string.preference_finish_content))
//                        }
                    }
                }
            }
            txtSkip.setOnClickListener {
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        startActivity(
                            Intent(
                                mActivity,
                                SelectVaccineStatusActivity::class.java
                            ).putExtra("EditProfile", isFromSettings)
                        )
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    }
                    else -> {
                        nextActivity(SelectSmokeActivity::class.java)
                    }
                }
                finish()
            }
            txtSubmit.setOnClickListener {
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        adapter.selectedReligion()?.let{religion->
                        religionViewModel.saveReligion(Gson().toJson(religion))}
                    }
                    else -> {
                        multipleAdapter.allReligions()?.let{allReligions->
                            val selectedReligion = Gson().toJson(
                                allReligions.filter {
                                    it.isSelected
                                })
                            religionViewModel.saveMultipleReligion(selectedReligion)
                            allReligions.onEach {
                                CoroutineScope(Dispatchers.IO).launch {
                                    mDatabase.userProfileDao()
                                        .updateReligion(it._id ?: 0, it.value ?: "", it.isSelected)
                                }
                            }
                        }
                    }
                }
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        startActivity(
                            Intent(
                                mActivity,
                                SelectVaccineStatusActivity::class.java
                            ).putExtra("EditProfile", isFromSettings)
                        )
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    }
                    else -> {
                        nextActivity(SelectSmokeActivity::class.java)
                    }
                }
                finish()
            }
            selectAll.setOnClickListener {
                if (isSelectedAll) {
                    isSelectedAll = false
                    selectAll.background = ContextCompat.getDrawable(
                        this@SelectReligionActivity,
                        R.drawable.grey_border_bg
                    )
                    txtAll.setTextColor(
                        ContextCompat.getColor(
                            this@SelectReligionActivity,
                            R.color.black
                        )
                    )
                    multipleAdapter.selectAll(false)
                } else {
                    isSelectedAll = true
                    selectAll.background = ContextCompat.getDrawable(
                        this@SelectReligionActivity,
                        R.drawable.astro_gradient_enable_selector
                    )
                    txtAll.setTextColor(
                        ContextCompat.getColor(
                            this@SelectReligionActivity,
                            R.color.white
                        )
                    )
                    multipleAdapter.selectAll(true)
                }

            }
            var religionDbList: ArrayList<ReligionListModel.ReligionModel>
            lifecycleScope.launch {
                religionDbList =
                    mDatabase.userProfileDao()
                        .getAllReligion() as ArrayList<ReligionListModel.ReligionModel>
                if (religionDbList.isNotEmpty()) {
                    Log.e("printList",Gson().toJson(religionDbList))
                   // bindAdapter(religionDbList)
                } else {
                    getReligionList()
                    initObservers()
                }
            }
        }
    }

   // private fun bindAdapter(list: ArrayList<ReligionListModel.ReligionModel>) {
    private fun bindAdapter(list: ArrayList<ReligionModel.ReligionData.ReligionLevel>) {
        lifecycleScope.launch {
            when (AppConstants.SCREEN_NAME) {
                AppConstants.SCREEN_PROFILE -> {
                  //  val religion = religionViewModel.getReligion.firstOrNull()
                    val religion = AppUtils.getReligion(this@SelectReligionActivity)
                    if (!religion.isNullOrBlank()) {
                        val religionModel = Gson().fromJson<Any>(
                            religion,
                            ReligionModel.ReligionData.ReligionLevel::class.java
                        ) as ReligionModel.ReligionData.ReligionLevel
                        adapter.addAll(list)
                        adapter.setSelected(religionModel)
                    } else {
                        if (list.none { it.isSelected }) {
                            list.first().isSelected = true
                        }
                        adapter.addAll(list)
                    }
                }
                AppConstants.SCREEN_PREFERENCE ->{
                    val religion = AppUtils.getReligions(this@SelectReligionActivity)
                    if (!religion.isNullOrBlank()) {
                        val type: Type = object : TypeToken<java.util.ArrayList<ReligionModel.ReligionData.ReligionLevel>>() {}.type
                        val religionModel = Gson().fromJson(religion , type) as java.util.ArrayList<ReligionModel.ReligionData.ReligionLevel>
                        multipleAdapter.addAll(list)
                        multipleAdapter.setSelected(religionModel)
                    } else {
                        if (list.none { it.isSelected }) {
                            list.last().isSelected = true
                        }
                        multipleAdapter.addAll(list)
                    }
                }
                else -> {
                    val religion = AppUtils.getReligions(this@SelectReligionActivity)
                    if (!religion.isNullOrBlank()) {
                        val type: Type = object : TypeToken<java.util.ArrayList<ReligionModel.ReligionData.ReligionLevel>>() {}.type
                        val religionModel = Gson().fromJson(religion , type) as java.util.ArrayList<ReligionModel.ReligionData.ReligionLevel>
                        multipleAdapter.addAll(list)
                        multipleAdapter.setSelected(religionModel)
                    } else {
                        if (list.none { it.isSelected }) {
                            list.first().isSelected = true
                        }
                        multipleAdapter.addAll(list)
                    }
                }
            }
        }
    }

    private fun getReligionList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getReligionList()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getReligionList()
//                }
            }
            else -> {
                lifecycleScope.launch {

                    religionViewModel.religionDataListApi()
                   /* religionViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        religionViewModel.religionList(
                            "Bearer $authToken"
                        )
                    }*/
                }
            }
        }
    }

    private fun initObservers() {
        religionViewModel.apply {
            showLoading.observe(mActivity) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(mActivity) {
                Log.e("TAG" , "ERROR === $it")
            }
           /* data.observe(mActivity) { religionList ->
                mDatabase.userProfileDao().insertAllReligion(religionList)
                bindAdapter(religionList)
            }   */

            dataReligion.observe(mActivity) { religionList ->
               // mDatabase.userProfileDao().insertAllReligion(religionList)
                bindAdapter(religionList)
            }
            error.observe(mActivity) {
                Log.e("TAG" , "ERROR=== $it")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}