package com.services;

import com.models.Transaction;

import java.util.List;

/**
 * Created by Hari Rao on 30/04/17.
 */
public interface FeeCalculatorService {

    public void calculateFee(List<Transaction> transaction);
}
