/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.series.beans.impl;

import com.example.series.beans.HttpConnection;
import com.example.series.services.SeriesServicesException;

/**
 *
 * @author sergio
 */
public class HttpConnectionIextrading implements HttpConnection {

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

}
