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

        /**
         * CatalogSubgroup
         */
        Log.e(Constants.LOG_TAG, "Catalog Subgroup");

        String[] projection = {
                CatalogContract.CatalogSubgroup.COLUMN_NAME_LINE_NAME,
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME,
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE,
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO
        };

        String sortOrder =
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME + " DESC";

        String[] whereClauseValues = {lineName};

        Cursor c = db.query(
                CatalogContract.CatalogSubgroup.TABLE_NAME,                  // The table to query
                projection,                                                  // The columns to return
                CatalogContract.CatalogSubgroup.COLUMN_NAME_LINE_NAME+"=?",  // The columns for the WHERE clause
                whereClauseValues,                                           // The values for the WHERE clause
                null,                                                        // don't group the rows
                null,                                                        // don't filter by row groups
                sortOrder                                                    // The sort order
        );


        if(c.getCount()!=0){

            c.moveToFirst();
            if(c.isBeforeFirst()){
                c.moveToNext();
            }

            do{

                subgroupName = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME));
                subgroupPrice = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE));
                subgroupPhoto = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO));
                subgroups.add(new Subgroups(subgroupName, subgroupPrice, subgroupPhoto));
            }while (c.moveToNext());
        }
        c.close();

        return subgroups;
    }




}
