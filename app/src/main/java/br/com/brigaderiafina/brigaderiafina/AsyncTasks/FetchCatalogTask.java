package br.com.brigaderiafina.brigaderiafina.AsyncTasks;



import android.content.Context;
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

public class FetchCatalogTask extends AsyncTask<Context, Void, JSONObject> {

    String mType;

    /**
     * http://www.brigaderiafina.com.br/mobile/database/database.php?auth_id=4981bb031dc179bd13edbda08a64dd94&w=???
     * w Options:
     * subgroup: catalog of products and prices (only text)
     * events: photos and description of each event
     */

    FetchCatalogTask(String type){
       mType = type;
    }

    @Override
    protected JSONObject doInBackground(Context... context) {
        Log.i("Debug x", "doInBackground()");
        Log.i(Constants.LOG_TAG, "Do in background");

        JSONFetch jsonFetch = new JSONFetch();

        if(mType.equals(Constants.OWM_SUBGROUPS)){
            return jsonFetch.pullJSONData("catalogo");
        }else{
            return jsonFetch.pullJSONData("events");
        }

    }

    @Override
    protected void onPostExecute(JSONObject catalogJson){

        JSONObject lineJSONObject;
        JSONObject groupJSONObject;
        JSONArray  groupJSONArray;
        JSONObject subgroupJSONObject;
        JSONArray  subgroupJSONArray;
        JSONArray  subFlavorJSONArray;

        try {

            JSONArray lineJSONArray = catalogJson.getJSONArray(Constants.OWM_LINES);
            /**
             * Line
             */
            for (int i = 0; i < lineJSONArray.length(); i++) {
                lineJSONObject = lineJSONArray.getJSONObject(i);
                //Log.d(Constants.LOG_TAG, "Line Name " + line.getString(Constants.OWM_LINE_NAME));

                /**
                 * Group
                 */
                groupJSONArray = lineJSONObject.getJSONArray(Constants.OWM_GROUPS);

                for (int j = 0; j < groupJSONArray.length(); j++) {
                    groupJSONObject = groupJSONArray.getJSONObject(j);
                    Log.d(Constants.LOG_TAG, "Group Name: " + groupJSONObject.getString(Constants.OWM_GROUP_NAME));
                    Log.d(Constants.LOG_TAG, "Group Photo: " + groupJSONObject.getString(Constants.OWM_GROUP_PHOTO));
                    /**
                     * Subgroup
                     */
                    subgroupJSONArray = groupJSONObject.getJSONArray(Constants.OWM_SUBGROUPS);

                    for (int m = 0; m < subgroupJSONArray.length(); m++) {
                        subgroupJSONObject = subgroupJSONArray.getJSONObject(m);
                        Log.d(Constants.LOG_TAG, "Subgroup Name: " + subgroupJSONObject.getString(Constants.OWM_SUBGROUP_NAME));
                        Log.d(Constants.LOG_TAG, "Subgroup Price: " + subgroupJSONObject.getString(Constants.OWM_SUBGROUP_PRICE));
                        /**
                         * Flavors
                         */

                        subFlavorJSONArray = subgroupJSONObject.getJSONArray(Constants.OWM_FLAVORS);

                        for (int n = 0; n < subFlavorJSONArray.length(); n++) {

                            Log.d(Constants.LOG_TAG, "Flavors: " + subFlavorJSONArray.getString(n));

                        }
                    }
                }

            }
        }catch (JSONException e){
            Log.e(Constants.LOG_TAG,e.getMessage());
        }
    }


}
