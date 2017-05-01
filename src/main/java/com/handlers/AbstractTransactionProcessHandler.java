package com.handlers;

import com.models.RawTransaction;
import com.models.Transaction;
import com.services.FeeCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.services.APIException;
import com.validators.TransactionProcessValidator;

import java.util.List;

/**
 * Created by Hari Rao on 29/04/17.
 */
public abstract class AbstractTransactionProcessHandler implements TransactionProcessHandler{

    @Autowired
    @Qualifier("transactionProcessValidatorImpl")
    TransactionProcessValidator transactionProcessValidator;

    @Autowired
    @Qualifier("feeCalculatorServiceImpl")
    FeeCalculatorService feeCalculatorService;

    public void validate(RawTransaction rawTransaction) throws APIException {
        transactionProcessValidator.validate(rawTransaction);
    }

    public void calculateFee(List<Transaction> transactions){
        feeCalculatorService.calculateFee(transactions);
    }

}
