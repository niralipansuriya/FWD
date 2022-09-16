package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\f\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\tJ\b\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bH\u0016J\u000e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0005R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/swipefwd/ui/profile/SelectVaccineStatusAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/profile/SelectVaccineStatusAdapter$CovidHolder;", "itemClickListener", "Lkotlin/Function1;", "Lcom/swipefwd/data/models/VaccinationModel$VaccinationData$VaccinationLevel;", "", "(Lkotlin/jvm/functions/Function1;)V", "arrayList", "Ljava/util/ArrayList;", "selectedPos", "", "addAll", "list", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setSelected", "model", "CovidHolder", "app_debug"})
public final class SelectVaccineStatusAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.profile.SelectVaccineStatusAdapter.CovidHolder> {
    private final kotlin.jvm.functions.Function1<com.swipefwd.data.models.VaccinationModel.VaccinationData.VaccinationLevel, kotlin.Unit> itemClickListener = null;
    private int selectedPos = 0;
    private final java.util.ArrayList<com.swipefwd.data.models.VaccinationModel.VaccinationData.VaccinationLevel> arrayList = null;
    
    public SelectVaccineStatusAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.swipefwd.data.models.VaccinationModel.VaccinationData.VaccinationLevel, kotlin.Unit> itemClickListener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.profile.SelectVaccineStatusAdapter.CovidHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.profile.SelectVaccineStatusAdapter.CovidHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void addAll(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.data.models.VaccinationModel.VaccinationData.VaccinationLevel> list) {
    }
    
    public final void setSelected(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.VaccinationModel.VaccinationData.VaccinationLevel model) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/profile/SelectVaccineStatusAdapter$CovidHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemSelectCommonBinding;", "(Lcom/swipefwd/databinding/ItemSelectCommonBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemSelectCommonBinding;", "app_debug"})
    public static final class CovidHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.databinding.ItemSelectCommonBinding binding = null;
        
        public CovidHolder(@org.jetbrains.annotations.NotNull()
        com.swipefwd.databinding.ItemSelectCommonBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.databinding.ItemSelectCommonBinding getBinding() {
            return null;
        }
    }
}