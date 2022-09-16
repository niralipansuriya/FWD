package com.swipefwd.ui.updateuserprofile

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.swipefwd.BuildConfig
import com.swipefwd.utils.FileUtil
import java.io.File
import java.io.IOException


/** this class is related to the select image from the camera and gallery
 * this class  also contains the permission for camera and gallery */
class ImageSelector(private val activity: AppCompatActivity) {
    companion object {
        private const val TAG = "image_selector"
        private val mimeTypes = arrayOf("image/jpg")
    }

    /** this callback is implemented where result is required of pickup and capture image */
    interface Callback {
        fun onError(type: ErrorType, errorText: String? = null)
        fun getCameraOutputFilePath(): File?
        fun onCameraImageCaptureSuccess()
        fun onGalleryImageSelected(path:String)
        fun onCancel()
    }


    /** this are the error type occurred in this image selection operation */
    enum class ErrorType {
        CAMERA_NOT_AVAILABLE, NULL_IMAGE_FILE, UNKNOWN
    }

    private var callback: Callback? = null


    //capture image activity
    private val captureCameraImageActivity =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            onCameraImageCaptureResult(it)
        }

    //select image from the gallery
    private val pickGalleryImageActivity = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { onPickGalleryImageResult(it) }


    //////////////////////////////////////////////
//set callback
    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    /** when click on the camera option for pick the profile picture
     * then this block will be called */
    fun startCameraImageCapture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        val isCameraAvailable = takePictureIntent.resolveActivity(activity.packageManager)
        /** check if camera on device available or not
         * if camera is not available than something went wrong message will be shown */
        if (isCameraAvailable == null) {
            Log.e(TAG, "camera not available")
            callback?.onError(ErrorType.CAMERA_NOT_AVAILABLE)
            return
        }
        /** create temporary file where photo can be store */
        val photoFile: File? = try {
           callback?.getCameraOutputFilePath()
            //    FileUtil(this).getImageFile()
        } catch (ex: IOException) {
            Log.e(TAG, ex.toString())
            //Log.i("imageIntent", ex.toString())
            null
        }
        if (photoFile == null) {
            Log.e(TAG, "null photo file")
            callback?.onError(ErrorType.NULL_IMAGE_FILE)
            return
        }

        /** get file uri form the files */
        val photoURI: Uri = FileProvider.getUriForFile(
            activity,
            BuildConfig.APPLICATION_ID,
            photoFile
        )
        /** put picture intent and output uri for capturing the picture */
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

        //launch the camera activity
        //TODO in try catch, and remove resolveActivityResult function
        captureCameraImageActivity.launch(takePictureIntent)
    }

    /** this will handle the capture camera activity's result here */
    private fun onCameraImageCaptureResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            Log.i(TAG, "result ok")
            callback?.onCameraImageCaptureSuccess()
        } else {
            Log.i(TAG, "result not ok")
            callback?.onCancel()
        }
    }


    /** #####################################################################
     *  when click on open gallery option then this block will be called */
    fun openGallery() {
        try {
            val intent =
                Intent(Intent.ACTION_PICK)
                    .setType("image/*")
                    .putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                    .putExtra(Intent.EXTRA_LOCAL_ONLY, true)

            //  val chooser = Intent.createChooser(intent, "Select Picture")
            pickGalleryImageActivity.launch(intent)
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, "gallery activity not found")
            Log.e(TAG, e.toString())
            callback?.onError(ErrorType.UNKNOWN)
        }
    }

    /** after picture select the uri given for cropping purpose */
    private fun onPickGalleryImageResult(result: ActivityResult) {
         if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            if (data == null) {
                Log.i(TAG, "gallery result data is null")
                callback?.onError(ErrorType.UNKNOWN)
                return
            }
            val uri = data.data
            if (uri == null) {
                Log.i(TAG, "gallery result uri is null")
                callback?.onError(ErrorType.UNKNOWN)
                return
            }
            val path = FileUtil.getRealPath(activity, uri)
            if (path == null) {
                Log.i(TAG, "gallery result uri path is null")
                callback?.onError(ErrorType.UNKNOWN)
                return
            }

            callback?.onGalleryImageSelected(path)

        } else {
            Log.i(TAG, "pick image cancel")
        }
    }
    ////////////////////////////////////////////////////////////////////////
}