package com.mador96.voom;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.*;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class callAPI extends AppCompatActivity {

    /*
    public void mapquest(String url, Header[] headers, RequestParams params) throws JSONException {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    */

    class RetrieveFeedTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            Log.i("INFO", "pre-execute");
        }

        protected Void doInBackground(String... apiValues) {

            //String API_URL = apiValues[0];
            //String key = apiValues[1];
            //String location = apiValues[2];

            String API_URL = "http://open.mapquestapi.com/geocoding/v1/address";
            String key = "augaAA25cxYUzzXABtyHXE7ddADGNq1F%0A";
            String location = "260 Wiltshire Road Wynnewood PA 19096";

            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");

                //headers
                urlConnection.setRequestProperty("Postman-Token", "37038645-fa36-416d-91ec-780712b3a9dc");
                urlConnection.setRequestProperty("cache-control", "no-cache");

                //params
                Map<String, String> params = new HashMap<>();
                params.put("key", key);
                params.put("location", location);
                urlConnection.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
                out.writeBytes(ParameterStringBuilder.getParamsString(params));
                out.flush();
                out.close();


                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    //return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                    Log.i("INFO", "API call was successful");
                }
                return null;
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }

        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            Log.i("INFO", response);
            //do something here with response
        }
    }
}