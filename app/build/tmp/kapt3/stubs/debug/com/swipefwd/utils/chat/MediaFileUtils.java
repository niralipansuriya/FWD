package com.swipefwd.utils.chat;

import java.lang.System;

/**
 * Created by gongguopei87@gmail.com on 2015/8/5.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002J \u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bJ\u0016\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\bJ\"\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0010J\u0018\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0010R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005\u00a8\u0006\u001d"}, d2 = {"Lcom/swipefwd/utils/chat/MediaFileUtils;", "", "()V", "isExternalStorageReadable", "", "()Z", "isExternalStorageWritable", "calculateInSampleSize", "", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "decodeBitmapFromPath", "Landroid/graphics/Bitmap;", "filePath", "", "dpToPx", "context", "Landroid/content/Context;", "dp", "getAlbumStorageDir", "Ljava/io/File;", "type", "albumName", "getRealPathFromURI", "contentUri", "Landroid/net/Uri;", "getVoiceFilePath", "app_debug"})
public final class MediaFileUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.chat.MediaFileUtils INSTANCE = null;
    
    private MediaFileUtils() {
        super();
    }
    
    public final boolean isExternalStorageWritable() {
        return false;
    }
    
    public final boolean isExternalStorageReadable() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getAlbumStorageDir(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.Nullable()
    java.lang.String albumName) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getVoiceFilePath(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String albumName) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRealPathFromURI(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.net.Uri contentUri) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap decodeBitmapFromPath(@org.jetbrains.annotations.Nullable()
    java.lang.String filePath, int reqWidth, int reqHeight) {
        return null;
    }
    
    private final int calculateInSampleSize(android.graphics.BitmapFactory.Options options, int reqWidth, int reqHeight) {
        return 0;
    }
    
    public final int dpToPx(@org.jetbrains.annotations.NotNull()
    android.content.Context context, int dp) {
        return 0;
    }
}