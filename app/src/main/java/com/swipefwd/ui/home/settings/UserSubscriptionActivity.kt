package com.swipefwd.ui.home.settings

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.android.billingclient.api.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.JsonObject
import com.swipefwd.R
import com.swipefwd.databinding.*
import com.swipefwd.data.models.AllSubPlansModel
import com.swipefwd.data.models.SettingSubscriptionModel
import com.swipefwd.data.models.SubscriptionPlansModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.home.message.SubscriptionPlansListAdapter
import com.swipefwd.ui.home.message.SubscriptionPlansPagerAdapter
import com.swipefwd.ui.main.WebViewActivity
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.createPlaceholderImage
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class UserSubscriptionActivity : AppCompatActivity(), PurchasesUpdatedListener {

    private lateinit var binding: ActivityUserSubscriptionBinding
    private val mActivity by lazy {
        this@UserSubscriptionActivity
    }
    private val settingsViewModel: SettingsViewModel by viewModels {
        viewModelFactory { SettingsViewModel(mActivity, AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(mActivity)
    }
    private var mDetailedList = ArrayList<SettingSubscriptionModel.Plan?>()
    private var currentPosition = 0
    private var image = ""
    private var fName = ""
    private var orderId = ""
    private var productId = ""
    private lateinit var billingClient: BillingClient
    private var mAllSubList = ArrayList<AllSubPlansModel>()
    private val subIds = arrayListOf(
        "fwdpremmon",
        "fwdelimon",
        "3monthpremier",
        "3monthelite",
        "6monthpremier",
        "6monthelite"
    )
    private val boostersIds = arrayListOf(
        "rematchonly",
        "extendtimeonly",
        "superlikesonly",
        "unlocksonly",
        "recallonly",
        "locationchangeonly"
    )
    private lateinit var subscriptionsBinding: DialogSubscriptionPlansBinding
    private var dataList: List<SkuDetails>? = arrayListOf()
    private var boostersList: ArrayList<SkuDetails?> = arrayListOf()
    private var planModel: SettingSubscriptionModel.Plan? = null
    private val adapter by lazy {
        SubscriptionPlansPagerAdapter(mActivity)
    }
    private val plansAdapter by lazy {
        SubscriptionPlansListAdapter(mActivity) {
            subscriptionsBinding.apply {
                isBooster = false
                layoutPlan.background =
                    ContextCompat.getDrawable(mActivity, R.drawable.grey_border_bg)
                txtPlanMonth.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPagerDesc))
                txtPlanValue.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPagerDesc))
                txtPlanMonthOffer.setTextColor(
                    ContextCompat.getColor(
                        mActivity,
                        R.color.colorPagerDesc
                    )
                )
            }
        }
    }
    private var exDate = String()
    private var isBooster = false
    private var boosterModel: SkuDetails? = null
    private var pagerPosition = 0
    private val outFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    }
    private var subscriptionDuration = "P1M"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSubscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            setUpBillingClient()
            btnClose.setOnClickListener {
                onBackPressed()
            }
            btnCancelSub.setOnClickListener {
                openCancelSubscriptionDialog()
            }
            lifecycleScope.launch {
                image = settingsViewModel.getProfileImage.firstOrNull() ?: ""
                fName = settingsViewModel.getFirstName.firstOrNull() ?: ""
                if (fName.isNotEmpty()) {
                    val textDrawable = mActivity.createPlaceholderImage(
                        fName,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )

                    Glide.with(mActivity)
                        .load(image).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable)
                        .into(imgUser)
                }
            }
            initObservers()
        }
    }

    private fun setUpBillingClient() {
        billingClient =
            BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build()
        billingClient.startConnection(object : BillingClientStateListener {

            override fun onBillingServiceDisconnected() {
                billingClient.startConnection(this)
            }

            override fun onBillingSetupFinished(p0: BillingResult) {
                if (p0.responseCode == BillingClient.BillingResponseCode.OK) {
                    Log.e("onBillingSetupFinished", p0.responseCode.toString())
                    getProductSkus()
                }
            }
        })
    }

    private fun getProductSkus() {
        if (billingClient.isReady) {
            val params = SkuDetailsParams
                .newBuilder()
                .setSkusList(subIds)
                .setType(BillingClient.SkuType.SUBS)
                .build()
            billingClient.querySkuDetailsAsync(params) { responseCode, skuDetailsList ->
                if (responseCode.responseCode == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                    //SkuDetails: {"productId":"3monthelite","type":"subs","title":"Elite (com.swipefwd (unreviewed))",
                    // "name":"Elite","price":"₹5,517.54","price_amount_micros":5517539436,
                    // "price_currency_code":"INR","description":"3 month","subscriptionPeriod":"P3M",
                    // "skuDetailsToken":"AEuhp4J5M6apAZaXWQbILyA6wFm5P7ciTX6TR0eCMgUvodlgpK1SLKKkO6DCY_jHOfU="}
                    Log.d("Shop", "skuDetailsList:- $skuDetailsList")
                    if (!skuDetailsList.isNullOrEmpty()) {
                        mAllSubList.clear()
                        skuDetailsList.onEach {
                            mAllSubList.add(
                                AllSubPlansModel(
                                    productId = it.sku,
                                    title = it.title,
                                    description = it.description,
                                    currency = it.priceCurrencyCode,
                                    priceValue = it.price,
                                    subscriptionPeriod = it.subscriptionPeriod
                                )
                            )
                        }
                        dataList = skuDetailsList.toList()
                            .asReversed() //reversed first as Premier then Elite
                        Log.d("Shop", "mAllSubList:- $mAllSubList")
                        Log.d("Shop", "dataList:- $dataList")
                    }
                }
            }
            //for boosters
            val boosterParams = SkuDetailsParams
                .newBuilder()
                .setSkusList(boostersIds)
                .setType(BillingClient.SkuType.INAPP)
                .build()
            billingClient.querySkuDetailsAsync(boosterParams) { responseCode, skuDetailsList ->
                if (responseCode.responseCode == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                    Log.d("Shop", "BoosterSkuDetailsList:- $skuDetailsList")
                    if (!skuDetailsList.isNullOrEmpty()) {
                        val tempList = skuDetailsList.toList()
                        boostersIds.forEach { name ->
                            tempList.find {
                                it.sku == name
                            }?.let {
                                boostersList.add(it)
                            }
                        }
                        boostersList.apply {
                            add(2, null)
                            add(4, null)
                            removeAt(4)
                            Log.d("Shop", "boostersList:- $this")
                            removeAt(4)
                        }
                        Log.d("Shop", "boostersList:- $boostersList")
                    }
                    getUserPlansList()
                }
            }
        }
    }

    /*override fun onProductPurchased(productId: String, details: TransactionDetails?) {
        Log.d("Shop", "onProductPurchased")
        Log.d("Shop", productId)
        Log.d("Shop", details.toString())
        Log.e("TAG", "PRODUCT PURCHASED")
        val info = details?.purchaseInfo!!
        val data = info.purchaseData!!
        orderId = data.orderId
        if (isBooster) {
            val dt = bp.getPurchaseListingDetails(productId)!!
            getExpiryDate(data.purchaseTime, dt.subscriptionPeriod)
            boosterPurchase()
        } else {
            val dt = bp.getSubscriptionListingDetails(productId)!!
            getExpiryDate(data.purchaseTime, dt.subscriptionPeriod)
            planPurchase()
        }
    }
    override fun onBillingError(errorCode: Int, error: Throwable?) {
        Log.d("Shop", "onBillingError")
        Log.d("Shop", errorCode.toString())
        Log.d("Shop", error?.message.toString())
        Toast.makeText(
            this@UserSubscriptionActivity,
            resources.getString(R.string.purchase_fail),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onBillingInitialized() {
    }*/

    override fun onDestroy() {
        if (this::billingClient.isInitialized) {
            billingClient.endConnection()
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun setPagerData(list: List<SettingSubscriptionModel.Plan?>) {
        val featureAdapter = SubscriptionPagerAdapter(mActivity, listener = { model ->
            //do upgrade subscription task
            planModel = model
            subscriptionDuration = model?.planSkuSubscriptionPeriod ?: ""
            openSubscriptionDialog()
        }).apply {
            addAll(list)
        }
        binding.apply {
            pagerPackType.apply {
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3
                val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
                val offsetPx = resources.getDimensionPixelOffset(R.dimen.offset)
                setPageTransformer { page, position ->
                    val viewPager = page.parent.parent as ViewPager2
                    val offset = position * -(2 * offsetPx + pageMarginPx)
                    if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
                        if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                            page.translationX = -offset
                        } else {
                            page.translationX = offset
                        }
                    } else {
                        page.translationY = offset
                    }
                }
                adapter = featureAdapter
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        currentPosition = position
                        setListData(position)
                        when (position) {
                            0 -> {
                                // txtType.text = list[0]?.planType
                                txtDate.text = "(current subscription)"
                                btnCancelSub.visibility = View.GONE
                            }
                            1 -> {
                                // txtType.text = list[1]?.planType
                                //txtDate.text = "(ends 6/27/21)"
                                txtDate.text = "(current subscription)"
                                btnCancelSub.visibility = View.VISIBLE
                            }
                            2 -> {
                                // txtType.text = list[1]?.planType
                                //txtDate.text = "(ends 6/27/21)"
                                txtDate.text = "(current subscription)"
                                btnCancelSub.visibility = View.VISIBLE
                            }
                        }
                    }
                })
            }
        }
    }

    private fun setListData(position: Int) {
        binding.apply {
            rvFeatures.adapter = SubscriptionPlansAdapter(mActivity, position).apply {
                val tripleList: ArrayList<Triple<String, String, String>> = arrayListOf()
                if (position < mDetailedList.size - 1) {
                    mDetailedList[position]?.list?.onEach { pair ->
                        mDetailedList[position + 1]?.list?.find { nextPair ->
                            pair.first == nextPair.first
                        }?.let { finalPair ->
                            tripleList.add(
                                Triple(
                                    pair.first,
                                    pair.second,
                                    finalPair.second
                                )
                            )
                        }
                    }
                } else {
                    mDetailedList[position]?.list?.onEach { pair ->
                        mDetailedList[position - 1]?.list?.find { nextPair ->
                            pair.first == nextPair.first
                        }?.let { finalPair ->
                            tripleList.add(
                                Triple(
                                    pair.first,
                                    finalPair.second,
                                    pair.second
                                )
                            )
                        }
                    }
                }
                Log.d("TAG:", "tripleList:  $tripleList")
                this.addAll(tripleList)
            }
            when (position) {
                0 -> {
                    txFreemium.visibility = View.VISIBLE
                    txtPremier.visibility = View.VISIBLE
                    txtElite.visibility = View.GONE
                }
                1, 2 -> {
                    txFreemium.visibility = View.GONE
                    txtPremier.visibility = View.VISIBLE
                    txtElite.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun openCancelSubscriptionDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val subBinding = DialogCancelSubscriptionBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(subBinding.root)
            subBinding.apply {
                //setting text according to account in Dialog
                when (currentPosition) {
                    1 -> {
                        txtCancelTitle.text =
                            getString(R.string.cancel_sub_title, getString(R.string.premier))
                        txtCancelDesc.text =
                            getString(R.string.cancel_sub_message_1, getString(R.string.premier))
                        txtKeepAccount.text =
                            getString(R.string.keep_sub, getString(R.string.premier))
                    }
                    2 -> {
                        txtCancelTitle.text =
                            getString(R.string.cancel_sub_title, getString(R.string.elite))
                        txtCancelDesc.text =
                            getString(R.string.cancel_sub_message_1, getString(R.string.elite))
                        txtKeepAccount.text =
                            getString(R.string.keep_sub, getString(R.string.elite))
                    }
                }
                txtKeepAccount.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dialog.dismiss()
                        onBackPressed()
                    }
                }
                txtConfirm.setOnClickListener {
                    imgDialogGradient.setDialogFadeOutAnimation {
                        dialog.dismiss()
                        openCancelConfirmedDialog()
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

    private fun openCancelConfirmedDialog() {
        val dialog = Dialog(this, R.style.SlideDialogTheme)
        val cancelSubBinding = DialogCanceledSubscriptionBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(cancelSubBinding.root)
            cancelSubBinding.apply {
                if (fName.isNotEmpty()) {
                    val textDrawable = mActivity.createPlaceholderImage(
                        fName,
                        100,
                        R.color.blue_gradient_2,
                        R.color.blue_gradient_3
                    )

                    Glide.with(mActivity)
                        .load(image).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(textDrawable)
                        .into(imgUser)
                }
                //setting text according to account in Dialog
                /*when (currentPosition) {
                    1 -> {
                        txtCancel.text =
                            getString(R.string.sub_canceled, getString(R.string.premier))
                        txtDaterMessage.text =
                            getString(R.string.sub_last_date_message, "6/10/2021")
                    }
                    2 -> {
                        txtCancel.text = getString(R.string.sub_canceled, getString(R.string.elite))
                        txtDaterMessage.text =
                            getString(R.string.sub_last_date_message, "8/12/2021")
                    }
                }*/
                btnGotIt.setOnClickListener {
                    dialog.dismiss()
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/account/subscriptions")
                    )
                    startActivity(browserIntent)
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

    private fun getUserPlansList() {
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){getUserPlansList()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getUserPlansList()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        settingsViewModel.getUserPlansList(
                            "Bearer $authToken"
                        )
                    }
                }
            }
        }
    }

    private fun initObservers() {
        settingsViewModel.apply {
            showLoading.observe(mActivity, {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            })
            errorMessage.observe(mActivity, {
                Log.e("TAG", "ERROR === $it")
            })
            getPlansError.observe(mActivity, {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.response.toString())
            })
            settingsError.observe(mActivity, {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.response.toString())
            })
            getPlansData.observe(mActivity, {
                Log.e("TAG", "DATA=== ${it.plan}")
                val planList = it.plan
                planList?.let { list ->
                    if (list.isNotEmpty()) {
                        //binding google store data to model
                        list.sortedBy { planModel ->
                            planModel?.id
                        }.let { mList ->
                            mList.onEachIndexed { i, planModel ->
                                if (i > 0) {
                                    val j = i - 1
                                    planModel?.planSkuDesc = dataList?.get(j)?.description
                                    planModel?.planSkuProductId = dataList?.get(j)?.sku
                                    planModel?.planSkuTitle = dataList?.get(j)?.title
                                    planModel?.planSkuPrice = dataList?.get(j)?.price
                                    planModel?.planSkuCurrencyCode =
                                        dataList?.get(j)?.priceCurrencyCode
                                    planModel?.planSkuSubscriptionPeriod =
                                        dataList?.get(j)?.subscriptionPeriod
                                } else {
                                    val j = i + 1
                                    planModel?.planSkuDesc = dataList?.get(j)?.description
                                    planModel?.planSkuTitle = dataList?.get(j)?.title
                                }
                            }
                            val allDetailsList: List<SettingSubscriptionModel.Plan?> =
                                mList.onEach { plan ->
                                    plan?.list =
                                        ArrayList<Pair<String, String>>().also { subPlans ->
                                            subPlans.add(
                                                Pair(
                                                    plan?.display_connection!!,
                                                    if (plan.connectionNumber!! == 0) {
                                                        plan.connectionString!!
                                                    } else {
                                                        "x${plan.connectionNumber} ${plan.connectionString!!}"

                                                    }
                                                )
                                            )
                                            subPlans.add(
                                                Pair(
                                                    plan.display_extendtime!!,
                                                    if (plan.extendtimeNumber == 0) {
                                                        plan.extendtimeString!!
                                                    } else {
                                                        "x${plan.extendtimeNumber} ${plan.extendtimeString}"
                                                    }
                                                )
                                            )

                                            subPlans.add(
                                                Pair(
                                                    plan.display_fwd!!, if (plan.fwdNumber == 0) {
                                                        plan.fwdString!!
                                                    } else {
                                                        "x${plan.fwdNumber} ${plan.fwdString}"
                                                    }
                                                )
                                            )
                                            subPlans.add(
                                                Pair(
                                                    plan.display_fire!!, if (plan.fireNumber == 0) {
                                                        plan.fireString!!
                                                    } else {
                                                        "x${plan.fireNumber} ${plan.fireString}"
                                                    }
                                                )
                                            )
                                            subPlans.add(
                                                Pair(
                                                    plan.display_travel_location!!,
                                                    if (plan.travelLocationNumber == 0) {
                                                        plan.travelLocationString!!
                                                    } else {
                                                        "x${plan.travelLocationNumber} ${plan.travelLocationString}"
                                                    }
                                                )
                                            )
                                            subPlans.add(
                                                Pair(
                                                    plan.display_profile!!,
                                                    if (plan.profileNumber == 0) {
                                                        plan.profileString!!
                                                    } else {
                                                        "x${plan.profileNumber} ${plan.profileString}"
                                                    }
                                                )
                                            )
                                            subPlans.add(
                                                Pair(
                                                    plan.display_profile_recall!!,
                                                    if (plan.profileRecallNumber == 0) {
                                                        plan.profileRecallString!!
                                                    } else {
                                                        "x${plan.profileRecallNumber} ${plan.profileRecallString}"
                                                    }
                                                )
                                            )
                                            subPlans.add(
                                                Pair(
                                                    plan.display_profile_rematch!!,
                                                    if (plan.profileRematchNumber == 0) {
                                                        plan.profileRematchString!!
                                                    } else {
                                                        "x${plan.profileRematchNumber} ${plan.profileRematchString}"
                                                    }
                                                )
                                            )
                                            subPlans.add(
                                                Pair(
                                                    plan.display_profile_superlikes!!,
                                                    if (plan.profileSuperlikesNumber == 0) {
                                                        plan.profileSuperlikesString!!
                                                    } else {
                                                        "x${plan.profileSuperlikesNumber} ${plan.profileSuperlikesString}"
                                                    }
                                                )
                                            )
                                        }
                                }
                            Log.d("TAG:", "tempList:  $allDetailsList")
                            mDetailedList = allDetailsList.toCollection(arrayListOf())
                            setPagerData(allDetailsList)
                        }
                    }
                }
            })
            planPurchaseData.observe(mActivity, {
                if (it.response == "success") {
                    startActivity(
                        Intent(
                            this@UserSubscriptionActivity,
                            PurchaseSuccessActivity::class.java
                        ).putExtra("planType", planModel?.planType)
                    )
                    finish()
                    overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                }
            })
        }
    }

    private fun openSubscriptionDialog() {
        val dialog = Dialog(mActivity, R.style.SlideDialogTheme)
        subscriptionsBinding = DialogSubscriptionPlansBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCanceledOnTouchOutside(true)
            setContentView(subscriptionsBinding.root)
            subscriptionsBinding.setSubscriptionData(this)
            setOnCancelListener {
                dialog.dismiss()
            }
            subscriptionsBinding.apply {
                txtText.text = getString(R.string.subscription, planModel?.planType)
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                subscriptionsBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun DialogSubscriptionPlansBinding.setSubscriptionData(
        dialog: Dialog
    ) {
        plansAdapter.addAll(mAllSubList)
        rvPlansList.adapter = plansAdapter
        plansAdapter.setValues(planModel!!)
        when (planModel?.planType) {
            getString(R.string.elite) -> {
                layoutIndicatorGreen.visibility = View.VISIBLE
                layoutIndicator.visibility = View.GONE
                adapter.addAll(
                    arrayListOf(
                        SubscriptionPlansModel(
                            R.drawable.pager1_black,
                            getString(R.string.pager_1)
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager2_black,
                            getString(R.string.pager_2)
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager3_black,
                            getString(R.string.pager_3, "50")
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager4_black,
                            getString(R.string.pager_4, "5")
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager5_black,
                            getString(R.string.pager_5)
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager6_black,
                            getString(R.string.pager_6)
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager7_black,
                            getString(R.string.pager_7)
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager8_black,
                            getString(R.string.elite_pager_8)
                        )
                    )
                )
                mActivity.setShaderView(
                    txtText,
                    R.color.mint_gradient1,
                    R.color.mint_gradient2
                )
            }
            else -> {
                layoutIndicatorGreen.visibility = View.GONE
                layoutIndicator.visibility = View.VISIBLE
                adapter.addAll(
                    arrayListOf(
                        SubscriptionPlansModel(
                            R.drawable.pager1_white,
                            getString(R.string.pager_1)
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager2_white,
                            getString(R.string.pager_2)
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager3_white,
                            getString(R.string.pager_3, "25")
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager4_white,
                            getString(R.string.pager_4, "2")
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager5_white,
                            getString(R.string.pager_5)
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager6_white,
                            getString(R.string.pager_6)
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager7_white,
                            getString(R.string.pager_7)
                        ),
                        SubscriptionPlansModel(
                            R.drawable.pager8_white,
                            getString(R.string.pager_8)
                        )

                    )
                )
                mActivity.setShaderView(
                    txtText,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )
            }
        }
        planPager.adapter = adapter
        adapter.setValues(planModel!!)
        mActivity.setShaderView(
            txtLink,
            R.color.blue_gradient_2,
            R.color.blue_gradient_3
        )

        layoutIndicator.setViewPager(planPager)
        layoutIndicatorGreen.setViewPager(planPager)
        layoutPlan.setOnClickListener {
            plansAdapter.apply {
                isBooster = true
                selectedPosition = -1
                notifyDataSetChanged()
            }
            when (planModel?.planType) {
                getString(R.string.elite) -> {
                    txtPlanMonthOffer.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPagerDesc,
                            theme
                        )
                    )
                    txtPlanMonth.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPagerDesc,
                            theme
                        )
                    )
                    txtPlanValue.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.colorPagerDesc,
                            theme
                        )
                    )
                    layoutPlan.background =
                        ContextCompat.getDrawable(
                            mActivity,
                            R.drawable.corner_mint_bottom_gradient_bg
                        )

                }
                else -> {
                    txtPlanMonthOffer.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.white,
                            theme
                        )
                    )
                    txtPlanMonth.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.white,
                            theme
                        )
                    )
                    txtPlanValue.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.white,
                            theme
                        )
                    )
                    layoutPlan.background =
                        ContextCompat.getDrawable(mActivity, R.drawable.profile_progress_bg)
                }
            }
        }

        planPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (planModel?.planType) {
                    getString(R.string.elite) -> {
                        planPager.background = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.top_mint_corner_bottom_gradient_bg,
                            theme
                        )
                    }
                    else -> {
                        planPager.background = ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.top_blue_corner_gradient_bg,
                            theme
                        )
                    }
                }
                pagerPosition = position
                boosterModel = boostersList[position]
                if (!boosterModel?.title.isNullOrEmpty()) {
                    txtPlanType.text = boosterModel?.title!!.split("(")[0]
                }
                txtPlanMonth.text = boosterModel?.description
                txtPlanValue.text = boosterModel?.price
                when (position) {
                    2 -> {
                        txtPlanType.visibility = View.GONE
                        layoutPlan.visibility = View.GONE
                    }
                    0, 1, 3, 4, 5, 6, 7 -> {
                        txtPlanType.visibility = View.VISIBLE
                        layoutPlan.visibility = View.VISIBLE
                    }
                }
            }
        })
        txtLink.setOnClickListener {
            startActivity(
                Intent(
                    mActivity,
                    WebViewActivity::class.java
                ).putExtra("url", AppConstants.SWIPE_FWD_URL)
            )
            overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
        }

        txtGetDiamond.setOnClickListener {
            dialog.dismiss()
            if (isBooster) {
                //bp.purchase(mActivity, boosterModel?.productId)
                hasPurchaseOnGoogle()
            } else {
                if (productId != "") {
                    //check if already purchased plan & selected plan is not same then apply for upgrade plan
                    if (productId != planModel?.planSkuProductId) {
                        //if user wants to upgrade plan
                        //  bp.updateSubscription(mActivity, productId, planModel?.planSkuProductId)
                    } else {
                        Toast.makeText(mActivity, "Already Purchased", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    // bp.subscribe(mActivity, planModel?.planSkuProductId)
                    hasPurchaseOnGoogle()
                }
            }
        }
    }

    private fun openDiamondUpgradeDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        val getDiamondBinding = DialogDiamondUpgradedBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(getDiamondBinding.root)
            getDiamondBinding.btnGreat.setOnClickListener {
                getDiamondBinding.imgDialogGradient.setDialogFadeOutAnimation {
                    dismiss()
                }
            }
            setZoomDialogWindowAttributes()
            show()
            getDiamondBinding.imgDialogGradient.setDialogFadeInAnimation()
        }
    }

    private fun planPurchase() {
        val jsonObject = JsonObject().apply {
            binding.apply {
                addProperty("ref_plan", planModel?.id)
                addProperty("device", AppConstants.DEVICE_TYPE)
                addProperty("end_date", exDate)
                addProperty("transaction_number", orderId)
            }
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){planPurchase()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    planPurchase()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        settingsViewModel.planPurchase(
                            "Bearer $authToken", jsonObject
                        )
                    }
                }
            }
        }
    }

    private fun boosterPurchase() {
        val jsonObject = JsonObject().apply {
            val boosterType = when (pagerPosition) {
                0 -> {
                    AppConstants.BOOSTER_REMATCH
                }
                1 -> {
                    AppConstants.BOOSTER_EXTEND_TIME
                }
                3 -> {
                    AppConstants.BOOSTER_SUPERLIKES
                }
                5 -> {
                    AppConstants.BOOSTER_UNLOCKPROFILE
                }
                6 -> {
                    AppConstants.BOOSTER_RECALL
                }
                7 -> {
                    AppConstants.BOOSTER_LOCATION
                }
                else -> ""
            }
            binding.apply {
                addProperty("booster_type", boosterType)
                addProperty("device", AppConstants.DEVICE_TYPE)
                addProperty("plan_quota", boosterModel?.description?.take(1)?.toInt())
                addProperty("transaction_number", orderId)
            }
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog(){boosterPurchase()}
//                AppUtils.showMessageOK(
//                    mActivity,
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    boosterPurchase()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    settingsViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                        settingsViewModel.boosterPurchase(
                            "Bearer $authToken", jsonObject
                        )
                    }
                }
            }
        }
    }

    override fun onPurchasesUpdated(p0: BillingResult, p1: MutableList<Purchase>?) {
        Log.d("TAG", "onPurchasesUpdated, response code${p0.responseCode}")
        when (p0.responseCode) {
            BillingClient.BillingResponseCode.OK -> {
                if (p1 == null) {
                    Log.e("TAG", "Purchase update: No purchases")
                    handlePurchases(null)
                } else {
                    Log.e("purchasesList", p1.toString())
                    handlePurchases(p1)
                }
            }
            BillingClient.BillingResponseCode.USER_CANCELED -> Log.e(
                "TAG",
                "User canceled the purchase"
            )
            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                hasPurchaseOnGoogle()
                Log.e("TAG", "The user already owns this item")
            }
            BillingClient.BillingResponseCode.DEVELOPER_ERROR -> Log.e(
                "TAG", "Developer error means that Google Play does not recognize the " +
                        "configuration. If you are just getting started, make sure you have " +
                        "configured the application correctly in the Google Play Console. " +
                        "The SKU product ID must match and the APK you are using must be " +
                        "signed with release keys."
            )
            else -> Log.e(
                "TAG",
                "See error code in BillingClient.BillingResponse: ${p0.responseCode}"
            )
        }
    }

    private fun hasPurchaseOnGoogle() {
        val type = if (isBooster) {
            BillingClient.SkuType.INAPP
        } else {
            BillingClient.SkuType.SUBS
        }
        var list: ArrayList<Purchase>
        billingClient
            .queryPurchasesAsync(
                type
            ) { p0, p1 ->
                p0.responseCode
                if (p1.isNotEmpty()) {
                    list = p1 as ArrayList<Purchase>
                    if (list.isNullOrEmpty()) {
                        setProductSku()
                    } else {
                        getProductSkus()
                    }
                } else {
                    setProductSku()
                }
            }
    }

    private fun setProductSku() {
        val params = SkuDetailsParams.newBuilder()
        val sku: String
        val type: String
        if (isBooster) {
            sku = boosterModel?.sku ?: ""
            type = BillingClient.SkuType.INAPP
        } else {
            sku = planModel?.planSkuProductId ?: ""
            type = BillingClient.SkuType.SUBS
        }
        params.setSkusList(arrayListOf(sku))
        params.setType(type)
        billingClient.querySkuDetailsAsync(
            params.build()
        ) { _, p1 ->
            val billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(p1!![0])
                .build()
            billingClient.launchBillingFlow(mActivity, billingFlowParams)
        }
    }

    private fun handlePurchases(purchasesList: List<Purchase>?) {
        if (purchasesList != null) {
            Log.e("subscriptionList", purchasesList.toString())
            val purchase = purchasesList[0]
            Log.e("purchase", purchase.toString())
            val receipt = purchase.originalJson
            Log.e("receipt", receipt)
            val jsonObject = JSONObject(receipt)
            val purchaseTime = jsonObject.getLong("purchaseTime")
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = purchaseTime
            val subDate = outFormat.format(calendar.time)
            exDate = AppUtils.getExpiryDate(
                outFormat.parse(subDate) ?: Date(),
                subscriptionDuration
            )
            orderId = (jsonObject.getString("orderId") ?: "")
            if (isBooster) {
                boosterPurchase()
            } else {
                planPurchase()
            }
        }
    }
}