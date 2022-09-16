package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\u0012\u0010\u001f\u001a\u00020\u001d2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u001dH\u0014J\b\u0010#\u001a\u00020\u001dH\u0002J\u000e\u0010$\u001a\u00020\b*\u0004\u0018\u00010%H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006&"}, d2 = {"Lcom/swipefwd/ui/profile/EmailActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/swipefwd/databinding/ActivityEmailBinding;", "emailId", "", "isFromLoading", "", "isFromSettings", "isKeyboardOpened", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/profile/EmailActivity;", "mActivity$delegate", "Lkotlin/Lazy;", "mCustomSnackbar", "Lcom/google/android/material/snackbar/Snackbar;", "profileViewModel", "Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "getProfileViewModel", "()Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "profileViewModel$delegate", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "init", "", "initObservers", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "recoveryEmailId", "isValidEmail", "", "app_debug"})
public final class EmailActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivityEmailBinding binding;
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy profileViewModel$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private java.lang.String emailId = "";
    private boolean isFromSettings = false;
    private com.google.android.material.snackbar.Snackbar mCustomSnackbar;
    private boolean isFromLoading = false;
    private boolean isKeyboardOpened = false;
    
    public EmailActivity() {
        super();
    }
    
    private final com.swipefwd.ui.profile.EmailActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.ui.updateuserprofile.UserProfileViewModel getProfileViewModel() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final boolean isValidEmail(java.lang.CharSequence $this$isValidEmail) {
        return false;
    }
    
    private final void recoveryEmailId() {
    }
    
    private final void initObservers() {
    }
}