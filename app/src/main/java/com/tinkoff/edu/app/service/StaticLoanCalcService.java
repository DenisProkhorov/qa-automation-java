package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.repository.LoanCalcRepository;
import com.tinkoff.edu.app.repository.LoanCalcService;
import com.tinkoff.edu.app.model.LoanRequest;

public class StaticLoanCalcService implements LoanCalcService {
    private LoanCalcRepository repo;

    public StaticLoanCalcService(LoanCalcRepository repo){
        this.repo = repo;
    }
    /**
     *TODO  Loan calculation
     */
    @Override
    public int createRequest(LoanRequest request){
        return repo.save(request);
    }
}
