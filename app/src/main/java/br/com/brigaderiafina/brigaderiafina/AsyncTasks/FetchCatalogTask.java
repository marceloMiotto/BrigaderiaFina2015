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
    String mType;


    /**
     * http://www.brigaderiafina.com.br/mobile/database/database.php?auth_id=4981bb031dc179bd13edbda08a64dd94&w=???
     * w Options:
     * subgroup: catalog of products and prices (only text)
     * events: photos and description of each event
     */

    FetchCatalogTask(Context context, String type) {
        mType = type;
        mContext = context;
    }

    @Override
    protected JSONObject doInBackground(Context... context) {

        JSONFetch jsonFetch = new JSONFetch();

        switch (mType){
            case Constants.OWM_SUBGROUP:
                return jsonFetch.pullJSONData(Constants.OWM_SUBGROUP);


            case Constants.OWM_SUBGROUP_PHOTO:
                return jsonFetch.pullJSONData(Constants.OWM_SUBGROUP_PHOTO);


            case Constants.OWM_EVENTS:
                return jsonFetch.pullJSONData(Constants.OWM_EVENTS);


            case Constants.OWM_EVENT_PHOTOS:
                return jsonFetch.pullJSONData(Constants.OWM_EVENT_PHOTOS);

            default:
                return jsonFetch.pullJSONData(Constants.OWM_SUBGROUP);

        }

    }

    @Override
    protected void onPostExecute(JSONObject catalogJson) {

        String resultType;

        try {

            resultType = catalogJson.getString(Constants.OWM_TYPE);

            Log.e("Debug50","result type "+resultType);

            switch (resultType) {
                case Constants.OWM_SUBGROUP:
                    updateCatalogDB(catalogJson);
                    Log.e("Debug50","Catalogo Subgroup ok");
                    break;

                case Constants.OWM_SUBGROUP_PHOTOS:
                    updateCatalogPhotosDB(catalogJson);
                    Log.e("Debug50","Catalogo Subgroup Fotos ok");
                    break;

                case Constants.OWM_EVENTS:
                    updateEventsDB(catalogJson);
                    Log.e("Debug50","Catalogo Eventos ok");
                    break;

                case Constants.OWM_EVENT_PHOTOS:
                    updateEventsPhotosDB(catalogJson);
                    Log.e("Debug50","Catalogo Eventos Fotos ok");
                    break;

            }
        } catch (JSONException e) {
            Log.e(Constants.LOG_TAG, e.getMessage());
        }

    }

    protected void updateSharedPref(Context context, String type) {


        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.file_key_pref), context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        Log.e("Debug12","Type: "+type);
        if (type.equals(Constants.OWM_SUBGROUP)) {

            //Catalog
            String subgroupModuleServer = sharedPref.getString(context.getString(R.string.subgroup_module_version_server_pref)
                    , context.getString(R.string.module_version_default_pref));
            editor.putString(context.getString(R.string.subgroup_module_version_app_pref), subgroupModuleServer);
            Log.e("Debug12","Ok - "+subgroupModuleServer);


        } else {
            //Events
            String eventsModuleServer = sharedPref.getString(context.getString(R.string.events_module_version_server_pref)
                    , context.getString(R.string.module_version_default_pref));
            editor.putString(context.getString(R.string.events_module_version_app_pref), eventsModuleServer);

        }

        editor.commit();


    }

    private void updateCatalogDB(JSONObject catalogJson) {

        JSONObject lineJSONObject;
        JSONObject groupJSONObject;
        JSONArray groupJSONArray;
        JSONObject subgroupJSONObject;
        JSONArray subgroupJSONArray;
        JSONArray subFlavorJSONArray;

        String lineName;
        String groupName;
        String subgroupName;
        String subgroupPrice;
        String subgroupPhoto;
        String subgroupFlavors;

        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //clean tables to receive new data
        db.delete(CatalogContract.CatalogSubgroupFlavors.TABLE_NAME, null, null);
        db.delete(CatalogContract.CatalogSubgroup.TABLE_NAME, null, null);

        try {

            JSONArray lineJSONArray = catalogJson.getJSONArray(Constants.OWM_LINES);

            /**
             * Line
             */
            for (int i = 0; i < lineJSONArray.length(); i++) {
                lineJSONObject = lineJSONArray.getJSONObject(i);
                lineName = lineJSONObject.getString(Constants.OWM_LINE_NAME);
                Log.d(Constants.LOG_TAG, "Line Name " + lineJSONObject.getString(Constants.OWM_LINE_NAME));

                /**
                 * Group
                 */
                groupJSONArray = lineJSONObject.getJSONArray(Constants.OWM_GROUPS);

                for (int j = 0; j < groupJSONArray.length(); j++) {
                    groupJSONObject = groupJSONArray.getJSONObject(j);
                    groupName = groupJSONObject.getString(Constants.OWM_GROUP_NAME);

                    Log.d(Constants.LOG_TAG, "Group Name: " + groupJSONObject.getString(Constants.OWM_GROUP_NAME));

                    /**
                     * Subgroup
                     */
                    subgroupJSONArray = groupJSONObject.getJSONArray(Constants.OWM_SUBGROUPS);

                    for (int m = 0; m < subgroupJSONArray.length(); m++) {
                        subgroupJSONObject = subgroupJSONArray.getJSONObject(m);
                        subgroupName = subgroupJSONObject.getString(Constants.OWM_SUBGROUP_NAME);
                        subgroupPrice = subgroupJSONObject.getString(Constants.OWM_SUBGROUP_PRICE);
                        subgroupPhoto = subgroupJSONObject.getString(Constants.OWM_SUBGROUP_PHOTO);

                        /**
                         * Insert Subgroup
                         */
                        ContentValues catalogSubgroupValues = new ContentValues();
                        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_LINE_NAME, lineName);
                        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_GROUP_NAME, groupName);
                        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGROUP_NAME, subgroupName);
                        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGROUP_PRICE, subgroupPrice);
                        catalogSubgroupValues.put(CatalogContract.CatalogSubgroup.COLUMN_NAME_SUBGROUP_PHOTO, subgroupPhoto);

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
                            catalogSubgroupFlavorsValues.put(CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGROUP_NAME, subgroupName);
                            catalogSubgroupFlavorsValues.put(CatalogContract.CatalogSubgroupFlavors.COLUMN_NAME_SUBGROUP_FLAVORS, subgroupFlavors);
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

            updateSharedPref(mContext, Constants.OWM_SUBGROUP);

        } catch (JSONException e) {
            Log.e(Constants.LOG_TAG, e.getMessage());
        }
    }

    private void updateCatalogPhotosDB(JSONObject catalogJson) {

        JSONObject photoJSONObject;
        JSONArray photosNameJSONArray;

        String photoName;

        String lineName;
        String subgroupName;
        String photosPath;

        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //clean tables to receive new data
        db.delete(CatalogContract.CatalogSubgroupPhotos.TABLE_NAME, null, null);

        try {

            JSONArray photosJSONArray = catalogJson.getJSONArray(Constants.OWM_PHOTOS);

            /**
             * Photos
             */
            for (int i = 0; i < photosJSONArray.length(); i++) {
                photoJSONObject = photosJSONArray.getJSONObject(i);
                lineName = photoJSONObject.getString("linha_nome");
                subgroupName = photoJSONObject.getString("subgroup_name");
                photosPath = photoJSONObject.getString("fotos_path");

                Log.d(Constants.LOG_TAG, "Photo " + photoJSONObject.getString("linha_nome"));

                /**
                 * Photos Names
                 */
                photosNameJSONArray = photoJSONObject.getJSONArray("fotos");

                for (int j = 0; j < photosNameJSONArray.length(); j++) {

                    photoName = photosNameJSONArray.getString(j);

                    /**
                     * Insert Subgroup Photos
                     */
                    ContentValues catalogSubgroupValues = new ContentValues();
                    catalogSubgroupValues.put(CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_LINE, lineName);
                    catalogSubgroupValues.put(CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_SUBGROUP_NAME, subgroupName);
                    catalogSubgroupValues.put(CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_PHOTOS_NAME, photoName);
                    catalogSubgroupValues.put(CatalogContract.CatalogSubgroupPhotos.COLUMN_NAME_PHOTOS_PATH, photosPath);

                    // Insert the new row, returning the primary key value of the new row
                    long newCatalogSubgroupRowId;
                    newCatalogSubgroupRowId = db.insert(
                            CatalogContract.CatalogSubgroupPhotos.TABLE_NAME,
                            CatalogContract.COLUMN_NAME_NULLABLE,
                            catalogSubgroupValues);

                }

            }

        } catch (JSONException e) {
            Log.e(Constants.LOG_TAG, e.getMessage());
        }
    }

    private void updateEventsDB(JSONObject catalogJson) {

        JSONObject eventsJSONObject;

        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //clean tables to receive new data
        db.delete(CatalogContract.Events.TABLE_NAME, null, null);

        try {

            JSONArray eventsJSONArray = catalogJson.getJSONArray("eventos");

            /**
             * Photos
             */
            for (int i = 0; i < eventsJSONArray.length(); i++) {
                eventsJSONObject = eventsJSONArray.getJSONObject(i);
                String eventCode = eventsJSONObject.getString("codigo");
                String eventName = eventsJSONObject.getString("name");
                String eventType = eventsJSONObject.getString("type");
                String eventPhotoCard = eventsJSONObject.getString("foto_card");
                String eventDescription = eventsJSONObject.getString("description");

                Log.e("Debug6","eventCode "+eventCode);
                Log.e("Debug6","eventName "+eventName);
                Log.e("Debug6","eventType "+eventType);
                Log.e("Debug6","eventPhotoCard "+eventPhotoCard);
                Log.e("Debug6","eventDescription "+eventDescription);


                /**
                 * Insert Subgroup Photos
                 */
                ContentValues eventsValues = new ContentValues();
                eventsValues.put(CatalogContract.Events.COLUMN_NAME_EVENT_CODE, eventCode);
                eventsValues.put(CatalogContract.Events.COLUMN_NAME_EVENT_NAME, eventName);
                eventsValues.put(CatalogContract.Events.COLUMN_NAME_EVENT_TYPE, eventType);
                eventsValues.put(CatalogContract.Events.COLUMN_NAME_EVENT_MAIN_PHOTO, eventPhotoCard);
                eventsValues.put(CatalogContract.Events.COLUMN_NAME_EVENT_DESCRIPTION, eventDescription);

                // Insert the new row, returning the primary key value of the new row
                long newEventRowId;
                newEventRowId = db.insert(
                        CatalogContract.Events.TABLE_NAME,
                        CatalogContract.COLUMN_NAME_NULLABLE,
                        eventsValues);

            }

            updateSharedPref(mContext, Constants.OWM_EVENTS);

        } catch (JSONException e) {
            Log.e(Constants.LOG_TAG, e.getMessage());
        }
    }


    private void updateEventsPhotosDB(JSONObject catalogJson) {

        JSONObject photoJSONObject;
        JSONArray photosNameJSONArray;

        String photoName;

        String eventCode;
        String photosPath;

        CatalogDbHelper mDbHelper = new CatalogDbHelper(mContext);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //clean tables to receive new data
        db.delete(CatalogContract.EventsPhotos.TABLE_NAME, null, null);

        try {

            JSONArray photosJSONArray = catalogJson.getJSONArray("fotos");

            /**
             * Photos
             */
            for (int i = 0; i < photosJSONArray.length(); i++) {
                photoJSONObject = photosJSONArray.getJSONObject(i);
                eventCode = photoJSONObject.getString("codigo");
                photosPath = photoJSONObject.getString("foto_path");


                Log.d(Constants.LOG_TAG, "Photo " + photoJSONObject.getString(Constants.OWM_LINE_NAME));

                /**
                 * Photos Names
                 */
                photosNameJSONArray = photoJSONObject.getJSONArray("fotos_nome");

                for (int j = 0; j < photosNameJSONArray.length(); j++) {

                    photoName = photosNameJSONArray.getString(j);

                    /**
                     * Insert Subgroup Photos
                     */
                    ContentValues eventPhotosValues = new ContentValues();
                    eventPhotosValues.put(CatalogContract.EventsPhotos.COLUMN_NAME_EVENT_CODE, eventCode);
                    eventPhotosValues.put(CatalogContract.EventsPhotos.COLUMN_NAME_PHOTOS_NAME, photoName);
                    eventPhotosValues.put(CatalogContract.EventsPhotos.COLUMN_NAME_PHOTOS_PATH, photosPath);

                    // Insert the new row, returning the primary key value of the new row
                    long newCatalogSubgroupRowId;
                    newCatalogSubgroupRowId = db.insert(
                            CatalogContract.EventsPhotos.TABLE_NAME,
                            CatalogContract.COLUMN_NAME_NULLABLE,
                            eventPhotosValues);

                }

            }

        } catch (JSONException e) {
            Log.e(Constants.LOG_TAG, e.getMessage());
        }
    }
}