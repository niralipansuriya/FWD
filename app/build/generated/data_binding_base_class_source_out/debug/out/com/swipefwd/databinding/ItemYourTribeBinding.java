// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemYourTribeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final MaterialCardView cardProfile;

  @NonNull
  public final AppCompatImageView imgProfile;

  @NonNull
  public final TextView txtName;

  private ItemYourTribeBinding(@NonNull RelativeLayout rootView,
      @NonNull MaterialCardView cardProfile, @NonNull AppCompatImageView imgProfile,
      @NonNull TextView txtName) {
    this.rootView = rootView;
    this.cardProfile = cardProfile;
    this.imgProfile = imgProfile;
    this.txtName = txtName;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemYourTribeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemYourTribeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_your_tribe, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemYourTribeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardProfile;
      MaterialCardView cardProfile = ViewBindings.findChildViewById(rootView, id);
      if (cardProfile == null) {
        break missingId;
      }

      id = R.id.imgProfile;
      AppCompatImageView imgProfile = ViewBindings.findChildViewById(rootView, id);
      if (imgProfile == null) {
        break missingId;
      }

      id = R.id.txtName;
      TextView txtName = ViewBindings.findChildViewById(rootView, id);
      if (txtName == null) {
        break missingId;
      }

      return new ItemYourTribeBinding((RelativeLayout) rootView, cardProfile, imgProfile, txtName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
