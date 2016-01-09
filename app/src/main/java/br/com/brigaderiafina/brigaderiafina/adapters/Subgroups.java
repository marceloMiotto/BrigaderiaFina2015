package br.com.brigaderiafina.brigaderiafina.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.data.CatalogContract;
import br.com.brigaderiafina.brigaderiafina.data.CatalogDbHelper;

public class Subgroups {

    private String subgroupName;
    private String subgroupPrice;
    private String subgroupPhoto;
    Context mContext;

    CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
    SQLiteDatabase db = mDbHelper.getWritableDatabase();

    public Subgroups(Context context,String subgroupName, String subgroupPrice, String subgroupPhoto){
        this.subgroupName  = subgroupName;
        this.subgroupPrice = subgroupPrice;
        this.subgroupPhoto = subgroupPhoto;
        this.mContext      = context;

    }

    public ArrayList<Subgroups> getSubgroups(String lineName){


        ArrayList<Subgroups> subgroups = new ArrayList<Subgroups>();

        //Log.i(Constants.LOG_TAG, "Database Select Test Begin");
        SQLiteDatabase dbS = mDbHelper.getReadableDatabase();

        /**
         * CatalogSubgroup
         */
        //Log.i(Constants.LOG_TAG, "Catalog Subgroup");

        String[] projection = {
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME,
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE,
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO
        };

        String sortOrder =
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME + " DESC";

        String[] whereClauseValues = {lineName};

        Cursor c = db.query(
                CatalogContract.CatalogSubgroup.TABLE_NAME,            // The table to query
                projection,                                            // The columns to return
                CatalogContract.CatalogSubgroup.COLUMN_NAME_LINE_NAME, // The columns for the WHERE clause
                null,                                                  // The values for the WHERE clause
                null,                                                  // don't group the rows
                null,                                                  // don't filter by row groups
                sortOrder                                              // The sort order
        );


        c.moveToFirst();
        while (c.moveToNext()) {
            subgroupName = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME));
            subgroupPrice = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE));
            subgroupPhoto = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO));
            //Log.i(Constants.LOG_TAG, "Line Name:      " + lineName);
            //Log.i(Constants.LOG_TAG, "Subgroup Name:  " + subgroupName);
            //Log.i(Constants.LOG_TAG, "Subgroup Price: " + subgroupPrice);
            //Log.i(Constants.LOG_TAG, "Subgroup Photo: " + subgroupPhoto);

            subgroups.add(new Subgroups(mContext,subgroupName,subgroupPrice,subgroupPhoto));
        }
        c.close();



        return subgroups;
    }




}
