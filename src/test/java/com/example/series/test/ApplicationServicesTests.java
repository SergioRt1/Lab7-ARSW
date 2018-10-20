package com.example.series.test;

import com.example.series.beans.SeriesPersitence;
import com.example.series.beans.impl.InMemoryPersistence;
import com.example.series.services.SeriesServices;
import com.example.series.services.SeriesServicesException;
import com.example.series.services.SeriesServicesStub;
import com.mycila.junit.concurrent.Concurrency;
import com.mycila.junit.concurrent.ConcurrentJunitRunner;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * 
 * @author sergio
 */

@RunWith(ConcurrentJunitRunner.class)
@Concurrency(30)
@SpringBootTest
public class ApplicationServicesTests {

    @Test
    public void contextLoads() throws SeriesServicesException{
        
    }
    
    @Test
    public void concurrentQueryToPersitence() throws SeriesServicesException{
        SeriesPersitence services = new InMemoryPersistence();
        String queryAlphavantage = services.getSerie("fb", "Daily", "alphavantage");
        String queryIextrading = services.getSerie("fb", "1d", "iextrading");
        for(int i = 0; i<100;i++){
            Assert.assertTrue(queryAlphavantage.equals(services.getSerie("fb", "Daily", "alphavantage")));
            Assert.assertTrue(queryIextrading.equals(services.getSerie("fb", "1d", "iextrading")));
        }
    }

}
