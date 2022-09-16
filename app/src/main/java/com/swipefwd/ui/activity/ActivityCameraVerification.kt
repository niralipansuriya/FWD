package com.swipefwd.ui.activity

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.*
import android.hardware.Camera
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.swipefwd.BuildConfig
import com.swipefwd.R
import com.swipefwd.animations.AnimationBounceDown
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.*
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.createPlaceholderImage2
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.cameraUtility.CameraPreview
import com.swipefwd.utils.cameraUtility.ExceptionUtility
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
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*

class ActivityCameraVerification : AppCompatActivity() {
    private var mGestureVerificationImage: Bitmap? = null

    private lateinit var binding: ActivityCameraVerificationBinding
    private var maPreview: CameraPreview? = null
    private val TAG = "ActivityCameraVerification"
    private val mActivity by lazy {
        this@ActivityCameraVerification
    }
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private var mCamera: Camera? = null
    private var flashOn: Boolean = false

    //    private var camera: Camera? = null
//    private var surfaceHolder: SurfaceHolder? = null
//    private var mPicture: Camera.PictureCallback? = null
    private var VERIFICATION_IMAGE = "verification_picture"

    private lateinit var callbackShutter: Camera.ShutterCallback
    private lateinit var callbackRaw: Camera.PictureCallback
    private lateinit var callbackJpeg: Camera.PictureCallback
    private val dialogs: Vector<Dialog> = Vector<Dialog>()

    private var prevY = 0
    private var prevX = 0
    private var checkForFaceInPreview = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

//        surfaceHolder=findViewById<SurfaceView>(R.id.surfaceView).holder
//        surfaceHolder?.addCallback(this@ActivityCameraVerification)

        var gender = 3
        lifecycleScope.launch {
            //   gender = profileViewModel.getGender.firstOrNull().toString()
            gender = AppUtils.getGender(this@ActivityCameraVerification)
        }
        if (gender == 1) {
            binding.imageView.setImageResource(R.drawable.male_gesture_verification_small)

        } else {
            binding.imageView.setImageResource(R.drawable.female_gesture_verification_small)

        }


