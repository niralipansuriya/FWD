// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SplashActivityBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageView imgSplash;

  @NonNull
  public final ConstraintLayout layoutMain;

  @NonNull
  public final LottieAnimationView lottieSplash;

  private SplashActivityBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageView imgSplash, @NonNull ConstraintLayout layoutMain,
      @NonNull LottieAnimationView lottieSplash) {
    this.rootView = rootView;
    this.imgSplash = imgSplash;
    this.layoutMain = layoutMain;
    this.lottieSplash = lottieSplash;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SplashActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SplashActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.splash_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SplashActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgSplash;
      AppCompatImageView imgSplash = ViewBindings.findChildViewById(rootView, id);
      if (imgSplash == null) {
        break missingId;
      }

      ConstraintLayout layoutMain = (ConstraintLayout) rootView;

      id = R.id.lottieSplash;
      LottieAnimationView lottieSplash = ViewBindings.findChildViewById(rootView, id);
      if (lottieSplash == null) {
        break missingId;
      }

      return new SplashActivityBinding((ConstraintLayout) rootView, imgSplash, layoutMain,
          lottieSplash);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
