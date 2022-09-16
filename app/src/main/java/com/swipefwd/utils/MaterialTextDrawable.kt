package com.swipefwd.utils

import android.content.Context
import android.graphics.*
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.drawable.BitmapDrawable
import android.os.Looper
import android.text.TextPaint
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.swipefwd.R
import java.util.*

class MaterialTextDrawable constructor(builder: Builder) {

    companion object {
        fun with(context: Context): Builder = Builder().with(context)
        private fun isOnMainThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()
        var displayBackgroundWhite: Boolean = true
    }

    enum class MaterialShape {
        CIRCLE,
        RECTANGLE
    }

    private var context: Context
    private var size: Int
    private var drawableShape: MaterialShape
    private var text: String
    private var firstColor: Int
    private var secondColor: Int
    private var isModify: Boolean


    init {
        this.context = builder.context
        this.size = builder.size
        this.drawableShape = builder.drawableShape
        this.text = builder.text
        this.firstColor = builder.firstColor
        this.secondColor = builder.secondColor
        this.isModify = builder.isModify // this bit is added to change text size on some screens
    }

    class Builder {

        internal lateinit var context: Context
        internal var size = 150
        internal var drawableShape: MaterialShape = MaterialShape.CIRCLE
        internal var text: String = ""
        internal var firstColor: Int = R.color.blue_gradient_2
        internal var secondColor: Int = R.color.blue_gradient_3
        internal var isModify: Boolean = false

        fun with(context: Context): Builder {
            this.context = context
            return this
        }

        fun textSize(size: Int): Builder {
            this.size = size
            return this
        }

        fun shaderColor(firstColor: Int, secondColor: Int): Builder {
            this.firstColor = firstColor
            this.secondColor = secondColor
            return this
        }

        fun shape(shape: MaterialShape): Builder {
            this.drawableShape = shape
            return this
        }

        fun text(text: String): Builder {
            this.text = text
            return this
        }

        fun isModify(modify: Boolean): Builder {
            this.isModify = modify
            return this
        }

        fun backgroundHandle(displayBackgroundWhite: Boolean) : Builder {
            MaterialTextDrawable.displayBackgroundWhite = displayBackgroundWhite
            return this
        }

        fun get(): BitmapDrawable {
            if (text == "") {
                throw NullPointerException(
                    "No text provided, " +
                            "call text(<your_text>) before calling this method"
                )
            }
            return MaterialTextDrawable(this).getTextDrawable()
        }

        fun into(view: ImageView) {
            if (!isOnMainThread()) {
                throw IllegalArgumentException("You must call this method on the main thread")
            }
            // Set text-drawable
            view.setImageDrawable(get())
        }

        fun into(view: ImageView, scale: ImageView.ScaleType) {
            view.scaleType = scale
            into(view)
        }

    }

    private fun getTextDrawable(): BitmapDrawable {
        val initials = if (text.length > 1) {
            getFirstChar(text)
        } else {
            text
        }
        var calculatedVSize = 0F
        calculatedVSize = if (isModify)
            calculateTextSizeAccordingToSize(this.size)
        else
            calculateTextSize(this.size)
        val textPaint = textPainter(calculatedVSize)
        val painter = Paint()
        painter.isAntiAlias = true

        painter.color = if (drawableShape == MaterialShape.RECTANGLE) {
            Color.WHITE
        } else {
            Color.TRANSPARENT
        }

        val areaRectangle = Rect(0, 0, size, size)
        val bitmap = Bitmap.createBitmap(size, size, ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawRect(areaRectangle, painter)

        if (drawableShape == MaterialShape.RECTANGLE) {
            painter.color = Color.TRANSPARENT
        } else {
            painter.color = Color.WHITE
        }

        if (!displayBackgroundWhite){
            painter.color =  Color.TRANSPARENT
        }

        val bounds = RectF(areaRectangle)
        bounds.right = textPaint.measureText(initials, 0, 1)
        bounds.bottom = textPaint.descent() - textPaint.ascent()

        bounds.left += (areaRectangle.width() - bounds.right) / 2.0f
        bounds.top += (areaRectangle.height() - bounds.bottom) / 2.0f

        canvas.drawCircle(size.toFloat() / 2, size.toFloat() / 2, size.toFloat() / 2, painter)
        canvas.drawText(initials, bounds.left, bounds.top - textPaint.ascent(), textPaint)
        return BitmapDrawable(context.resources, bitmap)
    }

    private fun calculateTextSize(size: Int): Float {
        return (size / 4.125).toFloat()
    }

    private fun calculateTextSizeAccordingToSize(size: Int): Float {
        return (size / 10).toFloat()
    }

    private fun getFirstChar(text: String): String {
        return text.first().toString().uppercase(Locale.ROOT)
    }

    private fun textPainter(size: Float): TextPaint {
        val textPaint = TextPaint()
        textPaint.apply {
            isAntiAlias = true
            textSize = size * context.resources.displayMetrics.scaledDensity
            typeface = Typeface.create("sans-serif-light", Typeface.BOLD)
        }
        val width = textPaint.measureText(text)
        val textShader: Shader = LinearGradient(
            0f,
            0f,
            width / 2,
            0.5f,
            intArrayOf(
                ContextCompat.getColor(
                    context,
                    firstColor
                ),
                ContextCompat.getColor(
                    context,
                    secondColor
                )
            ),
            null,
            Shader.TileMode.CLAMP
        )
        textPaint.shader = textShader
        return textPaint
    }
}