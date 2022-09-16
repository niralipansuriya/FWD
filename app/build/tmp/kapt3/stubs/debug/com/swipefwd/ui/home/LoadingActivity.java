package com.swipefwd.ui.home;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020.H\u0002J\b\u00100\u001a\u00020.H\u0002J\b\u00101\u001a\u00020.H\u0002J\u0012\u00102\u001a\u00020.2\b\u00103\u001a\u0004\u0018\u000104H\u0014J\b\u00105\u001a\u00020.H\u0002J\b\u00106\u001a\u00020.H\u0002J\b\u00107\u001a\u00020.H\u0002J\b\u00108\u001a\u00020.H\u0002J\b\u00109\u001a\u00020.H\u0002J\u0012\u0010:\u001a\u00020.2\b\b\u0002\u0010;\u001a\u00020\u000fH\u0002J\b\u0010<\u001a\u00020.H\u0002J\b\u0010=\u001a\u00020.H\u0002J(\u0010>\u001a\u00020.2\u0006\u0010?\u001a\u00020%2\u0006\u0010@\u001a\u00020,2\u0006\u0010A\u001a\u00020,2\u0006\u0010B\u001a\u00020,H\u0016J\u0010\u0010C\u001a\u00020.2\u0006\u0010?\u001a\u00020%H\u0016J\u0010\u0010D\u001a\u00020.2\u0006\u0010?\u001a\u00020%H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010&\u001a\u00020\'8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010\u0014\u001a\u0004\b(\u0010)R\u000e\u0010+\u001a\u00020,X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006E"}, d2 = {"Lcom/swipefwd/ui/home/LoadingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Landroid/view/SurfaceHolder$Callback;", "()V", "accountType", "", "binding", "Lcom/swipefwd/databinding/ActivityLoadingBinding;", "camera", "Landroid/hardware/Camera;", "gender", "gestureVerificationStartForResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "isProfileVerified", "", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/home/LoadingActivity;", "mActivity$delegate", "Lkotlin/Lazy;", "mAnimation", "Lcom/swipefwd/animations/SlideView;", "mDateFormat", "Ljava/text/SimpleDateFormat;", "mGestureVerificationImage", "Landroid/graphics/Bitmap;", "profileList", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/SwipingCustomModel;", "recommendingUser", "Lcom/swipefwd/data/models/DaterConnectionResponseModel$User;", "recoveryEmail", "showEmailRecoveryDialogDate", "Ljava/util/Date;", "showProfileVerificationDialogDate", "surfaceHolder", "Landroid/view/SurfaceHolder;", "swipeViewModel", "Lcom/swipefwd/ui/swiping/dater_both/SwipeProfileViewModel;", "getSwipeViewModel", "()Lcom/swipefwd/ui/swiping/dater_both/SwipeProfileViewModel;", "swipeViewModel$delegate", "userId", "", "getSwipingProfiles", "", "init", "initObservers", "moveToSwipe", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openCopyGestureDialog", "openFinalGestureDialog", "openVerificationCameraActivity", "openVerifiedDialog", "releaseCamera", "requestRuntimePermission", "isGesture", "resetCamera", "startCamera", "surfaceChanged", "holder", "format", "width", "height", "surfaceCreated", "surfaceDestroyed", "app_debug"})
public final class LoadingActivity extends androidx.appcompat.app.AppCompatActivity implements android.view.SurfaceHolder.Callback {
    private com.swipefwd.databinding.ActivityLoadingBinding binding;
    private final kotlin.Lazy swipeViewModel$delegate = null;
    private java.lang.String gender = "";
    private java.lang.String recoveryEmail = "";
    private final java.text.SimpleDateFormat mDateFormat = null;
    private java.util.Date showProfileVerificationDialogDate;
    private java.util.Date showEmailRecoveryDialogDate;
    private boolean isProfileVerified = false;
    private android.hardware.Camera camera;
    private android.view.SurfaceHolder surfaceHolder;
    private java.util.ArrayList<com.swipefwd.data.models.SwipingCustomModel> profileList;
    private com.swipefwd.animations.SlideView mAnimation;
    private int userId = 0;
    private final kotlin.Lazy mActivity$delegate = null;
    private com.swipefwd.data.models.DaterConnectionResponseModel.User recommendingUser;
    private java.lang.String accountType = "";
    private android.graphics.Bitmap mGestureVerificationImage;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> gestureVerificationStartForResult;
    
    public LoadingActivity() {
        super();
    }
    
    private final com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel getSwipeViewModel() {
        return null;
    }
    
    private final com.swipefwd.ui.home.LoadingActivity getMActivity() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void getSwipingProfiles() {
    }
    
    private final void initObservers() {
    }
    
    private final void moveToSwipe() {
    }
    
    private final void openVerifiedDialog() {
    }
    
    private final void openCopyGestureDialog() {
    }
    
    private final void openVerificationCameraActivity() {
    }
    
    private final void requestRuntimePermission(boolean isGesture) {
    }
    
    private final void openFinalGestureDialog() {
    }
    
    @java.lang.Override()
    public void surfaceCreated(@org.jetbrains.annotations.NotNull()
    android.view.SurfaceHolder holder) {
    }
    
    @java.lang.Override()
    public void surfaceChanged(@org.jetbrains.annotations.NotNull()
    android.view.SurfaceHolder holder, int format, int width, int height) {
    }
    
    @java.lang.Override()
    public void surfaceDestroyed(@org.jetbrains.annotations.NotNull()
    android.view.SurfaceHolder holder) {
    }
    
    private final void startCamera() {
    }
    
    private final void resetCamera() {
    }
    
    private final void releaseCamera() {
    }
}