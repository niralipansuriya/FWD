package com.swipefwd.ui.home.message;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001dB1\u0012\u0016\b\u0002\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\r\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000fJ\u0006\u0010\u0010\u001a\u00020\u0006J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u00062\n\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\u001c\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0012H\u0016J\u0014\u0010\u001a\u001a\u00020\u0006*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/swipefwd/ui/home/message/TribeMessageHorizontalAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/home/message/TribeMessageHorizontalAdapter$MyHolder;", "listener", "Lkotlin/Function1;", "Lcom/swipefwd/xmpp/database/Rosters;", "", "clickListener", "Lcom/swipefwd/data/models/SwipingProfileModel$ProfileModel;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "list", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/SwipingProfileModel;", "addMatches", "tempList", "", "clear", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "openDeleteDaterDialog", "Landroid/content/Context;", "rosters", "MyHolder", "app_debug"})
public final class TribeMessageHorizontalAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.home.message.TribeMessageHorizontalAdapter.MyHolder> {
    private kotlin.jvm.functions.Function1<? super com.swipefwd.xmpp.database.Rosters, kotlin.Unit> listener;
    private kotlin.jvm.functions.Function1<? super com.swipefwd.data.models.SwipingProfileModel.ProfileModel, kotlin.Unit> clickListener;
    private java.util.ArrayList<com.swipefwd.data.models.SwipingProfileModel> list;
    
    public TribeMessageHorizontalAdapter(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super com.swipefwd.xmpp.database.Rosters, kotlin.Unit> listener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.swipefwd.data.models.SwipingProfileModel.ProfileModel, kotlin.Unit> clickListener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.home.message.TribeMessageHorizontalAdapter.MyHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.home.message.TribeMessageHorizontalAdapter.MyHolder holder, int position) {
    }
    
    private final void openDeleteDaterDialog(android.content.Context $this$openDeleteDaterDialog, com.swipefwd.xmpp.database.Rosters rosters) {
    }
    
    public final void addMatches(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.SwipingProfileModel> tempList) {
    }
    
    public final void clear() {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/home/message/TribeMessageHorizontalAdapter$MyHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemMessageMatchesBinding;", "(Lcom/swipefwd/ui/home/message/TribeMessageHorizontalAdapter;Lcom/swipefwd/databinding/ItemMessageMatchesBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemMessageMatchesBinding;", "app_debug"})
    public final class MyHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.databinding.ItemMessageMatchesBinding binding = null;
        
        public MyHolder(@org.jetbrains.annotations.NotNull()
        com.swipefwd.databinding.ItemMessageMatchesBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.databinding.ItemMessageMatchesBinding getBinding() {
            return null;
        }
    }
}