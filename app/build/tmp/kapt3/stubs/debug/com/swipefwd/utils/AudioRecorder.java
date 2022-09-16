package com.swipefwd.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0006\u0010\u000b\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/swipefwd/utils/AudioRecorder;", "", "()V", "mediaRecorder", "Landroid/media/MediaRecorder;", "destroyMediaRecorder", "", "initMediaRecorder", "start", "filePath", "", "stop", "app_debug"})
public final class AudioRecorder {
    private android.media.MediaRecorder mediaRecorder;
    
    public AudioRecorder() {
        super();
    }
    
    private final void initMediaRecorder() {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    public final void start(@org.jetbrains.annotations.Nullable()
    java.lang.String filePath) throws java.io.IOException {
    }
    
    public final void stop() {
    }
    
    private final void destroyMediaRecorder() {
    }
}