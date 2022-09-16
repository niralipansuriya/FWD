package com.swipefwd.utils

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Handler
import android.os.StrictMode
import android.os.SystemClock
import android.text.Editable
import android.text.TextUtils
import android.text.style.UnderlineSpan
import android.util.DisplayMetrics
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.mukesh.countrypicker.CountryPicker
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import com.skydoves.balloon.overlay.BalloonOverlayRect
import com.swipefwd.R
import com.swipefwd.databinding.DialogProfileFinishBinding
import com.swipefwd.databinding.DialogSocialLoginBinding
import com.swipefwd.ui.activity.BaseViewModel
import com.swipefwd.ui.auth.login.LoginActivity
import com.swipefwd.utils.datastore.InternalAppDataStore
import com.swipefwd.utils.datastore.PreferenceKeys
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import io.michaelrocks.libphonenumber.android.Phonenumber
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.Period
import org.joda.time.PeriodType
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


object AppUtils {
    fun Activity.nextActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
        overridePendingTransition(R.anim.enter_anim, R.anim.leave_anim)
    }

    fun hideUnderline(s: Editable?) {
        for (span in s!!.getSpans(
            0, s!!.length,
            UnderlineSpan::class.java
        )) {
            s!!.removeSpan(span)
        }
    }

    fun Activity.showSnackBar(v: View, message: String) {
        val snackBar = Snackbar.make(v, message, Snackbar.LENGTH_LONG)
        val layout = snackBar.view as Snackbar.SnackbarLayout
        layout.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                android.R.color.transparent,
                theme
            )
        )
        val customSnackBarView = this.layoutInflater.inflate(
            R.layout.layout_custom_toast,
            findViewById(R.id.customToastContainer), false
        )
        customSnackBarView.findViewById<AppCompatTextView>(R.id.toastText).text = message
        customSnackBarView.findViewById<AppCompatImageView>(R.id.ivCancel).apply {
            setOnClickListener {
                snackBarCloseAnimation(this.context, snackBar)
            }
        }
        val params = layout.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        layout.layoutParams = params
        layout.removeAllViews()
        layout.addView(customSnackBarView, 0)
        snackBarOpenAnimation(this, snackBar)

    }

    fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }

    val Context.internalAppDataStore: DataStore<Preferences> by preferencesDataStore(PreferenceKeys.PREF_NAME)

    object AccountTypes {
        // Matchmaker(friends)/Dater(me)
        //1:dater, 2:Matchmaker
        const val Matchmaker = "Matchmaker"
        const val Dater = "Dater"
    }

    object PreviousPicker {
        const val FromFour = "FromFour"
        const val FromFive = "FromFive"
        const val FromSix = "FromSix"
    }

    fun RecyclerView.setDivider(@DrawableRes drawableRes: Int = R.drawable.rv_divider) {
        this.addItemDecoration(
            DividerItemDecorator(
                ContextCompat.getDrawable(
                    context,
                    drawableRes
                )!!
            )
        )
    }

    fun View.setDialogFadeInAnimation(duration: Long = 50, delay: Long = 50) {
        postDelayed({
            animate().alpha(1f)
                .setDuration(duration)
                .setInterpolator(AccelerateDecelerateInterpolator())
                .start()
        }, delay)
    }

    fun View.setDialogFadeOutAnimation(
        duration: Long = 200,
        delay: Long = 100,
        runnable: Runnable
    ) {
        animate().alpha(0.5f)
            .setDuration(duration)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()

        postDelayed(runnable, delay)
    }

    fun Dialog.setBottomDialogWindowAttributes() {
        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        window!!.setGravity(Gravity.BOTTOM)
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window?.statusBarColor = Color.parseColor("#00000000")
    }

    fun Dialog.setZoomDialogWindowAttributes() {
        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window?.statusBarColor = Color.parseColor("#00000000")
    }

    inline fun <reified T> ArrayList<*>.asListOfType(): ArrayList<T> =
        if (all { it is T })
            @Suppress("UNCHECKED_CAST")
            this as ArrayList<T>
        else
            arrayListOf()

    fun IntRange.getMultipliersBy(multiplier: Int): List<Int> {
        return map { it * multiplier }
    }

    //    https://medium.com/@iamanbansal/linkedin-v2-integration-in-android-cf6587df324f
    object LinkedInKeys {
        const val linkedInClientId = "86t9hwyaea3vfa"
        const val linkedInClientSecret = "TjcRoukhtVzTATTE"
        const val linkedInRedirectURI = "https://www.swipefwd.com/"
    }

    object InstaKeys {
        //        const val instaClientId = "2173752229428132"
//        const val instaClientSecret = "7cd86294a03a2dc14d1c91d4bc9be10e"
//        const val instaRedirectURI = "https://www.swipefwd.com/"
        const val instaClientId = "668596977493284"

        //        const val instaClientSecret = "7cd86294a03a2dc14d1c91d4bc9be10e"
        const val instaClientSecret = "7147916ea7338cd0f24392bd8446576f"
        const val instaRedirectURI = "https://www.swipefwd.com/"
//        const val instaRedirectURI = "https://httpstat.us/200"
    }

    fun Activity.changeStatusBarColor() {
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window?.statusBarColor = Color.parseColor("#00000000")
    }

    fun Activity.changeStatusBarColorToBlack() {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Color.parseColor("#00FFFFFF")
    }

    fun Activity.changeStatusBar() {
        window?.statusBarColor = ContextCompat.getColor(this, R.color.black_dark_mode)
    }

