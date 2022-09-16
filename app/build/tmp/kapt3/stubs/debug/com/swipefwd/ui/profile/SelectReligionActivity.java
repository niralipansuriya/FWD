package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010)\u001a\u00020*2\u0016\u0010+\u001a\u0012\u0012\u0004\u0012\u00020-0,j\b\u0012\u0004\u0012\u00020-`.H\u0002J\b\u0010/\u001a\u00020*H\u0002J\b\u00100\u001a\u00020*H\u0002J\b\u00101\u001a\u00020*H\u0002J\b\u00102\u001a\u00020*H\u0016J\u0012\u00103\u001a\u00020*2\b\u00104\u001a\u0004\u0018\u000105H\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\b\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\b\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\b\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010\b\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010\b\u001a\u0004\b&\u0010\'\u00a8\u00066"}, d2 = {"Lcom/swipefwd/ui/profile/SelectReligionActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/swipefwd/ui/profile/SelectReligionAdapter;", "getAdapter", "()Lcom/swipefwd/ui/profile/SelectReligionAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "binding", "Lcom/swipefwd/databinding/ActivitySelectReligionBinding;", "isFromSettings", "", "isSelectedAll", "()Z", "setSelectedAll", "(Z)V", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/profile/SelectReligionActivity;", "mActivity$delegate", "mDatabase", "Lcom/swipefwd/utils/AppDatabase;", "getMDatabase", "()Lcom/swipefwd/utils/AppDatabase;", "mDatabase$delegate", "multipleAdapter", "Lcom/swipefwd/ui/profile/SelectMultipleReligionAdapter;", "getMultipleAdapter", "()Lcom/swipefwd/ui/profile/SelectMultipleReligionAdapter;", "multipleAdapter$delegate", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "religionViewModel", "Lcom/swipefwd/ui/profile/SelectReligionViewModel;", "getReligionViewModel", "()Lcom/swipefwd/ui/profile/SelectReligionViewModel;", "religionViewModel$delegate", "bindAdapter", "", "list", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/ReligionModel$ReligionData$ReligionLevel;", "Lkotlin/collections/ArrayList;", "getReligionList", "init", "initObservers", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class SelectReligionActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivitySelectReligionBinding binding;
    private final kotlin.Lazy mActivity$delegate = null;
    private boolean isSelectedAll = false;
    private final kotlin.Lazy adapter$delegate = null;
    private final kotlin.Lazy multipleAdapter$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private final kotlin.Lazy mDatabase$delegate = null;
    private final kotlin.Lazy religionViewModel$delegate = null;
    private boolean isFromSettings = false;
    
    public SelectReligionActivity() {
        super();
    }
    
    private final com.swipefwd.ui.profile.SelectReligionActivity getMActivity() {
        return null;
    }
    
    public final boolean isSelectedAll() {
        return false;
    }
    
    public final void setSelectedAll(boolean p0) {
    }
    
    private final com.swipefwd.ui.profile.SelectReligionAdapter getAdapter() {
        return null;
    }
    
    private final com.swipefwd.ui.profile.SelectMultipleReligionAdapter getMultipleAdapter() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    private final com.swipefwd.utils.AppDatabase getMDatabase() {
        return null;
    }
    
    private final com.swipefwd.ui.profile.SelectReligionViewModel getReligionViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void bindAdapter(java.util.ArrayList<com.swipefwd.data.models.ReligionModel.ReligionData.ReligionLevel> list) {
    }
    
    private final void getReligionList() {
    }
    
    private final void initObservers() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
}