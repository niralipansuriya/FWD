package com.swipefwd.utils.otpView;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 22\u00020\u0001:\u00012B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u001a\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010$\u001a\u00020!2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0006\u0010%\u001a\u00020!J\u0006\u0010&\u001a\u00020!J\u0010\u0010\'\u001a\u00020!2\u0006\u0010\u0012\u001a\u00020\tH\u0002J\u000e\u0010(\u001a\u00020!2\u0006\u0010)\u001a\u00020*J\u000e\u0010(\u001a\u00020!2\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010+\u001a\u00020!2\u0006\u0010,\u001a\u00020-H\u0017J\u0010\u0010.\u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0006\u0010/\u001a\u00020!J\u0006\u00100\u001a\u00020!J\u001a\u00101\u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002R\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u00158F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f\u00a8\u00063"}, d2 = {"Lcom/swipefwd/utils/otpView/OtpTextView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "filter", "Landroid/text/InputFilter;", "getFilter", "()Landroid/text/InputFilter;", "itemViews", "", "Lcom/swipefwd/utils/otpView/ItemView;", "length", "mContext", "otp", "", "getOtp", "()Ljava/lang/String;", "otpChildEditText", "Lcom/swipefwd/utils/otpView/OTPChildEditText;", "otpListener", "Lcom/swipefwd/utils/otpView/OTPListener;", "getOtpListener", "()Lcom/swipefwd/utils/otpView/OTPListener;", "setOtpListener", "(Lcom/swipefwd/utils/otpView/OTPListener;)V", "generateViews", "", "styles", "Landroid/content/res/TypedArray;", "init", "requestFocusOTP", "resetState", "setFocus", "setOTP", "s", "", "setOnTouchListener", "l", "Landroid/view/View$OnTouchListener;", "setTextWatcher", "showError", "showSuccess", "styleEditTexts", "Companion", "app_debug"})
public final class OtpTextView extends android.widget.FrameLayout {
    private android.content.Context mContext;
    private java.util.List<com.swipefwd.utils.otpView.ItemView> itemViews;
    private com.swipefwd.utils.otpView.OTPChildEditText otpChildEditText;
    @org.jetbrains.annotations.Nullable()
    private com.swipefwd.utils.otpView.OTPListener otpListener;
    private int length = 0;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.otpView.OtpTextView.Companion Companion = null;
    private static final int DEFAULT_LENGTH = 4;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_WIDTH = 48;
    private static final int DEFAULT_SPACE = -1;
    private static final int DEFAULT_SPACE_LEFT = 4;
    private static final int DEFAULT_SPACE_RIGHT = 4;
    private static final int DEFAULT_SPACE_TOP = 4;
    private static final int DEFAULT_SPACE_BOTTOM = 4;
    private static final java.lang.String PATTERN = "[1234567890]*";
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.utils.otpView.OTPListener getOtpListener() {
        return null;
    }
    
    public final void setOtpListener(@org.jetbrains.annotations.Nullable()
    com.swipefwd.utils.otpView.OTPListener p0) {
    }
    
    private final android.text.InputFilter getFilter() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getOtp() {
        return null;
    }
    
    public OtpTextView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    public OtpTextView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public OtpTextView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    private final void init(android.util.AttributeSet attrs) {
    }
    
    private final void styleEditTexts(android.content.res.TypedArray styles, android.util.AttributeSet attrs) {
    }
    
    private final void generateViews(android.content.res.TypedArray styles, android.util.AttributeSet attrs) {
    }
    
    private final void setTextWatcher(com.swipefwd.utils.otpView.OTPChildEditText otpChildEditText) {
    }
    
    private final void setFocus(int length) {
    }
    
    public final void setOTP(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence s) {
    }
    
    public final void requestFocusOTP() {
    }
    
    public final void showError() {
    }
    
    public final void resetState() {
    }
    
    public final void showSuccess() {
    }
    
    public final void setOTP(@org.jetbrains.annotations.NotNull()
    java.lang.String otp) {
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    @java.lang.Override()
    public void setOnTouchListener(@org.jetbrains.annotations.NotNull()
    android.view.View.OnTouchListener l) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/swipefwd/utils/otpView/OtpTextView$Companion;", "", "()V", "DEFAULT_HEIGHT", "", "DEFAULT_LENGTH", "DEFAULT_SPACE", "DEFAULT_SPACE_BOTTOM", "DEFAULT_SPACE_LEFT", "DEFAULT_SPACE_RIGHT", "DEFAULT_SPACE_TOP", "DEFAULT_WIDTH", "PATTERN", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}