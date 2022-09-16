// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.devlomi.record_view.RecordButton;
import com.devlomi.record_view.RecordView;
import com.google.android.material.textfield.TextInputEditText;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityChatBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ScrollView ScrollChat;

  @NonNull
  public final ConstraintLayout bottomLayout;

  @NonNull
  public final TextInputEditText edtMessage;

  @NonNull
  public final RoundedImageView imgUser;

  @NonNull
  public final AppCompatImageView ivBack;

  @NonNull
  public final AppCompatImageView ivGallery;

  @NonNull
  public final AppCompatImageView ivOptions;

  @NonNull
  public final AppCompatImageView ivRecord;

  @NonNull
  public final AppCompatImageView ivSend;

  @NonNull
  public final AppCompatImageView ivVideo;

  @NonNull
  public final LinearLayout llImage;

  @NonNull
  public final RecyclerView rcvChat;

  @NonNull
  public final RecordButton recordButton;

  @NonNull
  public final RecordView recordView;

  @NonNull
  public final ItemChatSenderTempBinding senderImageLayout;

  @NonNull
  public final TextView txtDate;

  @NonNull
  public final TextView txtUserName;

  private ActivityChatBinding(@NonNull ConstraintLayout rootView, @NonNull ScrollView ScrollChat,
      @NonNull ConstraintLayout bottomLayout, @NonNull TextInputEditText edtMessage,
      @NonNull RoundedImageView imgUser, @NonNull AppCompatImageView ivBack,
      @NonNull AppCompatImageView ivGallery, @NonNull AppCompatImageView ivOptions,
      @NonNull AppCompatImageView ivRecord, @NonNull AppCompatImageView ivSend,
      @NonNull AppCompatImageView ivVideo, @NonNull LinearLayout llImage,
      @NonNull RecyclerView rcvChat, @NonNull RecordButton recordButton,
      @NonNull RecordView recordView, @NonNull ItemChatSenderTempBinding senderImageLayout,
      @NonNull TextView txtDate, @NonNull TextView txtUserName) {
    this.rootView = rootView;
    this.ScrollChat = ScrollChat;
    this.bottomLayout = bottomLayout;
    this.edtMessage = edtMessage;
    this.imgUser = imgUser;
    this.ivBack = ivBack;
    this.ivGallery = ivGallery;
    this.ivOptions = ivOptions;
    this.ivRecord = ivRecord;
    this.ivSend = ivSend;
    this.ivVideo = ivVideo;
    this.llImage = llImage;
    this.rcvChat = rcvChat;
    this.recordButton = recordButton;
    this.recordView = recordView;
    this.senderImageLayout = senderImageLayout;
    this.txtDate = txtDate;
    this.txtUserName = txtUserName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChatBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChatBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_chat, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChatBinding bind(@NonNull View rootView) {
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

      id = R.id.imgUser;
      RoundedImageView imgUser = ViewBindings.findChildViewById(rootView, id);
      if (imgUser == null) {
        break missingId;
      }

      id = R.id.ivBack;
      AppCompatImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.ivGallery;
      AppCompatImageView ivGallery = ViewBindings.findChildViewById(rootView, id);
      if (ivGallery == null) {
        break missingId;
      }

      id = R.id.ivOptions;
      AppCompatImageView ivOptions = ViewBindings.findChildViewById(rootView, id);
      if (ivOptions == null) {
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

      id = R.id.ivVideo;
      AppCompatImageView ivVideo = ViewBindings.findChildViewById(rootView, id);
      if (ivVideo == null) {
        break missingId;
      }

      id = R.id.llImage;
      LinearLayout llImage = ViewBindings.findChildViewById(rootView, id);
      if (llImage == null) {
        break missingId;
      }

      id = R.id.rcvChat;
      RecyclerView rcvChat = ViewBindings.findChildViewById(rootView, id);
      if (rcvChat == null) {
        break missingId;
      }

      id = R.id.record_button;
      RecordButton recordButton = ViewBindings.findChildViewById(rootView, id);
      if (recordButton == null) {
        break missingId;
      }

      id = R.id.record_view;
      RecordView recordView = ViewBindings.findChildViewById(rootView, id);
      if (recordView == null) {
        break missingId;
      }

      id = R.id.senderImageLayout;
      View senderImageLayout = ViewBindings.findChildViewById(rootView, id);
      if (senderImageLayout == null) {
        break missingId;
      }
      ItemChatSenderTempBinding binding_senderImageLayout = ItemChatSenderTempBinding.bind(senderImageLayout);

      id = R.id.txtDate;
      TextView txtDate = ViewBindings.findChildViewById(rootView, id);
      if (txtDate == null) {
        break missingId;
      }

      id = R.id.txtUserName;
      TextView txtUserName = ViewBindings.findChildViewById(rootView, id);
      if (txtUserName == null) {
        break missingId;
      }

      return new ActivityChatBinding((ConstraintLayout) rootView, ScrollChat, bottomLayout,
          edtMessage, imgUser, ivBack, ivGallery, ivOptions, ivRecord, ivSend, ivVideo, llImage,
          rcvChat, recordButton, recordView, binding_senderImageLayout, txtDate, txtUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}