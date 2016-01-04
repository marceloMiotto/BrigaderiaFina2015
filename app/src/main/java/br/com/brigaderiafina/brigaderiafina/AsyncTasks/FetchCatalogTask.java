package br.com.brigaderiafina.brigaderiafina.AsyncTasks;



import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class FetchCatalogTask extends AsyncTask<String, Void, JSONArray> {
    /**
     * http://www.brigaderiafina.com.br/mobile/database/database.php?auth_id=4981bb031dc179bd13edbda08a64dd94&w=???
     * w Options:
     * price_version: server version of the catalog products and prices
     * photo_version: server version of the product photos
     * events_version: server version of the events
     * price: product catalog
     * photo: product photos
     * events: events
     */


    private final String AUTH_ID = "auth_id";
    private final String API_KEY = "4981bb031dc179bd13edbda08a64dd94";
    private final String CATALOG_BASE_URL = "http://www.brigaderiafina.com.br/mobile/database/database.php?";

    @Override
    protected JSONArray doInBackground(String... params) {
        Log.i("Debug x", "doInBackground()");
        Log.i(Constants.LOG_TAG, "Do in background");

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String catalogJsonStr = null;

        try{

            Uri builtUri = Uri.parse(CATALOG_BASE_URL)
                    .buildUpon()
                    .appendQueryParameter(AUTH_ID, API_KEY)
                    .appendQueryParameter("w", "price")
                    .build();

            Log.i(Constants.LOG_TAG,builtUri.toString());

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                Log.i(Constants.LOG_TAG, "Buffer empty");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                Log.i(Constants.LOG_TAG, "Append new line");
                Log.i(Constants.LOG_TAG, line);
            }

            if (buffer.length() == 0) {
                return null;
            }
            catalogJsonStr = buffer.toString();
            Log.i(Constants.LOG_TAG,catalogJsonStr);

        }catch (IOException e){
            Log.e(Constants.LOG_TAG, "Error ", e);
            return null;
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(Constants.LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try{
            return  getCatalogDataFromJson(catalogJsonStr);
        } catch (JSONException e){
            Log.e(Constants.LOG_TAG,e.getMessage(),e);
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(JSONArray catalog){

    }

    private JSONArray getCatalogDataFromJson(String catalogJsonStr)
            throws JSONException {


        JSONObject catalogJson = new JSONObject(catalogJsonStr);
        //new object JSON begin
        JSONObject line;
        JSONObject group;
        JSONArray  groupArray;
        JSONObject subgroup;
        JSONArray  subgroupArray;
        JSONObject subFlavors;
        JSONArray  subFlavorArray;

        Log.d(Constants.LOG_TAG,"Catalog version: "+ catalogJson.getInt(Constants.OWM_CATALOG));

        JSONArray lineArray = catalogJson.getJSONArray(Constants.OWM_LINES);
        /**
         * Line
         */
        for(int i = 0; i<lineArray.length();i++){
            line = lineArray.getJSONObject(i);
            Log.d(Constants.LOG_TAG,"Line Name "+ line.getString(Constants.OWM_LINE_NAME));

            /**
             * Group
              */
            groupArray = line.getJSONArray(Constants.OWM_GROUPS);

            for (int j = 0; j<groupArray.length() ; j++){
                group      = groupArray.getJSONObject(j);
                Log.d(Constants.LOG_TAG,"Group Name: "+ group.getString(Constants.OWM_GROUP_NAME));
                Log.d(Constants.LOG_TAG,"Group Photo: "+ group.getString(Constants.OWM_GROUP_PHOTO));
                /**
                 * Subgroup
                 */
                subgroupArray = group.getJSONArray(Constants.OWM_SUBGROUPS);

                for(int m =0; m< subgroupArray.length(); m++){
                    subgroup      = subgroupArray.getJSONObject(m);
                    Log.d(Constants.LOG_TAG,"Subgroup Name: "+ subgroup.getString(Constants.OWM_SUBGROUP_NAME));
                    Log.d(Constants.LOG_TAG,"Subgroup Price: "+ subgroup.getString(Constants.OWM_SUBGROUP_PRICE));
                    /**
                     * Flavors
                     */

                    subFlavorArray = subgroup.getJSONArray(Constants.OWM_FLAVORS);

                    for(int n=0; n<subFlavorArray.length() ; n++){

                        subFlavors     = subgroupArray.getJSONObject(n);
                        Log.d(Constants.LOG_TAG,"Flavors: "+ subFlavors.getString(Constants.OWM_FLAVORS));

                    }
                }
            }

        }

        return lineArray;

    }
}
