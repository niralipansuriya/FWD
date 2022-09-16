package com.swipefwd.data.models;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0001$B1\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J<\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001J\t\u0010#\u001a\u00020\u0007H\u00d6\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016\u00a8\u0006%"}, d2 = {"Lcom/swipefwd/data/models/EducationDetailModel;", "", "code", "", "data", "Lcom/swipefwd/data/models/EducationDetailModel$DataEducatiton;", "message", "", "status", "(Ljava/lang/Integer;Lcom/swipefwd/data/models/EducationDetailModel$DataEducatiton;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/Integer;", "setCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getData", "()Lcom/swipefwd/data/models/EducationDetailModel$DataEducatiton;", "setData", "(Lcom/swipefwd/data/models/EducationDetailModel$DataEducatiton;)V", "getMessage", "()Ljava/lang/String;", "setMessage", "(Ljava/lang/String;)V", "getStatus", "setStatus", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Lcom/swipefwd/data/models/EducationDetailModel$DataEducatiton;Ljava/lang/String;Ljava/lang/String;)Lcom/swipefwd/data/models/EducationDetailModel;", "equals", "", "other", "hashCode", "toString", "DataEducatiton", "app_debug"})
public final class EducationDetailModel {
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "code")
    private java.lang.Integer code;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "data")
    private com.swipefwd.data.models.EducationDetailModel.DataEducatiton data;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "message")
    private java.lang.String message;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "status")
    private java.lang.String status;
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.EducationDetailModel copy(@org.jetbrains.annotations.Nullable()
    java.lang.Integer code, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.EducationDetailModel.DataEducatiton data, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
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
    
    public EducationDetailModel(@org.jetbrains.annotations.Nullable()
    java.lang.Integer code, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.EducationDetailModel.DataEducatiton data, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.String status) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getCode() {
        return null;
    }
    
    public final void setCode(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.EducationDetailModel.DataEducatiton component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.models.EducationDetailModel.DataEducatiton getData() {
        return null;
    }
    
    public final void setData(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.EducationDetailModel.DataEducatiton p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getStatus() {
        return null;
    }
    
    public final void setStatus(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u0013B#\u0012\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005\u00a2\u0006\u0002\u0010\u0006J\u001d\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005H\u00c6\u0003J\'\u0010\u000b\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R2\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006\u00a8\u0006\u0014"}, d2 = {"Lcom/swipefwd/data/models/EducationDetailModel$DataEducatiton;", "", "education", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/EducationDetailModel$DataEducatiton$EducationData;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getEducation", "()Ljava/util/ArrayList;", "setEducation", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "EducationData", "app_debug"})
    public static final class DataEducatiton {
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "education")
        private java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData> education;
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.data.models.EducationDetailModel.DataEducatiton copy(@org.jetbrains.annotations.Nullable()
        java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData> education) {
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
        
        public DataEducatiton() {
            super();
        }
        
        public DataEducatiton(@org.jetbrains.annotations.Nullable()
        java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData> education) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData> component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData> getEducation() {
            return null;
        }
        
        public final void setEducation(@org.jetbrains.annotations.Nullable()
        java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData> p0) {
        }
        
        @androidx.room.Entity()
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\bJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J&\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0005H\u00d6\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0019"}, d2 = {"Lcom/swipefwd/data/models/EducationDetailModel$DataEducatiton$EducationData;", "", "_id", "", "education_level", "", "(Ljava/lang/Integer;Ljava/lang/String;)V", "get_id", "()Ljava/lang/Integer;", "set_id", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getEducation_level", "()Ljava/lang/String;", "setEducation_level", "(Ljava/lang/String;)V", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/swipefwd/data/models/EducationDetailModel$DataEducatiton$EducationData;", "equals", "", "other", "hashCode", "toString", "app_debug"})
        public static final class EducationData {
            @org.jetbrains.annotations.Nullable()
            @com.google.gson.annotations.SerializedName(value = "_id")
            private java.lang.Integer _id;
            @org.jetbrains.annotations.Nullable()
            @com.google.gson.annotations.SerializedName(value = "education_level")
            private java.lang.String education_level;
            
            @org.jetbrains.annotations.NotNull()
            public final com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData copy(@org.jetbrains.annotations.Nullable()
            java.lang.Integer _id, @org.jetbrains.annotations.Nullable()
            java.lang.String education_level) {
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
            
            public EducationData() {
                super();
            }
            
            public EducationData(@org.jetbrains.annotations.Nullable()
            java.lang.Integer _id, @org.jetbrains.annotations.Nullable()
            java.lang.String education_level) {
                super();
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component1() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer get_id() {
                return null;
            }
            
            public final void set_id(@org.jetbrains.annotations.Nullable()
            java.lang.Integer p0) {
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.String getEducation_level() {
                return null;
            }
            
            public final void setEducation_level(@org.jetbrains.annotations.Nullable()
            java.lang.String p0) {
            }
        }
    }
}