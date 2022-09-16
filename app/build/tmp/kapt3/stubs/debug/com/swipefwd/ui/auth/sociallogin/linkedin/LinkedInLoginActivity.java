package com.swipefwd.ui.auth.sociallogin.linkedin;

import java.lang.System;

/**
 * this activity is deal with the linkedin  login
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 &2\u00020\u0001:\u0001&B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0014\u0010\u0019\u001a\u00020\u00132\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\u0016\u0010\u001c\u001a\u00020\u00132\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0002J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u001fH\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\'"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/linkedin/LinkedInLoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "linkedInWebLoginActivityLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "linkedLoginViewModel", "Lcom/swipefwd/ui/auth/sociallogin/linkedin/LinkedInLoginViewModel;", "getLinkedLoginViewModel", "()Lcom/swipefwd/ui/auth/sociallogin/linkedin/LinkedInLoginViewModel;", "linkedLoginViewModel$delegate", "Lkotlin/Lazy;", "progressLoader", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressLoader", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressLoader$delegate", "finish", "", "initLoginProcess", "onCancelled", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onError", "errorMessage", "", "onLinkedInUserDetailsResult", "result", "Lcom/swipefwd/base/ResultState;", "Lcom/swipefwd/ui/auth/sociallogin/linkedin/LinkedInUserDetails;", "onLinkedInWebLoginDetailsResult", "Lcom/swipefwd/ui/auth/sociallogin/linkedin/LinkedInWebActivityResult;", "onLinkedInWebLoginResult", "Landroidx/activity/result/ActivityResult;", "sendSuccessResult", "userDetails", "Companion", "app_debug"})
public final class LinkedInLoginActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInLoginActivity.Companion Companion = null;
    private static final java.lang.String TAG = "LinkedInLoginActivity";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_RESULT_DATA = "linked_in_login_result_data";
    private final kotlin.Lazy progressLoader$delegate = null;
    private final kotlin.Lazy linkedLoginViewModel$delegate = null;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> linkedInWebLoginActivityLauncher;
    
    public LinkedInLoginActivity() {
        super();
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressLoader() {
        return null;
    }
    
    private final com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInLoginViewModel getLinkedLoginViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void finish() {
    }
    
    private final void initLoginProcess() {
    }
    
    private final void onLinkedInWebLoginResult(androidx.activity.result.ActivityResult result) {
    }
    
    private final void onLinkedInWebLoginDetailsResult(com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInWebActivityResult result) {
    }
    
    private final void onLinkedInUserDetailsResult(com.swipefwd.base.ResultState<com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInUserDetails> result) {
    }
    
    private final void sendSuccessResult(com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInUserDetails userDetails) {
    }
    
    private final void onCancelled() {
    }
    
    private final void onError(java.lang.String errorMessage) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/linkedin/LinkedInLoginActivity$Companion;", "", "()V", "EXTRA_RESULT_DATA", "", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}