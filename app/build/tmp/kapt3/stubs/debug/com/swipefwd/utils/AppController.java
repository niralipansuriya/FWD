package com.swipefwd.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u000b2\u00020\u00012\u00020\u0002:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\u0005H\u0007J\b\u0010\t\u001a\u00020\u0005H\u0007J\b\u0010\n\u001a\u00020\u0005H\u0016\u00a8\u0006\f"}, d2 = {"Lcom/swipefwd/utils/AppController;", "Landroid/app/Application;", "Landroidx/lifecycle/LifecycleObserver;", "()V", "getKeyHash", "", "hashStretagy", "", "onAppBackgrounded", "onAppForegrounded", "onCreate", "Companion", "app_debug"})
public final class AppController extends android.app.Application implements androidx.lifecycle.LifecycleObserver {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.AppController.Companion Companion = null;
    @org.jetbrains.annotations.Nullable()
    private static android.content.Context context;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_INTENT_TYPING = "ACTION_TYPING";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_ONLINE_OFFLINE = "ACTION_STATUS";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_INTENT_MESSAGE_INCOMING = "ACTION_INTENT_MESSAGE_INCOMING";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VOICE_FILE_NAME_PREFIX = "VOICE_";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VOICE_FILE_NAME_SUFFIX = ".3gp";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_INTENT_IMAGE_INCOMING = "ACTION_INTENT_IMAGE_INCOMING";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_INTENT_VOICE_INCOMING = "ACTION_INTENT_VOICE_INCOMING";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_INTENT_VIDEO_INCOMING = "ACTION_INTENT_VIDEO_INCOMING";
    private static boolean psstBoolean = false;
    
    public AppController() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void getKeyHash(java.lang.String hashStretagy) {
    }
    
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_STOP)
    public final void onAppBackgrounded() {
    }
    
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_START)
    public final void onAppForegrounded() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2 = {"Lcom/swipefwd/utils/AppController$Companion;", "", "()V", "ACTION_INTENT_IMAGE_INCOMING", "", "ACTION_INTENT_MESSAGE_INCOMING", "ACTION_INTENT_TYPING", "ACTION_INTENT_VIDEO_INCOMING", "ACTION_INTENT_VOICE_INCOMING", "ACTION_ONLINE_OFFLINE", "VOICE_FILE_NAME_PREFIX", "VOICE_FILE_NAME_SUFFIX", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "psstBoolean", "", "getPsstBoolean", "()Z", "setPsstBoolean", "(Z)V", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.content.Context getContext() {
            return null;
        }
        
        public final void setContext(@org.jetbrains.annotations.Nullable()
        android.content.Context p0) {
        }
        
        public final boolean getPsstBoolean() {
            return false;
        }
        
        public final void setPsstBoolean(boolean p0) {
        }
    }
}