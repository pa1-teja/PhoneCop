package com.venkata.sai.pavan.phonecop.SettingsScreen;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.venkata.sai.pavan.phonecop.R;

import java.util.prefs.Preferences;

import static android.R.attr.key;

public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener,
        SharedPreferences.OnSharedPreferenceChangeListener {


    public static SettingsActivity settingsActivity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_activity); // attached the preference screen.
        settingsActivity = this;
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {


        return true;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }

}
