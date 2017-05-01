package com.models;

import com.utilities.PriorityFlag;
import com.utilities.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Hari Rao on 24/04/17.
 */
public class Transaction {

    private Integer externalTransactionId;
    private Integer securityId;
    private BigDecimal marketValue;
    private boolean appliedFee;
    private Integer clientId;
    private TransactionType transactionType;
    private Date transactionDate;
    private PriorityFlag priorityFlag;
    private BigDecimal processingFee;

    public Integer getExternalTransactionId() {
        return externalTransactionId;
    }

    public void setExternalTransactionId(Integer externalTransactionId) {
        this.externalTransactionId = externalTransactionId;
    }

    public Integer getSecurityId() {
        return securityId;
    }

    public void setSecurityId(Integer securityId) {
        this.securityId = securityId;
    }


    public boolean isAppliedFee() {
        return appliedFee;
    }

    public void setAppliedFee(boolean appliedFee) {
        this.appliedFee = appliedFee;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public PriorityFlag getPriorityFlag() {
        return priorityFlag;
    }

    public void setPriorityFlag(PriorityFlag priorityFlag) {
        this.priorityFlag = priorityFlag;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }



}
