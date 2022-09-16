package com.swipefwd.utils.numberFormat;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 H2\u00020\u0001:\u0002GHB3\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\nJ\u0018\u0010)\u001a\u00020*2\u0006\u0010\u000e\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\rH\u0002J\u0016\u0010)\u001a\u00020*2\u0006\u0010\u000e\u001a\u00020\u00102\u0006\u0010,\u001a\u00020-J\u0016\u0010)\u001a\u00020*2\u0006\u0010\u000e\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\bJ\b\u0010/\u001a\u00020*H\u0002J\b\u00100\u001a\u000201H\u0002J\u001c\u00102\u001a\u0004\u0018\u00010\b2\b\u00103\u001a\u0004\u0018\u00010\b2\b\u00104\u001a\u0004\u0018\u00010\bJ\u0017\u00105\u001a\b\u0012\u0004\u0012\u00020\r0\u0007H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00106J\u0012\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010\bJ\u0012\u0010:\u001a\u0004\u0018\u00010;2\b\u00109\u001a\u0004\u0018\u00010\bJ\u0010\u0010<\u001a\u0004\u0018\u00010\b2\u0006\u0010=\u001a\u00020\bJ\u001c\u0010>\u001a\u0004\u0018\u0001082\b\u00103\u001a\u0004\u0018\u00010\b2\b\u00104\u001a\u0004\u0018\u00010\bJ\u0010\u0010?\u001a\u00020*2\u0006\u0010+\u001a\u00020\rH\u0002J\u0010\u0010?\u001a\u0002012\u0006\u0010.\u001a\u00020\bH\u0002J\u0018\u0010@\u001a\u00020*2\u0006\u0010A\u001a\u00020\u00102\u0006\u0010B\u001a\u00020\bH\u0002J\u0012\u0010C\u001a\u00020\u00052\b\u00103\u001a\u0004\u0018\u00010\u0012H\u0002J\u0016\u0010D\u001a\u00020\u00052\u0006\u00103\u001a\u00020\b2\u0006\u0010=\u001a\u00020\bJ#\u0010E\u001a\u0004\u0018\u00010\r*\b\u0012\u0004\u0012\u00020\r0\u00072\b\u0010=\u001a\u0004\u0018\u00010-H\u0002\u00a2\u0006\u0002\u0010FJ\u001e\u0010E\u001a\u0004\u0018\u00010\r*\b\u0012\u0004\u0012\u00020\r0\u00072\b\u0010.\u001a\u0004\u0018\u00010\bH\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R(\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u00128B@BX\u0082\u000e\u00a2\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u00058F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010 \u001a\u00020!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\'\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006I"}, d2 = {"Lcom/swipefwd/utils/numberFormat/PhoneNumberKit;", "", "context", "Landroid/content/Context;", "isIconEnabled", "", "excludedCountries", "", "", "admittedCountries", "(Landroid/content/Context;ZLjava/util/List;Ljava/util/List;)V", "countriesCache", "", "Lcom/swipefwd/utils/numberFormat/api/Country;", "input", "Ljava/lang/ref/WeakReference;", "Landroid/widget/EditText;", "value", "", "inputValue", "getInputValue", "()Ljava/lang/CharSequence;", "setInputValue", "(Ljava/lang/CharSequence;)V", "isValid", "()Z", "proxy", "Lcom/swipefwd/utils/numberFormat/internal/core/Proxy;", "getProxy", "()Lcom/swipefwd/utils/numberFormat/internal/core/Proxy;", "proxy$delegate", "Lkotlin/Lazy;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/swipefwd/utils/numberFormat/internal/model/State;", "supervisorJob", "Lkotlinx/coroutines/CompletableJob;", "textChangedListener", "Lcom/redmadrobot/inputmask/MaskedTextChangedListener;", "attachToInput", "", "country", "defaultCountry", "", "countryIso2", "clearInputValue", "collectState", "Lkotlinx/coroutines/Job;", "formatPhoneNumber", "number", "defaultRegion", "getCountries", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExampleNumber", "Lcom/swipefwd/utils/numberFormat/api/Phone;", "iso2", "getFlagIcon", "Landroid/graphics/drawable/Drawable;", "getFormattedNumber", "countryCode", "parsePhoneNumber", "setCountry", "setupListener", "editText", "pattern", "validate", "validateNumber", "findCountry", "(Ljava/util/List;Ljava/lang/Integer;)Lcom/swipefwd/utils/numberFormat/api/Country;", "Builder", "Companion", "app_debug"})
public final class PhoneNumberKit {
    private final android.content.Context context = null;
    private final boolean isIconEnabled = false;
    private final java.util.List<java.lang.String> excludedCountries = null;
    private final java.util.List<java.lang.String> admittedCountries = null;
    private final kotlinx.coroutines.CompletableJob supervisorJob = null;
    private final kotlinx.coroutines.CoroutineScope scope = null;
    private final kotlin.Lazy proxy$delegate = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.swipefwd.utils.numberFormat.internal.model.State> state = null;
    private java.lang.ref.WeakReference<android.widget.EditText> input;
    private final java.util.List<com.swipefwd.utils.numberFormat.api.Country> countriesCache = null;
    private com.redmadrobot.inputmask.MaskedTextChangedListener textChangedListener;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.numberFormat.PhoneNumberKit.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ASSET_FILE_NAME = "countries.json";
    
