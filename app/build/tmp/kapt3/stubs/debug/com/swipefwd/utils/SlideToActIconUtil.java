package com.swipefwd.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u00c0\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001d\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000\u00a2\u0006\u0002\b\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0015\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0000\u00a2\u0006\u0002\b\u0016J\u001d\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0011H\u0000\u00a2\u0006\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/swipefwd/utils/SlideToActIconUtil;", "", "()V", "createIconAnimator", "Landroid/animation/ValueAnimator;", "view", "Lcom/swipefwd/utils/SlideToActView;", "icon", "Landroid/graphics/drawable/Drawable;", "listener", "Landroid/animation/ValueAnimator$AnimatorUpdateListener;", "fallbackToFadeAnimation", "", "loadIconCompat", "context", "Landroid/content/Context;", "value", "", "loadIconCompat$app_debug", "startIconAnimation", "", "stopIconAnimation", "stopIconAnimation$app_debug", "tintIconCompat", "color", "tintIconCompat$app_debug", "app_debug"})
public final class SlideToActIconUtil {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.SlideToActIconUtil INSTANCE = null;
    
    private SlideToActIconUtil() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.drawable.Drawable loadIconCompat$app_debug(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int value) {
        return null;
    }
    
    public final void tintIconCompat$app_debug(@org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable icon, int color) {
    }
    
    /**
     * Internal method to start the Icon AVD animation, with the proper library based on API level.
     */
    private final void startIconAnimation(android.graphics.drawable.Drawable icon) {
    }
    
    /**
     * Internal method to stop the Icon AVD animation, with the proper library based on API level.
     */
    public final void stopIconAnimation$app_debug(@org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable icon) {
    }
    
    /**
     * Creates a [ValueAnimator] to animate the complete icon. Uses the [fallbackToFadeAnimation]
     * to decide if the icon should be animated with a Fade or with using [AnimatedVectorDrawable].
     */
    @org.jetbrains.annotations.NotNull()
    public final android.animation.ValueAnimator createIconAnimator(@org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.SlideToActView view, @org.jetbrains.annotations.NotNull()
    android.graphics.drawable.Drawable icon, @org.jetbrains.annotations.NotNull()
    android.animation.ValueAnimator.AnimatorUpdateListener listener) {
        return null;
    }
    
    /**
     * Logic to decide if we should do a Fade or use the [AnimatedVectorDrawable] animation.
     */
    private final boolean fallbackToFadeAnimation(android.graphics.drawable.Drawable icon) {
        return false;
    }
}