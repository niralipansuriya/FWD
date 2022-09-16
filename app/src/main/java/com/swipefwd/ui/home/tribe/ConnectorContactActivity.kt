package com.swipefwd.ui.home.tribe

import android.Manifest
import android.app.Dialog
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.mukesh.countrypicker.CountryPicker
import com.swipefwd.R
import com.swipefwd.databinding.ActivityConnectorContactBinding
import com.swipefwd.databinding.DialogNoMessageBinding
import com.swipefwd.databinding.DialogTribeChatBinding
import com.swipefwd.databinding.DialogTribeMemberConnectionBinding
import com.swipefwd.data.models.DaterConnectionResponseModel
import com.swipefwd.data.models.UserContactDetails
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.xmpp.XmppServiceCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ConnectorContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConnectorContactBinding
    private val mActivity by lazy {
        this@ConnectorContactActivity
    }
    private var selectedUser = DaterConnectionResponseModel.User()
    private var user2 = UserContactDetails()
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var countryCode = ""
    private var contactList = ArrayList<UserContactDetails>()
    private var mAdapter: ConnectorContactListAdapter? = null
    private var userContactDetailsModel = UserContactDetails()
    private val homeViewModel: HomeFragmentViewModel by viewModels {
        viewModelFactory { HomeFragmentViewModel(mActivity, AppRepository()) }
    }
    private var isSMS = false
    private val dialogs: Vector<Dialog> = Vector<Dialog>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectorContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initObservers()
    }

    private fun init() {
        binding.apply {
            ivBack.setOnClickListener {
                onBackPressed()
            }
            val locale = resources.configuration?.locale
            val country2 =
                let { CountryPicker.Builder().with(mActivity).build() }
            val country1 = locale?.let { country2?.getCountryByLocale(locale) }
            if (country1 != null)
                countryCode = country1.dialCode



            homeViewModel.fetchContacts(mActivity, countryCode)
            val intentModel = intent.getStringExtra("selectedUser")
            if (!intentModel.isNullOrEmpty()) {
                selectedUser = Gson().fromJson<Any>(
                    intentModel,
                    DaterConnectionResponseModel.User::class.java
                ) as DaterConnectionResponseModel.User
            }
            txtContacts.text =
                getString(R.string.selected_contact, selectedUser.username!!.split(" ")[0] ?: "")

            btnContinue.setOnClickListener {
                requestRuntimePermission()
            }

            searchContact.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    mAdapter?.search(newText) {
                        if (it) {
                            rbContactList.visibility = View.GONE
                            txtNoRecordFound.visibility = View.VISIBLE
                        } else {
                            rbContactList.visibility = View.VISIBLE
                            txtNoRecordFound.visibility = View.GONE
                        }
                    }
                    return false
                }
            })
        }

        /*CoroutineScope(Dispatchers.Main).launch {
                val contactNumberList = ArrayList<String>()
                if (getContacts() != null)
                    contactList = getContacts()?.distinctBy { it.name }!!.toCollection(ArrayList())
                if (!contactList.isNullOrEmpty()) {
                    lifecycleScope.launch {
                        val loggedInNumber = homeViewModel.userPhoneNumber.firstOrNull() ?: ""
                        contactList.removeAll {
                            loggedInNumber == it.simpleCountryCodeNumber
                        }
                        contactList.forEach { model ->
                            contactNumberList.add(model.simpleCountryCodeNumber ?: "")
                        }
                        sendContactApi(contactNumberList)
                    }
                }
            }*/
    }

    private fun sendContactApi(contactList: ArrayList<String>) {
        val jsonArray = JsonArray()
        contactList.forEach {
            jsonArray.add(it)
        }
        val apiRequest = JsonObject().apply {
            add("contact", jsonArray)
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){sendContactApi(contactList)}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    sendContactApi(contactList)
//                }
            }
            else -> {
                lifecycleScope.launch {
                    homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        homeViewModel.sendContactApi(
                            "Bearer $authToken", apiRequest
                        )
                    }
                }
            }
        }
    }

    private fun requestRuntimePermission() {
        Dexter.withContext(mActivity)
            .withPermission(Manifest.permission.SEND_SMS)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    suggestionProfileApi()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied) {
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

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    private suspend fun getContacts(): ArrayList<UserContactDetails>? =
        withContext(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                progressBarHandler.show()
            }
            val resolver: ContentResolver = contentResolver
            val cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null,
                null
            )
            val locale = resources.configuration?.locale
            val country2 =
                let { CountryPicker.Builder().with(mActivity).build() }
            val country1 = locale?.let { country2?.getCountryByLocale(locale) }
            if (country1 != null)
                countryCode = country1.dialCode //+91
            Log.e("TAG:", "countryCode : == $countryCode")
            val mContactList = ArrayList<UserContactDetails>()
            if (cursor!!.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
                    val phoneNumber = (cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                    )).toInt()
                    val phoneDate =
                        cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_LAST_UPDATED_TIMESTAMP))
                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    val calendar: Calendar = Calendar.getInstance()
                    calendar.timeInMillis = phoneDate
                    if (phoneNumber > 0) {
                        val cursorPhone = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                            arrayOf(id),
                            null
                        )
                        if (cursorPhone!!.count > 0) {
                            while (cursorPhone.moveToNext()) {
                                var phoneNumValue = cursorPhone.getString(
                                    cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                )
                                val phonesCursor = resolver.query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                    null,
                                    null
                                )
                                val phone = phoneNumValue
                                if (!phoneNumValue.startsWith("+")) {
                                    phoneNumValue = countryCode + phoneNumValue
                                }
                                while (phonesCursor!!.moveToNext()) {
                                    val type: Int =
                                        phonesCursor.getInt(
                                            phonesCursor.getColumnIndex(
                                                ContactsContract.CommonDataKinds.Phone.TYPE
                                            )
                                        )
                                    mContactList.add(
                                        UserContactDetails(
                                            id = id,
                                            numberType = type.toString(),
                                            name = name,
                                            phonebookNumber = phone,
                                            simpleCountryCodeNumber = phoneNumValue.replace(
                                                "[(,)]".toRegex(),
                                                ""
                                            ).replace("\\s".toRegex(), "")
                                                .replace("-".toRegex(), ""),
                                            formattedNumber = phoneNumValue.replace(
                                                "\\s".toRegex(),
                                                "-"
                                            )
                                                .replace("[(,)]".toRegex(), "")
                                        )
                                    )
                                    Log.e("TYPE ===>", "TYPE:==$type")
                                    Log.d("TAG", "DATE: $name+" + formatter.format(calendar.time))
                                }
                                phonesCursor.close()
                            }
                        }
                        cursorPhone.close()
                    }
                }
                cursor.close()
                return@withContext mContactList
            } else {
                cursor.close()
                Log.e("TAG", "No contacts available!")
                return@withContext null
            }
        }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun openChatDialog() {
        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val chatBinding = DialogTribeChatBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(chatBinding.root)
            chatBinding.apply {
                txt1.text = getString(
                    R.string.connect_title_1,
                    selectedUser.username!!.split(" ")[0],
                    user2.name!!.split(" ")[0]
                )
                if (selectedUser.username != "") {
                    val textDrawable = mActivity.createPlaceholderImage(
                        selectedUser.username!!,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )

                    Glide.with(mActivity)
                        .load(selectedUser.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable)
                        .into(img1)
                }
                if (user2.name != "") {
                    val textDrawable2 = mActivity.createPlaceholderImage(
                        user2.name!!,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )

                    Glide.with(mActivity)
                        .load(user2.imageProfile).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable2)
                        .into(img2)
                }
                ivSend.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        XmppServiceCommand.addContactToRoster(
                            mActivity,
                            selectedUser.phone_number + AppConstants.XMPP_EXTENSION,
                            selectedUser.username
                        )
                        XmppServiceCommand.addContactToRoster(
                            mActivity,
                            user2.simpleCountryCodeNumber + AppConstants.XMPP_EXTENSION,
                            user2.name
                        )

                        XmppServiceCommand.sendMessage(
                            mActivity,
                            selectedUser.phone_number + AppConstants.XMPP_EXTENSION,
                            edtMessage.text.toString()
                        )
                        XmppServiceCommand.sendMessage(
                            mActivity,
                            user2.simpleCountryCodeNumber + AppConstants.XMPP_EXTENSION,
                            edtMessage.text.toString()
                        )
                        openTribeConnectionDialog()
                    }
                }
                imgCancel.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        openNoMessageDialog()
                    }
                }
                edtMessage.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        val str = p0.toString().trim()
                        if (str.isNotEmpty()) {
                            ivRecord.visibility = View.GONE
                            ivSend.visibility = View.VISIBLE
                        } else {
                            ivRecord.visibility = View.VISIBLE
                            ivSend.visibility = View.GONE
                        }
                    }

                })
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                chatBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openNoMessageDialog() {
        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val messageBinding = DialogNoMessageBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(messageBinding.root)
            messageBinding.apply {
                txtBackground.apply {
                    background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.top_mint_corner_gradient_bg,
                        theme
                    )
                    setTextColor(getColor(resources, R.color.colorPagerDesc, theme))
                }
                txtDesc.text = getString(
                    R.string.no_message_desc_send,
                    selectedUser.username!!.split(" ")[0] + "'s",
                    user2.name!!.split(" ")[0]
                )
                btnQuickNote.apply {
                    background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.corner_mint_gradient_bg,
                        theme
                    )
                    setTextColor(getColor(resources, R.color.colorPagerDesc, theme))
                }
                btnSure.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        openTribeConnectionDialog()
                    }
                }
                btnQuickNote.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        openChatDialog()
                    }
                }
                try {
                    setBottomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun openTribeConnectionDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val connectionBinding = DialogTribeMemberConnectionBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(connectionBinding.root)
            connectionBinding.apply {
                txtDesc.text =
                    getString(
                        R.string.tribe_connected_1,
                        selectedUser.username!!.split(" ")[0],
                        user2.name!!.split(" ")[0]
                    )
                btnDontShow.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        onBackPressed()
                    }
                }
                btnGotIt.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        onBackPressed()
                    }
                }
                try {
                    setBottomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun suggestionProfileApi() {
        lifecycleScope.launch {
            val jsonObject = JsonObject().apply {
                val number = if (isSMS) {
                    userContactDetailsModel.simpleCountryCodeNumber
                } else {
                    user2.simpleCountryCodeNumber
                }
                addProperty("phone_number", number)
                addProperty("ref_profile_suggest", selectedUser.userid)
            }
            when {
                !AppUtils.isNetworkAvailable(mActivity) -> {
                    openFailNetworkDialog(){suggestionProfileApi()}
//                    AppUtils.showMessageOK(
//                        mActivity,
//                        getString(R.string.app_name),
//                        getString(R.string.common_retry),
//                        getString(R.string.no_internet)
//                    ) { _, _ ->
//                        suggestionProfileApi()
//                    }
                }
                else -> {
                    homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        homeViewModel.suggestionProfileApi(
                            "Bearer $authToken", jsonObject
                        )
                    }
                }
            }
        }
    }

    private fun initObservers() {
        homeViewModel.apply {
            showLoading.observe(mActivity, {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            })
            errorMessage.observe(mActivity, {
                Log.e("TAG", "ERROR === $it")
                mActivity.showSnackBar(binding.layoutMain, it)
            })
            inviteData.observe(mActivity, {
                if (it.response == "success") {
                    if (isSMS) {
                        //open message sheet to send SMS
                        val smsBody =
                            "Hey, ${userContactDetailsModel.name!!.split(" ")[0] + "!"}\n\n" + "I want to introduce you to\n" + selectedUser.username!!.split(
                                " "
                            )[0] +
                                    "--someone I know and I think is\n" + "great." +
                                    "\n\nDownload FWD today to see all their\n" + "details and to get connected!" +
                                    "\n\n" +
                                    "http://qa.swipefwd.com/web_preview.html"
                        Log.d("TAG:", "SMS:  $smsBody")

                        val sharingIntent = Intent(Intent.ACTION_SEND)
                        sharingIntent.type = "text/plain"
                        sharingIntent.putExtra(
                            Intent.EXTRA_TEXT,
                            smsBody
                        )
                        startActivity(Intent.createChooser(sharingIntent, "Share using"))
                       /* val url = Uri.parse("smsto:${userContactDetailsModel.phonebookNumber}")
                        val smsIntent = Intent(Intent.ACTION_SENDTO, url)
                        smsIntent.apply {
                            putExtra("sms_body", smsBody)
                        }
                        startActivity(smsIntent)*/
                    } else {
                        openChatDialog()
                    }
                } else {
                    mActivity.showSnackBar(binding.layoutMain, it.message.toString())
                }
            })

            contactsLiveData.observe(mActivity, {
                Log.e("TAG", "CONTACT=== $it")
                val contactNumberList = ArrayList<String>()
                if (it != null)
                    contactList = it.distinctBy { userModel ->
                        userModel.name
                    }.toCollection(ArrayList())
                if (!contactList.isNullOrEmpty()) {
                    lifecycleScope.launch {
                        val loggedInNumber = homeViewModel.userPhoneNumber.firstOrNull() ?: ""
                        contactList.removeAll { model->
                            loggedInNumber == model.simpleCountryCodeNumber
                        }
                        contactList.forEach { model ->
                            contactNumberList.add(model.simpleCountryCodeNumber ?: "")
                        }
                        sendContactApi(contactNumberList)
                    }
                }
            })

            suggestionModel.observe(mActivity, {
                if (it.response == "success") {
                    if (isSMS) {
                        //open message sheet to send SMS
                        val smsBody =
                            "Hey, ${userContactDetailsModel.name!!.split(" ")[0] + "!"}\n\n" + "I want to introduce you to\n" + selectedUser.username!!.split(
                                " "
                            )[0] +
                                    "--someone I know and I think is\n" + "great." +
                                    "\n\nDownload FWD today to see all their\n" + "details and to get connected!" +
                                    "\n\n" +
                                    "http://qa.swipefwd.com/web_preview.html"
                        Log.d("TAG:", "SMS:  $smsBody")

                        val sharingIntent = Intent(Intent.ACTION_SEND)
                        sharingIntent.type = "text/plain"
                        sharingIntent.putExtra(
                            Intent.EXTRA_TEXT,
                            smsBody
                        )
                        startActivity(Intent.createChooser(sharingIntent, "Share using"))
                        /* val url = Uri.parse("smsto:${userContactDetailsModel.phonebookNumber}")
                         val smsIntent = Intent(Intent.ACTION_SENDTO, url)
                         smsIntent.apply {
                             putExtra("sms_body", smsBody)
                         }
                         startActivity(smsIntent)*/
                    } else {
                        openChatDialog()
                    }
                } else {
                    mActivity.showSnackBar(binding.layoutMain, it.message.toString())
                }
            })
            contactData.observe(mActivity, { fwdUserList ->
                val userContactDetails = ArrayList<UserContactDetails>()
                val pendingList = fwdUserList.pending?.filter {
                    it.user_id != null && it.user_id != 0
                    //filtering pending list here as invited via SMS user's don't
                    //have user_id so will no show in FWD section
                }
                val mList = fwdUserList.users?.plus(pendingList!!)
                mList?.onEach { modelList ->
                    contactList.find {
                        modelList.userNumber == it.simpleCountryCodeNumber
                    }?.let {
                        userContactDetails.add(
                            it.copy(
                                imageProfile = modelList.image,
                                user_id = modelList.user_id,
                                invitedStage = modelList.stage
                            )
                        )
                    }
                }
                Log.e("TAG:", "userContactDetails: $userContactDetails")
                binding.apply {
                    //setting FWD Friends List
                    if (userContactDetails.size <= 0) {
                        txtFriendOnFwd.visibility = View.GONE
                        rbFriendsOnFwd.visibility = View.GONE
                    }
                    rbFriendsOnFwd.apply {
                        adapter = ConnectorFriendsOnFwdAdapter(mActivity, listener = {
                            //open dialog to send msg
                            isSMS = false
                            user2 = it
                            suggestionProfileApi()
                        }).apply {
                            setDivider()
                            addAll(userContactDetails.filter {
                                it.user_id != 1 //showing only daters for connections
                            }.toCollection(ArrayList()))
                        }
                    }
                    //Setting Contact List view
                    contactList.let { list ->
                        rbContactList.apply {
                            try {
                                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                                setDivider()
                                mAdapter = ConnectorContactListAdapter(listener = {
                                    isSMS = true
                                    userContactDetailsModel = it
                                    btnContinue.visibility = View.VISIBLE
                                })
                                adapter = mAdapter
                                val expiredList = fwdUserList.expired_invitation?.map {
                                    it.phone_number
                                } ?: arrayListOf()
                                list.onEach {
                                    it.isInvited = expiredList.contains(it.simpleCountryCodeNumber)
                                }
                                val pendingList = fwdUserList.pending?.map {
                                    it.userNumber
                                } ?: arrayListOf()
                                pendingList.onEach { pendingNumber ->
                                    list.find {
                                        pendingNumber == it.simpleCountryCodeNumber
                                    }?.let {
                                        it.invitedStage = "pending"
                                    }
                                }
                                mAdapter?.addAll(list.filter { contact ->
                                    userContactDetails.none {
                                        it.simpleCountryCodeNumber == contact.simpleCountryCodeNumber
                                    }
                                }.toCollection(ArrayList()))
                                progressBarHandler.dismiss()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            })
            contactError.observe(mActivity, {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.response.toString())
            })
            error.observe(mActivity, {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.message.toString())
            })
        }
    }
    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if(it.isShowing)
            {
                it.dismiss()
            }
        }
    }
}