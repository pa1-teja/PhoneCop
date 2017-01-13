package com.venkata.sai.pavan.phonecop;

import android.os.Bundle;
import android.app.Activity;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import java.util.prefs.Preferences;

public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_activity); // attached the preference screen.

    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        return false;
    }
}
