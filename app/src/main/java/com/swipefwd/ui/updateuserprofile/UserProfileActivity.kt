package com.swipefwd.ui.updateuserprofile

import ProfilePhotosModel
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.hardware.Camera
import android.net.Uri
import android.os.*
import android.provider.Settings
import android.renderscript.Allocation
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup
import com.android.billingclient.api.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.ramijemli.percentagechartview.renderer.BaseModeRenderer.GRADIENT_LINEAR
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import com.skydoves.balloon.overlay.BalloonOverlayOval
import com.swipefwd.BuildConfig
import com.swipefwd.R
import com.swipefwd.animations.AnimationBounceDown
import com.swipefwd.animations.AnimationBounceUpAndSlideUp
import com.swipefwd.animations.SlideView
import com.swipefwd.data.models.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.*
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.activity.ActivityCameraVerification
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.home.message.SubscriptionPlansListAdapter
import com.swipefwd.ui.home.message.SubscriptionPlansPagerAdapter
import com.swipefwd.ui.home.settings.SettingsViewModel
import com.swipefwd.ui.main.WebViewActivity
import com.swipefwd.ui.profile.*
import com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel
import com.swipefwd.ui.video.VideoTutorial
import com.swipefwd.utils.*
import com.swipefwd.utils.AppConstants.BINARY
import com.swipefwd.utils.AppConstants.FEMALE
import com.swipefwd.utils.AppConstants.MALE
import com.swipefwd.utils.AppConstants.SCREEN_NAME
import com.swipefwd.utils.AppConstants.SCREEN_PROFILE
import com.swipefwd.utils.AppConstants.USER_FEMALE
import com.swipefwd.utils.AppConstants.USER_MALE
import com.swipefwd.utils.AppConstants.USER_NON_BINARY
import com.swipefwd.utils.AppUtils.createPlaceholderImage2
import com.swipefwd.utils.AppUtils.disableView
import com.swipefwd.utils.AppUtils.getExpiryDate
import com.swipefwd.utils.AppUtils.makeTextviewScrollable
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.AppUtils.showSnackBarMargin
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.utils.segmentedButtonTemp.SegmentedButtonGroup2
import com.swipefwd.utils.wheelPicker.WheelPicker
import com.yuyakaido.android.cardstackview.internal.DisplayUtil
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.*
import java.lang.reflect.Type
import java.net.URL
import java.net.URLConnection
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.text.isEmpty as isEmpty1


class UserProfileActivity : AppCompatActivity(), SurfaceHolder.Callback, Camera.PictureCallback,
    PurchasesUpdatedListener {

    private var isProfileVerified: Int = 0
    private lateinit var binding: ActivityUserProfileBinding
    private var isDobDialogOpen = false

    var h = 0
    var bitmap: Bitmap? = null
    var renderScript: RenderScript? = null
    var statusBarHeight = 30
    private val toolTipBinding by lazy {

        createBalloon(mActivity) {
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(380)
            setBackgroundColor(ContextCompat.getColor(mActivity, android.R.color.transparent))
            setLayout(R.layout.layout_custom_tool_tip)
            setArrowSize(10)
            setCornerRadius(0f)
            setWidthRatio(1f)
            setArrowColor(
                ContextCompat.getColor(
                    mActivity,
                    R.color.balloon_arrow_profile_activity
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
            setArrowAlignAnchorPadding(6)
            setLifecycleOwner(mActivity)
        }
    }

    //    private val toolTipBinding by balloon<ToolTipFactory>()
    private val mActivity by lazy { this@UserProfileActivity }
    private var destination: File? = null
    private var path: String? = ""
    private val requestImageCapture = 1112
    private val requestImageGallery = 1114
    private var ft = "4′"//"05'"
    private var inch = "10″"//"01\""
    private var cm = "147 cm"//"152 cm"
    private var feet = 4 //5
    private var inches = 10//1
    private var inCm = 147//152
    private var isMoreImages: Boolean = false
    private var preferenceRedirected: Boolean = false
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private val settingsViewModel: SettingsViewModel by viewModels {
        viewModelFactory { SettingsViewModel(mActivity, AppRepository()) }
    }
    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(applicationContext, AppRepository()) }
    }
    private var location = ""
    private var latitude = 0.0
    private var longitude = 0.0
    private var addMoreIndex = 0
    private var startForResult: ActivityResultLauncher<Intent>? = null
    private val photosAdapter by lazy {
        AddMorePhotosAdapter(mActivity, deleteListener = {
            isDeleteMore = true
            deletedId = it.position
            deleteUserImage(it.position, isDeleteMore)
        }, addImageListener = {
            addMoreIndex = it
            isMoreImages = true
            openSelectPhotoDialog()
//            requestRuntimePermission()
        })
    }
    private var isBioLocal = false
    private var accountType = ""
    private var accountUserType = 0
    private var userGender = "2"
    private var convertedDob = ""
    private var languagesList = ArrayList<LanguageDataModel.LanguageData.Language>()
    private var dob: String? = ""
    private var occupationModel = OccupationModel()
    private var educationModel = EducationModel()
    private var castModel = CastListModel.CastModel()

    // private var childrenModel = ChildrenListModel.ChildrenModel()
    private var childrenModel = ChildrenModel.ChildrenData.ChildrenLevel()

    // private var religionModel = ReligionListModel.ReligionModel()
    private var religionModel = ReligionModel.ReligionData.ReligionLevel()
    private var smokingModel = SmokingDataModel.SmokingData.Smoking()

    //  private var smokingModel = SmokingListModel.SmokingModel()
    // private var relationshipModel = RelationshipListModel.RelationshipModel()
    private var relationshipModel = RelationshipModel.RelationShipData.RelationshipLevel()

    //   private var vaccineStatusModel = CovidVaccineListModel.CovidModel()
    private var vaccineStatusModel = VaccinationModel.VaccinationData.VaccinationLevel()

    //private var astrologyModel = AstroSignListModel.AstroSignModel()
    private var astrologyModel = AstrologicalModel.AstrologicalData.AstrologicalSignLevel()
    private var socialMediaUserModel = SocialMediaUserModel()
    private var travelLocationModel = TravelLocationListModel()
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var socialMedia = ""
    private var uploadedImages = ArrayList<UserImagesResponseModel.Item>()
    private var uploadedMmoreImages = ArrayList<ProfilePhotosModel.DataProfile.UserPhotos>()
    private var deletedId: Int? = 0
    private var instagramStartForResult: ActivityResultLauncher<Intent>? = null
    private var instaImagesList = ArrayList<String>()
    private var instagramId = ""
    private var instagramUserName = ""
    private var isInstaShowPhoto = true
    private var mMonth = "01"
    private var mDay = "01"
    private var mYear = "1990"
    private var userId = 0
    private var convertedHeightInInches = 0 //5'1" ==> (5x12) + 1
    private var isInCM = false
    private var isEditLocation = true
    private var camera: Camera? = null
    private var surfaceHolder: SurfaceHolder? = null
    private var isPermissionGranted = false
    private var isHeightChanged = false
    private var profileValue = 0
    private var isFirst = false
    private var isLast = false
    private var isBio = false
    private var isDobAdded = false
    private var isHeightAdded = false
    private var isDeleteMore = false
    private var isAdd = false
    private var isFromSettings = false
    private lateinit var subscriptionsBinding: DialogSubscriptionPlansBinding
    private var isBooster = false
    private val plansAdapter by lazy {
        SubscriptionPlansListAdapter(mActivity) {
            subscriptionsBinding.apply {
                isBooster = false
                layoutPlan.background =
                    ContextCompat.getDrawable(mActivity, R.drawable.grey_border_bg)
                txtPlanMonth.setTextColor(
                    ContextCompat.getColor(
                        mActivity,
                        R.color.colorPagerDesc
                    )
                )
                txtPlanValue.setTextColor(
                    ContextCompat.getColor(
                        mActivity,
                        R.color.colorPagerDesc
                    )
                )
                txtPlanMonthOffer.setTextColor(
                    ContextCompat.getColor(
                        mActivity,
                        R.color.colorPagerDesc
                    )
                )
            }
        }
    }
    private var mAllSubList = ArrayList<AllSubPlansModel>()
    private val subIds = arrayListOf(
        "fwdpremmon",
        "fwdelimon",
        "3monthpremier",
        "3monthelite",
        "6monthpremier",
        "6monthelite"
    )
    private val boostersIds = arrayListOf(
        "rematchonly",
        "extendtimeonly",
        "superlikesonly",
        "unlocksonly",
        "recallonly",
        "locationchangeonly"
    )
    private var dataList: List<SkuDetails>? = arrayListOf()
    private var boostersList: ArrayList<SkuDetails?> = arrayListOf()
    private var orderId = ""
    private var productId = ""
    private var exDate = String()
    private val adapter by lazy {
        SubscriptionPlansPagerAdapter(mActivity)
    }
    private var boosterModel: SkuDetails? = null
    private var planModel = SettingSubscriptionModel.Plan()
    private var planType = ""
    private lateinit var billingClient: BillingClient
    private val outFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    }
    private var subscriptionDuration = "P1M"
    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    private var instagramModel = InstagramProfileModel()
    private var instagramAccessToken: String = ""
    private val imageSelector by lazy { ImageSelector(this) }

    private var mAnimation = SlideView()
    private var gestureVerificationStartForResult: ActivityResultLauncher<Intent>? = null
    private var mGestureVerificationByteArray: ByteArray? = null
    private var mGestureVerificationString: String? = ""
    private var mGestureVerificationImage: Bitmap? = null
    var filename = ""
    var customSnackbar: Snackbar? = null
    var dialogVerification: Dialog? = null

    var signUpType = 0
    var socialPrefData = ""
    var socialEmail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        //////////////////////////////////////////////////////////////////////////////
        setContentView(binding.root)
        //     changeStatusBarColorToBlack()
        renderScript = RenderScript.create(this)
