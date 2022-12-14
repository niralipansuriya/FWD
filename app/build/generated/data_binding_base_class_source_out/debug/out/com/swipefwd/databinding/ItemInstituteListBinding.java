// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;

public final class ItemInstituteListBinding implements ViewBinding {
  @NonNull
  private final TextView rootView;

  @NonNull
  public final TextView tvSchoolName;

  private ItemInstituteListBinding(@NonNull TextView rootView, @NonNull TextView tvSchoolName) {
    this.rootView = rootView;
    this.tvSchoolName = tvSchoolName;
  }

  @Override
  @NonNull
  public TextView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemInstituteListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemInstituteListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_institute_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemInstituteListBinding bind(@NonNull View rootView) {
    if (rootView == null) {
      throw new NullPointerException("rootView");
    }

    TextView tvSchoolName = (TextView) rootView;

    return new ItemInstituteListBinding((TextView) rootView, tvSchoolName);
  }
}
