package br.com.brigaderiafina.brigaderiafina;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import br.com.brigaderiafina.brigaderiafina.data.CatalogContract;
import br.com.brigaderiafina.brigaderiafina.data.CatalogDbHelper;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class MainActivity extends AppCompatActivity {

    public static String MAIN_LIST_VIEW = "MAIN_LIST_VIEW";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.ToolBarId);
        setSupportActionBar(toolbar);

        /*
        ** Added from http://stackoverflow.com/questions/4776933/android-application-icon-not-showing-up
         */
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        /*
        ** Added End
         */

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainActivityFragment fragment = new MainActivityFragment();
        fragmentTransaction.add(R.id.main_fragment_container, fragment, MAIN_LIST_VIEW);
        fragmentTransaction.commit();

        /**
         * Database Insert Test Begin
         */

        Log.i(Constants.LOG_TAG,"Database Insert Test Begin");

        CatalogDbHelper mDbHelper = new CatalogDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

       /**
        * ModuleVersion
        */
       // Create a new map of values, where column names are the keys
        ContentValues moduleValues = new ContentValues();
        moduleValues.put(CatalogContract.ModuleVersion.COLUMN_NAME_MODULE_NAME, "price_version");
        moduleValues.put(CatalogContract.ModuleVersion.COLUMN_NAME_MODULE_VERSION, "1");
        // Insert the new row, returning the primary key value of the new row
        long newModuleRowId;
        newModuleRowId = db.insert(
                CatalogContract.ModuleVersion.TABLE_NAME,
                CatalogContract.COLUMN_NAME_NULLABLE,
                moduleValues);
        Log.i(Constants.LOG_TAG,"Module Version Row Id" + newModuleRowId);
        /**
         * CatalogSubgroup
         */
        ContentValues catalogSubgroupValues = new ContentValues();
        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_LINE_NAME, Constants.GOURMET_LINE);
        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME, "Nuts");
        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE, "1,95");
        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO, "gourmet_brigadeiros.jpg");

        // Insert the new row, returning the primary key value of the new row
        long newCatalogSubgroupRowId;
        newCatalogSubgroupRowId = db.insert(
                CatalogContract.CatalogSubgroup.TABLE_NAME,
                CatalogContract.COLUMN_NAME_NULLABLE,
                catalogSubgroupValues);
        Log.i(Constants.LOG_TAG,"Catalog Subgroup Row Id" + newCatalogSubgroupRowId);

        /**
         * CatalogSubgroupFlavors
         */
        ContentValues catalogSubgroupFlavorsValues = new ContentValues();
        catalogSubgroupFlavorsValues.put(CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_NAME, "Nuts");
        catalogSubgroupFlavorsValues.put(CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS, "Castanha de Caju");
        // Insert the new row, returning the primary key value of the new row
        long newCatalogSubgroupFlavorsRowId;
        newCatalogSubgroupFlavorsRowId = db.insert(
                CatalogContract.CatalogSubgroupFlavors.TABLE_NAME,
                CatalogContract.COLUMN_NAME_NULLABLE,
                catalogSubgroupFlavorsValues);

        Log.i(Constants.LOG_TAG,"Catalog Subgroup Flavors Row Id" + newCatalogSubgroupFlavorsRowId);
        Log.i(Constants.LOG_TAG,"Database Insert Test End");

        /**
         * Database Insert Test End
         */


        /**
         * Database Select Test Begin
         */
          Log.i(Constants.LOG_TAG,"Database Select Test Begin");
          SQLiteDatabase dbS = mDbHelper.getReadableDatabase();

        /**
         * ModuleVersion
         */
        Log.i(Constants.LOG_TAG, "Module Version");
          String[] projection = {
                  CatalogContract.ModuleVersion.COLUMN_NAME_MODULE_NAME,
                  CatalogContract.ModuleVersion.COLUMN_NAME_MODULE_VERSION
        };


        String sortOrder =
                CatalogContract.ModuleVersion.COLUMN_NAME_MODULE_NAME + " DESC";

        Cursor c = dbS.query(
                CatalogContract.ModuleVersion.TABLE_NAME,  // The table to query
                projection,                                // The columns to return
                null,                                      // The columns for the WHERE clause
                null,                                      // The values for the WHERE clause
                null,                                      // don't group the rows
                null,                                      // don't filter by row groups
                sortOrder                                  // The sort order
        );

        String moduleName;
        String moduleVersion;
        c.moveToFirst();
        while(c.moveToNext()) {
            moduleName    = c.getString(c.getColumnIndexOrThrow(CatalogContract.ModuleVersion.COLUMN_NAME_MODULE_NAME));
            moduleVersion = c.getString(c.getColumnIndexOrThrow(CatalogContract.ModuleVersion.COLUMN_NAME_MODULE_VERSION));
            Log.i(Constants.LOG_TAG,"Module Name:    "+moduleName);
            Log.i(Constants.LOG_TAG,"Module Version: "+moduleVersion);
        }
        c.close();


        /**
         * CatalogSubgroup
         */
        Log.i(Constants.LOG_TAG, "Catalog Subgroup");

        String[] projectionS = {
                CatalogContract.CatalogSubgroup.COLUMN_NAME_LINE_NAME,
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME,
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE,
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO
        };

        String sortOrderS =
                CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME + " DESC";

        Cursor cS = dbS.query(
                CatalogContract.CatalogSubgroup.TABLE_NAME,  // The table to query
                projectionS,                                 // The columns to return
                null,                                        // The columns for the WHERE clause
                null,                                        // The values for the WHERE clause
                null,                                        // don't group the rows
                null,                                        // don't filter by row groups
                sortOrderS                                   // The sort order
        );

        String lineName;
        String subgroupName;
        String subgroupPrice;
        String subgroupPhoto;

        cS.moveToFirst();
        while(cS.moveToNext()) {
            lineName      = cS.getString(cS.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_LINE_NAME));
            subgroupName  = cS.getString(cS.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME));
            subgroupPrice = cS.getString(cS.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE));
            subgroupPhoto = cS.getString(cS.getColumnIndexOrThrow(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO));
            Log.i(Constants.LOG_TAG,"Line Name:      "+lineName);
            Log.i(Constants.LOG_TAG,"Subgroup Name:  "+subgroupName);
            Log.i(Constants.LOG_TAG,"Subgroup Price: "+subgroupPrice);
            Log.i(Constants.LOG_TAG,"Subgroup Photo: "+subgroupPhoto);
        }
        cS.close();


        /**
         * CatalogSubgroupFlavors
         */
        Log.i(Constants.LOG_TAG, "Catalog Subgroup Flavors");

        String[] projectionSF = {
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_NAME,
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS

        };

        String sortOrderSF =
                CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS + " DESC";

        Cursor cSF = dbS.query(
                CatalogContract.CatalogSubgroupFlavors.TABLE_NAME,   // The table to query
                projectionSF,                                        // The columns to return
                null,                                                // The columns for the WHERE clause
                null,                                                // The values for the WHERE clause
                null,                                                // don't group the rows
                null,                                                // don't filter by row groups
                sortOrderSF                                          // The sort order
        );

        String subgroupFlavors;

        cSF.moveToFirst();
        while(cSF.moveToNext()) {
            subgroupName     = cSF.getString(cSF.getColumnIndexOrThrow(CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_NAME));
            subgroupFlavors  = cSF.getString(cSF.getColumnIndexOrThrow(CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS));
            Log.i(Constants.LOG_TAG,"Subgroup Name:    "+subgroupName);
            Log.i(Constants.LOG_TAG,"Subgroup Flavors: "+subgroupFlavors);
        }
        cSF.close();

        Log.i(Constants.LOG_TAG, "Database Select Test End");

        /**
         * Database Select Test End
         */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
