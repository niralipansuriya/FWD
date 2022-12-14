// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityInstagramFullScreenBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatImageView ivCancel;

  @NonNull
  public final AppCompatImageView ivImage;

  @NonNull
  public final RoundedImageView ivUser;

  @NonNull
  public final RelativeLayout layoutUser;

  @NonNull
  public final AppCompatTextView txtUserName;

  private ActivityInstagramFullScreenBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatImageView ivCancel, @NonNull AppCompatImageView ivImage,
      @NonNull RoundedImageView ivUser, @NonNull RelativeLayout layoutUser,
      @NonNull AppCompatTextView txtUserName) {
    this.rootView = rootView;
    this.ivCancel = ivCancel;
    this.ivImage = ivImage;
    this.ivUser = ivUser;
    this.layoutUser = layoutUser;
    this.txtUserName = txtUserName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityInstagramFullScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityInstagramFullScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_instagram_full_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityInstagramFullScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ivCancel;
      AppCompatImageView ivCancel = ViewBindings.findChildViewById(rootView, id);
      if (ivCancel == null) {
        break missingId;
      }

      id = R.id.ivImage;
      AppCompatImageView ivImage = ViewBindings.findChildViewById(rootView, id);
      if (ivImage == null) {
        break missingId;
      }

      id = R.id.ivUser;
      RoundedImageView ivUser = ViewBindings.findChildViewById(rootView, id);
      if (ivUser == null) {
        break missingId;
      }

      id = R.id.layoutUser;
      RelativeLayout layoutUser = ViewBindings.findChildViewById(rootView, id);
      if (layoutUser == null) {
        break missingId;
      }

      id = R.id.txtUserName;
      AppCompatTextView txtUserName = ViewBindings.findChildViewById(rootView, id);
      if (txtUserName == null) {
        break missingId;
      }

      return new ActivityInstagramFullScreenBinding((RelativeLayout) rootView, ivCancel, ivImage,
          ivUser, layoutUser, txtUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
