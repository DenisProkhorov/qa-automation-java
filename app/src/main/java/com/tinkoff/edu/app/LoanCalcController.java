package com.tinkoff.edu.app;


public class LoanCalcController{
    private LoanCalcService service;

    public LoanCalcController(LoanCalcService service) {
        this.service = service;
    }

    /**
     *TODO  validates and logs request
     */
    public int createRequest(LoanRequest request) {
        return service.createRequest(request);
    }
}
