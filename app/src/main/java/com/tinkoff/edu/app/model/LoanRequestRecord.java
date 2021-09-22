package com.tinkoff.edu.app.model;

import java.util.UUID;

public class LoanRequestRecord {
    private LoanRequest request;
    private UUID requestId;
    private RequestStatus requestStatus;


    public LoanRequestRecord(LoanRequest request) {
        this.request = request;
        this.requestId =  UUID.randomUUID();
    }

    public LoanRequest getRequest() {
        return request;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }
}
