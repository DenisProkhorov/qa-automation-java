package com.tinkoff.edu.app.controllers;


import com.tinkoff.edu.app.repository.LoanCalcService;
import com.tinkoff.edu.app.model.LoanRequest;

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
