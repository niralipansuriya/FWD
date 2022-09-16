package com.swipefwd.utils.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    const val PREF_NAME = "FWD_Preference"

    val PREF_LOGIN_FLAG = booleanPreferencesKey("loginFlag")
    val PREF_ACCOUNT_TYPE = stringPreferencesKey("accountType")// Matchmaker(friends)/Dater(me)
    val PREF_LANGUAGE = stringPreferencesKey("language")
    val PREF_OCCUPATION = stringPreferencesKey("occupation")
    val PREF_EDUCATION = stringPreferencesKey("education")
    val PREF_CAST = stringPreferencesKey("cast")
    val PREF_CHILDREN = stringPreferencesKey("children")
    val PREF_RELIGION = stringPreferencesKey("religion")
    val PREF_SMOKING = stringPreferencesKey("smoking")
    val PREF_RELATIONSHIP = stringPreferencesKey("relationship")
    val PREF_COVID = stringPreferencesKey("covid")
    val PREF_ASTROLOGY_SIGN = stringPreferencesKey("astrologySign")
    val PREF_FIRST_NAME = stringPreferencesKey("first_name")
    val PREF_LAST_NAME = stringPreferencesKey("last_name")
    val PREF_PROFILE_IMAGE = stringPreferencesKey("profile_image")
    val PREF_DOB = stringPreferencesKey("dob") //09/18/1984
    val PREF_CONVERTED_DOB = stringPreferencesKey("converted_dob") //"$mYear-$mMonth-$mDay"
    val PREF_GENDER = stringPreferencesKey("gender") //female, male
    val PREF_AREA = stringPreferencesKey("area")
    val PREF_BIO = stringPreferencesKey("bio")
    val PREF_HEIGHT = stringPreferencesKey("height")
    val PREF_IS_HEIGHT_FEET = booleanPreferencesKey("is_height_feet")
    val PREF_IMAGES = stringPreferencesKey("selected_images")
    val PREF_LAT = doublePreferencesKey("latitude")
    val PREF_LONG = doublePreferencesKey("longitude")
    val PREF_INSTA_ID = stringPreferencesKey("insta_id")
    val PREF_INSTA_NAME = stringPreferencesKey("insta_user_name")
    val PREF_LINKEDIN_NAME = stringPreferencesKey("linkedin_user_name")
    val PREF_IS_SHOW_PROFILE = booleanPreferencesKey("is_insta_profile_show")
    val PREF_INSTA_IMAGES = stringPreferencesKey("insta_images")
    val PREF_IS_AGREE = booleanPreferencesKey("agreement")
    val PREF_INVITE_EXPIRED = booleanPreferencesKey("is_invite_expired")
    val PREF_FACEBOOK_FRD_PERMISSION = booleanPreferencesKey("frd_permission")
    val PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION = booleanPreferencesKey("connector_frd_permission")
    val PREF_TRAVEL_LOCATION = stringPreferencesKey("travel_location")
    val PREF_FACEBOOK_IDS = stringPreferencesKey("facebook_friend_ids")
    val PREF_REMAINING_INVITATION = intPreferencesKey("remaining_invitation")
    val PREF_REMAINING_CONNECTOR_CONNECTION = intPreferencesKey("remain_connection")
    val PREF_IS_NEW_USER = booleanPreferencesKey("is_new_user")


    /*Preference screen keys*/
    val PREF_PREF_LANGUAGE = stringPreferencesKey("pref_language")
    val PREF_PREF_EDUCATION = stringPreferencesKey("pref_education")
    val PREF_PREF_ASTROLOGY_SIGN = stringPreferencesKey("pref_astrologySign")
    val PREF_PREF_CAST = stringPreferencesKey("pref_cast")
    val PREF_PREF_CHILDREN = stringPreferencesKey("pref_children")
    val PREF_PREF_RELIGION = stringPreferencesKey("pref_religion")
    val PREF_PREF_SMOKING = stringPreferencesKey("pref_smoking")
    val PREF_PREF_RELATIONSHIP = stringPreferencesKey("pref_relationship")
    val PREF_PREF_START_HEIGHT = stringPreferencesKey("pref_start_height")
    val PREF_PREF_END_HEIGHT = stringPreferencesKey("pref_end_height")
    val PREF_IS_VERIFIED = booleanPreferencesKey("is_verified")
    val PREF_PREF_GENDER = stringPreferencesKey("pref_gender")
    val PREF_START_AGE = intPreferencesKey("start_age")
    val PREF_END_AGE = intPreferencesKey("end_age")
    val PREF_MAX_DISTANCE = intPreferencesKey("max_distance")
    val PREF_RECOVERY_EMAIL = stringPreferencesKey("recovery_email")
    val PREF_PREF_IS_HEIGHT_FEET = booleanPreferencesKey("pref_is_height_feet")
    val PREF_PREF_START_HEIGHT_INCHES = intPreferencesKey("pref_height_start_inches")
    val PREF_PREF_END_HEIGHT_INCHES = intPreferencesKey("pref_height_end_inches")

    val TIMEOUT_OTP = stringPreferencesKey("time_out_otp")
    val PREF_PROFILE_FLAG = booleanPreferencesKey("profile_flag")
    val PREF_TOKEN = stringPreferencesKey("authorization_token")
    val PREF_OTP_COUNTER = intPreferencesKey("otp_counter")
    val PREF_SOCIAL_MEDIA_USER = stringPreferencesKey("social_media_user_details")
    val PREF_PREFERENCE_FLAG = booleanPreferencesKey("preference_flag")
    val PREF_ADV_PROFILE_FLAG = booleanPreferencesKey("advance_profile_flag")
    val PREF_CURRENT_SCREEN =
        stringPreferencesKey("screen_flag") /*"0" = default, "1" = UserInfo, "2" = profile & Preference*/
    val PREF_IS_ONBOARD_TUTORIAL_COMPLETED = booleanPreferencesKey("onboard_tutorial_completed_status")
    val PREF_PHONE_NUMBER = stringPreferencesKey("phone_number")
    val PREF_COUNTRY_CODE = stringPreferencesKey("country_code")
    val PREF_LINKEDIN_ID = stringPreferencesKey("linkedin_id")
    val PREF_PREVIOUS_NUMBER = booleanPreferencesKey("previous_number")
    val PREF_USER_ID = intPreferencesKey("user_id")
    val PREF_ACCOUNT_TOOL_TIP = booleanPreferencesKey("is_account_tooltip")
    val PREF_CONNECTOR_TOOL_TIP = booleanPreferencesKey("connector_tool_tip")
    val PREF_DARK_MODE_TOOL_TIP = booleanPreferencesKey("is_dark_mode_tooltip")
    val PREF_FWD_TOOL_TIP = booleanPreferencesKey("fwd_tooltip")
    val PREF_FWD_GREEN_TOOL_TIP = booleanPreferencesKey("fwd_green_tooltip")

    /*Swiping Flow*/
    val PREF_NOT_INTERESTED = booleanPreferencesKey("is_not_interested")
    val PREF_INTERESTED = booleanPreferencesKey("is_interested")
    val PREF_DO_NOT_SHOW_DELETE_DATER = booleanPreferencesKey("delete_dater")
    val PREF_PROFILE_PERCENTAGE = intPreferencesKey("profile_percentage")
    val PREF_DATER_DISABLED = booleanPreferencesKey("dater_disabled")
    val PREF_CONNECTOR_DISABLED = booleanPreferencesKey("connector_disabled")
    val PREF_SMS_SENT = booleanPreferencesKey("is_sms_sent")

    //   val PROFILE_CREATE_DATE = stringPreferencesKey("on_board_complete_time")
    val SHOW_VERIFICATION_REMINDER_DATE = stringPreferencesKey("verification_reminder_date")
    val SHOW_EMAIL_REMINDER_DATE = stringPreferencesKey("email_reminder_date")
   val IS_PROFILE_VERIFIED =  booleanPreferencesKey("verify_dialog_shown")
 //   val IS_PROFILE_VERIFIED =  intPreferencesKey("verify_dialog_shown")

    val INVITER_GENDER = stringPreferencesKey("inviter_gender")

    val PREF_SHOW_NOTIFICATION_DIALOG = booleanPreferencesKey("show_notification_dialog")

    val PREF_IS_DOB_DIALOG_OPEN = booleanPreferencesKey("is_dob_dialog_open")
}