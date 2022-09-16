data class BasicProfileDetails(
    val code: Int,
    val `data`: BasicData,
    val message: String,
    val status: String
)

data class BasicData(
    val dob: String,
    val first_name: String,
    val gender: Int,
    val last_name: String,
    val profile_image: String,
    val user_id: Int,
    val user_type: Int
)