package com.swipefwd.utils.customprogressbar;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\tB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\nJ\u000e\u00100\u001a\u0002012\u0006\u00100\u001a\u00020\u0010J\u000e\u00102\u001a\u0002012\u0006\u00102\u001a\u00020\u0010J\u000e\u00103\u001a\u0002012\u0006\u00103\u001a\u00020\u0010J\u0006\u00104\u001a\u000205J\u0010\u00106\u001a\u0002012\b\u00107\u001a\u0004\u0018\u000108J\u000e\u00109\u001a\u0002012\u0006\u0010:\u001a\u00020\u0007J\u001e\u00109\u001a\u0002012\u0006\u0010;\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u0007J\u000e\u0010>\u001a\u0002012\u0006\u0010?\u001a\u00020\u0007J\u000e\u0010@\u001a\u0002012\u0006\u0010A\u001a\u00020\u0007J\u0010\u0010B\u001a\u0002012\b\u0010C\u001a\u0004\u0018\u00010DJ\u0010\u0010E\u001a\u0002012\b\u0010F\u001a\u0004\u0018\u00010GJ\u000e\u0010H\u001a\u0002012\u0006\u0010I\u001a\u00020\u0010J\u0010\u0010J\u001a\u0002012\b\u0010K\u001a\u0004\u0018\u00010LJ\u000e\u0010M\u001a\u0002012\u0006\u0010N\u001a\u00020\u0010J\u0016\u0010M\u001a\u0002012\u0006\u0010N\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0010J\u0010\u0010M\u001a\u0002012\u0006\u0010&\u001a\u00020\u0007H\u0002J\u000e\u0010*\u001a\u0002012\u0006\u0010&\u001a\u00020\u0007J\u0016\u0010/\u001a\u0002012\u0006\u0010,\u001a\u00020\u00102\u0006\u0010O\u001a\u00020PJ\u000e\u0010Q\u001a\u0002012\u0006\u0010R\u001a\u00020\u0007J\u000e\u0010S\u001a\u0002012\u0006\u0010S\u001a\u00020\u0010R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R$\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00108F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0013\u0010\u0011\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0010@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R$\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00108F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0015R\u001e\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0010@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011R\u0011\u0010\u001d\u001a\u00020\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u0011R\u0011\u0010\u001e\u001a\u00020\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u0011R\u0011\u0010\u001f\u001a\u00020\u00108F\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u0011R(\u0010 \u001a\u0004\u0018\u00010!2\b\u0010 \u001a\u0004\u0018\u00010!8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010&\u001a\u00020\'2\u0006\u0010&\u001a\u00020\'8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R$\u0010-\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u00108F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b.\u0010\u0011\"\u0004\b/\u0010\u0015\u00a8\u0006T"}, d2 = {"Lcom/swipefwd/utils/customprogressbar/SquareProgressBar;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "(Landroid/content/Context;)V", "bar", "Lcom/swipefwd/utils/customprogressbar/SquareProgressView;", "imageView", "Lcom/google/android/material/imageview/ShapeableImageView;", "isCenterline", "", "()Z", "clearOnHundred", "isClearOnHundred", "setClearOnHundred", "(Z)V", "isFadingOnProgress", "<set-?>", "isGreyscale", "indeterminate", "isIndeterminate", "setIndeterminate", "isOpacity", "isOutline", "isShowProgress", "isStartline", "percentStyle", "Lcom/swipefwd/utils/customprogressbar/PercentStyle;", "getPercentStyle", "()Lcom/swipefwd/utils/customprogressbar/PercentStyle;", "setPercentStyle", "(Lcom/swipefwd/utils/customprogressbar/PercentStyle;)V", "progress", "", "getProgress", "()D", "setProgress", "(D)V", "useRoundedCorners", "roundedCorners", "getRoundedCorners", "setRoundedCorners", "drawCenterline", "", "drawOutline", "drawStartline", "getImageView", "Landroid/widget/ImageView;", "setColor", "colorString", "", "setColorRGB", "rgb", "r", "g", "b", "setHoloColor", "androidHoloColor", "setImage", "image", "setImageBitmap", "bitmap", "Landroid/graphics/Bitmap;", "setImageDrawable", "imageDrawable", "Landroid/graphics/drawable/Drawable;", "setImageGrayscale", "greyscale", "setImageScaleType", "scale", "Landroid/widget/ImageView$ScaleType;", "setOpacity", "opacity", "radius", "", "setWidth", "width", "showProgress", "app_debug"})
public final class SquareProgressBar extends android.widget.RelativeLayout {
    private com.google.android.material.imageview.ShapeableImageView imageView;
    private final com.swipefwd.utils.customprogressbar.SquareProgressView bar = null;
    
    /**
     * If opacity is enabled.
     *
     * @return true if opacity is enabled.
     */
    private boolean isOpacity = false;
    
    /**
     * If greyscale is enabled.
     *
     * @return true if greyscale is enabled.
     */
    private boolean isGreyscale = false;
    private boolean isFadingOnProgress = false;
    
    public final boolean isOpacity() {
        return false;
    }
    
    public final boolean isGreyscale() {
        return false;
    }
    
    public final boolean getRoundedCorners() {
        return false;
    }
    
    public final void setRoundedCorners(boolean useRoundedCorners) {
    }
    
    /**
     * New SquareProgressBar.
     *
     * @param context
     * the [Context]
     * @param attrs
     * an [AttributeSet]
     * @param defStyle
     * a defined style.
     * @since 1.0.0
     */
    public SquareProgressBar(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs, int defStyle) {
        super(null);
    }
    
    /**
     * New SquareProgressBar.
     *
     * @param context
     * the [Context]
     * @param attrs
     * an [AttributeSet]
     * @since 1.0.0
     */
    public SquareProgressBar(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.util.AttributeSet attrs) {
        super(null);
    }
    
    /**
     * New SquareProgressBar.
     *
     * @param context the context
     * @since 1.0.0
     */
    public SquareProgressBar(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    /**
     * Sets the image of the [SquareProgressBar]. Must be a valid
     * ressourceId.
     *
     * @param image
     * the image as a ressourceId
     * @since 1.0
     */
    public final void setImage(int image) {
    }
    
    /**
     * Sets the image of the [SquareProgressBar]. Must be a valid
     * Drawable.
     *
     * @param imageDrawable the image as a Drawable
     * @since 1.6.1
     * @author erikswed
     */
    public final void setImageDrawable(@org.jetbrains.annotations.Nullable()
    android.graphics.drawable.Drawable imageDrawable) {
    }
    
    /**
     * Sets the image scale type according to [ScaleType].
     *
     * @param scale
     * the image ScaleType
     * @since 1.3.0
     * @author thiagokimo
     */
    public final void setImageScaleType(@org.jetbrains.annotations.Nullable()
    android.widget.ImageView.ScaleType scale) {
    }
    
    /**
     * Sets the colour of the [SquareProgressBar] to a predefined android
     * holo color.
     * **Examples:**
     *
     * * holo_blue_bright
     * * holo_blue_dark
     * * holo_blue_light
     * * holo_green_dark
     * * holo_green_light
     * * holo_orange_dark
     * * holo_orange_light
     * * holo_purple
     * * holo_red_dark
     * * holo_red_light
     *
     *
     * @param androidHoloColor holo color value
     * @since 1.0.0
     */
    public final void setHoloColor(int androidHoloColor) {
    }
    
    /**
     * Sets the colour of the [SquareProgressBar]. YOu can give it a
     * hex-color string like *#C9C9C9*.
     *
     * @param colorString
     * the colour of the [SquareProgressBar]
     * @since 1.1.0
     */
    public final void setColor(@org.jetbrains.annotations.Nullable()
    java.lang.String colorString) {
    }
    
    /**
     * 7y67uuy9hui87j,h This sets the colour of the [SquareProgressBar] with a RGB colour.
     *
     * @param r
     * red
     * @param g
     * green
     * @param b
     * blue
     * @since 1.1.0
     */
    public final void setColorRGB(int r, int g, int b) {
    }
    
    /**
     * This sets the colour of the [SquareProgressBar] with a RGB colour.
     * Works when used with
     * `android.graphics.Color.rgb(int)`
     *
     * @param rgb the rgb color
     * @since 1.4.0
     */
    public final void setColorRGB(int rgb) {
    }
    
    /**
     * This sets the width of the [SquareProgressBar].
     *
     * @param width
     * in Dp
     * @since 1.1.0
     */
    public final void setWidth(int width) {
    }
    
    /**
     * This sets the alpha of the image in the view. Actually I need to use the
     * deprecated method here as the new one is only available for the API-level
     * 16. And the min API level of this library is 14.
     *
     * Use this only as private method.
     *
     * @param progress
     * the progress
     */
    private final void setOpacity(int progress) {
    }
    
    /**
     * Switches the opacity state of the image. This forces the
     * SquareProgressBar to redraw with the current progress. As bigger the
     * progress is, then more of the image comes to view. If the progress is 0,
     * then you can't see the image at all. If the progress is 100, the image is
     * shown full.
     *
     * @param opacity
     * true if opacity should be enabled.
     * @since 1.2.0
     */
    public final void setOpacity(boolean opacity) {
    }
    
    /**
     * Switches the opacity state of the image. This forces the
     * SquareProgressBar to redraw with the current progress. As bigger the
     * progress is, then more of the image comes to view. If the progress is 0,
     * then you can't see the image at all. If the progress is 100, the image is
     * shown full.
     *
     * You can also set the flag if the fading should get inverted so the image
     * disappears when the progress increases.
     *
     * @param opacity
     * true if opacity should be enabled.
     * @param isFadingOnProgress
     * default false. This changes the behavior the opacity works. If
     * the progress increases then the images fades. When the
     * progress reaches 100, then the image disappears.
     * @since 1.4.0
     */
    public final void setOpacity(boolean opacity, boolean isFadingOnProgress) {
    }
    
    /**
     * You can set the image to b/w with this method. Works fine with the
     * opacity.
     *
     * @param greyscale
     * true if the grayscale should be activated.
     * @since 1.2.0
     */
    public final void setImageGrayscale(boolean greyscale) {
    }
    
    /**
     * Draws an outline of the progressbar. Looks quite cool in some situations.
     *
     * @param drawOutline
     * true if it should or not.
     * @since 1.3.0
     */
    public final void drawOutline(boolean drawOutline) {
    }
    
    public final boolean isOutline() {
        return false;
    }
    
    /**
     * Draws the startline. this is the line where the progressbar starts the
     * drawing around the image.
     *
     * @param drawStartline
     * true if it should or not.
     * @since 1.3.0
     */
    public final void drawStartline(boolean drawStartline) {
    }
    
    public final boolean isStartline() {
        return false;
    }
    
    /**
     * Defines if the percent text should be shown or not. To modify the text
     * checkout [.setPercentStyle].
     *
     * @param showProgress
     * true if it should or not.
     * @since 1.3.0
     */
    public final void showProgress(boolean showProgress) {
    }
    
    public final boolean isShowProgress() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.utils.customprogressbar.PercentStyle getPercentStyle() {
        return null;
    }
    
    public final void setPercentStyle(@org.jetbrains.annotations.Nullable()
    com.swipefwd.utils.customprogressbar.PercentStyle percentStyle) {
    }
    
    public final boolean isClearOnHundred() {
        return false;
    }
    
    public final void setClearOnHundred(boolean clearOnHundred) {
    }
    
    /**
     * Set an image resource directly to the ImageView.
     *
     * @param bitmap the [android.graphics.Bitmap] to set.
     */
    public final void setImageBitmap(@org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap bitmap) {
    }
    
    public final boolean isIndeterminate() {
        return false;
    }
    
    public final void setIndeterminate(boolean indeterminate) {
    }
    
    /**
     * Draws a line in the center of the way the progressbar has to go.
     *
     * @param drawCenterline
     * true if it should or not.
     * @since 1.6.0
     */
    public final void drawCenterline(boolean drawCenterline) {
    }
    
    public final boolean isCenterline() {
        return false;
    }
    
    /**
     * Returns the [ImageView] that the progress gets drawn around.
     *
     * @return the main ImageView
     * @since 1.6.0
     */
    @org.jetbrains.annotations.NotNull()
    public final android.widget.ImageView getImageView() {
        return null;
    }
    
    public final double getProgress() {
        return 0.0;
    }
    
    public final void setProgress(double progress) {
    }
    
    /**
     * Sets the progress as an integer value. This is mainly used for animations.
     *
     * @param progress as an integer value.
     * @since 1.6.2
     */
    public final void setProgress(int progress) {
    }
    
    /**
     * Activates the drawing of rounded corners with a given radius.
     *
     * @since 1.6.2
     */
    public final void setRoundedCorners(boolean useRoundedCorners, float radius) {
    }
}