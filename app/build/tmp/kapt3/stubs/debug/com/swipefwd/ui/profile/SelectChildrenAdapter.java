package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\b\u0010\u0010\u001a\u00020\bH\u0016J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\bH\u0016J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bH\u0016J\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u001c"}, d2 = {"Lcom/swipefwd/ui/profile/SelectChildrenAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/profile/SelectChildrenAdapter$PreferenceHolder;", "()V", "arrayList", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/ChildrenModel$ChildrenData$ChildrenLevel;", "selectedPos", "", "getSelectedPos", "()I", "setSelectedPos", "(I)V", "addAll", "", "list", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "selectedChildrenPlan", "setSelected", "model", "PreferenceHolder", "app_debug"})
public final class SelectChildrenAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.profile.SelectChildrenAdapter.PreferenceHolder> {
    private int selectedPos = 0;
    private final java.util.ArrayList<com.swipefwd.data.models.ChildrenModel.ChildrenData.ChildrenLevel> arrayList = null;
    
    public SelectChildrenAdapter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.profile.SelectChildrenAdapter.PreferenceHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.profile.SelectChildrenAdapter.PreferenceHolder holder, int position) {
    }
    
    public final int getSelectedPos() {
        return 0;
    }
    
    public final void setSelectedPos(int p0) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void addAll(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.data.models.ChildrenModel.ChildrenData.ChildrenLevel> list) {
    }
    
    public final void setSelected(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.ChildrenModel.ChildrenData.ChildrenLevel model) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.data.models.ChildrenModel.ChildrenData.ChildrenLevel selectedChildrenPlan() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/profile/SelectChildrenAdapter$PreferenceHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemSelectCommonBinding;", "(Lcom/swipefwd/databinding/ItemSelectCommonBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemSelectCommonBinding;", "app_debug"})
    public static final class PreferenceHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.databinding.ItemSelectCommonBinding binding = null;
        
        public PreferenceHolder(@org.jetbrains.annotations.NotNull()
        com.swipefwd.databinding.ItemSelectCommonBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.databinding.ItemSelectCommonBinding getBinding() {
            return null;
        }
    }
}