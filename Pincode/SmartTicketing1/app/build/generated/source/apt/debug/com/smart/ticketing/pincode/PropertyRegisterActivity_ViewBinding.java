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

public class PropertyRegisterActivity_ViewBinding<T extends PropertyRegisterActivity> implements Unbinder {
  protected T target;

  @UiThread
  public PropertyRegisterActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.etAadharId = Utils.findRequiredViewAsType(source, R.id.editText0, "field 'etAadharId'", EditText.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.editText, "field 'etName'", EditText.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.editText2, "field 'etPhone'", EditText.class);
    target.etPropertyId = Utils.findRequiredViewAsType(source, R.id.editText3, "field 'etPropertyId'", EditText.class);
    target.etPincode = Utils.findRequiredViewAsType(source, R.id.editText4, "field 'etPincode'", EditText.class);
    target.etAddress1 = Utils.findRequiredViewAsType(source, R.id.editText5, "field 'etAddress1'", EditText.class);
    target.etAddress2 = Utils.findRequiredViewAsType(source, R.id.editText6, "field 'etAddress2'", EditText.class);
    target.etLatLng = Utils.findRequiredViewAsType(source, R.id.editText7, "field 'etLatLng'", EditText.class);
    target.etCity = Utils.findRequiredViewAsType(source, R.id.editText8, "field 'etCity'", EditText.class);
    target.etKathano = Utils.findRequiredViewAsType(source, R.id.editText9, "field 'etKathano'", EditText.class);
    target.etFromWhom = Utils.findRequiredViewAsType(source, R.id.editText10, "field 'etFromWhom'", EditText.class);
    target.etToWhom = Utils.findRequiredViewAsType(source, R.id.editText11, "field 'etToWhom'", EditText.class);
    target.etRegdate = Utils.findRequiredViewAsType(source, R.id.editText12, "field 'etRegdate'", EditText.class);
    target.etRegno = Utils.findRequiredViewAsType(source, R.id.editText13, "field 'etRegno'", EditText.class);
    target.btnSubmit = Utils.findRequiredViewAsType(source, R.id.button2, "field 'btnSubmit'", Button.class);
    target.btnDelete = Utils.findRequiredViewAsType(source, R.id.delete, "field 'btnDelete'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etAadharId = null;
    target.etName = null;
    target.etPhone = null;
    target.etPropertyId = null;
    target.etPincode = null;
    target.etAddress1 = null;
    target.etAddress2 = null;
    target.etLatLng = null;
    target.etCity = null;
    target.etKathano = null;
    target.etFromWhom = null;
    target.etToWhom = null;
    target.etRegdate = null;
    target.etRegno = null;
    target.btnSubmit = null;
    target.btnDelete = null;

    this.target = null;
  }
}
