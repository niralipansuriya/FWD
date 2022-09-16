package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010 \u001a\u00020!2\u0016\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020$0#j\b\u0012\u0004\u0012\u00020$`%H\u0002J\b\u0010&\u001a\u00020!H\u0002J\b\u0010\'\u001a\u00020!H\u0002J\b\u0010(\u001a\u00020!H\u0002J\b\u0010)\u001a\u00020!H\u0016J\u0012\u0010*\u001a\u00020!2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J0\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\f2\u0016\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020$0#j\b\u0012\u0004\u0012\u00020$`%H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\b\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\b\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\b\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001f\u0010\b\u001a\u0004\b\u001d\u0010\u001e\u00a8\u00061"}, d2 = {"Lcom/swipefwd/ui/profile/SelectSmokeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/swipefwd/ui/profile/SelectSmokingAdapter;", "getAdapter", "()Lcom/swipefwd/ui/profile/SelectSmokingAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "binding", "Lcom/swipefwd/databinding/ActivitySelectSmokeBinding;", "isFromSettings", "", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/profile/SelectSmokeActivity;", "mActivity$delegate", "mDatabase", "Lcom/swipefwd/utils/AppDatabase;", "getMDatabase", "()Lcom/swipefwd/utils/AppDatabase;", "mDatabase$delegate", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "smokingViewModel", "Lcom/swipefwd/ui/profile/SelectSmokingViewModel;", "getSmokingViewModel", "()Lcom/swipefwd/ui/profile/SelectSmokingViewModel;", "smokingViewModel$delegate", "bindAdapter", "", "list", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/SmokingDataModel$SmokingData$Smoking;", "Lkotlin/collections/ArrayList;", "getSmokingList", "init", "initObservers", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setSmokeData", "smoke", "", "isPreference", "app_debug"})
public final class SelectSmokeActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivitySelectSmokeBinding binding;
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy adapter$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private final kotlin.Lazy mDatabase$delegate = null;
    private final kotlin.Lazy smokingViewModel$delegate = null;
    private boolean isFromSettings = false;
    
    public SelectSmokeActivity() {
        super();
    }
    
    private final com.swipefwd.ui.profile.SelectSmokeActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.ui.profile.SelectSmokingAdapter getAdapter() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    private final com.swipefwd.utils.AppDatabase getMDatabase() {
        return null;
    }
    
    private final com.swipefwd.ui.profile.SelectSmokingViewModel getSmokingViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void getSmokingList() {
    }
    
    private final void initObservers() {
    }
    
    private final void bindAdapter(java.util.ArrayList<com.swipefwd.data.models.SmokingDataModel.SmokingData.Smoking> list) {
    }
    
    private final void setSmokeData(java.lang.String smoke, boolean isPreference, java.util.ArrayList<com.swipefwd.data.models.SmokingDataModel.SmokingData.Smoking> list) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
}