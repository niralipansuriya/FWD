package com.swipefwd.data.source.remote.api

const val IO_TIMEOUT = 30L

//const val BASE_URL = "https://jtbf0sbl55.execute-api.ap-south-1.amazonaws.com/dev/api/v1/app/"
//const val BASE_URL = "http://fwd-dev.ap-south-1.elasticbeanstalk.com/api/v1/app/"

//--- previous base url
//const val BASE_URL ="https://swipefwd.iapplabz.co.in/api/v1/app/"  //"https://qa.swipefwd.com/api/v1/app/"
//const val NEW_BASE_URL = "https://swipefwd.iapplabz.co.in/api/v1/app/"  //"https://qa.swipefwd.com/api/v1/app/"
//const val SWIPE_BASE_URL ="https://swipefwd.iapplabz.co.in/api/v1/app/" //"https://qa.swipefwd.com/api/v1/app/"

//--- new base url ChicMic
const val BASE_URL ="http://3.7.104.75/api/v1/app/"
const val NEW_BASE_URL = "http://3.7.104.75/api/v1/app/"
const val SWIPE_BASE_URL ="http://3.7.104.75/api/v1/app/"

//--- Temp base url ChicMic
/*const val BASE_URL ="http://75c4-112-196-113-2.ngrok.io/api/v1/app/"
const val NEW_BASE_URL = "http://75c4-112-196-113-2.ngrok.io/api/v1/app/"
const val SWIPE_BASE_URL ="http://75c4-112-196-113-2.ngrok.io/api/v1/app/"*/

//Google Base Url
const val GOOGLE_BASE_URL ="https://maps.googleapis.com/"

const val CHAT_IMAGE_URL = "http://52.66.190.14/chattest/"
const val FACEBOOK_URL = "https://graph.facebook.com/me/"
const val LINKEDIN_BASE_URL = "https://api.linkedin.com/"
const val INSTAGRAM_BASE_TOKEN = "https://api.instagram.com/"
const val INSTAGRAM_BASE_PROFILE = "https://graph.instagram.com/"
const val RELOADLY_AUTH_TOKEN = "https://auth.reloadly.com"

const val INSTAGRAM_TOKEN_URL = "oauth/access_token"

const val LINKEDIN_TOKEN_URL = "oauth/v2/accessToken"
const val LINKEDIN_PROFILE_URL = "v2/me"
const val LINKEDIN_EMAIL_URL = "v2/emailAddress"

const val LOGIN_URL = "auth/login/"
const val REGISTER_URL = "auth/register/"
const val RECOVER_ACCOUNT_URL = "auth/update/phonenumber/"

const val GET_OTP_URL = "phonenumber/code/"
const val VERIFY_OTP_URL = "phonenumber/verify/"

const val LANGUAGE_URL = "cms/language/"
const val EDUCATION_URL = "cms/education/"
const val ASTROLOGY_SIGN_URL = "cms/astrological/"
const val CAST_URL = "cms/cast/"
const val CHILDREN_URL = "cms/children/"
const val RELIGION_URL = "cms/religion/"
const val SMOKING_URL = "cms/smoking/"
const val PROFILE_RELATIONSHIP_URL = "cms/looking/"
const val PREFERENCE_RELATIONSHIP_URL = "cms/relationship/"
const val COVID_VACCINATION_URL = "cms/vaccinated/"

const val USER_PROFILE_SUBMIT_URL = "user/profile/"
//const val GET_POST_USER_PROFILE_PICTURE_URL = "user/picture/"
const val GET_POST_USER_PROFILE_PICTURE_URL = "user/picture1/"
const val USER_RECOVER_EMAIL_URL = "user/recover/email/"
const val ACCOUNT_RECOVER_EMAIL_URL = "auth/email/"
const val USER_PREFERENCE_SUBMIT_URL = "user/preference/"
const val DELETE_USER_PICTURE_URL = "user/picture/{id}/"
const val GET_UPDATE_PROFILE_URL = "user/profile/{id}/"
const val GET_MATCHES_URL = "user/matches/{id}/"
const val GET_UPDATE_PREFERENCE_URL = "user/preference/{id}/"
const val SEND_INSTAGRAM_IMAGES_URL = "user/instagram/picture/"
const val UPDATE_USER_TOKEN = "user/device/token/"

const val SEND_CONTACT_URL = "user/contact/verify/"
const val INVITE_CONTACT_URL = "contact/sms/invite/"
const val SEND_CONNECTION_URL = "tribe/connection/"
const val CONNECTION_URL = "tribe/connection_v2/"
const val CHANGE_USER_TYPE_URL = "user/usertype/"
const val REMOVE_TRIBE_DATER = "tribe/remove/connection/"
const val SUGGESTION_PROFILE_URL = "user/suggestion/profile/"
const val POPUP_SHOW_URL = "tribe/connection_v1/{id}/"

