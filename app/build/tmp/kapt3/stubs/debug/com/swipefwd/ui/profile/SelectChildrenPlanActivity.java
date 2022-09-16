package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J \u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020!0 j\b\u0012\u0004\u0012\u00020!`\"H\u0002J\b\u0010#\u001a\u00020\u001eH\u0002J\b\u0010$\u001a\u00020\u001eH\u0002J\b\u0010%\u001a\u00020\u001eH\u0002J\b\u0010&\u001a\u00020\u001eH\u0016J\u0012\u0010\'\u001a\u00020\u001e2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J0\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u000e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020!0 j\b\u0012\u0004\u0012\u00020!`\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\f\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006."}, d2 = {"Lcom/swipefwd/ui/profile/SelectChildrenPlanActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/swipefwd/ui/profile/SelectChildrenAdapter;", "binding", "Lcom/swipefwd/databinding/ActivitySelectChildrenPlanBinding;", "childrenViewModel", "Lcom/swipefwd/ui/profile/SelectChildrenViewModel;", "getChildrenViewModel", "()Lcom/swipefwd/ui/profile/SelectChildrenViewModel;", "childrenViewModel$delegate", "Lkotlin/Lazy;", "isFromSettings", "", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/profile/SelectChildrenPlanActivity;", "mActivity$delegate", "mDatabase", "Lcom/swipefwd/utils/AppDatabase;", "getMDatabase", "()Lcom/swipefwd/utils/AppDatabase;", "mDatabase$delegate", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "bindAdapter", "", "list", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/ChildrenModel$ChildrenData$ChildrenLevel;", "Lkotlin/collections/ArrayList;", "getChildrenList", "init", "initObservers", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setChildData", "child", "", "isPreference", "app_debug"})
public final class SelectChildrenPlanActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivitySelectChildrenPlanBinding binding;
    private final kotlin.Lazy mActivity$delegate = null;
    private com.swipefwd.ui.profile.SelectChildrenAdapter adapter;
    private final kotlin.Lazy childrenViewModel$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private final kotlin.Lazy mDatabase$delegate = null;
    private boolean isFromSettings = false;
    
    public SelectChildrenPlanActivity() {
        super();
    }
    
    private final com.swipefwd.ui.profile.SelectChildrenPlanActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.ui.profile.SelectChildrenViewModel getChildrenViewModel() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    private final com.swipefwd.utils.AppDatabase getMDatabase() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void getChildrenList() {
    }
    
    private final void initObservers() {
    }
    
    private final void bindAdapter(java.util.ArrayList<com.swipefwd.data.models.ChildrenModel.ChildrenData.ChildrenLevel> list) {
    }
    
    private final void setChildData(java.lang.String child, boolean isPreference, java.util.ArrayList<com.swipefwd.data.models.ChildrenModel.ChildrenData.ChildrenLevel> list) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
}