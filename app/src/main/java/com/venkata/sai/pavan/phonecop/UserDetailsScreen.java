package com.venkata.sai.pavan.phonecop;

import android.os.Bundle;
import android.app.Activity;

public class UserDetailsScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_screen);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
