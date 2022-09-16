package com.swipefwd.ui.home.tribe;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001)B;\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\f\u00a2\u0006\u0002\u0010\u000eJ\u001e\u0010\u0018\u001a\u00020\n2\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0010j\b\u0012\u0004\u0012\u00020\b`\u0011J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\tH\u0016J\u0018\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0018\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\tH\u0016J\u0010\u0010$\u001a\u00020\n2\b\u0010%\u001a\u0004\u0018\u00010\u0005J\u0016\u0010&\u001a\u00020\n2\u0006\u0010\'\u001a\u00020\t2\u0006\u0010(\u001a\u00020\rR\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0010j\b\u0012\u0004\u0012\u00020\b`\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0010j\b\u0012\u0004\u0012\u00020\b`\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0002X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/swipefwd/ui/home/tribe/ContactListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/home/tribe/ContactListAdapter$ContactListHolder;", "Landroid/widget/Filterable;", "isoCode", "", "listener", "Lkotlin/Function2;", "Lcom/swipefwd/data/models/UserContactDetails;", "", "", "onNothingFound", "Lkotlin/Function1;", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "contactList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "filterList", "myHolder", "getMyHolder", "()Lcom/swipefwd/ui/home/tribe/ContactListAdapter$ContactListHolder;", "setMyHolder", "(Lcom/swipefwd/ui/home/tribe/ContactListAdapter$ContactListHolder;)V", "addAll", "list", "getFilter", "Landroid/widget/Filter;", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "search", "s", "showAdded", "isReInvite", "isSuccess", "ContactListHolder", "app_debug"})
public final class ContactListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.home.tribe.ContactListAdapter.ContactListHolder> implements android.widget.Filterable {
    private final java.lang.String isoCode = null;
    private final kotlin.jvm.functions.Function2<com.swipefwd.data.models.UserContactDetails, java.lang.Integer, kotlin.Unit> listener = null;
    private kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onNothingFound;
    private final java.util.ArrayList<com.swipefwd.data.models.UserContactDetails> contactList = null;
    private java.util.ArrayList<com.swipefwd.data.models.UserContactDetails> filterList;
    @org.jetbrains.annotations.Nullable()
    private com.swipefwd.ui.home.tribe.ContactListAdapter.ContactListHolder myHolder;
    
    public ContactListAdapter(@org.jetbrains.annotations.NotNull()
    java.lang.String isoCode, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.swipefwd.data.models.UserContactDetails, ? super java.lang.Integer, kotlin.Unit> listener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onNothingFound) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.ui.home.tribe.ContactListAdapter.ContactListHolder getMyHolder() {
        return null;
    }
    
    public final void setMyHolder(@org.jetbrains.annotations.Nullable()
    com.swipefwd.ui.home.tribe.ContactListAdapter.ContactListHolder p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.home.tribe.ContactListAdapter.ContactListHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.home.tribe.ContactListAdapter.ContactListHolder holder, int position) {
    }
    
    public final void search(@org.jetbrains.annotations.Nullable()
    java.lang.String s) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.widget.Filter getFilter() {
        return null;
    }
    
    public final void addAll(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.swipefwd.data.models.UserContactDetails> list) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public final void showAdded(int isReInvite, boolean isSuccess) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/home/tribe/ContactListAdapter$ContactListHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemContactListBinding;", "(Lcom/swipefwd/databinding/ItemContactListBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemContactListBinding;", "app_debug"})
    public static final class ContactListHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.databinding.ItemContactListBinding binding = null;
        
        public ContactListHolder(@org.jetbrains.annotations.NotNull()
        com.swipefwd.databinding.ItemContactListBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.databinding.ItemContactListBinding getBinding() {
            return null;
        }
    }
}