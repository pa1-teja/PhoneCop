package com.venkata.sai.pavan.phonecop.SettingsScreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.venkata.sai.pavan.phonecop.R;
import com.venkata.sai.pavan.phonecop.Utilities.utilities;

import java.util.Set;

import static com.venkata.sai.pavan.phonecop.Utilities.utilities.userDetailsValidation;

/**
 * Created by KVR on 1/14/2017.
 */


public class userDetailsDialog extends DialogPreference {


    EditText userName,mobileNumber,emailID;
    private int userDialogLayout = R.layout.user_details_dialog;


    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        userName = (EditText) view.findViewById(R.id.user_name);
        mobileNumber = (EditText) view.findViewById(R.id.user_mobile_number);
        emailID = (EditText) view.findViewById(R.id.email_id);

    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        String[] validResponse = new String[3];

        validResponse[0] = "valid user name";
        validResponse[1] = "valid phone number";
        validResponse[2] = "valid email address";

        String[] validatedResponse = utilities.userDetailsValidation(userName.getText().toString(),
                mobileNumber.getText().toString(),
                emailID.getText().toString());

        if (validatedResponse[0].equals(validResponse[0])
                && validatedResponse[1].equals(validResponse[1])
                && validatedResponse[2].equals(validResponse[2])){ // validating user name, mobile number & email
            /* TODO: check if the new values are same as the values from Google account details also
               TODO: check if the new values are same as old values.
               TODO: if the values are new then override the values of the shared preferences.
             */

        }



        for (String s: validatedResponse) {
            System.out.println("================D : " + s);
        }
    }

    public userDetailsDialog(Context context, AttributeSet attrs) {
        super(context, attrs);

        setDialogLayoutResource(userDialogLayout);
        setDialogIcon(R.drawable.ic_person_black_24dp);
    }


}
