// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySwipeChatBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ScrollView ScrollChat;

  @NonNull
  public final ConstraintLayout bottomLayout;

  @NonNull
  public final TextInputEditText edtMessage;

  @NonNull
  public final RoundedImageView img1;

  @NonNull
  public final RoundedImageView img2;

  @NonNull
  public final AppCompatImageView ivCancel;

  @NonNull
  public final AppCompatImageView ivGallery;

  @NonNull
  public final AppCompatImageView ivRecord;

  @NonNull
  public final AppCompatImageView ivSend;

  @NonNull
  public final RecyclerView rcvChat;

  @NonNull
  public final RelativeLayout rlImages;

  @NonNull
  public final TextView txt1;

  @NonNull
  public final TextView txt2;

  @NonNull
  public final TextView txt3;

  @NonNull
  public final AppCompatTextView txtYou;

  private ActivitySwipeChatBinding(@NonNull ConstraintLayout rootView,
      @NonNull ScrollView ScrollChat, @NonNull ConstraintLayout bottomLayout,
      @NonNull TextInputEditText edtMessage, @NonNull RoundedImageView img1,
      @NonNull RoundedImageView img2, @NonNull AppCompatImageView ivCancel,
      @NonNull AppCompatImageView ivGallery, @NonNull AppCompatImageView ivRecord,
      @NonNull AppCompatImageView ivSend, @NonNull RecyclerView rcvChat,
      @NonNull RelativeLayout rlImages, @NonNull TextView txt1, @NonNull TextView txt2,
      @NonNull TextView txt3, @NonNull AppCompatTextView txtYou) {
    this.rootView = rootView;
    this.ScrollChat = ScrollChat;
    this.bottomLayout = bottomLayout;
    this.edtMessage = edtMessage;
    this.img1 = img1;
    this.img2 = img2;
    this.ivCancel = ivCancel;
    this.ivGallery = ivGallery;
    this.ivRecord = ivRecord;
    this.ivSend = ivSend;
    this.rcvChat = rcvChat;
    this.rlImages = rlImages;
    this.txt1 = txt1;
    this.txt2 = txt2;
    this.txt3 = txt3;
    this.txtYou = txtYou;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySwipeChatBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySwipeChatBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_swipe_chat, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySwipeChatBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ScrollChat;
      ScrollView ScrollChat = ViewBindings.findChildViewById(rootView, id);
      if (ScrollChat == null) {
        break missingId;
      }

      id = R.id.bottomLayout;
      ConstraintLayout bottomLayout = ViewBindings.findChildViewById(rootView, id);
      if (bottomLayout == null) {
        break missingId;
      }

      id = R.id.edtMessage;
      TextInputEditText edtMessage = ViewBindings.findChildViewById(rootView, id);
      if (edtMessage == null) {
        break missingId;
      }

      id = R.id.img1;
      RoundedImageView img1 = ViewBindings.findChildViewById(rootView, id);
      if (img1 == null) {
        break missingId;
      }

      id = R.id.img2;
      RoundedImageView img2 = ViewBindings.findChildViewById(rootView, id);
      if (img2 == null) {
        break missingId;
      }

      id = R.id.ivCancel;
      AppCompatImageView ivCancel = ViewBindings.findChildViewById(rootView, id);
      if (ivCancel == null) {
        break missingId;
      }

      id = R.id.ivGallery;
      AppCompatImageView ivGallery = ViewBindings.findChildViewById(rootView, id);
      if (ivGallery == null) {
        break missingId;
      }

      id = R.id.ivRecord;
      AppCompatImageView ivRecord = ViewBindings.findChildViewById(rootView, id);
      if (ivRecord == null) {
        break missingId;
      }

      id = R.id.ivSend;
      AppCompatImageView ivSend = ViewBindings.findChildViewById(rootView, id);
      if (ivSend == null) {
        break missingId;
      }

      id = R.id.rcvChat;
      RecyclerView rcvChat = ViewBindings.findChildViewById(rootView, id);
      if (rcvChat == null) {
        break missingId;
      }

      id = R.id.rlImages;
      RelativeLayout rlImages = ViewBindings.findChildViewById(rootView, id);
      if (rlImages == null) {
        break missingId;
      }

      id = R.id.txt1;
      TextView txt1 = ViewBindings.findChildViewById(rootView, id);
      if (txt1 == null) {
        break missingId;
      }

      id = R.id.txt2;
      TextView txt2 = ViewBindings.findChildViewById(rootView, id);
      if (txt2 == null) {
        break missingId;
      }

      id = R.id.txt3;
      TextView txt3 = ViewBindings.findChildViewById(rootView, id);
      if (txt3 == null) {
        break missingId;
      }

      id = R.id.txtYou;
      AppCompatTextView txtYou = ViewBindings.findChildViewById(rootView, id);
      if (txtYou == null) {
        break missingId;
      }

      return new ActivitySwipeChatBinding((ConstraintLayout) rootView, ScrollChat, bottomLayout,
          edtMessage, img1, img2, ivCancel, ivGallery, ivRecord, ivSend, rcvChat, rlImages, txt1,
          txt2, txt3, txtYou);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
