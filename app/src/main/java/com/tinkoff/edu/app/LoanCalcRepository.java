package com.tinkoff.edu.app;

public class LoanCalcRepository {
    private static int requestId = 0;

    /**
     * TODO persists request
     * @return requestId
     */
    public int save(LoanRequest request) {
        return  ++requestId;
    }
}
