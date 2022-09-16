package com.swipefwd.ui.home;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00aa\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010D\u001a\u00020EH\u0002J\u000e\u0010F\u001a\u00020E2\u0006\u0010G\u001a\u00020HJ\u0006\u0010I\u001a\u00020\u0017J\u0006\u0010J\u001a\u00020EJ\b\u0010K\u001a\u00020EH\u0002J@\u0010L\u001a\u00020E2\u0006\u0010M\u001a\u00020\u00072\u0006\u0010N\u001a\u0002042\u0006\u0010O\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u00072\u0006\u0010Q\u001a\u00020\u00072\u0006\u0010R\u001a\u00020\u00072\u0006\u0010S\u001a\u00020\u0007H\u0002J\"\u0010T\u001a\u00020E2\u0006\u0010U\u001a\u0002042\u0006\u0010V\u001a\u0002042\b\u0010W\u001a\u0004\u0018\u00010XH\u0014J\b\u0010Y\u001a\u00020EH\u0016J\u0012\u0010Z\u001a\u00020E2\b\u0010[\u001a\u0004\u0018\u00010\\H\u0014J\b\u0010]\u001a\u00020EH\u0014J\u0010\u0010^\u001a\u00020\u00172\u0006\u0010_\u001a\u00020`H\u0016J\b\u0010a\u001a\u00020EH\u0014J\b\u0010b\u001a\u00020EH\u0014J\u0010\u0010c\u001a\u00020E2\u0006\u0010d\u001a\u00020\u0017H\u0016J\b\u0010e\u001a\u00020EH\u0002J\b\u0010f\u001a\u00020EH\u0002J\u0012\u0010g\u001a\u00020E2\b\u0010h\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010i\u001a\u00020E2\u0006\u0010j\u001a\u000204H\u0016J\u0006\u0010k\u001a\u00020EJ\b\u0010l\u001a\u00020EH\u0002J\u0010\u0010m\u001a\u00020E2\u0006\u0010n\u001a\u00020oH\u0007J\f\u0010p\u001a\u00020E*\u00020HH\u0002R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u001a\u0010\u001f\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\t\"\u0004\b!\u0010\u000bR\u001a\u0010\"\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\t\"\u0004\b$\u0010\u000bR\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\'0&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010)\u001a\u00020*8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b+\u0010,R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u00105\u001a\u0002068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b9\u0010.\u001a\u0004\b7\u00108R\u001b\u0010:\u001a\u00020;8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b>\u0010.\u001a\u0004\b<\u0010=R\u001b\u0010?\u001a\u00020@8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bC\u0010.\u001a\u0004\bA\u0010B\u00a8\u0006q"}, d2 = {"Lcom/swipefwd/ui/home/TabManagerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/material/navigation/NavigationBarView$OnItemSelectedListener;", "Lcom/swipefwd/utils/BubbleListener;", "Lcom/swipefwd/utils/ChangeScreenListener;", "()V", "accountType", "", "getAccountType", "()Ljava/lang/String;", "setAccountType", "(Ljava/lang/String;)V", "badgeView", "Landroid/view/View;", "binding", "Lcom/swipefwd/databinding/ActivityTabManagerBinding;", "callbackmanager", "Lcom/facebook/CallbackManager;", "getCallbackmanager", "()Lcom/facebook/CallbackManager;", "setCallbackmanager", "(Lcom/facebook/CallbackManager;)V", "connectorDisabled", "", "getConnectorDisabled", "()Z", "setConnectorDisabled", "(Z)V", "daterDisabled", "getDaterDisabled", "setDaterDisabled", "device_id", "getDevice_id", "setDevice_id", "device_token", "getDevice_token", "setDevice_token", "dialogs", "Ljava/util/Vector;", "Landroid/app/Dialog;", "doubleBackToExitPressedOnce", "homeViewModel", "Lcom/swipefwd/ui/home/tribe/HomeFragmentViewModel;", "getHomeViewModel", "()Lcom/swipefwd/ui/home/tribe/HomeFragmentViewModel;", "homeViewModel$delegate", "Lkotlin/Lazy;", "itemView", "Lcom/google/android/material/bottomnavigation/BottomNavigationItemView;", "socialUser", "Lcom/swipefwd/data/models/SocialMediaUserModel;", "tabPosition", "", "toolTipBinding", "Lcom/skydoves/balloon/Balloon;", "getToolTipBinding", "()Lcom/skydoves/balloon/Balloon;", "toolTipBinding$delegate", "userViewModel", "Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "getUserViewModel", "()Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "userViewModel$delegate", "viewModel", "Lcom/swipefwd/ui/tutorial/onboard/OnBoardingViewModel;", "getViewModel", "()Lcom/swipefwd/ui/tutorial/onboard/OnBoardingViewModel;", "viewModel$delegate", "addBadge", "", "addFragment", "fragment", "Landroidx/fragment/app/Fragment;", "getDisableLogic", "getValues", "init", "loginOrRegister", "phone", "password", "name", "email", "filePath", "firstName", "lastName", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNavigationItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onResume", "onShowBubble", "isShow", "openDeleteDialog", "openVerifiedDialog", "saveImage", "url", "setScreen", "position", "showSingleFriendToolTip", "showWalletToolTip", "swapFragment", "changeRoleModel", "Lcom/swipefwd/data/models/ChangeRoleModel;", "replaceFragment", "app_debug"})
public final class TabManagerActivity extends androidx.appcompat.app.AppCompatActivity implements com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener, com.swipefwd.utils.BubbleListener, com.swipefwd.utils.ChangeScreenListener {
    private com.swipefwd.databinding.ActivityTabManagerBinding binding;
    private boolean doubleBackToExitPressedOnce = false;
    private final kotlin.Lazy toolTipBinding$delegate = null;
    private final kotlin.Lazy homeViewModel$delegate = null;
    private final kotlin.Lazy userViewModel$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.facebook.CallbackManager callbackmanager;
    private int tabPosition = 0;
    private com.google.android.material.bottomnavigation.BottomNavigationItemView itemView;
    private android.view.View badgeView;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String accountType = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String device_id = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String device_token = "";
    private boolean daterDisabled = false;
    private boolean connectorDisabled = false;
    private com.swipefwd.data.models.SocialMediaUserModel socialUser;
    private final java.util.Vector<android.app.Dialog> dialogs = null;
    
    public TabManagerActivity() {
        super();
    }
    
    private final com.skydoves.balloon.Balloon getToolTipBinding() {
        return null;
    }
    
    private final com.swipefwd.ui.home.tribe.HomeFragmentViewModel getHomeViewModel() {
        return null;
    }
    
    private final com.swipefwd.ui.updateuserprofile.UserProfileViewModel getUserViewModel() {
        return null;
    }
    
    private final com.swipefwd.ui.tutorial.onboard.OnBoardingViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.facebook.CallbackManager getCallbackmanager() {
        return null;
    }
    
    public final void setCallbackmanager(@org.jetbrains.annotations.Nullable()
    com.facebook.CallbackManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAccountType() {
        return null;
    }
    
    public final void setAccountType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDevice_id() {
        return null;
    }
    
    public final void setDevice_id(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDevice_token() {
        return null;
    }
    
    public final void setDevice_token(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final boolean getDaterDisabled() {
        return false;
    }
    
    public final void setDaterDisabled(boolean p0) {
    }
    
    public final boolean getConnectorDisabled() {
        return false;
    }
    
    public final void setConnectorDisabled(boolean p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void getValues() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void init() {
    }
    
    private final void addBadge() {
    }
    
    private final void replaceFragment(androidx.fragment.app.Fragment $this$replaceFragment) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    public final void addFragment(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment) {
    }
    
    private final void openDeleteDialog() {
    }
    
    @java.lang.Override()
    public boolean onNavigationItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @org.greenrobot.eventbus.Subscribe()
    public final void swapFragment(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.ChangeRoleModel changeRoleModel) {
    }
    
    private final void openVerifiedDialog() {
    }
    
    public final void showSingleFriendToolTip() {
    }
    
    private final void showWalletToolTip() {
    }
    
    @java.lang.Override()
    public void onShowBubble(boolean isShow) {
    }
    
    @java.lang.Override()
    public void setScreen(int position) {
    }
    
    private final void loginOrRegister(java.lang.String phone, int password, java.lang.String name, java.lang.String email, java.lang.String filePath, java.lang.String firstName, java.lang.String lastName) {
    }
    
    private final void saveImage(java.lang.String url) {
    }
    
    public final boolean getDisableLogic() {
        return false;
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
}