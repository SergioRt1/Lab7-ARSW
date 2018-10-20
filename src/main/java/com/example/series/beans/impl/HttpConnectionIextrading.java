/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.series.beans.impl;

import com.example.series.beans.HttpConnection;
import com.example.series.services.SeriesServicesException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
public class HttpConnectionIextrading implements HttpConnection {

    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    public String getSerie(String name, String type, String date) throws SeriesServicesException {
        String GET_URL = String.format("https://api.iextrading.com/1.0/stock/%s/chart/%s/%s", name, type, date);
        return getSerie(GET_URL);
    }

    @Override
    public String getSerie(String name, String type) throws SeriesServicesException {
        String GET_URL = String.format("https://api.iextrading.com/1.0/stock/%s/chart/%s", name, type);
        return getSerie(GET_URL);
    }

    @Override
    public String getSerie(String GET_URL) throws SeriesServicesException {
        try {

            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            //The following invocation perform the connection implicitly before getting the code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                return response.toString();

            } else {
                System.out.println("GET request not worked");
            }
            System.out.println("GET DONE");
            throw new SeriesServicesException("Error consutando al API externo.");
        } catch (IOException ex) {
            Logger.getLogger(HttpConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new SeriesServicesException("Error consutando al API externo.");
        }
    }
}
