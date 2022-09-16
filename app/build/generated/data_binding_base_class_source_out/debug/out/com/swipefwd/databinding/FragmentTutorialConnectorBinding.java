// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentTutorialConnectorBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppCompatTextView txtHome;

  @NonNull
  public final AppCompatTextView txtOnBoarding;

  @NonNull
  public final AppCompatTextView txtSwiping;

  private FragmentTutorialConnectorBinding(@NonNull LinearLayout rootView,
      @NonNull AppCompatTextView txtHome, @NonNull AppCompatTextView txtOnBoarding,
      @NonNull AppCompatTextView txtSwiping) {
    this.rootView = rootView;
    this.txtHome = txtHome;
    this.txtOnBoarding = txtOnBoarding;
    this.txtSwiping = txtSwiping;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentTutorialConnectorBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentTutorialConnectorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_tutorial_connector, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentTutorialConnectorBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.txtHome;
      AppCompatTextView txtHome = ViewBindings.findChildViewById(rootView, id);
      if (txtHome == null) {
        break missingId;
      }

      id = R.id.txtOnBoarding;
      AppCompatTextView txtOnBoarding = ViewBindings.findChildViewById(rootView, id);
      if (txtOnBoarding == null) {
        break missingId;
      }

      id = R.id.txtSwiping;
      AppCompatTextView txtSwiping = ViewBindings.findChildViewById(rootView, id);
      if (txtSwiping == null) {
        break missingId;
      }

      return new FragmentTutorialConnectorBinding((LinearLayout) rootView, txtHome, txtOnBoarding,
          txtSwiping);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
