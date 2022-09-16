package com.swipefwd.ui.splash;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020\u000bJ\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00100\u0015J\u000e\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020\u000bJ\u0006\u0010/\u001a\u00020\u0013J\u0011\u00100\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J\u0012\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0015J\u0010\u00103\u001a\u00020*2\b\u00104\u001a\u0004\u0018\u00010\u000bR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0 \u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0 \u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00130 \u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00130 \u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\"R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00065"}, d2 = {"Lcom/swipefwd/ui/splash/SplashViewModel;", "Lcom/swipefwd/ui/activity/BaseViewModel;", "context", "Landroid/content/Context;", "appRepository", "Lcom/swipefwd/data/source/AppRepository;", "internalAppDataStore", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "packageManager", "Landroid/content/pm/PackageManager;", "packageName", "", "(Landroid/content/Context;Lcom/swipefwd/data/source/AppRepository;Lcom/swipefwd/utils/datastore/InternalAppDataStore;Landroid/content/pm/PackageManager;Ljava/lang/String;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "_inactivityResponseData", "Lcom/swipefwd/data/models/InactivityModel;", "_isUpdateRequired", "Lcom/swipefwd/base/ResultState;", "", "errorMessage", "Landroidx/lifecycle/LiveData;", "getErrorMessage", "()Landroidx/lifecycle/LiveData;", "setErrorMessage", "(Landroidx/lifecycle/LiveData;)V", "fwdDataStore", "getFwdDataStore", "()Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "fwdDataStore$delegate", "Lkotlin/Lazy;", "getAuthToken", "Lkotlinx/coroutines/flow/Flow;", "getGetAuthToken", "()Lkotlinx/coroutines/flow/Flow;", "getCurrentScreen", "getGetCurrentScreen", "getLoginFlag", "getGetLoginFlag", "isOnBoardTutorialCompletedStatus", "recoveryEmailId", "checkForUpdates", "", "getRecoveryEmail", "inactivityResponseData", "inactivityStatus", "token", "isForRecoverAccount", "isOnBoardTutorialCompleted", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isUpdateRequiredResult", "setRecoverEmailId", "id", "app_debug"})
public final class SplashViewModel extends com.swipefwd.ui.activity.BaseViewModel {
    private final com.swipefwd.data.source.AppRepository appRepository = null;
    private final com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStore = null;
    private final android.content.pm.PackageManager packageManager = null;
    private final java.lang.String packageName = null;
    private final kotlin.Lazy fwdDataStore$delegate = null;
    private java.lang.String recoveryEmailId;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getAuthToken = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getLoginFlag = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getCurrentScreen = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isOnBoardTutorialCompletedStatus = null;
    private final androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<java.lang.Boolean>> _isUpdateRequired = null;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.InactivityModel> _inactivityResponseData;
    private androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.lang.String> errorMessage;
    
    public SplashViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.source.AppRepository appRepository, @org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStore, @org.jetbrains.annotations.NotNull()
    android.content.pm.PackageManager packageManager, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
        super(null);
    }
    
    private final com.swipefwd.utils.datastore.InternalAppDataStore getFwdDataStore() {
        return null;
    }
    
    public final void setRecoverEmailId(@org.jetbrains.annotations.Nullable()
    java.lang.String id) {
    }
    
    public final boolean isForRecoverAccount() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRecoveryEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetAuthToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getGetLoginFlag() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetCurrentScreen() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isOnBoardTutorialCompletedStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isOnBoardTutorialCompleted(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<java.lang.Boolean>> isUpdateRequiredResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.InactivityModel> inactivityResponseData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
    
    public final void setErrorMessage(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.lang.String> p0) {
    }
    
    public final void checkForUpdates() {
    }
    
    public final void inactivityStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
}