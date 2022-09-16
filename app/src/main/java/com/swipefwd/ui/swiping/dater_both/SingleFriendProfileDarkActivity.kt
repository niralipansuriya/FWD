package com.swipefwd.ui.swiping.dater_both

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Window
import android.view.animation.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import com.skydoves.balloon.overlay.BalloonOverlayRect
import com.swipefwd.Injection
import com.swipefwd.R
import com.swipefwd.databinding.*
import com.swipefwd.data.models.*
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.auth.login.LoginViewModel
import com.swipefwd.ui.auth.register.UserInfoViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.changeStatusBar
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.yuyakaido.android.cardstackview.*
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

class SingleFriendProfileDarkActivity : AppCompatActivity(), CardStackListener {

    private lateinit var mBinding: ActivitySingleFriendProfileDarkBinding
    private val mActivity by lazy {
        this@SingleFriendProfileDarkActivity
    }
    private var animationFlag = 0
    private var isNotInterested = false
    private var isInterested = false
    private var swipedPosition = 0
    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(mActivity, AppRepository()) }
    }
    private var userId = 0
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var profileList = ArrayList<SwipingCustomModel>()
    private var recommendingUser = DaterConnectionResponseModel.User()
    private var isRewindEnabled = true
    private var isSwipedLeft = false
    private val mAdapter: SwipeConnectorCardAdapter by lazy {
        SwipeConnectorCardAdapter(mActivity, itemClickListener = { flag, position ->
            //Flag::-1:Dislike 2:Revert,3:Forward
            when (flag) {
                1 -> {
                    val setting = SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Left)
                        .setDuration(Duration.Normal.duration)
                        .setInterpolator(AccelerateInterpolator())
                        .build()
                    manager.setSwipeAnimationSetting(setting)
                    mBinding.cardStackView.swipe()
                }
                2 -> {
                    if (isRewindEnabled && isSwipedLeft) {
                        isRewindEnabled = false
                        if (position != 0) {
                            swipeProfileRewind(profileList[position - 1].userProfileModel)
                            val setting = RewindAnimationSetting.Builder()
                                .setDirection(Direction.Left)
                                .setDuration(Duration.Slow.duration)
                                .setInterpolator(BounceInterpolator())
                                .build()
                            manager.setRewindAnimationSetting(setting)
                            mBinding.cardStackView.rewind()
                        }
                    }
                }
                3 -> {
                    val setting = SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Right)
                        .setDuration(Duration.Normal.duration)
                        .setInterpolator(AccelerateInterpolator())
                        .build()
                    manager.setSwipeAnimationSetting(setting)
                    mBinding.cardStackView.swipe()
                }
            }
        })
    }
    private var swipeDirection: Int = 1 //0 : dislike , 1 :like
    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private var count = 0
    private val toolTip by lazy {
        createBalloon(mActivity) {
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setBackgroundColor(ContextCompat.getColor(mActivity, android.R.color.transparent))
            setLayout(R.layout.layout_custom_tool_tip)
            setArrowSize(10)
            setCornerRadius(0f)
            setWidthRatio(1f)
            setArrowColor(
                ContextCompat.getColor(
                    mActivity,
                    R.color.blue_gradient_2
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
            setLifecycleOwner(mActivity)
        }
    }
    private val userInfoViewModel: UserInfoViewModel by viewModels {
        viewModelFactory { UserInfoViewModel(mActivity, AppRepository()) }
    }
    private var socialUser = SocialMediaUserModel()
    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory {
            LoginViewModel(
                Injection.getAppRepository(),
                Injection.getInternalAppDataStore(applicationContext),
                Injection.getAppDataBase(applicationContext)
            )
        }
    }
    var callbackmanager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySingleFriendProfileDarkBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        EventBus.getDefault().register(this)
        init()
    }

    private fun init() {
        changeStatusBar()
        mBinding.apply {
            val userDetail = intent.getStringExtra("selectedUser")
            recommendingUser = Gson().fromJson<Any>(
                userDetail,
                DaterConnectionResponseModel.User::class.java
            ) as DaterConnectionResponseModel.User
            mActivity.setShaderView(
                ivLogo,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            val swipeDeckData = intent.getStringExtra("swipeDeck")
            if (swipeDeckData != null) {
                val type: Type =
                    object : TypeToken<List<SwipingCustomModel?>?>() {}.type
                profileList =
                    Gson().fromJson(swipeDeckData, type) as ArrayList<SwipingCustomModel>
                Log.e("TAG:", "DECK: == $profileList")
            }
            txtRecommendUserName.text =
                getString(R.string.recommending_for, recommendingUser.username!!.split(" ")[0])
            Glide.with(mActivity).load(recommendingUser.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.circleCropTransform())
                .into(ivRecommendUserImage)
            imgHome.setOnClickListener {
                nextActivity(TabManagerActivity::class.java)
                finish()
                finishAffinity()
            }
            lifecycleScope.launch {
                userId = swipeViewModel.getUserId.firstOrNull() ?: 0
                if (swipeViewModel.getIsDarkModeTip.firstOrNull() == false) {
                    showDarkModeToolTip()
                }
            }
            imgChat.setOnClickListener {
                startActivity(
                    Intent(mActivity, TabManagerActivity::class.java)
                        .putExtra("message", "message")
                )
                finishAffinity()
                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
            }
            cardStackView.apply {
                manager.setCanScrollVertical(false)
                manager.setCanScrollHorizontal(true)
                layoutManager = manager
                this.adapter = mAdapter
                mAdapter.addAll(profileList)
                itemAnimator.apply {
                    if (this is DefaultItemAnimator) {
                        supportsChangeAnimations = false
                    }
                }
            }
            initObservers()
        }
    }

    private fun hideImageViews() {
        animationFlag = 0
        mBinding.imgDislike.visibility = View.INVISIBLE
        mBinding.imgLike.visibility = View.INVISIBLE
    }

    private fun AppCompatImageView.scaleUp() {
        animationFlag = 1
        visibility = View.VISIBLE
        val scaleAnimation = ScaleAnimation(
            0f, 1f, 0f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.fillBefore = true
        val alphaAnimation = AlphaAnimation(0f, 1f)
        alphaAnimation.fillBefore = true
        scaleAnimation.duration = 500
        alphaAnimation.duration = 500
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(scaleAnimation)
        animationSet.addAnimation(alphaAnimation)
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        startAnimation(animationSet)
    }

    private fun openInterestedDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val binding = DialogSwipeInterestedBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                cardLike.setImageResource(R.drawable.like_fwd)
                txtTitle.text = getString(R.string.fwd_connector)
                txtDesc.text = getString(R.string.swipe_interested_text_connector)
                btnYes.text = getString(R.string.yes_fwd)
                btnGoBack.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        //mAdapter.addAll(profileList)
                        //mBinding.cardStackView.adapter = mAdapter
                        val setting = RewindAnimationSetting.Builder()
                            .setDirection(Direction.Left)
                            .setDuration(Duration.Slow.duration)
                            .setInterpolator(BounceInterpolator())
                            .build()
                        manager.setRewindAnimationSetting(setting)
                        mBinding.cardStackView.rewind()
                        dismiss()
                    }
                }
                btnYes.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        apiCallForSwipedUser(false)
                    }
                }
                try {
                    setZoomDialogWindowAttributes()
                    show()
                    swipeViewModel.savePreference(PreferenceKeys.PREF_INTERESTED, true)
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun openNotInterestedDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val binding = DialogSwipeNotInterestedBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                txtTitle.text = getString(R.string.not_a_match)
                txtDesc.text = getString(R.string.swipe_not_interested_text_connector)
                btnNo.text = getString(R.string.not_match)
                btnGoBack.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        //mAdapter.addAll(profileList)
                        //mBinding.cardStackView.adapter = mAdapter
                        val setting = RewindAnimationSetting.Builder()
                            .setDirection(Direction.Left)
                            .setDuration(Duration.Slow.duration)
                            .setInterpolator(BounceInterpolator())
                            .build()
                        manager.setRewindAnimationSetting(setting)
                        mBinding.cardStackView.rewind()
                        dismiss()
                    }
                }
                btnNo.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        apiCallForSwipedUser(true)
                        dismiss()
                    }
                }
                try {
                    setZoomDialogWindowAttributes()
                    show()
                    swipeViewModel.savePreference(PreferenceKeys.PREF_NOT_INTERESTED, true)
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun swipeProfileLeftRight(
        swipeDirection: Int,
        swipeModel: SwipingProfileModel.ProfileModel
    ) {
        lifecycleScope.launch {
            val apiObject = JsonObject().apply {
                if (userId != 0)
                    addProperty("swiper_id", userId.toString()) //self id
                if (swipeModel.id != 0)
                    addProperty("target_id", swipeModel.id.toString()) //matched person id
                if (recommendingUser.userid?.toInt() != 0)
                    addProperty(
                        "dater_id",
                        recommendingUser.userid?.toString()
                    )
                addProperty("direction", swipeDirection.toString())
                addProperty("deck_section_id", "2")
                addProperty("mode", 1.toString())
            }
            when {
                !AppUtils.isNetworkAvailable(mActivity) -> {
                    openFailNetworkDialog(){swipeProfileLeftRight(swipeDirection, swipeModel)}
//                    AppUtils.showMessageOK(
//                        mActivity,
//                        getString(R.string.app_name),
//                        getString(R.string.common_retry),
//                        getString(R.string.no_internet)
//                    ) { _, _ ->
//                        swipeProfileLeftRight(swipeDirection, swipeModel)
//                    }
                }
                else -> {
                    val token = swipeViewModel.getAuthToken.firstOrNull()!!
                    swipeViewModel.swipeUserLeftRight(apiObject, "Bearer $token")
                }
            }
        }
    }

    private fun initObservers() {
        swipeViewModel.apply {
            showLoading.observe(mActivity, {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            })
            errorMessage.observe(mActivity, {
                Log.e("TAG", "ERROR === $it")
                mActivity.showSnackBar(mBinding.constraintMain, it)
            })
            swipeLeftRightData.observe(mActivity) { responseModel ->
                Log.e("TAG" , "SWIPE === $responseModel")
                if (count == 6) {
                    lifecycleScope.launch {
                        val socialMediaUser =
                            userInfoViewModel.socialMediaUser.firstOrNull() ?: ""
                        if (socialMediaUser.isNotEmpty()) {
                            socialUser =
                                Gson().fromJson(
                                    socialMediaUser ,

                                    SocialMediaUserModel::class.java
                                )
                            if (socialUser.socialType == AppConstants.SOCIAL_FACEBOOK) {
                                val isPermissionDenied =
                                    userInfoViewModel.isConnectorFaceBookPermissionDenied.firstOrNull()
                                val accessToken =
                                    AccessToken.getCurrentAccessToken()
                                val isLoggedIn =
                                    accessToken != null && !accessToken.isExpired
                                if (!AppUtils.dialogForFbFriends(mActivity)) {
                                    redirectToLoading()
                                } else {
                                    if (isPermissionDenied == true && isLoggedIn) {
                                        openPermissionDialog()
                                    } else {
                                        redirectToLoading()
                                    }
                                }
                            }
                        }
                    }
                } else {
                    //remove swiped user from list
                    when (swipeDirection) {
                        1 -> {
                            profileList[swipedPosition].userProfileModel?.let {
                                openChatDialog(
                                    it
                                )
                            }
                        }
                        else -> {
                            if (count == profileList.size) {
                                startActivity(
                                    Intent(
                                        mActivity ,
                                        ChangePreferencesActivity::class.java
                                    ).putExtra("selectedUser" , Gson().toJson(recommendingUser))
                                )
                                finish()
                                overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                            }
                        }
                    }
                }
            }
            swipeLeftRightError.observe(mActivity) {
                Log.e("TAG" , "ERROR === $it")
                mActivity.showSnackBar(mBinding.constraintMain , it.data?.message.toString())
                val setting = RewindAnimationSetting.Builder()
                    .setDirection(Direction.Left)
                    .setDuration(Duration.Slow.duration)
                    .setInterpolator(BounceInterpolator())
                    .build()
                manager.setRewindAnimationSetting(setting)
                mBinding.cardStackView.rewind()
                /*CM.showMessageOK(
                    mActivity,
                    getString(R.string.app_name),
                    getString(R.string.common_ok),
                    it.data?.message.toString()
                ) { _, _ ->
                    //mAdapter.addAll(profileList)
                    //mBinding.cardStackView.adapter = mAdapter
                    //if error comes while swiping we will revert that card on deck
                    val setting = RewindAnimationSetting.Builder()
                        .setDirection(Direction.Left)
                        .setDuration(Duration.Normal.duration)
                        .setInterpolator(DecelerateInterpolator())
                        .build()
                    manager.setRewindAnimationSetting(setting)
                    mBinding.cardStackView.rewind()
                }*/
            }
        }
    }

    private fun openPermissionDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        dialogs.add(dialog)
        val userProfileBinding = DialogFbPermissionBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(userProfileBinding.root)
            userProfileBinding.apply {
                btnAllow.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        //asking for fb permission
                        checkFbPermission()
                    }
                }
                btnNotNow.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        loginViewModel.saveLoginData(
                            PreferenceKeys.PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION,
                            false
                        )
                        redirectToLoading()
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

    private fun apiCallForSwipedUser(isNotInterested: Boolean) {
        isSwipedLeft = isNotInterested
        if (isNotInterested) {
            swipeDirection = 0
            swipeProfileLeftRight(
                1,
                profileList[swipedPosition].userProfileModel!!
            )
        } else {
            swipeDirection = 1
            swipeProfileLeftRight(
                2,
                profileList[swipedPosition].userProfileModel!!
            )
        }
    }

    private fun showDarkModeToolTip() {
        toolTip.getContentView().apply {
            findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                getString(R.string.dark_mode)
            findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
            findViewById<AppCompatTextView>(R.id.txtTipDesc).text =
                getString(R.string.dark_mode_message)
            findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {
                swipeViewModel.savePreference(PreferenceKeys.PREF_DARK_MODE_TOOL_TIP, true)
                toolTip.dismiss()
            }
        }
        toolTip.showAlignBottom(mBinding.rlConnection)
    }

    private fun openChatDialog(user2: SwipingProfileModel.ProfileModel) {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val chatBinding = DialogTribeChatBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(chatBinding.root)
            chatBinding.apply {
                //for first user
                if (recommendingUser.username != "") {
                    val textDrawable1 = mActivity.createPlaceholderImage(
                        recommendingUser.username!!,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )

                    Glide.with(mActivity)
                        .load(recommendingUser.image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable1)
                        .into(img1)
                }
                //for user2
                if (user2.firstName != "") {
                    val textDrawable2 = mActivity.createPlaceholderImage(
                        user2.firstName!!,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )

                    Glide.with(mActivity)
                        .load(user2.profilePictureUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable2)
                        .into(img2)
                }
                txt1.text =
                    getString(
                        R.string.connect_title,
                        recommendingUser.username!!.split(" ")[0],
                        user2.firstName
                    )
                txt2.text = getString(R.string.connector_message)
                ivSend.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        if (count == profileList.size) {
                            startActivity(
                                Intent(
                                    mActivity,
                                    ChangePreferencesActivity::class.java
                                ).putExtra("selectedUser", Gson().toJson(recommendingUser))
                            )
                            finish()
                            overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                        }
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
                if (!dialog.isShowing) {
                    setBottomDialogWindowAttributes()
                    show()
                    chatBinding.imgDialogGradient.setDialogFadeInAnimation()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openNoMessageDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val messageBinding = DialogNoMessageBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(messageBinding.root)
            messageBinding.apply {
                val user2 = profileList[swipedPosition].userProfileModel
                txtBackground.background =
                    ContextCompat.getDrawable(mActivity, R.drawable.top_blue_corner_gradient_bg)
                txtDesc.text = getString(
                    R.string.no_message_send_1,
                    user2?.firstName,
                    recommendingUser.username!!.split(" ")[0]
                )
                btnSure.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        if (count == profileList.size) {
                            startActivity(
                                Intent(
                                    mActivity,
                                    ChangePreferencesActivity::class.java
                                ).putExtra("selectedUser", Gson().toJson(recommendingUser))
                            )
                            finish()
                            overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                        }
                    }
                }
                btnQuickNote.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        if (count == profileList.size) {
                            startActivity(
                                Intent(
                                    mActivity,
                                    ChangePreferencesActivity::class.java
                                ).putExtra("selectedUser", Gson().toJson(recommendingUser))
                            )
                            finish()
                            overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                        }
                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                messageBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun refreshData(eventModel: EventBusModel) {
        if (eventModel.event == "swipeUser") {
            when (eventModel.swipeDirection) {
                1, 2 -> {
                    //openChatDialog(eventModel.profileModel)
                    val setting = SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Right)
                        .setDuration(Duration.Normal.duration)
                        .setInterpolator(AccelerateInterpolator())
                        .build()
                    manager.setSwipeAnimationSetting(setting)
                    mBinding.cardStackView.swipe()
                }
                0 -> {
                    val setting = SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Left)
                        .setDuration(Duration.Normal.duration)
                        .setInterpolator(AccelerateInterpolator())
                        .build()
                    manager.setSwipeAnimationSetting(setting)
                    mBinding.cardStackView.swipe()
                }
                3 -> {
                    swipeProfileRewind(eventModel.profileModel)
                    val setting = RewindAnimationSetting.Builder()
                        .setDirection(Direction.Left)
                        .setDuration(Duration.Slow.duration)
                        .setInterpolator(BounceInterpolator())
                        .build()
                    manager.setRewindAnimationSetting(setting)
                    mBinding.cardStackView.rewind()
                }
            }
        }
        EventBus.getDefault().removeAllStickyEvents()
    }

    private fun swipeProfileRewind(swipeModel: SwipingProfileModel.ProfileModel?) {
        lifecycleScope.launch {
            val apiObject = JsonObject().apply {
                if (userId != 0)
                    addProperty("swiper_id", userId)
                if (swipeModel?.id != 0)
                    addProperty("target_id", swipeModel?.id)
                if (recommendingUser.userid?.toInt() != 0)
                    addProperty("dater_id", recommendingUser.userid?.toInt())
            }
            when {
                !AppUtils.isNetworkAvailable(mActivity) -> {
                    openFailNetworkDialog(){swipeProfileRewind(swipeModel)}
//                    AppUtils.showMessageOK(
//                        mActivity,
//                        getString(R.string.app_name),
//                        getString(R.string.common_retry),
//                        getString(R.string.no_internet)
//                    ) { _, _ ->
//                        swipeProfileRewind(swipeModel)
//                    }
                }
                else -> {
                    val token = swipeViewModel.getAuthToken.firstOrNull()!!
                    swipeViewModel.swipeUserRewind(apiObject, "Bearer $token", userId)
                }
            }
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction?.name}, r = $ratio")
        when (direction?.name) {
            AppConstants.SWIPE_LEFT -> {
                if (ratio >= 0.15) {
                    mBinding.apply {
                        if (animationFlag == 0) {
                            imgDislike.scaleUp()
                        }
                        imgLike.visibility = View.INVISIBLE
                    }
                }
            }
            AppConstants.SWIPE_RIGHT -> {
                if (ratio >= 0.15) {
                    mBinding.apply {
                        imgDislike.visibility = View.INVISIBLE
                        if (animationFlag == 0) {
                            imgLike.scaleUp()
                        }
                    }
                }
            }
        }
    }

    override fun onCardSwiped(direction: Direction?) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        count += 1
        when (direction?.name) {
            AppConstants.SWIPE_LEFT -> {
                swipedPosition = manager.topPosition - 1
                hideImageViews()
                lifecycleScope.launch {
                    isNotInterested = swipeViewModel.isNotInterested.firstOrNull() ?: false
                }
                if (!isNotInterested) {
                    openNotInterestedDialog()
                } else {
                    apiCallForSwipedUser(true)
                }
            }
            AppConstants.SWIPE_RIGHT -> {
                swipedPosition = manager.topPosition - 1
                hideImageViews()
                lifecycleScope.launch {
                    isInterested = swipeViewModel.isInterested.firstOrNull() ?: false
                }
                if (!isInterested) {
                    openInterestedDialog()
                } else {
                    apiCallForSwipedUser(false)
                }
            }
        }
        Log.e("TAG", "COUNT === $count")
        Log.e("TAG", "SIZE === ${profileList.size}")
        if (count == profileList.size) {
            hideImageViews()
            startActivity(
                Intent(
                    mActivity,
                    ChangePreferencesActivity::class.java
                ).putExtra("selectedUser", Gson().toJson(recommendingUser))
            )
            finish()
            overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
        }
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
        hideImageViews()
    }

    override fun onCardAppeared(view: View?, position: Int) {
        Log.d("CardStackView", "onCardAppeared: $position")
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d("CardStackView", "onCardDisappeared: $position")
    }

    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    private fun redirectToLoading() {
        nextActivity(LoadingActivity::class.java)
        finish()
    }

    private fun checkFbPermission() {
        callbackmanager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(
            mActivity,
            listOf("user_friends")
        )
        LoginManager.getInstance().registerCallback(callbackmanager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    loginViewModel.saveLoginData(
                        PreferenceKeys.PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION,
                        true
                    )
                    redirectToLoading()
                }

                override fun onError(error: FacebookException) {
                    loginViewModel.saveLoginData(
                        PreferenceKeys.PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION,
                        true
                    )
                    redirectToLoading()
                }

                override fun onSuccess(result: LoginResult) {
                    loginViewModel.saveLoginData(
                        PreferenceKeys.PREF_CONNECTOR_FACEBOOK_FRD_PERMISSION,
                        false
                    )
                    getFbFriendsList(result!!.accessToken)
                }
            })
    }

    private fun getFbFriendsList(accessToken: AccessToken) {
        GraphRequest.newMyFriendsRequest(
            accessToken
        ) { _, response ->
            response.let { graphResponse ->
                graphResponse?.jsonObject?.let { jsonObject ->
                    parseResponse(jsonObject)
                }
            }
        }.executeAsync()
    }

    private fun parseResponse(friends: JSONObject) {
        val facebookIds = java.util.ArrayList<String>()
        try {

            val friendsArray: JSONArray = friends["data"] as JSONArray
            Log.d("Facebook", "FRIEND_LIST: $friendsArray")
            for (i in 0 until friendsArray.length()) {
                val obj = friendsArray.getJSONObject(i)
                val userId = obj.getString("id")
                facebookIds.add(userId.toString())
            }
            Log.d("Facebook", "Ids: $facebookIds")
            loginViewModel.saveLoginData(
                PreferenceKeys.PREF_FACEBOOK_IDS,
                Gson().toJson(facebookIds)
            )
            // facebook use paging if have "next" this mean you still have friends if not start load fbFriends list
            val next = friends.getJSONObject("paging")
                .getString("next")
            Log.d("Facebook", "NEXT_FRIEND_LIST: $next")
        } catch (e1: JSONException) {
            e1.printStackTrace()
        }
        if (facebookIds.isNotEmpty()) {
            lifecycleScope.launch {
                userInfoViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                    sendFacebookIds(authToken, facebookIds)
                }
            }

        } else {
            redirectToLoading()
        }
        //activity?.nextActivity(LoadingActivity::class.java)
    }

    private fun sendFacebookIds(token: String, facebookIds: ArrayList<String>) {
        val jsonArray = JsonArray()
        facebookIds.forEach {
            jsonArray.add(it)
        }
        val apiRequest = JsonObject().apply {
            add("facebook_ids", jsonArray)
        }
        loginViewModel.sendFacebookIds("Bearer $token", apiRequest)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackmanager!!.onActivityResult(requestCode, resultCode, data)
    }
}