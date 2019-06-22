package com.mador96.voom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TransportOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transport_options);

        String theOrigin = getIntent().getStringExtra("ORIGIN");
        String theDestination = getIntent().getStringExtra("DESTINATION");
        Double originLat = getIntent().getDoubleExtra("O_LAT", 0);
        Double originLng = getIntent().getDoubleExtra("O_LNG", 0);
        Double destinationLat = getIntent().getDoubleExtra("D_LAT", 0);
        Double destinationLng = getIntent().getDoubleExtra("D_LNG", 0);
        String passengers = getIntent().getStringExtra("PASSENGERS");
    }

}
