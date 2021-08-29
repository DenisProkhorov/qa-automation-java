package com.tinkoff.edu.app;


public class LoanCalcController {
    private LoanCalcLogger loanCalcLogger = new LoanCalcLogger();
    private LoanCalcService service = new StaticLoanCalcService();

    /**
     *TODO  validates and logs request
     */
    public int createRequest(LoanRequest request) {
        loanCalcLogger.log(request);
        return service.createRequest(request);

    }
}
