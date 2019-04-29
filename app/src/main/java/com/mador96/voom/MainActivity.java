package com.mador96.voom;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dropdown for # of passengers
        Spinner passengerDropdown = findViewById(R.id.passengerNum);
        String[] passengerAmount = new String[]{"1", "2", "3", "4"}; //need more than 4?
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, passengerAmount);
        passengerDropdown.setAdapter(adapter);

        //testing purposes
        EditText test = (EditText) findViewById(R.id.originEditText);

        Button queryButton = (Button) findViewById(R.id.findRideButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            Log.i("INFO", "pre-execute");
        }

        protected Void doInBackground(String... apiValues) {
            RequestBuilder req = new RequestBuilder();

            try {
                //Prepare for API request

                //URL
                String API_URL = "http://www.mapquestapi.com/geocoding/v1/address";

                //Params
                String key = "augaAA25cxYUzzXABtyHXE7ddADGNq1F";
                String location = "260 Wiltshire Road Wynnewood PA 19096";
                Map<String, String> params = new HashMap<>();
                params.put("key", key);
                params.put("location", location);

                //Request method
                String requestMethod = "GET";

                //Headers
                Map<String, String> headers = new HashMap<>();
                headers.put("Postman-Token", "37038645-fa36-416d-91ec-780712b3a9dc");
                headers.put("cache-control", "no-cache");

                //Make Request
                HttpURLConnection urlConnection = req.makeRequest(requestMethod, API_URL, headers, params);

                //Get response
                String fullResponse = ResponseBuilder.getFullResponse(urlConnection);
                Log.i("INFO", "responsehere: " + fullResponse);

                //Do something with response here

                return null;
            }
            catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }

        }

        protected void onPostExecute(String response) {
            if (response == null) {
                response = "THERE WAS AN ERROR";
                Log.e("ERROR", response);

            }
            //do something here with response
        }
    }
}
