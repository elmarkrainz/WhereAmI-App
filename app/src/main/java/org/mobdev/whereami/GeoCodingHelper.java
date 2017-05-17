package org.mobdev.whereami;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by EKrainz on 16/05/17.
 */

public class GeoCodingHelper {


    private final double lat;
    private final double lon;
    private final Activity activity;


    public GeoCodingHelper(Activity activity, double lat, double lon) {
       
        this.activity = activity;
        this.lat = lat;
        this.lon = lon;

        String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";

        url += lat + "," + lon + "&sensor=true";

        Log.i("GEOCODING", url);
        HttpHelper httpHelper = new HttpHelper();
        httpHelper.execute(url);
    }


    private class HttpHelper extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... params) {


            StringBuilder out = new StringBuilder();
            try {

                // get the string parameter from execute()
                URL url = new URL(params[0]);

                // create Urlconnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                // read inputstream
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    out.append(line);
                }
                Log.i("INTERNET", out.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

            return out.toString(); // return of do in background method is input parameter to the  on-post-execude method
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // do something with results from do-in-background method
            try {
                // parse JSon input
                JSONObject jsonObj = new JSONObject(s);
                JSONArray results = jsonObj.getJSONArray("results");
                JSONObject jo = results.getJSONObject(0);

                String address = jo.getString("formatted_address");
                System.out.println(address);

                // TODO
                // handle output in App

                // e.g. save to file 
                FileHelper fileHelper= new FileHelper(activity);
                fileHelper.saveToFile("address.txt", address);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}