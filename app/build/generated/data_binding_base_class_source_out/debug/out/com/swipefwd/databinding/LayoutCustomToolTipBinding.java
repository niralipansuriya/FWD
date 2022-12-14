// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LayoutCustomToolTipBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout bottomView;

  @NonNull
  public final MaterialButton btnTipGotIt;

  @NonNull
  public final ImageView imageTooltipBottom;

  @NonNull
  public final ImageView imageTooltipTop;

  @NonNull
  public final AppCompatImageView imgToolTip;

  @NonNull
  public final LinearLayout layoutMain;

  @NonNull
  public final LinearLayout topView;

  @NonNull
  public final AppCompatTextView txtTipDesc;

  @NonNull
  public final AppCompatTextView txtTipHeader;

  private LayoutCustomToolTipBinding(@NonNull LinearLayout rootView,
      @NonNull LinearLayout bottomView, @NonNull MaterialButton btnTipGotIt,
      @NonNull ImageView imageTooltipBottom, @NonNull ImageView imageTooltipTop,
      @NonNull AppCompatImageView imgToolTip, @NonNull LinearLayout layoutMain,
      @NonNull LinearLayout topView, @NonNull AppCompatTextView txtTipDesc,
      @NonNull AppCompatTextView txtTipHeader) {
    this.rootView = rootView;
    this.bottomView = bottomView;
    this.btnTipGotIt = btnTipGotIt;
    this.imageTooltipBottom = imageTooltipBottom;
    this.imageTooltipTop = imageTooltipTop;
    this.imgToolTip = imgToolTip;
    this.layoutMain = layoutMain;
    this.topView = topView;
    this.txtTipDesc = txtTipDesc;
    this.txtTipHeader = txtTipHeader;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LayoutCustomToolTipBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LayoutCustomToolTipBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout_custom_tool_tip, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LayoutCustomToolTipBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomView;
      LinearLayout bottomView = ViewBindings.findChildViewById(rootView, id);
      if (bottomView == null) {
        break missingId;
      }

      id = R.id.btnTipGotIt;
      MaterialButton btnTipGotIt = ViewBindings.findChildViewById(rootView, id);
      if (btnTipGotIt == null) {
        break missingId;
      }

      id = R.id.image_tooltipBottom;
      ImageView imageTooltipBottom = ViewBindings.findChildViewById(rootView, id);
      if (imageTooltipBottom == null) {
        break missingId;
      }

      id = R.id.image_tooltipTop;
      ImageView imageTooltipTop = ViewBindings.findChildViewById(rootView, id);
      if (imageTooltipTop == null) {
        break missingId;
      }

      id = R.id.imgToolTip;
      AppCompatImageView imgToolTip = ViewBindings.findChildViewById(rootView, id);
      if (imgToolTip == null) {
        break missingId;
      }

      LinearLayout layoutMain = (LinearLayout) rootView;

      id = R.id.topView;
      LinearLayout topView = ViewBindings.findChildViewById(rootView, id);
      if (topView == null) {
        break missingId;
      }

      id = R.id.txtTipDesc;
      AppCompatTextView txtTipDesc = ViewBindings.findChildViewById(rootView, id);
      if (txtTipDesc == null) {
        break missingId;
      }

      id = R.id.txtTipHeader;
      AppCompatTextView txtTipHeader = ViewBindings.findChildViewById(rootView, id);
      if (txtTipHeader == null) {
        break missingId;
      }

      return new LayoutCustomToolTipBinding((LinearLayout) rootView, bottomView, btnTipGotIt,
          imageTooltipBottom, imageTooltipTop, imgToolTip, layoutMain, topView, txtTipDesc,
          txtTipHeader);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
