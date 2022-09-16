// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import me.relex.circleindicator.CircleIndicator3;

public final class ItemSwipingCardBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout bottomLayout;

  @NonNull
  public final AppCompatImageView btnDislike;

  @NonNull
  public final AppCompatImageView btnFwd;

  @NonNull
  public final AppCompatImageView btnLike;

  @NonNull
  public final AppCompatImageView btnRevert;

  @NonNull
  public final AppCompatImageView btnSuperlike;

  @NonNull
  public final RoundedImageView img1;

  @NonNull
  public final RoundedImageView img2;

  @NonNull
  public final AppCompatImageView imgCheck;

  @NonNull
  public final AppCompatImageView imgSuperLike;

  @NonNull
  public final ConstraintLayout layoutConnectors;

  @NonNull
  public final CircleIndicator3 layoutIndicator;

  @NonNull
  public final ViewPager2 pagerImages;

  @NonNull
  public final RelativeLayout rlTitle;

  @NonNull
  public final View tipAnchor;

  @NonNull
  public final TextView txtAddress;

  @NonNull
  public final TextView txtCount;

  @NonNull
  public final TextView txtDaterName;

  @NonNull
  public final AppCompatTextView txtFwd;

  @NonNull
  public final TextView txtOccupation;

  @NonNull
  public final TextView txtSuperCount;

  @NonNull
  public final View viewLeft;

  @NonNull
  public final View viewRight;

  private ItemSwipingCardBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout bottomLayout, @NonNull AppCompatImageView btnDislike,
      @NonNull AppCompatImageView btnFwd, @NonNull AppCompatImageView btnLike,
      @NonNull AppCompatImageView btnRevert, @NonNull AppCompatImageView btnSuperlike,
      @NonNull RoundedImageView img1, @NonNull RoundedImageView img2,
      @NonNull AppCompatImageView imgCheck, @NonNull AppCompatImageView imgSuperLike,
      @NonNull ConstraintLayout layoutConnectors, @NonNull CircleIndicator3 layoutIndicator,
      @NonNull ViewPager2 pagerImages, @NonNull RelativeLayout rlTitle, @NonNull View tipAnchor,
      @NonNull TextView txtAddress, @NonNull TextView txtCount, @NonNull TextView txtDaterName,
      @NonNull AppCompatTextView txtFwd, @NonNull TextView txtOccupation,
      @NonNull TextView txtSuperCount, @NonNull View viewLeft, @NonNull View viewRight) {
    this.rootView = rootView;
    this.bottomLayout = bottomLayout;
    this.btnDislike = btnDislike;
    this.btnFwd = btnFwd;
    this.btnLike = btnLike;
    this.btnRevert = btnRevert;
    this.btnSuperlike = btnSuperlike;
    this.img1 = img1;
    this.img2 = img2;
    this.imgCheck = imgCheck;
    this.imgSuperLike = imgSuperLike;
    this.layoutConnectors = layoutConnectors;
    this.layoutIndicator = layoutIndicator;
    this.pagerImages = pagerImages;
    this.rlTitle = rlTitle;
    this.tipAnchor = tipAnchor;
    this.txtAddress = txtAddress;
    this.txtCount = txtCount;
    this.txtDaterName = txtDaterName;
    this.txtFwd = txtFwd;
    this.txtOccupation = txtOccupation;
    this.txtSuperCount = txtSuperCount;
    this.viewLeft = viewLeft;
    this.viewRight = viewRight;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemSwipingCardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemSwipingCardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_swiping_card, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemSwipingCardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottomLayout;
      ConstraintLayout bottomLayout = ViewBindings.findChildViewById(rootView, id);
      if (bottomLayout == null) {
        break missingId;
      }

      id = R.id.btnDislike;
      AppCompatImageView btnDislike = ViewBindings.findChildViewById(rootView, id);
      if (btnDislike == null) {
        break missingId;
      }

      id = R.id.btnFwd;
      AppCompatImageView btnFwd = ViewBindings.findChildViewById(rootView, id);
      if (btnFwd == null) {
        break missingId;
      }

      id = R.id.btnLike;
      AppCompatImageView btnLike = ViewBindings.findChildViewById(rootView, id);
      if (btnLike == null) {
        break missingId;
      }

      id = R.id.btnRevert;
      AppCompatImageView btnRevert = ViewBindings.findChildViewById(rootView, id);
      if (btnRevert == null) {
        break missingId;
      }

      id = R.id.btnSuperlike;
      AppCompatImageView btnSuperlike = ViewBindings.findChildViewById(rootView, id);
      if (btnSuperlike == null) {
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

      id = R.id.imgCheck;
      AppCompatImageView imgCheck = ViewBindings.findChildViewById(rootView, id);
      if (imgCheck == null) {
        break missingId;
      }

      id = R.id.imgSuperLike;
      AppCompatImageView imgSuperLike = ViewBindings.findChildViewById(rootView, id);
      if (imgSuperLike == null) {
        break missingId;
      }

      id = R.id.layoutConnectors;
      ConstraintLayout layoutConnectors = ViewBindings.findChildViewById(rootView, id);
      if (layoutConnectors == null) {
        break missingId;
      }

      id = R.id.layoutIndicator;
      CircleIndicator3 layoutIndicator = ViewBindings.findChildViewById(rootView, id);
      if (layoutIndicator == null) {
        break missingId;
      }

      id = R.id.pagerImages;
      ViewPager2 pagerImages = ViewBindings.findChildViewById(rootView, id);
      if (pagerImages == null) {
        break missingId;
      }

      id = R.id.rlTitle;
      RelativeLayout rlTitle = ViewBindings.findChildViewById(rootView, id);
      if (rlTitle == null) {
        break missingId;
      }

      id = R.id.tipAnchor;
      View tipAnchor = ViewBindings.findChildViewById(rootView, id);
      if (tipAnchor == null) {
        break missingId;
      }

      id = R.id.txtAddress;
      TextView txtAddress = ViewBindings.findChildViewById(rootView, id);
      if (txtAddress == null) {
        break missingId;
      }

      id = R.id.txtCount;
      TextView txtCount = ViewBindings.findChildViewById(rootView, id);
      if (txtCount == null) {
        break missingId;
      }

      id = R.id.txtDaterName;
      TextView txtDaterName = ViewBindings.findChildViewById(rootView, id);
      if (txtDaterName == null) {
        break missingId;
      }

      id = R.id.txtFwd;
      AppCompatTextView txtFwd = ViewBindings.findChildViewById(rootView, id);
      if (txtFwd == null) {
        break missingId;
      }

      id = R.id.txtOccupation;
      TextView txtOccupation = ViewBindings.findChildViewById(rootView, id);
      if (txtOccupation == null) {
        break missingId;
      }

      id = R.id.txtSuperCount;
      TextView txtSuperCount = ViewBindings.findChildViewById(rootView, id);
      if (txtSuperCount == null) {
        break missingId;
      }

      id = R.id.viewLeft;
      View viewLeft = ViewBindings.findChildViewById(rootView, id);
      if (viewLeft == null) {
        break missingId;
      }

      id = R.id.viewRight;
      View viewRight = ViewBindings.findChildViewById(rootView, id);
      if (viewRight == null) {
        break missingId;
      }

      return new ItemSwipingCardBinding((ConstraintLayout) rootView, bottomLayout, btnDislike,
          btnFwd, btnLike, btnRevert, btnSuperlike, img1, img2, imgCheck, imgSuperLike,
          layoutConnectors, layoutIndicator, pagerImages, rlTitle, tipAnchor, txtAddress, txtCount,
          txtDaterName, txtFwd, txtOccupation, txtSuperCount, viewLeft, viewRight);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
