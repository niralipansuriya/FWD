package com.swipefwd.ui.updateuserprofile;

import java.lang.System;

/**
 * this class is related to the select image from the camera and gallery
 * this class  also contains the permission for camera and gallery
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0003\u0014\u0015\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0006\u0010\u0011\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0013\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/swipefwd/ui/updateuserprofile/ImageSelector;", "", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "(Landroidx/appcompat/app/AppCompatActivity;)V", "callback", "Lcom/swipefwd/ui/updateuserprofile/ImageSelector$Callback;", "captureCameraImageActivity", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "pickGalleryImageActivity", "onCameraImageCaptureResult", "", "result", "Landroidx/activity/result/ActivityResult;", "onPickGalleryImageResult", "openGallery", "setCallback", "startCameraImageCapture", "Callback", "Companion", "ErrorType", "app_debug"})
public final class ImageSelector {
    private final androidx.appcompat.app.AppCompatActivity activity = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.updateuserprofile.ImageSelector.Companion Companion = null;
    private static final java.lang.String TAG = "image_selector";
    private static final java.lang.String[] mimeTypes = {"image/jpg"};
    private com.swipefwd.ui.updateuserprofile.ImageSelector.Callback callback;
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> captureCameraImageActivity = null;
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> pickGalleryImageActivity = null;
    
    public ImageSelector(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity) {
        super();
    }
    
    public final void setCallback(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.updateuserprofile.ImageSelector.Callback callback) {
    }
    
    /**
     * when click on the camera option for pick the profile picture
     * then this block will be called
     */
    public final void startCameraImageCapture() {
    }
    
    /**
     * this will handle the capture camera activity's result here
     */
    private final void onCameraImageCaptureResult(androidx.activity.result.ActivityResult result) {
    }
    
    /**
     * #####################################################################
     * when click on open gallery option then this block will be called
     */
    public final void openGallery() {
    }
    
    /**
     * after picture select the uri given for cropping purpose
     */
    private final void onPickGalleryImageResult(androidx.activity.result.ActivityResult result) {
    }
    
    /**
     * this callback is implemented where result is required of pickup and capture image
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\u001c\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000bH&\u00a8\u0006\u000e"}, d2 = {"Lcom/swipefwd/ui/updateuserprofile/ImageSelector$Callback;", "", "getCameraOutputFilePath", "Ljava/io/File;", "onCameraImageCaptureSuccess", "", "onCancel", "onError", "type", "Lcom/swipefwd/ui/updateuserprofile/ImageSelector$ErrorType;", "errorText", "", "onGalleryImageSelected", "path", "app_debug"})
    public static abstract interface Callback {
        
        public abstract void onError(@org.jetbrains.annotations.NotNull()
        com.swipefwd.ui.updateuserprofile.ImageSelector.ErrorType type, @org.jetbrains.annotations.Nullable()
        java.lang.String errorText);
        
        @org.jetbrains.annotations.Nullable()
        public abstract java.io.File getCameraOutputFilePath();
        
        public abstract void onCameraImageCaptureSuccess();
        
        public abstract void onGalleryImageSelected(@org.jetbrains.annotations.NotNull()
        java.lang.String path);
        
        public abstract void onCancel();
        
        /**
         * this callback is implemented where result is required of pickup and capture image
         */
        @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
        public static final class DefaultImpls {
        }
    }
    
    /**
     * this are the error type occurred in this image selection operation
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/swipefwd/ui/updateuserprofile/ImageSelector$ErrorType;", "", "(Ljava/lang/String;I)V", "CAMERA_NOT_AVAILABLE", "NULL_IMAGE_FILE", "UNKNOWN", "app_debug"})
    public static enum ErrorType {
        /*public static final*/ CAMERA_NOT_AVAILABLE /* = new CAMERA_NOT_AVAILABLE() */,
        /*public static final*/ NULL_IMAGE_FILE /* = new NULL_IMAGE_FILE() */,
        /*public static final*/ UNKNOWN /* = new UNKNOWN() */;
        
        ErrorType() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/swipefwd/ui/updateuserprofile/ImageSelector$Companion;", "", "()V", "TAG", "", "mimeTypes", "", "[Ljava/lang/String;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}