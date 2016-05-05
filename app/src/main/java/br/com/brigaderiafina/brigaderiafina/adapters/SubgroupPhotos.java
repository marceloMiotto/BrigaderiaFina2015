package br.com.brigaderiafina.brigaderiafina.adapters;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.data.CatalogContract;
import br.com.brigaderiafina.brigaderiafina.data.CatalogDbHelper;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class SubgroupPhotos {

    public String subgroupLine;
    public String subgroupName;
    public String subgroupPhotoPath;
    public String subgroupNumPhoto;
    public String subgroupPhotos;

    Context mContext;

    public SubgroupPhotos(Context context) {
        this.mContext = context;
    }

    public SubgroupPhotos(String subgroupLine, String subgroupName, String subgroupPhotoPath
                        , String subgroupPhotos , String subgroupNumPhoto) {
        this.subgroupLine      = subgroupLine;
        this.subgroupName      = subgroupName;
        this.subgroupPhotoPath = subgroupPhotoPath;
        this.subgroupPhotos    = subgroupPhotos;
        this.subgroupNumPhoto  = subgroupNumPhoto;


    }

    public ArrayList<SubgroupPhotos> getSubgroupPhotos(String lineName, String subgroupName) {


        if (mContext != null) {
            Log.e(Constants.LOG_TAG, "Context up!");
        }
        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ArrayList<SubgroupPhotos> subgroupPhoto = new ArrayList<>();

        /**
         * CatalogSubgroupPhotos
         */
        Log.e(Constants.LOG_TAG, "Catalog Subgroup Photos");

        String[] projection = {
                CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_LINE,
                CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_SUBGROUP_NAME,
                CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_PHOTOS_PATH,
                CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_NUM_PHOTOS,
                CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_PHOTOS_NAME
        };

        String sortOrder =
                CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_SUBGROUP_NAME + " DESC";

        String[] whereClauseValues = {lineName , subgroupName};

        Log.e("Debug3",CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_LINE + "=? AND "+ CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_SUBGROUP_NAME + "=?");

        Cursor c = db.query(
                CatalogContract.CatalogSubgroupPhotos.TABLE_NAME,            // The table to query
                projection,                                                  // The columns to return
                CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_LINE + "=? AND "+ CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_SUBGROUP_NAME + "=?",  // The columns for the WHERE clause
                whereClauseValues,                                           // The values for the WHERE clause
                null,                                                        // don't group the rows
                null,                                                        // don't filter by row groups
                sortOrder                                                    // The sort order
        );


        if (c.getCount() != 0) {

            Log.e("Debug3","found subgroupphotos");

            c.moveToFirst();
            if (c.isBeforeFirst()) {
                c.moveToNext();
            }

            do {

                subgroupLine      = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_LINE));
                subgroupName      = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_SUBGROUP_NAME));
                subgroupPhotoPath = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_PHOTOS_PATH));
                subgroupPhotos    = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_PHOTOS_NAME));
                subgroupNumPhoto  = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_NUM_PHOTOS));




                subgroupPhoto.add(new SubgroupPhotos(subgroupLine, subgroupName, subgroupPhotoPath,subgroupPhotos,subgroupNumPhoto));

                Log.e("Debug99", subgroupLine);
                Log.e("Debug99", subgroupName);
                Log.e("Debug99", subgroupPhotoPath);
                Log.e("Debug99", subgroupPhotos);
                Log.e("Debug99", subgroupNumPhoto);

            } while (c.moveToNext());
        }
        c.close();

        return subgroupPhoto;
    }


}