//        val displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
//        val width: Int = displayMetrics.widthPixels
//        Log.d("Screen_Width", "$width")
//        if(width<=1440) {
//            h = 350
//        } else {
//
//        }

        val profilePic = AppUtils.getProfileImage(this@UserProfileActivity)
        val isVerified = AppUtils.getProfileVerified(this@UserProfileActivity)

        if (isVerified == 1) {
            println("oncreate isVerifiedBlock-------")
            isVerifiedBlock(true)
        } else {
            isVerifiedBlock(false)

        }

        if (profilePic.isNotBlank() && profilePic.isNotEmpty()) {
            binding.apply {

                imgSettings.apply {
                    visibility = View.VISIBLE
                    Glide.with(mActivity)
                        .asBitmap()
                        .load(profilePic)
                        //   .thumbnail(/*sizeMultiplier=*/ 0.25f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(this)
                }
                addImagerl.visibility = View.INVISIBLE
                ivClose.visibility = View.VISIBLE
            }
        }
        signUpType = AppUtils.getSignupType(this)

        socialPrefData = AppUtils.getSocialMediaData(this)


        if (socialPrefData.isNotEmpty()) {
            socialMediaUserModel = Gson().fromJson<Any>(
                socialPrefData,
                SocialMediaUserModel::class.java
            ) as SocialMediaUserModel

        }

        println("signUpType======>>>>${signUpType}")

        statusBarHeight = AppUtils.getStatusBarHeight(this)
        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    location = result.data?.getStringExtra("location") ?: ""
                    latitude = result.data?.getDoubleExtra("latitude", 0.0) ?: 0.0
                    longitude = result.data?.getDoubleExtra("longitude", 0.0) ?: 0.0


                    AppUtils.storeLogitude(this, longitude.toLong())
                    AppUtils.storeLatitude(this, latitude.toLong())
                    AppUtils.storeLocation(this, location)

                    profileValue += AppConstants.PROFILE_DATER
                    binding.apply {
                        edtLocation.apply {
                            setTextColor(ContextCompat.getColor(mActivity, R.color.colorGrey))
                            background =
                                ContextCompat.getDrawable(mActivity, R.drawable.corner_grey_bg)
                            setText(location)
                            setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, 0, 0)
                            compoundDrawablePadding = 10
                        }
                    }
                    AppUtils.storeLocation(this, location)
                    AppUtils.storeLatitude(this, latitude.toLong())
                    AppUtils.storeLogitude(this, longitude.toLong())
                    //  profileViewModel.savePreference(PreferenceKeys.PREF_AREA, location)
                    //profileViewModel.savePreference(PreferenceKeys.PREF_LAT, latitude)
                    //profileViewModel.savePreference(PreferenceKeys.PREF_LONG, longitude)
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_IS_ONBOARD_TUTORIAL_COMPLETED,
                        true
                    )
                    if (!isFromSettings) {
                        if (!isEditLocation) {
                            binding.btnContinue.performClick()
                        }
                    }
                }
            }
        gestureVerificationStartForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    var bmp: Bitmap? = null
                    filename = result.data?.extras?.getString("verification_picture")!!
                    try {
                        val `is` = openFileInput(filename)
                        bmp = BitmapFactory.decodeStream(`is`)
                        `is`.close()
                        mGestureVerificationImage = bmp
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                    sendImageVerification()
                    //commented by nirali
                   /* try {
                        val `is` = openFileInput(filename)
                        bmp = BitmapFactory.decodeStream(`is`)
                        `is`.close()

                        val options = AccuratePoseDetectorOptions.Builder()
                            .setDetectorMode(AccuratePoseDetectorOptions.SINGLE_IMAGE_MODE)
                            .build()

                        val poseDetector = PoseDetection.getClient(options)

                        val image = InputImage.fromBitmap(bmp, 0)

                        poseDetector.process(image).addOnSuccessListener {
                            print("onSuccess Detected ${it.allPoseLandmarks.size} objects")
                            var isLeftEye = false
                            var isRightEye = false
                            var isRightEar = false
                            var isLeftEar = false
                            var isLeftMouth = false
                            var isRightMouth = false
                            var isLeftWrist = false
                            var isLeftThumb = false
                            var isLeftIndex = false
                            var isLeftPinky = false
                            it.allPoseLandmarks.forEach {
                                if (it.inFrameLikelihood >= 0.6) {
                                    when (it.landmarkType) {
                                        PoseLandmark.LEFT_EYE -> {
                                            isLeftEye = true
                                        }
                                        PoseLandmark.RIGHT_EYE -> {
                                            isRightEye = true
                                        }
                                        PoseLandmark.RIGHT_EAR -> {
                                            isRightEar = true
                                        }
                                        PoseLandmark.LEFT_EAR -> {
                                            isLeftEar = true
                                        }
                                        PoseLandmark.RIGHT_MOUTH -> {
                                            isRightMouth = true
                                        }
                                        PoseLandmark.LEFT_MOUTH -> {
                                            isLeftMouth = true
                                        }
                                        PoseLandmark.RIGHT_WRIST -> {
                                            isLeftWrist = true
                                        }
                                        PoseLandmark.RIGHT_THUMB -> {
                                            isLeftThumb = true
                                        }
                                        PoseLandmark.RIGHT_INDEX -> {
                                            isLeftIndex = true
                                        }
                                        PoseLandmark.RIGHT_PINKY -> {
                                            isLeftPinky = true
                                        }
                                    }
                                }
                            }
                            if (isLeftEye && isRightEye && isLeftEar && isRightEar && isLeftThumb && isLeftMouth && isRightMouth) {
                                if (isLeftWrist && isLeftThumb && isLeftIndex && isLeftPinky) {
                                    println("ml kit issues-------->>>000000000000")

                                    mGestureVerificationImage = bmp
                                    openFinalGestureDialog(true)
                                } else {
                                    //  openVerificationProblemDialog() // need to show gesture dialog
                                    mGestureVerificationImage = bmp
                                    openFinalGestureDialog(false)


                                    println("ml kit issues-------->>>11111111111111")

                                }
                            } else {
                                mGestureVerificationImage = bmp
                                //  Toast.makeText(this,"we are...",Toast.LENGTH_LONG).show()
                                // openVerificationProblemDialog() // need to show gesture dialog
                                openFinalGestureDialog(false)

                                println("ml kit issues-------->>>2222222222")


                            }
                        }
                            .addOnFailureListener {
                                mGestureVerificationImage = bmp
                                // openVerificationProblemDialog()
                                openFinalGestureDialog(false)

                                println("ml kit issues-------->>>33333333")

                                print("onFailureOfDetector ${it.message}")
                                it.printStackTrace()
                            }
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }*/
                }
            }
        /*     gestureVerificationStartForResult =
                 registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                     if (result.resultCode == Activity.RESULT_OK) {
                         var bmp: Bitmap? = null
                         filename = result.data?.extras?.getString("verification_picture")!!
                         try {
                             val `is` = openFileInput(filename)
                             bmp = BitmapFactory.decodeStream(`is`)
                             `is`.close()
                             mGestureVerificationImage = bmp
                         } catch (e: java.lang.Exception) {
                             e.printStackTrace()
                         }
                         openFinalGestureDialog()
     //                    mGestureVerificationByteArray = result.data?.getByteArrayExtra("verification_picture")
     //                    mGestureVerificationImage = result.data?.getParcelableExtra("verification_picture")
     //                    mGestureVerificationString = result.data?.getStringExtra("verification_picture")
     //                    mGestureVerificationImage = ActivityCameraVerification().stringToBitMap(mGestureVerificationString)
                     }
                 }*/

        instagramStartForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val code = result.data?.getStringExtra("instagramCode") ?: ""
                    Log.e("TAG", "INSTA OK == $code")
                    profileViewModel.getInstagramToken(code)
                }
            }

        ///////////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////////////////////////////////////////////////////////////////////
        //click listener


        ///////////////////////////////////////////////////////////////////////////////////////////
        //observer


        ///////////////////////////////////////////////////////////////////////////////////////////
        //image selector callback
        imageSelector.setCallback(imageSelectorCallback)
        ///////////////////////////////////////////////////////////////////////////////////////////
        init()
        initObservers()
    }


    override fun onResume() {
        super.onResume()
        preferenceRedirected = false
        if (!isFromSettings) {
            profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "2")
        }

        SCREEN_NAME = SCREEN_PROFILE
        if (SCREEN_NAME == SCREEN_PROFILE) {
            AppUtils.storeProfileORPref(this, 0)
        }
        lifecycleScope.launch {
            try {
                binding.edtLastName.clearFocus()
                getSelectedData()
                if (profileViewModel.isDobDialogOpen.firstOrNull() == true) {
                    val age = AppUtils.getAgeFromCurrentDate(
                        mYear.toInt(),
                        mMonth.toInt(),
                        mDay.toInt()
                    )
                    if (age.toInt() < 18) {
                        openDOBDialog(true)
                    } else {
                        openDOBDialog()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
//        try {
//            binding.edtLastName.clearFocus()
//            getSelectedData()
//            if (isDobDialogOpen) {
//                val age = AppUtils.getAgeFromCurrentDate(
//                    mYear.toInt(),
//                    mMonth.toInt(),
//                    mDay.toInt()
//                )
//                if (age.toInt() < 18) {
//                    openDOBDialog(true)
//                }
//               // else
//                 //   openDOBDialog()
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    private fun init() {
//        getInviterGender()
        binding.apply {
//            if(isTaskRoot)
//                ivBack.visibility = View.GONE
            lifecycleScope.launch {
                userId = profileViewModel.getUserId.firstOrNull() ?: 0
                // accountType = profileViewModel.getAccountType.firstOrNull() ?: ""
                accountUserType = AppUtils.getUserType(this@UserProfileActivity)
                if (accountUserType == 1) {

                    accountType = AppUtils.AccountTypes.Dater

                } else {
                    accountType = AppUtils.AccountTypes.Matchmaker

                }
                println("accountUserType======>>>>>>$accountUserType")
                Log.d("accountTypeInEditProfile", accountType)
                socialMedia = profileViewModel.getSocialMediaUserModel.firstOrNull() ?: ""
                planType = "Premier"
            }
            setUpBillingClient()
            getUserProfileDetails()
            toolTipBinding.getContentView().apply {

                findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                    getString(R.string.location_message_1)
                findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
                findViewById<AppCompatTextView>(R.id.txtTipDesc).text =
                    getString(R.string.location_message_2)
                findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {

                    blurHandling(false)
                    toolTipBinding.dismiss()
                }
            }
            if (intent.hasExtra("EditProfile")) {
                isFromSettings = intent.getBooleanExtra("EditProfile", false)
            }
            if (isFromSettings) {
                profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
            } else {
                profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "2")
            }
            Log.e("TAG", "ACCOUNT TYPE === $accountType")
            Log.e("TAG", "socialMediaUserModel === $socialMedia")
            //temp  if (socialMedia != "") {
            /*   socialMediaUserModel = Gson().fromJson<Any>(
                   socialMedia,
                   SocialMediaUserModel::class.java
               ) as SocialMediaUserModel*/
            if (socialMediaUserModel.socialType != "") {
                println("social media geneder------------------->>>${socialMediaUserModel.gender}")
                if (socialMediaUserModel.profilePicture != null && socialMediaUserModel.profilePicture != "") {
                    AppUtils.storeProfileImage(
                        this@UserProfileActivity,
                        socialMediaUserModel.profilePicture.toString()
                    )
                    binding.apply {
                        isMoreImages = false
                        addImagerl.visibility = View.INVISIBLE
                        ivClose.visibility = View.VISIBLE

                        imgSettings.apply {
                            visibility = View.VISIBLE
                            Glide.with(mActivity)
                                .asBitmap()
                                .load(socialMediaUserModel.profilePicture)
                                //   .thumbnail(/*sizeMultiplier=*/ 0.25f)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(this)
                        }
                    }
                } else {
                    binding.apply {
                        addImagerl.visibility = View.VISIBLE
                        ivClose.visibility = View.INVISIBLE
                        imgSettings.visibility = View.INVISIBLE
                        AppUtils.storeProfileImage(this@UserProfileActivity, "")

                    }
                }

                if (socialMediaUserModel.firstName!!.isNotEmpty()) {
                    edtFirstName.setText(socialMediaUserModel.firstName.toString())
                }
                if (socialMediaUserModel.gender!!.isNotBlank()) {
                    if (socialMediaUserModel.gender!!.equals("male", ignoreCase = true)) {
                        userGender = "0"

                        binding.btnSegmentGender.setPosition(0, true)

                    } else if (socialMediaUserModel.gender!!.equals("female", ignoreCase = true)) {
                        userGender = "1"

                        binding.btnSegmentGender.setPosition(1, true)

                    } else {
                        userGender = "2"

                        binding.btnSegmentGender.setPosition(2, true)

                    }

                }
                if (socialMediaUserModel.lastName!!.isNotEmpty()) {
                    edtLastName.setText(socialMediaUserModel.lastName)
                }

                if (socialMediaUserModel.dob!!.isNotEmpty()) {
                    edtBirthDate.setText(AppUtils.formatDate(socialMediaUserModel.dob))
                    convertedDob = edtBirthDate.text.toString()
                    edtBirthDate.apply {
                        background =
                            ContextCompat.getDrawable(mActivity, R.drawable.corner_blue_border_bg)

                    }
                }
            }

            if (edtBirthDate.text.toString().isNotBlank()) {
                convertedDob = edtBirthDate.text.toString()
                println("convertedDob 11111111111111111---->>>${convertedDob}")

            }
            //   }
            addImagerl.setOnClickListener {
                if (addImagerl.visibility == View.VISIBLE) {
                    if (imgSettings.visibility == View.INVISIBLE) {

                        //ask runtime permission for gallery & camera
                        isMoreImages = false
//                    requestRuntimePermission()

                        openSelectPhotoDialog()
                    }
                }
            }

            btnTipGotIt.setOnClickListener {
                AppUtils.slideView(binding.linLocAnim, DisplayUtil.dpToPx(mActivity, 280F), 0, 300)
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        relFadedView.visibility = View.GONE
                        ivInfo.isEnabled = true
                    },
                    300
                )

                toolTipArrowBottom.visibility = View.GONE

                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        toolTipArrowTop.visibility = View.GONE
                    },
                    250
                )

            }
            relFadedView.setOnClickListener(null)
            ivBack.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                if (!isTaskRoot) {
                    if (isFromSettings) {
                        onBackPressed()
                    } else {
                        if (layoutAddMore.isVisible) {
                            layoutAddMore.visibility = View.GONE
                            btnAddMore.visibility = View.VISIBLE
                            txtFields.visibility = View.VISIBLE
                        } else {
                            onBackPressed()
                        }
                    }
                } else {
                    nextActivity(VideoTutorial::class.java)
                    finish()
                }
            }
            mActivity.setShaderView(
                btnAddMore,
                R.color.blue_gradient_1,
                R.color.blue_gradient_6
            )
            setGradientToProgress(true)
            when (accountType) {
                AppUtils.AccountTypes.Dater -> {
                    layoutVerified.visibility = View.VISIBLE
                    btnAddMore.visibility = View.VISIBLE
                    txtFields.visibility = View.VISIBLE
                    //  layoutVerified.visibility = View.VISIBLE
                    txtAreaLive.visibility = View.VISIBLE
                    astericAi.visibility = View.VISIBLE
                    ivInfo.visibility = View.VISIBLE
                    edtLocation.visibility = View.VISIBLE
                    txtProfileHeader.text = getString(R.string.header_profile_dater)
                }
                AppUtils.AccountTypes.Matchmaker -> {
                    layoutVerified.visibility = View.GONE
                    btnAddMore.visibility = View.GONE
                    txtFields.visibility = View.VISIBLE
                    txtAreaLive.visibility = View.GONE
                    astericAi.visibility = View.GONE
                    ivInfo.visibility = View.GONE
                    edtLocation.visibility = View.GONE
                    val ss = SpannableString(getString(R.string.header_profile_matchmaker))
                    ss.setSpan(UnderlineSpan(), 8, 11, 0)
                    txtProfileHeader.text = ss
                }
            }
            userGender = btnSegmentGender.position.toString()
            btnSegmentGender.onPositionChangedListener =
                SegmentedButtonGroup.OnPositionChangedListener {
                    // Handle stuff here
                    userGender = it.toString()
                    if (userGender == "0") {
                        AppUtils.storeGender(this@UserProfileActivity, 1)
                    } else if (userGender == "1") {
                        AppUtils.storeGender(this@UserProfileActivity, 2)

                    } else {
                        AppUtils.storeGender(this@UserProfileActivity, 3)

                    }
                    setGenderFont(it)
                    profileViewModel.savePreference(PreferenceKeys.PREF_GENDER, userGender)
                }

            if (userGender == "0") {
                AppUtils.storeGender(this@UserProfileActivity, 1)
            } else if (userGender == "1") {
                AppUtils.storeGender(this@UserProfileActivity, 2)

            } else {
                AppUtils.storeGender(this@UserProfileActivity, 3)

            }
            btnContinue.setOnClickListener {

                if (isValid()) {
                    val age = AppUtils.getAgeFromCurrentDate(
                        mYear.toInt(),
                        mMonth.toInt(),
                        mDay.toInt()
                    )
                    if (age.toInt() < 18) {
                        openDOBDialog(true)
                    } else {
                        when (accountType) {
                            AppUtils.AccountTypes.Dater -> {
                                if (isFromSettings) {
                                    lifecycleScope.launch {
                                        submitUpdateProfileCall(
                                            profileViewModel.getProfileSubmitted.firstOrNull()
                                                ?: false
                                        )
                                    }
                                } else {
                                    if (socialMediaUserModel.socialType != "" && !isPermissionGranted) {
//                                        requestRuntimePermission()
                                        lifecycleScope.launch {
                                            submitUpdateProfileCall(
                                                profileViewModel.getProfileSubmitted.firstOrNull()
                                                    ?: false
                                            )
                                        }
                                    } else {
                                        if (edtLocation.text.toString().trim().isEmpty1()) {
                                            isEditLocation = false
                                            edtLocation.performClick()
                                        } else {
                                            lifecycleScope.launch {
                                                submitUpdateProfileCall(
                                                    profileViewModel.getProfileSubmitted.firstOrNull()
                                                        ?: false
                                                )
                                            }

                                        }
                                    }
                                }
                            }
                            AppUtils.AccountTypes.Matchmaker -> {
                                if (socialMediaUserModel.socialType != "") {
//                                    requestRuntimePermission()
                                    lifecycleScope.launch {
                                        submitUpdateProfileCall(
                                            profileViewModel.getProfileSubmitted.firstOrNull()
                                                ?: false
                                        )
                                    }
                                } else {
                                    lifecycleScope.launch {
                                        Log.e("printDD", "11111")
                                        submitUpdateProfileCall(
                                            profileViewModel.getProfileSubmitted.firstOrNull()
                                                ?: false
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            edtFirstName.apply {
                addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (s!!.isEmpty1() && isFirst) {
                            when (accountType) {
                                AppUtils.AccountTypes.Dater -> {
                                    profileValue -= AppConstants.PROFILE_DATER
                                }
                                AppUtils.AccountTypes.Matchmaker -> {
                                    profileValue -= AppConstants.PROFILE_CONNECTOR
                                }
                            }
                            isFirst = false
                            updateProfileProgress()
                        } else if (s.isNotEmpty() && !isFirst) {
                            when (accountType) {
                                AppUtils.AccountTypes.Dater -> {
                                    profileValue += AppConstants.PROFILE_DATER
                                }
                                AppUtils.AccountTypes.Matchmaker -> {
                                    profileValue += AppConstants.PROFILE_CONNECTOR
                                }
                            }
                            isFirst = true
                            updateProfileProgress()
                        }
                        edtFirstName.background =
                            ContextCompat.getDrawable(mActivity, R.drawable.corner_blue_border_bg)
                        edtFirstName.setHintTextColor(resources.getColor(R.color.colorGrey))
                        edtFirstName.setTextColor(resources.getColor(R.color.fontBlack))
                        if (s.length >= 25) {
                            mActivity.showSnackBar(
                                layoutProfileMain,
                                getString(R.string.character_limit)
                            )
                        }
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        AppUtils.hideUnderline(s)
                        profileViewModel.savePreference(
                            PreferenceKeys.PREF_FIRST_NAME,
                            s.toString()
                        )
                    }
                })
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        edtFirstName.clearFocus()
                        true
                    }
                    false
                }
            }


            edtLastName.apply {
                addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(
                        s: CharSequence?, start: Int, before: Int, count: Int
                    ) {
                        if (s!!.isEmpty1() && isLast) {
                            when (accountType) {
                                AppUtils.AccountTypes.Dater -> {
                                    profileValue -= AppConstants.PROFILE_DATER
                                }
                                AppUtils.AccountTypes.Matchmaker -> {
                                    profileValue -= AppConstants.PROFILE_CONNECTOR
                                }
                            }
                            isLast = false
                            updateProfileProgress()
                        } else if (s.isNotEmpty() && !isLast) {
                            when (accountType) {
                                AppUtils.AccountTypes.Dater -> {
                                    profileValue += AppConstants.PROFILE_DATER
                                }
                                AppUtils.AccountTypes.Matchmaker -> {
                                    profileValue += AppConstants.PROFILE_CONNECTOR
                                }
                            }
                            isLast = true
                            updateProfileProgress()
                        }
                        edtLastName.background =
                            ContextCompat.getDrawable(mActivity, R.drawable.corner_blue_border_bg)
                        edtLastName.setHintTextColor(resources.getColor(R.color.colorGrey))
                        edtLastName.setTextColor(getResources().getColor(R.color.fontBlack))
                        if (s.length >= 25) {
                            mActivity.showSnackBar(
                                layoutProfileMain,
                                getString(R.string.character_limit)
                            )
                        }
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        AppUtils.hideUnderline(s)
                        edtFirstName.clearFocus()
                        profileViewModel.savePreference(
                            PreferenceKeys.PREF_LAST_NAME,
                            s.toString()
                        )
                    }
                })
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        edtLastName.clearFocus()
                        edtBirthDate.performClick()
                        true
                    }
                    false
                }
            }
            ivInfo.setOnClickListener {
                getCoordinates(
                    toolTipBinding.getContentView().findViewById(R.id.bottomView),
                    toolTipBinding.getContentView().findViewById(R.id.topView)
                )
                ivInfo.isEnabled = false
                //  Log.e("printResult",Gson().toJson(data))
                //  Log.e("printResult",AppUtils.getScreenHeight(this@UserProfileActivity).toString())  //2168

//                toolTipBinding.getContentView().startAnimation(
//                    AnimationUtils.loadAnimation(
//                        mActivity,
//                        R.anim.tip_slide_down
//                    )
//                )
                // toolTipBinding.showAlignBottom(it)
//                if(test) {
//                    it.setPadding(0 , 50 , 0 , 0)
//                    test = false
//                }
                blurHandling(true, 25F)
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val height: Int = displayMetrics.heightPixels
                val width: Int = displayMetrics.widthPixels
                //    Log.e("printHtt",DisplayUtil.pxToDp(this@UserProfileActivity,statusBarHeight).toString())
                //     binding.imageFaded.setPadding(0,36,0,0)
                //  binding.imageFaded.setPadding(0,DisplayUtil.pxToDp(this@UserProfileActivity,statusBarHeight),0,0)

                /* var loc = IntArray(2)
                 ivInfo.getLocationOnScreen(loc)
                 var distanceFromBottom = height - loc[1]
                 val viewHeight = ivInfo.height
                 distanceFromBottom += viewHeight //distance from bottom

                 if (pxToDp(distanceFromBottom) >= 355) {
                     toolTipBinding.showAlignBottom(it)
                 } else {
                     toolTipBinding.showAlignTop(it)
                 }*/

            }
            btnAddMore.setOnClickListener {
                //show layout for other information
                //deleteDatabase()
                btnAddMore.visibility = View.GONE
                txtFields.visibility = View.GONE
                layoutAddMore.visibility = View.VISIBLE
            }
            imgProfile.setOnClickListener {
                if (imgSettings.visibility == View.INVISIBLE) {

                    //ask runtime permission for gallery & camera
                    isMoreImages = false
//                    requestRuntimePermission()

                    openSelectPhotoDialog()
                }
            }
            layoutVerified.setOnClickListener {
                //open verified layout
                openVerifiedImageDialog()
                /*lifecycleScope.launch {
                    if (!profileViewModel.getIsVerified.firstOrNull()!!){
                        openVerifiedDialog()
                    }
                }*/
            }
            edtLocation.apply {
                setOnClickListener {
                    //we are not changing location once it is selected
                    if (location.isEmpty1()) {
                        startForResult?.launch(
                            Intent(
                                mActivity,
                                LocationActivity::class.java
                            )
                        )
                        //getLocationPermission()
                    }
                }
            }
            edtTravelLocation.apply {
                setOnClickListener {
                    openSubscriptionDialog()
                }
            }
            edtBirthDate.apply {
                setOnClickListener {
                    background =
                        ContextCompat.getDrawable(mActivity, R.drawable.corner_blue_border_bg)
                    setHintTextColor(resources.getColor(R.color.colorGrey))
                    openDOBDialog()
                }
            }
            toggleInstagram.setOnCheckedChangeListener { _, isChecked ->
                isInstaShowPhoto = isChecked
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_IS_SHOW_PROFILE,
                    isChecked
                )
            }
            ivClose.setOnClickListener {
                deleteImage()
            }


            layoutSelectedImage.adapter = photosAdapter

            edtBio.apply {
                makeTextviewScrollable(this)
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        AppUtils.hideUnderline(s)
                        edtLastName.clearFocus()
                        // profileViewModel.savePreference(PreferenceKeys.PREF_BIO, s.toString())
                        background = ContextCompat.getDrawable(
                            mActivity,
                            R.drawable.corner_blue_border_bg
                        )
                        AppUtils.storeBio(this@UserProfileActivity, s.toString())
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
                        if (s!!.isEmpty1() && isBio) {
                            when (accountType) {
                                AppUtils.AccountTypes.Dater -> {
                                    profileValue -= AppConstants.PROFILE_DATER
                                }
                                AppUtils.AccountTypes.Matchmaker -> {
                                    profileValue -= AppConstants.PROFILE_CONNECTOR
                                }
                            }
                            isBio = false
                        } else if (s.isNotEmpty() && !isBio) {
                            when (accountType) {
                                AppUtils.AccountTypes.Dater -> {
                                    profileValue += AppConstants.PROFILE_DATER
                                }
                                AppUtils.AccountTypes.Matchmaker -> {
                                    profileValue += AppConstants.PROFILE_CONNECTOR
                                }
                            }
                            isBio = true
                            updateProfileProgress()
                        }
                        tvBioCharacter.text =
                            resources.getString(R.string.bio_length, edtBio.length().toString())
                        if (edtBio.length() == 150 && !isBioLocal) {
                            mActivity.showSnackBar(
                                layoutProfileMain,
                                getString(R.string.character_limit)
                            )
                        } else {
                            isBioLocal = false
                        }
                    }
                })
            }
            edtLanguage.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                startActivity(
                    Intent(
                        mActivity,
                        SelectLanguageActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            edtOccupation.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                startActivity(
                    Intent(
                        mActivity,
                        SelectOccupationActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            edtEducation.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                startActivity(
                    Intent(
                        mActivity,
                        SelectSchoolActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            edtAstroSign.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                startActivity(
                    Intent(
                        mActivity,
                        SelectAstroSignActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            edtCaste.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                startActivity(
                    Intent(
                        mActivity,
                        SelectCastActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            edtChildren.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                startActivity(
                    Intent(
                        mActivity,
                        SelectChildrenPlanActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            edtReligion.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                startActivity(
                    Intent(
                        mActivity,
                        SelectReligionActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            edtCovid.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                startActivity(
                    Intent(
                        mActivity,
                        SelectVaccineStatusActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            edtSmoking.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                startActivity(
                    Intent(
                        mActivity,
                        SelectSmokeActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            edtLookingFor.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                startActivity(
                    Intent(
                        mActivity,
                        SelectRelationshipActivity::class.java
                    ).putExtra("EditProfile", isFromSettings)
                )
                overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
            }
            edtInstagram.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                //instagram integration
                if (instagramId.isBlank()) {
                    instagramStartForResult?.launch(
                        Intent(
                            mActivity,
                            InstagramWebActivity::class.java
                        )
                    )
                } else {
                    instagramId = ""
                    instagramUserName = ""
                    edtInstagram.apply {
                        setText(instagramUserName)
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.instagram_gray,
                            0,
                            R.drawable.right_arrow,
                            0
                        )
                        background = ContextCompat.getDrawable(
                            mActivity,
                            R.drawable.grey_border_bg
                        )
                    }
                    layoutShowInProfile.visibility = View.GONE
                    profileViewModel.removePreference(PreferenceKeys.PREF_INSTA_ID)
                    profileViewModel.removePreference(PreferenceKeys.PREF_INSTA_NAME)
                    userSettings()
                }
            }
            txtHeightValue.setOnClickListener {
                if (AppUtils.isClickedRecently()) return@setOnClickListener
                edtBio.clearFocus()
                openHeightDialog(segmentedHeight.position == 0)
            }
            segmentedHeight.onPositionChangedListener =
                SegmentedButtonGroup2.OnPositionChangedListener {
                    // Handle stuff here
                    if (isHeightChanged) {
                        when (it) {
                            0 -> {
                                //convert height to ft
                                isInCM = false
                                ft = "${(convertedHeightInInches.toFloat() / 12).toInt()}′"
                                feet = (convertedHeightInInches.toFloat() / 12).toInt()
                                inches = convertedHeightInInches.minus(feet * 12)
                                inch = if (inches <= 9) {
                                    "0${inches}″"
                                } else {
                                    "${inches}″"
                                }
                                txtHeightValue.text =
                                    resources.getString(
                                        R.string.feet_inches_show,
                                        feet.toString(),
                                        inches.toString()
                                    )

                                txtHeightValue.apply {
                                    background = ContextCompat.getDrawable(
                                        mActivity,
                                        R.drawable.corner_blue_border_bg
                                    )
                                }
                            }
                            1 -> {
                                //convert height to cm
                                isInCM = true
                                inCm = (convertedHeightInInches.div(0.3937)).toInt()
                                cm = "$inCm cm"
                                txtHeightValue.text = "$inCm"
                                txtHeightValue.apply {
                                    background = ContextCompat.getDrawable(
                                        mActivity,
                                        R.drawable.corner_blue_border_bg
                                    )
                                }
                            }
                        }
                    }
                }
            if (isFromSettings) {
                if (accountType == AppUtils.AccountTypes.Dater) {
                    btnAddMore.performClick()
                }
            }
            if (intent.hasExtra("ChangeUserType")) {
                lifecycleScope.launch {
                    if (profileViewModel.getAdvProfileSubmitted.firstOrNull() == false) {
                        if (accountType == AppUtils.AccountTypes.Dater) {
                            btnAddMore.performClick()
                        }
                    }
                }
            }
            toggleTravelLocation.setOnCheckedChangeListener { buttonView, isChecked ->
                Log.d("TAG:", "isChecked: $isChecked")
                /**
                 * we are showing it in reverse manner.
                 */
                if (isChecked) {
                    if (edtTravelLocation.text.toString().trim().isEmpty1()) {
                        edtTravelLocation.apply {
                            performClick()
                        }
                    }
                } else {
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_TRAVEL_LOCATION,
                        ""
                    )
                    edtTravelLocation.apply {
                        setText("")
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow, 0)
                    }
                }
            }
        }
//            getInviteUserGender()
    }

    private fun openVerifiedImageDialog() {
        lifecycleScope.launch {
            //  if (!profileViewModel.getIsVerified.firstOrNull()!!) {
            if (AppUtils.getProfileVerified(this@UserProfileActivity) == 0) {
                openVerifiedDialog()
            }
        }
    }

    private fun deleteImage() {
        try {
            isDeleteMore = false
            uploadedImages.forEach {
                if (!it.is_more!!) {
//                    deletedId = it.id
                    deleteUserImage(it.id)
                    Log.e("TAG:", "REMAINING: $uploadedImages")
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_IMAGES,
                        Gson().toJson(uploadedImages)
                    )
                }
            }
            deleteUserImage(deletedId)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        /* binding.apply {
             imgSettings.visibility = View.INVISIBLE
             addImagerl.visibility = View.VISIBLE
             ivClose.visibility = View.INVISIBLE
         }
         path = ""
         socialMediaUserModel.socialType = ""
         profileViewModel.savePreference(
             PreferenceKeys.PREF_PROFILE_IMAGE,
             ""
         )*/
    }

    private fun getSelectedData() {
        //default value for gender
        when (accountType) {
            AppUtils.AccountTypes.Dater -> {
                profileValue = AppConstants.PROFILE_DATER
                //   isVerifiedBlock()
            }
            AppUtils.AccountTypes.Matchmaker -> {
                profileValue = AppConstants.PROFILE_CONNECTOR
            }
        }
        lifecycleScope.launch {
            binding.apply {


                //commented by nirali
                /*       userGender = profileViewModel.getGender.firstOrNull() ?: "2"
                       if (userGender.isNotEmpty() && (userGender == "0" || userGender == "1" || userGender == "2")) {

                       } else {
                           if (userGender.isEmpty1()) {
                               userGender = "2"
                           }
                           if (userGender == "male") {
                               userGender = "0"
                               AppUtils.storeGender(this@UserProfileActivity, 1)

                           } else if (userGender == "female") {
                               userGender = "1"
                               AppUtils.storeGender(this@UserProfileActivity, 2)


                           } else {
                               AppUtils.storeGender(this@UserProfileActivity, 3)

                               userGender = "2"

                           }
                       }

                       binding.btnSegmentGender.setPosition(userGender.toInt(), true)*/

                isInCM = profileViewModel.getIsHeightFeet.firstOrNull() ?: false
                val isInFeet = if (isInCM) {
                    1
                } else {
                    0
                }
                segmentedHeight.setPosition(isInFeet, true)

                try {
                    val height = profileViewModel.getHeight.firstOrNull()
                    if (!height.isNullOrEmpty() && height != "0.0") {
                        isHeightAdded = true
                        profileValue += AppConstants.PROFILE_DATER
                        isHeightChanged = true
                        if (isInCM) {

                            cm = "$height cm"
                            inCm = height.toInt()
                            convertedHeightInInches = (0.3937 * height.toInt()).roundToInt()
                            txtHeightValue.text = height
                            txtHeightValue.apply {
                                background = ContextCompat.getDrawable(
                                    mActivity,
                                    R.drawable.corner_blue_border_bg
                                )
                            }
                        } else {
                            feet = height.split(".")[0].toInt()
                            inches = height.split(".")[1].toInt()
                            ft = "${feet}′"
                            inch = if (inches <= 9) {
                                "0${inches}″"
                            } else {
                                "${inches}″"
                            }
                            convertedHeightInInches = (feet * 12) + inches
                            txtHeightValue.apply {
                                background = ContextCompat.getDrawable(
                                    mActivity,
                                    R.drawable.grey_border_bg
                                )
                            }
                            txtHeightValue.text = resources.getString(
                                R.string.feet_inches_show,
                                feet.toString(),
                                inches.toString()
                            )
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                //val languages = profileViewModel.getLanguage.firstOrNull()
                val languages = AppUtils.getLanguage(this@UserProfileActivity)
                val height = AppUtils.getHeight(this@UserProfileActivity)

                if (height == 0) {

                }
                if (!languages.isNullOrBlank()) {
                    edtLanguage.apply {
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
                        background = if (languagesList.isEmpty()) {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.grey_border_bg
                            )
                        } else {
                            profileValue += AppConstants.PROFILE_DATER
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.corner_blue_border_bg
                            )
                        }
                        setText(languageName.joinToString { it })
                    }
                }

                // val occupation = profileViewModel.getOccupation.firstOrNull()
                val occupation = AppUtils.getOccupation(this@UserProfileActivity)
                if (!occupation.isNullOrBlank()) {
                    occupationModel = Gson().fromJson<Any>(
                        occupation,
                        OccupationModel::class.java
                    ) as OccupationModel
                    edtOccupation.apply {
                        val occupationArray = ArrayList<String>()
                        if (occupationModel.title!!.isNotEmpty()) {
                            occupationArray.add(occupationModel.title!!)
                        }
                        if (occupationModel.company!!.isNotEmpty()) {
                            occupationArray.add(occupationModel.company!!)
                        }
                        setText(occupationArray.joinToString(", "))
                        background = if (occupationArray.isEmpty()) {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.grey_border_bg
                            )
                        } else {
                            profileValue += AppConstants.PROFILE_DATER
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.corner_blue_border_bg
                            )
                        }
                    }
                }

                val travelLocation = profileViewModel.getTravelLocation.firstOrNull()
                if (!travelLocation.isNullOrBlank()) {
                    travelLocationModel = Gson().fromJson<Any>(
                        travelLocation,
                        TravelLocationListModel::class.java
                    ) as TravelLocationListModel
                    edtTravelLocation.apply {
                        if (travelLocationModel.city.isNotEmpty()) {
                            setText(
                                getString(
                                    R.string.city_country,
                                    travelLocationModel.city,
                                    travelLocationModel.country
                                )
                            )
                            toggleTravelLocation.isChecked = true
                            setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, 0, 0)
                        } else {
                            setText("")
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                        }
                    }
                }

                // val education = profileViewModel.getEducation.firstOrNull()
                val education = AppUtils.getEducation(this@UserProfileActivity)
                if (!education.isNullOrBlank()) {
                    educationModel = Gson().fromJson<Any>(
                        education,
                        EducationModel::class.java
                    ) as EducationModel
                    edtEducation.apply {
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
                        background = if (educationArray.isEmpty()) {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.grey_border_bg
                            )
                        } else {
                            profileValue += AppConstants.PROFILE_DATER
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.corner_blue_border_bg
                            )
                        }
                    }
                }

                val cast = profileViewModel.getCast.firstOrNull()
                if (!cast.isNullOrBlank()) {
                    castModel = Gson().fromJson<Any>(
                        cast,
                        CastListModel.CastModel::class.java
                    ) as CastListModel.CastModel
                    edtCaste.apply {
                        if (castModel.name!!.isNotEmpty()) {
                            setText(castModel.name)
                            background = ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.corner_blue_border_bg
                            )
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            profileValue += AppConstants.PROFILE_DATER
                        } else {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                            setText("")
                            background = ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.grey_border_bg
                            )
                        }

                    }
                }

                //val children = profileViewModel.getChildren.firstOrNull()
                val children = AppUtils.getChildren(this@UserProfileActivity)
                if (!children.isNullOrBlank()) {
                    childrenModel = Gson().fromJson<Any>(
                        children,
                        ChildrenModel.ChildrenData.ChildrenLevel::class.java
                    ) as ChildrenModel.ChildrenData.ChildrenLevel
                    edtChildren.apply {
                        background = if (childrenModel.value != "") {
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            setText(childrenModel.value)
                            profileValue += AppConstants.PROFILE_DATER
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.corner_blue_border_bg
                            )
                        } else {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                            setText("")
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.grey_border_bg
                            )
                        }
                    }
                }

                //  val religion = profileViewModel.getReligion.firstOrNull()
                val religion = AppUtils.getReligion(this@UserProfileActivity)
                if (!religion.isNullOrBlank()) {
                    religionModel = Gson().fromJson<Any>(
                        religion,
                        ReligionModel.ReligionData.ReligionLevel::class.java
                    ) as ReligionModel.ReligionData.ReligionLevel
                    edtReligion.apply {
                        background = if (religionModel.value != "") {
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            setText(religionModel.value)
                            profileValue += AppConstants.PROFILE_DATER
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.corner_blue_border_bg
                            )
                        } else {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                            setText("")
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.grey_border_bg
                            )
                        }

                    }
                }

                //val smoking = profileViewModel.getSmoking.firstOrNull()
                val smoking = AppUtils.getSmoking(this@UserProfileActivity)
                if (!smoking.isNullOrBlank()) {
                    smokingModel = Gson().fromJson<Any>(
                        smoking,
                        SmokingDataModel.SmokingData.Smoking::class.java
                    ) as SmokingDataModel.SmokingData.Smoking
                    edtSmoking.apply {
                        background = if (smokingModel.value != "") {
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            setText(smokingModel.value)
                            profileValue += AppConstants.PROFILE_DATER
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.corner_blue_border_bg
                            )
                        } else {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                            setText("")
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.grey_border_bg
                            )
                        }

                    }
                }

                //   val relationship = profileViewModel.getRelationship.firstOrNull()
                val relationship = AppUtils.getRelationShip(this@UserProfileActivity)
                if (!relationship.isNullOrBlank()) {
                    /* relationshipModel = Gson().fromJson<Any>(
                         relationship,
                         RelationshipListModel.RelationshipModel::class.java
                     ) as RelationshipListModel.RelationshipModel*/

                    relationshipModel = Gson().fromJson<Any>(
                        relationship,
                        RelationshipModel.RelationShipData.RelationshipLevel::class.java
                    ) as RelationshipModel.RelationShipData.RelationshipLevel


                    edtLookingFor.apply {
                        background = if (relationshipModel.value != "") {
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            setText(relationshipModel.value)
                            profileValue += AppConstants.PROFILE_DATER
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.corner_blue_border_bg
                            )
                        } else {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                            setText("")
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.grey_border_bg
                            )
                        }

                    }
                }

                // val vaccineStatus = profileViewModel.getVaccineStatus.firstOrNull()
                val vaccineStatus = AppUtils.getVaccination(this@UserProfileActivity)
                if (!vaccineStatus.isNullOrBlank()) {
                    vaccineStatusModel = Gson().fromJson<Any>(
                        vaccineStatus,
                        VaccinationModel.VaccinationData.VaccinationLevel::class.java
                    ) as VaccinationModel.VaccinationData.VaccinationLevel
                    edtCovid.apply {
                        background = if (vaccineStatusModel.value != "") {
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            setText(vaccineStatusModel.value)
                            profileValue += AppConstants.PROFILE_DATER
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.corner_blue_border_bg
                            )
                        } else {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                            setText("")
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.grey_border_bg
                            )
                        }
                    }
                }

                //  val astrology = profileViewModel.getAstroSign.firstOrNull()
                val astrology = AppUtils.getAstrologicalSign(this@UserProfileActivity)
                if (!astrology.isNullOrBlank()) {
                    astrologyModel = Gson().fromJson<Any>(
                        astrology,
                        AstrologicalModel.AstrologicalData.AstrologicalSignLevel::class.java
                    ) as AstrologicalModel.AstrologicalData.AstrologicalSignLevel

                    if (astrologyModel.logo?.trim() != "") {
                        imgAstro.apply {
                            visibility = View.VISIBLE
                            Glide
                                .with(mActivity)
                                .load(astrologyModel.logo)
                                .centerCrop()
                                .into(this)
                        }
                    } else {
                        imgAstro.visibility = View.GONE
                    }
                    layoutAstroSign.apply {
                        background = if (astrologyModel.value != "") {
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.corner_blue_border_bg
                            )
                        } else {
                            ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.grey_border_bg
                            )
                        }

                    }
                    edtAstroSign.apply {
                        text = if (astrologyModel.value != "") {
                            profileValue += AppConstants.PROFILE_DATER
                            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                            val layoutParams = this.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.setMargins(15, 0, 0, 0)
                            this.layoutParams = layoutParams
                            astrologyModel.value
                        } else {
                            setCompoundDrawablesWithIntrinsicBounds(
                                0,
                                0,
                                R.drawable.right_arrow,
                                0
                            )
                            val layoutParams = this.layoutParams as ConstraintLayout.LayoutParams
                            layoutParams.setMargins(0, 0, 0, 0)
                            this.layoutParams = layoutParams
                            ""
                        }
                    }
                }
                val instaImages = profileViewModel.getInstagramImages.firstOrNull()
                if (!instaImages.isNullOrEmpty()) {
                    val type: Type =
                        object : TypeToken<List<String?>?>() {}.type
                    instaImagesList =
                        Gson().fromJson(instaImages, type) as ArrayList<String>
                    Log.e("TAG", "STORED URL === ${instaImagesList.size}")
                }

                instagramId = profileViewModel.getInstaId.firstOrNull() ?: ""
               // instagramUserName = profileViewModel.getInstaName.firstOrNull() ?: ""
                instagramUserName =AppUtils.getInstagramUserName(this@UserProfileActivity)
                isInstaShowPhoto = profileViewModel.getIsInstaProfile.firstOrNull() ?: true
                edtInstagram.apply {
                    if (instagramUserName.isNotEmpty() && instagramUserName != "@null") {
                        setText(instagramUserName)
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.color_instagram,
                            0,
                            R.drawable.right_arrow,
                            0
                        )
                        background = ContextCompat.getDrawable(
                            mActivity,
                            R.drawable.corner_blue_border_bg
                        )
                        /*layoutShowInProfile.visibility = View.VISIBLE
                        toggleInstagram.isChecked = isInstaShowPhoto*/
                        profileValue += AppConstants.PROFILE_DATER
                    } else {
                        setText("")
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.instagram_gray,
                            0,
                            R.drawable.right_arrow,
                            0
                        )
                        background = ContextCompat.getDrawable(
                            mActivity,
                            R.drawable.grey_border_bg
                        )
                    }
                }