//const val SWIPING_MATCHES_URL = "matches"
const val SWIPING_MATCHES_URL = "user/swipe_profiles/{id}/"
const val SWIPING_PROFILES_URL = "profiles/{id}"
const val SWIPE_LEFT_RIGHT_REWIND_URL = "user/swipes/"
const val SWIPE_LEFT_RIGHT_REWIND = "user/recall/"

const val GET_UPDATE_SETTINGS_URL = "user/settings/{id}/"
const val USER_LOGOUT_URL = "auth/logout/"

const val USER_PLANS_URL = "account/plan/"
const val PLAN_PURCHASE_URL = "account/subscript/plan/"
const val BOOSTER_PURCHASE_URL = "account/booster/subscription/"
const val SEND_RECEIPT_URL = "account/subscription/receipt/"
const val UPDATE_TRAVEL_LOCATION_URL = "account/travel/location/"

const val GET_ADD_ACTIVE_PENDING_REQUEST_URL = "tribe/manage/connection/"
const val GET_ACTIVITY_PENDING_EXPIRE_URL = "tribe/dater/connection/"

const val SEND_FACEBOOK_ID_URL = "user/facebook/"
const val FACEBOOK_FRIENDS_LIST_URL = "connections/{id}"
const val IMAGE_MODERATION_URL = "validations/photos"
const val INACTIVITY_URL = "auth/lastactivity"

/*Wallet - Reloadly*/
const val GET_AUTH_TOKEN = "oauth/token"
const val GET_OPERATOR_BY_ISO = "/operators/countries/{iso}"
const val MAKE_RECHARGE = "/topups"


const val UPLOAD_CHAT_IMAGE = "testimage.php"
const val FACEBOOK_PERMISSIONS = "permissions"

const val DELETE_ACCOUNT = "settings/{id}/"

//Gesture Verification
const val GESTURE_VERIFICATION = "user/verifyProfile/"

//Google address
const val GOOGLE_ADDRESS = "maps/api/geocode/json"

const val INACTIVITY = "auth/lastactivity/"





/*TRI-STATE APIS */

const val BASE_URL_NEW ="http://54.219.172.96:3003/api/v1/"
//const val BASE_URL_NEW ="http://192.168.1.225:3003/api/v1/"
const val SIGNUP_OR_SIGNIN = "user/signup"
const val SOCIAL_SIGNUP = "user/socialSignupCheck"
const val OTP_VERIFY = "user/otpVerify"
const val REFRESH_TOKEN = "user/refreshToken"
const val EMAIL_RECOVERY = "user/emailrecovery"
const val UPDAte_MOBILE = "user/updateMobile"
const val PROFILE_PIC_UPLOAD = "user/uploadProfilepic"
const val BASIC_PROFILE_SET = "user/setBasicProfile"
const val SET_LOCATION = "user/setlocation"
const val DETAILED_PROFILE_SET   = "user/setAdvanceProfile"
const val UPLOAD_DETAILED_PROFILE_PHOTOS   = "user/uploadPhotos"
const val INSERT_USER_EDUCATION   = "user/insertUserEducation"
const val GET_EDUCATION   = "dropdown/education"
const val USER_PREFERENCE = "user/setPreferences"
const val DELETE_USER_PROFILE_PICTURE_URL = "user/removeProfilepic"
const val DELETE_USER_IMAGES= "user/removePhoto"
const val USER_ADVANCE_PREFERENCE = "user/setAdvancePreferences"
const val ASTROLOGICAL_SIGN = "dropdown/astrological_sign"
const val TRAVEL_LOCATION = "user/setTravellocation"
const val RELATIONSHIP_STATUS = "dropdown/relationship_level"
const val RESEND_OTP_API = "user/resendOtp"
const val RELIGION_API = "dropdown/religion"
const val VACCINATION_API = "dropdown/vaccination"
const val CHILDREN_API = "dropdown/children"
const val SMOKING_API = "dropdown/smoking"
const val LANGUAGE_API = "dropdown/language"
const val SET_USER_TYPE = "user/setUsertype"
const val VERIFY_PROFILE = "user/verifyProfile"
const val DELETE_USER = "user/deleteUser"
const val HOME_TRIBE_CONNECTIONS = "user/home/home"
const val SYNC_CONTACTS = "user/home/syncContact"
const val INVITE_CONNECTOR = "user/home/inviteConnector"
const val SET_EMAIL = "user/setUserEmail"
const val VERIFY_OTP_EMAIL = "user/verifyOTPEmail"
const val SEND_OTP_EMAIL = "user/sendOTPEmail"
const val DATE_LIST = "user/dateList"
const val UPDATE_DATE_LIST = "user/updatedateList"















