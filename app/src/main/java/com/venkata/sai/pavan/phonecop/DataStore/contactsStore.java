package com.venkata.sai.pavan.phonecop.DataStore;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.util.Patterns;
import android.widget.ArrayAdapter;

import com.venkata.sai.pavan.phonecop.SettingsScreen.SettingsActivity;

import java.security.Permission;
import java.util.ArrayList;

/**
 * Created by KVR on 1/18/2017.
 */

public class contactsStore {

    private static final String TAG = "Contacts Store Class";

    public static ArrayList<String> getAllContactNames(Context context) {
       ContentResolver resolver = context.getContentResolver();

        ArrayList<String> allContactsNames = new ArrayList<String>();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    allContactsNames.add(name);
                }

                int contactNulls=0;

                for (String contactName : allContactsNames) {
                    if (contactName != null)
                    Log.e(TAG,contactName);
                    else
                        contactNulls++;
                }

                Log.e(TAG, String.valueOf(contactNulls));

            }

            cursor.close();

        return allContactsNames;
        }

    public static String getSpecificContactEmailID(String contactName, Context context){
        ContentResolver resolver =  context.getContentResolver();

        String contactEmailAddress = null;
        Cursor cursor = null;

        cursor = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Email.DATA},
                ContactsContract.Contacts.DISPLAY_NAME + "=?", new String[]{contactName},null);

        if (cursor.getCount() >0)
        {
            cursor.moveToFirst();

            do {
                    contactEmailAddress = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            }while (cursor.moveToNext());
        }

        Log.d(TAG, contactName + " " + contactEmailAddress);
        cursor.close();

        if (contactEmailAddress == null)
            return "This contact doesn't have a Email Address.";
        else {

            if(!Patterns.EMAIL_ADDRESS.matcher(contactEmailAddress).matches())
                return  "invalid email address";
            else
             return contactEmailAddress;
        }

    }

    public static ArrayList<String> getSearchStringContacts(String contactString,Context context){
        ContentResolver resolver = context.getContentResolver();
        ArrayList<String> contacts = new ArrayList<>();


        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME},
                ContactsContract.PhoneLookup.DISPLAY_NAME +  " like ?", new String[]{ "%" + contactString + "%"},
                ContactsContract.PhoneLookup.DISPLAY_NAME + " ASC");
        String name;
        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            do {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                if (name != null && !Patterns.EMAIL_ADDRESS.matcher(name).matches())
                contacts.add(name);
            }while (cursor.moveToNext());
        }

        for (String s : contacts) {
            Log.d(TAG,s);
        }



        return contacts;
    }

    public static String getSpecificContactNumber(String contactName, Context context){
        ContentResolver resolver =  context.getContentResolver();

        String contactNumber = null;
        Cursor cursor;

        cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                ContactsContract.Contacts.DISPLAY_NAME + "=?", new String[]{contactName},null);

        if (cursor.getCount() >0)
        {
            cursor.moveToFirst();
            contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        Log.d(TAG, contactName + " " + contactNumber);


        if (contactNumber == null)
            return "This contact doesn't have a contact number.";
        else if (contactNumber.length() !=10)
            return  "invalid contact number";

        cursor.close();

        return contactNumber;
    }
}

