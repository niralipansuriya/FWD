package com.swipefwd.ui.auth.sociallogin.facebook;

import java.lang.System;

/**
 * this activity is deal with the fetching the facebook profile details
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\"\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\u0012\u0010\u001f\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0014\u0010\"\u001a\u00020\u00172\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0015H\u0002J\u0016\u0010$\u001a\u00020\u00172\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&H\u0002J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\'H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/facebook/FacebookLoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "callbackManager", "Lcom/facebook/CallbackManager;", "facebookCallback", "Lcom/facebook/FacebookCallback;", "Lcom/facebook/login/LoginResult;", "facebookLoginViewModel", "Lcom/swipefwd/ui/auth/sociallogin/facebook/FacebookLoginViewModel;", "getFacebookLoginViewModel", "()Lcom/swipefwd/ui/auth/sociallogin/facebook/FacebookLoginViewModel;", "facebookLoginViewModel$delegate", "Lkotlin/Lazy;", "progressLoader", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressLoader", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressLoader$delegate", "requestedPermission", "", "", "finish", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCancelled", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onError", "errorMessage", "onFacebookUserDetailsResult", "result", "Lcom/swipefwd/base/ResultState;", "Lcom/swipefwd/ui/auth/sociallogin/facebook/FacebookUserDetails;", "sendSuccessResult", "userDetails", "Companion", "app_debug"})
public final class FacebookLoginActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.auth.sociallogin.facebook.FacebookLoginActivity.Companion Companion = null;
    private static final java.lang.String TAG = "FacebookLoginActivity";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KEY_SOCIAL_USER_DETAILS_MODEL = "social_user_details";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_RESULT_DATA = "facebook_login_result_data";
    private final kotlin.Lazy facebookLoginViewModel$delegate = null;
    private com.facebook.CallbackManager callbackManager;
    private final kotlin.Lazy progressLoader$delegate = null;
    private final java.util.List<java.lang.String> requestedPermission = null;
    private final com.facebook.FacebookCallback<com.facebook.login.LoginResult> facebookCallback = null;
    
    public FacebookLoginActivity() {
        super();
    }
    
    private final com.swipefwd.ui.auth.sociallogin.facebook.FacebookLoginViewModel getFacebookLoginViewModel() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressLoader() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void finish() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void onFacebookUserDetailsResult(com.swipefwd.base.ResultState<com.swipefwd.ui.auth.sociallogin.facebook.FacebookUserDetails> result) {
    }
    
    private final void sendSuccessResult(com.swipefwd.ui.auth.sociallogin.facebook.FacebookUserDetails userDetails) {
    }
    
    private final void onCancelled() {
    }
    
    private final void onError(java.lang.String errorMessage) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/facebook/FacebookLoginActivity$Companion;", "", "()V", "EXTRA_KEY_SOCIAL_USER_DETAILS_MODEL", "", "EXTRA_RESULT_DATA", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}