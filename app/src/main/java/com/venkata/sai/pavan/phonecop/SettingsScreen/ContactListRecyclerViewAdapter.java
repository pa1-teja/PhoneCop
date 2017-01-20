package com.venkata.sai.pavan.phonecop.SettingsScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.venkata.sai.pavan.phonecop.DataStore.contactsStore;
import com.venkata.sai.pavan.phonecop.R;

import java.util.ArrayList;

import static com.venkata.sai.pavan.phonecop.DataStore.contactsStore.getSpecificContactEmailID;
import static com.venkata.sai.pavan.phonecop.DataStore.contactsStore.getSpecificContactNumber;

/**
 * Created by KVR on 1/18/2017.
 */

public class ContactListRecyclerViewAdapter extends RecyclerView.Adapter<ContactListRecyclerViewAdapter.ContactsViewHolder> {

    private int numberOfContacts;

    final private ListItemClickListener mListItemClickListener;

    private ArrayList<String> contactNames;

    public ContactListRecyclerViewAdapter(int numberOfContacts,ListItemClickListener mListItemClickListener, ArrayList<String> contactNames) {
        this.numberOfContacts = numberOfContacts;
        this.mListItemClickListener = mListItemClickListener;
        this.contactNames = contactNames;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForContactsListItem = R.layout.contacts_list_layout_for_dialogs_recycler_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;


        View view = inflater.inflate(layoutIdForContactsListItem,parent, shouldAttachToParentImmediately);
        ContactsViewHolder contactsViewHolder = new ContactsViewHolder(view);

        return contactsViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberOfContacts;
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView contactName;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            contactName = (TextView) itemView.findViewById(R.id.contact_name_in_contacts_list_dialog);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex){

            contactName.setText(contactNames.get(listIndex));
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mListItemClickListener.onListItemClick(clickedPosition);
            String name = (String) contactName.getText();
            String number =  getSpecificContactNumber(name,view.getContext());
            String email  =  getSpecificContactEmailID(name,view.getContext());

            Toast toast = new Toast(view.getContext());
            boolean notAValidNumber = false;
            boolean notAValidEmail = false;
            if (email.equals("This contact doesn't have a Email Address.") || email.equals("invalid email address"))
                notAValidEmail = true;
            if (number.equals("This contact doesn't have a contact number.") || number.equals("invalid contact number"))
                notAValidNumber = true;

            if (notAValidNumber | notAValidEmail){
                if (toast != null)
                    toast.cancel();

                toast = toast.makeText(view.getContext(),"Sorry, This contact doesn't have a valid contact details", Toast.LENGTH_LONG);
                toast.show();
            }else {
                if (toast != null)
                    toast.cancel();
                toast.makeText(view.getContext(),"Details can be added.", Toast.LENGTH_LONG).show();
            }

        }
    }

}
