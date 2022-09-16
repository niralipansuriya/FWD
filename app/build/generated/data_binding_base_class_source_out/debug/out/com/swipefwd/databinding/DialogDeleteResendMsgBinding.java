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
import com.google.android.material.card.MaterialCardView;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogDeleteResendMsgBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnCancel;

  @NonNull
  public final MaterialButton btnDelete;

  @NonNull
  public final MaterialButton btnResend;

  @NonNull
  public final MaterialCardView cardMain;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  private DialogDeleteResendMsgBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnCancel, @NonNull MaterialButton btnDelete,
      @NonNull MaterialButton btnResend, @NonNull MaterialCardView cardMain,
      @NonNull AppCompatImageView imgDialogGradient) {
    this.rootView = rootView;
    this.btnCancel = btnCancel;
    this.btnDelete = btnDelete;
    this.btnResend = btnResend;
    this.cardMain = cardMain;
    this.imgDialogGradient = imgDialogGradient;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogDeleteResendMsgBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogDeleteResendMsgBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_delete_resend_msg, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogDeleteResendMsgBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnCancel;
      MaterialButton btnCancel = ViewBindings.findChildViewById(rootView, id);
      if (btnCancel == null) {
        break missingId;
      }

      id = R.id.btnDelete;
      MaterialButton btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.btnResend;
      MaterialButton btnResend = ViewBindings.findChildViewById(rootView, id);
      if (btnResend == null) {
        break missingId;
      }

      id = R.id.cardMain;
      MaterialCardView cardMain = ViewBindings.findChildViewById(rootView, id);
      if (cardMain == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      return new DialogDeleteResendMsgBinding((ConstraintLayout) rootView, btnCancel, btnDelete,
          btnResend, cardMain, imgDialogGradient);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
