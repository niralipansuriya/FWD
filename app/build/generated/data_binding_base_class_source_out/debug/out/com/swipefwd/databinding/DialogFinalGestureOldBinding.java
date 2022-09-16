// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogFinalGestureOldBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialCardView cardGesture;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final RelativeLayout pullDown;

  @NonNull
  public final AppCompatTextView txtCopyGesture;

  @NonNull
  public final AppCompatTextView txtDesc;

  @NonNull
  public final MaterialButton txtRetake;

  @NonNull
  public final MaterialButton txtSubmit;

  @NonNull
  public final AppCompatImageView viewUserImage;

  private DialogFinalGestureOldBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialCardView cardGesture, @NonNull ImageView imageView,
      @NonNull AppCompatImageView imgDialogGradient, @NonNull RelativeLayout pullDown,
      @NonNull AppCompatTextView txtCopyGesture, @NonNull AppCompatTextView txtDesc,
      @NonNull MaterialButton txtRetake, @NonNull MaterialButton txtSubmit,
      @NonNull AppCompatImageView viewUserImage) {
    this.rootView = rootView;
    this.cardGesture = cardGesture;
    this.imageView = imageView;
    this.imgDialogGradient = imgDialogGradient;
    this.pullDown = pullDown;
    this.txtCopyGesture = txtCopyGesture;
    this.txtDesc = txtDesc;
    this.txtRetake = txtRetake;
    this.txtSubmit = txtSubmit;
    this.viewUserImage = viewUserImage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogFinalGestureOldBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogFinalGestureOldBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_final_gesture_old, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogFinalGestureOldBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardGesture;
      MaterialCardView cardGesture = ViewBindings.findChildViewById(rootView, id);
      if (cardGesture == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      id = R.id.pullDown;
      RelativeLayout pullDown = ViewBindings.findChildViewById(rootView, id);
      if (pullDown == null) {
        break missingId;
      }

      id = R.id.txtCopyGesture;
      AppCompatTextView txtCopyGesture = ViewBindings.findChildViewById(rootView, id);
      if (txtCopyGesture == null) {
        break missingId;
      }

      id = R.id.txtDesc;
      AppCompatTextView txtDesc = ViewBindings.findChildViewById(rootView, id);
      if (txtDesc == null) {
        break missingId;
      }

      id = R.id.txtRetake;
      MaterialButton txtRetake = ViewBindings.findChildViewById(rootView, id);
      if (txtRetake == null) {
        break missingId;
      }

      id = R.id.txtSubmit;
      MaterialButton txtSubmit = ViewBindings.findChildViewById(rootView, id);
      if (txtSubmit == null) {
        break missingId;
      }

      id = R.id.viewUserImage;
      AppCompatImageView viewUserImage = ViewBindings.findChildViewById(rootView, id);
      if (viewUserImage == null) {
        break missingId;
      }

      return new DialogFinalGestureOldBinding((ConstraintLayout) rootView, cardGesture, imageView,
          imgDialogGradient, pullDown, txtCopyGesture, txtDesc, txtRetake, txtSubmit,
          viewUserImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
