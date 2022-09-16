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
import com.google.android.material.card.MaterialCardView;
import com.swipefwd.R;
import com.swipefwd.utils.wheelPicker.WheelPicker;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogCustomRechargeAmountBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnSubmit;

  @NonNull
  public final MaterialCardView cardBottom;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final AppCompatTextView txtTimeOut;

  @NonNull
  public final WheelPicker wheelAmount;

  private DialogCustomRechargeAmountBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnSubmit, @NonNull MaterialCardView cardBottom,
      @NonNull AppCompatImageView imgDialogGradient, @NonNull AppCompatTextView txtTimeOut,
      @NonNull WheelPicker wheelAmount) {
    this.rootView = rootView;
    this.btnSubmit = btnSubmit;
    this.cardBottom = cardBottom;
    this.imgDialogGradient = imgDialogGradient;
    this.txtTimeOut = txtTimeOut;
    this.wheelAmount = wheelAmount;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogCustomRechargeAmountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogCustomRechargeAmountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_custom_recharge_amount, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogCustomRechargeAmountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSubmit;
      MaterialButton btnSubmit = ViewBindings.findChildViewById(rootView, id);
      if (btnSubmit == null) {
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

      id = R.id.txtTimeOut;
      AppCompatTextView txtTimeOut = ViewBindings.findChildViewById(rootView, id);
      if (txtTimeOut == null) {
        break missingId;
      }

      id = R.id.wheelAmount;
      WheelPicker wheelAmount = ViewBindings.findChildViewById(rootView, id);
      if (wheelAmount == null) {
        break missingId;
      }

      return new DialogCustomRechargeAmountBinding((ConstraintLayout) rootView, btnSubmit,
          cardBottom, imgDialogGradient, txtTimeOut, wheelAmount);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}