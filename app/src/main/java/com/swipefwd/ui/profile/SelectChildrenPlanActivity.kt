package com.swipefwd.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.swipefwd.R
import com.swipefwd.databinding.ActivitySelectChildrenPlanBinding
import com.swipefwd.data.models.ChildrenListModel
import com.swipefwd.data.models.ChildrenModel
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

class SelectChildrenPlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectChildrenPlanBinding
    private val mActivity by lazy {
        this@SelectChildrenPlanActivity
    }
    private lateinit var adapter: SelectChildrenAdapter
    private val childrenViewModel: SelectChildrenViewModel by viewModels {
        viewModelFactory { SelectChildrenViewModel(mActivity, AppRepository()) }
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
        binding = ActivitySelectChildrenPlanBinding.inflate(layoutInflater)
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
                    txtIndex.text = getString(R.string.sequence_profile, "5")
                    txtTitle.text = getString(R.string.children_hint)
                    adapter = SelectChildrenAdapter()
                }
                else -> {
                    txtIndex.text = getString(R.string.sequence_preference, "4")
                    txtTitle.text = getString(R.string.children)
                    adapter = SelectChildrenAdapter()
                }
            }
            rcvChildrenPlan.adapter = adapter
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
                startActivity(
                    Intent(
                        mActivity,
                        SelectReligionActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                finish()
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            txtSubmit.setOnClickListener {
                adapter.selectedChildrenPlan()?.let {childPlan->
                    when (AppConstants.SCREEN_NAME) {
                        AppConstants.SCREEN_PROFILE -> {
                            childrenViewModel.saveChildren(Gson().toJson(childPlan))
                        }
                        else -> {
                            childrenViewModel.savePrefChildren(Gson().toJson(childPlan))
                        }
                    }
                }
                startActivity(
                    Intent(
                        mActivity,
                        SelectReligionActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                finish()
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            var childrenDbList: ArrayList<ChildrenListModel.ChildrenModel>
            lifecycleScope.launch {
                childrenDbList = mDatabase.userProfileDao().getAllChildren() as ArrayList<ChildrenListModel.ChildrenModel>
                if (childrenDbList.isNotEmpty()) {
                  //  bindAdapter(childrenDbList)
                } else {
                    getChildrenList()
                    initObservers()
                }
            }
        }
    }

    private fun getChildrenList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getChildrenList()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getChildrenList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                   /* childrenViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        childrenViewModel.childrenList(
                            "Bearer $authToken"
                        )
                    }*/

                    childrenViewModel.childrenApi()
                }
            }
        }
    }

    private fun initObservers() {
        childrenViewModel.apply {
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
         /*   data.observe(mActivity, { childList ->
                bindAdapter(childList)
                mDatabase.userProfileDao().insertAllChildren(childList)
            })   */

            dataChildren.observe(mActivity, { childList ->
                bindAdapter(childList)
             //   mDatabase.userProfileDao().insertAllChildren(childList)
            })
            error.observe(mActivity, {
                Log.e("TAG", "ERROR=== $it")
            })
        }
    }

   // private fun bindAdapter(list: ArrayList<ChildrenListModel.ChildrenModel>) {
    private fun bindAdapter(list: ArrayList<ChildrenModel.ChildrenData.ChildrenLevel>) {
        lifecycleScope.launch {
            when (AppConstants.SCREEN_NAME) {
                AppConstants.SCREEN_PROFILE -> {
                 //   val child = childrenViewModel.getChildren.firstOrNull() ?: ""
                    val child = AppUtils.getChildren(this@SelectChildrenPlanActivity)
                    setChildData(child, false, list)
                }
                AppConstants.SCREEN_PREFERENCE -> {
                    val child = AppUtils.getChildrenPref(this@SelectChildrenPlanActivity)
                    setChildData(child, true, list)
                }
            }

        }
    }

    private fun setChildData(
        child: String,
        isPreference: Boolean,
        list: ArrayList<ChildrenModel.ChildrenData.ChildrenLevel>
    ) {
        if (child.isNotBlank()) {
            val childModel = Gson().fromJson<Any>(
                child,
                ChildrenModel.ChildrenData.ChildrenLevel::class.java
            ) as ChildrenModel.ChildrenData.ChildrenLevel
            adapter.apply {
               /* list.last().value = if (isPreference) {
                    "No preference"
                } else {
                    "Prefer not to say"
                }*/
                addAll(list)
                setSelected(childModel)
            }
        } else {
            if (isPreference) {
                if (list.none { it.isSelected }) {
                   /* list.last().apply {
                        isSelected = true
                        value = "No preference"
                    }*/
                }
            } else {
                if (list.none { it.isSelected }) {
                    list.first().isSelected = true
                  /*  list.last().apply {
                        value = "Prefer not to say"
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
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}