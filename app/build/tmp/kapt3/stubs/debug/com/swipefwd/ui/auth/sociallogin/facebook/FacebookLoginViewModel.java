package com.swipefwd.ui.auth.sociallogin.facebook;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u0019\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/facebook/FacebookLoginViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_userDetailsData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/swipefwd/base/ResultState;", "Lcom/swipefwd/ui/auth/sociallogin/facebook/FacebookUserDetails;", "friendPermissionStatus", "", "loginResult", "Lcom/facebook/login/LoginResult;", "createUserDetails", "", "getFacebookProfileUrl", "", "clientId", "getFacebookUserDetails", "accessToken", "Lcom/facebook/AccessToken;", "(Lcom/facebook/AccessToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setLoginResult", "userDetails", "Landroidx/lifecycle/LiveData;", "Companion", "app_debug"})
public final class FacebookLoginViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.auth.sociallogin.facebook.FacebookLoginViewModel.Companion Companion = null;
    private static final java.lang.String TAG = "facebook_login";
    private boolean friendPermissionStatus = true;
    private com.facebook.login.LoginResult loginResult;
    private final androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<com.swipefwd.ui.auth.sociallogin.facebook.FacebookUserDetails>> _userDetailsData = null;
    
    public FacebookLoginViewModel() {
        super();
    }
    
    public final void setLoginResult(@org.jetbrains.annotations.Nullable()
    com.facebook.login.LoginResult loginResult) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<com.swipefwd.ui.auth.sociallogin.facebook.FacebookUserDetails>> userDetails() {
        return null;
    }
    
    public final void createUserDetails() {
    }
    
    /**
     * this function request the user details from facebook based on the access token
     */
    private final java.lang.Object getFacebookUserDetails(com.facebook.AccessToken accessToken, kotlin.coroutines.Continuation<? super com.swipefwd.ui.auth.sociallogin.facebook.FacebookUserDetails> continuation) {
        return null;
    }
    
    private final java.lang.String getFacebookProfileUrl(java.lang.String clientId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/facebook/FacebookLoginViewModel$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}