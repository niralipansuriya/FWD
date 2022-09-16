package com.swipefwd.ui.home.settings;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 p2\u00020\u0001:\u0001pB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\u0016H\u0002J\b\u0010G\u001a\u00020EH\u0002J\u0010\u0010H\u001a\u00020E2\u0006\u0010I\u001a\u00020JH\u0002J\b\u0010K\u001a\u00020EH\u0002J\u0010\u0010L\u001a\u00020E2\u0006\u0010M\u001a\u00020NH\u0002J\b\u0010O\u001a\u00020EH\u0002J\b\u0010P\u001a\u00020EH\u0002J\"\u0010Q\u001a\u00020E2\u0006\u0010R\u001a\u00020=2\u0006\u0010S\u001a\u00020=2\b\u0010T\u001a\u0004\u0018\u00010\u000fH\u0014J\u0012\u0010U\u001a\u00020E2\b\u0010V\u001a\u0004\u0018\u00010WH\u0014J\u0010\u0010X\u001a\u00020E2\u0006\u0010Y\u001a\u00020ZH\u0002J\u0010\u0010[\u001a\u00020E2\u0006\u0010Y\u001a\u00020\\H\u0002J\b\u0010]\u001a\u00020EH\u0014J\b\u0010^\u001a\u00020EH\u0002J\b\u0010_\u001a\u00020EH\u0002J\b\u0010`\u001a\u00020EH\u0002J\b\u0010a\u001a\u00020EH\u0002J\b\u0010b\u001a\u00020EH\u0002J\b\u0010c\u001a\u00020EH\u0002J\b\u0010d\u001a\u00020EH\u0002J\b\u0010e\u001a\u00020EH\u0002J\u0010\u0010f\u001a\u00020E2\u0006\u0010g\u001a\u00020hH\u0002J\b\u0010i\u001a\u00020EH\u0002J\u0010\u0010j\u001a\u00020E2\u0006\u0010k\u001a\u00020lH\u0002J\b\u0010m\u001a\u00020EH\u0002J\u0010\u0010n\u001a\u00020E2\u0006\u0010o\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0010\u0012\f\u0012\n \"*\u0004\u0018\u00010\u000f0\u000f0\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010\'R\u000e\u0010*\u001a\u00020+X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010,\u001a\u00020-8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b0\u0010)\u001a\u0004\b.\u0010/R\u001b\u00101\u001a\u0002028BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b5\u0010)\u001a\u0004\b3\u00104R\u000e\u00106\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u00107\u001a\u0002088BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b;\u0010)\u001a\u0004\b9\u0010:R\u000e\u0010<\u001a\u00020=X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010>\u001a\u00020?8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bB\u0010)\u001a\u0004\b@\u0010AR\u000e\u0010C\u001a\u00020=X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006q"}, d2 = {"Lcom/swipefwd/ui/home/settings/AccountActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/swipefwd/databinding/ActivityAccountBinding;", "callbackManager", "Lcom/facebook/CallbackManager;", "countryCode", "", "fb_id", "fb_name", "googleId", "googleName", "googleStartForResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "instaImagesList", "Ljava/util/ArrayList;", "instagramId", "instagramStartForResult", "instagramUserName", "isFacebookConnected", "", "isGoogleConnected", "isInstagramConnected", "isLinkedInConnected", "isoCode", "lastKey", "getLastKey", "()Ljava/lang/String;", "setLastKey", "(Ljava/lang/String;)V", "linkedInId", "linkedInLogInActivityContract", "kotlin.jvm.PlatformType", "linkedInName", "linkedInStartForResult", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/home/settings/AccountActivity;", "mActivity$delegate", "Lkotlin/Lazy;", "mSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "profileViewModel", "Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "getProfileViewModel", "()Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "profileViewModel$delegate", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "recoveryEmail", "settingsViewModel", "Lcom/swipefwd/ui/home/settings/SettingsViewModel;", "getSettingsViewModel", "()Lcom/swipefwd/ui/home/settings/SettingsViewModel;", "settingsViewModel$delegate", "socialFlag", "", "toolTipBinding", "Lcom/skydoves/balloon/Balloon;", "getToolTipBinding", "()Lcom/skydoves/balloon/Balloon;", "toolTipBinding$delegate", "userId", "disableDialog", "", "isChecked", "doFacebookLogin", "getFbFriendsList", "accessToken", "Lcom/facebook/AccessToken;", "getUserSettingsDetails", "handleSignInResult", "acct", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "init", "initObservers", "onActivityResult", "requestCode", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onLinkedInUserDetailsResult", "result", "Lcom/swipefwd/ui/auth/sociallogin/linkedin/LinkedInLoginResult;", "onLinkedLoginResult", "Landroidx/activity/result/ActivityResult;", "onResume", "openDisableConnectorDialog", "openDisableDaterDialog", "openFailDialog", "openUnlinkEmailDialog", "openUnlinkFbDialog", "openUnlinkGoogleDialog", "openUnlinkInstaDialog", "openUnlinkLinkedInDialog", "parseResponse", "friends", "Lorg/json/JSONObject;", "recoveryEmailId", "sendFacebookIds", "friendsArray", "Lorg/json/JSONArray;", "uploadInstagramImages", "userSettings", "key", "Companion", "app_debug"})
public final class AccountActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.home.settings.AccountActivity.Companion Companion = null;
    private static final java.lang.String TAG = "AccountActivity";
    private com.swipefwd.databinding.ActivityAccountBinding binding;
    private final kotlin.Lazy toolTipBinding$delegate = null;
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy settingsViewModel$delegate = null;
    private final kotlin.Lazy profileViewModel$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private boolean isFacebookConnected = false;
    private boolean isInstagramConnected = false;
    private boolean isLinkedInConnected = false;
    private int userId = 0;
    private com.facebook.CallbackManager callbackManager;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> instagramStartForResult;
    private java.util.ArrayList<java.lang.String> instaImagesList;
    private java.lang.String instagramId = "";
    private java.lang.String instagramUserName = "";
    private java.lang.String fb_name = "";
    private java.lang.String fb_id = "";
    private java.lang.String isoCode = "";
    private java.lang.String linkedInId = "";
    private java.lang.String linkedInName = "";
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> linkedInStartForResult;
    private com.google.android.gms.auth.api.signin.GoogleSignInClient mSignInClient;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> googleStartForResult;
    private java.lang.String googleId = "";
    private java.lang.String googleName = "";
    private boolean isGoogleConnected = false;
    private int socialFlag = 0;
    private java.lang.String countryCode = "";
    private java.lang.String recoveryEmail = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String lastKey = "";
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> linkedInLogInActivityContract;
    
    public AccountActivity() {
        super();
    }
    
    private final com.skydoves.balloon.Balloon getToolTipBinding() {
        return null;
    }
    
    private final com.swipefwd.ui.home.settings.AccountActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.ui.home.settings.SettingsViewModel getSettingsViewModel() {
        return null;
    }
    
    private final com.swipefwd.ui.updateuserprofile.UserProfileViewModel getProfileViewModel() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastKey() {
        return null;
    }
    
    public final void setLastKey(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * this function is called when linkedin account select for the login
     */
    private final void onLinkedLoginResult(androidx.activity.result.ActivityResult result) {
    }
    
    /**
     * this function is called on the linked in login result
     */
    private final void onLinkedInUserDetailsResult(com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInLoginResult result) {
    }
    
    private final void init() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void handleSignInResult(com.google.android.gms.auth.api.signin.GoogleSignInAccount acct) {
    }
    
    private final void doFacebookLogin() {
    }
    
    private final void getFbFriendsList(com.facebook.AccessToken accessToken) {
    }
    
    private final void parseResponse(org.json.JSONObject friends) {
    }
    
    private final void sendFacebookIds(org.json.JSONArray friendsArray) {
    }
    
    private final void openUnlinkFbDialog() {
    }
    
    private final void openDisableDaterDialog() {
    }
    
    private final void openDisableConnectorDialog() {
    }
    
    private final void openUnlinkInstaDialog() {
    }
    
    private final void openUnlinkLinkedInDialog() {
    }
    
    private final void openUnlinkGoogleDialog() {
    }
    
    private final void openUnlinkEmailDialog() {
    }
    
    private final void userSettings(java.lang.String key) {
    }
    
    private final void getUserSettingsDetails() {
    }
    
    private final void initObservers() {
    }
    
    private final void openFailDialog() {
    }
    
    private final void uploadInstagramImages() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void recoveryEmailId() {
    }
    
    private final void disableDialog(boolean isChecked) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/ui/home/settings/AccountActivity$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}