package com.tinkoff.edu.app;

public class StaticLoanCalcService implements LoanCalcService{
    private LoanCalcRepository repo;
    /**
     *TODO  Loan calculation
     */

    public StaticLoanCalcService(LoanCalcRepository repo){
        this.repo = repo;
    }

    @Override
    public int createRequest(LoanRequest request){
        return repo.save(request);
    }
}
