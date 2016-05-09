package br.com.brigaderiafina.brigaderiafina.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.data.CatalogContract;
import br.com.brigaderiafina.brigaderiafina.data.CatalogDbHelper;

public class Events {
    public String  mEventTitle;
    public String  mEventType;
    public String  mEventMainPhoto;
    public String  mEventCode;
    public Context mContext;
    public String  mDescription;

    public Events(Context context){ mContext = context;}

    public Events(String eventTitle, String eventType, String eventMainPhoto, String eventCode, String eventDescription){
        mEventTitle     = eventTitle;
        mEventType      = eventType;
        mEventMainPhoto = eventMainPhoto;
        mEventCode      = eventCode;
        mDescription    = eventDescription;
    }

    public ArrayList<Events> getEvents(){


        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ArrayList<Events> events = new ArrayList<>();

        /**
         * Events
         */


        String[] projection = {
                CatalogContract.Events.COLUMN_NAME_EVENT_CODE,
                CatalogContract.Events.COLUMN_NAME_EVENT_NAME,
                CatalogContract.Events.COLUMN_NAME_EVENT_MAIN_PHOTO,
                CatalogContract.Events.COLUMN_NAME_EVENT_DESCRIPTION,
                CatalogContract.Events.COLUMN_NAME_EVENT_TYPE
        };

        String sortOrder =
                CatalogContract.Events.COLUMN_NAME_EVENT_CODE + " DESC";

        Cursor c = db.query(
                CatalogContract.Events.TABLE_NAME,   // The table to query
                projection,                          // The columns to return
                null,                                // The columns for the WHERE clause
                null,                                // The values for the WHERE clause
                null,                                // don't group the rows
                null,                                // don't filter by row groups
                sortOrder                            // The sort order
        );


        if(c.getCount()!=0){

            c.moveToFirst();
            if(c.isBeforeFirst()){
                c.moveToNext();
            }

            do{
                   mEventCode      = c.getString(c.getColumnIndexOrThrow(CatalogContract.Events.COLUMN_NAME_EVENT_CODE));
                   mEventTitle     = c.getString(c.getColumnIndexOrThrow(CatalogContract.Events.COLUMN_NAME_EVENT_NAME));
                   mEventType      = c.getString(c.getColumnIndexOrThrow(CatalogContract.Events.COLUMN_NAME_EVENT_TYPE));
                   mEventMainPhoto = c.getString(c.getColumnIndexOrThrow(CatalogContract.Events.COLUMN_NAME_EVENT_MAIN_PHOTO));
                   mDescription    = c.getString(c.getColumnIndexOrThrow(CatalogContract.Events.COLUMN_NAME_EVENT_DESCRIPTION));
                   events.add(new Events(mEventTitle, mEventType, mEventMainPhoto, mEventCode, mDescription));


            }while (c.moveToNext());
        }
        c.close();
        db.close();
        return events;
    }



}