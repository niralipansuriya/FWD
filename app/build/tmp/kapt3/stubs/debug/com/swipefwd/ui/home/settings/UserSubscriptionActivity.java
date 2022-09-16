package com.swipefwd.ui.home.settings;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010B\u001a\u00020CH\u0002J\b\u0010D\u001a\u00020CH\u0002J\b\u0010E\u001a\u00020CH\u0002J\u0018\u0010F\u001a\u00020C2\u000e\u0010G\u001a\n\u0012\u0004\u0012\u00020H\u0018\u00010\u0018H\u0002J\b\u0010I\u001a\u00020CH\u0002J\b\u0010J\u001a\u00020CH\u0002J\b\u0010K\u001a\u00020CH\u0002J\b\u0010L\u001a\u00020CH\u0016J\u0012\u0010M\u001a\u00020C2\b\u0010N\u001a\u0004\u0018\u00010OH\u0014J\b\u0010P\u001a\u00020CH\u0014J \u0010Q\u001a\u00020C2\u0006\u0010R\u001a\u00020S2\u000e\u0010T\u001a\n\u0012\u0004\u0012\u00020H\u0018\u00010UH\u0016J\b\u0010V\u001a\u00020CH\u0002J\b\u0010W\u001a\u00020CH\u0002J\b\u0010X\u001a\u00020CH\u0002J\b\u0010Y\u001a\u00020CH\u0002J\b\u0010Z\u001a\u00020CH\u0002J\u0010\u0010[\u001a\u00020C2\u0006\u0010\\\u001a\u00020\u0016H\u0002J\u0018\u0010]\u001a\u00020C2\u000e\u0010^\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0\u0018H\u0002J\b\u0010_\u001a\u00020CH\u0002J\b\u0010`\u001a\u00020CH\u0002J\u0014\u0010a\u001a\u00020C*\u00020A2\u0006\u0010b\u001a\u00020cH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0011j\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f`\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001e\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b!\u0010\t\u001a\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\'\u001a\u00020(8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b+\u0010\t\u001a\u0004\b)\u0010*R\u000e\u0010,\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010.\u001a\u00020/8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b2\u0010\t\u001a\u0004\b0\u00101R\u000e\u00103\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u00104\u001a\u0002058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b8\u0010\t\u001a\u0004\b6\u00107R\u001b\u00109\u001a\u00020:8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b=\u0010\t\u001a\u0004\b;\u0010<R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006d"}, d2 = {"Lcom/swipefwd/ui/home/settings/UserSubscriptionActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/android/billingclient/api/PurchasesUpdatedListener;", "()V", "adapter", "Lcom/swipefwd/ui/home/message/SubscriptionPlansPagerAdapter;", "getAdapter", "()Lcom/swipefwd/ui/home/message/SubscriptionPlansPagerAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "billingClient", "Lcom/android/billingclient/api/BillingClient;", "binding", "Lcom/swipefwd/databinding/ActivityUserSubscriptionBinding;", "boosterModel", "Lcom/android/billingclient/api/SkuDetails;", "boostersIds", "Ljava/util/ArrayList;", "", "boostersList", "Lkotlin/collections/ArrayList;", "currentPosition", "", "dataList", "", "exDate", "fName", "image", "isBooster", "", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/home/settings/UserSubscriptionActivity;", "mActivity$delegate", "mAllSubList", "Lcom/swipefwd/data/models/AllSubPlansModel;", "mDetailedList", "Lcom/swipefwd/data/models/SettingSubscriptionModel$Plan;", "orderId", "outFormat", "Ljava/text/SimpleDateFormat;", "getOutFormat", "()Ljava/text/SimpleDateFormat;", "outFormat$delegate", "pagerPosition", "planModel", "plansAdapter", "Lcom/swipefwd/ui/home/message/SubscriptionPlansListAdapter;", "getPlansAdapter", "()Lcom/swipefwd/ui/home/message/SubscriptionPlansListAdapter;", "plansAdapter$delegate", "productId", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "settingsViewModel", "Lcom/swipefwd/ui/home/settings/SettingsViewModel;", "getSettingsViewModel", "()Lcom/swipefwd/ui/home/settings/SettingsViewModel;", "settingsViewModel$delegate", "subIds", "subscriptionDuration", "subscriptionsBinding", "Lcom/swipefwd/databinding/DialogSubscriptionPlansBinding;", "boosterPurchase", "", "getProductSkus", "getUserPlansList", "handlePurchases", "purchasesList", "Lcom/android/billingclient/api/Purchase;", "hasPurchaseOnGoogle", "init", "initObservers", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPurchasesUpdated", "p0", "Lcom/android/billingclient/api/BillingResult;", "p1", "", "openCancelConfirmedDialog", "openCancelSubscriptionDialog", "openDiamondUpgradeDialog", "openSubscriptionDialog", "planPurchase", "setListData", "position", "setPagerData", "list", "setProductSku", "setUpBillingClient", "setSubscriptionData", "dialog", "Landroid/app/Dialog;", "app_debug"})
public final class UserSubscriptionActivity extends androidx.appcompat.app.AppCompatActivity implements com.android.billingclient.api.PurchasesUpdatedListener {
    private com.swipefwd.databinding.ActivityUserSubscriptionBinding binding;
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy settingsViewModel$delegate = null;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private java.util.ArrayList<com.swipefwd.data.models.SettingSubscriptionModel.Plan> mDetailedList;
    private int currentPosition = 0;
    private java.lang.String image = "";
    private java.lang.String fName = "";
    private java.lang.String orderId = "";
    private java.lang.String productId = "";
    private com.android.billingclient.api.BillingClient billingClient;
    private java.util.ArrayList<com.swipefwd.data.models.AllSubPlansModel> mAllSubList;
    private final java.util.ArrayList<java.lang.String> subIds = null;
    private final java.util.ArrayList<java.lang.String> boostersIds = null;
    private com.swipefwd.databinding.DialogSubscriptionPlansBinding subscriptionsBinding;
    private java.util.List<? extends com.android.billingclient.api.SkuDetails> dataList;
    private java.util.ArrayList<com.android.billingclient.api.SkuDetails> boostersList;
    private com.swipefwd.data.models.SettingSubscriptionModel.Plan planModel;
    private final kotlin.Lazy adapter$delegate = null;
    private final kotlin.Lazy plansAdapter$delegate = null;
    private java.lang.String exDate;
    private boolean isBooster = false;
    private com.android.billingclient.api.SkuDetails boosterModel;
    private int pagerPosition = 0;
    private final kotlin.Lazy outFormat$delegate = null;
    private java.lang.String subscriptionDuration = "P1M";
    
