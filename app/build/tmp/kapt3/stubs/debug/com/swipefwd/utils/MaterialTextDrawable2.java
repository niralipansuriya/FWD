package com.swipefwd.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001a2\u00020\u0001:\u0003\u0019\u001a\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u0012H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/swipefwd/utils/MaterialTextDrawable2;", "", "builder", "Lcom/swipefwd/utils/MaterialTextDrawable2$Builder;", "(Lcom/swipefwd/utils/MaterialTextDrawable2$Builder;)V", "context", "Landroid/content/Context;", "drawableShape", "Lcom/swipefwd/utils/MaterialTextDrawable2$MaterialShape;", "firstColor", "", "isModify", "", "secondColor", "size", "text", "", "calculateTextSize", "", "calculateTextSizeAccordingToSize", "getFirstChar", "getTextDrawable", "Landroid/graphics/drawable/BitmapDrawable;", "textPainter", "Landroid/text/TextPaint;", "Builder", "Companion", "MaterialShape", "app_debug"})
public final class MaterialTextDrawable2 {
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.utils.MaterialTextDrawable2.Companion Companion = null;
    private static boolean displayBackgroundWhite = true;
    private android.content.Context context;
    private int size;
    private com.swipefwd.utils.MaterialTextDrawable2.MaterialShape drawableShape;
    private java.lang.String text;
    private int firstColor;
    private int secondColor;
    private boolean isModify;
    
    public MaterialTextDrawable2(@org.jetbrains.annotations.NotNull()
    com.swipefwd.utils.MaterialTextDrawable2.Builder builder) {
        super();
    }
    
    private final android.graphics.drawable.BitmapDrawable getTextDrawable() {
        return null;
    }
    
    private final float calculateTextSize(int size) {
        return 0.0F;
    }
    
    private final float calculateTextSizeAccordingToSize(int size) {
        return 0.0F;
    }
    
    private final java.lang.String getFirstChar(java.lang.String text) {
        return null;
    }
    
    private final android.text.TextPaint textPainter(float size) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/swipefwd/utils/MaterialTextDrawable2$MaterialShape;", "", "(Ljava/lang/String;I)V", "CIRCLE", "RECTANGLE", "app_debug"})
    public static enum MaterialShape {
        /*public static final*/ CIRCLE /* = new CIRCLE() */,
        /*public static final*/ RECTANGLE /* = new RECTANGLE() */;
        
        MaterialShape() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u0016J\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.J\u0016\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u00101\u001a\u00020\u0016J\u0016\u00102\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010J\u000e\u00103\u001a\u00020\u00002\u0006\u00103\u001a\u00020\nJ\u000e\u0010!\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"J\u000e\u00104\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0010J\u000e\u00105\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0080.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0010X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R\u001a\u0010\u001e\u001a\u00020\u0010X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R\u001a\u0010!\u001a\u00020\"X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u00a8\u00066"}, d2 = {"Lcom/swipefwd/utils/MaterialTextDrawable2$Builder;", "", "()V", "context", "Landroid/content/Context;", "getContext$app_debug", "()Landroid/content/Context;", "setContext$app_debug", "(Landroid/content/Context;)V", "drawableShape", "Lcom/swipefwd/utils/MaterialTextDrawable2$MaterialShape;", "getDrawableShape$app_debug", "()Lcom/swipefwd/utils/MaterialTextDrawable2$MaterialShape;", "setDrawableShape$app_debug", "(Lcom/swipefwd/utils/MaterialTextDrawable2$MaterialShape;)V", "firstColor", "", "getFirstColor$app_debug", "()I", "setFirstColor$app_debug", "(I)V", "isModify", "", "isModify$app_debug", "()Z", "setModify$app_debug", "(Z)V", "secondColor", "getSecondColor$app_debug", "setSecondColor$app_debug", "size", "getSize$app_debug", "setSize$app_debug", "text", "", "getText$app_debug", "()Ljava/lang/String;", "setText$app_debug", "(Ljava/lang/String;)V", "backgroundHandle", "displayBackgroundWhite", "get", "Landroid/graphics/drawable/BitmapDrawable;", "into", "", "view", "Landroid/widget/ImageView;", "scale", "Landroid/widget/ImageView$ScaleType;", "modify", "shaderColor", "shape", "textSize", "with", "app_debug"})
    public static final class Builder {
        public android.content.Context context;
        private int size = 150;
        @org.jetbrains.annotations.NotNull()
        private com.swipefwd.utils.MaterialTextDrawable2.MaterialShape drawableShape = com.swipefwd.utils.MaterialTextDrawable2.MaterialShape.CIRCLE;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String text = "";
        private int firstColor = com.swipefwd.R.color.blue_gradient_2;
        private int secondColor = com.swipefwd.R.color.blue_gradient_3;
        private boolean isModify = false;
        
        public Builder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Context getContext$app_debug() {
            return null;
        }
        
        public final void setContext$app_debug(@org.jetbrains.annotations.NotNull()
        android.content.Context p0) {
        }
        
        public final int getSize$app_debug() {
            return 0;
        }
        
        public final void setSize$app_debug(int p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.MaterialTextDrawable2.MaterialShape getDrawableShape$app_debug() {
            return null;
        }
        
        public final void setDrawableShape$app_debug(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.MaterialTextDrawable2.MaterialShape p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getText$app_debug() {
            return null;
        }
        
        public final void setText$app_debug(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        public final int getFirstColor$app_debug() {
            return 0;
        }
        
        public final void setFirstColor$app_debug(int p0) {
        }
        
        public final int getSecondColor$app_debug() {
            return 0;
        }
        
        public final void setSecondColor$app_debug(int p0) {
        }
        
        public final boolean isModify$app_debug() {
            return false;
        }
        
        public final void setModify$app_debug(boolean p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.MaterialTextDrawable2.Builder with(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.MaterialTextDrawable2.Builder textSize(int size) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.MaterialTextDrawable2.Builder shaderColor(int firstColor, int secondColor) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.MaterialTextDrawable2.Builder shape(@org.jetbrains.annotations.NotNull()
        com.swipefwd.utils.MaterialTextDrawable2.MaterialShape shape) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.MaterialTextDrawable2.Builder text(@org.jetbrains.annotations.NotNull()
        java.lang.String text) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.MaterialTextDrawable2.Builder isModify(boolean modify) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.MaterialTextDrawable2.Builder backgroundHandle(boolean displayBackgroundWhite) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.graphics.drawable.BitmapDrawable get() {
            return null;
        }
        
        public final void into(@org.jetbrains.annotations.NotNull()
        android.widget.ImageView view) {
        }
        
        public final void into(@org.jetbrains.annotations.NotNull()
        android.widget.ImageView view, @org.jetbrains.annotations.NotNull()
        android.widget.ImageView.ScaleType scale) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000e"}, d2 = {"Lcom/swipefwd/utils/MaterialTextDrawable2$Companion;", "", "()V", "displayBackgroundWhite", "", "getDisplayBackgroundWhite", "()Z", "setDisplayBackgroundWhite", "(Z)V", "isOnMainThread", "with", "Lcom/swipefwd/utils/MaterialTextDrawable2$Builder;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.utils.MaterialTextDrawable2.Builder with(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private final boolean isOnMainThread() {
            return false;
        }
        
        public final boolean getDisplayBackgroundWhite() {
            return false;
        }
        
        public final void setDisplayBackgroundWhite(boolean p0) {
        }
    }
}