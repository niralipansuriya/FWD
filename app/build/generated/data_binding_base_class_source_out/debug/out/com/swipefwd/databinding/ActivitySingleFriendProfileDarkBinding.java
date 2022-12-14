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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textview.MaterialTextView;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import com.yuyakaido.android.cardstackview.CardStackView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySingleFriendProfileDarkBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardStackView cardStackView;

  @NonNull
  public final ConstraintLayout constraintMain;

  @NonNull
  public final AppCompatImageView imgChat;

  @NonNull
  public final AppCompatImageView imgDislike;

  @NonNull
  public final AppCompatImageView imgHome;

  @NonNull
  public final AppCompatImageView imgLike;

  @NonNull
  public final MaterialTextView ivLogo;

  @NonNull
  public final RoundedImageView ivRecommendUserImage;

  @NonNull
  public final RelativeLayout rlConnection;

  @NonNull
  public final TextView txtBadge;

  @NonNull
  public final TextView txtRecommendUserName;

  private ActivitySingleFriendProfileDarkBinding(@NonNull ConstraintLayout rootView,
      @NonNull CardStackView cardStackView, @NonNull ConstraintLayout constraintMain,
      @NonNull AppCompatImageView imgChat, @NonNull AppCompatImageView imgDislike,
      @NonNull AppCompatImageView imgHome, @NonNull AppCompatImageView imgLike,
      @NonNull MaterialTextView ivLogo, @NonNull RoundedImageView ivRecommendUserImage,
      @NonNull RelativeLayout rlConnection, @NonNull TextView txtBadge,
      @NonNull TextView txtRecommendUserName) {
    this.rootView = rootView;
    this.cardStackView = cardStackView;
    this.constraintMain = constraintMain;
    this.imgChat = imgChat;
    this.imgDislike = imgDislike;
    this.imgHome = imgHome;
    this.imgLike = imgLike;
    this.ivLogo = ivLogo;
    this.ivRecommendUserImage = ivRecommendUserImage;
    this.rlConnection = rlConnection;
    this.txtBadge = txtBadge;
    this.txtRecommendUserName = txtRecommendUserName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySingleFriendProfileDarkBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySingleFriendProfileDarkBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_single_friend_profile_dark, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySingleFriendProfileDarkBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardStackView;
      CardStackView cardStackView = ViewBindings.findChildViewById(rootView, id);
      if (cardStackView == null) {
        break missingId;
      }

      ConstraintLayout constraintMain = (ConstraintLayout) rootView;

      id = R.id.imgChat;
      AppCompatImageView imgChat = ViewBindings.findChildViewById(rootView, id);
      if (imgChat == null) {
        break missingId;
      }

      id = R.id.imgDislike;
      AppCompatImageView imgDislike = ViewBindings.findChildViewById(rootView, id);
      if (imgDislike == null) {
        break missingId;
      }

      id = R.id.imgHome;
      AppCompatImageView imgHome = ViewBindings.findChildViewById(rootView, id);
      if (imgHome == null) {
        break missingId;
      }

      id = R.id.imgLike;
      AppCompatImageView imgLike = ViewBindings.findChildViewById(rootView, id);
      if (imgLike == null) {
        break missingId;
      }

      id = R.id.ivLogo;
      MaterialTextView ivLogo = ViewBindings.findChildViewById(rootView, id);
      if (ivLogo == null) {
        break missingId;
      }

      id = R.id.ivRecommendUserImage;
      RoundedImageView ivRecommendUserImage = ViewBindings.findChildViewById(rootView, id);
      if (ivRecommendUserImage == null) {
        break missingId;
      }

      id = R.id.rlConnection;
      RelativeLayout rlConnection = ViewBindings.findChildViewById(rootView, id);
      if (rlConnection == null) {
        break missingId;
      }

      id = R.id.txtBadge;
      TextView txtBadge = ViewBindings.findChildViewById(rootView, id);
      if (txtBadge == null) {
        break missingId;
      }

      id = R.id.txtRecommendUserName;
      TextView txtRecommendUserName = ViewBindings.findChildViewById(rootView, id);
      if (txtRecommendUserName == null) {
        break missingId;
      }

      return new ActivitySingleFriendProfileDarkBinding((ConstraintLayout) rootView, cardStackView,
          constraintMain, imgChat, imgDislike, imgHome, imgLike, ivLogo, ivRecommendUserImage,
          rlConnection, txtBadge, txtRecommendUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
