// Generated code from Butter Knife. Do not modify!
package com.smart.ticketing.backendless;

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

public class RegisterActivity_ViewBinding<T extends RegisterActivity> implements Unbinder {
  protected T target;

  @UiThread
  public RegisterActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.etUsernmae = Utils.findRequiredViewAsType(source, R.id.editText, "field 'etUsernmae'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.editText2, "field 'etPassword'", EditText.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.editText3, "field 'etName'", EditText.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.editText4, "field 'etPhone'", EditText.class);
    target.etAddress = Utils.findRequiredViewAsType(source, R.id.editText5, "field 'etAddress'", EditText.class);
    target.etLocality = Utils.findRequiredViewAsType(source, R.id.editText7, "field 'etLocality'", EditText.class);
    target.etBloodGroup = Utils.findRequiredViewAsType(source, R.id.editText8, "field 'etBloodGroup'", EditText.class);
    target.etDepartment = Utils.findRequiredViewAsType(source, R.id.editText6, "field 'etDepartment'", EditText.class);
    target.btnSubmit = Utils.findRequiredViewAsType(source, R.id.button2, "field 'btnSubmit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etUsernmae = null;
    target.etPassword = null;
    target.etName = null;
    target.etPhone = null;
    target.etAddress = null;
    target.etLocality = null;
    target.etBloodGroup = null;
    target.etDepartment = null;
    target.btnSubmit = null;

    this.target = null;
  }
}
