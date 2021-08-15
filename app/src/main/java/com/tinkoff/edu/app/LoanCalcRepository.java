package com.tinkoff.edu.app;

public class LoanCalcRepository {
    private static int requestId = 0;

    /**
     * TODO persists request
     * @return requestId
     */
    public static int save() {
        int localVar = ++requestId;
        return localVar;
    }
}
