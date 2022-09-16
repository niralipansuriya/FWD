package com.swipefwd.data.models;

import java.lang.System;

/**
 * this class will hold the phone number that is suspended for get otp after
 * filling invalid otp 3 times
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\bH\u00c6\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0015\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\u0016\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001d"}, d2 = {"Lcom/swipefwd/data/models/SuspendedPhoneNumber;", "", "countryCode", "", "phoneNumber", "suspendStartTime", "", "isFiveMins", "", "(Ljava/lang/String;Ljava/lang/String;JZ)V", "getCountryCode", "()Ljava/lang/String;", "()Z", "getPhoneNumber", "getSuspendStartTime", "()J", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "isEqual", "_phoneNumber", "_countryCode", "toString", "app_debug"})
public final class SuspendedPhoneNumber {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String countryCode = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String phoneNumber = null;
    private final long suspendStartTime = 0L;
    private final boolean isFiveMins = false;
    
    /**
     * this class will hold the phone number that is suspended for get otp after
     * filling invalid otp 3 times
     */
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.SuspendedPhoneNumber copy(@org.jetbrains.annotations.NotNull()
    java.lang.String countryCode, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, long suspendStartTime, boolean isFiveMins) {
        return null;
    }
    
    /**
     * this class will hold the phone number that is suspended for get otp after
     * filling invalid otp 3 times
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    /**
     * this class will hold the phone number that is suspended for get otp after
     * filling invalid otp 3 times
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * this class will hold the phone number that is suspended for get otp after
     * filling invalid otp 3 times
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public SuspendedPhoneNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String countryCode, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, long suspendStartTime, boolean isFiveMins) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCountryCode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPhoneNumber() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long getSuspendStartTime() {
        return 0L;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean isFiveMins() {
        return false;
    }
    
    public final boolean isEqual(@org.jetbrains.annotations.NotNull()
    java.lang.String _phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String _countryCode) {
        return false;
    }
}