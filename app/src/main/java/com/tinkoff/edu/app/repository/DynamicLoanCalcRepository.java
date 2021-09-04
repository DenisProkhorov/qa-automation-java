package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.LoanRequest;

public class DynamicLoanCalcRepository implements LoanCalcRepository {
    private int requestId;

    public DynamicLoanCalcRepository(int requestId){
        this.requestId=requestId;
    }

    public DynamicLoanCalcRepository(){
        this(0);
    }
    /**
     * TODO persists request
     */
    @Override
    public int save(LoanRequest request) {
        return  ++requestId;
    }
}
