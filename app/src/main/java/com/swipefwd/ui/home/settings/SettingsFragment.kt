package com.swipefwd.ui.home.settings

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.swipefwd.BuildConfig
import com.swipefwd.R
import com.swipefwd.data.models.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.DeleteAccountBinding
import com.swipefwd.databinding.FragmentSettingsBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.auth.login.LoginActivity
import com.swipefwd.ui.home.settings.tutorials.TutorialActivity
import com.swipefwd.ui.main.WebViewActivity
import com.swipefwd.ui.profile.PreferencesActivity
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.removePreference
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ChangeScreenListener
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val settingsViewModel: SettingsViewModel by viewModels {
        viewModelFactory { SettingsViewModel(requireContext(), AppRepository()) }
    }
    private val mActivity by lazy {
        requireActivity()
    }
    private var userId = 0
    private var fName = ""
    private var accountType = ""
    private var listener: ChangeScreenListener? = null
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private var percentage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            activity?.setShaderView(
                ivLogo,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            imgSettings.setOnClickListener {
                activity?.nextActivity(ProfileActivity::class.java)
            }
            btnTerms.setOnClickListener {
                startActivity(
                    Intent(
                        mActivity,
                        WebViewActivity::class.java
                    ).putExtra("url", AppConstants.TERM_CONDITION)
                )

            }
            btnPrivacy.setOnClickListener {
                startActivity(
                    Intent(
                        mActivity,
                        WebViewActivity::class.java
                    ).putExtra("url", AppConstants.PRIVACY_POLICY)
                )
            }
            btnDeleteacc.setOnClickListener {
                openDeleteDialog()

            }
            btnVersion.text = "V"+" "+BuildConfig.VERSION_NAME
            btnLogout.setOnClickListener {
                settingsViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "9")

                AppUtils.removePrefData(mActivity)
                activity?.nextActivity(LoginActivity::class.java)
                activity?.finish()
                activity?.finishAffinity()
                //   userLogout()
            }
            btnWallet.setOnClickListener {
                listener?.setScreen(1)
            }
            imgCancel.setOnClickListener {
                //onBackPressed()
            }

            btnAccount.setOnClickListener {
                activity?.nextActivity(AccountActivity::class.java)
            }

            btnContact.setOnClickListener {
                activity?.nextActivity(ContactActivity::class.java)
            }

            btnDatingPref.setOnClickListener {
                startActivity(
                    Intent(
                        requireActivity(),
                        PreferencesActivity::class.java
                    ).putExtra("EditProfile", true)
                )
//                activity?.overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                activity?.overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            btnTutorials.setOnClickListener {
                activity?.nextActivity(TutorialActivity::class.java)
            }
            btnSubscription.setOnClickListener {
                activity?.nextActivity(UserSubscriptionActivity::class.java)
            }
            initObservers()
        }
    }

    private fun openDeleteDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        val dialogBinding = DeleteAccountBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(dialogBinding.root)
            dialogBinding.apply {
                btnYes.apply {
                    setOnClickListener {
                        dismiss()
                        //delete user api call
                        settingsViewModel.deleteUserAccount()

                    }
                }

                btnNo.apply {
                    setOnClickListener {
                        dismiss()

                    }
                }
            }
            setZoomDialogWindowAttributes()
            show()
            dialogBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            userId = settingsViewModel.getUserId.firstOrNull() ?: 0
            fName = settingsViewModel.getFirstName.firstOrNull() ?: ""
            accountType = settingsViewModel.getAccountType.firstOrNull() ?: ""
            binding.txtName.text = fName
            when (accountType) {
                AppUtils.AccountTypes.Dater -> {
                    percentage = 5
                }
                AppUtils.AccountTypes.Matchmaker -> {
                    percentage = 20
                }
            }
            //getUserProfileDetails()
            //setting profile image
            val image = settingsViewModel.getProfileImage.firstOrNull()
            binding.apply {
                if (fName.isNotEmpty()) {
                    val textDrawable = requireContext().createPlaceholderImage(
                        fName,
                        200,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )

                    Glide.with(mActivity)
                        .load(image).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable)
                        .into(imgSettings)
                }
                when (accountType) {
                    AppUtils.AccountTypes.Dater -> {
                        btnSubscription.visibility = View.VISIBLE
                        getUserPreferenceDetails()
                    }
                    AppUtils.AccountTypes.Matchmaker -> {
                        btnDatingPref.visibility = View.GONE
                        btnSubscription.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun getUserPreferenceDetails() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { getUserPreferenceDetails() }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getUserPreferenceDetails()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        settingsViewModel.getUserPreferenceDetails(
                            "Bearer $authToken", userId
                        )
                    }
                }
            }
        }
    }

    private fun initObservers() {
        settingsViewModel.apply {
            errorMessage.observe(mActivity) {
                Log.e("TAG", "ERROR === $it")
            }
            settingsViewModel.deleteAccount.observe(mActivity)
            {
                if (it.code == 1) {
                    AppUtils.removePrefData(mActivity)
                    activity?.nextActivity(LoginActivity::class.java)
                    activity?.finish()
                    activity?.finishAffinity()
                }

            }
            userPreferenceData.observe(mActivity) { model ->
                Log.e("TAG", "SUCCESS=== $model")
                val data = model.data
                val gender = when (data?.gender) {
                    AppConstants.USER_MALE -> "0"
                    AppConstants.USER_FEMALE -> "1"
                    AppConstants.USER_NON_BINARY -> "2"
                    else -> "0"
                }
                settingsViewModel.savePreference(PreferenceKeys.PREF_PREF_GENDER, gender)
                settingsViewModel.savePreference(
                    PreferenceKeys.PREF_START_AGE,
                    data?.ageStart ?: 18
                )
                settingsViewModel.savePreference(PreferenceKeys.PREF_END_AGE, data?.ageEnd ?: 99)
                settingsViewModel.savePreference(
                    PreferenceKeys.PREF_MAX_DISTANCE,
                    data?.maxDistance ?: 160
                )
                settingsViewModel.savePreference(
                    PreferenceKeys.PREF_IS_VERIFIED,
                    data?.isProfileVerified ?: false
                )
                settingsViewModel.savePreference(
                    PreferenceKeys.PREF_PREF_START_HEIGHT_INCHES,
                    data?.startHeight ?: 0
                )
                settingsViewModel.savePreference(
                    PreferenceKeys.PREF_PREF_END_HEIGHT_INCHES,
                    data?.endHeight ?: 0
                )
                if (data?.startHeight != null) {
                    val startHeightCM = (data.startHeight.div(0.3937)).toInt()
                    settingsViewModel.savePreference(
                        PreferenceKeys.PREF_PREF_START_HEIGHT,
                        startHeightCM.toString()
                    )
                }
                if (data?.endHeight != null) {
                    val endHeightCM = (data.endHeight.div(0.3937)).toInt()
                    settingsViewModel.savePreference(
                        PreferenceKeys.PREF_PREF_END_HEIGHT,
                        endHeightCM.toString()
                    )
                }
                if (!data?.education.isNullOrEmpty()) {
                    val educationModel = EducationModel()
                    educationModel.graduation_year = data?.graduationYear
                    educationModel.level = data?.education?.get(0)?.name
                    educationModel.institute = data?.institution
                    educationModel.level_id = data?.education?.get(0)?.id
                    settingsViewModel.savePreference(
                        PreferenceKeys.PREF_PREF_EDUCATION,
                        Gson().toJson(educationModel)
                    )
                }
                if (!data?.astrologicalSign.isNullOrEmpty()) {
                    data?.astrologicalSign?.onEach {
                        it?.isSelected = true
                    }.let {
                        settingsViewModel.savePreference(
                            PreferenceKeys.PREF_PREF_ASTROLOGY_SIGN,
                            Gson().toJson(it)
                        )
                    }

                }
                if (!data?.cast.isNullOrEmpty()) {
                    settingsViewModel.savePreference(
                        PreferenceKeys.PREF_PREF_CAST,
                        Gson().toJson(data?.cast)
                    )
                }
                if (!data?.religion.isNullOrEmpty()) {
                    data?.religion?.onEach {
                        it?.isSelected = true
                    }.let {
                        settingsViewModel.savePreference(
                            PreferenceKeys.PREF_PREF_RELIGION,
                            Gson().toJson(it)
                        )
                    }
                }
                if (!data?.children.isNullOrEmpty()) {
                    val childrenModel = ChildrenListModel.ChildrenModel()
                    childrenModel.id = data?.children?.get(0)?.id
                    childrenModel.isSelected = true
                    childrenModel.name = data?.children?.get(0)?.name
                    settingsViewModel.savePreference(
                        PreferenceKeys.PREF_PREF_CHILDREN,
                        Gson().toJson(childrenModel)
                    )
                }
                if (!data?.smoking.isNullOrEmpty()) {
                    val smokingModel = SmokingListModel.SmokingModel()
                    smokingModel.id = data?.smoking?.get(0)?.id
                    smokingModel.isSelected = true
                    smokingModel.name = data?.smoking?.get(0)?.name
                    settingsViewModel.savePreference(
                        PreferenceKeys.PREF_PREF_SMOKING,
                        Gson().toJson(smokingModel)
                    )
                }
                if (!data?.relationship.isNullOrEmpty()) {
                    val relationshipModel = RelationshipListModel.RelationshipModel()
                    relationshipModel.id = data?.relationship?.get(0)?.id
                    relationshipModel.isSelected = true
                    relationshipModel.name = data?.relationship?.get(0)?.name
                    settingsViewModel.savePreference(
                        PreferenceKeys.PREF_PREF_RELATIONSHIP,
                        Gson().toJson(relationshipModel)
                    )
                }
                if (!data?.language.isNullOrEmpty()) {
                    settingsViewModel.savePreference(
                        PreferenceKeys.PREF_PREF_LANGUAGE,
                        Gson().toJson(data?.language)
                    )
                }
                val isHeight = when (data?.heightIn) {
                    AppConstants.HEIGHT_CM -> {
                        true
                    }
                    AppConstants.HEIGHT_FEET -> {
                        false
                    }
                    else -> {
                        false
                    }
                }
                settingsViewModel.savePreference(
                    PreferenceKeys.PREF_PREF_IS_HEIGHT_FEET,
                    isHeight
                )
            }
            userPreferenceError.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.response.toString())
            }
            logoutData.observe(mActivity) {
                if (it.response == "success") {
                    settingsViewModel.apply {
                        var isNotInterested: Boolean
                        var isInterested: Boolean
                        var isGreenProfileTip: Boolean
                        var isAccountTip: Boolean
                        var isSMSsent: Boolean
                        lifecycleScope.launch {
                            isNotInterested =
                                settingsViewModel.isNotInterested.firstOrNull() ?: false
                            isInterested = settingsViewModel.isInterested.firstOrNull() ?: false
                            isGreenProfileTip =
                                settingsViewModel.getGreenShowTip.firstOrNull() ?: false
                            isAccountTip =
                                settingsViewModel.getAccountTipShow.firstOrNull() ?: false
                            isSMSsent =
                                settingsViewModel.isSmsSent.firstOrNull() ?: false
                            withContext(Dispatchers.Main) {
                                removePreference(
                                    isNotInterested,
                                    isInterested,
                                    isGreenProfileTip,
                                    isAccountTip,
                                    isSMSsent,
                                    requireContext()
                                )
                            }
                            deleteDatabase(requireContext(), userId)
                            activity?.nextActivity(LoginActivity::class.java)
                            activity?.finish()
                            activity?.finishAffinity()
                        }
                    }
                }
            }
        }
        profileViewModel.apply {
            imagesData.observe(mActivity) { imagesList ->
                if (!imagesList.isNullOrEmpty()) {
                    Log.e("TAG:", "IMAGES: == $imagesList")
                    if (imagesList.isNotEmpty()) {
                        imagesList.onEach {
                            it.imageUrl = it.image
                            it.is_more = true
                        }.find {
                            it.isProfile == true
                        }.let {
                            it?.is_more = false
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_PROFILE_IMAGE,
                                it?.imageUrl.toString()
                            )
                            if (!it?.imageUrl.isNullOrEmpty()) {
                                when (accountType) {
                                    AppUtils.AccountTypes.Dater -> {
                                        percentage += AppConstants.PROFILE_PICTURE_DATER
                                    }
                                    AppUtils.AccountTypes.Matchmaker -> {
                                        percentage += AppConstants.PROFILE_CONNECTOR
                                    }
                                }
                            }
                        }
                        imagesList.filter {
                            it.is_more == true
                        }.let {
                            if (it.isNotEmpty()) {
                                percentage += AppConstants.PROFILE_DATER
                            }
                        }
                    }
                    val instaList = ArrayList<String>()
                    if (imagesList.isNotEmpty()) {
                        imagesList.filter {
                            it.is_instagram == true
                        }.let {
                            it.onEach {
                                instaList.add(it.imageUrl.toString())
                            }
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_INSTA_IMAGES,
                                Gson().toJson(instaList)
                            )
                        }
                    }
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_IMAGES,
                        Gson().toJson(imagesList)
                    )
                } else {
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_PROFILE_IMAGE,
                        ""
                    )
                }
                updateProgress()
            }
            imagesError.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
            }
            userProfileData.observe(mActivity) {
                Log.e("TAG", "SUCCESS=== $it")
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_FIRST_NAME,
                    it.data?.firstName ?: ""
                )
                if (!it.data?.firstName.isNullOrEmpty()) {
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            percentage += AppConstants.PROFILE_DATER
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            percentage += AppConstants.PROFILE_CONNECTOR
                        }
                    }
                }
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_LAST_NAME,
                    it.data?.lastName ?: ""
                )
                if (!it.data?.lastName.isNullOrEmpty()) {
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            percentage += AppConstants.PROFILE_DATER
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            percentage += AppConstants.PROFILE_CONNECTOR
                        }
                    }
                }
                val year = it.data?.dob!!.split("-")[0] //"1990-12-21"
                val month = it.data.dob.split("-")[1]
                val date = it.data.dob.split("-")[2]
                val responseDob = "$month/$date/$year"
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_DOB,
                    responseDob
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_CONVERTED_DOB,
                    it.data.dob
                )
                if (!it.data.dob.isNullOrEmpty()) {
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            percentage += AppConstants.PROFILE_DATER
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            percentage += AppConstants.PROFILE_CONNECTOR
                        }
                    }
                }
                val responseGender = when (it.data.gender) {
                    AppConstants.USER_MALE -> {
                        0
                    }
                    AppConstants.USER_FEMALE -> {
                        1
                    }
                    AppConstants.USER_NON_BINARY -> {
                        2
                    }
                    else -> {
                        0
                    }
                }

