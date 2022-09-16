package com.swipefwd.modules;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nJ\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\nJ\u0010\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\r\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\nR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0013"}, d2 = {"Lcom/swipefwd/modules/NetworkModule;", "", "()V", "apiService", "Lcom/swipefwd/data/source/remote/api/services/ApiService;", "getApiService", "()Lcom/swipefwd/data/source/remote/api/services/ApiService;", "getClient", "Lretrofit2/Retrofit;", "baseUrl", "", "getSocialMediaService", "Lcom/swipefwd/data/source/remote/api/SocialMediaApiService;", "url", "getSwipeProfileService", "getWalletService", "Lcom/swipefwd/data/source/remote/api/WalletReloadlyApiService;", "newApi", "TokenAuthenticator", "app_debug"})
public final class NetworkModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.modules.NetworkModule INSTANCE = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.swipefwd.data.source.remote.api.services.ApiService apiService = null;
    
    private NetworkModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit getClient(@org.jetbrains.annotations.NotNull()
    java.lang.String baseUrl) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.source.remote.api.services.ApiService getApiService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.source.remote.api.SocialMediaApiService getSocialMediaService(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.source.remote.api.services.ApiService newApi(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.source.remote.api.services.ApiService getSwipeProfileService(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.swipefwd.data.source.remote.api.WalletReloadlyApiService getWalletService(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\t"}, d2 = {"Lcom/swipefwd/modules/NetworkModule$TokenAuthenticator;", "Lokhttp3/Authenticator;", "()V", "authenticate", "Lokhttp3/Request;", "route", "Lokhttp3/Route;", "response", "Lokhttp3/Response;", "app_debug"})
    public static final class TokenAuthenticator implements okhttp3.Authenticator {
        
        public TokenAuthenticator() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        public okhttp3.Request authenticate(@org.jetbrains.annotations.Nullable()
        okhttp3.Route route, @org.jetbrains.annotations.NotNull()
        okhttp3.Response response) {
            return null;
        }
    }
}