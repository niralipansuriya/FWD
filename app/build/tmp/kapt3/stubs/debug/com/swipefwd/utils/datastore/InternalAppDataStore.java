package com.swipefwd.utils.datastore;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u0000 %2\u00020\u0001:\u0001%B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0013\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eJ\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00100\u000eJ\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00120\u000eJ\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eJ\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00160\u000eJ\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00180\u000eJ\u0011\u0010\u0019\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ%\u0010\u001b\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u001c2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u0011\u0010\u001e\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ-\u0010\u001f\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u001c2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u000e2\u0006\u0010 \u001a\u0002H\u001cH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0019\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006&"}, d2 = {"Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "fetchSuspendedPhoneNumber", "Lcom/swipefwd/data/models/SuspendedPhoneNumber;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBoolean", "Lkotlinx/coroutines/flow/Flow;", "", "key", "Landroidx/datastore/preferences/core/Preferences$Key;", "getDouble", "", "getFloat", "", "getInt", "", "getLong", "", "getString", "", "removeAll", "", "removePreference", "T", "(Landroidx/datastore/preferences/core/Preferences$Key;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeSuspendedPhoneNumber", "savePreference", "value", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveSuspendedPhoneNumber", "data", "(Lcom/swipefwd/data/models/SuspendedPhoneNumber;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class InternalAppDataStore {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.datastore.InternalAppDataStore.Companion Companion = null;
    private static final java.lang.String KEY_SUSPENDED_PHONE_NUMBER = "key.phone.suspended";
    
    public InternalAppDataStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getInt(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> key) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Long> getLong(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<java.lang.Long> key) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Float> getFloat(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<java.lang.Float> key) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Double> getDouble(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<java.lang.Double> key) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getBoolean(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> key) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getString(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<java.lang.String> key) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final <T extends java.lang.Object>java.lang.Object savePreference(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key, T value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final <T extends java.lang.Object>java.lang.Object removePreference(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchSuspendedPhoneNumber(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.swipefwd.data.models.SuspendedPhoneNumber> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveSuspendedPhoneNumber(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.SuspendedPhoneNumber data, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeSuspendedPhoneNumber(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * ###############################################################################
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/utils/datastore/InternalAppDataStore$Companion;", "", "()V", "KEY_SUSPENDED_PHONE_NUMBER", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}