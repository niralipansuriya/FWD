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
import com.google.android.material.button.MaterialButton;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogUnmatchUserBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnGotIt;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  private DialogUnmatchUserBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnGotIt, @NonNull AppCompatImageView imgDialogGradient) {
    this.rootView = rootView;
    this.btnGotIt = btnGotIt;
    this.imgDialogGradient = imgDialogGradient;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogUnmatchUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogUnmatchUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_unmatch_user, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogUnmatchUserBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnGotIt;
      MaterialButton btnGotIt = ViewBindings.findChildViewById(rootView, id);
      if (btnGotIt == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      return new DialogUnmatchUserBinding((ConstraintLayout) rootView, btnGotIt, imgDialogGradient);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
