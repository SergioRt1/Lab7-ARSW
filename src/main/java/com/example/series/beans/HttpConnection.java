/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.series.beans;

import com.example.series.beans.impl.HttpConnectionAlphavantage;
import com.example.series.beans.impl.HttpConnectionIextrading;
import com.example.series.services.SeriesServicesException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public interface HttpConnection {

    public static final String ALPHAVANTAGE = "alphavantage";
    public static final String IEXTRADING = "iextrading";

    public final String USER_AGENT = "Mozilla/5.0";

    public String getSerie(String name, String type) throws SeriesServicesException;

    public String getSerie(String name, String type, String interval) throws SeriesServicesException;

    default String getSerie(String GET_URL) throws SeriesServicesException {
        try {
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            //The following invocation perform the connection implicitly before getting the code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_ACCEPTED) {
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

    static HttpConnection getSource(String source) throws SeriesServicesException {
        if (source.equals(HttpConnection.ALPHAVANTAGE)) {
            return new HttpConnectionAlphavantage();
        } else if (source.equals(HttpConnection.IEXTRADING)) {
            return new HttpConnectionIextrading();
        } else {
            throw new SeriesServicesException("Sorce not valid.");
        }
    }
}
