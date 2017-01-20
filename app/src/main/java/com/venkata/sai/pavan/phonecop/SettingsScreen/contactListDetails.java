package com.venkata.sai.pavan.phonecop.SettingsScreen;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.preference.DialogPreference;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.venkata.sai.pavan.phonecop.DataStore.contactsStore;
import com.venkata.sai.pavan.phonecop.R;
import com.venkata.sai.pavan.phonecop.Utilities.utilities;

import java.util.ArrayList;

import static com.venkata.sai.pavan.phonecop.DataStore.contactsStore.getAllContactNames;

/**
 * Created by KVR on 1/15/2017.
 */

public class contactListDetails extends DialogPreference implements ContactListRecyclerViewAdapter.ListItemClickListener {

    TextView contactName;
    RecyclerView chosenContactsRecyclerView;
    LinearLayoutManager linearLayoutManager;
    ContactListRecyclerViewAdapter contactListRecyclerViewAdapter;

    public static boolean contactsPermission = false;

    private SearchView searchView;
    private Button addContactsButton;

    public int NUM_OF_LIST_ITEMS_IN_RECYCLER_VIEW_ONSCREEN = 100;
    public static int MY_PERMISSIONS_REQUEST_READ_CONTACTS;

    public contactListDetails(Context context, AttributeSet attrs) {
        super(context, attrs);

        setDialogLayoutResource(R.layout.chosen_contact_list_dialog);
        setDialogIcon(null);

    }

    @Override
    protected void onBindDialogView(final View view) {
        super.onBindDialogView(view);

        // TODO : ERROR while requesting permission to read contacts
        final ArrayList<String> contactNames;

        searchView = (SearchView) view.findViewById(R.id.contact_search_text);
        searchView.setVisibility(View.GONE);

        addContactsButton = (Button) view.findViewById(R.id.add_contacts_button);

        contactNames = getAllContactNames(getContext());
        NUM_OF_LIST_ITEMS_IN_RECYCLER_VIEW_ONSCREEN = contactNames.size();


        contactName = (TextView) view.findViewById(R.id.contact_name_in_contacts_list_dialog);

        chosenContactsRecyclerView = (RecyclerView) view.findViewById(R.id.chosen_contacts_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        chosenContactsRecyclerView.setHasFixedSize(true);

        contactListRecyclerViewAdapter = new ContactListRecyclerViewAdapter(NUM_OF_LIST_ITEMS_IN_RECYCLER_VIEW_ONSCREEN, this, contactNames);

        chosenContactsRecyclerView.setLayoutManager(linearLayoutManager);
        chosenContactsRecyclerView.setAdapter(contactListRecyclerViewAdapter);

        addContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setVisibility(View.VISIBLE);
                addContactsButton.setVisibility(View.GONE);
            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(view.getContext(), query,
                        Toast.LENGTH_SHORT).show();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                contactNames.clear();
                contactNames.addAll(contactsStore.getSearchStringContacts(newText,view.getContext()));
                NUM_OF_LIST_ITEMS_IN_RECYCLER_VIEW_ONSCREEN = contactNames.size();
                contactListRecyclerViewAdapter.notifyDataSetChanged();

                return true;
            }
        });


    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        // TODO :
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {

    }



}
