package com.swipefwd.ui.auth.register;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020LJ\u001a\u0010M\u001a\u00020J\"\u0004\b\u0000\u0010N2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002HN0PJ\'\u0010Q\u001a\u00020J\"\u0004\b\u0000\u0010N2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002HN0P2\u0006\u0010R\u001a\u0002HN\u00a2\u0006\u0002\u0010SJ\u0016\u0010T\u001a\u00020J2\u0006\u0010U\u001a\u00020\t2\u0006\u0010K\u001a\u00020LJ\u000e\u0010V\u001a\u00020J2\u0006\u0010W\u001a\u00020XJ\u001e\u0010Y\u001a\u00020J2\u0006\u0010U\u001a\u00020\t2\u0006\u0010Z\u001a\u00020X2\u0006\u0010K\u001a\u00020LR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0$\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020\t0$\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0$\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\t0$\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010&R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0$\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010&R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00130$\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010&R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00130$\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010&R\u001c\u00101\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R \u00106\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001a\"\u0004\b8\u0010\u001cR \u00109\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u001a\"\u0004\b;\u0010\u001cR \u0010<\u001a\b\u0012\u0004\u0012\u00020\u00130\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u001a\"\u0004\b>\u0010\u001cR\u0017\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00130$\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010&R\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020\t0$\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010&R \u0010C\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u001a\"\u0004\bE\u0010\u001cR \u0010F\u001a\b\u0012\u0004\u0012\u00020\u00150\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u001a\"\u0004\bH\u0010\u001c\u00a8\u0006["}, d2 = {"Lcom/swipefwd/ui/auth/register/UserInfoViewModel;", "Lcom/swipefwd/ui/activity/BaseViewModel;", "context", "Landroid/content/Context;", "appRepository", "Lcom/swipefwd/data/source/AppRepository;", "(Landroid/content/Context;Lcom/swipefwd/data/source/AppRepository;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "_facebookData", "Lcom/swipefwd/data/models/CommonResponseModel;", "_facebookError", "_registerData", "Lcom/swipefwd/data/models/LoginResponseModel;", "_registerError", "_settingsData", "_settingsError", "_showLoading", "", "_userTypeData", "Lcom/swipefwd/data/models/UserTypeModel;", "_userTypeDataError", "errorMessage", "Landroidx/lifecycle/LiveData;", "getErrorMessage", "()Landroidx/lifecycle/LiveData;", "setErrorMessage", "(Landroidx/lifecycle/LiveData;)V", "fwdDataStore", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "getFwdDataStore", "()Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "fwdDataStore$delegate", "Lkotlin/Lazy;", "getAuthToken", "Lkotlinx/coroutines/flow/Flow;", "getGetAuthToken", "()Lkotlinx/coroutines/flow/Flow;", "getCountryCode", "getGetCountryCode", "getCurrentScreen", "getGetCurrentScreen", "getFbIds", "getGetFbIds", "getPhoneNumber", "getGetPhoneNumber", "isConnectorFaceBookPermissionDenied", "isFaceBookPermissionDenied", "otpCode", "getOtpCode", "()Ljava/lang/String;", "setOtpCode", "(Ljava/lang/String;)V", "registerData", "getRegisterData", "setRegisterData", "registerError", "getRegisterError", "setRegisterError", "showLoading", "getShowLoading", "setShowLoading", "showNotificationDialog", "getShowNotificationDialog", "socialMediaUser", "getSocialMediaUser", "userTypeData", "getUserTypeData", "setUserTypeData", "userTypeDataError", "getUserTypeDataError", "setUserTypeDataError", "registerUser", "", "jsonObject", "Lcom/google/gson/JsonObject;", "removePreference", "T", "key", "Landroidx/datastore/preferences/core/Preferences$Key;", "saveUserInfo", "value", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;)V", "sendFacebookIds", "token", "setUserAccountType", "userType", "", "userSettingsUpdate", "id", "app_debug"})
public final class UserInfoViewModel extends com.swipefwd.ui.activity.BaseViewModel {
    private final com.swipefwd.data.source.AppRepository appRepository = null;
    private final kotlin.Lazy fwdDataStore$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> socialMediaUser = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getAuthToken = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getCurrentScreen = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getPhoneNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getCountryCode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isFaceBookPermissionDenied = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isConnectorFaceBookPermissionDenied = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getFbIds = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> showNotificationDialog = null;
    private androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.lang.String> errorMessage;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _showLoading;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.lang.Boolean> showLoading;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.LoginResponseModel> _registerData;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.swipefwd.data.models.LoginResponseModel> registerData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.UserTypeModel> _userTypeData;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.swipefwd.data.models.UserTypeModel> userTypeData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.LoginResponseModel> _registerError;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.swipefwd.data.models.LoginResponseModel> registerError;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.UserTypeModel> _userTypeDataError;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.swipefwd.data.models.UserTypeModel> userTypeDataError;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.CommonResponseModel> _settingsData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.CommonResponseModel> _settingsError;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.CommonResponseModel> _facebookData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.CommonResponseModel> _facebookError;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String otpCode;
    
    public UserInfoViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.source.AppRepository appRepository) {
        super(null);
    }
    
    private final com.swipefwd.utils.datastore.InternalAppDataStore getFwdDataStore() {
        return null;
    }
    
    public final <T extends java.lang.Object>void saveUserInfo(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key, T value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getSocialMediaUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetAuthToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetCurrentScreen() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetPhoneNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetCountryCode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isFaceBookPermissionDenied() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isConnectorFaceBookPermissionDenied() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetFbIds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getShowNotificationDialog() {
        return null;
    }
    
    public final <T extends java.lang.Object>void removePreference(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key) {
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
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.LoginResponseModel> getRegisterData() {
        return null;
    }
    
    public final void setRegisterData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.swipefwd.data.models.LoginResponseModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.UserTypeModel> getUserTypeData() {
        return null;
    }
    
    public final void setUserTypeData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.swipefwd.data.models.UserTypeModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.LoginResponseModel> getRegisterError() {
        return null;
    }
    
    public final void setRegisterError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.swipefwd.data.models.LoginResponseModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.UserTypeModel> getUserTypeDataError() {
        return null;
    }
    
    public final void setUserTypeDataError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.swipefwd.data.models.UserTypeModel> p0) {
    }
    
    public final void registerUser(@org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject) {
    }
    
    public final void userSettingsUpdate(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int id, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject) {
    }
    
    public final void setUserAccountType(int userType) {
    }
    
    public final void sendFacebookIds(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOtpCode() {
        return null;
    }
    
    public final void setOtpCode(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
}