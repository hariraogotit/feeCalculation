package com.utilities;

import com.models.RawTransaction;
import com.models.Transaction;
import org.apache.commons.csv.CSVRecord;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Hari Rao on 30/04/17.
 */
public class FeeCalculatorUtil {

    public static RawTransaction buildRawTransaction(CSVRecord csvRecord){

          RawTransaction rawTransaction = new RawTransaction();
          rawTransaction.setExternalTransactionId(csvRecord.get(Constant.EXTERNAL_TRANSACTION_ID));
          rawTransaction.setClientId(csvRecord.get(Constant.CLIENT_ID));
          rawTransaction.setSecurityId(csvRecord.get(Constant.SECURITY_ID));
          rawTransaction.setTransactionType(csvRecord.get(Constant.TRANSACTION_TYPE));
          rawTransaction.setTransactionDate(csvRecord.get(Constant.TRANSACTION_DATE));
          rawTransaction.setMarketValue(csvRecord.get(Constant.MARKET_VALUE));
          rawTransaction.setPriorityFlag(csvRecord.get(Constant.PRIORITY_FLAG));
          return rawTransaction;
    }

    public static Transaction buildTransaction(RawTransaction rawTransaction) throws Exception{

        Transaction transaction = new Transaction();
        transaction.setExternalTransactionId(Integer.parseInt(rawTransaction.getExternalTransactionId()));
        transaction.setClientId(Integer.parseInt(rawTransaction.getClientId()));
        transaction.setSecurityId(Integer.parseInt(rawTransaction.getSecurityId()));
        transaction.setTransactionType(TransactionType.valueOf(rawTransaction.getTransactionType().toUpperCase()));
        transaction.setTransactionDate(new SimpleDateFormat(Constant.DATE_FORMAT).parse(rawTransaction.getTransactionDate()));
        transaction.setMarketValue(new BigDecimal(getValue(rawTransaction.getMarketValue())));
        transaction.setPriorityFlag(PriorityFlag.valueOf(rawTransaction.getPriorityFlag().toUpperCase()));
        return transaction;
    }

    public static String getValue(String marketValue) throws Exception{
        return marketValue.replaceAll("[$,]", "");
    }


    public static List<String> buildErrorTransaction(String errorMessage, RawTransaction rawTransaction) {
        List<String> writeRawTransaction = new ArrayList<>();
        writeRawTransaction.add(rawTransaction.getExternalTransactionId());
        writeRawTransaction.add(rawTransaction.getClientId());
        writeRawTransaction.add(rawTransaction.getSecurityId());
        writeRawTransaction.add(rawTransaction.getTransactionType());
        writeRawTransaction.add(rawTransaction.getTransactionDate());
        writeRawTransaction.add(Constant.DOLLAR + rawTransaction.getMarketValue());
        writeRawTransaction.add(rawTransaction.getPriorityFlag());
        writeRawTransaction.add(errorMessage);
        return writeRawTransaction;
    }

    public static List<String> buildSummaryTransaction(Transaction transaction) {
        List<String> writeSummaryTransaction = new ArrayList<>();
        writeSummaryTransaction.add(String.valueOf(transaction.getClientId()));
        writeSummaryTransaction.add(transaction.getTransactionType().getTransactionType());
        writeSummaryTransaction.add(new SimpleDateFormat(Constant.DATE_FORMAT).format(transaction.getTransactionDate()));
        writeSummaryTransaction.add(transaction.getPriorityFlag().getPriorityFlag());
        writeSummaryTransaction.add(Constant.DOLLAR + transaction.getProcessingFee());
        return writeSummaryTransaction;
    }

    public static  List<Transaction> partition(List<Transaction> transaction, TransactionType transactionType) {
        Map<Boolean, List<Transaction>> partitioned =
                transaction.stream().collect(Collectors.partitioningBy(trans ->
                        (trans.getTransactionType() == transactionType )
                ));
        return partitioned.get(true);
    }
}
