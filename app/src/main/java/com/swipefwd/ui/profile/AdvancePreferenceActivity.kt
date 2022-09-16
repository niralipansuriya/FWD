package com.swipefwd.ui.profile

//import com.addisonelliott.segmentedbutton.SegmentedButtonGroup
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.renderscript.RenderScript
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import com.skydoves.balloon.overlay.BalloonOverlayOval
import com.swipefwd.R
import com.swipefwd.data.models.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityAdvancePreferenceBinding
import com.swipefwd.databinding.DialogPreferenceHeightPickerBinding
import com.swipefwd.databinding.DialogProfileImageBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.AppConstants.SCREEN_NAME
import com.swipefwd.utils.AppConstants.SCREEN_PREFERENCE
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.dpToPx
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.RSBlurProcessor
import com.swipefwd.utils.crystalRangeSeekbar.interfaces.OnRangeSeekbarChangeListener
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.utils.segmentedButtonTemp.SegmentedButtonGroup2
import com.yuyakaido.android.cardstackview.internal.DisplayUtil
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.util.*
import kotlin.math.roundToInt

class AdvancePreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdvancePreferenceBinding
    private var startFeet = 4
    private var endFeet = 6
    private var startInches = 10 //6
    private var endInches = 9
    private var startHeightCM = 0 //this is in CM , converted from 4'0
    private var endHeightCM = 0 //this is in CM , converted from 6'9
    private var startHeightInch = "4'10"
    private var endHeightInch = "6'9"
    var bitmap: Bitmap? = null
    var renderScript: RenderScript? = null
    private val mActivity by lazy {
        this@AdvancePreferenceActivity
    }
    private val advancePrefViewModel: AdvancePreferenceViewModel by viewModels {
        viewModelFactory { AdvancePreferenceViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var educationModel = EducationModel()

    //    private var childrenModel = ChildrenListModel.ChildrenModel()
//    private var smokingModel = SmokingListModel.SmokingModel()
//    private var relationshipModel = RelationshipListModel.RelationshipModel()
    private var isVerifiedProfile = false
    private var convertedStartInches = 0 //4'0 ==> (4x12) + 0
    private var convertedEndInches = 0 //6'9 ==> (6x12) + 9
    private var isInCM = false
    private var userGender = "0"
    private var startAge = 18
    private var endAge = 99
    private var maxDistance = 160
    private var isFromSettings = false
    private var isHeightChanged = false
    var statusBarHeight = 30
    private val dialogs: Vector<Dialog> = Vector<Dialog>()

    private var languagesList = ArrayList<LanguageDataModel.LanguageData.Language>()
    private var childrenModel = ChildrenModel.ChildrenData.ChildrenLevel()
    private var religionModel = ArrayList<ReligionModel.ReligionData.ReligionLevel>()
    private var smokingModel = SmokingDataModel.SmokingData.Smoking()
    private var relationshipModel = RelationshipModel.RelationShipData.RelationshipLevel()
    private var vaccineStatusModel = VaccinationModel.VaccinationData.VaccinationLevel()
    private var astrologyModel =
        ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>()


    //multiple data
//    private var languagesList = ArrayList<LanguageListModel.LanguageModel>()
    private var castModel = ArrayList<CastListModel.CastModel>()

    //    private var religionModel = ArrayList<ReligionListModel.ReligionModel>()
//    private var astrologyModel = ArrayList<AstroSignListModel.AstroSignModel>()
    private var balloonBuilder: Balloon.Builder? = null
    private val toolTip by lazy {
        createBalloon(mActivity) {
            balloonBuilder = this
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setBackgroundColor(ContextCompat.getColor(mActivity, android.R.color.transparent))
            setLayout(R.layout.layout_custom_tool_tip_height_wrap)
            setArrowSize(15)
            setCornerRadius(0f)
            setWidthRatio(1f)
            setArrowColor(
                ContextCompat.getColor(
                    mActivity,
                    R.color.balloon_arrow
                )
            )
            setIsVisibleOverlay(true)
            setOverlayColorResource(android.R.color.transparent)
            setOverlayPadding(6f)
            setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE) // default is fade.
            setOverlayShape(BalloonOverlayOval)
            setDismissWhenOverlayClicked(false)
            setArrowOrientation(ArrowOrientation.BOTTOM)
            setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            setArrowPosition(0.5f)
            setElevation(-1)
            setIsVisibleArrow(false)
            setBalloonAnimation(BalloonAnimation.OVERSHOOT)
            setDismissWhenTouchOutside(false)
            setDismissWhenShowAgain(false)
            setLifecycleOwner(mActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvancePreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("EditProfile")) {
            isFromSettings = intent.getBooleanExtra("EditProfile", false)
        }
        renderScript = RenderScript.create(this)
        init()
        initObservers()
        //  binding.layoutMain.viewTreeObserver.addOnGlobalLayoutListener(onDisplayCutoutReadyListener)
    }

    override fun onResume() {
        super.onResume()
        SCREEN_NAME = SCREEN_PREFERENCE
        if (SCREEN_NAME == AppConstants.SCREEN_PREFERENCE)
        {
            AppUtils.storeProfileORPref(this,1)
        }
        try {
            getSelectedData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("WrongViewCast")
    private fun init() {
        statusBarHeight = AppUtils.getStatusBarHeight(this)
        binding.apply {
            if (isFromSettings) {
                advancePrefViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
            } else {
                advancePrefViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "4")
            }
            labelPrefLocation.text =
                Html.fromHtml("<sup>†</sup>You and your Connectors (if you add them) get<br> </br>FWD profiles based on your selected Preferences")
            val span = SpannableString(labelPrefLocation.text)
            span.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.color_orange,
                        theme
                    )
                ),
                0,
                1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            labelPrefLocation.setText(span, TextView.BufferType.SPANNABLE)
            toolTip.getContentView().apply {
                AppUtils.changeDrawableColor(
                    mActivity,
                    findViewById(R.id.image_tooltip),
                    resources.getColor(R.color.balloon_arrow)
                )

                val btnGotIt = findViewById<MaterialButton>(R.id.btnTipGotIt)
                val params = btnGotIt.layoutParams as LinearLayout.LayoutParams
                params.topMargin = 10.dpToPx()
                btnGotIt.layoutParams = params
                findViewById<AppCompatTextView>(R.id.txtTipHeader)?.text =
                    getString(R.string.verified_profiles_only)
                findViewById<AppCompatTextView>(R.id.txtTipDesc)?.text =
                    context.getString(R.string.verified_profiles_tip_desc)
                findViewById<MaterialButton>(R.id.btnTipGotIt)?.setOnClickListener {
                    blurHandling(false)
                    toolTip.dismiss()

                }
            }
            ivBack.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                if (isFromSettings) {
                    btnContinue.performClick()
                } else {
                    onBackPressed()
                }
            }
            relFadedView.setOnClickListener(null)
            rlToggle.setOnClickListener {
                toggleProfiles.toggle()
            }
            edtLanguage.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                if (isFromSettings) {
                    startActivity(
                        Intent(
                            mActivity,
                            SelectLanguageActivity::class.java
                        ).putExtra("EditProfile", true)
                    )
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                } else {
                    nextActivity(SelectLanguageActivity::class.java)
                }
            }
            edtEducation.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                if (isFromSettings) {
                    startActivity(
                        Intent(
                            mActivity,
                            SelectSchoolActivity::class.java
                        ).putExtra("EditProfile", true)
                    )
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                } else {
                    nextActivity(SelectSchoolActivity::class.java)
                }
            }
            edtAstroSign.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                if (isFromSettings) {
                    startActivity(
                        Intent(
                            mActivity,
                            SelectAstroSignActivity::class.java
                        ).putExtra("EditProfile", true)
                    )
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                } else {
                    nextActivity(SelectAstroSignActivity::class.java)
                }
            }
            edtCaste.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                if (isFromSettings) {
                    startActivity(
                        Intent(
                            mActivity,
                            SelectMultipleCastActivity::class.java
                        ).putExtra("EditProfile", true)
                    )
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                } else {
                    nextActivity(SelectMultipleCastActivity::class.java)
                }
            }
            edtChildren.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                if (isFromSettings) {
                    startActivity(
                        Intent(
                            mActivity,
                            SelectChildrenPlanActivity::class.java
                        ).putExtra("EditProfile", true)
                    )
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                } else {
                    nextActivity(SelectChildrenPlanActivity::class.java)
                }
            }
            edtReligion.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                if (isFromSettings) {
                    startActivity(
                        Intent(
                            mActivity,
                            SelectReligionActivity::class.java
                        ).putExtra("EditProfile", true)
                    )
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                } else {
                    nextActivity(SelectReligionActivity::class.java)
                }
            }
            edtSmoking.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                if (isFromSettings) {
                    startActivity(
                        Intent(
                            mActivity,
                            SelectSmokeActivity::class.java
                        ).putExtra("EditProfile", true)
                    )
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                } else {
                    nextActivity(SelectSmokeActivity::class.java)
                }
            }
            edtLookingFor.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                if (isFromSettings) {
                    startActivity(
                        Intent(
                            mActivity,
                            SelectRelationshipActivity::class.java
                        ).putExtra("EditProfile", true)
                    )
                    overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
                } else {
                    nextActivity(SelectRelationshipActivity::class.java)
                }
            }

            btnContinue.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                lifecycleScope.launch {
                    submitUpdatePreference(
                        advancePrefViewModel.getPreferenceSubmitted.firstOrNull() ?: false
                    )
                }
            }
            imgInfo.setOnClickListener {
//                toolTip.getContentView().startAnimation(
//                    AnimationUtils.loadAnimation(
//                        mActivity ,
//                        R.anim.tip_slide_down
//                    )
//                )
//                toolTip.showAlignBottom(it)
                blurHandling(true)
                getCoordinates()
                imgInfo.isEnabled = false
                //  toolTip.showAlignBottom(it)
//                SlideView().slideBalloon(mActivity, balloonBuilder!!, toolTip, it, 1, BalloonSizeSpec.WRAP)
            }
            btnTipGotIt.setOnClickListener {
                AppUtils.slideView(binding.linLocAnim, DisplayUtil.dpToPx(mActivity, 210F), 0, 300)
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        relFadedView.visibility = View.GONE
                        imgInfo.isEnabled = true
                    },
                    300
                )
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        toolTipArrow.visibility = View.GONE
                    },
                    250
                )
            }
            txtHeight.setOnClickListener {
                Log.e("TAG", "POS===>${segmentedHeight.position}")
                openHeightDialog(segmentedHeight.position == 0)
            }
            toggleProfiles.setOnCheckedChangeListener { _, isChecked ->
                isVerifiedProfile = isChecked
                advancePrefViewModel.savePreference(PreferenceKeys.PREF_IS_VERIFIED, isChecked)
            }
            segmentedHeight.onPositionChangedListener =
                SegmentedButtonGroup2.OnPositionChangedListener {
                    // Handle stuff here
                    if (isHeightChanged) {
                        when (it) {
                            0 -> {
                                //convert height to ft
                                isInCM = false
                                advancePrefViewModel.savePreference(
                                    PreferenceKeys.PREF_PREF_IS_HEIGHT_FEET,
                                    isInCM
                                )
                                startFeet = (convertedStartInches.toFloat() / 12).toInt()
                                startInches = convertedStartInches.minus(startFeet * 12)
                                startHeightInch = resources.getString(
                                    R.string.feet_inches_show,
                                    startFeet.toString(),
                                    startInches.toString()
                                )
                                //for end
                                endFeet = (convertedEndInches.toFloat() / 12).toInt()
                                endInches = convertedEndInches.minus(endFeet * 12)
                                endHeightInch = resources.getString(
                                    R.string.feet_inches_show,
                                    endFeet.toString(),
                                    endInches.toString()
                                )
                                txtHeight.text =
                                    resources.getString(
                                        R.string.height_range,
                                        startHeightInch,
                                        endHeightInch
                                    )
                            }
                            1 -> {
                                //convert height to cm
                                isInCM = true
                                advancePrefViewModel.savePreference(
                                    PreferenceKeys.PREF_PREF_IS_HEIGHT_FEET,
                                    isInCM
                                )
                                endHeightCM = (convertedEndInches.div(0.3937)).toInt()
                                startHeightCM = (convertedStartInches.div(0.3937)).toInt()
                                txtHeight.text =
                                    resources.getString(
                                        R.string.height_range,
                                        startHeightCM.toString(),
                                        endHeightCM.toString()
                                    )
                            }
                        }
                    }
                }
        }
    }

    private fun getSelectedData() {
        lifecycleScope.launch {
            userGender = advancePrefViewModel.getGender.firstOrNull() ?: "2"

            startAge = advancePrefViewModel.getStartAge.firstOrNull() ?: 18
            if (startAge == 0) {
                startAge = 18
            }
            Log.d("TAG:", "startAgeData: == $startAge")

            endAge = advancePrefViewModel.getEndAge.firstOrNull() ?: 99
            if (endAge == 0) {
                endAge = 99
            }
            Log.d("TAG:", "endAgeData: == $endAge")

            maxDistance = advancePrefViewModel.getMaxDistance.firstOrNull() ?: 160
            if (maxDistance == 0) {
                maxDistance = 10
            }

            val languages = AppUtils.getLanguagePref(this@AdvancePreferenceActivity)
            if (!languages.isNullOrBlank()) {
                binding.edtLanguage.apply {
                    val type: Type =
                        object :
                            TypeToken<List<LanguageDataModel.LanguageData.Language?>?>() {}.type
                    languagesList =
                        Gson().fromJson(
                            languages,
                            type
                        ) as ArrayList<LanguageDataModel.LanguageData.Language>
                    val languageName = ArrayList<String>()
                    languagesList.onEach {
                        languageName.add(it.value!!)
                    }
                    setText(languageName.joinToString { it })
                    background = ContextCompat.getDrawable(
                        mActivity,
                        R.drawable.corner_blue_border_bg
                    )
                }
            } else {
                binding.edtLanguage.apply {
                    setText("")
                }
            }

//            val education = advancePrefViewModel.getEducation.firstOrNull()
            val education = AppUtils.getEducationPref(this@AdvancePreferenceActivity)
            if (!education.isNullOrBlank()) {
                educationModel = Gson().fromJson<Any>(
                    education,
                    EducationModel::class.java
                ) as EducationModel
                binding.edtEducation.apply {
                    val educationArray = ArrayList<String>()
                    if (educationModel.level != "") {
                        educationArray.add(educationModel.level!!)
                    }
                    if (educationModel.institute != "") {
                        educationArray.add(educationModel.institute!!)
                    }
                    if (educationModel.graduation_year != 0) {
                        educationArray.add(educationModel.graduation_year.toString())
                    }
                    setText(educationArray.joinToString(", "))
                    background = ContextCompat.getDrawable(
                        mActivity,
                        R.drawable.corner_blue_border_bg
                    )
                }
            }

            val cast = advancePrefViewModel.getCast.firstOrNull()
            if (!cast.isNullOrBlank()) {
                val type: Type =
                    object : TypeToken<List<CastListModel.CastModel?>?>() {}.type
                castModel =
                    Gson().fromJson(cast, type) as ArrayList<CastListModel.CastModel>
                binding.edtCaste.apply {
                    setText(castModel.map { it.name }.joinToString { it.toString() })
                    background = ContextCompat.getDrawable(
                        mActivity,
                        R.drawable.corner_blue_border_bg
                    )
                }
            }

//            val children = advancePrefViewModel.getChildren.firstOrNull()
            val children = AppUtils.getChildrenPref(this@AdvancePreferenceActivity)
            if (!children.isNullOrBlank()) {
                childrenModel = Gson().fromJson<Any>(
                    children,
                    ChildrenModel.ChildrenData.ChildrenLevel::class.java
                ) as ChildrenModel.ChildrenData.ChildrenLevel
                binding.edtChildren.apply {
                    setText(childrenModel.value)
                    background = ContextCompat.getDrawable(
                        mActivity,
                        R.drawable.corner_blue_border_bg
                    )
                }
            }

//            val religion = advancePrefViewModel.getReligion.firstOrNull()
            val religion = AppUtils.getReligions(this@AdvancePreferenceActivity)
            if (!religion.isNullOrBlank()) {
                val type: Type = object :
                    TypeToken<ArrayList<ReligionModel.ReligionData.ReligionLevel>>() {}.type
                religionModel = Gson().fromJson(
                    religion,
                    type
                ) as ArrayList<ReligionModel.ReligionData.ReligionLevel>
                binding.edtReligion.apply {
                    setText(religionModel.joinToString(", ") {
                        it.value.toString()
                    })
                    background = ContextCompat.getDrawable(
                        mActivity,
                        R.drawable.corner_blue_border_bg
                    )
                }
            }

//            val smoking = advancePrefViewModel.getSmoking.firstOrNull()
            val smoking = AppUtils.getSmokingPref(this@AdvancePreferenceActivity)
            if (!smoking.isNullOrBlank()) {
                smokingModel = Gson().fromJson<Any>(
                    smoking,
                    SmokingDataModel.SmokingData.Smoking::class.java
                ) as SmokingDataModel.SmokingData.Smoking
                binding.edtSmoking.apply {
                    setText(smokingModel.value)
                    background = ContextCompat.getDrawable(
                        mActivity,
                        R.drawable.corner_blue_border_bg
                    )
                }
            }

//            val relationship = advancePrefViewModel.getRelationship.firstOrNull()
            val relationship = AppUtils.getRelationShipPref(this@AdvancePreferenceActivity)
            if (!relationship.isNullOrBlank()) {
                relationshipModel = Gson().fromJson<Any>(
                    relationship,
                    RelationshipModel.RelationShipData.RelationshipLevel::class.java
                ) as RelationshipModel.RelationShipData.RelationshipLevel
                binding.edtLookingFor.apply {
                    setText(relationshipModel.value)
                    background = ContextCompat.getDrawable(
                        mActivity,
                        R.drawable.corner_blue_border_bg
                    )
                }
            }

//            val astrology = advancePrefViewModel.getAstroSign.firstOrNull()
            val astrology = AppUtils.getAstrologicalSigns(this@AdvancePreferenceActivity)
            if (!astrology.isNullOrBlank()) {
                val type: Type =
                    object :
                        TypeToken<ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>>() {}.type
                astrologyModel = Gson().fromJson(
                    astrology,
                    type
                ) as ArrayList<AstrologicalModel.AstrologicalData.AstrologicalSignLevel>
                binding.layoutAstroSign.apply {
                    background = ContextCompat.getDrawable(
                        mActivity,
                        R.drawable.corner_blue_border_bg
                    )
                }
                binding.edtAstroSign.apply {
                    text = astrologyModel.joinToString(", ") {
                        it.value.toString()
                    }
                }
            }
            isInCM = advancePrefViewModel.getIsHeightFeet.firstOrNull() ?: false

            val height = advancePrefViewModel.getStartHeight.firstOrNull() ?: ""
            val endHeightPref = advancePrefViewModel.getEndHeight.firstOrNull() ?: ""
            if (height.isNotEmpty() && height != "0.0" || endHeightPref.isNotEmpty() && endHeightPref != "0.0") {
                isHeightChanged = true
                startHeightCM = height.toInt()
                endHeightCM = endHeightPref.toInt()
                startHeightInch = when (isInCM) {
                    true -> {
                        startHeightCM.toString()
                    }
                    false -> {
                        val length = startHeightCM.div(2.54).toFloat()
                        startFeet = (length / 12).toInt()
                        startInches = (length - 12 * startFeet).roundToInt()
                        resources.getString(
                            R.string.feet_inches_show,
                            startFeet.toString(),
                            startInches.toString()
                        )
                    }
                }
                endHeightInch = when (isInCM) {
                    true -> {
                        endHeightCM.toString()
                    }
                    false -> {
                        val length = endHeightCM.div(2.54).toFloat()
                        endFeet = (length / 12).toInt()
                        endInches = (length - 12 * endFeet).roundToInt()
                        resources.getString(
                            R.string.feet_inches_show,
                            endFeet.toString(),
                            endInches.toString()
                        )
                    }
                }
                binding.txtHeight.text =
                    getString(R.string.height_range, startHeightInch, endHeightInch)
            }
            convertedStartInches = advancePrefViewModel.getStartHeightInches.firstOrNull() ?: 0
            convertedEndInches = advancePrefViewModel.getEndHeightInches.firstOrNull() ?: 0

            val feetPosition = if (isInCM) {
                1
            } else {
                0
            }
            binding.segmentedHeight.setPosition(feetPosition, true)

            isVerifiedProfile = advancePrefViewModel.getIsVerified.firstOrNull() ?: false
            binding.toggleProfiles.isChecked = isVerifiedProfile
        }
    }

    private fun openHeightDialog(isUnitInFeet: Boolean) {
        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            val pickerBinding = DialogPreferenceHeightPickerBinding.inflate(layoutInflater)
            setContentView(pickerBinding.root)
//            setTheme(R.style.AppTheme_FullScreen)
//            changeStatusBarColor()
            pickerBinding.apply {
                AppUtils.disableView(relHeight)
                txtSkip.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dialog.dismiss()
                        binding.edtLanguage.performClick()
//                        binding.edtAstroSign.performClick()
                    }
                }
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                        }
                        true
                    }
                    false
                }
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dialog.dismiss()
//                        }
//                        true
//                    }
//                    false
//                }
                btnSubmit.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        isHeightChanged = true
                        if (isUnitInFeet) {
                            convertedStartInches = (startFeet * 12) + startInches
                            convertedEndInches = (endFeet * 12) + endInches
                            Log.d("TAG:", "convertedStartFeet ==> $convertedStartInches")
                            Log.d("TAG:", "convertedEndFeet ==> $convertedEndInches")
                            mActivity.binding.txtHeight.text =
                                resources.getString(
                                    R.string.height_range,
                                    startHeightInch,
                                    endHeightInch
                                )
                        } else {
                            mActivity.binding.txtHeight.text = getString(
                                R.string.height_range,
                                startHeightCM.toString(),
                                endHeightCM.toString()
                            )
                            convertedStartInches = (0.3937 * startHeightCM).roundToInt()
                            convertedEndInches = (0.3937 * endHeightCM).roundToInt()
                            Log.d("TAG:", "convertedStartCm ==> $convertedStartInches")
                            Log.d("TAG:", "convertedEndCm ==> $convertedEndInches")
                        }
                        isInCM = !isUnitInFeet
                        advancePrefViewModel.savePreference(
                            PreferenceKeys.PREF_PREF_START_HEIGHT,
                            startHeightCM.toString()
                        )
                        advancePrefViewModel.savePreference(
                            PreferenceKeys.PREF_PREF_END_HEIGHT,
                            endHeightCM.toString()
                        )
                        advancePrefViewModel.savePreference(
                            PreferenceKeys.PREF_PREF_START_HEIGHT_INCHES,
                            convertedStartInches
                        )
                        advancePrefViewModel.savePreference(
                            PreferenceKeys.PREF_PREF_END_HEIGHT_INCHES,
                            convertedEndInches
                        )
                        advancePrefViewModel.savePreference(
                            PreferenceKeys.PREF_PREF_IS_HEIGHT_FEET,
                            isInCM
                        )
                        dialog.dismiss()
                        binding.edtLanguage.performClick()
                        //                        binding.edtAstroSign.performClick()
                    }
                }

                try {
                    if (isUnitInFeet) {
                        //converting feet/inches from CM min & max values
                        heightRangeSeekbar.apply {
                            txtAgeRange.text = resources.getString(
                                R.string.between_height_inche,
                                startHeightInch,
                                endHeightInch
                            )
                            startHeightCM =
                                ((startFeet * 30.48) + (startInches * 2.54)).roundToInt()
                            endHeightCM = ((endFeet * 30.48) + (endInches * 2.54)).roundToInt()
                            setMinValue(147.32F)//(4 * 30.48) + (10 * 2.54)
                            setMaxValue(205.74F)//(6 * 30.48) + (9 * 2.54)
                            setMinStartValue(startHeightCM.toFloat())
                            setMaxStartValue(endHeightCM.toFloat())
                            apply()
                            // set listener
                            setOnRangeSeekbarChangeListener(object :
                                OnRangeSeekbarChangeListener {
                                override fun valueChanged(minValues: Number?, maxValues: Number?) {
                                    val values = arrayOf(minValues, maxValues)
                                    val minValue = values[0].toString().split(".")[0].toInt()
                                    val maxValue = values[1].toString().split(".")[0].toInt()
                                    val startInche = (0.3937 * startHeightCM).roundToInt()
                                    startFeet = (startInche.toFloat() / 12).toInt()
                                    startInches =
                                        startInche.minus(startFeet * 12)
                                    startHeightInch = resources.getString(
                                        R.string.feet_inches_show,
                                        startFeet.toString(),
                                        startInches.toString()
                                    )
                                    Log.d("TAG:", "START: $startHeightInch")
                                    val endInche = (0.3937 * endHeightCM).roundToInt()
                                    endFeet = (endInche.toFloat() / 12).toInt()
                                    endInches = endInche.minus(endFeet * 12)
                                    endHeightInch = resources.getString(
                                        R.string.feet_inches_show,
                                        endFeet.toString(),
                                        endInches.toString()
                                    )
                                    Log.d("TAG:", "END: $endHeightInch")
                                    txtAgeRange.text = resources.getString(
                                        R.string.between_height_inche,
                                        startHeightInch,
                                        endHeightInch
                                    )
                                    startHeightCM = minValue
                                    endHeightCM = maxValue
                                }
                            })
                        }
                    } else {
                        heightRangeSeekbar.apply {
                            if (startHeightCM == 0) {
                                startHeightCM = 146
                            }
                            if (endHeightCM == 0) {
                                endHeightCM = 210
                            }
                            txtAgeRange.text = resources.getString(
                                R.string.between_height_cm,
                                startHeightCM.toString(),
                                endHeightCM.toString()
                            )
//                            setMinValue(146.toFloat())
//                            setMaxValue(210.toFloat())
                            setMinStartValue(startHeightCM.toFloat())
                            setMaxStartValue(endHeightCM.toFloat())
                            apply()
                            setOnRangeSeekbarChangeListener(object :
                                OnRangeSeekbarChangeListener {
                                override fun valueChanged(minValues: Number?, maxValues: Number?) {
                                    val values = arrayOf(minValues, maxValues)
                                    val minValue = values[0].toString().split(".")[0].toInt()
                                    val maxValue = values[1].toString().split(".")[0].toInt()
                                    txtAgeRange.text = resources.getString(
                                        R.string.between_height_cm,
                                        minValue.toString(),
                                        maxValue.toString()
                                    )
                                    startHeightCM = minValue
                                    endHeightCM = maxValue

                                }
                            })
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                pickerBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /* old  private fun submitUpdatePreference(isUpdate: Boolean) {
          val gender = when (userGender) {
              "0" -> AppConstants.USER_MALE
              "1" -> AppConstants.USER_FEMALE
              "2" -> AppConstants.USER_NON_BINARY
              else -> AppConstants.USER_MALE
          }
          val languageIds = languagesList.map { it.id }
          val signIds = astrologyModel.map { it.id }
          val castIds = castModel.map { it.id }
          val religionIds = religionModel.map { it.id }
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
                  addProperty("is_profile_verified" , isVerifiedProfile)
                  addProperty("gender" , gender)
                  if (startAge != 0)
                      addProperty("age_start" , startAge)
                  if (endAge != 0)
                      addProperty("age_end" , endAge)
                  addProperty("min_distance" , 0)
                  if (maxDistance != 0)
                      addProperty("max_distance" , maxDistance)
                  if (convertedStartInches != 0)
                      addProperty("start_height" , convertedStartInches)
                  if (convertedEndInches != 0)
                      addProperty("end_height" , convertedEndInches)
                  addProperty("institution" , educationModel.institute)
                  if (educationModel.graduation_year != 0)
                      addProperty("graduation_year" , educationModel.graduation_year)
                  add("language_ids" , languageArray)
                  add("astrological_sign_ids" , signArray)
                  if (educationModel.level_id != 0)
                      add("education_ids" , JsonArray().also { it.add(educationModel.level_id) })
                  add("caste_ids" , castArray)
                  if (childrenModel.id != 0)
                      add("children_ids" , JsonArray().also { it.add(childrenModel.id) })
                  add("religion_ids" , religionArray)
                  if (smokingModel.id != 0)
                      add("smoking_ids" , JsonArray().also { it.add(smokingModel.id) })
                  if (relationshipModel.id != 0)
                      add("relationship_ids" , JsonArray().also { it.add(relationshipModel.id) })
                  if (convertedEndInches != 0 && convertedStartInches != 0)
                      addProperty("height_in" , isHeightInFeet)
              } catch (e: Exception) {
                  e.printStackTrace()
              }
          }
          when {
              !AppUtils.isNetworkAvailable(mActivity) -> {
                  openFailNetworkDialog() {
                      lifecycleScope.launch {
                          submitUpdatePreference(
                              advancePrefViewModel.getPreferenceSubmitted.firstOrNull() ?: false
                          )
                      }
                  }
  //                    AppUtils.showMessageOK(
  //                        mActivity ,
  //                        getString(R.string.app_name) ,
  //                        getString(R.string.common_retry) ,
  //                        getString(R.string.no_internet)
  //                    ) { _ , _ ->
  //                        lifecycleScope.launch {
  //                            submitUpdatePreference(
  //                                advancePrefViewModel.getPreferenceSubmitted.firstOrNull() ?: false
  //                            )
  //                        }
  //                    }
              }
              else -> {
                  if (isUpdate) {
                      Log.d("TAG:" , "UPDATE== $apiRequest")
                      lifecycleScope.launch {
                          val userId = advancePrefViewModel.getUserId.firstOrNull() ?: 0
                          advancePrefViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                              advancePrefViewModel.userPreferenceUpdate(
                                  "Bearer $authToken" , userId , apiRequest
                              )
                          }
                      }
                  } else {
                      Log.d("TAG:" , "SUBMIT_MODEL== $apiRequest")
                      lifecycleScope.launch {
                          advancePrefViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                              advancePrefViewModel.userAdvancePreferenceSubmit(
                                   apiRequest
                              )
                          }
                      }
                  }
              }
          }
      }*/

    private fun submitUpdatePreference(isUpdate: Boolean) {
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
                addProperty("verified", if (isVerifiedProfile) 1 else 0)

                if (convertedStartInches!=0)
                {
                    addProperty("min_height", convertedStartInches)

                }
                if (convertedEndInches != 0) {
                    addProperty("max_height", convertedEndInches)

                }
                if (educationModel.level!!.isNotBlank()) {
                    addProperty("education", educationModel.level)

                }
                /*addProperty("institution" , educationModel.level)
                if (educationModel.graduation_year != 0)
                    addProperty("graduation_year" , educationModel.graduation_year)
                f (educationModel.level_id != 0)
                add("education_ids" , JsonArray().also { it.add(educationModel.level_id) })*/
//                add("language_ids" , languageArray)
//                add("astrological_sign_ids" , signArray)
                if (languageArray.size() > 0 && !languageArray.isEmpty) {
                    add("language", languageArray)

                }
                if (signArray.size() > 0 && !signArray.isEmpty) {
                    add("astrological", signArray)

                }
                if (childrenModel.value!!.isNotBlank()) {
                    addProperty("children", childrenModel.value)

                }
                if (religionArray.size() > 0 && !religionArray.isEmpty) {
                    add("religion", religionArray)

                }
                if (smokingModel.value!!.isNotBlank()) {
                    addProperty("smoking", smokingModel.value)

                }
                if (relationshipModel.value!!.isNotBlank()) {
                    addProperty("relationship_interest", relationshipModel.value)

                }
                /*if (convertedEndInches != 0 && convertedStartInches != 0)
                    addProperty("height_in" , isHeightInFeet)*/
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() {
                    lifecycleScope.launch {
                        submitUpdatePreference(
                            advancePrefViewModel.getPreferenceSubmitted.firstOrNull() ?: false
                        )
                    }
                }
            }
            else -> {
                if (isUpdate) {
                    Log.d("TAG:", "UPDATE== $apiRequest")
                    lifecycleScope.launch {
                        val userId = advancePrefViewModel.getUserId.firstOrNull() ?: 0
                        advancePrefViewModel.userAdvancePreferenceSubmit(
                            apiRequest
                        )
                        /*  advancePrefViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                              advancePrefViewModel.userPreferenceUpdate(
                                  "Bearer $authToken" , userId , apiRequest
                              )
                          }*/
                    }
                } else {
                    Log.d("TAG:", "SUBMIT_MODEL== $apiRequest")
                    lifecycleScope.launch {
                        advancePrefViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            advancePrefViewModel.userAdvancePreferenceSubmit(
                                apiRequest
                            )
                        }
                    }
                }
            }
        }
    }

    private fun initObservers() {
        advancePrefViewModel.apply {
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
            advanceData.observe(mActivity) { responseModel ->
//                if (it.response == "success") {
                if (responseModel.code == 1 && responseModel.message == "Success") {
                    advancePrefViewModel.savePreference(PreferenceKeys.PREF_PREFERENCE_FLAG, true)
//                    nextActivity(EmailActivity::class.java)
                    openPrivacyModeDialog()
//                    onBackPressed()
                }
            }
            updateData.observe(mActivity) {
                if (it.response == "success") {
                    advancePrefViewModel.savePreference(PreferenceKeys.PREF_PREFERENCE_FLAG, true)
                    if (isFromSettings) {
                        setResult(RESULT_OK)
                        finish()
//                        onBackPressed()
                    } else {
                        openPrivacyModeDialog()
                    }
                }
            }
            error.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.message.toString())
            }
            updateError.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.message.toString())
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right)
//        finish()
    }

    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if (it.isShowing) {
                it.dismiss()
            }
        }
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
                        finishAffinity()
                        finish()


                    }
                }
                btnNotNow.setOnClickListener {
                    userProfileBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
//                        onBackPressed()
                        nextActivity(TabManagerActivity::class.java)
                        finishAffinity()

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

    fun blurHandling(isBlur: Boolean = false) {
        if (isBlur) {
            val rootView = window.decorView.rootView
            val processedBitmap =
                RSBlurProcessor(renderScript).blur(AppUtils.getScreenShot(rootView), 25f, 1)
            val processedBitmap2 = RSBlurProcessor(renderScript).blur(processedBitmap, 25f, 1)
            val processedBitmap3 = RSBlurProcessor(renderScript).blur(processedBitmap2, 25f, 1)
            val processedBitmap4 = RSBlurProcessor(renderScript).blur(processedBitmap3, 25f, 1)
            val processedBitmap5 = RSBlurProcessor(renderScript).blur(processedBitmap4, 25f, 1)
            binding.imageFaded.setImageBitmap(processedBitmap5)
            binding.relFadedView.visibility = View.VISIBLE
        } else {
            binding.relFadedView.visibility = View.GONE
        }
    }

    private fun getCoordinates() {
        // set text to views
        binding.apply {
            txtTipHeader.text = getString(R.string.verified_profiles_only)
            txtTipDesc.text = getString(R.string.verified_profiles_tip_desc)
          //  imgToolTip.setBackgroundResource(R.drawable.verify_profile)
            imgToolTip.setBackgroundResource(R.drawable.verified_icon)
        }

        // get position of view
        val point = IntArray(2)
        binding.imgInfo.getLocationOnScreen(point)
        val data = point
        if (data.size > 1) {
            // move info icon to selected position
            var params = binding.ivInfo2.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(
                data[0],
                data[1] - statusBarHeight,
                params.rightMargin,
                params.bottomMargin
            )
            binding.ivInfo2.layoutParams = params

            // move arrow
            var paramsArrow = binding.toolTipArrow.layoutParams as ViewGroup.MarginLayoutParams
            paramsArrow.setMargins(
                data[0] - DisplayUtil.dpToPx(mActivity, 4F),
                data[1] - statusBarHeight + DisplayUtil.dpToPx(mActivity, 25F),
                paramsArrow.rightMargin,
                paramsArrow.bottomMargin
            )
            binding.toolTipArrow.layoutParams = paramsArrow


            // move view below info icon
            var paramsLoc = binding.linLocAnim.layoutParams as ViewGroup.MarginLayoutParams
            paramsLoc.setMargins(
                0,
                data[1] - statusBarHeight + DisplayUtil.dpToPx(mActivity, 38F),
                paramsLoc.rightMargin,
                paramsLoc.bottomMargin
            )
            binding.linLocAnim.layoutParams = paramsLoc

            binding.toolTipArrow.visibility = View.VISIBLE
            AppUtils.slideView(binding.linLocAnim, 0, DisplayUtil.dpToPx(mActivity, 210F), 300)
            AppUtils.changeDrawableColor(
                mActivity,
                binding.toolTipArrow,
                resources.getColor(R.color.balloon_arrow)
            )


        }
    }

    /* private val onDisplayCutoutReadyListener : ViewTreeObserver.OnGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener{
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

             var displayCutout = window.decorView.rootWindowInsets.displayCutout
             if (displayCutout?.boundingRects!!.size>0) {
                 val notchRect = displayCutout?.boundingRects!!.get(0)
                 Log.e("AdvancePref","notch height in pixels="+notchRect.height()) // 102
                 Log.e("AdvancePref",statusBarHeight.toString()) // 102
             }
         }

     }*/

}