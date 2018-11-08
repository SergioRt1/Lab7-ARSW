/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.series.model;

import org.springframework.data.annotation.Id;

/**
 *
 * @author 2135494
 */
public class Serie {

    @Id
    private String key;

    private String name;
    private String type;
    private String source;
    private String interval;
    private String data;

    public Serie() {
    }

    public Serie(String name, String type, String source, String interval, String data) {
        this.name = name;
        this.type = type;
        this.source = source;
        this.interval = interval;
        this.data = data;
        this.key = source + name + type + interval;
    }

    public Serie(String name, String type, String source, String data) {
        this.name = name;
        this.type = type;
        this.source = source;
        this.data = data;
        this.key = source + name + type;
    }

    @Override
    public String toString() {
        return "Serie{" + "name=" + name + ", type=" + type + ", source=" + source + ", data=" + data + '}';
    }

    public String getData() {
        return data;
    }
    
}
