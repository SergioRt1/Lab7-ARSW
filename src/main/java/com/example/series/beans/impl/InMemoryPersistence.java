package com.example.series.beans.impl;

import com.example.series.beans.HttpConnection;
import com.example.series.beans.SeriesPersitence;
import com.example.series.services.SeriesServicesException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPersistence implements SeriesPersitence {
    
    private Map<String, String> cache;

    public InMemoryPersistence() {
        cache = new ConcurrentHashMap<>();
    }
    
    @Override
    public String getSerie(String name, String type, String source) throws SeriesServicesException {
        String key = source+name+type;
        HttpConnection externalAPI = HttpConnection.getSource(source);
        if(cache.containsKey(key)){
            return cache.get(key);
        }else{
            String serie = externalAPI.getSerie(name, type);
            cache.put(key, serie);
            return serie;
        }
    }

    @Override
    public String getSerie(String name, String type, String interval, String source) throws SeriesServicesException {
        String key = source+name+type+interval;
        HttpConnection externalAPI = HttpConnection.getSource(source);
        if(cache.containsKey(key)){
            return cache.get(key);
        }else{
            String serie = externalAPI.getSerie(name, type, interval);
            cache.put(key, serie);
            return serie;
        }
    }

}
