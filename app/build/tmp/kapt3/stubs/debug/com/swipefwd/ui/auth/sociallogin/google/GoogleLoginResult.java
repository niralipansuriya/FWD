package com.swipefwd.ui.auth.sociallogin.google;

import java.lang.System;

@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0007H\u00d6\u0001J\u0019\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001f"}, d2 = {"Lcom/swipefwd/ui/auth/sociallogin/google/GoogleLoginResult;", "Landroid/os/Parcelable;", "resultStatus", "", "profileDetails", "Lcom/swipefwd/ui/auth/sociallogin/google/GoogleUserDetails;", "message", "", "(ZLcom/swipefwd/ui/auth/sociallogin/google/GoogleUserDetails;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getProfileDetails", "()Lcom/swipefwd/ui/auth/sociallogin/google/GoogleUserDetails;", "getResultStatus", "()Z", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
public final class GoogleLoginResult implements android.os.Parcelable {
    private final boolean resultStatus = false;
    @org.jetbrains.annotations.Nullable()
    private final com.swipefwd.ui.auth.sociallogin.google.GoogleUserDetails profileDetails = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String message = null;
    public static final android.os.Parcelable.Creator<com.swipefwd.ui.auth.sociallogin.google.GoogleLoginResult> CREATOR = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.ui.auth.sociallogin.google.GoogleLoginResult copy(boolean resultStatus, @org.jetbrains.annotations.Nullable()
    com.swipefwd.ui.auth.sociallogin.google.GoogleUserDetails profileDetails, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public GoogleLoginResult(boolean resultStatus, @org.jetbrains.annotations.Nullable()
    com.swipefwd.ui.auth.sociallogin.google.GoogleUserDetails profileDetails, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    public final boolean getResultStatus() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.ui.auth.sociallogin.google.GoogleUserDetails component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.ui.auth.sociallogin.google.GoogleUserDetails getProfileDetails() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<com.swipefwd.ui.auth.sociallogin.google.GoogleLoginResult> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.swipefwd.ui.auth.sociallogin.google.GoogleLoginResult createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final com.swipefwd.ui.auth.sociallogin.google.GoogleLoginResult[] newArray(int size) {
            return null;
        }
    }
}