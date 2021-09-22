package com.tinkoff.edu.app.controllers;


import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.model.RequestStatus;
import com.tinkoff.edu.app.service.LoanCalcService;
import com.tinkoff.edu.app.model.LoanRequest;

import java.util.UUID;

public class LoanCalcController {
    private LoanCalcService service;

    public LoanCalcController(LoanCalcService service) {
        this.service = service;
    }

    public LoanResponse createRequest(LoanRequest request) {
        // в случае ошибки, теперь возвращаем Exception
        if (request == null) throw  new IllegalArgumentException("Request не может быть пустым");
        if (request.getMonths() <= 0) throw new IllegalArgumentException("Срок должен быть больше 0");
        if (request.getAmount() <= 0) throw new IllegalArgumentException("Размер займа должен быть больше 0");
        switch (request.getLoanType()) {
            case IP:
                return new LoanResponse(RequestStatus.DECLINED, service.createRequest(request,RequestStatus.DECLINED));
            case PERSON:
                if (request.getAmount() > 10_000.0) {
                    if (request.getMonths() > 12) {
                        return new LoanResponse(RequestStatus.DECLINED, service.createRequest(request, RequestStatus.DECLINED));
                    }
                } else {
                    if (request.getMonths() <= 12) {
                        return new LoanResponse(RequestStatus.APPROVED, service.createRequest(request,RequestStatus.APPROVED));
                    }
                }
                break;
            case OOO:
                if (request.getAmount() <= 10_000.0) {
                    return new LoanResponse(RequestStatus.DECLINED, service.createRequest(request,RequestStatus.DECLINED));
                } else {
                    if (request.getMonths() < 12) {
                        return new LoanResponse(RequestStatus.APPROVED, service.createRequest(request,RequestStatus.APPROVED));
                    } else {
                        return new LoanResponse(RequestStatus.DECLINED, service.createRequest(request,RequestStatus.DECLINED));
                    }
                }
        }
        throw new IllegalArgumentException("набор параметров не покрывается бизнес логикой");
    }

    public RequestStatus getRequestStatus(UUID requestId){
        if (requestId == null) throw  new IllegalArgumentException("requestId не может быть пустым");
        return service.getRequestStatus(requestId);
    }

    public void updateRequestStatus(UUID requestId, RequestStatus status ){
        if ((requestId == null)||(status == null)) throw  new IllegalArgumentException("requestId или status не может быть пустым");
        service.updateRequestStatus(requestId, status);
    }
}
