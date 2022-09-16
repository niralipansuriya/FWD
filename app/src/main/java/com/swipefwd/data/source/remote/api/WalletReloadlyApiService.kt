package com.swipefwd.data.source.remote.api

import com.google.gson.JsonObject
import com.swipefwd.data.models.walletModels.GetAccessTokenModel
import com.swipefwd.data.models.walletModels.GetOperatorByIsoModel
import com.swipefwd.data.models.walletModels.MakeRechargeModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface WalletReloadlyApiService {

    @POST(GET_AUTH_TOKEN)
    fun getAccessTokenAsync(
        @Body jsonObject: JsonObject
    ): Deferred<Response<GetAccessTokenModel>>

    @GET(GET_OPERATOR_BY_ISO)
    fun getOperatorByIsoAsync(
        @Header("Authorization") Authorization: String,
        @Path("iso") isoCode: String,
        @Query("includeBundles") includeBundles: Boolean,
        @Query("includeData") includeData: Boolean,
        @Query("includePin") includePin: Boolean,
        @Query("suggestedAmounts") suggestedAmounts: Boolean,
        @Query("suggestedAmountsMap") suggestedAmountsMap: Boolean,
    ): Deferred<Response<GetOperatorByIsoModel>>

    @POST(MAKE_RECHARGE)
    fun makeRechargeAsync(
        @Header("Authorization") Authorization: String,
        @Body jsonObject: JsonObject
    ): Deferred<Response<MakeRechargeModel>>
}