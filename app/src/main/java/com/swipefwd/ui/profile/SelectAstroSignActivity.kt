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
import com.swipefwd.databinding.ActivitySelectAstroSignBinding
import com.swipefwd.data.models.AstroSignListModel
import com.swipefwd.data.models.AstrologicalModel
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

class SelectAstroSignActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectAstroSignBinding
    private val mActivity by lazy {
        this@SelectAstroSignActivity
    }
    private val adapter by lazy {
        SelectAstroSignAdapter(mActivity)
    }
    private val multipleAdapter by lazy {
        SelectMultipleAstroSignAdapter(mActivity){

            if(!it)
            {
                isSelectedAll=false
                binding.selectAll.background = ContextCompat.getDrawable(
                    this@SelectAstroSignActivity,
                    R.drawable.grey_border_bg
                )
                binding.txtAll.setTextColor(ContextCompat.getColor(this@SelectAstroSignActivity,R.color.black))
            }else{
                isSelectedAll=true
                binding.selectAll.background = ContextCompat.getDrawable(
                    this@SelectAstroSignActivity,
                    R.drawable.astro_gradient_enable_selector
                )
                binding.txtAll.setTextColor(ContextCompat.getColor(this@SelectAstroSignActivity,R.color.white))
            }
        }
    }
    private val astroSignViewModel: SelectAstroSignViewModel by viewModels {
        viewModelFactory { SelectAstroSignViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private val mDatabase by lazy {
        AppDatabase.getInstance(mActivity)
    }
    private var isFromSettings = false

    var isSelectedAll = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectAstroSignBinding.inflate(layoutInflater)
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
                    txtIndex.text = getString(R.string.sequence_profile, "4")
                    txtTitle.text = getString(R.string.astro_title)
                    rcvAstroSign.adapter = adapter
                    txtDate.visibility = View.GONE
                    selectAll.visibility = View.GONE
                }
                else -> {
                    txtDate.visibility = View.VISIBLE
                    selectAll.visibility = View.VISIBLE
                    txtIndex.text = getString(R.string.sequence_preference, "3")
                    txtTitle.text = getString(R.string.astrological_sign)
                    rcvAstroSign.adapter = multipleAdapter
                }
            }
            selectAll.setOnClickListener {
                if (isSelectedAll) {
                    isSelectedAll=false
                    selectAll.background = ContextCompat.getDrawable(
                        this@SelectAstroSignActivity,
                        R.drawable.grey_border_bg
                    )
                    txtAll.setTextColor(ContextCompat.getColor(this@SelectAstroSignActivity,R.color.black))
                    multipleAdapter.selectAll(false)
                } else {
                    isSelectedAll=true
                    selectAll.background = ContextCompat.getDrawable(
                        this@SelectAstroSignActivity,
                        R.drawable.astro_gradient_enable_selector
                    )
                    txtAll.setTextColor(ContextCompat.getColor(this@SelectAstroSignActivity,R.color.white))
                    multipleAdapter.selectAll(true)
                }

            }
            txtSkip.setOnClickListener {
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        startActivity(
                            Intent(
                                mActivity,
                                SelectChildrenPlanActivity::class.java
                            ).putExtra("EditProfile", isFromSettings)
                        )
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    }
                    else -> {
                        nextActivity(SelectChildrenPlanActivity::class.java)
                    }
                }
                finish()
            }
            txtSubmit.setOnClickListener {
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        adapter.selectedSign()?.let{sign->
                            astroSignViewModel.saveAstroSign(Gson().toJson(sign))
                        }
                        startActivity(
                            Intent(
                                mActivity,
                                SelectChildrenPlanActivity::class.java
                            ).putExtra("EditProfile", isFromSettings)
                        )
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    }
                    else -> {
                        val selectedSign = Gson().toJson(
                            multipleAdapter.allSigns().filter {
                                it.isSelected
                            })
                        astroSignViewModel.saveMultipleAstroSign(selectedSign)
                        multipleAdapter.allSigns().onEach {
                            CoroutineScope(Dispatchers.IO).launch {
                                mDatabase.userProfileDao()
                                    .updateSign(it._id ?: 0, it.value ?: "", it.isSelected)
                            }
                        }
                        nextActivity(SelectChildrenPlanActivity::class.java)
                    }
                }
                finish()
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
            var astroDbList: ArrayList<AstroSignListModel.AstroSignModel>
            lifecycleScope.launch {
                astroDbList =
                    mDatabase.userProfileDao()
                        .getAllAstroSign() as ArrayList<AstroSignListModel.AstroSignModel>
                if (astroDbList.isNotEmpty()) {
                   // bindAdapter(astroDbList)
                } else {
                    getAstroSignList()
                    initObservers()
                }
            }
        }
    }

  //  private fun bindAdapter(list: ArrayList<AstroSignListModel.AstroSignModel>) {
    private fun bindAdapter(list: ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>) {
        lifecycleScope.launch {
            when (AppConstants.SCREEN_NAME) {
                AppConstants.SCREEN_PROFILE -> {
                    adapter.addAll(list)
                   // val astrology = astroSignViewModel.getSign.firstOrNull()
                    val astrology = AppUtils.getAstrologicalSign(this@SelectAstroSignActivity)
                    if (!astrology.isNullOrBlank()) {
                        val astrologyModel = Gson().fromJson<Any>(
                            astrology,
                            AstrologicalModel.AstrologicalData.AstrologicalSignLevel::class.java
                        ) as AstrologicalModel.AstrologicalData.AstrologicalSignLevel
                        adapter.setSelected(astrologyModel)
                    } else {
                        if (list.none { it.isSelected }) {
                            list.first().isSelected = true
                        }
                    }
                }
                AppConstants.SCREEN_PREFERENCE->{
                    multipleAdapter.addAll(list)
                    // val astrology = astroSignViewModel.getSign.firstOrNull()
                    val astrology = AppUtils.getAstrologicalSigns(this@SelectAstroSignActivity)
                    if (!astrology.isNullOrBlank()) {
                        val type: Type =
                            object : TypeToken<java.util.ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>>() {}.type
                        val astrologyModel = Gson().fromJson(astrology , type) as java.util.ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>
                        multipleAdapter.setSelected(astrologyModel)
                    } else {
                        if (list.none { it.isSelected }) {
                            list.first().isSelected = true
                        }
                    }
                }
                else -> {
                    multipleAdapter.addAll(list)
                    val astrology = AppUtils.getAstrologicalSigns(this@SelectAstroSignActivity)
                    if (!astrology.isNullOrBlank()) {
                        val type: Type =
                            object : TypeToken<java.util.ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>>() {}.type
                        val astrologyModel = Gson().fromJson(astrology , type) as java.util.ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>
                        multipleAdapter.setSelected(astrologyModel)
                    } else {
                        if (list.none { it.isSelected }) {
                            list.first().isSelected = true
                        }
                    }
                }
            }

        }
    }

    private fun getAstroSignList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
              openFailNetworkDialog(){getAstroSignList()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getAstroSignList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                /*    astroSignViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        astroSignViewModel.astroSignList(
                            "Bearer $authToken"
                        )
                    }*/
                    astroSignViewModel.astrologicalApi()
                }
            }
        }
    }

    private fun initObservers() {
        astroSignViewModel.apply {
            showLoading.observe(mActivity) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(mActivity) {
                Log.e("TAG", "ERROR === $it")
            }
            /*data.observe(mActivity) { signList ->
                signList.onEach {
                    it.name = it.name?.replaceFirstChar { char ->
                        char.uppercase()
                    }
                }.let { list ->
                    CoroutineScope(Dispatchers.IO).launch {
                        mDatabase.userProfileDao().insertAllSigns(list)
                    }
                    bindAdapter(list)
                }
            } */
            dataAstrological.observe(mActivity) { signList ->
                signList.onEach {
                    it.value = it.value?.replaceFirstChar { char ->
                        char.uppercase()
                    }
                }.let { list ->
                    CoroutineScope(Dispatchers.IO).launch {
                      //  mDatabase.userProfileDao().insertAllSigns(list)
                    }
                    bindAdapter(list)
                }
            }
            error.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}