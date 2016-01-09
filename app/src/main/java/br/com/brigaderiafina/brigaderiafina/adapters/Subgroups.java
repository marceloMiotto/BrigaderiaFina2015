package br.com.brigaderiafina.brigaderiafina.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.data.CatalogContract;
import br.com.brigaderiafina.brigaderiafina.data.CatalogDbHelper;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class Subgroups {

    public String subgroupName;
    public String subgroupPrice;
    public String subgroupPhoto;
    Context mContext;

    public Subgroups(Context context){
        this.mContext      = context;
    }

    public Subgroups(String subgroupName, String subgroupPrice, String subgroupPhoto){
        this.subgroupName  = subgroupName;
        this.subgroupPrice = subgroupPrice;
        this.subgroupPhoto = subgroupPhoto;


    }

    public ArrayList<Subgroups> getSubgroups(String lineName){


        if(mContext != null){
            Log.e(Constants.LOG_TAG,"Context up!");
        }
        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ArrayList<Subgroups> subgroups = new ArrayList<Subgroups>();

        Log.e(Constants.LOG_TAG, "Database Select Test Begin");


        /**
         * CatalogSubgroup
         */
        Log.e(Constants.LOG_TAG, "Catalog Subgroup");

        String[] projection = {
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME,
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE,
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO
        };

        String sortOrder =
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME + " DESC";

        Log.e(Constants.LOG_TAG, "Where Clause Begin");
        String[] whereClauseValues = {lineName};

        Log.e(Constants.LOG_TAG, "Where Clause End");
        Log.e(Constants.LOG_TAG, "line name " + lineName);
        Cursor c = db.query(
                CatalogContract.CatalogSubgroup.TABLE_NAME,            // The table to query
                projection,                                            // The columns to return
                "line=?",                                              // The columns for the WHERE clause
                whereClauseValues,                                     // The values for the WHERE clause
                null,                                                  // don't group the rows
                null,                                                  // don't filter by row groups
                sortOrder                                              // The sort order
        );

        Log.e(Constants.LOG_TAG, "Move to first");
        c.moveToFirst();
        Log.e(Constants.LOG_TAG, "Start Loop");
        while (c.moveToNext()) {
            subgroupName = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME));
            subgroupPrice = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE));
            subgroupPhoto = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO));

            Log.e(Constants.LOG_TAG, "Subgroup Name:  " + subgroupName);
            Log.e(Constants.LOG_TAG, "Subgroup Price: " + subgroupPrice);
            Log.e(Constants.LOG_TAG, "Subgroup Photo: " + subgroupPhoto);

            subgroups.add(new Subgroups(subgroupName,subgroupPrice,subgroupPhoto));
        }
        c.close();



        return subgroups;
    }




}
