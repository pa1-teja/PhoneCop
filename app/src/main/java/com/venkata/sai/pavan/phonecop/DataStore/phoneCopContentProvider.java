package com.venkata.sai.pavan.phonecop.DataStore;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by KVR on 1/17/2017.
 */

public class phoneCopContentProvider extends ContentProvider {

    private phoneCopDbHelper mPhoneCopDbHelper;
    private Context context;

    public static final int MAIN_TABLE = 500;
    public static final int MAIN_TABLE_WITH_ID = 501;

    public static final int CONTACTS_TABLE = 100;
    public static final int CONTACTS_TABLE_WITH_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);



        uriMatcher.addURI(phoneCopContract.CONTENT_AUTHORITY,phoneCopContract.MainEntry.TABLE_NAME,MAIN_TABLE);
        uriMatcher.addURI(phoneCopContract.CONTENT_AUTHORITY,phoneCopContract.ContactsEntry.TABLE_NAME,CONTACTS_TABLE);

        uriMatcher.addURI(phoneCopContract.CONTENT_AUTHORITY,phoneCopContract.MainEntry.TABLE_NAME + "/#",MAIN_TABLE_WITH_ID);
        uriMatcher.addURI(phoneCopContract.CONTENT_AUTHORITY,phoneCopContract.ContactsEntry.TABLE_NAME + "/#",CONTACTS_TABLE_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {

        context = getContext();

        mPhoneCopDbHelper = new phoneCopDbHelper(context);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase sqLiteDatabase = mPhoneCopDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor returnCursor = null;
        String id,mSelection;
        String[] mSelectionArgs;

        switch (match){
            case MAIN_TABLE:
                returnCursor = sqLiteDatabase.query(phoneCopContract.MainEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case MAIN_TABLE_WITH_ID:
                 id = uri.getPathSegments().get(1);

                 mSelection = "_id=?"; // default _id column.
                 mSelectionArgs = new String[]{id};

                returnCursor = sqLiteDatabase.query(phoneCopContract.MainEntry.TABLE_NAME,
                        projection,
                        mSelection,
                        mSelectionArgs,
                        null,
                        null,
                        sortOrder);

                break;

            case CONTACTS_TABLE:
                returnCursor = sqLiteDatabase.query(phoneCopContract.ContactsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            case CONTACTS_TABLE_WITH_ID:
                id = uri.getPathSegments().get(1);

                mSelection = "_id=?"; // default _id column.
                mSelectionArgs = new String[]{id};

                returnCursor = sqLiteDatabase.query(phoneCopContract.MainEntry.TABLE_NAME,
                        projection,
                        mSelection,
                        mSelectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        returnCursor.setNotificationUri(context.getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase sqLiteDatabase = mPhoneCopDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        long id;
        Uri returnUri = null;

        switch (match){
            case MAIN_TABLE:
                id = sqLiteDatabase.insert(phoneCopContract.MainEntry.TABLE_NAME,null,contentValues);
                if (id > 0){
                    returnUri = ContentUris.withAppendedId(phoneCopContract.MainEntry.CONTENT_URI,id);
                }
                else {
                    throw new android.database.SQLException("Failed to insert a row into the main table : " + uri);
                }
                break;
            case MAIN_TABLE_WITH_ID:
                id = sqLiteDatabase.insert(phoneCopContract.MainEntry.TABLE_NAME,null,contentValues);
                if (id > 0){
                    returnUri = ContentUris.withAppendedId(phoneCopContract.MainEntry.CONTENT_URI,id);
                }
                else {
                    throw new android.database.SQLException("Failed to insert a row into the main table with ID : " + uri);
                }
                break;
            case CONTACTS_TABLE:
                id = sqLiteDatabase.insert(phoneCopContract.ContactsEntry.TABLE_NAME,null,contentValues);
                if (id > 0){
                    returnUri = ContentUris.withAppendedId(phoneCopContract.MainEntry.CONTENT_URI,id);
                }
                else {
                    throw new android.database.SQLException("Failed to insert a row into the contacts table : " + uri);
                }
                break;
            case CONTACTS_TABLE_WITH_ID:
                id = sqLiteDatabase.insert(phoneCopContract.ContactsEntry.TABLE_NAME,null,contentValues);
                if (id > 0){
                    returnUri = ContentUris.withAppendedId(phoneCopContract.MainEntry.CONTENT_URI,id);
                }
                else {
                    throw new android.database.SQLException("Failed to insert a row into the contacts table with ID : " + uri);
                }
                break;
            default:
               throw  new UnsupportedOperationException("Unknown Uri : " + uri);
        }

        context.getContentResolver().notifyChange(uri,null);

        return returnUri;
    }


    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {

        final SQLiteDatabase database = mPhoneCopDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        long insertedRowId=0;
        int rowsInserted=0;

        switch (match){
            case MAIN_TABLE:
                database.beginTransaction();
                try{

                    for (ContentValues value : values){
                        insertedRowId = database.insert(phoneCopContract.MainEntry.TABLE_NAME,null,value);

                        if (insertedRowId != -1)
                            rowsInserted++;
                    }
                    database.setTransactionSuccessful();

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    database.endTransaction();
                }
                break;


            case CONTACTS_TABLE:
                database.beginTransaction();
                try{

                    for (ContentValues value : values){
                        insertedRowId = database.insert(phoneCopContract.ContactsEntry.TABLE_NAME,null,value);

                        if (insertedRowId != -1)
                            rowsInserted++;
                    }
                    database.setTransactionSuccessful();

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    database.endTransaction();
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsInserted > 0)
            context.getContentResolver().notifyChange(uri,null);

        return rowsInserted;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase database = mPhoneCopDbHelper.getWritableDatabase();

        String id;
        int match = sUriMatcher.match(uri);

        int numOfRecordsDeleted;

        switch (match){
            case MAIN_TABLE:
                numOfRecordsDeleted = database.delete(phoneCopContract.MainEntry.TABLE_NAME,null,null);
                break;

            case MAIN_TABLE_WITH_ID:
                id = uri.getPathSegments().get(1);
                numOfRecordsDeleted = database.delete(phoneCopContract.MainEntry.TABLE_NAME, "_id=?", new String[]{id});
                break;

            case CONTACTS_TABLE:
                numOfRecordsDeleted = database.delete(phoneCopContract.ContactsEntry.TABLE_NAME,null,null);
                break;

            case CONTACTS_TABLE_WITH_ID:
                id = uri.getPathSegments().get(1);
                numOfRecordsDeleted = database.delete(phoneCopContract.ContactsEntry.TABLE_NAME,"_id=?",new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (numOfRecordsDeleted != 0)
            context.getContentResolver().notifyChange(uri,null);

        return numOfRecordsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {

        final SQLiteDatabase database = mPhoneCopDbHelper.getWritableDatabase();


        int match = sUriMatcher.match(uri);

        int numOfRecordsUpdated;

        switch (match){
            case MAIN_TABLE:
                numOfRecordsUpdated = database.update(phoneCopContract.MainEntry.TABLE_NAME,contentValues,null,null);
                break;

            case MAIN_TABLE_WITH_ID:
                numOfRecordsUpdated = database.update(phoneCopContract.MainEntry.TABLE_NAME,contentValues,s,strings);
                break;

            case CONTACTS_TABLE:
                numOfRecordsUpdated = database.delete(phoneCopContract.ContactsEntry.TABLE_NAME,null,null);
                break;

            case CONTACTS_TABLE_WITH_ID:

                numOfRecordsUpdated = database.update(phoneCopContract.ContactsEntry.TABLE_NAME,contentValues,s,strings);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (numOfRecordsUpdated != 0)
            context.getContentResolver().notifyChange(uri,null);

        return numOfRecordsUpdated;
    }
}
