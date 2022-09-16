// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemInstagramImagesBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final CardView cardInsta;

  @NonNull
  public final AppCompatImageView ivImage;

  private ItemInstagramImagesBinding(@NonNull CardView rootView, @NonNull CardView cardInsta,
      @NonNull AppCompatImageView ivImage) {
    this.rootView = rootView;
    this.cardInsta = cardInsta;
    this.ivImage = ivImage;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemInstagramImagesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemInstagramImagesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_instagram_images, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemInstagramImagesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      CardView cardInsta = (CardView) rootView;

      id = R.id.ivImage;
      AppCompatImageView ivImage = ViewBindings.findChildViewById(rootView, id);
      if (ivImage == null) {
        break missingId;
      }

      return new ItemInstagramImagesBinding((CardView) rootView, cardInsta, ivImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}