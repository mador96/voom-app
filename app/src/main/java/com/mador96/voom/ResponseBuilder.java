package com.mador96.voom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;

public class ResponseBuilder {
    public static String getFullResponse(HttpURLConnection urlConnection) throws IOException {
        //Response status
        int status = urlConnection.getResponseCode();

        Reader streamReader = null;
        streamReader = new InputStreamReader(urlConnection.getInputStream());

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        StringBuilder fullResponse = new StringBuilder();
        fullResponse.append(content);

        in.close();
        urlConnection.disconnect();
        return fullResponse.toString();
    }
}