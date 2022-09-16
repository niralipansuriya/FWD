// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityOccupationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageView ivClose;

  @NonNull
  public final ConstraintLayout layoutMain;

  @NonNull
  public final AppCompatEditText txtCompanyName;

  @NonNull
  public final AppCompatTextView txtIndex;

  @NonNull
  public final AppCompatTextView txtOccupationCompany;

  @NonNull
  public final AppCompatEditText txtOccupationName;

  @NonNull
  public final AppCompatTextView txtOccupationTitle;

  @NonNull
  public final MaterialButton txtSkip;

  @NonNull
  public final MaterialButton txtSubmit;

  @NonNull
  public final AppCompatTextView txtTitle;

  private ActivityOccupationBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageView ivClose, @NonNull ConstraintLayout layoutMain,
      @NonNull AppCompatEditText txtCompanyName, @NonNull AppCompatTextView txtIndex,
      @NonNull AppCompatTextView txtOccupationCompany, @NonNull AppCompatEditText txtOccupationName,
      @NonNull AppCompatTextView txtOccupationTitle, @NonNull MaterialButton txtSkip,
      @NonNull MaterialButton txtSubmit, @NonNull AppCompatTextView txtTitle) {
    this.rootView = rootView;
    this.ivClose = ivClose;
    this.layoutMain = layoutMain;
    this.txtCompanyName = txtCompanyName;
    this.txtIndex = txtIndex;
    this.txtOccupationCompany = txtOccupationCompany;
    this.txtOccupationName = txtOccupationName;
    this.txtOccupationTitle = txtOccupationTitle;
    this.txtSkip = txtSkip;
    this.txtSubmit = txtSubmit;
    this.txtTitle = txtTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityOccupationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityOccupationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_occupation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityOccupationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ivClose;
      AppCompatImageView ivClose = ViewBindings.findChildViewById(rootView, id);
      if (ivClose == null) {
        break missingId;
      }

      ConstraintLayout layoutMain = (ConstraintLayout) rootView;

      id = R.id.txtCompanyName;
      AppCompatEditText txtCompanyName = ViewBindings.findChildViewById(rootView, id);
      if (txtCompanyName == null) {
        break missingId;
      }

      id = R.id.txtIndex;
      AppCompatTextView txtIndex = ViewBindings.findChildViewById(rootView, id);
      if (txtIndex == null) {
        break missingId;
      }

      id = R.id.txtOccupationCompany;
      AppCompatTextView txtOccupationCompany = ViewBindings.findChildViewById(rootView, id);
      if (txtOccupationCompany == null) {
        break missingId;
      }

      id = R.id.txtOccupationName;
      AppCompatEditText txtOccupationName = ViewBindings.findChildViewById(rootView, id);
      if (txtOccupationName == null) {
        break missingId;
      }

      id = R.id.txtOccupationTitle;
      AppCompatTextView txtOccupationTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtOccupationTitle == null) {
        break missingId;
      }

      id = R.id.txtSkip;
      MaterialButton txtSkip = ViewBindings.findChildViewById(rootView, id);
      if (txtSkip == null) {
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

      return new ActivityOccupationBinding((ConstraintLayout) rootView, ivClose, layoutMain,
          txtCompanyName, txtIndex, txtOccupationCompany, txtOccupationName, txtOccupationTitle,
          txtSkip, txtSubmit, txtTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}