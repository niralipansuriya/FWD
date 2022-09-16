import com.google.gson.annotations.SerializedName

data class BasicProfileUpload(
    @SerializedName("code") val code: Int? = 0,
    @SerializedName("data") val data: DataProfile,
    @SerializedName("status") var status: String? = "",
    @SerializedName("message") var message: String? = "",
)

data class DataProfile(
    @SerializedName("profile_image") var profile_image: String? = "",
    @SerializedName("user_id") val user_id: Int? = null
)