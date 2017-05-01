package com.handlers;

import com.config.ApplicationContextFeeCalculatorApp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hari Rao on 01/05/17.
 */
public class TransactionProcessFactoryHandlerTest {

    private TransactionProcessFactoryHandler transactionProcessFactoryHandler;

    @Before
    public void instialize(){
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ApplicationContextFeeCalculatorApp.class);
        transactionProcessFactoryHandler =  (TransactionProcessFactoryHandler) applicationContext.getBean("transactionProcessFactoryHandler");

    }
    @Test
    public void testGetHandlerForNull(){
          assertNull(transactionProcessFactoryHandler.getHandler("randomFileName.json"));
    }

    @Test
    public void testGetCSvHandler(){
        assertTrue(transactionProcessFactoryHandler.getHandler("randomFileName.csv") instanceof TransactionProcessCsvHandlerImpl);
    }

}