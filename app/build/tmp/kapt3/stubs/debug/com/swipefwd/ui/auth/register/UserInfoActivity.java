package com.swipefwd.ui.auth.register;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 32\u00020\u0001:\u00013B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\b\u0010 \u001a\u00020\u001eH\u0016J\u0012\u0010!\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\b\u0010$\u001a\u00020\u001eH\u0002J\b\u0010%\u001a\u00020\u001eH\u0002J\u0010\u0010&\u001a\u00020\u001e2\u0006\u0010\'\u001a\u00020\u0004H\u0002J\u0010\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u0004H\u0002J\u0018\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\u00112\u0006\u0010\'\u001a\u00020\u0004H\u0002J\u0014\u0010,\u001a\u00020\u001e*\u00020-2\u0006\u0010.\u001a\u00020\u0011H\u0002J\u0018\u0010/\u001a\u00020\u001e*\u0002002\n\u00101\u001a\u0006\u0012\u0002\b\u000302H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00064"}, d2 = {"Lcom/swipefwd/ui/auth/register/UserInfoActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "accountType", "", "binding", "Lcom/swipefwd/databinding/ActivityUserInfoActivityBinding;", "countryCode", "facebookIds", "Ljava/util/ArrayList;", "infoViewModel", "Lcom/swipefwd/ui/auth/register/UserInfoViewModel;", "getInfoViewModel", "()Lcom/swipefwd/ui/auth/register/UserInfoViewModel;", "infoViewModel$delegate", "Lkotlin/Lazy;", "isConnection", "", "phoneNumber", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "snackBar", "Lcom/google/android/material/snackbar/Snackbar;", "socialUser", "Lcom/swipefwd/data/models/SocialMediaUserModel;", "userType", "init", "", "initObserver", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openNotificationDialog", "registerUser", "sendFacebookIds", "token", "showSnackBar", "message", "userSettings", "userId", "changeViewOnSelection", "Landroidx/appcompat/widget/AppCompatTextView;", "selectedIndex", "nextActivity", "Landroid/app/Activity;", "cls", "Ljava/lang/Class;", "Companion", "app_debug"})
public final class UserInfoActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.auth.register.UserInfoActivity.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_KEY_OTP = "extra.key.otp";
    private com.swipefwd.databinding.ActivityUserInfoActivityBinding binding;
    private final kotlin.Lazy infoViewModel$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private java.util.ArrayList<java.lang.String> facebookIds;
    private int userType = 3;
    private java.lang.String accountType = "Dater";
    private java.lang.String phoneNumber = "";
    private java.lang.String countryCode = "";
    private int isConnection = 0;
    private com.swipefwd.data.models.SocialMediaUserModel socialUser;
    private com.google.android.material.snackbar.Snackbar snackBar;
    
    public UserInfoActivity() {
        super();
    }
    
    private final com.swipefwd.ui.auth.register.UserInfoViewModel getInfoViewModel() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initObserver() {
    }
    
    private final void userSettings(int userId, java.lang.String token) {
    }
    
    private final void registerUser() {
    }
    
    private final void init() {
    }
    
    private final void changeViewOnSelection(androidx.appcompat.widget.AppCompatTextView $this$changeViewOnSelection, int selectedIndex) {
    }
    
    private final void sendFacebookIds(java.lang.String token) {
    }
    
    private final void showSnackBar(java.lang.String message) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final void openNotificationDialog() {
    }
    
    private final void nextActivity(android.app.Activity $this$nextActivity, java.lang.Class<?> cls) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/ui/auth/register/UserInfoActivity$Companion;", "", "()V", "EXTRA_KEY_OTP", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}