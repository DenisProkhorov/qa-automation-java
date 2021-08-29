package com.tinkoff.edu.app;

public class StaticLoanCalcService implements LoanCalcService{
    private LoanCalcRepository repo = new StaticLoanCalcRepository();
    /**
     *TODO  Loan calculation
     */
    @Override
    public int createRequest(LoanRequest request){
        return repo.save(request);
    }
}
