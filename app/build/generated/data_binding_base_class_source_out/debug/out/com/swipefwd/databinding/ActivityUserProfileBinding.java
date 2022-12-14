// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.addisonelliott.segmentedbutton.SegmentedButtonGroup;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ramijemli.percentagechartview.PercentageChartView;
import com.swipefwd.R;
import com.swipefwd.utils.RoundedImageView;
import com.swipefwd.utils.segmentedButtonTemp.SegmentedButton;
import com.swipefwd.utils.segmentedButtonTemp.SegmentedButtonGroup2;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityUserProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RelativeLayout addImagerl;

  @NonNull
  public final AppCompatImageView astericAi;

  @NonNull
  public final MaterialButton btnAddMore;

  @NonNull
  public final SegmentedButton btnCm;

  @NonNull
  public final MaterialButton btnContinue;

  @NonNull
  public final com.addisonelliott.segmentedbutton.SegmentedButton btnFemale;

  @NonNull
  public final SegmentedButton btnFt;

  @NonNull
  public final com.addisonelliott.segmentedbutton.SegmentedButton btnMale;

  @NonNull
  public final com.addisonelliott.segmentedbutton.SegmentedButton btnNone;

  @NonNull
  public final SegmentedButtonGroup btnSegmentGender;

  @NonNull
  public final MaterialButton btnTipGotIt;

  @NonNull
  public final AppCompatTextView choosePhoto1;

  @NonNull
  public final TextView edtAstroSign;

  @NonNull
  public final TextInputEditText edtBio;

  @NonNull
  public final TextInputEditText edtBirthDate;

  @NonNull
  public final TextInputEditText edtCaste;

  @NonNull
  public final TextInputEditText edtChildren;

  @NonNull
  public final TextInputEditText edtCovid;

  @NonNull
  public final TextInputEditText edtEducation;

  @NonNull
  public final TextInputEditText edtFirstName;

  @NonNull
  public final TextInputEditText edtInstagram;

  @NonNull
  public final TextInputEditText edtLanguage;

  @NonNull
  public final TextInputEditText edtLastName;

  @NonNull
  public final TextInputEditText edtLocation;

  @NonNull
  public final RelativeLayout edtLocationOuter;

  @NonNull
  public final TextInputEditText edtLookingFor;

  @NonNull
  public final TextInputEditText edtOccupation;

  @NonNull
  public final TextInputEditText edtReligion;

  @NonNull
  public final TextInputEditText edtSmoking;

  @NonNull
  public final TextInputEditText edtTravelLocation;

  @NonNull
  public final AppCompatImageView imageFaded;

  @NonNull
  public final AppCompatImageView imageFadedBackground;

  @NonNull
  public final AppCompatImageView imgAstro;

  @NonNull
  public final ConstraintLayout imgProfile;

  @NonNull
  public final RoundedImageView imgSettings;

  @NonNull
  public final AppCompatImageView imgToolTip;

  @NonNull
  public final AppCompatImageView ivArrow;

  @NonNull
  public final AppCompatImageView ivBack;

  @NonNull
  public final AppCompatImageView ivClose;

  @NonNull
  public final AppCompatImageView ivInfo;

  @NonNull
  public final AppCompatImageView ivInfo2;

  @NonNull
  public final AppCompatImageView ivNotVerified;

  @NonNull
  public final ConstraintLayout layoutAddMore;

  @NonNull
  public final ConstraintLayout layoutAstroSign;

  @NonNull
  public final LinearLayout layoutHeight;

  @NonNull
  public final ConstraintLayout layoutMain;

  @NonNull
  public final NestedScrollView layoutMainScroll;

  @NonNull
  public final ConstraintLayout layoutProfileMain;

  @NonNull
  public final RecyclerView layoutSelectedImage;

  @NonNull
  public final RelativeLayout layoutShowInProfile;

  @NonNull
  public final LinearLayout layoutUserDetails;

  @NonNull
  public final ConstraintLayout layoutVerified;

  @NonNull
  public final LinearLayout linLocAnim;

  @NonNull
  public final AppCompatImageView locNextArrow;

  @NonNull
  public final PercentageChartView progressSettings;

  @NonNull
  public final RelativeLayout relFadedView;

  @NonNull
  public final SegmentedButtonGroup2 segmentedHeight;

  @NonNull
  public final AppCompatToggleButton toggleInstagram;

  @NonNull
  public final AppCompatToggleButton toggleTravelLocation;

  @NonNull
  public final ImageView toolTipArrowBottom;

  @NonNull
  public final ImageView toolTipArrowTop;

  @NonNull
  public final RoundedImageView tvAddImage1;

  @NonNull
  public final AppCompatTextView tvBio;

  @NonNull
  public final AppCompatTextView tvBioCharacter;

  @NonNull
  public final AppCompatTextView tvUploadPhoto;

  @NonNull
  public final AppCompatTextView txtAreaLive;

  @NonNull
  public final AppCompatTextView txtDob;

  @NonNull
  public final AppCompatTextView txtFName;

  @NonNull
  public final AppCompatTextView txtFields;

  @NonNull
  public final AppCompatTextView txtGender;

  @NonNull
  public final AppCompatTextView txtHeight;

  @NonNull
  public final TextView txtHeightValue;

  @NonNull
  public final AppCompatTextView txtLName;

  @NonNull
  public final AppCompatTextView txtLocationMessage;

  @NonNull
  public final AppCompatTextView txtOnOff;

  @NonNull
  public final AppCompatTextView txtProfile;

  @NonNull
  public final AppCompatTextView txtProfileHeader;

  @NonNull
  public final TextView txtProgress;

  @NonNull
  public final AppCompatTextView txtShowInInstagram;

  @NonNull
  public final AppCompatTextView txtTipDesc;

  @NonNull
  public final AppCompatTextView txtTipHeader;

  @NonNull
  public final AppCompatTextView txtTravelling;

  @NonNull
  public final AppCompatTextView txtUploadPhoto;

  @NonNull
  public final AppCompatTextView txtVerified;

  @NonNull
  public final AppCompatTextView txtVerifiedTitle;

  private ActivityUserProfileBinding(@NonNull ConstraintLayout rootView,
      @NonNull RelativeLayout addImagerl, @NonNull AppCompatImageView astericAi,
      @NonNull MaterialButton btnAddMore, @NonNull SegmentedButton btnCm,
      @NonNull MaterialButton btnContinue,
      @NonNull com.addisonelliott.segmentedbutton.SegmentedButton btnFemale,
      @NonNull SegmentedButton btnFt,
      @NonNull com.addisonelliott.segmentedbutton.SegmentedButton btnMale,
      @NonNull com.addisonelliott.segmentedbutton.SegmentedButton btnNone,
      @NonNull SegmentedButtonGroup btnSegmentGender, @NonNull MaterialButton btnTipGotIt,
      @NonNull AppCompatTextView choosePhoto1, @NonNull TextView edtAstroSign,
      @NonNull TextInputEditText edtBio, @NonNull TextInputEditText edtBirthDate,
      @NonNull TextInputEditText edtCaste, @NonNull TextInputEditText edtChildren,
      @NonNull TextInputEditText edtCovid, @NonNull TextInputEditText edtEducation,
      @NonNull TextInputEditText edtFirstName, @NonNull TextInputEditText edtInstagram,
      @NonNull TextInputEditText edtLanguage, @NonNull TextInputEditText edtLastName,
      @NonNull TextInputEditText edtLocation, @NonNull RelativeLayout edtLocationOuter,
      @NonNull TextInputEditText edtLookingFor, @NonNull TextInputEditText edtOccupation,
      @NonNull TextInputEditText edtReligion, @NonNull TextInputEditText edtSmoking,
      @NonNull TextInputEditText edtTravelLocation, @NonNull AppCompatImageView imageFaded,
      @NonNull AppCompatImageView imageFadedBackground, @NonNull AppCompatImageView imgAstro,
      @NonNull ConstraintLayout imgProfile, @NonNull RoundedImageView imgSettings,
      @NonNull AppCompatImageView imgToolTip, @NonNull AppCompatImageView ivArrow,
      @NonNull AppCompatImageView ivBack, @NonNull AppCompatImageView ivClose,
      @NonNull AppCompatImageView ivInfo, @NonNull AppCompatImageView ivInfo2,
      @NonNull AppCompatImageView ivNotVerified, @NonNull ConstraintLayout layoutAddMore,
      @NonNull ConstraintLayout layoutAstroSign, @NonNull LinearLayout layoutHeight,
      @NonNull ConstraintLayout layoutMain, @NonNull NestedScrollView layoutMainScroll,
      @NonNull ConstraintLayout layoutProfileMain, @NonNull RecyclerView layoutSelectedImage,
      @NonNull RelativeLayout layoutShowInProfile, @NonNull LinearLayout layoutUserDetails,
      @NonNull ConstraintLayout layoutVerified, @NonNull LinearLayout linLocAnim,
      @NonNull AppCompatImageView locNextArrow, @NonNull PercentageChartView progressSettings,
      @NonNull RelativeLayout relFadedView, @NonNull SegmentedButtonGroup2 segmentedHeight,
      @NonNull AppCompatToggleButton toggleInstagram,
      @NonNull AppCompatToggleButton toggleTravelLocation, @NonNull ImageView toolTipArrowBottom,
      @NonNull ImageView toolTipArrowTop, @NonNull RoundedImageView tvAddImage1,
      @NonNull AppCompatTextView tvBio, @NonNull AppCompatTextView tvBioCharacter,
      @NonNull AppCompatTextView tvUploadPhoto, @NonNull AppCompatTextView txtAreaLive,
      @NonNull AppCompatTextView txtDob, @NonNull AppCompatTextView txtFName,
      @NonNull AppCompatTextView txtFields, @NonNull AppCompatTextView txtGender,
      @NonNull AppCompatTextView txtHeight, @NonNull TextView txtHeightValue,
      @NonNull AppCompatTextView txtLName, @NonNull AppCompatTextView txtLocationMessage,
      @NonNull AppCompatTextView txtOnOff, @NonNull AppCompatTextView txtProfile,
      @NonNull AppCompatTextView txtProfileHeader, @NonNull TextView txtProgress,
      @NonNull AppCompatTextView txtShowInInstagram, @NonNull AppCompatTextView txtTipDesc,
      @NonNull AppCompatTextView txtTipHeader, @NonNull AppCompatTextView txtTravelling,
      @NonNull AppCompatTextView txtUploadPhoto, @NonNull AppCompatTextView txtVerified,
      @NonNull AppCompatTextView txtVerifiedTitle) {
    this.rootView = rootView;
    this.addImagerl = addImagerl;
    this.astericAi = astericAi;
    this.btnAddMore = btnAddMore;
    this.btnCm = btnCm;
    this.btnContinue = btnContinue;
    this.btnFemale = btnFemale;
    this.btnFt = btnFt;
    this.btnMale = btnMale;
    this.btnNone = btnNone;
    this.btnSegmentGender = btnSegmentGender;
    this.btnTipGotIt = btnTipGotIt;
    this.choosePhoto1 = choosePhoto1;
    this.edtAstroSign = edtAstroSign;
    this.edtBio = edtBio;
    this.edtBirthDate = edtBirthDate;
    this.edtCaste = edtCaste;
    this.edtChildren = edtChildren;
    this.edtCovid = edtCovid;
    this.edtEducation = edtEducation;
    this.edtFirstName = edtFirstName;
    this.edtInstagram = edtInstagram;
    this.edtLanguage = edtLanguage;
    this.edtLastName = edtLastName;
    this.edtLocation = edtLocation;
    this.edtLocationOuter = edtLocationOuter;
    this.edtLookingFor = edtLookingFor;
    this.edtOccupation = edtOccupation;
    this.edtReligion = edtReligion;
    this.edtSmoking = edtSmoking;
    this.edtTravelLocation = edtTravelLocation;
    this.imageFaded = imageFaded;
    this.imageFadedBackground = imageFadedBackground;
    this.imgAstro = imgAstro;
    this.imgProfile = imgProfile;
    this.imgSettings = imgSettings;
    this.imgToolTip = imgToolTip;
    this.ivArrow = ivArrow;
    this.ivBack = ivBack;
    this.ivClose = ivClose;
    this.ivInfo = ivInfo;
    this.ivInfo2 = ivInfo2;
    this.ivNotVerified = ivNotVerified;
    this.layoutAddMore = layoutAddMore;
    this.layoutAstroSign = layoutAstroSign;
    this.layoutHeight = layoutHeight;
    this.layoutMain = layoutMain;
    this.layoutMainScroll = layoutMainScroll;
    this.layoutProfileMain = layoutProfileMain;
    this.layoutSelectedImage = layoutSelectedImage;
    this.layoutShowInProfile = layoutShowInProfile;
    this.layoutUserDetails = layoutUserDetails;
    this.layoutVerified = layoutVerified;
    this.linLocAnim = linLocAnim;
    this.locNextArrow = locNextArrow;
    this.progressSettings = progressSettings;
    this.relFadedView = relFadedView;
    this.segmentedHeight = segmentedHeight;
    this.toggleInstagram = toggleInstagram;
    this.toggleTravelLocation = toggleTravelLocation;
    this.toolTipArrowBottom = toolTipArrowBottom;
    this.toolTipArrowTop = toolTipArrowTop;
    this.tvAddImage1 = tvAddImage1;
    this.tvBio = tvBio;
    this.tvBioCharacter = tvBioCharacter;
    this.tvUploadPhoto = tvUploadPhoto;
    this.txtAreaLive = txtAreaLive;
    this.txtDob = txtDob;
    this.txtFName = txtFName;
    this.txtFields = txtFields;
    this.txtGender = txtGender;
    this.txtHeight = txtHeight;
    this.txtHeightValue = txtHeightValue;
    this.txtLName = txtLName;
    this.txtLocationMessage = txtLocationMessage;
    this.txtOnOff = txtOnOff;
    this.txtProfile = txtProfile;
    this.txtProfileHeader = txtProfileHeader;
    this.txtProgress = txtProgress;
    this.txtShowInInstagram = txtShowInInstagram;
    this.txtTipDesc = txtTipDesc;
    this.txtTipHeader = txtTipHeader;
    this.txtTravelling = txtTravelling;
    this.txtUploadPhoto = txtUploadPhoto;
    this.txtVerified = txtVerified;
    this.txtVerifiedTitle = txtVerifiedTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityUserProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityUserProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_user_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityUserProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addImagerl;
      RelativeLayout addImagerl = ViewBindings.findChildViewById(rootView, id);
      if (addImagerl == null) {
        break missingId;
      }

      id = R.id.astericAi;
      AppCompatImageView astericAi = ViewBindings.findChildViewById(rootView, id);
      if (astericAi == null) {
        break missingId;
      }

      id = R.id.btnAddMore;
      MaterialButton btnAddMore = ViewBindings.findChildViewById(rootView, id);
      if (btnAddMore == null) {
        break missingId;
      }

      id = R.id.btnCm;
      SegmentedButton btnCm = ViewBindings.findChildViewById(rootView, id);
      if (btnCm == null) {
        break missingId;
      }

      id = R.id.btnContinue;
      MaterialButton btnContinue = ViewBindings.findChildViewById(rootView, id);
      if (btnContinue == null) {
        break missingId;
      }

      id = R.id.btnFemale;
      com.addisonelliott.segmentedbutton.SegmentedButton btnFemale = ViewBindings.findChildViewById(rootView, id);
      if (btnFemale == null) {
        break missingId;
      }

      id = R.id.btnFt;
      SegmentedButton btnFt = ViewBindings.findChildViewById(rootView, id);
      if (btnFt == null) {
        break missingId;
      }

      id = R.id.btnMale;
      com.addisonelliott.segmentedbutton.SegmentedButton btnMale = ViewBindings.findChildViewById(rootView, id);
      if (btnMale == null) {
        break missingId;
      }

      id = R.id.btnNone;
      com.addisonelliott.segmentedbutton.SegmentedButton btnNone = ViewBindings.findChildViewById(rootView, id);
      if (btnNone == null) {
        break missingId;
      }

      id = R.id.btnSegmentGender;
      SegmentedButtonGroup btnSegmentGender = ViewBindings.findChildViewById(rootView, id);
      if (btnSegmentGender == null) {
        break missingId;
      }

      id = R.id.btnTipGotIt;
      MaterialButton btnTipGotIt = ViewBindings.findChildViewById(rootView, id);
      if (btnTipGotIt == null) {
        break missingId;
      }

      id = R.id.choosePhoto1;
      AppCompatTextView choosePhoto1 = ViewBindings.findChildViewById(rootView, id);
      if (choosePhoto1 == null) {
        break missingId;
      }

      id = R.id.edtAstroSign;
      TextView edtAstroSign = ViewBindings.findChildViewById(rootView, id);
      if (edtAstroSign == null) {
        break missingId;
      }

      id = R.id.edtBio;
      TextInputEditText edtBio = ViewBindings.findChildViewById(rootView, id);
      if (edtBio == null) {
        break missingId;
      }

      id = R.id.edtBirthDate;
      TextInputEditText edtBirthDate = ViewBindings.findChildViewById(rootView, id);
      if (edtBirthDate == null) {
        break missingId;
      }

      id = R.id.edtCaste;
      TextInputEditText edtCaste = ViewBindings.findChildViewById(rootView, id);
      if (edtCaste == null) {
        break missingId;
      }

      id = R.id.edtChildren;
      TextInputEditText edtChildren = ViewBindings.findChildViewById(rootView, id);
      if (edtChildren == null) {
        break missingId;
      }

      id = R.id.edtCovid;
      TextInputEditText edtCovid = ViewBindings.findChildViewById(rootView, id);
      if (edtCovid == null) {
        break missingId;
      }

      id = R.id.edtEducation;
      TextInputEditText edtEducation = ViewBindings.findChildViewById(rootView, id);
      if (edtEducation == null) {
        break missingId;
      }

      id = R.id.edtFirstName;
      TextInputEditText edtFirstName = ViewBindings.findChildViewById(rootView, id);
      if (edtFirstName == null) {
        break missingId;
      }

      id = R.id.edtInstagram;
      TextInputEditText edtInstagram = ViewBindings.findChildViewById(rootView, id);
      if (edtInstagram == null) {
        break missingId;
      }

      id = R.id.edtLanguage;
      TextInputEditText edtLanguage = ViewBindings.findChildViewById(rootView, id);
      if (edtLanguage == null) {
        break missingId;
      }

      id = R.id.edtLastName;
      TextInputEditText edtLastName = ViewBindings.findChildViewById(rootView, id);
      if (edtLastName == null) {
        break missingId;
      }

      id = R.id.edtLocation;
      TextInputEditText edtLocation = ViewBindings.findChildViewById(rootView, id);
      if (edtLocation == null) {
        break missingId;
      }

      id = R.id.edtLocationOuter;
      RelativeLayout edtLocationOuter = ViewBindings.findChildViewById(rootView, id);
      if (edtLocationOuter == null) {
        break missingId;
      }

      id = R.id.edtLookingFor;
      TextInputEditText edtLookingFor = ViewBindings.findChildViewById(rootView, id);
      if (edtLookingFor == null) {
        break missingId;
      }

      id = R.id.edtOccupation;
      TextInputEditText edtOccupation = ViewBindings.findChildViewById(rootView, id);
      if (edtOccupation == null) {
        break missingId;
      }

      id = R.id.edtReligion;
      TextInputEditText edtReligion = ViewBindings.findChildViewById(rootView, id);
      if (edtReligion == null) {
        break missingId;
      }

      id = R.id.edtSmoking;
      TextInputEditText edtSmoking = ViewBindings.findChildViewById(rootView, id);
      if (edtSmoking == null) {
        break missingId;
      }

      id = R.id.edtTravelLocation;
      TextInputEditText edtTravelLocation = ViewBindings.findChildViewById(rootView, id);
      if (edtTravelLocation == null) {
        break missingId;
      }

      id = R.id.imageFaded;
      AppCompatImageView imageFaded = ViewBindings.findChildViewById(rootView, id);
      if (imageFaded == null) {
        break missingId;
      }

      id = R.id.imageFadedBackground;
      AppCompatImageView imageFadedBackground = ViewBindings.findChildViewById(rootView, id);
      if (imageFadedBackground == null) {
        break missingId;
      }

      id = R.id.imgAstro;
      AppCompatImageView imgAstro = ViewBindings.findChildViewById(rootView, id);
      if (imgAstro == null) {
        break missingId;
      }

      id = R.id.img_profile;
      ConstraintLayout imgProfile = ViewBindings.findChildViewById(rootView, id);
      if (imgProfile == null) {
        break missingId;
      }

      id = R.id.imgSettings;
      RoundedImageView imgSettings = ViewBindings.findChildViewById(rootView, id);
      if (imgSettings == null) {
        break missingId;
      }

      id = R.id.imgToolTip;
      AppCompatImageView imgToolTip = ViewBindings.findChildViewById(rootView, id);
      if (imgToolTip == null) {
        break missingId;
      }

      id = R.id.ivArrow;
      AppCompatImageView ivArrow = ViewBindings.findChildViewById(rootView, id);
      if (ivArrow == null) {
        break missingId;
      }

      id = R.id.ivBack;
      AppCompatImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.ivClose;
      AppCompatImageView ivClose = ViewBindings.findChildViewById(rootView, id);
      if (ivClose == null) {
        break missingId;
      }

      id = R.id.ivInfo;
      AppCompatImageView ivInfo = ViewBindings.findChildViewById(rootView, id);
      if (ivInfo == null) {
        break missingId;
      }

      id = R.id.ivInfo2;
      AppCompatImageView ivInfo2 = ViewBindings.findChildViewById(rootView, id);
      if (ivInfo2 == null) {
        break missingId;
      }

      id = R.id.ivNotVerified;
      AppCompatImageView ivNotVerified = ViewBindings.findChildViewById(rootView, id);
      if (ivNotVerified == null) {
        break missingId;
      }

      id = R.id.layoutAddMore;
      ConstraintLayout layoutAddMore = ViewBindings.findChildViewById(rootView, id);
      if (layoutAddMore == null) {
        break missingId;
      }

      id = R.id.layoutAstroSign;
      ConstraintLayout layoutAstroSign = ViewBindings.findChildViewById(rootView, id);
      if (layoutAstroSign == null) {
        break missingId;
      }

      id = R.id.layoutHeight;
      LinearLayout layoutHeight = ViewBindings.findChildViewById(rootView, id);
      if (layoutHeight == null) {
        break missingId;
      }

      id = R.id.layoutMain;
      ConstraintLayout layoutMain = ViewBindings.findChildViewById(rootView, id);
      if (layoutMain == null) {
        break missingId;
      }

      id = R.id.layoutMainScroll;
      NestedScrollView layoutMainScroll = ViewBindings.findChildViewById(rootView, id);
      if (layoutMainScroll == null) {
        break missingId;
      }

      ConstraintLayout layoutProfileMain = (ConstraintLayout) rootView;

      id = R.id.layoutSelectedImage;
      RecyclerView layoutSelectedImage = ViewBindings.findChildViewById(rootView, id);
      if (layoutSelectedImage == null) {
        break missingId;
      }

      id = R.id.layoutShowInProfile;
      RelativeLayout layoutShowInProfile = ViewBindings.findChildViewById(rootView, id);
      if (layoutShowInProfile == null) {
        break missingId;
      }

      id = R.id.layoutUserDetails;
      LinearLayout layoutUserDetails = ViewBindings.findChildViewById(rootView, id);
      if (layoutUserDetails == null) {
        break missingId;
      }

      id = R.id.layoutVerified;
      ConstraintLayout layoutVerified = ViewBindings.findChildViewById(rootView, id);
      if (layoutVerified == null) {
        break missingId;
      }

      id = R.id.linLocAnim;
      LinearLayout linLocAnim = ViewBindings.findChildViewById(rootView, id);
      if (linLocAnim == null) {
        break missingId;
      }

      id = R.id.locNextArrow;
      AppCompatImageView locNextArrow = ViewBindings.findChildViewById(rootView, id);
      if (locNextArrow == null) {
        break missingId;
      }

      id = R.id.progressSettings;
      PercentageChartView progressSettings = ViewBindings.findChildViewById(rootView, id);
      if (progressSettings == null) {
        break missingId;
      }

      id = R.id.relFadedView;
      RelativeLayout relFadedView = ViewBindings.findChildViewById(rootView, id);
      if (relFadedView == null) {
        break missingId;
      }

      id = R.id.segmentedHeight;
      SegmentedButtonGroup2 segmentedHeight = ViewBindings.findChildViewById(rootView, id);
      if (segmentedHeight == null) {
        break missingId;
      }

      id = R.id.toggleInstagram;
      AppCompatToggleButton toggleInstagram = ViewBindings.findChildViewById(rootView, id);
      if (toggleInstagram == null) {
        break missingId;
      }

      id = R.id.toggleTravelLocation;
      AppCompatToggleButton toggleTravelLocation = ViewBindings.findChildViewById(rootView, id);
      if (toggleTravelLocation == null) {
        break missingId;
      }

      id = R.id.toolTipArrowBottom;
      ImageView toolTipArrowBottom = ViewBindings.findChildViewById(rootView, id);
      if (toolTipArrowBottom == null) {
        break missingId;
      }

      id = R.id.toolTipArrowTop;
      ImageView toolTipArrowTop = ViewBindings.findChildViewById(rootView, id);
      if (toolTipArrowTop == null) {
        break missingId;
      }

      id = R.id.tvAddImage1;
      RoundedImageView tvAddImage1 = ViewBindings.findChildViewById(rootView, id);
      if (tvAddImage1 == null) {
        break missingId;
      }

      id = R.id.tvBio;
      AppCompatTextView tvBio = ViewBindings.findChildViewById(rootView, id);
      if (tvBio == null) {
        break missingId;
      }

      id = R.id.tvBioCharacter;
      AppCompatTextView tvBioCharacter = ViewBindings.findChildViewById(rootView, id);
      if (tvBioCharacter == null) {
        break missingId;
      }

      id = R.id.tvUploadPhoto;
      AppCompatTextView tvUploadPhoto = ViewBindings.findChildViewById(rootView, id);
      if (tvUploadPhoto == null) {
        break missingId;
      }

      id = R.id.txtAreaLive;
      AppCompatTextView txtAreaLive = ViewBindings.findChildViewById(rootView, id);
      if (txtAreaLive == null) {
        break missingId;
      }

      id = R.id.txtDob;
      AppCompatTextView txtDob = ViewBindings.findChildViewById(rootView, id);
      if (txtDob == null) {
        break missingId;
      }

      id = R.id.txtFName;
      AppCompatTextView txtFName = ViewBindings.findChildViewById(rootView, id);
      if (txtFName == null) {
        break missingId;
      }

      id = R.id.txtFields;
      AppCompatTextView txtFields = ViewBindings.findChildViewById(rootView, id);
      if (txtFields == null) {
        break missingId;
      }

      id = R.id.txtGender;
      AppCompatTextView txtGender = ViewBindings.findChildViewById(rootView, id);
      if (txtGender == null) {
        break missingId;
      }

      id = R.id.txtHeight;
      AppCompatTextView txtHeight = ViewBindings.findChildViewById(rootView, id);
      if (txtHeight == null) {
        break missingId;
      }

      id = R.id.txtHeightValue;
      TextView txtHeightValue = ViewBindings.findChildViewById(rootView, id);
      if (txtHeightValue == null) {
        break missingId;
      }

      id = R.id.txtLName;
      AppCompatTextView txtLName = ViewBindings.findChildViewById(rootView, id);
      if (txtLName == null) {
        break missingId;
      }

      id = R.id.txtLocationMessage;
      AppCompatTextView txtLocationMessage = ViewBindings.findChildViewById(rootView, id);
      if (txtLocationMessage == null) {
        break missingId;
      }

      id = R.id.txtOnOff;
      AppCompatTextView txtOnOff = ViewBindings.findChildViewById(rootView, id);
      if (txtOnOff == null) {
        break missingId;
      }

      id = R.id.txtProfile;
      AppCompatTextView txtProfile = ViewBindings.findChildViewById(rootView, id);
      if (txtProfile == null) {
        break missingId;
      }

      id = R.id.txtProfileHeader;
      AppCompatTextView txtProfileHeader = ViewBindings.findChildViewById(rootView, id);
      if (txtProfileHeader == null) {
        break missingId;
      }

      id = R.id.txtProgress;
      TextView txtProgress = ViewBindings.findChildViewById(rootView, id);
      if (txtProgress == null) {
        break missingId;
      }

      id = R.id.txtShowInInstagram;
      AppCompatTextView txtShowInInstagram = ViewBindings.findChildViewById(rootView, id);
      if (txtShowInInstagram == null) {
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

      id = R.id.txtTravelling;
      AppCompatTextView txtTravelling = ViewBindings.findChildViewById(rootView, id);
      if (txtTravelling == null) {
        break missingId;
      }

      id = R.id.txtUploadPhoto;
      AppCompatTextView txtUploadPhoto = ViewBindings.findChildViewById(rootView, id);
      if (txtUploadPhoto == null) {
        break missingId;
      }

      id = R.id.txtVerified;
      AppCompatTextView txtVerified = ViewBindings.findChildViewById(rootView, id);
      if (txtVerified == null) {
        break missingId;
      }

      id = R.id.txtVerifiedTitle;
      AppCompatTextView txtVerifiedTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtVerifiedTitle == null) {
        break missingId;
      }

      return new ActivityUserProfileBinding((ConstraintLayout) rootView, addImagerl, astericAi,
          btnAddMore, btnCm, btnContinue, btnFemale, btnFt, btnMale, btnNone, btnSegmentGender,
          btnTipGotIt, choosePhoto1, edtAstroSign, edtBio, edtBirthDate, edtCaste, edtChildren,
          edtCovid, edtEducation, edtFirstName, edtInstagram, edtLanguage, edtLastName, edtLocation,
          edtLocationOuter, edtLookingFor, edtOccupation, edtReligion, edtSmoking,
          edtTravelLocation, imageFaded, imageFadedBackground, imgAstro, imgProfile, imgSettings,
          imgToolTip, ivArrow, ivBack, ivClose, ivInfo, ivInfo2, ivNotVerified, layoutAddMore,
          layoutAstroSign, layoutHeight, layoutMain, layoutMainScroll, layoutProfileMain,
          layoutSelectedImage, layoutShowInProfile, layoutUserDetails, layoutVerified, linLocAnim,
          locNextArrow, progressSettings, relFadedView, segmentedHeight, toggleInstagram,
          toggleTravelLocation, toolTipArrowBottom, toolTipArrowTop, tvAddImage1, tvBio,
          tvBioCharacter, tvUploadPhoto, txtAreaLive, txtDob, txtFName, txtFields, txtGender,
          txtHeight, txtHeightValue, txtLName, txtLocationMessage, txtOnOff, txtProfile,
          txtProfileHeader, txtProgress, txtShowInInstagram, txtTipDesc, txtTipHeader,
          txtTravelling, txtUploadPhoto, txtVerified, txtVerifiedTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
