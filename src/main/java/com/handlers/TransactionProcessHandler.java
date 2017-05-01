package com.handlers;

/**
 * Created by Hari Rao on 25/04/17.
 */
public interface TransactionProcessHandler {

     void process(String fileName, String errorFileName, String summaryReportFileName) throws Exception;
}
