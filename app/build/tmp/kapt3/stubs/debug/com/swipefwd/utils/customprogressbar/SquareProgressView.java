package com.swipefwd.utils.customprogressbar;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0002KLB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\nJ\u0010\u00107\u001a\u0002082\u0006\u00104\u001a\u00020\u000fH\u0002J\b\u00109\u001a\u000208H\u0002J\u0010\u0010:\u001a\u0002082\u0006\u0010;\u001a\u00020*H\u0002J\b\u0010<\u001a\u000208H\u0002J\u001a\u0010=\u001a\u00060>R\u00020\u00002\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010@\u001a\u000201J\u0010\u0010A\u001a\u0002082\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010B\u001a\u0002082\u0006\u0010\u000b\u001a\u00020\fH\u0014J\u000e\u0010C\u001a\u0002082\u0006\u0010D\u001a\u00020\bJ\u000e\u0010E\u001a\u0002082\u0006\u00100\u001a\u000201J\u0016\u0010F\u001a\u0002082\u0006\u0010G\u001a\u00020\u00112\u0006\u0010H\u001a\u00020\u000fJ\u000e\u0010I\u001a\u0002082\u0006\u0010J\u001a\u00020\bR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D\u00a2\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R$\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R$\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R\u001e\u0010 \u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013R$\u0010\"\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0013\"\u0004\b#\u0010\u0015R$\u0010%\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0011@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0013\"\u0004\b&\u0010\u0015R\u0010\u0010\'\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u000e\u00a2\u0006\u0002\n\u0000R$\u0010+\u001a\u00020*2\u0006\u0010)\u001a\u00020*8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u000201X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006M"}, d2 = {"Lcom/swipefwd/utils/customprogressbar/SquareProgressView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "canvas", "Landroid/graphics/Canvas;", "indeterminate_count", "indeterminate_width", "", "centerline", "", "isCenterline", "()Z", "setCenterline", "(Z)V", "clearOnHundred", "isClearOnHundred", "setClearOnHundred", "indeterminate", "isIndeterminate", "setIndeterminate", "outline", "isOutline", "setOutline", "<set-?>", "isRoundedCorners", "showProgress", "isShowProgress", "setShowProgress", "startline", "isStartline", "setStartline", "outlinePaint", "Landroid/graphics/Paint;", "percentSettings", "Lcom/swipefwd/utils/customprogressbar/PercentStyle;", "percentStyle", "getPercentStyle", "()Lcom/swipefwd/utils/customprogressbar/PercentStyle;", "setPercentStyle", "(Lcom/swipefwd/utils/customprogressbar/PercentStyle;)V", "progress", "", "progressBarPaint", "roundedCornersRadius", "strokewidth", "textPaint", "widthInDp", "drawCenterline", "", "drawOutline", "drawPercent", "setting", "drawStartline", "getDrawEnd", "Lcom/swipefwd/utils/customprogressbar/SquareProgressView$DrawStop;", "percent", "getProgress", "initializePaints", "onDraw", "setColor", "color", "setProgress", "setRoundedCorners", "roundedCorners", "radius", "setWidthInDp", "width", "DrawStop", "Place", "app_debug"})
public final class SquareProgressView extends android.view.View {
    private double progress = 0.0;
    private android.graphics.Paint progressBarPaint;
    private android.graphics.Paint outlinePaint;
    private android.graphics.Paint textPaint;
    private float widthInDp = 10.0F;
    private float strokewidth = 0.0F;
    private android.graphics.Canvas canvas;
    private boolean isOutline = false;
    private boolean isStartline = false;
    private boolean isShowProgress = false;
    private boolean isCenterline = false;
    private boolean isRoundedCorners = false;
    private float roundedCornersRadius = 10.0F;
    private com.swipefwd.utils.customprogressbar.PercentStyle percentSettings;
    private boolean isClearOnHundred = false;
    private boolean isIndeterminate = false;
    private int indeterminate_count = 1;
    private final float indeterminate_width = 20.0F;
    
    public final boolean isOutline() {
        return false;
    }
    
    public final void setOutline(boolean outline) {
    }
    
    public final boolean isStartline() {
        return false;
    }
    
    public final void setStartline(boolean startline) {
    }
    
    public final boolean isShowProgress() {
        return false;
    }
    
    public final void setShowProgress(boolean showProgress) {
    }
    
    public final boolean isCenterline() {
        return false;
    }
    
    public final void setCenterline(boolean centerline) {
    }
    
    public final boolean isRoundedCorners() {
        return false;
    }
    
    public final boolean isClearOnHundred() {
        return false;
    }
    
    public final void setClearOnHundred(boolean clearOnHundred) {
    }
    
    public final boolean isIndeterminate() {
        return false;
    }
    
    public final void setIndeterminate(boolean indeterminate) {
    }
    
    public SquareProgressView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    public SquareProgressView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyle) {
        super(null);
    }
    
    public SquareProgressView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    private final void initializePaints(android.content.Context context) {
    }
    
    @java.lang.Override()
    protected void onDraw(@org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
    }
    
    private final void drawStartline() {
    }
    
    private final void drawOutline() {
    }
    
    public final double getProgress() {
        return 0.0;
    }
    
    public final void setProgress(double progress) {
    }
    
    public final void setColor(int color) {
    }
    
    public final void setWidthInDp(int width) {
    }
    
    private final void drawPercent(com.swipefwd.utils.customprogressbar.PercentStyle setting) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.utils.customprogressbar.PercentStyle getPercentStyle() {
        return null;
    }
    
    public final void setPercentStyle(@org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.customprogressbar.PercentStyle percentSettings) {
    }
    
    private final void drawCenterline(float strokewidth) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.utils.customprogressbar.SquareProgressView.DrawStop getDrawEnd(float percent, @org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas) {
        return null;
    }
    
    public final void setRoundedCorners(boolean roundedCorners, float radius) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/swipefwd/utils/customprogressbar/SquareProgressView$DrawStop;", "", "(Lcom/swipefwd/utils/customprogressbar/SquareProgressView;)V", "location", "", "getLocation", "()F", "setLocation", "(F)V", "place", "Lcom/swipefwd/utils/customprogressbar/SquareProgressView$Place;", "getPlace", "()Lcom/swipefwd/utils/customprogressbar/SquareProgressView$Place;", "setPlace", "(Lcom/swipefwd/utils/customprogressbar/SquareProgressView$Place;)V", "app_debug"})
    public final class DrawStop {
        @org.jetbrains.annotations.Nullable()
        private com.swipefwd.utils.customprogressbar.SquareProgressView.Place place;
        private float location = 0.0F;
        
        public DrawStop() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.swipefwd.utils.customprogressbar.SquareProgressView.Place getPlace() {
            return null;
        }
        
        public final void setPlace(@org.jetbrains.annotations.Nullable()
        com.swipefwd.utils.customprogressbar.SquareProgressView.Place p0) {
        }
        
        public final float getLocation() {
            return 0.0F;
        }
        
        public final void setLocation(float p0) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/utils/customprogressbar/SquareProgressView$Place;", "", "(Ljava/lang/String;I)V", "TOP", "RIGHT", "BOTTOM", "LEFT", "app_debug"})
    public static enum Place {
        /*public static final*/ TOP /* = new TOP() */,
        /*public static final*/ RIGHT /* = new RIGHT() */,
        /*public static final*/ BOTTOM /* = new BOTTOM() */,
        /*public static final*/ LEFT /* = new LEFT() */;
        
        Place() {
        }
    }
}