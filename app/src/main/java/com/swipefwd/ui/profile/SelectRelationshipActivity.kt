package com.swipefwd.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.swipefwd.R
import com.swipefwd.data.models.RelationshipModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivitySelectRelationshipBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.openProfileFinishDialog
import com.swipefwd.utils.ProgressBarHandler
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SelectRelationshipActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectRelationshipBinding
    private val mActivity by lazy {
        this@SelectRelationshipActivity
    }
    private val adapter by lazy {
        SelectRelationshipAdapter()
    }
    private val relationshipViewModel: SelectRelationshipViewModel by viewModels {
        viewModelFactory { SelectRelationshipViewModel(mActivity, AppRepository()) }
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
        binding = ActivitySelectRelationshipBinding.inflate(layoutInflater)
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
                    txtIndex.text = getString(R.string.sequence_profile, "9")
                    txtTitle.text = getString(R.string.looking_title)
                    getRelationshipList()
                }
                AppConstants.SCREEN_PREFERENCE -> {
                    txtIndex.text = getString(R.string.sequence_preference, "7")
                    txtTitle.text = getString(R.string.type_of_fwd_date)
                    getPreferenceRelationshipList()
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
                finish()
            }
            txtSubmit.setOnClickListener {
                adapter.selectedRelationship()?.let { relationship ->
                    when (AppConstants.SCREEN_NAME) {
                        AppConstants.SCREEN_PROFILE -> {
                            relationshipViewModel.saveRelationship(Gson().toJson(relationship))
                            finish()
                        }
                        else -> {
                            relationshipViewModel.savePrefRelationship(Gson().toJson(relationship))
                            finish()
                        }
                    }
                }
            }
            rcvDateType.adapter = adapter
            /*var relationshipDbList: ArrayList<RelationshipListModel.RelationshipModel>
            lifecycleScope.launch {
                relationshipDbList =
                    mDatabase.userProfileDao()
                        .getAllRelationship() as ArrayList<RelationshipListModel.RelationshipModel>
                if (relationshipDbList.isNotEmpty()) {
                    bindAdapter(relationshipDbList)
                } else {
                    initObservers()
                }
            }*/
            initObservers()
        }
    }

    private fun getRelationshipList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { getRelationshipList() }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getRelationshipList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    relationshipViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        /*relationshipViewModel.relationshipList(
                            "Bearer $authToken"
                        )*/
                        relationshipViewModel.relationshipDataListApi(
                        )
                    }
                }
            }
        }
    }

    private fun getPreferenceRelationshipList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { getPreferenceRelationshipList() }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getPreferenceRelationshipList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    relationshipViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        relationshipViewModel.relationshipDataListApi()
                    }
                }
            }
        }
    }

    // private fun bindAdapter(list: ArrayList<RelationshipListModel.RelationshipModel>) {
    private fun bindAdapter(list: ArrayList<RelationshipModel.RelationShipData.RelationshipLevel>) {
        lifecycleScope.launch {
            when (AppConstants.SCREEN_NAME) {
                AppConstants.SCREEN_PROFILE -> {
                  //  val relationship = relationshipViewModel.getRelationShip.firstOrNull() ?: ""
                    val relationship = AppUtils.getRelationShip(this@SelectRelationshipActivity)
                    setRelationshipData(relationship, false, list)
                }
                AppConstants.SCREEN_PREFERENCE->{
                    val relationship = AppUtils.getRelationShipPref(this@SelectRelationshipActivity)
                    setRelationshipData(relationship, true, list)
                }
                else -> {
                    val relationship = relationshipViewModel.getPrefRelationShip.firstOrNull() ?: ""
                    setRelationshipData(relationship, true, list)
                }
            }
        }
    }

    private fun setRelationshipData(
        relationShip: String,
        isPreference: Boolean,
        //   list: ArrayList<RelationshipListModel.RelationshipModel>
        list: ArrayList<RelationshipModel.RelationShipData.RelationshipLevel>
    ) {
        if (relationShip.isNotBlank()) {
            /* val relationShipModel = Gson().fromJson<Any>(
                 relationShip,
                 RelationshipListModel.RelationshipModel::class.java
             ) as RelationshipListModel.RelationshipModel */

            val relationShipModel = Gson().fromJson<Any>(
                relationShip,
                RelationshipModel.RelationShipData.RelationshipLevel::class.java
            ) as RelationshipModel.RelationShipData.RelationshipLevel
            adapter.addAll(list)
            adapter.setSelected(relationShipModel)
        } else {
            if (isPreference) {
                if (list.none { it.isSelected }) {
                    list.last().isSelected = true
                }
            } else {
                if (list.none { it.isSelected }) {
                    list.first().isSelected = true
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
    }

    private fun initObservers() {
        relationshipViewModel.apply {
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
            /*temp   data.observe(mActivity) { relationShipList ->
                   mDatabase.userProfileDao().insertAllRelationship(relationShipList)
                   bindAdapter(relationShipList)
               }
   */
            relationshipData.observe(mActivity)
            { relationList ->

                println("relationList------------------>>>>>>>>>>>>${relationList.toString()}")

                bindAdapter(relationList)

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