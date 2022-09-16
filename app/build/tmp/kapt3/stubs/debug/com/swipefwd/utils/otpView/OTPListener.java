package com.swipefwd.utils.otpView;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/swipefwd/utils/otpView/OTPListener;", "", "onDoneClick", "", "onInteractionListener", "onOTPComplete", "otp", "", "app_debug"})
public abstract interface OTPListener {
    
    /**
     * Callback Fired when user starts typing in the OTP/PIN box.
     */
    public abstract void onInteractionListener();
    
    /**
     * @param otp Filled OTP
     * Callback Fired when user has completed filling the OTP/PIN.
     */
    public abstract void onOTPComplete(@org.jetbrains.annotations.NotNull()
    java.lang.String otp);
    
    public abstract void onDoneClick();
}