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
import com.google.android.material.button.MaterialButton;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogMoreProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnContinue;

  @NonNull
  public final MaterialButton btnNo;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final RoundedImageView ivProfilePicture;

  @NonNull
  public final TextView txt1;

  @NonNull
  public final TextView txt2;

  private DialogMoreProfileBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnContinue, @NonNull MaterialButton btnNo,
      @NonNull AppCompatImageView imgDialogGradient, @NonNull RoundedImageView ivProfilePicture,
      @NonNull TextView txt1, @NonNull TextView txt2) {
    this.rootView = rootView;
    this.btnContinue = btnContinue;
    this.btnNo = btnNo;
    this.imgDialogGradient = imgDialogGradient;
    this.ivProfilePicture = ivProfilePicture;
    this.txt1 = txt1;
    this.txt2 = txt2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogMoreProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogMoreProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_more_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogMoreProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnContinue;
      MaterialButton btnContinue = ViewBindings.findChildViewById(rootView, id);
      if (btnContinue == null) {
        break missingId;
      }

      id = R.id.btnNo;
      MaterialButton btnNo = ViewBindings.findChildViewById(rootView, id);
      if (btnNo == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      id = R.id.ivProfilePicture;
      RoundedImageView ivProfilePicture = ViewBindings.findChildViewById(rootView, id);
      if (ivProfilePicture == null) {
        break missingId;
      }

      id = R.id.txt1;
      TextView txt1 = ViewBindings.findChildViewById(rootView, id);
      if (txt1 == null) {
        break missingId;
      }

      id = R.id.txt2;
      TextView txt2 = ViewBindings.findChildViewById(rootView, id);
      if (txt2 == null) {
        break missingId;
      }

      return new DialogMoreProfileBinding((ConstraintLayout) rootView, btnContinue, btnNo,
          imgDialogGradient, ivProfilePicture, txt1, txt2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
