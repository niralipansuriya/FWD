package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

public class SwipingProfileMatchesModel :
    ArrayList<SwipingProfileMatchesModel.SwipeLeftRightResponseModelItem>() {
    data class SwipeLeftRightResponseModelItem(
       /* @SerializedName("astrological_sign")
        var astrologicalSign: Int? = 0,
        @SerializedName("astrological_signs")
        var astrologicalSigns :String?="",*/
        @SerializedName("bio")
        var bio: String? = "",
        @SerializedName("caste")
        var caste: Int? = 0,
        @SerializedName("castes")
        var castes: List<Caste>? = ArrayList(),
        @SerializedName("children")
        var children: Int? = 0,
        @SerializedName("childrens")
        var childrens: List<Children>? = ArrayList(),
        @SerializedName("dob")
        var dob: String? = "",
        @SerializedName("education")
        var education: Int? = 0,
        @SerializedName("educations")
        var educations: List<Education>? = ArrayList(),
        @SerializedName("first_name")
        var firstName: String? = "",
        @SerializedName("gender")
        var gender: String? = "",
        @SerializedName("sunsign_name")
        var sunsign_name: String? = "",
        @SerializedName("sunsign_url")
        var sunsign_url: String? = "",
        @SerializedName("graduation_year")
        var graduationYear: String? = "",
        @SerializedName("height")
        var height: Int? = 0,
        @SerializedName("height_in")
        var heightIn: String? = "",
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("instagram_id")
        var instagramId: String? = "",
        @SerializedName("instagram_name")
        var instagramName: String? = "",
        @SerializedName("instagram_posts")
        var instagramPosts: Boolean? = false,
        @SerializedName("institution")
        var institution: String? = "",
        @SerializedName("instagram")
        var instagram: ArrayList<String?>? = ArrayList(),
        @SerializedName("images")
        var images: ArrayList<String?>? = ArrayList(),
        @SerializedName("is_agree")
        var isAgree: Boolean? = false,
        @SerializedName("is_verified")
        var isVerified: Boolean? = false,
        @SerializedName("language")
        var language: List<Language>? = ArrayList(),
        @SerializedName("language_ids")
        var languageIds: List<Int>? = ArrayList(),
        @SerializedName("last_name")
        var lastName: String? = "",
        @SerializedName("latitude")
        var latitude: String? = "",
        @SerializedName("liked")
        var liked: Boolean? = false,
        @SerializedName("location")
        var location: String? = "",
        @SerializedName("longitude")
        var longitude: String? = "",
        @SerializedName("looking")
        var looking: Int? = 0,
        @SerializedName("lookings")
        var lookings: List<Looking>? = ArrayList(),
        @SerializedName("occupation_company")
        var occupationCompany: String? = "",
        @SerializedName("occupation_title")
        var occupationTitle: String? = "",
        @SerializedName("recovery_email")
        var recoveryEmail: String? = "",
        @SerializedName("phone_number")
        var phoneNumber: String? = "",
        @SerializedName("profile_picture_url")
        var profilePictureUrl: String? = "",
        @SerializedName("percentage")
        var percentage: Int? = 0,
        @SerializedName("profile_type")
        var profileType: String? = "",
        @SerializedName("ref_user")
        var refUser: Int? = 0,
        @SerializedName("religion")
        var religion: Int? = 0,
        @SerializedName("religions")
        var religions: List<Religion>? = ArrayList(),
        @SerializedName("smoking")
        var smoking: Int? = 0,
        @SerializedName("mutual_friends")
        var mutualFriends: List<MutualFriend>? = ArrayList(),
        @SerializedName("smokings")
        var smokings: List<Smoking>? = ArrayList(),
        @SerializedName("suggested_by")
        var suggestedBy: List<SuggestedBy>? = ArrayList(),
        @SerializedName("superliked")
        var superliked: Boolean? = false,
        @SerializedName("travel_location")
        var travelLocation: Boolean? = false,
        @SerializedName("vaccine")
        var vaccine: Int? = 0,
        @SerializedName("vaccines")
        var vaccines: List<Vaccine>? = ArrayList(),
        @SerializedName("relationship")
        var relationship: List<Relationship>? = ArrayList()
    )

    data class AstrologicalSign(
        @SerializedName("icon")
        var icon: String? = "",
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )
    data class Relationship(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )
    data class Caste(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )

    data class Children(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )

    data class Education(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )

    data class Language(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )

    data class Looking(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )

    data class Religion(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )

    data class Smoking(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = "",
    )

    data class MutualFriend(
        @SerializedName("image")
        var image: String? = "",
        @SerializedName("name")
        var name: String? = "",
    )

    data class SuggestedBy(
        @SerializedName("date_joined")
        var dateJoined: String? = "",
        @SerializedName("profile_picture_url")
        var profilePictureUrl: String? = "",
        @SerializedName("email")
        var email: String? = "",
        @SerializedName("first_name")
        var firstName: String? = "",
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("is_active")
        var isActive: Boolean? = false,
        @SerializedName("is_staff")
        var isStaff: Boolean? = false,
        @SerializedName("is_superuser")
        var isSuperuser: Boolean? = false,
        @SerializedName("last_login")
        var lastLogin: Any? = Any(),
        @SerializedName("last_name")
        var lastName: String? = "",
        /*@SerializedName("photo")
        var photo: Photo? = Photo(),*/
        @SerializedName("username")
        var username: String? = ""
    )

    data class Vaccine(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("name")
        var name: String? = ""
    )

    data class Photo(
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("image")
        var image: String? = "",
        @SerializedName("is_deleted")
        var isDeleted: Boolean? = false,
        @SerializedName("is_instagram")
        var isInstagram: Boolean? = false,
        @SerializedName("is_profile")
        var isProfile: Boolean? = false,
        @SerializedName("user_picture")
        var userPicture: Int? = 0,
    )
}

