package com.swipefwd.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.swipefwd.R
import com.swipefwd.data.models.SmokingDataModel
import com.swipefwd.databinding.ActivitySelectSmokeBinding
import com.swipefwd.data.models.SmokingListModel
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

class SelectSmokeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectSmokeBinding
    private val mActivity by lazy {
        this@SelectSmokeActivity
    }
    private val adapter by lazy {
        SelectSmokingAdapter()
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private val mDatabase by lazy {
        AppDatabase.getInstance(mActivity)
    }
    private val smokingViewModel: SelectSmokingViewModel by viewModels {
        viewModelFactory { SelectSmokingViewModel(mActivity, AppRepository()) }
    }
    private var isFromSettings = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectSmokeBinding.inflate(layoutInflater)
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
                    txtIndex.text = getString(R.string.sequence_profile, "8")
                    txtTitle.text = getString(R.string.smoke_hint)
                }
                else -> {
                    txtIndex.text = getString(R.string.sequence_preference, "6")
                    txtTitle.text = getString(R.string.smoking)
                }
            }
            rcvSmoke.adapter = adapter
            txtSkip.setOnClickListener {
                startActivity(
                    Intent(
                        mActivity,
                        SelectRelationshipActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                finish()
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            txtSubmit.setOnClickListener {
                adapter.selectedSmoking()?.let{smoking->
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        smokingViewModel.saveSmoking(Gson().toJson(smoking))
                    }
                    else -> {
                        smokingViewModel.savePrefSmoking(Gson().toJson(smoking))
                    }
                }
                }
                startActivity(
                    Intent(
                        mActivity,
                        SelectRelationshipActivity::class.java
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
//                        if (isFromSettings) {
//                            onBackPressed()
//                        } else {
                            mActivity.openProfileFinishDialog(getString(R.string.preference_finish_content))
//                        }
                    }
                }
            }

            var smokingDbList: ArrayList<SmokingListModel.SmokingModel>
            lifecycleScope.launch {
                smokingDbList =
                    mDatabase.userProfileDao()
                        .getAllSmoking() as ArrayList<SmokingListModel.SmokingModel>
                if (smokingDbList.isNotEmpty()) {
                  //temp nirali  bindAdapter(smokingDbList)
                } else {
                    getSmokingList()
                    initObservers()
                }
            }
        }
    }

    private fun getSmokingList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getSmokingList()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getSmokingList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    smokingViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                       /* smokingViewModel.smokingList(
                            "Bearer $authToken"
                        )*/
                        smokingViewModel.getSmokingListApi(
                        )
                    }
                }
            }
        }
    }

    private fun initObservers() {
        smokingViewModel.apply {
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
     /*       data.observe(mActivity) { smokingList ->
                var temp = SmokingListModel.SmokingModel()
                val newList = ArrayList<SmokingListModel.SmokingModel>()
                smokingList.forEachIndexed { index, smokingModel ->
                    if (smokingModel.name != "No preferences"){
                        newList.add(smokingModel)
                    }
                    else{
                        temp = smokingModel
                    }
                  }
                newList.add(temp)
              //  bindAdapter(newList)
                mDatabase.userProfileDao().insertAllSmoking(smokingList)
            }*/

            smokingData.observe(mActivity) { smokingList ->
                println("smokingList from api-------->>>>>${smokingList}")
                var temp = SmokingDataModel.SmokingData.Smoking()

                val newList = ArrayList<SmokingDataModel.SmokingData.Smoking>()
                smokingList.forEachIndexed { index, smokingModel ->
                    if (smokingModel.value != "No preferences"){
                        newList.add(smokingModel)
                    }
                    else{
                        temp = smokingModel
                    }
                  }
                println("smokingList temp from api-------->>>>>${temp.toString()}")

             //temp   newList.add(temp)
                println("smokingList newlist from api-------->>>>>${newList.toString()}")

                bindAdapter(newList)

                //  mDatabase.userProfileDao().insertAllSmoking(smokingList)
            }
            error.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
            }
        }
    }

   // private fun bindAdapter(list: ArrayList<SmokingListModel.SmokingModel>) {
    private fun bindAdapter(list: ArrayList<SmokingDataModel.SmokingData.Smoking>) {
      lifecycleScope.launch {
            when (AppConstants.SCREEN_NAME) {
                AppConstants.SCREEN_PROFILE -> {
                    //val smoke = smokingViewModel.getSmoke.firstOrNull() ?: ""
                    val smoke = AppUtils.getSmoking(this@SelectSmokeActivity)
                    setSmokeData(smoke, false, list)
                }
                AppConstants.SCREEN_PREFERENCE -> {
                    val smoke = AppUtils.getSmokingPref(this@SelectSmokeActivity)
                    setSmokeData(smoke, true, list)
                }
                else ->
                {
                    val smoke = AppUtils.getSmokingPref(this@SelectSmokeActivity)
                    setSmokeData(smoke, true, list)
                }
            }
        }
    }

    private fun setSmokeData(
        smoke: String, isPreference: Boolean,
        //list: ArrayList<SmokingListModel.SmokingModel>
        list: ArrayList<SmokingDataModel.SmokingData.Smoking>
    ) {
        if (smoke.isNotBlank()) {
        /*    val smokeModel = Gson().fromJson<Any>(
                smoke,
                SmokingListModel.SmokingModel::class.java
            ) as SmokingListModel.SmokingModel
            */
            val smokeModel = Gson().fromJson<Any>(
                smoke,
                SmokingDataModel.SmokingData.Smoking::class.java
            ) as SmokingDataModel.SmokingData.Smoking
            /*list.last().name = if (isPreference) {
                "No preference"
            } else {
                "Prefer not to say"
            }*/
         /* temp  if (isPreference &&  list.last().value == "No preferences"){
                list.last().value = "No preference"
            }
            else if (list.last().value == "No preferences"){
                list.last().value = "Prefer not to say"
            }*/
            adapter.apply {
                addAll(list)
                setSelected(smokeModel)
            }
        }
        else {
            if (isPreference) {
                if (list.none { it.isSelected }) {
                    /*list.last().apply {
                        isSelected = true
                        name = "No preference"
                    }*/
                   /*temp if (list.last().value == "No preferences"){
                        list.last().value = "No preference"
                    }*/
                }
            } else {
                if (list.none { it.isSelected }) {
                  //  list.first().isSelected = true
                    /*list.last().apply {
                        name = "Prefer not to say"
                    }*/
                   /*temp  if (list.last().value == "No preferences"){
                        list.last().value = "Prefer not to say"
                    }*/
                }
            }
            adapter.addAll(list)

            adapter.selectedPos = if (isPreference) {
                list.size - 1
            } else {
                0
            }
            adapter.notifyDataSetChanged()
        }

        println("smoking list---------->>>>>${list.toString()}")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}