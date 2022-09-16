package com.swipefwd.ui.auth.phone;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u0000 \\2\u00020\u0001:\u0001\\B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\u0010J\u000e\u0010A\u001a\u00020\u00122\u0006\u0010B\u001a\u00020\u0014J\u0006\u0010C\u001a\u00020\u0012J\u0012\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0EJ\u0012\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0EJ\b\u0010G\u001a\u0004\u0018\u00010\u0010J\u0006\u0010H\u001a\u00020\u0014J\u0011\u0010I\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010JJ\u0006\u0010K\u001a\u00020\u0014J\'\u0010L\u001a\u00020\u0012\"\u0004\b\u0000\u0010M2\f\u0010N\u001a\b\u0012\u0004\u0012\u0002HM0O2\u0006\u0010P\u001a\u0002HM\u00a2\u0006\u0002\u0010QJ\u0010\u0010R\u001a\u00020\u00122\b\u0010S\u001a\u0004\u0018\u00010\u0010J\u0010\u0010T\u001a\u00020\u00122\u0006\u0010U\u001a\u00020VH\u0002J\u0006\u0010W\u001a\u00020\u0012J\u000e\u0010X\u001a\u00020\u00122\u0006\u0010Y\u001a\u00020\fJ\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u00100EJ\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00120ER\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0016\"\u0004\b%\u0010\u0018R\u001c\u0010&\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u001b\"\u0004\b(\u0010\u001dR\u001a\u0010)\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001b\"\u0004\b+\u0010\u001dR\u000e\u0010,\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100.X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001b\"\u0004\b1\u0010\u001dR\u0010\u00102\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u00103\u001a\u000204X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u001b\"\u0004\b;\u0010\u001dR\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006]"}, d2 = {"Lcom/swipefwd/ui/auth/phone/PhoneNumberViewModel;", "Lcom/swipefwd/base/AppBaseViewModel;", "appRepository", "Lcom/swipefwd/data/source/AppRepository;", "internalAppDataStore", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "appDatabase", "Lcom/swipefwd/utils/AppDatabase;", "(Lcom/swipefwd/data/source/AppRepository;Lcom/swipefwd/utils/datastore/InternalAppDataStore;Lcom/swipefwd/utils/AppDatabase;)V", "_getOtpData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/swipefwd/base/ResultState;", "LOTPModel;", "_getOtpEmailData", "Lcom/swipefwd/data/models/ResendOtp;", "_timerRefreshed", "", "_timerStopped", "", "backpress", "", "getBackpress", "()Z", "setBackpress", "(Z)V", "countryCode", "getCountryCode", "()Ljava/lang/String;", "setCountryCode", "(Ljava/lang/String;)V", "firstName", "getFirstName", "setFirstName", "getOtpEmailJob", "Lkotlinx/coroutines/Job;", "getOtpJob", "isFromDeepLink", "setFromDeepLink", "isoCode", "getIsoCode", "setIsoCode", "phoneNumber", "getPhoneNumber", "setPhoneNumber", "phoneNumberSuspendedStatus", "previousFilledPhoneNumber", "Lkotlinx/coroutines/flow/Flow;", "previousNumber", "getPreviousNumber", "setPreviousNumber", "recoveryEmailId", "signUpType", "", "getSignUpType", "()I", "setSignUpType", "(I)V", "socialId", "getSocialId", "setSocialId", "timer", "Landroid/os/CountDownTimer;", "checkIfPhoneSuspend", "timestampTimeout", "globalTimeStampTimeout", "getOtp", "isSocialLogin", "getOtpEamil", "getOtpEmailResult", "Landroidx/lifecycle/LiveData;", "getOtpResult", "getRecoveryEmail", "isForRecoverAccount", "isPhoneDifferentFromPreviousFilled", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isPhoneNumberSuspend", "saveData", "T", "key", "Landroidx/datastore/preferences/core/Preferences$Key;", "value", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;)V", "setRecoverEmailId", "id", "startTimer", "timerSecond", "", "stopTimer", "storeData", "otpModel", "timerRefreshed", "timerStopped", "Companion", "app_debug"})
public final class PhoneNumberViewModel extends com.swipefwd.base.AppBaseViewModel {
    private final com.swipefwd.data.source.AppRepository appRepository = null;
    private final com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStore = null;
    private final com.swipefwd.utils.AppDatabase appDatabase = null;
    private boolean isFromDeepLink = false;
    private java.lang.String recoveryEmailId;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String firstName = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String phoneNumber = "";
    @org.jetbrains.annotations.Nullable()
    private java.lang.String countryCode;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String isoCode;
    private boolean backpress = false;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String previousNumber = "";
    private int signUpType = 0;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String socialId = "";
    private final kotlinx.coroutines.flow.Flow<java.lang.String> previousFilledPhoneNumber = null;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<OTPModel>> _getOtpData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<com.swipefwd.data.models.ResendOtp>> _getOtpEmailData;
    private kotlinx.coroutines.Job getOtpJob;
    private kotlinx.coroutines.Job getOtpEmailJob;
    private boolean phoneNumberSuspendedStatus = false;
    private android.os.CountDownTimer timer;
    private androidx.lifecycle.MutableLiveData<java.lang.String> _timerRefreshed;
    private androidx.lifecycle.MutableLiveData<kotlin.Unit> _timerStopped;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.auth.phone.PhoneNumberViewModel.Companion Companion = null;
    
    public PhoneNumberViewModel(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.source.AppRepository appRepository, @org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.datastore.InternalAppDataStore internalAppDataStore, @org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.AppDatabase appDatabase) {
        super(null, null);
    }
    
    public final boolean isFromDeepLink() {
        return false;
    }
    
    public final void setFromDeepLink(boolean p0) {
    }
    
    public final void setRecoverEmailId(@org.jetbrains.annotations.Nullable()
    java.lang.String id) {
    }
    
    public final boolean isForRecoverAccount() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getRecoveryEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFirstName() {
        return null;
    }
    
    public final void setFirstName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPhoneNumber() {
        return null;
    }
    
    public final void setPhoneNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCountryCode() {
        return null;
    }
    
    public final void setCountryCode(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getIsoCode() {
        return null;
    }
    
    public final void setIsoCode(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final boolean getBackpress() {
        return false;
    }
    
    public final void setBackpress(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPreviousNumber() {
        return null;
    }
    
    public final void setPreviousNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getSignUpType() {
        return 0;
    }
    
    public final void setSignUpType(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSocialId() {
        return null;
    }
    
    public final void setSocialId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final <T extends java.lang.Object>void saveData(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key, T value) {
    }
    
    /**
     * this will check if previous filled phone number is same as current phone number
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isPhoneDifferentFromPreviousFilled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<OTPModel>> getOtpResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<com.swipefwd.data.models.ResendOtp>> getOtpEmailResult() {
        return null;
    }
    
    public final void getOtp(boolean isSocialLogin) {
    }
    
    public final void getOtpEamil() {
    }
    
    public final void storeData(@org.jetbrains.annotations.NotNull()
    OTPModel otpModel) {
    }
    
    public final boolean isPhoneNumberSuspend() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> timerRefreshed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> timerStopped() {
        return null;
    }
    
    private final void startTimer(long timerSecond) {
    }
    
    public final void checkIfPhoneSuspend(@org.jetbrains.annotations.NotNull()
    java.lang.String timestampTimeout, @org.jetbrains.annotations.NotNull()
    java.lang.String globalTimeStampTimeout) {
    }
    
    public final void stopTimer() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/swipefwd/ui/auth/phone/PhoneNumberViewModel$Companion;", "", "()V", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}