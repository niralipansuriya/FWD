package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class SettingSubscriptionModel(
    @SerializedName("Plan") val plan: ArrayList<Plan?>? = arrayListOf(),
    @SerializedName("response") val response: String? = "",
    @SerializedName("status") val status: Boolean? = false
) {
    data class Plan(
        @SerializedName("connection_number") val connectionNumber: Int? = 0,
        @SerializedName("connection_string") val connectionString: String? = "",
        @SerializedName("extendtime_number") val extendtimeNumber: Int? = 0,
        @SerializedName("extendtime_string") val extendtimeString: String? = "",
        @SerializedName("fire_number") val fireNumber: Int? = 0,
        @SerializedName("fire_string") val fireString: String? = "",
        @SerializedName("fwd_number") val fwdNumber: Int? = 0,
        @SerializedName("fwd_string") val fwdString: String? = "",
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("plan_type") val planType: String? = "",
        @SerializedName("profile_number") val profileNumber: Int? = 0,
        @SerializedName("profile_recall_number") val profileRecallNumber: Int? = 0,
        @SerializedName("profile_recall_string") val profileRecallString: String? = "",
        @SerializedName("profile_rematch_number") val profileRematchNumber: Int? = 0,
        @SerializedName("profile_rematch_string") val profileRematchString: String? = "",
        @SerializedName("profile_string") val profileString: String? = "",
        @SerializedName("profile_superlikes_number") val profileSuperlikesNumber: Int? = 0,
        @SerializedName("profile_superlikes_string") val profileSuperlikesString: String? = "",
        @SerializedName("travel_location_number") val travelLocationNumber: Int? = 0,
        @SerializedName("travel_location_string") val travelLocationString: String? = "",
        @SerializedName("display_connection") val display_connection: String? = "",
        @SerializedName("display_extendtime") val display_extendtime: String? = "",
        @SerializedName("display_fwd") val display_fwd: String? = "",
        @SerializedName("display_fire") val display_fire: String? = "",
        @SerializedName("display_travel_location") val display_travel_location: String? = "",
        @SerializedName("display_profile") val display_profile: String? = "",
        @SerializedName("display_profile_recall") val display_profile_recall: String? = "",
        @SerializedName("display_profile_rematch") val display_profile_rematch: String? = "",
        @SerializedName("display_profile_superlikes") val display_profile_superlikes: String? = "",
        //below are for Google Play store's data
        var planSkuProductId: String? = "0",
        var planSkuTitle: String? = "",
        var planSkuDesc: String? = "",
        var planSkuPrice: String? = "0",
        var planSkuCurrencyCode: String? = "0",
        var planSkuSubscriptionPeriod: String? = "0",
        var list: ArrayList<Pair<String, String>> = arrayListOf()
    )
}