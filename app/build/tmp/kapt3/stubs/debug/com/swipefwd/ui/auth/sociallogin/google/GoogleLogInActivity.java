package com.swipefwd.ui.auth.sociallogin.google;

import java.lang.System;

/**
 * this activity is deal with the google login
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 12\u00020\u0001:\u00011B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\u0016\u0010\u001b\u001a\u00020\u00192\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u0019H\u0002J\b\u0010 \u001a\u00020\u0019H\u0002J\b\u0010!\u001a\u00020\u0019H\u0002J\u0012\u0010\"\u001a\u00020\u00192\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\u0014\u0010%\u001a\u00020\u00192\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\'H\u0002J\u0010\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020*H\u0002J\u0016\u0010+\u001a\u00020\u00192\f\u0010)\u001a\b\u0012\u0004\u0012\u00020-0,H\u0002J\b\u0010.\u001a\u00020\u0019H\u0002J\u0010\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u00020-H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\b\u001a\u0004\b\u0015\u0010\u0016\u00a8\u00062"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/google/GoogleLogInActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "googleLogInViewModel", "Lcom/swipefwd/ui/auth/sociallogin/google/GoogleLogInViewModel;", "getGoogleLogInViewModel", "()Lcom/swipefwd/ui/auth/sociallogin/google/GoogleLogInViewModel;", "googleLogInViewModel$delegate", "Lkotlin/Lazy;", "googleLoginActivityLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "mGoogleApiClient", "Lcom/google/android/gms/common/api/GoogleApiClient;", "getMGoogleApiClient", "()Lcom/google/android/gms/common/api/GoogleApiClient;", "setMGoogleApiClient", "(Lcom/google/android/gms/common/api/GoogleApiClient;)V", "progressLoader", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressLoader", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressLoader$delegate", "finish", "", "googleLoginApi", "handleGoogleSignInResult", "task", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "initGoogleLoginProcess", "initGoogleSignInClient", "onCancelled", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onError", "errorMessage", "", "onGoogleLoginActivityResult", "result", "Landroidx/activity/result/ActivityResult;", "onGoogleUserDetailsResult", "Lcom/swipefwd/base/ResultState;", "Lcom/swipefwd/ui/auth/sociallogin/google/GoogleUserDetails;", "openGoogleLoginDialog", "sendSuccessResult", "userDetails", "Companion", "app_debug"})
public final class GoogleLogInActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.auth.sociallogin.google.GoogleLogInActivity.Companion Companion = null;
    private static final java.lang.String TAG = "GoogleLogInActivity";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_RESULT_DATA = "google_login_result_data";
    private final kotlin.Lazy progressLoader$delegate = null;
    private final kotlin.Lazy googleLogInViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.common.api.GoogleApiClient mGoogleApiClient;
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> googleLoginActivityLauncher = null;
    
    public GoogleLogInActivity() {
        super();
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressLoader() {
        return null;
    }
    
    private final com.swipefwd.ui.auth.sociallogin.google.GoogleLogInViewModel getGoogleLogInViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.android.gms.common.api.GoogleApiClient getMGoogleApiClient() {
        return null;
    }
    
    public final void setMGoogleApiClient(@org.jetbrains.annotations.Nullable()
    com.google.android.gms.common.api.GoogleApiClient p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void finish() {
    }
    
    private final void googleLoginApi() {
    }
    
    /**
     * this function will be called on the launching of the activity
     * in this function first of all check for google play services availability
     * if available than proceed further, otherwise this activity will finish
     */
    private final void initGoogleLoginProcess() {
    }
    
    private final void initGoogleSignInClient() {
    }
    
    private final void openGoogleLoginDialog() {
    }
    
    private final void onGoogleLoginActivityResult(androidx.activity.result.ActivityResult result) {
    }
    
    private final void handleGoogleSignInResult(com.google.android.gms.tasks.Task<com.google.android.gms.auth.api.signin.GoogleSignInAccount> task) {
    }
    
    private final void onGoogleUserDetailsResult(com.swipefwd.base.ResultState<com.swipefwd.ui.auth.sociallogin.google.GoogleUserDetails> result) {
    }
    
    private final void sendSuccessResult(com.swipefwd.ui.auth.sociallogin.google.GoogleUserDetails userDetails) {
    }
    
    private final void onCancelled() {
    }
    
    private final void onError(java.lang.String errorMessage) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/google/GoogleLogInActivity$Companion;", "", "()V", "EXTRA_RESULT_DATA", "", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}