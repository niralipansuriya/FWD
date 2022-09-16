// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogDeleteAccountBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnDeleteCancel;

  @NonNull
  public final MaterialButton btnDeleteYes;

  @NonNull
  public final MaterialCardView cardBottom;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final LinearLayout layoutConfirm;

  private DialogDeleteAccountBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnDeleteCancel, @NonNull MaterialButton btnDeleteYes,
      @NonNull MaterialCardView cardBottom, @NonNull AppCompatImageView imgDialogGradient,
      @NonNull LinearLayout layoutConfirm) {
    this.rootView = rootView;
    this.btnDeleteCancel = btnDeleteCancel;
    this.btnDeleteYes = btnDeleteYes;
    this.cardBottom = cardBottom;
    this.imgDialogGradient = imgDialogGradient;
    this.layoutConfirm = layoutConfirm;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogDeleteAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogDeleteAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_delete_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogDeleteAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDeleteCancel;
      MaterialButton btnDeleteCancel = ViewBindings.findChildViewById(rootView, id);
      if (btnDeleteCancel == null) {
        break missingId;
      }

      id = R.id.btnDeleteYes;
      MaterialButton btnDeleteYes = ViewBindings.findChildViewById(rootView, id);
      if (btnDeleteYes == null) {
        break missingId;
      }

      id = R.id.cardBottom;
      MaterialCardView cardBottom = ViewBindings.findChildViewById(rootView, id);
      if (cardBottom == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      id = R.id.layoutConfirm;
      LinearLayout layoutConfirm = ViewBindings.findChildViewById(rootView, id);
      if (layoutConfirm == null) {
        break missingId;
      }

      return new DialogDeleteAccountBinding((ConstraintLayout) rootView, btnDeleteCancel,
          btnDeleteYes, cardBottom, imgDialogGradient, layoutConfirm);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}