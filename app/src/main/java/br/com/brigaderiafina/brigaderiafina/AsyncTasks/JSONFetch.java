package br.com.brigaderiafina.brigaderiafina.AsyncTasks;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class JSONFetch {

    JSONFetch(){}

    protected JSONObject pullJSONData(String queryParameter){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String catalogJsonStr = null;

        try{

            Uri builtUri = Uri.parse(Constants.CATALOG_BASE_URL)
                    .buildUpon()
                    .appendQueryParameter(Constants.AUTH_ID, Constants.API_KEY)
                    .appendQueryParameter(Constants.QUERY_PARAMETER, queryParameter)
                    .build();


            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            catalogJsonStr = buffer.toString();


        }catch (IOException e){
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

    private JSONObject getCatalogDataFromJson(String catalogJsonStr)
            throws JSONException {

        return  new JSONObject(catalogJsonStr);


    }
}
