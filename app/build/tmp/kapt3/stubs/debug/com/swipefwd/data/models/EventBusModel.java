package com.swipefwd.data.models;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003J1\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020\u0006H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u00a8\u0006\""}, d2 = {"Lcom/swipefwd/data/models/EventBusModel;", "", "event", "", "activityName", "swipeDirection", "", "profileModel", "Lcom/swipefwd/data/models/SwipingProfileModel$ProfileModel;", "(Ljava/lang/String;Ljava/lang/String;ILcom/swipefwd/data/models/SwipingProfileModel$ProfileModel;)V", "getActivityName", "()Ljava/lang/String;", "setActivityName", "(Ljava/lang/String;)V", "getEvent", "setEvent", "getProfileModel", "()Lcom/swipefwd/data/models/SwipingProfileModel$ProfileModel;", "setProfileModel", "(Lcom/swipefwd/data/models/SwipingProfileModel$ProfileModel;)V", "getSwipeDirection", "()I", "setSwipeDirection", "(I)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class EventBusModel {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String event;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String activityName;
    private int swipeDirection;
    @org.jetbrains.annotations.NotNull()
    private com.swipefwd.data.models.SwipingProfileModel.ProfileModel profileModel;
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.EventBusModel copy(@org.jetbrains.annotations.NotNull()
    java.lang.String event, @org.jetbrains.annotations.NotNull()
    java.lang.String activityName, int swipeDirection, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.SwipingProfileModel.ProfileModel profileModel) {
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
    
    public EventBusModel(@org.jetbrains.annotations.NotNull()
    java.lang.String event, @org.jetbrains.annotations.NotNull()
    java.lang.String activityName, int swipeDirection, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.SwipingProfileModel.ProfileModel profileModel) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEvent() {
        return null;
    }
    
    public final void setEvent(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getActivityName() {
        return null;
    }
    
    public final void setActivityName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getSwipeDirection() {
        return 0;
    }
    
    public final void setSwipeDirection(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.SwipingProfileModel.ProfileModel component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.SwipingProfileModel.ProfileModel getProfileModel() {
        return null;
    }
    
    public final void setProfileModel(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.SwipingProfileModel.ProfileModel p0) {
    }
}