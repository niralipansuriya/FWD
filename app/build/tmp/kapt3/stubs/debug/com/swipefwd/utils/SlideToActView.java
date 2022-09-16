package com.swipefwd.utils;

import java.lang.System;

/**
 * Class representing the custom view, SlideToActView.
 *
 * SlideToActView is an elegant material designed slider, that enrich your app
 * with a "Slide-to-unlock" like widget.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\r\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u00a1\u00012\u00020\u0001:\f\u00a1\u0001\u00a2\u0001\u00a3\u0001\u00a4\u0001\u00a5\u0001\u00a6\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\u0084\u0001\u001a\u00020\u001f2\u0007\u0010\u0085\u0001\u001a\u00020.2\u0007\u0010\u0086\u0001\u001a\u00020.H\u0002J\b\u0010\u0087\u0001\u001a\u00030\u0088\u0001J\n\u0010\u0089\u0001\u001a\u00030\u0088\u0001H\u0003J\u0013\u0010\u008a\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u008b\u0001\u001a\u00020\u0007H\u0002J\u0007\u0010\u008c\u0001\u001a\u00020\u001fJ\u0016\u0010\u008d\u0001\u001a\u00030\u0088\u00012\n\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008f\u0001H\u0014J\u001c\u0010\u0090\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u0091\u0001\u001a\u00020\u00072\u0007\u0010\u0092\u0001\u001a\u00020\u0007H\u0014J.\u0010\u0093\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u0094\u0001\u001a\u00020\u00072\u0007\u0010\u0095\u0001\u001a\u00020\u00072\u0007\u0010\u0096\u0001\u001a\u00020\u00072\u0007\u0010\u0097\u0001\u001a\u00020\u0007H\u0014J\u0015\u0010\u0098\u0001\u001a\u00020\u001f2\n\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u009a\u0001H\u0016J\t\u0010\u009b\u0001\u001a\u00020\u001fH\u0016J\b\u0010\u009c\u0001\u001a\u00030\u0088\u0001J\b\u0010\u009d\u0001\u001a\u00030\u0088\u0001J\n\u0010\u009e\u0001\u001a\u00030\u0088\u0001H\u0002J\n\u0010\u009f\u0001\u001a\u00030\u0088\u0001H\u0002J\r\u0010\u00a0\u0001\u001a\u00020\u0007*\u00020\u0007H\u0002R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR&\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00078\u0006@FX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R&\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00078\u0006@FX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R&\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00078\u0006@FX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010 \"\u0004\b$\u0010\"R$\u0010%\u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020\u001f@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010 \"\u0004\b&\u0010\"R\u001a\u0010\'\u001a\u00020\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010 \"\u0004\b(\u0010\"R\u000e\u0010)\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000206X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u00108\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007@BX\u0082\u000e\u00a2\u0006\b\n\u0000\"\u0004\b9\u0010\u0017R\u000e\u0010:\u001a\u00020\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020.X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020?X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020AX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010G\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007@BX\u0082\u000e\u00a2\u0006\b\n\u0000\"\u0004\bH\u0010\u0017R\u000e\u0010I\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020?X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020?X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010M\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007@BX\u0082\u000e\u00a2\u0006\b\n\u0000\"\u0004\bN\u0010\u0017R\u000e\u0010O\u001a\u00020PX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010T\u001a\u0004\u0018\u00010UX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u001c\u0010Z\u001a\u0004\u0018\u00010[X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\u001c\u0010`\u001a\u0004\u0018\u00010aX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR\u001c\u0010f\u001a\u0004\u0018\u00010gX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bh\u0010i\"\u0004\bj\u0010kR&\u0010l\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00078\u0006@FX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\u0015\"\u0004\bn\u0010\u0017R&\u0010o\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00078\u0006@FX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bp\u0010\u0015\"\u0004\bq\u0010\u0017R&\u0010r\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00078\u0006@FX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bs\u0010\u0015\"\u0004\bt\u0010\u0017R$\u0010v\u001a\u00020u2\u0006\u0010\u0012\u001a\u00020u@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR&\u0010{\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00078\u0006@FX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b|\u0010\u0015\"\u0004\b}\u0010\u0017R\'\u0010~\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00078\u0006@FX\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010\u0015\"\u0005\b\u0080\u0001\u0010\u0017R\'\u0010\u0081\u0001\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007@FX\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0015\"\u0005\b\u0083\u0001\u0010\u0017\u00a8\u0006\u00a7\u0001"}, d2 = {"Lcom/swipefwd/utils/SlideToActView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "xmlAttrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "animDuration", "", "getAnimDuration", "()J", "setAnimDuration", "(J)V", "bumpVibration", "getBumpVibration", "setBumpVibration", "value", "completeIcon", "getCompleteIcon", "()I", "setCompleteIcon", "(I)V", "iconColor", "getIconColor", "setIconColor", "innerColor", "getInnerColor", "setInnerColor", "isAnimateCompletion", "", "()Z", "setAnimateCompletion", "(Z)V", "isLocked", "setLocked", "isReversed", "setReversed", "isRotateIcon", "setRotateIcon", "mActualAreaMargin", "mActualAreaWidth", "mAreaHeight", "mAreaWidth", "mArrowAngle", "", "mArrowMargin", "mBorderRadius", "mDesiredSliderHeight", "mDesiredSliderHeightDp", "mDesiredSliderWidth", "mDesiredSliderWidthDp", "mDrawableArrow", "Landroid/graphics/drawable/Drawable;", "mDrawableTick", "mEffectivePosition", "setMEffectivePosition", "mFlagDrawTick", "mFlagMoving", "mGraceValue", "mIconMargin", "mInnerPaint", "Landroid/graphics/Paint;", "mInnerRect", "Landroid/graphics/RectF;", "mIsCompleted", "mLastX", "mOriginAreaMargin", "mOuterPaint", "mOuterRect", "mPosition", "setMPosition", "mPositionPerc", "mPositionPercInv", "mStrokePaint", "mTextPaint", "mTextSize", "setMTextSize", "mTextView", "Landroid/widget/TextView;", "mTextXPosition", "mTextYPosition", "mTickMargin", "onSlideCompleteListener", "Lcom/swipefwd/utils/SlideToActView$OnSlideCompleteListener;", "getOnSlideCompleteListener", "()Lcom/swipefwd/utils/SlideToActView$OnSlideCompleteListener;", "setOnSlideCompleteListener", "(Lcom/swipefwd/utils/SlideToActView$OnSlideCompleteListener;)V", "onSlideResetListener", "Lcom/swipefwd/utils/SlideToActView$OnSlideResetListener;", "getOnSlideResetListener", "()Lcom/swipefwd/utils/SlideToActView$OnSlideResetListener;", "setOnSlideResetListener", "(Lcom/swipefwd/utils/SlideToActView$OnSlideResetListener;)V", "onSlideToActAnimationEventListener", "Lcom/swipefwd/utils/SlideToActView$OnSlideToActAnimationEventListener;", "getOnSlideToActAnimationEventListener", "()Lcom/swipefwd/utils/SlideToActView$OnSlideToActAnimationEventListener;", "setOnSlideToActAnimationEventListener", "(Lcom/swipefwd/utils/SlideToActView$OnSlideToActAnimationEventListener;)V", "onSlideUserFailedListener", "Lcom/swipefwd/utils/SlideToActView$OnSlideUserFailedListener;", "getOnSlideUserFailedListener", "()Lcom/swipefwd/utils/SlideToActView$OnSlideUserFailedListener;", "setOnSlideUserFailedListener", "(Lcom/swipefwd/utils/SlideToActView$OnSlideUserFailedListener;)V", "outerColor", "getOuterColor", "setOuterColor", "sliderIcon", "getSliderIcon", "setSliderIcon", "strokeColor", "getStrokeColor", "setStrokeColor", "", "text", "getText", "()Ljava/lang/CharSequence;", "setText", "(Ljava/lang/CharSequence;)V", "textAppearance", "getTextAppearance", "setTextAppearance", "textColor", "getTextColor", "setTextColor", "typeFace", "getTypeFace", "setTypeFace", "checkInsideButton", "x", "y", "completeSlider", "", "handleVibration", "increasePosition", "inc", "isCompleted", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onSizeChanged", "w", "h", "oldw", "oldh", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "performClick", "resetSlider", "resetSliderNoAnim", "startAnimationComplete", "startAnimationReset", "dpToPx", "Companion", "OnSlideCompleteListener", "OnSlideResetListener", "OnSlideToActAnimationEventListener", "OnSlideUserFailedListener", "SlideToActOutlineProvider", "app_debug"})
public final class SlideToActView extends android.view.View {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.SlideToActView.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "SlideToActView";
    private float mDesiredSliderHeightDp = 70.0F;
    private float mDesiredSliderWidthDp = 280.0F;
    private int mDesiredSliderHeight = 0;
    private int mDesiredSliderWidth = 0;
    
    /**
     * Height of the drawing area
     */
    private int mAreaHeight = 0;
    
    /**
     * Width of the drawing area
     */
    private int mAreaWidth = 0;
    
    /**
     * Actual Width of the drawing area, used for animations
     */
    private int mActualAreaWidth = 0;
    
    /**
     * Border Radius, default to mAreaHeight/2, -1 when not initialized
     */
    private int mBorderRadius = -1;
    
    /**
     * Margin of the cursor from the outer area
     */
    private int mActualAreaMargin;
    private final int mOriginAreaMargin = 0;
    
    /**
     * Text message
     */
    @org.jetbrains.annotations.NotNull()
    private java.lang.CharSequence text = "";
    
    /**
     * Typeface for the text field
     */
    private int typeFace = android.graphics.Typeface.NORMAL;
    
    /**
     * Text Appearance used to fully customize the font
     */
    @androidx.annotation.StyleRes()
    private int textAppearance = 0;
    
    /**
     * Outer color used by the slider (primary)
     */
    @androidx.annotation.ColorInt()
    private int outerColor = 0;
    @androidx.annotation.ColorInt()
    private int strokeColor = 0;
    
    /**
     * Inner color used by the slider (secondary, icon and border)
     */
    @androidx.annotation.ColorInt()
    private int innerColor = 0;
    
    /**
     * Duration of the complete and reset animation (in milliseconds).
     */
    private long animDuration = 300L;
    
    /**
     * Duration of vibration after bumping to the end point
     */
    private long bumpVibration = 0L;
    @androidx.annotation.ColorInt()
    private int textColor = 0;
    
    /**
     * Custom Icon color
     */
    @androidx.annotation.ColorInt()
    private int iconColor = 0;
    
    /**
     * Custom Slider Icon
     */
    @androidx.annotation.DrawableRes()
    private int sliderIcon = com.swipefwd.R.drawable.right_grayarrow;
    
    /**
     * Slider cursor position (between 0 and (`mAreaWidth - mAreaHeight))
     */
    private int mPosition = 0;
    
    /**
     * Slider cursor effective position. This is used to handle the `reversed` scenario.
     */
    private int mEffectivePosition = 0;
    
    /**
     * Positioning of text
     */
    private float mTextYPosition = -1.0F;
    private float mTextXPosition = -1.0F;
    
    /**
     * Private size for the text message
     */
    private int mTextSize = 0;
    
    /**
     * Slider cursor position in percentage (between 0f and 1f)
     */
    private float mPositionPerc = 0.0F;
    
    /**
     * 1/mPositionPerc
     */
    private float mPositionPercInv = 1.0F;
    private final int mIconMargin = 0;
    
    /**
     * Margin for Arrow Icon
     */
    private int mArrowMargin;
    
    /**
     * Current angle for Arrow Icon
     */
    private float mArrowAngle = 0.0F;
    
    /**
     * Margin for Tick Icon
     */
    private int mTickMargin;
    
    /**
     * Arrow drawable
     */
    private android.graphics.drawable.Drawable mDrawableArrow;
    
    /**
     * Tick drawable, if is an AnimatedVectorDrawable it will be animated
     */
    private android.graphics.drawable.Drawable mDrawableTick;
    private boolean mFlagDrawTick = false;
    @androidx.annotation.DrawableRes()
    private int completeIcon = 0;
    
    /**
     * Paint used for outer elements
     */
    private final android.graphics.Paint mOuterPaint = null;
    private final android.graphics.Paint mStrokePaint = null;
    
    /**
     * Paint used for inner elements
     */
    private final android.graphics.Paint mInnerPaint = null;
    
    /**
     * Paint used for text elements
     */
    private android.graphics.Paint mTextPaint;
    
    /**
     * TextView used for text elements
     */
    private android.widget.TextView mTextView;
    
    /**
     * Inner rectangle (used for arrow rotation)
     */
    private android.graphics.RectF mInnerRect;
    
    /**
     * Outer rectangle (used for area drawing)
     */
    private android.graphics.RectF mOuterRect;
    
    /**
     * Grace value, when mPositionPerc > mGraceValue slider will perform the 'complete' operations
     */
    private final float mGraceValue = 0.8F;
    
    /**
     * Last X coordinate for the touch event
     */
    private float mLastX = 0.0F;
    
    /**
     * Flag to understand if user is moving the slider cursor
     */
    private boolean mFlagMoving = false;
    
    /**
     * Private flag to check if the slide gesture have been completed
     */
    private boolean mIsCompleted = false;
    
    /**
     * Public flag to lock the slider
     */
    private boolean isLocked = false;
    
    /**
     * Public flag to reverse the slider by 180 degree
     */
    private boolean isReversed = false;
    
    /**
     * Public flag to lock the rotation icon
     */
    private boolean isRotateIcon = true;
    
    /**
     * Public flag to enable complete animation
     */
    private boolean isAnimateCompletion = true;
    
    /**
     * Public Slide event listeners
     */
    @org.jetbrains.annotations.Nullable()
    private com.swipefwd.utils.SlideToActView.OnSlideToActAnimationEventListener onSlideToActAnimationEventListener;
    @org.jetbrains.annotations.Nullable()
    private com.swipefwd.utils.SlideToActView.OnSlideCompleteListener onSlideCompleteListener;
    @org.jetbrains.annotations.Nullable()
    private com.swipefwd.utils.SlideToActView.OnSlideResetListener onSlideResetListener;
    @org.jetbrains.annotations.Nullable()
    private com.swipefwd.utils.SlideToActView.OnSlideUserFailedListener onSlideUserFailedListener;
    
    @kotlin.jvm.JvmOverloads()
    public SlideToActView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public SlideToActView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet xmlAttrs) {
        super(null);
    }
    
    @kotlin.jvm.JvmOverloads()
    public SlideToActView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet xmlAttrs, int defStyleAttr) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.CharSequence getText() {
        return null;
    }
    
    public final void setText(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence value) {
    }
    
    public final int getTypeFace() {
        return 0;
    }
    
    public final void setTypeFace(int value) {
    }
    
    public final int getTextAppearance() {
        return 0;
    }
    
    public final void setTextAppearance(int value) {
    }
    
    public final int getOuterColor() {
        return 0;
    }
    
    public final void setOuterColor(int value) {
    }
    
    public final int getStrokeColor() {
        return 0;
    }
    
    public final void setStrokeColor(int value) {
    }
    
    private final int dpToPx(int $this$dpToPx) {
        return 0;
    }
    
    public final int getInnerColor() {
        return 0;
    }
    
    public final void setInnerColor(int value) {
    }
    
    public final long getAnimDuration() {
        return 0L;
    }
    
    public final void setAnimDuration(long p0) {
    }
    
    public final long getBumpVibration() {
        return 0L;
    }
    
    public final void setBumpVibration(long p0) {
    }
    
    public final int getTextColor() {
        return 0;
    }
    
    public final void setTextColor(int value) {
    }
    
    public final int getIconColor() {
        return 0;
    }
    
    public final void setIconColor(int value) {
    }
    
    public final int getSliderIcon() {
        return 0;
    }
    
    public final void setSliderIcon(int value) {
    }
    
    private final void setMPosition(int value) {
    }
    
    private final void setMEffectivePosition(int value) {
    }
    
    private final void setMTextSize(int value) {
    }
    
    public final int getCompleteIcon() {
        return 0;
    }
    
    public final void setCompleteIcon(int value) {
    }
    
    public final boolean isLocked() {
        return false;
    }
    
    public final void setLocked(boolean p0) {
    }
    
    public final boolean isReversed() {
        return false;
    }
    
    public final void setReversed(boolean value) {
    }
    
    public final boolean isRotateIcon() {
        return false;
    }
    
    public final void setRotateIcon(boolean p0) {
    }
    
    public final boolean isAnimateCompletion() {
        return false;
    }
    
    public final void setAnimateCompletion(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.utils.SlideToActView.OnSlideToActAnimationEventListener getOnSlideToActAnimationEventListener() {
        return null;
    }
    
    public final void setOnSlideToActAnimationEventListener(@org.jetbrains.annotations.Nullable()
    com.swipefwd.utils.SlideToActView.OnSlideToActAnimationEventListener p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.utils.SlideToActView.OnSlideCompleteListener getOnSlideCompleteListener() {
        return null;
    }
    
    public final void setOnSlideCompleteListener(@org.jetbrains.annotations.Nullable()
    com.swipefwd.utils.SlideToActView.OnSlideCompleteListener p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.utils.SlideToActView.OnSlideResetListener getOnSlideResetListener() {
        return null;
    }
    
    public final void setOnSlideResetListener(@org.jetbrains.annotations.Nullable()
    com.swipefwd.utils.SlideToActView.OnSlideResetListener p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.utils.SlideToActView.OnSlideUserFailedListener getOnSlideUserFailedListener() {
        return null;
    }
    
    public final void setOnSlideUserFailedListener(@org.jetbrains.annotations.Nullable()
    com.swipefwd.utils.SlideToActView.OnSlideUserFailedListener p0) {
    }
    
    @java.lang.Override()
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    }
    
    @java.lang.Override()
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    }
    
    @java.lang.Override()
    protected void onDraw(@org.jetbrains.annotations.Nullable()
    android.graphics.Canvas canvas) {
    }
    
    @kotlin.Suppress(names = {"RedundantOverride"})
    @java.lang.Override()
    public boolean performClick() {
        return false;
    }
    
    @java.lang.Override()
    public boolean onTouchEvent(@org.jetbrains.annotations.Nullable()
    android.view.MotionEvent event) {
        return false;
    }
    
    /**
     * Private method to check if user has touched the slider cursor
     * @param x The x coordinate of the touch event
     * @param y The y coordinate of the touch event
     * @return A boolean that informs if user has pressed or not
     */
    private final boolean checkInsideButton(float x, float y) {
        return false;
    }
    
    /**
     * Private method for increasing/decreasing the position
     * Ensure that position never exits from its range [0, (mAreaWidth - mAreaHeight)].
     *
     * Please note that the increment is inverted in case of a reversed slider.
     *
     * @param inc Increment to be performed (negative if it's a decrement)
     */
    private final void increasePosition(int inc) {
    }
    
    /**
     * Private method that is performed when user completes the slide
     */
    private final void startAnimationComplete() {
    }
    
    /**
     * Method that completes the slider
     */
    public final void completeSlider() {
    }
    
    /**
     * Method that reset the slider
     */
    public final void resetSlider() {
    }
    
    /**
     * Method that returns the 'mIsCompleted' flag
     * @return True if slider is in the Complete state
     */
    public final boolean isCompleted() {
        return false;
    }
    
    public final void resetSliderNoAnim() {
    }
    
    /**
     * Private method that is performed when you want to reset the cursor
     */
    private final void startAnimationReset() {
    }
    
    /**
     * Private method to handle vibration logic, called when the cursor it moved to the end of
     * it's path.
     */
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final void handleVibration() {
    }
    
    /**
     * Event handler for the SlideToActView animation events.
     * This event handler can be used to react to animation events from the Slide,
     * the event will be fired whenever an animation start/end.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u000b"}, d2 = {"Lcom/swipefwd/utils/SlideToActView$OnSlideToActAnimationEventListener;", "", "onSlideCompleteAnimationEnded", "", "view", "Lcom/swipefwd/utils/SlideToActView;", "onSlideCompleteAnimationStarted", "threshold", "", "onSlideResetAnimationEnded", "onSlideResetAnimationStarted", "app_debug"})
    public static abstract interface OnSlideToActAnimationEventListener {
        
        /**
         * Called when the slide complete animation start. You can perform actions during the
         * complete animations.
         *
         * @param view The SlideToActView who created the event
         * @param threshold The mPosition (in percentage [0f,1f]) where the user has left the cursor
         */
        public abstract void onSlideCompleteAnimationStarted(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.SlideToActView view, float threshold);
        
        /**
         * Called when the slide complete animation finish. At this point the slider is stuck in the
         * center of the slider.
         *
         * @param view The SlideToActView who created the event
         */
        public abstract void onSlideCompleteAnimationEnded(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.SlideToActView view);
        
        /**
         * Called when the slide reset animation start. You can perform actions during the reset
         * animations.
         *
         * @param view The SlideToActView who created the event
         */
        public abstract void onSlideResetAnimationStarted(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.SlideToActView view);
        
        /**
         * Called when the slide reset animation finish. At this point the slider will be in the
         * ready on the left of the screen and user can interact with it.
         *
         * @param view The SlideToActView who created the event
         */
        public abstract void onSlideResetAnimationEnded(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.SlideToActView view);
    }
    
    /**
     * Event handler for the slide complete event.
     * Use this handler to react to slide event
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/swipefwd/utils/SlideToActView$OnSlideCompleteListener;", "", "onSlideComplete", "", "view", "Lcom/swipefwd/utils/SlideToActView;", "app_debug"})
    public static abstract interface OnSlideCompleteListener {
        
        /**
         * Called when user performed the slide
         * @param view The SlideToActView who created the event
         */
        public abstract void onSlideComplete(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.SlideToActView view);
    }
    
    /**
     * Event handler for the slide react event.
     * Use this handler to inform the user that he can slide again.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/swipefwd/utils/SlideToActView$OnSlideResetListener;", "", "onSlideReset", "", "view", "Lcom/swipefwd/utils/SlideToActView;", "app_debug"})
    public static abstract interface OnSlideResetListener {
        
        /**
         * Called when slides is again available
         * @param view The SlideToActView who created the event
         */
        public abstract void onSlideReset(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.SlideToActView view);
    }
    
    /**
     * Event handler for the user failure with the Widget.
     * You can subscribe to this event to get notified when the user is wrongly
     * interacting with the widget to eventually educate it:
     *
     * - The user clicked outside of the cursor
     * - The user slided but left when the cursor was back to zero
     *
     * You can use this listener to show a Toast or other messages.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/swipefwd/utils/SlideToActView$OnSlideUserFailedListener;", "", "onSlideFailed", "", "view", "Lcom/swipefwd/utils/SlideToActView;", "isOutside", "", "app_debug"})
    public static abstract interface OnSlideUserFailedListener {
        
        /**
         * Called when user failed to interact with the slider slide
         * @param view The SlideToActView who created the event
         * @param isOutside True if user pressed outside the cursor
         */
        public abstract void onSlideFailed(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.SlideToActView view, boolean isOutside);
    }
    
    /**
     * Outline provider for the SlideToActView.
     * This outline will suppress the shadow (till the moment when Android will support
     * updatable Outlines).
     */
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.LOLLIPOP)
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0083\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016\u00a8\u0006\t"}, d2 = {"Lcom/swipefwd/utils/SlideToActView$SlideToActOutlineProvider;", "Landroid/view/ViewOutlineProvider;", "(Lcom/swipefwd/utils/SlideToActView;)V", "getOutline", "", "view", "Landroid/view/View;", "outline", "Landroid/graphics/Outline;", "app_debug"})
    final class SlideToActOutlineProvider extends android.view.ViewOutlineProvider {
        
        public SlideToActOutlineProvider() {
            super();
        }
        
        @java.lang.Override()
        public void getOutline(@org.jetbrains.annotations.Nullable()
        android.view.View view, @org.jetbrains.annotations.Nullable()
        android.graphics.Outline outline) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/utils/SlideToActView$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}