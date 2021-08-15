package com.tinkoff.edu.app;

public class LoanCalcController {
    /**
     *TODO  validates and logs request
     */
    public static int createRequest() {
        LoanCalcLogger.log();
        return LoanCalcService.createRequest();

    }
}