    private PhoneNumberKit(android.content.Context context, boolean isIconEnabled, java.util.List<java.lang.String> excludedCountries, java.util.List<java.lang.String> admittedCountries) {
        super();
    }
    
    private final com.swipefwd.utils.numberFormat.internal.core.Proxy getProxy() {
        return null;
    }
    
    private final java.lang.CharSequence getInputValue() {
        return null;
    }
    
    private final void setInputValue(java.lang.CharSequence value) {
    }
    
    public final boolean isValid() {
        return false;
    }
    
    private final void setupListener(android.widget.EditText editText, java.lang.String pattern) {
    }
    
    private final kotlinx.coroutines.Job setCountry(java.lang.String countryIso2) {
        return null;
    }
    
    private final void setCountry(com.swipefwd.utils.numberFormat.api.Country country) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFormattedNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String countryCode) {
        return null;
    }
    
    public final void attachToInput(@org.jetbrains.annotations.NotNull()
    android.widget.EditText input, int defaultCountry) {
    }
    
    public final void attachToInput(@org.jetbrains.annotations.NotNull()
    android.widget.EditText input, @org.jetbrains.annotations.NotNull()
    java.lang.String countryIso2) {
    }
    
    private final kotlinx.coroutines.Job collectState() {
        return null;
    }
    
    private final java.lang.Object getCountries(kotlin.coroutines.Continuation<? super java.util.List<com.swipefwd.utils.numberFormat.api.Country>> continuation) {
        return null;
    }
    
    private final void clearInputValue() {
    }
    
    private final void attachToInput(android.widget.EditText input, com.swipefwd.utils.numberFormat.api.Country country) {
    }
    
    /**
     * Parses raw phone number into phone object
     */
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.utils.numberFormat.api.Phone parsePhoneNumber(@org.jetbrains.annotations.Nullable()
    java.lang.String number, @org.jetbrains.annotations.Nullable()
    java.lang.String defaultRegion) {
        return null;
    }
    
    /**
     * Formats raw phone number into international phone
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String formatPhoneNumber(@org.jetbrains.annotations.Nullable()
    java.lang.String number, @org.jetbrains.annotations.Nullable()
    java.lang.String defaultRegion) {
        return null;
    }
    
    /**
     * Provides an example phone number according to country iso2 code
     */
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.utils.numberFormat.api.Phone getExampleNumber(@org.jetbrains.annotations.Nullable()
    java.lang.String iso2) {
        return null;
    }
    
    /**
     * Provides country flag icon for given country iso2 code
     */
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.drawable.Drawable getFlagIcon(@org.jetbrains.annotations.Nullable()
    java.lang.String iso2) {
        return null;
    }
    
    private final com.swipefwd.utils.numberFormat.api.Country findCountry(java.util.List<com.swipefwd.utils.numberFormat.api.Country> $this$findCountry, java.lang.Integer countryCode) {
        return null;
    }
    
    private final com.swipefwd.utils.numberFormat.api.Country findCountry(java.util.List<com.swipefwd.utils.numberFormat.api.Country> $this$findCountry, java.lang.String countryIso2) {
        return null;
    }
    
    private final boolean validate(java.lang.CharSequence number) {
        return false;
    }
    
    public final boolean validateNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String number, @org.jetbrains.annotations.NotNull()
    java.lang.String countryCode) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u000b\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u000f\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\nR\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/swipefwd/utils/numberFormat/PhoneNumberKit$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "admittedCountries", "", "", "excludedCountries", "isIconEnabled", "", "admitCountries", "countries", "build", "Lcom/swipefwd/utils/numberFormat/PhoneNumberKit;", "excludeCountries", "setIconEnabled", "isEnabled", "app_debug"})
    public static final class Builder {
        private final android.content.Context context = null;
        private boolean isIconEnabled = true;
        private java.util.List<java.lang.String> excludedCountries;
        private java.util.List<java.lang.String> admittedCountries;
        
        public Builder(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.numberFormat.PhoneNumberKit.Builder setIconEnabled(boolean isEnabled) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.numberFormat.PhoneNumberKit.Builder excludeCountries(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> countries) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.numberFormat.PhoneNumberKit.Builder admitCountries(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> countries) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.numberFormat.PhoneNumberKit build() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/utils/numberFormat/PhoneNumberKit$Companion;", "", "()V", "ASSET_FILE_NAME", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}