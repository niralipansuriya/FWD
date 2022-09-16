package com.swipefwd.ui.profile

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.swipefwd.R
import com.swipefwd.databinding.ActivityPreferencesBinding
import com.swipefwd.data.models.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.DialogProfileImageBinding
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.updateuserprofile.UserProfileActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.crystalRangeSeekbar.interfaces.OnRangeSeekbarChangeListener
import com.swipefwd.utils.crystalRangeSeekbar.interfaces.OnSeekbarChangeListener
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


class PreferencesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesBinding
    private var userGender = 2
    private val mActivity by lazy {
        this@PreferencesActivity
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private val prefViewModel: AdvancePreferenceViewModel by viewModels {
        viewModelFactory { AdvancePreferenceViewModel(mActivity, AppRepository()) }
    }
    private var startAge = 18
    private var defaultAge = 75
    private var endAge = 99
    private var maxDistance = 160
    private var isVerifiedProfile = false
    private var convertedStartHeight = 0 //4'0" ==> (4x12) + 0
    private var convertedEndHeight = 0 //6'9" ==> (6x12) + 9
    private var educationModel = EducationModel()
//    private var childrenModel = ChildrenListModel.ChildrenModel()
//    private var smokingModel = SmokingListModel.SmokingModel()
//    private var relationshipModel = RelationshipListModel.RelationshipModel()
    private var isInCM = false
    private var isFromSettings = false
    private var isContinueClick = false
    private var startForResult: ActivityResultLauncher<Intent>? = null
    private var languagesList = java.util.ArrayList<LanguageDataModel.LanguageData.Language>()

    //multiple data
//    private var languagesList = ArrayList<LanguageListModel.LanguageModel>()
    private var castModel = ArrayList<CastListModel.CastModel>()
   /* private var religionModel = ArrayList<ReligionListModel.ReligionModel>()
    private var astrologyModel = ArrayList<AstroSignListModel.AstroSignModel>()*/
    private val dialogs: Vector<Dialog> = Vector<Dialog>()

    private var childrenModel = ChildrenModel.ChildrenData.ChildrenLevel()
    private var religionModel = java.util.ArrayList<ReligionModel.ReligionData.ReligionLevel>()
    private var smokingModel = SmokingDataModel.SmokingData.Smoking()
    private var relationshipModel = RelationshipModel.RelationShipData.RelationshipLevel()
    private var vaccineStatusModel = VaccinationModel.VaccinationData.VaccinationLevel()
    private var astrologyModel =
        java.util.ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("EditProfile")) {
            isFromSettings = intent.getBooleanExtra("EditProfile", false)
        }
        ///////////////////////////////////////////////////////////////////////////////////////////
        init()
        initObservers()
    }

    private fun init() {
     //   binding.btnSegmentGender.selectionAnimationDuration = 10
     //   applyFontFamily()
        binding.apply {
            if (isFromSettings) {
                prefViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
            } else {
                prefViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "3")
            }
            startForResult =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        if (isFromSettings)
                            onBackPressed()
                    }
                }
            labelPrefLocation.text =
                Html.fromHtml("<sup>†</sup>You and your Connectors (if you add them) get<br> </br>FWD profiles based on your selected Preferences")

            val span = SpannableString(labelPrefLocation.text)
            span.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.orange_gradient_1,
                        theme
                    )
                ),
                0,
                1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            labelPrefLocation.setText(span, TextView.BufferType.SPANNABLE)
            mActivity.setShaderView(
                btnAdvancedFilters,
                R.color.blue_gradient_1,
                R.color.blue_gradient_6
            )

            ivBack.setOnClickListener {
                if (isFromSettings) {
                    btnContinue.performClick()
                } else {
                    onBackPressed()
                }
            }
            ageRangeSeekbar.apply {
                Log.d("TAG:", "apply:")
                startAge = 18
                endAge = 99
                setOnRangeSeekbarChangeListener { minValues, maxValues ->
                    val values = arrayOf(minValues, maxValues)
                    val minValue = values[0].toString().split(".")[0].toInt()
                    val maxValue = values[1].toString().split(".")[0].toInt()
                    txtAgeRange.text = resources.getString(
                        R.string.between_values,
                        minValue.toString(),
                        maxValue.toString()
                    )
                    startAge = minValue
                    endAge = maxValue
//                    prefViewModel.savePreference(PreferenceKeys.PREF_START_AGE, startAge)
                    AppUtils.storeStartAgePref(this@PreferencesActivity, startAge)
//                    prefViewModel.savePreference(PreferenceKeys.PREF_END_AGE, endAge)
                    AppUtils.storeEndAgePref(this@PreferencesActivity, endAge)
                }
            }

            seekDistance.apply {
                maxDistance = 160
                minStartValue = 25f
                apply()
                setOnSeekbarChangeListener { minValues ->
                    val distance = minValues.toString().split(".")[0].toInt()
                    Log.d("TAG:", "distance: $distance")
                    /*if (distance < 10) {
                                    setValue(10f)
                                    distance = 10
                                }*/
                    txtDistance.text =
                        resources.getString(R.string.up_to_distance, distance.toString())
                    maxDistance = distance
//                    prefViewModel.savePreference(PreferenceKeys.PREF_MAX_DISTANCE, maxDistance)
                    AppUtils.storeDistance(this@PreferencesActivity, maxDistance)
                }
            }
            btnAdvancedFilters.setOnClickListener {
                startForResult?.launch(
                    Intent(
                        mActivity,
                        AdvancePreferenceActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                /*startActivity(
                    Intent(
                        mActivity,
                        AdvancePreferenceActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )*/
            }
            btnContinue.setOnClickListener {
                lifecycleScope.launch {
                    submitUpdatePreference(
                        prefViewModel.getPreferenceSubmitted.firstOrNull() ?: false
                    )
                }
            }
            //gender selection
            userGender = btnSegmentGender.position
            applyFontFamily(btnSegmentGender.position+1)
            btnSegmentGender.onPositionChangedListener =
                SegmentedButtonGroup.OnPositionChangedListener {
                    applyFontFamily(it+1)
                    userGender = it
//                    prefViewModel.savePreference(PreferenceKeys.PREF_PREF_GENDER, userGender)
                   AppUtils.storeGenderPref(this@PreferencesActivity, it)
                }
        }
    }

    private fun submitUpdatePreference(isUpdate: Boolean) {
        val gender =  when (userGender) {
            0 -> 1
            1 -> 2
            2 -> 3
            else -> 3
        }
        val languageIds = languagesList.map { it.value }
        val signIds = astrologyModel.map { it.value }
        val castIds = castModel.map { it.id }
        val religionIds = religionModel.map { it.value }
        val languageArray = JsonArray()
        languageIds.forEach {
            languageArray.add(it)
        }
        val signArray = JsonArray()
        signIds.forEach {
            signArray.add(it)
        }
        val castArray = JsonArray()
        castIds.forEach {
            castArray.add(it)
        }
        val religionArray = JsonArray()
        religionIds.forEach {
            religionArray.add(it)
        }
        val isHeightInFeet = if (!isInCM) {
            AppConstants.HEIGHT_FEET
        } else {
            AppConstants.HEIGHT_CM
        }
        val apiRequest = JsonObject().apply {
            try {
                addProperty("gender", gender)
//                if (startAge != 0)
                    addProperty("min_age", startAge)
//                if (endAge != 0)
                    addProperty("max_age", endAge)
//                if (maxDistance != 0)
                    addProperty("distance", maxDistance)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){
                    lifecycleScope.launch {
                        submitUpdatePreference(
                            prefViewModel.getPreferenceSubmitted.firstOrNull() ?: false
                        )
                    }
                }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    lifecycleScope.launch {
//                        submitUpdatePreference(
//                            prefViewModel.getPreferenceSubmitted.firstOrNull() ?: false
//                        )
//                    }
//                }
            }
            else -> {
                if (isUpdate) {
                    isContinueClick = true
                    Log.d("TAG:", "UPDATE_MODEL== $apiRequest")
                    lifecycleScope.launch {
                        val userId = prefViewModel.getUserId.firstOrNull() ?: 0
                        prefViewModel.userPreferenceSubmit( apiRequest)

                        /*prefViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            prefViewModel.userPreferenceUpdate(
                                "Bearer $authToken", userId, apiRequest
                            )
                        }*/
                    }
                } else {
                    isContinueClick = true
                    Log.d("TAG:", "SUBMIT_MODEL== $apiRequest")
                    lifecycleScope.launch {
                        prefViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            prefViewModel.userPreferenceSubmit( apiRequest)
                        }
                    }
                }
            }
        }
    }

    private fun initObservers() {
        prefViewModel.apply {
            showLoading.observe(mActivity) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(mActivity) {
                Log.e("TAG", "ERROR === $it")
                mActivity.showSnackBar(binding.layoutMain, it)
            }
            data.observe(
                mActivity
            ) { responseModel->
//                if (it.response == "success") {
                if (responseModel.code == 1 && responseModel.message =="Success") {
//                    prefViewModel.savePreference(PreferenceKeys.PREF_PREFERENCE_FLAG, true)
                    if (isFromSettings) {
                        onBackPressed()
                    } else {
//                        nextActivity(EmailActivity::class.java)
                        openPrivacyModeDialog()
                    }

                }
            }
            updateData.observe(mActivity) {
                if (it.response == "success") {
//                    prefViewModel.savePreference(PreferenceKeys.PREF_PREFERENCE_FLAG, true)
                    if (isFromSettings) {
                        /*if(isContinueClick){
                            openUserProfileDialog()
                            isContinueClick = false
                        }
                        else*/
                        onBackPressed()
                    } else {
//                        nextActivity(EmailActivity::class.java)
                        openPrivacyModeDialog()
                    }

                }
            }
            error.observe(mActivity) {
                isContinueClick = false
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.message.toString())
            }
            updateError.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.message.toString())
            }
        }
    }

    private fun getSelectedData() {
        try {
            lifecycleScope.launch {
//                userGender = prefViewModel.getGender.firstOrNull() ?: "2"
                userGender = AppUtils.getGenderPref(this@PreferencesActivity)
                binding.btnSegmentGender.setPosition(userGender, true)
                applyFontFamily(userGender.toInt()+1)

//                startAge = prefViewModel.getStartAge.firstOrNull() ?: 18
                startAge = AppUtils.getStartAgePref(this@PreferencesActivity)//default it will give 18
                /*if (startAge == 0) {
                    startAge = 18
                }*/
                Log.d("TAG:", "startAgeData: == $startAge")

//                endAge = prefViewModel.getEndAge.firstOrNull() ?: 99
                endAge = AppUtils.getEndAgePref(this@PreferencesActivity)//default it will give 99
               /* if (endAge == 0) {
                    endAge = 99
                }*/
                Log.d("TAG:", "endAgeData: == $endAge")

                binding.ageRangeSeekbar.apply {
//                    setMinValue(18.0f)
//                    setMaxValue(99.0f)
                 //   setMinStartValue(startAge.toFloat())
                    setMinStartValue(startAge.toFloat())
                   // setMaxStartValue(endAge.toFloat())
                    setMaxStartValue(defaultAge.toFloat())
                    apply()
//                    setValues(startAge.toFloat(), endAge.toFloat())
                }
                binding.txtAgeRange.text = resources.getString(
                    R.string.between_values,
                    startAge.toString(),
                    endAge.toString()
                )
//                maxDistance = prefViewModel.getMaxDistance.firstOrNull() ?: 160
                maxDistance = AppUtils.getDistance(this@PreferencesActivity)
                if (maxDistance == 0) {
                    maxDistance = 25
                    binding.seekDistance.apply {
                        setMinStartValue(25f)
                        apply()
                    }
                } else {
                    binding.seekDistance.apply {
                        setMinStartValue(maxDistance.toFloat())
                        apply()
                    }
                }

                Log.d("TAG:", "maxDistance: == $maxDistance")

                binding.txtDistance.text =
                    resources.getString(R.string.up_to_distance, maxDistance.toString())

                //advance preference data from local storage
//                val languages = prefViewModel.getLanguage.firstOrNull()
                val languages = AppUtils.getLanguage(this@PreferencesActivity)
                if (!languages.isNullOrBlank()) {
                    val type: Type =
                        object : TypeToken<List<LanguageDataModel.LanguageData.Language?>?>() {}.type
                    languagesList =
                        Gson().fromJson(
                            languages,
                            type
                        ) as ArrayList<LanguageDataModel.LanguageData.Language>
                    val languageName = ArrayList<String>()
                    languagesList.onEach {
                        languageName.add(it.value!!)
                    }
                }

//                val education = prefViewModel.getEducation.firstOrNull()
                val education =AppUtils.getEducation(this@PreferencesActivity)
                if (!education.isNullOrBlank()) {
                    educationModel = Gson().fromJson<Any>(
                        education,
                        EducationModel::class.java
                    ) as EducationModel
                }

                val cast = prefViewModel.getCast.firstOrNull()
                if (!cast.isNullOrBlank()) {
                    val type: Type =
                        object : TypeToken<List<CastListModel.CastModel?>?>() {}.type
                    castModel =
                        Gson().fromJson(cast, type) as ArrayList<CastListModel.CastModel>
                }

//                val children = prefViewModel.getChildren.firstOrNull()
                val children = AppUtils.getChildren(this@PreferencesActivity)
                if (!children.isNullOrBlank()) {
                    childrenModel = Gson().fromJson<Any>(
                        children,
                        ChildrenModel.ChildrenData.ChildrenLevel::class.java
                    ) as ChildrenModel.ChildrenData.ChildrenLevel
                }

//                val religion = prefViewModel.getReligion.firstOrNull()
                val religion = AppUtils.getReligions(this@PreferencesActivity)
                if (!religion.isNullOrBlank()) {
                    val type: Type = object : TypeToken<java.util.ArrayList<ReligionModel.ReligionData.ReligionLevel>>() {}.type
                    religionModel = Gson().fromJson(religion , type) as java.util.ArrayList<ReligionModel.ReligionData.ReligionLevel>
                }

//                val smoking = prefViewModel.getSmoking.firstOrNull()
                val smoking = AppUtils.getSmoking(this@PreferencesActivity)
                if (!smoking.isNullOrBlank()) {
                    smokingModel =Gson().fromJson<Any>(
                        smoking,
                        SmokingDataModel.SmokingData.Smoking::class.java
                    ) as SmokingDataModel.SmokingData.Smoking
                }

//                val relationship = prefViewModel.getRelationship.firstOrNull()
                val relationship = AppUtils.getAstrologicalSign(this@PreferencesActivity)
                if (!relationship.isNullOrBlank()) {
                    relationshipModel = Gson().fromJson<Any>(
                        relationship,
                        RelationshipModel.RelationShipData.RelationshipLevel::class.java
                    ) as RelationshipModel.RelationShipData.RelationshipLevel
                }

                val astrology = AppUtils.getAstrologicalSigns(this@PreferencesActivity)
                if (!astrology.isNullOrBlank()) {
                    val type: Type =
                        object : TypeToken<java.util.ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>>() {}.type
                    astrologyModel = Gson().fromJson(astrology , type) as java.util.ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>
                }

                convertedStartHeight = prefViewModel.getStartHeightInches.firstOrNull() ?: 0

                convertedEndHeight = prefViewModel.getEndHeightInches.firstOrNull() ?: 0

                isInCM = prefViewModel.getIsHeightFeet.firstOrNull() ?: false

                isVerifiedProfile = prefViewModel.getIsVerified.firstOrNull() ?: false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        getSelectedData()
    }

    private fun openPrivacyModeDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val userProfileBinding = DialogProfileImageBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(userProfileBinding.root)
            userProfileBinding.apply {
                txtMessage1.text = getString(R.string.privacy_mode)
                txtMessage2.text = getString(R.string.privacy_mode_text)
                btnFillProfile.text = getString(R.string.three_months_payment)
                btnFillProfile.setOnClickListener {
                    userProfileBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
//                        onBackPressed()
                        nextActivity(TabManagerActivity::class.java)
                        finish()
                    }
                }
                btnNotNow.setOnClickListener {
                    userProfileBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
//                        onBackPressed()
                        nextActivity(TabManagerActivity::class.java)
                        finish()

                    }
                }
                try {
                    if (!dialog.isShowing) {
                        setZoomDialogWindowAttributes()
                        show()
                        imgDialogGradient.setDialogFadeInAnimation()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun applyFontFamily(selectedItem : Int = 3){
        /*binding.apply {
            val typeface: Typeface? = ResourcesCompat.getFont(mActivity, R.font.regular)
            btnMale.textTypeface = typeface
            btnFemale.textTypeface = typeface
            btnNone.textTypeface = typeface
            val typefaceSel: Typeface? = ResourcesCompat.getFont(mActivity, R.font.bold)
            when (selectedItem) {
                1 -> btnMale.textTypeface = typefaceSel
                2 -> btnFemale.textTypeface = typefaceSel
                else -> btnNone.textTypeface = typefaceSel
            }


        }*/
    }

}