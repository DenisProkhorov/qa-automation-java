package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.RequestStatus;

import java.util.UUID;

public interface LoanCalcService {
    UUID createRequest(LoanRequest request, RequestStatus status);
    void updateRequestStatus(UUID id, RequestStatus status);
    RequestStatus getRequestStatus(UUID id);
}
