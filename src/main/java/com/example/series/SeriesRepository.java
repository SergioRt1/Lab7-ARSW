package com.example.series;

import com.example.series.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SeriesRepository extends MongoRepository<Serie, String>{
    
    public Serie findByKey(String key);
}
