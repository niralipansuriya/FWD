package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\u00102\b\b\u0002\u0010I\u001a\u000205J\u0006\u0010J\u001a\u00020GJ\u0016\u0010?\u001a\u00020G2\u0006\u0010K\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0010J\u000e\u0010M\u001a\u00020G2\u0006\u0010N\u001a\u00020\u0010J\'\u0010O\u001a\u00020G\"\u0004\b\u0000\u0010P2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002HP0Q2\u0006\u0010N\u001a\u0002HP\u00a2\u0006\u0002\u0010RJ\u000e\u0010S\u001a\u00020G2\u0006\u0010N\u001a\u00020\u0010R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R0\u0010\u0015\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u00170\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR0\u0010\u001c\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f0\tj\b\u0012\u0004\u0012\u00020\f`\u00170\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0019\"\u0004\b\"\u0010\u001bR \u0010#\u001a\b\u0012\u0004\u0012\u00020\u00100\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0019\"\u0004\b%\u0010\u001bR\u001b\u0010&\u001a\u00020\'8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b(\u0010)R\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00100-\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\u00100-\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010/R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u00100-\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010/R\u0017\u00104\u001a\b\u0012\u0004\u0012\u0002050-\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010/R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\u00140-\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010/R\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020\u00100-\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010/R\u0017\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00100-\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010/R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u0002050-\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010/R0\u0010?\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00120\tj\b\u0012\u0004\u0012\u00020\u0012`\u00170\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0019\"\u0004\bA\u0010\u001bR\u0014\u0010B\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010C\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0019\"\u0004\bE\u0010\u001b\u00a8\u0006T"}, d2 = {"Lcom/swipefwd/ui/profile/SelectSchoolViewModel;", "Lcom/swipefwd/ui/activity/BaseViewModel;", "context", "Landroid/content/Context;", "appRepository", "Lcom/swipefwd/data/source/AppRepository;", "(Landroid/content/Context;Lcom/swipefwd/data/source/AppRepository;)V", "_data", "Landroidx/lifecycle/MutableLiveData;", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/EducationLevelListModel$LevelModel;", "_educationData", "Lcom/swipefwd/data/models/EducationDetailModel$DataEducatiton$EducationData;", "_error", "Lcom/swipefwd/data/models/EducationLevelListModel;", "_errorMessage", "", "_instituteList", "Lcom/swipefwd/data/models/InstitutesModel$Result;", "_showLoading", "", "data", "Landroidx/lifecycle/LiveData;", "Lkotlin/collections/ArrayList;", "getData", "()Landroidx/lifecycle/LiveData;", "setData", "(Landroidx/lifecycle/LiveData;)V", "educationData", "getEducationData", "setEducationData", "educationList", "error", "getError", "setError", "errorMessage", "getErrorMessage", "setErrorMessage", "fwdDataStore", "Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "getFwdDataStore", "()Lcom/swipefwd/utils/datastore/InternalAppDataStore;", "fwdDataStore$delegate", "Lkotlin/Lazy;", "getAuthToken", "Lkotlinx/coroutines/flow/Flow;", "getGetAuthToken", "()Lkotlinx/coroutines/flow/Flow;", "getEducation", "getGetEducation", "getEndHeight", "getGetEndHeight", "getEndHeightInches", "", "getGetEndHeightInches", "getIsHeightFeet", "getGetIsHeightFeet", "getPrefEducation", "getGetPrefEducation", "getStartHeight", "getGetStartHeight", "getStartHeightInches", "getGetStartHeightInches", "instituteList", "getInstituteList", "setInstituteList", "levelList", "showLoading", "getShowLoading", "setShowLoading", "educationLevelList", "", "token", "page", "getEducationDetails", "query", "key", "savePrefSchool", "value", "savePreference", "T", "Landroidx/datastore/preferences/core/Preferences$Key;", "(Landroidx/datastore/preferences/core/Preferences$Key;Ljava/lang/Object;)V", "saveSchool", "app_debug"})
public final class SelectSchoolViewModel extends com.swipefwd.ui.activity.BaseViewModel {
    private final com.swipefwd.data.source.AppRepository appRepository = null;
    private final kotlin.Lazy fwdDataStore$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getAuthToken = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getIsHeightFeet = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getStartHeight = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getEndHeight = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> getStartHeightInches = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> getEndHeightInches = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getEducation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> getPrefEducation = null;
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> _showLoading;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.lang.Boolean> showLoading;
    private androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.lang.String> errorMessage;
    private androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.swipefwd.data.models.EducationLevelListModel.LevelModel>> _data;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.util.ArrayList<com.swipefwd.data.models.EducationLevelListModel.LevelModel>> data;
    private androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData>> _educationData;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData>> educationData;
    private androidx.lifecycle.MutableLiveData<com.swipefwd.data.models.EducationLevelListModel> _error;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<com.swipefwd.data.models.EducationLevelListModel> error;
    private androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Result>> _instituteList;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Result>> instituteList;
    private final java.util.ArrayList<com.swipefwd.data.models.EducationLevelListModel.LevelModel> levelList = null;
    private final java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData> educationList = null;
    
    public SelectSchoolViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.swipefwd.data.source.AppRepository appRepository) {
        super(null);
    }
    
    private final com.swipefwd.utils.datastore.InternalAppDataStore getFwdDataStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetAuthToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getGetIsHeightFeet() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetStartHeight() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetEndHeight() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getGetStartHeightInches() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getGetEndHeightInches() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetEducation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGetPrefEducation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getShowLoading() {
        return null;
    }
    
    public final void setShowLoading(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
    
    public final void setErrorMessage(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.ArrayList<com.swipefwd.data.models.EducationLevelListModel.LevelModel>> getData() {
        return null;
    }
    
    public final void setData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.ArrayList<com.swipefwd.data.models.EducationLevelListModel.LevelModel>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData>> getEducationData() {
        return null;
    }
    
    public final void setEducationData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.ArrayList<com.swipefwd.data.models.EducationDetailModel.DataEducatiton.EducationData>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.swipefwd.data.models.EducationLevelListModel> getError() {
        return null;
    }
    
    public final void setError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.swipefwd.data.models.EducationLevelListModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Result>> getInstituteList() {
        return null;
    }
    
    public final void setInstituteList(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.util.ArrayList<com.swipefwd.data.models.InstitutesModel.Result>> p0) {
    }
    
    public final void saveSchool(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void savePrefSchool(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final <T extends java.lang.Object>void savePreference(@org.jetbrains.annotations.NotNull()
    androidx.datastore.preferences.core.Preferences.Key<T> key, T value) {
    }
    
    public final void educationLevelList(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int page) {
    }
    
    public final void getEducationDetails() {
    }
    
    public final void instituteList(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
}