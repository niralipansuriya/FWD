// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemAudioOutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RoundedImageView imgReceiver;

  @NonNull
  public final RoundedImageView imgReceiverMsg;

  @NonNull
  public final Group imgSenderGroup;

  @NonNull
  public final ConstraintLayout layoutReceive;

  @NonNull
  public final LinearLayout playAudioLay;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final SeekBar seekbar;

  @NonNull
  public final Group textReceiverGroup;

  @NonNull
  public final ImageView thumbnailVideoDownload;

  @NonNull
  public final ImageView thumbnailVideoIcon;

  @NonNull
  public final TextView txtAudioTime;

  @NonNull
  public final TextView txtReceiverImageTime;

  @NonNull
  public final TextView txtReceiverTextTime;

  private ItemAudioOutBinding(@NonNull ConstraintLayout rootView,
      @NonNull RoundedImageView imgReceiver, @NonNull RoundedImageView imgReceiverMsg,
      @NonNull Group imgSenderGroup, @NonNull ConstraintLayout layoutReceive,
      @NonNull LinearLayout playAudioLay, @NonNull ProgressBar progressBar,
      @NonNull SeekBar seekbar, @NonNull Group textReceiverGroup,
      @NonNull ImageView thumbnailVideoDownload, @NonNull ImageView thumbnailVideoIcon,
      @NonNull TextView txtAudioTime, @NonNull TextView txtReceiverImageTime,
      @NonNull TextView txtReceiverTextTime) {
    this.rootView = rootView;
    this.imgReceiver = imgReceiver;
    this.imgReceiverMsg = imgReceiverMsg;
    this.imgSenderGroup = imgSenderGroup;
    this.layoutReceive = layoutReceive;
    this.playAudioLay = playAudioLay;
    this.progressBar = progressBar;
    this.seekbar = seekbar;
    this.textReceiverGroup = textReceiverGroup;
    this.thumbnailVideoDownload = thumbnailVideoDownload;
    this.thumbnailVideoIcon = thumbnailVideoIcon;
    this.txtAudioTime = txtAudioTime;
    this.txtReceiverImageTime = txtReceiverImageTime;
    this.txtReceiverTextTime = txtReceiverTextTime;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemAudioOutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemAudioOutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_audio_out, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemAudioOutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgReceiver;
      RoundedImageView imgReceiver = ViewBindings.findChildViewById(rootView, id);
      if (imgReceiver == null) {
        break missingId;
      }

      id = R.id.imgReceiverMsg;
      RoundedImageView imgReceiverMsg = ViewBindings.findChildViewById(rootView, id);
      if (imgReceiverMsg == null) {
        break missingId;
      }

      id = R.id.imgSenderGroup;
      Group imgSenderGroup = ViewBindings.findChildViewById(rootView, id);
      if (imgSenderGroup == null) {
        break missingId;
      }

      id = R.id.layoutReceive;
      ConstraintLayout layoutReceive = ViewBindings.findChildViewById(rootView, id);
      if (layoutReceive == null) {
        break missingId;
      }

      id = R.id.play_audio_lay;
      LinearLayout playAudioLay = ViewBindings.findChildViewById(rootView, id);
      if (playAudioLay == null) {
        break missingId;
      }

      id = R.id.progress_bar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.seekbar;
      SeekBar seekbar = ViewBindings.findChildViewById(rootView, id);
      if (seekbar == null) {
        break missingId;
      }

      id = R.id.textReceiverGroup;
      Group textReceiverGroup = ViewBindings.findChildViewById(rootView, id);
      if (textReceiverGroup == null) {
        break missingId;
      }

      id = R.id.thumbnail_video_download;
      ImageView thumbnailVideoDownload = ViewBindings.findChildViewById(rootView, id);
      if (thumbnailVideoDownload == null) {
        break missingId;
      }

      id = R.id.thumbnail_video_icon;
      ImageView thumbnailVideoIcon = ViewBindings.findChildViewById(rootView, id);
      if (thumbnailVideoIcon == null) {
        break missingId;
      }

      id = R.id.txt_audio_time;
      TextView txtAudioTime = ViewBindings.findChildViewById(rootView, id);
      if (txtAudioTime == null) {
        break missingId;
      }

      id = R.id.txtReceiverImageTime;
      TextView txtReceiverImageTime = ViewBindings.findChildViewById(rootView, id);
      if (txtReceiverImageTime == null) {
        break missingId;
      }

      id = R.id.txtReceiverTextTime;
      TextView txtReceiverTextTime = ViewBindings.findChildViewById(rootView, id);
      if (txtReceiverTextTime == null) {
        break missingId;
      }

      return new ItemAudioOutBinding((ConstraintLayout) rootView, imgReceiver, imgReceiverMsg,
          imgSenderGroup, layoutReceive, playAudioLay, progressBar, seekbar, textReceiverGroup,
          thumbnailVideoDownload, thumbnailVideoIcon, txtAudioTime, txtReceiverImageTime,
          txtReceiverTextTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
