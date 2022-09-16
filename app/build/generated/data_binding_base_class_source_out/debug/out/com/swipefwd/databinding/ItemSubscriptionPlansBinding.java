// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemSubscriptionPlansBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView txtDesc;

  @NonNull
  public final TextView txtFreemiumOffer;

  @NonNull
  public final TextView txtPremierOffer;

  private ItemSubscriptionPlansBinding(@NonNull LinearLayout rootView, @NonNull TextView txtDesc,
      @NonNull TextView txtFreemiumOffer, @NonNull TextView txtPremierOffer) {
    this.rootView = rootView;
    this.txtDesc = txtDesc;
    this.txtFreemiumOffer = txtFreemiumOffer;
    this.txtPremierOffer = txtPremierOffer;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemSubscriptionPlansBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemSubscriptionPlansBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_subscription_plans, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemSubscriptionPlansBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.txtDesc;
      TextView txtDesc = ViewBindings.findChildViewById(rootView, id);
      if (txtDesc == null) {
        break missingId;
      }

      id = R.id.txtFreemiumOffer;
      TextView txtFreemiumOffer = ViewBindings.findChildViewById(rootView, id);
      if (txtFreemiumOffer == null) {
        break missingId;
      }

      id = R.id.txtPremierOffer;
      TextView txtPremierOffer = ViewBindings.findChildViewById(rootView, id);
      if (txtPremierOffer == null) {
        break missingId;
      }

      return new ItemSubscriptionPlansBinding((LinearLayout) rootView, txtDesc, txtFreemiumOffer,
          txtPremierOffer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