    public UserSubscriptionActivity() {
        super();
    }
    
    private final com.swipefwd.ui.home.settings.UserSubscriptionActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.ui.home.settings.SettingsViewModel getSettingsViewModel() {
        return null;
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    private final com.swipefwd.ui.home.message.SubscriptionPlansPagerAdapter getAdapter() {
        return null;
    }
    
    private final com.swipefwd.ui.home.message.SubscriptionPlansListAdapter getPlansAdapter() {
        return null;
    }
    
    private final java.text.SimpleDateFormat getOutFormat() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void setUpBillingClient() {
    }
    
    private final void getProductSkus() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final void setPagerData(java.util.List<com.swipefwd.data.models.SettingSubscriptionModel.Plan> list) {
    }
    
    private final void setListData(int position) {
    }
    
    private final void openCancelSubscriptionDialog() {
    }
    
    private final void openCancelConfirmedDialog() {
    }
    
    private final void getUserPlansList() {
    }
    
    private final void initObservers() {
    }
    
    private final void openSubscriptionDialog() {
    }
    
    private final void setSubscriptionData(com.swipefwd.databinding.DialogSubscriptionPlansBinding $this$setSubscriptionData, android.app.Dialog dialog) {
    }
    
    private final void openDiamondUpgradeDialog() {
    }
    
    private final void planPurchase() {
    }
    
    private final void boosterPurchase() {
    }
    
    @java.lang.Override()
    public void onPurchasesUpdated(@org.jetbrains.annotations.NotNull()
    com.android.billingclient.api.BillingResult p0, @org.jetbrains.annotations.Nullable()
    java.util.List<com.android.billingclient.api.Purchase> p1) {
    }
    
    private final void hasPurchaseOnGoogle() {
    }
    
    private final void setProductSku() {
    }
    
    private final void handlePurchases(java.util.List<? extends com.android.billingclient.api.Purchase> purchasesList) {
    }
}