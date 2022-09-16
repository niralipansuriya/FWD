
import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\bV\b\u0086\b\u0018\u00002\u00020\u0001B\u00bb\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0018\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\f\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\u0006\u0010!\u001a\u00020\u0003\u0012\u0006\u0010\"\u001a\u00020\f\u0012\u0006\u0010#\u001a\u00020\f\u0012\u0006\u0010$\u001a\u00020\u0003\u0012\u0006\u0010%\u001a\u00020\f\u0012\u0006\u0010&\u001a\u00020\u0003\u0012\u0006\u0010\'\u001a\u00020\f\u0012\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)\u0012\u0006\u0010+\u001a\u00020\f\u0012\u0006\u0010,\u001a\u00020\f\u0012\u0006\u0010-\u001a\u00020\u0011\u00a2\u0006\u0002\u0010.J\t\u0010U\u001a\u00020\u0003H\u00c6\u0003J\t\u0010V\u001a\u00020\fH\u00c6\u0003J\t\u0010W\u001a\u00020\u0003H\u00c6\u0003J\t\u0010X\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Y\u001a\u00020\u0011H\u00c6\u0003J\t\u0010Z\u001a\u00020\u0011H\u00c6\u0003J\t\u0010[\u001a\u00020\u0011H\u00c6\u0003J\t\u0010\\\u001a\u00020\u0003H\u00c6\u0003J\t\u0010]\u001a\u00020\u0003H\u00c6\u0003J\t\u0010^\u001a\u00020\u0003H\u00c6\u0003J\t\u0010_\u001a\u00020\u0018H\u00c6\u0003J\t\u0010`\u001a\u00020\u0003H\u00c6\u0003J\t\u0010a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010c\u001a\u00020\u0018H\u00c6\u0003J\t\u0010d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010f\u001a\u00020\fH\u00c6\u0003J\t\u0010g\u001a\u00020\u0003H\u00c6\u0003J\t\u0010h\u001a\u00020\u0003H\u00c6\u0003J\t\u0010i\u001a\u00020\u0003H\u00c6\u0003J\t\u0010j\u001a\u00020\fH\u00c6\u0003J\t\u0010k\u001a\u00020\u0003H\u00c6\u0003J\t\u0010l\u001a\u00020\fH\u00c6\u0003J\t\u0010m\u001a\u00020\u0003H\u00c6\u0003J\t\u0010n\u001a\u00020\fH\u00c6\u0003J\t\u0010o\u001a\u00020\u0003H\u00c6\u0003J\t\u0010p\u001a\u00020\fH\u00c6\u0003J\u000f\u0010q\u001a\b\u0012\u0004\u0012\u00020*0)H\u00c6\u0003J\t\u0010r\u001a\u00020\fH\u00c6\u0003J\t\u0010s\u001a\u00020\fH\u00c6\u0003J\t\u0010t\u001a\u00020\u0011H\u00c6\u0003J\t\u0010u\u001a\u00020\u0003H\u00c6\u0003J\t\u0010v\u001a\u00020\u0003H\u00c6\u0003J\t\u0010w\u001a\u00020\u0003H\u00c6\u0003J\t\u0010x\u001a\u00020\u0003H\u00c6\u0003J\t\u0010y\u001a\u00020\u0003H\u00c6\u0003J\t\u0010z\u001a\u00020\fH\u00c6\u0003J\u008b\u0003\u0010{\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\f2\b\b\u0002\u0010#\u001a\u00020\f2\b\b\u0002\u0010$\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\f2\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010\'\u001a\u00020\f2\u000e\b\u0002\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\b\b\u0002\u0010+\u001a\u00020\f2\b\b\u0002\u0010,\u001a\u00020\f2\b\b\u0002\u0010-\u001a\u00020\u0011H\u00c6\u0001J\u0013\u0010|\u001a\u00020\u00112\b\u0010}\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010~\u001a\u00020\fH\u00d6\u0001J\t\u0010\u007f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00100R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00100R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u00100R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00100R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00100R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u00100R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00100R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\r\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u00109R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u00100R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u00100R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010=R\u0011\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010=R\u0011\u0010\u0013\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010=R\u0011\u0010-\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010=R\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u00100R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u00100R\u0011\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u00100R\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0011\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u00100R\u0011\u0010\u001a\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u00100R\u0011\u0010\u001b\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010AR\u0011\u0010\u001c\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u00100R\u0011\u0010\u001d\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u00100R\u0011\u0010\u001e\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u00109R\u0011\u0010\u001f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u00100R\u0011\u0010 \u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u00100R\u0011\u0010!\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u00100R\u0011\u0010\"\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\bK\u00109R\u0011\u0010#\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\bL\u00109R\u0011\u0010$\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bM\u00100R\u0011\u0010%\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\bN\u00109R\u0011\u0010&\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\bO\u00100R\u0011\u0010\'\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\bP\u00109R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)\u00a2\u0006\b\n\u0000\u001a\u0004\bQ\u0010RR\u0011\u0010+\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\bS\u00109R\u0011\u0010,\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\bT\u00109\u00a8\u0006\u0080\u0001"}, d2 = {"LUserDetails;", "", "astrological_sign", "", "bio", "children", "dob", "education_level", "facebook", "first_name", "google", "graduation_year", "", "height", "instagram_name", "institute", "is_agree", "", "is_connector_disabled", "is_dater_disabled", "is_vaccinated", "language", "last_name", "latitude", "", "linkedin", "location_name", "longitude", "occupation", "phone_number", "profile_complete_per", "profile_image", "relation_interest", "religion", "remaim_connection", "remain_invitation", "smoke", "travel_latitude", "travel_location_name", "travel_longitude", "userPhoto", "", "LUserPhoto;", "user_id", "user_type", "is_profile_verified", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;ZZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;ILjava/lang/String;ILjava/util/List;IIZ)V", "getAstrological_sign", "()Ljava/lang/String;", "getBio", "getChildren", "getDob", "getEducation_level", "getFacebook", "getFirst_name", "getGoogle", "getGraduation_year", "()I", "getHeight", "getInstagram_name", "getInstitute", "()Z", "getLanguage", "getLast_name", "getLatitude", "()D", "getLinkedin", "getLocation_name", "getLongitude", "getOccupation", "getPhone_number", "getProfile_complete_per", "getProfile_image", "getRelation_interest", "getReligion", "getRemaim_connection", "getRemain_invitation", "getSmoke", "getTravel_latitude", "getTravel_location_name", "getTravel_longitude", "getUserPhoto", "()Ljava/util/List;", "getUser_id", "getUser_type", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class UserDetails {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String astrological_sign = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String bio = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String children = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dob = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String education_level = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String facebook = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String first_name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String google = null;
    private final int graduation_year = 0;
    private final int height = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String instagram_name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String institute = null;
    private final boolean is_agree = false;
    private final boolean is_connector_disabled = false;
    private final boolean is_dater_disabled = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String is_vaccinated = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String language = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String last_name = null;
    private final double latitude = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String linkedin = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String location_name = null;
    private final double longitude = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String occupation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String phone_number = null;
    private final int profile_complete_per = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String profile_image = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String relation_interest = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String religion = null;
    private final int remaim_connection = 0;
    private final int remain_invitation = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String smoke = null;
    private final int travel_latitude = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String travel_location_name = null;
    private final int travel_longitude = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<UserPhoto> userPhoto = null;
    private final int user_id = 0;
    private final int user_type = 0;
    private final boolean is_profile_verified = false;
    
    @org.jetbrains.annotations.NotNull()
    public final UserDetails copy(@org.jetbrains.annotations.NotNull()
    java.lang.String astrological_sign, @org.jetbrains.annotations.NotNull()
    java.lang.String bio, @org.jetbrains.annotations.NotNull()
    java.lang.String children, @org.jetbrains.annotations.NotNull()
    java.lang.String dob, @org.jetbrains.annotations.NotNull()
    java.lang.String education_level, @org.jetbrains.annotations.NotNull()
    java.lang.String facebook, @org.jetbrains.annotations.NotNull()
    java.lang.String first_name, @org.jetbrains.annotations.NotNull()
    java.lang.String google, int graduation_year, int height, @org.jetbrains.annotations.NotNull()
    java.lang.String instagram_name, @org.jetbrains.annotations.NotNull()
    java.lang.String institute, boolean is_agree, boolean is_connector_disabled, boolean is_dater_disabled, @org.jetbrains.annotations.NotNull()
    java.lang.String is_vaccinated, @org.jetbrains.annotations.NotNull()
    java.lang.String language, @org.jetbrains.annotations.NotNull()
    java.lang.String last_name, double latitude, @org.jetbrains.annotations.NotNull()
    java.lang.String linkedin, @org.jetbrains.annotations.NotNull()
    java.lang.String location_name, double longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String occupation, @org.jetbrains.annotations.NotNull()
    java.lang.String phone_number, int profile_complete_per, @org.jetbrains.annotations.NotNull()
    java.lang.String profile_image, @org.jetbrains.annotations.NotNull()
    java.lang.String relation_interest, @org.jetbrains.annotations.NotNull()
    java.lang.String religion, int remaim_connection, int remain_invitation, @org.jetbrains.annotations.NotNull()
    java.lang.String smoke, int travel_latitude, @org.jetbrains.annotations.NotNull()
    java.lang.String travel_location_name, int travel_longitude, @org.jetbrains.annotations.NotNull()
    java.util.List<UserPhoto> userPhoto, int user_id, int user_type, boolean is_profile_verified) {
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
    
    public UserDetails(@org.jetbrains.annotations.NotNull()
    java.lang.String astrological_sign, @org.jetbrains.annotations.NotNull()
    java.lang.String bio, @org.jetbrains.annotations.NotNull()
    java.lang.String children, @org.jetbrains.annotations.NotNull()
    java.lang.String dob, @org.jetbrains.annotations.NotNull()
    java.lang.String education_level, @org.jetbrains.annotations.NotNull()
    java.lang.String facebook, @org.jetbrains.annotations.NotNull()
    java.lang.String first_name, @org.jetbrains.annotations.NotNull()
    java.lang.String google, int graduation_year, int height, @org.jetbrains.annotations.NotNull()
    java.lang.String instagram_name, @org.jetbrains.annotations.NotNull()
    java.lang.String institute, boolean is_agree, boolean is_connector_disabled, boolean is_dater_disabled, @org.jetbrains.annotations.NotNull()
    java.lang.String is_vaccinated, @org.jetbrains.annotations.NotNull()
    java.lang.String language, @org.jetbrains.annotations.NotNull()
    java.lang.String last_name, double latitude, @org.jetbrains.annotations.NotNull()
    java.lang.String linkedin, @org.jetbrains.annotations.NotNull()
    java.lang.String location_name, double longitude, @org.jetbrains.annotations.NotNull()
    java.lang.String occupation, @org.jetbrains.annotations.NotNull()
    java.lang.String phone_number, int profile_complete_per, @org.jetbrains.annotations.NotNull()
    java.lang.String profile_image, @org.jetbrains.annotations.NotNull()
    java.lang.String relation_interest, @org.jetbrains.annotations.NotNull()
    java.lang.String religion, int remaim_connection, int remain_invitation, @org.jetbrains.annotations.NotNull()
    java.lang.String smoke, int travel_latitude, @org.jetbrains.annotations.NotNull()
    java.lang.String travel_location_name, int travel_longitude, @org.jetbrains.annotations.NotNull()
    java.util.List<UserPhoto> userPhoto, int user_id, int user_type, boolean is_profile_verified) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAstrological_sign() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBio() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getChildren() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDob() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEducation_level() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFacebook() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFirst_name() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGoogle() {
        return null;
    }
    
    public final int component9() {
        return 0;
    }
    
    public final int getGraduation_year() {
        return 0;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int getHeight() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInstagram_name() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInstitute() {
        return null;
    }
    
    public final boolean component13() {
        return false;
    }
    
    public final boolean is_agree() {
        return false;
    }
    
    public final boolean component14() {
        return false;
    }
    
    public final boolean is_connector_disabled() {
        return false;
    }
    
    public final boolean component15() {
        return false;
    }
    
    public final boolean is_dater_disabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String is_vaccinated() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLanguage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLast_name() {
        return null;
    }
    
    public final double component19() {
        return 0.0;
    }
    
    public final double getLatitude() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLinkedin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLocation_name() {
        return null;
    }
    
    public final double component22() {
        return 0.0;
    }
    
    public final double getLongitude() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOccupation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPhone_number() {
        return null;
    }
    
    public final int component25() {
        return 0;
    }
    
    public final int getProfile_complete_per() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProfile_image() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRelation_interest() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReligion() {
        return null;
    }
    
    public final int component29() {
        return 0;
    }
    
    public final int getRemaim_connection() {
        return 0;
    }
    
    public final int component30() {
        return 0;
    }
    
    public final int getRemain_invitation() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component31() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSmoke() {
        return null;
    }
    
    public final int component32() {
        return 0;
    }
    
    public final int getTravel_latitude() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component33() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTravel_location_name() {
        return null;
    }
    
    public final int component34() {
        return 0;
    }
    
    public final int getTravel_longitude() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<UserPhoto> component35() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<UserPhoto> getUserPhoto() {
        return null;
    }
    
    public final int component36() {
        return 0;
    }
    
    public final int getUser_id() {
        return 0;
    }
    
    public final int component37() {
        return 0;
    }
    
    public final int getUser_type() {
        return 0;
    }
    
    public final boolean component38() {
        return false;
    }
    
    public final boolean is_profile_verified() {
        return false;
    }
}