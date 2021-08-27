package com.tinkoff.edu.app;


public class LoanCalcController {
    /**
     *TODO  validates and logs request
     */
    public int createRequest(LoanRequest request) {
        LoanCalcLogger loanCalcLogger = new LoanCalcLogger();
        loanCalcLogger.log(request);
        LoanCalcService loanCalcService = new LoanCalcService();
        return loanCalcService.createRequest(request);

    }
}
