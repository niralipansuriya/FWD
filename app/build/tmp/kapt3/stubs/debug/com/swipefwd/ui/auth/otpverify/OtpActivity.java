package com.swipefwd.ui.auth.otpverify;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 v2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001vB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u0002072\u0006\u00109\u001a\u00020:H\u0002J\b\u0010;\u001a\u000207H\u0002J\b\u0010<\u001a\u000207H\u0002J\b\u0010=\u001a\u000207H\u0002J\b\u0010>\u001a\u000207H\u0002J\b\u0010?\u001a\u000207H\u0016J\u0012\u0010@\u001a\u0002072\b\u0010A\u001a\u0004\u0018\u00010BH\u0014J\b\u0010C\u001a\u00020\u0002H\u0016J\b\u0010D\u001a\u000207H\u0014J\u0010\u0010E\u001a\u0002072\u0006\u00109\u001a\u00020:H\u0002J\u0016\u0010F\u001a\u0002072\f\u0010G\u001a\b\u0012\u0004\u0012\u0002070HH\u0002J\b\u0010I\u001a\u000207H\u0002J\u0010\u0010J\u001a\u0002072\u0006\u0010K\u001a\u00020LH\u0002J\b\u0010M\u001a\u000207H\u0002J\b\u0010N\u001a\u000207H\u0014J\u0012\u0010O\u001a\u0002072\b\u0010P\u001a\u0004\u0018\u00010QH\u0002J\u0016\u0010R\u001a\u0002072\f\u0010G\u001a\b\u0012\u0004\u0012\u00020S0HH\u0002J\b\u0010T\u001a\u000207H\u0014J\b\u0010U\u001a\u000207H\u0014J\u0010\u0010V\u001a\u0002072\u0006\u0010W\u001a\u00020:H\u0002J\b\u0010X\u001a\u000207H\u0002J\b\u0010Y\u001a\u000207H\u0002J\u0012\u0010Z\u001a\u0002072\b\u0010[\u001a\u0004\u0018\u00010:H\u0002J\b\u0010\\\u001a\u000207H\u0002J0\u0010]\u001a\u0002072\u0006\u0010^\u001a\u00020\u00052\u0006\u0010_\u001a\u00020:2\u0006\u0010`\u001a\u00020:2\u0006\u0010a\u001a\u00020:2\u0006\u0010b\u001a\u00020:H\u0002J\b\u0010c\u001a\u000207H\u0002J\b\u0010d\u001a\u000207H\u0002J\u0006\u0010e\u001a\u000207J\u0010\u0010f\u001a\u0002072\u0006\u0010g\u001a\u00020:H\u0002J\u0010\u0010h\u001a\u0002072\u0006\u0010i\u001a\u00020jH\u0002J\u0010\u0010k\u001a\u0002072\u0006\u0010l\u001a\u00020\nH\u0002J\b\u0010m\u001a\u000207H\u0002J\b\u0010n\u001a\u000207H\u0002J\u0016\u0010o\u001a\u0002072\f\u0010G\u001a\b\u0012\u0004\u0012\u00020S0HH\u0002J\u0016\u0010p\u001a\u0002072\f\u0010G\u001a\b\u0012\u0004\u0012\u00020S0HH\u0002J\u0018\u0010q\u001a\u0002072\u0006\u0010r\u001a\u00020\u00052\u0006\u0010s\u001a\u00020:H\u0002J\b\u0010t\u001a\u000207H\u0002J\b\u0010u\u001a\u000207H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\"\u001a\u00020#8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b&\u0010\u0011\u001a\u0004\b$\u0010%R\u001b\u0010\'\u001a\u00020(8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b+\u0010\u0011\u001a\u0004\b)\u0010*R\u000e\u0010,\u001a\u00020-X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010.\u001a\u0010\u0012\f\u0012\n 1*\u0004\u0018\u000100000/X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006w"}, d2 = {"Lcom/swipefwd/ui/auth/otpverify/OtpActivity;", "Lcom/swipefwd/base/BaseActivity;", "Lcom/swipefwd/databinding/OtpActivityBinding;", "()V", "SMS_VERIFICATION_REQUEST", "", "dialogs", "Ljava/util/Vector;", "Landroid/app/Dialog;", "isFromDeepLink", "", "isRecoveryFlow", "isWrongOTPEntered", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/auth/otpverify/OtpActivity;", "mActivity$delegate", "Lkotlin/Lazy;", "mAnimation", "Lcom/swipefwd/animations/SlideView;", "mAnimationShake", "Landroid/view/animation/Animation;", "getMAnimationShake", "()Landroid/view/animation/Animation;", "setMAnimationShake", "(Landroid/view/animation/Animation;)V", "mySMSBroadcastReceiver", "Lcom/swipefwd/utils/MySMSBroadcastReceiver;", "getMySMSBroadcastReceiver", "()Lcom/swipefwd/utils/MySMSBroadcastReceiver;", "setMySMSBroadcastReceiver", "(Lcom/swipefwd/utils/MySMSBroadcastReceiver;)V", "otpListener", "Lcom/swipefwd/utils/otpView/OTPListener;", "otpViewModel", "Lcom/swipefwd/ui/auth/otpverify/OtpViewModel;", "getOtpViewModel", "()Lcom/swipefwd/ui/auth/otpverify/OtpViewModel;", "otpViewModel$delegate", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "smsClient", "Lcom/google/android/gms/auth/api/phone/SmsRetrieverClient;", "smsLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "smsVerificationBroadcastReceiver", "Landroid/content/BroadcastReceiver;", "snackbar", "Lcom/google/android/material/snackbar/Snackbar;", "getOtp", "", "getOtpFromMessage", "message", "", "init", "initSmsListener", "loginUser", "moveToNext", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateViewBinding", "onDestroy", "onError", "onLogOutUser", "result", "Lcom/swipefwd/base/ResultState;", "onLoginErrorResult", "onLoginResult", "loginModel", "Lcom/swipefwd/data/models/LoginResponseModel;", "onNextButtonClick", "onPause", "onRecoverResult", "it", "Lcom/swipefwd/data/models/RecoverAccountModel;", "onResendOtpResult", "LOTPModel;", "onStart", "onStop", "onTimerRefreshed", "timeValue", "onTimerStopped", "onUserRegistrationRequired", "onVerifyOtpError", "error", "openAddedUserDialog", "openAlternateLoginDialog", "signUpType", "phone", "google", "faceBook", "linkedIn", "openTimeOutDialog", "recoverAccount", "saveTimeoutOtp", "setOtpCode", "code", "setupFullHeight", "bottomSheet", "Landroid/view/View;", "showLoading", "isLoading", "startOTPBroadCastListener", "startSmsListener", "submitOtpEmailResult", "submitOtpResult", "userSettings", "userId", "token", "verifyOTP", "verifyOTPEmail", "Companion", "app_debug"})
public final class OtpActivity extends com.swipefwd.base.BaseActivity<com.swipefwd.databinding.OtpActivityBinding> {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.auth.otpverify.OtpActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KEY_SNACK_BAR_MESSAGE = "snackbarMsg";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KEY_COUNTRY_CODE = "countryCode";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KEY_PHONE_NUMBER = "phoneNumber";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KEY_EMAIL = "email";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KEY_ISO_CODE = "isoCode";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KEY_OTP = "otp";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KEY_FIRST_NAME = "extra.key.firstname";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String IS_FROM_DEEP_LINK = "fromDeepLink";
    private final kotlin.Lazy otpViewModel$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private final kotlin.Lazy mActivity$delegate = null;
    private final java.util.Vector<android.app.Dialog> dialogs = null;
    private boolean isFromDeepLink = false;
    public android.view.animation.Animation mAnimationShake;
    private boolean isWrongOTPEntered = false;
    private final int SMS_VERIFICATION_REQUEST = 2;
    private final com.swipefwd.animations.SlideView mAnimation = null;
    private com.google.android.material.snackbar.Snackbar snackbar;
    private boolean isRecoveryFlow = false;
    @org.jetbrains.annotations.Nullable()
    private com.swipefwd.utils.MySMSBroadcastReceiver mySMSBroadcastReceiver;
    private com.google.android.gms.auth.api.phone.SmsRetrieverClient smsClient;
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> smsLauncher = null;
    private final android.content.BroadcastReceiver smsVerificationBroadcastReceiver = null;
    private final com.swipefwd.utils.otpView.OTPListener otpListener = null;
    
    public OtpActivity() {
        super();
    }
    
    private final com.swipefwd.ui.auth.otpverify.OtpViewModel getOtpViewModel() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    private final com.swipefwd.ui.auth.otpverify.OtpActivity getMActivity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.animation.Animation getMAnimationShake() {
        return null;
    }
    
    public final void setMAnimationShake(@org.jetbrains.annotations.NotNull()
    android.view.animation.Animation p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.utils.MySMSBroadcastReceiver getMySMSBroadcastReceiver() {
        return null;
    }
    
    public final void setMySMSBroadcastReceiver(@org.jetbrains.annotations.Nullable()
    com.swipefwd.utils.MySMSBroadcastReceiver p0) {
    }
    
    private final void initSmsListener() {
    }
    
    private final void getOtpFromMessage(java.lang.String message) {
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.databinding.OtpActivityBinding onCreateViewBinding() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void onError(java.lang.String message) {
    }
    
    private final void onUserRegistrationRequired() {
    }
    
    private final void onRecoverResult(com.swipefwd.data.models.RecoverAccountModel it) {
    }
    
    private final void onLogOutUser(com.swipefwd.base.ResultState<kotlin.Unit> result) {
    }
    
    private final void showLoading(boolean isLoading) {
    }
    
    private final void onVerifyOtpError(java.lang.String error) {
    }
    
    private final void onNextButtonClick() {
    }
    
    private final void onTimerRefreshed(java.lang.String timeValue) {
    }
    
    private final void onTimerStopped() {
    }
    
    private final void openAlternateLoginDialog(int signUpType, java.lang.String phone, java.lang.String google, java.lang.String faceBook, java.lang.String linkedIn) {
    }
    
    private final void submitOtpResult(com.swipefwd.base.ResultState<OTPModel> result) {
    }
    
    private final void submitOtpEmailResult(com.swipefwd.base.ResultState<OTPModel> result) {
    }
    
    private final void onResendOtpResult(com.swipefwd.base.ResultState<OTPModel> result) {
    }
    
    private final void onLoginResult(com.swipefwd.data.models.LoginResponseModel loginModel) {
    }
    
    private final void userSettings(int userId, java.lang.String token) {
    }
    
    private final void onLoginErrorResult() {
    }
    
    private final void recoverAccount() {
    }
    
    private final void loginUser() {
    }
    
    private final void moveToNext() {
    }
    
    private final void getOtp() {
    }
    
    private final void verifyOTP() {
    }
    
    private final void verifyOTPEmail() {
    }
    
    private final void init() {
    }
    
    private final void openTimeOutDialog() {
    }
    
    private final void setupFullHeight(android.view.View bottomSheet) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    private final void setOtpCode(java.lang.String code) {
    }
    
    private final void startSmsListener() {
    }
    
    private final void startOTPBroadCastListener() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public final void saveTimeoutOtp() {
    }
    
    private final void openAddedUserDialog() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/swipefwd/ui/auth/otpverify/OtpActivity$Companion;", "", "()V", "EXTRA_KEY_COUNTRY_CODE", "", "EXTRA_KEY_EMAIL", "EXTRA_KEY_FIRST_NAME", "EXTRA_KEY_ISO_CODE", "EXTRA_KEY_OTP", "EXTRA_KEY_PHONE_NUMBER", "EXTRA_KEY_SNACK_BAR_MESSAGE", "IS_FROM_DEEP_LINK", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}