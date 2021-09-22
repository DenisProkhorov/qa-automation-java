package com.tinkoff.edu.app.model;

import java.util.Objects;
import java.util.UUID;

public class LoanResponse {
    private RequestStatus requestStatus;
    private UUID requestId;

    public LoanResponse(RequestStatus requestStatus, UUID requestId) {
        this.requestStatus = requestStatus;
        this.requestId = requestId;
    }

    public RequestStatus getResponseType() {
        return requestStatus;
    }

    public UUID getRequestId() {
        return requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanResponse that = (LoanResponse) o;
        return requestStatus == that.requestStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestStatus, requestId);
    }


}