//                Add percentage for gender
//                if (!it.data.gender.isNullOrEmpty()) {
//                    Log.d("percentageb" , "$percentage")
//                    percentage += 5
//                    Log.d("percentageA" , "$percentage")
//                }


                profileViewModel.savePreference(
                    PreferenceKeys.PREF_GENDER,
                    responseGender.toString()
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_AREA,
                    it.data.location ?: ""
                )
                if (!it.data.location.isNullOrEmpty()) {
                    percentage += AppConstants.PROFILE_DATER
                }
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_LAT,
                    it.data.latitude?.toDouble() ?: 0.0
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_LONG,
                    it.data.longitude?.toDouble() ?: 0.0
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_BIO,
                    it.data.bio ?: ""
                )
                if (!it.data.bio.isNullOrEmpty()) {
                    percentage += AppConstants.PROFILE_DATER
                }
                if (!it.data.heightIn.isNullOrEmpty()) {
                    val responseHeight = when (it.data.heightIn) {
                        AppConstants.HEIGHT_CM -> {
                            (it.data.height!!.div(0.3937)).toInt().toString()
                        }
                        AppConstants.HEIGHT_FEET -> {
                            val feet = (it.data.height!!.toFloat() / 12).toInt()
                            val inches = it.data.height.minus(feet * 12)
                            resources.getString(
                                R.string.feet_inches,
                                feet.toString(),
                                inches.toString()
                            )
                        }
                        else -> {
                            val feet = (it.data.height!!.toFloat() / 12).toInt()
                            val inches = it.data.height.minus(feet * 12)
                            resources.getString(
                                R.string.feet_inches,
                                feet.toString(),
                                inches.toString()
                            )
                        }
                    }
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_HEIGHT,
                        responseHeight
                    )
                    percentage += AppConstants.PROFILE_DATER
                }
                val isHeight = when (it.data.heightIn) {
                    AppConstants.HEIGHT_CM -> {
                        true
                    }
                    AppConstants.HEIGHT_FEET -> {
                        false
                    }
                    else -> {
                        false
                    }
                }
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_IS_HEIGHT_FEET,
                    isHeight
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_INSTA_ID,
                    it.data.instagramId ?: ""
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_INSTA_NAME,
                    it.data.instagramName ?: ""
                )
                if (!it.data.instagramName.isNullOrEmpty()) {
                    percentage += AppConstants.PROFILE_DATER
                }
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_IS_SHOW_PROFILE,
                    it.data.instagramPosts ?: false
                )
                if (!it.data.language.isNullOrEmpty()) {
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_LANGUAGE,
                        Gson().toJson(it.data.language)
                    )
                    percentage += AppConstants.PROFILE_DATER
                }
                val occupation = OccupationModel()
                occupation.company = it.data.occupationCompany
                occupation.title = it.data.occupationTitle
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_OCCUPATION,
                    Gson().toJson(occupation)
                )
                if (!it.data.occupationCompany.isNullOrEmpty() || !it.data.occupationTitle.isNullOrEmpty()) {
                    percentage += AppConstants.PROFILE_DATER
                }
                if (!it.data.educations.isNullOrEmpty()) {
                    val educationModel = EducationModel()
                    educationModel.apply {
                        graduation_year = it.data.graduationYear
                        level = it.data.educations[0]?.name
                        institute = it.data.institution
                        level_id = it.data.educations[0]?.id
                    }
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_EDUCATION,
                        Gson().toJson(educationModel)
                    )
                    percentage += AppConstants.PROFILE_DATER
                }
                if (!it.data.castes.isNullOrEmpty()) {
                    val castModel = CastListModel.CastModel()
                    castModel.name = it.data.castes[0]?.name
                    castModel.id = it.data.castes[0]?.id
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_CAST,
                        Gson().toJson(castModel)
                    )
                    percentage += AppConstants.PROFILE_DATER
                }
                if (!it.data.childrens.isNullOrEmpty()) {
                    val childrenModel = ChildrenListModel.ChildrenModel()
                    childrenModel.id = it.data.childrens[0]?.id
                    childrenModel.isSelected = true
                    childrenModel.name = it.data.childrens[0]?.name
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_CHILDREN,
                        Gson().toJson(childrenModel)
                    )
                    percentage += AppConstants.PROFILE_DATER
                }
                if (!it.data.religions.isNullOrEmpty()) {
                    val religionModel = ReligionListModel.ReligionModel()
                    religionModel.id = it.data.religions[0]?.id
                    religionModel.isSelected = true
                    religionModel.name = it.data.religions[0]?.name
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_RELIGION,
                        Gson().toJson(religionModel)
                    )
                    percentage += AppConstants.PROFILE_DATER
                }
                if (!it.data.smokings.isNullOrEmpty()) {
                    val smokingModel = SmokingListModel.SmokingModel()
                    smokingModel.id = it.data.smokings[0]?.id
                    smokingModel.isSelected = true
                    smokingModel.name = it.data.smokings[0]?.name
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_SMOKING,
                        Gson().toJson(smokingModel)
                    )
                    percentage += AppConstants.PROFILE_DATER
                }
                if (!it.data.lookings.isNullOrEmpty()) {
                    val relationshipModel = RelationshipListModel.RelationshipModel()
                    relationshipModel.id = it.data.lookings[0]?.id
                    relationshipModel.isSelected = true
                    relationshipModel.name = it.data.lookings[0]?.name
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_RELATIONSHIP,
                        Gson().toJson(relationshipModel)
                    )
                    percentage += AppConstants.PROFILE_DATER
                }
                if (!it.data.vaccines.isNullOrEmpty()) {
                    val covidVaccineModel = CovidVaccineListModel.CovidModel()
                    covidVaccineModel.id = it.data.vaccines[0]?.id
                    covidVaccineModel.name = it.data.vaccines[0]?.name
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_COVID,
                        Gson().toJson(covidVaccineModel)
                    )
                    percentage += AppConstants.PROFILE_DATER
                }
                if (!it.data.astrologicalSigns.isNullOrEmpty()) {
                    val sunsignModel = AstroSignListModel.AstroSignModel()
                    sunsignModel.id = it.data.astrologicalSigns[0]?.id
                    sunsignModel.isSelected = true
                    sunsignModel.name = it.data.astrologicalSigns[0]?.name
                    sunsignModel.icon = it.data.astrologicalSigns[0]?.icon
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_ASTROLOGY_SIGN,
                        Gson().toJson(sunsignModel)
                    )
                    percentage += AppConstants.PROFILE_DATER
                }

                //showing percentage
                updateProgress()
            }
            userProfileError.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.message.toString())
            }
        }
    }

    private fun updateProgress() {
        binding.apply {
            //showing percentage
            profileViewModel.savePreference(
                PreferenceKeys.PREF_PROFILE_PERCENTAGE,
                percentage
            )
            binding.apply {
                if (percentage != 0) {
                    txtProgress.visibility = View.VISIBLE
                    progressSettings.visibility = View.VISIBLE
                    if (percentage > 100) {
                        progressSettings.setProgress(100f, true)
                    } else {
                        progressSettings.setProgress(percentage.toFloat(), true)
                    }
                    Log.d("TAG:", "PER: $percentage")
                    progressSettings.setOnProgressChangeListener {
                        txtProgress.text = "${it.toInt()}%"
                    }
                } else {
                    txtProgress.visibility = View.GONE
                    progressSettings.visibility = View.GONE
                }
            }
        }
    }

    private fun userLogout() {
        if (!AppUtils.isNetworkAvailable(mActivity)) {
            openFailNetworkDialog() { userLogout() }
//            AppUtils.showMessageOK(
//                mActivity,
//                getString(R.string.app_name),
//                getString(R.string.common_retry),
//                getString(R.string.no_internet)
//            ) { _, _ ->
//                userLogout()
//            }
        } else {
            lifecycleScope.launch {
                settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                    settingsViewModel.userLogout(
                        "Bearer $authToken"
                    )
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ChangeScreenListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun getUserProfileDetails() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { getUserProfileDetails() }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getUserProfileDetails()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        /*  profileViewModel.getUserProfileDetails(
                              false, "Bearer $authToken", userId
                          )*/ //commented by nirali
                    }
                }
            }
        }
    }
}