// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class ActivityContactBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextInputEditText edtMsg;

  @NonNull
  public final AppCompatImageView imgCancel;

  @NonNull
  public final AppCompatImageView ivLogo;

  @NonNull
  public final ConstraintLayout layoutMain;

  @NonNull
  public final TextView txtDesc;

  private ActivityContactBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextInputEditText edtMsg, @NonNull AppCompatImageView imgCancel,
      @NonNull AppCompatImageView ivLogo, @NonNull ConstraintLayout layoutMain,
      @NonNull TextView txtDesc) {
    this.rootView = rootView;
    this.edtMsg = edtMsg;
    this.imgCancel = imgCancel;
    this.ivLogo = ivLogo;
    this.layoutMain = layoutMain;
    this.txtDesc = txtDesc;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityContactBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityContactBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_contact, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityContactBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edtMsg;
      TextInputEditText edtMsg = ViewBindings.findChildViewById(rootView, id);
      if (edtMsg == null) {
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

      id = R.id.txtDesc;
      TextView txtDesc = ViewBindings.findChildViewById(rootView, id);
      if (txtDesc == null) {
        break missingId;
      }

      return new ActivityContactBinding((ConstraintLayout) rootView, edtMsg, imgCancel, ivLogo,
          layoutMain, txtDesc);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
