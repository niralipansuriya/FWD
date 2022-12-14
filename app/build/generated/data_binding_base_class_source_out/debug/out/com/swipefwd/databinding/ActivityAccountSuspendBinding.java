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
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAccountSuspendBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageView imgCancel;

  @NonNull
  public final AppCompatImageView imgLogo;

  @NonNull
  public final TextView txtSuspend1;

  @NonNull
  public final TextView txtSuspend2;

  private ActivityAccountSuspendBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageView imgCancel, @NonNull AppCompatImageView imgLogo,
      @NonNull TextView txtSuspend1, @NonNull TextView txtSuspend2) {
    this.rootView = rootView;
    this.imgCancel = imgCancel;
    this.imgLogo = imgLogo;
    this.txtSuspend1 = txtSuspend1;
    this.txtSuspend2 = txtSuspend2;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAccountSuspendBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAccountSuspendBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_account_suspend, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAccountSuspendBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgCancel;
      AppCompatImageView imgCancel = ViewBindings.findChildViewById(rootView, id);
      if (imgCancel == null) {
        break missingId;
      }

      id = R.id.imgLogo;
      AppCompatImageView imgLogo = ViewBindings.findChildViewById(rootView, id);
      if (imgLogo == null) {
        break missingId;
      }

      id = R.id.txtSuspend1;
      TextView txtSuspend1 = ViewBindings.findChildViewById(rootView, id);
      if (txtSuspend1 == null) {
        break missingId;
      }

      id = R.id.txtSuspend2;
      TextView txtSuspend2 = ViewBindings.findChildViewById(rootView, id);
      if (txtSuspend2 == null) {
        break missingId;
      }

      return new ActivityAccountSuspendBinding((ConstraintLayout) rootView, imgCancel, imgLogo,
          txtSuspend1, txtSuspend2);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
