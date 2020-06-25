// Generated code from Butter Knife. Do not modify!
package com.smart.ticketing.pincode;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smart.ticketing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddressRegisterActivity_ViewBinding<T extends AddressRegisterActivity> implements Unbinder {
  protected T target;

  @UiThread
  public AddressRegisterActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.etName = Utils.findRequiredViewAsType(source, R.id.editText, "field 'etName'", EditText.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.editText2, "field 'etPhone'", EditText.class);
    target.etPropertyId = Utils.findRequiredViewAsType(source, R.id.editText3, "field 'etPropertyId'", EditText.class);
    target.etPincode = Utils.findRequiredViewAsType(source, R.id.editText4, "field 'etPincode'", EditText.class);
    target.etAddress1 = Utils.findRequiredViewAsType(source, R.id.editText5, "field 'etAddress1'", EditText.class);
    target.etAddress2 = Utils.findRequiredViewAsType(source, R.id.editText6, "field 'etAddress2'", EditText.class);
    target.etLatLng = Utils.findRequiredViewAsType(source, R.id.editText7, "field 'etLatLng'", EditText.class);
    target.etCity = Utils.findRequiredViewAsType(source, R.id.editText8, "field 'etCity'", EditText.class);
    target.btnSubmit = Utils.findRequiredViewAsType(source, R.id.button2, "field 'btnSubmit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etName = null;
    target.etPhone = null;
    target.etPropertyId = null;
    target.etPincode = null;
    target.etAddress1 = null;
    target.etAddress2 = null;
    target.etLatLng = null;
    target.etCity = null;
    target.btnSubmit = null;

    this.target = null;
  }
}
