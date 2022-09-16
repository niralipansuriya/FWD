package com.swipefwd.data.models.walletModels


import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class GetOperatorByIsoModel : ArrayList<GetOperatorByIsoModel.GetOperatorByIsoModelItem>() {
    data class GetOperatorByIsoModelItem(
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("operatorId") val operatorId: Int? = 0,
        @SerializedName("name") val name: String? = "",
        @SerializedName("senderCurrencyCode") val senderCurrencyCode: String? = "",
        @SerializedName("senderCurrencySymbol") val senderCurrencySymbol: String? = "",
        @SerializedName("geographicalRechargePlans") val geographicalRechargePlans: List<GeographicalRechargePlan?>? = listOf(),
    ) {
        data class GeographicalRechargePlan(
            @SerializedName("fixedAmountsDescriptions") val fixedAmountsDescriptions: JsonObject? = JsonObject(),
            @SerializedName("localFixedAmountsDescriptions") val localFixedAmountsDescriptions: JsonObject? = JsonObject(),
            @SerializedName("locationCode") val locationCode: String? = "",
            @SerializedName("locationName") val locationName: String? = ""
        )
    }
}