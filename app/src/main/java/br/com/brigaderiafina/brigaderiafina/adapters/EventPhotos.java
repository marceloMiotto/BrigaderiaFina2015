package br.com.brigaderiafina.brigaderiafina.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.data.CatalogContract;
import br.com.brigaderiafina.brigaderiafina.data.CatalogDbHelper;


public class EventPhotos {

    public String eventCode;
    public String eventPhotoPath;
    public String eventNumPhotos;
    public String eventPhotosName;


    Context mContext;

    public EventPhotos(Context context) {
        this.mContext = context;
    }

    public EventPhotos(String eventCode, String eventPhotoPath, String eventNumPhotos
            , String eventPhotosName) {
        this.eventCode      = eventCode;
        this.eventPhotoPath = eventPhotoPath;
        this.eventNumPhotos = eventNumPhotos;
        this.eventPhotosName = eventPhotosName;



    }

    public ArrayList<EventPhotos> getEventPhotos(String eventCode) {


        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ArrayList<EventPhotos> eventPhotos = new ArrayList<>();

        /**
         * EventPhotos
         */
        String[] projection = {
                CatalogContract.EventsPhotos.COLUMN_NAME_EVENT_CODE,
                CatalogContract.EventsPhotos.COLUMN_NAME_PHOTOS_NAME,
                CatalogContract.EventsPhotos.COLUMN_NAME_NUM_PHOTOS,
                CatalogContract.EventsPhotos.COLUMN_NAME_PHOTOS_PATH

        };

        String sortOrder =
                CatalogContract.EventsPhotos.COLUMN_NAME_PHOTOS_NAME + " DESC";

        String[] whereClauseValues = {eventCode};

        Cursor c = db.query(
                CatalogContract.EventsPhotos.TABLE_NAME,                     // The table to query
                projection,                                                  // The columns to return
                CatalogContract.EventsPhotos.COLUMN_NAME_EVENT_CODE + "=?",  // The columns for the WHERE clause
                whereClauseValues,                                           // The values for the WHERE clause
                null,                                                        // don't group the rows
                null,                                                        // don't filter by row groups
                sortOrder                                                    // The sort order
        );


        if (c.getCount() != 0) {

            c.moveToFirst();
            if (c.isBeforeFirst()) {
                c.moveToNext();
            }

            do {

                eventCode       = c.getString(c.getColumnIndexOrThrow(CatalogContract.EventsPhotos.COLUMN_NAME_EVENT_CODE));
                eventPhotoPath  = c.getString(c.getColumnIndexOrThrow(CatalogContract.EventsPhotos.COLUMN_NAME_PHOTOS_PATH));
                eventNumPhotos  = c.getString(c.getColumnIndexOrThrow(CatalogContract.EventsPhotos.COLUMN_NAME_NUM_PHOTOS));
                eventPhotosName = c.getString(c.getColumnIndexOrThrow(CatalogContract.EventsPhotos.COLUMN_NAME_PHOTOS_NAME));

                eventPhotos.add(new EventPhotos(eventCode, eventPhotoPath, eventNumPhotos, eventPhotosName));


            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return eventPhotos;
    }


}






