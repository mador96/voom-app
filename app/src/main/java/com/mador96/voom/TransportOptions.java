package com.mador96.voom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

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

        TextView originText = (TextView)findViewById(R.id.originTextView);
        TextView destText = (TextView)findViewById(R.id.destTextView);

        //Make API calls here to get info needed for list to show end user

        //Then populate the arrays below with appropriate info
        String[] companyArray = {"Uber", "Lyft", "Via", "etc"};
        String[] categoryArray = {"one", "two", "three", "four"};
        String[] priceArray = {"$1.00", "$2.00", "$3.00", "$4.00"};



        //How to strip out only first part of address???
        originText.setText(theOrigin);
        destText.setText(theDestination);

        //Initialize list for prices
        ServiceListAdapter services = new ServiceListAdapter(this, companyArray, categoryArray, priceArray);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(services);
    }

}
