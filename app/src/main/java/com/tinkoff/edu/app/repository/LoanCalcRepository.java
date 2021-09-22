package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanRequestRecord;
import com.tinkoff.edu.app.model.RequestStatus;

import java.util.UUID;

public interface LoanCalcRepository {

    LoanRequestRecord save(LoanRequest request, RequestStatus status);
    void updateStatus(UUID id, RequestStatus status);
    LoanRequestRecord getRequestById(UUID id);
}
