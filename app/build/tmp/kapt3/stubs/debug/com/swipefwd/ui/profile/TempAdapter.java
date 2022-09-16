package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002*+B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\u001b\u001a\u00020\b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u001dJ\u0016\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u0016J\b\u0010!\u001a\u00020\nH\u0016J\u001c\u0010\"\u001a\u00020\b2\n\u0010#\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001f\u001a\u00020\nH\u0016J\u001c\u0010$\u001a\u00060\u0002R\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020\nH\u0016J\u000e\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u0007R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0010\u001a\b\u0018\u00010\u0002R\u00020\u0000X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u0006,"}, d2 = {"Lcom/swipefwd/ui/profile/TempAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/swipefwd/ui/profile/TempAdapter$MyItemHolder;", "context", "Landroid/content/Context;", "deleteListener", "Lkotlin/Function1;", "Lcom/swipefwd/data/models/UserImagesResponseModel$Item;", "", "addImageListener", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getContext", "()Landroid/content/Context;", "imageList", "Ljava/util/ArrayList;", "myHolder", "getMyHolder", "()Lcom/swipefwd/ui/profile/TempAdapter$MyItemHolder;", "setMyHolder", "(Lcom/swipefwd/ui/profile/TempAdapter$MyItemHolder;)V", "newList", "", "getNewList", "()Ljava/util/ArrayList;", "setNewList", "(Ljava/util/ArrayList;)V", "addAll", "list", "", "changePhotoInvalid", "position", "isOk", "getItemCount", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "removeImage", "model", "MyItemHolder", "PayLoad", "app_debug"})
public final class TempAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.swipefwd.ui.profile.TempAdapter.MyItemHolder> {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final kotlin.jvm.functions.Function1<com.swipefwd.data.models.UserImagesResponseModel.Item, kotlin.Unit> deleteListener = null;
    private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> addImageListener = null;
    @org.jetbrains.annotations.Nullable()
    private com.swipefwd.ui.profile.TempAdapter.MyItemHolder myHolder;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<java.lang.Boolean> newList;
    private final java.util.ArrayList<com.swipefwd.data.models.UserImagesResponseModel.Item> imageList = null;
    
    public TempAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.swipefwd.data.models.UserImagesResponseModel.Item, kotlin.Unit> deleteListener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> addImageListener) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.swipefwd.ui.profile.TempAdapter.MyItemHolder getMyHolder() {
        return null;
    }
    
    public final void setMyHolder(@org.jetbrains.annotations.Nullable()
    com.swipefwd.ui.profile.TempAdapter.MyItemHolder p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<java.lang.Boolean> getNewList() {
        return null;
    }
    
    public final void setNewList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Boolean> p0) {
    }
    
    public final void addAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.swipefwd.data.models.UserImagesResponseModel.Item> list) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.swipefwd.ui.profile.TempAdapter.MyItemHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    public final void removeImage(@org.jetbrains.annotations.NotNull()
    com.swipefwd.data.models.UserImagesResponseModel.Item model) {
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.swipefwd.ui.profile.TempAdapter.MyItemHolder holder, int position) {
    }
    
    public final void changePhotoInvalid(int position, boolean isOk) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/swipefwd/ui/profile/TempAdapter$MyItemHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/swipefwd/databinding/ItemSelectedPhotoBinding;", "(Lcom/swipefwd/ui/profile/TempAdapter;Lcom/swipefwd/databinding/ItemSelectedPhotoBinding;)V", "getBinding", "()Lcom/swipefwd/databinding/ItemSelectedPhotoBinding;", "bind", "", "position", "", "app_debug"})
    public final class MyItemHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.swipefwd.databinding.ItemSelectedPhotoBinding binding = null;
        
        public MyItemHolder(@org.jetbrains.annotations.NotNull()
        com.swipefwd.databinding.ItemSelectedPhotoBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.databinding.ItemSelectedPhotoBinding getBinding() {
            return null;
        }
        
        public final void bind(int position) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/swipefwd/ui/profile/TempAdapter$PayLoad;", "", "(Ljava/lang/String;I)V", "IS_FALSE_IMAGE", "app_debug"})
    static enum PayLoad {
        /*public static final*/ IS_FALSE_IMAGE /* = new IS_FALSE_IMAGE() */;
        
        PayLoad() {
        }
    }
}