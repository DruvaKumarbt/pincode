// Generated code from Butter Knife. Do not modify!
package com.smart.ticketing.pincode;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.smart.ticketing.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddressInfoActivity_ViewBinding<T extends AddressInfoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public AddressInfoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.tvDetails = Utils.findRequiredViewAsType(source, R.id.tv, "field 'tvDetails'", TextView.class);
    target.btnPlayvideo = Utils.findRequiredViewAsType(source, R.id.playvideo, "field 'btnPlayvideo'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvDetails = null;
    target.btnPlayvideo = null;

    this.target = null;
  }
}
