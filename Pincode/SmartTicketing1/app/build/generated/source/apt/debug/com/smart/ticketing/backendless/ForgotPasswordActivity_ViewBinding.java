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

public class ForgotPasswordActivity_ViewBinding<T extends ForgotPasswordActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ForgotPasswordActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.etUsernmae = Utils.findRequiredViewAsType(source, R.id.editText, "field 'etUsernmae'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.editText2, "field 'etPassword'", EditText.class);
    target.btnLogin = Utils.findRequiredViewAsType(source, R.id.button1, "field 'btnLogin'", Button.class);
    target.btnRegister = Utils.findRequiredViewAsType(source, R.id.button2, "field 'btnRegister'", Button.class);
    target.btnForgotpassword = Utils.findRequiredViewAsType(source, R.id.forgotpassword, "field 'btnForgotpassword'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etUsernmae = null;
    target.etPassword = null;
    target.btnLogin = null;
    target.btnRegister = null;
    target.btnForgotpassword = null;

    this.target = null;
  }
}
