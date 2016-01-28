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

    public String  flavours;
    Context mContext;
    String  subgroupFlavour;

    public Flavours(Context context){
        this.mContext = context;
    }
    public Flavours(String flavours){
        this.flavours = flavours;
    }

    public ArrayList<Flavours> getFlavors(String subgroupName){

        ArrayList<Flavours> flavoursArrayList = new ArrayList<Flavours>();

        if(mContext != null){
            Log.e(Constants.LOG_TAG, "Context up!");
        }
        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        /**
         * CatalogSubgroupFlavours
         */

        String[] projection = {
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_NAME,
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS
        };

        String sortOrder =
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS + " DESC";


        String[] whereClauseValues = {subgroupName};


        Cursor c = db.query(
                CatalogContract.CatalogSubgroupFlavors.TABLE_NAME,                  // The table to query
                projection,                                                  // The columns to return
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_NAME+"=?",  // The columns for the WHERE clause
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
                subgroupFlavour = c.getString(c.getColumnIndexOrThrow(CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS));
                flavoursArrayList.add(new Flavours(subgroupFlavour));

            }while (c.moveToNext());
        }
        c.close();

        return flavoursArrayList;
    }
}
