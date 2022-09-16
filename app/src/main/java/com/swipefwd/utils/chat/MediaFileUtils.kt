package com.swipefwd.utils.chat

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.swipefwd.utils.AppController
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.math.roundToInt

/**
 * Created by gongguopei87@gmail.com on 2015/8/5.
 */
object MediaFileUtils {
    /* Checks if external storage is available for read and write */
    val isExternalStorageWritable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state
        }

    /* Checks if external storage is available to at least read */
    val isExternalStorageReadable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
        }

    fun getAlbumStorageDir(context: Context, type: String?, albumName: String?): File {
        // Get the directory for the app's private pictures directory.
        val file = File(
            context.getExternalFilesDir(
                type
            ), albumName!!
        )
        if (!file.exists()) {
            file.mkdir()
            Log.e("MediaFileUtils", "Directory created")
        }
        return file
    }

    fun getVoiceFilePath(context: Context, albumName: String?): String? {
        val c = Calendar.getInstance()
        val fileDir = getAlbumStorageDir(context, Environment.DIRECTORY_MUSIC, albumName)
        val fileName = StringBuilder().append(AppController.VOICE_FILE_NAME_PREFIX)
            .append(c[Calendar.YEAR])
            .append(c[Calendar.MONTH])
            .append(c[Calendar.DAY_OF_MONTH])
            .append(c[Calendar.HOUR_OF_DAY])
            .append(c[Calendar.MINUTE])
            .append(c[Calendar.SECOND])
            .append(c[Calendar.MILLISECOND])
            .append(AppController.VOICE_FILE_NAME_SUFFIX)
            .toString()
        val file = File(fileDir, fileName)
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
        }
        return file.toString()
    }

    fun getRealPathFromURI(context: Context, contentUri: Uri?): String {
        var cursor: Cursor? = null
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri!!, proj, null, null, null)
            val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(columnIndex)
        } finally {
            cursor?.close()
        }
    }

    fun decodeBitmapFromPath(filePath: String?, reqWidth: Int, reqHeight: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, options)
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(filePath, options)
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int
    ): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize > reqHeight
                && halfWidth / inSampleSize > reqWidth
            ) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    fun dpToPx(context: Context, dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }
}