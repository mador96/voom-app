package com.mador96.voom;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mador96.voom.Geolocation.Geolocation;

import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public String origin;
    public String destination;
    public Map<Double, Double> originCoordinates;
    public Map<Double, Double> destinationCoordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dropdown for # of passengers
        final Spinner passengerDropdown = findViewById(R.id.passengerNum);
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
                String pass = passengerDropdown.getSelectedItem().toString();


                final String[] addresses = new String[2];
                addresses[0] = origin;
                addresses[1] = destination;

                new RetrieveFeedTask().execute(addresses);

                //go to next activity
                //openTransportOptions();
                //openTransportOptions(originCoordinates, destinationCoordinates, origin, destination);
            }
        });
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
        }

        protected String doInBackground(String... apiValues) {

            //Get user inputted addresses
            origin = apiValues[0];
            destination = apiValues[1];

            originCoordinates = Geolocation.getGeolocation(origin);
            destinationCoordinates = Geolocation.getGeolocation(destination);

           //At this point, you should have the number of passengers, and the lat/long coordinates of both the origin and destination locations
            return null;
        }

        protected void onPostExecute(String response) {
            openTransportOptions();
        }
    }

    public void openTransportOptions() {

        Map.Entry<Double, Double> originEntry = originCoordinates.entrySet().iterator().next();
        Double originLat = originEntry.getKey();
        Double originLng = originEntry.getValue();

        Map.Entry<Double, Double> destinationEntry = destinationCoordinates.entrySet().iterator().next();
        Double destLat = destinationEntry.getKey();
        Double destLng = destinationEntry.getValue();

        Intent intent = new Intent(this, TransportOptions.class);
        intent.putExtra("ORIGIN", origin);
        intent.putExtra("DESTINATION", destination);
        intent.putExtra("O_LAT", originLat);
        intent.putExtra("O_LNG", originLng);
        intent.putExtra("D_LAT", destLat);
        intent.putExtra("D_LNG", destLng);
        startActivity(intent);
    }
}
