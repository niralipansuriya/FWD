package com.swipefwd.xmpp;

import java.lang.System;

/**
 * XMPP Service library logger.
 * You can provide your own logger delegate implementation, to be able to log in a different way.
 * By default the log level is set to DEBUG when the build type is debug, and OFF in release.
 * The default logger implementation logs in Android's LogCat.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00072\u00020\u0001:\u0004\u0007\b\t\nB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/swipefwd/xmpp/Logger;", "", "()V", "mDelegate", "Lcom/swipefwd/xmpp/Logger$LoggerDelegate;", "mLogLevel", "Lcom/swipefwd/xmpp/Logger$LogLevel;", "Companion", "LogLevel", "LoggerDelegate", "SingletonHolder", "app_debug"})
public final class Logger {
    private com.swipefwd.xmpp.Logger.LogLevel mLogLevel;
    private com.swipefwd.xmpp.Logger.LoggerDelegate mDelegate;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.xmpp.Logger.Companion Companion = null;
    
    private Logger() {
        super();
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void error(@org.jetbrains.annotations.Nullable()
    java.lang.String tag, @org.jetbrains.annotations.Nullable()
    java.lang.String message, @org.jetbrains.annotations.Nullable()
    java.lang.Throwable exception) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void info(@org.jetbrains.annotations.Nullable()
    java.lang.String tag, @org.jetbrains.annotations.Nullable()
    java.lang.String message) {
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void debug(@org.jetbrains.annotations.Nullable()
    java.lang.String tag, @org.jetbrains.annotations.Nullable()
    java.lang.String message) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/xmpp/Logger$LogLevel;", "", "(Ljava/lang/String;I)V", "DEBUG", "INFO", "ERROR", "OFF", "app_debug"})
    public static enum LogLevel {
        /*public static final*/ DEBUG /* = new DEBUG() */,
        /*public static final*/ INFO /* = new INFO() */,
        /*public static final*/ ERROR /* = new ERROR() */,
        /*public static final*/ OFF /* = new OFF() */;
        
        LogLevel() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J\u001c\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J&\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\u001c\u0010\n\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&\u00a8\u0006\u000b"}, d2 = {"Lcom/swipefwd/xmpp/Logger$LoggerDelegate;", "", "debug", "", "tag", "", "message", "error", "exception", "", "info", "app_debug"})
    public static abstract interface LoggerDelegate {
        
        public abstract void error(@org.jetbrains.annotations.Nullable()
        java.lang.String tag, @org.jetbrains.annotations.Nullable()
        java.lang.String message);
        
        public abstract void error(@org.jetbrains.annotations.Nullable()
        java.lang.String tag, @org.jetbrains.annotations.Nullable()
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.Throwable exception);
        
        public abstract void debug(@org.jetbrains.annotations.Nullable()
        java.lang.String tag, @org.jetbrains.annotations.Nullable()
        java.lang.String message);
        
        public abstract void info(@org.jetbrains.annotations.Nullable()
        java.lang.String tag, @org.jetbrains.annotations.Nullable()
        java.lang.String message);
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/xmpp/Logger$SingletonHolder;", "", "()V", "instance", "Lcom/swipefwd/xmpp/Logger;", "getInstance", "()Lcom/swipefwd/xmpp/Logger;", "app_debug"})
    static final class SingletonHolder {
        @org.jetbrains.annotations.NotNull()
        public static final com.swipefwd.xmpp.Logger.SingletonHolder INSTANCE = null;
        @org.jetbrains.annotations.NotNull()
        private static final com.swipefwd.xmpp.Logger instance = null;
        
        private SingletonHolder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.swipefwd.xmpp.Logger getInstance() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J\u001a\u0010\b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J&\u0010\b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\u001c\u0010\u000b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/swipefwd/xmpp/Logger$Companion;", "", "()V", "debug", "", "tag", "", "message", "error", "exception", "", "info", "resetLoggerDelegate", "setLogLevel", "level", "Lcom/swipefwd/xmpp/Logger$LogLevel;", "setLoggerDelegate", "delegate", "Lcom/swipefwd/xmpp/Logger$LoggerDelegate;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void resetLoggerDelegate() {
        }
        
        public final void setLoggerDelegate(@org.jetbrains.annotations.Nullable()
        com.swipefwd.xmpp.Logger.LoggerDelegate delegate) {
        }
        
        public final void setLogLevel(@org.jetbrains.annotations.NotNull()
        com.swipefwd.xmpp.Logger.LogLevel level) {
        }
        
        public final void error(@org.jetbrains.annotations.Nullable()
        java.lang.String tag, @org.jetbrains.annotations.Nullable()
        java.lang.String message) {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void error(@org.jetbrains.annotations.Nullable()
        java.lang.String tag, @org.jetbrains.annotations.Nullable()
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.Throwable exception) {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void info(@org.jetbrains.annotations.Nullable()
        java.lang.String tag, @org.jetbrains.annotations.Nullable()
        java.lang.String message) {
        }
        
        @kotlin.jvm.JvmStatic()
        public final void debug(@org.jetbrains.annotations.Nullable()
        java.lang.String tag, @org.jetbrains.annotations.Nullable()
        java.lang.String message) {
        }
    }
}