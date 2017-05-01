package com.services;

import com.models.Transaction;
import com.utilities.Constant;
import com.utilities.FeeCalculatorUtil;
import com.utilities.PriorityFlag;
import com.utilities.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Hari Rao on 30/04/17.
 */
@Service
public class FeeCalculatorServiceImpl implements FeeCalculatorService{
    @Override
    public void calculateFee(List<Transaction> transaction) {


        List<Transaction> sellTransaction = FeeCalculatorUtil.partition(transaction,TransactionType.SELL);
        List<Transaction> buyTransaction = FeeCalculatorUtil.partition(transaction,TransactionType.BUY);
        List<Transaction> depositTransaction = FeeCalculatorUtil.partition(transaction,TransactionType.DEPOSIT);
        List<Transaction> withDrawTransaction = FeeCalculatorUtil.partition(transaction,TransactionType.WITHDRAW);

        applyFeeForBuyOrSellTransactionType(sellTransaction, buyTransaction);
        depositTransaction.stream().forEach(
                depositTrans -> applyNominalFee(depositTrans)
        );
        withDrawTransaction.stream().forEach(
                withDrawTrans -> applyNominalFee(withDrawTrans)
        );
        System.out.println(buyTransaction);

    }

    private void applyFeeForBuyOrSellTransactionType(List<Transaction> sellTransaction, List<Transaction> buyTransaction) {
        sellTransaction.stream().forEach(
                              sellTran -> buyTransaction.stream().forEach(
                                        buyTran -> {
                                            if(sellTran.getClientId().equals(buyTran.getClientId())
                                                    && sellTran.getSecurityId().equals(buyTran.getSecurityId())
                                                    && sellTran.getTransactionDate().equals(buyTran.getTransactionDate())){
                                                applyIntraDayCharge(buyTran);
                                                applyIntraDayCharge(sellTran);
                                                if(!buyTran.isAppliedFee()){
                                                    applyNominalFee(buyTran);
                                                }
                                                if(!sellTran.isAppliedFee()) {
                                                    applyNominalFee(sellTran);
                                                }
                                            }else{
                                                if(!buyTran.isAppliedFee()) {
                                                    applyNominalFee(buyTran);
                                                }
                                                if(!sellTran.isAppliedFee()) {
                                                    applyNominalFee(sellTran);
                                                }
                                            }
                                        }
                              )
                        );
    }

    public void applyNominalFee(Transaction transaction) {
        if(transaction.getPriorityFlag() == PriorityFlag.Y){
            transaction.setProcessingFee(addProcessingFee(transaction, Constant.HIGH_PRIORITY_TRANSACTION ));
        }else {
            if(transaction.getTransactionType() == TransactionType.SELL || transaction.getTransactionType() == TransactionType.WITHDRAW){
              transaction.setProcessingFee(addProcessingFee(transaction,Constant.LOW_PRIORITY_TRANSACTION_SELL_OR_WITHDRAWN));
            }else if(transaction.getTransactionType() == TransactionType.BUY || transaction.getTransactionType() == TransactionType.DEPOSIT){
                transaction.setProcessingFee(addProcessingFee(transaction,Constant.LOW_PRIORITY_TRANSACTION_BUY_OR_DEPOSIT));
            }
        }
        transaction.setAppliedFee(true);
    }

    public BigDecimal addProcessingFee(Transaction transaction, BigDecimal fee) {
        if(transaction.getProcessingFee() != null) {
           return transaction.getProcessingFee().add(fee);
        }
        return fee;
    }

    public void applyIntraDayCharge(Transaction transaction) {
        transaction.setProcessingFee(Constant.INTRA_DAY_FEE);
    }



}
