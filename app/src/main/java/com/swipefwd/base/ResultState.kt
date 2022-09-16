package com.swipefwd.base

import android.content.Context

sealed class ResultState<out T : Any> {
    companion object {
        fun error(message: String): Error = Error(message, null)
        fun error(messageRes: Int?): Error = Error(null, messageRes)
    }

    data class Success<out T : Any>(val data: T, val message: String? = null) : ResultState<T>()

    data class Error(
        private val errorMessage: String? = null,
        private val errorMessageRes: Int? = null
    ) : ResultState<Nothing>() {
        //        fun getResolvedString(context: Context): String?{
//            return if (errorMessageRes == null) {
//                errorMessage
//            } else {
//                context.getString(errorMessageRes)
//            }
//        }
        fun rawErrorMessage() = errorMessage
        fun rawErrorMessageResource() = errorMessageRes

    }

    object Loading : ResultState<Nothing>()
}

fun getResolvedString(context: Context, errorMessage: String?, errorMessageRes: Int?): String? {
    return if (errorMessageRes == null) {
        errorMessage
    } else {
        context.getString(errorMessageRes)
    }
}

fun ResultState.Error.getResolvedString(context: Context): String? {
    return getResolvedString(context, rawErrorMessage(), rawErrorMessageResource())
}

