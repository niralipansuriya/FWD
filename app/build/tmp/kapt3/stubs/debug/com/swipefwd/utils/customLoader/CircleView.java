package com.swipefwd.utils.customLoader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tB/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\fB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\rB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0012J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010&\u001a\u00020%H\u0002J\u0010\u0010\'\u001a\u00020%2\u0006\u0010(\u001a\u00020)H\u0014J\u0018\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0005H\u0014R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\n\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u001a\"\u0004\b\u001d\u0010\u001cR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0016R\u000e\u0010\"\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/swipefwd/utils/customLoader/CircleView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "circleRadius", "", "circleColor", "isAntiAlias", "", "(Landroid/content/Context;IIZ)V", "drawOnlyStroke", "strokeWidth", "(Landroid/content/Context;IIZI)V", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "getCircleColor", "()I", "setCircleColor", "(I)V", "getCircleRadius", "setCircleRadius", "getDrawOnlyStroke", "()Z", "setDrawOnlyStroke", "(Z)V", "setAntiAlias", "paint", "Landroid/graphics/Paint;", "getStrokeWidth", "setStrokeWidth", "xyCordinates", "", "initAttributes", "", "initValues", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "app_debug"})
public final class CircleView extends android.view.View {
    private int circleRadius = 30;
    private int strokeWidth = 0;
    private int circleColor = 0;
    private boolean drawOnlyStroke = false;
    private boolean isAntiAlias = true;
    private float xyCordinates = 0.0F;
    private final android.graphics.Paint paint = null;
    
    public final int getCircleRadius() {
        return 0;
    }
    
    public final void setCircleRadius(int p0) {
    }
    
    public final int getStrokeWidth() {
        return 0;
    }
    
    public final void setStrokeWidth(int p0) {
    }
    
    public final int getCircleColor() {
        return 0;
    }
    
    public final void setCircleColor(int p0) {
    }
    
    public final boolean getDrawOnlyStroke() {
        return false;
    }
    
    public final void setDrawOnlyStroke(boolean p0) {
    }
    
    public final boolean isAntiAlias() {
        return false;
    }
    
    public final void setAntiAlias(boolean p0) {
    }
    
    public CircleView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int circleRadius, int circleColor, boolean isAntiAlias) {
        super(null);
    }
    
    public CircleView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int circleRadius, int circleColor, boolean drawOnlyStroke, int strokeWidth) {
        super(null);
    }
    
    public CircleView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    public CircleView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    public CircleView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.util.AttributeSet attrs, int defStyleAttr) {
        super(null);
    }
    
    private final void initAttributes(android.util.AttributeSet attrs) {
    }
    
    @java.lang.Override()
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    }
    
    private final void initValues() {
    }
    
    @java.lang.Override()
    protected void onDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
}