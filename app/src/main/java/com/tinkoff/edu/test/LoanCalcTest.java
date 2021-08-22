package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanCalcController;
import com.tinkoff.edu.app.LoanRequest;

import static org.junit.Assert.assertTrue;


/**
 * Hello world!
 *
 */
public class LoanCalcTest
{
    public static void main(String... args)
    {
        LoanRequest request = new LoanRequest(10,1_000);
        LoanCalcController loanCalcController = new LoanCalcController();
        int requestId = loanCalcController.createRequest(request);
        assertTrue("requestId must be 1",requestId==1);
    }
}
