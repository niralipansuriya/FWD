package com.swipefwd.ui.swiping.dater_both;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B\'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\u0010\tJ\u001e\u0010\u0010\u001a\u00020\b2\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fJ\b\u0010\u0012\u001a\u00020\u0007H\u0016J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0007H\u0017J\u0018\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007H\u0016J8\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001c2\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001e0\rj\b\u0012\u0004\u0012\u00020\u001e`\u000f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/swipefwd/ui/swiping/dater_both/SwipeConnectorCardAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/swiping/dater_both/SwipeConnectorCardAdapter$ViewHolder;", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "itemClickListener", "Lkotlin/Function2;", "", "", "(Landroidx/appcompat/app/AppCompatActivity;Lkotlin/jvm/functions/Function2;)V", "instagramAdapter", "Lcom/swipefwd/ui/swiping/dater_both/InstagramImageAdapter;", "profileList", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/SwipingCustomModel;", "Lkotlin/collections/ArrayList;", "addAll", "list", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "openFacebookFriendDialog", "isFbMutual", "", "model", "Lcom/swipefwd/data/models/SwipingProfileModel$ProfileModel$MutualFriend;", "context", "Landroid/content/Context;", "name", "", "ViewHolder", "app_debug"})
public final class SwipeConnectorCardAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.swiping.dater_both.SwipeConnectorCardAdapter.ViewHolder> {
    private final androidx.appcompat.app.AppCompatActivity activity = null;
    private final kotlin.jvm.functions.Function2<java.lang.Integer, java.lang.Integer, kotlin.Unit> itemClickListener = null;
    private final java.util.ArrayList<com.swipefwd.data.models.SwipingCustomModel> profileList = null;
    private com.swipefwd.ui.swiping.dater_both.InstagramImageAdapter instagramAdapter;
    
    public SwipeConnectorCardAdapter(@org.jetbrains.annotations.NotNull()
    androidx.appcompat.app.AppCompatActivity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.Integer, kotlin.Unit> itemClickListener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.swiping.dater_both.SwipeConnectorCardAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.swiping.dater_both.SwipeConnectorCardAdapter.ViewHolder holder, int position) {
    }
    
    private final void openFacebookFriendDialog(boolean isFbMutual, java.util.ArrayList<com.swipefwd.data.models.SwipingProfileModel.ProfileModel.MutualFriend> model, android.content.Context context, java.lang.String name) {
    }
    
    public final void addAll(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.data.models.SwipingCustomModel> list) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/swiping/dater_both/SwipeConnectorCardAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemSwipingCardNewBinding;", "(Lcom/swipefwd/databinding/ItemSwipingCardNewBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemSwipingCardNewBinding;", "app_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.databinding.ItemSwipingCardNewBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.swipefwd.databinding.ItemSwipingCardNewBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.databinding.ItemSwipingCardNewBinding getBinding() {
            return null;
        }
    }
}