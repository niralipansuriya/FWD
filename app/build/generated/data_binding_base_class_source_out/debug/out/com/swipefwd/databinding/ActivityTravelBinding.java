// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityTravelBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialCardView cardList;

  @NonNull
  public final AppCompatImageView ivClose;

  @NonNull
  public final ConstraintLayout layoutMain;

  @NonNull
  public final RecyclerView rvLocation;

  @NonNull
  public final ScrollView scrollView;

  @NonNull
  public final MaterialButton txtSubmit;

  @NonNull
  public final AppCompatTextView txtTitle;

  @NonNull
  public final TextInputEditText txtTravelLocation;

  private ActivityTravelBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialCardView cardList, @NonNull AppCompatImageView ivClose,
      @NonNull ConstraintLayout layoutMain, @NonNull RecyclerView rvLocation,
      @NonNull ScrollView scrollView, @NonNull MaterialButton txtSubmit,
      @NonNull AppCompatTextView txtTitle, @NonNull TextInputEditText txtTravelLocation) {
    this.rootView = rootView;
    this.cardList = cardList;
    this.ivClose = ivClose;
    this.layoutMain = layoutMain;
    this.rvLocation = rvLocation;
    this.scrollView = scrollView;
    this.txtSubmit = txtSubmit;
    this.txtTitle = txtTitle;
    this.txtTravelLocation = txtTravelLocation;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTravelBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTravelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_travel, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTravelBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardList;
      MaterialCardView cardList = ViewBindings.findChildViewById(rootView, id);
      if (cardList == null) {
        break missingId;
      }

      id = R.id.ivClose;
      AppCompatImageView ivClose = ViewBindings.findChildViewById(rootView, id);
      if (ivClose == null) {
        break missingId;
      }

      ConstraintLayout layoutMain = (ConstraintLayout) rootView;

      id = R.id.rvLocation;
      RecyclerView rvLocation = ViewBindings.findChildViewById(rootView, id);
      if (rvLocation == null) {
        break missingId;
      }

      id = R.id.scrollView;
      ScrollView scrollView = ViewBindings.findChildViewById(rootView, id);
      if (scrollView == null) {
        break missingId;
      }

      id = R.id.txtSubmit;
      MaterialButton txtSubmit = ViewBindings.findChildViewById(rootView, id);
      if (txtSubmit == null) {
        break missingId;
      }

      id = R.id.txtTitle;
      AppCompatTextView txtTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtTitle == null) {
        break missingId;
      }

      id = R.id.txtTravelLocation;
      TextInputEditText txtTravelLocation = ViewBindings.findChildViewById(rootView, id);
      if (txtTravelLocation == null) {
        break missingId;
      }

      return new ActivityTravelBinding((ConstraintLayout) rootView, cardList, ivClose, layoutMain,
          rvLocation, scrollView, txtSubmit, txtTitle, txtTravelLocation);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
