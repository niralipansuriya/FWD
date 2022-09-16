// Generated by view binder compiler. Do not edit!
package com.swipefwd.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.swipefwd.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAccountBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MaterialButton btnClose;

  @NonNull
  public final MaterialButton btnDeleteAccount;

  @NonNull
  public final TextInputEditText edtPhone;

  @NonNull
  public final AppCompatImageView imgInfo;

  @NonNull
  public final LinearLayout layoutMain;

  @NonNull
  public final RelativeLayout rlConnection;

  @NonNull
  public final RelativeLayout rlDating;

  @NonNull
  public final RelativeLayout rlMatchMaking;

  @NonNull
  public final RelativeLayout rlMatches;

  @NonNull
  public final RelativeLayout rlMessage;

  @NonNull
  public final RelativeLayout rlNewFwds;

  @NonNull
  public final AppCompatToggleButton toggleConnection;

  @NonNull
  public final AppCompatToggleButton toggleDating;

  @NonNull
  public final AppCompatToggleButton toggleFwds;

  @NonNull
  public final AppCompatToggleButton toggleMatchMaking;

  @NonNull
  public final AppCompatToggleButton toggleMatches;

  @NonNull
  public final AppCompatToggleButton toggleMessages;

  @NonNull
  public final AppCompatTextView txtAccountDisable;

  @NonNull
  public final TextInputEditText txtEmail;

  @NonNull
  public final TextInputEditText txtFb;

  @NonNull
  public final TextInputEditText txtGoogle;

  @NonNull
  public final TextInputEditText txtInsta;

  @NonNull
  public final TextInputEditText txtLinkedin;

  @NonNull
  public final View viewConnector;

  @NonNull
  public final View viewDater;

  private ActivityAccountBinding(@NonNull LinearLayout rootView, @NonNull MaterialButton btnClose,
      @NonNull MaterialButton btnDeleteAccount, @NonNull TextInputEditText edtPhone,
      @NonNull AppCompatImageView imgInfo, @NonNull LinearLayout layoutMain,
      @NonNull RelativeLayout rlConnection, @NonNull RelativeLayout rlDating,
      @NonNull RelativeLayout rlMatchMaking, @NonNull RelativeLayout rlMatches,
      @NonNull RelativeLayout rlMessage, @NonNull RelativeLayout rlNewFwds,
      @NonNull AppCompatToggleButton toggleConnection, @NonNull AppCompatToggleButton toggleDating,
      @NonNull AppCompatToggleButton toggleFwds, @NonNull AppCompatToggleButton toggleMatchMaking,
      @NonNull AppCompatToggleButton toggleMatches, @NonNull AppCompatToggleButton toggleMessages,
      @NonNull AppCompatTextView txtAccountDisable, @NonNull TextInputEditText txtEmail,
      @NonNull TextInputEditText txtFb, @NonNull TextInputEditText txtGoogle,
      @NonNull TextInputEditText txtInsta, @NonNull TextInputEditText txtLinkedin,
      @NonNull View viewConnector, @NonNull View viewDater) {
    this.rootView = rootView;
    this.btnClose = btnClose;
    this.btnDeleteAccount = btnDeleteAccount;
    this.edtPhone = edtPhone;
    this.imgInfo = imgInfo;
    this.layoutMain = layoutMain;
    this.rlConnection = rlConnection;
    this.rlDating = rlDating;
    this.rlMatchMaking = rlMatchMaking;
    this.rlMatches = rlMatches;
    this.rlMessage = rlMessage;
    this.rlNewFwds = rlNewFwds;
    this.toggleConnection = toggleConnection;
    this.toggleDating = toggleDating;
    this.toggleFwds = toggleFwds;
    this.toggleMatchMaking = toggleMatchMaking;
    this.toggleMatches = toggleMatches;
    this.toggleMessages = toggleMessages;
    this.txtAccountDisable = txtAccountDisable;
    this.txtEmail = txtEmail;
    this.txtFb = txtFb;
    this.txtGoogle = txtGoogle;
    this.txtInsta = txtInsta;
    this.txtLinkedin = txtLinkedin;
    this.viewConnector = viewConnector;
    this.viewDater = viewDater;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnClose;
      MaterialButton btnClose = ViewBindings.findChildViewById(rootView, id);
      if (btnClose == null) {
        break missingId;
      }

      id = R.id.btnDeleteAccount;
      MaterialButton btnDeleteAccount = ViewBindings.findChildViewById(rootView, id);
      if (btnDeleteAccount == null) {
        break missingId;
      }

      id = R.id.edtPhone;
      TextInputEditText edtPhone = ViewBindings.findChildViewById(rootView, id);
      if (edtPhone == null) {
        break missingId;
      }

      id = R.id.imgInfo;
      AppCompatImageView imgInfo = ViewBindings.findChildViewById(rootView, id);
      if (imgInfo == null) {
        break missingId;
      }

      LinearLayout layoutMain = (LinearLayout) rootView;

      id = R.id.rlConnection;
      RelativeLayout rlConnection = ViewBindings.findChildViewById(rootView, id);
      if (rlConnection == null) {
        break missingId;
      }

      id = R.id.rlDating;
      RelativeLayout rlDating = ViewBindings.findChildViewById(rootView, id);
      if (rlDating == null) {
        break missingId;
      }

      id = R.id.rlMatchMaking;
      RelativeLayout rlMatchMaking = ViewBindings.findChildViewById(rootView, id);
      if (rlMatchMaking == null) {
        break missingId;
      }

      id = R.id.rlMatches;
      RelativeLayout rlMatches = ViewBindings.findChildViewById(rootView, id);
      if (rlMatches == null) {
        break missingId;
      }

      id = R.id.rlMessage;
      RelativeLayout rlMessage = ViewBindings.findChildViewById(rootView, id);
      if (rlMessage == null) {
        break missingId;
      }

      id = R.id.rlNewFwds;
      RelativeLayout rlNewFwds = ViewBindings.findChildViewById(rootView, id);
      if (rlNewFwds == null) {
        break missingId;
      }

      id = R.id.toggleConnection;
      AppCompatToggleButton toggleConnection = ViewBindings.findChildViewById(rootView, id);
      if (toggleConnection == null) {
        break missingId;
      }

      id = R.id.toggleDating;
      AppCompatToggleButton toggleDating = ViewBindings.findChildViewById(rootView, id);
      if (toggleDating == null) {
        break missingId;
      }

      id = R.id.toggleFwds;
      AppCompatToggleButton toggleFwds = ViewBindings.findChildViewById(rootView, id);
      if (toggleFwds == null) {
        break missingId;
      }

      id = R.id.toggleMatchMaking;
      AppCompatToggleButton toggleMatchMaking = ViewBindings.findChildViewById(rootView, id);
      if (toggleMatchMaking == null) {
        break missingId;
      }

      id = R.id.toggleMatches;
      AppCompatToggleButton toggleMatches = ViewBindings.findChildViewById(rootView, id);
      if (toggleMatches == null) {
        break missingId;
      }

      id = R.id.toggleMessages;
      AppCompatToggleButton toggleMessages = ViewBindings.findChildViewById(rootView, id);
      if (toggleMessages == null) {
        break missingId;
      }

      id = R.id.txtAccountDisable;
      AppCompatTextView txtAccountDisable = ViewBindings.findChildViewById(rootView, id);
      if (txtAccountDisable == null) {
        break missingId;
      }

      id = R.id.txtEmail;
      TextInputEditText txtEmail = ViewBindings.findChildViewById(rootView, id);
      if (txtEmail == null) {
        break missingId;
      }

      id = R.id.txtFb;
      TextInputEditText txtFb = ViewBindings.findChildViewById(rootView, id);
      if (txtFb == null) {
        break missingId;
      }

      id = R.id.txtGoogle;
      TextInputEditText txtGoogle = ViewBindings.findChildViewById(rootView, id);
      if (txtGoogle == null) {
        break missingId;
      }

      id = R.id.txtInsta;
      TextInputEditText txtInsta = ViewBindings.findChildViewById(rootView, id);
      if (txtInsta == null) {
        break missingId;
      }

      id = R.id.txtLinkedin;
      TextInputEditText txtLinkedin = ViewBindings.findChildViewById(rootView, id);
      if (txtLinkedin == null) {
        break missingId;
      }

      id = R.id.viewConnector;
      View viewConnector = ViewBindings.findChildViewById(rootView, id);
      if (viewConnector == null) {
        break missingId;
      }

      id = R.id.viewDater;
      View viewDater = ViewBindings.findChildViewById(rootView, id);
      if (viewDater == null) {
        break missingId;
      }

      return new ActivityAccountBinding((LinearLayout) rootView, btnClose, btnDeleteAccount,
          edtPhone, imgInfo, layoutMain, rlConnection, rlDating, rlMatchMaking, rlMatches,
          rlMessage, rlNewFwds, toggleConnection, toggleDating, toggleFwds, toggleMatchMaking,
          toggleMatches, toggleMessages, txtAccountDisable, txtEmail, txtFb, txtGoogle, txtInsta,
          txtLinkedin, viewConnector, viewDater);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}