data class DeleteProfileImageModel(
    val code: Int,
    val `data`: DeleteProfileImagData,
    val message: String,
    val status: String
)

data class DeleteProfileImagData(
    val dob: String,
    val first_name: String,
    val gender: Int,
    val last_name: String,
    val profile_image: String,
    val user_id: Int,
    val user_type: Int
)