package com.swipefwd.ui.home.message

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.devlomi.record_view.OnRecordListener
import com.devlomi.record_view.RecordPermissionHandler
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.swipefwd.R
import com.swipefwd.databinding.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.*
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.xmpp.Utils
import com.swipefwd.xmpp.XmppRosterEntry
import com.swipefwd.xmpp.XmppServiceBroadcastEventReceiver
import com.swipefwd.xmpp.XmppServiceCommand
import com.swipefwd.xmpp.database.ChatListener
import com.swipefwd.xmpp.database.Message
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit


class ChatActivity : AppCompatActivity(), ChatListener {

    var lastMessage = "image"
    private lateinit var binding: ActivityChatBinding
    private var startForResult: ActivityResultLauncher<Intent>? = null
    private val mActivity by lazy {
        this@ChatActivity
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(this@ChatActivity)
    }
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private val messageViewModel: MessageViewModel by viewModels {
        viewModelFactory { MessageViewModel(this, AppRepository()) }
    }
    lateinit var xmppBroadcastReceiver: XmppServiceBroadcastEventReceiver
    private val mDatabase by lazy {
        AppDatabase.getInstance(mActivity)
    }
    lateinit var messageListAdapter: MessageListAdapter
    private var jid = ""
    private var name = ""
    private var phone = ""
    private var profilePicture = ""
    private var isInConnection = false
    private var userName = ""
    private var recordFile: File? = null
    private var audioRecorder: AudioRecorder? = null
    private var image_url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        if (intent.hasExtra("jid")) {
            jid = intent.getStringExtra("jid")!!
        }

