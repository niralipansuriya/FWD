package com.swipefwd.data.source.remote.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b2\n\u0002\u0010\t\n\u0002\b?\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010#\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010$\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010%\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010&\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\'\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010(\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010)\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010*\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010+\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010,\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010-\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010.\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010/\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u00100\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u00101\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u00102\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u00103\u001a\u000204X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u00105\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u00106\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u00107\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u00108\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u00109\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010:\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010;\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010<\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010=\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010>\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010?\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010@\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010A\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010B\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010C\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010D\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010E\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010F\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010G\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010H\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010I\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010J\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010K\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010L\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010M\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010N\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010O\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010P\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010Q\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010R\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010S\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010T\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010U\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010V\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010W\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010X\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010Y\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010Z\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010[\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\\\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010]\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010^\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010_\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010`\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010a\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010b\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010c\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010d\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010e\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010f\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010g\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010h\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010i\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010j\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010k\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010l\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010m\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010n\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010o\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010p\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010q\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010r\u001a\u00020\u0001X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006s"}, d2 = {"ACCOUNT_RECOVER_EMAIL_URL", "", "ASTROLOGICAL_SIGN", "ASTROLOGY_SIGN_URL", "BASE_URL", "BASE_URL_NEW", "BASIC_PROFILE_SET", "BOOSTER_PURCHASE_URL", "CAST_URL", "CHANGE_USER_TYPE_URL", "CHAT_IMAGE_URL", "CHILDREN_API", "CHILDREN_URL", "CONNECTION_URL", "COVID_VACCINATION_URL", "DATE_LIST", "DELETE_ACCOUNT", "DELETE_USER", "DELETE_USER_IMAGES", "DELETE_USER_PICTURE_URL", "DELETE_USER_PROFILE_PICTURE_URL", "DETAILED_PROFILE_SET", "EDUCATION_URL", "EMAIL_RECOVERY", "FACEBOOK_FRIENDS_LIST_URL", "FACEBOOK_PERMISSIONS", "FACEBOOK_URL", "GESTURE_VERIFICATION", "GET_ACTIVITY_PENDING_EXPIRE_URL", "GET_ADD_ACTIVE_PENDING_REQUEST_URL", "GET_AUTH_TOKEN", "GET_EDUCATION", "GET_MATCHES_URL", "GET_OPERATOR_BY_ISO", "GET_OTP_URL", "GET_POST_USER_PROFILE_PICTURE_URL", "GET_UPDATE_PREFERENCE_URL", "GET_UPDATE_PROFILE_URL", "GET_UPDATE_SETTINGS_URL", "GOOGLE_ADDRESS", "GOOGLE_BASE_URL", "HOME_TRIBE_CONNECTIONS", "IMAGE_MODERATION_URL", "INACTIVITY", "INACTIVITY_URL", "INSERT_USER_EDUCATION", "INSTAGRAM_BASE_PROFILE", "INSTAGRAM_BASE_TOKEN", "INSTAGRAM_TOKEN_URL", "INVITE_CONNECTOR", "INVITE_CONTACT_URL", "IO_TIMEOUT", "", "LANGUAGE_API", "LANGUAGE_URL", "LINKEDIN_BASE_URL", "LINKEDIN_EMAIL_URL", "LINKEDIN_PROFILE_URL", "LINKEDIN_TOKEN_URL", "LOGIN_URL", "MAKE_RECHARGE", "NEW_BASE_URL", "OTP_VERIFY", "PLAN_PURCHASE_URL", "POPUP_SHOW_URL", "PREFERENCE_RELATIONSHIP_URL", "PROFILE_PIC_UPLOAD", "PROFILE_RELATIONSHIP_URL", "RECOVER_ACCOUNT_URL", "REFRESH_TOKEN", "REGISTER_URL", "RELATIONSHIP_STATUS", "RELIGION_API", "RELIGION_URL", "RELOADLY_AUTH_TOKEN", "REMOVE_TRIBE_DATER", "RESEND_OTP_API", "SEND_CONNECTION_URL", "SEND_CONTACT_URL", "SEND_FACEBOOK_ID_URL", "SEND_INSTAGRAM_IMAGES_URL", "SEND_OTP_EMAIL", "SEND_RECEIPT_URL", "SET_EMAIL", "SET_LOCATION", "SET_USER_TYPE", "SIGNUP_OR_SIGNIN", "SMOKING_API", "SMOKING_URL", "SOCIAL_SIGNUP", "SUGGESTION_PROFILE_URL", "SWIPE_BASE_URL", "SWIPE_LEFT_RIGHT_REWIND", "SWIPE_LEFT_RIGHT_REWIND_URL", "SWIPING_MATCHES_URL", "SWIPING_PROFILES_URL", "SYNC_CONTACTS", "TRAVEL_LOCATION", "UPDATE_DATE_LIST", "UPDATE_TRAVEL_LOCATION_URL", "UPDATE_USER_TOKEN", "UPDAte_MOBILE", "UPLOAD_CHAT_IMAGE", "UPLOAD_DETAILED_PROFILE_PHOTOS", "USER_ADVANCE_PREFERENCE", "USER_LOGOUT_URL", "USER_PLANS_URL", "USER_PREFERENCE", "USER_PREFERENCE_SUBMIT_URL", "USER_PROFILE_SUBMIT_URL", "USER_RECOVER_EMAIL_URL", "VACCINATION_API", "VERIFY_OTP_EMAIL", "VERIFY_OTP_URL", "VERIFY_PROFILE", "app_debug"})
public final class NetworkConfigKt {
    public static final long IO_TIMEOUT = 30L;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "http://3.7.104.75/api/v1/app/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NEW_BASE_URL = "http://3.7.104.75/api/v1/app/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SWIPE_BASE_URL = "http://3.7.104.75/api/v1/app/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GOOGLE_BASE_URL = "https://maps.googleapis.com/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHAT_IMAGE_URL = "http://52.66.190.14/chattest/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FACEBOOK_URL = "https://graph.facebook.com/me/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LINKEDIN_BASE_URL = "https://api.linkedin.com/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INSTAGRAM_BASE_TOKEN = "https://api.instagram.com/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INSTAGRAM_BASE_PROFILE = "https://graph.instagram.com/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RELOADLY_AUTH_TOKEN = "https://auth.reloadly.com";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INSTAGRAM_TOKEN_URL = "oauth/access_token";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LINKEDIN_TOKEN_URL = "oauth/v2/accessToken";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LINKEDIN_PROFILE_URL = "v2/me";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LINKEDIN_EMAIL_URL = "v2/emailAddress";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN_URL = "auth/login/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REGISTER_URL = "auth/register/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RECOVER_ACCOUNT_URL = "auth/update/phonenumber/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_OTP_URL = "phonenumber/code/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VERIFY_OTP_URL = "phonenumber/verify/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LANGUAGE_URL = "cms/language/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EDUCATION_URL = "cms/education/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ASTROLOGY_SIGN_URL = "cms/astrological/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CAST_URL = "cms/cast/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHILDREN_URL = "cms/children/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RELIGION_URL = "cms/religion/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SMOKING_URL = "cms/smoking/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PROFILE_RELATIONSHIP_URL = "cms/looking/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PREFERENCE_RELATIONSHIP_URL = "cms/relationship/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String COVID_VACCINATION_URL = "cms/vaccinated/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_PROFILE_SUBMIT_URL = "user/profile/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_POST_USER_PROFILE_PICTURE_URL = "user/picture1/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_RECOVER_EMAIL_URL = "user/recover/email/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACCOUNT_RECOVER_EMAIL_URL = "auth/email/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_PREFERENCE_SUBMIT_URL = "user/preference/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DELETE_USER_PICTURE_URL = "user/picture/{id}/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_UPDATE_PROFILE_URL = "user/profile/{id}/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_MATCHES_URL = "user/matches/{id}/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_UPDATE_PREFERENCE_URL = "user/preference/{id}/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEND_INSTAGRAM_IMAGES_URL = "user/instagram/picture/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String UPDATE_USER_TOKEN = "user/device/token/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEND_CONTACT_URL = "user/contact/verify/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INVITE_CONTACT_URL = "contact/sms/invite/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEND_CONNECTION_URL = "tribe/connection/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CONNECTION_URL = "tribe/connection_v2/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANGE_USER_TYPE_URL = "user/usertype/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REMOVE_TRIBE_DATER = "tribe/remove/connection/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUGGESTION_PROFILE_URL = "user/suggestion/profile/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String POPUP_SHOW_URL = "tribe/connection_v1/{id}/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SWIPING_MATCHES_URL = "user/swipe_profiles/{id}/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SWIPING_PROFILES_URL = "profiles/{id}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SWIPE_LEFT_RIGHT_REWIND_URL = "user/swipes/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SWIPE_LEFT_RIGHT_REWIND = "user/recall/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_UPDATE_SETTINGS_URL = "user/settings/{id}/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_LOGOUT_URL = "auth/logout/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_PLANS_URL = "account/plan/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PLAN_PURCHASE_URL = "account/subscript/plan/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BOOSTER_PURCHASE_URL = "account/booster/subscription/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEND_RECEIPT_URL = "account/subscription/receipt/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String UPDATE_TRAVEL_LOCATION_URL = "account/travel/location/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_ADD_ACTIVE_PENDING_REQUEST_URL = "tribe/manage/connection/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_ACTIVITY_PENDING_EXPIRE_URL = "tribe/dater/connection/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEND_FACEBOOK_ID_URL = "user/facebook/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FACEBOOK_FRIENDS_LIST_URL = "connections/{id}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String IMAGE_MODERATION_URL = "validations/photos";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INACTIVITY_URL = "auth/lastactivity";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_AUTH_TOKEN = "oauth/token";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_OPERATOR_BY_ISO = "/operators/countries/{iso}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MAKE_RECHARGE = "/topups";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String UPLOAD_CHAT_IMAGE = "testimage.php";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FACEBOOK_PERMISSIONS = "permissions";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DELETE_ACCOUNT = "settings/{id}/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GESTURE_VERIFICATION = "user/verifyProfile/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GOOGLE_ADDRESS = "maps/api/geocode/json";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INACTIVITY = "auth/lastactivity/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL_NEW = "http://54.219.172.96:3003/api/v1/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SIGNUP_OR_SIGNIN = "user/signup";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOCIAL_SIGNUP = "user/socialSignupCheck";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String OTP_VERIFY = "user/otpVerify";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REFRESH_TOKEN = "user/refreshToken";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EMAIL_RECOVERY = "user/emailrecovery";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String UPDAte_MOBILE = "user/updateMobile";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PROFILE_PIC_UPLOAD = "user/uploadProfilepic";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASIC_PROFILE_SET = "user/setBasicProfile";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SET_LOCATION = "user/setlocation";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DETAILED_PROFILE_SET = "user/setAdvanceProfile";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String UPLOAD_DETAILED_PROFILE_PHOTOS = "user/uploadPhotos";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INSERT_USER_EDUCATION = "user/insertUserEducation";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_EDUCATION = "dropdown/education";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_PREFERENCE = "user/setPreferences";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DELETE_USER_PROFILE_PICTURE_URL = "user/removeProfilepic";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DELETE_USER_IMAGES = "user/removePhoto";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_ADVANCE_PREFERENCE = "user/setAdvancePreferences";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ASTROLOGICAL_SIGN = "dropdown/astrological_sign";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRAVEL_LOCATION = "user/setTravellocation";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RELATIONSHIP_STATUS = "dropdown/relationship_level";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RESEND_OTP_API = "user/resendOtp";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RELIGION_API = "dropdown/religion";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VACCINATION_API = "dropdown/vaccination";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHILDREN_API = "dropdown/children";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SMOKING_API = "dropdown/smoking";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LANGUAGE_API = "dropdown/language";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SET_USER_TYPE = "user/setUsertype";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VERIFY_PROFILE = "user/verifyProfile";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DELETE_USER = "user/deleteUser";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HOME_TRIBE_CONNECTIONS = "user/home/home";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SYNC_CONTACTS = "user/home/syncContact";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String INVITE_CONNECTOR = "user/home/inviteConnector";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SET_EMAIL = "user/setUserEmail";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VERIFY_OTP_EMAIL = "user/verifyOTPEmail";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SEND_OTP_EMAIL = "user/sendOTPEmail";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DATE_LIST = "user/dateList";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String UPDATE_DATE_LIST = "user/updatedateList";
}