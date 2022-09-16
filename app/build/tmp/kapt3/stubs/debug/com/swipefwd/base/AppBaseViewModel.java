package com.swipefwd.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 %2\u00020\u0001:\u0001%B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\u0005\u00a2\u0006\u0002\u0010\u0007J\u0011\u0010\u001f\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0011\u0010!\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0012\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0#J\u0011\u0010$\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0013\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R\u001a\u0010\u0015\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001a\u0010\u0017\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006&"}, d2 = {"Lcom/swipefwd/base/AppBaseViewModel;", "Landroidx/lifecycle/ViewModel;", "internalAppDataStore", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "appDatabase", "Lcom/swipefwd/utils/AppDatabase;", "(Lcom/swipefwd/utils/datastore/InternalAppDataStore;Lcom/swipefwd/utils/AppDatabase;)V", "()V", "_logOutUser", "Landroidx/lifecycle/MutableLiveData;", "Lcom/swipefwd/base/ResultState;", "", "isAccountTip", "", "()Z", "setAccountTip", "(Z)V", "isGreenShowTip", "setGreenShowTip", "isInterested", "setInterested", "isNotInterested", "setNotInterested", "isSmsSent", "setSmsSent", "userId", "", "getUserId", "()I", "setUserId", "(I)V", "clearDatabase", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logoutUser", "onLogOutUser", "Landroidx/lifecycle/LiveData;", "removePreference", "Companion", "app_debug"})
public class AppBaseViewModel extends androidx.lifecycle.ViewModel {
    private com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStore;
    private com.swipefwd.utils.AppDatabase appDatabase;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.base.AppBaseViewModel.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOMETHING_WENT_WRONG_ERROR = "Something went wrong";
    private boolean isNotInterested = false;
    private boolean isInterested = false;
    private boolean isGreenShowTip = false;
    private boolean isAccountTip = false;
    private boolean isSmsSent = false;
    private int userId = 0;
    
    /**
     * call logout
     */
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<kotlin.Unit>> _logOutUser;
    
    public AppBaseViewModel() {
        super();
    }
    
    public AppBaseViewModel(@org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStore, @org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.AppDatabase appDatabase) {
        super();
    }
    
    public final boolean isNotInterested() {
        return false;
    }
    
    public final void setNotInterested(boolean p0) {
    }
    
    public final boolean isInterested() {
        return false;
    }
    
    public final void setInterested(boolean p0) {
    }
    
    public final boolean isGreenShowTip() {
        return false;
    }
    
    public final void setGreenShowTip(boolean p0) {
    }
    
    public final boolean isAccountTip() {
        return false;
    }
    
    public final void setAccountTip(boolean p0) {
    }
    
    public final boolean isSmsSent() {
        return false;
    }
    
    public final void setSmsSent(boolean p0) {
    }
    
    public final int getUserId() {
        return 0;
    }
    
    public final void setUserId(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<kotlin.Unit>> onLogOutUser() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logoutUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    /**
     * remove preference
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removePreference(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * this function will clear the data base
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearDatabase(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/base/AppBaseViewModel$Companion;", "", "()V", "SOMETHING_WENT_WRONG_ERROR", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}