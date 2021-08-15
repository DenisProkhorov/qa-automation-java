package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanCalcController;
import static org.junit.Assert.assertTrue;

/**
 * Hello world!
 *
 */
public class LoanCalcTest
{
    public static void main(String... args)
    {
        int requestId = LoanCalcController.createRequest();
        assertTrue("requestId must be 1",requestId==1);
    }
}
