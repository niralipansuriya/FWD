package com.swipefwd.utils.otpView


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.swipefwd.R
import java.util.*
import java.util.regex.Pattern

class OtpTextView : FrameLayout {

    private var mContext: Context? = null
    private var itemViews: MutableList<ItemView>? = null
    private var otpChildEditText: OTPChildEditText? = null
    var otpListener: OTPListener? = null

    private var length: Int = 0

    private val filter: InputFilter
        get() = InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!Pattern.compile(
                        PATTERN
                    )
                        .matcher(source[i].toString())
                        .matches()
                ) {
                    return@InputFilter ""
                }
            }
            null
        }

    val otp: String?
        get() = if (otpChildEditText != null && otpChildEditText!!.text != null) otpChildEditText!!.text!!.toString() else null

    constructor(context: Context) : super(context) {
        this.mContext = context
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.mContext = context
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.mContext = context
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val styles = getContext().obtainStyledAttributes(attrs, R.styleable.OtpTextView)
        styleEditTexts(styles, attrs)
        styles.recycle()
    }

    private fun styleEditTexts(styles: TypedArray, attrs: AttributeSet?) {
        length = styles.getInt(R.styleable.OtpTextView_length, DEFAULT_LENGTH)
        generateViews(styles, attrs)
    }

    private fun generateViews(styles: TypedArray, attrs: AttributeSet?) {
        itemViews = ArrayList()
        if (length > 0) {
            val otp = styles.getString(R.styleable.OtpTextView_otp)
            val width = styles.getDimension(
                R.styleable.OtpTextView_width,
                Utils.getPixels(mContext!!, DEFAULT_WIDTH).toFloat()
            ).toInt()
            val height = styles.getDimension(
                R.styleable.OtpTextView_height,
                Utils.getPixels(mContext!!, DEFAULT_HEIGHT).toFloat()
            ).toInt()
            val space = styles.getDimension(
                R.styleable.OtpTextView_box_margin,
                Utils.getPixels(mContext!!, DEFAULT_SPACE).toFloat()
            ).toInt()
            val spaceLeft = styles.getDimension(
                R.styleable.OtpTextView_box_margin_left,
                Utils.getPixels(mContext!!, DEFAULT_SPACE_LEFT).toFloat()
            ).toInt()
            val spaceRight = styles.getDimension(
                R.styleable.OtpTextView_box_margin_right,
                Utils.getPixels(mContext!!, DEFAULT_SPACE_RIGHT).toFloat()
            ).toInt()
            val spaceTop = styles.getDimension(
                R.styleable.OtpTextView_box_margin_top,
                Utils.getPixels(mContext!!, DEFAULT_SPACE_TOP).toFloat()
            ).toInt()
            val spaceBottom = styles.getDimension(
                R.styleable.OtpTextView_box_margin_bottom,
                Utils.getPixels(mContext!!, DEFAULT_SPACE_BOTTOM).toFloat()
            ).toInt()
            val params = LinearLayout.LayoutParams(width, height)
            if (space > 0) {
                params.setMargins(space, space, space, space)
            } else {
                params.setMargins(spaceLeft, spaceTop, spaceRight, spaceBottom)
            }

            val editTextLayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            editTextLayoutParams.gravity = Gravity.CENTER
            otpChildEditText = OTPChildEditText(mContext!!)
            otpChildEditText!!.filters = arrayOf(filter, InputFilter.LengthFilter(length))
            setTextWatcher(otpChildEditText!!)
            addView(otpChildEditText, editTextLayoutParams)


            val linearLayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val linearLayout = LinearLayout(mContext)

            addView(linearLayout, linearLayoutParams)

            for (i in 0 until length) {
                val itemView = ItemView(mContext!!, attrs)
                itemView.setViewState(ItemView.INACTIVE)
                linearLayout.addView(itemView, i, params)
                itemViews!!.add(itemView)
            }
            if (otp != null) {
                setOTP(otp)
            } else {
                setOTP("")
            }
        } else {
            throw IllegalStateException("Please specify the length of the otp view")
        }
    }

    private fun setTextWatcher(otpChildEditText: OTPChildEditText) {
        otpChildEditText.addTextChangedListener(object : TextWatcher {
            /**
             * @param s
             * @param start
             * @param count
             * @param after
             */
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            /**
             * @param s
             * @param start
             * @param before
             * @param count
             */
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (otpListener != null) {
                    otpListener!!.onInteractionListener()
                    if (s.length == length) {
                        otpListener!!.onOTPComplete(s.toString())
                    }
                }
                setOTP(s)
                setFocus(s.length)
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
        otpChildEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (otpListener != null) {
                    otpListener!!.onDoneClick()
                }
                true
            }
            false
        }
    }

    private fun setFocus(length: Int) {
        for (i in itemViews!!.indices) {
            if (i == length) {
                itemViews!![i].setViewState(ItemView.ACTIVE)
            } else {
                itemViews!![i].setViewState(ItemView.INACTIVE)
            }
        }
        if (length == itemViews!!.size) {
            itemViews!![itemViews!!.size - 1].setViewState(ItemView.ACTIVE)
        }
    }

    fun setOTP(s: CharSequence) {
        for (i in itemViews!!.indices) {
            if (i < s.length) {
                itemViews!![i].setText(s[i].toString())
            } else {
                itemViews!![i].setText("")
            }
        }
    }

    fun requestFocusOTP() {
        if (otpChildEditText != null) {
            otpChildEditText!!.isFocusable = true
            otpChildEditText!!.requestFocus()
        }
    }

    fun showError() {
        if (itemViews != null) {
            for (itemView in itemViews!!) {
                itemView.setViewState(ItemView.ERROR)
            }
        }
    }

    fun resetState() {
        setFocus(otp!!.length)
    }

    fun showSuccess() {
        if (itemViews != null) {
            for (itemView in itemViews!!) {
                itemView.setViewState(ItemView.SUCCESS)
            }
        }
    }

    fun setOTP(otp: String) {
        otpChildEditText!!.setText(otp)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun setOnTouchListener(l: View.OnTouchListener) {
        super.setOnTouchListener(l)
        otpChildEditText!!.setOnTouchListener(l)
    }

    companion object {
        private const val DEFAULT_LENGTH = 4
        private const val DEFAULT_HEIGHT = 48
        private const val DEFAULT_WIDTH = 48
        private const val DEFAULT_SPACE = -1
        private const val DEFAULT_SPACE_LEFT = 4
        private const val DEFAULT_SPACE_RIGHT = 4
        private const val DEFAULT_SPACE_TOP = 4
        private const val DEFAULT_SPACE_BOTTOM = 4
        private const val PATTERN = "[1234567890]*"
    }
}
