// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public final class DialogInviteRedemptionBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnHome;

  @NonNull
  public final MaterialButton btnWallet;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final AppCompatTextView txtHeader;

  @NonNull
  public final AppCompatTextView txtMessage;

  private DialogInviteRedemptionBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnHome, @NonNull MaterialButton btnWallet,
      @NonNull AppCompatImageView imgDialogGradient, @NonNull AppCompatTextView txtHeader,
      @NonNull AppCompatTextView txtMessage) {
    this.rootView = rootView;
    this.btnHome = btnHome;
    this.btnWallet = btnWallet;
    this.imgDialogGradient = imgDialogGradient;
    this.txtHeader = txtHeader;
    this.txtMessage = txtMessage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogInviteRedemptionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogInviteRedemptionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_invite_redemption, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogInviteRedemptionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnHome;
      MaterialButton btnHome = ViewBindings.findChildViewById(rootView, id);
      if (btnHome == null) {
        break missingId;
      }

      id = R.id.btnWallet;
      MaterialButton btnWallet = ViewBindings.findChildViewById(rootView, id);
      if (btnWallet == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      id = R.id.txtHeader;
      AppCompatTextView txtHeader = ViewBindings.findChildViewById(rootView, id);
      if (txtHeader == null) {
        break missingId;
      }

      id = R.id.txtMessage;
      AppCompatTextView txtMessage = ViewBindings.findChildViewById(rootView, id);
      if (txtMessage == null) {
        break missingId;
      }

      return new DialogInviteRedemptionBinding((ConstraintLayout) rootView, btnHome, btnWallet,
          imgDialogGradient, txtHeader, txtMessage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}