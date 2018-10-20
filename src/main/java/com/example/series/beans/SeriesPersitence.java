package com.example.series.beans;

import com.example.series.services.SeriesServicesException;

public interface SeriesPersitence {

    public String getSerie(String name, String type, String source) throws SeriesServicesException;

    public String getSerie(String name, String type, String interval, String source) throws SeriesServicesException;

}
