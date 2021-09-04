package com.tinkoff.edu.app;


public class LoanCalcController {
    private LoanCalcLogger loanCalcLogger = new LoanCalcLogger();
    private LoanCalcService service;

    /**
     *TODO  validates and logs request
     */

    public LoanCalcController(LoanCalcService service){
        this.service = service;
    }


    public int createRequest(LoanRequest request) {
        loanCalcLogger.log(request);
        return service.createRequest(request);

    }
}
