package com.tinkoff.edu.app;

public class StaticLoanCalcRepository implements LoanCalcRepository{
    private static int requestId = 0;

    /**
     * TODO persists request
     */
    @Override
    public int save(LoanRequest request) {
        return  ++requestId;
    }
}
