package br.com.brigaderiafina.brigaderiafina.AsyncTasks;

import android.content.ContentValues;
import android.content.Context;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.brigaderiafina.brigaderiafina.R;
import br.com.brigaderiafina.brigaderiafina.data.CatalogContract;
import br.com.brigaderiafina.brigaderiafina.data.CatalogDbHelper;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class FetchCatalogTask extends AsyncTask<Context, Void, JSONObject> {

    Context mContext;
    String  mType;


    /**
     * http://www.brigaderiafina.com.br/mobile/database/database.php?auth_id=4981bb031dc179bd13edbda08a64dd94&w=???
     * w Options:
     * subgroup: catalog of products and prices (only text)
     * events: photos and description of each event
     */

    FetchCatalogTask(Context context, String type){
        mType = type;
        mContext = context;
    }

    @Override
    protected JSONObject doInBackground(Context... context) {

        JSONFetch jsonFetch = new JSONFetch();

        if(mType.equals(Constants.OWM_SUBGROUP)){
            return jsonFetch.pullJSONData(Constants.OWM_SUBGROUP);
        }else{
            return jsonFetch.pullJSONData(Constants.OWM_EVENTS);
        }

    }

    @Override
    protected void onPostExecute(JSONObject catalogJson){

        String     resultType;

        try {

            resultType = catalogJson.getString(Constants.OWM_TYPE);

            if(resultType.equals(Constants.OWM_SUBGROUP)){
                updateCatalogDB(catalogJson);
            }else{
                updateEventsDB();
            }

        }catch (JSONException e){
            Log.e(Constants.LOG_TAG,e.getMessage());
        }

    }

    protected void updateSharedPref(Context context){


        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.file_key_pref), context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        //Catalog
        String subgroupModuleServer  = sharedPref.getString(context.getString(R.string.subgroup_module_version_server_pref)
                , context.getString(R.string.module_version_default_pref));
        //Events
        String eventsModuleServer = sharedPref.getString(context.getString(R.string.events_module_version_server_pref)
                , context.getString(R.string.module_version_default_pref));


        editor.putString(context.getString(R.string.subgroup_module_version_app_pref),subgroupModuleServer);
        editor.putString(context.getString(R.string.events_module_version_app_pref),eventsModuleServer);

        editor.commit();


    }

    private void updateCatalogDB(JSONObject catalogJson){

        JSONObject lineJSONObject;
        JSONObject groupJSONObject;
        JSONArray  groupJSONArray;
        JSONObject subgroupJSONObject;
        JSONArray  subgroupJSONArray;
        JSONArray  subFlavorJSONArray;

        String lineName;
        String groupName;
        String subgroupName;
        String subgroupPrice;
        String subgroupPhoto;
        String subgroupFlavors;

        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //clean tables to receive new data
        db.delete(CatalogContract.CatalogSubgroupFlavors.TABLE_NAME,null,null);
        db.delete(CatalogContract.CatalogSubgroup.TABLE_NAME,null,null);

        try {

            JSONArray lineJSONArray = catalogJson.getJSONArray(Constants.OWM_LINES);

            /**
             * Line
             */
            for (int i = 0; i < lineJSONArray.length(); i++) {
                lineJSONObject = lineJSONArray.getJSONObject(i);
                lineName       = lineJSONObject.getString(Constants.OWM_LINE_NAME);
                Log.d(Constants.LOG_TAG, "Line Name " + lineJSONObject.getString(Constants.OWM_LINE_NAME));

                /**
                 * Group
                 */
                groupJSONArray = lineJSONObject.getJSONArray(Constants.OWM_GROUPS);

                for (int j = 0; j < groupJSONArray.length(); j++) {
                    groupJSONObject = groupJSONArray.getJSONObject(j);
                    groupName  =  groupJSONObject.getString(Constants.OWM_GROUP_NAME);

                    Log.d(Constants.LOG_TAG, "Group Name: " + groupJSONObject.getString(Constants.OWM_GROUP_NAME));

                    /**
                     * Subgroup
                     */
                    subgroupJSONArray = groupJSONObject.getJSONArray(Constants.OWM_SUBGROUPS);

                    for (int m = 0; m < subgroupJSONArray.length(); m++) {
                        subgroupJSONObject = subgroupJSONArray.getJSONObject(m);
                        subgroupName       = subgroupJSONObject.getString(Constants.OWM_SUBGROUP_NAME);
                        subgroupPrice      = subgroupJSONObject.getString(Constants.OWM_SUBGROUP_PRICE);
                        subgroupPhoto      =  subgroupJSONObject.getString(Constants.OWM_SUBGROUP_PHOTO);

                        /**
                         * Insert Subgroup
                         */
                        ContentValues catalogSubgroupValues = new ContentValues();
                        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_LINE_NAME, lineName);
                        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_GROUP_NAME, groupName);
                        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_NAME, subgroupName);
                        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PRICE, subgroupPrice);
                        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGRUPO_PHOTO, subgroupPhoto);

                        // Insert the new row, returning the primary key value of the new row
                        long newCatalogSubgroupRowId;
                        newCatalogSubgroupRowId = db.insert(
                                CatalogContract.CatalogSubgroup.TABLE_NAME,
                                CatalogContract.COLUMN_NAME_NULLABLE,
                                catalogSubgroupValues);

                        /**
                         * Flavors
                         */

                        subFlavorJSONArray = subgroupJSONObject.getJSONArray(Constants.OWM_FLAVORS);

                        for (int n = 0; n < subFlavorJSONArray.length(); n++) {

                            subgroupFlavors = subFlavorJSONArray.getString(n);

                            /**
                             * Insert Flavours
                             */
                            ContentValues catalogSubgroupFlavorsValues = new ContentValues();
                            catalogSubgroupFlavorsValues.put(CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_NAME,subgroupName);
                            catalogSubgroupFlavorsValues.put(CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGRUPO_FLAVORS, subgroupFlavors);
                            // Insert the new row, returning the primary key value of the new row
                            long newCatalogSubgroupFlavorsRowId;
                            newCatalogSubgroupFlavorsRowId = db.insert(
                                    CatalogContract.CatalogSubgroupFlavors.TABLE_NAME,
                                    CatalogContract.COLUMN_NAME_NULLABLE,
                                    catalogSubgroupFlavorsValues);


                        }
                    }
                }

            }

            //TODO remove comments updateSharedPref(mcontext);

        }catch (JSONException e){
            Log.e(Constants.LOG_TAG,e.getMessage());
        }
    }


    private void updateEventsDB(){
      //TODO events update
    }
}
