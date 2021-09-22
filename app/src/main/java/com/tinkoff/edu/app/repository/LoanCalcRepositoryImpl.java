package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanRequestRecord;
import com.tinkoff.edu.app.model.RequestStatus;

import java.util.UUID;

public class LoanCalcRepositoryImpl implements LoanCalcRepository {
     private int requestsCount=0;
     final private LoanRequestRecord[] loanRequests=new LoanRequestRecord[100];

    @Override
    public LoanRequestRecord save(LoanRequest request, RequestStatus status) {
        loanRequests[requestsCount] =  new LoanRequestRecord(request);
        loanRequests[requestsCount].getRequest().setLoanType(request.getLoanType());
        loanRequests[requestsCount].getRequest().setFullName(request.getFullName());
        loanRequests[requestsCount].getRequest().setAmount(request.getAmount());
        loanRequests[requestsCount].getRequest().setMonths(request.getMonths());
        loanRequests[requestsCount].setRequestStatus(status);
        requestsCount++;
        return loanRequests[requestsCount-1];
    }

    @Override
    public void updateStatus(UUID id, RequestStatus status) {
        getRequestById(id).setRequestStatus(status);
    }

    @Override
    public LoanRequestRecord getRequestById(UUID id) {
        for(LoanRequestRecord request:loanRequests){
            if((request!=null)&&(request.getRequestId().equals(id))){
                return request;
            }
        }
        return null;
    }
}
