// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySchoolBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialCardView cardList;

  @NonNull
  public final MaterialCardView educationLevelView;

  @NonNull
  public final AppCompatImageView ivClose;

  @NonNull
  public final ConstraintLayout layoutMain;

  @NonNull
  public final LinearLayout llButtons;

  @NonNull
  public final RecyclerView rvLevels;

  @NonNull
  public final NestedScrollView scrollView;

  @NonNull
  public final AppCompatTextView txtGraduationYear;

  @NonNull
  public final AppCompatTextView txtIndex;

  @NonNull
  public final TextInputEditText txtInstituteName;

  @NonNull
  public final AppCompatTextView txtInstituteTitle;

  @NonNull
  public final TextInputEditText txtLevelName;

  @NonNull
  public final AppCompatTextView txtLevelTitle;

  @NonNull
  public final MaterialButton txtSkip;

  @NonNull
  public final MaterialButton txtSkip2;

  @NonNull
  public final MaterialButton txtSubmit;

  @NonNull
  public final MaterialButton txtSubmit2;

  @NonNull
  public final AppCompatTextView txtTitle;

  @NonNull
  public final AppCompatAutoCompleteTextView txtYear;

  private ActivitySchoolBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialCardView cardList, @NonNull MaterialCardView educationLevelView,
      @NonNull AppCompatImageView ivClose, @NonNull ConstraintLayout layoutMain,
      @NonNull LinearLayout llButtons, @NonNull RecyclerView rvLevels,
      @NonNull NestedScrollView scrollView, @NonNull AppCompatTextView txtGraduationYear,
      @NonNull AppCompatTextView txtIndex, @NonNull TextInputEditText txtInstituteName,
      @NonNull AppCompatTextView txtInstituteTitle, @NonNull TextInputEditText txtLevelName,
      @NonNull AppCompatTextView txtLevelTitle, @NonNull MaterialButton txtSkip,
      @NonNull MaterialButton txtSkip2, @NonNull MaterialButton txtSubmit,
      @NonNull MaterialButton txtSubmit2, @NonNull AppCompatTextView txtTitle,
      @NonNull AppCompatAutoCompleteTextView txtYear) {
    this.rootView = rootView;
    this.cardList = cardList;
    this.educationLevelView = educationLevelView;
    this.ivClose = ivClose;
    this.layoutMain = layoutMain;
    this.llButtons = llButtons;
    this.rvLevels = rvLevels;
    this.scrollView = scrollView;
    this.txtGraduationYear = txtGraduationYear;
    this.txtIndex = txtIndex;
    this.txtInstituteName = txtInstituteName;
    this.txtInstituteTitle = txtInstituteTitle;
    this.txtLevelName = txtLevelName;
    this.txtLevelTitle = txtLevelTitle;
    this.txtSkip = txtSkip;
    this.txtSkip2 = txtSkip2;
    this.txtSubmit = txtSubmit;
    this.txtSubmit2 = txtSubmit2;
    this.txtTitle = txtTitle;
    this.txtYear = txtYear;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySchoolBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySchoolBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_school, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySchoolBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardList;
      MaterialCardView cardList = ViewBindings.findChildViewById(rootView, id);
      if (cardList == null) {
        break missingId;
      }

      id = R.id.educationLevelView;
      MaterialCardView educationLevelView = ViewBindings.findChildViewById(rootView, id);
      if (educationLevelView == null) {
        break missingId;
      }

      id = R.id.ivClose;
      AppCompatImageView ivClose = ViewBindings.findChildViewById(rootView, id);
      if (ivClose == null) {
        break missingId;
      }

      ConstraintLayout layoutMain = (ConstraintLayout) rootView;

      id = R.id.llButtons;
      LinearLayout llButtons = ViewBindings.findChildViewById(rootView, id);
      if (llButtons == null) {
        break missingId;
      }

      id = R.id.rvLevels;
      RecyclerView rvLevels = ViewBindings.findChildViewById(rootView, id);
      if (rvLevels == null) {
        break missingId;
      }

      id = R.id.scrollView;
      NestedScrollView scrollView = ViewBindings.findChildViewById(rootView, id);
      if (scrollView == null) {
        break missingId;
      }

      id = R.id.txtGraduationYear;
      AppCompatTextView txtGraduationYear = ViewBindings.findChildViewById(rootView, id);
      if (txtGraduationYear == null) {
        break missingId;
      }

      id = R.id.txtIndex;
      AppCompatTextView txtIndex = ViewBindings.findChildViewById(rootView, id);
      if (txtIndex == null) {
        break missingId;
      }

      id = R.id.txtInstituteName;
      TextInputEditText txtInstituteName = ViewBindings.findChildViewById(rootView, id);
      if (txtInstituteName == null) {
        break missingId;
      }

      id = R.id.txtInstituteTitle;
      AppCompatTextView txtInstituteTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtInstituteTitle == null) {
        break missingId;
      }

      id = R.id.txtLevelName;
      TextInputEditText txtLevelName = ViewBindings.findChildViewById(rootView, id);
      if (txtLevelName == null) {
        break missingId;
      }

      id = R.id.txtLevelTitle;
      AppCompatTextView txtLevelTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtLevelTitle == null) {
        break missingId;
      }

      id = R.id.txtSkip;
      MaterialButton txtSkip = ViewBindings.findChildViewById(rootView, id);
      if (txtSkip == null) {
        break missingId;
      }

      id = R.id.txtSkip2;
      MaterialButton txtSkip2 = ViewBindings.findChildViewById(rootView, id);
      if (txtSkip2 == null) {
        break missingId;
      }

      id = R.id.txtSubmit;
      MaterialButton txtSubmit = ViewBindings.findChildViewById(rootView, id);
      if (txtSubmit == null) {
        break missingId;
      }

      id = R.id.txtSubmit2;
      MaterialButton txtSubmit2 = ViewBindings.findChildViewById(rootView, id);
      if (txtSubmit2 == null) {
        break missingId;
      }

      id = R.id.txtTitle;
      AppCompatTextView txtTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtTitle == null) {
        break missingId;
      }

      id = R.id.txtYear;
      AppCompatAutoCompleteTextView txtYear = ViewBindings.findChildViewById(rootView, id);
      if (txtYear == null) {
        break missingId;
      }

      return new ActivitySchoolBinding((ConstraintLayout) rootView, cardList, educationLevelView,
          ivClose, layoutMain, llButtons, rvLevels, scrollView, txtGraduationYear, txtIndex,
          txtInstituteName, txtInstituteTitle, txtLevelName, txtLevelTitle, txtSkip, txtSkip2,
          txtSubmit, txtSubmit2, txtTitle, txtYear);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}