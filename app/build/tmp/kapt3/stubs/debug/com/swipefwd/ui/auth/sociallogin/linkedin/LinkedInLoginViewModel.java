package com.swipefwd.ui.auth.sociallogin.linkedin;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0012R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/linkedin/LinkedInLoginViewModel;", "Landroidx/lifecycle/ViewModel;", "appRepository", "Lcom/swipefwd/data/source/AppRepository;", "(Lcom/swipefwd/data/source/AppRepository;)V", "_userDetailsData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/swipefwd/base/ResultState;", "Lcom/swipefwd/ui/auth/sociallogin/linkedin/LinkedInUserDetails;", "linkedInTokenRequestJob", "Lkotlinx/coroutines/Job;", "getLinkedInProfileDetails", "", "code", "", "getResolvedAccessToken", "token", "userDetails", "Landroidx/lifecycle/LiveData;", "Companion", "app_debug"})
public final class LinkedInLoginViewModel extends androidx.lifecycle.ViewModel {
    private final com.swipefwd.data.source.AppRepository appRepository = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInLoginViewModel.Companion Companion = null;
    private static final java.lang.String TAG = "LinkedInLoginViewModel";
    private final androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInUserDetails>> _userDetailsData = null;
    private kotlinx.coroutines.Job linkedInTokenRequestJob;
    
    public LinkedInLoginViewModel(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.source.AppRepository appRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<com.swipefwd.ui.auth.sociallogin.linkedin.LinkedInUserDetails>> userDetails() {
        return null;
    }
    
    public final void getLinkedInProfileDetails(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
    }
    
    private final java.lang.String getResolvedAccessToken(java.lang.String token) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/linkedin/LinkedInLoginViewModel$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}