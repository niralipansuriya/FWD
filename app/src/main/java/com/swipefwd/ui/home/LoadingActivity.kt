package com.swipefwd.ui.home

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.Window
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.swipefwd.R
import com.swipefwd.animations.SlideView
import com.swipefwd.data.models.DaterConnectionResponseModel
import com.swipefwd.data.models.SwipingCustomModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityLoadingBinding
import com.swipefwd.databinding.DialogCopyGestureBinding
import com.swipefwd.databinding.DialogFinalGestureBinding
import com.swipefwd.databinding.DialogVerifiedProfileBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.activity.ActivityCameraVerification
import com.swipefwd.ui.profile.EmailActivity
import com.swipefwd.ui.swiping.dater_both.ChangePreferencesActivity
import com.swipefwd.ui.swiping.dater_both.SwipeProfileActivity
import com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.changeStatusBarColor
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.datastore.PreferenceKeys
import com.yuyakaido.android.cardstackview.internal.DisplayUtil
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class LoadingActivity : AppCompatActivity() , SurfaceHolder.Callback {
    private lateinit var binding: ActivityLoadingBinding
    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(mActivity, AppRepository()) }
    }

    private var gender :String?= ""
    private var recoveryEmail = ""
    private val mDateFormat = SimpleDateFormat("MM/dd/yyyy")
    private var showProfileVerificationDialogDate = Date()
    private var showEmailRecoveryDialogDate = Date()
    //private var profileCreateDate: Date?=Date()
    private var isProfileVerified=false
    private var camera: Camera? = null
    private var surfaceHolder: SurfaceHolder? = null

    private var profileList = ArrayList<SwipingCustomModel>()
    private var mAnimation = SlideView()
    private var userId = 0
    private val mActivity by lazy {
        this@LoadingActivity
    }
    private var recommendingUser = DaterConnectionResponseModel.User()
    private var accountType = ""

    private var mGestureVerificationImage: Bitmap? = null
    private var gestureVerificationStartForResult: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme(R.style.AppTheme_FullScreen)
        changeStatusBarColor()
        /*swipeViewModel.savePreference(
            PreferenceKeys.PREF_CURRENT_SCREEN,
            "0"
        )*/

//        changeStatusBar()
        gestureVerificationStartForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
                if(result.resultCode == Activity.RESULT_OK){
                    var bmp: Bitmap? = null
                    val filename = result.data?.extras?.getString("verification_picture")
                    try {
                        val `is` = openFileInput(filename)
                        bmp = BitmapFactory.decodeStream(`is`)
                        `is`.close()
                        mGestureVerificationImage=bmp
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                    openFinalGestureDialog()
//                    mGestureVerificationByteArray = result.data?.getByteArrayExtra("verification_picture")
//                    mGestureVerificationImage = result.data?.getParcelableExtra("verification_picture")
//                    mGestureVerificationString = result.data?.getStringExtra("verification_picture")
//                    mGestureVerificationImage = ActivityCameraVerification().stringToBitMap(mGestureVerificationString)
                }
            }
        init()
    }

    private fun init() {
        binding.apply {
            val userDetail = intent.getStringExtra("selectedUser")
            lifecycleScope.launch {
                accountType = swipeViewModel.getAccountType.firstOrNull() ?: ""
                userId = swipeViewModel.getUserId.firstOrNull() ?: 0

                isProfileVerified = swipeViewModel.getIsProfileVerified.firstOrNull() ?: false
                try {
                    val x = swipeViewModel.getVerifyDialogDisplayDate.firstOrNull()?: Date().toString()
                    showProfileVerificationDialogDate = mDateFormat.parse(x)
                    val y = swipeViewModel.getEmailDialogDisplayDate.firstOrNull()?: Date().toString()
                    showEmailRecoveryDialogDate = mDateFormat.parse(y)

                }catch (e: Exception){
                }
                recoveryEmail = swipeViewModel.getRecoveryEmail.firstOrNull() ?: ""
                gender = swipeViewModel.getGender.firstOrNull()
            }
            progress.animate()

            if (!userDetail.isNullOrEmpty() && accountType==AppUtils.AccountTypes.Matchmaker) {
                recommendingUser = Gson().fromJson<Any>(
                    userDetail ,
                    DaterConnectionResponseModel.User::class.java
                ) as DaterConnectionResponseModel.User
                getSwipingProfiles()
            } else if(accountType==AppUtils.AccountTypes.Dater){
                getSwipingProfiles()
            }
            else {
                lifecycleScope.launch{
                    swipeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        swipeViewModel.tribeConnectionListApi(
                            "Bearer $authToken"
                        )
                    }
                }
            }

            initObservers()
        }
    }

    private fun getSwipingProfiles() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getSwipingProfiles()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getSwipingProfiles()
