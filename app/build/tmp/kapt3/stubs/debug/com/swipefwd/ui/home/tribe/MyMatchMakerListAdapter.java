package com.swipefwd.ui.home.tribe;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002#$B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\u0010\nJ\"\u0010\u0011\u001a\u00020\b2\u001a\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000ej\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u0013J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0015H\u0016J&\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0016J\u0018\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0015H\u0016J\u000e\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\"R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/swipefwd/ui/home/tribe/MyMatchMakerListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/home/tribe/MyMatchMakerListAdapter$FriendsListHolder;", "activity", "Landroid/app/Activity;", "startChat", "Lkotlin/Function1;", "Lcom/swipefwd/data/models/TribeDaterConnectionsResponseModel$TribeModel$Tribe;", "", "deleteListener", "(Landroid/app/Activity;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getActivity", "()Landroid/app/Activity;", "mList", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/DaterConnectionResponseModel$User;", "mTribeList", "addAll", "list", "Lkotlin/collections/ArrayList;", "getItemCount", "", "onBindViewHolder", "holder", "position", "payloads", "", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "statusOnlineOFfline", "from", "Lcom/swipefwd/xmpp/XmppRosterEntry;", "FriendsListHolder", "PayLoad", "app_debug"})
public final class MyMatchMakerListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.home.tribe.MyMatchMakerListAdapter.FriendsListHolder> {
    @org.jetbrains.annotations.NotNull()
    private final android.app.Activity activity = null;
    private kotlin.jvm.functions.Function1<? super com.swipefwd.data.models.TribeDaterConnectionsResponseModel.TribeModel.Tribe, kotlin.Unit> startChat;
    private final kotlin.jvm.functions.Function1<com.swipefwd.data.models.TribeDaterConnectionsResponseModel.TribeModel.Tribe, kotlin.Unit> deleteListener = null;
    private final java.util.ArrayList<com.swipefwd.data.models.DaterConnectionResponseModel.User> mList = null;
    private final java.util.ArrayList<com.swipefwd.data.models.TribeDaterConnectionsResponseModel.TribeModel.Tribe> mTribeList = null;
    
    public MyMatchMakerListAdapter(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.swipefwd.data.models.TribeDaterConnectionsResponseModel.TribeModel.Tribe, kotlin.Unit> startChat, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.swipefwd.data.models.TribeDaterConnectionsResponseModel.TribeModel.Tribe, kotlin.Unit> deleteListener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Activity getActivity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.home.tribe.MyMatchMakerListAdapter.FriendsListHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.home.tribe.MyMatchMakerListAdapter.FriendsListHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void addAll(@org.jetbrains.annotations.Nullable()
    java.util.ArrayList<com.swipefwd.data.models.TribeDaterConnectionsResponseModel.TribeModel.Tribe> list) {
    }
    
    public final void statusOnlineOFfline(@org.jetbrains.annotations.NotNull()
    com.swipefwd.xmpp.XmppRosterEntry from) {
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.home.tribe.MyMatchMakerListAdapter.FriendsListHolder holder, int position, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Object> payloads) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/home/tribe/MyMatchMakerListAdapter$FriendsListHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemMatchMakerListBinding;", "(Lcom/swipefwd/databinding/ItemMatchMakerListBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemMatchMakerListBinding;", "app_debug"})
    public static final class FriendsListHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.databinding.ItemMatchMakerListBinding binding = null;
        
        public FriendsListHolder(@org.jetbrains.annotations.NotNull()
        com.swipefwd.databinding.ItemMatchMakerListBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.databinding.ItemMatchMakerListBinding getBinding() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/swipefwd/ui/home/tribe/MyMatchMakerListAdapter$PayLoad;", "", "(Ljava/lang/String;I)V", "IN_STATUS", "app_debug"})
    static enum PayLoad {
        /*public static final*/ IN_STATUS /* = new IN_STATUS() */;
        
        PayLoad() {
        }
    }
}