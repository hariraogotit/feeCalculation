package com.utilities;

/**
 * Created by Hari Rao on 24/04/17.
 */
public enum TransactionType {

    BUY("buy"),
    SELL("sell"),
    DEPOSIT("deposit"),
    WITHDRAW("withdraw");

    private String transactionType;

    TransactionType(String transactionType){
         this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
