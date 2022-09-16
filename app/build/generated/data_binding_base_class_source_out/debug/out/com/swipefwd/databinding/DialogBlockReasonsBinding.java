// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogBlockReasonsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnBlockNext;

  @NonNull
  public final MaterialCardView cardBottom;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final LinearLayout layoutConfirm;

  @NonNull
  public final RecyclerView rvBlockReason;

  private DialogBlockReasonsBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnBlockNext, @NonNull MaterialCardView cardBottom,
      @NonNull AppCompatImageView imgDialogGradient, @NonNull LinearLayout layoutConfirm,
      @NonNull RecyclerView rvBlockReason) {
    this.rootView = rootView;
    this.btnBlockNext = btnBlockNext;
    this.cardBottom = cardBottom;
    this.imgDialogGradient = imgDialogGradient;
    this.layoutConfirm = layoutConfirm;
    this.rvBlockReason = rvBlockReason;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogBlockReasonsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogBlockReasonsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_block_reasons, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogBlockReasonsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnBlockNext;
      MaterialButton btnBlockNext = ViewBindings.findChildViewById(rootView, id);
      if (btnBlockNext == null) {
        break missingId;
      }

      id = R.id.cardBottom;
      MaterialCardView cardBottom = ViewBindings.findChildViewById(rootView, id);
      if (cardBottom == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      id = R.id.layoutConfirm;
      LinearLayout layoutConfirm = ViewBindings.findChildViewById(rootView, id);
      if (layoutConfirm == null) {
        break missingId;
      }

      id = R.id.rvBlockReason;
      RecyclerView rvBlockReason = ViewBindings.findChildViewById(rootView, id);
      if (rvBlockReason == null) {
        break missingId;
      }

      return new DialogBlockReasonsBinding((ConstraintLayout) rootView, btnBlockNext, cardBottom,
          imgDialogGradient, layoutConfirm, rvBlockReason);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
