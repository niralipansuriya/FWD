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
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemContactFromWalletsBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppCompatImageView ivCheck;

  @NonNull
  public final LinearLayout layoutUserName;

  @NonNull
  public final AppCompatTextView txtLetter;

  @NonNull
  public final AppCompatTextView txtName;

  private ItemContactFromWalletsBinding(@NonNull LinearLayout rootView,
      @NonNull AppCompatImageView ivCheck, @NonNull LinearLayout layoutUserName,
      @NonNull AppCompatTextView txtLetter, @NonNull AppCompatTextView txtName) {
    this.rootView = rootView;
    this.ivCheck = ivCheck;
    this.layoutUserName = layoutUserName;
    this.txtLetter = txtLetter;
    this.txtName = txtName;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemContactFromWalletsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemContactFromWalletsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_contact_from_wallets, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemContactFromWalletsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ivCheck;
      AppCompatImageView ivCheck = ViewBindings.findChildViewById(rootView, id);
      if (ivCheck == null) {
        break missingId;
      }

      LinearLayout layoutUserName = (LinearLayout) rootView;

      id = R.id.txtLetter;
      AppCompatTextView txtLetter = ViewBindings.findChildViewById(rootView, id);
      if (txtLetter == null) {
        break missingId;
      }

      id = R.id.txtName;
      AppCompatTextView txtName = ViewBindings.findChildViewById(rootView, id);
      if (txtName == null) {
        break missingId;
      }

      return new ItemContactFromWalletsBinding((LinearLayout) rootView, ivCheck, layoutUserName,
          txtLetter, txtName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}