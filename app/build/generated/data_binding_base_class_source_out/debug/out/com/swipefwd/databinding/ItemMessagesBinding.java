// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.daimajia.swipe.SwipeLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemMessagesBinding implements ViewBinding {
  @NonNull
  private final SwipeLayout rootView;

  @NonNull
  public final MaterialButton btnDelete;

  @NonNull
  public final MaterialCardView cardMessage;

  @NonNull
  public final AppCompatImageView imgMessages;

  @NonNull
  public final AppCompatImageView imgOnline;

  @NonNull
  public final ConstraintLayout layoutMain;

  @NonNull
  public final LinearLayout layoutText;

  @NonNull
  public final LinearLayout llDelete;

  @NonNull
  public final SwipeLayout swipeMessage;

  @NonNull
  public final TextView txtExpire;

  @NonNull
  public final TextView txtMessage;

  @NonNull
  public final TextView txtName;

  private ItemMessagesBinding(@NonNull SwipeLayout rootView, @NonNull MaterialButton btnDelete,
      @NonNull MaterialCardView cardMessage, @NonNull AppCompatImageView imgMessages,
      @NonNull AppCompatImageView imgOnline, @NonNull ConstraintLayout layoutMain,
      @NonNull LinearLayout layoutText, @NonNull LinearLayout llDelete,
      @NonNull SwipeLayout swipeMessage, @NonNull TextView txtExpire, @NonNull TextView txtMessage,
      @NonNull TextView txtName) {
    this.rootView = rootView;
    this.btnDelete = btnDelete;
    this.cardMessage = cardMessage;
    this.imgMessages = imgMessages;
    this.imgOnline = imgOnline;
    this.layoutMain = layoutMain;
    this.layoutText = layoutText;
    this.llDelete = llDelete;
    this.swipeMessage = swipeMessage;
    this.txtExpire = txtExpire;
    this.txtMessage = txtMessage;
    this.txtName = txtName;
  }

  @Override
  @NonNull
  public SwipeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemMessagesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemMessagesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_messages, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemMessagesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDelete;
      MaterialButton btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.cardMessage;
      MaterialCardView cardMessage = ViewBindings.findChildViewById(rootView, id);
      if (cardMessage == null) {
        break missingId;
      }

      id = R.id.imgMessages;
      AppCompatImageView imgMessages = ViewBindings.findChildViewById(rootView, id);
      if (imgMessages == null) {
        break missingId;
      }

      id = R.id.imgOnline;
      AppCompatImageView imgOnline = ViewBindings.findChildViewById(rootView, id);
      if (imgOnline == null) {
        break missingId;
      }

      id = R.id.layoutMain;
      ConstraintLayout layoutMain = ViewBindings.findChildViewById(rootView, id);
      if (layoutMain == null) {
        break missingId;
      }

      id = R.id.layoutText;
      LinearLayout layoutText = ViewBindings.findChildViewById(rootView, id);
      if (layoutText == null) {
        break missingId;
      }

      id = R.id.llDelete;
      LinearLayout llDelete = ViewBindings.findChildViewById(rootView, id);
      if (llDelete == null) {
        break missingId;
      }

      SwipeLayout swipeMessage = (SwipeLayout) rootView;

      id = R.id.txtExpire;
      TextView txtExpire = ViewBindings.findChildViewById(rootView, id);
      if (txtExpire == null) {
        break missingId;
      }

      id = R.id.txtMessage;
      TextView txtMessage = ViewBindings.findChildViewById(rootView, id);
      if (txtMessage == null) {
        break missingId;
      }

      id = R.id.txtName;
      TextView txtName = ViewBindings.findChildViewById(rootView, id);
      if (txtName == null) {
        break missingId;
      }

      return new ItemMessagesBinding((SwipeLayout) rootView, btnDelete, cardMessage, imgMessages,
          imgOnline, layoutMain, layoutText, llDelete, swipeMessage, txtExpire, txtMessage,
          txtName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
