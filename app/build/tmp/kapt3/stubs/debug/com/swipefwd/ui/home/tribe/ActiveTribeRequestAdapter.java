package com.swipefwd.ui.home.tribe;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u00012\u00020\u0003:\u0001&B5\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u0015\u001a\u00020\n2\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\tH\u0016J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u001bJ\u001c\u0010\u001c\u001a\u00020\n2\n\u0010\u001d\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001e\u001a\u00020\tH\u0016J\u001c\u0010\u001f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\tH\u0016J&\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010%2\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n\u0018\u00010\u0014R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/swipefwd/ui/home/tribe/ActiveTribeRequestAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/home/tribe/ActiveTribeRequestAdapter$MyHolder;", "Landroid/widget/Filterable;", "mContext", "Landroid/content/Context;", "listener", "Lkotlin/Function2;", "", "", "", "listenerSize", "Lkotlin/Function0;", "(Landroid/content/Context;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;)V", "filterList", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/ActiveExpireRequestListModel$Pending;", "Lkotlin/collections/ArrayList;", "mList", "onNothingFound", "Lkotlin/Function1;", "addAll", "list", "getFilter", "Landroid/widget/Filter;", "getItemCount", "getSelectedUser", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "search", "s", "", "MyHolder", "app_debug"})
public final class ActiveTribeRequestAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.home.tribe.ActiveTribeRequestAdapter.MyHolder> implements android.widget.Filterable {
    private android.content.Context mContext;
    private final kotlin.jvm.functions.Function2<java.lang.Boolean, java.lang.Integer, kotlin.Unit> listener = null;
    private final kotlin.jvm.functions.Function0<kotlin.Unit> listenerSize = null;
    private final java.util.ArrayList<com.swipefwd.data.models.ActiveExpireRequestListModel.Pending> mList = null;
    private java.util.ArrayList<com.swipefwd.data.models.ActiveExpireRequestListModel.Pending> filterList;
    private kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onNothingFound;
    
    public ActiveTribeRequestAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.Integer, kotlin.Unit> listener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> listenerSize) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.home.tribe.ActiveTribeRequestAdapter.MyHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.home.tribe.ActiveTribeRequestAdapter.MyHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
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
    
    public final void addAll(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.data.models.ActiveExpireRequestListModel.Pending> list) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.swipefwd.data.models.ActiveExpireRequestListModel.Pending> getSelectedUser() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/home/tribe/ActiveTribeRequestAdapter$MyHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemActiveTribesBinding;", "(Lcom/swipefwd/ui/home/tribe/ActiveTribeRequestAdapter;Lcom/swipefwd/databinding/ItemActiveTribesBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemActiveTribesBinding;", "app_debug"})
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