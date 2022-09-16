package com.swipefwd.ui.tutorial.welcome;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\b\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\b\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001d"}, d2 = {"Lcom/swipefwd/ui/tutorial/welcome/WelcomeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/swipefwd/databinding/ActivityWelcomeBinding;", "getBinding", "()Lcom/swipefwd/databinding/ActivityWelcomeBinding;", "binding$delegate", "Lkotlin/Lazy;", "lstOfGreets", "", "Lcom/swipefwd/ui/tutorial/welcome/Greet;", "getLstOfGreets", "()Ljava/util/List;", "lstOfGreets$delegate", "viewPagerOnPageChangeListener", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "welcomePagerAdapter", "Lcom/swipefwd/ui/tutorial/welcome/WelcomePagerAdapter;", "getWelcomePagerAdapter", "()Lcom/swipefwd/ui/tutorial/welcome/WelcomePagerAdapter;", "welcomePagerAdapter$delegate", "initPager", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onTutorialCompleted", "Companion", "app_debug"})
public final class WelcomeActivity extends androidx.appcompat.app.AppCompatActivity {
    private final kotlin.Lazy lstOfGreets$delegate = null;
    private final kotlin.Lazy welcomePagerAdapter$delegate = null;
    private final kotlin.Lazy binding$delegate = null;
    private androidx.viewpager.widget.ViewPager.OnPageChangeListener viewPagerOnPageChangeListener;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.tutorial.welcome.WelcomeActivity.Companion Companion = null;
    
    public WelcomeActivity() {
        super();
    }
    
    private final java.util.List<com.swipefwd.ui.tutorial.welcome.Greet> getLstOfGreets() {
        return null;
    }
    
    private final com.swipefwd.ui.tutorial.welcome.WelcomePagerAdapter getWelcomePagerAdapter() {
        return null;
    }
    
    private final com.swipefwd.databinding.ActivityWelcomeBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void onTutorialCompleted() {
    }
    
    private final void initPager() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/tutorial/welcome/WelcomeActivity$Companion;", "", "()V", "start", "", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void start(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
    }
}