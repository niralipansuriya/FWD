package com.swipefwd.ui.home.tribe;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u00012\u00020\u0003:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u000e\u001a\u00020\r2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u001c\u0010\u0014\u001a\u00020\r2\n\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0013H\u0016J\u001c\u0010\u0017\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0013H\u0016J&\u0010\u001b\u001a\u00020\r2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000bR\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/swipefwd/ui/home/tribe/ExpiredTribeRequestAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/home/tribe/ExpiredTribeRequestAdapter$MyHolder;", "Landroid/widget/Filterable;", "()V", "filterList", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/ActiveExpireRequestListModel$Expire;", "Lkotlin/collections/ArrayList;", "mList", "onNothingFound", "Lkotlin/Function1;", "", "", "addAll", "list", "getFilter", "Landroid/widget/Filter;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "search", "s", "", "MyHolder", "app_debug"})
public final class ExpiredTribeRequestAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.home.tribe.ExpiredTribeRequestAdapter.MyHolder> implements android.widget.Filterable {
    private final java.util.ArrayList<com.swipefwd.data.models.ActiveExpireRequestListModel.Expire> mList = null;
    private java.util.ArrayList<com.swipefwd.data.models.ActiveExpireRequestListModel.Expire> filterList;
    private kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onNothingFound;
    
    public ExpiredTribeRequestAdapter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.home.tribe.ExpiredTribeRequestAdapter.MyHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.home.tribe.ExpiredTribeRequestAdapter.MyHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void addAll(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.data.models.ActiveExpireRequestListModel.Expire> list) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.widget.Filter getFilter() {
        return null;
    }
    
    public final void search(@org.jetbrains.annotations.Nullable()
    java.lang.String s, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onNothingFound) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/home/tribe/ExpiredTribeRequestAdapter$MyHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemActiveTribesBinding;", "(Lcom/swipefwd/ui/home/tribe/ExpiredTribeRequestAdapter;Lcom/swipefwd/databinding/ItemActiveTribesBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemActiveTribesBinding;", "app_debug"})
    public final class MyHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.databinding.ItemActiveTribesBinding binding = null;
        
        public MyHolder(@org.jetbrains.annotations.NotNull()
        com.swipefwd.databinding.ItemActiveTribesBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.databinding.ItemActiveTribesBinding getBinding() {
            return null;
        }
    }
}