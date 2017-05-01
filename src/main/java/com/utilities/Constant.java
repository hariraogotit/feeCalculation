package com.utilities;

import java.math.BigDecimal;

/**
 * Created by Hari Rao on 30/04/17.
 */
public class Constant {


    public static final String [] INPUT_FILE_HEADER_MAPPING =   {
                                                            "External_Transaction_Id",
                                                            "Client_Id",
                                                            "Security_Id",
                                                            "Transaction_Type",
                                                            "Transaction_Date",
                                                            "Market_Value",
                                                            "Priority_Flag"
                                                           };

    public static final String [] SUMMARY_REPORT_HEADER_MAPPING =   {
                                                            "Client_Id",
                                                            "Transaction_Type",
                                                            "Transaction_Date",
                                                            "Priority_Flag",
                                                            "Processing_Fee"
                                                          };


    public static final String EXTERNAL_TRANSACTION_ID = INPUT_FILE_HEADER_MAPPING[0];
    public static final String CLIENT_ID = INPUT_FILE_HEADER_MAPPING[1];
    public static final String SECURITY_ID = INPUT_FILE_HEADER_MAPPING[2];
    public static final String TRANSACTION_TYPE = INPUT_FILE_HEADER_MAPPING[3];
    public static final String TRANSACTION_DATE = INPUT_FILE_HEADER_MAPPING[4];
    public static final String MARKET_VALUE = INPUT_FILE_HEADER_MAPPING[5];
    public static final String PRIORITY_FLAG = INPUT_FILE_HEADER_MAPPING[6];


    public static final String RECORD_DELIMITER = "\n";

    public static final String DATE_FORMAT = "dd/MM/yy";
    public static final String DOLLAR = "$";

    public static final BigDecimal INTRA_DAY_FEE = new BigDecimal("10");
    public static final BigDecimal HIGH_PRIORITY_TRANSACTION = new BigDecimal("500");
    public static final BigDecimal LOW_PRIORITY_TRANSACTION_SELL_OR_WITHDRAWN = new BigDecimal("100");
    public static final BigDecimal LOW_PRIORITY_TRANSACTION_BUY_OR_DEPOSIT = new BigDecimal("50");

    public static final String USAGE = "Usage :- java -jar calculator-1.0-SNAPSHOT.jar <inputTransactionDirectory/> <erroneousTransactionDirectory/> <summaryReportTransactionDirectory/>";
}
