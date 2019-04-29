package com.mador96.voom;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RequestBuilder {
    public static HttpURLConnection makeRequest(String requestMethod, String API_URL, Map<String, String> headers, Map<String, String> parameters) throws IOException {

        URL url = new URL(API_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        //Set request method
        urlConnection.setRequestMethod(requestMethod);

        //Set headers
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        //Set params
        Map<String, String> params = parameters;

        urlConnection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(params));
        out.flush();
        out.close();

        return urlConnection;
    }
}
