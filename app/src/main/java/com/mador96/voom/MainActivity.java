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

import com.mador96.voom.Geolocation.Geolocation;

import java.util.Map;


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

        Button queryButton = (Button) findViewById(R.id.findRideButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get address origin from edit text
                EditText originEditText = (EditText) findViewById(R.id.originEditText);
                EditText destinationEditText = (EditText) findViewById(R.id.destinationEditText);
                String origin = originEditText.getText().toString();
                String destination = destinationEditText.getText().toString();

                final String[] addresses = new String[2];
                addresses[0] = origin;
                addresses[1] = destination;

                new RetrieveFeedTask().execute(addresses);
            }
        });
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
        }

        protected Void doInBackground(String... apiValues) {

            //Get user inputted addresses
            String origin = apiValues[0];
            String destination = apiValues[1];

           Map<Double, Double> originCoordinates = Geolocation.getGeolocation(origin);
           Map<Double, Double> destinationCoordinates = Geolocation.getGeolocation(destination);

           //At this point, you should have the number of passengers, and the lat/long coordinates of both the origin and destination locations

            return null;
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
