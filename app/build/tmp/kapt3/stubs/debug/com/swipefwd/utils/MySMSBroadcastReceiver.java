package com.swipefwd.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/swipefwd/utils/MySMSBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "otpReceiveListener", "Lcom/swipefwd/utils/MySMSBroadcastReceiver$OTPReceiveListener;", "init", "", "onReceive", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "OTPReceiveListener", "app_debug"})
public final class MySMSBroadcastReceiver extends android.content.BroadcastReceiver {
    private com.swipefwd.utils.MySMSBroadcastReceiver.OTPReceiveListener otpReceiveListener;
    
    public MySMSBroadcastReceiver() {
        super();
    }
    
    public final void init(@org.jetbrains.annotations.Nullable()
    com.swipefwd.utils.MySMSBroadcastReceiver.OTPReceiveListener otpReceiveListener) {
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/utils/MySMSBroadcastReceiver$OTPReceiveListener;", "", "onOTPReceived", "", "otp", "", "onOTPTimeOut", "app_debug"})
    public static abstract interface OTPReceiveListener {
        
        public abstract void onOTPReceived(@org.jetbrains.annotations.Nullable()
        java.lang.String otp);
        
        public abstract void onOTPTimeOut();
    }
}