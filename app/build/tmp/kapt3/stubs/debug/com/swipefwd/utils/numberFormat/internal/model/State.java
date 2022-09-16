package com.swipefwd.utils.numberFormat.internal.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/swipefwd/utils/numberFormat/internal/model/State;", "", "()V", "Attached", "AttachedWithoutCountry", "Ready", "Lcom/swipefwd/utils/numberFormat/internal/model/State$Ready;", "Lcom/swipefwd/utils/numberFormat/internal/model/State$Attached;", "Lcom/swipefwd/utils/numberFormat/internal/model/State$AttachedWithoutCountry;", "app_debug"})
public abstract class State {
    
    private State() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/swipefwd/utils/numberFormat/internal/model/State$Ready;", "Lcom/swipefwd/utils/numberFormat/internal/model/State;", "()V", "app_debug"})
    public static final class Ready extends com.swipefwd.utils.numberFormat.internal.model.State {
        @org.jetbrains.annotations.NotNull()
        public static final com.swipefwd.utils.numberFormat.internal.model.State.Ready INSTANCE = null;
        
        private Ready() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/swipefwd/utils/numberFormat/internal/model/State$Attached;", "Lcom/swipefwd/utils/numberFormat/internal/model/State;", "country", "Lcom/swipefwd/utils/numberFormat/api/Country;", "pattern", "", "(Lcom/swipefwd/utils/numberFormat/api/Country;Ljava/lang/String;)V", "getCountry", "()Lcom/swipefwd/utils/numberFormat/api/Country;", "getPattern", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class Attached extends com.swipefwd.utils.numberFormat.internal.model.State {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.utils.numberFormat.api.Country country = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String pattern = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.numberFormat.internal.model.State.Attached copy(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.numberFormat.api.Country country, @org.jetbrains.annotations.NotNull()
        java.lang.String pattern) {
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
        
        public Attached(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.numberFormat.api.Country country, @org.jetbrains.annotations.NotNull()
        java.lang.String pattern) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.numberFormat.api.Country component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.numberFormat.api.Country getCountry() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPattern() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/swipefwd/utils/numberFormat/internal/model/State$AttachedWithoutCountry;", "Lcom/swipefwd/utils/numberFormat/internal/model/State;", "pattern", "", "(Ljava/lang/String;)V", "getPattern", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class AttachedWithoutCountry extends com.swipefwd.utils.numberFormat.internal.model.State {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String pattern = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.numberFormat.internal.model.State.AttachedWithoutCountry copy(@org.jetbrains.annotations.NotNull()
        java.lang.String pattern) {
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
        
        public AttachedWithoutCountry(@org.jetbrains.annotations.NotNull()
        java.lang.String pattern) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPattern() {
            return null;
        }
    }
}