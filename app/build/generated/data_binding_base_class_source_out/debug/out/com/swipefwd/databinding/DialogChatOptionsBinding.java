// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogChatOptionsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnBlock;

  @NonNull
  public final MaterialButton btnUnmatch;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final AppCompatImageView imgOptions;

  private DialogChatOptionsBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnBlock, @NonNull MaterialButton btnUnmatch,
      @NonNull AppCompatImageView imgDialogGradient, @NonNull AppCompatImageView imgOptions) {
    this.rootView = rootView;
    this.btnBlock = btnBlock;
    this.btnUnmatch = btnUnmatch;
    this.imgDialogGradient = imgDialogGradient;
    this.imgOptions = imgOptions;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogChatOptionsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogChatOptionsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_chat_options, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogChatOptionsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBlock;
      MaterialButton btnBlock = ViewBindings.findChildViewById(rootView, id);
      if (btnBlock == null) {
        break missingId;
      }

      id = R.id.btnUnmatch;
      MaterialButton btnUnmatch = ViewBindings.findChildViewById(rootView, id);
      if (btnUnmatch == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      id = R.id.imgOptions;
      AppCompatImageView imgOptions = ViewBindings.findChildViewById(rootView, id);
      if (imgOptions == null) {
        break missingId;
      }

      return new DialogChatOptionsBinding((ConstraintLayout) rootView, btnBlock, btnUnmatch,
          imgDialogGradient, imgOptions);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}