package com.swipefwd.ui.splash;

import java.lang.System;

/**
 * this is splash activity opened on the launch of the app
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 C2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001CB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020-H\u0002J\b\u0010/\u001a\u00020-H\u0002J\u0012\u00100\u001a\u00020-2\b\u00101\u001a\u0004\u0018\u000102H\u0014J\b\u00103\u001a\u00020\u0002H\u0016J\b\u00104\u001a\u00020-H\u0014J\b\u00105\u001a\u00020-H\u0014J\b\u00106\u001a\u00020-H\u0014J\u0012\u00107\u001a\u00020-2\b\u00108\u001a\u0004\u0018\u00010\u0005H\u0002J\u0016\u00109\u001a\u00020-2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000e0;H\u0002J\u0010\u0010<\u001a\u00020-2\u0006\u0010=\u001a\u00020\u000eH\u0002J\b\u0010>\u001a\u00020-H\u0002J\u0018\u0010?\u001a\u00020-*\u00020@2\n\u0010A\u001a\u0006\u0012\u0002\b\u00030BH\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010\u001e\u001a\u0004\b!\u0010\"R\u0016\u0010$\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\'\u001a\u00020(8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b+\u0010\u001e\u001a\u0004\b)\u0010*\u00a8\u0006D"}, d2 = {"Lcom/swipefwd/ui/splash/SplashActivity;", "Lcom/swipefwd/base/BaseActivity;", "Lcom/swipefwd/databinding/SplashActivityBinding;", "()V", "accountType", "", "getAccountType", "()Ljava/lang/String;", "setAccountType", "(Ljava/lang/String;)V", "deviceID", "getDeviceID", "setDeviceID", "isNewUserFromEmail", "", "()Z", "setNewUserFromEmail", "(Z)V", "isUnSubscribeFlow", "setUnSubscribeFlow", "isUserInactive", "()Ljava/lang/Boolean;", "setUserInactive", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "profileViewModel", "Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "getProfileViewModel", "()Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "profileViewModel$delegate", "Lkotlin/Lazy;", "splashViewModel", "Lcom/swipefwd/ui/splash/SplashViewModel;", "getSplashViewModel", "()Lcom/swipefwd/ui/splash/SplashViewModel;", "splashViewModel$delegate", "startForResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "swipeViewModel", "Lcom/swipefwd/ui/swiping/dater_both/SwipeProfileViewModel;", "getSwipeViewModel", "()Lcom/swipefwd/ui/swiping/dater_both/SwipeProfileViewModel;", "swipeViewModel$delegate", "applyScreenTransitions", "", "checkForUpdates", "getDeepLinkData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateViewBinding", "onDestroy", "onResume", "onStart", "onUpdateCheckError", "error", "onUpdateRequiredResult", "result", "Lcom/swipefwd/base/ResultState;", "onUpdateRequiredStatus", "status", "redirectScreen", "nextActivity", "Landroid/app/Activity;", "cls", "Ljava/lang/Class;", "Companion", "app_debug"})
public final class SplashActivity extends com.swipefwd.base.BaseActivity<com.swipefwd.databinding.SplashActivityBinding> {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.splash.SplashActivity.Companion Companion = null;
    private static final long SPLASH_SCREEN_DELAY = 8000L;
    private static final java.lang.String TAG = "SplashActivity";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String deviceID = "";
    private boolean isNewUserFromEmail = false;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Boolean isUserInactive;
    private boolean isUnSubscribeFlow = false;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> startForResult;
    private final kotlin.Lazy splashViewModel$delegate = null;
    private final kotlin.Lazy profileViewModel$delegate = null;
    private final kotlin.Lazy swipeViewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String accountType = "";
    
    public SplashActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDeviceID() {
        return null;
    }
    
    public final void setDeviceID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final boolean isNewUserFromEmail() {
        return false;
    }
    
    public final void setNewUserFromEmail(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean isUserInactive() {
        return null;
    }
    
    public final void setUserInactive(@org.jetbrains.annotations.Nullable()
    java.lang.Boolean p0) {
    }
    
    public final boolean isUnSubscribeFlow() {
        return false;
    }
    
    public final void setUnSubscribeFlow(boolean p0) {
    }
    
    private final com.swipefwd.ui.splash.SplashViewModel getSplashViewModel() {
        return null;
    }
    
    private final com.swipefwd.ui.updateuserprofile.UserProfileViewModel getProfileViewModel() {
        return null;
    }
    
    private final com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel getSwipeViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAccountType() {
        return null;
    }
    
    public final void setAccountType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.databinding.SplashActivityBinding onCreateViewBinding() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    private final void getDeepLinkData() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void onUpdateRequiredResult(com.swipefwd.base.ResultState<java.lang.Boolean> result) {
    }
    
    private final void onUpdateRequiredStatus(boolean status) {
    }
    
    private final void onUpdateCheckError(java.lang.String error) {
    }
    
    private final void checkForUpdates() {
    }
    
    private final void redirectScreen() {
    }
    
    private final void nextActivity(android.app.Activity $this$nextActivity, java.lang.Class<?> cls) {
    }
    
    private final void applyScreenTransitions() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/splash/SplashActivity$Companion;", "", "()V", "SPLASH_SCREEN_DELAY", "", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}