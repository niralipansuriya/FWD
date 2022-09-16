package com.swipefwd.ui.home.tribe;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J%\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`%H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\b\u0010\'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020(H\u0002J\b\u0010*\u001a\u00020(H\u0016J\u0012\u0010+\u001a\u00020(2\b\u0010,\u001a\u0004\u0018\u00010-H\u0014J\b\u0010.\u001a\u00020(H\u0014J\b\u0010/\u001a\u00020(H\u0002J\b\u00100\u001a\u00020(H\u0002J\b\u00101\u001a\u00020(H\u0002J\b\u00102\u001a\u00020(H\u0002J \u00103\u001a\u00020(2\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`%H\u0002J\b\u00104\u001a\u00020(H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001f\u0010\u0012\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010 \u001a\u00020!X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00065"}, d2 = {"Lcom/swipefwd/ui/home/tribe/ConnectorContactActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/swipefwd/databinding/ActivityConnectorContactBinding;", "contactList", "Ljava/util/ArrayList;", "Lcom/swipefwd/data/models/UserContactDetails;", "countryCode", "", "dialogs", "Ljava/util/Vector;", "Landroid/app/Dialog;", "homeViewModel", "Lcom/swipefwd/ui/home/tribe/HomeFragmentViewModel;", "getHomeViewModel", "()Lcom/swipefwd/ui/home/tribe/HomeFragmentViewModel;", "homeViewModel$delegate", "Lkotlin/Lazy;", "isSMS", "", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/home/tribe/ConnectorContactActivity;", "mActivity$delegate", "mAdapter", "Lcom/swipefwd/ui/home/tribe/ConnectorContactListAdapter;", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "selectedUser", "Lcom/swipefwd/data/models/DaterConnectionResponseModel$User;", "user2", "userContactDetailsModel", "getContacts", "Lkotlin/collections/ArrayList;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "init", "", "initObservers", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "openChatDialog", "openNoMessageDialog", "openTribeConnectionDialog", "requestRuntimePermission", "sendContactApi", "suggestionProfileApi", "app_debug"})
public final class ConnectorContactActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivityConnectorContactBinding binding;
    private final kotlin.Lazy mActivity$delegate = null;
    private com.swipefwd.data.models.DaterConnectionResponseModel.User selectedUser;
    private com.swipefwd.data.models.UserContactDetails user2;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private java.lang.String countryCode = "";
    private java.util.ArrayList<com.swipefwd.data.models.UserContactDetails> contactList;
    private com.swipefwd.ui.home.tribe.ConnectorContactListAdapter mAdapter;
    private com.swipefwd.data.models.UserContactDetails userContactDetailsModel;
    private final kotlin.Lazy homeViewModel$delegate = null;
    private boolean isSMS = false;
    private final java.util.Vector<android.app.Dialog> dialogs = null;
    
    public ConnectorContactActivity() {
        super();
    }
    
    private final com.swipefwd.ui.home.tribe.ConnectorContactActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    private final com.swipefwd.ui.home.tribe.HomeFragmentViewModel getHomeViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void sendContactApi(java.util.ArrayList<java.lang.String> contactList) {
    }
    
    private final void requestRuntimePermission() {
    }
    
    private final java.lang.Object getContacts(kotlin.coroutines.Continuation<? super java.util.ArrayList<com.swipefwd.data.models.UserContactDetails>> continuation) {
        return null;
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final void openChatDialog() {
    }
    
    private final void openNoMessageDialog() {
    }
    
    private final void openTribeConnectionDialog() {
    }
    
    private final void suggestionProfileApi() {
    }
    
    private final void initObservers() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
}