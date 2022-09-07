package com.kmush12.CurrencyConverter.urlReader;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class PageReader {


    private HttpURLConnection connection;

    public JSONObject getPageContent( String requiredPath) throws IOException {
        String response = null;
        setConnection(requiredPath);
        try {
            if (connectionResponseCode() > 299) {
                response = connectionError();
            } else {
                response = connectionCorrectly();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return new JSONObject(response);
    }

    private void setConnection(String requiredPath) throws IOException {
        System.out.println(requiredPath);
        URL url = new URL(requiredPath);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
    }

    private String connectionCorrectly() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return read(reader);
    }

    private String connectionError() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        return read(reader);
    }

    private int connectionResponseCode() throws IOException {
        return connection.getResponseCode();
    }

    private String read(BufferedReader reader) throws IOException {
        String line;
        StringBuilder responseContent = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        reader.close();
        return responseContent.toString();
    }

}
