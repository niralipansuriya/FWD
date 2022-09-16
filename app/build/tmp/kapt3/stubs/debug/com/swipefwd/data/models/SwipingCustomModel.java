package com.swipefwd.data.models;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b#\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000bj\b\u0012\u0004\u0012\u00020\t`\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\u0019\u0010)\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000bj\b\u0012\u0004\u0012\u00020\t`\fH\u00c6\u0003J\t\u0010*\u001a\u00020\u000eH\u00c6\u0003J\t\u0010+\u001a\u00020\u000eH\u00c6\u0003Jk\u0010,\u001a\u00020\u00002\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000bj\b\u0012\u0004\u0012\u00020\t`\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010-\u001a\u00020\u000e2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u00020\u0004H\u00d6\u0001J\t\u00100\u001a\u00020\u0007H\u00d6\u0001R\u001b\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R*\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000bj\b\u0012\u0004\u0012\u00020\t`\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u000f\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u00a8\u00061"}, d2 = {"Lcom/swipefwd/data/models/SwipingCustomModel;", "", "connectorIds", "", "", "id", "profileName", "", "userProfileModel", "Lcom/swipefwd/data/models/SwipingProfileModel$ProfileModel;", "connectorProfileModel", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "hasLiked", "", "hasSuperLiked", "(Ljava/util/List;ILjava/lang/String;Lcom/swipefwd/data/models/SwipingProfileModel$ProfileModel;Ljava/util/ArrayList;ZZ)V", "getConnectorIds", "()Ljava/util/List;", "getConnectorProfileModel", "()Ljava/util/ArrayList;", "setConnectorProfileModel", "(Ljava/util/ArrayList;)V", "getHasLiked", "()Z", "setHasLiked", "(Z)V", "getHasSuperLiked", "setHasSuperLiked", "getId", "()I", "getProfileName", "()Ljava/lang/String;", "getUserProfileModel", "()Lcom/swipefwd/data/models/SwipingProfileModel$ProfileModel;", "setUserProfileModel", "(Lcom/swipefwd/data/models/SwipingProfileModel$ProfileModel;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class SwipingCustomModel {
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<java.lang.Integer> connectorIds = null;
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String profileName = null;
    @org.jetbrains.annotations.Nullable()
    private com.swipefwd.data.models.SwipingProfileModel.ProfileModel userProfileModel;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.swipefwd.data.models.SwipingProfileModel.ProfileModel> connectorProfileModel;
    private boolean hasLiked;
    private boolean hasSuperLiked;
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.SwipingCustomModel copy(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> connectorIds, int id, @org.jetbrains.annotations.NotNull()
    java.lang.String profileName, @org.jetbrains.annotations.Nullable()
    com.swipefwd.data.models.SwipingProfileModel.ProfileModel userProfileModel, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.data.models.SwipingProfileModel.ProfileModel> connectorProfileModel, boolean hasLiked, boolean hasSuperLiked) {
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
    
    public SwipingCustomModel() {
        super();
    }
    
    public SwipingCustomModel(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> connectorIds, int id, @org.jetbrains.annotations.NotNull()
    java.lang.String profileName, @org.jetbrains.annotations.Nullable()
    com.swipefwd.data.models.SwipingProfileModel.ProfileModel userProfileModel, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.data.models.SwipingProfileModel.ProfileModel> connectorProfileModel, boolean hasLiked, boolean hasSuperLiked) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.Integer> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.Integer> getConnectorIds() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProfileName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.data.models.SwipingProfileModel.ProfileModel component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.data.models.SwipingProfileModel.ProfileModel getUserProfileModel() {
        return null;
    }
    
    public final void setUserProfileModel(@org.jetbrains.annotations.Nullable()
    com.swipefwd.data.models.SwipingProfileModel.ProfileModel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.swipefwd.data.models.SwipingProfileModel.ProfileModel> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.swipefwd.data.models.SwipingProfileModel.ProfileModel> getConnectorProfileModel() {
        return null;
    }
    
    public final void setConnectorProfileModel(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.data.models.SwipingProfileModel.ProfileModel> p0) {
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean getHasLiked() {
        return false;
    }
    
    public final void setHasLiked(boolean p0) {
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean getHasSuperLiked() {
        return false;
    }
    
    public final void setHasSuperLiked(boolean p0) {
    }
}