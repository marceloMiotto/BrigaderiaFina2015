package br.com.brigaderiafina.brigaderiafina.adapters;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.data.CatalogContract;
import br.com.brigaderiafina.brigaderiafina.data.CatalogDbHelper;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class Flavours {

    String  flavours;
    Context mContext;
    String  subgroupFlavour;

    public Flavours(Context context){
        this.mContext = context;
    }
    public Flavours(String flavours){
        this.flavours = flavours;
    }

    public ArrayList<Flavours> getFlavors(String subgroupName){

        ArrayList<Flavours> flavours = new ArrayList<Flavours>();

        if(mContext != null){
            Log.e(Constants.LOG_TAG, "Context up!");
        }
        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        Log.e(Constants.LOG_TAG, "Database Select Test Begin");


        /**
         * CatalogSubgroupFlavours
         */
        Log.e(Constants.LOG_TAG, "Catalog Subgroup Flavours");

        String[] projection = {
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_NAME,
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS
        };

        String sortOrder =
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS + " DESC";

        Log.e(Constants.LOG_TAG, "Where Clause Begin");
        String[] whereClauseValues = {subgroupName};

        Log.e(Constants.LOG_TAG, "Where Clause End");
        Log.e(Constants.LOG_TAG, "subgroup name " + subgroupName);
        Cursor c = db.query(
                CatalogContract.CatalogSubgroupFlavors.TABLE_NAME,                  // The table to query
                projection,                                                  // The columns to return
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_NAME+"=?",  // The columns for the WHERE clause
                whereClauseValues,                                           // The values for the WHERE clause
                null,                                                        // don't group the rows
                null,                                                        // don't filter by row groups
                sortOrder                                                    // The sort order
        );

        Log.e(Constants.LOG_TAG, "Move to first");
        if(c.getCount()!=0){

            c.moveToFirst();
            if(c.isBeforeFirst()){
                c.moveToNext();
            }
            Log.e(Constants.LOG_TAG, "Start Loop");
            do{
                subgroupFlavour = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS));

                Log.e(Constants.LOG_TAG, "Subgroup Flavour: " + subgroupFlavour);

                flavours.add(new Flavours(subgroupFlavour));
            }while (c.moveToNext());
        }
        c.close();

        return flavours;
    }
}
