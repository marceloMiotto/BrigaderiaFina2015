package br.com.brigaderiafina.brigaderiafina.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CatalogDbHelper extends SQLiteOpenHelper{

    public static final int    DATABASE_VERSION = 3;
    public static final String DATABASE_NAME    = "Catalog.db";

    public CatalogDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CatalogSQLHelper.SQL_CREATE_CATALOG_SUBGROUPS);
        db.execSQL(CatalogSQLHelper.SQL_CREATE_CATALOG_SUBGROUP_FLAVORS);
        db.execSQL(CatalogSQLHelper.SQL_CREATE_EVENTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /**
         * CatalogSubgroup
         */
         db.execSQL(CatalogSQLHelper.SQL_DROP_TABLE_CATALOG_SUBGROUPS);
        /**
         * CatalogSubgroupFlavors
         */
        db.execSQL(CatalogSQLHelper.SQL_DROP_TABLE_CATALOG_SUBGROUP_FLAVORS);
        /**
         * Events
         */
        db.execSQL(CatalogSQLHelper.SQL_DROP_TABLE_EVENTS);

        onCreate(db);

    }
}