//                location = profileViewModel.getArea.firstOrNull() ?: ""
                location = AppUtils.getLocation(this@UserProfileActivity)
                if (location.isNotEmpty() && location != "null") {
                    edtLocation.apply {
                        setTextColor(ContextCompat.getColor(mActivity, R.color.colorGrey))
                        //    background = ContextCompat.getDrawable(mActivity, R.drawable.grey_border_bg)
                        background =
                            ContextCompat.getDrawable(mActivity, R.drawable.corner_blue_border_bg)
                        setText(location)
                        setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, 0, 0)
                        compoundDrawablePadding = 10
                    }
                    locNextArrow.setBackgroundResource(R.drawable.next_arrow_blue_new)
                    profileValue += AppConstants.PROFILE_DATER
                } else {
                    edtLocation.apply {
                        setTextColor(ContextCompat.getColor(mActivity, R.color.colorPagerDesc))
                        //  background = ContextCompat.getDrawable(mActivity, R.drawable.corner_blue_border_bg)
                        background = ContextCompat.getDrawable(mActivity, R.drawable.grey_border_bg)
                        setHintTextColor(getResources().getColor(R.color.colorGrey))
                    }
                    locNextArrow.setBackgroundResource(R.drawable.next_arrow_blue_new)
                }

                val firstName = profileViewModel.getFirstName.firstOrNull()

                if (!firstName.isNullOrEmpty()) {
                    isFirst = true
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            profileValue += AppConstants.PROFILE_DATER
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            profileValue += AppConstants.PROFILE_CONNECTOR
                        }
                    }
                    edtFirstName.setText(firstName)
                }
                val lastName = profileViewModel.getLastName.firstOrNull()
                if (!lastName.isNullOrEmpty()) {
                    isLast = true
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            profileValue += AppConstants.PROFILE_DATER
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            profileValue += AppConstants.PROFILE_CONNECTOR
                        }
                    }
                    edtLastName.setText(lastName)
                }

                dob = profileViewModel.getDob.firstOrNull()

                // convertedDob = profileViewModel.getConvertedDob.firstOrNull()!!
                if (!dob.isNullOrEmpty()) {
                    isDobAdded = true
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            profileValue += AppConstants.PROFILE_DATER
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            profileValue += AppConstants.PROFILE_CONNECTOR
                        }
                    }
                    try {


                        mMonth = dob!!.split("/")[0]
                        mDay = dob!!.split("/")[1]
                        mYear = dob!!.split("/")[2]
                        dob = "$mMonth/$mDay/$mYear"

                        val age = AppUtils.getAgeFromCurrentDate(
                            mYear.toInt(),
                            mMonth.toInt(),
                            mDay.toInt()
                        )

                        if (age.toInt() < 18.0) {
                            customSnackbar = mActivity.showSnackBarMargin(
                                isDOB = false,
                                v = layoutProfileMain,
                                message = getString(R.string.dob_message)
                            )

                            binding.edtBirthDate.apply {
                                background =
                                    ContextCompat.getDrawable(
                                        this@UserProfileActivity,
                                        R.drawable.corner_orange_border_bg
                                    )
                            }
                        } else {
                            if (customSnackbar != null && customSnackbar!!.isShown) {
                                AppUtils.snackBarCloseAnimation(
                                    this@UserProfileActivity,
                                    customSnackbar!!
                                )
                            }
                        }
                    } catch (e: Exception) {
                    }
                    edtBirthDate.setText(AppUtils.formatDate(dob))

                    edtBirthDate.apply {
                        background =
                            ContextCompat.getDrawable(mActivity, R.drawable.corner_blue_border_bg)

                    }

                }
                // if (profileViewModel.getIsVerified.firstOrNull()!!)
                //     layoutVerified.visibility = View.GONE

                val gender = profileViewModel.getGender.firstOrNull()
                if (!gender.isNullOrEmpty()) {
                    userGender = gender
//                    if(gender=="male"){
//                        userGender = "0"
//                    } else if (gender=="female"){
//                        userGender = "1"
//                    } else {
//                        userGender = "2"
//                    }
//                    when (accountType) {
//                        AppUtils.AccountTypes.Dater -> {
//                            profileValue += AppConstants.PROFILE_DATER
//                        }
//                        AppUtils.AccountTypes.Matchmaker -> {
//                            profileValue += AppConstants.PROFILE_CONNECTOR
//                        }
//                    }
//                    btnSegmentGender.setPosition(userGender.toInt(), true)
                }


                //   val bio = profileViewModel.getBio.firstOrNull() ?: ""
                val bio = AppUtils.getBio(this@UserProfileActivity)

                if (bio.isNotEmpty() && bio != "null") {
                    isBioLocal = true
                    isBio = true
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            profileValue += AppConstants.PROFILE_DATER
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            profileValue += AppConstants.PROFILE_CONNECTOR
                        }
                    }
                    //         profileValue += AppConstants.PROFILE_DATER
                    edtBio.setText(bio)
                    edtBio.apply {
                        background = ContextCompat.getDrawable(
                            mActivity,
                            R.drawable.corner_blue_border_bg
                        )
                    }
                }
                val dbImages = profileViewModel.getAllImages.firstOrNull()
                val dbMoreImages = AppUtils.getMorePhotos(this@UserProfileActivity)
                println("dbMoreImages---->>>>${dbMoreImages}")
                if (dbMoreImages.isNotEmpty()) {
                    val type: Type =
                        object :
                            TypeToken<List<ProfilePhotosModel.DataProfile.UserPhotos?>?>() {}.type
                    uploadedMmoreImages =
                        Gson().fromJson(
                            dbMoreImages,
                            type
                        ) as ArrayList<ProfilePhotosModel.DataProfile.UserPhotos>
                    println("uploadedMmoreImages---->>>>${uploadedMmoreImages.size}")

                    uploadedMmoreImages.apply {
                        // photosAdapter.addAll(list)
                        photosAdapter.addImage(this)
                        if (this.isNotEmpty()) {
                            isAdd = true
                            profileValue += AppConstants.PROFILE_DATER
                        }
                    }
                } else {
                    //if there is no images in storage (happens in new users only)
                    /* val list = ArrayList<ProfilePhotosModel.DataProfile.UserPhotos>()
                     for (i in list.size..5) {
                         list.add(ProfilePhotosModel.DataProfile.UserPhotos(isNull = true))
                     }*/
                    photosAdapter.addImage(ArrayList<ProfilePhotosModel.DataProfile.UserPhotos>())
                }
                updateProfileProgress()
                if (!dbImages.isNullOrEmpty()) {
                    val type: Type =
                        object : TypeToken<List<UserImagesResponseModel.Item?>?>() {}.type
                    uploadedImages =
                        Gson().fromJson(dbImages, type) as ArrayList<UserImagesResponseModel.Item>
                    Log.e("TAG:", "IMAGES: == $uploadedImages")
                    uploadedImages.filter {
                        it.is_more == true && it.is_instagram == false && it.isProfile == false
                    }.apply {
                        val list = ArrayList<UserImagesResponseModel.Item>()
                        list.addAll(reversed())
                        for (i in list.size..5) {
                            list.add(UserImagesResponseModel.Item())
                        }
                        //temp photosAdapter.addAll(list)
                        if (this.isNotEmpty()) {
                            profileValue += AppConstants.PROFILE_DATER
                        }
                    }

                    uploadedImages.filter {
                        it.is_more == false && it.is_instagram == false && it.isProfile == true
                    }.let {
                        if (it.isNotEmpty()) {
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_PROFILE_IMAGE,
                                it[0].imageUrl.toString()
                            )
                            when (accountType) {
                                AppUtils.AccountTypes.Dater -> {
                                    profileValue += AppConstants.PROFILE_PICTURE_DATER//profile picture = 10
                                }
                                AppUtils.AccountTypes.Matchmaker -> {
                                    profileValue += AppConstants.PROFILE_CONNECTOR
                                }
                            }
                            //path = this[0].imageUrl
                            binding.apply {
                                if (it[0].imageUrl != null && it[0].imageUrl != "") {
                                    addImagerl.visibility = View.INVISIBLE
                                    ivClose.visibility = View.VISIBLE
                                    imgSettings.apply {
                                        visibility = View.VISIBLE
                                        Glide.with(mActivity)
                                            .asBitmap()
                                            .load(it[0].imageUrl)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .into(this)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateProfileProgress() {
        try {
            binding.apply {
                Log.d("TAG:", "PROGRESS: $profileValue")
                if (profileValue <= 100) {
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_PROFILE_PERCENTAGE,
                        profileValue
                    )
                    progressSettings.setProgress(profileValue.toFloat(), true)
                } else {
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_PROFILE_PERCENTAGE,
                        100
                    )
                    progressSettings.setProgress(100f, true)
                }
                progressSettings.setOnProgressChangeListener {
                    txtProgress.text = "${it.toInt()}%"
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun openHeightDialog(isUnitInFeet: Boolean) {
//        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        val dialog = BottomSheetDialog(this, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            val pickerBinding = DialogHeightPickerBinding.inflate(layoutInflater)
            val bottomBehaviour = BottomSheetBehavior.from(pickerBinding.bottomSheet)
            setContentView(pickerBinding.root)

            pickerBinding.apply {
                disableView(llSelectHeight)
                wheelFt.apply {
                    typeface = ResourcesCompat.getFont(mActivity, R.font.regular)
                }
                wheelInch.apply {
                    typeface = ResourcesCompat.getFont(mActivity, R.font.regular)
                }
                wheelCM.apply {
                    typeface = ResourcesCompat.getFont(mActivity, R.font.regular)
                }
                btnSubmit.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        isHeightChanged = true
                        var selectedHeight = ""
                        if (isUnitInFeet) {
                            mActivity.binding.txtHeightValue.text =
                                resources.getString(
                                    R.string.feet_inches_show,
                                    feet.toString(),
                                    inches.toString()
                                )
                            selectedHeight = "$feet.$inches"
                            convertedHeightInInches = (feet * 12) + inches
                            mActivity.binding.txtHeightValue.apply {
                                background = ContextCompat.getDrawable(
                                    mActivity,
                                    R.drawable.corner_blue_border_bg
                                )
                            }
                            Log.d("TAG:", "convertedFeet ==> $convertedHeightInInches")
                        } else {
                            mActivity.binding.txtHeightValue.text = "$inCm"
                            selectedHeight = inCm.toString()
                            convertedHeightInInches = (0.3937 * inCm).roundToInt()
                            mActivity.binding.txtHeightValue.apply {
                                background = ContextCompat.getDrawable(
                                    mActivity,
                                    R.drawable.corner_blue_border_bg
                                )
                            }
                            Log.d("TAG:", "convertedCm ==> $convertedHeightInInches")
                        }
                        isInCM = !isUnitInFeet
                        profileViewModel.savePreference(
                            PreferenceKeys.PREF_HEIGHT,
                            selectedHeight
                        )
                        profileViewModel.savePreference(
                            PreferenceKeys.PREF_IS_HEIGHT_FEET,
                            isInCM
                        )
                        if (!isHeightAdded) {
                            isHeightAdded = true
                            profileValue += AppConstants.PROFILE_DATER
                            updateProfileProgress()
                        }
                        dialog.dismiss()
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

                if (isUnitInFeet) {
                    layoutFt.visibility = View.VISIBLE
                    wheelCM.visibility = View.GONE
                    val ftList = arrayListOf<String>().apply {
                        addAll((4..6).map {
                            String.format(
                                "%1d′",
                                it
                            )
                        })
                    }
                    val inchListAllDigits = arrayListOf<String>().apply {
                        addAll((0..11).map {
                            String.format(
                                "%02d″",
                                it
                            )
                        })
                    }
                    val inchListStartFromTen = arrayListOf<String>().apply {
                        addAll((10..11).map {
                            String.format(
                                "%02d″",
                                it
                            )
                        })
                    }
                    val inchListEndAtNine = arrayListOf<String>().apply {
                        addAll((0..9).map {
                            String.format(
                                "%02d″",
                                it
                            )
                        })
                    }
                    wheelFt.data = ftList

                    var prevPicker = AppUtils.PreviousPicker.FromFive
//                    wheelInch.data = inchListAllDigits
                    if (feet == 5) {
                        wheelInch.data = inchListAllDigits
                        prevPicker = AppUtils.PreviousPicker.FromFive
                    } else if (feet == 4) {
                        wheelInch.data = inchListStartFromTen
                        prevPicker = AppUtils.PreviousPicker.FromFour
                        if (inches < 10) {
                            inches = 10
                            inch = "10″"
                        } else if (inches == 11) {
                            inches = 11
                            inch = "11″"
                        }
                    } else if (feet == 6) {
                        //  wheelInch.data = inchListEndAtNine
                        wheelInch.data = inchListAllDigits
                        prevPicker = AppUtils.PreviousPicker.FromSix
                        /*if (inches > 9) {
                            inches = 9
                            inch = "09″"
                        }*/
                    }

                    val ftPos = ftList.indexOf(ft)
                    wheelFt.selectedItemPosition = ftPos
                    val inchPos = wheelInch.data.indexOf(inch)
                    wheelInch.selectedItemPosition = inchPos


                    wheelFt.setOnItemSelectedListener { _, data, position ->

                        if (data.toString() == "5′") {

                            /*if(prevPicker==AppUtils.PreviousPicker.FromFour) {
                                if (inches == 10) {
                                    inches = 0
                                    inch = "00″"
                                }
                                else {
                                    inches = 1
                                    inch = "01″"
                                }
                            }*/

                            wheelInch.data = inchListAllDigits
                            prevPicker = AppUtils.PreviousPicker.FromFive
                            setListeneronWheelInch(wheelInch)
                            val inchPos = wheelInch.data.indexOf(inch)
                            if (inchPos != -1) {
                                wheelInch.selectedItemPosition = inchPos
                                inch = inchListAllDigits[inchPos]
                                if (inchPos < 10)
                                    inches = "0${inchPos}".toInt()
                                else
                                    inches = inchPos
                            } else {
                                wheelInch.selectedItemPosition = inchListAllDigits.size - 1
                                inch = inchListAllDigits[inchListAllDigits.size - 1]
                                inches = 11
                            }
                        } else if (data.toString() == "4′") {
                            /* if (inches == 0) {
                                 inches = 10
                                 inch = "10″"
                             }
                             else {
                                 inches = 11
                                 inch = "11″"
                             }*/

                            wheelInch.data = inchListStartFromTen
                            prevPicker = AppUtils.PreviousPicker.FromFour
                            setListeneronWheelInch(wheelInch)

                            val inchPos = wheelInch.data.indexOf(inch)
                            if (inchPos != -1) {
                                wheelInch.selectedItemPosition = inchPos
                                inch = inchListStartFromTen[inchPos]
                                if (inchPos == 10)
                                    inches = 10
                                else
                                    inches = 11
                            } else {
                                wheelInch.selectedItemPosition = inchListStartFromTen.size - 1
                                inch = inchListStartFromTen[inchListStartFromTen.size - 1]
                                inches = 11
                            }
                        } else if (data.toString() == "6′") {

                            //   wheelInch.data = inchListEndAtNine
                            /* wheelInch.data = inchListAllDigits
                             prevPicker=AppUtils.PreviousPicker.FromSix
                             setListeneronWheelInch(wheelInch)

                             val inchPos = wheelInch.data.indexOf(inch)
                             if (inchPos != -1) {
                                 wheelInch.selectedItemPosition = inchPos
                               //  inch = inchListEndAtNine[inchPos]
                                 inch = inchListAllDigits[inchPos]
                                 if (inchPos < 10 )
                                     inches = "0${inchPos}".toInt()
                                 else
                                     inches = "09".toInt()
                             }
                             else {
                                 wheelInch.selectedItemPosition = inchListEndAtNine.size - 1
                                 inch = inchListEndAtNine[inchListEndAtNine.size - 1]
                                 inches = "09".toInt()
                             }*/

                            wheelInch.data = inchListAllDigits
                            prevPicker = AppUtils.PreviousPicker.FromSix
                            setListeneronWheelInch(wheelInch)
                            val inchPos = wheelInch.data.indexOf(inch)
                            if (inchPos != -1) {
                                wheelInch.selectedItemPosition = inchPos
                                inch = inchListAllDigits[inchPos]
                                if (inchPos < 10)
                                    inches = "0${inchPos}".toInt()
                                else
                                    inches = inchPos
                            } else {
                                wheelInch.selectedItemPosition = inchListAllDigits.size - 1
                                inch = inchListAllDigits[inchListAllDigits.size - 1]
                                inches = 11
                            }
                        }
                        mActivity.ft = data.toString()
                        feet = data.toString().trim().dropLast(data.toString().length - 1).toInt()
                    }
                    setListeneronWheelInch(wheelInch)
                } else {
                    layoutFt.visibility = View.GONE
                    wheelCM.visibility = View.VISIBLE
                    val cmList = arrayListOf<String>().apply {
                        addAll((147..210).map {
                            String.format(
                                "%03d cm",
                                it
                            )
                        })
                    }
                    wheelCM.data = cmList
                    val cmPos = cmList.indexOf(cm)
                    wheelCM.selectedItemPosition = cmPos
                    wheelCM.setOnItemSelectedListener { _, data, position ->
                        Log.e("TAG", "WHEEL ==== $data === $position")
                        mActivity.cm = data.toString()
                        inCm = data.toString().trim().dropLast(data.toString().length - 3).toInt()
                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                if (isShowing) {
                    bottomBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                    bottomBehaviour.peekHeight = Resources.getSystem().displayMetrics.heightPixels
                }
                pickerBinding.imgDialogGradient.setDialogFadeInAnimation()
                mAnimation.slide(pickerBinding.llSelectHeight, 100, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun setListeneronWheelInch(wheelInch: WheelPicker) {
        /*wheelInch.setOnItemSelectedListener { picker, data, position -> }*/
        wheelInch.setOnItemSelectedListener { _, data, position ->
            Log.e("TAG", "WHEEL INCH ==== $data === $position")
            mActivity.inch = data.toString()
            inches = data.toString().trim().dropLast(data.toString().length - 2).toInt()
        }
    }

//    private fun openDOBDialog(isError: Boolean = false) {
//        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
//        dialogs.add(dialog)
//        val dobPickerBinding = DialogDobPickerBinding.inflate(layoutInflater)
//        dialog.apply {
//            requestWindowFeature(Window.FEATURE_NO_TITLE)
//            setCancelable(true)
//            setCanceledOnTouchOutside(true)
//            setContentView(dobPickerBinding.root)
//            //setting data
//            dobPickerBinding.apply {
//                if (isError) {
//                    mActivity.showSnackBarMargin(
//                        isDOB = true,
//                        v = layoutMain,
//                        message = getString(R.string.dob_message)
//                    )
//                }
//                pickerYear.apply {
//                    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
//                    val endYear = currentYear/* - 19*/ //we do not have users below 18 years
//                    val startYear = currentYear - 100 //we do not have users above 99 years
//                    val yearList = arrayListOf<String>().apply {
//                        addAll((startYear..endYear).map {
//                            String.format(
//                                "%02d",
//                                it
//                            )
//                        })
//                    }
//                    data = yearList
//                    selectedItemPosition = yearList.indexOf(mYear)
//                    setOnItemSelectedListener { _, data, _ ->
//                        mYear = data.toString()
//                    }
//                }
//                var daysList = arrayListOf<String>().apply {
//                    addAll((1..31).map {
//                        String.format(
//                            "%02d",
//                            it
//                        )
//                    })
//                }
//                //Setting Month
//                pickerMonth.setOnItemSelectedListener { _, _, position ->
//                    mMonth = if ((position + 1) <= 9) {
//                        "0${(position + 1)}"
//                    } else {
//                        (position + 1).toString()
//                    }
//                    //setting days in particular Month
//                    val daysOfMonth: String =
//                        if (mMonth == "04" || mMonth == "06" || mMonth == "09" || mMonth == "11") {
//                            "30"
//                        } else if (mMonth == "02") {
//                            if (mYear.toInt() % 4 == 0) {
//                                "29"
//                            } else {
//                                "28"
//                            }
//                        } else {
//                            "31"
//                        }
//                    daysList = arrayListOf<String>().apply {
//                        addAll((1..daysOfMonth.toInt()).map {
//                            String.format(
//                                "%02d",
//                                it
//                            )
//                        })
//                    }
//                    pickerDay.data = daysList
//                }
//                pickerMonth.selectedItemPosition = daysList.indexOf(mMonth)
//                pickerDay.apply {
//                    data = daysList
//                    selectedItemPosition = daysList.indexOf(mDay)
//                    setOnItemSelectedListener { _, data, _ ->
//                        mDay = data.toString()
//
//                        val daysOfMonth: String =
//                            if (mMonth == "04" || mMonth == "06" || mMonth == "09" || mMonth == "11") {
//                                "30"
//                            } else if (mMonth == "02") {
//                                if (mYear.toInt() % 4 == 0) {
//                                    "29"
//                                } else {
//                                    "28"
//                                }
//                            } else {
//                                "31"
//                            }
//                        daysList = arrayListOf<String>().apply {
//                            addAll((1..daysOfMonth.toInt()).map {
//                                String.format(
//                                    "%02d",
//                                    it
//                                )
//                            })
//                        }
//                        this.data = daysList
//                    }
//                }
//                txtDone.setOnClickListener {
//                    //get DOB and show in view
//                    dob = "$mMonth/$mDay/$mYear"
//                    convertedDob = "$mYear-$mMonth-$mDay"
//                    val age = AppUtils.getAgeFromCurrentDate(
//                        mYear.toInt(),
//                        mMonth.toInt(),
//                        mDay.toInt()
//                    )
//                    if (age.toInt() < 18) {
//                        mActivity.showSnackBarMargin(
//                            isDOB = true,
//                            v = layoutMain,
//                            message = getString(R.string.dob_message)
//                        )
//                    } else {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            mActivity.binding.edtBirthDate.setText(dob)
//                            profileViewModel.savePreference(PreferenceKeys.PREF_DOB, dob!!)
//                            profileViewModel.savePreference(
//                                PreferenceKeys.PREF_CONVERTED_DOB,
//                                convertedDob
//                            )
//                            if (!isDobAdded) {
//                                isDobAdded = true
//                                when (accountType) {
//                                    AppUtils.AccountTypes.Dater -> {
//                                        profileValue += AppConstants.PROFILE_DATER
//                                    }
//                                    AppUtils.AccountTypes.Matchmaker -> {
//                                        profileValue += AppConstants.PROFILE_CONNECTOR
//                                    }
//                                }
//                                updateProfileProgress()
//                            }
//                            dismiss()
//                        }
//                    }
//                }
//                try {
//                    setBottomDialogWindowAttributes()
//                    show()
//                    imgDialogGradient.setDialogFadeInAnimation()
//                    mAnimation.doBounceUpAnimation(llSelectDob)
//
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//    }

    private fun openDOBDialog(isError: Boolean = false) {
        profileViewModel.savePreference(PreferenceKeys.PREF_IS_DOB_DIALOG_OPEN, true)
        isDobDialogOpen = true
//        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        val dialog = BottomSheetDialog(this, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val dobPickerBinding = DialogDobPickerBinding.inflate(layoutInflater)
        val bottomBehaviour = BottomSheetBehavior.from(dobPickerBinding.bottomSheet)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setCanceledOnTouchOutside(false)
            setContentView(dobPickerBinding.root)
            //setting data
            dobPickerBinding.apply {
                AppUtils.disableView(clSelectDob)
                pickerMonth.apply {
                    typeface = ResourcesCompat.getFont(mActivity, R.font.regular)
                }
                pickerDay.apply {
                    typeface = ResourcesCompat.getFont(mActivity, R.font.regular)
                }
                pickerYear.apply {
                    typeface = ResourcesCompat.getFont(mActivity, R.font.regular)
                }
                setOnDismissListener {
                    profileViewModel.savePreference(PreferenceKeys.PREF_IS_DOB_DIALOG_OPEN, false)
                }
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_IS_DOB_DIALOG_OPEN,
                                false
                            )
                        }
                        true
                    }
                    false
                }
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dialog.dismiss()
//                            profileViewModel.savePreference(PreferenceKeys.PREF_IS_DOB_DIALOG_OPEN, false)
//                        }
//                        true
//                    }
//                    false
//                }

                if (isError) {
                    mActivity.showSnackBarMargin(
                        isDOB = false,
                        v = layoutMain,
                        message = getString(R.string.dob_message)
                    )
                    txtDone.setBackgroundResource(R.drawable.corner_button_gradient_alpha_bg)
                }
                pickerYear.apply {
                    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                    val endYear = currentYear/* - 19*/ //we do not have users below 18 years
                    val startYear = currentYear - 100 //we do not have users above 99 years
                    val yearList = arrayListOf<String>().apply {
                        addAll((startYear..endYear).map {
                            String.format("%02d", it)
                        })
                    }
                    data = yearList
                    selectedItemPosition = yearList.indexOf(mYear)
                    setOnItemSelectedListener { _, data, _ ->
                        txtDone.isEnabled = true

                        mYear = data.toString()
                        val age = AppUtils.getAgeFromCurrentDate(
                            mYear.toInt(),
                            mMonth.toInt(),
                            mDay.toInt()
                        )
                        if (age.toInt() < 18.0) {
                            customSnackbar = mActivity.showSnackBarMargin(
                                isDOB = true,
                                v = window!!.decorView,
                                message = getString(R.string.dob_message)
                            )
                            txtDone.isEnabled = false

                            txtDone.setBackgroundResource(R.drawable.corner_button_gradient_alpha_bg)
                        } else {
                            if (customSnackbar != null && customSnackbar!!.isShown) {
                                AppUtils.snackBarCloseAnimation(
                                    this@UserProfileActivity,
                                    customSnackbar!!
                                )
                            }
                            txtDone.isEnabled = true


                            txtDone.setBackgroundResource(R.drawable.button_gradient_enable_selector)
                        }
                    }
                }
                var daysList = arrayListOf<String>().apply {
                    addAll((1..31).map {
                        String.format(
                            "%02d",
                            it
                        )
                    })
                }
                //Setting Month
                pickerMonth.setOnItemSelectedListener { _, _, position ->
                    txtDone.isEnabled = true

                    mMonth = if ((position + 1) <= 9) {
                        "0${(position + 1)}"
                    } else {
                        (position + 1).toString()
                    }
                    val age = AppUtils.getAgeFromCurrentDate(
                        mYear.toInt(),
                        mMonth.toInt(),
                        mDay.toInt()
                    )
                    if (age.toInt() < 18.0) {
                        customSnackbar = mActivity.showSnackBarMargin(
                            isDOB = true,
                            v = window!!.decorView,
                            message = getString(R.string.dob_message)
                        )
                        txtDone.isEnabled = false

                        txtDone.setBackgroundResource(R.drawable.corner_button_gradient_alpha_bg)
                    } else {
                        if (customSnackbar != null && customSnackbar!!.isShown) {
                            AppUtils.snackBarCloseAnimation(
                                this@UserProfileActivity,
                                customSnackbar!!
                            )
                        }
                        txtDone.isEnabled = true

                        txtDone.setBackgroundResource(R.drawable.button_gradient_enable_selector)
                    }
                    //setting days in particular Month
                    val daysOfMonth: String =
                        if (mMonth == "04" || mMonth == "06" || mMonth == "09" || mMonth == "11") {
                            "30"
                        } else if (mMonth == "02") {
                            if (mYear.toInt() % 4 == 0) {
                                "29"
                            } else {
                                "28"
                            }
                        } else {
                            "31"
                        }
                    daysList = arrayListOf<String>().apply {
                        addAll((1..daysOfMonth.toInt()).map {
                            String.format(
                                "%02d",
                                it
                            )
                        })
                    }
                    pickerDay.data = daysList
                }
                pickerMonth.selectedItemPosition = daysList.indexOf(mMonth)
                pickerDay.apply {
                    data = daysList
                    selectedItemPosition = daysList.indexOf(mDay)
                    setOnItemSelectedListener { _, data, _ ->
                        mDay = data.toString()
                        txtDone.isEnabled = true

                        val age = AppUtils.getAgeFromCurrentDate(
                            mYear.toInt(),
                            mMonth.toInt(),
                            mDay.toInt()
                        )
                        if (age.toInt() < 18.0) {
                            customSnackbar = mActivity.showSnackBarMargin(
                                isDOB = true,
                                v = window!!.decorView,
                                message = getString(R.string.dob_message)
                            )
                            txtDone.setBackgroundResource(R.drawable.corner_button_gradient_alpha_bg)
                            txtDone.isEnabled = false
                        } else {
                            if (customSnackbar != null && customSnackbar!!.isShown) {
                                AppUtils.snackBarCloseAnimation(
                                    this@UserProfileActivity,
                                    customSnackbar!!
                                )
                            }
                            txtDone.isEnabled = true

                            txtDone.setBackgroundResource(R.drawable.button_gradient_enable_selector)
                        }
                        val daysOfMonth: String =
                            if (mMonth == "04" || mMonth == "06" || mMonth == "09" || mMonth == "11") {
                                "30"
                            } else if (mMonth == "02") {
                                if (mYear.toInt() % 4 == 0) {
                                    "29"
                                } else {
                                    "28"
                                }
                            } else {
                                "31"
                            }
                        daysList = arrayListOf<String>().apply {
                            addAll((1..daysOfMonth.toInt()).map {
                                String.format(
                                    "%02d",
                                    it
                                )
                            })
                        }
                        this.data = daysList
                    }
                }
                txtDone.setOnClickListener {
                    //get DOB and show in view
                    dob = "$mMonth/$mDay/$mYear"
                    convertedDob = "$mYear-$mMonth-$mDay"
                    val age = AppUtils.getAgeFromCurrentDate(
                        mYear.toInt(),
                        mMonth.toInt(),
                        mDay.toInt()
                    )
                    if (age.toInt() < 18) {
                        mActivity.showSnackBarMargin(
                            isDOB = true,
                            v = layoutMain,
                            message = getString(R.string.dob_message)
                        )
                    } else {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            mActivity.binding.edtBirthDate.setText(AppUtils.formatDate(dob))

                            mActivity.binding.edtBirthDate.apply {
                                background = ContextCompat.getDrawable(
                                    mActivity,
                                    R.drawable.corner_blue_border_bg
                                )

                            }

                            profileViewModel.savePreference(PreferenceKeys.PREF_DOB, dob!!)
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_CONVERTED_DOB,
                                convertedDob
                            )
                            if (!isDobAdded) {
                                isDobAdded = true
                                when (accountType) {
                                    AppUtils.AccountTypes.Dater -> {
                                        profileValue += AppConstants.PROFILE_DATER
                                    }
                                    AppUtils.AccountTypes.Matchmaker -> {
                                        profileValue += AppConstants.PROFILE_CONNECTOR
                                    }
                                }
                                updateProfileProgress()
                            }
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_IS_DOB_DIALOG_OPEN,
                                false
                            )
                            isDobDialogOpen = false
                            dismiss()
                        }
                    }
                }
                try {
                    setBottomDialogWindowAttributes()
                    show()
                    if (isShowing) {
                        bottomBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                        bottomBehaviour.peekHeight =
                            Resources.getSystem().displayMetrics.heightPixels
                    }
                    imgDialogGradient.setDialogFadeInAnimation()
                    mAnimation.slide(dobPickerBinding.clSelectDob, 200, 0)
//                    AppUtils.slideView(dobPickerBinding.clSelectDob, 500, 0, 8000)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


    private fun userAlreadyVerifiedDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val verifiedBinding = DialogUserVerifiedBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(verifiedBinding.root)
            verifiedBinding.btnContinue.setOnClickListener {
                //   AnimationBounceUpAndSlideUp().doBounceUpAnimation(verifiedBinding.root, dialog)
                verifiedBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            //  verifiedBinding.ivBlueTick.visibility = View.INVISIBLE
            /*if (mGestureVerificationImage != null) {
                var imageBitmap = AppUtils.bitmapCircularCroper(mGestureVerificationImage!!)
                if (imageBitmap != null)
                     verifiedBinding.imgUser.setImageBitmap(imageBitmap)
                else
                    verifiedBinding.imgUser.setImageBitmap(mGestureVerificationImage!!)
            }*/

            if (mGestureVerificationImage != null)
                Glide.with(context).load(mGestureVerificationImage)
                    .apply(RequestOptions.circleCropTransform()).into(verifiedBinding.imgUser)
            else
                Glide.with(context).load(R.drawable.user_full_image)
                    .apply(RequestOptions.circleCropTransform()).into(verifiedBinding.imgUser)


            //-------------------

            setZoomDialogWindowAttributes()
            show()
            verifiedBinding.imgDialogGradient.setDialogFadeInAnimation()

            //--------------------


/*            val animationTick =
                AnimationUtils.loadAnimation(this@UserProfileActivity, R.anim.verified_tick)
            val animation2 =
                AnimationUtils.loadAnimation(this@UserProfileActivity, R.anim.verified_2)
            setZoomDialogWindowAttributes()
            show()
            Handler(Looper.getMainLooper()).postDelayed({
                verifiedBinding.cvUserVerified.startAnimation(animation2)
            }, 100)

            animation2.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        verifiedBinding.ivBlueTick.startAnimation(animationTick)
                    }, 100)
                }

                override fun onAnimationEnd(p0: Animation?) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        verifiedBinding.rlBtnContinue.visibility = View.VISIBLE
                        AppUtils.slideView(verifiedBinding.rlBtnContinue, 0, verifiedBinding.btnContinue.height, 700)
                    }, 300)
                }

                override fun onAnimationRepeat(p0: Animation?) {
                }

            })
            animationTick.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {

                }

                override fun onAnimationRepeat(p0: Animation?) {

                }

            })

            dialog.setOnDismissListener {
                AnimationBounceUpAndSlideUp().doBounceUpAnimation(verifiedBinding.root, dialog)
            }
            dialog.setCancelable(false)*/
//            verifiedBinding.root.startAnimation(AnimationUtils.loadAnimation(this@LoginActivity,R.anim.slide_up_dialog))


        }
    }

    private fun openVerifiedDialog() {
//        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        val dialog = BottomSheetDialog(this, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val profileBinding = DialogVerifiedProfileBinding.inflate(layoutInflater)
        val bottomBehaviour = BottomSheetBehavior.from(profileBinding.bottomSheet)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(profileBinding.root)
            profileBinding.apply {
                lifecycleScope.launch {
                    //val image = profileViewModel.getProfileImage.firstOrNull()
                    val image = AppUtils.getProfileImage(this@UserProfileActivity)
                    binding.apply {
//                        pullDown.setOnTouchListener { v, event ->
//                            if (event.action === MotionEvent.ACTION_DOWN) {
//                                imgDialogGradient.setDialogFadeOutAnimation {
//                                    dialog.dismiss()
//                                }
//                                true
//                            }
//                            false
//                        }
                        imgDialogGradient.setOnTouchListener { v, event ->
                            if (event.action === MotionEvent.ACTION_DOWN) {
                                imgDialogGradient.setDialogFadeOutAnimation {
                                    dialog.dismiss()
                                }
                                true
                            }
                            false
                        }
                        disableView(profileBinding.clVerifyProfile)
                        val fName = edtFirstName.text.toString().trim()
                        if (fName.isNotEmpty()) {
                            val textDrawable = mActivity.createPlaceholderImage2(
                                fName.toUpperCase(),
                                200,
                                R.color.blue_gradient_2,
                                R.color.blue_gradient_3,
                                isModify = true
                            )
                            ivProfileCheck.visibility = View.VISIBLE
                            txtFirstLetter.text = fName.first().toString().uppercase(Locale.ROOT)

                            Glide.with(context)
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(textDrawable)
                                .into(ivProfile)
                        } else if (fName.isNullOrEmpty() && !image.isNullOrEmpty()) {
                            ivProfileCheck.visibility = View.VISIBLE

                            Glide.with(context)
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivProfile)
                        } else {
                            ivProfileCheck.visibility = View.GONE
//                            Glide.with(context)
//                                .load(R.mipmap.ic_launcher)
//                                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                                .into(ivProfile)

                            Glide.with(context)
                                .load(R.drawable.fwd_whitebutton)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivProfile)
                        }
                    }
                }
                txtGetVerified.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        openCopyGestureDialog()
                    }
                }
                txtDoItLater.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()

                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                if (isShowing) {
                    bottomBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                    bottomBehaviour.peekHeight = Resources.getSystem().displayMetrics.heightPixels
                }
                profileBinding.imgDialogGradient.setDialogFadeInAnimation()
                mAnimation.slide(profileBinding.clVerifyProfile, 100, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun openCopyGestureDialog() {
        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val gestureBinding = DialogCopyGestureBinding.inflate(layoutInflater)
        var gender = 3
        lifecycleScope.launch {
            //   gender = profileViewModel.getGender.firstOrNull().toString()
            gender = AppUtils.getGender(this@UserProfileActivity)
        }
        if (gender == 1) {
//            gestureBinding.viewUserImage.setImageResource(R.drawable.male_gesture_verification)
//            gestureBinding.viewUserImage.setImageResource(R.drawable.male_gesture_verification)
            gestureBinding.viewUserImage.setImageResource(R.drawable.male_new_verification)
//            gestureBinding.viewUserImage.setBackgroundResource(R.drawable.picture_gradient_male)
        }
        val height = AppUtils.getScreenHeight(this@UserProfileActivity)
        val heightDp = DisplayUtil.pxToDp(this, height)


        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(gestureBinding.root)
            gestureBinding.apply {
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                        }
                        true
                    }
                    false
                }

                txtReady.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                    requestRuntimePermission(true)
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                gestureBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openVerificationCameraActivity() {
        val intent = Intent(
            mActivity,
            ActivityCameraVerification::class.java
        )
        gestureVerificationStartForResult?.launch(intent)
    }

    private fun openFinalGestureDialog(isValid: Boolean) {
        val dialogGesture = Dialog(mActivity, R.style.SlideDialogTheme)
        dialogs.add(dialogGesture)
        val finalGestureBinding = DialogFinalGestureBinding.inflate(layoutInflater)
        dialogGesture.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(finalGestureBinding.root)
            val height = AppUtils.getScreenHeight(this@UserProfileActivity)
            val heightDp = DisplayUtil.pxToDp(this@UserProfileActivity, height)
//
//            if(heightDp<700) {
//                finalGestureBinding.viewUserImage.layoutParams.height = 200.dpToPx()
//                finalGestureBinding.viewUserImage.scaleType = ImageView.ScaleType.FIT_XY
//                finalGestureBinding.imageView.layoutParams.height = 200.dpToPx()
//            }
            //surfaceHolder = finalGestureBinding.surfaceView.holder
//            surfaceHolder?.addCallback(this@UserProfileActivity)
            finalGestureBinding.apply {
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                        }
                        true
                    }
                    false
                }
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dismiss()
//                        }
//                        true
//                    }
//                    false
//                }

                var gender = 3
                lifecycleScope.launch {

                    gender = AppUtils.getGender(this@UserProfileActivity)
                }
                if (gender == 1) {
                    viewUserImage.setImageResource(R.drawable.male_small)
                }
                imageView.setImageBitmap(mGestureVerificationImage)
                txtRetake.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                    val intent = Intent(
                        mActivity,
                        ActivityCameraVerification::class.java
                    )
                    gestureVerificationStartForResult?.launch(intent)
                }
                txtSubmit.setOnClickListener {
                    //captureImageVerification()
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }

                    if (isValid) {
                      //temp  sendImageVerification()

                    } else {
                        openVerificationProblemDialog()
                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                finalGestureBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openVerificationProgressDialog() {
        dialogVerification = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialogVerification)
        val binding = DialogVerificationProgressBinding.inflate(layoutInflater)
        dialogVerification?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(binding.root)
            binding.apply {
                txtGotVerify.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        AnimationBounceUpAndSlideUp().doBounceUpAnimation(
                            binding.root,
                            dialogVerification!!
                        )
                    }
                }
            }
            setOnDismissListener {
                AnimationBounceUpAndSlideUp().doBounceUpAnimation(
                    binding.root,
                    dialogVerification!!
                )
            }
            try {
                setZoomDialogWindowAttributes()
                show()
                binding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openPictureProblemDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val binding = DialogPictureProblemBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                val span1 = SpannableString(txtMessage.text.toString())
                span1.setSpan(
                    StyleSpan(Typeface.BOLD),
                    59,
                    65,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                span1.setSpan(
                    object : ClickableSpan() {
                        override fun onClick(view: View) {
                            startActivity(
                                Intent(
                                    mActivity,
                                    WebViewActivity::class.java
                                ).putExtra("url", AppConstants.PRIVACY_POLICY)
                            )
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            super.updateDrawState(ds)
                            ds.apply {
                                ds.color =
                                    ContextCompat.getColor(mActivity, R.color.colorPagerDesc)
                                isUnderlineText = false
                            }
                        }
                    }, 59, 65, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                txtMessage.apply {
                    setText(span1, TextView.BufferType.SPANNABLE)
                    highlightColor = Color.TRANSPARENT
                    movementMethod = LinkMovementMethod.getInstance()
                }
                txtChangeProfile.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                    openSelectPhotoDialog()
                }
                txtPrivacy.setOnClickListener {
                    //privacy policy
                    startActivity(
                        Intent(
                            mActivity,
                            WebViewActivity::class.java
                        ).putExtra("url", AppConstants.PRIVACY_POLICY)
                    )
                }
            }
            try {
                setZoomDialogWindowAttributes()
                show()
                binding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun requestRuntimePermission(isGesture: Boolean = false, isCamera: Boolean = false) {
        Dexter.withContext(mActivity).withPermissions(
            if (isGesture) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    arrayListOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    )
                } else {
                    arrayListOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    )
                }
            } else if (isCamera) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    arrayListOf(
                        Manifest.permission.CAMERA
                    )
                } else {
                    arrayListOf(
                        Manifest.permission.CAMERA
                    )
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    arrayListOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                    )
                } else {
                    arrayListOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                    )
                }
            }
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                when {
                    report.areAllPermissionsGranted() -> {
                        isPermissionGranted = true
                        when (isGesture) {
                            true -> {
//                                openFinalGestureDialog()
                                openVerificationCameraActivity()
                            }
                            false -> {
                                try {
                                    if (socialMediaUserModel.socialType != "" && !isMoreImages) {
                                        runBlocking(Dispatchers.IO) {
                                            /*val bitmap = BitmapFactory.decodeStream(
                                                URL(socialMediaUserModel.profilePicture).openConnection()
                                                    .getInputStream()
                                            )*/

                                            if (socialMediaUserModel.profilePicture?.isNotEmpty()
                                                    ?: false
                                            ) {
                                                val file = createImageFile()
                                                val profilePicture =
                                                    socialMediaUserModel.profilePicture
                                                if (file != null && profilePicture != null) {
                                                    downloadFile(profilePicture, file, true)
                                                }
                                            } else {
                                                // Else part execute if there is no image from social media
                                                runOnUiThread {
                                                    if (isCamera) captureImage()
                                                    else openGallery()
                                                    //   openSelectPhotoDialog()
                                                }
                                            }

                                            //saveImageAsync(bitmap, true)
                                        }
                                    } else {
                                        runOnUiThread {
                                            if (isCamera) captureImage()
                                            else openGallery()
//                                            openSelectPhotoDialog()
                                        }
                                    }
                                } catch (e: Exception) {
                                    if (BuildConfig.DEBUG)
                                        e.printStackTrace()
                                }
                            }
                        }
                    }
                    report.deniedPermissionResponses != null && report.deniedPermissionResponses.size > 0 -> {
                        if (report.deniedPermissionResponses[0].isPermanentlyDenied) {
                            val intent =
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts(
                                "package",
                                packageName, null
                            )
                            intent.data = uri
                            try {
                                startActivity(intent)
                            } catch (e: java.lang.Exception) {
                                e.printStackTrace()
                            }
                        } else {
                            dialogs[dialogs.size - 1].show()
                        }
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }

        })
            .withErrorListener {
                Log.e("Dexter", "There was an error: $it")
            }
            .check()
    }

    private fun openSelectPhotoDialog() {
//        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        val dialog = BottomSheetDialog(this, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val cameraGalleryBinding = DialogCameraGalleryBinding.inflate(layoutInflater)

        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(cameraGalleryBinding.root)
            val bottomBehaviour = BottomSheetBehavior.from(cameraGalleryBinding.bottomSheet)
            cameraGalleryBinding.apply {
                btnCancelPopup.setOnClickListener {
                    cameraGalleryBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dialog.dismiss()
                    }
                }
                btnOpenGallery.setOnClickListener {
                    cameraGalleryBinding.imgDialogGradient.setDialogFadeOutAnimation {
//                        dialog.dismiss()
                        requestRuntimePermission()
                        //open phone gallery
//                        openGallery()
                    }
                }
                btnOpenCamera.setOnClickListener {
                    cameraGalleryBinding.imgDialogGradient.setDialogFadeOutAnimation {
//                        dialog.dismiss()
                        requestRuntimePermission(isCamera = true)
                        //open phone camera
//                        captureImage()
                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                if (isShowing) {
                    bottomBehaviour.state = BottomSheetBehavior.STATE_EXPANDED
                    bottomBehaviour.peekHeight = Resources.getSystem().displayMetrics.heightPixels
                }
                cameraGalleryBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /** function open camera for take and save picture*/
    private fun captureImage() = imageSelector.startCameraImageCapture()

    /**function for openGallery for image selection*/
    private fun openGallery() = imageSelector.openGallery()
    private val imageSelectorCallback = object : ImageSelector.Callback {
        override fun onError(type: ImageSelector.ErrorType, errorText: String?) {
            Log.e("IMAGE_ERROR TYPE:", type.toString())
            if (errorText != null) {
                Log.e("IMAGE_ERROR TEXT:", errorText)
            }
        }

        override fun getCameraOutputFilePath(): File? {
            destination = try {
                createImageFile()
            } catch (ex: IOException) {
                null
            }
            return destination
        }

        override fun onCameraImageCaptureSuccess() {
            if (path != null) {
                uploadUserImage()
            }
        }

        override fun onGalleryImageSelected(path: String) {
            this@UserProfileActivity.path = path
            uploadUserImage()
        }

        override fun onCancel() {

        }
    }

    private fun createImageFile(): File? {
        return try {
            val storageDir = File(filesDir, "/Data/Pictures")
            if (!storageDir.exists()) {
                storageDir.mkdirs()
            }
            File(storageDir, "IMG_SHARE_${System.currentTimeMillis()}.jpg").apply {
                Log.e("TAG", absolutePath)
                mActivity.path = absolutePath
            }
        } catch (e: IOException) {
            null
        }
    }


//    private fun isValid(): Boolean {
//        val validMsg = AppUtils.validationTextInput(
//            mActivity ,
//            binding.layoutMain ,
//            binding.edtFirstName ,
//            binding.edtLastName ,
//            binding.edtBirthDate,
//            binding.edtLocation
//        )
//        if (validMsg != AppConstants.Valid) {
//            return false
//        }
//        return true
//    }

    private fun isValid(): Boolean {
        var validMsg: String? = ""
        if (accountType == AppUtils.AccountTypes.Dater) {
            validMsg = AppUtils.validationTextInput(
                mActivity,
                binding.locNextArrow,
                binding.layoutProfileMain,
                binding.edtFirstName,
                binding.edtLastName,
                binding.edtBirthDate,
                binding.edtLocation
            )
        } else if (accountType == AppUtils.AccountTypes.Matchmaker) {
            validMsg = AppUtils.validationTextInput(
                mActivity,
                binding.locNextArrow,
                binding.layoutProfileMain,
                binding.edtFirstName,
                binding.edtLastName,
                binding.edtBirthDate
            )
        }
        if (validMsg != AppConstants.Valid) {
            return false
        }
        var isAllDigit = false
        if (binding.edtFirstName.text!!.toString().replace(" ", "").any { it.isDigit() }) {
            isAllDigit = true
            binding.edtFirstName.apply {
                clearFocus()
                background =
                    ContextCompat.getDrawable(
                        this@UserProfileActivity,
                        R.drawable.corner_orange_border_bg
                    )
                setTextColor(resources.getColor(R.color.color_orange, null))
            }
        }
        if (binding.edtLastName.text!!.toString().replace(" ", "").any { it.isDigit() }) {
            isAllDigit = true
            binding.edtLastName.apply {
                clearFocus()
                background =
                    ContextCompat.getDrawable(
                        this@UserProfileActivity,
                        R.drawable.corner_orange_border_bg
                    )
                setTextColor(resources.getColor(R.color.color_orange))
            }
        }
        if (isAllDigit) {
            showSnackBarMargin(
                false,
                binding.layoutProfileMain,
                getString(R.string.numerical_input)
            )
            return false
        }
        return true
    }


    private fun submitBasicProfileDetail(isUpdate: Boolean) {
        val gender = when (userGender) {
            "0" -> USER_MALE
            "1" -> USER_FEMALE
            "2" -> USER_NON_BINARY
            else -> USER_MALE
        }
        val languageIds = languagesList.map { it._id }
        val jsonArray = JsonArray()
        languageIds.forEach {
            jsonArray.add(it)
        }
        val isHeightInFeet = if (!isInCM) {
            AppConstants.HEIGHT_FEET
        } else {
            AppConstants.HEIGHT_CM
        }
        var apiRequest = JsonObject()

        println("convertedDob----->>>>>$convertedDob")
        when (accountType) {
            AppUtils.AccountTypes.Dater -> {

                apiRequest = JsonObject().apply {
                    addProperty("first_name", binding.edtFirstName.text.toString().trim())
                    addProperty("last_name", binding.edtLastName.text.toString().trim())
                    addProperty("birth_date", convertedDob)
                    //addProperty("percentage", profileValue.toString())
                    addProperty("gender", gender)
                    //  addProperty("location", location)

                }
            }
            AppUtils.AccountTypes.Matchmaker -> {
                apiRequest = JsonObject().apply {
                    addProperty("first_name", binding.edtFirstName.text.toString().trim())
                    addProperty("last_name", binding.edtLastName.text.toString().trim())
                    addProperty("dob", convertedDob)
                    addProperty("percentage", profileValue.toString())
                    addProperty("gender", gender)

                }
            }
        }
        lifecycleScope.launch {
            when {
                !AppUtils.isNetworkAvailable(mActivity) -> {
                    openFailNetworkDialog() {
                        lifecycleScope.launch {
                            Log.e("printDD", "22222")
                            submitUpdateProfileCall(
                                profileViewModel.getProfileSubmitted.firstOrNull() ?: false
                            )
                        }
                    }
//                    AppUtils.showMessageOK(
//                        mActivity,
//                        getString(R.string.app_name),
//                        getString(R.string.common_retry),
//                        getString(R.string.no_internet)
//                    ) { _, _ ->
//                        lifecycleScope.launch {
//                            submitUpdateProfileCall(
//                                profileViewModel.getProfileSubmitted.firstOrNull() ?: false
//                            )
//                        }
//                    }
                }
                else -> {
                    if (isUpdate) {
                        Log.e("printDD", "666666")
                        Log.d("TAG:", "UPDATE_REQUEST: == $apiRequest")
                        profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            profileViewModel.userProfileUpdate(
                                "Bearer $authToken", userId, apiRequest
                            )
                        }
                    } else {
                        Log.e("printDD", "77777")
                        Log.d("TAG:", "SUBMIT_REQUEST: == $apiRequest")
                        profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            profileViewModel.userProfileSubmit(
                                "Bearer $authToken", apiRequest
                            )
                        }
                    }
                }
            }
        }
    }

    private fun submitUpdateProfileCall(isUpdate: Boolean) {


        if (convertedDob.isBlank()) {
            convertedDob = binding.edtBirthDate.text.toString()
        }
        println("convertedDob---->>>>>>>>>>>>$convertedDob")
        val gender = when (userGender) {
            "0" -> MALE
            "1" -> FEMALE
            "2" -> BINARY
            else -> MALE
        }
        val languageIds = languagesList.map { it._id }
        val languageNames = languagesList.map { it.value }
        println("languageNames------->>>>${languageNames}")
        val jsonArray = JsonArray()
        languageIds.forEach {
            jsonArray.add(it)
        }
        val isHeightInFeet = if (!isInCM) {
            AppConstants.HEIGHT_FEET
        } else {
            AppConstants.HEIGHT_CM
        }
        var apiRequest = JsonObject()
        var apiDetailedRequest = JsonObject()
        when (accountType) {
            AppUtils.AccountTypes.Dater -> {

                apiRequest = JsonObject().apply {
                    addProperty("first_name", binding.edtFirstName.text.toString().trim())
                    addProperty("last_name", binding.edtLastName.text.toString().trim())
                    addProperty("birth_date", convertedDob)
                    //temp   addProperty("percentage", profileValue.toString())
                    addProperty("gender", gender)
                    /*         addProperty("location", location)
                             if (latitude != 0.0)
                                 addProperty(
                                     "latitude",
                                     DecimalFormat("###.######").format(latitude).toDouble()
                                 )
                             if (longitude != 0.0)
                                 addProperty(
                                     "longitude",
                                     DecimalFormat("###.######").format(longitude).toDouble()
                                 )
                             addProperty("bio", binding.edtBio.text.toString().trim())
                             if (convertedHeightInInches != 0) {
                                 addProperty("height", convertedHeightInInches)
                                 addProperty("height_in", isHeightInFeet)
                             }
                             add("language_ids", jsonArray)
                             addProperty("occupation_title", occupationModel.title)
                             addProperty("occupation_company", occupationModel.company)
                             if (educationModel.level_id != 0)
                                 addProperty("education", educationModel.level_id)
                             addProperty("institution", educationModel.institute)
                             if (educationModel.graduation_year != 0)
                                 addProperty("graduation_year", educationModel.graduation_year)
                             if (astrologyModel.id != 0)
                                 addProperty("astrological_sign", astrologyModel.id)
                             if (castModel.id != 0)
                                 addProperty("caste", castModel.id)
                             if (childrenModel.id != 0)
                                 addProperty("children", childrenModel.id)
                             if (religionModel.id != 0)
                                 addProperty("religion", religionModel.id)
                             if (smokingModel.id != 0)
                                 addProperty("smoking", smokingModel.id)
                             if (relationshipModel.id != 0)
                                 addProperty("looking", relationshipModel.id)
                             if (vaccineStatusModel.id != 0)
                                 addProperty("vaccine", vaccineStatusModel.id)
                             addProperty("instagram_id", instagramId)
                             val userName = if (instagramUserName == "") {
                                 ""
                             } else {
                                 binding.edtInstagram.text.toString().trim()
                             }
                             addProperty("instagram_name", userName)
                             addProperty("instagram_posts", isInstaShowPhoto)*/
                }
            }
            AppUtils.AccountTypes.Matchmaker -> {
                apiRequest = JsonObject().apply {
                    addProperty("first_name", binding.edtFirstName.text.toString().trim())
                    addProperty("last_name", binding.edtLastName.text.toString().trim())
                    addProperty("birth_date", convertedDob)
                    //temp  addProperty("percentage", profileValue.toString())
                    addProperty("gender", gender)
                    /*   addProperty("location", location)
                       if (latitude != 0.0)
                           addProperty(
                               "latitude",
                               DecimalFormat("###.######").format(latitude).toDouble()
                           )
                       if (longitude != 0.0)
                           addProperty(
                               "longitude",
                               DecimalFormat("###.######").format(longitude).toDouble()
                           )*/
                }
            }
        }
        lifecycleScope.launch {
            when {
                !AppUtils.isNetworkAvailable(mActivity) -> {
                    openFailNetworkDialog() {
                        lifecycleScope.launch {
                            Log.e("printDD", "22222")
                            submitUpdateProfileCall(
                                profileViewModel.getProfileSubmitted.firstOrNull() ?: false
                            )
                        }
                    }
//                    AppUtils.showMessageOK(
//                        mActivity,
//                        getString(R.string.app_name),
//                        getString(R.string.common_retry),
//                        getString(R.string.no_internet)
//                    ) { _, _ ->
//                        lifecycleScope.launch {
//                            submitUpdateProfileCall(
//                                profileViewModel.getProfileSubmitted.firstOrNull() ?: false
//                            )
//                        }
//                    }
                }
                else -> {
                    if (isUpdate) {
                        Log.e("printDD", "666666")
                        Log.d("TAG:", "UPDATE_REQUEST: == $apiRequest")

                        println("languageNames------>>>${languageNames.toString()}")
                        profileViewModel.sendBasicProfileData(apiRequest)
                        /*  profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                              profileViewModel.userProfileUpdate(
                                  "Bearer $authToken", userId, apiRequest
                              )
                          }*/

                        var occupationReq = occupationModel.title + "," + occupationModel.company
                        apiDetailedRequest = JsonObject().apply {

                            if (binding.edtBio.text.toString().trim().isNotBlank()) {
                                addProperty("bio", binding.edtBio.text.toString().trim())

                            }
                            //addProperty("height", binding.edtBio.text.toString().trim())
                            if (convertedHeightInInches != 0) {
                                addProperty("height", convertedHeightInInches)
                                addProperty("height_in", isHeightInFeet)
                            }
                            if (languageNames.isNotEmpty()) {
                                addProperty("language", languageNames.toString())

                            }
                            if (occupationReq.isNotEmpty()) {
                                addProperty("occupation", occupationReq)

                            }
                            if (educationModel.level!!.isNotBlank())
                                addProperty("education_level", educationModel.level)
                            if (educationModel.institute!!.isNotBlank())
                                addProperty("institute", educationModel.institute)
                            if (educationModel.graduation_year!! != 0)
                                addProperty("graduation_year", educationModel.graduation_year)
                            if (astrologyModel.value!!.isNotBlank())
                                addProperty("astrological_sign", astrologyModel.value)
                            if (childrenModel.value!!.isNotBlank())
                                addProperty("children", childrenModel.value)
                            if (religionModel.value!!.isNotBlank())
                                addProperty("religion", religionModel.value)
                            if (vaccineStatusModel.value!!.isNotBlank())
                                addProperty("is_vaccinated", vaccineStatusModel.value)
                            if (smokingModel.value!!.isNotBlank())
                                addProperty("smoke", smokingModel.value)
                            if (relationshipModel.value!!.isNotBlank())
                                addProperty("relation_interest", relationshipModel.value)
                            val userName = if (instagramUserName == "") {
                                ""
                            } else {
                                binding.edtInstagram.text.toString().trim()
                            }

                        }
                        profileViewModel.sendDetailedProfileData(apiDetailedRequest)


                    } else {
                        Log.e("printDD", "77777")
                        Log.d("TAG:", "SUBMIT_REQUEST: == $apiRequest")

                        profileViewModel.sendBasicProfileData(apiRequest)

                        var occupationReq = occupationModel.title + "," + occupationModel.company
                        apiDetailedRequest = JsonObject().apply {
                            if (binding.edtBio.text.toString().trim().isNotBlank()) {
                                addProperty("bio", binding.edtBio.text.toString().trim())

                            }
                            //addProperty("height", binding.edtBio.text.toString().trim())
                            if (convertedHeightInInches != 0) {
                                addProperty("height", convertedHeightInInches)
                                addProperty("height_in", isHeightInFeet)
                            }
                            if (languageNames.isNotEmpty()) {
                                addProperty("language", languageNames.toString())

                            }
                            if (occupationReq.isNotEmpty()) {
                                addProperty("occupation", occupationReq)

                            }

                            if (educationModel.level!!.isNotBlank())
                                addProperty("education_level", educationModel.level)
                            if (educationModel.institute!!.isNotBlank())
                                addProperty("institute", educationModel.institute)
                            if (educationModel.graduation_year!! != 0)
                                addProperty("graduation_year", educationModel.graduation_year)
                            if (astrologyModel.value!!.isNotBlank())
                                addProperty("astrological_sign", astrologyModel.value)
                            if (childrenModel.value!!.isNotBlank())
                                addProperty("children", childrenModel.value)
                            if (religionModel.value!!.isNotBlank())
                                addProperty("religion", religionModel.value)
                            if (vaccineStatusModel.value!!.isNotBlank())
                                addProperty("is_vaccinated", vaccineStatusModel.value)
                            if (smokingModel.value!!.isNotBlank())
                                addProperty("smoke", smokingModel.value)
                            if (relationshipModel.value!!.isNotBlank())
                                addProperty("relation_interest", relationshipModel.value)

                        }
                        profileViewModel.sendDetailedProfileData(apiDetailedRequest)

                        /*  profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                              profileViewModel.userProfileSubmit(
                                  "Bearer $authToken", apiRequest
                              )
                          }*/
                    }
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
                mActivity.showSnackBar(binding.layoutProfileMain, it)
                //  AppUtils.openFailNetworkDialog(this@UserProfileActivity)

                println("error message isVerifiedBlock-------")

                isVerifiedBlock(false)
            }
            data.observe(mActivity) { responseModel ->
                if (responseModel.response == "success") {
                    profileViewModel.savePreference(PreferenceKeys.PREF_PROFILE_FLAG, true)
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            lifecycleScope.launch {
                                profileViewModel.savePreference(
                                    PreferenceKeys.PREF_LOGIN_FLAG,
                                    true
                                ) // comment
//                                nextActivity(PreferencesActivity::class.java)
                                if (profileViewModel.getPreferenceSubmitted.firstOrNull() == true) {
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_LOGIN_FLAG,
                                        true
                                    )
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_CURRENT_SCREEN,
                                        "0"
                                    )
                                    lifecycleScope.launch {
//                                        if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                                            nextActivity(AgreementActivity::class.java)
//                                        } else {
//                                            nextActivity(TabManagerActivity::class.java)
//                                            finish()
//                                            finishAffinity()
//                                        }
                                        nextActivity(LoadingActivity::class.java)
                                        finish()
                                        finishAffinity()
                                    }
                                } else {
                                    nextActivity(PreferencesActivity::class.java)
                                }
                            }
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            profileViewModel.savePreference(PreferenceKeys.PREF_LOGIN_FLAG, true)
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_CURRENT_SCREEN,
                                "0"
                            )
                            lifecycleScope.launch {
//                                if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                                    nextActivity(AgreementActivity::class.java)
//                                } else {
//                                    nextActivity(TabManagerActivity::class.java)
//                                    finish()
//                                    finishAffinity()
//                                }
                                nextActivity(LoadingActivity::class.java)
                                finish()
                                finishAffinity()
                            }
                        }
                    }
                }
                /*if (senderId != "" && isLinkedClicked) {
                   getTribeConnection()
                }*/
            }

            basicProfileData.observe(mActivity) { response ->
                if (response.code == 1) {
                    profileViewModel.savePreference(PreferenceKeys.PREF_PROFILE_FLAG, true)
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            lifecycleScope.launch {
                                profileViewModel.savePreference(
                                    PreferenceKeys.PREF_LOGIN_FLAG,
                                    true
                                ) // comment
//                                nextActivity(PreferencesActivity::class.java)
                                if (profileViewModel.getPreferenceSubmitted.firstOrNull() == true) {
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_LOGIN_FLAG,
                                        true
                                    )
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_CURRENT_SCREEN,
                                        "0"
                                    )
                                    lifecycleScope.launch {
//                                        if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                                            nextActivity(AgreementActivity::class.java)
//                                        } else {
//                                            nextActivity(TabManagerActivity::class.java)
//                                            finish()
//                                            finishAffinity()
//                                        }
                                        nextActivity(TabManagerActivity::class.java)
                                        finish()
                                        finishAffinity()
                                    }
                                } else {
                                    if (!preferenceRedirected) {
                                        preferenceRedirected = true
                                        nextActivity(PreferencesActivity::class.java)
                                    }
                                }
                            }
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            profileViewModel.savePreference(PreferenceKeys.PREF_LOGIN_FLAG, true)
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_CURRENT_SCREEN,
                                "0"
                            )
                            lifecycleScope.launch {
//                                if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                                    nextActivity(AgreementActivity::class.java)
//                                } else {
//                                    nextActivity(TabManagerActivity::class.java)
//                                    finish()
//                                    finishAffinity()
//                                }
                                nextActivity(LoadingActivity::class.java)
                                finish()
                                finishAffinity()
                            }
                        }
                    }
                }
            }

            detailedProfileData.observe(mActivity) {
                if (it.code == 1) {
                    profileViewModel.savePreference(PreferenceKeys.PREF_PROFILE_FLAG, true)
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            lifecycleScope.launch {
                                profileViewModel.savePreference(
                                    PreferenceKeys.PREF_LOGIN_FLAG,
                                    true
                                ) // comment
//                                nextActivity(PreferencesActivity::class.java)
                                if (profileViewModel.getPreferenceSubmitted.firstOrNull() == true) {
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_LOGIN_FLAG,
                                        true
                                    )
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_CURRENT_SCREEN,
                                        "0"
                                    )
                                    lifecycleScope.launch {
//                                        if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                                            nextActivity(AgreementActivity::class.java)
//                                        } else {
//                                            nextActivity(TabManagerActivity::class.java)
//                                            finish()
//                                            finishAffinity()
//                                        }
                                        nextActivity(TabManagerActivity::class.java)
                                        finish()
                                        finishAffinity()
                                    }
                                } else {
                                    if (!preferenceRedirected) {
                                        preferenceRedirected = true
                                        nextActivity(PreferencesActivity::class.java)
                                    }
                                }
                            }
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            profileViewModel.savePreference(PreferenceKeys.PREF_LOGIN_FLAG, true)
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_CURRENT_SCREEN,
                                "0"
                            )
                            lifecycleScope.launch {
//                                if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                                    nextActivity(AgreementActivity::class.java)
//                                } else {
//                                    nextActivity(TabManagerActivity::class.java)
//                                    finish()
//                                    finishAffinity()
//                                }
                                nextActivity(LoadingActivity::class.java)
                                finish()
                                finishAffinity()
                            }
                        }
                    }
                }
            }

            profileUploadData.observe(mActivity)//for profile upload
            {
                if (it.code == 1) {
                    println("profileUploadData......................")

                    if (!isMoreImages) {
                        val profilePic = AppUtils.getProfileImage(this@UserProfileActivity)
                        binding.apply {
                            addImagerl.visibility = View.INVISIBLE
                            ivClose.visibility = View.VISIBLE

                            imgSettings.apply {
                                visibility = View.VISIBLE
                                Glide.with(mActivity)
                                    .asBitmap()
                                    .load(profilePic)
                                    //   .thumbnail(/*sizeMultiplier=*/ 0.25f)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(this)
                            }
                            when (accountType) {
                                AppUtils.AccountTypes.Dater -> {
                                    profileValue += AppConstants.PROFILE_PICTURE_DATER //profile picture = 10
                                    updateProfileProgress()
                                }
                                AppUtils.AccountTypes.Matchmaker -> {
                                    profileValue += AppConstants.PROFILE_CONNECTOR
                                    updateProfileProgress()
                                }
                            }
                        }

                    }
                }
            }
            profileUploadError.observe(mActivity)
            {
                if (it.code == 0) {
                    openPictureProblemDialog()
                }
            }
            detailPhotosUploadData.observe(mActivity)//for profile upload
            { responseModel ->
                if (responseModel.code == 1) {
                    println("profileUploadData......................")
                    val profilePhotosModel = ProfilePhotosModel.DataProfile.UserPhotos()

                    for (i in 0 until responseModel!!.dataProfile.userPhotos!!.size) {
                        profilePhotosModel.position =
                            responseModel.dataProfile.userPhotos!![i].position!!
                        profilePhotosModel.photo = responseModel.dataProfile.userPhotos!![i].photo
                    }

                    uploadedMmoreImages.add(profilePhotosModel)
                    /* AppUtils.storeMorePhotos(
                         this@UserProfileActivity,
                         Gson().toJson(uploadedMmoreImages)
                     )*/

                    /* profileViewModel.savePreference(
                         PreferenceKeys.PREF_IMAGES,
                         Gson().toJson(uploadedImages)
                     )*/
                    if (isMoreImages) {
                        responseModel.dataProfile.userPhotos?.let { it1 ->
                            photosAdapter.addImage(it1.toList())
                            AppUtils.storeMorePhotos(
                                this@UserProfileActivity,
                                Gson().toJson(it1.toList())
                            )
                        }
                        uploadedMmoreImages.apply {
                            /* val list = ArrayList<ProfilePhotosModel.DataProfile.UserPhotos>()
                             list.addAll(this)
                             list.forEachIndexed { index, item ->
                                 list[index].isInAppropriate = false
                             }
                             println("list===========${list.size}")

                             for (i in list.size..5) {
                                 list.add(ProfilePhotosModel.DataProfile.UserPhotos())
                             }
                             var x = list.sortedBy { it.position }
                             if (x.elementAt(0).position == 0) {
                                 val elementWithId0 = x.elementAt(0)
                                 x = x.filter { it.position != 0 }
                                 while (x.size < 6) {
                                     var index = (x as MutableList).indexOf(last())
                                     (x as MutableList).add(index + 1, elementWithId0)
                                 }
                             }
                             photosAdapter.addImage(list)*/
                            if (!isAdd) {
                                if (this.isNotEmpty()) {
                                    isAdd = true
                                    profileValue += AppConstants.PROFILE_DATER
                                    updateProfileProgress()
                                }
                            }
                        }
                    }
                }
            }
            updateData.observe(mActivity) {
                when (accountType) {
                    AppUtils.AccountTypes.Dater -> {
                        lifecycleScope.launch {
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_LOGIN_FLAG,
                                true
                            ) // comment
                            if (isFromSettings) {
                                onBackPressed()
                            } else {
                                if (profileViewModel.getPreferenceSubmitted.firstOrNull() == true) {
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_LOGIN_FLAG,
                                        true
                                    )
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_CURRENT_SCREEN,
                                        "0"
                                    )
                                    lifecycleScope.launch {
//                                        if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                                            nextActivity(AgreementActivity::class.java)
//                                        } else {
//                                            nextActivity(TabManagerActivity::class.java)
//                                            finish()
//                                            finishAffinity()
//                                        }
                                        nextActivity(LoadingActivity::class.java)
                                        finish()
                                        finishAffinity()
                                    }
                                } else {
                                    nextActivity(PreferencesActivity::class.java)
                                }
                            }
                        }
                    }
                    AppUtils.AccountTypes.Matchmaker -> {
                        profileViewModel.savePreference(PreferenceKeys.PREF_LOGIN_FLAG, true)
                        profileViewModel.savePreference(PreferenceKeys.PREF_CURRENT_SCREEN, "0")
                        lifecycleScope.launch {
                            if (isFromSettings) {
                                onBackPressed()
                            } else {
//                                if (profileViewModel.isDisableAgreement.firstOrNull() == false) {
//                                    nextActivity(AgreementActivity::class.java)
//                                } else {
//                                    nextActivity(TabManagerActivity::class.java)
//                                    finish()
//                                    finishAffinity()
//                                }
                                nextActivity(LoadingActivity::class.java)
                                finish()
                                finishAffinity()
                            }
                        }
                    }
                }
            }

            updateError.observe(mActivity) {
                mActivity.showSnackBar(binding.layoutProfileMain, it.message.toString())
            }
            uploadData.observe(mActivity) { responseModel ->
                if (responseModel.response == "success") {
                    val profileModel = UserImagesResponseModel.Item()
                    profileModel.id = responseModel.id
                    profileModel.imageUrl = responseModel.image_url
                    profileModel.is_more = isMoreImages
                    profileModel.isProfile = !isMoreImages
                    uploadedImages.add(profileModel)
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_IMAGES,
                        Gson().toJson(uploadedImages)
                    )
                    when (isMoreImages) {
                        true -> {
                            uploadedImages.filter {
                                it.is_more == true && it.is_instagram == false && it.isProfile == false
                            }.apply {
                                val list = ArrayList<UserImagesResponseModel.Item>()
                                list.addAll(this)
                                list.forEachIndexed { index, item ->
                                    list[index].isInAppropriate = false
                                }
                                for (i in list.size..5) {
                                    list.add(UserImagesResponseModel.Item())
                                }
                                var x = list.sortedBy { it.id }
                                if (x.elementAt(0).id == 0) {
                                    val elementWithId0 = x.elementAt(0)
                                    x = x.filter { it.id != 0 }
                                    while (x.size < 6) {
                                        var index = (x as MutableList).indexOf(last())
                                        (x as MutableList).add(index + 1, elementWithId0)
                                    }
                                }
                                //temp   photosAdapter.addAll(x)
                                if (!isAdd) {
                                    if (this.isNotEmpty()) {
                                        isAdd = true
                                        profileValue += AppConstants.PROFILE_DATER
                                        updateProfileProgress()
                                    }
                                }
                            }
                        }
                        false -> {
                            profileViewModel.savePreference(
                                PreferenceKeys.PREF_PROFILE_IMAGE,
                                responseModel.image_url.toString()
                            )
                            binding.apply {
                                if (responseModel.image_url != null && responseModel.image_url != "") {
                                    when (accountType) {
                                        AppUtils.AccountTypes.Dater -> {
                                            profileValue += AppConstants.PROFILE_PICTURE_DATER //profile picture = 10
                                            updateProfileProgress()
                                        }
                                        AppUtils.AccountTypes.Matchmaker -> {
                                            profileValue += AppConstants.PROFILE_CONNECTOR
                                            updateProfileProgress()
                                        }
                                    }
                                    addImagerl.visibility = View.INVISIBLE
                                    ivClose.visibility = View.VISIBLE
                                    imgSettings.apply {
                                        visibility = View.VISIBLE
                                        Glide.with(mActivity)
                                            .asBitmap()
                                            .load(responseModel.image_url)
                                            //   .thumbnail(/*sizeMultiplier=*/ 0.25f)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .into(this)
                                    }
                                }
                            }

                            if (socialMediaUserModel.socialType != "") {
                                when (accountType) {
                                    AppUtils.AccountTypes.Dater -> {
                                        binding.apply {
                                            if (edtLocation.text.toString().trim().isEmpty1()) {
                                                isEditLocation = false
                                                edtLocation.performClick()
                                            } else {
                                                lifecycleScope.launch {
                                                    submitUpdateProfileCall(
                                                        profileViewModel.getProfileSubmitted.firstOrNull()
                                                            ?: false
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    AppUtils.AccountTypes.Matchmaker -> {
                                        lifecycleScope.launch {
                                            submitUpdateProfileCall(
                                                profileViewModel.getProfileSubmitted.firstOrNull()
                                                    ?: false
                                            )
                                        }
                                    }
                                }
                            }
                            setGradientToProgress(true)
                        }
                    }
                } else {
                    if (!responseModel.message.isNullOrEmpty()) {
                        if (socialMediaUserModel.socialType != "") {
                            lifecycleScope.launch {
                                socialMediaUserModel.profilePicture = ""
                                binding.apply {
                                    imgSettings.visibility = View.INVISIBLE
                                    addImagerl.visibility = View.VISIBLE
                                    ivClose.visibility = View.INVISIBLE
                                }

                                profileViewModel.savePreference(
                                    PreferenceKeys.PREF_PROFILE_IMAGE,
                                    ""
                                )
                                AppUtils.storeProfileImage(this@UserProfileActivity, "")
                                AppUtils.saveSocialMediaData(
                                    AppController.context!!,
                                    Gson().toJson(socialMediaUserModel)
                                )

                                profileViewModel.savePreference(
                                    PreferenceKeys.PREF_SOCIAL_MEDIA_USER,
                                    Gson().toJson(socialMediaUserModel)
                                )
                            }
                        }

                        openInvalidPictureDialog()
//                        mActivity.showSnackBar(binding.layoutMain , responseModel.message)
                    } else if (!responseModel.detail.isNullOrEmpty()) {
                        mActivity.showSnackBar(binding.layoutProfileMain, responseModel.detail)
                    }
                    if (!isMoreImages)
                        setGradientToProgress(false)
                }
            }
            uploadError.observe(mActivity) {
                Log.e("TAG", "ERROR === $it")
                if (it == null) {
                    mActivity.showSnackBar(
                        binding.layoutProfileMain,
                        getString(R.string.error_something_went_wrong)
                    )
                } else {
                    if (!it.message.isNullOrEmpty()) {
                        mActivity.showSnackBar(binding.layoutProfileMain, it.message.toString())
                    } else if (!it.detail.isNullOrEmpty()) {
                        mActivity.showSnackBar(binding.layoutProfileMain, it.detail.toString())
                    }
                }
            }
            errorBasicDetails.observe(mActivity) {
                Log.e("TAG", "ERROR === $it")
                if (it == null) {
                    mActivity.showSnackBar(
                        binding.layoutProfileMain,
                        getString(R.string.error_something_went_wrong)
                    )
                } else {
                    if (!it.message.isNullOrEmpty()) {
                        mActivity.showSnackBar(binding.layoutProfileMain, it.message.toString())
                    }
                }
            }

            errorDetailedProfile.observe(mActivity) {
                Log.e("TAG", "ERROR === $it")
                if (it == null) {
                    mActivity.showSnackBar(
                        binding.layoutProfileMain,
                        getString(R.string.error_something_went_wrong)
                    )
                } else {
                    if (!it.message.isNullOrEmpty()) {
                        mActivity.showSnackBar(binding.layoutProfileMain, it.message.toString())
                    }
                }
            }


            deleteData.observe(mActivity) { _ ->
                try {
                    //database list derived from user response
                    uploadedImages.find {
                        it.id == deletedId
                    }?.let {
                        uploadedImages.remove(it)
                        //temp  photosAdapter.removeImage(it)
                        profileViewModel.savePreference(
                            PreferenceKeys.PREF_IMAGES,
                            Gson().toJson(uploadedImages)
                        )
                    }
                    if (uploadedImages.isEmpty()) {
                        isAdd = false
                        profileValue -= AppConstants.PROFILE_DATER
                        updateProfileProgress()
                    }
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            profileValue -= AppConstants.PROFILE_PICTURE_DATER
                            updateProfileProgress()
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            profileValue -= AppConstants.PROFILE_CONNECTOR
                            updateProfileProgress()
                        }
                    }
                    binding.apply {
                        imgSettings.visibility = View.INVISIBLE
                        addImagerl.visibility = View.VISIBLE
                        ivClose.visibility = View.INVISIBLE
                    }
                    path = ""
                    //    socialMediaUserModel.socialType = ""
                    socialMediaUserModel.profilePicture = ""
                    AppUtils.saveSocialMediaData(
                        AppController.context!!,
                        Gson().toJson(socialMediaUserModel)
                    )

                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_PROFILE_IMAGE,
                        ""
                    )
                    AppUtils.storeProfileImage(this@UserProfileActivity, "")

                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }

            deleteMoreImage.observe(mActivity) { _ ->
                try {

                    //database list derived from user response
                    uploadedMmoreImages.find {
                        it.position == deletedId
                    }?.let {
                        photosAdapter.removeImage(deletedId!!)
                        uploadedMmoreImages.remove(it)
                        println("List after remove $deletedId & ${it.position}==== $uploadedMmoreImages")
                        /*profileViewModel.savePreference(
                            PreferenceKeys.PREF_IMAGES,
                            Gson().toJson(uploadedImages)
                        )*/
                        AppUtils.storeMorePhotos(
                            this@UserProfileActivity,
                            Gson().toJson(uploadedMmoreImages)
                        )
                    }
                    if (uploadedMmoreImages.isEmpty()) {
                        isAdd = false
                        profileValue -= AppConstants.PROFILE_DATER
                        updateProfileProgress()
                    }
                    binding.apply {
                        imgSettings.visibility = View.INVISIBLE
                        addImagerl.visibility = View.VISIBLE
                        ivClose.visibility = View.INVISIBLE
                    }
                    path = ""
                    socialMediaUserModel.socialType = ""
                    /* profileViewModel.savePreference(
                         PreferenceKeys.PREF_PROFILE_IMAGE,
                         ""
                     )*/

                    /*uploadedImages.filter {
                        it.is_more == true
                    }.let {
                        if (it.isEmpty()) {
                            isAdd = false
                            profileValue -= AppConstants.PROFILE_DATER
                            updateProfileProgress()
                        }
                    }*/

                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }

        /*    error.observe(mActivity) {
                if (it.code != null && it.error_type != null) {
                    if (it.code == 400 && it.error_type == "OAuthException") {
                        //if error from instagram connection then show failure dialog
                        //   openFailDialog()
                    } else {
                        mActivity.showSnackBar(binding.layoutProfileMain, it.message.toString())
                    }
                } else {
                    mActivity.showSnackBar(binding.layoutProfileMain, it.message.toString())
                }
            }*/
            instagramToken.observe(mActivity) {
                instagramAccessToken = it
            }
            instaProfile.observe(mActivity) { profile ->
                instagramModel = profile

                if (profile.username!="") //added by nirali
                {
                    binding.edtInstagram.apply {
                        AppUtils.storeInstaUserName(this@UserProfileActivity,profile.username!!.toString())
                        setText(profile.username)
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.color_instagram,
                            0,
                            R.drawable.right_arrow,
                            0
                        )
                        background = ContextCompat.getDrawable(
                            mActivity,
                            R.drawable.corner_blue_border_bg
                        )
                    }
                }
                if (profile.accountType != "PERSONAL") {
                    showSnackBar(
                        binding.layoutProfileMain,
                        getString(R.string.insta_authentication)
                    )
                } else {
                    instagramId = profile.id ?: ""
                    instagramUserName = profile.username ?: ""
                    /*binding.apply {
                        layoutShowInProfile.visibility = View.VISIBLE
                        toggleInstagram.isChecked = true
                    }*/
                    userSettings()
                }
            }



            settingsData.observe(mActivity) {
                if (it.response == "success") {
                    profileValue += AppConstants.PROFILE_DATER
                    updateProfileProgress()
                    binding.edtInstagram.apply {
                        setText(instagramUserName)
                        setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.color_instagram,
                            0,
                            R.drawable.right_arrow,
                            0
                        )
                        background = ContextCompat.getDrawable(
                            mActivity,
                            R.drawable.corner_blue_border_bg
                        )
                    }
                    profileViewModel.savePreference(PreferenceKeys.PREF_INSTA_ID, instagramId)
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_INSTA_NAME,
                        instagramUserName
                    )
                    if (instagramModel.accountType == "PERSONAL") {
                        getInstagramMedia(
                            instagramModel.media?.data ?: arrayListOf(),
                            instagramAccessToken
                        )
                    }
                } else {
                    mActivity.showSnackBar(binding.layoutProfileMain, it.message.toString())
                }
            }
            instaImages.observe(mActivity) { mediaList ->
                instaImagesList.clear()
                instaImagesList.addAll(mediaList.distinctBy {
                    it
                })
                uploadInstagramImages()
            }
            sendInstaImagesData.observe(mActivity) {
                if (it.response == "success") {
                    instaImagesList.clear()
                    instaImagesList.addAll(it.imageUrl?.distinctBy { url ->
                        url
                    }!!)
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_INSTA_IMAGES,
                        Gson().toJson(instaImagesList)
                    )
                }
            }
            sendInstaImagesError.observe(mActivity) {
                mActivity.showSnackBar(binding.layoutProfileMain, it.detail.toString())
            }
            imageVerification.observe(mActivity) {
                if (it.response == "success") {
                    when (it.data?.appropriate) {
                        true -> {
                            isProfileVerified = 1
                            //if it is appropriate then we are upload that image on server
                            uploadUserImage()
                            if (isMoreImages) {
                                //  photosAdapter.changePhotoInvalid(addMoreIndex, true)
                            }
                        }
                        false -> {
                            openPictureProblemDialog()
                            if (isMoreImages) {
                                //  photosAdapter.changePhotoInvalid(addMoreIndex, false)
                            }
                        }
                    }
                }
            }
            imageVerificationError.observe(mActivity) {
                try {
                    mActivity.showSnackBar(binding.layoutProfileMain, it.message.toString())
                } catch (e: Exception) {
                    e.printStackTrace()
                    mActivity.showSnackBar(binding.layoutProfileMain, "Try again.")
                }
            }
            userProfileData.observe(mActivity) {
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_FIRST_NAME,
                    it.data?.firstName ?: ""
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_LAST_NAME,
                    it.data?.lastName ?: ""
                )
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_IS_VERIFIED,
                    it.data?.is_verified ?: false
                )
                profileValue = when (accountType) {
                    AppUtils.AccountTypes.Dater -> {
                        it.data?.percentage ?: AppConstants.PROFILE_DATER
                    }
                    AppUtils.AccountTypes.Matchmaker -> {
                        it.data?.percentage ?: AppConstants.PROFILE_CONNECTOR
                    }
                    else -> {
                        it.data?.percentage ?: AppConstants.PROFILE_DATER
                    }
                }

                if (accountType == AppUtils.AccountTypes.Dater) {
                    println("userProfileData isVerifiedBlock-------")
                    isVerifiedBlock(it.data?.is_verified!!)

                }

                profileViewModel.savePreference(
                    PreferenceKeys.PREF_PROFILE_PERCENTAGE,
                    profileValue
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
                    USER_MALE -> {
                        0
                    }
                    USER_FEMALE -> {
                        1
                    }

                    USER_NON_BINARY -> {
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
                if (!it.data.travelCity.isNullOrEmpty()) {
                    val travelModel = TravelLocationListModel()
                    travelModel.city = it.data.travelCity.split(",")[0]
                    travelModel.country = it.data.travelCity.split(",")[1]
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_TRAVEL_LOCATION,
                        Gson().toJson(travelModel)
                    )
                }
                try {
                    Handler(Looper.getMainLooper()).postDelayed({
                        onResume()
                    }, 1000)
                } catch (e: Exception) {
                }
            }
            /* userProfileError.observe(mActivity) {
                 println("userProfileError isVerifiedBlock-------")

                 isVerifiedBlock(false)
             }*/
            imagesData.observe(mActivity) { imagesList ->
                if (!imagesList.isNullOrEmpty()) {
                    if (imagesList.isNotEmpty()) {
                        imagesList.onEach {
                            it.imageUrl = it.image
                            it.is_more = true
                        }.find {
                            it.isProfile == true
                        }.let {
                            it?.is_more = false
                            binding.apply {
                                if (it?.imageUrl != null && it?.imageUrl != "") {
                                    addImagerl.visibility = View.INVISIBLE
                                    ivClose.visibility = View.VISIBLE
                                    imgSettings.apply {
                                        visibility = View.VISIBLE
                                        Glide.with(mActivity)
                                            .load(it?.imageUrl)
                                            // .thumbnail(/*sizeMultiplier=*/ 0.000025f)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .into(binding.imgSettings)
                                    }
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_PROFILE_IMAGE,
                                        it?.imageUrl.toString()
                                    )
                                } else {
                                    addImagerl.visibility = View.VISIBLE
                                    ivClose.visibility = View.GONE
                                    imgSettings.visibility = View.INVISIBLE
                                    AppUtils.storeProfileImage(this@UserProfileActivity, "")
                                    profileViewModel.savePreference(
                                        PreferenceKeys.PREF_PROFILE_IMAGE,
                                        ""
                                    )
                                }
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
                }
            }
            imagesError.observe(mActivity) {
            }
            profileVerificationData.observe(mActivity) { responseModel ->
                if (responseModel.is_verified) {
                    userAlreadyVerifiedDialog()
                    profileViewModel.savePreference(
                        PreferenceKeys.PREF_IS_VERIFIED,
                        true
                    )
                    profileViewModel.savePreference(            // For verification popup to be displayed every 4 days
                        PreferenceKeys.IS_PROFILE_VERIFIED,
                        true
                    )
                    verifiedUser()
                    //   binding.layoutVerified.visibility = View.GONE
                } else {
                    println("profileVerificationData else isVerifiedBlock-------")

                    isVerifiedBlock(false)
                    if (dialogVerification != null && dialogVerification!!.isShowing)
                        dialogVerification!!.dismiss()
                    //    openPictureProblemDialog()
                    openVerificationProblemDialog()
                    //  mActivity.showSnackBar(binding.layoutMain , responseModel.message!!)
                }
            }
            gestureError.observe(mActivity)
            {
                //  snackbar = showSnackBarMargin(v = binding.layoutMain, message = message)
                //   mActivity.showSnackBar(binding.layoutProfileMain, it.message!!) //commented by nirali

                println("gestureError else isVerifiedBlock-------")

                isVerifiedBlock(false)
                if (dialogVerification != null && dialogVerification!!.isShowing)
                    dialogVerification!!.dismiss()
                //    openPictureProblemDialog()
                openVerificationProblemDialog()
            }
            profileGestureVerificationData.observe(mActivity) { responseModel ->
                if (responseModel.data.is_profile_verified == 1) {
                    userAlreadyVerifiedDialog()
                    AppUtils.storeProfileVerified(
                        this@UserProfileActivity,
                        responseModel.data.is_profile_verified
                    )

                    verifiedUser()
                } else {
                    println("profileGestureVerificationData else isVerifiedBlock-------")

                    isVerifiedBlock(false)
                    if (dialogVerification != null && dialogVerification!!.isShowing)
                        dialogVerification!!.dismiss()
                    //    openPictureProblemDialog()
                    openVerificationProblemDialog()
                }
            }
        }
        settingsViewModel.apply {
            planPurchaseData.observe(mActivity) {
                if (it.response == "success") {
                    nextActivity(TravelActivity::class.java)
                }
            }
            planPurchaseError.observe(mActivity) {
                mActivity.showSnackBar(binding.layoutProfileMain, it.response.toString())
            }
        }
    }


    // api call to upload user profile image.
    private fun uploadUserImage() {
        //"0" != Profile and "1" == Profile
        val profile = if (isMoreImages) "0" else "1"
        val imagePath = path ?: return

        println("imagePath uploadUserImage-------${imagePath}")
        val file = File(imagePath)
        if (!AppUtils.isNetworkAvailable(this)) {
            openFailNetworkDialog() { uploadUserImage() }
//            AppUtils.showMessageOK(
//                this,
//                getString(R.string.app_name),
//                getString(R.string.common_retry),
//                getString(R.string.no_internet)
//            ) { _, _ ->
//                uploadUserImage()
//            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {

                    /* var tempfile = if (AppUtils.isFileLessThan4MB(file)) {
                         file
                     } else {*/
                    var tempfile = Compressor.compress(this@UserProfileActivity, file) {
                        quality(70)
                        format(Bitmap.CompressFormat.JPEG)
                        //size(200000) // 2 MB
                    }

                    lifecycleScope.launch {
                        profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            /*profileViewModel.userProfilePicture(
                                "Bearer $authToken", profile, tempfile
                            )*/
                            if (isMoreImages) {
                                profileViewModel.uploadProfilePhotos(
                                    addMoreIndex.toString(), tempfile
                                )
                            } else {
                                profileViewModel.uploadProfilePicture(
                                    tempfile
                                )
                            }

                        }
                    }
                }
            }
        }
    }


    private fun deleteUserImage(id: Int?, isDeleteMore: Boolean = false) {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { deleteUserImage(id, isDeleteMore) }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    deleteUserImage(id)
//                }
            }
            else -> {
                lifecycleScope.launch {
                    profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        if (isDeleteMore) {
                            if (id != null) {
                                profileViewModel.userProfileImagesDelete(id)
                            }
                        } else {
                            profileViewModel.userProfilePictureDelete(isProfileVerified)
                        }
                    }
                }
            }
        }
    }

    private fun uploadInstagramImages() {
        val jsonArray = JsonArray()
        instaImagesList.forEach {
            jsonArray.add(it)
        }
        val apiRequest = JsonObject().apply {
            add("instagram", jsonArray)
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { uploadInstagramImages() }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    uploadInstagramImages()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        profileViewModel.uploadInstaImages(
                            "Bearer $authToken", apiRequest
                        )
                    }
                }
            }
        }
    }

    private fun userSettings() {
        val jsonObject = JsonObject().apply {
            binding.apply {
                addProperty("is_instagram_connect", instagramId.isNotEmpty())
                addProperty("instagram_name", instagramUserName)
            }
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { userSettings() }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    userSettings()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        profileViewModel.userSettingsUpdate(
                            "Bearer $authToken", userId, jsonObject
                        )
                    }
                }
            }
        }
    }

    private fun openLocationDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val binding = DialogHelpUsLocationBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                txtGotIt.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        getLocationPermission()
                    }
                }
                btnSetting.setOnClickListener {
                    //open settings for location permission
                    val intent =
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts(
                        "package",
                        packageName, null
                    )
                    intent.data = uri
                    try {
                        startActivity(intent)
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                }
            }
            try {
                setZoomDialogWindowAttributes()
                show()
                binding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openFailDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val fbBinding = DialogSocialLoginBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(fbBinding.root)
            fbBinding.apply {
                view.visibility = View.GONE
                btnCancel.visibility = View.GONE
                btnContinue.visibility = View.GONE
                btnOk.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                        }
                    }
                }
                txtTitle.text = getString(R.string.login_failure)
                txtContent.text = getString(R.string.failure_message)
            }
            setZoomDialogWindowAttributes()
            show()
            fbBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }


    private fun sendImageVerification() {
        // Log.d("imageFile", "-------->${File(path!!)}")
        // mGestureVerificationImage


        var imagePath = persistImage(mGestureVerificationImage!!, "newImage")
        var tempfile: File?
        var gender = ""
        var genderPref = 0
        var userId = ""
        var requestGender = ""
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                /*tempfile = if (AppUtils.isFileLessThan4MB(imagePath)) {
                    imagePath
                }
                else {
                    Compressor.compress(this@UserProfileActivity, imagePath) {
                        quality(80)
                        format(Bitmap.CompressFormat.JPEG)
                        //size(200000) // 2 MB
                    }
                }*/

                tempfile = Compressor.compress(this@UserProfileActivity, imagePath) {
                    quality(80)
                    format(Bitmap.CompressFormat.JPEG)
                    //size(200000) // 2 MB
                }

                lifecycleScope.launch {
                    gender = profileViewModel.getGender.firstOrNull().toString()
                    if (gender == "0") {
                        genderPref = 1
                    } else if (gender == "1") {
                        genderPref = 2
                    } else {
                        genderPref = 1
                    }
//                    genderPref = profileViewModel.getGenderPref
                    println("genderPref---------->>>>>>$genderPref")
                    userId = profileViewModel.getUserId.firstOrNull().toString()

                    requestGender = genderPref.toString()
                    // requestGender =  "1"
                    /*  if (gender == "0") {
                          requestGender = "M"
                      } else {
                          requestGender = "F"
                      }*/

                    val mediaTypeImage: MediaType = "image/*".toMediaTypeOrNull()!!
                    val requestBody: RequestBody = tempfile!!.asRequestBody(mediaTypeImage)
                    val imageBody: MultipartBody.Part =
                        MultipartBody.Part.createFormData("photo", tempfile!!.name, requestBody)
                    val requestBodyUserId: RequestBody =
                        userId.toRequestBody("text/plain".toMediaTypeOrNull())
                    val requestBodyGender: RequestBody =
                        requestGender.toRequestBody("text/plain".toMediaTypeOrNull())

                    when {
                        !AppUtils.isNetworkAvailable(mActivity) -> {
                            openFailNetworkDialog() { sendImageVerification() }

                        }
                        else -> {

                            lifecycleScope.launch {

                                profileViewModel.imageGestureVerification(
                                    requestBodyGender,
                                    imageBody
                                )

                                /* profileViewModel.getAuthToken.firstOrNull()?.let { token ->
                                     if (token.isNotEmpty()) {
                                         profileViewModel.sendImageGestureVerification(
                                             requestBodyUserId,
                                             requestBodyGender,
                                             imageBody
 //                                        "Bearer $token"
                                         )
                                     }
                                 }*/
                            }
                        }
                    }
                }
            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        startCamera()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        resetCamera()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        releaseCamera()
    }

    private fun startCamera() {
        camera = Camera.open(1)
        camera!!.setDisplayOrientation(90)
        val parameters = camera!!.parameters.apply {
            supportedPreviewSizes
        }
        try {
            camera!!.parameters = parameters
            camera!!.setPreviewDisplay(surfaceHolder)
            camera!!.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun getLocationPermission() {
        Dexter.withContext(mActivity).withPermissions(
            arrayListOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                when {
                    report.areAllPermissionsGranted() -> {
                        startForResult?.launch(
                            Intent(
                                mActivity,
                                LocationActivity::class.java
                            )
                        )
                    }
                    report.deniedPermissionResponses != null && report.deniedPermissionResponses.size > 0 -> {
                        openLocationDialog()
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        })
            .withErrorListener {
                Log.e("Dexter", "There was an error: $it")
            }
            .check()
    }

    private fun resetCamera() {
        if (surfaceHolder!!.surface == null) {
            // Return if preview surface does not exist
            return
        }

        // Stop if preview surface is already running.
        camera!!.stopPreview()
        try {
            // Set preview display
            camera!!.setPreviewDisplay(surfaceHolder)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Start the camera preview...
        camera!!.startPreview()
    }

    override fun onPictureTaken(data: ByteArray?, camera: Camera?) {
        val bmp = BitmapFactory.decodeByteArray(data, 0, data!!.size)
        saveImageAsync(bmp, false)
    }

    private fun captureImageVerification() {
        if (camera != null) {
            camera!!.takePicture(null, null, this)
        }
    }

    private fun saveImageAsync(bitmap: Bitmap?, isProfileUpload: Boolean) =
        runBlocking(Dispatchers.IO) {
            try {
                val file = createImageFile()
                if (file != null) {
                    val out = FileOutputStream(file)
                    bitmap?.compress(Bitmap.CompressFormat.PNG, 0, out)
                    out.flush()
                    out.close()
                    Log.e("TAG_IMAGE", file.path)
                    if (isProfileUpload) {
                        mActivity.path = file.absolutePath
                        //sendImageVerification()
                        uploadUserImage()
                    } else {
                        resetCamera()
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }

    private fun releaseCamera() {
        camera!!.stopPreview()
        camera!!.release()
        camera = null
    }

    private fun openSubscriptionDialog() {
        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        subscriptionsBinding = DialogSubscriptionPlansBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCanceledOnTouchOutside(true)
            setContentView(subscriptionsBinding.root)
            subscriptionsBinding.setSubscriptionData(this)
            setOnCancelListener {
                dialog.dismiss()
                binding.toggleTravelLocation.isChecked = false
            }
            subscriptionsBinding.apply {
                txtText.text = getString(R.string.subscription, planType)
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                subscriptionsBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun DialogSubscriptionPlansBinding.setSubscriptionData(
        dialog: Dialog
    ) {
        plansAdapter.addAll(mAllSubList)
        rvPlansList.adapter = plansAdapter
        plansAdapter.setValues(planModel)
        layoutIndicatorGreen.visibility = View.GONE
        layoutIndicator.visibility = View.VISIBLE
        val mListData = arrayListOf(
            SubscriptionPlansModel(
                R.drawable.pager1_white,
                getString(R.string.pager_1)
            ),
            SubscriptionPlansModel(
                R.drawable.pager2_white,
                getString(R.string.pager_2)
            ),
            SubscriptionPlansModel(
                R.drawable.pager3_white,
                getString(R.string.pager_3, "25")
            ),
            SubscriptionPlansModel(
                R.drawable.pager4_white,
                getString(R.string.pager_4, "2")
            ),
            SubscriptionPlansModel(
                R.drawable.pager5_white,
                getString(R.string.pager_5)
            ),
            SubscriptionPlansModel(
                R.drawable.pager6_white,
                getString(R.string.pager_6)
            ),
            SubscriptionPlansModel(
                R.drawable.pager7_white,
                getString(R.string.pager_7)
            ),
            SubscriptionPlansModel(
                R.drawable.pager8_white,
                getString(R.string.pager_8)
            )

        )
        adapter.addAll(mListData)
        mActivity.setShaderView(
            txtText,
            R.color.blue_gradient_2,
            R.color.blue_gradient_3
        )
        planPager.adapter = adapter
        adapter.setValues(planModel)
        mActivity.setShaderView(
            txtLink,
            R.color.blue_gradient_2,
            R.color.blue_gradient_3
        )
        layoutIndicator.setViewPager(planPager)
        layoutIndicatorGreen.setViewPager(planPager)
        layoutPlan.setOnClickListener {
            plansAdapter.apply {
                isBooster = true
                selectedPosition = -1
                notifyDataSetChanged()
            }
            when (planModel.planType) {
                getString(R.string.elite) -> {
                    txtPlanMonthOffer.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPagerDesc,
                            theme
                        )
                    )
                    txtPlanMonth.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPagerDesc,
                            theme
                        )
                    )
                    txtPlanValue.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPagerDesc,
                            theme
                        )
                    )
                    layoutPlan.background =
                        ContextCompat.getDrawable(
                            mActivity,
                            R.drawable.corner_mint_bottom_gradient_bg
                        )

                }
                else -> {
                    txtPlanMonthOffer.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.white,
                            theme
                        )
                    )
                    txtPlanMonth.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.white,
                            theme
                        )
                    )
                    txtPlanValue.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.white,
                            theme
                        )
                    )
                    layoutPlan.background =
                        ContextCompat.getDrawable(mActivity, R.drawable.profile_progress_bg)
                }
            }
        }
        planPager.currentItem = mListData.size - 1
        boosterModel = boostersList[mListData.size - 1]
        if (!boosterModel?.title.isNullOrEmpty()) {
            txtPlanType.text = boosterModel?.title!!.split("(")[0]
        }
        txtPlanMonth.text = boosterModel?.description
        txtPlanValue.text = boosterModel?.price
        planPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (planModel.planType) {
                    getString(R.string.elite) -> {
                        planPager.background = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.top_mint_corner_bottom_gradient_bg,
                            theme
                        )
                    }
                    else -> {
                        planPager.background = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.top_blue_corner_gradient_bg,
                            theme
                        )
                    }
                }
                boosterModel = boostersList[position]
                if (!boosterModel?.title.isNullOrEmpty()) {
                    txtPlanType.text = boosterModel?.title!!.split("(")[0]
                }
                txtPlanMonth.text = boosterModel?.description
                txtPlanValue.text = boosterModel?.price
                if (position == mListData.size - 1) {
                    txtGetDiamond.apply {
                        isEnabled = true
                        isClickable = true
                    }
                } else {
                    txtGetDiamond.apply {
                        isEnabled = false
                        isClickable = false
                    }
                }
                when (position) {
                    2 -> {
                        txtPlanType.visibility = View.GONE
                        layoutPlan.visibility = View.GONE
                    }
                    0, 1, 3, 4, 5, 6, 7 -> {
                        txtPlanType.visibility = View.VISIBLE
                        layoutPlan.visibility = View.VISIBLE
                    }
                }
            }
        })
        txtLink.setOnClickListener {
            startActivity(
                Intent(
                    mActivity,
                    WebViewActivity::class.java
                ).putExtra("url", AppConstants.SWIPE_FWD_URL)
            )
        }

        txtGetDiamond.setOnClickListener {
            dialog.dismiss()
            if (isBooster) {
                //bp.purchase(mActivity, boosterModel?.productId)
                hasPurchaseOnGoogle()
            } else {
                if (productId != "") {
                    //check if already purchased plan & selected plan is not same then apply for upgrade plan
                    if (productId != planModel.planSkuProductId) {
                        //if user wants to upgrade plan
                        //bp.updateSubscription(mActivity, productId, planModel.planSkuProductId)
                    } else {
                        Toast.makeText(mActivity, "Already Purchased", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    //bp.subscribe(mActivity, planModel.planSkuProductId)
                    hasPurchaseOnGoogle()
                }
            }
        }
    }

    override fun onDestroy() {
        if (this::billingClient.isInitialized) {
            billingClient.endConnection()
        }
        super.onDestroy()
    }

    private fun planPurchase() {
        val planId = when (planType) {
            AppConstants.PLAN_ELITE -> {
                3
            }
            AppConstants.PLAN_PREMIER -> {
                2
            }
            else -> {
                1
            }
        }
        val jsonObject = JsonObject().apply {
            binding.apply {
                addProperty("ref_plan", planId)
                addProperty("device", AppConstants.DEVICE_TYPE)
                addProperty("end_date", exDate)
                addProperty("transaction_number", orderId)
            }
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { planPurchase() }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    planPurchase()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        settingsViewModel.planPurchase(
                            "Bearer $authToken", jsonObject
                        )
                    }
                }
            }
        }
    }

    private fun boosterPurchase() {
        val jsonObject = JsonObject().apply {
            binding.apply {
                addProperty("booster_type", AppConstants.BOOSTER_LOCATION)
                addProperty("device", AppConstants.DEVICE_TYPE)
                addProperty("plan_quota", boosterModel?.description?.take(1)?.toInt())
                addProperty("transaction_number", orderId)
            }
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { boosterPurchase() }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    boosterPurchase()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        settingsViewModel.boosterPurchase(
                            "Bearer $authToken", jsonObject
                        )
                    }
                }
            }
        }
    }

    private fun sendReceipt(receipt: String) {
        val jsonObject = JsonObject().apply {
            addProperty("receipt_data", receipt)
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { sendReceipt(receipt) }
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    sendReceipt(receipt)
//                }
            }
            else -> {
                lifecycleScope.launch {
                    settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        settingsViewModel.sendReceipt(
                            "Bearer $authToken", jsonObject
                        )
                    }
                }
            }
        }
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
                    /*  profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                          profileViewModel.getUserProfileDetails(
                              false, "Bearer $authToken", userId
                          )
                      }*/
                }
            }
        }
    }

    private fun setUpBillingClient() {
        billingClient =
            BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build()
        billingClient.startConnection(object : BillingClientStateListener {

            override fun onBillingServiceDisconnected() {
                billingClient.startConnection(this)
            }

            override fun onBillingSetupFinished(p0: BillingResult) {
                if (p0.responseCode == BillingClient.BillingResponseCode.OK) {
                    Log.e("onBillingSetupFinished", p0.responseCode.toString())
                    getProductSkus()
                }
            }
        })
    }

    private fun getProductSkus() {
        if (billingClient.isReady) {
            val params = SkuDetailsParams
                .newBuilder()
                .setSkusList(subIds)
                .setType(BillingClient.SkuType.SUBS)
                .build()
            billingClient.querySkuDetailsAsync(params) { responseCode, skuDetailsList ->
                if (responseCode.responseCode == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                    //SkuDetails: {"productId":"3monthelite","type":"subs","title":"Elite (com.swipefwd (unreviewed))",
                    // "name":"Elite","price":"₹5,517.54","price_amount_micros":5517539436,
                    // "price_currency_code":"INR","description":"3 month","subscriptionPeriod":"P3M",
                    // "skuDetailsToken":"AEuhp4J5M6apAZaXWQbILyA6wFm5P7ciTX6TR0eCMgUvodlgpK1SLKKkO6DCY_jHOfU="}
                    Log.d("Shop", "skuDetailsList:- $skuDetailsList")
                    if (!skuDetailsList.isNullOrEmpty()) {
                        skuDetailsList.onEach {
                            mAllSubList.add(
                                AllSubPlansModel(
                                    productId = it.sku,
                                    title = it.title,
                                    description = it.description,
                                    currency = it.priceCurrencyCode,
                                    priceValue = it.price,
                                    subscriptionPeriod = it.subscriptionPeriod
                                )
                            )
                        }
                        dataList = skuDetailsList.toList()
                            .asReversed() //reversed first as Premier then Elite
                        Log.d("Shop", "mAllSubList:- $mAllSubList")
                        Log.d("Shop", "dataList:- $dataList")
                    }
                }
            }
            //for boosters
            val boosterParams = SkuDetailsParams
                .newBuilder()
                .setSkusList(boostersIds)
                .setType(BillingClient.SkuType.INAPP)
                .build()
            billingClient.querySkuDetailsAsync(boosterParams) { responseCode, skuDetailsList ->
                if (responseCode.responseCode == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                    Log.d("Shop", "BoosterSkuDetailsList:- $skuDetailsList")
                    if (!skuDetailsList.isNullOrEmpty()) {
                        val tempList = skuDetailsList.toList()
                        boostersIds.forEach { name ->
                            tempList.find {
                                it.sku == name
                            }?.let {
                                boostersList.add(it)
                            }
                        }
                        boostersList.apply {
                            add(2, null)
                            add(4, null)
                            removeAt(4)
                            Log.d("Shop", "boostersList:- $this")
                            removeAt(4)
                        }
                        Log.d("Shop", "boostersList:- $boostersList")
                    }
                }
            }
        }
    }

    override fun onPurchasesUpdated(p0: BillingResult, p1: MutableList<Purchase>?) {
        Log.d("TAG", "onPurchasesUpdated, response code${p0.responseCode}")
        when (p0.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                if (p1 == null) {
                    Log.e("TAG", "Purchase update: No purchases")
                    handlePurchases(null)
                } else {
                    Log.e("purchasesList", p1.toString())
                    handlePurchases(p1)
                }
            }
            BillingClient.BillingResponseCode.USER_CANCELED -> Log.e(
                "TAG",
                "User canceled the purchase"
            )
            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                hasPurchaseOnGoogle()
                Log.e("TAG", "The user already owns this item")
            }
            BillingClient.BillingResponseCode.DEVELOPER_ERROR -> Log.e(
                "TAG", "Developer error means that Google Play does not recognize the " +
                        "configuration. If you are just getting started, make sure you have " +
                        "configured the application correctly in the Google Play Console. " +
                        "The SKU product ID must match and the APK you are using must be " +
                        "signed with release keys."
            )
            else -> Log.e(
                "TAG",
                "See error code in BillingClient.BillingResponse: ${p0.responseCode}"
            )
        }
    }

    private fun hasPurchaseOnGoogle() {
        val type = if (isBooster) {
            BillingClient.SkuType.INAPP
        } else {
            BillingClient.SkuType.SUBS
        }
        var list: ArrayList<Purchase>
        billingClient
            .queryPurchasesAsync(
                type
            ) { p0, p1 ->
                p0.responseCode
                if (p1.isNotEmpty()) {
                    list = p1 as ArrayList<Purchase>
                    if (list.isNullOrEmpty()) {
                        setProductSku()
                    } else {
                        getProductSkus()
                    }
                } else {
                    setProductSku()
                }
            }
    }

    private fun setProductSku() {
        val params = SkuDetailsParams.newBuilder()
        val sku: String
        val type: String
        if (isBooster) {
            sku = boosterModel?.sku ?: ""
            type = BillingClient.SkuType.INAPP
        } else {
            sku = planModel.planSkuProductId ?: ""
            type = BillingClient.SkuType.SUBS
        }
        params.setSkusList(arrayListOf(sku))
        params.setType(type)
        billingClient.querySkuDetailsAsync(
            params.build()
        ) { _, p1 ->
            val billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(p1!![0])
                .build()
            billingClient.launchBillingFlow(mActivity, billingFlowParams)
        }
    }

    private fun handlePurchases(purchasesList: List<Purchase>?) {
        if (purchasesList != null) {
            Log.e("subscriptionList", purchasesList.toString())
            val purchase = purchasesList[0]
            Log.e("purchase", purchase.toString())
            val receipt = purchase.originalJson
            Log.e("receipt", receipt)
            val jsonObject = JSONObject(receipt)
            val purchaseTime = jsonObject.getLong("purchaseTime")
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = purchaseTime
            val subDate = outFormat.format(calendar.time)
            exDate = getExpiryDate(
                outFormat.parse(subDate) ?: Date(),
                subscriptionDuration
            )
            orderId = (jsonObject.getString("orderId") ?: "")
            sendReceipt(receipt)
            /*if (isBooster) {
                boosterPurchase()
            } else {
                planPurchase()
            }*/
        }
    }

    private fun downloadFile(url: String, outputFile: File, isProfileUpload: Boolean) {
        try {
            val u = URL(url)
            val conn: URLConnection = u.openConnection()
            val contentLength: Int = conn.contentLength
            val stream = DataInputStream(u.openStream())
            val buffer = ByteArray(contentLength)
            stream.readFully(buffer)
            stream.close()
            val fos = DataOutputStream(FileOutputStream(outputFile))
            fos.write(buffer)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            return  // swallow a 404
        } catch (e: IOException) {
            return  // swallow a 404
        } catch (e: Exception) {
            return
        } finally {
            if (isProfileUpload) {
                mActivity.path = outputFile.absolutePath
                //sendImageVerification()
                uploadUserImage()
            } else {
                resetCamera()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    private fun openInvalidPictureDialog() {
        val dialog = Dialog(mActivity, R.style.SlideDialogThemePsst)
//        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val dialogBinding = DialogInvalidContentBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(dialogBinding.root)
            dialogBinding.apply {
                val span1 = SpannableString(txtMessage.text.toString())
                span1.setSpan(
                    StyleSpan(Typeface.BOLD),
                    29,
                    43,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                span1.setSpan(
                    object : ClickableSpan() {
                        override fun onClick(view: View) {
                            startActivity(
                                Intent(
                                    mActivity,
                                    WebViewActivity::class.java
                                ).putExtra("url", AppConstants.GUIDELINE)
                            )
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            super.updateDrawState(ds)
                            ds.apply {
                                ds.color =
                                    ContextCompat.getColor(mActivity, R.color.colorPagerDesc)
                                isUnderlineText = true
                            }
                        }
                    }, 29, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                txtMessage.apply {
                    setText(span1, TextView.BufferType.SPANNABLE)
                    highlightColor = Color.TRANSPARENT
                    movementMethod = LinkMovementMethod.getInstance()
                }
                txtChangeProfile.setOnClickListener {
                    dialogBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        setAnimationPsstDialogClose(
                            dialogBinding.cardInvalidContent,
                            dialog,
                            textLayout = dialogBinding.llInvalidContent
                        )
                        //   animationCenterGrowEnd(dialogBinding.cardInvalidContent,dialogBinding.llInvalidContent,dialog)
                    }
//                        dismiss()
                }
            }
            if (isMoreImages) {
                photosAdapter.changePhotoInvalid(addMoreIndex, true)
            }
            try {
                if (!dialog.isShowing) {
                    setBottomDialogWindowAttributes()
                    dialogBinding.imgDialogGradient.setDialogFadeInAnimation()

                    //------------animation starts here-------------------------

                    //----------------------------------------------------------
                    setAnimationDialogOpen(
                        dialogBinding.cardInvalidContent,
                        dialogBinding.orangeLogoInvalidContent,
                        dialogBinding.llInvalidContent
                    )
                    show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun setAnimationDialogOpen(cardView: View, logo: View, textLayout: LinearLayout) {
        var fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = 500
        fadeIn.startOffset = 200
        val animationOpen1 =
            AnimationUtils.loadAnimation(this, R.anim.center_grow_open)
        val animationOpen2 =
            AnimationUtils.loadAnimation(this, R.anim.center_grow_open_2)
        var bounceAnimation = AnimationBounceDown()
        cardView.startAnimation(animationOpen1)
        animationOpen1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                textLayout.startAnimation(fadeIn)
            }

            override fun onAnimationEnd(p0: Animation?) {
                cardView.startAnimation(animationOpen2)
                //textLayout.visibility = View.VISIBLE
                bounceAnimation.doBounceDownAnimation(logo, 1, 300)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
    }

    private fun setAnimationPsstDialogClose(
        view: View,
        dialog: Dialog,
        case: Int = 0,
        textLayout: LinearLayout
    ) {
        var fadeOut = AlphaAnimation(1.0f, 0.0f)
        fadeOut.duration = 400
        fadeOut.startOffset = 0
        val animationClose1 =
            AnimationUtils.loadAnimation(this, R.anim.center_grow_close)
        val animationClose2 =
            AnimationUtils.loadAnimation(this, R.anim.center_grow_close_2)
        val animationClose3 =
            AnimationUtils.loadAnimation(this, R.anim.center_grow_close_dismiss)
        view.startAnimation(animationClose1)
        animationClose1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                textLayout.startAnimation(fadeOut)
                fadeOut.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        textLayout.visibility = View.INVISIBLE
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                })
            }

            override fun onAnimationEnd(p0: Animation?) {
                view.startAnimation(animationClose2)
                //textLayout.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
        animationClose2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                view.startAnimation(animationClose3)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
        animationClose3.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                dialog.dismiss()
                if (case == 1) {
                    openVerifiedImageDialog()
                }
            }

            override fun onAnimationEnd(p0: Animation?) {
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
    }

    private fun openVerificationProblemDialog() {
//        userGender = btnSegmentGender.position.toString()
        /*if (userGender == "0") {
            AppUtils.storeGender(this@UserProfileActivity,1)
        } else if (userGender == "1") {
            AppUtils.storeGender(this@UserProfileActivity,2)
        } else {
            AppUtils.storeGender(this@UserProfileActivity,3)
        }*/
        val dialog = Dialog(mActivity, R.style.SlideDialogThemePsst)
        dialogs.add(dialog)
        val binding = DialogVerificationProblemBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(binding.root)
            binding.apply {

                txtChangeProfile.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        setAnimationPsstDialogClose(
                            cardVerificationProblem,
                            dialog,
                            1,
                            binding.llVerificationProblem
                        )
                        //    animationCenterGrowEnd(cardVerificationProblem,binding.llVerificationProblem,dialog)
                    }
                }
                txtDoItLater2.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        setAnimationPsstDialogClose(
                            cardVerificationProblem,
                            dialog,
                            textLayout = binding.llVerificationProblem
                        )
                        //  animationCenterGrowEnd(cardVerificationProblem,binding.llVerificationProblem,dialog)
                    }
                }
            }
            try {
                if (!dialog.isShowing) {
                    setBottomDialogWindowAttributes()
                    //binding.llVerificationProblem.visibility = View.INVISIBLE
                    binding.imgDialogGradient.setDialogFadeInAnimation()

                    setAnimationDialogOpen(
                        binding.cardVerificationProblem,
                        binding.logoOrangeVerificationProblem,
                        binding.llVerificationProblem
                    )
                    show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun animationCenterGrowEnd(
        cardVerificationProblem: MaterialCardView,
        llVerificationProblem: LinearLayout,
        dialog: Dialog
    ) {
        //-----------animation start on dialog closed--------------
        Handler(Looper.getMainLooper()).postDelayed({
            AppUtils.slideView(
                cardVerificationProblem,
                DisplayUtil.dpToPx(this@UserProfileActivity, 500F),
                0,
                400
            )
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
            }, 300)
        }, 150)
        Handler(Looper.getMainLooper()).postDelayed({
            llVerificationProblem.visibility = View.GONE
        }, 250)

        AppUtils.slideView(
            cardVerificationProblem,
            DisplayUtil.dpToPx(this@UserProfileActivity, 450F),
            DisplayUtil.dpToPx(this@UserProfileActivity, 500F),
            150
        )

        //---------------------------------------------------------
        //  dialog.dismiss()
    }

    fun bitmapToFile(
        context: Context,
        bitmap: Bitmap?,
        fileNameToSave: String
    ): File? { // File name like "image.png"
        //create a file to write bitmap data
        var file: File? = null
        return try {
            file =
                File(
                    Environment.getExternalStorageDirectory()
                        .toString() + File.separator.toString() + fileNameToSave
                )
            file!!.createNewFile()

            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap!!.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            return persistImage(bitmap, "newImage")
            /* val bitmapdata = bos.toByteArray()

             //write the bytes in file
             val fos = FileOutputStream(file)
             fos.write(bitmapdata)
             fos.flush()
             fos.close()
             return file*/
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }

    private fun persistImage(bitmap: Bitmap, name: String): File {
        val filesDir: File = filesDir
        val imageFile = File(filesDir, "$name.JPEG")
        val os: OutputStream
        try {
            os = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.flush()
            os.close()

        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "Error writing bitmap", e)
        }
        return imageFile
    }

    private fun isVerifiedBlock(isUserVerified: Boolean) {
        // if(isUserVerisVerifiedBlockified)
        lifecycleScope.launch {
            if (accountType == AppUtils.AccountTypes.Dater) {
                binding.layoutVerified.visibility = View.VISIBLE
            }
            if (isUserVerified) {
                println("isUserVerified->>>>>>>>>>>>> trueeeeeeee")
                verifiedUser()
            } else {
                println("isUserVerified->>>>>>>>>>>>> falseeeeeeeeeeeeee")

                binding.apply {
                    txtVerifiedTitle.visibility = View.VISIBLE
                    ivArrow.visibility = View.VISIBLE
                    txtVerified.setText(R.string.not_verified_message)
                    val layoutParams = (txtVerified.layoutParams as? ViewGroup.MarginLayoutParams)
                    layoutParams?.setMargins(0, 0, 0, 0)
                    txtVerified.layoutParams = layoutParams
                    ivNotVerified.background =
                        ContextCompat.getDrawable(this@UserProfileActivity, R.drawable.not_verified)
                }
            }
        }
    }

    private fun verifiedUser() {
        binding.apply {
            ivNotVerified.background =
                ContextCompat.getDrawable(this@UserProfileActivity, R.drawable.verify_profile)
            txtVerified.visibility = View.GONE
            ivArrow.visibility = View.GONE
            txtVerifiedTitle.visibility = View.VISIBLE
            txtVerifiedTitle.setText(R.string.you_are_verified)
            txtVerifiedTitle.textSize = 15f
            val layoutParams = (txtVerifiedTitle.layoutParams as? ViewGroup.MarginLayoutParams)
            layoutParams?.setMargins(0, 10, 0, 0)
            txtVerifiedTitle.layoutParams = layoutParams
        }
    }

    private fun verificationInProgress() {
        binding.apply {
            ivNotVerified.background = ContextCompat.getDrawable(
                this@UserProfileActivity,
                R.drawable.verification_in_progress
            )
            txtVerifiedTitle.visibility = View.GONE
            ivArrow.visibility = View.GONE
            txtVerified.setText(R.string.verification_progress_)
            val layoutParams = (txtVerified.layoutParams as? ViewGroup.MarginLayoutParams)
            layoutParams?.setMargins(0, 20, 0, 0)
            txtVerified.layoutParams = layoutParams
        }
    }

//    private fun getInviterGender() {
//        lifecycleScope.launch {
//            if (accountType == AppUtils.AccountTypes.Matchmaker && swipeViewModel.isOnboardingTutorialShown.firstOrNull() == false) {
//                profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
//                    profileViewModel.tribeConnectionListApi(
//                        "Bearer $authToken"
//                    )
//                }
//            }
//        }
//    }

    private fun setGradientToProgress(isValidPhoto: Boolean = true) {

        if (isValidPhoto) {

            binding.apply {

                progressSettings.setGradientColors(
                    GRADIENT_LINEAR,
                    intArrayOf(
                        ContextCompat.getColor(
                            mActivity,
                            R.color.blue_gradient_2
                        ),
                        ContextCompat.getColor(
                            mActivity,
                            R.color.blue_gradient_3
                        )
                    ),
                    null,
                    90f
                )

                txtProgress.background = ContextCompat.getDrawable(
                    mActivity,
                    R.drawable.profile_progress_bg
                )

                tvAddImage1.setImageDrawable(resources.getDrawable(R.drawable.add_photo_new_2))
//                tvAddImage1.setColorFilter(
//                    ContextCompat.getColor(
//                        mActivity,
//                        R.color.settings_name
//                    )
//                )

                choosePhoto1.apply {
                    text = getString(R.string.choose_photo)
                    setTextColor(
                        ContextCompat.getColor(
                            mActivity,
                            R.color.colorPagerDesc
                        )
                    )
                }
            }
        } else {

            binding.apply {

                progressSettings.setGradientColors(
                    GRADIENT_LINEAR,
                    intArrayOf(
                        ContextCompat.getColor(
                            mActivity,
                            R.color.orange_gradient_1
                        ),
                        ContextCompat.getColor(mActivity, R.color.color_orange)
                    ),
                    null,
                    90f
                )

                txtProgress.background = ContextCompat.getDrawable(
                    mActivity,
                    R.drawable.orange_gradient
                )

                tvAddImage1.setImageDrawable(resources.getDrawable(R.drawable.add_photo_orange))
//                tvAddImage1.setColorFilter(
//                    ContextCompat.getColor(
//                        mActivity,
//                        R.color.orange_gradient_1
//                    )
//                )
                choosePhoto1.apply {
                    text = getString(R.string.profile_guideline)
                    setTextColor(
                        ContextCompat.getColor(
                            mActivity,
                            R.color.orange_gradient_1
                        )
                    )
                }
            }
        }
    }

    override fun onBackPressed() {
//        if (!isTaskRoot) {
        //   profileViewModel.savePreference(PreferenceKeys.PREF_IS_DOB_DIALOG_OPEN, false)

        super.onBackPressed()
        overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right)
//        }
    }

    private fun applyBlur(image: View, layout: View) {
        image.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            @SuppressLint("NewApi")
            override fun onPreDraw(): Boolean {
                image.viewTreeObserver.removeOnPreDrawListener(this)
                image.buildDrawingCache()
                val bmp = image.drawingCache
                val overlay = Bitmap.createBitmap(
                    layout.measuredWidth,
                    layout.measuredHeight, Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(overlay)
                canvas.translate((-layout.left).toFloat(), (-layout.top).toFloat())
                canvas.drawBitmap(bmp, 0F, 0F, null)
                val rs = RenderScript.create(this@UserProfileActivity)
                val overlayAlloc = Allocation.createFromBitmap(
                    rs, overlay
                )
                val blur = ScriptIntrinsicBlur.create(
                    rs, overlayAlloc.element
                )
                blur.setInput(overlayAlloc)
                blur.setRadius(24f)
                blur.forEach(overlayAlloc)
                overlayAlloc.copyTo(overlay)
                layout.background = BitmapDrawable(
                    resources, overlay
                )
                rs.destroy()
                return true
            }
        })
    }

    private fun pxToDp(px: Int): Int {
        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    private fun blurHandling(isBlur: Boolean = false, radius: Float = 25F) {
        if (isBlur) {
            binding.relFadedView.visibility = View.VISIBLE

            //  val rootView = window.decorView.rootView
            val rootView = binding.layoutProfileMain
            //  val rootView = binding.relFadedView
            /*val processedBitmap = RSBlurProcessor(renderScript).blur(AppUtils.getScreenShot(rootView), radius, 1)
            val processedBitmap2 = RSBlurProcessor(renderScript).blur(processedBitmap, radius, 1)
            val processedBitmap3 = RSBlurProcessor(renderScript).blur(processedBitmap2, radius, 1)
            val processedBitmap4 = RSBlurProcessor(renderScript).blur(processedBitmap3, radius, 1)
            val processedBitmap5 = RSBlurProcessor(renderScript).blur(processedBitmap4, radius, 1)
            binding.imageFaded.setImageBitmap(processedBitmap5)*/

            val processedBitmap =
                RSBlurProcessor(renderScript).blur(AppUtils.getScreenShot(rootView), radius, 1)
            val processedBitmap2 = RSBlurProcessor(renderScript).blur(processedBitmap, radius, 1)
            val processedBitmap3 = RSBlurProcessor(renderScript).blur(processedBitmap2, radius, 1)
            val processedBitmap4 = RSBlurProcessor(renderScript).blur(processedBitmap3, radius, 1)
            val processedBitmap5 = RSBlurProcessor(renderScript).blur(processedBitmap4, radius, 1)
            binding.imageFadedBackground.setImageBitmap(processedBitmap5)
            binding.imageFaded.setImageBitmap(processedBitmap5)
            // saveBitmap(processedBitmap)
            /* val rootView = binding.imageFaded
             val processedBitmap = RSBlurProcessor(renderScript).blur(drawableToBitmap(resources.getDrawable(R.drawable.bg_blur2)), radius, 1)
             val processedBitmap2 = RSBlurProcessor(renderScript).blur(processedBitmap, radius, 1)
             val processedBitmap3 = RSBlurProcessor(renderScript).blur(processedBitmap2, radius, 1)
             val processedBitmap4 = RSBlurProcessor(renderScript).blur(processedBitmap3, radius, 1)
             val processedBitmap5 = RSBlurProcessor(renderScript).blur(processedBitmap4, radius, 1)
             binding.imageFaded.setImageBitmap(processedBitmap5)
             binding.imageFaded.alpha = 0.1F*/

            // binding.relFadedView.visibility = View.VISIBLE
        } else {
            binding.relFadedView.visibility = View.GONE
        }
    }

    private fun getCoordinates(layoutTop: LinearLayout, layoutBottom: LinearLayout) {
        val point = IntArray(2)
        binding.ivInfo.getLocationOnScreen(point)
        val data = point
        if (data.size > 1) {

            /*  // move info icon to selected position
              var params = binding.ivInfo2.layoutParams as ViewGroup.MarginLayoutParams
              params.setMargins(data[0], data[1]-DisplayUtil.dpToPx(mActivity,45F), params.rightMargin, params.bottomMargin)
              binding.ivInfo2.layoutParams = params

              // move arrow
              var paramsArrow = binding.toolTipArrowTop.layoutParams as ViewGroup.MarginLayoutParams
              paramsArrow.setMargins(data[0] - DisplayUtil.dpToPx(mActivity,5F) , DisplayUtil.dpToPx(mActivity,10F), paramsArrow.rightMargin, paramsArrow.bottomMargin)
              binding.toolTipArrowTop.layoutParams = paramsArrow

              // move view below info icon
              var paramsLoc = binding.linLocAnim.layoutParams as ViewGroup.MarginLayoutParams
              paramsLoc.setMargins(0, data[1]- DisplayUtil.dpToPx(mActivity,5F), paramsLoc.rightMargin, paramsLoc.bottomMargin)
              binding.linLocAnim.layoutParams = paramsLoc

              AppUtils.slideView(binding.linLocAnim,0,DisplayUtil.dpToPx(mActivity,280F),300)
              AppUtils.changeDrawableColor(mActivity, binding.toolTipArrowTop,resources.getColor( R.color.blue_gradient_3_btn))*/


            // manipulation
            var screenHeight = AppUtils.getScreenHeight(this@UserProfileActivity)
            var screenEvaluation = data[1] - binding.ivInfo.measuredHeight
            if (screenHeight / 2 < screenEvaluation) {
                //   binding.toolTipArrowBottom.visibility = View.VISIBLE
                binding.toolTipArrowTop.visibility = View.GONE

                // move info icon to selected position
                var params = binding.ivInfo2.layoutParams as ViewGroup.MarginLayoutParams
                //  params.setMargins(data[0], data[1]- statusBarHeight - DisplayUtil.dpToPx(mActivity,8F), params.rightMargin, params.bottomMargin)
                params.setMargins(
                    data[0],
                    data[1] - statusBarHeight,
                    params.rightMargin,
                    params.bottomMargin
                )
                binding.ivInfo2.layoutParams = params

                // move arrow
                var paramsArrow =
                    binding.toolTipArrowBottom.layoutParams as ViewGroup.MarginLayoutParams
                //   paramsArrow.setMargins(data[0] - DisplayUtil.dpToPx(mActivity,4F) ,data[1]- statusBarHeight - DisplayUtil.dpToPx(mActivity,30F), paramsArrow.rightMargin, paramsArrow.bottomMargin)
                paramsArrow.setMargins(
                    data[0] - DisplayUtil.dpToPx(mActivity, 4F),
                    data[1] - statusBarHeight - DisplayUtil.dpToPx(mActivity, 26F),
                    paramsArrow.rightMargin,
                    paramsArrow.bottomMargin
                )
                binding.toolTipArrowBottom.layoutParams = paramsArrow

                // move view below info icon
                var paramsLoc = binding.linLocAnim.layoutParams as ViewGroup.MarginLayoutParams
                paramsLoc.setMargins(
                    0,
                    data[1] - statusBarHeight - DisplayUtil.dpToPx(mActivity, 306F),
                    paramsLoc.rightMargin,
                    paramsLoc.bottomMargin
                )
                binding.linLocAnim.layoutParams = paramsLoc

                //  binding.linLocAnim.animate().y(data[1] - DisplayUtil.dpToPx(mActivity,80F).toFloat()).duration = 2000
                //   binding.toolTipArrowBottom.visibility = View.GONE
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.toolTipArrowBottom.visibility = View.VISIBLE
                }, 310)
                AppUtils.slideView(binding.linLocAnim, 0, DisplayUtil.dpToPx(mActivity, 280F), 300)
                AppUtils.changeDrawableColor(
                    mActivity,
                    binding.toolTipArrowTop,
                    resources.getColor(R.color.blue_gradient_3_btn)
                )
            } else {
                binding.toolTipArrowBottom.visibility = View.GONE
                binding.toolTipArrowTop.visibility = View.VISIBLE
                // move info icon to selected position
                var params = binding.ivInfo2.layoutParams as ViewGroup.MarginLayoutParams
                //  params.setMargins(data[0], data[1]- statusBarHeight - DisplayUtil.dpToPx(mActivity,8F) , params.rightMargin, params.bottomMargin)
                params.setMargins(
                    data[0],
                    data[1] - statusBarHeight,
                    params.rightMargin,
                    params.bottomMargin
                )
                binding.ivInfo2.layoutParams = params

                // move arrow
                var paramsArrow =
                    binding.toolTipArrowTop.layoutParams as ViewGroup.MarginLayoutParams
                //  paramsArrow.setMargins(data[0] - DisplayUtil.dpToPx(mActivity,4F) ,data[1]- statusBarHeight + DisplayUtil.dpToPx(mActivity,20F), paramsArrow.rightMargin, paramsArrow.bottomMargin)
                paramsArrow.setMargins(
                    data[0] - DisplayUtil.dpToPx(mActivity, 3F),
                    data[1] - statusBarHeight + DisplayUtil.dpToPx(mActivity, 26F),
                    paramsArrow.rightMargin,
                    paramsArrow.bottomMargin
                )
                binding.toolTipArrowTop.layoutParams = paramsArrow

                // move view below info icon
                var paramsLoc = binding.linLocAnim.layoutParams as ViewGroup.MarginLayoutParams
                //  paramsLoc.setMargins(0, data[1]- statusBarHeight + DisplayUtil.dpToPx(mActivity,33F), paramsLoc.rightMargin, paramsLoc.bottomMargin)
                paramsLoc.setMargins(
                    0,
                    data[1] - statusBarHeight + DisplayUtil.dpToPx(mActivity, 40F),
                    paramsLoc.rightMargin,
                    paramsLoc.bottomMargin
                )
                binding.linLocAnim.layoutParams = paramsLoc

                AppUtils.slideView(binding.linLocAnim, 0, DisplayUtil.dpToPx(mActivity, 280F), 300)
                AppUtils.changeDrawableColor(
                    mActivity,
                    binding.toolTipArrowTop,
                    resources.getColor(R.color.blue_gradient_3_btn)
                )
            }
        }
    }

    private fun moveView(layout: LinearLayout) {
        // move imageview dynamically
        var params = layout.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(
            params.leftMargin,
            params.topMargin,
            DisplayUtil.dpToPx(this, 30F),
            params.bottomMargin
        )
        layout.layoutParams = params
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        var bitmap: Bitmap? = null
        if (drawable is BitmapDrawable) {
            val bitmapDrawable = drawable as BitmapDrawable
            if (bitmapDrawable.bitmap != null) {
                return bitmapDrawable.bitmap
            }
        }
        bitmap = if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888
            )
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun setGenderFont(pos: Int) {
        binding.apply {
            /*btnMale.textTypeface = ResourcesCompat.getFont(mActivity , R.font.regular)
            btnFemale.textTypeface = ResourcesCompat.getFont(mActivity , R.font.regular)
            btnNone.textTypeface = ResourcesCompat.getFont(mActivity , R.font.regular)*/
            binding.btnMale.setTextSize(DisplayUtil.dpToPx(this@UserProfileActivity, 14F).toFloat())
            binding.btnFemale.setTextSize(
                DisplayUtil.dpToPx(this@UserProfileActivity, 14F).toFloat()
            )
            binding.btnNone.setTextSize(DisplayUtil.dpToPx(this@UserProfileActivity, 14F).toFloat())
        }
        if (pos == 0) {
            //  binding.btnMale.textTypeface = ResourcesCompat.getFont(mActivity , R.font.bold)
            binding.btnMale.setTextSize(DisplayUtil.dpToPx(this@UserProfileActivity, 15F).toFloat())
        } else if (pos == 1) {
            // binding.btnFemale.textTypeface = ResourcesCompat.getFont(mActivity , R.font.bold)
            binding.btnFemale.setTextSize(
                DisplayUtil.dpToPx(this@UserProfileActivity, 15F).toFloat()
            )
        } else if (pos == 2) {
            //  binding.btnNone.textTypeface = ResourcesCompat.getFont(mActivity , R.font.bold)
            binding.btnNone.setTextSize(DisplayUtil.dpToPx(this@UserProfileActivity, 15F).toFloat())
        }
    }

}