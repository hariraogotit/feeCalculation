package com.validators;

import com.services.APIException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Hari Rao on 29/04/17.
 */
public class TransactionProcessValidatorImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testValidateTransType() throws APIException{
        TransactionProcessValidatorImpl transactionProcessValidator = new TransactionProcessValidatorImpl();
        thrown.expect(APIException.class);
        thrown.expectMessage("Error - Invalid Transaction Type X");
        transactionProcessValidator.validateTransType("X");

    }

    @Test
    public void testValidatePriorityFlag() throws APIException{
        TransactionProcessValidatorImpl transactionProcessValidator = new TransactionProcessValidatorImpl();
        thrown.expect(APIException.class);
        thrown.expectMessage("Error - Invalid Priority Flag X");
        transactionProcessValidator.validatePriorityFlag("X");

    }


}