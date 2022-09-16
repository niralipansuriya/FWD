package com.swipefwd.utils.customprogressbar;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B!\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u001f\u001a\u00020 2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u00a8\u0006!"}, d2 = {"Lcom/swipefwd/utils/customprogressbar/PercentStyle;", "", "()V", "align", "Landroid/graphics/Paint$Align;", "textSize", "", "percentSign", "", "(Landroid/graphics/Paint$Align;FZ)V", "customText", "", "getCustomText", "()Ljava/lang/String;", "setCustomText", "(Ljava/lang/String;)V", "isPercentSign", "()Z", "setPercentSign", "(Z)V", "textColor", "", "getTextColor", "()I", "setTextColor", "(I)V", "getTextSize", "()F", "setTextSize", "(F)V", "getAlign", "setAlign", "", "app_debug"})
public final class PercentStyle {
    private android.graphics.Paint.Align align;
    private float textSize = 0.0F;
    private boolean isPercentSign = false;
    
    /**
     * With this you can set a custom text which should get displayed right
     * behind the number of the progress. Per default it displays a *%*.
     *
     * @param customText
     * The custom text you want to display.
     * @since 1.4.0
     */
    @org.jetbrains.annotations.NotNull()
    private java.lang.String customText = "%";
    
    /**
     * Set the color of the text that display the current progress. This will
     * also change the color of the text that normally represents a *%*.
     *
     * @param textColor
     * the color to set the text to.
     * @since 1.4.0
     */
    private int textColor = android.graphics.Color.BLACK;
    
    public final float getTextSize() {
        return 0.0F;
    }
    
    public final void setTextSize(float p0) {
    }
    
    public final boolean isPercentSign() {
        return false;
    }
    
    public final void setPercentSign(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCustomText() {
        return null;
    }
    
    public final void setCustomText(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getTextColor() {
        return 0;
    }
    
    public final void setTextColor(int p0) {
    }
    
    public PercentStyle() {
        super();
    }
    
    public PercentStyle(@org.jetbrains.annotations.Nullable()
    android.graphics.Paint.Align align, float textSize, boolean percentSign) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Paint.Align getAlign() {
        return null;
    }
    
    public final void setAlign(@org.jetbrains.annotations.Nullable()
    android.graphics.Paint.Align align) {
    }
}