package com.handlers;

import com.utilities.FileType;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Hari Rao on 26/04/17.
 */
@Component
public class TransactionProcessFactoryHandler {


    @Autowired
    TransactionProcessCsvHandlerImpl transactionProcessCsvHandler;

    /**
     *
     * @param fileName
     * @return
     * Based on the file type get the corresponding handler
     */
    public TransactionProcessHandler getHandler(String fileName){

        String fileType = FilenameUtils.getExtension(fileName).toLowerCase();

        if(FileType.CSV.getFileType().equals(fileType)){
            return transactionProcessCsvHandler;
        }
        return null;
    }

}
