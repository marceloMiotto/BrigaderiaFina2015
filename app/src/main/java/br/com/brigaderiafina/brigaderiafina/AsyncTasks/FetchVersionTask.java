package br.com.brigaderiafina.brigaderiafina.AsyncTasks;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.brigaderiafina.brigaderiafina.R;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class FetchVersionTask extends AsyncTask<Context, Void, Context> {


    /**
     * http://www.brigaderiafina.com.br/mobile/database/database.php?auth_id=4981bb031dc179bd13edbda08a64dd94&w=???
     * w Option:
     * catalog_versions: server version of the catalog - subgroups, prices and events
     */

    @Override
    protected Context doInBackground(Context... context) {

        JSONObject versionJSONObject;

        SharedPreferences sharedPref = context[0].getSharedPreferences(
                context[0].getString(R.string.file_key_pref), context[0].MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        JSONFetch jsonFetch = new JSONFetch();
        JSONObject jsonObject = jsonFetch.pullJSONData("catalog_versions");
        try {

                JSONArray versionJSONArray = jsonObject.getJSONArray(Constants.OWM_CATALOG);
                /**
                 * Module Version
                 */
                for (int i = 0; i < versionJSONArray.length(); i++) {

                    versionJSONObject = versionJSONArray.getJSONObject(i);
                    editor.putString(versionJSONObject.getString(Constants.OWM_MODULE_NAME),versionJSONObject.getString(Constants.OWM_MODULE_VERSION ));
                    editor.commit();

                }

            }catch (JSONException e){
            Log.e(Constants.LOG_TAG,e.getMessage());
        }

        return context[0];

    }

    @Override
    protected void onPostExecute(Context context){

        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.file_key_pref), context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        //Catalog
        String subgroupModuleApp  = sharedPref.getString(context.getString(R.string.subgroup_module_version_app_pref)
                , context.getString(R.string.module_version_default_pref));

        String subgroupModuleServer  = sharedPref.getString(context.getString(R.string.subgroup_module_version_server_pref)
                , subgroupModuleApp);
        //Events
        String eventsModuleApp = sharedPref.getString(context.getString(R.string.events_module_version_app_pref)
                , context.getString(R.string.module_version_default_pref));

        String eventsModuleServer = sharedPref.getString(context.getString(R.string.events_module_version_server_pref)
                ,eventsModuleApp);



        if (!subgroupModuleApp.equals(subgroupModuleServer)) {
            updateCatalog(context,Constants.OWM_SUBGROUP);
        }

        if (!eventsModuleApp.equals(eventsModuleServer)) {
            updateCatalog(context,Constants.OWM_EVENTS);
        }

    }

    protected void updateCatalog(Context context, String type) {

        FetchCatalogTask fetchCatalogTask = new FetchCatalogTask(context,type);
        fetchCatalogTask.execute(context);
    }


}
