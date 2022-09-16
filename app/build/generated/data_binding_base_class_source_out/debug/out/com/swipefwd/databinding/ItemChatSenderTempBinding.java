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
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemChatSenderTempBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageView imgSender;

  @NonNull
  public final Group imgSenderGroup;

  @NonNull
  public final RoundedImageView imgSenderMsg;

  @NonNull
  public final ConstraintLayout layoutSenderText;

  @NonNull
  public final Group textSenderGroup;

  @NonNull
  public final TextView txtMsgSender;

  @NonNull
  public final TextView txtSenderImageTime;

  @NonNull
  public final TextView txtSenderTextTime;

  private ItemChatSenderTempBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageView imgSender, @NonNull Group imgSenderGroup,
      @NonNull RoundedImageView imgSenderMsg, @NonNull ConstraintLayout layoutSenderText,
      @NonNull Group textSenderGroup, @NonNull TextView txtMsgSender,
      @NonNull TextView txtSenderImageTime, @NonNull TextView txtSenderTextTime) {
    this.rootView = rootView;
    this.imgSender = imgSender;
    this.imgSenderGroup = imgSenderGroup;
    this.imgSenderMsg = imgSenderMsg;
    this.layoutSenderText = layoutSenderText;
    this.textSenderGroup = textSenderGroup;
    this.txtMsgSender = txtMsgSender;
    this.txtSenderImageTime = txtSenderImageTime;
    this.txtSenderTextTime = txtSenderTextTime;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemChatSenderTempBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemChatSenderTempBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_chat_sender_temp, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemChatSenderTempBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgSender;
      AppCompatImageView imgSender = ViewBindings.findChildViewById(rootView, id);
      if (imgSender == null) {
        break missingId;
      }

      id = R.id.imgSenderGroup;
      Group imgSenderGroup = ViewBindings.findChildViewById(rootView, id);
      if (imgSenderGroup == null) {
        break missingId;
      }

      id = R.id.imgSenderMsg;
      RoundedImageView imgSenderMsg = ViewBindings.findChildViewById(rootView, id);
      if (imgSenderMsg == null) {
        break missingId;
      }

      id = R.id.layoutSenderText;
      ConstraintLayout layoutSenderText = ViewBindings.findChildViewById(rootView, id);
      if (layoutSenderText == null) {
        break missingId;
      }

      id = R.id.textSenderGroup;
      Group textSenderGroup = ViewBindings.findChildViewById(rootView, id);
      if (textSenderGroup == null) {
        break missingId;
      }

      id = R.id.txtMsgSender;
      TextView txtMsgSender = ViewBindings.findChildViewById(rootView, id);
      if (txtMsgSender == null) {
        break missingId;
      }

      id = R.id.txtSenderImageTime;
      TextView txtSenderImageTime = ViewBindings.findChildViewById(rootView, id);
      if (txtSenderImageTime == null) {
        break missingId;
      }

      id = R.id.txtSenderTextTime;
      TextView txtSenderTextTime = ViewBindings.findChildViewById(rootView, id);
      if (txtSenderTextTime == null) {
        break missingId;
      }

      return new ItemChatSenderTempBinding((ConstraintLayout) rootView, imgSender, imgSenderGroup,
          imgSenderMsg, layoutSenderText, textSenderGroup, txtMsgSender, txtSenderImageTime,
          txtSenderTextTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
