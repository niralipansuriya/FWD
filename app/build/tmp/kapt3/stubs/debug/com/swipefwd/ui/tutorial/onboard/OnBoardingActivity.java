package com.swipefwd.ui.tutorial.onboard;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 52\u00020\u0001:\u00015B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010/\u001a\u000200H\u0002J\u0012\u00101\u001a\u0002002\b\u00102\u001a\u0004\u0018\u000103H\u0014J\b\u00104\u001a\u000200H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00008BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u000e\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\"\u0010\u000e\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\u00020$8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\'\u0010\u000e\u001a\u0004\b%\u0010&R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b,\u0010\u000e\u001a\u0004\b*\u0010+R\u000e\u0010-\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00066"}, d2 = {"Lcom/swipefwd/ui/tutorial/onboard/OnBoardingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "accountType", "", "getAccountType", "()Ljava/lang/String;", "setAccountType", "(Ljava/lang/String;)V", "binding", "Lcom/swipefwd/databinding/ActivityOnboardingBinding;", "getBinding", "()Lcom/swipefwd/databinding/ActivityOnboardingBinding;", "binding$delegate", "Lkotlin/Lazy;", "mActivity", "getMActivity", "()Lcom/swipefwd/ui/tutorial/onboard/OnBoardingActivity;", "mActivity$delegate", "onBoardingWarehouse", "", "Lcom/swipefwd/ui/tutorial/onboard/OnBoardingItem;", "getOnBoardingWarehouse", "()Ljava/util/List;", "onBoardingWarehouse$delegate", "onboardPagerAdapter", "Lcom/swipefwd/ui/tutorial/onboard/OnBoardPagerAdapter;", "getOnboardPagerAdapter", "()Lcom/swipefwd/ui/tutorial/onboard/OnBoardPagerAdapter;", "onboardPagerAdapter$delegate", "profileViewModel", "Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "getProfileViewModel", "()Lcom/swipefwd/ui/updateuserprofile/UserProfileViewModel;", "profileViewModel$delegate", "swipeViewModel", "Lcom/swipefwd/ui/swiping/dater_both/SwipeProfileViewModel;", "getSwipeViewModel", "()Lcom/swipefwd/ui/swiping/dater_both/SwipeProfileViewModel;", "swipeViewModel$delegate", "viewModel", "Lcom/swipefwd/ui/tutorial/onboard/OnBoardingViewModel;", "getViewModel", "()Lcom/swipefwd/ui/tutorial/onboard/OnBoardingViewModel;", "viewModel$delegate", "viewPagerOnPageChangeListener", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "initPager", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onTutorialCompleted", "Companion", "app_debug"})
public final class OnBoardingActivity extends androidx.appcompat.app.AppCompatActivity {
    private final kotlin.Lazy binding$delegate = null;
    private final kotlin.Lazy mActivity$delegate = null;
    private final kotlin.Lazy profileViewModel$delegate = null;
    private final kotlin.Lazy swipeViewModel$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy onBoardingWarehouse$delegate = null;
    private final kotlin.Lazy onboardPagerAdapter$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String accountType = "";
    private androidx.viewpager.widget.ViewPager.OnPageChangeListener viewPagerOnPageChangeListener;
    @org.jetbrains.annotations.NotNull()
    public static final com.swipefwd.ui.tutorial.onboard.OnBoardingActivity.Companion Companion = null;
    
    public OnBoardingActivity() {
        super();
    }
    
    private final com.swipefwd.databinding.ActivityOnboardingBinding getBinding() {
        return null;
    }
    
    private final com.swipefwd.ui.tutorial.onboard.OnBoardingActivity getMActivity() {
        return null;
    }
    
    private final com.swipefwd.ui.updateuserprofile.UserProfileViewModel getProfileViewModel() {
        return null;
    }
    
    private final com.swipefwd.ui.swiping.dater_both.SwipeProfileViewModel getSwipeViewModel() {
        return null;
    }
    
    private final com.swipefwd.ui.tutorial.onboard.OnBoardingViewModel getViewModel() {
        return null;
    }
    
    private final java.util.List<com.swipefwd.ui.tutorial.onboard.OnBoardingItem> getOnBoardingWarehouse() {
        return null;
    }
    
    private final com.swipefwd.ui.tutorial.onboard.OnBoardPagerAdapter getOnboardPagerAdapter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAccountType() {
        return null;
    }
    
    public final void setAccountType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void onTutorialCompleted() {
    }
    
    private final void initPager() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/swipefwd/ui/tutorial/onboard/OnBoardingActivity$Companion;", "", "()V", "start", "", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void start(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
        }
    }
}