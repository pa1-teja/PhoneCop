package com.venkata.sai.pavan.phonecop.DataMediators;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by KVR on 1/16/2017.
 */

public class ChosenContactsListAdapter extends ArrayAdapter {

    public ChosenContactsListAdapter(Context context, int resource) {
        super(context, resource);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }


}
