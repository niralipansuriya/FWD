package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class SwipingProfileModel(
    @SerializedName("data") var data: ProfileModel = ProfileModel(),
    @SerializedName("response") var response: String? = "",
    @SerializedName("status") var status: Boolean? = false
) {
    data class ProfileModel(
        @SerializedName("bio") var bio: String? = "",
        @SerializedName("caste") var caste: String? = "",
        @SerializedName("children") var children: String? = "",
        @SerializedName("dob") var dob: String? = "",
        @SerializedName("education") var education: String? = "",
        @SerializedName("first_name") var firstName: String? = "",
        @SerializedName("gender") var gender: String? = "",
        @SerializedName("graduation_year") var graduationYear: String? = "",
        @SerializedName("height") var height: Int? = 0,
        @SerializedName("id") var id: Int? = 0,
        @SerializedName("images") var images: Images = Images(),
        @SerializedName("instagram_id") var instagramId: String? = "",
        @SerializedName("instagram_name") var instagramName: String? = "",
        @SerializedName("instagram_posts") var instagramPosts: Boolean? = false,
        @SerializedName("institution") var institution: String? = "",
        @SerializedName("languages") var languages: ArrayList<String?>? = ArrayList(),
        @SerializedName("mutual_friends") var mutualFriends: ArrayList<MutualFriend>? = ArrayList(),
        @SerializedName("last_name") var lastName: String? = "",
        @SerializedName("latitude") var latitude: String? = "",
        @SerializedName("location") var location: String? = "",
        @SerializedName("longitude") var longitude: String? = "",
        @SerializedName("occupation_company") var occupationCompany: String? = "",
        @SerializedName("occupation_title") var occupationTitle: String? = "",
        @SerializedName("phone_number") var phoneNumber: String? = "",
        @SerializedName("profile_picture_url") var profilePictureUrl: String? = "",
        @SerializedName("recovery_email") var recoveryEmail: Any? = Any(),
        @SerializedName("relationship_interest") var relationshipInterest: String? = "",
        @SerializedName("religion") var religion: String? = "",
        @SerializedName("smoking") var smoking: String? = "",
        @SerializedName("sunsign") var sunsign: String? = "",
        @SerializedName("sunsign_url") var sunsign_url: String? = "",
        @SerializedName("vaccination_status") var vaccinationStatus: String? = "",
        @SerializedName("height_in") var height_in: String? = ""
    ) {
        data class Images(
            @SerializedName("instagram") var instagram: ArrayList<String> = ArrayList(),
            @SerializedName("uploads") var uploads: ArrayList<String> = ArrayList()
        )

        data class MutualFriend(
            @SerializedName("image")
            var image: String? = "",
            @SerializedName("name")
            var name: String? = ""
        )
    }
}