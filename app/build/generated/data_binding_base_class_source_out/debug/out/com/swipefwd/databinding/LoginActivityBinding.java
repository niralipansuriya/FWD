// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LoginActivityBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MaterialButton btnFacebook;

  @NonNull
  public final CardView cardGoogle;

  @NonNull
  public final CardView cardLinkedIn;

  @NonNull
  public final CardView cardPhone;

  @NonNull
  public final FrameLayout frmFacebook;

  @NonNull
  public final AppCompatImageView ivFacebook;

  @NonNull
  public final AppCompatImageView ivLogo;

  @NonNull
  public final AppCompatTextView labelContinueWith;

  @NonNull
  public final LinearLayout layoutBottom;

  @NonNull
  public final ConstraintLayout layoutFacebook;

  @NonNull
  public final LinearLayout layoutTop;

  @NonNull
  public final AppCompatImageView txtFwd;

  @NonNull
  public final AppCompatTextView txtTermsPolicy;

  @NonNull
  public final AppCompatTextView txtTroubleSignIn;

  private LoginActivityBinding(@NonNull LinearLayout rootView, @NonNull MaterialButton btnFacebook,
      @NonNull CardView cardGoogle, @NonNull CardView cardLinkedIn, @NonNull CardView cardPhone,
      @NonNull FrameLayout frmFacebook, @NonNull AppCompatImageView ivFacebook,
      @NonNull AppCompatImageView ivLogo, @NonNull AppCompatTextView labelContinueWith,
      @NonNull LinearLayout layoutBottom, @NonNull ConstraintLayout layoutFacebook,
      @NonNull LinearLayout layoutTop, @NonNull AppCompatImageView txtFwd,
      @NonNull AppCompatTextView txtTermsPolicy, @NonNull AppCompatTextView txtTroubleSignIn) {
    this.rootView = rootView;
    this.btnFacebook = btnFacebook;
    this.cardGoogle = cardGoogle;
    this.cardLinkedIn = cardLinkedIn;
    this.cardPhone = cardPhone;
    this.frmFacebook = frmFacebook;
    this.ivFacebook = ivFacebook;
    this.ivLogo = ivLogo;
    this.labelContinueWith = labelContinueWith;
    this.layoutBottom = layoutBottom;
    this.layoutFacebook = layoutFacebook;
    this.layoutTop = layoutTop;
    this.txtFwd = txtFwd;
    this.txtTermsPolicy = txtTermsPolicy;
    this.txtTroubleSignIn = txtTroubleSignIn;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LoginActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LoginActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.login_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LoginActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnFacebook;
      MaterialButton btnFacebook = ViewBindings.findChildViewById(rootView, id);
      if (btnFacebook == null) {
        break missingId;
      }

      id = R.id.cardGoogle;
      CardView cardGoogle = ViewBindings.findChildViewById(rootView, id);
      if (cardGoogle == null) {
        break missingId;
      }

      id = R.id.cardLinkedIn;
      CardView cardLinkedIn = ViewBindings.findChildViewById(rootView, id);
      if (cardLinkedIn == null) {
        break missingId;
      }

      id = R.id.cardPhone;
      CardView cardPhone = ViewBindings.findChildViewById(rootView, id);
      if (cardPhone == null) {
        break missingId;
      }

      id = R.id.frmFacebook;
      FrameLayout frmFacebook = ViewBindings.findChildViewById(rootView, id);
      if (frmFacebook == null) {
        break missingId;
      }

      id = R.id.ivFacebook;
      AppCompatImageView ivFacebook = ViewBindings.findChildViewById(rootView, id);
      if (ivFacebook == null) {
        break missingId;
      }

      id = R.id.ivLogo;
      AppCompatImageView ivLogo = ViewBindings.findChildViewById(rootView, id);
      if (ivLogo == null) {
        break missingId;
      }

      id = R.id.labelContinueWith;
      AppCompatTextView labelContinueWith = ViewBindings.findChildViewById(rootView, id);
      if (labelContinueWith == null) {
        break missingId;
      }

      id = R.id.layoutBottom;
      LinearLayout layoutBottom = ViewBindings.findChildViewById(rootView, id);
      if (layoutBottom == null) {
        break missingId;
      }

      id = R.id.layoutFacebook;
      ConstraintLayout layoutFacebook = ViewBindings.findChildViewById(rootView, id);
      if (layoutFacebook == null) {
        break missingId;
      }

      id = R.id.layoutTop;
      LinearLayout layoutTop = ViewBindings.findChildViewById(rootView, id);
      if (layoutTop == null) {
        break missingId;
      }

      id = R.id.txtFwd;
      AppCompatImageView txtFwd = ViewBindings.findChildViewById(rootView, id);
      if (txtFwd == null) {
        break missingId;
      }

      id = R.id.txtTermsPolicy;
      AppCompatTextView txtTermsPolicy = ViewBindings.findChildViewById(rootView, id);
      if (txtTermsPolicy == null) {
        break missingId;
      }

      id = R.id.txtTroubleSignIn;
      AppCompatTextView txtTroubleSignIn = ViewBindings.findChildViewById(rootView, id);
      if (txtTroubleSignIn == null) {
        break missingId;
      }

      return new LoginActivityBinding((LinearLayout) rootView, btnFacebook, cardGoogle,
          cardLinkedIn, cardPhone, frmFacebook, ivFacebook, ivLogo, labelContinueWith, layoutBottom,
          layoutFacebook, layoutTop, txtFwd, txtTermsPolicy, txtTroubleSignIn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}