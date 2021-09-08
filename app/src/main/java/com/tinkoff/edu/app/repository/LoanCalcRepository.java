package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.LoanRequest;

public interface LoanCalcRepository {

    int save(LoanRequest request);
}
