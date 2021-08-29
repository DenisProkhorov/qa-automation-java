package com.tinkoff.edu.app;

public class StaticLoanCalcService implements LoanCalcService{
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
