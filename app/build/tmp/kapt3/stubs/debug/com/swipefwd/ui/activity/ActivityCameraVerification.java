package com.swipefwd.ui.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\n\u0010&\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\b\u0010+\u001a\u00020(H\u0002J\u0018\u0010,\u001a\u00020(2\u0006\u0010-\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u0004H\u0002J\b\u0010/\u001a\u00020(H\u0002J\b\u00100\u001a\u00020(H\u0002J\u0018\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u0004H\u0002J\b\u00105\u001a\u00020(H\u0002J \u00106\u001a\u00020(2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020;H\u0002J*\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u0002082\u0006\u0010>\u001a\u00020\u00112\b\b\u0002\u0010?\u001a\u00020\u001f2\u0006\u0010:\u001a\u00020;H\u0002J\b\u0010@\u001a\u00020(H\u0002J\b\u0010A\u001a\u00020(H\u0002J\b\u0010B\u001a\u00020(H\u0002J\u000e\u0010C\u001a\u00020(2\u0006\u0010.\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b%\u0010\u0017\u001a\u0004\b#\u0010$\u00a8\u0006D"}, d2 = {"Lcom/swipefwd/ui/activity/ActivityCameraVerification;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "VERIFICATION_IMAGE", "binding", "Lcom/swipefwd/databinding/ActivityCameraVerificationBinding;", "callbackJpeg", "Landroid/hardware/Camera$PictureCallback;", "callbackRaw", "callbackShutter", "Landroid/hardware/Camera$ShutterCallback;", "checkForFaceInPreview", "", "dialogs", "Ljava/util/Vector;", "Landroid/app/Dialog;", "flashOn", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/activity/ActivityCameraVerification;", "mActivity$delegate", "Lkotlin/Lazy;", "mCamera", "Landroid/hardware/Camera;", "mGestureVerificationImage", "Landroid/graphics/Bitmap;", "maPreview", "Lcom/swipefwd/utils/cameraUtility/CameraPreview;", "prevX", "", "prevY", "profileViewModel", "Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "getProfileViewModel", "()Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "profileViewModel$delegate", "getCameraInstance", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "openCopyGestureDialog", "openFinalGestureDialog", "isValid", "filename", "openVerificationProblemDialog", "openVerifiedImageDialog", "persistImage", "Ljava/io/File;", "bitmap", "name", "sendImageVerification", "setAnimationDialogOpen", "cardView", "Landroid/view/View;", "logo", "textLayout", "Landroid/widget/LinearLayout;", "setAnimationPsstDialogClose", "view", "dialog", "case", "setCallbacks", "setClickListeners", "startCameraPreview", "verificationProcess", "app_debug"})
public final class ActivityCameraVerification extends androidx.appcompat.app.AppCompatActivity {
    private android.graphics.Bitmap mGestureVerificationImage;
    private com.swipefwd.databinding.ActivityCameraVerificationBinding binding;
    private com.swipefwd.utils.cameraUtility.CameraPreview maPreview;
    private final java.lang.String TAG = "ActivityCameraVerification";
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy profileViewModel$delegate = null;
    private android.hardware.Camera mCamera;
    private boolean flashOn = false;
    private java.lang.String VERIFICATION_IMAGE = "verification_picture";
    private android.hardware.Camera.ShutterCallback callbackShutter;
    private android.hardware.Camera.PictureCallback callbackRaw;
    private android.hardware.Camera.PictureCallback callbackJpeg;
    private final java.util.Vector<android.app.Dialog> dialogs = null;
    private int prevY = 0;
    private int prevX = 0;
    private boolean checkForFaceInPreview = false;
    
    public ActivityCameraVerification() {
        super();
    }
    
    private final com.swipefwd.ui.activity.ActivityCameraVerification getMActivity() {
        return null;
    }
    
    private final com.swipefwd.ui.updateuserprofile.UserProfileViewModel getProfileViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setCallbacks() {
    }
    
    public final void verificationProcess(@org.jetbrains.annotations.NotNull()
    java.lang.String filename) {
    }
    
    private final void openFinalGestureDialog(boolean isValid, java.lang.String filename) {
    }
    
    private final void openVerificationProblemDialog() {
    }
    
    private final void setAnimationDialogOpen(android.view.View cardView, android.view.View logo, android.widget.LinearLayout textLayout) {
    }
    
    private final void setAnimationPsstDialogClose(android.view.View view, android.app.Dialog dialog, int p2_1523096, android.widget.LinearLayout textLayout) {
    }
    
    private final void openVerifiedImageDialog() {
    }
    
    private final void openCopyGestureDialog() {
    }
    
    private final java.io.File persistImage(android.graphics.Bitmap bitmap, java.lang.String name) {
        return null;
    }
    
    private final void sendImageVerification() {
    }
    
    private final void startCameraPreview() {
    }
    
    private final android.hardware.Camera getCameraInstance() {
        return null;
    }
    
    private final void setClickListeners() {
    }
}