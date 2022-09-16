package com.swipefwd.data.models;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b(\b\u0086\b\u0018\u00002\u00020\u0001:\u00014B}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0007H\u00c6\u0003J\t\u0010%\u001a\u00020\u0007H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0007H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u0007H\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\t\u0010,\u001a\u00020\u0007H\u00c6\u0003J\t\u0010-\u001a\u00020\rH\u00c6\u0003J\t\u0010.\u001a\u00020\u0007H\u00c6\u0003J\u0081\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u0005H\u00c6\u0001J\u0013\u00100\u001a\u00020\u00072\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00102\u001a\u00020\rH\u00d6\u0001J\t\u00103\u001a\u00020\u0005H\u00d6\u0001R\u0016\u0010\u000e\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0010\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0016\u0010\u000b\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0016\u0010\u000f\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0016\u0010\t\u001a\u00020\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"\u00a8\u00065"}, d2 = {"Lcom/swipefwd/data/models/LoginResponseModel;", "", "data", "Lcom/swipefwd/data/models/LoginResponseModel$Data;", "message", "", "profile", "", "response", "status", "token", "preference", "user_id", "", "advanceprofile", "setting", "invitation_expired", "gender", "(Lcom/swipefwd/data/models/LoginResponseModel$Data;Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/String;ZIZZZLjava/lang/String;)V", "getAdvanceprofile", "()Z", "getData", "()Lcom/swipefwd/data/models/LoginResponseModel$Data;", "getGender", "()Ljava/lang/String;", "getInvitation_expired", "getMessage", "getPreference", "getProfile", "getResponse", "getSetting", "getStatus", "getToken", "getUser_id", "()I", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "Data", "app_debug"})
public final class LoginResponseModel {
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "data")
    private final com.swipefwd.data.models.LoginResponseModel.Data data = null;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "message")
    private final java.lang.String message = null;
    @com.google.gson.annotations.SerializedName(value = "profile")
    private final boolean profile = false;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "response")
    private final java.lang.String response = null;
    @com.google.gson.annotations.SerializedName(value = "status")
    private final boolean status = false;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "token")
    private final java.lang.String token = null;
    @com.google.gson.annotations.SerializedName(value = "preference")
    private final boolean preference = false;
    @com.google.gson.annotations.SerializedName(value = "user_id")
    private final int user_id = 0;
    @com.google.gson.annotations.SerializedName(value = "advanceprofile")
    private final boolean advanceprofile = false;
    @com.google.gson.annotations.SerializedName(value = "setting")
    private final boolean setting = false;
    @com.google.gson.annotations.SerializedName(value = "invitation_expired")
    private final boolean invitation_expired = false;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "gender")
    private final java.lang.String gender = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.LoginResponseModel copy(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.LoginResponseModel.Data data, @org.jetbrains.annotations.NotNull()
    java.lang.String message, boolean profile, @org.jetbrains.annotations.NotNull()
    java.lang.String response, boolean status, @org.jetbrains.annotations.NotNull()
    java.lang.String token, boolean preference, int user_id, boolean advanceprofile, boolean setting, boolean invitation_expired, @org.jetbrains.annotations.NotNull()
    java.lang.String gender) {
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
    
    public LoginResponseModel() {
        super();
    }
    
    public LoginResponseModel(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.LoginResponseModel.Data data, @org.jetbrains.annotations.NotNull()
    java.lang.String message, boolean profile, @org.jetbrains.annotations.NotNull()
    java.lang.String response, boolean status, @org.jetbrains.annotations.NotNull()
    java.lang.String token, boolean preference, int user_id, boolean advanceprofile, boolean setting, boolean invitation_expired, @org.jetbrains.annotations.NotNull()
    java.lang.String gender) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.LoginResponseModel.Data component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.LoginResponseModel.Data getData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final boolean getProfile() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResponse() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean getStatus() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToken() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean getPreference() {
        return false;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int getUser_id() {
        return 0;
    }
    
    public final boolean component9() {
        return false;
    }
    
    public final boolean getAdvanceprofile() {
        return false;
    }
    
    public final boolean component10() {
        return false;
    }
    
    public final boolean getSetting() {
        return false;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final boolean getInvitation_expired() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGender() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b3\b\u0086\b\u0018\u00002\u00020\u0001B\u00b9\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\r\u0012\b\b\u0002\u0010\u0015\u001a\u00020\r\u0012\b\b\u0002\u0010\u0016\u001a\u00020\r\u00a2\u0006\u0002\u0010\u0017J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\rH\u00c6\u0003J\t\u00101\u001a\u00020\rH\u00c6\u0003J\t\u00102\u001a\u00020\rH\u00c6\u0003J\t\u00103\u001a\u00020\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0005H\u00c6\u0003J\t\u0010:\u001a\u00020\rH\u00c6\u0003J\u00bd\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\rH\u00c6\u0001J\u0013\u0010<\u001a\u00020\r2\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020\u0005H\u00d6\u0001J\t\u0010?\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0010\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001eR\u0016\u0010\u0015\u001a\u00020\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u001eR\u0016\u0010\u0014\u001a\u00020\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u001eR\u0016\u0010\u0016\u001a\u00020\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u001eR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0016\u0010\u0013\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0016\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0016\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010%R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019\u00a8\u0006@"}, d2 = {"Lcom/swipefwd/data/models/LoginResponseModel$Data;", "", "recoveryEmail", "", "userId", "", "userType", "first_name", "last_name", "profile_image", "phone_number", "remain_invitation", "is_agree", "", "remain_connection", "instargram_name", "facebook_id", "google_id", "apple_id", "linkdin_id", "is_dater_disabled", "is_connector_disabled", "is_verified", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZ)V", "getApple_id", "()Ljava/lang/String;", "getFacebook_id", "getFirst_name", "getGoogle_id", "getInstargram_name", "()Z", "getLast_name", "getLinkdin_id", "getPhone_number", "getProfile_image", "getRecoveryEmail", "getRemain_connection", "()I", "getRemain_invitation", "getUserId", "getUserType", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
    public static final class Data {
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "email")
        private final java.lang.String recoveryEmail = null;
        @com.google.gson.annotations.SerializedName(value = "user_id")
        private final int userId = 0;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "user_type")
        private final java.lang.String userType = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "first_name")
        private final java.lang.String first_name = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "last_name")
        private final java.lang.String last_name = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "profile_image")
        private final java.lang.String profile_image = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "phone_number")
        private final java.lang.String phone_number = null;
        @com.google.gson.annotations.SerializedName(value = "remain_invitation")
        private final int remain_invitation = 0;
        @com.google.gson.annotations.SerializedName(value = "is_agree")
        private final boolean is_agree = false;
        @com.google.gson.annotations.SerializedName(value = "remain_connection")
        private final int remain_connection = 0;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "instargram_name")
        private final java.lang.String instargram_name = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "facebook")
        private final java.lang.String facebook_id = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "google")
        private final java.lang.String google_id = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "apple")
        private final java.lang.String apple_id = null;
        @org.jetbrains.annotations.NotNull()
        @com.google.gson.annotations.SerializedName(value = "linkdin")
        private final java.lang.String linkdin_id = null;
        @com.google.gson.annotations.SerializedName(value = "is_dater_disabled")
        private final boolean is_dater_disabled = false;
        @com.google.gson.annotations.SerializedName(value = "is_connector_disabled")
        private final boolean is_connector_disabled = false;
        @com.google.gson.annotations.SerializedName(value = "is_verified")
        private final boolean is_verified = false;
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.data.models.LoginResponseModel.Data copy(@org.jetbrains.annotations.NotNull()
        java.lang.String recoveryEmail, int userId, @org.jetbrains.annotations.NotNull()
        java.lang.String userType, @org.jetbrains.annotations.NotNull()
        java.lang.String first_name, @org.jetbrains.annotations.NotNull()
        java.lang.String last_name, @org.jetbrains.annotations.NotNull()
        java.lang.String profile_image, @org.jetbrains.annotations.NotNull()
        java.lang.String phone_number, int remain_invitation, boolean is_agree, int remain_connection, @org.jetbrains.annotations.NotNull()
        java.lang.String instargram_name, @org.jetbrains.annotations.NotNull()
        java.lang.String facebook_id, @org.jetbrains.annotations.NotNull()
        java.lang.String google_id, @org.jetbrains.annotations.NotNull()
        java.lang.String apple_id, @org.jetbrains.annotations.NotNull()
        java.lang.String linkdin_id, boolean is_dater_disabled, boolean is_connector_disabled, boolean is_verified) {
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
        
        public Data() {
            super();
        }
        
        public Data(@org.jetbrains.annotations.NotNull()
        java.lang.String recoveryEmail, int userId, @org.jetbrains.annotations.NotNull()
        java.lang.String userType, @org.jetbrains.annotations.NotNull()
        java.lang.String first_name, @org.jetbrains.annotations.NotNull()
        java.lang.String last_name, @org.jetbrains.annotations.NotNull()
        java.lang.String profile_image, @org.jetbrains.annotations.NotNull()
        java.lang.String phone_number, int remain_invitation, boolean is_agree, int remain_connection, @org.jetbrains.annotations.NotNull()
        java.lang.String instargram_name, @org.jetbrains.annotations.NotNull()
        java.lang.String facebook_id, @org.jetbrains.annotations.NotNull()
        java.lang.String google_id, @org.jetbrains.annotations.NotNull()
        java.lang.String apple_id, @org.jetbrains.annotations.NotNull()
        java.lang.String linkdin_id, boolean is_dater_disabled, boolean is_connector_disabled, boolean is_verified) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getRecoveryEmail() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getUserId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFirst_name() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLast_name() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getProfile_image() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPhone_number() {
            return null;
        }
        
        public final int component8() {
            return 0;
        }
        
        public final int getRemain_invitation() {
            return 0;
        }
        
        public final boolean component9() {
            return false;
        }
        
        public final boolean is_agree() {
            return false;
        }
        
        public final int component10() {
            return 0;
        }
        
        public final int getRemain_connection() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getInstargram_name() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component12() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getFacebook_id() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component13() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getGoogle_id() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component14() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getApple_id() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component15() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLinkdin_id() {
            return null;
        }
        
        public final boolean component16() {
            return false;
        }
        
        public final boolean is_dater_disabled() {
            return false;
        }
        
        public final boolean component17() {
            return false;
        }
        
        public final boolean is_connector_disabled() {
            return false;
        }
        
        public final boolean component18() {
            return false;
        }
        
        public final boolean is_verified() {
            return false;
        }
    }
}