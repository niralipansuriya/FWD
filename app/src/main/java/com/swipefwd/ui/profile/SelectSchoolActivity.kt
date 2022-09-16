package com.swipefwd.ui.profile

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.swipefwd.R
import com.swipefwd.animations.SlideView
import com.swipefwd.data.models.EducationDetailModel
import com.swipefwd.data.models.EducationLevelListModel
import com.swipefwd.data.models.EducationModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivitySchoolBinding
import com.swipefwd.databinding.DialogGraduationYearBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.AppDatabase
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.dpToPx
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.openProfileFinishDialog
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.yuyakaido.android.cardstackview.internal.DisplayUtil.pxToDp
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt

class SelectSchoolActivity : AppCompatActivity() {

    private var isKeyboardOpened = false
    private lateinit var binding: ActivitySchoolBinding
    private val mActivity by lazy {
        this@SelectSchoolActivity
    }
    private var isFromSettings = false
    private var mYear = "2015"
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private val mDatabase by lazy {
        AppDatabase.getInstance(mActivity)
    }
    private var selectedLevel = ""
    private var selectedLevelId = 0
    private var educationModel = EducationModel()
    private val schoolViewModel: SelectSchoolViewModel by viewModels {
        viewModelFactory { SelectSchoolViewModel(mActivity, AppRepository()) }
    }
    private val mLevelAdapter by lazy {
        LevelAdapter(mActivity) {
            binding.apply {
//                cardList.visibility = View.GONE
                isVisibleCard = false
                println("isVisibleCard adapter------>>>>>>>>>")

                Handler(Looper.getMainLooper()).postDelayed({
                    slideView(cardList,maxCardHeight,0, 180)

                }, 200)
                txtLevelName.apply {
                    txtSubmit.apply {
                        isEnabled = true
                        isClickable = true
                    }
                    txtSubmit2.apply {
                        isEnabled = true
                        isClickable = true
                    }
                    setText(it.education_level ?: "")
                    selectedLevel = it.education_level ?: ""
                    selectedLevelId = it._id ?: 0
                   // background = ContextCompat.getDrawable(mActivity, R.drawable.corner_blue_border_bg)
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dropdown, 0)
                }
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE ->
                    {
                        txtInstituteName.visibility=View.VISIBLE
                        txtInstituteTitle.visibility=View.VISIBLE
                        txtYear.visibility=View.VISIBLE
                        txtGraduationYear.visibility=View.VISIBLE
                    }
                    else ->
                    {
                        txtInstituteName.visibility=View.GONE
                        txtInstituteTitle.visibility=View.GONE
                        txtYear.visibility=View.GONE
                        txtGraduationYear.visibility=View.GONE
                    }
                }

            }
        }
    }
    private var isVisibleCard = false
    private var startHeightCM = 0 //this is in CM , converted from 4'0
    private var endHeightCM = 0 //this is in CM , converted from 6'9
    private var isInCM = false
    private var startFeet = 4
    private var endFeet = 6
    private var startInches = 10//6
    private var endInches = 9
    private var startHeightInch = "4'10"
    private var endHeightInch = "6'9"
    private var convertedStartInches = 0 //4'0 ==> (4x12) + 0
    private var convertedEndInches = 0 //6'9 ==> (6x12) + 9
    private var maxCardHeight = 200
    private var mAnimation = SlideView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchoolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("EditProfile")) {
            isFromSettings = intent.getBooleanExtra("EditProfile", false)
        }
        if(!(AppConstants.SCREEN_NAME==AppConstants.SCREEN_PROFILE)) {
            maxCardHeight = 160
        }

        println("SCREEN_NAME-------->>>${AppConstants.SCREEN_NAME}")
        init()
        initObservers()
    }

    private fun init() {
        binding.apply {
            txtInstituteName.setOnClickListener{
//                txtSkip.visibility=View.GONE
//                txtSubmit.visibility=View.GONE
//                llButtons.visibility=View.VISIBLE
            }
            val contentView = layoutMain
            contentView.viewTreeObserver.addOnGlobalLayoutListener {
                val r = Rect()
                contentView.getWindowVisibleDisplayFrame(r)
                val screenHeight = contentView.rootView.height
                val keypadHeight = screenHeight - r.bottom
                if (keypadHeight > screenHeight * 0.15) {
                    if(!isKeyboardOpened){
                        isKeyboardOpened = true
                        txtSkip.visibility=View.GONE
                        txtSubmit.visibility=View.GONE
                        llButtons.visibility=View.VISIBLE
                    }
                } else {
                    if(isKeyboardOpened){
                        isKeyboardOpened = false
                        txtSkip.visibility=View.VISIBLE
                        txtSubmit.visibility=View.VISIBLE
                        llButtons.visibility=View.GONE
                    }
                }
            }
            lifecycleScope.launch {
                isInCM = schoolViewModel.getIsHeightFeet.firstOrNull() ?: false
                val height = schoolViewModel.getStartHeight.firstOrNull() ?: ""
                val endHeightPref = schoolViewModel.getEndHeight.firstOrNull() ?: ""
                if (height.isNotEmpty() && height != "0.0" || endHeightPref.isNotEmpty() && endHeightPref != "0.0") {
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
                }
            }
            when (AppConstants.SCREEN_NAME) {
                AppConstants.SCREEN_PROFILE -> {
                    txtIndex.text = getString(R.string.sequence_profile, "3")
                    txtTitle.text = getString(R.string.education_hint)
                    txtInstituteTitle.visibility = View.VISIBLE
                    txtInstituteName.visibility = View.VISIBLE
                    txtGraduationYear.visibility = View.VISIBLE
                    txtYear.visibility = View.VISIBLE
                }
                else -> {
                    txtIndex.text = getString(R.string.sequence_preference, "2")
                    txtTitle.text = getString(R.string.education)
                    txtInstituteTitle.visibility = View.GONE
                    txtInstituteName.visibility = View.GONE
                    txtGraduationYear.visibility = View.GONE
                    txtYear.visibility = View.GONE
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
            txtSubmit.isEnabled = true
            txtSubmit.isClickable = true
            txtSubmit.setOnClickListener {
                if (txtYear.text.toString().trim().isNotEmpty()) {
                    educationModel.graduation_year = txtYear.text.toString().trim().toInt()
                }
                educationModel.level = selectedLevel
                educationModel.institute = txtInstituteName.text.toString().trim()
                educationModel.level_id = selectedLevelId
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        schoolViewModel.saveSchool(Gson().toJson(educationModel))
                        startActivity(
                            Intent(
                                mActivity,
                                SelectAstroSignActivity::class.java
                            ).putExtra("EditProfile", isFromSettings)
                        )
                        finish()
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    }
                    else -> {
                        schoolViewModel.savePrefSchool(Gson().toJson(educationModel))
                        nextActivity(SelectAstroSignActivity::class.java)
//                        openHeightDialog(isInCM)
//                        finish()
                    }
                }
            }
            txtSubmit2.isEnabled = true
            txtSubmit2.isClickable = true
            txtSubmit2.setOnClickListener {
                if (txtYear.text.toString().trim().isNotEmpty()) {
                    educationModel.graduation_year = txtYear.text.toString().trim().toInt()
                }
                educationModel.level = selectedLevel
                educationModel.institute = txtInstituteName.text.toString().trim()
                educationModel.level_id = selectedLevelId
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        schoolViewModel.saveSchool(Gson().toJson(educationModel))
                        startActivity(
                            Intent(
                                mActivity,
                                SelectAstroSignActivity::class.java
                            ).putExtra("EditProfile", isFromSettings)
                        )
                        finish()
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    }
                    else -> {
                        schoolViewModel.savePrefSchool(Gson().toJson(educationModel))
                        nextActivity(SelectAstroSignActivity::class.java)
//                        openHeightDialog(isInCM)
//                        finish()
                    }
                }
            }
            txtSkip2.setOnClickListener {
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        startActivity(
                            Intent(
                                mActivity,
                                SelectAstroSignActivity::class.java
                            ).putExtra("EditProfile", isFromSettings)
                        )
                        finish()
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    }
                    AppConstants.SCREEN_PREFERENCE -> {
                        nextActivity(SelectAstroSignActivity::class.java)
//                        openHeightDialog(isInCM)
//                        finish()
                    }
                }
            }
            txtSkip.setOnClickListener {
                when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                        startActivity(
                            Intent(
                                mActivity,
                                SelectAstroSignActivity::class.java
                            ).putExtra("EditProfile", isFromSettings)
                        )
                        finish()
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    }
                    AppConstants.SCREEN_PREFERENCE -> {
                        nextActivity(SelectAstroSignActivity::class.java)
//                        openHeightDialog(isInCM)
//                        finish()
                    }
                }
            }
            txtYear.setOnClickListener {
                //open year dialog
                openGraduationYearDialog()
            }
            var levelDbList: ArrayList<EducationLevelListModel.LevelModel>
            var educationDbList: ArrayList<EducationDetailModel.DataEducatiton.EducationData>
            lifecycleScope.launch {
               levelDbList = mDatabase.userProfileDao().getAllLevel() as ArrayList<EducationLevelListModel.LevelModel>
                if (levelDbList.isNotEmpty()) {
                    //setData(levelDbList)
                } else {
                    getLevelList()
                }
            }
            txtLevelName.setOnClickListener {
                 if (!isVisibleCard) {
                     println("isVisibleCard not------>>>>>>>>>")
                    slideView(cardList,0,maxCardHeight, 300)
                    isVisibleCard = true
                     txtLevelName.setBackgroundResource(R.drawable.corner_blue_border_bg)
                    txtLevelName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up_arrow, 0)
                 //   cardList.startAnimation(AnimationUtils.loadAnimation(this@SelectSchoolActivity, R.anim.tip_slide_down))
                 //   View.VISIBLE
                     txtInstituteName.visibility=View.GONE
                     txtInstituteTitle.visibility=View.GONE
                     txtYear.visibility=View.GONE
                     txtGraduationYear.visibility=View.GONE
                } else {
                     println("isVisibleCard Yes------>>>>>>>>>")


                     Handler(Looper.getMainLooper()).postDelayed({
                         slideView(cardList,maxCardHeight,0, 180)

                     }, 200)
                    isVisibleCard = false
                     txtLevelName.setBackgroundResource(R.drawable.grey_border_bg)
                    txtLevelName.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_down_arrow,
                        0
                    )
                    if(AppConstants.SCREEN_NAME == AppConstants.SCREEN_PROFILE) {
                         txtInstituteName.visibility = View.VISIBLE
                         txtInstituteTitle.visibility = View.VISIBLE
                         txtYear.visibility = View.VISIBLE
                         txtGraduationYear.visibility = View.VISIBLE
                     }
                  //  View.GONE
                }
            }
            rvLevels.adapter = mLevelAdapter
            txtInstituteName.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    txtYear.performClick()
                    true
                }
                false
            }
            txtYear.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    AppUtils.hideUnderline(p0)
                }

            })
            txtInstituteName.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    AppUtils.hideUnderline(s)
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (count == 0) {
                        //    txtLevelName.setBackgroundResource(R.drawable.grey_border_bg)
                    } else {
                        //    txtLevelName.setBackgroundResource(R.drawable.corner_blue_border_bg)
                        txtSubmit.apply {
                            isEnabled = true
                            isClickable = true
                        }
                        txtSubmit2.apply {
                            isEnabled = true
                            isClickable = true
                        }
                    }
                    if (txtLevelName.text.toString().trim()
                            .isEmpty() && txtYear.text.toString().trim()
                            .isEmpty() && txtInstituteName.text.toString().trim().isEmpty()
                    ) {
                        txtSubmit.apply {
                            isEnabled = false
                            isClickable = false
                        }
                        txtSubmit2.apply {
                            isEnabled = false
                            isClickable = false
                        }
                    } else {
                        txtSubmit.apply {
                            isEnabled = true
                            isClickable = true
                        }
                        txtSubmit2.apply {
                            isEnabled = true
                            isClickable = true
                        }
                    }
                    if (s != null) {
                        if (s.length >= 25) {
                            showSnackBar(
                                binding.layoutMain,
                                getString(R.string.character_limit)
                            )
                        }
                    }
                }
            })
            txtLevelName.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (count == 0) {
                    //    txtLevelName.setBackgroundResource(R.drawable.grey_border_bg)
                    } else {
                    //    txtLevelName.setBackgroundResource(R.drawable.corner_blue_border_bg)
                        txtSubmit.apply {
                            isEnabled = true
                            isClickable = true
                        }
                        txtSubmit2.apply {
                            isEnabled = true
                            isClickable = true
                        }
                    }
                    if (txtLevelName.text.toString().trim()
                            .isEmpty() && txtYear.text.toString().trim()
                            .isEmpty() && txtInstituteName.text.toString().trim().isEmpty()
                    ) {
                        txtSubmit.apply {
                            isEnabled = false
                            isClickable = false
                        }
                        txtSubmit2.apply {
                            isEnabled = false
                            isClickable = false
                        }
                    } else {
                        txtSubmit.apply {
                            isEnabled = true
                            isClickable = true
                        }
                        txtSubmit2.apply {
                            isEnabled = true
                            isClickable = true
                        }
                    }
                    if (s != null) {
                        if (s.length >= 25) {
                            showSnackBar(
                                binding.layoutMain,
                                getString(R.string.character_limit)
                            )
                        }
                    }
                }
            })
        }
    }
    fun Activity.nextActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
        finish()
        overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
    }
    private fun openGraduationYearDialog() {
        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        val yearBinding = DialogGraduationYearBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            setContentView(yearBinding.root)
            //setting data
            yearBinding.apply {
                AppUtils.disableView(cardviewGraduation)
                val yearList = arrayListOf<String>().apply {
                    addAll((1900..Calendar.getInstance().get(Calendar.YEAR)).map {
                        String.format(
                            "%02d",
                            it
                        )
                    })
                }
                pickerYear.apply {
                    typeface = ResourcesCompat.getFont(this@SelectSchoolActivity, R.font.regular)
                }
                pickerYear.data = yearList
                pickerYear.selectedItemPosition = yearList.indexOf(mYear)
                pickerYear.setOnItemSelectedListener { _, data, _ ->
                    mYear = data.toString()
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
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                        }
                        true
                    }
                    false
                }
                txtDone.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        //get DOB and show in view
                        binding.txtSubmit.apply {
                            isEnabled = true
                            isClickable = true
                        }
                        mActivity.binding.txtYear.apply {
                            setText(mYear)
                            setBackgroundResource(R.drawable.corner_blue_border_bg)
                        }
                        dismiss()
                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                yearBinding.imgDialogGradient.setDialogFadeInAnimation()
                mAnimation.slide(yearBinding.clGraduationYear, 100, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getLevelList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getLevelList()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getLevelList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                   /* schoolViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        schoolViewModel.educationLevelList(
                            "Bearer $authToken"
                        )
                    }*/
                    schoolViewModel.getEducationDetails()

                }
            }
        }
    }

    private fun initObservers() {
        schoolViewModel.apply {
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
           /* data.observe(mActivity) { list ->
                lifecycleScope.launch {
                    mDatabase.userProfileDao().insertAllLevel(list)
                    setData(list)
                } */
            educationData.observe(mActivity) { list ->
                lifecycleScope.launch {
                   // mDatabase.userProfileDao().insertAllLevel(list)
                    setData(list)
                }
            }
        }
    }

   // private fun setData(levelList: ArrayList<EducationLevelListModel.LevelModel>) {
    private fun setData(levelList: ArrayList<EducationDetailModel.DataEducatiton.EducationData>) {
        mLevelAdapter.addAll(levelList,AppConstants.SCREEN_NAME)
        binding.apply {
            lifecycleScope.launch {
                val education = when (AppConstants.SCREEN_NAME) {
                    AppConstants.SCREEN_PROFILE -> {
                       // schoolViewModel.getEducation.firstOrNull()
                        AppUtils.getEducation(this@SelectSchoolActivity)
                    }
                    else -> {
                        AppUtils.getEducationPref(this@SelectSchoolActivity)
                      //  schoolViewModel.getPrefEducation.firstOrNull()
                    }
                }
                if (!education.isNullOrBlank()) {
                    educationModel = Gson().fromJson<Any>(
                        education,
                        EducationModel::class.java
                    ) as EducationModel
                    selectedLevelId = educationModel.level_id!!
                    selectedLevel = educationModel.level!!
                    txtInstituteName.setText(educationModel.institute)
                    txtLevelName.apply {
                        setText(selectedLevel)
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dropdown, 0)
                    }
                    txtYear.apply {
                        if (educationModel.graduation_year != 0) {
                            setText(educationModel.graduation_year.toString())
                            setBackgroundResource(R.drawable.corner_blue_border_bg)
                        } else {
                            setText("")
                            setBackgroundResource(R.drawable.grey_border_bg)
                        }
                    }
                    mYear = educationModel.graduation_year.toString()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun slideView(view : View, currentHeight : Int, newHeight : Int, duration: Long) {
        val slideAnimator = ValueAnimator
            .ofInt(currentHeight.dpToPx(), newHeight.dpToPx())
            .setDuration(duration)
        slideAnimator.addUpdateListener { animation1 ->
            val value = animation1.animatedValue
            view.layoutParams.height = value as Int
            view.requestLayout()
        }
        val animationSet = AnimatorSet()
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.play(slideAnimator)
        animationSet.start()
    }

//    private fun openHeightDialog(isUnitInCM: Boolean) {
//        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
//        dialog.apply {
//            requestWindowFeature(Window.FEATURE_NO_TITLE)
//            setCancelable(true)
//            setCanceledOnTouchOutside(true)
//            val pickerBinding = DialogPreferenceHeightPickerBinding.inflate(layoutInflater)
//            setContentView(pickerBinding.root)
//
//            pickerBinding.apply {
//                txtSkip.setOnClickListener {
//                    imgDialogGradient.setDialogFadeOutAnimation {
//                        dialog.dismiss()
//                        startActivity(
//                            Intent(
//                                mActivity,
//                                SelectAstroSignActivity::class.java
//                            ).putExtra("EditProfile", isFromSettings)
//                        )
//                        finish()
//                    }
//                }
//                btnSubmit.setOnClickListener {
//                    imgDialogGradient.setDialogFadeOutAnimation {
//                        if (isUnitInCM) {
//                            convertedStartInches = (startFeet * 12) + startInches
//                            convertedEndInches = (endFeet * 12) + endInches
//                        } else {
//                            convertedStartInches = (0.3937 * startHeightCM).roundToInt()
//                            convertedEndInches = (0.3937 * endHeightCM).roundToInt()
//                        }
//                        schoolViewModel.savePreference(
//                            PreferenceKeys.PREF_PREF_START_HEIGHT,
//                            startHeightCM.toString()
//                        )
//                        schoolViewModel.savePreference(
//                            PreferenceKeys.PREF_PREF_END_HEIGHT,
//                            endHeightCM.toString()
//                        )
//                        schoolViewModel.savePreference(
//                            PreferenceKeys.PREF_PREF_START_HEIGHT_INCHES,
//                            convertedStartInches
//                        )
//                        schoolViewModel.savePreference(
//                            PreferenceKeys.PREF_PREF_END_HEIGHT_INCHES,
//                            convertedEndInches
//                        )
//                        dialog.dismiss()
//                        startActivity(
//                            Intent(
//                                mActivity,
//                                SelectAstroSignActivity::class.java
//                            ).putExtra("EditProfile", isFromSettings)
//                        )
//                        finish()
//                    }
//                }
//                try {
//                    if (!isUnitInCM) {
//                        //converting feet/inches from CM min & max values
//                        heightRangeSeekbar.apply {
//                            txtAgeRange.text = resources.getString(
//                                R.string.between_height_inche,
//                                startHeightInch,
//                                endHeightInch
//                            )
//                            startHeightCM =
//                                ((startFeet * 30.48) + (startInches * 2.54)).roundToInt()
//                            endHeightCM = ((endFeet * 30.48) + (endInches * 2.54)).roundToInt()
//                            setValues(startHeightCM.toFloat(), endHeightCM.toFloat())
//                            addOnChangeListener(RangeSlider.OnChangeListener { _, _, _ ->
//                                val values = values
//                                val minValue = values[0].toString().split(".")[0].toInt()
//                                val maxValue = values[1].toString().split(".")[0].toInt()
//                                val startInche = (0.3937 * startHeightCM).roundToInt()
//                                startFeet = (startInche.toFloat() / 12).toInt()
//                                startInches =
//                                    startInche.minus(startFeet * 12)
//                                startHeightInch = resources.getString(
//                                    R.string.feet_inches_show,
//                                    startFeet.toString(),
//                                    startInches.toString()
//                                )
//                                Log.d("TAG:", "START: $startHeightInch")
//                                val endInche = (0.3937 * endHeightCM).roundToInt()
//                                endFeet = (endInche.toFloat() / 12).toInt()
//                                endInches = endInche.minus(endFeet * 12)
//                                endHeightInch = resources.getString(
//                                    R.string.feet_inches_show,
//                                    endFeet.toString(),
//                                    endInches.toString()
//                                )
//                                Log.d("TAG:", "END: $endHeightInch")
//                                txtAgeRange.text = resources.getString(
//                                    R.string.between_height_inche,
//                                    startHeightInch,
//                                    endHeightInch
//                                )
//                                startHeightCM = minValue
//                                endHeightCM = maxValue
//                            })
//                        }
//                    } else {
//                        heightRangeSeekbar.apply {
//                            if (startHeightCM == 0) {
//                                startHeightCM = 146
//                            }
//                            if (endHeightCM == 0) {
//                                endHeightCM = 210
//                            }
//                            txtAgeRange.text = resources.getString(
//                                R.string.between_height_cm,
//                                startHeightCM.toString(),
//                                endHeightCM.toString()
//                            )
//                            setValues(startHeightCM.toFloat(), endHeightCM.toFloat())
//                            addOnChangeListener(RangeSlider.OnChangeListener { _, _, _ ->
//                                val values = values
//                                val minValue = values[0].toString().split(".")[0].toInt()
//                                val maxValue = values[1].toString().split(".")[0].toInt()
//                                txtAgeRange.text = resources.getString(
//                                    R.string.between_height_cm,
//                                    minValue.toString(),
//                                    maxValue.toString()
//                                )
//                                startHeightCM = minValue
//                                endHeightCM = maxValue
//                            })
//                        }
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    Log.d("exceptionHeight",e.toString())
//                }
//            }
//            try {
//                setBottomDialogWindowAttributes()
//                show()
//                pickerBinding.imgDialogGradient.setDialogFadeInAnimation()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}