package com.venkata.sai.pavan.phonecop;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.preference.DialogPreference;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.venkata.sai.pavan.phonecop.Utilities.utilities;

/**
 * Created by KVR on 1/15/2017.
 */

public class contactListDetails extends DialogPreference {

    TextView contactName;
    ImageButton addOrRemoveContactImageButton;
    ListView chosenContactsListView;

    public contactListDetails(Context context, AttributeSet attrs) {
        super(context, attrs);

        setDialogLayoutResource(R.layout.chosen_contact_list_dialog);
        setDialogIcon(null);

    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        // TODO : ERROR while requesting permission to read contacts

//        SettingsActivity settingsActivity = new SettingsActivity();

        if(ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
        utilities.getAllContactsFromContactsApp(getContext());
        else if(ActivityCompat.shouldShowRequestPermissionRationale(new SettingsActivity(),
                    Manifest.permission.READ_CONTACTS))
            utilities.getAllContactsFromContactsApp(getContext());



        contactName = (TextView) view.findViewById(R.id.contact_name);
        addOrRemoveContactImageButton = (ImageButton) view.findViewById(R.id.add_or_remove_image);

        chosenContactsListView = (ListView) view.findViewById(R.id.chosen_contacts_list_view);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        // TODO :
    }


}
