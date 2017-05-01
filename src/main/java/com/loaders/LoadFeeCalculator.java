package com.loaders;


import com.config.ApplicationContextFeeCalculatorApp;
import com.handlers.TransactionProcessFactoryHandler;
import com.utilities.Constant;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by Hari Rao on 25/04/17.
 */
public class LoadFeeCalculator {

    private static Logger logger = Logger.getLogger(LoadFeeCalculator.class);

    public static void main(String[] args) throws Exception {

        try {
            ApplicationContext applicationContext =
                    new AnnotationConfigApplicationContext(ApplicationContextFeeCalculatorApp.class);
            TransactionProcessFactoryHandler transactionProcessFactoryHandler =
                    (TransactionProcessFactoryHandler) applicationContext.getBean("transactionProcessFactoryHandler");

            if(args.length == 3){
                processAllTheTransactionFiles(transactionProcessFactoryHandler, args[0],args[1],args[2]);
            }else{
               System.out.println(usage());
            }

        }catch (Exception ex){
            logger.error("Exception occurred ",ex);
            throw ex;
        }

    }

    private static void processAllTheTransactionFiles(TransactionProcessFactoryHandler transactionProcessFactoryHandler,
                                                      String inputTransactionDirectory,
                                                      String erroneousTransactionDirectory,
                                                      String summaryReportTransactionDirectory) throws IOException {
        Files.walk(Paths.get(inputTransactionDirectory))
                .filter(Files::isRegularFile)
                .forEach(
                        path -> {
                            try {
                                transactionProcessFactoryHandler.getHandler(path.toString())
                                        .process(path.toString(),
                                                erroneousTransactionDirectory + path.getFileName().toString() + new Date().toString(),
                                                summaryReportTransactionDirectory + path.getFileName().toString() + new Date().toString() ) ;
                            }catch (Exception ex){
                                logger.error("Exception occurred while parsing the transaction file ",ex);
                                throw new RuntimeException(ex);
                            }
                        }
                );
    }

    private static String usage(){
        return Constant.USAGE;
    }
}
