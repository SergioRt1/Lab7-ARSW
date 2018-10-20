package com.example.series.services;

import com.example.series.beans.SeriesPersitence;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesServicesStub implements SeriesServices {

    @Autowired
    SeriesPersitence persitence;

    public SeriesServicesStub() {
    }

    @Override
    public String getSerie(String name, String type, String source) throws SeriesServicesException {
        return persitence.getSerie(name, type, source);
    }

    @Override
    public String getSerie(String name, String type, String interval, String source) throws SeriesServicesException {
        return persitence.getSerie(name, type, interval, source);
    }

}
