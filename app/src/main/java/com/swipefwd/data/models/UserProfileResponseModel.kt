package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class UserProfileResponseModel(
    @SerializedName("data") val data: Data? = Data(),
    @SerializedName("response") val response: String? = "",
    @SerializedName("status") val status: Boolean? = false,
    @SerializedName("detail") val errorDetail: String? = ""
) {
    data class Data(
        @SerializedName("astrological_sign") val astrologicalSign: Int? = 0,
        @SerializedName("astrological_signs") val astrologicalSigns: List<AstroSignListModel.AstroSignModel?>? = listOf(),
        @SerializedName("bio") val bio: String? = "",
        @SerializedName("caste") val caste: Int? = 0,
        @SerializedName("castes") val castes: List<CastListModel.CastModel?>? = listOf(),
        @SerializedName("children") val children: Int? = 0,
        @SerializedName("childrens") val childrens: List<ChildrenListModel.ChildrenModel?>? = listOf(),
        @SerializedName("dob") val dob: String? = "",
        @SerializedName("education") val education: Int? = 0,
        @SerializedName("educations") val educations: List<EducationLevelListModel.LevelModel?>? = listOf(),
        @SerializedName("first_name") val firstName: String? = "",
        @SerializedName("gender") val gender: String? = "",
        @SerializedName("graduation_year") val graduationYear: Int? = 0,
        @SerializedName("height") val height: Int? = 0,
        @SerializedName("height_in") val heightIn: String? = "",
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("instagram_id") val instagramId: String? = "",
        @SerializedName("instagram_name") val instagramName: String? = "",
        @SerializedName("instagram_posts") val instagramPosts: Boolean? = false,
        @SerializedName("institution") val institution: String? = "",
        @SerializedName("language") val language: List<LanguageListModel.LanguageModel?>? = listOf(),
        @SerializedName("language_ids") val languageIds: List<Int?>? = listOf(),
        @SerializedName("last_name") val lastName: String? = "",
        @SerializedName("latitude") val latitude: String? = "",
        @SerializedName("location") val location: String? = "",
        @SerializedName("longitude") val longitude: String? = "",
        @SerializedName("looking") val looking: Int? = 0,
        @SerializedName("lookings") val lookings: List<RelationshipListModel.RelationshipModel?>? = listOf(),
        @SerializedName("occupation_company") val occupationCompany: String? = "",
        @SerializedName("occupation_title") val occupationTitle: String? = "",
        @SerializedName("ref_user") val refUser: Int? = 0,
        @SerializedName("religion") val religion: Int? = 0,
        @SerializedName("religions") val religions: List<ReligionListModel.ReligionModel?>? = listOf(),
        @SerializedName("smoking") val smoking: Int? = 0,
        @SerializedName("smokings") val smokings: List<SmokingListModel.SmokingModel?>? = listOf(),
        @SerializedName("vaccine") val vaccine: Int? = 0,
        @SerializedName("vaccines") val vaccines: List<CovidVaccineListModel.CovidModel?>? = listOf(),
        @SerializedName("percentage") val percentage: Int? = 0,
        @SerializedName("city") val travelCity: String? = "",
        @SerializedName("is_verified") var is_verified: Boolean = false
    )
}