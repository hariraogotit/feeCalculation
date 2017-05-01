package com.utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Hari Rao on 30/04/17.
 */
public class FeeCalculatorUtilTest {

  @Test
  public void testGetCurrencyValue() throws Exception{
      String marketValue = "$123,456.78";
      assertEquals("123456.78",FeeCalculatorUtil.getValue(marketValue));
  }

}