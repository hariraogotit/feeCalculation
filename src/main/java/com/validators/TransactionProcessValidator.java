package com.validators;

import com.models.RawTransaction;
import com.services.APIException;

/**
 * Created by Hari Rao on 29/04/17.
 */
public interface TransactionProcessValidator {

    void validate(RawTransaction rawTransaction) throws APIException;

}
