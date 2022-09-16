package com.swipefwd.ui.profile

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.swipefwd.R
import com.swipefwd.data.models.TravelLocationListModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityTravelBinding
import com.swipefwd.databinding.DialogChangeTravelLocationBinding
import com.swipefwd.databinding.DialogTravelRelocationBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppUtils
import com.swipefwd.utils.AppUtils.openFailNetworkDialog
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setShaderView
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.AppUtils.showSnackBar
import com.swipefwd.utils.datastore.PreferenceKeys
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class TravelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTravelBinding
    private val mActivity by lazy {
        this@TravelActivity
    }
    private val placesClient: PlacesClient by lazy {
        Places.createClient(this)
    }
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private var placeModel: TravelLocationListModel? = null
    private val token = AutocompleteSessionToken.newInstance()
    private var placesList = ArrayList<TravelLocationListModel>()
    private var editMode = true
    var isShow = false
    var share_travel_location = 0
    private val adapter by lazy {
        TravelLocationListAdapter(mActivity) {
            placeModel = it
            findLatLng(it)
            editMode = false
            binding.apply {
                txtTravelLocation.isCursorVisible = false
                txtTravelLocation.setText(
                    getString(R.string.city_country, it.city, it.country),
                    TextView.BufferType.NORMAL
                )
            }
        }
    }

    private fun initObserver() {
        profileViewModel.apply {
            travelData.observe(mActivity, {
                if (it.response == "success") {
                    if (isShow) {
                        openTravelRelocationDialog()
                    } else {
                        onBackPressed()
                    }
                }
            })
            travelError.observe(mActivity, {
                Log.e("TAG", "ERROR=== $it")
                mActivity.showSnackBar(binding.layoutMain, it.message.toString())
            })
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTravelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        Places.initialize(applicationContext, getString(R.string.google_maps_key))
        binding.apply {
            ivClose.setOnClickListener { onBackPressed() }
            txtTravelLocation.apply {
                requestFocus()
                setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        txtSubmit.performClick()
                        true
                    }
                    false
                }
                setOnClickListener {
                    editMode = true
                    isCursorVisible = true
                }
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        if (s.isNullOrBlank() || !editMode) {
                            cardList.visibility = View.GONE
                        } else {
                            cardList.visibility = View.VISIBLE
                            searchCity(s.toString())
                            setBackgroundResource(R.drawable.corner_blue_border_bg)
                            txtSubmit.apply {
                                isEnabled = true
                                isClickable = true
                            }
                        }
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (count == 0) {
                            setBackgroundResource(R.drawable.grey_border_bg)
                            cardList.visibility = View.GONE
                            txtSubmit.apply {
                                isEnabled = false
                                isClickable = false
                            }
                        }
                    }
                })
            }
            txtSubmit.setOnClickListener {
                profileViewModel.savePreference(
                    PreferenceKeys.PREF_TRAVEL_LOCATION,
                    Gson().toJson(placeModel)
                )
                openTravelChangeDialog()
            }
        }
        initObserver()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val location = profileViewModel.getTravelLocation.firstOrNull() ?: ""
            binding.apply {
                if (location.isNotEmpty()) {
                    placeModel = Gson().fromJson<Any>(
                        location,
                        TravelLocationListModel::class.java
                    ) as TravelLocationListModel
                    if (placeModel != null) {
                        placeModel?.let { placeModel ->
                            editMode = false
                            if (placeModel.city.isNotEmpty() && placeModel.country.isNotEmpty()) {
                                txtTravelLocation.apply {
                                    setText(
                                        getString(
                                            R.string.city_country,
                                            placeModel.city,
                                            placeModel.country
                                        )
                                    )
                                    setBackgroundResource(R.drawable.corner_blue_border_bg)
                                }
                                txtSubmit.apply {
                                    isEnabled = true
                                    isClickable = true
                                }
                            } else {
                                txtTravelLocation.apply {
                                    setText(
                                        ""
                                    )
                                    setBackgroundResource(R.drawable.grey_border_bg)
                                }
                                txtSubmit.apply {
                                    isEnabled = false
                                    isClickable = false
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // fetching city from google places sdk
    private fun searchCity(query: String) {
        placesList.clear()
        val request = FindAutocompletePredictionsRequest.builder()
            .setSessionToken(token)
            .setQuery(query)
            .setTypeFilter(TypeFilter.CITIES)
            .build()

        placesClient.findAutocompletePredictions(request).addOnSuccessListener { response ->
            response.autocompletePredictions.onEach { place ->
                placesList.add(
                    TravelLocationListModel(
                        city = place.getPrimaryText(null).toString(),
                        country = place.getSecondaryText(null).toString(),
                        placeId = place.placeId
                    )
                )
            }
            adapter.addAll(placesList)
            binding.rvLocation.adapter = adapter
        }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
            }
    }

    private fun findLatLng(place: TravelLocationListModel) {
        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.builder(place.placeId, placeFields)
            .build()
        placesClient.fetchPlace(request).addOnSuccessListener { response ->
            val places = response.place
            place.lat = places.latLng!!.latitude
            place.lng = places.latLng!!.longitude
        }.addOnFailureListener { exception ->
            if (exception is ApiException) {
                exception.printStackTrace()
            }
        }
    }

    private fun updateTravelLocation(isShow: Boolean,share_travel_location :Int) {
        val city = getString(R.string.city_country, placeModel?.city, placeModel?.country)
        /*  val jsonObject = JsonObject().apply {
              addProperty("latitude", DecimalFormat("###.######").format(placeModel?.lat).toDouble())
              addProperty("longitude", DecimalFormat("###.######").format(placeModel?.lng).toDouble())
              addProperty("share_with_connector", isShow)
              addProperty("city", city)
          }*/
        val jsonObject = JsonObject().apply {
            addProperty(
                "travel_latitude",
                DecimalFormat("###.######").format(placeModel?.lat).toDouble()
            )
            addProperty(
                "travel_longitude",
                DecimalFormat("###.######").format(placeModel?.lng).toDouble()
            )
            //  addProperty("share_with_connector", isShow)
            addProperty("travel_location_name", city)
            addProperty("share_travel_location", share_travel_location)
        }
        when {
            !AppUtils.isNetworkAvailable(mActivity) -> {
                openFailNetworkDialog() { updateTravelLocation(isShow,share_travel_location) }
            }
            else -> {
                lifecycleScope.launch {
                    /* profileViewModel.getAuthToken.firstOrNull()?.let { authToken ->
                         profileViewModel.updateTravelLocation(
                             "Bearer $authToken", jsonObject
                         )
                     }*/

                    profileViewModel.travelLocationApi(jsonObject)
                }
            }
        }
    }

    private fun openTravelChangeDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        val binding = DialogChangeTravelLocationBinding.inflate(layoutInflater)
        dialog.apply {
            isShow = false
            share_travel_location = 0
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                val ss = SpannableString(getString(R.string.enjoy_city_text_1, placeModel?.city))
                ss.setSpan(UnderlineSpan(), 21, 24, 0)
                txtMessage1.apply {
                    visibility = View.VISIBLE
                    text = ss
                }
                txtGotIt.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        updateTravelLocation(isShow,share_travel_location)
                    }
                }
                toggleTravelLocation.setOnCheckedChangeListener { _, isChecked ->
                    isShow = isChecked
                    if (isShow)
                    {
                        share_travel_location = 1
                    }
                    else{
                        share_travel_location = 0
                    }
                }
                txtEnjoy.text = getString(R.string.enjoy_city, placeModel?.city)
                txtMessage.text = getString(R.string.enjoy_city_text, placeModel?.city)
                mActivity.setShaderView(
                    shareLocation,
                    R.color.blue_gradient_2,
                    R.color.blue_gradient_3
                )
            }
            try {
                setZoomDialogWindowAttributes()
                show()
                binding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openTravelRelocationDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)
        val binding = DialogTravelRelocationBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                val ss =
                    SpannableString(getString(R.string.travel_relocation_message, placeModel?.city))
                ss.setSpan(UnderlineSpan(), 41, 45, 0)
                txtMessage2.text = ss
                view.visibility = View.INVISIBLE
                btnGotIt.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        dismiss()
                        this@TravelActivity.onBackPressed()
                    }
                }
            }
            try {
                setZoomDialogWindowAttributes()
                show()
                binding.imgDialogGradient.setDialogFadeInAnimation()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}