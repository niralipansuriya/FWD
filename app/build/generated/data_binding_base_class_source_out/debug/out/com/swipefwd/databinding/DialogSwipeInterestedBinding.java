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
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogSwipeInterestedBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnGoBack;

  @NonNull
  public final MaterialButton btnGotIt;

  @NonNull
  public final MaterialButton btnYes;

  @NonNull
  public final AppCompatImageView cardConnected;

  @NonNull
  public final MaterialCardView cardIcon;

  @NonNull
  public final AppCompatImageView cardLike;

  @NonNull
  public final MaterialCardView cardMessage;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final TextView txtDesc;

  @NonNull
  public final TextView txtTitle;

  private DialogSwipeInterestedBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnGoBack, @NonNull MaterialButton btnGotIt,
      @NonNull MaterialButton btnYes, @NonNull AppCompatImageView cardConnected,
      @NonNull MaterialCardView cardIcon, @NonNull AppCompatImageView cardLike,
      @NonNull MaterialCardView cardMessage, @NonNull AppCompatImageView imgDialogGradient,
      @NonNull TextView txtDesc, @NonNull TextView txtTitle) {
    this.rootView = rootView;
    this.btnGoBack = btnGoBack;
    this.btnGotIt = btnGotIt;
    this.btnYes = btnYes;
    this.cardConnected = cardConnected;
    this.cardIcon = cardIcon;
    this.cardLike = cardLike;
    this.cardMessage = cardMessage;
    this.imgDialogGradient = imgDialogGradient;
    this.txtDesc = txtDesc;
    this.txtTitle = txtTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogSwipeInterestedBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogSwipeInterestedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_swipe_interested, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogSwipeInterestedBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnGoBack;
      MaterialButton btnGoBack = ViewBindings.findChildViewById(rootView, id);
      if (btnGoBack == null) {
        break missingId;
      }

      id = R.id.btnGotIt;
      MaterialButton btnGotIt = ViewBindings.findChildViewById(rootView, id);
      if (btnGotIt == null) {
        break missingId;
      }

      id = R.id.btnYes;
      MaterialButton btnYes = ViewBindings.findChildViewById(rootView, id);
      if (btnYes == null) {
        break missingId;
      }

      id = R.id.cardConnected;
      AppCompatImageView cardConnected = ViewBindings.findChildViewById(rootView, id);
      if (cardConnected == null) {
        break missingId;
      }

      id = R.id.cardIcon;
      MaterialCardView cardIcon = ViewBindings.findChildViewById(rootView, id);
      if (cardIcon == null) {
        break missingId;
      }

      id = R.id.cardLike;
      AppCompatImageView cardLike = ViewBindings.findChildViewById(rootView, id);
      if (cardLike == null) {
        break missingId;
      }

      id = R.id.cardMessage;
      MaterialCardView cardMessage = ViewBindings.findChildViewById(rootView, id);
      if (cardMessage == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      id = R.id.txtDesc;
      TextView txtDesc = ViewBindings.findChildViewById(rootView, id);
      if (txtDesc == null) {
        break missingId;
      }

      id = R.id.txtTitle;
      TextView txtTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtTitle == null) {
        break missingId;
      }

      return new DialogSwipeInterestedBinding((ConstraintLayout) rootView, btnGoBack, btnGotIt,
          btnYes, cardConnected, cardIcon, cardLike, cardMessage, imgDialogGradient, txtDesc,
          txtTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
