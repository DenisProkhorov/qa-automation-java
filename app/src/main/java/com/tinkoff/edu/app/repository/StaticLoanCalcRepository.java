package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.LoanRequest;

public class StaticLoanCalcRepository implements LoanCalcRepository {
    private static int requestId = 0;

    /**
     * TODO persists request
     */
    @Override
    public int save(LoanRequest request) {
        return  ++requestId;
    }
}
