// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentActivityBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ViewPager2 pager;

  @NonNull
  public final TabLayout tabMessages;

  @NonNull
  public final TextView txtActivity;

  private FragmentActivityBinding(@NonNull ConstraintLayout rootView, @NonNull ViewPager2 pager,
      @NonNull TabLayout tabMessages, @NonNull TextView txtActivity) {
    this.rootView = rootView;
    this.pager = pager;
    this.tabMessages = tabMessages;
    this.txtActivity = txtActivity;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.pager;
      ViewPager2 pager = ViewBindings.findChildViewById(rootView, id);
      if (pager == null) {
        break missingId;
      }

      id = R.id.tabMessages;
      TabLayout tabMessages = ViewBindings.findChildViewById(rootView, id);
      if (tabMessages == null) {
        break missingId;
      }

      id = R.id.txtActivity;
      TextView txtActivity = ViewBindings.findChildViewById(rootView, id);
      if (txtActivity == null) {
        break missingId;
      }

      return new FragmentActivityBinding((ConstraintLayout) rootView, pager, tabMessages,
          txtActivity);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}