        audioRecorder = AudioRecorder()
        xmppBroadcastReceiver = XmppServiceBroadcastEventReceiver(mActivity)

        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    onBackPressed()
                }
            }

        binding.apply {
            if (intent.hasExtra("name")) {
                name = intent.getStringExtra("name")!!
                if (!name.isNullOrEmpty()) {
                    val mName = name.split(" ")[0]
                    txtUserName.text = mName
                }
            }
            try {
                if (intent.hasExtra("image_url")) {
                    image_url = intent.getStringExtra("image_url")!!
                }
            } catch (e: java.lang.Exception) {
                image_url = ""
            }

            var textDrawable: BitmapDrawable? = null
            if (name != "") {
                textDrawable = mActivity.createPlaceholderImage(
                    name,
                    100,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )
                Glide.with(mActivity)
                    .load(image_url).diskCacheStrategy(
                        DiskCacheStrategy.ALL
                    )
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(textDrawable)
                    .into(binding.imgUser)
            }

            lifecycleScope.launch {
                userName = messageViewModel.getFirstName.firstOrNull() ?: ""
                profilePicture = messageViewModel.getProfileImage.firstOrNull() ?: ""
                phone = messageViewModel.phoneNumber.firstOrNull() ?: ""
                messageListAdapter = MessageListAdapter(phone, profilePicture, userName, name)
                rcvChat.adapter = messageListAdapter
            }
            ivOptions.setOnClickListener {
                openOptionsDialog()
            }

            ivBack.setOnClickListener {
                onBackPressed()
            }

            ivGallery.setOnClickListener {
                requestRuntimePermission(false)
            }


            recordButton.setRecordView(recordView);


            recordView.cancelBounds = 8F;


            recordView.setSmallMicColor(Color.parseColor("#c2185b"));

            //prevent recording under one Second
            recordView.setLessThanSecondAllowed(false);


            recordView.setSlideToCancelText("Slide To Cancel");


            recordView.setOnRecordListener(object : OnRecordListener {
                override fun onStart() {
                    requestRuntimePermission(true)
                    ivGallery.visibility = View.GONE
                    edtMessage.visibility = View.GONE
                    recordView.visibility = View.VISIBLE
                    recordFile = File(filesDir, UUID.randomUUID().toString() + ".3gp")
                    try {
                        audioRecorder!!.start(recordFile!!.getPath())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    Log.d("RecordView", "onStart")
                    //Toast.makeText(this@ChatActivity, "OnStartRecord", Toast.LENGTH_SHORT).show()
                }

                override fun onCancel() {
                    ivGallery.visibility = View.VISIBLE
                    edtMessage.visibility = View.VISIBLE
                    recordView.visibility = View.GONE

                    stopRecording(true)
                    //Toast.makeText(this@ChatActivity, "onCancel", Toast.LENGTH_SHORT).show()
                    Log.d("RecordView", "onCancel")
                }

                override fun onFinish(recordTime: Long, limitReached: Boolean) {
                    stopRecording(false)
                    lastMessage = "voice"
                    recordFile?.let {
                        uploadImage(it)
                    }
                    ivGallery.visibility = View.VISIBLE
                    edtMessage.visibility = View.VISIBLE
                    recordView.visibility = View.GONE

                    val time: String = getHumanTimeText(recordTime)!!
                    /*Toast.makeText(
                        this@ChatActivity,
                        "onFinishRecord - Recorded Time is: " + time + " File saved at " + recordFile!!.getPath(),
                        Toast.LENGTH_SHORT
                    ).show()*/
                    Log.d("RecordView", "onFinish Limit Reached? $limitReached")
                    Log.d("RecordTime", time)
                }

                override fun onLessThanSecond() {
                    ivGallery.visibility = View.VISIBLE
                    edtMessage.visibility = View.VISIBLE
                    recordView.visibility = View.GONE

                    stopRecording(true)
                    Toast.makeText(this@ChatActivity, "OnLessThanSecond", Toast.LENGTH_SHORT).show()
                    //Log.d("RecordView", "onLessThanSecond")
                }
            })


            recordView.setOnBasketAnimationEndListener {
                Log.d(
                    "RecordView",
                    "Basket Animation Finished"
                )
            }

            recordView.setRecordPermissionHandler(RecordPermissionHandler {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    return@RecordPermissionHandler true
                }
                val recordPermissionAvailable = ContextCompat.checkSelfPermission(
                    this@ChatActivity,
                    Manifest.permission.RECORD_AUDIO
                ) === PERMISSION_GRANTED
                if (recordPermissionAvailable) {
                    return@RecordPermissionHandler true
                }
                ActivityCompat.requestPermissions(
                    this@ChatActivity, arrayOf(Manifest.permission.RECORD_AUDIO),
                    0
                )
                false
            })
            recordView.setCustomSounds(R.raw.record_start, R.raw.record_finished, 0);


            ivSend.setOnClickListener {
                val message = edtMessage.text.toString()
                if (message.isNotEmpty()) {
                    if (isInConnection) {
                        sendMessage(message)
                    } else {
                        XmppServiceCommand.addContactToRoster(mActivity, jid, name)
                        isInConnection = true
                        sendMessage(message)
                    }
                }
            }
        }
        profileViewModel.apply {

            showLoading.observe(mActivity, {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            })
            errorMessage.observe(mActivity, {
                Log.e("TAG", "ERROR === $it")
                //mActivity.showSnackBar(binding.layoutMain, it)
            })
            chatImageUrl.observe(mActivity, { imagesList ->

                if(lastMessage.equals("voice"))
                {
                    XmppServiceCommand.sendVoice(this@ChatActivity, jid, imagesList.response.image)
                }else{
                    XmppServiceCommand.sendImage(this@ChatActivity, jid, imagesList.response.image)
                }

            })
            chatImageError.observe(mActivity, {
                Log.e("TAG", "ERROR=== $it")
            })
        }
    }

    private fun sendMessage(message: String) {
        binding.edtMessage.setText("")
        XmppServiceCommand.sendTypingStop(mActivity, jid)
        XmppServiceCommand.sendMessage(mActivity, jid, message)
    }

    override fun onDestroy() {
        super.onDestroy()
        XmppServiceCommand.sendTypingStop(mActivity, jid)
        xmppBroadcastReceiver.unregister(mActivity)
    }

    override fun onResume() {
        super.onResume()
        xmppBroadcastReceiver.register(mActivity)
        sortingRosters()
        binding.edtMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim { it <= ' ' }.isEmpty()) {
                    XmppServiceCommand.sendTypingStop(mActivity, jid)
                } else {
                    XmppServiceCommand.sendTyping(mActivity, jid)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                XmppServiceCommand.sendTyping(mActivity, jid)
            }

            override fun afterTextChanged(s: Editable?) {
                binding.apply {
                    if (s.toString().trim { it <= ' ' }.isEmpty()) {
                        XmppServiceCommand.sendTypingStop(mActivity, jid)
                        ivSend.visibility = View.GONE
                        ivRecord.visibility = View.VISIBLE
                    } else {
                        ivSend.visibility = View.VISIBLE
                        ivRecord.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun requestRuntimePermission(forAudio: Boolean) {
        Dexter.withContext(this).withPermissions(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                arrayListOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO
                )
            } else {
                arrayListOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO
                )
            }
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                when {
                    report.areAllPermissionsGranted() -> {
                        if (forAudio) {

                        } else {
                            ImagePicker.with(this@ChatActivity)
                                .crop()                    //Crop image(Optional), Check Customization for more option
                                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                                .maxResultSize(
                                    1080,
                                    1080
                                )    //Final image resolution will be less than 1080 x 1080(Optional)
                                .start()
                        }


                        //XmppServiceCommand.sendImage(this@ChatActivity,jid,)
                        // binding.llImage.visibility = View.VISIBLE
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
                AlertDialog.Builder(mActivity, R.style.SlideDialogTheme)
                    .setTitle(getString(R.string.app_name))
                    .setMessage(resources.getString(R.string.user_permission_message))
                    .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                        token.cancelPermissionRequest()
                    }
                    .setPositiveButton(android.R.string.ok) { dialog, _ ->
                        dialog.dismiss()
                        token.continuePermissionRequest()
                    }
                    .setOnDismissListener { token.cancelPermissionRequest() }
                    .show()
            }

        })
            .withErrorListener {
                Log.e("Dexter", "There was an error: $it")
            }
            .check()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            lastMessage = "image"
            val uri: Uri = data?.data!!
            var file = File(uri.path)
            uploadImage(file)

            //  var filePath = PathUtil.getPath(this@ChatActivity, uri)
            //XmppServiceCommand.sendImage(this@ChatActivity, jid, filePath)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {

        } else {

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
        val binding = DialogUnmatchReasonsBinding.inflate(layoutInflater)

        var reason: String
        val reasonAdapter = UnmatchReasonAdapter(this) {
            reason = it
            binding.btnUnmatchNext.isEnabled = reason.isNotEmpty()
        }

        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            setOnDismissListener {
                binding.imgDialogGradient.setDialogFadeOutAnimation {
                    // do nothing
                }
            }
            binding.btnUnmatchNext.setOnClickListener {
                binding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                    openUnmatchUserDialog()
                }
            }
            binding.rvUnmatchReason.apply {
                setDivider()
                adapter = reasonAdapter
            }
            reasonAdapter.addAll(reasonList)
            try {
                setBottomDialogWindowAttributes()
                show()
                binding.imgDialogGradient.setDialogFadeInAnimation()
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
                    mActivity.onBackPressed()
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
                                    mActivity,
                                    BlockReasonActivity::class.java
                                )
                            )
                        }
                        else -> {
                            mActivity.onBackPressed()
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

    /**
     * XMPP functionality
     */
    override fun setRosterList(list: ArrayList<XmppRosterEntry>) {
    }

    override fun onMessageSent(messageId: Long, message: Message) {
        Log.e("message sent", "sent")
    }

    override fun onMessageDeleted(messageId: Long) {
    }

    override fun onMessageAdded(remoteAccount: String?, message: Message, incoming: Boolean) {
        messageListAdapter.add(message)
        binding.rcvChat.smoothScrollToPosition(messageListAdapter.itemCount - 1)
    }

    override fun onRosterChanged() {
    }

    override fun onRemoveContact(contact: String?) {
    }

    override fun onTyping(from: String?) {
        if (from!!.contains(jid)) {
            binding.edtMessage.hint = getString(R.string.person_typing, name)
        }
    }

    override fun onTypingStop(from: String?) {
        binding.edtMessage.hint = getString(R.string.start_message)
    }

    private fun sortingRosters() {
        Thread {
            val list = mDatabase.chatDao().getAllRosters()
            Log.e("list size", list.size.toString())
            if (list.isNotEmpty()) {
                try {
                    list.any {
                        it.xmppJid.contentEquals(jid)
                    }.let {
                        isInConnection = it
                        Log.e("TAG:", "isInConnection $isInConnection")
                    }
                    if (isInConnection) {
                        try {
                            lifecycleScope.launch {
                                val chatUsers =
                                    Utils.getChatUsers("$phone${AppConstants.XMPP_EXTENSION}", jid)
                                val mList = mDatabase.chatDao().getAllMessageByRoster(chatUsers);
                                val rosterModel = mDatabase.chatDao().getRoster(jid)
                                runOnUiThread {

                                    var textDrawable: BitmapDrawable? = null
                                    if (name != "") {
                                        textDrawable = mActivity.createPlaceholderImage(
                                            name,
                                            100,
                                            R.color.blue_gradient_2,
                                            R.color.blue_gradient_3
                                        )
                                        Glide.with(mActivity)
                                            .load(image_url).diskCacheStrategy(
                                                DiskCacheStrategy.ALL
                                            )
                                            .apply(RequestOptions.circleCropTransform())
                                            .placeholder(textDrawable)
                                            .into(binding.imgUser)
                                    }

                                    val messageList = ArrayList<Message>()
                                    messageList.addAll(mList)
                                    messageList.sortBy {
                                        it.id
                                    }
                                    if (messageList.isNotEmpty()) {
                                        messageListAdapter.apply {
                                            addAll(messageList)
                                            getSenderAvatar(rosterModel.avatar)
                                        }
                                        binding.rcvChat.scrollToPosition(messageListAdapter.itemCount)
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }


    private fun uploadImage(clickedFile: File) {

        Log.d("imageFile", "-------->$clickedFile")
        val mediaTypeImage: MediaType = "image/*".toMediaTypeOrNull()!!
        val requestBody: RequestBody = clickedFile.asRequestBody(mediaTypeImage)
        val imageBody: MultipartBody.Part =
            MultipartBody.Part.createFormData("image", clickedFile.name, requestBody)
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){uploadImage(clickedFile)}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    uploadImage(clickedFile)
//                }
            }
            else -> {
                lifecycleScope.launch {
                    profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        if (authToken.isNotEmpty()) {
                            profileViewModel.sendChatImage(imageBody)
                        }
                    }
                }
            }
        }
    }

    override fun onRosterStatusChanges(xmppRosterEntry: XmppRosterEntry) {

    }


    private fun getHumanTimeText(milliseconds: Long): String? {
        return java.lang.String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(milliseconds),
            TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds))
        )
    }
    private fun stopRecording(deleteFile: Boolean) {
        audioRecorder!!.stop()
        if (recordFile != null && deleteFile) {
            recordFile!!.delete()
        }
    }
}