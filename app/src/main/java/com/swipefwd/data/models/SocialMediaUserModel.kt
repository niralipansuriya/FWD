package com.swipefwd.data.models


import com.google.gson.annotations.SerializedName

data class SocialMediaUserModel(
    @SerializedName("email_id") var emailId: String? = "",
    @SerializedName("f_name") var firstName: String? = "",
    @SerializedName("l_name") var lastName: String? = "",
    @SerializedName("profile_picture") var profilePicture: String? = "",
    @SerializedName("social_id") var socialId: String? = "",
    @SerializedName("social_type") var socialType: String? = "",
    @SerializedName("gender") var gender: String? = "", //female, male
    @SerializedName("dob") var dob: String? = "" // MM/DD/YYYY
)