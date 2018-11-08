/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.series.beans.impl;

import com.example.series.SeriesRepository;
import com.example.series.beans.HttpConnection;
import com.example.series.beans.SeriesPersitence;
import com.example.series.model.Serie;
import com.example.series.services.SeriesServicesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongodbPersistence implements SeriesPersitence {

    @Autowired
    private SeriesRepository repository;

    @Override
    public String getSerie(String name, String type, String source) throws SeriesServicesException {
        HttpConnection externalAPI = HttpConnection.getSource(source);
        Serie serie = repository.findByKey(source + name + type);
        if (serie == null) {
            String data = externalAPI.getSerie(name, type);
            serie = new Serie(name, type, source, data);
            repository.save(serie);
        }
        return serie.getData();
    }

    @Override
    public String getSerie(String name, String type, String interval, String source) throws SeriesServicesException {
        HttpConnection externalAPI = HttpConnection.getSource(source);
        Serie serie = repository.findByKey(source + name + type + interval);
        if (serie == null) {
            String data = externalAPI.getSerie(name, type);
            serie = new Serie(name, type, source, interval, data);
            repository.save(serie);
        }
        return serie.getData();
    }

}
