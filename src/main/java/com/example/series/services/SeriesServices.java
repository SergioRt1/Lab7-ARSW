/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.series.services;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
public interface SeriesServices {

    String getSerie(String name, String type, String sorce) throws SeriesServicesException;

    String getSerie(String name, String type, String interval, String sorce) throws SeriesServicesException;

}
