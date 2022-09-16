package com.swipefwd.utils.customprogressbar


import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.swipefwd.R
import com.swipefwd.utils.customprogressbar.CalculationUtil.convertDpToPx


class SquareProgressBar : RelativeLayout {
    private var imageView: ShapeableImageView
    private val bar: SquareProgressView

    /**
     * If opacity is enabled.
     *
     * @return true if opacity is enabled.
     */
    var isOpacity = false
        private set

    /**
     * If greyscale is enabled.
     *
     * @return true if greyscale is enabled.
     */
    var isGreyscale = false
        private set
    private var isFadingOnProgress = false
    /**
     * Returns a boolean if rounded corners is active or not.
     *
     * @return true if rounded corners is active.
     * @since 1.6.2
     */
    /**
     * Activates the drawing of rounded corners.
     *
     * @since 1.6.2
     */
    var roundedCorners: Boolean
        get() = bar.isRoundedCorners
        set(useRoundedCorners) {
            bar.setRoundedCorners(useRoundedCorners, 16.toFloat())
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
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        val mInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mInflater.inflate(R.layout.progressbarview, this, true)
        bar = findViewById<View>(R.id.squareProgressBar1) as SquareProgressView
        imageView = findViewById<View>(R.id.imageView1) as ShapeableImageView
        bar.bringToFront()
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
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val mInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mInflater.inflate(R.layout.progressbarview, this, true)
        bar = findViewById<View>(R.id.squareProgressBar1) as SquareProgressView
        imageView = findViewById<View>(R.id.imageView1) as ShapeableImageView
        bar.bringToFront()
    }

    /**
     * New SquareProgressBar.
     *
     * @param context the context
     * @since 1.0.0
     */
    constructor(context: Context) : super(context) {
        val mInflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mInflater.inflate(R.layout.progressbarview, this, true)
        bar = findViewById<View>(R.id.squareProgressBar1) as SquareProgressView
        imageView = findViewById<View>(R.id.imageView1) as ShapeableImageView
        bar.bringToFront()
    }

