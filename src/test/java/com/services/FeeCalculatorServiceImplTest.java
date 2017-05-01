package com.services;

import com.models.Transaction;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by Hari Rao on 01/05/17.
 */
public class FeeCalculatorServiceImplTest {

   @Test
   public void testAddProcessingFee(){
      FeeCalculatorServiceImpl feeCalculatorService = new FeeCalculatorServiceImpl();
      Transaction transaction = new Transaction();
      transaction.setProcessingFee(new BigDecimal("10"));
      transaction.setProcessingFee(feeCalculatorService.addProcessingFee(transaction,new BigDecimal("10")));
      assertEquals(new BigDecimal("20"), transaction.getProcessingFee());

   }

   @Test
   public void testAddProcessingFeeWithNullProcessingFee(){
      FeeCalculatorServiceImpl feeCalculatorService = new FeeCalculatorServiceImpl();
      Transaction transaction = new Transaction();
      transaction.setProcessingFee(feeCalculatorService.addProcessingFee(transaction,new BigDecimal("10")));
      assertEquals(new BigDecimal("10"), transaction.getProcessingFee());

   }

}