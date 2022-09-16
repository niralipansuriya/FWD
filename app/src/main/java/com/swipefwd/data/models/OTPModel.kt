data class OTPModel(
    val code: Int,
    val `data`: Data,
    val message: String,
    val status: String
)

data class Data(
    val gender: Int,
    val invitation_expired: Boolean,
    val is_alternat_login: Int,
    val is_advance_profile: Boolean,
    val is_basic_profile: Boolean,
    val is_location: Boolean,
    val is_verified: Boolean,
    val preference: Boolean,
    val signup_type: Int,
    val userExist: Int,
    val token: String,
    val userDetails: UserDetails
)

data class UserDetails(
    val astrological_sign: String,
    val bio: String,
    val children: String,
    val dob: String,
    val education_level: String,
    val facebook: String,
    val first_name: String,
    val google: String,
    val graduation_year: Int,
    val height: Int,
    val instagram_name: String,
    val institute: String,
    val is_agree: Boolean,
    val is_connector_disabled: Boolean,
    val is_dater_disabled: Boolean,
    val is_vaccinated: String,
    val language: String,
    val last_name: String,
    val latitude: Double,
    val linkedin: String,
    val location_name: String,
    val longitude: Double,
    val occupation: String,
    val phone_number: String,
    val profile_complete_per: Int,
    val profile_image: String,
    val relation_interest: String,
    val religion: String,
    val remaim_connection: Int,
    val remain_invitation: Int,
    val smoke: String,
    val travel_latitude: Int,
    val travel_location_name: String,
    val travel_longitude: Int,
    val userPhoto: List<UserPhoto>,
    val user_id: Int,
    val user_type: Int,
    val is_profile_verified: Boolean
)

data class UserPhoto(
    val photo: String,
    val position: String
)