package com.swipefwd.ui.auth.otpverify;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00a2\u00012\u00020\u0001:\u0002\u00a2\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\f\u0010w\u001a\b\u0012\u0004\u0012\u00020\u000e04J\u0012\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001004J\u0006\u0010y\u001a\u00020\"J\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\u000b04J\u0006\u0010{\u001a\u00020\u0016J\f\u0010|\u001a\b\u0012\u0004\u0012\u00020\u001304J\f\u0010}\u001a\b\u0012\u0004\u0012\u00020\u001304J\u0006\u0010~\u001a\u00020\"J\u0006\u0010\u007f\u001a\u00020\"J%\u0010\u0080\u0001\u001a\u00020\"2\u0007\u0010\u0081\u0001\u001a\u00020\u000b2\u0007\u0010\u0082\u0001\u001a\u00020\u0016H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u0083\u0001J\u001c\u0010\u0080\u0001\u001a\u00020\"2\u0007\u0010\u0081\u0001\u001a\u00020\u0013H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0003\u0010\u0084\u0001J\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020\u001604J\u0007\u0010\u0086\u0001\u001a\u00020\"J\r\u0010\u0087\u0001\u001a\b\u0012\u0004\u0012\u00020\u001804J\u0006\u0010f\u001a\u00020\"J\u0006\u0010g\u001a\u00020\"J/\u0010\u0088\u0001\u001a\u00020\"\"\u0005\b\u0000\u0010\u0089\u00012\u000f\u0010\u008a\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0089\u00010\u008b\u00012\b\u0010\u008c\u0001\u001a\u0003H\u0089\u0001\u00a2\u0006\u0003\u0010\u008d\u0001J\r\u0010\u008e\u0001\u001a\b\u0012\u0004\u0012\u00020\u001604J\u0007\u0010\u008f\u0001\u001a\u00020\"J\u0007\u0010\u0090\u0001\u001a\u00020\"J\u0013\u0010\u0091\u0001\u001a\u00020\"2\b\u0010\u0092\u0001\u001a\u00030\u0093\u0001H\u0002J\u0007\u0010\u0094\u0001\u001a\u00020\"J\r\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e04J\r\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u00020\"04J\r\u0010\u0097\u0001\u001a\b\u0012\u0004\u0012\u00020\"04J#\u0010\u0098\u0001\u001a\u00020\"2\u0007\u0010\u0099\u0001\u001a\u00020\u000e2\u0007\u0010\u009a\u0001\u001a\u00020)2\b\u0010\u009b\u0001\u001a\u00030\u009c\u0001J\u0007\u0010\u009d\u0001\u001a\u00020\"J\u0007\u0010\u009e\u0001\u001a\u00020\"J\u0013\u0010\u009f\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001004J\u0013\u0010\u00a0\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001004J\t\u0010\u00a1\u0001\u001a\u00020\"H\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00100\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00100\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00100\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00100\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00100\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u00020)X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R \u00103\u001a\b\u0012\u0004\u0012\u00020\u000b04X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R \u00109\u001a\b\u0012\u0004\u0012\u00020\u000b04X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u00106\"\u0004\b;\u00108R \u0010<\u001a\b\u0012\u0004\u0012\u00020\u000e0=X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001a\u0010B\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u00100\"\u0004\bD\u00102R\u0017\u0010E\u001a\b\u0012\u0004\u0012\u00020\u000e0F\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0010\u0010I\u001a\u0004\u0018\u00010JX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010K\u001a\u0004\u0018\u00010)X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010O\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001a\u0010P\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u000e\u0010T\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010U\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bV\u00100\"\u0004\bW\u00102R\u0010\u0010X\u001a\u0004\u0018\u00010JX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010Y\u001a\u00020)X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010+\"\u0004\b[\u0010-R\u001a\u0010\\\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b]\u00100\"\u0004\b^\u00102R\u0010\u0010_\u001a\u0004\u0018\u00010JX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010`\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\ba\u00100\"\u0004\bb\u00102R\u001a\u0010c\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bd\u00100\"\u0004\be\u00102R\u0010\u0010f\u001a\u0004\u0018\u00010JX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010g\u001a\u0004\u0018\u00010JX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010h\u001a\b\u0012\u0004\u0012\u00020\u000e0F\u00a2\u0006\b\n\u0000\u001a\u0004\bi\u0010HR\u001a\u0010j\u001a\u00020kX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR\u0010\u0010p\u001a\u0004\u0018\u00010qX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bs\u00100\"\u0004\bt\u00102R\u0010\u0010u\u001a\u0004\u0018\u00010JX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010v\u001a\u0004\u0018\u00010JX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u00a3\u0001"}, d2 = {"Lcom/swipefwd/ui/auth/otpverify/OtpViewModel;", "Lcom/swipefwd/base/AppBaseViewModel;", "appRepository", "Lcom/swipefwd/data/source/AppRepository;", "appPreferences", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "appDataBase", "Lcom/swipefwd/utils/AppDatabase;", "(Lcom/swipefwd/data/source/AppRepository;Lcom/swipefwd/utils/datastore/InternalAppDataStore;Lcom/swipefwd/utils/AppDatabase;)V", "_dataEmail", "Landroidx/lifecycle/MutableLiveData;", "LOTPModel;", "_emailError", "_errorMessage", "", "_getOTPData", "Lcom/swipefwd/base/ResultState;", "_getOtpError", "_loginData", "Lcom/swipefwd/data/models/LoginResponseModel;", "_loginError", "_phoneNumberSuspended", "", "_recoverAccountData", "Lcom/swipefwd/data/models/RecoverAccountModel;", "_resendOtp", "Lcom/swipefwd/data/models/ResendOtp;", "_resendOtpEmail", "_settingsData", "Lcom/swipefwd/data/models/CommonResponseModel;", "_settingsError", "_showLoading", "_timerRefreshed", "_timerStopped", "", "_userRegistrationRequired", "_verifyOtpResponseData", "_verifyOtpResponseEmailData", "getAppPreferences", "()Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "counter", "", "getCounter", "()I", "setCounter", "(I)V", "countryCode", "getCountryCode", "()Ljava/lang/String;", "setCountryCode", "(Ljava/lang/String;)V", "dataEmail", "Landroidx/lifecycle/LiveData;", "getDataEmail", "()Landroidx/lifecycle/LiveData;", "setDataEmail", "(Landroidx/lifecycle/LiveData;)V", "emailError", "getEmailError", "setEmailError", "facebookIds", "Ljava/util/ArrayList;", "getFacebookIds", "()Ljava/util/ArrayList;", "setFacebookIds", "(Ljava/util/ArrayList;)V", "firstName", "getFirstName", "setFirstName", "getFbIds", "Lkotlinx/coroutines/flow/Flow;", "getGetFbIds", "()Lkotlinx/coroutines/flow/Flow;", "getOtpJob", "Lkotlinx/coroutines/Job;", "isConnection", "()Ljava/lang/Integer;", "setConnection", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "isOtpExpired", "()Z", "setOtpExpired", "(Z)V", "isPhoneNumberSuspended", "isoCode", "getIsoCode", "setIsoCode", "loginCoroutineJob", "noOfTimeWrongOtpSubmit", "getNoOfTimeWrongOtpSubmit", "setNoOfTimeWrongOtpSubmit", "phoneNumber", "getPhoneNumber", "setPhoneNumber", "recoverAccountCoroutineJob", "recoverEmail", "getRecoverEmail", "setRecoverEmail", "recoverPhoneNumber", "getRecoverPhoneNumber", "setRecoverPhoneNumber", "resendOtp", "resendOtpEmail", "socialMediaUser", "getSocialMediaUser", "socialUser", "Lcom/swipefwd/data/models/SocialMediaUserModel;", "getSocialUser", "()Lcom/swipefwd/data/models/SocialMediaUserModel;", "setSocialUser", "(Lcom/swipefwd/data/models/SocialMediaUserModel;)V", "timer", "Landroid/os/CountDownTimer;", "userFilledOtp", "getUserFilledOtp", "setUserFilledOtp", "verifyOtpEmailJob", "verifyOtpJob", "errorMessage", "getOTPData", "getOtp", "getOtpError", "isWrongOtpFillLimitExceed", "loginData", "loginError", "loginUser", "loginUserWithEmailAndMobile", "onLoginSuccessResult", "loginModel", "isDeepLink", "(LOTPModel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/swipefwd/data/models/LoginResponseModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "phoneNumberSuspended", "recoverAccount", "recoverAccountData", "saveLoginData", "T", "key", "Landroidx/datastore/preferences/core/Preferences$Key;", "value", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;)V", "showLoading", "startResendCodeTimer", "startSuspendedPhoneNumberTimer", "startTimer", "timerSecond", "", "stopTimer", "timerRefreshed", "timerStopped", "userRegistrationRequired", "userSettingsUpdate", "token", "id", "jsonObject", "Lcom/google/gson/JsonObject;", "verifyOtp", "verifyOtpEmail", "verifyOtpEmailResponseData", "verifyOtpResponseData", "wrongOtpSubmitResponse", "Companion", "app_debug"})
public final class OtpViewModel extends com.swipefwd.base.AppBaseViewModel {
    private final com.swipefwd.data.source.AppRepository appRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.swipefwd.utils.datastore.InternalAppDataStore appPreferences = null;
    private final com.swipefwd.utils.AppDatabase appDataBase = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.auth.otpverify.OtpViewModel.Companion Companion = null;
    private static final java.lang.String TAG = "OtpViewModel";
    public static final int MAX_WRONG_OTP_FILL_CHANCE = 2;
    private static final long RESEND_TIME_OUT = 30000L;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String firstName = "";
    private int counter = 1;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String countryCode = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String phoneNumber = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String isoCode = "";
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer isConnection = 0;
    @org.jetbrains.annotations.NotNull()
    private com.swipefwd.data.models.SocialMediaUserModel socialUser;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<java.lang.String> facebookIds;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String recoverEmail = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String recoverPhoneNumber = "";
    private boolean isOtpExpired = false;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String userFilledOtp;
    
    /**
     * wrong otp submitted count
     */
    private int noOfTimeWrongOtpSubmit = 0;
    private boolean isPhoneNumberSuspended = false;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _phoneNumberSuspended;
    private androidx.lifecycle.MutableLiveData<OTPModel> _dataEmail;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<OTPModel> dataEmail;
    private androidx.lifecycle.MutableLiveData<OTPModel> _emailError;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<OTPModel> emailError;
    private android.os.CountDownTimer timer;
    private androidx.lifecycle.MutableLiveData<java.lang.String> _timerRefreshed;
    private androidx.lifecycle.MutableLiveData<kotlin.Unit> _timerStopped;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getFbIds = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> socialMediaUser = null;
    private androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _showLoading;
    private androidx.lifecycle.MutableLiveData<OTPModel> _getOtpError;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.LoginResponseModel> _loginError;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.CommonResponseModel> _settingsData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.CommonResponseModel> _settingsError;
    private androidx.lifecycle.MutableLiveData<kotlin.Unit> _userRegistrationRequired;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<OTPModel>> _getOTPData;
    private kotlinx.coroutines.Job getOtpJob;
    private kotlinx.coroutines.Job resendOtp;
    private kotlinx.coroutines.Job resendOtpEmail;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<OTPModel>> _verifyOtpResponseData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<OTPModel>> _verifyOtpResponseEmailData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<com.swipefwd.data.models.ResendOtp>> _resendOtp;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.base.ResultState<com.swipefwd.data.models.ResendOtp>> _resendOtpEmail;
    private kotlinx.coroutines.Job verifyOtpJob;
    private kotlinx.coroutines.Job verifyOtpEmailJob;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.LoginResponseModel> _loginData;
    private kotlinx.coroutines.Job loginCoroutineJob;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.RecoverAccountModel> _recoverAccountData;
    private kotlinx.coroutines.Job recoverAccountCoroutineJob;
    
    public OtpViewModel(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.source.AppRepository appRepository, @org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.datastore.InternalAppDataStore appPreferences, @org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.AppDatabase appDataBase) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.utils.datastore.InternalAppDataStore getAppPreferences() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFirstName() {
        return null;
    }
    
    public final void setFirstName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getCounter() {
        return 0;
    }
    
    public final void setCounter(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCountryCode() {
        return null;
    }
    
    public final void setCountryCode(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPhoneNumber() {
        return null;
    }
    
    public final void setPhoneNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIsoCode() {
        return null;
    }
    
    public final void setIsoCode(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer isConnection() {
        return null;
    }
    
    public final void setConnection(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.SocialMediaUserModel getSocialUser() {
        return null;
    }
    
    public final void setSocialUser(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.SocialMediaUserModel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<java.lang.String> getFacebookIds() {
        return null;
    }
    
    public final void setFacebookIds(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRecoverEmail() {
        return null;
    }
    
    public final void setRecoverEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRecoverPhoneNumber() {
        return null;
    }
    
    public final void setRecoverPhoneNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final boolean isOtpExpired() {
        return false;
    }
    
    public final void setOtpExpired(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUserFilledOtp() {
        return null;
    }
    
    public final void setUserFilledOtp(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final int getNoOfTimeWrongOtpSubmit() {
        return 0;
    }
    
    public final void setNoOfTimeWrongOtpSubmit(int p0) {
    }
    
    public final boolean isWrongOtpFillLimitExceed() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> phoneNumberSuspended() {
        return null;
    }
    
    private final void wrongOtpSubmitResponse() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<OTPModel> getDataEmail() {
        return null;
    }
    
    public final void setDataEmail(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<OTPModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<OTPModel> getEmailError() {
        return null;
    }
    
    public final void setEmailError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<OTPModel> p0) {
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
    
    public final void startResendCodeTimer() {
    }
    
    public final void startSuspendedPhoneNumberTimer() {
    }
    
    public final void stopTimer() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetFbIds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getSocialMediaUser() {
        return null;
    }
    
    public final <T extends java.lang.Object>void saveLoginData(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key, T value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> errorMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> showLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<OTPModel> getOtpError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.LoginResponseModel> loginError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> userRegistrationRequired() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<OTPModel>> getOTPData() {
        return null;
    }
    
    public final void getOtp() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<OTPModel>> verifyOtpResponseData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.base.ResultState<OTPModel>> verifyOtpEmailResponseData() {
        return null;
    }
    
    public final void verifyOtp() {
    }
    
    public final void verifyOtpEmail() {
    }
    
    public final void loginUserWithEmailAndMobile() {
    }
    
    public final void resendOtp() {
    }
    
    public final void resendOtpEmail() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.LoginResponseModel> loginData() {
        return null;
    }
    
    public final void loginUser() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.RecoverAccountModel> recoverAccountData() {
        return null;
    }
    
    public final void recoverAccount() {
    }
    
    private final java.lang.Object onLoginSuccessResult(com.swipefwd.data.models.LoginResponseModel loginModel, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object onLoginSuccessResult(OTPModel loginModel, boolean isDeepLink, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void userSettingsUpdate(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int id, @org.jetbrains.annotations.NotNull()
    com.google.gson.JsonObject jsonObject) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/swipefwd/ui/auth/otpverify/OtpViewModel$Companion;", "", "()V", "MAX_WRONG_OTP_FILL_CHANCE", "", "RESEND_TIME_OUT", "", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}