package com.venkata.sai.pavan.phonecop.DataStore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KVR on 1/16/2017.
 */

public class phoneCopDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PhoneCop.db";

    public static final int DATABASE_VERSION = 1;

    public phoneCopDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_MAIN_TABLE = "CREATE TABLE IF NOT EXISTS " +
                phoneCopContract.MainEntry.TABLE_NAME + " (" +
                phoneCopContract.MainEntry._ID + " INTEGER AUTOINCREMENT, " +
                phoneCopContract.MainEntry.COLUMN_LOC_ADDRESS + " TEXT NOT NULL, " +
                phoneCopContract.MainEntry.COLUMN_COORD_LATITUDE + " REAL NOT NULL, " +
                phoneCopContract.MainEntry.COLUMN_COORD_LONGITUDE + " REAL NOT NULL, " +
                phoneCopContract.MainEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                phoneCopContract.MainEntry.COLUMN_TIME_STAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP PRIMARY KEY" +
                ");";

        final String SQL_CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " +
                phoneCopContract.ContactsEntry.TABLE_NAME + " (" +
                phoneCopContract.ContactsEntry._ID + " INTEGER AUTOINCREMENT, " +
                phoneCopContract.ContactsEntry.COLUMN_CONTACT_NAME + " TEXT NOT NULL, " +
                phoneCopContract.ContactsEntry.COLUMN_CONTACT_MOBILE_NUMBER + " INTEGER NOT NULL, " +
                phoneCopContract.ContactsEntry.COLUMN_CONTACT_EMAIL_ADDRESS + " TEXT NOT NULL " +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_MAIN_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        final String DROP_MAIN_TABLE = "DROP TABLE IF EXISTS " + phoneCopContract.MainEntry.TABLE_NAME;
        final String DROP_CONTACTS_TABLE = "DROP TABLE IF EXISTS " + phoneCopContract.ContactsEntry.TABLE_NAME;

        sqLiteDatabase.execSQL(DROP_MAIN_TABLE);
        sqLiteDatabase.execSQL(DROP_CONTACTS_TABLE);
        onCreate(sqLiteDatabase);
    }
}
