// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemOnboardPagerBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LottieAnimationView lottieAnimationView;

  @NonNull
  public final AppCompatTextView txtDesc;

  @NonNull
  public final AppCompatTextView txtTitle;

  private ItemOnboardPagerBinding(@NonNull ConstraintLayout rootView,
      @NonNull LottieAnimationView lottieAnimationView, @NonNull AppCompatTextView txtDesc,
      @NonNull AppCompatTextView txtTitle) {
    this.rootView = rootView;
    this.lottieAnimationView = lottieAnimationView;
    this.txtDesc = txtDesc;
    this.txtTitle = txtTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemOnboardPagerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemOnboardPagerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_onboard_pager, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemOnboardPagerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.lottieAnimationView;
      LottieAnimationView lottieAnimationView = ViewBindings.findChildViewById(rootView, id);
      if (lottieAnimationView == null) {
        break missingId;
      }

      id = R.id.txtDesc;
      AppCompatTextView txtDesc = ViewBindings.findChildViewById(rootView, id);
      if (txtDesc == null) {
        break missingId;
      }

      id = R.id.txtTitle;
      AppCompatTextView txtTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtTitle == null) {
        break missingId;
      }

      return new ItemOnboardPagerBinding((ConstraintLayout) rootView, lottieAnimationView, txtDesc,
          txtTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
