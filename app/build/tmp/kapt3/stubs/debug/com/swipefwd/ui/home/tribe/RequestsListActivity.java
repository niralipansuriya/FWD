package com.swipefwd.ui.home.tribe;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 H\u0002J\b\u0010\"\u001a\u00020\u001eH\u0002J\b\u0010#\u001a\u00020\u001eH\u0002J\b\u0010$\u001a\u00020\u001eH\u0002J\u0012\u0010%\u001a\u00020\u001e2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0014J\b\u0010(\u001a\u00020\u001eH\u0014J\b\u0010)\u001a\u00020\u001eH\u0014J\b\u0010*\u001a\u00020\u001eH\u0002J\b\u0010+\u001a\u00020\u001eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006,"}, d2 = {"Lcom/swipefwd/ui/home/tribe/RequestsListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/swipefwd/databinding/ActivityRequestsListBinding;", "connectorSize", "", "dialogs", "Ljava/util/Vector;", "Landroid/app/Dialog;", "homeViewModel", "Lcom/swipefwd/ui/home/tribe/HomeFragmentViewModel;", "getHomeViewModel", "()Lcom/swipefwd/ui/home/tribe/HomeFragmentViewModel;", "homeViewModel$delegate", "Lkotlin/Lazy;", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/home/tribe/RequestsListActivity;", "mActivity$delegate", "mAdapter", "Lcom/swipefwd/ui/home/tribe/ActiveTribeRequestAdapter;", "mExpiredAdapter", "Lcom/swipefwd/ui/home/tribe/ExpiredTribeRequestAdapter;", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "addActivePendingRequestList", "", "selectedUsers", "", "Lcom/swipefwd/data/models/ActiveExpireRequestListModel$Pending;", "getActivePendingRequestList", "init", "initObservers", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "openAddedUserDialog", "openSinglePlayerLimitDialog", "app_debug"})
public final class RequestsListActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivityRequestsListBinding binding;
    private final kotlin.Lazy mActivity$delegate = null;
    private com.swipefwd.ui.home.tribe.ActiveTribeRequestAdapter mAdapter;
    private com.swipefwd.ui.home.tribe.ExpiredTribeRequestAdapter mExpiredAdapter;
    private final kotlin.Lazy homeViewModel$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private int connectorSize = 1;
    private final java.util.Vector<android.app.Dialog> dialogs = null;
    
    public RequestsListActivity() {
        super();
    }
    
    private final com.swipefwd.ui.home.tribe.RequestsListActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.ui.home.tribe.HomeFragmentViewModel getHomeViewModel() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void openAddedUserDialog() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void openSinglePlayerLimitDialog() {
    }
    
    private final void getActivePendingRequestList() {
    }
    
    private final void addActivePendingRequestList(java.util.List<com.swipefwd.data.models.ActiveExpireRequestListModel.Pending> selectedUsers) {
    }
    
    private final void initObservers() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
}