package com.swipefwd.ui.home.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.swipefwd.R
import com.swipefwd.data.models.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityProfileBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.updateuserprofile.UserProfileActivity
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val mActivity by lazy {
        this@ProfileActivity
    }
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var userId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onResume() {
        super.onResume()
        getUserProfileDetails()
    }

    private fun init() {
        binding.apply {
            mActivity.setShaderView(
                ivLogo,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            lifecycleScope.launch {
                userId = profileViewModel.getUserId.firstOrNull() ?: 0
            }
            ivBack.setOnClickListener {
                onBackPressed()
            }

            btnEdit.setOnClickListener {
                startActivity(
                    Intent(
                        this@ProfileActivity,
                        UserProfileActivity::class.java
                    ).putExtra("EditProfile", true)
                )
//                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            initObservers()
        }
    }

    private fun getUserProfileDetails() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getUserProfileDetails()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { ,  ->
//                    getUserProfileDetails()
//                }
            }
            else -> {
                lifecycleScope.launch {
                   /* profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        profileViewModel.getUserProfileDetails(
                            true,"Bearer $authToken", userId
                        )
                    }*/
                }
            }
        }
    }

    private fun initObservers() {
        profileViewModel.apply {
            showLoading.observe(mActivity) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(mActivity) {
                mActivity.showSnackBar(binding.layoutMain, it)
            }
            imagesData.observe(mActivity) { imagesList ->
                if (!imagesList.isNullOrEmpty()) {
                    binding.ivUserDefaultImage.visibility = View.GONE
                    binding.ivUserImage.visibility = View.VISIBLE
                    if (imagesList.isNotEmpty()) {
                        imagesList.onEach {
                            it.imageUrl = it.image
                            it.is_more = true
                        }.find {
                            it.isProfile == true
                        }.let {
                            it?.is_more = false
                            Glide.with(mActivity)
                                .load(it?.imageUrl)
                                // .diskCacheStrategy(DiskCacheStrategy.NONE)
                                //  .skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(binding.ivUserImage)
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_PROFILE_IMAGE,
                                it?.imageUrl.toString()
                            )
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
                    lifecycleScope.launch {
                        setDefaultImage(profileViewModel.getFirstName.firstOrNull() ?: "","")
                    }
                    /*Glide.with(mActivity)
                        .load(
                            "" +
                                    ""
                        ).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivUserImage)
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_PROFILE_IMAGE,
                        ""
                    )*/
                }
            }
            imagesError.observe(mActivity) {

            }
            userProfileData.observe(mActivity) {
                binding.apply {
                    val dob = it.data?.dob!!.split("-") //1996-06-20 (actual format from server)
                    val age =
                        AppUtils.getAgeFromCurrentDate(
                            dob[0].toInt(),
                            dob[1].toInt(),
                            dob[2].toInt()
                        )
                    txtName.text = getString(R.string.user_name_age, it.data.firstName, age)
                    val workList = ArrayList<String>()
                    if (!it.data.occupationTitle.isNullOrEmpty())
                        workList.add(it.data.occupationTitle)
                    if (!it.data.occupationCompany.isNullOrEmpty())
                        workList.add(it.data.occupationCompany)
                    workList.isNotEmpty().apply {
                        txtWork.text = workList.joinToString(" at ")
                    }
                    if (!it.data.location.isNullOrEmpty() && it.data.location != "null") {
                        txtLocation.apply {
                            visibility = View.VISIBLE
                            text = it.data.location
                        }
                    } else {
                        txtLocation.visibility = View.GONE
                    }
                }

                profileViewModel.savePreference(
                    PreferenceKeys.PREF_FIRST_NAME,
                    it.data?.firstName ?: ""
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_LAST_NAME,
                    it.data?.lastName ?: ""
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_PROFILE_PERCENTAGE,
                    it.data?.percentage ?: 0
                )
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
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_GENDER,
                    responseGender.toString()
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_AREA,
                    it.data.location ?: ""
                )
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
                if (it.data.heightIn != null) {
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
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_IS_SHOW_PROFILE,
                    it.data.instagramPosts ?: false
                )
                if (!it.data.language.isNullOrEmpty()) {
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_LANGUAGE,
                        Gson().toJson(it.data.language)
                    )
                }
                val occupation = OccupationModel()
                occupation.company = it.data.occupationCompany
                occupation.title = it.data.occupationTitle
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_OCCUPATION,
                    Gson().toJson(occupation)
                )
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
                }
                if (!it.data.castes.isNullOrEmpty()) {
                    val castModel = CastListModel.CastModel()
                    castModel.name = it.data.castes[0]?.name
                    castModel.id = it.data.castes[0]?.id
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_CAST,
                        Gson().toJson(castModel)
                    )
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
                }
                if (!it.data.vaccines.isNullOrEmpty()) {
                    val covidVaccineModel = CovidVaccineListModel.CovidModel()
                    covidVaccineModel.id = it.data.vaccines[0]?.id
                    covidVaccineModel.name = it.data.vaccines[0]?.name
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_COVID,
                        Gson().toJson(covidVaccineModel)
                    )
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
                }
            }
            userProfileError.observe(mActivity) {
                mActivity.showSnackBar(binding.layoutMain, it.message.toString())
            }
        }
    }
    private fun setDefaultImage(fName: String,image: String) {
        if (fName.isNotEmpty()) {
            val textDrawable = createPlaceholderImage(
                fName,
                200,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3,
                false
            )
            binding.ivUserDefaultImage.visibility = View.VISIBLE
            binding.ivUserImage.visibility = View.INVISIBLE
            Glide.with(mActivity)
                .load(image).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(textDrawable)
                .into(binding.ivUserDefaultImage)
        }
    }
}