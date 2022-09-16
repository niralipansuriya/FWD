package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000eJ\b\u0010\u000f\u001a\u00020\u0007H\u0016J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u0018\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007H\u0016J\b\u0010\u0017\u001a\u0004\u0018\u00010\nJ\u000e\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/swipefwd/ui/profile/SelectAstroSignAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/profile/SelectAstroSignAdapter$SignHolder;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "selectedPos", "", "signList", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/AstrologicalModel$AstrologicalData$AstrologicalSignLevel;", "addAll", "", "signs", "", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "selectedSign", "setSelected", "model", "SignHolder", "app_debug"})
public final class SelectAstroSignAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.profile.SelectAstroSignAdapter.SignHolder> {
    private final android.content.Context mContext = null;
    private int selectedPos = 0;
    private final java.util.ArrayList<com.swipefwd.data.models.AstrologicalModel.AstrologicalData.AstrologicalSignLevel> signList = null;
    
    public SelectAstroSignAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.profile.SelectAstroSignAdapter.SignHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.profile.SelectAstroSignAdapter.SignHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void addAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.AstrologicalModel.AstrologicalData.AstrologicalSignLevel> signs) {
    }
    
    public final void setSelected(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.AstrologicalModel.AstrologicalData.AstrologicalSignLevel model) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.data.models.AstrologicalModel.AstrologicalData.AstrologicalSignLevel selectedSign() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/profile/SelectAstroSignAdapter$SignHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemSelectCommonBinding;", "(Lcom/swipefwd/databinding/ItemSelectCommonBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemSelectCommonBinding;", "app_debug"})
    public static final class SignHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.databinding.ItemSelectCommonBinding binding = null;
        
        public SignHolder(@org.jetbrains.annotations.NotNull()
        com.swipefwd.databinding.ItemSelectCommonBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.databinding.ItemSelectCommonBinding getBinding() {
            return null;
        }
    }
}