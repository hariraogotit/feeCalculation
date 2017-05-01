package com.handlers;

import com.models.RawTransaction;
import com.models.Transaction;
import com.services.APIException;
import com.utilities.Constant;
import com.utilities.FeeCalculatorUtil;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * Created by Hari Rao on 25/04/17.
 */
@Component
public class TransactionProcessCsvHandlerImpl extends AbstractTransactionProcessHandler{


    public void process(String fileName, String errorFileName, String summaryReportFileName) throws Exception{
        Map<String,RawTransaction> erroneousRawTransaction = new HashMap<>();
        List<Transaction> transactions = new ArrayList<>();
        readFromCsvFile(fileName,erroneousRawTransaction, transactions);
        calculateFee(transactions);
        writeToSummaryReportCsvFile(transactions, summaryReportFileName);
        writeToErrorCsvFile(erroneousRawTransaction, errorFileName);
   }

    /**
     *
     * @param fileName
     * @param erroneousRawTransaction
     * @param transactions
     * @throws Exception
     * Below method reads from csv file and validates each records. If there is any invalid data, it builds @param erroneousRawTransaction.
     * Ideally readFromCsvFile should only read and return a Transaction object which can then be validated but validating while reading will have a
     * good latency.
     */
    private void readFromCsvFile(String fileName,  Map<String,RawTransaction> erroneousRawTransaction, List<Transaction> transactions) throws Exception {

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(Constant.INPUT_FILE_HEADER_MAPPING);
        try(FileReader fileReader = new FileReader(fileName);
            CSVParser csvFileParser = new CSVParser(fileReader,csvFormat)) {
            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            csvRecords.stream().skip(1).forEach(
                    csvRecord -> {
                        RawTransaction rawTransaction = null;
                        try {
                            rawTransaction = FeeCalculatorUtil.buildRawTransaction(csvRecord);
                            validate(rawTransaction);
                            transactions.add(FeeCalculatorUtil.buildTransaction(rawTransaction));
                        } catch (APIException ex) {
                            erroneousRawTransaction.put(ex.getMessage(), rawTransaction);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
            );
        }
    }

    /**
     *
     * @param erroneousRawTransaction
     * @param errorFileName
     * @throws Exception
     * Any record with errors will be written to this file.
     */

    private void writeToErrorCsvFile(Map<String, RawTransaction> erroneousRawTransaction, String errorFileName) throws Exception{

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(Constant.RECORD_DELIMITER);
        if(erroneousRawTransaction.size() > 0){
            try(FileWriter fileWriter = new FileWriter(errorFileName);
                CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat)) {
                csvFilePrinter.printRecord( Constant.INPUT_FILE_HEADER_MAPPING);
                for (Map.Entry entry : erroneousRawTransaction.entrySet()) {
                    List<String> writeRawTransaction = FeeCalculatorUtil.buildErrorTransaction(entry.getKey().toString(),(RawTransaction) entry.getValue());
                    csvFilePrinter.printRecord(writeRawTransaction);
                }
            }

        }
    }

    /**
     *
     * @param summaryReportTransaction
     * @param summaryReportFileName
     * @throws Exception
     * Below method creates the final summary report
     */
    private void writeToSummaryReportCsvFile(List<Transaction> summaryReportTransaction, String summaryReportFileName) throws Exception{

        summaryReportTransaction.sort(Comparator.comparing(Transaction::getClientId)
                                                .thenComparing(Transaction::getTransactionType)
                                                .thenComparing(Transaction::getTransactionDate)
                                                .thenComparing(Transaction::getPriorityFlag));

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(Constant.RECORD_DELIMITER);

        try(FileWriter fileWriter = new FileWriter(summaryReportFileName);
            CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat)) {
            csvFilePrinter.printRecord( Constant.SUMMARY_REPORT_HEADER_MAPPING);
            for (Transaction transaction : summaryReportTransaction) {
                List<String> writeRawTransaction = FeeCalculatorUtil.buildSummaryTransaction(transaction);
                csvFilePrinter.printRecord(writeRawTransaction);
            }
        }

    }

}
