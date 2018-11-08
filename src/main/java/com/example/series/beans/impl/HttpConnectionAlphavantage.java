package com.example.series.beans.impl;



import com.example.series.beans.HttpConnection;
import com.example.series.services.SeriesServicesException;
import java.util.HashMap;
import java.util.Map;

public class HttpConnectionAlphavantage implements HttpConnection{

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

}