//    fun validationTextInput(
//        activity: Activity,
//        v: View,
//        vararg edt: TextInputEditText
//    ): String? {
//        var message: String? = null
//        for (i in edt.indices) {
//            if (edt[i].text?.trim()!!.isNotEmpty()) {
//                message = AppConstants.Valid
//            } else {
//                message = activity.getString(R.string.fill_details_message)
//                edt[i].apply {
//                    clearFocus()
//                    background =
//                        ContextCompat.getDrawable(activity, R.drawable.corner_orange_border_bg)
//                    //requestFocus()
//                }
//                activity.showSnackBar(v, message)
//                break
//            }
//        }
//        return message
//    }

    fun validationTextInput(
        activity: Activity,
        locNextArrow: ImageView,
        v: View,
        vararg edt: TextInputEditText
    ): String? {
        var message: String? = null
        var emptyFieldFlag = false
        for (i in edt.indices) {
            if (edt[i].text?.trim()!!.isNotEmpty()) {
                if (!emptyFieldFlag) {
                    message = AppConstants.Valid
                }
            } else {
                emptyFieldFlag = true
                message = activity.getString(R.string.fill_details_message)
                edt[i].apply {
                    clearFocus()
                    background =
                        ContextCompat.getDrawable(activity, R.drawable.corner_orange_border_bg)
                    setHintTextColor(resources.getColor(R.color.color_orange, null))
                    //requestFocus()

                    if (edt[i].hint == "Where do you live?") {
                        locNextArrow.setBackgroundResource(R.drawable.next_arrow_orange_new)

                    }

                }
            }
            if (i == edt.lastIndex && message == activity.getString(R.string.fill_details_message)) {
                activity.showSnackBarMargin(false, v, message)
            }
        }
        return message
    }


    fun showMessageOK(
        context: Activity?,
        title: String,
        positiveText: String,
        message: String,
        okListener: DialogInterface.OnClickListener?
    ) {
        val mContext = context ?: return
        val builder = AlertDialog.Builder(mContext, R.style.AlertDialogTheme)
            .setMessage(message).setCancelable(true)
            .setPositiveButton(positiveText, okListener)
        if (TextUtils.isEmpty(title)) {
            builder.setTitle(title)
        }
        builder.create()
        builder.show()
    }

