// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogCountryCodeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView_;

  @NonNull
  public final MaterialCardView cardBottom;

  @NonNull
  public final EditText edtSearch;

  @NonNull
  public final AppCompatImageView imgCancel;

  @NonNull
  public final AppCompatImageView imgDialogGradient;

  @NonNull
  public final RelativeLayout rootView;

  @NonNull
  public final RecyclerView rvCountry;

  @NonNull
  public final View view;

  @NonNull
  public final View viewBottom;

  private DialogCountryCodeBinding(@NonNull RelativeLayout rootView_,
      @NonNull MaterialCardView cardBottom, @NonNull EditText edtSearch,
      @NonNull AppCompatImageView imgCancel, @NonNull AppCompatImageView imgDialogGradient,
      @NonNull RelativeLayout rootView, @NonNull RecyclerView rvCountry, @NonNull View view,
      @NonNull View viewBottom) {
    this.rootView_ = rootView_;
    this.cardBottom = cardBottom;
    this.edtSearch = edtSearch;
    this.imgCancel = imgCancel;
    this.imgDialogGradient = imgDialogGradient;
    this.rootView = rootView;
    this.rvCountry = rvCountry;
    this.view = view;
    this.viewBottom = viewBottom;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView_;
  }

  @NonNull
  public static DialogCountryCodeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogCountryCodeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_country_code, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogCountryCodeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardBottom;
      MaterialCardView cardBottom = ViewBindings.findChildViewById(rootView, id);
      if (cardBottom == null) {
        break missingId;
      }

      id = R.id.edtSearch;
      EditText edtSearch = ViewBindings.findChildViewById(rootView, id);
      if (edtSearch == null) {
        break missingId;
      }

      id = R.id.imgCancel;
      AppCompatImageView imgCancel = ViewBindings.findChildViewById(rootView, id);
      if (imgCancel == null) {
        break missingId;
      }

      id = R.id.imgDialogGradient;
      AppCompatImageView imgDialogGradient = ViewBindings.findChildViewById(rootView, id);
      if (imgDialogGradient == null) {
        break missingId;
      }

      RelativeLayout rootView_ = (RelativeLayout) rootView;

      id = R.id.rvCountry;
      RecyclerView rvCountry = ViewBindings.findChildViewById(rootView, id);
      if (rvCountry == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);
      if (view == null) {
        break missingId;
      }

      id = R.id.viewBottom;
      View viewBottom = ViewBindings.findChildViewById(rootView, id);
      if (viewBottom == null) {
        break missingId;
      }

      return new DialogCountryCodeBinding((RelativeLayout) rootView, cardBottom, edtSearch,
          imgCancel, imgDialogGradient, rootView_, rvCountry, view, viewBottom);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
