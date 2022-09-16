package com.swipefwd.ui.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\fJ\'\u00100\u001a\u00020.\"\u0004\b\u0000\u001012\f\u00102\u001a\b\u0012\u0004\u0012\u0002H1032\u0006\u00104\u001a\u0002H1\u00a2\u0006\u0002\u00105R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\f0\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0\"\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0\"\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010$R \u0010*\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0014\u00a8\u00066"}, d2 = {"Lcom/swipefwd/ui/activity/ActivityViewModel;", "Lcom/swipefwd/ui/activity/BaseViewModel;", "context", "Landroid/content/Context;", "appRepository", "Lcom/swipefwd/data/source/AppRepository;", "(Landroid/content/Context;Lcom/swipefwd/data/source/AppRepository;)V", "_activePendingData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/swipefwd/data/models/ActiveExpireRequestListModel;", "_activePendingError", "_errorMessage", "", "_showLoading", "", "activePendingData", "Landroidx/lifecycle/LiveData;", "getActivePendingData", "()Landroidx/lifecycle/LiveData;", "setActivePendingData", "(Landroidx/lifecycle/LiveData;)V", "activePendingError", "getActivePendingError", "setActivePendingError", "errorMessage", "getErrorMessage", "setErrorMessage", "fwdDataStore", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "getFwdDataStore", "()Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "fwdDataStore$delegate", "Lkotlin/Lazy;", "getAccountType", "Lkotlinx/coroutines/flow/Flow;", "getGetAccountType", "()Lkotlinx/coroutines/flow/Flow;", "getAuthToken", "getGetAuthToken", "getUserId", "", "getGetUserId", "showLoading", "getShowLoading", "setShowLoading", "getActivityExpiredRequestList", "", "token", "saveData", "T", "key", "Landroidx/datastore/preferences/core/Preferences$Key;", "value", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;)V", "app_debug"})
public final class ActivityViewModel extends com.swipefwd.ui.activity.BaseViewModel {
    private final com.swipefwd.data.source.AppRepository appRepository = null;
    private final kotlin.Lazy fwdDataStore$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getAuthToken = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getAccountType = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> getUserId = null;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _showLoading;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.lang.Boolean> showLoading;
    private androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.lang.String> errorMessage;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.ActiveExpireRequestListModel> _activePendingData;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.swipefwd.data.models.ActiveExpireRequestListModel> activePendingData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.ActiveExpireRequestListModel> _activePendingError;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.swipefwd.data.models.ActiveExpireRequestListModel> activePendingError;
    
    public ActivityViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.source.AppRepository appRepository) {
        super(null);
    }
    
    private final com.swipefwd.utils.datastore.InternalAppDataStore getFwdDataStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetAuthToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetAccountType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getGetUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getShowLoading() {
        return null;
    }
    
    public final void setShowLoading(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
    
    public final void setErrorMessage(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.ActiveExpireRequestListModel> getActivePendingData() {
        return null;
    }
    
    public final void setActivePendingData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.swipefwd.data.models.ActiveExpireRequestListModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.ActiveExpireRequestListModel> getActivePendingError() {
        return null;
    }
    
    public final void setActivePendingError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.swipefwd.data.models.ActiveExpireRequestListModel> p0) {
    }
    
    public final <T extends java.lang.Object>void saveData(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key, T value) {
    }
    
    public final void getActivityExpiredRequestList(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
}