
package com.smart.ticketing.globalnest.result;


import android.app.Activity;

import com.google.zxing.client.result.EmailAddressParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.smart.ticketing.R;

/**
 * Handles email addresses.
 * 
 * 
 */
public final class EmailAddressResultHandler extends ResultHandler {

    public EmailAddressResultHandler(Activity activity, ParsedResult result) {
        super(activity, result);
    }

    @Override
    public CharSequence getDisplayContents() {
        EmailAddressParsedResult result = (EmailAddressParsedResult) getResult();
        StringBuilder contents = new StringBuilder(100);
        ParsedResult.maybeAppend(result.getEmailAddress(), contents);
        contents.trimToSize();
        return contents.toString();
    }

    @Override
    public int getDisplayTitle() {
        return R.string.result_email_address;
    }
}
