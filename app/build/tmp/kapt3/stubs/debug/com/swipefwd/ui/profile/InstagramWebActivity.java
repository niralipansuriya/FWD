package com.swipefwd.ui.profile;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0003J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/swipefwd/ui/profile/InstagramWebActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/swipefwd/databinding/ActivityInstagramWebBinding;", "progressBarHandler", "Lcom/swipefwd/utils/ProgressBarHandler;", "getProgressBarHandler", "()Lcom/swipefwd/utils/ProgressBarHandler;", "progressBarHandler$delegate", "Lkotlin/Lazy;", "url", "", "init", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class InstagramWebActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.swipefwd.databinding.ActivityInstagramWebBinding binding;
    private final kotlin.Lazy progressBarHandler$delegate = null;
    private final java.lang.String url = "https://api.instagram.com/oauth/authorize/?client_id=668596977493284&client_secret=7147916ea7338cd0f24392bd8446576f&redirect_uri=https://www.swipefwd.com/&scope=user_profile&response_type=code";
    
    public InstagramWebActivity() {
        super();
    }
    
    private final com.swipefwd.utils.ProgressBarHandler getProgressBarHandler() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"SetJavaScriptEnabled"})
    private final void init() {
    }
}