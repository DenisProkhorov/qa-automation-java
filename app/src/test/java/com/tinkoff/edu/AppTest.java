package com.tinkoff.edu;

import com.tinkoff.edu.app.controllers.LoanCalcController;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.model.LoanType;
import com.tinkoff.edu.app.model.ResponseType;
import com.tinkoff.edu.app.repository.DynamicLoanCalcRepository;
import com.tinkoff.edu.app.repository.StaticLoanCalcRepository;
import com.tinkoff.edu.app.service.StaticLoanCalcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 *
 */
public class AppTest {
    private LoanRequest request;
    private LoanCalcController sut;

    @BeforeEach
    public void init() {
        request = new LoanRequest(LoanType.PERSON, 10, 1_000);
    }

    @Test
    @Order(1)
    public void shouldGet1WhenFirstRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        int requestId = sut.createRequest(request).getRequestId();
        assertTrue(requestId == 1, "requestId must be 1 and now it is " + requestId);
    }

    @Test
    public void shouldGetIncrementedBy1WhenAnyRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository(3)));
        int requestId = sut.createRequest(request).getRequestId();
        assertTrue(requestId == 4, "requestId must be 4 and now it is " + requestId);
    }

    @Test
    public void shouldGetApproveWhenValidRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new StaticLoanCalcRepository()));
        int approvedMonths = 12;
        request = new LoanRequest(LoanType.PERSON, approvedMonths, 10);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.APPROVED, 1), loanResponse,
                "For request " + request + " should get " + ResponseType.APPROVED + " and got "
                        + loanResponse.getResponseType());
    }

    @Test
    public void shouldGetErrorWhenApplyNullRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        LoanResponse loanResponse = sut.createRequest(null);
        assertEquals(new LoanResponse(ResponseType.DENIED, -1), loanResponse);
    }

    @Test
    public void shouldGetErrorWhenApplyZeroOrNegativeMonthsRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        request = new LoanRequest(LoanType.PERSON, 0, 10_000);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.DENIED, -1), loanResponse);
    }

    @Test
    public void shouldGetErrorWhenApplyZeroOrNegativeAmountRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        request = new LoanRequest(LoanType.PERSON, 12, 0);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.DENIED, -1), loanResponse);
    }

    @Test
    public void shouldGetDeclineWhenNoBusinessRulesRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        request = new LoanRequest(LoanType.PERSON, 12, 11_000);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.DENIED, -1), loanResponse);
    }

    @Test
    public void shouldGetApproveWhenPersonApply10_000Amount12MonthsRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        request = new LoanRequest(LoanType.PERSON, 12, 10_000);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.APPROVED, 1), loanResponse);
    }

    @Test
    public void shouldGetDeclineWhenPersonApply10_000Amount13MonthsRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        request = new LoanRequest(LoanType.PERSON, 13, 10_000);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.DENIED, -1), loanResponse);
    }


    @Test
    public void shouldGetDeclineWhenPersonApply13_000Amount13MonthsRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        request = new LoanRequest(LoanType.PERSON, 13, 11_000);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.DENIED, 1), loanResponse);
    }

    @Test
    public void shouldGetDeclineWhenIPApply13_000Amount13MonthsRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        request = new LoanRequest(LoanType.IP, 13, 11_000);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.DENIED, 1), loanResponse);
    }

    @Test
    public void shouldGetDeclineWhenOOOApply10_000Amount10MonthsRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        request = new LoanRequest(LoanType.OOO, 10, 10_000);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.DENIED, 1), loanResponse);
    }

    @Test
    public void shouldGetApproveWhenOOOApply11_000Amount11MonthsRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        request = new LoanRequest(LoanType.OOO, 11, 11_000);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.APPROVED, 1), loanResponse);
    }

    @Test
    public void shouldGetDeclineWhenOOOApply11_000Amount12MonthsRequest() {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository()));
        request = new LoanRequest(LoanType.OOO, 12, 11_000);
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(new LoanResponse(ResponseType.DENIED, 1), loanResponse);
    }
}
