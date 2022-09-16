package com.swipefwd.ui.swiping.dater_both

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.util.Log
import android.view.View
import android.view.Window
import android.view.animation.*
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
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
import com.swipefwd.data.models.EventBusModel
import com.swipefwd.data.models.SocialMediaUserModel
import com.swipefwd.data.models.SwipingCustomModel
import com.swipefwd.data.models.SwipingProfileModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.*
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.auth.login.LoginViewModel
import com.swipefwd.ui.home.LoadingActivity
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.ui.auth.register.UserInfoViewModel
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.mintTooltip
import com.swipefwd.utils.AppUtils.nextActivity
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.utils.datastore.PreferenceKeys
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


class SwipeProfileActivity : AppCompatActivity(), CardStackListener {

    private val mActivity by lazy {
        this@SwipeProfileActivity
    }
    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    var lastwasLeft = false
    var lastwasRight = false
    private lateinit var mBinding: ActivitySwipeProfileBinding
    private var profileList = ArrayList<SwipingCustomModel>()
    private val mAdapter: SwipeCardAdapter by lazy {
        SwipeCardAdapter(mActivity, itemClickListener = { flag, position ->
            //Flag::-0: Check(Open Details) 1:Dislike 2:Like,3:revert 4:superlike
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
                    isSuperLike = false
                    val setting = SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Right)
                        .setDuration(Duration.Normal.duration)
                        .setInterpolator(AccelerateInterpolator())
                        .build()
                    manager.setSwipeAnimationSetting(setting)
                    mBinding.cardStackView.swipe()
                }
                4 -> {
                    isSuperLike = true
                    val setting = SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Top)
                        .setDuration(Duration.Normal.duration)
                        .setInterpolator(AccelerateInterpolator())
                        .build()
                    manager.setSwipeAnimationSetting(setting)
                    mBinding.cardStackView.swipe()
                }
                3 -> {
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
            }
        }, connectorClickListener = { connectorList, daterUser, profileType ->
            openConnectorFriendDialog(connectorList, daterUser, profileType)
        })
    }
    private var animationFlag = 0
    private val swipeViewModel: SwipeProfileViewModel by viewModels {
        viewModelFactory { SwipeProfileViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var userId = 0
    private var swipedPosition = 0
    private var isNotInterested = false
    private var isInterested = false
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private var count = 0
    private var isSuperLike = false
    private var isSwipedLeft = false
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
    private var isRewindEnabled = true
    private val userInfoViewModel: UserInfoViewModel by viewModels {
        viewModelFactory { UserInfoViewModel(mActivity, AppRepository()) }
    }
    private var socialUser = SocialMediaUserModel()
    var callbackmanager: CallbackManager? = null
    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory {
            LoginViewModel(
                Injection.getAppRepository(),
                Injection.getInternalAppDataStore(applicationContext),
                Injection.getAppDataBase(applicationContext)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySwipeProfileBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        EventBus.getDefault().register(this)
        init()
    }

    private fun init() {
        mBinding.apply {
            lifecycleScope.launch {
                userId = swipeViewModel.getUserId.firstOrNull() ?: 0
                val swipeDeckData = intent.getStringExtra("swipeDeck")
                if (swipeDeckData != null) {
                    val type: Type =
                        object : TypeToken<List<SwipingCustomModel?>?>() {}.type
                    profileList =
                        Gson().fromJson(swipeDeckData, type) as ArrayList<SwipingCustomModel>
                    Log.e("TAG:", "DECK: == $profileList")
                }
            }
            mActivity.setShaderView(
                ivLogo,
                R.color.blue_gradient_2,
                R.color.blue_gradient_3
            )
            imgHome.setOnClickListener {
                nextActivity(TabManagerActivity::class.java)
                finish()
                finishAffinity()
            }
            imgFire.setOnClickListener {
                toolTip.getContentView().apply {
                    findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                        getString(R.string.fire_title)
                    findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
                    val image =
                        ImageSpan(context, R.drawable.super_like_orange, ImageSpan.ALIGN_CENTER)
                    val spannableText =
                        SpannableString("This icon will be on fire  when your friends have introduced you to people they know and they have swiped right on you").apply {
                            setSpan(image, 26, 27, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }
                    findViewById<AppCompatTextView>(R.id.txtTipDesc).text = spannableText
                    findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {
                        toolTip.dismiss()
                    }
                }
                toolTip.showAlignBottom(it)
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

    private fun moveToChangePreferencesActivity() {
        nextActivity(ChangePreferencesActivity::class.java)
        finish()
    }

    private fun hideImageViews() {
        animationFlag = 0
        mBinding.imgDislike.visibility = View.INVISIBLE
        mBinding.imgLike.visibility = View.INVISIBLE
    }

    private fun AppCompatImageView.scaleUp() {
        //animationFlag = 1
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
                when (profileList[swipedPosition].profileName) {
                    AppConstants.PROFILE_GREEN -> {
                        btnGoBack.visibility = View.GONE
                        btnYes.visibility = View.GONE
                        btnGotIt.apply {
                            visibility = View.VISIBLE
                            setOnClickListener {
                                binding.imgDialogGradient.setDialogFadeOutAnimation {
                                    apiCallForSwipedUser(false)
                                    dismiss()
                                }
                            }
                        }
                        cardIcon.visibility = View.GONE
                        cardConnected.visibility = View.VISIBLE
                        txtTitle.text = getString(R.string.tribe_connected_title)
                        txtDesc.text = getString(
                            R.string.tribe_connected_message,
                            profileList[swipedPosition].userProfileModel!!.firstName
                        )
                    }
                    else -> {
                        btnGoBack.visibility = View.VISIBLE
                        btnYes.visibility = View.VISIBLE
                        btnGotIt.visibility = View.GONE
                        cardIcon.visibility = View.VISIBLE
                        cardConnected.visibility = View.GONE
                        txtTitle.text = getString(R.string.interested_question)
                        txtDesc.text = getString(R.string.swipe_interested_text)
                        btnYes.text = getString(R.string.yes_i_am)
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
                                apiCallForSwipedUser(false)
                                dismiss()
                            }
                        }
                    }
                }
                try {
                    setZoomDialogWindowAttributes()
                    if (!mActivity.isFinishing) {
                        show()
                    }
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
                txtTitle.text = getString(R.string.not_interested1)
                txtDesc.text = getString(R.string.swipe_not_interested_text)
                btnNo.text = getString(R.string.no_i_m_not)
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
                    if (!mActivity.isFinishing) {
                        show()
                    }
                    swipeViewModel.savePreference(PreferenceKeys.PREF_NOT_INTERESTED, true)
                    imgDialogGradient.setDialogFadeInAnimation()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun initObservers() {
        swipeViewModel.apply {
            showLoading.observe(mActivity) {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            }
            errorMessage.observe(mActivity) {
                Log.e("TAG" , "ERROR === $it")
                mActivity.showSnackBar(mBinding.layoutMain , it)
            }
            swipeLeftRightData.observe(mActivity) { responseModel ->
                Log.e("TAG" , "SWIPE === $responseModel")
                lastwasLeft = false
                lastwasRight = false
                if (count == profileList.size) {
                    hideImageViews()
                    moveToChangePreferencesActivity()
                } else if (count == 6) {
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
                                    userInfoViewModel.isFaceBookPermissionDenied.firstOrNull()
                                val accessToken = AccessToken.getCurrentAccessToken()
                                val isLoggedIn = accessToken != null && !accessToken.isExpired
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
                    if (!isSwipedLeft) {
                        val swipedUser = profileList[swipedPosition]
                        if (swipedUser.hasLiked || swipedUser.hasSuperLiked) {
                            val isGreenProfile = swipedUser.profileName
                            startActivity(
                                Intent(mActivity , SwipeChatActivity::class.java)
                                    .putExtra(
                                        "swipedProfileImage" ,
                                        swipedUser.userProfileModel?.profilePictureUrl
                                    )
                                    .putExtra(
                                        "jid" ,
                                        swipedUser.userProfileModel?.phoneNumber + AppConstants.XMPP_EXTENSION
                                    )
                                    .putExtra(
                                        "isGreenProfile" ,
                                        isGreenProfile
                                    )
                                    .putExtra("name" , swipedUser.userProfileModel?.firstName)
                            )
                            overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                        }

                    }
                }
            }
            swipeLeftRightError.observe(mActivity) {
                Log.e("TAG" , "ERROR === $it")
                mActivity.showSnackBar(mBinding.layoutMain , it.data?.message.toString())
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun swipeProfileLeftRight(
        swipeDirection: Int,
        swipeModel: SwipingProfileModel.ProfileModel,
        swiperMode: Int
    ) {
        lifecycleScope.launch {
            val apiObject = JsonObject().apply {
                if (userId != 0)
                    addProperty("swiper_id", userId.toString())
                if (swipeModel.id != 0)
                    addProperty("target_id", swipeModel.id.toString())
                addProperty(
                    "deck_section_id",
                    AppUtils.getProfileTypeId(profileList[swipedPosition].profileName).toString()
                )
                addProperty("direction", swipeDirection.toString())
                addProperty("mode", swiperMode.toString())
            }
            val token = swipeViewModel.getAuthToken.firstOrNull()!!
            when {
                !AppUtils.isNetworkAvailable(mActivity) -> {
                    openFailNetworkDialog(){swipeProfileLeftRight(swipeDirection, swipeModel, swiperMode)}
//                    AppUtils.showMessageOK(
//                        mActivity,
//                        getString(R.string.app_name),
//                        getString(R.string.common_retry),
//                        getString(R.string.no_internet)
//                    ) { _, _ ->
//                        swipeProfileLeftRight(swipeDirection, swipeModel, swiperMode)
//                    }
                }
                else -> {
                    swipeViewModel.swipeUserLeftRight(apiObject, "Bearer $token")
                }
            }
        }
    }

    private fun swipeProfileRewind(swipeModel: SwipingProfileModel.ProfileModel?) {
        lifecycleScope.launch {
            val apiObject = JsonObject().apply {
                if (userId != 0)
                    addProperty("swiper_id", userId)
                if (swipeModel?.id != 0)
                    addProperty("target_id", swipeModel?.id)
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

    private fun apiCallForSwipedUser(isNotInterested: Boolean) {
        isSwipedLeft = isNotInterested
        if (isNotInterested) {
            swipeProfileLeftRight(
                1,
                profileList[swipedPosition].userProfileModel!!,
                2
            )
        } else {
            if (isSuperLike) {
                swipeProfileLeftRight(
                    3,
                    profileList[swipedPosition].userProfileModel!!,
                    2
                )
            } else {
                swipeProfileLeftRight(
                    2,
                    profileList[swipedPosition].userProfileModel!!,
                    2
                )
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
                0 -> {
                    val setting = SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Left)
                        .setDuration(Duration.Normal.duration)
                        .setInterpolator(AccelerateInterpolator())
                        .build()
                    manager.setSwipeAnimationSetting(setting)
                    mBinding.cardStackView.swipe()
                }
                1, 2 -> {
                    val setting = SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Right)
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

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction?.name}, r = $ratio")
        when (direction?.name) {
            AppConstants.SWIPE_LEFT -> {
                if (ratio >= 0.15) {
                    mBinding.apply {
                        if (animationFlag == 0) {
                            Log.e("left", "left inside")
                            if (!lastwasLeft) {
                                lastwasLeft = true
                                lastwasRight = false
                                imgDislike.scaleUp()
                            }

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
                            if (!lastwasRight) {

                                lastwasLeft = false
                                lastwasRight = true
                                imgLike.scaleUp()
                            }
                            Log.e("right", "right inside")

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
                    if (!isInterested) {
                        if (!mActivity.isFinishing) {
                            if (isSuperLike) {
                                apiCallForSwipedUser(false)
                            } else {
                                openInterestedDialog()
                            }
                        } else {
                            apiCallForSwipedUser(false)
                        }
                    } else {
                        apiCallForSwipedUser(false)
                    }
                }
            }
        }
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        lastwasLeft = false
        lastwasRight = false
        hideImageViews()
    }

    override fun onCardAppeared(view: View?, position: Int) {
        Log.d("CardStackView", "onCardAppeared: $position")
        mAdapter.showSuperLike(position)
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d("CardStackView", "onCardDisappeared: $position")
    }

    fun showFWDToolTip(rlTitle: RelativeLayout, isGreenProfile: Boolean, position: Int) {
        if (isGreenProfile) {
            val mintTip = mActivity.mintTooltip()
            mintTip.getContentView().apply {
                findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                    getString(R.string.connector_title)
                findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
                findViewById<AppCompatTextView>(R.id.txtTipDesc).text =
                    getString(R.string.connector_tip_message)
                findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {
                    mintTip.dismiss()
                    swipeViewModel.savePreference(PreferenceKeys.PREF_FWD_GREEN_TOOL_TIP, true)
                }
            }
            mintTip.setOnBalloonDismissListener {
                mAdapter.showSuperLike(position)
            }
            lifecycleScope.launch {
                if (swipeViewModel.getGreenShowTip.firstOrNull() == false) {
                    mintTip.showAlignBottom(rlTitle)
                }
            }
        } else {
            toolTip.getContentView().apply {
                findViewById<AppCompatTextView>(R.id.txtTipHeader).text =
                    getString(R.string.fwd_title)
                findViewById<AppCompatImageView>(R.id.imgToolTip).visibility = View.GONE
                findViewById<AppCompatTextView>(R.id.txtTipDesc).text =
                    getString(R.string.fwd_message)
                findViewById<MaterialButton>(R.id.btnTipGotIt).setOnClickListener {
                    toolTip.dismiss()
                    swipeViewModel.savePreference(PreferenceKeys.PREF_FWD_TOOL_TIP, true)
                }
            }
            toolTip.setOnBalloonDismissListener {
                mAdapter.showSuperLike(position)
            }
            lifecycleScope.launch {
                if (swipeViewModel.getShowTip.firstOrNull() == false) {
                    toolTip.showAlignBottom(rlTitle)
                }
            }
        }
    }

    private fun openConnectorFriendDialog(
        connectorList: ArrayList<SwipingProfileModel.ProfileModel>,
        daterUser: SwipingProfileModel.ProfileModel?,
        profileType: String
    ) {
        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        dialogs.add(dialog)
        val binding = DialogBottomListFriendsBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            setOnCancelListener {
                dialog.dismiss()
            }
            binding.apply {
                when (profileType) {
                    AppConstants.PROFILE_GREEN -> {
                        txtTitle.apply {
                            text =
                                getString(R.string.fwd_green_list, daterUser?.firstName)
                            background = ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.top_mint_corner_gradient_bg
                            )
                            setTextColor(
                                ContextCompat.getColor(
                                    mActivity,
                                    R.color.colorPagerDesc
                                )
                            )
                            text = this.text
                        }
                    }
                    else -> {
                        txtTitle.apply {
                            text =
                                getString(R.string.fwd_friend_list, daterUser?.firstName)
                            background = ContextCompat.getDrawable(
                                mActivity,
                                R.drawable.top_blue_corner_gradient_bg
                            )
                            setTextColor(ContextCompat.getColor(mActivity, R.color.whiteColor))
                        }
                    }
                }
                rvFriend.apply {
                    adapter = FacebookFriendsListAdapter(true).apply {
                        addAll(connectorList)
                    }
                    setDivider()
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

    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackmanager!!.onActivityResult(requestCode, resultCode, data)
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
                        PreferenceKeys.PREF_FACEBOOK_FRD_PERMISSION,
                        true
                    )
                    redirectToLoading()
                }

                override fun onError(error: FacebookException) {
                    loginViewModel.saveLoginData(
                        PreferenceKeys.PREF_FACEBOOK_FRD_PERMISSION,
                        true
                    )
                    redirectToLoading()
                }

                override fun onSuccess(result: LoginResult) {
                    loginViewModel.saveLoginData(
                        PreferenceKeys.PREF_FACEBOOK_FRD_PERMISSION,
                        false
                    )
                    getFbFriendsList(result!!.accessToken)
                }
            })
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
                            PreferenceKeys.PREF_FACEBOOK_FRD_PERMISSION,
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

    private fun redirectToLoading() {
        nextActivity(LoadingActivity::class.java)
        finish()
    }
}