package com.example.series.test;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import com.example.series.beans.SeriesPersitence;
import com.example.series.beans.impl.InMemoryPersistence;
import com.example.series.services.SeriesServices;
import com.example.series.services.SeriesServicesException;
import com.example.series.services.SeriesServicesStub;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Assert;
import org.junit.Before;
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

@RunWith(ConcurrentTestRunner.class)
@SpringBootTest
public class ApplicationServicesTests {
    
    AtomicInteger cont;
    SeriesPersitence services;
    String queryAlphavantage;
    String queryIextrading;
    
    @Before
    public void initialCount() throws SeriesServicesException {
        cont = new AtomicInteger(0);
        services = new InMemoryPersistence();
        queryAlphavantage = services.getSerie("fb", "Daily", "alphavantage");
        queryIextrading = services.getSerie("fb", "1d", "iextrading");
    }
    
    @Test
    public void contextLoads() throws SeriesServicesException{
        
    }
    
    @Test
    @ThreadCount(10)
    public void concurrentQueryToPersitence() throws SeriesServicesException{
        for(int i = 0; i<5;i++){
            String queryA = services.getSerie("fb", "Daily", "alphavantage");
            System.out.println("A: "+queryAlphavantage.equals(queryA)+" = \n"+queryA+"\n -- "+queryAlphavantage);
            Assert.assertTrue(queryAlphavantage.equals(queryA));
            String queryB = services.getSerie("fb", "1d", "iextrading");
            System.out.println("B: "+queryIextrading.equals(queryB)+" = "+queryB+" -- "+queryIextrading);
            Assert.assertTrue(queryIextrading.equals(queryB));
        }
    }

}
