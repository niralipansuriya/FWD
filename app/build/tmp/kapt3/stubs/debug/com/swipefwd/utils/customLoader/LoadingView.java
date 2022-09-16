package com.swipefwd.utils.customLoader;

import java.lang.System;

/**
 * Thanks to @JeasonWong
 * https://github.com/JeasonWong/BezierLoadingView
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tB\u0017\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u000bJ\b\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u00020\b2\u0006\u0010<\u001a\u00020\u001eH\u0002J\u0010\u0010=\u001a\u00020:2\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010@\u001a\u00020:2\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010A\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020\bH\u0002J\u0010\u0010C\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020\bH\u0002J\b\u0010D\u001a\u00020\u001eH\u0002J\b\u0010E\u001a\u00020\u001eH\u0002J\u0018\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u0002042\u0006\u0010I\u001a\u000204H\u0002J\u0012\u0010J\u001a\u00020:2\b\u0010K\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010L\u001a\u00020MH\u0002J\b\u0010N\u001a\u00020:H\u0014J\u0010\u0010O\u001a\u00020:2\u0006\u0010>\u001a\u00020?H\u0014J(\u0010P\u001a\u00020:2\u0006\u0010Q\u001a\u00020\b2\u0006\u0010R\u001a\u00020\b2\u0006\u0010S\u001a\u00020\b2\u0006\u0010T\u001a\u00020\bH\u0014J\b\u0010U\u001a\u00020:H\u0002J\b\u0010V\u001a\u00020:H\u0002J\u0006\u0010W\u001a\u00020:J\u0006\u0010X\u001a\u00020:J\b\u0010Y\u001a\u00020:H\u0002R\u000e\u0010\f\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\bX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020\bX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0017\"\u0004\b\'\u0010\u0019R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u00103\u001a\n\u0012\u0004\u0012\u000204\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006Z"}, d2 = {"Lcom/swipefwd/utils/customLoader/LoadingView;", "Landroid/view/View;", "c", "Landroid/content/Context;", "(Landroid/content/Context;)V", "atr", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "context", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DEFAULT_EXTERNAL_RADIUS", "DEFAULT_INTERNAL_RADIUS", "DEFAULT_RADIAN", "MAX_DURATION", "MAX_EXTERNAL_R", "MAX_INTERNAL_R", "MIN_DURATION", "MIN_EXTERNAL_R", "MIN_INTERNAL_R", "angle1", "getAngle1$app_debug", "()I", "setAngle1$app_debug", "(I)V", "animators", "", "Landroid/animation/ValueAnimator;", "biggerCircleRadius", "", "colors", "", "getColors$app_debug", "()[I", "setColors$app_debug", "([I)V", "cyclic", "getCyclic$app_debug", "setCyclic$app_debug", "disposable", "Lio/reactivex/disposables/Disposable;", "externalRadius", "internalRadius", "mDuration", "mHeight", "mPaint", "Landroid/graphics/Paint;", "mPath", "Landroid/graphics/Path;", "mWidth", "points", "Landroid/graphics/PointF;", "radian", "smallerCircleRadius", "xX", "yY", "createPoints", "", "dp2px", "dp", "drawBezier", "canvas", "Landroid/graphics/Canvas;", "drawCircle", "getCircleX", "angle", "getCircleY", "getMaxInternalRadius", "getMinInternalRadius", "getTheta", "", "pointCenterLeft", "pointCenterRight", "init", "attrs", "isEvenCyclic", "", "onDetachedFromWindow", "onDraw", "onSizeChanged", "w", "h", "oldw", "oldh", "resetPoint", "setShader", "start", "stop", "update", "app_debug"})
public final class LoadingView extends android.view.View {
    private final int DEFAULT_EXTERNAL_RADIUS = 0;
    private final int DEFAULT_INTERNAL_RADIUS = 0;
    private final int DEFAULT_RADIAN = 45;
    private int mWidth = 0;
    private int mHeight = 0;
    private io.reactivex.disposables.Disposable disposable;
    private android.graphics.Paint mPaint;
    private final android.graphics.Path mPath = null;
    private int mDuration = 30;
    @org.jetbrains.annotations.Nullable()
    private int[] colors;
    private int angle1 = 0;
    private int cyclic = 0;
    private float biggerCircleRadius = 0.0F;
    private float smallerCircleRadius = 0.0F;
    private java.util.List<android.graphics.PointF> points;
    private java.util.List<android.animation.ValueAnimator> animators;
    private float xX = 0.0F;
    private float yY = 0.0F;
    private final int radian = 0;
    private final int MAX_DURATION = 120;
    private final int MIN_DURATION = 1;
    private float internalRadius = 0.0F;
    private final int MAX_INTERNAL_R = 0;
    private final int MIN_INTERNAL_R = 0;
    private float externalRadius = 0.0F;
    private final int MAX_EXTERNAL_R = 0;
    private final int MIN_EXTERNAL_R = 0;
    
    public LoadingView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet atr) {
        super(null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final int[] getColors$app_debug() {
        return null;
    }
    
    public final void setColors$app_debug(@org.jetbrains.annotations.Nullable()
    int[] p0) {
    }
    
    public final int getAngle1$app_debug() {
        return 0;
    }
    
    public final void setAngle1$app_debug(int p0) {
    }
    
    public final int getCyclic$app_debug() {
        return 0;
    }
    
    public final void setCyclic$app_debug(int p0) {
    }
    
    public LoadingView(@org.jetbrains.annotations.NotNull()
    android.content.Context c) {
        super(null);
    }
    
    public LoadingView(@org.jetbrains.annotations.NotNull()
    android.content.Context c, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet atr, int defStyleAttr) {
        super(null);
    }
    
    private final void init(android.util.AttributeSet attrs) {
    }
    
    @java.lang.Override()
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    }
    
    private final void setShader() {
    }
    
    public final void start() {
    }
    
    public final void stop() {
    }
    
    @java.lang.Override()
    protected void onDetachedFromWindow() {
    }
    
    private final void update() {
    }
    
    @java.lang.Override()
    protected void onDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    private final void drawCircle(android.graphics.Canvas canvas) {
    }
    
    private final void drawBezier(android.graphics.Canvas canvas) {
    }
    
    private final void resetPoint() {
    }
    
    private final void createPoints() {
    }
    
    private final boolean isEvenCyclic() {
        return false;
    }
    
    private final float getCircleY(int angle) {
        return 0.0F;
    }
    
    private final float getCircleX(int angle) {
        return 0.0F;
    }
    
    private final double getTheta(android.graphics.PointF pointCenterLeft, android.graphics.PointF pointCenterRight) {
        return 0.0;
    }
    
    private final float getMaxInternalRadius() {
        return 0.0F;
    }
    
    private final float getMinInternalRadius() {
        return 0.0F;
    }
    
    private final int dp2px(float dp) {
        return 0;
    }
}