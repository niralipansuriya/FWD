package com.swipefwd.data.models.walletModels


import com.google.gson.annotations.SerializedName

data class MakeRechargeModel(
    @SerializedName("balanceInfo") val balanceInfo: BalanceInfo? = BalanceInfo(),
    @SerializedName("countryCode") val countryCode: String? = "",
    @SerializedName("customIdentifier") val customIdentifier: Any? = Any(),
    @SerializedName("deliveredAmount") val deliveredAmount: Int? = 0,
    @SerializedName("deliveredAmountCurrencyCode") val deliveredAmountCurrencyCode: String? = "",
    @SerializedName("discount") val discount: Double? = 0.0,
    @SerializedName("discountCurrencyCode") val discountCurrencyCode: String? = "",
    @SerializedName("operatorId") val operatorId: Int? = 0,
    @SerializedName("operatorName") val operatorName: String? = "",
    @SerializedName("operatorTransactionId") val operatorTransactionId: Any? = Any(),
    @SerializedName("pinDetail") val pinDetail: Any? = Any(),
    @SerializedName("recipientEmail") val recipientEmail: Any? = Any(),
    @SerializedName("recipientPhone") val recipientPhone: String? = "",
    @SerializedName("requestedAmount") val requestedAmount: Int? = 0,
    @SerializedName("requestedAmountCurrencyCode") val requestedAmountCurrencyCode: String? = "",
    @SerializedName("senderPhone") val senderPhone: Any? = Any(),
    @SerializedName("transactionDate") val transactionDate: String? = "",
    @SerializedName("transactionId") val transactionId: Int? = 0
) {
    data class BalanceInfo(
        @SerializedName("currencyCode") val currencyCode: String? = "",
        @SerializedName("currencyName") val currencyName: String? = "",
        @SerializedName("newBalance") val newBalance: Double? = 0.0,
        @SerializedName("oldBalance") val oldBalance: Double? = 0.0,
        @SerializedName("updatedAt") val updatedAt: String? = ""
    )
}