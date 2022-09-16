// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public final class DialogAlternateLoginAddedBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnLetsGo;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final AppCompatImageView imgSocialLogo;

  @NonNull
  public final LinearLayout llPhone;

  @NonNull
  public final LinearLayout llUser;

  @NonNull
  public final AppCompatTextView txtHeader;

  @NonNull
  public final AppCompatTextView txtMessage;

  @NonNull
  public final AppCompatTextView txtPhoneNumber;

  @NonNull
  public final AppCompatTextView txtUserName;

  private DialogAlternateLoginAddedBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnLetsGo, @NonNull AppCompatImageView imgDialogGradient,
      @NonNull AppCompatImageView imgSocialLogo, @NonNull LinearLayout llPhone,
      @NonNull LinearLayout llUser, @NonNull AppCompatTextView txtHeader,
      @NonNull AppCompatTextView txtMessage, @NonNull AppCompatTextView txtPhoneNumber,
      @NonNull AppCompatTextView txtUserName) {
    this.rootView = rootView;
    this.btnLetsGo = btnLetsGo;
    this.imgDialogGradient = imgDialogGradient;
    this.imgSocialLogo = imgSocialLogo;
    this.llPhone = llPhone;
    this.llUser = llUser;
    this.txtHeader = txtHeader;
    this.txtMessage = txtMessage;
    this.txtPhoneNumber = txtPhoneNumber;
    this.txtUserName = txtUserName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogAlternateLoginAddedBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogAlternateLoginAddedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_alternate_login_added, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogAlternateLoginAddedBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnLetsGo;
      MaterialButton btnLetsGo = ViewBindings.findChildViewById(rootView, id);
      if (btnLetsGo == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      id = R.id.imgSocialLogo;
      AppCompatImageView imgSocialLogo = ViewBindings.findChildViewById(rootView, id);
      if (imgSocialLogo == null) {
        break missingId;
      }

      id = R.id.llPhone;
      LinearLayout llPhone = ViewBindings.findChildViewById(rootView, id);
      if (llPhone == null) {
        break missingId;
      }

      id = R.id.llUser;
      LinearLayout llUser = ViewBindings.findChildViewById(rootView, id);
      if (llUser == null) {
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

      id = R.id.txtPhoneNumber;
      AppCompatTextView txtPhoneNumber = ViewBindings.findChildViewById(rootView, id);
      if (txtPhoneNumber == null) {
        break missingId;
      }

      id = R.id.txtUserName;
      AppCompatTextView txtUserName = ViewBindings.findChildViewById(rootView, id);
      if (txtUserName == null) {
        break missingId;
      }

      return new DialogAlternateLoginAddedBinding((ConstraintLayout) rootView, btnLetsGo,
          imgDialogGradient, imgSocialLogo, llPhone, llUser, txtHeader, txtMessage, txtPhoneNumber,
          txtUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
