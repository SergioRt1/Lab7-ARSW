package com.example.series.beans.impl;



import com.example.series.beans.HttpConnection;
import com.example.series.services.SeriesServicesException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
public class HttpConnectionAlphavantage implements HttpConnection{

    private final String USER_AGENT = "Mozilla/5.0";
    private Map<String, String> function;

    public HttpConnectionAlphavantage() {
        function = new HashMap<>();
        function.put("Intraday", "TIME_SERIES_INTRADAY");
        function.put("Daily", "TIME_SERIES_DAILY");
        function.put("Weekly", "TIME_SERIES_WEEKLY");
        function.put("Monthly", "TIME_SERIES_MONTHLY");
    }
    

    @Override
    public String getSerie(String name, String type, String interval) throws SeriesServicesException {
        String GET_URL = String.format("https://www.alphavantage.co/query?function=%s&symbol=%s&interval=%s&apikey=Q1QZFVJQ21K7C6XM", function.get(type), name, interval);
        return getSerie(GET_URL);
    }

    @Override
    public String getSerie(String name, String type) throws SeriesServicesException {
        String GET_URL = String.format("https://www.alphavantage.co/query?function=%s&symbol=%s&apikey=Q1QZFVJQ21K7C6XM", function.get(type), name);
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