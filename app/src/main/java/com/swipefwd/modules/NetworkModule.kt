package com.swipefwd.modules

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.swipefwd.BuildConfig
import com.swipefwd.data.source.remote.api.BASE_URL_NEW
import com.swipefwd.data.source.remote.api.IO_TIMEOUT
import com.swipefwd.data.source.remote.api.SocialMediaApiService
import com.swipefwd.data.source.remote.api.WalletReloadlyApiService
import com.swipefwd.data.source.remote.api.services.ApiService
import com.swipefwd.utils.AppConstants.RELOADLY_AUDIENCE_SANDBOX
import com.swipefwd.utils.AppController
import com.swipefwd.utils.AppUtils
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object NetworkModule {

    //  fun getClient(baseUrl: String = BASE_URL): Retrofit {
    fun getClient(baseUrl: String = BASE_URL_NEW): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .authenticator(TokenAuthenticator())

            /*.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true; }*/
            .addInterceptor(loggingInterceptor)
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BODY)
                    .request("Request")
                    .response("Response")
                    .addHeader("Accept", "application/json")
//                            .addHeader("X-localization",App.preferences.getLanguage())
                    .build()
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }


    class TokenAuthenticator : Authenticator {
        override fun authenticate(route: Route?, response: Response): Request? {
            // This is a synchronous call

            val requestParams = HashMap<String, String>()
            requestParams.put("refresh_token", BuildConfig.REFRESH_TOKEN)
            requestParams.put("auth_token", AppUtils.getAuthToken(AppController.context!!))
           // requestParams.put("Content-Type", "application/x-www-form-urlencoded")
           // requestParams.put("Proxy-Connection", "Keep-Alive")


            try {
                val authTokenResponse =
                    apiService.callWebservice("user/refreshToken", requestParams)
                //val authTokenResponse = apiService.callWebservice( requestParams)

                val tokenResponse = authTokenResponse.execute().body()

                tokenResponse?.let {
                    val authToken = tokenResponse.data.token.toString()
                    println("refreshedddddddd token api call---------->>>>>>$authToken")

                    AppUtils.storeAuthToken(AppController.context!!, tokenResponse.data.token)


                    return response.request.newBuilder()
                        .method(response.request.method, response.request.body)
                        .header("Content-Type", "application/x-www-form-urlencoded")

                        .header("auth_token", AppUtils.getAuthToken(AppController.context!!))
                        .header("refresh_token", BuildConfig.REFRESH_TOKEN)
                        .header("Proxy-Connection", "Keep-Alive")
                        .build()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }



            return null
        }

    }

    val apiService: ApiService = getClient().create(ApiService::class.java)

    fun getSocialMediaService(url: String): SocialMediaApiService {
        return getClient(url).create(SocialMediaApiService::class.java)
    }

    fun newApi(url: String): ApiService {
        return getClient(url).create(ApiService::class.java)
    }

    fun getSwipeProfileService(url: String): ApiService {
        return getClient(url).create(ApiService::class.java)
    }

    fun getWalletService(url: String = RELOADLY_AUDIENCE_SANDBOX): WalletReloadlyApiService {
        return getClient(url).create(WalletReloadlyApiService::class.java)
    }

}