    /**
     * Sets the image of the [SquareProgressBar]. Must be a valid
     * ressourceId.
     *
     * @param image
     * the image as a ressourceId
     * @since 1.0
     */
    fun setImage(image: Int) {

        imageView.setImageResource(image)
        imageView.shapeAppearanceModel = imageView.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, 29F)
            .build()

    }

    /**
     * Sets the image of the [SquareProgressBar]. Must be a valid
     * Drawable.
     *
     * @param imageDrawable the image as a Drawable
     * @since 1.6.1
     * @author erikswed
     */
    fun setImageDrawable(imageDrawable: Drawable?) {
        imageView.setImageDrawable(imageDrawable)
    }

    /**
     * Sets the image scale type according to [ScaleType].
     *
     * @param scale
     * the image ScaleType
     * @since 1.3.0
     * @author thiagokimo
     */
    fun setImageScaleType(scale: ImageView.ScaleType?) {
        imageView.setScaleType(scale)
    }

    /**
     * Sets the colour of the [SquareProgressBar] to a predefined android
     * holo color.
     * **Examples:**
     *
     *  * holo_blue_bright
     *  * holo_blue_dark
     *  * holo_blue_light
     *  * holo_green_dark
     *  * holo_green_light
     *  * holo_orange_dark
     *  * holo_orange_light
     *  * holo_purple
     *  * holo_red_dark
     *  * holo_red_light
     *
     *
     * @param androidHoloColor holo color value
     * @since 1.0.0
     */
    fun setHoloColor(androidHoloColor: Int) {
        bar.setColor(context.resources.getColor(androidHoloColor))
    }

    /**
     * Sets the colour of the [SquareProgressBar]. YOu can give it a
     * hex-color string like *#C9C9C9*.
     *
     * @param colorString
     * the colour of the [SquareProgressBar]
     * @since 1.1.0
     */
    fun setColor(colorString: String?) {
        bar.setColor(Color.parseColor(colorString))
    }

    /**
     *7y67uuy9hui87j,h This sets the colour of the [SquareProgressBar] with a RGB colour.
     *
     * @param r
     * red
     * @param g
     * green
     * @param b
     * blue
     * @since 1.1.0
     */
    fun setColorRGB(r: Int, g: Int, b: Int) {
        bar.setColor(Color.rgb(r, g, b))
    }

    /**
     * This sets the colour of the [SquareProgressBar] with a RGB colour.
     * Works when used with
     * `android.graphics.Color.rgb(int)`
     *
     * @param rgb the rgb color
     * @since 1.4.0
     */
    fun setColorRGB(rgb: Int) {
        bar.setColor(rgb)
    }

    /**
     * This sets the width of the [SquareProgressBar].
     *
     * @param width
     * in Dp
     * @since 1.1.0
     */
    fun setWidth(width: Int) {
        val padding = convertDpToPx(width.toFloat(), context)
        imageView.setPadding(padding, padding, padding, padding)
        bar.setWidthInDp(width)
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
    private fun setOpacity(progress: Int) {
        imageView.setAlpha((2.55 * progress).toInt())
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
    fun setOpacity(opacity: Boolean) {
        isOpacity = opacity
        progress = bar.getProgress()
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
    fun setOpacity(opacity: Boolean, isFadingOnProgress: Boolean) {
        isOpacity = opacity
        this.isFadingOnProgress = isFadingOnProgress
        progress = bar.getProgress()
    }

    /**
     * You can set the image to b/w with this method. Works fine with the
     * opacity.
     *
     * @param greyscale
     * true if the grayscale should be activated.
     * @since 1.2.0
     */
    fun setImageGrayscale(greyscale: Boolean) {
        isGreyscale = greyscale
        if (greyscale) {
            val matrix = ColorMatrix()
            matrix.setSaturation(0f)
            imageView.setColorFilter(ColorMatrixColorFilter(matrix))
        } else {
            imageView.setColorFilter(null)
        }
    }

    /**
     * Draws an outline of the progressbar. Looks quite cool in some situations.
     *
     * @param drawOutline
     * true if it should or not.
     * @since 1.3.0
     */
    fun drawOutline(drawOutline: Boolean) {
        bar.isOutline = drawOutline
    }

    /**
     * If outline is enabled or not.
     *
     * @return true if outline is enabled.
     */
    val isOutline: Boolean
        get() = bar.isOutline

    /**
     * Draws the startline. this is the line where the progressbar starts the
     * drawing around the image.
     *
     * @param drawStartline
     * true if it should or not.
     * @since 1.3.0
     */
    fun drawStartline(drawStartline: Boolean) {
        bar.isStartline = drawStartline
    }

    /**
     * If the startline is enabled.
     *
     * @return true if startline is enabled or not.
     */
    val isStartline: Boolean
        get() = bar.isStartline

    /**
     * Defines if the percent text should be shown or not. To modify the text
     * checkout [.setPercentStyle].
     *
     * @param showProgress
     * true if it should or not.
     * @since 1.3.0
     */
    fun showProgress(showProgress: Boolean) {
        bar.isShowProgress = showProgress
    }

    /**
     * If the progress text inside of the image is enabled.
     *
     * @return true if it is or not.
     */
    val isShowProgress: Boolean
        get() = bar.isShowProgress
    /**
     * Returns the [PercentStyle] of the percent text. Maybe returns the
     * default value, check [.setPercentStyle] fo that.
     *
     * @return the percent style of the moment.
     */
    /**
     * Sets a custom percent style to the text inside the image. Make sure you
     * set [.showProgress] to true. Otherwise it doesn't shows.
     * The default settings are:
     * Text align: CENTER
     * Text size: 150 [dp]
     * Display percentsign: true
     * Custom text: %
     *
     * @param percentStyle the percent style
     */
    var percentStyle: PercentStyle?
        get() = bar.percentStyle
        set(percentStyle) {
            bar.percentStyle = percentStyle!!
        }
    /**
     * If the progressbar disappears when the progress reaches 100%.
     *
     * @since 1.4.0
     *
     * @return if "clearOnHundred" is enabled or not
     */
    /**
     * If the progress hits 100% then the progressbar disappears if this flag is
     * set to `true`. The default is set to false.
     *
     * @param clearOnHundred
     * if it should disappear or not.
     * @since 1.4.0
     */
    var isClearOnHundred: Boolean
        get() = bar.isClearOnHundred
        set(clearOnHundred) {
            bar.isClearOnHundred = clearOnHundred
        }

    /**
     * Set an image resource directly to the ImageView.
     *
     * @param bitmap the [android.graphics.Bitmap] to set.
     */
    fun setImageBitmap(bitmap: Bitmap?) {
        imageView.setImageBitmap(bitmap)
        imageView.shapeAppearanceModel = imageView.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, 15F)
            .build()
    }
    /**
     * Returns the status of the indeterminate mode. The default status is false.
     *
     * @since 1.6.0
     *
     * @return if "indeterminate" is enabled or not
     */
    /**
     * Set the status of the indeterminate mode. The default is false. You can
     * still configure colour, width and so on.
     *
     * @param indeterminate true to enable the indeterminate mode (default true)
     * @since 1.6.0
     */
    var isIndeterminate: Boolean
        get() = bar.isIndeterminate
        set(indeterminate) {
            bar.isIndeterminate = indeterminate
        }

    /**
     * Draws a line in the center of the way the progressbar has to go.
     *
     * @param drawCenterline
     * true if it should or not.
     * @since 1.6.0
     */
    fun drawCenterline(drawCenterline: Boolean) {
        bar.isCenterline = drawCenterline
    }

    /**
     * If the centerline is enabled or not.
     *
     * @return true if centerline is enabled.
     * @since 1.6.0
     */
    val isCenterline: Boolean
        get() = bar.isCenterline

    /**
     * Returns the [ImageView] that the progress gets drawn around.
     *
     * @return the main ImageView
     * @since 1.6.0
     */
    fun getImageView(): ImageView {
        return imageView
    }
    /**
     * Returns the progress of the progressbar as a double.
     *
     * @return the current progress as double.
     * @since 1.6.2
     */
    /**
     * Sets the progress of the [SquareProgressBar]. If opacity is
     * selected then here it sets it. See [.setOpacity] for more
     * information.
     *
     * @param progress
     * the progress
     * @since 1.0.0
     */
    var progress: Double
        get() = bar.getProgress()
        set(progress) {
            bar.setProgress(progress)
            if (isOpacity) {
                if (isFadingOnProgress) {
                    setOpacity(100 - progress.toInt())
                } else {
                    setOpacity(progress.toInt())
                }
            } else {
                setOpacity(100)
            }
        }

    /**
     * Sets the progress as an integer value. This is mainly used for animations.
     *
     * @param progress as an integer value.
     * @since 1.6.2
     */
    fun setProgress(progress: Int) {
        this.progress = progress.toDouble()
    }

    /**
     * Activates the drawing of rounded corners with a given radius.
     *
     * @since 1.6.2
     */
    fun setRoundedCorners(useRoundedCorners: Boolean, radius: Float) {
        bar.setRoundedCorners(useRoundedCorners, radius)
    }
}