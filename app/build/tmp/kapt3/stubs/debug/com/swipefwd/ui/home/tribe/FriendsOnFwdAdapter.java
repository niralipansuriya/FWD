package com.swipefwd.ui.home.tribe;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\"B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\u0013\u001a\u00020\b2\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0012j\b\u0012\u0004\u0012\u00020\u0007`\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0017H\u0016J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0017H\u0016J\u000e\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020!R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0002X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/swipefwd/ui/home/tribe/FriendsOnFwdAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/home/tribe/FriendsOnFwdAdapter$ContactListHolder;", "context", "Landroid/content/Context;", "inviteListener", "Lkotlin/Function1;", "Lcom/swipefwd/data/models/UserContactDetails;", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "getContext", "()Landroid/content/Context;", "myHolder", "getMyHolder", "()Lcom/swipefwd/ui/home/tribe/FriendsOnFwdAdapter$ContactListHolder;", "setMyHolder", "(Lcom/swipefwd/ui/home/tribe/FriendsOnFwdAdapter$ContactListHolder;)V", "usersList", "Ljava/util/ArrayList;", "addAll", "list", "Lkotlin/collections/ArrayList;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "showAdded", "isSuccess", "", "ContactListHolder", "app_debug"})
public final class FriendsOnFwdAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.home.tribe.FriendsOnFwdAdapter.ContactListHolder> {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final kotlin.jvm.functions.Function1<com.swipefwd.data.models.UserContactDetails, kotlin.Unit> inviteListener = null;
    @org.jetbrains.annotations.Nullable()
    private com.swipefwd.ui.home.tribe.FriendsOnFwdAdapter.ContactListHolder myHolder;
    private final java.util.ArrayList<com.swipefwd.data.models.UserContactDetails> usersList = null;
    
    public FriendsOnFwdAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.swipefwd.data.models.UserContactDetails, kotlin.Unit> inviteListener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.ui.home.tribe.FriendsOnFwdAdapter.ContactListHolder getMyHolder() {
        return null;
    }
    
    public final void setMyHolder(@org.jetbrains.annotations.Nullable()
    com.swipefwd.ui.home.tribe.FriendsOnFwdAdapter.ContactListHolder p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.home.tribe.FriendsOnFwdAdapter.ContactListHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.home.tribe.FriendsOnFwdAdapter.ContactListHolder holder, int position) {
    }
    
    public final void showAdded(boolean isSuccess) {
    }
    
    public final void addAll(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.data.models.UserContactDetails> list) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/home/tribe/FriendsOnFwdAdapter$ContactListHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemFriendsFwdListBinding;", "(Lcom/swipefwd/databinding/ItemFriendsFwdListBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemFriendsFwdListBinding;", "app_debug"})
    public static final class ContactListHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.databinding.ItemFriendsFwdListBinding binding = null;
        
        public ContactListHolder(@org.jetbrains.annotations.NotNull()
        com.swipefwd.databinding.ItemFriendsFwdListBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.databinding.ItemFriendsFwdListBinding getBinding() {
            return null;
        }
    }
}