package com.swipefwd.data.source.remote.api

import com.swipefwd.modules.NetworkModule
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

object ErrorUtils {
    inline fun <reified T> parseError(response: Response<*>): T? {
        val converter: Converter<ResponseBody, T> =
            NetworkModule.getClient().responseBodyConverter(T::class.java, arrayOfNulls<Annotation>(0))
        return try {
            with(converter) { convert(response.run { errorBody() }!!) }
        } catch (e: IOException) {
            return null
        }
    }
}