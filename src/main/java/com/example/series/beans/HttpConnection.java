/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.series.beans;

import com.example.series.beans.impl.HttpConnectionAlphavantage;
import com.example.series.beans.impl.HttpConnectionIextrading;
import com.example.series.services.SeriesServicesException;

/**
 *
 * @author sergio
 */
public interface HttpConnection {
    
    public static final String ALPHAVANTAGE = "alphavantage";
    public static final String IEXTRADING = "iextrading";
    
    public String getSerie(String name, String type) throws SeriesServicesException;

    public String getSerie(String name, String type, String interval) throws SeriesServicesException;
    
    public String getSerie(String GET_URL) throws SeriesServicesException;
    
    static HttpConnection getSource(String source) throws SeriesServicesException{
        if(source.equals(HttpConnection.ALPHAVANTAGE)){
            return new HttpConnectionAlphavantage();
        }else if(source.equals(HttpConnection.IEXTRADING)){
            return new HttpConnectionIextrading();
        }else{
            throw new SeriesServicesException("Sorce not valid.");
        }
    }
}
