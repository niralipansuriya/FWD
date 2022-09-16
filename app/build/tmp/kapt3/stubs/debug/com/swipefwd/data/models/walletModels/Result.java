package com.swipefwd.data.models.walletModels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\u0002\u0010\rJ\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u000bH\u00c6\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003JQ\u0010\u001e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020\u0006H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f\u00a8\u0006%"}, d2 = {"Lcom/swipefwd/data/models/walletModels/Result;", "", "address_components", "", "Lcom/swipefwd/data/models/walletModels/AddressComponent;", "formatted_address", "", "geometry", "Lcom/swipefwd/data/models/walletModels/Geometry;", "place_id", "plus_code", "Lcom/swipefwd/data/models/walletModels/PlusCodeX;", "types", "(Ljava/util/List;Ljava/lang/String;Lcom/swipefwd/data/models/walletModels/Geometry;Ljava/lang/String;Lcom/swipefwd/data/models/walletModels/PlusCodeX;Ljava/util/List;)V", "getAddress_components", "()Ljava/util/List;", "getFormatted_address", "()Ljava/lang/String;", "getGeometry", "()Lcom/swipefwd/data/models/walletModels/Geometry;", "getPlace_id", "getPlus_code", "()Lcom/swipefwd/data/models/walletModels/PlusCodeX;", "getTypes", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class Result {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.swipefwd.data.models.walletModels.AddressComponent> address_components = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String formatted_address = null;
    @org.jetbrains.annotations.NotNull()
    private final com.swipefwd.data.models.walletModels.Geometry geometry = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String place_id = null;
    @org.jetbrains.annotations.NotNull()
    private final com.swipefwd.data.models.walletModels.PlusCodeX plus_code = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> types = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.walletModels.Result copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.walletModels.AddressComponent> address_components, @org.jetbrains.annotations.NotNull()
    java.lang.String formatted_address, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.walletModels.Geometry geometry, @org.jetbrains.annotations.NotNull()
    java.lang.String place_id, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.walletModels.PlusCodeX plus_code, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> types) {
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
    
    public Result(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.walletModels.AddressComponent> address_components, @org.jetbrains.annotations.NotNull()
    java.lang.String formatted_address, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.walletModels.Geometry geometry, @org.jetbrains.annotations.NotNull()
    java.lang.String place_id, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.walletModels.PlusCodeX plus_code, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> types) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.swipefwd.data.models.walletModels.AddressComponent> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.swipefwd.data.models.walletModels.AddressComponent> getAddress_components() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormatted_address() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.walletModels.Geometry component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.walletModels.Geometry getGeometry() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPlace_id() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.walletModels.PlusCodeX component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.walletModels.PlusCodeX getPlus_code() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTypes() {
        return null;
    }
}