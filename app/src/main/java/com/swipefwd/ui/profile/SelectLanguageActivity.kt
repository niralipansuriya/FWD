package com.swipefwd.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.swipefwd.R
import com.swipefwd.data.models.LanguageDataModel
import com.swipefwd.databinding.ActivitySelectLanguageBinding
import com.swipefwd.data.models.LanguageListModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openProfileFinishDialog
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.showKeyboard
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class SelectLanguageActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectLanguageBinding
    private val mActivity by lazy {
        this@SelectLanguageActivity
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private val mDatabase by lazy {
        AppDatabase.getInstance(mActivity)
    }
    private var selectedLanguageList = ArrayList<LanguageDataModel.LanguageData.Language>()
    private val adapter by lazy {
        SelectLanguageAdapter(listener = { languageModel ->
            if (selectedLanguageList.isNotEmpty()) {
                selectedLanguageList.any {
                    it._id == languageModel._id
                }.let {
                    if (it) {
                        binding.txtLanguageName.setText("")
                        binding.cardList.visibility = View.GONE
                    } else {
                        selectedLanguageList.add(languageModel)
                        updateListForSelectedLanguages(languageModel)
                    }
                }
            } else {
                selectedLanguageList.add(languageModel)
                updateListForSelectedLanguages(languageModel)
            }
        })
    }
    private val selectedLanguagesAdapter by lazy {
        SelectedLanguagesAdapter(removeListener = {
            //remove task here
            selectedLanguageList.remove(it)
        })
    }
    private val languageViewModel: SelectLanguageViewModel by viewModels {
        viewModelFactory { SelectLanguageViewModel(mActivity, AppRepository()) }
    }
    private var isFromSettings = false
    private var isAddProfile = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("EditProfile")) {
            isFromSettings = intent.getBooleanExtra("EditProfile", false)
        }
       if (intent.hasExtra("AddProfile")) {
           isAddProfile = intent.getBooleanExtra("AddProfile", false)
        }
        init()
    }

    private fun init() {
        binding.apply {
            when (AppConstants.SCREEN_NAME) {
                AppConstants.SCREEN_PROFILE -> {
                    txtIndex.text = getString(R.string.sequence_profile, "1")
                    txtTitle.text = getString(R.string.language_hint)
                    preferenceLanguage.isVisible = false
                }
                else -> {
                    txtIndex.text = getString(R.string.sequence_preference, "1")
                    txtTitle.text = getString(R.string.language)
                    preferenceLanguage.isVisible = true
                }
            }
            txtLanguageName.apply {
                this.showKeyboard()
                setOnClickListener {
                    txtLanguageName.isCursorVisible = true
                }
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        AppUtils.hideUnderline(s)
                        if (s.isNullOrBlank()) {
                            cardList.visibility = View.GONE
                        } else {
                            cardList.visibility = View.VISIBLE
                            adapter.search(s.toString()) {
                                // do nothing
                            }
                        }
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
                            txtLanguageName.setBackgroundResource(R.drawable.grey_border_bg)
                            cardList.visibility = View.GONE
                        }
                    }
                })
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
            var languageDbList: ArrayList<LanguageListModel.LanguageModel>
            lifecycleScope.launch {
                languageDbList =
                    mDatabase.userProfileDao()
                        .getAllLanguage() as ArrayList<LanguageListModel.LanguageModel>
                if (languageDbList.isNotEmpty()) {
                    //bindAdapter(languageDbList)
                } else {
                    getLanguageList()
                    initObservers()
                }
            }
            rvLanguage.adapter = adapter
            layoutSelectedLanguage.adapter = selectedLanguagesAdapter
            txtSubmit.setOnClickListener {
//                if (!selectedLanguageList.isNullOrEmpty()){
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        languageViewModel.saveLanguage(
                            Gson().toJson(selectedLanguageList)
                        )
                        startActivity(
                            Intent(
                                mActivity,
                                SelectOccupationActivity::class.java
                            ).putExtra("EditProfile", isFromSettings)
                        )
                        finish()
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    }
                    AppConstants.SCREEN_PREFERENCE -> {
                        languageViewModel.savePrefLanguage(Gson().toJson(selectedLanguageList))
                        nextActivity(SelectSchoolActivity::class.java)
//                        finish()
                    }
                }
//                }
            }
            txtSkip.setOnClickListener {
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        startActivity(
                            Intent(
                                mActivity,
                                SelectOccupationActivity::class.java
                            ).putExtra("EditProfile", isFromSettings)
                        )
                        finish()
                    }
                    AppConstants.SCREEN_PREFERENCE -> {
                        nextActivity(SelectSchoolActivity::class.java)
//                        finish()
                    }
                }
            }
            txtLanguageName.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    txtSubmit.performClick()
                    true
                }
                false
            }
        }
    }

    fun Activity.nextActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
        finish()
        overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
    }

    private fun updateListForSelectedLanguages(model: LanguageDataModel.LanguageData.Language) {
        selectedLanguagesAdapter.addLanguage(model)
        binding.apply {
            txtLanguageName.setText("")
            if (selectedLanguageList.isNotEmpty()) {
                txtSubmit.isEnabled = true
                txtSubmit.isClickable = true
            } else {
                txtSubmit.isEnabled = false
                txtSubmit.isClickable = false
            }
        }
    }

    private fun getLanguageList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getLanguageList()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getLanguageList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    languageViewModel.languageListApi()
                   /* languageViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        languageViewModel.languageList("Bearer $authToken")
                    }*/
                }
            }
        }
    }

    private fun initObservers() {
        languageViewModel.apply {
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
         /*   data.observe(mActivity) { languageList ->
                lifecycleScope.launch {
                    bindAdapter(languageList)
                    mDatabase.userProfileDao().insertAllLanguage(languageList)
                }
            } */

            dataLanguage.observe(mActivity) { languageList ->
                lifecycleScope.launch {
                    bindAdapter(languageList)
                   // mDatabase.userProfileDao().insertAllLanguage(languageList)
                }
            }
            error.observe(mActivity) {
                Log.e("TAG" , "ERROR=== $it")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        selectedLanguageList.clear()
        finish()
    }

    private fun bindAdapter(list: ArrayList<LanguageDataModel.LanguageData.Language>) {
        adapter.addAll(list)
        lifecycleScope.launch {
            val language = when (AppConstants.SCREEN_NAME) {
                AppConstants.SCREEN_PROFILE -> {
                   // languageViewModel.getLanguage.firstOrNull()
                    AppUtils.getLanguage(this@SelectLanguageActivity)
                }
                AppConstants.SCREEN_PREFERENCE->{
                    AppUtils.getLanguagePref(this@SelectLanguageActivity)
                }
                else -> {
                    languageViewModel.getPrefLanguage.firstOrNull()
                }
            }
            if (!language.isNullOrBlank()) {
                val type: Type =
                    object : TypeToken<List<LanguageDataModel.LanguageData.Language?>?>() {}.type
                selectedLanguageList =
                    Gson().fromJson(language, type) as ArrayList<LanguageDataModel.LanguageData.Language>
                Log.d("TAG:", "languages: == $selectedLanguageList")
                selectedLanguageList.onEach {
                    updateListForSelectedLanguages(it)
                }
            }
        }
    }
}