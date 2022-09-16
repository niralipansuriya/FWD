package com.swipefwd.utils;

import java.lang.System;

/**
 * Based on the following Stackoverflow answer:
 * http://stackoverflow.com/questions/2150078/how-to-check-visibility-of-software-keyboard-in-android
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\r\u00a8\u0006\u0013"}, d2 = {"Lcom/swipefwd/utils/KeyboardUtils;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "act", "Landroid/app/Activity;", "mCallback", "Lcom/swipefwd/utils/KeyboardUtils$SoftKeyboardToggleListener;", "(Landroid/app/Activity;Lcom/swipefwd/utils/KeyboardUtils$SoftKeyboardToggleListener;)V", "mRootView", "Landroid/view/View;", "mScreenDensity", "", "prevValue", "", "Ljava/lang/Boolean;", "onGlobalLayout", "", "removeListener", "Companion", "SoftKeyboardToggleListener", "app_debug"})
public final class KeyboardUtils implements android.view.ViewTreeObserver.OnGlobalLayoutListener {
    private com.swipefwd.utils.KeyboardUtils.SoftKeyboardToggleListener mCallback;
    private final android.view.View mRootView = null;
    private java.lang.Boolean prevValue;
    private final float mScreenDensity = 0.0F;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.KeyboardUtils.Companion Companion = null;
    private static final int MAGIC_NUMBER = 200;
    private static final java.util.HashMap<com.swipefwd.utils.KeyboardUtils.SoftKeyboardToggleListener, com.swipefwd.utils.KeyboardUtils> sListenerMap = null;
    
    private KeyboardUtils(android.app.Activity act, com.swipefwd.utils.KeyboardUtils.SoftKeyboardToggleListener mCallback) {
        super();
    }
    
    @java.lang.Override()
    public void onGlobalLayout() {
    }
    
    private final void removeListener() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/swipefwd/utils/KeyboardUtils$SoftKeyboardToggleListener;", "", "onToggleSoftKeyboard", "", "isVisible", "", "app_debug"})
    public static abstract interface SoftKeyboardToggleListener {
        
        public abstract void onToggleSoftKeyboard(boolean isVisible);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007J\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\nJ\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0007H\u0002J\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/swipefwd/utils/KeyboardUtils$Companion;", "", "()V", "MAGIC_NUMBER", "", "sListenerMap", "Ljava/util/HashMap;", "Lcom/swipefwd/utils/KeyboardUtils$SoftKeyboardToggleListener;", "Lcom/swipefwd/utils/KeyboardUtils;", "addKeyboardToggleListener", "", "act", "Landroid/app/Activity;", "listener", "forceCloseKeyboard", "activeView", "Landroid/view/View;", "removeAllKeyboardToggleListeners", "removeKeyboardToggleListener", "toggleKeyboardVisibility", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Add a new keyboard listener
         *
         * @param act      calling activity
         * @param listener callback
         */
        public final void addKeyboardToggleListener(@org.jetbrains.annotations.NotNull()
        android.app.Activity act, @org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.KeyboardUtils.SoftKeyboardToggleListener listener) {
        }
        
        /**
         * Remove a registered listener
         *
         * @param listener [SoftKeyboardToggleListener]
         */
        private final void removeKeyboardToggleListener(com.swipefwd.utils.KeyboardUtils.SoftKeyboardToggleListener listener) {
        }
        
        /**
         * Remove all registered keyboard listeners
         */
        public final void removeAllKeyboardToggleListeners() {
        }
        
        /**
         * Manually toggle soft keyboard visibility
         *
         * @param context calling context
         */
        public final void toggleKeyboardVisibility(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
        
        /**
         * Force closes the soft keyboard
         *
         * @param activeView the view with the keyboard focus
         */
        public final void forceCloseKeyboard(@org.jetbrains.annotations.NotNull()
        android.view.View activeView) {
        }
    }
}