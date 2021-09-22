package com.tinkoff.edu.app.service;

import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.model.RequestStatus;
import com.tinkoff.edu.app.repository.LoanCalcRepository;
import com.tinkoff.edu.app.model.LoanRequest;

import java.util.UUID;

public class LoanCalcServiceImpl implements LoanCalcService {
    private LoanCalcRepository repo;

    public LoanCalcServiceImpl(LoanCalcRepository repo){
        this.repo = repo;
    }

    @Override
    public UUID createRequest(LoanRequest request, RequestStatus status){
        return repo.save(request, status).getRequestId();
    }

    @Override
    public void updateRequestStatus(UUID id, RequestStatus status) {
        repo.updateStatus(id, status);
    }

    @Override
    public RequestStatus getRequestStatus(UUID id) {
      return repo.getRequestById(id).getRequestStatus();
    }
}
