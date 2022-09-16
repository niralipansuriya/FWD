package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010-\u001a\u00020.H\u0002J\u001a\u0010/\u001a\u00020.2\b\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020\u0019H\u0002J\b\u00103\u001a\u00020.H\u0002J\b\u00104\u001a\u00020.H\u0002J\b\u00105\u001a\u00020.H\u0002J\"\u00106\u001a\u00020.2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010;H\u0014J\u0012\u0010<\u001a\u00020.2\b\u0010=\u001a\u0004\u0018\u00010>H\u0014J\u0010\u0010?\u001a\u00020.2\u0006\u0010@\u001a\u00020\u0010H\u0016J\b\u0010A\u001a\u00020.H\u0014J\b\u0010B\u001a\u00020.H\u0014J\b\u0010C\u001a\u00020.H\u0002J\b\u0010D\u001a\u00020.H\u0002J\u0016\u0010E\u001a\u00020.2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020H0GH\u0002J\u000e\u0010I\u001a\u00020.2\u0006\u0010\u0018\u001a\u00020JR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010\u000e\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010!\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\"\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b%\u0010\u000e\u001a\u0004\b#\u0010$R\u000e\u0010&\u001a\u00020\'X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b,\u0010\u000e\u001a\u0004\b*\u0010+\u00a8\u0006K"}, d2 = {"Lcom/swipefwd/ui/profile/LocationActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "binding", "Lcom/swipefwd/databinding/ActivityLocationBinding;", "dialogs", "Ljava/util/Vector;", "Landroid/app/Dialog;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "getFusedLocationClient", "()Lcom/google/android/gms/location/FusedLocationProviderClient;", "fusedLocationClient$delegate", "Lkotlin/Lazy;", "googleMap", "Lcom/google/android/gms/maps/GoogleMap;", "initGPS", "", "Ljava/lang/Boolean;", "isBackFromPhoneSettings", "isMapMoved", "latitude", "", "location", "", "locationCallback", "Lcom/google/android/gms/location/LocationCallback;", "locationRequest", "Lcom/google/android/gms/location/LocationRequest;", "getLocationRequest", "()Lcom/google/android/gms/location/LocationRequest;", "locationRequest$delegate", "longitude", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/profile/LocationActivity;", "mActivity$delegate", "myMapFragment", "Lcom/google/android/gms/maps/SupportMapFragment;", "profileViewModel", "Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "getProfileViewModel", "()Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "profileViewModel$delegate", "createLocationRequest", "", "getAddress", "address", "Landroid/location/Address;", "apiAddress", "getLocationPermission", "init", "initObserver", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "p0", "onPause", "onResume", "openGpsSetting", "openLocationDialog", "submitLocation", "result", "Lcom/swipefwd/base/ResultState;", "Lcom/swipefwd/data/models/LocationModel;", "updateLocation", "Landroid/location/Location;", "app_debug"})
public final class LocationActivity extends androidx.appcompat.app.AppCompatActivity implements com.google.android.gms.maps.OnMapReadyCallback {
    private com.swipefwd.databinding.ActivityLocationBinding binding;
    private com.google.android.gms.maps.GoogleMap googleMap;
    private com.google.android.gms.maps.SupportMapFragment myMapFragment;
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy profileViewModel$delegate = null;
    private final kotlin.Lazy locationRequest$delegate = null;
    private final kotlin.Lazy fusedLocationClient$delegate = null;
    private final com.google.android.gms.location.LocationCallback locationCallback = null;
    private boolean isBackFromPhoneSettings = false;
    private final java.util.Vector<android.app.Dialog> dialogs = null;
    private java.lang.String location = "";
    private double latitude = 0.0;
    private double longitude = 0.0;
    private boolean isMapMoved = false;
    private java.lang.Boolean initGPS;
    
    public LocationActivity() {
        super();
    }
    
    private final com.swipefwd.ui.profile.LocationActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.ui.updateuserprofile.UserProfileViewModel getProfileViewModel() {
        return null;
    }
    
    private final com.google.android.gms.location.LocationRequest getLocationRequest() {
        return null;
    }
    
    private final com.google.android.gms.location.FusedLocationProviderClient getFusedLocationClient() {
        return null;
    }
    
    public final void updateLocation(@org.jetbrains.annotations.NotNull()
    android.location.Location location) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void initObserver() {
    }
    
    private final void submitLocation(com.swipefwd.base.ResultState<com.swipefwd.data.models.LocationModel> result) {
    }
    
    private final void init() {
    }
    
    private final void createLocationRequest() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void onMapReady(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.GoogleMap p0) {
    }
    
    private final void getAddress(android.location.Address address, java.lang.String apiAddress) {
    }
    
    private final void getLocationPermission() {
    }
    
    private final void openLocationDialog() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    private final void openGpsSetting() {
    }
}