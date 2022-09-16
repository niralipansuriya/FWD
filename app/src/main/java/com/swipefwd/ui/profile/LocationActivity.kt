package com.swipefwd.ui.profile

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.tasks.Task
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.swipefwd.R
import com.swipefwd.base.ResultState
import com.swipefwd.data.models.LocationModel
import com.swipefwd.data.source.AppRepository
import com.swipefwd.databinding.ActivityLocationBinding
import com.swipefwd.databinding.DialogHelpUsLocationBinding
import com.swipefwd.factory.viewModelFactory
import com.swipefwd.ui.updateuserprofile.UserProfileViewModel
import com.swipefwd.utils.AppUtils.setDialogFadeInAnimation
import com.swipefwd.utils.AppUtils.setDialogFadeOutAnimation
import com.swipefwd.utils.AppUtils.setZoomDialogWindowAttributes
import com.swipefwd.utils.datastore.PreferenceKeys
import java.util.*

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityLocationBinding
    private var googleMap: GoogleMap? = null
    private lateinit var myMapFragment: SupportMapFragment
    private val mActivity by lazy {
        this@LocationActivity
    }
    private val profileViewModel: UserProfileViewModel by viewModels {
        viewModelFactory { UserProfileViewModel(mActivity, AppRepository()) }
    }
    private val locationRequest: LocationRequest? by lazy { LocationRequest.create() }
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(mActivity)
    }


    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult?.locations?.forEach {
                it?.let { location ->
                    updateLocation(location)
                }
            }
        }
    }
    private var isBackFromPhoneSettings = false
    private val dialogs: Vector<Dialog> = Vector<Dialog>()
    private var location = ""
    private var latitude = 0.0
    private var longitude = 0.0
    private var isMapMoved = false
    private var initGPS: Boolean? = null
    // private var gpsPermissionDenied = false

    fun updateLocation(location: Location) {
        latitude = location.latitude
        longitude = location.longitude
        myMapFragment.getMapAsync(mActivity)
//        getAddress(null, "")
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initObserver()

        profileViewModel.locationSaveUpdate().observe(this) { submitLocation(it) }
    }

    override fun onResume() {
        super.onResume()
        if (isBackFromPhoneSettings) {
            getLocationPermission()
            binding.progressBar.visibility = View.GONE
            isBackFromPhoneSettings = false
        }
        if (initGPS != null && initGPS == true) {
            createLocationRequest()
        }
    }

    private fun initObserver() {
        profileViewModel.apply {
            addressData.observe(mActivity) {
                if(!it.results.isNullOrEmpty()){
                    val apiAddress = it.results[0].formatted_address
                    getAddress(null, apiAddress)
                }
            }
            addressError.observe(mActivity) {
                Log.e("TAG", "ERROR=== $it")
            }
        }
    }


    private fun submitLocation(result: ResultState<LocationModel>) {

        when (result) {

            is ResultState.Success -> {
                println("save location update--------->>>>")

            }

            is ResultState.Error -> {
                println("save location update error--------->>>>")

            }

        }
    }


    private fun init() {
        getLocationPermission()
        //createLocationRequest()
        binding.apply {

            myMapFragment =
                supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment

            btnSaveLocation.setOnClickListener {
                profileViewModel.savePreference(PreferenceKeys.PREF_AREA, location)
                profileViewModel.savePreference(PreferenceKeys.PREF_LAT, latitude)
                profileViewModel.savePreference(PreferenceKeys.PREF_LONG, longitude)

                val apiRequest = JsonObject().apply {
                    addProperty("latitude", latitude)
                    addProperty("longitude", longitude)
                    addProperty("location_name", location)
                }
                profileViewModel.saveLocation(apiRequest)


                val i = Intent()
                    .putExtra("latitude", latitude)
                    .putExtra("longitude", longitude)
                    .putExtra("location", location)
                setResult(RESULT_OK, i)
                finish()
            }

            imgCancel.setOnClickListener {
                onBackPressed()
            }

            btnSaveLocation.isEnabled = false
            progressBar.visibility = View.VISIBLE
            location = ""
            txtLocation.text = location
            imgCurrentLocation.setOnClickListener {
                createLocationRequest()
            }
        }
    }

    private fun createLocationRequest() {
        //   gpsPermissionDenied = true
        locationRequest?.apply {
            interval = 15000
            fastestInterval = 5000
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()

        locationRequest?.let {
            builder.addLocationRequest(it)
        }

        val client: SettingsClient = LocationServices.getSettingsClient(mActivity)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            if (ActivityCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    mActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@addOnSuccessListener
            }
            fusedLocationClient.requestLocationUpdates(locationRequest!!, locationCallback, null)
        }

        task.addOnFailureListener { exception ->
            Log.e("locationActivity-->", "failed")
            initGPS = true
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        mActivity,
                        1122
                    )
                } catch (sendEx: java.lang.Exception) {
                    sendEx.printStackTrace()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1122) {
                createLocationRequest()
            }
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap?.clear()
        googleMap = p0
        val cameraUpdate1 =
            CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 16f)
        googleMap?.apply {

            setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    mActivity, R.raw.style_json
                )
            )

            animateCamera(cameraUpdate1)

            setOnCameraMoveStartedListener {
                binding.apply {
                    btnSaveLocation.isEnabled = false
                    progressBar.visibility = View.VISIBLE
                    location = ""
                    txtLocation.text = location
                }
            }

            setOnCameraIdleListener {
                if (!isMapMoved) {
                    longitude += 0.0001
                    isMapMoved = true
                    val cameraUpdate2 =
                        CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), 16f)
                    animateCamera(cameraUpdate2)
                }
                if (googleMap?.cameraPosition != null) {
                    latitude = googleMap?.cameraPosition?.target?.latitude ?: 0.0
                    longitude = googleMap?.cameraPosition?.target?.longitude ?: 0.0
                    Log.d("LOCATION_LAT_LONG", "LAT: $latitude\n LONG: $longitude")
                    if (latitude != 0.0 && longitude != 0.0) {
                        val geocodeList = Geocoder(mActivity, Locale.getDefault()).getFromLocation(
                            latitude,
                            longitude,
                            1
                        )
                        if (geocodeList?.size == 0) {
                            profileViewModel.getAddressFromLatLong(
                                getString(R.string.google_maps_key),
                                "$latitude,$longitude"
                            )
                        } else {
                            val address = geocodeList!![0]
                            getAddress(address, "")
                        }
                    }
                }
            }
        }
    }

    private fun getAddress(address: Address?, apiAddress: String) {
        try {
//            val address =
//                Geocoder(mActivity, Locale.getDefault()).getFromLocation(latitude, longitude, 1)[0]
//            Log.e("ADDRESS_New", Gson().toJson(address))
//            return
            if (address != null) {
                Log.e("ADDRESS", "ADMIN AREA === ${address.subAdminArea}")
            }
            binding.apply {
                progressBar.visibility = View.GONE
//                location = "${address.subAdminArea}, ${address.adminArea}"
//                if ((address.subAdminArea.isNullOrEmpty() || address.adminArea.isNullOrEmpty())) {
//                    txtLocation.text = getString(R.string.adjust_map_pin)
//                    btnSaveLocation.isEnabled = false
//                } else {
//                    btnSaveLocation.isEnabled = true
//                    txtLocation.text = location
//                }
                if (address != null) {
                    Log.d("ADDRESS_EMPTY", Gson().toJson(address))
                    if (!address.subAdminArea.isNullOrEmpty() && !address.adminArea.isNullOrEmpty()) {
                        location = "${address.subAdminArea}, ${address.adminArea}"
                        Log.e("ADDRESS_1", "ADMIN AREA === $location")
                        txtLocation.text = location
                        btnSaveLocation.isEnabled = true
                    } else if (address.subAdminArea.isNullOrEmpty() && !address.adminArea.isNullOrEmpty()) {
                        location = address.adminArea
                        Log.e("ADDRESS_2", "ADMIN AREA === $location")
                        txtLocation.text = location
                        btnSaveLocation.isEnabled = true
                    } else if (!address.subAdminArea.isNullOrEmpty() && address.adminArea.isNullOrEmpty()) {
                        location = address.subAdminArea
                        Log.e("ADDRESS_3", "ADMIN AREA === $location")
                        txtLocation.text = location
                        btnSaveLocation.isEnabled = true
                    } else {
                        profileViewModel.getAddressFromLatLong(
                            getString(R.string.google_maps_key),
                            "$latitude,$longitude"
                        )
//                        btnSaveLocation.isEnabled = false
//                        txtLocation.text = getString(R.string.adjust_map_pin)
//                        Log.e("ADDRESS_4" , "ADMIN AREA === $location")
                    }
                } else if (apiAddress != "") {
                    location = apiAddress
                    Log.e("ADDRESS_5", "ADMIN AREA === $location")
                    txtLocation.text = location
                    btnSaveLocation.isEnabled = true
                } else {
//                    if (latitude != 0.0 && longitude != 0.0) {
//                        val geocodeList = Geocoder(mActivity, Locale.getDefault()).getFromLocation(latitude, longitude, 1)
//                        if(geocodeList.size==0){
//                            profileViewModel.getAddressFromLatLong(getString(R.string.google_maps_key), "$latitude,$longitude")
//                        } else {
//                            val geocodeAddress = geocodeList[0]
//                            getAddress(geocodeAddress, "")
//                        }
//                    }
                    btnSaveLocation.isEnabled = false
                    txtLocation.text = getString(R.string.adjust_map_pin)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
//            val address =
//                Geocoder(mActivity, Locale.getDefault()).getFromLocation(latitude, longitude, 1)
//            Log.e("ADDRESS_6", "ADMIN AREA === $e\n LAT: $latitude\n LONG: $longitude\n ADDRESS: $address")
//            Log.e("ADDRESS_New", Gson().toJson(address))
        }
    }

    private fun getLocationPermission() {
        Dexter.withContext(mActivity).withPermissions(
            arrayListOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                when {
                    report.areAllPermissionsGranted() -> {
                        createLocationRequest()
                    }
                    report.deniedPermissionResponses != null && report.deniedPermissionResponses.size > 0 -> {
                        openLocationDialog()
                    }
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        })
            .withErrorListener {
                Log.e("Dexter", "There was an error: $it")
            }
            .check()
    }

    private fun openLocationDialog() {
        val dialog = Dialog(mActivity, R.style.ZoomDialogTheme)

        dialogs.add(dialog)
        val binding = DialogHelpUsLocationBinding.inflate(layoutInflater)
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(true)
            setContentView(binding.root)
            binding.apply {
                setOnCancelListener {
                    finish()
                }
                txtGotIt.setOnClickListener {
                    binding.imgDialogGradient.setDialogFadeOutAnimation {
                        btnSetting.performClick()
                    }
                }
                btnSetting.setOnClickListener {
                    //open settings for location permission
                    val intent =
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts(
                        "package",
                        packageName, null
                    )
                    intent.data = uri
                    try {
                        isBackFromPhoneSettings = true
                        startActivity(intent)
                        overridePendingTransition(R.anim.enter_anim,  R.anim.leave_anim)
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
//                    getLocationPermission()
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

    override fun onPause() {
        super.onPause()
        dialogs.forEach {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    private fun openGpsSetting() {
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }
}