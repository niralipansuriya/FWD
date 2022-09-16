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
import com.google.android.material.card.MaterialCardView;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogExtendTimeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnDialogAddTime;

  @NonNull
  public final MaterialCardView cardBottom;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final AppCompatImageView imgHand;

  @NonNull
  public final RoundedImageView imgUser;

  @NonNull
  public final TextView txtGetTime;

  private DialogExtendTimeBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnDialogAddTime, @NonNull MaterialCardView cardBottom,
      @NonNull AppCompatImageView imgDialogGradient, @NonNull AppCompatImageView imgHand,
      @NonNull RoundedImageView imgUser, @NonNull TextView txtGetTime) {
    this.rootView = rootView;
    this.btnDialogAddTime = btnDialogAddTime;
    this.cardBottom = cardBottom;
    this.imgDialogGradient = imgDialogGradient;
    this.imgHand = imgHand;
    this.imgUser = imgUser;
    this.txtGetTime = txtGetTime;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogExtendTimeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogExtendTimeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_extend_time, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogExtendTimeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDialogAddTime;
      MaterialButton btnDialogAddTime = ViewBindings.findChildViewById(rootView, id);
      if (btnDialogAddTime == null) {
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

      id = R.id.imgHand;
      AppCompatImageView imgHand = ViewBindings.findChildViewById(rootView, id);
      if (imgHand == null) {
        break missingId;
      }

      id = R.id.imgUser;
      RoundedImageView imgUser = ViewBindings.findChildViewById(rootView, id);
      if (imgUser == null) {
        break missingId;
      }

      id = R.id.txtGetTime;
      TextView txtGetTime = ViewBindings.findChildViewById(rootView, id);
      if (txtGetTime == null) {
        break missingId;
      }

      return new DialogExtendTimeBinding((ConstraintLayout) rootView, btnDialogAddTime, cardBottom,
          imgDialogGradient, imgHand, imgUser, txtGetTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}