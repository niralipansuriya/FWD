package com.swipefwd.ui.home.tribe

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import com.skydoves.balloon.balloon
import com.swipefwd.Injection
import com.swipefwd.R
import com.swipefwd.animations.AnimationBounceDown
import com.swipefwd.animations.SlideView
import com.swipefwd.data.models.DaterConnectionResponseModel
import com.swipefwd.data.models.TribeDaterConnectionsResponseModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.*
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.activity.ActivityCameraVerification
import com.swipefwd.ui.auth.login.LoginViewModel
import com.swipefwd.ui.home.AgreementActivity
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.home.message.ChatActivity
import com.swipefwd.ui.home.settings.AccountActivity
import com.swipefwd.ui.home.settings.SettingsFragment
import com.swipefwd.ui.home.settings.SettingsViewModel
import com.swipefwd.ui.profile.EmailActivity
import com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel
import com.swipefwd.ui.updateuserprofile.UserProfileActivity
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.*
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.dpToPx
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.xmpp.XmppRosterEntry
import com.swipefwd.xmpp.XmppServiceBroadcastEventReceiver
import com.swipefwd.xmpp.XmppServiceCommand
import com.swipefwd.xmpp.database.ChatListener
import com.swipefwd.xmpp.database.Message
import com.yuyakaido.android.cardstackview.internal.DisplayUtil
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MyMatchMakerListFragment : Fragment() , ChatListener , SurfaceHolder.Callback {
    private var mGestureVerificationImage: Bitmap? = null
    private var gestureVerificationStartForResult: ActivityResultLauncher<Intent>? = null
    private var gender: String? = ""
    private var recoveryEmail = ""
    private val mDateFormat = SimpleDateFormat("MM/dd/yyyy")
    private var showProfileVerificationDialogDate = Date()
    private var showEmailRecoveryDialogDate = Date()

    //private var profileCreateDate: Date?=Date()
    private var isProfileVerified = false
    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(requireContext() , AppRepository()) }
    }
    private var mAnimation = SlideView()
    private var camera: Camera? = null
    private var surfaceHolder: SurfaceHolder? = null

    private var _binding: FragmentMyMatchMakerListBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeFragmentViewModel by viewModels {
        viewModelFactory { HomeFragmentViewModel(requireContext() , AppRepository()) }
    }
    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory {
            LoginViewModel(
                Injection.getAppRepository() ,
                Injection.getInternalAppDataStore(requireActivity().applicationContext) ,
                Injection.getAppDataBase(requireActivity().applicationContext)
            )
        }
    }
    private val userViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(requireContext() , AppRepository()) }
    }
    private val settingsViewModel: SettingsViewModel by viewModels {
        viewModelFactory { SettingsViewModel(requireContext() , AppRepository()) }
    }
    private var xmppBroadcastReceiver =
        XmppServiceBroadcastEventReceiver(this@MyMatchMakerListFragment)
    private val progressBarHandler by lazy {
        ProgressBarHandler(requireActivity())
    }
    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    private var completePendingList: ArrayList<DaterConnectionResponseModel.User>? = null
    private val mAdapter by lazy {
        MyMatchMakerListAdapter(requireActivity() , startChat = {
            //start chat here for person
           /* context?.startActivity(
                Intent(context , ChatActivity::class.java)
                    .putExtra("jid" , it.phone_number + AppConstants.XMPP_EXTENSION)
                    .putExtra("name" , it.username)
                    .putExtra("image_url" , it.image)
            )*/
        } , deleteListener = {
            lifecycleScope.launch {
                val isShow = homeViewModel.isShowDelete.firstOrNull() ?: false
                if (isShow) {
                    removeTribeDater(it)
                } else {
                    openDeleteDialog(it)
                }
            }
        })
    }
    private var isAddedDialog = false
    private val toolTipBinding by balloon<ToolTipFactory>()
    private var daterList: ArrayList<DaterConnectionResponseModel.User>? = null
    private var daterTribeList: ArrayList<TribeDaterConnectionsResponseModel.TribeModel.Tribe>? = arrayListOf()
    private var showProfilePicture = false
    private var bubbleListener: BubbleListener? = null
    private val mDatabase by lazy {
        AppDatabase.getInstance(requireActivity())
    }
    private var showProfile = false
    var accountType = ""
    var daterDisabled = false
    var connectorDisabled = false
    private var remainingCount = 0

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyMatchMakerListBinding.inflate(inflater , container , false)
        gestureVerificationStartForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    var bmp: Bitmap? = null
                    val filename = result.data?.extras?.getString("verification_picture")
                    try {
                        val `is` = requireActivity().openFileInput(filename)
                        bmp = BitmapFactory.decodeStream(`is`)
                        `is`.close()
                        mGestureVerificationImage = bmp
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                    openFinalGestureDialog()
//                    mGestureVerificationByteArray = result.data?.getByteArrayExtra("verification_picture")
//                    mGestureVerificationImage = result.data?.getParcelableExtra("verification_picture")
//                    mGestureVerificationString = result.data?.getStringExtra("verification_picture")
//                    mGestureVerificationImage = ActivityCameraVerification().stringToBitMap(mGestureVerificationString)
                }
            }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        binding.apply {
            //toggle changes
            toggleUser.text = getString(R.string.user_dater)
            toggleUser.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
                override fun onSlideComplete(view: SlideToActView) {
                    view.apply {
                        resetSliderNoAnim()
                        if (text == getString(R.string.user_dater)) {
                            isReversed = true
                            text = getString(R.string.user_matchmaker)
                            innerColor = ContextCompat.getColor(requireActivity() , R.color.white)
                            outerColor = ContextCompat.getColor(requireActivity() , R.color.colorPagerDesc)
                            strokeColor = ContextCompat.getColor(requireActivity() , R.color.colorPagerDesc)
                            iconColor = ContextCompat.getColor(requireActivity() , R.color.grey_light)
                            changeUserTypeApi()
                        }else if (text == getString(R.string.user_matchmaker)) {
                            isReversed = false
                            text = getString(R.string.user_dater)
                            innerColor = ContextCompat.getColor(requireActivity() , R.color.colorPagerDesc)
                            outerColor = ContextCompat.getColor(requireActivity() , R.color.whiteColor)
                            strokeColor = ContextCompat.getColor(requireActivity() , R.color.gray_30)
                            iconColor = ContextCompat.getColor(requireActivity() , R.color.whiteColor)
//                            changeUserTypeApi()
                        }
                    }
                }
            }

            txtSwiping.setOnClickListener {
                if (!getDisableLogic()) {
                    redirectToLoading()
                }
            }
            txtTip.setOnClickListener {
                lifecycleScope.launch {
                    if (homeViewModel.getIsAccountTip.firstOrNull() == false)
                        showDaterToolTip()
                }
            }
            ////////////////////////////////////////////////
            txtAddFriend.setOnClickListener {
                if (txtAddFriend.text.equals("Reactivate Account")) {
                    requireActivity().nextActivity(AccountActivity::class.java)
                    ((requireActivity() as TabManagerActivity)).addFragment(SettingsFragment())
                    /*lifecycleScope.launch {
                        var userId = settingsViewModel.getUserId.firstOrNull() ?: 0
                        var connector = userViewModel.getDaterDisabled.firstOrNull() ?: false

                        val jsonObject = JsonObject()
                        jsonObject.addProperty("is_dater_disabled", !connector)
                        lifecycleScope.launch {
                            settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                                settingsViewModel.userSettingsUpdate(
                                    "Bearer $authToken", userId, jsonObject
                                )
                            }
                        }
                    }*/

                } else {
                    if (remainingCount == 0) {
                        openConnectionLimitDialog()
                    } else {
                        requestRuntimePermission()
                    }
                }

            }
            xmppBroadcastReceiver = XmppServiceBroadcastEventReceiver(this@MyMatchMakerListFragment)
            activity?.let {
                XmppServiceCommand.sendRosterToActivity(requireActivity())
                initObservers()

            }
        }
    }

    private fun requestRuntimePermission() {
        Dexter.withContext(activity)
            .withPermission(Manifest.permission.READ_CONTACTS)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    //activity?.nextActivity(ContactsActivity::class.java)
                   /* val completeNumberList = daterList?.map {
                        it.phone_number
                    }*/
                    startActivity(
                        Intent(
                            activity ,
                            ContactsActivity::class.java
                        )/*.putExtra("completeNumberList" , Gson().toJson(completeNumberList))*/
                    )
                    activity?.overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied) {
                        val intent =
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts(
                            "package" ,
                            activity?.packageName , null
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
                    permission: PermissionRequest ,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    private fun inviteContactListApi() {
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog() { inviteContactListApi() }
//                AppUtils.showMessageOK(
//                    requireActivity(),
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    inviteContactListApi()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        homeViewModel.getTribeConnectionListApi()
                    }
                }
            }
        }
    }

    private fun initObservers() {

        loginViewModel.apply {
            facebookData.observe(requireActivity()) {
                redirectToLoading()
            }
            facebookError.observe(requireActivity()) {
                redirectToLoading()
            }
        }
        homeViewModel.apply {
            showLoading.observe(requireActivity()) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(viewLifecycleOwner) {
                Log.e("TAG", "ERROR === $it")
                requireActivity().showSnackBar(binding.layoutMain, it)
            }
            tribeConnectionsData.observe(viewLifecycleOwner) { responseModel ->

                if (responseModel.code == 1 && !responseModel.data?.tribe.isNullOrEmpty()){
                    daterTribeList = (responseModel.data?.tribe?.filter { model-> model.firstName!=null })?.toCollection(ArrayList())
                }

             /*   showProfilePicture = responseModel.profile_photo ?: false
                bubbleListener?.onShowBubble(responseModel.is_request_pending!!)
                if (responseModel.is_request_pending!!) {
                    val anim = AlphaAnimation(0.0f , 1.0f).apply {
                        duration = 500
                        repeatMode = Animation.REVERSE
                        repeatCount = Animation.INFINITE
                    }
                    binding.ivNotification.apply {
                        visibility = View.VISIBLE
                        startAnimation(anim)
                    }
                } else {
                    binding.ivNotification.visibility = View.GONE
                }*/
                /*responseModel?.let {
                    daterList =
                        (it.completed!!.filter { completedModel ->
                            completedModel.username != ""
                        }).toCollection(ArrayList())
                    val pendingList = (it.invitation!!.filter { pendingModel ->
                        pendingModel.username != ""
                    }).toCollection(ArrayList())
                    completePendingList = (daterList!! + pendingList).toCollection(ArrayList())
                }*/
                Log.d("TAG:" , "daterList: ${daterTribeList?.size}")

                binding.apply {
                    if (daterTribeList.isNullOrEmpty()) {
                        txtTitle.visibility = View.VISIBLE
                        rcvMatchMaker.visibility = View.GONE
                        if (!showProfilePicture) {
                            if (showProfile) {
                                openUserProfileDialog()
                            } else {
                                lifecycleScope.launch {
                                    if (homeViewModel.isInviteExpired.firstOrNull() == true) {
                                        openExpiredDialog()
                                    } else {
                                        if (!completePendingList.isNullOrEmpty() && completePendingList!!.size <= 9) {
                                            openConnectorPsstDialog()
                                        }
                                    }
                                }
                            }
                        } else {
                            lifecycleScope.launch {
                                if (homeViewModel.isInviteExpired.firstOrNull() == true) {
                                    openExpiredDialog()
                                } else {
                                    if (!completePendingList.isNullOrEmpty() && completePendingList!!.size <= 9) {
                                        openConnectorPsstDialog()
                                    }
                                }
                            }
                        }
                    } else {
                        txtTitle.visibility = View.GONE
                        rcvMatchMaker.visibility = View.VISIBLE
                        //mAdapter.addAll(daterList)
                        sortingRosters()
                        rcvMatchMaker.adapter = mAdapter

                    }

                    getValues()
                    if (getDisableLogic()) {
                        txtGreetMsg.text = "Your Dater Account is disabled"
                        txtTitle.visibility = View.INVISIBLE
                        rcvMatchMaker.visibility = View.INVISIBLE
                        txtAddFriend.visibility = View.VISIBLE
                        txtAddFriend.setText("Reactivate Account")
                        txtAddFriend.background = ContextCompat.getDrawable(
                            requireActivity() ,
                            R.drawable.corner_orange_gradient_bg
                        )
                        txtSwiping.setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext() ,
                                R.color.disble_dater
                            )
                        )
                    }
                }
            }
            tribeError.observe(requireActivity()) {
                Log.e("TAG" , "ERROR=== $it")
                if (daterList.isNullOrEmpty()) {
                    binding.apply {
                        txtTitle.visibility = View.VISIBLE
                        rcvMatchMaker.visibility = View.GONE
                    }
                }
                /*if (!showProfilePicture) {
                    if (showProfile) {
                        openUserProfileDialog()
                    }
                } else {
                    lifecycleScope.launch {
                        if (homeViewModel.isInviteExpired.firstOrNull() == true) {
                            openExpiredDialog()
                        } else {
                            if (completePendingList!!.size <= 9) {
                                openConnectorPsstDialog()
                            }
                        }
                    }
                }*/
            }


            userTypeData.observe(requireActivity()) {
                if (it.response == "success") {
                    //moving as Matchmaker type from now
                    (activity as TabManagerActivity).accountType = AppUtils.AccountTypes.Matchmaker
                    homeViewModel.savePreference(
                        PreferenceKeys.PREF_ACCOUNT_TYPE ,
                        AppUtils.AccountTypes.Matchmaker
                    )
                    lifecycleScope.launch {
//                        if (homeViewModel.isDisableAgreement.firstOrNull() == false) {
//                            requireActivity().nextActivity(AgreementActivity::class.java)
//                        } else {
//                            requireActivity().nextActivity(TabManagerActivity::class.java)
//                        }
                        requireActivity().nextActivity(TabManagerActivity::class.java)
                        requireActivity().finish()
                        requireActivity().finishAffinity()
                    }

                }
            }
            userTypeError.observe(requireActivity()) {
                Log.e("TAG" , "ERROR=== $it")
                if (it!=null && it.response == "fail") {
                    if (it.is_popup == true) {
                        //show disable connector popup
                        openConnectorDisableDialog()
                    }
                } else {
                    requireActivity().showSnackBar(binding.layoutMain, getString(R.string.error_something_went_wrong))
                }
            }
            popupData.observe(requireActivity()) {
                if (it.response == "success") {
                    lifecycleScope.launch {
                        if (homeViewModel.isInviteExpired.firstOrNull() == true) {
                            openExpiredDialog()
                        } else {
                            if (completePendingList!!.size <= 9) {
                                openConnectorPsstDialog()
                            }
                        }
                    }
                }
            }
            removeTribeData.observe(requireActivity()) {
                if (it.response == "success") {
                    onResume()
                    lifecycleScope.launch {
                        val existingDater = (homeViewModel.getRemainingUser.firstOrNull() ?: 10) + 1
                        homeViewModel.savePreference(
                            PreferenceKeys.PREF_REMAINING_INVITATION ,
                            existingDater
                        )
                        binding.apply {
                            txtTitle.visibility = if (existingDater == 0) {
                                View.GONE
                            } else {
                                View.VISIBLE
                            }

                            txtAddFriend.visibility = if (existingDater == 0) {
                                View.GONE
                            } else {
                                View.VISIBLE
                            }
                        }
                    }
                }
            }
        }

    }

    private fun showDaterToolTip() {
        toolTipBinding.getContentView().apply {
            findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                getString(R.string.account_type)
            findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
            findViewById<AppCompatTextView>(R.id.txtTipDesc).text =
                getString(R.string.account_type_message)
            findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {
                homeViewModel.savePreference(PreferenceKeys.PREF_ACCOUNT_TOOL_TIP , true)
                toolTipBinding.dismiss()
            }
        }
        toolTipBinding.showAlignBottom(binding.toggleUser)
    }


    private fun changeUserTypeApi() {
        val apiRequest = JsonObject().apply {
            addProperty("user_type" , AppConstants.USER_MATCHMAKER)
        }
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog() { changeUserTypeApi() }
//                AppUtils.showMessageOK(
//                    requireActivity(),
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    changeUserTypeApi()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        homeViewModel.changeUserTypeApi(
                            "Bearer $authToken" , apiRequest
                        )
                    }
                }
            }
        }
    }

    private fun openAddedUserDialog(list: List<TribeDaterConnectionsResponseModel.TribeModel.Tribe>?) {
        isAddedDialog = true
        val dialog = Dialog(requireActivity() , R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val userAddedBinding = DialogUserAddedBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(userAddedBinding.root)
            userAddedBinding.apply {
                txtHeader.text = requireActivity().getString(R.string.user_added_connector)
                txtMessage.text = requireActivity().getString(R.string.user_added_connector_message)
                val imageViewList: ArrayList<RoundedImageView> =
                    arrayListOf(img1 , img2 , img3 , img4 , img5)
                val visibleViews: List<RoundedImageView> = imageViewList.take(list!!.size)
                visibleViews.onEachIndexed { i , view ->
                    view.visibility = View.VISIBLE
                    val textDrawable = requireActivity().createPlaceholderImage(
                        list[i].firstName!! ,
                        100 ,
                        R.color.blue_gradient_2 ,
                        R.color.blue_gradient_3
                    )

                    Glide.with(requireActivity())
                        .load(list[i].profilePic).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable)
                        .into(imageViewList[i])
                }
                btnContinue.setOnClickListener {
                    isAddedDialog = false
                    userAddedBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        //API call to set is_popup_display  to false(this popup is only for new connected users)
                       /* list.map {
                            it.primaryKeyId
                        }.onEach {
                            popupShowApi(it!!)
                        }*/
                    }
                }

                if (!dialog.isShowing) {
                    setZoomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                }
            }
        }
    }

    private fun openUserProfileDialog() {
        val dialog = Dialog(requireActivity() , R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val userProfileBinding = DialogProfileImageBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(userProfileBinding.root)
            userProfileBinding.apply {
                btnFillProfile.setOnClickListener {
                    userProfileBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        //navigate to profile screen to upload photo
                        startActivity(
                            Intent(
                                requireContext() ,
                                UserProfileActivity::class.java
                            ).putExtra("EditProfile" , true)
                        )
                    }
                }
                btnNotNow.setOnClickListener {
                    userProfileBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        /*homeViewModel.savePreference(
                            PreferenceKeys.PREF_IS_DISABLE_PROFILE_IMAGE,
                            true
                        )*/
                    }
                    lifecycleScope.launch {
                        if (homeViewModel.isInviteExpired.firstOrNull() == true) {
                            openExpiredDialog()
                        } else {
                            if (completePendingList!!.size <= 9) {
                                openConnectorPsstDialog()
                            }
                        }
                    }
                }
                try {
                    if (!dialog.isShowing) {
                        setZoomDialogWindowAttributes()
                        show()
                        imgDialogGradient.setDialogFadeInAnimation()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        showProfile = AppUtils.dialogForProfile(requireContext())
        lifecycleScope.launch {
//            val image = homeViewModel.getProfileImage.firstOrNull() ?: ""
            val image = AppUtils.getProfileImage(requireContext())
//            remainingCount = homeViewModel.getRemainingUser.firstOrNull() ?: 10
            remainingCount = 10
            binding.apply {
                if (image.isNotEmpty() && image != "null") {
                    imgProfile.visibility = View.VISIBLE
                    txtNameLetter.visibility = View.INVISIBLE
                    Glide.with(requireActivity())
                        .load(image)
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgProfile)
                } else {
                    imgProfile.visibility = View.GONE
                    txtNameLetter.visibility = View.VISIBLE
                }
            }
            inviteContactListApi()
        }
    }

    private fun popupShowApi(id: Int) {
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog() { popupShowApi(id) }
//                AppUtils.showMessageOK(
//                    requireActivity(),
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    popupShowApi(id)
//                }
            }
            else -> {
                lifecycleScope.launch {
                    homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        homeViewModel.popupShow(
                            "Bearer $authToken" , id
                        )
                    }
                }
            }
        }
    }

    private fun openExpiredDialog() {
        val dialog = Dialog(requireActivity() , R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val userExpiredBinding = DialogUserExpiredBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(userExpiredBinding.root)
            userExpiredBinding.apply {
                btnContinue.setOnClickListener {
                    userExpiredBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        lifecycleScope.launch {
                            homeViewModel.savePreference(PreferenceKeys.PREF_INVITE_EXPIRED , false)
                            if (completePendingList!!.size <= 9) {
                                openConnectorPsstDialog()
                            } else {
                                inviteContactListApi()
                            }
                        }
                    }
                }
                if (!dialog.isShowing) {
                    setZoomDialogWindowAttributes()
                    show()
                    imgDialogGradient.setDialogFadeInAnimation()
                }
            }
        }
    }

    private fun openDeleteDialog(pendingUser: TribeDaterConnectionsResponseModel.TribeModel.Tribe) {
        val dialog = Dialog(requireContext() , R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val deleteMatchmakerBinding =
            DialogDeleteMatchmakerBinding.inflate(requireActivity().layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(deleteMatchmakerBinding.root)
            deleteMatchmakerBinding.apply {
                txtCancel.setOnClickListener {
                    deleteMatchmakerBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dialog.dismiss()
                    }

                }
                txtYes.setOnClickListener {
                    deleteMatchmakerBinding.imgDialogGradient.setDialogFadeOutAnimation {
                        dialog.dismiss()
                        removeTribeDater(pendingUser)
                    }
                }
                txtDontShow.setOnClickListener {
                    homeViewModel.savePreference(
                        PreferenceKeys.PREF_DO_NOT_SHOW_DELETE_DATER ,
                        true
                    )
                    txtYes.performClick()
                }
                try {
                    if (!dialog.isShowing) {
                        setBottomDialogWindowAttributes()
                        show()
                        imgDialogGradient.setDialogFadeInAnimation()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun removeTribeDater(pendingUser: TribeDaterConnectionsResponseModel.TribeModel.Tribe) {
        val apiRequest = JsonObject().apply {
            addProperty("userid" , pendingUser.connectorId)
        }
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog() { removeTribeDater(pendingUser) }
//                AppUtils.showMessageOK(
//                    requireActivity(),
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    removeTribeDater(pendingUser)
//                }
            }
            else -> {
                lifecycleScope.launch {
                    homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        homeViewModel.removeTribeDater(
                            "Bearer $authToken" , apiRequest
                        )
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bubbleListener = (context as BubbleListener)
    }

    override fun onDetach() {
        super.onDetach()
        bubbleListener = null
        activity?.let { xmppBroadcastReceiver.unregister(it) }
    }

    private fun openConnectorDisableDialog() {
        val dialog = Dialog(requireContext() , R.style.SlideDialogTheme)
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            val disableBinding = DialogConnectorDisableBinding.inflate(layoutInflater)
            setContentView(disableBinding.root)
            disableBinding.let { viewBind ->
                viewBind.btnGotIt.setOnClickListener {
                    viewBind.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        binding.toggleUser.apply {
                            isReversed = false
                            text = getString(R.string.user_dater)
                            outerColor =
                                ContextCompat.getColor(requireActivity() , R.color.whiteColor)
                            innerColor =
                                ContextCompat.getColor(requireActivity() , R.color.colorPagerDesc)
                            strokeColor =
                                ContextCompat.getColor(requireActivity() , R.color.gray_30)
                            iconColor =
                                ContextCompat.getColor(requireActivity() , R.color.grey_light)
                        }
                    }
                }
                try {
                    if (!dialog.isShowing) {
                        setBottomDialogWindowAttributes()
                        show()
                        viewBind.imgDialogGradient.setDialogFadeInAnimation()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.let { xmppBroadcastReceiver.register(it) }
    }

    /**
     * XMPP events
     */
    override fun setRosterList(list: java.util.ArrayList<XmppRosterEntry>) {
    }

    override fun onMessageSent(messageId: Long , message: Message) {
    }

    override fun onMessageDeleted(messageId: Long) {
    }

    override fun onMessageAdded(remoteAccount: String? , message: Message , incoming: Boolean) {
    }

    override fun onRosterStatusChanges(xmppRosterEntry: XmppRosterEntry) {
        mAdapter.statusOnlineOFfline(xmppRosterEntry)

    }

    override fun onRosterChanged() {
    }

    override fun onRemoveContact(contact: String?) {
    }

    override fun onTyping(from: String?) {
    }

    override fun onTypingStop(from: String?) {
    }

    private fun sortingRosters() {
        Thread {
            val list = mDatabase.chatDao().getAllRosters()

            daterTribeList?.forEach { tribe ->
                list.forEachIndexed { index , roster ->
                   /* if (roster.xmppJid!!.contains(tribe.phone_number!! , true)) {
                        if (roster.name!!.contains("null" , true)) {
                            roster.name = tribe.username
                        }
                        tribe.isAvailable = list[index].isAvailable

                    }*/
                }
            }
            activity?.runOnUiThread {
//                mAdapter.addAll(daterTribeList)
                mAdapter.addAll(daterTribeList)
                daterTribeList?.let { usersList ->
                    if (usersList!!.isNotEmpty()) {
                        if (!isAddedDialog) {
                            openAddedUserDialog(usersList)
                        } else {
                            return@let
                        }
                    } else {
                        if (!showProfilePicture) {
                            if (showProfile) {
                                openUserProfileDialog()
                            } else {
                                lifecycleScope.launch {
                                    if (homeViewModel.isInviteExpired.firstOrNull() == true) {
                                        openExpiredDialog()
                                    } else {
                                        if (completePendingList!!.size <= 9) {
                                            openConnectorPsstDialog()
                                        }
                                    }
                                }
                            }
                        } else {
                            lifecycleScope.launch {
                                if (homeViewModel.isInviteExpired.firstOrNull() == true) {
                                    openExpiredDialog()
                                } else {
                                    if (completePendingList!!.size <= 9) {
                                        openConnectorPsstDialog()
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }.start()
    }

    private fun getValues() {
        lifecycleScope.launch {
            daterDisabled = userViewModel.getDaterDisabled.firstOrNull() ?: false
            connectorDisabled = userViewModel.getConnectorDisabled.firstOrNull() ?: false
            accountType = homeViewModel.getAccountType.firstOrNull() ?: ""
        }

    }

    private fun getDisableLogic(): Boolean {

        var isDisbled = false
        if (accountType == AppUtils.AccountTypes.Matchmaker) {
            if (connectorDisabled) {
                isDisbled = true
            }
        } else if (accountType == AppUtils.AccountTypes.Dater) {
            if (daterDisabled) {
                isDisbled = true
            }
        }

        return isDisbled
    }

    private fun openConnectorPsstDialog() {
//        val dialog = Dialog(requireContext(), R.style.SlideDialogTheme)
        val dialog = Dialog(requireContext() , R.style.SlideDialogThemePsst)
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            val dialogBinding = DialogNewFriendPsstBinding.inflate(layoutInflater)
            setContentView(dialogBinding.root)
            dialogBinding.apply {
                btnAddFriends.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        setAnimationPsstDialogClose(cardViewPsst , dialog , 1, llPsstDialog)
//                        dismiss()
                        // binding.txtAddFriend.performClick()
                    }
                }
                btnHome.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        setAnimationPsstDialogClose(cardViewPsst , dialog , 2, llPsstDialog)
//                        dismiss()
//                        redirectToLoading()
                    }
                }

//                if (!dialog.isShowing) {
//                    setBottomDialogWindowAttributes()
//                    imgDialogGradient.setDialogFadeInAnimation()
//                    setAnimationPsstDialogOpen(cardViewPsst, ivFwdLogo)
//                    show()
//                }
                if (AppUtils.dialogForPsst(requireContext())) {
                    try {
                        if (!dialog.isShowing) {
                            setBottomDialogWindowAttributes()
                            imgDialogGradient.setDialogFadeInAnimation()
                            setAnimationPsstDialogOpen(cardViewPsst , ivFwdLogo, llPsstDialog)
                            show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
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

    private fun openEmailPopup() {
        var dt = Date()
        var c = Calendar.getInstance()
        c.time = dt
        c.add(Calendar.DATE , 7)
        dt = c.time
        val plusSeven = dt
        val stringPlusSeven = mDateFormat.format(plusSeven)
        swipeViewModel.savePreference(
            PreferenceKeys.SHOW_EMAIL_REMINDER_DATE ,
            stringPlusSeven
        )
        startActivity(
            Intent(
                activity ,
                EmailActivity::class.java
            ).putExtra("LoadingActivity" , true)
        )
        activity?.overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
    }

    private fun redirectToLoading() {
        lifecycleScope.launch {
            isProfileVerified = swipeViewModel.getIsProfileVerified.firstOrNull() ?: false
            try {
                val x = swipeViewModel.getVerifyDialogDisplayDate.firstOrNull() ?: Date().toString()
                showProfileVerificationDialogDate = mDateFormat.parse(x)
                val y = swipeViewModel.getEmailDialogDisplayDate.firstOrNull() ?: Date().toString()
                showEmailRecoveryDialogDate = mDateFormat.parse(y)

            } catch (e: Exception) {
            }
            recoveryEmail = swipeViewModel.getRecoveryEmail.firstOrNull() ?: ""
            gender = swipeViewModel.getGender.firstOrNull()
        }
        if (!isProfileVerified || recoveryEmail.equals("")) {
            if (!isProfileVerified&&showProfileVerificationDialogDate <= Date()) {
                //   Handler(Looper.getMainLooper()).post {
                var dt = Date()
                var c = Calendar.getInstance()
                c.time = dt
                c.add(Calendar.DATE , 4)
                dt = c.time
                val plusFour = dt
                val stringPlusFour = mDateFormat.format(plusFour)
                swipeViewModel.savePreference(
                    PreferenceKeys.SHOW_VERIFICATION_REMINDER_DATE ,
                    stringPlusFour
                )
                if (recoveryEmail.equals("")&&showEmailRecoveryDialogDate <= Date())
                    openVerifiedDialog(true)
                else openVerifiedDialog()
                //      }

            } else if (recoveryEmail.equals("")&&showEmailRecoveryDialogDate <= Date()) {
                //     Handler(Looper.getMainLooper()).post {
                openEmailPopup()
                //   }
            } else {
                activity?.nextActivity(LoadingActivity::class.java)
            }
        } else {
            activity?.nextActivity(LoadingActivity::class.java)
        }

    }

    private fun openConnectionLimitDialog() {
        val dialog = Dialog(requireActivity() , R.style.ZoomDialogTheme)
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
                    }
                }
                setZoomDialogWindowAttributes()
                show()
                imgDialogGradient.setDialogFadeInAnimation()
            }
        }
    }

    private fun setAnimationPsstDialogOpen(cardView: View , logo: View, textLayout: LinearLayout) {
        var fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = 500
        fadeIn.startOffset = 200
        val animationOpen1 =
            AnimationUtils.loadAnimation(requireContext() , R.anim.center_grow_open)
        val animationOpen2 =
            AnimationUtils.loadAnimation(requireContext() , R.anim.center_grow_open_2)
        var bounceAnimation = AnimationBounceDown()
        cardView.startAnimation(animationOpen1)
        animationOpen1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                textLayout.startAnimation(fadeIn)
            }

            override fun onAnimationEnd(p0: Animation?) {
                cardView.startAnimation(animationOpen2)
                bounceAnimation.doBounceDownAnimation(logo , 1 , 300)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
    }

    private fun setAnimationPsstDialogClose(view: View , dialog: Dialog , case: Int, textLayout: LinearLayout) {
        var fadeOut = AlphaAnimation(1.0f, 0.0f)
        fadeOut.duration = 400
        fadeOut.startOffset = 0
        val animationClose1 =
            AnimationUtils.loadAnimation(requireContext() , R.anim.center_grow_close)
        val animationClose2 =
            AnimationUtils.loadAnimation(requireContext() , R.anim.center_grow_close_2)
        val animationClose3 =
            AnimationUtils.loadAnimation(requireContext() , R.anim.center_grow_close_dismiss)
        view.startAnimation(animationClose1)
        animationClose1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                textLayout.startAnimation(fadeOut)
                fadeOut.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        textLayout.visibility = View.INVISIBLE
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                })
            }

            override fun onAnimationEnd(p0: Animation?) {
                view.startAnimation(animationClose2)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
        animationClose2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                view.startAnimation(animationClose3)
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
        animationClose3.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                dialog.dismiss()
                if (case == 1) {
                    binding.txtAddFriend.performClick()
                } else if (case == 2) {
                    redirectToLoading()
                }
            }

            override fun onAnimationEnd(p0: Animation?) {
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
    }

    private fun openVerifiedDialog(goToEmailPopup: Boolean = false) {
        val dialog = Dialog(requireActivity() , R.style.SlideDialogTheme)
        val profileBinding = DialogVerifiedProfileBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(profileBinding.root)
            profileBinding.apply {
                lifecycleScope.launch {
                    val image = swipeViewModel.getProfileImage.firstOrNull()
                    binding.apply {
//                        pullDown.setOnTouchListener { v, event ->
//                            if (event.action === MotionEvent.ACTION_DOWN) {
//                                imgDialogGradient.setDialogFadeOutAnimation {
//                                    dialog.dismiss()
//                                }
//                                true
//                            }
//                            false
//                        }
                        imgDialogGradient.setOnTouchListener { v, event ->
                            if (event.action === MotionEvent.ACTION_DOWN) {
                                imgDialogGradient.setDialogFadeOutAnimation {
                                    dialog.dismiss()
                                }
                                true
                            }
                            false
                        }
                        val firstName = swipeViewModel.getFirstName.firstOrNull()
                        val fName = firstName
                        if (fName != null) {
                            if (fName.isNotEmpty()) {
                                val textDrawable = requireActivity().createPlaceholderImage(
                                    fName ,
                                    200 ,
                                    R.color.blue_gradient_2 ,
                                    R.color.blue_gradient_3 ,
                                    isModify = true
                                )

                                Glide.with(context)
                                    .load(image)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .placeholder(textDrawable)
                                    .into(ivProfile)
                            } else {
                                Glide.with(context)
                                    .load(R.mipmap.ic_launcher)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(ivProfile)
                            }
                        }
                    }
                }
                txtGetVerified.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        openCopyGestureDialog()
                    }
                }
                txtDoItLater.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        if (goToEmailPopup)
                            openEmailPopup()
                        else
                            activity?.nextActivity(LoadingActivity::class.java)
                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                profileBinding.imgDialogGradient.setDialogFadeInAnimation()
                mAnimation.slide(profileBinding.clVerifyProfile , 100 , 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openCopyGestureDialog() {
        val dialog = Dialog(requireActivity() , R.style.SlideDialogTheme)
        val gestureBinding = DialogCopyGestureBinding.inflate(layoutInflater)
        if (gender.equals("0")) {
//            gestureBinding.viewUserImage.setImageResource(R.drawable.gesture_1_male)
            gestureBinding.viewUserImage.setImageResource(R.drawable.male_new_verification)
//            gestureBinding.viewUserImage.setBackgroundResource(R.drawable.picture_gradient_male)
        }
        val height=AppUtils.getScreenHeight(requireActivity())
        val heightDp = DisplayUtil.pxToDp(requireActivity(), height)

//        if(heightDp<700) {
//            gestureBinding.viewUserImage.layoutParams.height = 400.dpToPx()
////            gestureBinding.viewUserImage.scaleType = ImageView.ScaleType.FIT_XY
//        }
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(gestureBinding.root)
            gestureBinding.apply {
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dialog.dismiss()
                        }
                        true
                    }
                    false
                }
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dialog.dismiss()
//                        }
//                        true
//                    }
//                    false
//                }
//                txtSkip.setOnClickListener {
//                    imgDialogGradient.setDialogFadeOutAnimation {
//                        dismiss()
//                    }
//                }
                txtReady.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                    requestRuntimePermission(true)
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                gestureBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openVerificationCameraActivity() {
        val intent = Intent(
            activity ,
            ActivityCameraVerification::class.java
        )
        gestureVerificationStartForResult?.launch(intent)
    }

    private fun requestRuntimePermission(isGesture: Boolean = false) {
        Dexter.withContext(activity).withPermissions(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                arrayListOf(Manifest.permission.READ_EXTERNAL_STORAGE , Manifest.permission.CAMERA)
            } else {
                arrayListOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE ,
                    Manifest.permission.READ_EXTERNAL_STORAGE ,
                    Manifest.permission.CAMERA
                )
            }
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                when {
                    report.areAllPermissionsGranted() -> {
                        //  openFinalGestureDialog()
                        openVerificationCameraActivity()
                    }
                    report.deniedPermissionResponses != null && report.deniedPermissionResponses.size > 0 -> {
                        if (report.deniedPermissionResponses[0].isPermanentlyDenied) {
                            val intent =
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts(
                                "package" ,
                                requireActivity().packageName , null
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
                p0: MutableList<com.karumi.dexter.listener.PermissionRequest>? ,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }

        })
            .withErrorListener {
                Log.e("Dexter" , "There was an error: $it")
            }
            .check()

    }

    private fun openFinalGestureDialog() {
        val dialogGesture = Dialog(requireActivity() , R.style.SlideDialogTheme)
        val finalGestureBinding = DialogFinalGestureBinding.inflate(layoutInflater)
        if (gender.equals("0")) {
                finalGestureBinding.viewUserImage.setImageResource(R.drawable.male_small)
        }
        val height=AppUtils.getScreenHeight(requireActivity())
        val heightDp = DisplayUtil.pxToDp(requireActivity(), height)

//        if(heightDp<700) {
//            finalGestureBinding.viewUserImage.layoutParams.height = 200.dpToPx()
//            finalGestureBinding.viewUserImage.scaleType = ImageView.ScaleType.FIT_XY
//            finalGestureBinding.imageView.layoutParams.height = 200.dpToPx()
//        }
        dialogGesture.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(finalGestureBinding.root)
//            surfaceHolder = finalGestureBinding.surfaceView.holder
//            surfaceHolder?.addCallback(this@LoadingActivity)
            finalGestureBinding.apply {
                imgDialogGradient.setOnTouchListener { v, event ->
                    if (event.action === MotionEvent.ACTION_DOWN) {
                        imgDialogGradient.setDialogFadeOutAnimation {
                            dismiss()
                        }
                        true
                    }
                    false
                }
//                pullDown.setOnTouchListener { v, event ->
//                    if (event.action === MotionEvent.ACTION_DOWN) {
//                        imgDialogGradient.setDialogFadeOutAnimation {
//                            dismiss()
//                        }
//                        true
//                    }
//                    false
//                }
                imageView.setImageBitmap(mGestureVerificationImage)
                txtRetake.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }
                    val intent = Intent(
                        activity ,
                        ActivityCameraVerification::class.java
                    )
                    gestureVerificationStartForResult?.launch(intent)
                }
                txtSubmit.setOnClickListener {
                    //captureImageVerification()
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                    }

                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                finalGestureBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        startCamera()
    }

    override fun surfaceChanged(holder: SurfaceHolder , format: Int , width: Int , height: Int) {
        resetCamera()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        releaseCamera()
    }

    private fun startCamera() {
        camera = Camera.open(1)
        camera!!.setDisplayOrientation(90)
        val parameters = camera!!.parameters.apply {
            supportedPreviewSizes
        }
        try {
            camera!!.parameters = parameters
            camera!!.setPreviewDisplay(surfaceHolder)
            camera!!.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun resetCamera() {
        if (surfaceHolder!!.surface == null) {
            // Return if preview surface does not exist
            return
        }

        // Stop if preview surface is already running.
        camera!!.stopPreview()
        try {
            // Set preview display
            camera!!.setPreviewDisplay(surfaceHolder)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Start the camera preview...
        camera!!.startPreview()
    }

    private fun releaseCamera() {
        camera!!.stopPreview()
        camera!!.release()
        camera = null
    }
}