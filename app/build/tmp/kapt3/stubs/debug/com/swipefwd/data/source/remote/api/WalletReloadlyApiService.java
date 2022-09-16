package com.swipefwd.data.source.remote.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'JZ\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\u000b2\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u000e2\b\b\u0001\u0010\u0010\u001a\u00020\u000e2\b\b\u0001\u0010\u0011\u001a\u00020\u000e2\b\b\u0001\u0010\u0012\u001a\u00020\u000eH\'J(\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\'\u00a8\u0006\u0015"}, d2 = {"Lcom/swipefwd/data/source/remote/api/WalletReloadlyApiService;", "", "getAccessTokenAsync", "Lkotlinx/coroutines/Deferred;", "Lretrofit2/Response;", "Lcom/swipefwd/data/models/walletModels/GetAccessTokenModel;", "jsonObject", "Lcom/google/gson/JsonObject;", "getOperatorByIsoAsync", "Lcom/swipefwd/data/models/walletModels/GetOperatorByIsoModel;", "Authorization", "", "isoCode", "includeBundles", "", "includeData", "includePin", "suggestedAmounts", "suggestedAmountsMap", "makeRechargeAsync", "Lcom/swipefwd/data/models/walletModels/MakeRechargeModel;", "app_debug"})
public abstract interface WalletReloadlyApiService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "oauth/token")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.walletModels.GetAccessTokenModel>> getAccessTokenAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "/operators/countries/{iso}")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.walletModels.GetOperatorByIsoModel>> getOperatorByIsoAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "iso")
    java.lang.String isoCode, @retrofit2.http.Query(value = "includeBundles")
    boolean includeBundles, @retrofit2.http.Query(value = "includeData")
    boolean includeData, @retrofit2.http.Query(value = "includePin")
    boolean includePin, @retrofit2.http.Query(value = "suggestedAmounts")
    boolean suggestedAmounts, @retrofit2.http.Query(value = "suggestedAmountsMap")
    boolean suggestedAmountsMap);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/topups")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.walletModels.MakeRechargeModel>> makeRechargeAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.google.gson.JsonObject jsonObject);
}