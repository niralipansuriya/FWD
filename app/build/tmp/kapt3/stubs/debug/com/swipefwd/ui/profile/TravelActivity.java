package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0016H\u0002J\b\u0010/\u001a\u00020-H\u0002J\b\u00100\u001a\u00020-H\u0002J\u0012\u00101\u001a\u00020-2\b\u00102\u001a\u0004\u0018\u000103H\u0014J\b\u00104\u001a\u00020-H\u0014J\b\u00105\u001a\u00020-H\u0002J\b\u00106\u001a\u00020-H\u0002J\u0010\u00107\u001a\u00020-2\u0006\u00108\u001a\u000209H\u0002J\u0018\u0010:\u001a\u00020-2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010#\u001a\u00020$H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\b\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\b\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\"\u0010\b\u001a\u0004\b \u0010!R\u001a\u0010#\u001a\u00020$X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u0016\u0010)\u001a\n +*\u0004\u0018\u00010*0*X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lcom/swipefwd/ui/profile/TravelActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/swipefwd/ui/profile/TravelLocationListAdapter;", "getAdapter", "()Lcom/swipefwd/ui/profile/TravelLocationListAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "binding", "Lcom/swipefwd/databinding/ActivityTravelBinding;", "editMode", "", "isShow", "()Z", "setShow", "(Z)V", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/profile/TravelActivity;", "mActivity$delegate", "placeModel", "Lcom/swipefwd/data/models/TravelLocationListModel;", "placesClient", "Lcom/google/android/libraries/places/api/net/PlacesClient;", "getPlacesClient", "()Lcom/google/android/libraries/places/api/net/PlacesClient;", "placesClient$delegate", "placesList", "Ljava/util/ArrayList;", "profileViewModel", "Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "getProfileViewModel", "()Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "profileViewModel$delegate", "share_travel_location", "", "getShare_travel_location", "()I", "setShare_travel_location", "(I)V", "token", "Lcom/google/android/libraries/places/api/model/AutocompleteSessionToken;", "kotlin.jvm.PlatformType", "findLatLng", "", "place", "init", "initObserver", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "openTravelChangeDialog", "openTravelRelocationDialog", "searchCity", "query", "", "updateTravelLocation", "app_debug"})
public final class TravelActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivityTravelBinding binding;
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy placesClient$delegate = null;
    private final kotlin.Lazy profileViewModel$delegate = null;
    private com.swipefwd.data.models.TravelLocationListModel placeModel;
    private final com.google.android.libraries.places.api.model.AutocompleteSessionToken token = null;
    private java.util.ArrayList<com.swipefwd.data.models.TravelLocationListModel> placesList;
    private boolean editMode = true;
    private boolean isShow = false;
    private int share_travel_location = 0;
    private final kotlin.Lazy adapter$delegate = null;
    
    public TravelActivity() {
        super();
    }
    
    private final com.swipefwd.ui.profile.TravelActivity getMActivity() {
        return null;
    }
    
    private final com.google.android.libraries.places.api.net.PlacesClient getPlacesClient() {
        return null;
    }
    
    private final com.swipefwd.ui.updateuserprofile.UserProfileViewModel getProfileViewModel() {
        return null;
    }
    
    public final boolean isShow() {
        return false;
    }
    
    public final void setShow(boolean p0) {
    }
    
    public final int getShare_travel_location() {
        return 0;
    }
    
    public final void setShare_travel_location(int p0) {
    }
    
    private final com.swipefwd.ui.profile.TravelLocationListAdapter getAdapter() {
        return null;
    }
    
    private final void initObserver() {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void searchCity(java.lang.String query) {
    }
    
    private final void findLatLng(com.swipefwd.data.models.TravelLocationListModel place) {
    }
    
    private final void updateTravelLocation(boolean isShow, int share_travel_location) {
    }
    
    private final void openTravelChangeDialog() {
    }
    
    private final void openTravelRelocationDialog() {
    }
}