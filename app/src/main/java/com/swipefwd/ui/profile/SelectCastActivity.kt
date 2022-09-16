package com.swipefwd.ui.profile

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.swipefwd.R
import com.swipefwd.databinding.ActivitySelectCastBinding
import com.swipefwd.data.models.CastListModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.hideKeyboard
import com.swipefwd.utils.AppUtils.openProfileFinishDialog
import com.swipefwd.utils.AppUtils.showKeyboard
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SelectCastActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectCastBinding
    private var selectedCast = CastListModel.CastModel()
    private val mActivity by lazy {
        this@SelectCastActivity
    }
    private var editMode = true
    private val adapter by lazy {
        SelectCastAdapter {
            editMode = false
            selectedCast = it
            binding.apply {
                edtCast.isCursorVisible = false
                edtCast.setText(it.name, TextView.BufferType.NORMAL)
                edtCast.setBackgroundResource(R.drawable.corner_blue_border_bg)
            }
        }
    }
    private val castViewModel: SelectCastViewModel by viewModels {
        viewModelFactory { SelectCastViewModel(mActivity, AppRepository()) }
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
        binding = ActivitySelectCastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("EditProfile")) {
            isFromSettings = intent.getBooleanExtra("EditProfile", false)
        }
        init()
    }

    private fun init() {
        binding.apply {
            txtIndex.text = getString(R.string.sequence_profile, "5")
            rcvCast.adapter = adapter
            ivClose.setOnClickListener {
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        if (isFromSettings) {
                            onBackPressed()
                        } else {
                            mActivity.openProfileFinishDialog()
                        }
                    }
                    AppConstants.SCREEN_PREFERENCE -> {
                        onBackPressed()
                    }
                }
            }
            txtSkip.setOnClickListener {
                startActivity(
                    Intent(
                        mActivity,
                        SelectChildrenPlanActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                finish()
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            edtCast.apply {
                this.showKeyboard()
                setOnClickListener {
                    editMode = true
                    edtCast.isCursorVisible = true
                }
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        txtSubmit.performClick()
                        true
                    }
                    false
                }
                addTextChangedListener(object : TextWatcher {
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
                            edtCast.setBackgroundResource(R.drawable.grey_border_bg)
                            cardList.visibility = View.GONE
                        }
                    }

                    override fun afterTextChanged(s: Editable?) {
                        if (s.isNullOrBlank() || !editMode) {
                            cardList.visibility = View.GONE
                        } else {
                            cardList.visibility = View.VISIBLE
                            adapter.search(s.toString()) {
                                // do nothing
                            }
                        }
                    }

                })
            }
            txtSubmit.setOnClickListener {
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        castViewModel.saveCast(Gson().toJson(selectedCast))
                    }
                }
                startActivity(
                    Intent(
                        mActivity,
                        SelectChildrenPlanActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                finish()
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            var castDbList: ArrayList<CastListModel.CastModel>
            lifecycleScope.launch {
                castDbList =
                    mDatabase.userProfileDao().getAllCast() as ArrayList<CastListModel.CastModel>
                if (castDbList.isNotEmpty()) {
                    bindAdapter(castDbList)
                } else {
                    getCastList()
                    initObservers()
                }

                val cast = castViewModel.getCast.firstOrNull()
                if (!cast.isNullOrBlank()) {
                    val castModel = Gson().fromJson<Any>(
                        cast,
                        CastListModel.CastModel::class.java
                    ) as CastListModel.CastModel
                    edtCast.setText(castModel.name)
                    binding.txtSubmit.isEnabled = true
                    binding.txtSubmit.isClickable = true
                }
            }
        }
    }

    private fun getCastList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getCastList()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getCastList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    castViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        castViewModel.castList(
                            "Bearer $authToken"
                        )
                    }
                }
            }
        }
    }

    private fun initObservers() {
        castViewModel.apply {
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
            data.observe(mActivity, { castList ->
                bindAdapter(castList)
                CoroutineScope(Dispatchers.IO).launch {
                    mDatabase.userProfileDao().insertAllCast(castList)
                }
            })
            error.observe(mActivity, {
                Log.e("TAG", "ERROR=== $it")
            })
        }
    }

    private fun bindAdapter(list: ArrayList<CastListModel.CastModel>) {
        adapter.addAll(list)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.ivClose.hideKeyboard()
        finish()
    }
}