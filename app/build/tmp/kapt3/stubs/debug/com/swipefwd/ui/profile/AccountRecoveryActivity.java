package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0006J\u0010\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0006H\u0002J\b\u0010&\u001a\u00020 H\u0002J\b\u0010\'\u001a\u00020 H\u0002J\b\u0010(\u001a\u00020 H\u0016J\u0012\u0010)\u001a\u00020 2\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\b\u0010,\u001a\u00020 H\u0002J\u000e\u0010-\u001a\u00020\b*\u0004\u0018\u00010.H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001e\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/swipefwd/ui/profile/AccountRecoveryActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/swipefwd/databinding/ActivityAccountRecoveryBinding;", "emailId", "", "isFromSettings", "", "isKeyboardOpened", "()Z", "setKeyboardOpened", "(Z)V", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/profile/AccountRecoveryActivity;", "mActivity$delegate", "Lkotlin/Lazy;", "mCustomSnackbar", "Lcom/google/android/material/snackbar/Snackbar;", "profileViewModel", "Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "getProfileViewModel", "()Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "profileViewModel$delegate", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "sharingLink", "apiCall", "", "deepLink", "createLinks", "email", "createShareUri", "Landroid/net/Uri;", "init", "initObservers", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "recoveryAccount", "isValidEmail", "", "app_debug"})
public final class AccountRecoveryActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivityAccountRecoveryBinding binding;
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy profileViewModel$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private java.lang.String emailId = "";
    private boolean isFromSettings = false;
    private boolean isKeyboardOpened = false;
    private com.google.android.material.snackbar.Snackbar mCustomSnackbar;
    private java.lang.String sharingLink = "";
    
    public AccountRecoveryActivity() {
        super();
    }
    
    private final com.swipefwd.ui.profile.AccountRecoveryActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.ui.updateuserprofile.UserProfileViewModel getProfileViewModel() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    public final boolean isKeyboardOpened() {
        return false;
    }
    
    public final void setKeyboardOpened(boolean p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final boolean isValidEmail(java.lang.CharSequence $this$isValidEmail) {
        return false;
    }
    
    private final java.lang.String createLinks(java.lang.String email) {
        return null;
    }
    
    private final android.net.Uri createShareUri(java.lang.String email) {
        return null;
    }
    
    private final void recoveryAccount() {
    }
    
    public final void apiCall(@org.jetbrains.annotations.NotNull()
    java.lang.String deepLink) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final void initObservers() {
    }
}