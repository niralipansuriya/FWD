package com.swipefwd.ui.auth.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0011\u0010Y\u001a\u00020\u0017H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010ZJ\u0006\u0010[\u001a\u00020\u0017J\u0012\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u001eJ\u0010\u0010]\u001a\u00020\u00172\u0006\u0010^\u001a\u00020_H\u0002J\u0019\u0010`\u001a\u00020\u00172\u0006\u0010a\u001a\u00020\u0015H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010bJ\u0006\u0010c\u001a\u00020\u0017J\'\u0010d\u001a\u00020\u0017\"\u0004\b\u0000\u0010e2\f\u0010f\u001a\b\u0012\u0004\u0012\u0002He0g2\u0006\u0010h\u001a\u0002He\u00a2\u0006\u0002\u0010iJ\'\u0010j\u001a\u00020\u0017\"\u0004\b\u0000\u0010e2\f\u0010f\u001a\b\u0012\u0004\u0012\u0002He0g2\u0006\u0010h\u001a\u0002He\u00a2\u0006\u0002\u0010iJ\b\u0010k\u001a\u00020\u0017H\u0002J\u0016\u0010l\u001a\u00020\u00172\u0006\u0010m\u001a\u00020\u000b2\u0006\u0010n\u001a\u00020oJ\u0012\u0010p\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00100\u001eJ\f\u0010q\u001a\b\u0012\u0004\u0012\u00020\u00170\u001eR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00100\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R \u0010#\u001a\b\u0012\u0004\u0012\u00020\r0\u001eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R \u0010&\u001a\b\u0012\u0004\u0012\u00020\r0\u001eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010 \"\u0004\b(\u0010\"R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000b0*X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001a\"\u0004\b-\u0010\u001cR\u001a\u0010.\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001a\"\u0004\b5\u0010\u001cR\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\u000b07\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010:\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u001a\"\u0004\b<\u0010\u001cR\u0010\u0010=\u001a\u0004\u0018\u00010>X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010?\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u001a\"\u0004\bA\u0010\u001cR \u0010B\u001a\b\u0012\u0004\u0012\u00020\u00130\u001eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010 \"\u0004\bD\u0010\"R\u001a\u0010E\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u001a\"\u0004\bG\u0010\u001cR\u001a\u0010H\u001a\u00020IX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u001a\u0010N\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u001a\"\u0004\bP\u0010\u001cR\u000e\u0010Q\u001a\u00020RX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010S\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bT\u0010\u001a\"\u0004\bU\u0010\u001cR\u001a\u0010V\u001a\u00020IX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bW\u0010K\"\u0004\bX\u0010M\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006r"}, d2 = {"Lcom/swipefwd/ui/auth/login/LoginViewModel;", "Lcom/swipefwd/base/AppBaseViewModel;", "appRepository", "Lcom/swipefwd/data/source/AppRepository;", "internalAppDataStore", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "appDatabase", "Lcom/swipefwd/utils/AppDatabase;", "(Lcom/swipefwd/data/source/AppRepository;Lcom/swipefwd/utils/datastore/InternalAppDataStore;Lcom/swipefwd/utils/AppDatabase;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "_facebookData", "Lcom/swipefwd/data/models/CommonResponseModel;", "_facebookError", "_loginUserData", "Lcom/swipefwd/base/ResultState;", "Lcom/swipefwd/ui/auth/LogInNavigationModel;", "_showLoading", "", "_socialLoginData", "LOTPModel;", "_userRegistrationRequired", "", "birthDate", "getBirthDate", "()Ljava/lang/String;", "setBirthDate", "(Ljava/lang/String;)V", "errorMessage", "Landroidx/lifecycle/LiveData;", "getErrorMessage", "()Landroidx/lifecycle/LiveData;", "setErrorMessage", "(Landroidx/lifecycle/LiveData;)V", "facebookData", "getFacebookData", "setFacebookData", "facebookError", "getFacebookError", "setFacebookError", "facebookIds", "Ljava/util/ArrayList;", "firstName", "getFirstName", "setFirstName", "friendsDenied", "getFriendsDenied", "()Z", "setFriendsDenied", "(Z)V", "gender", "getGender", "setGender", "getTimeoutOTP", "Lkotlinx/coroutines/flow/Flow;", "getGetTimeoutOTP", "()Lkotlinx/coroutines/flow/Flow;", "lastName", "getLastName", "setLastName", "loginUserJob", "Lkotlinx/coroutines/Job;", "originalProfile", "getOriginalProfile", "setOriginalProfile", "showLoading", "getShowLoading", "setShowLoading", "socialEmail", "getSocialEmail", "setSocialEmail", "socialFlag", "", "getSocialFlag", "()I", "setSocialFlag", "(I)V", "socialId", "getSocialId", "setSocialId", "socialMediaModel", "Lcom/swipefwd/data/models/SocialMediaUserModel;", "socialType", "getSocialType", "setSocialType", "socialTypeLogin", "getSocialTypeLogin", "setSocialTypeLogin", "clearSharedPreferences", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginUser", "loginUserResult", "onLoginError", "loginModel", "Lcom/swipefwd/data/models/LoginResponseModel;", "onSocialResult", "otpModel", "(LOTPModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetViewModelData", "saveLoginData", "T", "key", "Landroidx/datastore/preferences/core/Preferences$Key;", "value", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;)V", "savePreference", "saveSocialUser", "sendFacebookIds", "token", "jsonObject", "Lcom/google/gson/JsonObject;", "socialLoginResult", "userRegistrationRequired", "app_debug"})
public final class LoginViewModel extends com.swipefwd.base.AppBaseViewModel {
    private final com.swipefwd.data.source.AppRepository appRepository = null;
    private final com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStore = null;
    private final com.swipefwd.utils.AppDatabase appDatabase = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String socialId = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String socialEmail = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String firstName = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String lastName = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String birthDate = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String gender = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String socialType = "";
    private int socialTypeLogin = 0;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String originalProfile = "";
    private int socialFlag = 0;
    private boolean friendsDenied = false;
    private com.swipefwd.data.models.SocialMediaUserModel socialMediaModel;
    private java.util.ArrayList<java.lang.String> facebookIds;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getTimeoutOTP = null;
    private androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.lang.String> errorMessage;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _showLoading;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.lang.Boolean> showLoading;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.CommonResponseModel> _facebookData;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.swipefwd.data.models.CommonResponseModel> facebookData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.CommonResponseModel> _facebookError;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.swipefwd.data.models.CommonResponseModel> facebookError;
    
    /**
     * ##############################################################
     */
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<com.swipefwd.ui.auth.LogInNavigationModel>> _loginUserData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<OTPModel>> _socialLoginData;
    private androidx.lifecycle.MutableLiveData<kotlin.Unit> _userRegistrationRequired;
    private kotlinx.coroutines.Job loginUserJob;
    
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.source.AppRepository appRepository, @org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStore, @org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.AppDatabase appDatabase) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSocialId() {
        return null;
    }
    
    public final void setSocialId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSocialEmail() {
        return null;
    }
    
    public final void setSocialEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFirstName() {
        return null;
    }
    
    public final void setFirstName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastName() {
        return null;
    }
    
    public final void setLastName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBirthDate() {
        return null;
    }
    
    public final void setBirthDate(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGender() {
        return null;
    }
    
    public final void setGender(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSocialType() {
        return null;
    }
    
    public final void setSocialType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getSocialTypeLogin() {
        return 0;
    }
    
    public final void setSocialTypeLogin(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOriginalProfile() {
        return null;
    }
    
    public final void setOriginalProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getSocialFlag() {
        return 0;
    }
    
    public final void setSocialFlag(int p0) {
    }
    
    public final boolean getFriendsDenied() {
        return false;
    }
    
    public final void setFriendsDenied(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetTimeoutOTP() {
        return null;
    }
    
    public final <T extends java.lang.Object>void saveLoginData(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key, T value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
    
    public final void setErrorMessage(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getShowLoading() {
        return null;
    }
    
    public final void setShowLoading(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.CommonResponseModel> getFacebookData() {
        return null;
    }
    
    public final void setFacebookData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.swipefwd.data.models.CommonResponseModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.CommonResponseModel> getFacebookError() {
        return null;
    }
    
    public final void setFacebookError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.swipefwd.data.models.CommonResponseModel> p0) {
    }
    
    public final <T extends java.lang.Object>void savePreference(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key, T value) {
    }
    
    public final void sendFacebookIds(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<com.swipefwd.ui.auth.LogInNavigationModel>> loginUserResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<OTPModel>> socialLoginResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> userRegistrationRequired() {
        return null;
    }
    
    /**
     * this function is called on facebook,google and linked in login
     * if user logged in successfully then user will redirect to the dashboard screen
     * if there is response code 400 then it means user not registered and user will goes to the
     * PhoneNumber screen to start the new registration process
     */
    public final void loginUser() {
    }
    
    private final java.lang.Object onSocialResult(OTPModel otpModel, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void saveSocialUser() {
    }
    
    private final void onLoginError(com.swipefwd.data.models.LoginResponseModel loginModel) {
    }
    
    private final java.lang.Object clearSharedPreferences(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void resetViewModelData() {
    }
}