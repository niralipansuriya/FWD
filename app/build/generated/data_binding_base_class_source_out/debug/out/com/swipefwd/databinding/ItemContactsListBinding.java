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
import com.daimajia.swipe.SwipeLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemContactsListBinding implements ViewBinding {
  @NonNull
  private final SwipeLayout rootView;

  @NonNull
  public final MaterialButton btnDelete;

  @NonNull
  public final MaterialCardView cardProfile;

  @NonNull
  public final AppCompatImageView imgProfile;

  @NonNull
  public final LinearLayout layoutDelete;

  @NonNull
  public final SwipeLayout swipeNewMatchMaker;

  @NonNull
  public final MaterialButton txtAddTribe;

  @NonNull
  public final AppCompatTextView txtName;

  private ItemContactsListBinding(@NonNull SwipeLayout rootView, @NonNull MaterialButton btnDelete,
      @NonNull MaterialCardView cardProfile, @NonNull AppCompatImageView imgProfile,
      @NonNull LinearLayout layoutDelete, @NonNull SwipeLayout swipeNewMatchMaker,
      @NonNull MaterialButton txtAddTribe, @NonNull AppCompatTextView txtName) {
    this.rootView = rootView;
    this.btnDelete = btnDelete;
    this.cardProfile = cardProfile;
    this.imgProfile = imgProfile;
    this.layoutDelete = layoutDelete;
    this.swipeNewMatchMaker = swipeNewMatchMaker;
    this.txtAddTribe = txtAddTribe;
    this.txtName = txtName;
  }

  @Override
  @NonNull
  public SwipeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemContactsListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemContactsListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_contacts_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemContactsListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDelete;
      MaterialButton btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

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

      id = R.id.layoutDelete;
      LinearLayout layoutDelete = ViewBindings.findChildViewById(rootView, id);
      if (layoutDelete == null) {
        break missingId;
      }

      SwipeLayout swipeNewMatchMaker = (SwipeLayout) rootView;

      id = R.id.txtAddTribe;
      MaterialButton txtAddTribe = ViewBindings.findChildViewById(rootView, id);
      if (txtAddTribe == null) {
        break missingId;
      }

      id = R.id.txtName;
      AppCompatTextView txtName = ViewBindings.findChildViewById(rootView, id);
      if (txtName == null) {
        break missingId;
      }

      return new ItemContactsListBinding((SwipeLayout) rootView, btnDelete, cardProfile, imgProfile,
          layoutDelete, swipeNewMatchMaker, txtAddTribe, txtName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
