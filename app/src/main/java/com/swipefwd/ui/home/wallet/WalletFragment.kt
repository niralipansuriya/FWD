package com.swipefwd.ui.home.wallet

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.mukesh.countrypicker.CountryPicker
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.JsonObject
import com.swipefwd.R
import com.swipefwd.databinding.*
import com.swipefwd.data.models.walletModels.GetOperatorByIsoModel
import com.swipefwd.data.models.walletModels.PlanListModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.ui.home.TabManagerActivity
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.dpToPx
import com.swipefwd.utils.AppUtils.setBottomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDivider
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.AppConstants
import com.swipefwd.utils.ProgressBarHandler
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!
    private val walletViewModel: WalletViewModel by viewModels {
        viewModelFactory { WalletViewModel(requireContext(), AppRepository()) }
    }
    private val progressBarHandler by lazy {
        ProgressBarHandler(requireActivity())
    }
    private var isoCode = ""
    private var isoList = ArrayList<GetOperatorByIsoModel.GetOperatorByIsoModelItem>()
    private var currentOperator = ""
    private var currentState = ""
    private var accessToken = ""
    private var mobileNumber = ""
    private var currentBalance = ""
    private var selectedPlanModel: PlanListModel? = null
    private var accountType = ""
    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleScope.launch {
                accountType = walletViewModel.getAccountType.firstOrNull() ?: ""
            }
            txtBalance.text = when (accountType) {
                AppUtils.AccountTypes.Matchmaker -> {
                    getString(R.string.cash_balance)
                }
                AppUtils.AccountTypes.Dater -> {
                    getString(R.string.coin_balance)
                }
                else -> {
                    getString(R.string.coin_balance)
                }
            }
            currentOperator =
                (context?.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager)?.networkOperatorName
                    ?: "unknown"
            currentBalance = txtBalanceValue.text.toString().trim()
            btnMobileRecharge.setOnClickListener {
                openMobileRechargeDialog()
            }
            btnSubscription.setOnClickListener {

            }
            txtBalance.setOnClickListener {
                //open bottom sheet
                try {
                    openHistoryDialog()
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
            getAccessToken()
            initObservers()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun openMobileRechargeDialog() {
        val dialog = Dialog(requireContext(), R.style.SlideDialogTheme)
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            val rechargeBinding = DialogMobileRechargeBinding.inflate(layoutInflater)
            setContentView(rechargeBinding.root)
            rechargeBinding.apply {
                lifecycleScope.launch {
                    mobileNumber = walletViewModel.getPhoneNumber.firstOrNull() ?: ""
                    txtMobileNumber.text = mobileNumber
                }
                val operatorList = arrayListOf<String>().apply {
                    isoList.onEach {
                        add(it.name ?: "")
                    }
                }
                val circleList = ArrayList<String>()
                val viewTypeFace =
                    ResourcesCompat.getFont(requireContext(), R.font.regular)
                pickerOperator.apply {
                    data = operatorList.sortedBy { it }
                    typeface = viewTypeFace
                    setSelectedItemTextSize(18.dpToPx())
                    selectedItemPosition = operatorList.indexOf(currentOperator)
                    setOnItemSelectedListener { position, data, _ ->
                        Log.e("TAG", "WHEEL ==== $data === $position")
                        currentOperator = data.toString()
                        edtOperator.apply {
                            setText(currentOperator)
                            background = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.corner_blue_border_bg
                            )
                            cardList.visibility = View.GONE
                        }
                        isoList.find {
                            it.name == currentOperator
                        }.let {
                            circleList.clear()
                            it?.geographicalRechargePlans?.let { geoPlanList ->
                                if (geoPlanList.isNotEmpty()) {
                                    geoPlanList.onEach { geoModel ->
                                        circleList.add(geoModel?.locationName ?: "")
                                    }
                                    pickerCircle.apply {
                                        Log.d("TAG", "SIZE:  ${circleList.size}")
                                        this.data = circleList.sortedBy { it }
                                        typeface = viewTypeFace
                                        setSelectedItemTextSize(18.dpToPx())
                                        selectedItemPosition = circleList.indexOf(currentState)
                                        setOnItemSelectedListener { position, data, _ ->
                                            Log.e("TAG", "CIRCLE ==== $data === $position")
                                            currentState = data.toString()
                                            edtCircle.apply {
                                                setText(currentState)
                                                background = ContextCompat.getDrawable(
                                                    requireContext(),
                                                    R.drawable.corner_blue_border_bg
                                                )
                                                cardCircleList.visibility = View.GONE
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                edtOperator.setOnClickListener {
                    edtCircle.apply {
                        setText("")
                        background = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.grey_border_bg
                        )
                    }
                    cardList.visibility = if (cardList.visibility == View.VISIBLE) {
                        View.GONE
                    } else {
                        View.VISIBLE
                    }
                }
                edtCircle.setOnClickListener {
                    cardCircleList.visibility = if (cardCircleList.visibility == View.VISIBLE) {
                        View.GONE
                    } else {
                        if (circleList.isNotEmpty()) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }
                    }
                }
                btnBundlePlan.setOnClickListener {
                    if (edtCircle.text.toString().trim().isNotEmpty() || edtOperator.text.toString()
                            .trim().isNotEmpty()
                    ) {
                        dismiss()
                        openMobileRechargePlanListDialog()
                    }

                }
                btnSelectAmount.setOnClickListener {
                    dismiss()
                    openSelectRechargeAmountDialog(false)
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                rechargeBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openHistoryDialog() {
        val dialog = BottomSheetDialog(
            requireContext(),
            R.style.SlideBottomSheetDialogTheme
        )
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            val walletBinding = DialogWalletTransactionBinding.inflate(layoutInflater)
            setContentView(walletBinding.root)
            setOnDismissListener {
                requireActivity().findViewById<AppCompatImageView>(R.id.imgDialogGradient).apply {
                    visibility = View.GONE
                }
            }
            behavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, state: Int) {
                    Log.d("TAG", "onStateChanged: $state")
                    when (state) {
                        BottomSheetBehavior.STATE_HIDDEN -> {
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                        }
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                        }
                        BottomSheetBehavior.STATE_DRAGGING -> {
                        }
                        BottomSheetBehavior.STATE_SETTLING -> {
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    Log.d("TAG", "Slide: $slideOffset")

                    if (activity is TabManagerActivity) {
                        requireActivity().findViewById<AppCompatImageView>(R.id.imgDialogGradient).alpha =
                            1 + slideOffset
                    }
                    Log.d("TAG", "Alpha: ${1 + slideOffset}")
                }
            })
            walletBinding.apply {
                rvHistory.apply {
                    setDivider()
                    adapter = WalletHistoryListAdapter(requireContext())
                }
            }
            try {
                show()
                if (activity is TabManagerActivity) {
                    requireActivity().findViewById<AppCompatImageView>(R.id.imgDialogGradient)
                        .apply {
                            visibility = View.VISIBLE
                            setDialogFadeInAnimation()
                        }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getAccessToken() {
        val apiRequest = JsonObject().apply {
            addProperty("client_id", AppConstants.RELOADLY_CLIENT_ID_SANDBOX)
            addProperty("client_secret", AppConstants.RELOADLY_CLIENT_SECRET_SANDBOX)
            addProperty("grant_type", AppConstants.RELOADLY_GRANT_TYPE)
            addProperty("audience", AppConstants.RELOADLY_AUDIENCE_SANDBOX)
        }
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog(){getAccessToken()}
//                AppUtils.showMessageOK(
//                    requireActivity(),
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getAccessToken()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    walletViewModel.getAccessToken(jsonObject = apiRequest)
                }
            }
        }
    }

    private fun getOperatorByISO() {
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog(){getAccessToken()}
//                AppUtils.showMessageOK(
//                    requireActivity(),
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getAccessToken()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    walletViewModel.getOperatorByIso(
                        "Bearer $accessToken",
                        isoCode,
                        true,
                        true,
                        true,
                        true,
                        true
                    )
                }
            }
        }
    }

    private fun initObservers() {
        walletViewModel.apply {
            showLoading.observe(requireActivity(), {
                if (it) {
                    progressBarHandler.show()
                } else {
                    progressBarHandler.dismiss()
                }
            })
            errorMessage.observe(requireActivity(), {
                Log.e("TAG", "ERROR === $it")
                requireActivity().showSnackBar(binding.layoutMain, it)
            })
            tokenData.observe(requireActivity(), {
                Log.e("TAG", "TOKEN_DATA === $it")
                if (!it.accessToken.isNullOrEmpty()) {
                    accessToken = it.accessToken
                    //call API for Country wise operator
                    val locale = resources.configuration?.locale
                    val country2 =
                        let { CountryPicker.Builder().with(requireActivity()).build() }
                    val country1 = locale?.let { country2?.getCountryByLocale(locale) }
                    if (country1 != null) {
                        isoCode = country1.code ?: ""//IN
                    }
                    getOperatorByISO()
                }
            })
            error.observe(requireActivity(), {
                Log.e("TAG", "ERROR === $it")
                requireActivity().showSnackBar(binding.layoutMain, it.message.toString())
            })
            isoData.observe(requireActivity(), {
                //after success bind operator
                Log.e("TAG", "ISO_DATA === $it")
                isoList.addAll(it)
            })
            isoError.observe(requireActivity(), {
                Log.e("TAG", "ISO_ERROR === $it")
                if (it != null) {
                    if (!it.message.isNullOrEmpty()) {
                        requireActivity().showSnackBar(
                            binding.layoutMain,
                            it.message.toString()
                        )
                    }
                }
            })
            rechargeData.observe(requireActivity(), {
                Log.e("TAG", "RECHARGE_DATA === $it")
            })
        }
    }

    private fun openMobileRechargePlanListDialog() {
        val dialog = Dialog(requireContext(), R.style.SlideDialogTheme)
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            val planListBinding = DialogMobileRechargePlanListBinding.inflate(layoutInflater)
            setContentView(planListBinding.root)
            planListBinding.apply {
                isoList.find {
                    it.name == currentOperator
                }.let {
                    it?.geographicalRechargePlans?.let { geoPlanList ->
                        if (geoPlanList.isNotEmpty()) {
                            geoPlanList.find { geoListModel ->
                                geoListModel?.locationName == currentState
                            }.let { geoModel ->
                                if (geoModel?.localFixedAmountsDescriptions != null) {
                                    val planPriceList =
                                        geoModel.localFixedAmountsDescriptions.keySet()
                                    Log.d("TAG:", "KEYS: $planPriceList")
                                    val planList = ArrayList<PlanListModel>()
                                    if (planPriceList.isNotEmpty()) {
                                        planPriceList?.onEachIndexed { _, price ->
                                            val planDesc =
                                                geoModel.localFixedAmountsDescriptions.get(price).asString
                                            //"Get Talktime of RS .4237.29. Validity : 0 Days. Talk time : 4237.29"
                                            val validity = planDesc.substringAfter("Validity :")
                                                .substringBefore(".")
                                            Log.d("TAG:", "VALIDITY: $validity")
                                            var talkTime = ""
                                            if (planDesc.split("Talk time :").size > 1) {
                                                talkTime = planDesc.split("Talk time :")[1]
                                                Log.d("TAG:", "TALKTIME: $talkTime")
                                            }
                                            planList.add(
                                                PlanListModel(
                                                    price = price, desc = planDesc,
                                                    talkTime = talkTime,
                                                    validity = validity,
                                                    data = "",
                                                    operatorId = it.operatorId ?: 0,
                                                    operatorName = currentOperator,
                                                    circleName = currentState
                                                )
                                            )
                                        }
                                    }
                                    rvPlanList.apply {
                                        adapter = PlanListAdapter { selectedPlan ->
                                            selectedPlanModel = selectedPlan
                                            dismiss()
                                            openRechargeSummaryDialog()
                                        }.apply {
                                            addAll(planList)
                                        }
                                        setDivider()
                                    }
                                }
                            }
                        }
                    }
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                planListBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openSelectRechargeAmountDialog(isApplyFwd: Boolean) {
        val dialog = Dialog(requireContext(), R.style.SlideDialogTheme)
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            val rechargeListBinding = DialogCustomRechargeAmountBinding.inflate(layoutInflater)
            setContentView(rechargeListBinding.root)
            rechargeListBinding.apply {
                if (isApplyFwd) {
                    txtTimeOut.text = getString(R.string.coin_balance)
                } else {
                    txtTimeOut.text = getString(R.string.select_custom_amount)
                }
                val amountList = arrayListOf<String>().apply {
                    addAll((10..100).map {
                        String.format(
                            "₹%02d",
                            it
                        )
                    })
                }
                wheelAmount.apply {
                    data = amountList.sortedBy { it }
                    setSelectedItemTextSize(18.dpToPx())
                    selectedItemPosition = amountList.indexOf(currentBalance)
                    setOnItemSelectedListener { position, data, _ ->
                        Log.e("TAG", "AMOUNT ==== $data === $position")
                        currentBalance = data.toString()
                    }
                }
                btnSubmit.setOnClickListener {
                    dismiss()
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                rechargeListBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openRechargeSummaryDialog() {
        val dialog = Dialog(requireContext(), R.style.SlideDialogTheme)
        dialogs.add(dialog)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            val summaryBinding = DialogRechargeSummaryBinding.inflate(layoutInflater)
            setContentView(summaryBinding.root)
            summaryBinding.apply {
                btnPurchase.setOnClickListener {
                    btnApplyBalance.visibility = View.GONE
                    layoutTotalAmount.visibility = View.VISIBLE
                    btnContinue.visibility = View.VISIBLE
                    btnPurchase.visibility = View.GONE
                }
                btnContinue.setOnClickListener {
                    dismiss()
                    makePayment(selectedPlanModel)
                }
                btnApplyBalance.setOnClickListener {
                    //open amount dialog
                    openSelectRechargeAmountDialog(true)
                }
            }
            try {
                setBottomDialogWindowAttributes()
                show()
                summaryBinding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun makePayment(selectedPlan: PlanListModel?) {
        val apiRequest = JsonObject().apply {
            addProperty("operatorId", selectedPlan?.operatorId)
            addProperty("amount", selectedPlan?.price)
            val recipientObject = JsonObject().apply {
                addProperty("countryCode", AppConstants.RELOADLY_CLIENT_ID_SANDBOX)
                addProperty("number", mobileNumber)
            }
            add("recipientPhone", recipientObject)
            addProperty("number", mobileNumber)
        }
        when {
            !AppUtils.isNetworkAvailable(requireContext()) -> {
                openFailNetworkDialog(){getAccessToken()}
//                AppUtils.showMessageOK(
//                    requireActivity(),
//                    getString(R.string.app_name),
//                    getString(R.string.common_retry),
//                    getString(R.string.no_internet)
//                ) { _, _ ->
//                    getAccessToken()
//                }
            }
            else -> {
                lifecycleScope.launch {
                    Log.d("TAG:", "JSON: $apiRequest")
                    //walletViewModel.makeRecharge("Bearer $accessToken", jsonObject = apiRequest)
                }
            }
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