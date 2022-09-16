package com.swipefwd.base

import android.content.Context


data class ResponseError(
    private val errorMessage: String? = null,
    private val errorMessageRes: Int? = null
) {
    companion object {
        fun put(message: String): ResponseError = ResponseError(message, null)
        fun put(messageRes: Int?): ResponseError = ResponseError(null, messageRes)
    }
    fun getResolvedString(context: Context): String? {
        return if (errorMessageRes == null) {
            errorMessage
        } else {
            context.getString(errorMessageRes)
        }
    }
}