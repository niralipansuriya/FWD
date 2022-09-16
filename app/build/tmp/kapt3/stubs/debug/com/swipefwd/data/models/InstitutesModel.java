package com.swipefwd.data.models;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001:\u0004\u001c\u001d\u001e\u001fBY\u0012\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0001\u0018\u0001`\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\nJ\u001d\u0010\u0011\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0001\u0018\u0001`\u0004H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u001d\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u0004H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J]\u0010\u0015\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0001\u0018\u0001`\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u001c\b\u0002\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0006H\u00d6\u0001R*\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0001\u0018\u0001`\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR*\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\u00048\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e\u00a8\u0006 "}, d2 = {"Lcom/swipefwd/data/models/InstitutesModel;", "", "htmlAttributions", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "nextPageToken", "", "results", "Lcom/swipefwd/data/models/InstitutesModel$Result;", "status", "(Ljava/util/ArrayList;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;)V", "getHtmlAttributions", "()Ljava/util/ArrayList;", "getNextPageToken", "()Ljava/lang/String;", "getResults", "getStatus", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "OpeningHours", "Photo", "PlusCode", "Result", "app_debug"})
public final class InstitutesModel {
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "html_attributions")
    private final java.util.ArrayList<java.lang.Object> htmlAttributions = null;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "next_page_token")
    private final java.lang.String nextPageToken = null;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "results")
    private final java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Result> results = null;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "status")
    private final java.lang.String status = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.InstitutesModel copy(@org.jetbrains.annotations.Nullable()
    java.util.ArrayList<java.lang.Object> htmlAttributions, @org.jetbrains.annotations.Nullable()
    java.lang.String nextPageToken, @org.jetbrains.annotations.Nullable()
    java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Result> results, @org.jetbrains.annotations.Nullable()
    java.lang.String status) {
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
    
    public InstitutesModel() {
        super();
    }
    
    public InstitutesModel(@org.jetbrains.annotations.Nullable()
    java.util.ArrayList<java.lang.Object> htmlAttributions, @org.jetbrains.annotations.Nullable()
    java.lang.String nextPageToken, @org.jetbrains.annotations.Nullable()
    java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Result> results, @org.jetbrains.annotations.Nullable()
    java.lang.String status) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.ArrayList<java.lang.Object> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.ArrayList<java.lang.Object> getHtmlAttributions() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNextPageToken() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Result> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Result> getResults() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getStatus() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b2\b\u0086\b\u0018\u00002\u00020\u0001:\u0003HIJB\u00d1\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u001c\b\u0002\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\u001c\b\u0002\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0010\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u00a2\u0006\u0002\u0010\u001aJ\u000b\u00104\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u0015H\u00c6\u0003\u00a2\u0006\u0002\u0010-J\u000b\u00107\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u001d\u00108\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u0010H\u00c6\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u0019H\u00c6\u0003\u00a2\u0006\u0002\u00102J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u0010\u0010?\u001a\u0004\u0018\u00010\fH\u00c6\u0003\u00a2\u0006\u0002\u0010%J\u001d\u0010@\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\u0010H\u00c6\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00da\u0001\u0010B\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u001c\b\u0002\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u001c\b\u0002\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u00102\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00c6\u0001\u00a2\u0006\u0002\u0010CJ\u0013\u0010D\u001a\u00020\f2\b\u0010E\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010F\u001a\u00020\u0019H\u00d6\u0001J\t\u0010G\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R*\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u000f\u0018\u0001`\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001cR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010.\u001a\u0004\b,\u0010-R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001cR*\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\u00108\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010(R\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00198\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u00103\u001a\u0004\b1\u00102\u00a8\u0006K"}, d2 = {"Lcom/swipefwd/data/models/InstitutesModel$Result;", "", "businessStatus", "", "formattedAddress", "geometry", "Lcom/swipefwd/data/models/InstitutesModel$Result$Geometry;", "icon", "name", "openingHours", "Lcom/swipefwd/data/models/InstitutesModel$OpeningHours;", "permanentlyClosed", "", "photos", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/InstitutesModel$Photo;", "Lkotlin/collections/ArrayList;", "placeId", "plusCode", "Lcom/swipefwd/data/models/InstitutesModel$PlusCode;", "rating", "", "reference", "types", "userRatingsTotal", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/swipefwd/data/models/InstitutesModel$Result$Geometry;Ljava/lang/String;Ljava/lang/String;Lcom/swipefwd/data/models/InstitutesModel$OpeningHours;Ljava/lang/Boolean;Ljava/util/ArrayList;Ljava/lang/String;Lcom/swipefwd/data/models/InstitutesModel$PlusCode;Ljava/lang/Double;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/Integer;)V", "getBusinessStatus", "()Ljava/lang/String;", "getFormattedAddress", "getGeometry", "()Lcom/swipefwd/data/models/InstitutesModel$Result$Geometry;", "getIcon", "getName", "getOpeningHours", "()Lcom/swipefwd/data/models/InstitutesModel$OpeningHours;", "getPermanentlyClosed", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getPhotos", "()Ljava/util/ArrayList;", "getPlaceId", "getPlusCode", "()Lcom/swipefwd/data/models/InstitutesModel$PlusCode;", "getRating", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getReference", "getTypes", "getUserRatingsTotal", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/swipefwd/data/models/InstitutesModel$Result$Geometry;Ljava/lang/String;Ljava/lang/String;Lcom/swipefwd/data/models/InstitutesModel$OpeningHours;Ljava/lang/Boolean;Ljava/util/ArrayList;Ljava/lang/String;Lcom/swipefwd/data/models/InstitutesModel$PlusCode;Ljava/lang/Double;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/Integer;)Lcom/swipefwd/data/models/InstitutesModel$Result;", "equals", "other", "hashCode", "toString", "Geometry", "Location", "Viewport", "app_debug"})
    public static final class Result {
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "business_status")
        private final java.lang.String businessStatus = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "formatted_address")
        private final java.lang.String formattedAddress = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "geometry")
        private final com.swipefwd.data.models.InstitutesModel.Result.Geometry geometry = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "icon")
        private final java.lang.String icon = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "name")
        private final java.lang.String name = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "opening_hours")
        private final com.swipefwd.data.models.InstitutesModel.OpeningHours openingHours = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "permanently_closed")
        private final java.lang.Boolean permanentlyClosed = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "photos")
        private final java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Photo> photos = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "place_id")
        private final java.lang.String placeId = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "plus_code")
        private final com.swipefwd.data.models.InstitutesModel.PlusCode plusCode = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "rating")
        private final java.lang.Double rating = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "reference")
        private final java.lang.String reference = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "types")
        private final java.util.ArrayList<java.lang.String> types = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "user_ratings_total")
        private final java.lang.Integer userRatingsTotal = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.data.models.InstitutesModel.Result copy(@org.jetbrains.annotations.Nullable()
        java.lang.String businessStatus, @org.jetbrains.annotations.Nullable()
        java.lang.String formattedAddress, @org.jetbrains.annotations.Nullable()
        com.swipefwd.data.models.InstitutesModel.Result.Geometry geometry, @org.jetbrains.annotations.Nullable()
        java.lang.String icon, @org.jetbrains.annotations.Nullable()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        com.swipefwd.data.models.InstitutesModel.OpeningHours openingHours, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permanentlyClosed, @org.jetbrains.annotations.Nullable()
        java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Photo> photos, @org.jetbrains.annotations.Nullable()
        java.lang.String placeId, @org.jetbrains.annotations.Nullable()
        com.swipefwd.data.models.InstitutesModel.PlusCode plusCode, @org.jetbrains.annotations.Nullable()
        java.lang.Double rating, @org.jetbrains.annotations.Nullable()
        java.lang.String reference, @org.jetbrains.annotations.Nullable()
        java.util.ArrayList<java.lang.String> types, @org.jetbrains.annotations.Nullable()
        java.lang.Integer userRatingsTotal) {
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
        
        public Result() {
            super();
        }
        
        public Result(@org.jetbrains.annotations.Nullable()
        java.lang.String businessStatus, @org.jetbrains.annotations.Nullable()
        java.lang.String formattedAddress, @org.jetbrains.annotations.Nullable()
        com.swipefwd.data.models.InstitutesModel.Result.Geometry geometry, @org.jetbrains.annotations.Nullable()
        java.lang.String icon, @org.jetbrains.annotations.Nullable()
        java.lang.String name, @org.jetbrains.annotations.Nullable()
        com.swipefwd.data.models.InstitutesModel.OpeningHours openingHours, @org.jetbrains.annotations.Nullable()
        java.lang.Boolean permanentlyClosed, @org.jetbrains.annotations.Nullable()
        java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Photo> photos, @org.jetbrains.annotations.Nullable()
        java.lang.String placeId, @org.jetbrains.annotations.Nullable()
        com.swipefwd.data.models.InstitutesModel.PlusCode plusCode, @org.jetbrains.annotations.Nullable()
        java.lang.Double rating, @org.jetbrains.annotations.Nullable()
        java.lang.String reference, @org.jetbrains.annotations.Nullable()
        java.util.ArrayList<java.lang.String> types, @org.jetbrains.annotations.Nullable()
        java.lang.Integer userRatingsTotal) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getBusinessStatus() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getFormattedAddress() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.swipefwd.data.models.InstitutesModel.Result.Geometry component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.swipefwd.data.models.InstitutesModel.Result.Geometry getGeometry() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getIcon() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.swipefwd.data.models.InstitutesModel.OpeningHours component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.swipefwd.data.models.InstitutesModel.OpeningHours getOpeningHours() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean getPermanentlyClosed() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Photo> component8() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Photo> getPhotos() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component9() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPlaceId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.swipefwd.data.models.InstitutesModel.PlusCode component10() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.swipefwd.data.models.InstitutesModel.PlusCode getPlusCode() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Double component11() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Double getRating() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component12() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getReference() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.ArrayList<java.lang.String> component13() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.ArrayList<java.lang.String> getTypes() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component14() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getUserRatingsTotal() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/swipefwd/data/models/InstitutesModel$Result$Geometry;", "", "location", "Lcom/swipefwd/data/models/InstitutesModel$Result$Location;", "viewport", "Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport;", "(Lcom/swipefwd/data/models/InstitutesModel$Result$Location;Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport;)V", "getLocation", "()Lcom/swipefwd/data/models/InstitutesModel$Result$Location;", "getViewport", "()Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
        public static final class Geometry {
            @org.jetbrains.annotations.Nullable()
            @com.google.gson.annotations.SerializedName(value = "location")
            private final com.swipefwd.data.models.InstitutesModel.Result.Location location = null;
            @org.jetbrains.annotations.Nullable()
            @com.google.gson.annotations.SerializedName(value = "viewport")
            private final com.swipefwd.data.models.InstitutesModel.Result.Viewport viewport = null;
            
            @org.jetbrains.annotations.NotNull()
            public final com.swipefwd.data.models.InstitutesModel.Result.Geometry copy(@org.jetbrains.annotations.Nullable()
            com.swipefwd.data.models.InstitutesModel.Result.Location location, @org.jetbrains.annotations.Nullable()
            com.swipefwd.data.models.InstitutesModel.Result.Viewport viewport) {
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
            
            public Geometry() {
                super();
            }
            
            public Geometry(@org.jetbrains.annotations.Nullable()
            com.swipefwd.data.models.InstitutesModel.Result.Location location, @org.jetbrains.annotations.Nullable()
            com.swipefwd.data.models.InstitutesModel.Result.Viewport viewport) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final com.swipefwd.data.models.InstitutesModel.Result.Location component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final com.swipefwd.data.models.InstitutesModel.Result.Location getLocation() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final com.swipefwd.data.models.InstitutesModel.Result.Viewport component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final com.swipefwd.data.models.InstitutesModel.Result.Viewport getViewport() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0007J&\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007\u00a8\u0006\u0015"}, d2 = {"Lcom/swipefwd/data/models/InstitutesModel$Result$Location;", "", "lat", "", "lng", "(Ljava/lang/Double;Ljava/lang/Double;)V", "getLat", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLng", "component1", "component2", "copy", "(Ljava/lang/Double;Ljava/lang/Double;)Lcom/swipefwd/data/models/InstitutesModel$Result$Location;", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
        public static final class Location {
            @org.jetbrains.annotations.Nullable()
            @com.google.gson.annotations.SerializedName(value = "lat")
            private final java.lang.Double lat = null;
            @org.jetbrains.annotations.Nullable()
            @com.google.gson.annotations.SerializedName(value = "lng")
            private final java.lang.Double lng = null;
            
            @org.jetbrains.annotations.NotNull()
            public final com.swipefwd.data.models.InstitutesModel.Result.Location copy(@org.jetbrains.annotations.Nullable()
            java.lang.Double lat, @org.jetbrains.annotations.Nullable()
            java.lang.Double lng) {
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
            
            public Location() {
                super();
            }
            
            public Location(@org.jetbrains.annotations.Nullable()
            java.lang.Double lat, @org.jetbrains.annotations.Nullable()
            java.lang.Double lng) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Double component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Double getLat() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Double component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Double getLng() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0017"}, d2 = {"Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport;", "", "northeast", "Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport$Northeast;", "southwest", "Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport$Southwest;", "(Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport$Northeast;Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport$Southwest;)V", "getNortheast", "()Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport$Northeast;", "getSouthwest", "()Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport$Southwest;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Northeast", "Southwest", "app_debug"})
        public static final class Viewport {
            @org.jetbrains.annotations.Nullable()
            @com.google.gson.annotations.SerializedName(value = "northeast")
            private final com.swipefwd.data.models.InstitutesModel.Result.Viewport.Northeast northeast = null;
            @org.jetbrains.annotations.Nullable()
            @com.google.gson.annotations.SerializedName(value = "southwest")
            private final com.swipefwd.data.models.InstitutesModel.Result.Viewport.Southwest southwest = null;
            
            @org.jetbrains.annotations.NotNull()
            public final com.swipefwd.data.models.InstitutesModel.Result.Viewport copy(@org.jetbrains.annotations.Nullable()
            com.swipefwd.data.models.InstitutesModel.Result.Viewport.Northeast northeast, @org.jetbrains.annotations.Nullable()
            com.swipefwd.data.models.InstitutesModel.Result.Viewport.Southwest southwest) {
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
            
            public Viewport() {
                super();
            }
            
            public Viewport(@org.jetbrains.annotations.Nullable()
            com.swipefwd.data.models.InstitutesModel.Result.Viewport.Northeast northeast, @org.jetbrains.annotations.Nullable()
            com.swipefwd.data.models.InstitutesModel.Result.Viewport.Southwest southwest) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final com.swipefwd.data.models.InstitutesModel.Result.Viewport.Northeast component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final com.swipefwd.data.models.InstitutesModel.Result.Viewport.Northeast getNortheast() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final com.swipefwd.data.models.InstitutesModel.Result.Viewport.Southwest component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final com.swipefwd.data.models.InstitutesModel.Result.Viewport.Southwest getSouthwest() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0007J&\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007\u00a8\u0006\u0015"}, d2 = {"Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport$Northeast;", "", "lat", "", "lng", "(Ljava/lang/Double;Ljava/lang/Double;)V", "getLat", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLng", "component1", "component2", "copy", "(Ljava/lang/Double;Ljava/lang/Double;)Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport$Northeast;", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
            public static final class Northeast {
                @org.jetbrains.annotations.Nullable()
                @com.google.gson.annotations.SerializedName(value = "lat")
                private final java.lang.Double lat = null;
                @org.jetbrains.annotations.Nullable()
                @com.google.gson.annotations.SerializedName(value = "lng")
                private final java.lang.Double lng = null;
                
                @org.jetbrains.annotations.NotNull()
                public final com.swipefwd.data.models.InstitutesModel.Result.Viewport.Northeast copy(@org.jetbrains.annotations.Nullable()
                java.lang.Double lat, @org.jetbrains.annotations.Nullable()
                java.lang.Double lng) {
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
                
                public Northeast() {
                    super();
                }
                
                public Northeast(@org.jetbrains.annotations.Nullable()
                java.lang.Double lat, @org.jetbrains.annotations.Nullable()
                java.lang.Double lng) {
                    super();
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.Double component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.Double getLat() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.Double component2() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.Double getLng() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0007J&\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007\u00a8\u0006\u0015"}, d2 = {"Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport$Southwest;", "", "lat", "", "lng", "(Ljava/lang/Double;Ljava/lang/Double;)V", "getLat", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLng", "component1", "component2", "copy", "(Ljava/lang/Double;Ljava/lang/Double;)Lcom/swipefwd/data/models/InstitutesModel$Result$Viewport$Southwest;", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
            public static final class Southwest {
                @org.jetbrains.annotations.Nullable()
                @com.google.gson.annotations.SerializedName(value = "lat")
                private final java.lang.Double lat = null;
                @org.jetbrains.annotations.Nullable()
                @com.google.gson.annotations.SerializedName(value = "lng")
                private final java.lang.Double lng = null;
                
                @org.jetbrains.annotations.NotNull()
                public final com.swipefwd.data.models.InstitutesModel.Result.Viewport.Southwest copy(@org.jetbrains.annotations.Nullable()
                java.lang.Double lat, @org.jetbrains.annotations.Nullable()
                java.lang.Double lng) {
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
                
                public Southwest() {
                    super();
                }
                
                public Southwest(@org.jetbrains.annotations.Nullable()
                java.lang.Double lat, @org.jetbrains.annotations.Nullable()
                java.lang.Double lng) {
                    super();
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.Double component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.Double getLat() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.Double component2() {
                    return null;
                }
                
                @org.jetbrains.annotations.Nullable()
                public final java.lang.Double getLng() {
                    return null;
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/swipefwd/data/models/InstitutesModel$OpeningHours;", "", "openNow", "", "(Ljava/lang/Boolean;)V", "getOpenNow", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "copy", "(Ljava/lang/Boolean;)Lcom/swipefwd/data/models/InstitutesModel$OpeningHours;", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
    public static final class OpeningHours {
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "open_now")
        private final java.lang.Boolean openNow = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.data.models.InstitutesModel.OpeningHours copy(@org.jetbrains.annotations.Nullable()
        java.lang.Boolean openNow) {
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
        
        public OpeningHours() {
            super();
        }
        
        public OpeningHours(@org.jetbrains.annotations.Nullable()
        java.lang.Boolean openNow) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Boolean getOpenNow() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJ\u001d\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\fJP\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0006H\u00d6\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR*\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u00078\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0012\u0010\f\u00a8\u0006\u001e"}, d2 = {"Lcom/swipefwd/data/models/InstitutesModel$Photo;", "", "height", "", "htmlAttributions", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "photoReference", "width", "(Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/Integer;)V", "getHeight", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHtmlAttributions", "()Ljava/util/ArrayList;", "getPhotoReference", "()Ljava/lang/String;", "getWidth", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/Integer;)Lcom/swipefwd/data/models/InstitutesModel$Photo;", "equals", "", "other", "hashCode", "toString", "app_debug"})
    public static final class Photo {
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "height")
        private final java.lang.Integer height = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "html_attributions")
        private final java.util.ArrayList<java.lang.String> htmlAttributions = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "photo_reference")
        private final java.lang.String photoReference = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "width")
        private final java.lang.Integer width = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.data.models.InstitutesModel.Photo copy(@org.jetbrains.annotations.Nullable()
        java.lang.Integer height, @org.jetbrains.annotations.Nullable()
        java.util.ArrayList<java.lang.String> htmlAttributions, @org.jetbrains.annotations.Nullable()
        java.lang.String photoReference, @org.jetbrains.annotations.Nullable()
        java.lang.Integer width) {
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
        
        public Photo() {
            super();
        }
        
        public Photo(@org.jetbrains.annotations.Nullable()
        java.lang.Integer height, @org.jetbrains.annotations.Nullable()
        java.util.ArrayList<java.lang.String> htmlAttributions, @org.jetbrains.annotations.Nullable()
        java.lang.String photoReference, @org.jetbrains.annotations.Nullable()
        java.lang.Integer width) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getHeight() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.ArrayList<java.lang.String> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.ArrayList<java.lang.String> getHtmlAttributions() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPhotoReference() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getWidth() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/swipefwd/data/models/InstitutesModel$PlusCode;", "", "compoundCode", "", "globalCode", "(Ljava/lang/String;Ljava/lang/String;)V", "getCompoundCode", "()Ljava/lang/String;", "getGlobalCode", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class PlusCode {
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "compound_code")
        private final java.lang.String compoundCode = null;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "global_code")
        private final java.lang.String globalCode = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.data.models.InstitutesModel.PlusCode copy(@org.jetbrains.annotations.Nullable()
        java.lang.String compoundCode, @org.jetbrains.annotations.Nullable()
        java.lang.String globalCode) {
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
        
        public PlusCode() {
            super();
        }
        
        public PlusCode(@org.jetbrains.annotations.Nullable()
        java.lang.String compoundCode, @org.jetbrains.annotations.Nullable()
        java.lang.String globalCode) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getCompoundCode() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getGlobalCode() {
            return null;
        }
    }
}