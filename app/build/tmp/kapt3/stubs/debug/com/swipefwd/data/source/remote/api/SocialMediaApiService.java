package com.swipefwd.data.source.remote.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 !2\u00020\u0001:\u0001!J2\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0003\u0010\t\u001a\u00020\u0007H\'J2\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0003\u0010\t\u001a\u00020\u0007H\'JF\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u00072\b\b\u0003\u0010\u0011\u001a\u00020\u00072\b\b\u0003\u0010\u0012\u001a\u00020\u00072\b\b\u0003\u0010\u0013\u001a\u00020\u00072\b\b\u0003\u0010\u0014\u001a\u00020\u0007H\'J5\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00042\b\b\u0001\u0010\u0017\u001a\u00020\u00072\b\b\u0003\u0010\u0018\u001a\u00020\u00072\b\b\u0003\u0010\u0019\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ+\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00042\b\b\u0001\u0010\u0017\u001a\u00020\u00072\b\b\u0003\u0010\u0019\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJI\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00042\b\b\u0001\u0010\u0010\u001a\u00020\u00072\b\b\u0003\u0010\u0011\u001a\u00020\u00072\b\b\u0003\u0010\u0012\u001a\u00020\u00072\b\b\u0003\u0010\u0013\u001a\u00020\u00072\b\b\u0003\u0010\u0014\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 \u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lcom/swipefwd/data/source/remote/api/SocialMediaApiService;", "", "getInstagramMediaAsync", "Lkotlinx/coroutines/Deferred;", "Lretrofit2/Response;", "Lcom/swipefwd/data/models/InstagramMediaModel;", "media_id", "", "access_token", "fields", "getInstagramProfileAsync", "Lcom/swipefwd/data/models/InstagramProfileModel;", "user_id", "", "getInstagramTokenAsync", "Lcom/swipefwd/data/models/InstagramTokenModel;", "code", "grant_type", "client_id", "client_secret", "redirect_uri", "getLinkedInEmailAsync", "Lcom/swipefwd/data/models/LinkedInEmailModel;", "Authorization", "q", "projection", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLinkedInProfileAsync", "Lcom/swipefwd/data/models/LinkedInProfileModel;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLinkedInTokenAsync", "Lcom/swipefwd/data/models/LinkedInTokenModel;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface SocialMediaApiService {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.data.source.remote.api.SocialMediaApiService.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "oauth/v2/accessToken")
    public abstract java.lang.Object getLinkedInTokenAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "code")
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "grant_type")
    java.lang.String grant_type, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "client_id")
    java.lang.String client_id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "client_secret")
    java.lang.String client_secret, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "redirect_uri")
    java.lang.String redirect_uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.swipefwd.data.models.LinkedInTokenModel>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "v2/me")
    public abstract java.lang.Object getLinkedInProfileAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "projection")
    java.lang.String projection, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.swipefwd.data.models.LinkedInProfileModel>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "v2/emailAddress")
    public abstract java.lang.Object getLinkedInEmailAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String Authorization, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "q")
    java.lang.String q, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "projection")
    java.lang.String projection, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.swipefwd.data.models.LinkedInEmailModel>> continuation);
    
    /**
     * ##################### ##################### ##################### #####################
     * #####################
     * #####################
     * ####################################################################################
     */
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "oauth/access_token")
    @retrofit2.http.FormUrlEncoded()
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.InstagramTokenModel>> getInstagramTokenAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "code")
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "grant_type")
    java.lang.String grant_type, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "client_id")
    java.lang.String client_id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "client_secret")
    java.lang.String client_secret, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "redirect_uri")
    java.lang.String redirect_uri);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "{user-id}")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.InstagramProfileModel>> getInstagramProfileAsync(@retrofit2.http.Path(value = "user-id")
    long user_id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "access_token")
    java.lang.String access_token, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "fields")
    java.lang.String fields);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "{media-id}")
    public abstract kotlinx.coroutines.Deferred<retrofit2.Response<com.swipefwd.data.models.InstagramMediaModel>> getInstagramMediaAsync(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "media-id")
    java.lang.String media_id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "access_token")
    java.lang.String access_token, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "fields")
    java.lang.String fields);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/data/source/remote/api/SocialMediaApiService$Companion;", "", "()V", "serialVersionUID", "", "app_debug"})
    public static final class Companion {
        private static final long serialVersionUID = -91L;
        
        private Companion() {
            super();
        }
    }
}