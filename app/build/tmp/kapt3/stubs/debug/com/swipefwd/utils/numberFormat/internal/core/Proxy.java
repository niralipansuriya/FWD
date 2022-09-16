package com.swipefwd.utils.numberFormat.internal.core;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\bJ\u001c\u0010\r\u001a\u0004\u0018\u00010\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\bJ\u001a\u0010\u0010\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/swipefwd/utils/numberFormat/internal/core/Proxy;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "phoneUtil", "Lio/michaelrocks/libphonenumber/android/PhoneNumberUtil;", "formatPhoneNumber", "", "phoneNumber", "Lio/michaelrocks/libphonenumber/android/Phonenumber$PhoneNumber;", "getExampleNumber", "iso2", "parsePhoneNumber", "number", "defaultRegion", "validateNumber", "", "countryCode", "app_debug"})
public final class Proxy {
    private io.michaelrocks.libphonenumber.android.PhoneNumberUtil phoneUtil;
    
    public Proxy(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final io.michaelrocks.libphonenumber.android.Phonenumber.PhoneNumber parsePhoneNumber(@org.jetbrains.annotations.Nullable()
    java.lang.String number, @org.jetbrains.annotations.Nullable()
    java.lang.String defaultRegion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String formatPhoneNumber(@org.jetbrains.annotations.Nullable()
    io.michaelrocks.libphonenumber.android.Phonenumber.PhoneNumber phoneNumber) {
        return null;
    }
    
    /**
     * Provides an example phone number according to country iso2 code
     */
    @org.jetbrains.annotations.Nullable()
    public final io.michaelrocks.libphonenumber.android.Phonenumber.PhoneNumber getExampleNumber(@org.jetbrains.annotations.Nullable()
    java.lang.String iso2) {
        return null;
    }
    
    public final boolean validateNumber(@org.jetbrains.annotations.Nullable()
    java.lang.String number, @org.jetbrains.annotations.Nullable()
    java.lang.String countryCode) {
        return false;
    }
}