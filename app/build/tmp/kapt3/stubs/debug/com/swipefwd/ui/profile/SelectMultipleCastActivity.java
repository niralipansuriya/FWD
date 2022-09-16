package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010)\u001a\u00020*2\u0016\u0010+\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`,H\u0002J\b\u0010-\u001a\u00020*H\u0002J\b\u0010.\u001a\u00020*H\u0002J\b\u0010/\u001a\u00020*H\u0002J\b\u00100\u001a\u00020*H\u0016J\u0012\u00101\u001a\u00020*2\b\u00102\u001a\u0004\u0018\u000103H\u0014J\u0010\u00104\u001a\u00020*2\u0006\u00105\u001a\u00020\rH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\b\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\b\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\"\u0010\b\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\'\u0010\b\u001a\u0004\b%\u0010&R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00066"}, d2 = {"Lcom/swipefwd/ui/profile/SelectMultipleCastActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/swipefwd/ui/profile/SelectCastAdapter;", "getAdapter", "()Lcom/swipefwd/ui/profile/SelectCastAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "binding", "Lcom/swipefwd/databinding/ActivitySelectMultipleCastBinding;", "castDbList", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/CastListModel$CastModel;", "castViewModel", "Lcom/swipefwd/ui/profile/SelectCastViewModel;", "getCastViewModel", "()Lcom/swipefwd/ui/profile/SelectCastViewModel;", "castViewModel$delegate", "isFromSettings", "", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/profile/SelectMultipleCastActivity;", "mActivity$delegate", "mDatabase", "Lcom/swipefwd/utils/AppDatabase;", "getMDatabase", "()Lcom/swipefwd/utils/AppDatabase;", "mDatabase$delegate", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "selectedCastAdapter", "Lcom/swipefwd/ui/profile/SelectedMultipleCastAdapter;", "getSelectedCastAdapter", "()Lcom/swipefwd/ui/profile/SelectedMultipleCastAdapter;", "selectedCastAdapter$delegate", "selectedCastList", "bindAdapter", "", "list", "Lkotlin/collections/ArrayList;", "getCastList", "init", "initObservers", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "updateListForSelectedLanguages", "model", "app_debug"})
public final class SelectMultipleCastActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivitySelectMultipleCastBinding binding;
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private final kotlin.Lazy mDatabase$delegate = null;
    private boolean isFromSettings = false;
    private final kotlin.Lazy castViewModel$delegate = null;
    private java.util.ArrayList<com.swipefwd.data.models.CastListModel.CastModel> selectedCastList;
    private java.util.ArrayList<com.swipefwd.data.models.CastListModel.CastModel> castDbList;
    private final kotlin.Lazy adapter$delegate = null;
    private final kotlin.Lazy selectedCastAdapter$delegate = null;
    
    public SelectMultipleCastActivity() {
        super();
    }
    
    private final com.swipefwd.ui.profile.SelectMultipleCastActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    private final com.swipefwd.utils.AppDatabase getMDatabase() {
        return null;
    }
    
    private final com.swipefwd.ui.profile.SelectCastViewModel getCastViewModel() {
        return null;
    }
    
    private final com.swipefwd.ui.profile.SelectCastAdapter getAdapter() {
        return null;
    }
    
    private final com.swipefwd.ui.profile.SelectedMultipleCastAdapter getSelectedCastAdapter() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void updateListForSelectedLanguages(com.swipefwd.data.models.CastListModel.CastModel model) {
    }
    
    private final void getCastList() {
    }
    
    private final void initObservers() {
    }
    
    private final void bindAdapter(java.util.ArrayList<com.swipefwd.data.models.CastListModel.CastModel> list) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
}