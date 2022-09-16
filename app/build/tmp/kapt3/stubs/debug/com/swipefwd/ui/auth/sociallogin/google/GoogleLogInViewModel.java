package com.swipefwd.ui.auth.sociallogin.google;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u0010J\u0012\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\u001fR\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/google/GoogleLogInViewModel;", "Lcom/swipefwd/base/AppBaseViewModel;", "appRepository", "Lcom/swipefwd/data/source/AppRepository;", "internalAppDataStore", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "appDatabase", "Lcom/swipefwd/utils/AppDatabase;", "(Lcom/swipefwd/data/source/AppRepository;Lcom/swipefwd/utils/datastore/InternalAppDataStore;Lcom/swipefwd/utils/AppDatabase;)V", "_socialData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/swipefwd/base/ResultState;", "LOTPModel;", "_userDetailsData", "Lcom/swipefwd/ui/auth/sociallogin/google/GoogleUserDetails;", "googleAccountDetails", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "googleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "getGoogleSignInClient", "()Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "setGoogleSignInClient", "(Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;)V", "createUserDetails", "", "googleLogin", "email", "", "setAccountDetails", "googleSignInAccount", "userDetails", "Landroidx/lifecycle/LiveData;", "app_debug"})
public final class GoogleLogInViewModel extends com.swipefwd.base.AppBaseViewModel {
    private final com.swipefwd.data.source.AppRepository appRepository = null;
    private final com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStore = null;
    private final com.swipefwd.utils.AppDatabase appDatabase = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.auth.api.signin.GoogleSignInClient googleSignInClient;
    private com.google.android.gms.auth.api.signin.GoogleSignInAccount googleAccountDetails;
    private final androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<com.swipefwd.ui.auth.sociallogin.google.GoogleUserDetails>> _userDetailsData = null;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<OTPModel>> _socialData;
    
    public GoogleLogInViewModel(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.source.AppRepository appRepository, @org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStore, @org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.AppDatabase appDatabase) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.android.gms.auth.api.signin.GoogleSignInClient getGoogleSignInClient() {
        return null;
    }
    
    public final void setGoogleSignInClient(@org.jetbrains.annotations.Nullable()
    com.google.android.gms.auth.api.signin.GoogleSignInClient p0) {
    }
    
    public final void setAccountDetails(@org.jetbrains.annotations.Nullable()
    com.google.android.gms.auth.api.signin.GoogleSignInAccount googleSignInAccount) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<com.swipefwd.ui.auth.sociallogin.google.GoogleUserDetails>> userDetails() {
        return null;
    }
    
    public final void createUserDetails() {
    }
    
    public final void googleLogin(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
}