// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public final class DialogUnlinkLinekdinBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnCancel;

  @NonNull
  public final MaterialButton btnYes;

  @NonNull
  public final MaterialCardView cardBottom;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final LinearLayout layoutConfirm;

  @NonNull
  public final RelativeLayout pullDown;

  private DialogUnlinkLinekdinBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnCancel, @NonNull MaterialButton btnYes,
      @NonNull MaterialCardView cardBottom, @NonNull AppCompatImageView imgDialogGradient,
      @NonNull LinearLayout layoutConfirm, @NonNull RelativeLayout pullDown) {
    this.rootView = rootView;
    this.btnCancel = btnCancel;
    this.btnYes = btnYes;
    this.cardBottom = cardBottom;
    this.imgDialogGradient = imgDialogGradient;
    this.layoutConfirm = layoutConfirm;
    this.pullDown = pullDown;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogUnlinkLinekdinBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogUnlinkLinekdinBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_unlink_linekdin, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogUnlinkLinekdinBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnCancel;
      MaterialButton btnCancel = ViewBindings.findChildViewById(rootView, id);
      if (btnCancel == null) {
        break missingId;
      }

      id = R.id.btnYes;
      MaterialButton btnYes = ViewBindings.findChildViewById(rootView, id);
      if (btnYes == null) {
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

      id = R.id.pullDown;
      RelativeLayout pullDown = ViewBindings.findChildViewById(rootView, id);
      if (pullDown == null) {
        break missingId;
      }

      return new DialogUnlinkLinekdinBinding((ConstraintLayout) rootView, btnCancel, btnYes,
          cardBottom, imgDialogGradient, layoutConfirm, pullDown);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
