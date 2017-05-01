package com.validators;

import com.models.RawTransaction;
import org.springframework.stereotype.Component;
import com.services.APIException;
import com.utilities.PriorityFlag;
import com.utilities.TransactionType;

/**
 * Created by Hari Rao on 29/04/17.
 */
@Component
public class TransactionProcessValidatorImpl implements TransactionProcessValidator {

    @Override
    public void validate(RawTransaction rawTransaction) throws APIException {

        validateTransType(rawTransaction.getTransactionType());
        validatePriorityFlag(rawTransaction.getPriorityFlag());
    }

    public void validateTransType(String transType) throws APIException {
        try {
             TransactionType.valueOf(transType.toUpperCase());
        }catch (IllegalArgumentException ex){
            throw new APIException("Error - Invalid Transaction Type " + transType );
        }
    }

    public void validatePriorityFlag(String priorityFlag) throws APIException {
        try {
             PriorityFlag.valueOf(priorityFlag.toUpperCase());
        }catch (IllegalArgumentException ex){
            throw new APIException("Error - Invalid Priority Flag " + priorityFlag);
        }
    }


}
