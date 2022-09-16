package com.swipefwd.ui.home.tribe

import android.Manifest
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.view.View.DragShadowBuilder
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.skydoves.balloon.balloon
import com.swipefwd.R
import com.swipefwd.databinding.*
import com.swipefwd.data.models.ChangeRoleModel
import com.swipefwd.data.models.DaterConnectionResponseModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.home.AgreementActivity
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.home.settings.AccountActivity
import com.swipefwd.ui.home.settings.SettingsFragment
import com.swipefwd.ui.home.settings.SettingsViewModel
import com.swipefwd.ui.profile.PreferencesActivity
import com.swipefwd.ui.updateuserprofile.UserProfileActivity
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.*
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.xmpp.XmppServiceCommand
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import java.util.*
import kotlin.collections.ArrayList


class MySingleFriendsListFragment : Fragment(), View.OnTouchListener,
    View.OnDragListener, GestureDetector.OnGestureListener {

    private var _binding: FragmentMySingleFriendsListBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeFragmentViewModel by viewModels {
        viewModelFactory { HomeFragmentViewModel(requireContext(), AppRepository()) }
    }
    private val userViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(requireContext(), AppRepository()) }
    }
    private val settingsViewModel: SettingsViewModel by viewModels {
        viewModelFactory { SettingsViewModel(requireContext(), AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(requireActivity())
    }
    private var daterList = ArrayList<DaterConnectionResponseModel.User>()
    private var user1 = DaterConnectionResponseModel.User()
    private var user2 = DaterConnectionResponseModel.User()
    private var totalViews = ArrayList<ConstraintLayout>()
    private var userProfileView = ArrayList<RoundedImageView>()
    private var userNameView = ArrayList<AppCompatTextView>()
    private var cancelViews = ArrayList<AppCompatImageView>()
    private lateinit var mDetector: GestureDetectorCompat
    private var vTouch: View? = null
    private val toolTipBinding by balloon<ToolTipFactory>()
    private var bubbleListener: BubbleListener? = null
    var accountType = ""
    var daterDisabled = false
    var connectorDisabled = false
    private val dialogs: Vector<Dialog> = Vector<Dialog>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMySingleFriendsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            totalViews = arrayListOf(
                layout1.layoutConnector,
                layout2.layoutConnector,
                layout3.layoutConnector,
                layout4.layoutConnector,
                layout5.layoutConnector/*,
                layout6.layoutConnector,
                layout7.layoutConnector,
                layout8.layoutConnector,
                layout9.layoutConnector,
                layout10.layoutConnector*/
            )
            userProfileView =
                arrayListOf(
                    layout1.img,
                    layout2.img,
                    layout3.img,
                    layout4.img,
                    layout5.img/*,
                    layout6.img,
                    layout7.img,
                    layout8.img,
                    layout9.img,
                    layout10.img*/
                )
            userNameView =
                arrayListOf(
                    layout1.txt,
                    layout2.txt,
                    layout3.txt,
                    layout4.txt,
                    layout5.txt/*,
                    layout6.txt,
                    layout7.txt,
                    layout8.txt,
                    layout9.txt,
                    layout10.txt*/
                )
            cancelViews = arrayListOf(
                layout1.cancel,
                layout2.cancel,
                layout3.cancel,
                layout4.cancel,
                layout5.cancel/*,
                layout6.cancel,
                layout7.cancel,
                layout8.cancel,
                layout9.cancel,
                layout10.cancel*/
            )
            requireActivity().setShaderView(
                txtInviteFriends,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            txtTip.setOnClickListener {
                lifecycleScope.launch {
                    if (homeViewModel.getIsAccountTip.firstOrNull() == false)
                        showDaterToolTip()
                }
            }
            ivCancel.setOnClickListener {
                btnConnect.visibility = View.GONE
                btnGreenConnect.visibility = View.GONE
                btnNewRequest.visibility = View.VISIBLE
                layoutToggle.visibility = View.VISIBLE
                ivCancel.visibility = View.GONE
                txtHome.text = getString(R.string.home)
                txtGreetMsg.text = getString(R.string.home_matchmaker)
                cardLogo2.apply {
                    visibility = View.GONE
                    setImageResource(R.drawable.fwd_logo)
                }
                cardLogo1.apply {
                    visibility = View.VISIBLE
                    setImageResource(R.drawable.fwd_logo)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        outlineSpotShadowColor =
                            resources.getColor(R.color.blackDialog, requireActivity().theme)
                    }
                }
                txtUser1.apply {
                    visibility = View.GONE
                    text = ""
                }
                txtUser2.apply {
                    visibility = View.GONE
                    text = ""
                }
                user1 = DaterConnectionResponseModel.User()
                user2 = DaterConnectionResponseModel.User()
                ObjectAnimator.ofFloat(cardLogo1, "translationX", 0f, 0f)
                    .start()
                ObjectAnimator.ofFloat(txtUser1, "translationX", 0f, 0f)
                    .start()
                ObjectAnimator.ofFloat(cardLogo2, "translationX", 0f, 0f)
                    .start()
                ObjectAnimator.ofFloat(txtUser2, "translationX", 0f, 0f)
                    .start()
                loadUI()
            }
            btnGreenConnect.setOnClickListener {
                if (user2.userid?.toInt() != 0) {
                    suggestionProfileApi()
                }
            }
            btnConnect.setOnClickListener {
                requestRuntimePermission()
            }

            //toggle changes
            toggleUser.text = getString(R.string.user_matchmaker)

            toggleUser.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
                override fun onSlideComplete(view: SlideToActView) {
                    view.apply {
                        resetSliderNoAnim()
                        if (text == getString(R.string.user_matchmaker)) {
                            isReversed = false
                            text = getString(R.string.user_dater)
                            outerColor = ContextCompat.getColor(requireActivity(), R.color.white)
                            innerColor =
                                ContextCompat.getColor(requireActivity(), R.color.colorPagerDesc)
                            strokeColor =
                                ContextCompat.getColor(requireActivity(), R.color.gray_30)
                            iconColor =
                                ContextCompat.getColor(requireActivity(), R.color.white)
                            requireActivity().openBecomeDaterDialog()
                        }
                    }
                }
            }
            btnNewRequest.setOnClickListener {
                if (txtAddFriend.text.equals("Reactivate Account")) {

                    requireActivity().nextActivity(AccountActivity::class.java)
                    ((requireActivity() as TabManagerActivity)).addFragment(SettingsFragment())
                    /*lifecycleScope.launch {
                        var userId = settingsViewModel.getUserId.firstOrNull() ?: 0
                        var connector = userViewModel.getConnectorDisabled.firstOrNull() ?: false

                        val jsonObject = JsonObject()
                        jsonObject.addProperty("is_connector_disabled", !connector)
                        lifecycleScope.launch {
                            settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                                settingsViewModel.userSettingsUpdate(
                                    "Bearer $authToken", userId, jsonObject
                                )
                            }
                        }
                    }*/

                } else {
                    requireActivity().nextActivity(RequestsListActivity::class.java)
                }

            }
            mDetector = GestureDetectorCompat(requireContext(), this@MySingleFriendsListFragment)
            initObservers()
        }
    }

    private fun initObservers() {
        homeViewModel.apply {
            showLoading.observe(requireActivity()) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(requireActivity()) {
                Log.e("TAG" , "ERROR === $it")
                requireActivity().showSnackBar(binding.layoutMain , it)
            }
            error.observe(requireActivity()) {
                Log.e("TAG" , "ERROR === $it")
                requireActivity().showSnackBar(binding.layoutMain , it.message.toString())
            }
            userTypeData.observe(requireActivity()) {
                if (it.response == "success") {
                    (activity as TabManagerActivity).accountType = AppUtils.AccountTypes.Dater
                    homeViewModel.savePreference(
                        PreferenceKeys.PREF_ACCOUNT_TYPE ,
                        AppUtils.AccountTypes.Dater
                    )
                    homeViewModel.savePreference(
                        PreferenceKeys.PREF_PROFILE_FLAG ,
                        it.profile!!
                    )
                    homeViewModel.savePreference(
                        PreferenceKeys.PREF_PREFERENCE_FLAG ,
                        it.preference!!
                    )
                    homeViewModel.savePreference(
                        PreferenceKeys.PREF_ADV_PROFILE_FLAG ,
                        it.advanceprofile!!
                    )
                    //if user press back from profile then toggle will show as matchmaker
                    binding.toggleUser.apply {
                        isReversed = true
                        text = getString(R.string.user_matchmaker)
                        outerColor =
                            ContextCompat.getColor(requireActivity() , R.color.colorPagerDesc)
                        innerColor =
                            ContextCompat.getColor(requireActivity() , R.color.whiteColor)
                        strokeColor =
                            ContextCompat.getColor(requireActivity() , R.color.gray_30)
                        iconColor =
                            ContextCompat.getColor(requireActivity() , R.color.grey_light)
                    }
                    if (!it.profile || !it.advanceprofile) {
                        startActivity(
                            Intent(
                                requireActivity() ,
                                UserProfileActivity::class.java
                            ).putExtra("ChangeUserType" , true)
                        )
                        EventBus.getDefault().post(ChangeRoleModel(AppUtils.AccountTypes.Dater))
                    } else if (!it.preference) {
                        requireActivity().nextActivity(PreferencesActivity::class.java)
                        EventBus.getDefault().post(ChangeRoleModel(AppUtils.AccountTypes.Dater))
                    } else {
                        lifecycleScope.launch {
//                            if (homeViewModel.isDisableAgreement.firstOrNull() == false) {
//                                requireActivity().nextActivity(AgreementActivity::class.java)
//                            } else {
//                                requireActivity().nextActivity(TabManagerActivity::class.java)
//                            }
                            requireActivity().nextActivity(TabManagerActivity::class.java)
                            requireActivity().finish()
                            requireActivity().finishAffinity()
                        }
                    }
                }
            }
            userTypeError.observe(requireActivity()) {
                Log.e("TAG" , "ERROR=== $it")
                requireActivity().showSnackBar(binding.layoutMain , it.message.toString())
                binding.toggleUser.apply {
                    isReversed = true
                    text = getString(R.string.user_matchmaker)
                    outerColor =
                        ContextCompat.getColor(requireActivity() , R.color.colorPagerDesc)
                    innerColor =
                        ContextCompat.getColor(requireActivity() , R.color.whiteColor)
                    strokeColor =
                        ContextCompat.getColor(requireActivity() , R.color.gray_30)
                    iconColor =
                        ContextCompat.getColor(requireActivity() , R.color.grey_light)
                }
            }
            tribeData.observe(requireActivity()) {
                Log.e("TAG" , "DATER_LIST_SUCCESS=== $daterList")
                bubbleListener?.onShowBubble(it.is_request_pending!!)
                binding.apply {
                    val anim = AlphaAnimation(0.0f , 1.0f).apply {
                        duration = 500
                        repeatMode = Animation.REVERSE
                        repeatCount = Animation.INFINITE
                    }
                    if (it.is_request_pending!!) {
                        ivAlert.apply {
                            visibility = View.VISIBLE
                            startAnimation(anim)
                        }
                        ivNotification.apply {
                            visibility = View.VISIBLE
                            startAnimation(anim)
                        }
                    } else {
                        ivAlert.apply {
                            clearAnimation()
                            visibility = View.GONE
                        }
                        ivNotification.apply {
                            clearAnimation()
                            visibility = View.GONE
                        }
                    }
                    daterList.clear()
                    daterList.addAll(it.completed?.filter { model ->
                        model.username != ""
                    }!!)
                    Log.e("TAG" , "DATERS_SUCCESS_AFTER=== $daterList")
                    if (daterList.isNullOrEmpty()) {
                        //layoutNoMembers.visibility = View.VISIBLE
                        //cardLogo.visibility = View.GONE
                        //layoutCenterFwd.visibility = View.GONE
                        totalViews.onEachIndexed { index , layout ->
                            layout.visibility = View.GONE
                            userProfileView[index].apply {
                                visibility = View.GONE
                                setPadding(4)
                                setImageResource(R.drawable.user_connector)
                            }
                            userNameView[index].apply {
                                visibility = View.GONE
                                text = ""
                            }
                        }
                    } else {
                        lifecycleScope.launch {
                            binding.ivCancel.performClick()
                            //not showing tip in this version
                            /*if (homeViewModel.getConnectorTip.firstOrNull() == false) {
                                showSingleFriendToolTip()
                            } else {
                                binding.ivCancel.performClick()
                            }*/
                        }
                    }
                    getValues()
                    if (getDisableLogic()) {
                        //layoutNoMembers.visibility = View.INVISIBLE
                        cardLogo.visibility = View.VISIBLE
                        val matrix = ColorMatrix()
                        matrix.setSaturation(0.toFloat())
                        txtGreetMsg.text = "Your Connector Account is disabled"
                        txtAddFriend.text = "Reactivate Account"
                        btnNewRequest.visibility = View.VISIBLE
                        btnNewRequest.background = ContextCompat.getDrawable(
                            requireActivity() ,
                            R.drawable.corner_orange_gradient_bg
                        )
                        totalViews.onEachIndexed { index , layout ->
                            layout.visibility = View.GONE
                            userProfileView[index].apply {
                                visibility = View.GONE
                                setPadding(4)
                                setImageResource(R.drawable.user_connector)
                            }
                            userNameView[index].apply {
                                visibility = View.GONE
                                text = ""
                            }
                        }
                        val filter = ColorMatrixColorFilter(matrix)
                        cardLogo1.colorFilter = filter
                    }
                }
            }
            tribeError.observe(requireActivity()) {
                Log.e("TAG" , "ERROR=== $it")
                Log.e("TAG" , "DATER_LIST_ERROR=== $daterList")
                if (daterList.isNullOrEmpty()) {
                    binding.apply {
                        //layoutNoMembers.visibility = View.VISIBLE
                        //cardLogo.visibility = View.GONE
                        //layoutCenterFwd.visibility = View.GONE
                        totalViews.onEachIndexed { index , layout ->
                            layout.visibility = View.GONE
                            userProfileView[index].apply {
                                visibility = View.GONE
                                setPadding(4)
                                setImageResource(R.drawable.user_connector)
                            }
                            userNameView[index].apply {
                                visibility = View.GONE
                                text = ""
                            }
                        }
                    }
                }
            }
            removeTribeData.observe(requireActivity()) {
                if (it.response == "success") {
                    lifecycleScope.launch {
                        val existingConnectorCount =
                            homeViewModel.getRemainingConnector.firstOrNull() ?: 5
                        homeViewModel.savePreference(
                            PreferenceKeys.PREF_REMAINING_CONNECTOR_CONNECTION ,
                            existingConnectorCount + 1
                        )
                    }
                    onResume()
                }
            }
            inviteData.observe(requireActivity()) {
                if (it.response == "success") {
                    openChatDialog()
                } else {
                    binding.ivCancel.performClick()
                    requireActivity().showSnackBar(binding.layoutMain , it.message.toString())
                }
            }
            suggestionModel.observe(requireActivity()) {
                if (it.response == "success") {
                    binding.ivCancel.performClick()
                }
            }
        }
        /*settingsViewModel.apply {
            settingsData.observe(requireActivity(), {
                lifecycleScope.launch {
                    var dater = userViewModel.getConnectorDisabled.firstOrNull() ?: false
                    if (dater) {
                        userViewModel.savePreference(
                            PreferenceKeys.PREF_CONNECTOR_DISABLED,
                            false
                        )
                        onResume()
                    } else {
                        userViewModel.savePreference(
                            PreferenceKeys.PREF_CONNECTOR_DISABLED,
                            true
                        )
                    }
                }
            })
        }*/

    }

    private fun changUserTypeApi() {
        val apiRequest = JsonObject().apply {
            addProperty("user_type", AppConstants.USER_DATER)
        }
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog(){changUserTypeApi()}
//                AppUtils.showMessageOK(
//                    requireActivity(),
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    changUserTypeApi()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        homeViewModel.changeUserTypeApi(
                            "Bearer $authToken", apiRequest
                        )
                    }
                }
            }
        }
    }

    private fun Activity.openBecomeDaterDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            val becomeDaterBinding = DialogBecomeDaterBinding.inflate(layoutInflater)
            setContentView(becomeDaterBinding.root)
            becomeDaterBinding.let { viewBind ->
                viewBind.btnGoBack.setOnClickListener {
                    viewBind.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        binding.toggleUser.apply {
                            isReversed = true
                            text = getString(R.string.user_matchmaker)
                            outerColor =
                                ContextCompat.getColor(requireActivity(), R.color.colorPagerDesc)
                            innerColor =
                                ContextCompat.getColor(requireActivity(), R.color.whiteColor)
                            strokeColor =
                                ContextCompat.getColor(requireActivity(), R.color.gray_30)
                            iconColor =
                                ContextCompat.getColor(requireActivity(), R.color.grey_light)
                        }
                    }
                }
                viewBind.btnContinue.setOnClickListener {
                    viewBind.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        changUserTypeApi()
                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                becomeDaterBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun inviteContactListApi() {
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog(){inviteContactListApi()}
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
                        homeViewModel.tribeConnectionListApi(
                            "Bearer $authToken"
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun loadUI() {
        binding.apply {
            //val visibleViews = totalViews.take(daterList.size)
            //layoutNoMembers.visibility = View.GONE
            layoutCenterFwd.visibility = View.VISIBLE
            cardLogo.visibility = View.VISIBLE
            val visibleViews = totalViews
            //setting position of Daters in circular view
            val angle = 360 / (visibleViews.size)
            visibleViews.onEachIndexed { i, view ->
                view.apply {
                    visibility = View.VISIBLE
                    layoutParams = ConstraintLayout.LayoutParams(
                        80.toPx(),
                        80.toPx()
                    ).apply {
                        circleConstraint = R.id.cardLogo
                        circleRadius =
                            cardLogo.measuredWidth.div(2) + 50.toPx()// setting 40px away from white circle
                        circleAngle = ((i * angle)+180).toFloat()
                    }
                    setOnClickListener {
                        Log.d("TAG:", "TAG: ${it.tag}")
                        if (it.tag == null) {
                            val dialog = Dialog(requireContext(), R.style.NoConnectorDialogTheme)
                            dialog.apply {
                                requestWindowFeature(Window.FEATURE_NO_TITLE)
                                setCancelable(true)
                                setCanceledOnTouchOutside(true)
                                val binding =
                                    DialogNoConnectorBinding.inflate(layoutInflater)
                                setContentView(binding.root)
                                binding.apply {
                                    txtMessage.text = getString(R.string.no_dater)
                                }
                                setOnCancelListener {
                                    dismiss()
                                }
                                try {
                                    show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    }
                }
                userProfileView[i].apply {
                    setPadding(4)
                    setImageResource(R.drawable.user_connector)
                    visibility = View.VISIBLE
                }
                userNameView[i].apply {
                    text = ""
                    visibility = View.VISIBLE
                }
            }
            daterList.onEachIndexed { index, user ->
                visibleViews[index].tag = index
                visibleViews[index].setOnTouchListener(this@MySingleFriendsListFragment)
                user.daterIndex = index
                if (user.username != "") {
                    val textDrawable = requireActivity().createPlaceholderImage(
                        user.username!!,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )
                    userProfileView[index].apply {
                        visibility = View.VISIBLE
                        Glide.with(requireActivity())
                            .load(user.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(textDrawable)
                            .into(this)
                    }

                }
                userNameView[index].let { textView ->
                    textView.apply {
                        visibility = View.VISIBLE
                        text = user.username?.let {
                            it.split(" ")[0]
                        }
                    }
                }
            }
            //setting listener to destination view
            layoutCenterFwd.setOnDragListener(this@MySingleFriendsListFragment)
        }
    }

    private fun Int.toPx(): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(), resources.displayMetrics
        ).toInt()

    @Suppress("DEPRECATION")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return if (mDetector.onTouchEvent(event!!)) {
            vTouch = v
            true
        } else {
            if (event?.action == MotionEvent.ACTION_MOVE) {
                val shadowBuilder = DragShadowBuilder(v)
                v!!.startDrag(null, shadowBuilder, v, 0)
                v.visibility = View.INVISIBLE
                true
            } else {
                false
            }
        }
    }

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        val action = event?.action
        val view = event?.localState as View
        when (action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                Log.e("TAG", "ACTION_DRAG_STARTED")
                return true
            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                Log.e("TAG", "ACTION_DRAG_LOCATION")
                return true
            }
            DragEvent.ACTION_DROP -> {
                Log.e("TAG", "ACTION_DROP")
                dragAndDropUser(view)
                return true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                Log.e("TAG", "ACTION_DRAG_ENDED")
                if (dropEventNotHandled(event)) {
                    view.visibility = View.VISIBLE
                }
                return true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                Log.e("TAG", "ACTION_DRAG_ENTERED")
                return true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                Log.e("TAG", "ACTION_DRAG_EXITED")
                return true
            }
            else -> {
                Log.e("TAG", "ELSE: $action")
            }
        }
        return true
    }

    private fun dragAndDropUser(mView: View) {
        if (daterList.isNotEmpty()) {
            daterList.onEachIndexed { index, _ ->
                if (mView.tag == daterList[index].daterIndex) {
                    Log.e("TAG:", "DATERLIST: ${daterList[index]}")
                    binding.apply {
                        if (user1.userid?.toInt() == 0) {
                            Log.e("TAG:", "DATERLIST1: ${daterList[index]}")
                            user1 = daterList[index]
                            if (user1.username != "") {
                                val textDrawable = requireActivity().createPlaceholderImage(
                                    user1.username!!,
                                    100,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )

                                Glide.with(requireActivity())
                                    .load(user1.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .placeholder(textDrawable)
                                    .into(cardLogo1)
                                txtUser1.apply {
                                    visibility = View.VISIBLE
                                    text = user1.username!!.split(" ")[0]
                                }
                            }
                            txtHome.text = getString(R.string.connect)
                            txtGreetMsg.text = getString(
                                R.string.connect_message,
                                user1.username?.let { it.split(" ")[0] })
                            ivCancel.visibility = View.VISIBLE
                            btnConnect.visibility = View.VISIBLE
                            layoutToggle.visibility = View.INVISIBLE
                            btnNewRequest.visibility = View.INVISIBLE
                            cardLogo2.apply {
                                visibility = View.VISIBLE
                                elevation = 80f
                            }
                            ObjectAnimator.ofFloat(cardLogo2, "translationX", 0f, -60f).apply {
                                duration = 1300
                                interpolator = AccelerateInterpolator()
                                start()
                            }
                            ObjectAnimator.ofFloat(txtUser2, "translationX", 0f, -60f).apply {
                                duration = 1300
                                interpolator = AccelerateInterpolator()
                                start()
                            }
                            ObjectAnimator.ofFloat(cardLogo1, "translationX", 0f, 60f).apply {
                                duration = 500
                                interpolator = AccelerateInterpolator()
                                start()
                            }
                            ObjectAnimator.ofFloat(txtUser1, "translationX", 0f, 60f).apply {
                                duration = 500
                                interpolator = AccelerateInterpolator()
                                start()
                            }
                            return@onEachIndexed
                        }
                        if (user2.userid?.toInt() == 0) {
                            Log.e("TAG:", "DATERLIST2: ${daterList[index]}")
                            user2 = daterList[index]
                            if (user2.username != "") {
                                val textDrawable = requireActivity().createPlaceholderImage(
                                    user2.username!!,
                                    100,
                                    R.color.blue_gradient_2,
                                    R.color.blue_gradient_3
                                )

                                Glide.with(requireActivity())
                                    .load(user2.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .placeholder(textDrawable)
                                    .into(cardLogo2)
                                txtUser2.apply {
                                    visibility = View.VISIBLE
                                    text = user2.username!!.split(" ")[0]
                                }
                            }
                        }
                        if (user2.userid?.toInt() != 0) {
                            btnGreenConnect.visibility = View.VISIBLE
                            btnConnect.visibility = View.GONE
                            //suggestionProfileApi()
                        }
                    }
                }
            }
        }
    }

    private fun dropEventNotHandled(dragEvent: DragEvent)
            : Boolean {
        return !dragEvent.result
    }

    private fun Activity.openDeleteDaterDialog(view: AppCompatImageView) {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            val deleteDaterBinding = DialogDeleteDaterBinding.inflate(layoutInflater)
            setContentView(deleteDaterBinding.root)
            deleteDaterBinding.let { viewBind ->
                viewBind.btnCancel.setOnClickListener {
                    viewBind.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        view.visibility = View.GONE
                    }
                }
                viewBind.btnYes.setOnClickListener {
                    viewBind.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        view.visibility = View.GONE
                        //API call for delete dater
                        removeTribeDater()
                    }
                }
                viewBind.txtDontShow.setOnClickListener {
                    viewBind.btnYes.performClick()
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                deleteDaterBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDown(
        e: MotionEvent
    )
            : Boolean {
        return true
    }

    override fun onShowPress(
        e: MotionEvent
    ) {
    }

    override fun onSingleTapUp(
        e: MotionEvent
    ): Boolean {
        Log.d("TAG", "selectedDater: $vTouch")
        val user: DaterConnectionResponseModel.User = daterList[vTouch?.tag as Int]
        requireActivity().startActivity(
            Intent(
                requireActivity(),
                LoadingActivity::class.java
            ).putExtra("selectedUser", Gson().toJson(user))
        )
        return true
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent
        ,
        distanceX: Float,
        distanceY: Float
    )
            : Boolean {
        return false
    }

    override fun onLongPress(
        e: MotionEvent
    ) {
        cancelViews[vTouch?.tag as Int].apply {
            visibility = View.VISIBLE
            this.setOnClickListener {
                requireActivity().openDeleteDaterDialog(cancelViews[vTouch?.tag as Int])
            }
        }
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent
        ,
        velocityX: Float,
        velocityY: Float
    )
            : Boolean {
        return false
    }

    private fun removeTribeDater() {
        val apiRequest = JsonObject().apply {
            addProperty("userid", daterList[vTouch?.tag as Int].userid?.toInt())
        }
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog(){removeTribeDater()}
//                AppUtils.showMessageOK(
//                    requireActivity(),
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    removeTribeDater()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    homeViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        homeViewModel.removeTribeDater(
                            "Bearer $authToken", apiRequest
                        )
                    }
                }
            }
        }
    }

    private fun openChatDialog() {
        val dialog = Dialog(requireContext(), R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val chatBinding = DialogTribeChatBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(chatBinding.root)
            chatBinding.apply {
                txt1.text = getString(
                    R.string.connect_title_1,
                    user1.username!!.split(" ")[0],
                    user2.username!!.split(" ")[0]
                )
                if (user1.username != "") {
                    val textDrawable = requireActivity().createPlaceholderImage(
                        user1.username!!,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )

                    Glide.with(requireActivity())
                        .load(user1.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable)
                        .into(img1)
                }
                if (user2.username != "") {
                    val textDrawable2 = requireActivity().createPlaceholderImage(
                        user2.username!!,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )

                    Glide.with(requireActivity())
                        .load(user2.image).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable2)
                        .into(img2)
                }
                ivSend.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        XmppServiceCommand.addContactToRoster(
                            requireActivity(),
                            user1.phone_number + AppConstants.XMPP_EXTENSION,
                            user1.username
                        )
                        XmppServiceCommand.addContactToRoster(
                            requireActivity(),
                            user2.phone_number + AppConstants.XMPP_EXTENSION,
                            user2.username
                        )

                        XmppServiceCommand.sendMessage(
                            requireActivity(),
                            user1.phone_number + AppConstants.XMPP_EXTENSION,
                            edtMessage.text.toString()
                        )
                        XmppServiceCommand.sendMessage(
                            requireActivity(),
                            user2.phone_number + AppConstants.XMPP_EXTENSION,
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
        val dialog = Dialog(requireActivity(), R.style.SlideDialogTheme)
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
                        requireActivity().theme
                    )
                    setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPagerDesc,
                            requireActivity().theme
                        )
                    )
                }
                txtDesc.text = getString(
                    R.string.no_message_desc_send,
                    user1.username!!.split(" ")[0] + "'s",
                    user2.username!!.split(" ")[0]
                )
                btnQuickNote.apply {
                    background = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.corner_mint_gradient_bg,
                        requireActivity().theme
                    )
                    setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPagerDesc,
                            requireActivity().theme
                        )
                    )
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
        val dialog = Dialog(requireActivity(), R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val connectionBinding = DialogTribeMemberConnectionBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(connectionBinding.root)
            connectionBinding.apply {
                txtDesc.text =
                    getString(
                        R.string.tribe_connected,
                        user1.username!!.split(" ")[0],
                        user2.username!!.split(" ")[0]
                    )
                btnDontShow.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        binding.ivCancel.performClick()
                    }
                }
                btnGotIt.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        binding.ivCancel.performClick()
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

    private fun showSingleFriendToolTip() {
        toolTipBinding.getContentView().apply {
            findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                getString(R.string.single_friend_tip_title)
            findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
            findViewById<AppCompatTextView>(R.id.txtTipDesc).text =
                getString(R.string.single_friend_tip_desc)
            findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {
                homeViewModel.savePreference(PreferenceKeys.PREF_CONNECTOR_TOOL_TIP, true)
                toolTipBinding.dismiss()
                binding.ivCancel.performClick()
            }
        }
        toolTipBinding.showAlignBottom(binding.cardLogo1)
    }

    private fun requestRuntimePermission() {
        Dexter.withContext(activity)
            .withPermission(Manifest.permission.READ_CONTACTS)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    requireActivity().startActivity(
                        Intent(
                            requireContext(),
                            ConnectorContactActivity::class.java
                        ).putExtra("selectedUser", Gson().toJson(user1))
                            .putExtra("daterList", Gson().toJson(daterList))
                    )
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied) {
                        val intent =
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts(
                            "package",
                            activity?.packageName, null
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bubbleListener = (context as BubbleListener)
    }

    override fun onDetach() {
        super.onDetach()
        bubbleListener = null
    }


    private fun suggestionProfileApi() {
        lifecycleScope.launch {
            val jsonObject = JsonObject().apply {
                addProperty(
                    "phone_number", user2.phone_number
                )
                addProperty("ref_profile_suggest", user1.userid)
            }
            when {
                !AppUtils.isNetworkAvailable(requireContext()) -> {
                    openFailNetworkDialog(){suggestionProfileApi()}
//                    AppUtils.showMessageOK(
//                        requireActivity(),
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

    override fun onResume() {
        super.onResume()

        inviteContactListApi()
    }

    private fun showDaterToolTip() {
        toolTipBinding.getContentView().apply {
            findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                getString(R.string.account_type)
            findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
            findViewById<AppCompatTextView>(R.id.txtTipDesc).text =
                getString(R.string.account_type_message)
            findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {
                homeViewModel.savePreference(PreferenceKeys.PREF_ACCOUNT_TOOL_TIP, true)
                toolTipBinding.dismiss()
            }
        }
        toolTipBinding.showAlignBottom(binding.toggleUser)
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

    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }
}