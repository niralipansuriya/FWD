package com.swipefwd.ui.home.message

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.swipefwd.R
import com.swipefwd.databinding.*
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes

class MatchChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchChatBinding
    private var startForResult: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {

        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    onBackPressed()
                }
            }

        binding.apply {

            progressMatch.setProgress(60.toFloat(), true)

            ivCamera.setOnClickListener {
                requestRuntimePermission()
            }

            imgUser.setOnClickListener {
                openFWDByDialog()
            }

            Glide.with(this@MatchChatActivity)
                .load(R.drawable.user_girl_image).diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(imgUser)

            val spannable = SpannableString(txtExpire.text.toString())
            spannable.setSpan(
                ForegroundColorSpan(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.colorPagerTitle,
                        theme
                    )
                ),
                1,
                8,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            txtExpire.apply {
                setText(spannable, TextView.BufferType.SPANNABLE)
                highlightColor = Color.TRANSPARENT
                movementMethod = LinkMovementMethod.getInstance()
            }

            txtExpire.setOnClickListener {
                msgGroup.visibility = View.GONE
                extendGroup.visibility = View.VISIBLE
            }

            ivError.setOnClickListener {
                openErrorDialog()
            }

            ivBack.setOnClickListener {
                onBackPressed()
            }

            ivOptions.setOnClickListener {
                openOptionsDialog()
            }

            edtMessage.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    val message = p0.toString().trim()
                    if (message.isNotEmpty()) {
                        ivSend.visibility = View.VISIBLE
                        ivRecord.visibility = View.INVISIBLE
                    } else {
                        ivSend.visibility = View.INVISIBLE
                        ivRecord.visibility = View.VISIBLE
                    }
                }
            })

            btnAddTime.setOnClickListener {
                openExtendTimeDialog()
            }

            if (intent.hasExtra("extendMatch")) {
                msgGroup.visibility = View.GONE
                extendGroup.visibility = View.VISIBLE
            } else {
                msgGroup.visibility = View.VISIBLE
                extendGroup.visibility = View.GONE
            }
        }
    }

    private fun requestRuntimePermission() {
        Dexter.withContext(this).withPermissions(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                arrayListOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
            } else {
                arrayListOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
            }
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                when {
                    report.areAllPermissionsGranted() -> {

                    }
                    report.deniedPermissionResponses != null && report.deniedPermissionResponses.size > 0 -> {
                        if (report.deniedPermissionResponses[0].isPermanentlyDenied) {
                            val intent =
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts(
                                "package",
                                packageName, null
                            )
                            intent.data = uri
                            try {
                                startActivity(intent)
                            } catch (e: java.lang.Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }

        })
            .withErrorListener {
                Log.e("Dexter", "There was an error: $it")
            }
            .check()
    }

    private fun openFWDByDialog() {
        val dialog = Dialog(this, R.style.OptionsDialogTheme)
        val fwdByBinding = DialogFwdByBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(fwdByBinding.root)
            setOnDismissListener {
                fwdByBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    //do nothing
                }
            }
            try {
                setZoomDialogWindowAttributes()
                show()
                fwdByBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openErrorDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val deleteResendMsgBinding = DialogDeleteResendMsgBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(deleteResendMsgBinding.root)
            deleteResendMsgBinding.btnCancel.setOnClickListener {
                deleteResendMsgBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            deleteResendMsgBinding.btnDelete.setOnClickListener {
                deleteResendMsgBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            deleteResendMsgBinding.btnResend.setOnClickListener {
                deleteResendMsgBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                deleteResendMsgBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openOptionsDialog() {
        val dialog = Dialog(this, R.style.OptionsDialogTheme)
        val chatOptionsBinding = DialogChatOptionsBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(chatOptionsBinding.root)
            setOnDismissListener {
                chatOptionsBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    //do nothing
                }
            }
            chatOptionsBinding.imgOptions.setOnClickListener {
                chatOptionsBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            chatOptionsBinding.btnBlock.setOnClickListener {
                chatOptionsBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    openBlockReasonDialog()
                }
            }
            chatOptionsBinding.btnUnmatch.setOnClickListener {
                chatOptionsBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    openUnmatchReasonDialog()
                }
            }
            try {
                setZoomDialogWindowAttributes()
                show()
                chatOptionsBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openUnmatchReasonDialog() {

        val reasonList = arrayListOf(
            getString(R.string.made_uncomfortable),
            getString(R.string.abusive_threatening),
            getString(R.string.inappropriate_content),
            getString(R.string.spam_scam),
            getString(R.string.stolen_photo),
            getString(R.string.not_interested)
        )

        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val unmatchReasonsBinding = DialogUnmatchReasonsBinding.inflate(layoutInflater)

        var reason: String
        val reasonAdapter = UnmatchReasonAdapter(this) {
            reason = it
            unmatchReasonsBinding.btnUnmatchNext.isEnabled = reason.isNotEmpty()
        }

        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(unmatchReasonsBinding.root)
            setOnDismissListener {
                unmatchReasonsBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    // do nothing
                }
            }
            unmatchReasonsBinding.btnUnmatchNext.setOnClickListener {
                unmatchReasonsBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    openUnmatchUserDialog()
                }
            }
            unmatchReasonsBinding.rvUnmatchReason.apply {
                setDivider()
                adapter = reasonAdapter
            }
            reasonAdapter.addAll(reasonList)
            try {
                setBottomDialogWindowAttributes()
                show()
                unmatchReasonsBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openUnmatchUserDialog() {
        val dialog = Dialog(this, R.style.ZoomDialogTheme)
        val unmatchUserBinding = DialogUnmatchedBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(unmatchUserBinding.root)
            unmatchUserBinding.btnGotIt.setOnClickListener {
                unmatchUserBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    this@MatchChatActivity.onBackPressed()
                }
            }
            setZoomDialogWindowAttributes()
            show()
            unmatchUserBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    private fun openBlockReasonDialog() {

        val reasonList = arrayListOf(
            getString(R.string.made_uncomfortable),
            getString(R.string.abusive_threatening),
            getString(R.string.inappropriate_content),
            getString(R.string.spam_scam),
            getString(R.string.stolen_photo),
            getString(R.string.not_interested),
            getString(R.string.common_other)
        )

        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val blockReasonsBinding = DialogBlockReasonsBinding.inflate(layoutInflater)

        var reason = reasonList[0]
        val reasonAdapter = BlockReasonAdapter(this) {
            if (it.isNotEmpty()) {
                reason = it
            }
        }

        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(blockReasonsBinding.root)
            setOnDismissListener {
                blockReasonsBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    // do nothing
                }
            }
            blockReasonsBinding.btnBlockNext.setOnClickListener {
                blockReasonsBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    when (reason) {
                        getString(R.string.common_other) -> {
                            startForResult?.launch(
                                Intent(
                                    this@MatchChatActivity,
                                    BlockReasonActivity::class.java
                                )
                            )
                        }
                        else -> {
                            this@MatchChatActivity.onBackPressed()
                        }
                    }
                }
            }
            blockReasonsBinding.rvBlockReason.apply {
                setDivider()
                adapter = reasonAdapter
            }
            reasonAdapter.addAll(reasonList)
            try {
                setBottomDialogWindowAttributes()
                show()
                blockReasonsBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openExtendTimeDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val extendBinding = DialogExtendTimeBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(extendBinding.root)
            extendBinding.btnDialogAddTime.setOnClickListener {
                extendBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    this@MatchChatActivity.binding.apply {
                        btnAddTime.visibility = View.GONE
                        txtExtendTitle.text = getString(R.string.extended_this_match)
                        txtExtendDesc.text = getString(R.string.another_48_hrs)
                        txtExpire.text = "(53 hours to message)"
                        val spannable = SpannableString(txtExpire.text.toString())
                        spannable.setSpan(
                            ForegroundColorSpan(
                                ResourcesCompat.getColor(
                                    resources,
                                    R.color.colorPagerTitle,
                                    theme
                                )
                            ),
                            1,
                            9,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                        txtExpire.apply {
                            setText(spannable, TextView.BufferType.SPANNABLE)
                            highlightColor = Color.TRANSPARENT
                            movementMethod = LinkMovementMethod.getInstance()
                        }
                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                extendBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}