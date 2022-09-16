package com.swipefwd.ui.home.tribe

import android.Manifest
import android.app.Dialog
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.mukesh.countrypicker.CountryPicker
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.Settings
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ImageSpan
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.swipefwd.R
import com.swipefwd.databinding.ActivityContactsBinding
import com.swipefwd.databinding.DialogConnectionLimitBinding
import com.swipefwd.databinding.DialogSmsSentBinding
import com.swipefwd.data.models.UserContactDetails
import com.swipefwd.data.source.AppRepository
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.phoneNumberValidation
import com.swipefwd.utils.AppUtils.showSnackBarMargin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import java.lang.reflect.Type


class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding
    private val mActivity by lazy {
        this
    }
    private var countryCode = ""
    private var contactList = ArrayList<UserContactDetails>()
    private var userContactList = ArrayList<UserContactDetails>()
    private var userContactDetailsModel = UserContactDetails()
    private var mAdapter: ContactListAdapter? = null
    private var fwdAdapter: FriendsOnFwdAdapter? = null
    private val homeViewModel: HomeFragmentViewModel by viewModels {
        viewModelFactory { HomeFragmentViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var accountType = ""
    private var userId = 0
    private var remainingCount = 0
    private var completedList = ArrayList<String>()
    private var isReinvite: Int = 0
    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    private var isSMSSent: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initObservers()
    }

    private fun init() {
        val locale = resources.configuration?.locale
        val country2 =
            let { CountryPicker.Builder().with(mActivity).build() }
        val country1 = locale?.let { country2?.getCountryByLocale(locale) }
        if (country1 != null)
            countryCode = country1.dialCode //+91
        Log.e("TAG:", "countryCode : == $countryCode")
        val completeList = intent.getStringExtra("completeNumberList")
        if (!completeList.isNullOrEmpty()) {
            val type: Type =
                object : TypeToken<List<String?>?>() {}.type
            completedList =
                Gson().fromJson(completeList, type) as ArrayList<String>
            Log.e("TAG", "completedList === ${completedList.size}")
            Log.e("TAG", "completedListData === $completedList")
        }
        binding.apply {
            lifecycleScope.launch {
                remainingCount = homeViewModel.getRemainingUser.firstOrNull() ?: 10
                val isNewUser = homeViewModel.isNewUser.firstOrNull() ?: false
                if (isNewUser) {
                    remainingCount = 10
                    txtRemainingInvite.text = getString(
                        R.string.left_invite, "10"
                    )
                    homeViewModel.savePreference(PreferenceKeys.PREF_IS_NEW_USER, false)
                } else {
                    txtRemainingInvite.text = if (remainingCount < 0) {
                        getString(
                            R.string.left_invite, "0"
                        )
                    } else {
                        getString(
                            R.string.left_invite, remainingCount.toString()
                        )
                    }
                }
                accountType = homeViewModel.getAccountType.firstOrNull() ?: ""
                userId = homeViewModel.getUserId.firstOrNull() ?: 0
                homeViewModel.savePreference(
                    PreferenceKeys.PREF_REMAINING_INVITATION,
                    remainingCount
                )
            }
            ivBack.setOnClickListener {
                onBackPressed()
            }
            /*CoroutineScope(Dispatchers.IO).launch {
                val contactNumberList = ArrayList<String>()
                Log.d("TAG:", "CONTACTS: ${getContacts()}")
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
            homeViewModel.fetchContacts(mActivity, countryCode)
            ivClose.setOnClickListener {
                searchContact.setText("")
                txtFriendOnFwd.visibility = View.VISIBLE
                rbFriendsOnFwd.visibility = View.VISIBLE
                txtNoRecordFound.visibility = View.GONE
            }
            searchContact.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    mAdapter?.search(s.toString())
                    ivClose.visibility = if (s.toString().isNotEmpty()) {
                        rbFriendsOnFwd.visibility = View.GONE
                        txtFriendOnFwd.visibility = View.GONE
                        View.VISIBLE
                    } else {
                        txtFriendOnFwd.visibility = View.VISIBLE
                        rbFriendsOnFwd.visibility = View.VISIBLE
                        View.INVISIBLE
                    }
                }

            })
        }
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
                        cursor.getLong(cursor.getColumnIndex(Phone.CONTACT_LAST_UPDATED_TIMESTAMP))
                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    val calendar: Calendar = Calendar.getInstance()
                    calendar.timeInMillis = phoneDate
                    if (phoneNumber > 0) {
                        val cursorPhone = contentResolver.query(
                            Phone.CONTENT_URI,
                            null,
                            Phone.CONTACT_ID + "=?",
                            arrayOf(id),
                            null
                        )
                        if (cursorPhone!!.count > 0) {
                            while (cursorPhone.moveToNext()) {
                                var phoneNumValue = cursorPhone.getString(
                                    cursorPhone.getColumnIndex(Phone.NUMBER)
                                )
                                val phonesCursor = resolver.query(
                                    Phone.CONTENT_URI,
                                    null,
                                    Phone.CONTACT_ID + " = " + id,
                                    null,
                                    null
                                )
                                val phone = phoneNumValue
                                if (!phoneNumValue.startsWith("+")) {
                                    phoneNumValue = countryCode + phoneNumValue
                                }
                                while (phonesCursor!!.moveToNext()) {
                                    val type: Int =
                                        phonesCursor.getInt(phonesCursor.getColumnIndex(Phone.TYPE))
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

    private fun sendContactApi(contactList: JsonArray) {
     /*   val jsonArray = JsonArray()
        contactList.forEach {
            jsonArray.add(it)
        }*/
        val apiRequest = JsonObject().apply {
            add("contact_list", contactList)
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
                            apiRequest
                        )
                    }
                }
            }
        }
    }

    private fun initObservers() {
        homeViewModel.apply {
            showLoading.observe(mActivity) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(mActivity) {
                Log.e("TAG" , "ERROR === $it")
                mActivity.showSnackBar(binding.layoutMain , it)
            }
            daterContactData.observe(mActivity) { fwdUserList ->
                val userContactDetails = ArrayList<UserContactDetails>()
                if (fwdUserList.code == 1){
                    fwdUserList.data?.leftInvivitation?.let{
                        remainingCount = it
                    }
                    if (fwdUserList.data?.existUser?.size!!>0){

                        fwdUserList.data?.existUser?.onEach { modelList ->
                            contactList.find {
                                modelList.firstName == it.name
                            }?.let {
                                /*if (modelList.image.isNullOrBlank()) {
                                    userContactDetails.add(it)
                                } else {
                                    userContactDetails.add(
                                        it.copy(
                                            imageProfile = modelList.image,
                                            user_id = modelList.user_id,
                                            invitedStage = modelList.stage
                                        )
                                    )
                                }*/
                                userContactDetails.add(
                                    it.copy(
                                        imageProfile = modelList.profilePic ,
                                        user_id = modelList.id ,
                                        simpleNumber = modelList.mobile?.apply {
                                            if (startsWith("0")) replaceFirstChar { "" }
                                        } ,
                                        invitedStage = ""
                                    )
                                )
                            }
                        }}
                    if (!fwdUserList.data?.nonExistUser.isNullOrEmpty()){
                        contactList.clear()

                     fwdUserList.data.nonExistUser?.onEach { modelList ->

                         contactList.add(
                                  UserContactDetails(
                                        name = modelList.name,
//                                        imageProfile = modelList.profilePic ,
//                                        user_id = modelList.id ,
                                        simpleNumber = modelList.mobile?.apply {
                                            if (startsWith("0")) replaceFirstChar { "" }
                                        } ,
                                        friendsOnFwd = modelList.freindCount ,
                                        invitedStage = ""
                                    )
                         )
                        }
                    }
                        Log.e("TAG:" , "userContactDetails: $userContactDetails")


                }
               /* val pendingList = fwdUserList.pending?.filter {
                    it.user_id != null && it.user_id != 0
                    //filtering pending list here as invited via SMS user's don't
                    // have user_id so will no show in FWD section
                }
                val mList = fwdUserList.users?.plus(pendingList!!)*/
//                mList?.onEach { modelList ->

                binding.apply {
                    //setting FWD Friends List
                    if (userContactDetails.size <= 0) {
                        txtFriendOnFwd.visibility = View.GONE
                        rbFriendsOnFwd.visibility = View.GONE
                    }
                    txtRemainingInvite.text = if (remainingCount < 0) {
                        getString(
                            R.string.left_invite, "0"
                        )
                    } else {
                        getString(
                            R.string.left_invite, remainingCount.toString()
                        )
                    }
                    rbFriendsOnFwd.apply {
                        fwdAdapter = FriendsOnFwdAdapter(mActivity) {
                            //invited user
                            getTribeConnection(it)
                        }.apply {
                            addAll(userContactDetails)
                            setDivider()
                        }
                        adapter = fwdAdapter
                    }
                    //Setting Contact List view
                    contactList.let { list ->
                        rbContactList.apply {
                            try {
                                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                                setDivider()
                                mAdapter = ContactListAdapter(countryCode,listener = { model , reInvite ->
                                    userContactDetailsModel = model
                                    isReinvite = reInvite
                                    if(!userContactDetailsModel.simpleCountryCodeNumber!!.contains("+")){
                                        val x = "$countryCode${userContactDetailsModel.simpleCountryCodeNumber}"
                                        userContactDetailsModel.simpleCountryCodeNumber=x
                                    }
                                    var phoneWithRemovedSpace=userContactDetailsModel.phonebookNumber!!.toString().replace(" ","")
                                    if(userContactDetailsModel.phonebookNumber!!.startsWith("0") && phoneWithRemovedSpace.length == 11){
                                        var y = (phoneWithRemovedSpace).replaceFirstChar { countryCode }
                                        userContactDetailsModel.simpleCountryCodeNumber=y
                                    }
                                    requestRuntimePermission()
                                } , onNothingFound = {
                                    if (it) {
                                        rbContactList.visibility = View.GONE
                                        txtNoRecordFound.visibility = View.VISIBLE
                                    } else {
                                        rbContactList.visibility = View.VISIBLE
                                        txtNoRecordFound.visibility = View.GONE
                                    }
                                })
                                adapter = mAdapter
                               /* fwdUserList.expired_invitation?.onEach { pendingUser ->
                                    list.find {
                                        pendingUser.phone_number == it.simpleCountryCodeNumber
                                    }.let {
                                        it?.invitedStage = "expired"
                                        it?.reinvited = true

                                    }
                                }*/
                                /*val pendingList = fwdUserList.pending?.map {
                                    it.userNumber
                                } ?: arrayListOf()
                                pendingList.onEach { pendingNumber ->
                                    list.find {
                                        pendingNumber == it.simpleCountryCodeNumber
                                    }?.let {
                                        it.invitedStage = "pending"
                                    }
                                }*/
                                /*fwdUserList.pending?.onEach { pendingUser ->
                                    list.find {
                                        pendingUser.userNumber == it.simpleCountryCodeNumber
                                    }.let {
                                        it?.invitedStage = "pending"
                                        it?.reinvited = pendingUser.reinvited ?: false
                                        it?.isInvited = pendingUser.invited ?: false
                                    }
                                }*/
                                mAdapter?.addAll(list.filter { contact ->
                                    userContactDetails.none {
                                        it.simpleNumber == contact.simpleNumber
                                    } && !completedList.contains(contact.simpleNumber) && contact.simpleNumber!!.length>=6
                                }.toCollection(ArrayList()))
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    }
                }
            }


            contactError.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.response.toString())
            }


            inviteData.observe(mActivity) {
                if (it.response == "success") {
                    lifecycleScope.launch {
                        mAdapter?.showAdded(isReinvite , true)
                        remainingCount = it.count ?: 10
                        homeViewModel.savePreference(
                            PreferenceKeys.PREF_REMAINING_INVITATION ,
                            remainingCount
                        )
                        Log.d("TAG" , "count: $remainingCount")
                        binding.txtRemainingInvite.text = if (remainingCount < 0) {
                            getString(R.string.left_invite , "0")
                        } else {
                            getString(R.string.left_invite , remainingCount.toString())
                        }
                        if (!userContactDetailsModel.name.isNullOrEmpty()) {
                            if (userContactDetailsModel.name!!.split(" ")[0] != null) {
                                val name = userContactDetailsModel.name!!.split(" ")[0]
                                //open message sheet to send SMS
                                isSMSSent = true
                                val smsBody = "Hello, " + name + "!" +
                                        " I have an invite to FWD and I want you to join. \uD83D\uDE42\n" +
                                        "\n" +
                                        "FWD is the new social networking app that allows people to meet and date through the sources they trust and not just an algorithm.\n" +
                                        "\n" +
                                        "This personal invite expires in 24 hours.\n\n" +
                                        "Here's the link!\n" +
//                                        "https://swipefwd.com/download"
//                                        "https://swipefwd.iapplabz.co.in/newaccount"
                                        "https://swipefwdapp.page.link/newaccount"
                                Log.d("TAG:" , "SMS:  $smsBody")
                                val sharingIntent = Intent(Intent.ACTION_SEND)
                                sharingIntent.type = "text/plain"
                                sharingIntent.putExtra(
                                    Intent.EXTRA_TEXT ,
                                    smsBody
                                )
                                startActivity(Intent.createChooser(sharingIntent , "Share using"))
                            }
                        }
                    }
                } else {
                    if (it.count == 0) {
                        openConnectionLimitDialog()
                    } else {
                        mActivity.showSnackBar(binding.layoutMain , it.message.toString())
                    }
                }
            }
            tribeInvitedData.observe(mActivity) {
                if (it.code == 1) {
                    lifecycleScope.launch {
                        fwdAdapter?.showAdded(true)
                        remainingCount = it.data?.leftInvitation ?: 10
                        homeViewModel.savePreference(
                            PreferenceKeys.PREF_REMAINING_INVITATION ,
                            remainingCount
                        )
                        Log.d("TAG" , "count: $remainingCount")
                        binding.txtRemainingInvite.text = if (remainingCount < 0) {
                            getString(R.string.left_invite , "0")
                        } else {
                            getString(R.string.left_invite , remainingCount.toString())
                        }
                    }
                } else {
                    if (it.code == 0) {
                    /*    openConnectionLimitDialog()
                    } else {*/
                        mActivity.showSnackBar(binding.layoutMain , it.message.toString())
                    }
                }
            }
            error.observe(mActivity) {
                Log.e("TAG" , "ERROR=== $it")
                fwdAdapter?.showAdded(false)
                mAdapter?.showAdded(isReinvite , false)
                if (it.response == "success") {
                    if (it.count == 0) {
                        openConnectionLimitDialog()
                    } else {
                        mActivity.showSnackBar(binding.layoutMain , it.message.toString())
                    }
                } else {
                    if (it.message.equals("you can not make connection more than 10 people")) {
                        openConnectionLimitDialog()
                    } else {
                        mActivity.showSnackBar(binding.layoutMain , it.message.toString())
                    }
                }
            }
            contactsLiveData.observe(mActivity) {
                Log.e("TAG" , "CONTACT=== $it")
                val contactNumberList = ArrayList<String>()
                val contact = JsonArray()
                if (it != null)
                    contactList = it.distinctBy { userModel ->
                        userModel.name
                    }.toCollection(ArrayList())
                if (!contactList.isNullOrEmpty()) {
                    lifecycleScope.launch {
                        val loggedInNumber = homeViewModel.userPhoneNumber.firstOrNull() ?: ""
                        contactList.removeAll { model ->
                            loggedInNumber == model.simpleCountryCodeNumber
                        }
                        contactList.forEach { model ->
//                            contactNumberList.add(model.simpleCountryCodeNumber ?: "")
                            val nameObj = JsonObject()
                            nameObj.addProperty("name",model.name)
                            nameObj.addProperty("mobile",model.simpleNumber)
                            contact.add(nameObj)
                        }

                        sendContactApi(contact)
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
                    inviteContactApi()
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

    private fun inviteContactApi() {
        val phoneNumber = userContactDetailsModel.simpleCountryCodeNumber
        if (phoneNumber !=null && mActivity.phoneNumberValidation(phoneNumber)){
            lifecycleScope.launch {
                val jsonObject = JsonObject().apply {
                    addProperty(
                        "phone_number",
                        userContactDetailsModel.simpleCountryCodeNumber
                    )
                    addProperty("name", userContactDetailsModel.name)
                    addProperty("branch_link", "")
                }
                when {
                    !AppUtils.isNetworkAvailable(mActivity) -> {
                        openFailNetworkDialog(){inviteContactApi()}
//                    AppUtils.showMessageOK(
//                        mActivity,
//                        getString(R.string.app_name),
//                        getString(R.string.common_retry),
//                        getString(R.string.no_internet)
//                    ) { _, _ ->
//                        inviteContactApi()
//                    }
                    }
                    else -> {
                        homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                            homeViewModel.inviteContactApi(
                                "Bearer $authToken", jsonObject
                            )
                        }
                    }
                }
            }
        }
        else {
            showSnackBarMargin(
                v = binding.layoutMain,
                message = getString(R.string.invalid_number)
            )
        }
    }

    private fun openConnectionLimitDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val binding = DialogConnectionLimitBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                btnContinue.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                }
                btnHome.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        this@ContactsActivity.onBackPressed()
                    }
                }
                setZoomDialogWindowAttributes()
                show()
                imgDialogGradient.setDialogFadeInAnimation()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun getTribeConnection(userContactDetails: UserContactDetails) {
        val apiRequest = JsonObject().apply {
//            addProperty("connector_id", userId) //self user id
            addProperty("connector_id", userContactDetails.user_id)
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getTribeConnection(userContactDetails)}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getTribeConnection(userContactDetails)
//                }
            }
            else -> {
                lifecycleScope.launch {
                    homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        homeViewModel.inviteConnectorApi(
                             apiRequest
                        )
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //show dialog for SMS
        lifecycleScope.launch {
            if (homeViewModel.isSmsSent.firstOrNull() == false) {
                if (isSMSSent) {
                    openSMSSentDialog()
                }
            }
        }
    }

    private fun openSMSSentDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val binding = DialogSmsSentBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                val image =
                   ImageSpan(mActivity, R.drawable.icon_activity_new, ImageSpan.ALIGN_BASELINE)
                val spannableText =
                    SpannableString("Your friend has 24 hours to respond\n\nIf they accept, they will be displayed on your Home Screen\n\nClick on the Activity icon   to see the status of your invite").apply {
                        setSpan(image, 124, 125, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                txtMessage.text = spannableText
                btnContinue.setOnClickListener {
                    homeViewModel.savePreference(PreferenceKeys.PREF_SMS_SENT, true)
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                }
                setZoomDialogWindowAttributes()
                show()
                imgDialogGradient.setDialogFadeInAnimation()
            }
        }
    }
}