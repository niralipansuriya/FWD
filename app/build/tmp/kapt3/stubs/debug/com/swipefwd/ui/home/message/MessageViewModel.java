package com.swipefwd.ui.home.message;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\'\u0010\u0016\u001a\u00020\u0017\"\u0004\b\u0000\u0010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0006\u0010\u001b\u001a\u0002H\u0018\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011\u00a8\u0006\u001d"}, d2 = {"Lcom/swipefwd/ui/home/message/MessageViewModel;", "Lcom/swipefwd/ui/activity/BaseViewModel;", "context", "Landroid/content/Context;", "appRepository", "Lcom/swipefwd/data/source/AppRepository;", "(Landroid/content/Context;Lcom/swipefwd/data/source/AppRepository;)V", "fwdDataStore", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "getFwdDataStore", "()Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "fwdDataStore$delegate", "Lkotlin/Lazy;", "getFirstName", "Lkotlinx/coroutines/flow/Flow;", "", "getGetFirstName", "()Lkotlinx/coroutines/flow/Flow;", "getProfileImage", "getGetProfileImage", "phoneNumber", "getPhoneNumber", "saveData", "", "T", "key", "Landroidx/datastore/preferences/core/Preferences$Key;", "value", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;)V", "app_debug"})
public final class MessageViewModel extends com.swipefwd.ui.activity.BaseViewModel {
    private final com.swipefwd.data.source.AppRepository appRepository = null;
    private final kotlin.Lazy fwdDataStore$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> phoneNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getProfileImage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getFirstName = null;
    
    public MessageViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.source.AppRepository appRepository) {
        super(null);
    }
    
    private final com.swipefwd.utils.datastore.InternalAppDataStore getFwdDataStore() {
        return null;
    }
    
    public final <T extends java.lang.Object>void saveData(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key, T value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getPhoneNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetProfileImage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetFirstName() {
        return null;
    }
}