//                }
            }
            else -> {

                lifecycleScope.launch {
                    val token = swipeViewModel.getAuthToken.firstOrNull()!!
                    when (accountType) {
                        AppUtils.AccountTypes.Dater -> {
                            swipeViewModel.getSwipingProfileIds(userId, false, "Bearer $token")
                        }
                        AppUtils.AccountTypes.Matchmaker -> {
                            if(recommendingUser.userid?.toInt()!=0) {
                                swipeViewModel.getSwipingProfileIds(
                                    recommendingUser.userid?.toInt() ,
                                    true , "Bearer $token"
                                )
                            } else {
                                nextActivity(TabManagerActivity::class.java)
                                finish()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initObservers() {
        swipeViewModel.apply {
            errorMessage.observe(mActivity) {
                Log.e("TAG" , "ERROR === $it")
                mActivity.showSnackBar(binding.layoutMain , it)
            }
            swipeProfileData.observe(mActivity) { profileModelList ->
                Log.e("TAG" , "Profiles === ${profileModelList.size}")

         //       Handler(Looper.getMainLooper()).post {
                    binding.progress.clearAnimation()
                    if (profileModelList.isEmpty()) {
                        val intent =
                            Intent(this@LoadingActivity, ChangePreferencesActivity::class.java)
                        when (accountType) {
                            AppUtils.AccountTypes.Dater -> {
                            }
                            AppUtils.AccountTypes.Matchmaker -> {
                                intent.putExtra("selectedUser", Gson().toJson(recommendingUser))
                            }
                        }

                        startActivity(intent)
                        finish()
//                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                        // nextActivity(ChangePreferencesActivity::class.java)
                    }
                    else {
                        profileModelList.forEach { profileModel ->
                            if (profileModel.userProfileModel?.id != 0)
                                profileList.add(profileModel)
                        }
                        profileList.forEachIndexed { _, swipingCustomModel ->
                            val list = ArrayList<String>()
                            list.add(swipingCustomModel.userProfileModel?.profilePictureUrl ?: "")
                            list.addAll(swipingCustomModel.userProfileModel?.images!!.uploads)
                            swipingCustomModel.userProfileModel?.images?.uploads = list
                        }
                        when (accountType) {
                            AppUtils.AccountTypes.Dater -> {

                                    moveToSwipe()

                            }
                            AppUtils.AccountTypes.Matchmaker -> {
                                if (recoveryEmail == "") {
                                    if (showEmailRecoveryDialogDate <= Date()) {
                                        //     Handler(Looper.getMainLooper()).post {
                                        var dt = Date()
                                        var c = Calendar.getInstance()
                                        c.time = dt
                                        c.add(Calendar.DATE, 7)
                                        dt = c.time
                                        val plusSeven = dt
                                        val stringPlusSeven = mDateFormat.format(plusSeven)
                                        swipeViewModel.savePreference(
                                            PreferenceKeys.SHOW_EMAIL_REMINDER_DATE,
                                            stringPlusSeven
                                        )
                                        startActivity(
                                            Intent(
                                                mActivity,
                                                EmailActivity::class.java
                                            ).putExtra("LoadingActivity", true)
                                                .putExtra("swipeDeck", Gson().toJson(profileList))
                                        )
                                        finish()
//                                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                                        //   }
                                    }
                                    else {
                                        moveToSwipe()
                                    }
                                }
                                else {
                                    moveToSwipe()
                                }
//                                moveToSwipe()
                            }
                        }
                    }
                   // finish()

            //    }
            }
            swipeProfileMatchesData.observe(mActivity) { matchModel ->

                when (accountType) {
                    AppUtils.AccountTypes.Dater -> {
                        if (matchModel.isEmpty()) {
                            nextActivity(ChangePreferencesActivity::class.java)
                            finish()
                        }
                    }
                    AppUtils.AccountTypes.Matchmaker -> {
                        if (matchModel.isEmpty()) {
                            startActivity(
                                Intent(
                                    mActivity ,
                                    ChangePreferencesActivity::class.java
                                ).putExtra("selectedUser" , Gson().toJson(recommendingUser))
                            )
                            finish()
//                            overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                            overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                        }
                    }
                }
            }

            tribeData.observe(this@LoadingActivity) {
                if(it?.completed != null && it.completed.isNotEmpty()) {
                    recommendingUser = it.completed[0]
                }
                getSwipingProfiles()
            }

            tribeError.observe(this@LoadingActivity){
                Log.e("TAG" , "ERROR=== $it")
            }
        }
    }

    private fun moveToSwipe() {
        var newList = ArrayList<SwipingCustomModel>()
        if (profileList.size>0)
            profileList.forEachIndexed { index, swipingCustomModel ->
                if (index <= 200)
                    newList.add(profileList[index])
            }


        val data = Gson().toJson(newList)
        val intent = Intent(
            this,
            SwipeProfileActivity::class.java
        )
        intent.putExtra("swipeDeck", data)
        startActivity(intent)
        finish()
//        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
    }

    private fun openVerifiedDialog() {
        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        val profileBinding = DialogVerifiedProfileBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(profileBinding.root)
            profileBinding.apply {
                lifecycleScope.launch {
                    val image = swipeViewModel.getProfileImage.firstOrNull()
                    binding.apply {
                        val firstName = swipeViewModel.getFirstName.firstOrNull()
                        val fName = firstName
                        if (fName != null) {
                            if (fName.isNotEmpty()) {
                                val textDrawable = mActivity.createPlaceholderImage(
                                    fName ,
                                    200 ,
                                    R.color.blue_gradient_2 ,
                                    R.color.blue_gradient_3 ,
                                    isModify = true
                                )

                                Glide.with(context)
                                    .load(image)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .placeholder(textDrawable)
                                    .into(ivProfile)
                            } else {
                                Glide.with(context)
                                    .load(R.mipmap.ic_launcher)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(ivProfile)
                            }
                        }
                    }
                }
                txtGetVerified.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        finish()
                        openCopyGestureDialog()
                    }
                }
                txtDoItLater.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                    moveToSwipe()
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                profileBinding.imgDialogGradient.setDialogFadeInAnimation()
                mAnimation.slide(profileBinding.clVerifyProfile, 100, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    private fun openCopyGestureDialog() {
        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        val gestureBinding = DialogCopyGestureBinding.inflate(layoutInflater)
        if(gender.equals("0")){
//            gestureBinding.viewUserImage.setImageResource(R.drawable.gesture_1_male)
            gestureBinding.viewUserImage.setImageResource(R.drawable.male_new_verification)
//            gestureBinding.viewUserImage.setBackgroundResource(R.drawable.picture_gradient_male)
        }
        val height=AppUtils.getScreenHeight(this@LoadingActivity)
        val heightDp = DisplayUtil.pxToDp(this, height)

//        if(heightDp<700) {
//            gestureBinding.viewUserImage.layoutParams.height = 400.dpToPx()
////            gestureBinding.viewUserImage.scaleType = ImageView.ScaleType.FIT_XY
//        }
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
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dialog.dismiss()
//                        }
//                        true
//                    }
//                    false
//                }
//                txtSkip.setOnClickListener {
//                    imgDialogGradient.setDialogFadeOutAnimation {
//                        dismiss()
//                    }
//                }
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
    private fun openVerificationCameraActivity(){
        val intent = Intent(
            mActivity,
            ActivityCameraVerification::class.java
        )
        gestureVerificationStartForResult?.launch(intent)
    }
    private fun requestRuntimePermission(isGesture: Boolean = false) {
        Dexter.withContext(mActivity).withPermissions(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                arrayListOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            } else {
                arrayListOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
            }
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                when {
                    report.areAllPermissionsGranted() -> {
                      //  openFinalGestureDialog()
                        openVerificationCameraActivity()
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
                        }
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<com.karumi.dexter.listener.PermissionRequest>? ,
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
    private fun openFinalGestureDialog() {
        val dialogGesture = Dialog(mActivity, R.style.SlideDialogTheme)
        val finalGestureBinding = DialogFinalGestureBinding.inflate(layoutInflater)
        if(gender.equals("0")){
            finalGestureBinding.viewUserImage.setImageResource(R.drawable.male_small)
        }
        val height=AppUtils.getScreenHeight(this@LoadingActivity)
        val heightDp = DisplayUtil.pxToDp(this@LoadingActivity, height)

//        if(heightDp<700) {
//            finalGestureBinding.viewUserImage.layoutParams.height = 200.dpToPx()
//            finalGestureBinding.viewUserImage.scaleType = ImageView.ScaleType.FIT_XY
//            finalGestureBinding.imageView.layoutParams.height = 200.dpToPx()
//        }
        dialogGesture.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(finalGestureBinding.root)
//            surfaceHolder = finalGestureBinding.surfaceView.holder
//            surfaceHolder?.addCallback(this@LoadingActivity)
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

    private fun releaseCamera() {
        camera!!.stopPreview()
        camera!!.release()
        camera = null
    }

}