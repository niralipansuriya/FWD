// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemSelectedPhotoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Group groupId;

  @NonNull
  public final AppCompatImageView ivClose;

  @NonNull
  public final RoundedImageView tvImage1;

  @NonNull
  public final RoundedImageView tvSelectedImage;

  private ItemSelectedPhotoBinding(@NonNull ConstraintLayout rootView, @NonNull Group groupId,
      @NonNull AppCompatImageView ivClose, @NonNull RoundedImageView tvImage1,
      @NonNull RoundedImageView tvSelectedImage) {
    this.rootView = rootView;
    this.groupId = groupId;
    this.ivClose = ivClose;
    this.tvImage1 = tvImage1;
    this.tvSelectedImage = tvSelectedImage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemSelectedPhotoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemSelectedPhotoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_selected_photo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemSelectedPhotoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.groupId;
      Group groupId = ViewBindings.findChildViewById(rootView, id);
      if (groupId == null) {
        break missingId;
      }

      id = R.id.ivClose;
      AppCompatImageView ivClose = ViewBindings.findChildViewById(rootView, id);
      if (ivClose == null) {
        break missingId;
      }

      id = R.id.tvImage1;
      RoundedImageView tvImage1 = ViewBindings.findChildViewById(rootView, id);
      if (tvImage1 == null) {
        break missingId;
      }

      id = R.id.tvSelectedImage;
      RoundedImageView tvSelectedImage = ViewBindings.findChildViewById(rootView, id);
      if (tvSelectedImage == null) {
        break missingId;
      }

      return new ItemSelectedPhotoBinding((ConstraintLayout) rootView, groupId, ivClose, tvImage1,
          tvSelectedImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}