// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDeleteReasonBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextInputEditText edtReason;

  @NonNull
  public final AppCompatImageView imgBack;

  @NonNull
  public final AppCompatImageView imgCancel;

  @NonNull
  public final AppCompatImageView ivLogo;

  @NonNull
  public final ConstraintLayout layoutMain;

  @NonNull
  public final LinearLayout llDesc;

  @NonNull
  public final TextView txtDesc1;

  @NonNull
  public final TextView txtDesc2;

  @NonNull
  public final TextView txtDesc3;

  private ActivityDeleteReasonBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextInputEditText edtReason, @NonNull AppCompatImageView imgBack,
      @NonNull AppCompatImageView imgCancel, @NonNull AppCompatImageView ivLogo,
      @NonNull ConstraintLayout layoutMain, @NonNull LinearLayout llDesc,
      @NonNull TextView txtDesc1, @NonNull TextView txtDesc2, @NonNull TextView txtDesc3) {
    this.rootView = rootView;
    this.edtReason = edtReason;
    this.imgBack = imgBack;
    this.imgCancel = imgCancel;
    this.ivLogo = ivLogo;
    this.layoutMain = layoutMain;
    this.llDesc = llDesc;
    this.txtDesc1 = txtDesc1;
    this.txtDesc2 = txtDesc2;
    this.txtDesc3 = txtDesc3;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDeleteReasonBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDeleteReasonBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_delete_reason, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDeleteReasonBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edtReason;
      TextInputEditText edtReason = ViewBindings.findChildViewById(rootView, id);
      if (edtReason == null) {
        break missingId;
      }

      id = R.id.imgBack;
      AppCompatImageView imgBack = ViewBindings.findChildViewById(rootView, id);
      if (imgBack == null) {
        break missingId;
      }

      id = R.id.imgCancel;
      AppCompatImageView imgCancel = ViewBindings.findChildViewById(rootView, id);
      if (imgCancel == null) {
        break missingId;
      }

      id = R.id.ivLogo;
      AppCompatImageView ivLogo = ViewBindings.findChildViewById(rootView, id);
      if (ivLogo == null) {
        break missingId;
      }

      ConstraintLayout layoutMain = (ConstraintLayout) rootView;

      id = R.id.llDesc;
      LinearLayout llDesc = ViewBindings.findChildViewById(rootView, id);
      if (llDesc == null) {
        break missingId;
      }

      id = R.id.txtDesc1;
      TextView txtDesc1 = ViewBindings.findChildViewById(rootView, id);
      if (txtDesc1 == null) {
        break missingId;
      }

      id = R.id.txtDesc2;
      TextView txtDesc2 = ViewBindings.findChildViewById(rootView, id);
      if (txtDesc2 == null) {
        break missingId;
      }

      id = R.id.txtDesc3;
      TextView txtDesc3 = ViewBindings.findChildViewById(rootView, id);
      if (txtDesc3 == null) {
        break missingId;
      }

      return new ActivityDeleteReasonBinding((ConstraintLayout) rootView, edtReason, imgBack,
          imgCancel, ivLogo, layoutMain, llDesc, txtDesc1, txtDesc2, txtDesc3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