//    fun getAgeFromCurrentDate(year: Int, month: Int, day: Int): String {
//        val dob = Calendar.getInstance()
//        val today = Calendar.getInstance()
//        dob[year, month] = day
//        var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
//        if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
//            age--
//        }
//        val ageInt = age
//        return ageInt.toString()
//    }

    fun getAgeFromCurrentDate(year: Int, month: Int, day: Int): String {
        val today = Date()//Calendar.getInstance()
        val dob = Date(year - 1900, month - 1, day)
//        dob[year, month] = day
//        var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
//        if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
//            age--
//        }
        val startdate: Long = dob.time
        val endDate: Long = today.getTime()
        val period: Period = Period(startdate, endDate, PeriodType.years())
        val years = period.years
//        val months = period.months
//        val days = period.days
        val ageInt = years
        return ageInt.toString()
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }


    fun Context.createPlaceholderImage(
        name: String,
        textSize: Int, firstColor: Int, secondColor: Int,
        displayBackgroundWhite: Boolean = true,
        isModify: Boolean = false
    ): BitmapDrawable {
        var drawable: BitmapDrawable? = null
        try {
            drawable = MaterialTextDrawable.with(this)
                .text(name)
                .textSize(textSize)
                .isModify(isModify)
                .shaderColor(firstColor, secondColor)
                .backgroundHandle(displayBackgroundWhite)
                .get()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return drawable!!
    }

    fun Context.createPlaceholderImage2(
        name: String,
        textSize: Int, firstColor: Int, secondColor: Int,
        displayBackgroundWhite: Boolean = true,
        isModify: Boolean = false
    ): BitmapDrawable {
        var drawable: BitmapDrawable? = null
        try {
            drawable = MaterialTextDrawable2.with(this)
                .text(name)
                .textSize(textSize)
                .isModify(isModify)
                .shaderColor(firstColor, secondColor)
                .backgroundHandle(displayBackgroundWhite)
                .get()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return drawable!!
    }

    fun Context.setShaderView(v: TextView, firstColor: Int, secondColor: Int) {
        val width = v.paint.measureText(
            v.text.toString()
        )
        val textShader: Shader = LinearGradient(
            0f,
            0f,
            width,
            v.textSize,
            intArrayOf(
                ContextCompat.getColor(this, firstColor),
                ContextCompat.getColor(
                    this,
                    secondColor
                )
            ),
            null,
            Shader.TileMode.CLAMP
        )
        v.paint.shader = textShader
    }

    fun EditText.showKeyboard() {
        this.postDelayed({
            this.dispatchTouchEvent(
                MotionEvent.obtain(
                    SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_DOWN,
                    0f,
                    0f,
                    0
                )
            )
            this.dispatchTouchEvent(
                MotionEvent.obtain(
                    SystemClock.uptimeMillis(),
                    SystemClock.uptimeMillis(),
                    MotionEvent.ACTION_UP,
                    0f,
                    0f,
                    0
                )
            )
        }, 200)
    }

    fun ViewModel.removePreference(
        isNotInterested: Boolean = false,
        isInterested: Boolean = false,
        isGreenProfile: Boolean = false,
        isAccountType: Boolean = false,
        isSmsSent: Boolean = false,
        context: Context
    ) {
        val fwdDataStore by lazy { InternalAppDataStore(context) }
        viewModelScope.launch {
            fwdDataStore.removeAll()
            fwdDataStore.savePreference(PreferenceKeys.PREF_NOT_INTERESTED, isNotInterested)
            fwdDataStore.savePreference(PreferenceKeys.PREF_FWD_GREEN_TOOL_TIP, isGreenProfile)
            fwdDataStore.savePreference(PreferenceKeys.PREF_INTERESTED, isInterested)
            fwdDataStore.savePreference(PreferenceKeys.PREF_ACCOUNT_TOOL_TIP, isAccountType)
            fwdDataStore.savePreference(PreferenceKeys.PREF_SMS_SENT, isSmsSent)
            /*context.getSharedPreferences("duration", MODE_PRIVATE)
                .edit().clear().apply()*/ //for We're so curious popup
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun AppCompatActivity.mintTooltip(): Balloon {
        return createBalloon(this) {
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setBackgroundColor(
                ContextCompat.getColor(
                    this@mintTooltip,
                    android.R.color.transparent
                )
            )
            setLayout(R.layout.layout_custom_tool_tip_mint)
            setArrowSize(10)
            setCornerRadius(0f)
            setWidthRatio(1f)
            setArrowColor(
                ContextCompat.getColor(
                    this@mintTooltip,
                    R.color.mint_gradient2
                )
            )
            setIsVisibleOverlay(true) // sets the visibility of the overlay for highlighting an anchor.
            setOverlayColorResource(R.color.white_80)
            setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE) // default is fade.
            setOverlayShape(BalloonOverlayRect)
            setDismissWhenOverlayClicked(false)
            setArrowOrientation(ArrowOrientation.BOTTOM)
            setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            setArrowPosition(0.5f)
            setElevation(6)
            setBalloonAnimation(BalloonAnimation.OVERSHOOT)
            setDismissWhenTouchOutside(false)
            setDismissWhenShowAgain(false)
            setLifecycleOwner(this@mintTooltip)
        }
    }

    fun AppCompatActivity.openProfileFinishDialog(message: String = getString(R.string.profile_finish_content)) {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        val binding = DialogProfileFinishBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                txtContent.text = message
                btnLater.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        this@openProfileFinishDialog.onBackPressed()
                    }
                }
                btnContinue.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                }
            }
            try {
                setZoomDialogWindowAttributes()
                show()
                binding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun isFileLessThan4MB(file: File): Boolean {
        val maxFileSize = 4 * 1024 * 1024
        val l: Long = file.length()
        val fileSize = l.toString()
        val finalFileSize = fileSize.toInt()
        return finalFileSize <= maxFileSize
    }

    fun getExpiryDate(time: Date, period: String): String {
        val outFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val calendar = Calendar.getInstance()
        calendar.time = time
        when (period) {
            "P1W" -> {
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7)
            }
            "P1M" -> calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1)
            "P3M" -> calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 3)
            "P6M" -> calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 6)
            "P1Y" -> {
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1)
            }
        }
        val exTime = calendar.time
        val exDateStr = outFormat.format(exTime)
        return exDateStr
    }

    fun storeDeviceId(context: Context, deviceId: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("deviceId", deviceId)
            .apply()
    }

    fun storeAuthToken(context: Context, auth_token: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("auth_token", auth_token)
            .apply()
    }

    fun storeGender(context: Context, gender: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("gender", gender)
            .apply()
    }

    fun storeProfileVerified(context: Context, is_profile_verified: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("is_profile_verified", is_profile_verified)
            .apply()
    }

    fun getProfileVerified(context: Context): Int {
        val is_profile_verified =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getInt("is_profile_verified", 0)
        return is_profile_verified!!
    }

    fun getGender(context: Context): Int {
        val gender =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("gender", 1)
        return gender!!
    }

    fun storeInvitationExpired(context: Context, invitationExpired: Boolean) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("invitationExpired", invitationExpired)
            .apply()
    }

    fun storeDob(context: Context, dob: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("dob", dob)
            .apply()
    }

    fun storeReminderDate(context: Context, reminderData: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("verification_reminder_date", reminderData)
            .apply()
    }

    fun storeRecoveryEmail(context: Context, recovery_email: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("recovery_email", recovery_email)
            .apply()
    }

    fun storeAuthorizationToken(context: Context, authorization_token: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("authorization_token", authorization_token)
            .apply()
    }

    fun storeProfileFlag(context: Context, profile_flag: Boolean) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("profile_flag", profile_flag)
            .apply()
    }

    fun storeAdvanceProfileFlag(context: Context, advance_profile_flag: Boolean) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("advance_profile_flag", advance_profile_flag)
            .apply()
    }

    fun storePrefFlag(context: Context, preference_flag: Boolean) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("preference_flag", preference_flag)
            .apply()
    }

    fun storeUserId(context: Context, userId: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("user_id", userId)
            .apply()
    }

    fun storeFirstName(context: Context, first_name: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("first_name", first_name)
            .apply()
    }
    fun deleteSharedPreferences(context: Context, name: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.deleteSharedPreferences(name)
        } else {
            context.getSharedPreferences(name, MODE_PRIVATE).edit().clear().apply()
            val dir = File(context.applicationInfo.dataDir, "shared_prefs")
            return File(dir, "$name.xml").delete()
        }
    }
    fun clearSharedPreferences(ctx: Context) {
        val dir = File(ctx.filesDir.parent + "/shared_prefs/")
        println("clearSharedPreferences dir name--->>${dir.name}")
        println("clearSharedPreferences dir path--->>${dir.absolutePath}")
        val children = dir.list()
        for (i in children.indices) {
            // clear each preference file
            ctx.getSharedPreferences(children[i].replace(".xml", ""), MODE_PRIVATE).edit().clear()
                .commit()
            //delete the file
            File(dir, children[i]).delete()
        }
    }
    fun removePrefData(context: Context) {
        context.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().clear().commit()

    }

    fun storeLastName(context: Context, last_name: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("last_name", last_name)
            .apply()
    }

    fun storeAlternateLogin(context: Context, alternateLogin: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("alternateLogin", alternateLogin)
            .apply()
    }

    fun storeProfileImage(context: Context, profile_image: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("profile_image", profile_image)
            .apply()
    }

    fun storePhoneNumber(context: Context, phone_number: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("phone_number", phone_number)
            .apply()
    }

    fun storeAgreement(context: Context, agreement: Boolean) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("agreement", agreement)
            .apply()
    }


    fun storeAccountType(context: Context, accountType: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("accountType", accountType)
            .apply()
    }

    fun getAccountType(context: Context): String {
        val accountType =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("accountType", "")
        return accountType!!
    }

    fun getProfileImage(context: Context): String {
        val profileImage =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("profile_image", "")
        return profileImage!!
    }

    fun getHeight(context: Context): Int {
        val height =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("height", 0)
        return height
    }

    fun getAlternateLogin(context: Context): Int {
        val alternateLogin =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("alternateLogin", 0)
        return alternateLogin
    }

    fun getDistance(context: Context): Int {
        val height =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("distance", 0)
        return height
    }

    fun getGenderPref(context: Context): Int {
        val gender =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("pref_gender", 2)
        return gender!!
    }

    fun getStartAgePref(context: Context): Int {
        val age =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("start_age", 18)
        return age!!
    }

    fun getEndAgePref(context: Context): Int {
        val age =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("end_age", 99)
        return age!!
    }

    fun getProfileOrPref(context: Context): Int {
        val profileOrPref =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("isProfileOrPref", 0)
        return profileOrPref!!
    }

    fun saveSocialMediaData(context: Context, socailData: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("socailData", socailData)
            .apply()
    }

    fun storeProfileORPref(context: Context, isProfileOrPref: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("isProfileOrPref", isProfileOrPref)
            .apply()
    }

    fun getSocialMediaData(context: Context): String {
        val socailData =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("socailData", "")
        return socailData!!
    }

    fun storeSignupType(context: Context, signupType: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("signupType", signupType)
            .apply()
    }

    fun storeLatitude(context: Context, latitude: Long) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putLong("latitude", latitude)
            .apply()
    }


    fun storeLogitude(context: Context, logitude: Long) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putLong("logitude", logitude)
            .apply()
    }

    fun storeLocation(context: Context, location: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit().putString("location", location)
            .apply()
    }


    fun getSignupType(context: Context): Int {
        val signupType =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("signupType", 0)
        return signupType
    }
    fun getLocation(context: Context): String {
        val location =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("location", "")
        return location!!
    }

    fun storeDaterDisable(context: Context, dater_disabled: Boolean) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("dater_disabled", dater_disabled)
            .apply()
    }

    fun storeConnectorDisable(context: Context, connector_disabled: Boolean) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("connector_disabled", connector_disabled)
            .apply()
    }

    fun storeBio(context: Context, bio: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("bio", bio)
            .apply()
    }
    fun storeEmail(context: Context, email: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("email", email)
            .apply()
    }

    fun storeDistance(context: Context, distance: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("distance", distance)
            .apply()
    }

    fun storeGenderPref(context: Context, gender: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("pref_gender", gender)
            .apply()
    }

    fun storeStartAgePref(context: Context, age: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("start_age", age)
            .apply()
    }

    fun storeEndAgePref(context: Context, age: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("end_age", age)
            .apply()
    }

    fun storeHeight(context: Context, height: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("height", height)
            .apply()
    }

    fun storeRemainingInvitation(context: Context, remaining_invitation: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("remaining_invitation", remaining_invitation)
            .apply()
    }

    fun storeRemainConnection(context: Context, remain_connection: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("remain_connection", remain_connection)
            .apply()
    }

    fun storeLinkedInId(context: Context, linkedin_id: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("linkedin_id", linkedin_id)
            .apply()
    }

    fun storeInstaUserName(context: Context, insta_user_name: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("insta_user_name", insta_user_name)
            .apply()
    }

    fun storeFacebook(context: Context, faceBook: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("faceBook", faceBook)
            .apply()
    }
    fun storeInstaId(context: Context, instaId: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("instaId", instaId)
            .apply()
    }
    fun storeIsInstaPhoto(context: Context, istaProfile: Boolean) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("isInstaProfile", istaProfile)
            .apply()
    }
    fun getFaceBook(context: Context): String {
        val faceBook =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("faceBook", "")
        return faceBook!!
    }
    fun getIsInstaProfile(context: Context): Boolean {
        val isInstaProfile =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isInstaProfile", false)
        return isInstaProfile!!
    }
    fun getInstaID(context: Context): String {
        val instaId =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("instaId", "")
        return instaId!!
    }

    fun getInstagramUserName(context: Context): String {
        val insta_user_name =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("insta_user_name", "")
        return insta_user_name!!
    }
    fun getGoogle(context: Context): String {
        val google =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("google", "")
        return google!!
    }
    fun getEmail(context: Context): String {
        val email =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("email", "")
        return email!!
    }
    fun getLinkedIn(context: Context): String {
        val linkedin_id =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("linkedin_id", "")
        return linkedin_id!!
    }

    fun storeRelationship(context: Context, relationShip: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("relationShip", relationShip)
            .apply()
    }

    fun storeSmoking(context: Context, smokingData: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("smokingData", smokingData)
            .apply()
    }

    fun storeVaccination(context: Context, vaccination: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("vaccination", vaccination)
            .apply()
    }

    fun storeChildren(context: Context, children: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("children", children)
            .apply()
    }

    fun storeAstrologicalSign(context: Context, sign: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("sign", sign)
            .apply()
    }

    fun storeAstrologicalSigns(context: Context, sign: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("signs", sign)
            .apply()
    }

    fun storeMorePhotos(context: Context, morePhotos: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("morePhotos", morePhotos)
            .apply()
    }

    fun storeReligion(context: Context, religion: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("religion", religion)
            .apply()
    }

    fun storeReligions(context: Context, religion: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("religions", religion)
            .apply()
    }

    fun storeEducation(context: Context, education: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("education", education)
            .apply()
    }

    fun storeInstitute(context: Context, institute: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("institute", institute)
            .apply()
    }

    fun storeGraduationYear(context: Context, graduationYear: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("graduationYear", graduationYear)
            .apply()
    }

    fun storeOccupation(context: Context, occupation: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("occupation", occupation)
            .apply()
    }

    fun storeLanguae(context: Context, language: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("language", language)
            .apply()
    }

    fun storeRelationshipPref(context: Context, relationShip: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("relationShipPref", relationShip)
            .apply()
    }

    fun storeSmokingPref(context: Context, smokingData: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("smokingDataPref", smokingData)
            .apply()
    }

    fun storeVaccinationPref(context: Context, vaccination: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("vaccinationPref", vaccination)
            .apply()
    }

    fun storeChildrenPref(context: Context, children: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("childrenPref", children)
            .apply()
    }

    fun storeEducationPref(context: Context, education: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("educationPref", education)
            .apply()
    }

    fun storeLanguagePref(context: Context, language: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("languagePref", language)
            .apply()
    }

    fun getEducation(context: Context): String {
        val education =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("education", "")
        return education!!
    }

    fun getMorePhotos(context: Context): String {
        val morePhotos =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("morePhotos", "")
        return morePhotos!!
    }

    fun getInstitute(context: Context): String {
        val institute =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("institute", "")
        return institute!!
    }

    fun getGraduationYear(context: Context): Int {
        val graduationYear =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("graduationYear", 0)
        return graduationYear!!
    }

    fun getLanguage(context: Context): String {
        val language =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("language", "")
        return language!!
    }

    fun getOccupation(context: Context): String {
        val occupation =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("occupation", "")
        return occupation!!
    }

    fun getAstrologicalSign(context: Context): String {
        val sign =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("sign", "")
        return sign!!
    }

    fun getAstrologicalSigns(context: Context): String {
        val sign = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("signs", "")
        return sign!!
    }

    fun getReligion(context: Context): String {
        val religion =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("religion", "")
        return religion!!
    }

    fun getBio(context: Context): String {
        val bio =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("bio", "")
        return bio!!
    }

    fun getReligions(context: Context): String {
        val religion =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("religions", "")
        return religion!!
    }

    fun getVaccination(context: Context): String {
        val vaccination =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("vaccination", "")
        return vaccination!!
    }

    fun getChildren(context: Context): String {
        val children =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("children", "")
        return children!!
    }

    fun getSmoking(context: Context): String {
        val smokingData =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("smokingData", "")
        return smokingData!!
    }

    fun getRelationShip(context: Context): String {
        val relationShip =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("relationShip", "")
        return relationShip!!
    }

    fun getEducationPref(context: Context): String {
        val education =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("educationPref", "")
        return education!!
    }

    fun getLanguagePref(context: Context): String {
        val language =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("languagePref", "")
        return language!!
    }

    fun getChildrenPref(context: Context): String {
        val children =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("childrenPref", "")
        return children!!
    }

    fun getSmokingPref(context: Context): String {
        val smokingData =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("smokingDataPref", "")
        return smokingData!!
    }

    fun getRelationShipPref(context: Context): String {
        val relationShip =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("relationShipPref", "")
        return relationShip!!
    }

    fun storeGoogle(context: Context, google: String) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putString("google", google)
            .apply()
    }

    fun storeAccountype(context: Context, accountType: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("accountType", accountType)
            .apply()
    }

    fun storeUserType(context: Context, userType: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("userType", userType)
            .apply()
    }

    fun getUserType(context: Context): Int {
        val userType =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("userType", 0)
        return userType!!
    }

    fun storeLoginFlag(context: Context, loginFlag: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("loginFlag", loginFlag)
            .apply()
    }

    fun storeScreenFlag(context: Context, screen_flag: Int) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putInt("screen_flag", screen_flag)
            .apply()
    }

    fun storeIsVerified(context: Context, isVerified: Boolean) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("is_verified", isVerified)
            .apply()
    }

    fun storeIsProfileSetup(context: Context, profileSetup: Boolean) {
        context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("is_profile_setup", profileSetup)
            .apply()
    }

    fun getDeviceId(context: Context): String {
        val deviceId =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("deviceId", "")
        return deviceId!!
    }

    fun getAuthToken(context: Context): String {
        val auth_token =
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("auth_token", "")
        return auth_token!!
    }


    fun dialogForProfile(context: Context): Boolean {
        //showing this dialog every other day for adding profile picture
        var isShow = false
        val duration: Long = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getLong(
            "duration",
            System.currentTimeMillis() - TimeUnit.DAYS.toMillis(12)
        )

        if (System.currentTimeMillis() - duration > TimeUnit.DAYS.toMillis(1)) {
            // inflateDialog is a function containing the functionality of popping up the dialog
            isShow = true
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putLong("duration", System.currentTimeMillis())
                .apply()
        }
        return isShow
    }

    fun dialogForPsst(context: Context): Boolean {
        //showing this dialog every other day for adding profile picture
        var isShow = false
        val duration: Long = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getLong(
            "durationPsst",
            System.currentTimeMillis() - TimeUnit.DAYS.toMillis(12)
        )

        if (System.currentTimeMillis() - duration > TimeUnit.DAYS.toMillis(1)) {
            // inflateDialog is a function containing the functionality of popping up the dialog
            isShow = true
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putLong("durationPsst", System.currentTimeMillis())
                .apply()
        }

        if (!AppController.psstBoolean && isShow) {
            AppController.psstBoolean = true
            isShow = true
        } else {
            isShow = false
        }
        return isShow
    }

    fun dialogForFbFriends(context: Context): Boolean {
        var isShow = false
        val duration: Long = context.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getLong(
            "durationFbFriends",
            System.currentTimeMillis() - TimeUnit.DAYS.toMillis(12)
        )

        if (System.currentTimeMillis() - duration > TimeUnit.DAYS.toMillis(3)) {
            // inflateDialog is a function containing the functionality of popping up the dialog
            isShow = true
            context.getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putLong("durationFbFriends", System.currentTimeMillis())
                .apply()
        }
        return isShow
    }

    fun Activity.showSnackBarMargin(
        isDOB: Boolean = false,
        v: View,
        message: String,
        isFromRecovery: Boolean = false,
        listener: View.OnClickListener? = null
    ): Snackbar {
        val snackBar = Snackbar.make(v, message, Snackbar.LENGTH_LONG)
        val layout = snackBar.view as Snackbar.SnackbarLayout

        layout.setBackgroundColor(
            ResourcesCompat.getColor(
                resources,
                android.R.color.transparent,
                theme
            )
        )
        val customSnackBarView = this.layoutInflater.inflate(
            R.layout.layout_custom_toast,
            findViewById(R.id.customToastContainer), false
        )
        customSnackBarView.findViewById<AppCompatTextView>(R.id.toastText).text = message
        customSnackBarView.findViewById<AppCompatImageView>(R.id.ivCancel).apply {
            setOnClickListener {
                snackBarCloseAnimation(this.context, snackBar)
                if (isFromRecovery) {
                    Handler(mainLooper).postDelayed({
                        listener?.onClick(this)
                    }, 400)
                    /*  val intent = Intent(this.context, LoginActivity::class.java)
                      startActivity(intent)
                      finishAffinity()
                      overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right)*/
                }
            }
        }
        val params = layout.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        params.topMargin = if (isDOB) {
            40.dpToPx()
        } else {
            50.dpToPx()
        }
        layout.layoutParams = params
        layout.removeAllViews()
        layout.addView(customSnackBarView, 0)
        snackBarOpenAnimation(this, snackBar)
        return snackBar
    }

    fun getScreenWidth(@NonNull activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    fun getScreenHeight(@NonNull activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.top - insets.bottom
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }

    @Throws(IOException::class)
    fun drawableFromUrl(url: String?): Drawable {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val x: Bitmap
        val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
        connection.connect()
        val input: InputStream = connection.getInputStream()
        x = BitmapFactory.decodeStream(input)
        return BitmapDrawable(Resources.getSystem(), x)
    }

    fun getProfileTypeId(profileType: String): Int {
        var type_id = 0
        when (profileType) {
            "black" -> {
                type_id = 1
            }
            "green" -> {
                type_id = 2
            }
            "basic" -> {
                type_id = 5
            }
            "blurple" -> {
                type_id = 1
            }
        }
        return type_id
    }

    fun setApiMessage(s: String, viewModel: ViewModel): String {
        var message = ""
        message = if (s == "timeout") {
            "The connection has timed out. Please try again."
        } else if (s == "something went wrong" || s == "Something went wrong") {
            "Unknown error. Please try again."
        } else {
            s
        }
        return message
    }

    fun callLogout(viewModel: ViewModel) {
        (viewModel as BaseViewModel).apply {
            viewModelScope.launch {
                removePreference(
                    isNotInterested1,
                    isInterested1,
                    getGreenShowTip1,
                    isAccountTip,
                    isSMSsent,
                    context
                )
                deleteDatabase(context, userId, viewModel)
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
                (context as Activity).finish()
                (context as Activity).finishAffinity()
            }
        }
    }

    private fun deleteDatabase(context: Context, userId: Int, viewModel: ViewModel) {
        (viewModel as BaseViewModel).apply {
            viewModelScope.launch {
                AppDatabase.getInstance(context).apply {
                    userProfileDao().apply {
                        deleteAllLanguage()
                        deleteAllLevel()
                        deleteAllAstroSign()
                        deleteAllCast()
                        deleteAllChildren()
                        deleteAllReligion()
                        deleteAllSmoking()
                        deleteAllRelationship()
                        deleteAllVaccineStatus()
                    }
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            chatDao().apply {
                                deleteAllRosters()
                                deleteAllMessageByUser(userId.toString())
                            }
                        }
                    }
                }
            }
        }

    }

    fun Fragment.openFailNetworkDialog(clickListener: View.OnClickListener? = null) {
        val dialog = Dialog(requireContext(), R.style.ZoomDialogTheme)
        val fbBinding = DialogSocialLoginBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(fbBinding.root)
            fbBinding.apply {
                view.visibility = View.GONE
                btnCancel.visibility = View.GONE
                btnContinue.visibility = View.GONE
                btnOk.apply {
                    visibility = View.VISIBLE

                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                            //  clickListener?.onClick(it)
                        }

                    }
                }
                txtTitle.text = getString(R.string.login_failure)
                txtContent.text = getString(R.string.failure_message)
            }
            setZoomDialogWindowAttributes()
            show()
            fbBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    fun openFailNetworkDialog(context: Context) {
        val dialog = Dialog(context, R.style.ZoomDialogTheme)
        val fbBinding = DialogSocialLoginBinding.inflate((context as Activity).layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(fbBinding.root)
            fbBinding.apply {
                view.visibility = View.GONE
                btnCancel.visibility = View.GONE
                btnContinue.visibility = View.GONE
                btnOk.apply {
                    visibility = View.VISIBLE

                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                        }

                    }
                }
                txtTitle.text = context.getString(R.string.login_failure)
                txtContent.text = context.getString(R.string.failure_message)
            }
            setZoomDialogWindowAttributes()
            show()
            fbBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    fun Activity.openFailNetworkDialog(clickListener: View.OnClickListener? = null) {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        val fbBinding = DialogSocialLoginBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(fbBinding.root)
            fbBinding.apply {
                view.visibility = View.GONE
                btnCancel.visibility = View.GONE
                btnContinue.visibility = View.GONE
                btnOk.apply {
                    visibility = View.VISIBLE

                    setOnClickListener {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                            //  clickListener?.onClick(it)
                        }

                    }
                }
                txtTitle.text = getString(R.string.login_failure)
                txtContent.text = getString(R.string.failure_message)
            }
            setZoomDialogWindowAttributes()
            show()
            fbBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    fun snackBarOpenAnimation(context: Context, snackBar: Snackbar) {
        val animationOpen1 = AnimationUtils.loadAnimation(context, R.anim.snackbar_open_slide_down)
        val animationOpen2 = AnimationUtils.loadAnimation(context, R.anim.snackbar_open_slide_up)
        snackBar.view.startAnimation(animationOpen1)
        animationOpen1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                snackBar.view.startAnimation(animationOpen2)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
        snackBar.show()
    }

    fun snackBarCloseAnimation(context: Context, snackBar: Snackbar) {
        val animationClose1 =
            AnimationUtils.loadAnimation(context, R.anim.snackbar_close_slide_down)
        val animationClose2 = AnimationUtils.loadAnimation(context, R.anim.snackbar_close_slide_up)
        val animationClose3 = AnimationUtils.loadAnimation(context, R.anim.snackbar_close_dismiss)
        snackBar.view.startAnimation(animationClose1)
        animationClose1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                snackBar.view.startAnimation(animationClose2)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
        animationClose2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                snackBar.view.startAnimation(animationClose3)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
        animationClose3.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                snackBar.dismiss()
            }

            override fun onAnimationEnd(p0: Animation?) {

            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
    }

    fun formatDate(date: String?): String {
        var _date = date
        try {
            var spf = SimpleDateFormat("MM/dd/yyyy")
            val newDate = spf.parse(date)
         //   spf = SimpleDateFormat("dd-MMM-yyyy")
            spf = SimpleDateFormat("dd-MM-yyyy")
            _date = spf.format(newDate)
        } catch (e: Exception) {

        }
        return _date!!
    }

    fun slideView(view: View, currentHeight: Int, newHeight: Int, duration: Long) {
        val slideAnimator = ValueAnimator
            .ofInt(currentHeight, newHeight)
            .setDuration(duration)
        slideAnimator.addUpdateListener { animation1 ->
            val value = animation1.animatedValue
            view.layoutParams.height = value as Int
            view.requestLayout()
        }
        val animationSet = AnimatorSet()
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.play(slideAnimator)
        animationSet.start()
    }

    fun Activity.phoneNumberValidation(number: String): Boolean {
        val locale = resources?.configuration?.locale
        var isoCode = "IN"
        val country2 =
            let { CountryPicker.Builder().with(this).build() }
        val country1 = locale?.let { country2?.getCountryByLocale(locale) }
        if (country1 != null)
            isoCode = country1.code ?: "IN"

        val phoneUtil = PhoneNumberUtil.createInstance(this)
        val phone: Phonenumber.PhoneNumber? = phoneUtil.parse(number, isoCode)
        return phoneUtil.isValidNumber(phone)
    }

    fun Activity.makeTextviewScrollable(editext: EditText) {
        editext.setOnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
            }
            false
        }
    }

    fun bitmapCircularCroper(bitmapimg: Bitmap): Bitmap? {
        val output = Bitmap.createBitmap(
            bitmapimg.width,
            bitmapimg.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(
            0, 0, bitmapimg.width,
            bitmapimg.height
        )
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        canvas.drawCircle(
            (bitmapimg.width / 2).toFloat(),
            (bitmapimg.height / 2).toFloat(), (bitmapimg.width / 2).toFloat(), paint
        )
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmapimg, rect, rect, paint)
        return output
    }

    fun getScreenShot(view: View): Bitmap? {
        val screenView = view.rootView
        screenView.isDrawingCacheEnabled = true
        val bitmap = Bitmap.createBitmap(screenView.drawingCache)
        screenView.isDrawingCacheEnabled = false
        return bitmap
    }

    fun disableView(v: View) {
        v.setOnTouchListener { v, event -> true }
    }

    fun changeDrawableColor(context: Context, view: ImageView, color: Int) {
        val unwrappedDrawable: Drawable? =
            AppCompatResources.getDrawable(context, R.drawable.arrow_gradient)
        val wrappedDrawable: Drawable? = unwrappedDrawable?.let { DrawableCompat.wrap(it) }
        if (wrappedDrawable != null) {
            DrawableCompat.setTint(wrappedDrawable, color)
            view.setBackgroundDrawable(wrappedDrawable)
        }
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            200F
        ) // toYDelta
        animate.duration = 1500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    // slide the view from its current position to below itself
    fun slideDown(view: View) {
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            0F,  // fromYDelta
            view.height.toFloat()
        ) // toYDelta
        animate.setDuration(500)
        animate.setFillAfter(true)
        view.startAnimation(animate)
    }

    //to avoid multiple clicks
    var mLastClickTime = 0L

    fun isClickedRecently(): Boolean {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 600) {
            return true
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        return false
    }
}
