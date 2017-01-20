package com.venkata.sai.pavan.phonecop.Utilities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Patterns;
import android.view.View;

import com.venkata.sai.pavan.phonecop.R;

import java.util.ArrayList;

/**
 * Created by KVR on 1/14/2017.
 */

public class utilities {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static SharedPreferences timerPref,triggerPref;
    private static String timePrefValue, triggerPrefValue;

    public static void appSettingsInfo(Context context){

        timerPref = PreferenceManager.getDefaultSharedPreferences(context);
        triggerPref = PreferenceManager.getDefaultSharedPreferences(context);

        timePrefValue =timerPref.getString(context.getString(R.string.set_timer_key),
                context.getString(R.string.set_timer_3_minutes)); // second argument is default value

        triggerPrefValue = triggerPref.getString(context.getString(R.string.set_trigger_key),
                context.getString(R.string.set_trigger_as_power_button_3_taps));// second argument is default value
    }


    public static void userDetailsInfo(){
        // Todo: hold the user details settings values in the SQLite database here.

    }



    public static String[] userDetailsValidation(String userName, String mobileNumber, String emailId){

        String[] validationResults = new String[3];



        if (userName.length() == 0)
            validationResults[0] = "user name is empty";
        else if (userName.length() >= 5)
            validationResults[0] = "valid user name";
        else
            validationResults[0] = "invalid username, it should be 5 or more chars";



        if (mobileNumber.length() == 0)
            validationResults[1] = "phone number is empty";
        else if (mobileNumber.length() !=10)
            validationResults[1] = "invalid phone number";
        else
            validationResults[1] = "valid phone number";




        if (emailId.length() == 0)
            validationResults[2] = "email ID is empty";
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailId).matches())
            validationResults[2] = "invalid email address";
        else
            validationResults[2]= "valid email address";





        return validationResults;
    }

}
