package com.swipefwd.utils

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.ParcelFileDescriptor
import java.io.File
import java.io.FileNotFoundException
import java.util.*

/*
 * The solution is taken from here: http://stackoverflow.com/questions/10042695/how-to-get-camera-result-as-a-uri-in-data-folder
 */

@SuppressLint("Registered")
class InternalStorageContentProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        return try {
            val mFile = File(context!!.filesDir, "temp_photo.jpg")
            if (!mFile.exists()) {
                mFile.createNewFile()
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun getType(uri: Uri): String? {
        val path: String = uri.toString()
        for (extension: String in MIME_TYPES.keys) {
            if (path.endsWith(extension)) {
                return MIME_TYPES[extension]
            }
        }
        return null
    }

    @Throws(FileNotFoundException::class)
    override fun openFile(uri: Uri, mode: String): ParcelFileDescriptor? {
        val f = File(context!!.filesDir, "temp_photo.jpg")
        if (f.exists()) {
            return ParcelFileDescriptor.open(f, ParcelFileDescriptor.MODE_READ_WRITE)
        }
        throw FileNotFoundException(uri.path)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    companion object {
        val CONTENT_URI: Uri = Uri.parse("content://com.example.lawyerondemand/")!!
        private val MIME_TYPES = HashMap<String, String>()

        init {
            MIME_TYPES[".jpg"] = "image/jpeg"
            MIME_TYPES[".jpeg"] = "image/jpeg"
        }
    }
}