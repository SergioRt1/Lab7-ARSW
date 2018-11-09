package com.example.series.test;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import com.example.series.beans.HttpConnection;
import com.example.series.beans.SeriesPersitence;
import com.example.series.beans.impl.HttpConnectionAlphavantage;
import com.example.series.beans.impl.HttpConnectionIextrading;
import com.example.series.beans.impl.InMemoryPersistence;
import com.example.series.beans.impl.MongodbPersistence;
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
    HttpConnection connectionA;
    HttpConnection connectionB;
    String URLA = "https://lab7-arsw.herokuapp.com/series/alphavantage/fb/Daily";
    String URLB = "https://lab7-arsw.herokuapp.com/series/iextrading/fb/1d";
    String herokuAlphavantage;
    String herokuIextrading;

    @Before
    public void initialCount() throws SeriesServicesException {
        services = new InMemoryPersistence();
        queryAlphavantage = services.getSerie("fb", "Daily", "alphavantage");
        queryIextrading = services.getSerie("fb", "1d", "iextrading");
        
        connectionA = new HttpConnectionAlphavantage();
        connectionB = new HttpConnectionIextrading();
        herokuAlphavantage = connectionA.getSerie(URLA);
        herokuIextrading = connectionA.getSerie(URLB);
        
    }

    @Test
    public void contextLoads() throws SeriesServicesException {

    }

    @Test
    @ThreadCount(10)
    public void concurrentQueryToPersitence() throws SeriesServicesException {
        for (int i = 0; i < 5; i++) {
            String queryA = services.getSerie("fb", "Daily", "alphavantage");
            Assert.assertTrue(queryAlphavantage.equals(queryA));
            String queryB = services.getSerie("fb", "1d", "iextrading");
            Assert.assertTrue(queryIextrading.equals(queryB));
        }
    }

    @Test
    @ThreadCount(10)
    public void concurrentQueryToAPIHeroku() throws SeriesServicesException {
        for (int i = 0; i < 5; i++) {
            String queryA = connectionA.getSerie(URLA);
            Assert.assertTrue(herokuAlphavantage.equals(queryA));
            String queryB = connectionB.getSerie(URLB);
            Assert.assertTrue(herokuIextrading.equals(queryB));
        }
    }

}
