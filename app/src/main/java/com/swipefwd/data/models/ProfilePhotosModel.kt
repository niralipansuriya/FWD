import com.google.gson.annotations.SerializedName

data class ProfilePhotosModel(
    @SerializedName("code") val code: Int? = 0,
    @SerializedName("data") val dataProfile: DataProfile,
    @SerializedName("status") var status: String? = "",
    @SerializedName("message") var message: String? = "",


    ) {
    data class DataProfile(
        @SerializedName("photo") var photo: String? = "",
        @SerializedName("userPhotos") val userPhotos: ArrayList<UserPhotos>? = arrayListOf(),
        @SerializedName("profile_image") var profileImage: String? = "",
        @SerializedName("user_id") val user_id: Int? = null

        ) {
        data class UserPhotos(
            @SerializedName("photo") var photo: String? = "",
            @SerializedName("position") var position: Int? = 0,
            var isInAppropriate :Boolean=false,
            var isNull :Boolean=false


        )
    }
}


/*
data class ProfilePhotosModel(
    val code: Int,
    val `data`: ProfileData,
    val message: String,
    val status: String
)

data class ProfileData(
    val photo: String,
    val userPhotos: List<UserPhoto>
)

data class UserPhoto(
    val photo: String,
    val position: String
)*/
