package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class UserPreferenceResponseModel(
    @SerializedName("data") val data: Data? = Data(),
    @SerializedName("response") val response: String? = "",
    @SerializedName("status") val status: Boolean? = false,
    @SerializedName("message") val message: String? = "",
    @SerializedName("code") val code: Int? = 0
) {
    data class Data(
        @SerializedName("user_id") val userId: Int? = 0,
        @SerializedName("min_age") val minAge: Int? = 0,
        @SerializedName("max_age") val maxAge: Int? = 0,
        @SerializedName("distance") val distance: Int? = 0,
        @SerializedName("gender") val gender: String? = "",
        @SerializedName("age_end") val ageEnd: Int? = 0,
        @SerializedName("age_start") val ageStart: Int? = 0,
        @SerializedName("astrological_sign") val astrologicalSign: List<AstroSignListModel.AstroSignModel?>? = listOf(),
        @SerializedName("astrological_sign_ids") val astrologicalSignIds: List<Int?>? = listOf(),
        @SerializedName("cast") val cast: List<CastListModel.CastModel?>? = listOf(),
        @SerializedName("caste_ids") val casteIds: List<Int?>? = listOf(),
        @SerializedName("children") val children: List<ChildrenListModel.ChildrenModel?>? = listOf(),
        @SerializedName("children_ids") val childrenIds: List<Int?>? = listOf(),
        @SerializedName("education") val education: List<EducationLevelListModel.LevelModel?>? = listOf(),
        @SerializedName("education_ids") val educationIds: List<Int?>? = listOf(),
        @SerializedName("end_height") val endHeight: Int? = 0,
        @SerializedName("graduation_year") val graduationYear: Int? = 0,
        @SerializedName("height_in") val heightIn: String? = "",
        @SerializedName("id") val id: Int? = 0,
        @SerializedName("institution") val institution: String? = "",
        @SerializedName("is_profile_verified") val isProfileVerified: Boolean? = false,
        @SerializedName("language") val language: List<LanguageListModel.LanguageModel?>? = listOf(),
        @SerializedName("language_ids") val languageIds: List<Int?>? = listOf(),
        @SerializedName("max_distance") val maxDistance: Int? = 0,
        @SerializedName("min_distance") val minDistance: Int? = 0,
        @SerializedName("ref_user") val refUser: Int? = 0,
        @SerializedName("relationship") val relationship: List<RelationshipListModel.RelationshipModel?>? = listOf(),
        @SerializedName("relationship_ids") val relationshipIds: List<Int?>? = listOf(),
        @SerializedName("religion") val religion: List<ReligionListModel.ReligionModel?>? = listOf(),
        @SerializedName("religion_ids") val religionIds: List<Int?>? = listOf(),
        @SerializedName("smoking") val smoking: List<SmokingListModel.SmokingModel?>? = listOf(),
        @SerializedName("smoking_ids") val smokingIds: List<Int?>? = listOf(),
        @SerializedName("start_height") val startHeight: Int? = 0
    )
}