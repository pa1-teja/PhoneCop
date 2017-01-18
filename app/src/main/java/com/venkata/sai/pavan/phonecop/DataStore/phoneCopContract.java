package com.venkata.sai.pavan.phonecop.DataStore;


import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by KVR on 1/16/2017.
 */

public class phoneCopContract {


    private phoneCopContract() {
    }

    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    public static final String CONTENT_AUTHORITY = "com.venkata.sai.pavan.phonecop";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String PATH_TO_CONTACTS_TABLE = "contacts_list";

    public static final String PATH_TO_MAIN_TABLE = "main_info";




    public static final class ContactsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TO_CONTACTS_TABLE).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TO_CONTACTS_TABLE ;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TO_CONTACTS_TABLE + "/#";

        public static final String TABLE_NAME = "contacts";

        public static final String COLUMN_CONTACT_NAME = "name";

        public static final String COLUMN_CONTACT_MOBILE_NUMBER = "mobile_number";

        public static final String COLUMN_CONTACT_EMAIL_ADDRESS = "email_address";
    }

    public static final class MainEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TO_MAIN_TABLE).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TO_MAIN_TABLE;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TO_MAIN_TABLE + "/#";

        public static final String TABLE_NAME = "main_table";

        public static final String COLUMN_LOC_ADDRESS = "location_address";

        public static final String COLUMN_COORD_LONGITUDE = "coord_long";

        public static final String COLUMN_COORD_LATITUDE = "coord_lat";

        public static final String COLUMN_DATE = "date";

        public static final String COLUMN_TIME_STAMP = "time_stamp";

    }
}
