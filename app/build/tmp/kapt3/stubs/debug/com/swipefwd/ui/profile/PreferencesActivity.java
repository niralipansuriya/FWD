package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010:\u001a\u00020;2\b\b\u0002\u0010<\u001a\u00020\rH\u0002J\b\u0010=\u001a\u00020;H\u0002J\b\u0010>\u001a\u00020;H\u0002J\b\u0010?\u001a\u00020;H\u0002J\u0012\u0010@\u001a\u00020;2\b\u0010A\u001a\u0004\u0018\u00010BH\u0014J\b\u0010C\u001a\u00020;H\u0014J\b\u0010D\u001a\u00020;H\u0002J\u0010\u0010E\u001a\u00020;2\u0006\u0010F\u001a\u00020\u0017H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001d\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010\"\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\'\u0010!\u001a\u0004\b%\u0010&R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b,\u0010!\u001a\u0004\b*\u0010+R\u000e\u0010-\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u00104\u001a\n\u0012\u0004\u0012\u000206\u0018\u000105X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006G"}, d2 = {"Lcom/swipefwd/ui/profile/PreferencesActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "astrologyModel", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/AstrologicalModel$AstrologicalData$AstrologicalSignLevel;", "binding", "Lcom/swipefwd/databinding/ActivityPreferencesBinding;", "castModel", "Lcom/swipefwd/data/models/CastListModel$CastModel;", "childrenModel", "Lcom/swipefwd/data/models/ChildrenModel$ChildrenData$ChildrenLevel;", "convertedEndHeight", "", "convertedStartHeight", "defaultAge", "dialogs", "Ljava/util/Vector;", "Landroid/app/Dialog;", "educationModel", "Lcom/swipefwd/data/models/EducationModel;", "endAge", "isContinueClick", "", "isFromSettings", "isInCM", "isVerifiedProfile", "languagesList", "Lcom/swipefwd/data/models/LanguageDataModel$LanguageData$Language;", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/profile/PreferencesActivity;", "mActivity$delegate", "Lkotlin/Lazy;", "maxDistance", "prefViewModel", "Lcom/swipefwd/ui/profile/AdvancePreferenceViewModel;", "getPrefViewModel", "()Lcom/swipefwd/ui/profile/AdvancePreferenceViewModel;", "prefViewModel$delegate", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "relationshipModel", "Lcom/swipefwd/data/models/RelationshipModel$RelationShipData$RelationshipLevel;", "religionModel", "Lcom/swipefwd/data/models/ReligionModel$ReligionData$ReligionLevel;", "smokingModel", "Lcom/swipefwd/data/models/SmokingDataModel$SmokingData$Smoking;", "startAge", "startForResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "userGender", "vaccineStatusModel", "Lcom/swipefwd/data/models/VaccinationModel$VaccinationData$VaccinationLevel;", "applyFontFamily", "", "selectedItem", "getSelectedData", "init", "initObservers", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "openPrivacyModeDialog", "submitUpdatePreference", "isUpdate", "app_debug"})
public final class PreferencesActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivityPreferencesBinding binding;
    private int userGender = 2;
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private final kotlin.Lazy prefViewModel$delegate = null;
    private int startAge = 18;
    private int defaultAge = 75;
    private int endAge = 99;
    private int maxDistance = 160;
    private boolean isVerifiedProfile = false;
    private int convertedStartHeight = 0;
    private int convertedEndHeight = 0;
    private com.swipefwd.data.models.EducationModel educationModel;
    private boolean isInCM = false;
    private boolean isFromSettings = false;
    private boolean isContinueClick = false;
    private androidx.activity.result.ActivityResultLauncher<android.content.Intent> startForResult;
    private java.util.ArrayList<com.swipefwd.data.models.LanguageDataModel.LanguageData.Language> languagesList;
    private java.util.ArrayList<com.swipefwd.data.models.CastListModel.CastModel> castModel;
    private final java.util.Vector<android.app.Dialog> dialogs = null;
    private com.swipefwd.data.models.ChildrenModel.ChildrenData.ChildrenLevel childrenModel;
    private java.util.ArrayList<com.swipefwd.data.models.ReligionModel.ReligionData.ReligionLevel> religionModel;
    private com.swipefwd.data.models.SmokingDataModel.SmokingData.Smoking smokingModel;
    private com.swipefwd.data.models.RelationshipModel.RelationShipData.RelationshipLevel relationshipModel;
    private com.swipefwd.data.models.VaccinationModel.VaccinationData.VaccinationLevel vaccineStatusModel;
    private java.util.ArrayList<com.swipefwd.data.models.AstrologicalModel.AstrologicalData.AstrologicalSignLevel> astrologyModel;
    
    public PreferencesActivity() {
        super();
    }
    
    private final com.swipefwd.ui.profile.PreferencesActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    private final com.swipefwd.ui.profile.AdvancePreferenceViewModel getPrefViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void submitUpdatePreference(boolean isUpdate) {
    }
    
    private final void initObservers() {
    }
    
    private final void getSelectedData() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void openPrivacyModeDialog() {
    }
    
    private final void applyFontFamily(int selectedItem) {
    }
}