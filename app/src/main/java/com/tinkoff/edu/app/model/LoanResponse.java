package com.tinkoff.edu.app.model;

import java.util.Objects;

public class LoanResponse {
    private ResponseType responseType;
    private int requestId;

    public LoanResponse(ResponseType responseType, int requestId) {
        this.responseType = responseType;
        this.requestId = requestId;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public int getRequestId() {
        return requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanResponse that = (LoanResponse) o;
        return requestId == that.requestId && responseType == that.responseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseType, requestId);
    }


}
