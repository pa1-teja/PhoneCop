package com.venkata.sai.pavan.phonecop.SettingsScreen;

import android.content.Context;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.venkata.sai.pavan.phonecop.R;

/**
 * Created by KVR on 1/19/2017.
 */

public class eachEmergencyContactDetails extends DialogPreference {

    TextView eachContactName,eachContactMobileNumber,eachContactEmailAddress;

    public eachEmergencyContactDetails(Context context, AttributeSet attrs) {
        super(context, attrs);

        setLayoutResource(R.layout.each_emergency_contact_details_dialog);
        setDialogIcon(null);
    }


    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        eachContactName = (TextView) view.findViewById(R.id.each_contact_name);
        eachContactMobileNumber = (TextView) view.findViewById(R.id.each_user_mobile_number);
        eachContactEmailAddress = (TextView) view.findViewById(R.id.each_contact_email_id);


    }

}
