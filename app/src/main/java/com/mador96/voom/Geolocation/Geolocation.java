package com.mador96.voom.Geolocation;

import android.util.Log;

import com.mador96.voom.RequestBuilder;
import com.mador96.voom.ResponseBuilder;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class Geolocation {

    public static Map<Double, Double> getGeolocation(String requestedLocation) {
        RequestBuilder req = new RequestBuilder();

        try {
            //Prepare for API request to get lat and log coordinates

            //URL
            String API_URL = "http://www.mapquestapi.com/geocoding/v1/address";

            //Params
            String key = "augaAA25cxYUzzXABtyHXE7ddADGNq1F";
            String location = requestedLocation;
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
            Log.i("INFO", "responsehere: " + fullResponse); //remove this

            Map<Double, Double> coordinates = new HashMap<>();
            getCoordinates(fullResponse);

            return coordinates;
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    public static Map<Double, Double> getCoordinates(String response) {
        Map<Double, Double> locationCoordinates = new HashMap<>();

        //extract lat/long from response and return

        return locationCoordinates;
    }
}