        startCameraPreview()
        setClickListeners()
        setCallbacks()


    }

    private fun setCallbacks() {
        callbackShutter = Camera.ShutterCallback {}
        callbackRaw = Camera.PictureCallback { _, _ -> }
        callbackJpeg = Camera.PictureCallback { p0, _ ->
            val bmp = BitmapFactory.decodeByteArray(p0, 0, p0.size)
            val matrix = Matrix()
            matrix.setScale(-1F, 1F)
            matrix.postRotate(90f)
            val rotatedBitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.width, bmp.height, matrix, true)
            try {
                val filename = "bitmap.png"
                val stream: FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                stream.close()

                val highAccuracyOpts = FaceDetectorOptions.Builder()
                    .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
                    .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
                    .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
                    .build()

                val image = InputImage.fromBitmap(rotatedBitmap, 0)
                val detector = FaceDetection.getClient(highAccuracyOpts)
                detector.process(image).addOnCompleteListener {
                    if (it.result.size > 0) {

                       /* val intent = Intent()
                        intent.putExtra("verification_picture", filename)
                        setResult(RESULT_OK, intent)
                        finish()*/  // commented by nirali

                        verificationProcess(filename)
                    } else {
                        showSnackBar(binding.root, getString(R.string.unable_to_detect_face))
                        startCameraPreview()
                    }
                }.addOnFailureListener {
                    it.printStackTrace()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun verificationProcess(filename :String)
    {
        var bmp: Bitmap? = null

        try {
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
                        openFinalGestureDialog(true,filename)
                    } else {
                        //  openVerificationProblemDialog() // need to show gesture dialog
                        mGestureVerificationImage = bmp
                        openFinalGestureDialog(false,filename)


                        println("ml kit issues-------->>>11111111111111")

                    }
                } else {
                    mGestureVerificationImage = bmp
                    //  Toast.makeText(this,"we are...",Toast.LENGTH_LONG).show()
                    // openVerificationProblemDialog() // need to show gesture dialog
                    openFinalGestureDialog(false,filename)

                    println("ml kit issues-------->>>2222222222")


                }
            }
                .addOnFailureListener {
                    mGestureVerificationImage = bmp
                    // openVerificationProblemDialog()
                    openFinalGestureDialog(false,filename)

                    println("ml kit issues-------->>>33333333")

                    print("onFailureOfDetector ${it.message}")
                    it.printStackTrace()
                }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun openFinalGestureDialog(isValid: Boolean,filename:String) {
        val dialogGesture = Dialog(mActivity, R.style.SlideDialogTheme)
        dialogs.add(dialogGesture)
        val finalGestureBinding = DialogFinalGestureBinding.inflate(layoutInflater)
        dialogGesture.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(finalGestureBinding.root)
            val height = AppUtils.getScreenHeight(this@ActivityCameraVerification)
            val heightDp = DisplayUtil.pxToDp(this@ActivityCameraVerification, height)
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

                var gender = 3
                lifecycleScope.launch {

                    gender = AppUtils.getGender(this@ActivityCameraVerification)
                }
                if (gender == 1) {
                    viewUserImage.setImageResource(R.drawable.male_small)
                }
                imageView.setImageBitmap(mGestureVerificationImage)
                txtRetake.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                    startCameraPreview()
                    val intent = Intent(
                        mActivity,
                        ActivityCameraVerification::class.java
                    )
                    //gestureVerificationStartForResult?.launch(intent)
                }
                txtSubmit.setOnClickListener {
                    //captureImageVerification()
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }

                    if (isValid) {

                         val intent = Intent()
                          intent.putExtra("verification_picture", filename)
                          setResult(RESULT_OK, intent)
                          finish()
                       // sendImageVerification()

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
                    //openVerifiedImageDialog() //commented by nirali

                    openCopyGestureDialog()
                }
                else
                {
                    finish()
                }
            }

            override fun onAnimationEnd(p0: Animation?) {
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
    }

    private fun openVerifiedImageDialog() {
        lifecycleScope.launch {
            //  if (!profileViewModel.getIsVerified.firstOrNull()!!) {
            if (AppUtils.getProfileVerified(this@ActivityCameraVerification) == 0)
            {

                openCopyGestureDialog()
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
            gender = AppUtils.getGender(this@ActivityCameraVerification)
        }
        if (gender == 1) {
//            gestureBinding.viewUserImage.setImageResource(R.drawable.male_gesture_verification)
//            gestureBinding.viewUserImage.setImageResource(R.drawable.male_gesture_verification)
            gestureBinding.viewUserImage.setImageResource(R.drawable.male_new_verification)
//            gestureBinding.viewUserImage.setBackgroundResource(R.drawable.picture_gradient_male)
        }
        val height = AppUtils.getScreenHeight(this@ActivityCameraVerification)
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
                    startCameraPreview()

                   // requestRuntimePermission(true) // commented by nirali
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

                tempfile = Compressor.compress(this@ActivityCameraVerification, imagePath) {
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

                    val mediaTypeImage: MediaType = "image/*".toMediaTypeOrNull()!!
                    val requestBody: RequestBody = tempfile!!.asRequestBody(mediaTypeImage)
                    val imageBody: MultipartBody.Part =
                        MultipartBody.Part.createFormData("photo", tempfile!!.name, requestBody)
                    val requestBodyUserId: RequestBody =
                        userId.toRequestBody("text/plain".toMediaTypeOrNull())
                    val requestBodyGender: RequestBody =
                        requestGender.toRequestBody("text/plain".toMediaTypeOrNull())

                    when {
                        !AppUtils.isNetworkAvailable(this@ActivityCameraVerification) -> {
                            openFailNetworkDialog() { sendImageVerification() }

                        }
                        else -> {

                            lifecycleScope.launch {

                                profileViewModel.imageGestureVerification(
                                    requestBodyGender,
                                    imageBody
                                )

                            }
                        }
                    }
                }
            }
        }
    }

/*
    private fun setCallbacks(){
        callbackShutter = Camera.ShutterCallback {
        }
        callbackRaw = Camera.PictureCallback { _ , _ ->
        }
        callbackJpeg = Camera.PictureCallback { p0 , _ ->
            val bmp = BitmapFactory.decodeByteArray(p0, 0, p0.size)
            val matrix = Matrix()

            // matrix.preScale(-1.0f, -1.0f)
            matrix.setScale(-1F, 1F)
            //   matrix.postTranslate(bmp.width.toFloat(), 0F)
            matrix.postRotate(90f)
            val rotatedBitmap = Bitmap.createBitmap(bmp , 0 , 0 , bmp.width , bmp.height , matrix , true)
            try {
                //Write file
                val filename = "bitmap.png"
                val stream: FileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE)
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

                //Cleanup
                stream.close()

                //Pop intent
                val intent = Intent()
                intent.putExtra("verification_picture", filename)
                setResult(RESULT_OK, intent)
                //        val in1 = Intent(this, Activity2::class.java)
                //       in1.putExtra("image", filename)
                //      startActivity(in1)
            } catch (e: Exception) {
                e.printStackTrace()
            }
//            binding.imageView.setImageBitmap(rotatedBitmap)
//            val intent = Intent()
//            intent.putExtra("verification_picture", bitmapToString(rotatedBitmap))
//            setResult(RESULT_OK, intent)
            finish()
        }
    }
*/
    /*  fun startCameraPreview() {
          try {
              mCamera = getCameraInstance()
              maPreview = CameraPreview(this, mCamera, null)
              binding.surfaceView.addView(maPreview)
          } catch (exc: java.lang.Exception) {
              ExceptionUtility.logError(TAG, "startCameraPreview", exc)
          }
      }*/

    private fun startCameraPreview() {
        try {
            mCamera = getCameraInstance()
            maPreview = CameraPreview(this, mCamera) { bytes, camera ->
                if (bytes != null && bytes.isNotEmpty() && checkForFaceInPreview) {
                    checkForFaceInPreview = false
                    val previewWidth = maPreview?.mPreviewSize?.width!!
                    val previewHeight = maPreview?.mPreviewSize?.height!!
//                    val yuvimage = YuvImage(bytes, ImageFormat.NV21, prevX, prevY, null)
                    val rect = Rect(0, 0, previewWidth, previewHeight)
                    val yuvimage =
                        YuvImage(bytes, ImageFormat.NV21, previewWidth, previewHeight, null)

                    val baos = ByteArrayOutputStream()
                    yuvimage.compressToJpeg(rect, 100, baos)

                    val bmp = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.size())
                    val highAccuracyOpts = FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
                        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
                        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
                        .build()

                    if (bmp != null) {
//                        binding.imagePreview.setImageBitmap(bmp)
                        val image = InputImage.fromBitmap(bmp, 0)
                        val detector = FaceDetection.getClient(highAccuracyOpts)
                        detector.process(image).addOnCompleteListener {
                            if (it.result.size == 0) {
                                showSnackBar(
                                    binding.root,
                                    getString(R.string.unable_to_detect_face)
                                )
                            }
                        }.addOnFailureListener {

                        }
                    }
                }
            }
            binding.surfaceView.addView(maPreview)
            Handler().postDelayed({
                checkForFaceInPreview = true
            }, 3000)
        } catch (exc: java.lang.Exception) {
            ExceptionUtility.logError(TAG, "startCameraPreview", exc)
        }
    }

    private fun getCameraInstance(): Camera? {
        if (mCamera == null) // mCamera = Camera.open(useBackCamera ? 0 : 1);
            mCamera = Camera.open(1)
        return mCamera
    }

    private fun setClickListeners() {
        binding.apply {
            btnCaptureImage.setOnClickListener {
                mCamera?.takePicture(callbackShutter, callbackRaw, callbackJpeg)
            }
            ivClose.setOnClickListener {
                onBackPressed()
            }
            btnFlash.setOnClickListener {
                flashOn = if (flashOn) {
                    maPreview?.turnFlashlightOff()
                    false
                } else {
                    maPreview?.turnFlashlightOn()
                    true
                }
            }
        }
    }


//    override fun onResume() {
//        super.onResume()
//        if (camera == null) {
//            camera = Camera.open()
//            mPicture = getPictureCallback()
//        } else {
//            Log.d("cameraIssue", "error")
//        }
//    }
//    private fun getPictureCallback(): Camera.PictureCallback? {
//        return Camera.PictureCallback { data, camera ->
//            bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
//            val intent = Intent(this@MainActivity, PictureActivity::class.java)
//            startActivity(intent)
//        }
//    }

//    fun bitmapToString(bitmap: Bitmap): String? {
//        val baos = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG , 100 , baos)
//        val b: ByteArray = baos.toByteArray()
//        return Base64.encodeToString(b , Base64.DEFAULT)
//    }
//
//    fun stringToBitMap(encodedString: String?): Bitmap? {
//        return try {
//            val encodeByte: ByteArray = Base64.decode(encodedString , Base64.DEFAULT)
//            BitmapFactory.decodeByteArray(encodeByte , 0 , encodeByte.size)
//        } catch (e: java.lang.Exception) {
//            e.message
//            null
//        }
//    }
}
