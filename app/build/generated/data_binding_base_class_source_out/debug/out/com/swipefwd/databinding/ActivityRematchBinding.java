// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.button.MaterialButton;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import me.relex.circleindicator.CircleIndicator;

public final class ActivityRematchBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CircleIndicator layoutIndicator;

  @NonNull
  public final ViewPager planPager;

  @NonNull
  public final RecyclerView rvPlansList;

  @NonNull
  public final MaterialButton txtContinue;

  @NonNull
  public final AppCompatTextView txtPlanMessage;

  private ActivityRematchBinding(@NonNull ConstraintLayout rootView,
      @NonNull CircleIndicator layoutIndicator, @NonNull ViewPager planPager,
      @NonNull RecyclerView rvPlansList, @NonNull MaterialButton txtContinue,
      @NonNull AppCompatTextView txtPlanMessage) {
    this.rootView = rootView;
    this.layoutIndicator = layoutIndicator;
    this.planPager = planPager;
    this.rvPlansList = rvPlansList;
    this.txtContinue = txtContinue;
    this.txtPlanMessage = txtPlanMessage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRematchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRematchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_rematch, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRematchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.layoutIndicator;
      CircleIndicator layoutIndicator = ViewBindings.findChildViewById(rootView, id);
      if (layoutIndicator == null) {
        break missingId;
      }

      id = R.id.planPager;
      ViewPager planPager = ViewBindings.findChildViewById(rootView, id);
      if (planPager == null) {
        break missingId;
      }

      id = R.id.rvPlansList;
      RecyclerView rvPlansList = ViewBindings.findChildViewById(rootView, id);
      if (rvPlansList == null) {
        break missingId;
      }

      id = R.id.txtContinue;
      MaterialButton txtContinue = ViewBindings.findChildViewById(rootView, id);
      if (txtContinue == null) {
        break missingId;
      }

      id = R.id.txtPlanMessage;
      AppCompatTextView txtPlanMessage = ViewBindings.findChildViewById(rootView, id);
      if (txtPlanMessage == null) {
        break missingId;
      }

      return new ActivityRematchBinding((ConstraintLayout) rootView, layoutIndicator, planPager,
          rvPlansList, txtContinue, txtPlanMessage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
