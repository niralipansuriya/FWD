package com.swipefwd.ui.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0016\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001a\u0010\u0018\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001a\u0010\u001a\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!\u00a8\u0006\""}, d2 = {"Lcom/swipefwd/ui/activity/BaseViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "fwdDataStore1", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "getFwdDataStore1", "()Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "fwdDataStore1$delegate", "Lkotlin/Lazy;", "getGreenShowTip1", "", "getGetGreenShowTip1", "()Z", "setGetGreenShowTip1", "(Z)V", "isAccountTip", "setAccountTip", "isInterested1", "setInterested1", "isNotInterested1", "setNotInterested1", "isSMSsent", "setSMSsent", "userId", "", "getUserId", "()I", "setUserId", "(I)V", "app_debug"})
public class BaseViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private android.content.Context context;
    private final kotlin.Lazy fwdDataStore1$delegate = null;
    private boolean isNotInterested1 = false;
    private boolean isInterested1 = false;
    private boolean getGreenShowTip1 = false;
    private boolean isAccountTip = false;
    private boolean isSMSsent = false;
    private int userId = 0;
    
    public BaseViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    private final com.swipefwd.utils.datastore.InternalAppDataStore getFwdDataStore1() {
        return null;
    }
    
    public final boolean isNotInterested1() {
        return false;
    }
    
    public final void setNotInterested1(boolean p0) {
    }
    
    public final boolean isInterested1() {
        return false;
    }
    
    public final void setInterested1(boolean p0) {
    }
    
    public final boolean getGetGreenShowTip1() {
        return false;
    }
    
    public final void setGetGreenShowTip1(boolean p0) {
    }
    
    public final boolean isAccountTip() {
        return false;
    }
    
    public final void setAccountTip(boolean p0) {
    }
    
    public final boolean isSMSsent() {
        return false;
    }
    
    public final void setSMSsent(boolean p0) {
    }
    
    public final int getUserId() {
        return 0;
    }
    
    public final void setUserId(int p0) {
    }
}