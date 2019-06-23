package com.mador96.voom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ServiceListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store company names
    private final String[] companyNames;

    //to store service level categories
    private final String[] categoryNames;

    //to store price
    private final String[] prices;

    public ServiceListAdapter(Activity context, String[] companyNames, String[] categoryNames, String[] prices) {

        super(context, R.layout.listview_row, companyNames);

        this.context = context;
        this.companyNames = companyNames;
        this.categoryNames = categoryNames;
        this.prices = prices;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null, true);

        TextView company = (TextView)rowView.findViewById(R.id.company);
        TextView price = (TextView)rowView.findViewById(R.id.price);


        //Dropdown for categories ----- FINISH!!!
        final Spinner categoryDropdown = rowView.findViewById(R.id.category);
        String[] categoryTypes = new String[]{"1", "2", "3", "4"}; //need more than 4?
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, categoryTypes);
        categoryDropdown.setAdapter(adapter);

        //set the values of the texts to values from the arrays
        company.setText(companyNames[position]);
        price.setText(prices[position]);
        //category?


        Button queryButton = (Button)rowView.findViewById(R.id.select);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return rowView;
    }
}


//https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc