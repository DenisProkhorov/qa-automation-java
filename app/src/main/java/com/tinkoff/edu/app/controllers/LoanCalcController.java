package com.tinkoff.edu.app.controllers;


import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.model.LoanType;
import com.tinkoff.edu.app.model.ResponseType;
import com.tinkoff.edu.app.repository.LoanCalcService;
import com.tinkoff.edu.app.model.LoanRequest;

public class LoanCalcController {
    private LoanCalcService service;

    public LoanCalcController(LoanCalcService service) {
        this.service = service;
    }

    public LoanResponse createRequest(LoanRequest request) {
        // По условиям домашки в данных трёх случаях ID должен быть равен -1 (пока без Exceptions)
        if (request == null) return new LoanResponse(ResponseType.DENIED, -1);
        if (request.getMonths() <= 0) return new LoanResponse(ResponseType.DENIED, -1);
        if (request.getAmount() <= 0) return new LoanResponse(ResponseType.DENIED, -1);
        // Вся остальная бизнес логика сделана без switch по условиям домашки
        // (в следующий раз переделаю под switch)

        if (request.getPresonType() == LoanType.IP) {
            return new LoanResponse(ResponseType.DENIED, service.createRequest(request));
        }
        if (request.getPresonType() == LoanType.PERSON) {
            if (request.getAmount() > 10_000.0) {
                if (request.getMonths() > 12) {
                    return new LoanResponse(ResponseType.DENIED, service.createRequest(request));
                }
            } else {
                if (request.getMonths() <= 12) {
                    return new LoanResponse(ResponseType.APPROVED, service.createRequest(request));
                }
            }
        }
        if (request.getPresonType() == LoanType.OOO) {
            if (request.getAmount() <= 10_000.0) {
                return new LoanResponse(ResponseType.DENIED, service.createRequest(request));
            } else {
                if (request.getMonths() < 12) {
                    return new LoanResponse(ResponseType.APPROVED, service.createRequest(request));
                } else {
                    return new LoanResponse(ResponseType.DENIED, service.createRequest(request));
                }
            }
        }
        // есть сценарии, которые логикой в задании не покрыты, для них сделал заглушку
        // например: (LoanType.PERSON, request.getAmount() > 10_000.0 && request.getMonths() <= 12)
        return new LoanResponse(ResponseType.DENIED, -1);
    }
}
