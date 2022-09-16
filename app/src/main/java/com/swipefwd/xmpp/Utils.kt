package com.swipefwd.xmpp

import android.graphics.Bitmap
import android.graphics.Matrix
import java.io.ByteArrayOutputStream

/**
 * Utility methods.
 */
object Utils {
    @JvmStatic
    fun getScaledBitmap(bitmap: Bitmap, maxDp: Int): Bitmap {
        // Get current dimensions
        val width = bitmap.width
        val height = bitmap.height

        //Determine which dimension requires less scaling
        //and perform scaling based on this result
        val xScale = maxDp.toFloat() / width
        val yScale = maxDp.toFloat() / height
        val scale = if (xScale <= yScale) xScale else yScale

        // Create a matrix for the scaling and add the scaling data
        val matrix = Matrix()
        matrix.postScale(scale, scale)
        return Bitmap.createBitmap(
            bitmap, 0, 0,
            width, height,
            matrix, true
        )
    }

    @JvmStatic
    fun getBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }

    @JvmStatic
    fun getChatUsers(myUser: String, oppositeUser: String): String {
        val myUser1 = myUser.split("@")
        val remoteUser = oppositeUser.split("@")
        val chatUsers = myUser1[0] + "_" + remoteUser[0]
        return chatUsers
    }
}