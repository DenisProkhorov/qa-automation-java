package com.tinkoff.edu;

import com.tinkoff.edu.app.controllers.LoanCalcController;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.model.LoanType;
import com.tinkoff.edu.app.model.RequestStatus;
import com.tinkoff.edu.app.repository.LoanCalcRepositoryImpl;
import com.tinkoff.edu.app.service.LoanCalcServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {
    private LoanRequest request;
    private LoanCalcController sut;

    @Test
    public void shouldGetApproveWhenNotFirstValidRequest() {
        LoanCalcServiceImpl loanCalcService = new LoanCalcServiceImpl(new LoanCalcRepositoryImpl());
        loanCalcService.createRequest(new LoanRequest(LoanType.PERSON, 10,
                1_000,"initial dummy"),RequestStatus.APPROVED);

        sut = new LoanCalcController(loanCalcService);
        int approvedMonths = 12;
        request = new LoanRequest(LoanType.PERSON, approvedMonths, 1,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(RequestStatus.APPROVED, loanResponse.getResponseType(),
                "For request " + request + " should get " + RequestStatus.APPROVED + " and got "
                        + loanResponse.getResponseType());
    }

    @Test
    public void shouldUpdateRequestResolution() {
        LoanCalcServiceImpl loanCalcService = new LoanCalcServiceImpl(new LoanCalcRepositoryImpl());
        UUID id = loanCalcService.createRequest(new LoanRequest(LoanType.PERSON, 10,
                1_000,"initial dummy"),RequestStatus.APPROVED);
        sut = new LoanCalcController(loanCalcService);
        sut.updateRequestStatus(id,RequestStatus.DECLINED);
        assertEquals(RequestStatus.DECLINED, sut.getRequestStatus(id),
                "For request " + request + " should get " + RequestStatus.DECLINED + " and got "
                        +  sut.getRequestStatus(id));
    }

    @Test
    public void shouldGetRequestInfoRequestIdNotNull() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        int approvedMonths = 12;
        request = new LoanRequest(LoanType.PERSON, approvedMonths, 1,"Dummy Name");
        UUID requestId = sut.createRequest(request).getRequestId();
        RequestStatus requestStatus = sut.getRequestStatus(requestId);
        assertEquals(RequestStatus.APPROVED, sut.getRequestStatus(requestId),
                "For request " + request + " should get " + RequestStatus.APPROVED + " and got "
                        + requestStatus);
    }

    @Test
    public void shouldNotGetRequestInfoRequestIsNull() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,()-> sut.getRequestStatus(null)
        );
        assertTrue(thrown.getMessage().contains("requestId не может быть пустым"));
    }
    @Test
    public void shouldGetApproveWhenFirstValidRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        int approvedMonths = 12;
        request = new LoanRequest(LoanType.PERSON, approvedMonths, 1,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(RequestStatus.APPROVED, loanResponse.getResponseType(),
                "For request " + request + " should get " + RequestStatus.APPROVED + " and got "
                        + loanResponse.getResponseType());
    }

    @Test
    public void shouldGetErrorWhenApplyNullRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,()-> sut.createRequest(null)
        );
        assertTrue(thrown.getMessage().contains("Request не может быть пустым"));
    }

    @Test
    public void shouldGetErrorWhenApplyZeroOrNegativeMonthsRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,()-> sut.createRequest(
                        new LoanRequest (LoanType.PERSON, 0, 1_000,
                                "dummy name" )
                )
        );
        assertTrue(thrown.getMessage().contains("Срок должен быть больше 0"));
    }

    @Test
    public void shouldGetErrorWhenApplyZeroOrNegativeAmountRequest() {
    sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
    final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,()-> sut.createRequest(
                    new LoanRequest (LoanType.PERSON, 10, -1,
                            "dummy name" )
            )
    );
    assertTrue(thrown.getMessage().contains("Размер займа должен быть больше 0"));
}
    @Test
    public void shouldGetDeclineWhenNoBusinessRulesRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,()-> sut.createRequest(
                        new LoanRequest(LoanType.PERSON, 12, 11_000,"Dummy Name")
                )
        );
        assertTrue(thrown.getMessage().contains("набор параметров не покрывается бизнес логикой"));
    }

    @Test
    public void shouldGetApproveWhenPersonApply10_000Amount12MonthsRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        request = new LoanRequest(LoanType.PERSON, 12, 10_000,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(RequestStatus.APPROVED, loanResponse.getResponseType(),
                "For request " + request + " should get " + RequestStatus.APPROVED + " and got "
                        + loanResponse.getResponseType());
    }

    @Test
    public void shouldGetDeclineWhenPersonApply10_000Amount13MonthsRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        final IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,()-> sut.createRequest(
                        new LoanRequest(LoanType.PERSON, 13, 10_000,"Dummy Name")
                )
        );
        assertTrue(thrown.getMessage().contains("набор параметров не покрывается бизнес логикой"));
    }


    @Test
    public void shouldGetDeclineWhenPersonApply13_000Amount13MonthsRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        request = new LoanRequest(LoanType.PERSON, 13, 11_000,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(RequestStatus.DECLINED, loanResponse.getResponseType(),
                "For request " + request + " should get " + RequestStatus.DECLINED + " and got "
                        + loanResponse.getResponseType());
    }

    @Test
    public void shouldGetDeclineWhenIPApply13_000Amount13MonthsRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        request = new LoanRequest(LoanType.IP, 13, 11_000,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(RequestStatus.DECLINED, loanResponse.getResponseType(),
                "For request " + request + " should get " + RequestStatus.DECLINED + " and got "
                        + loanResponse.getResponseType());
    }

    @Test
    public void shouldGetDeclineWhenOOOApply10_000Amount10MonthsRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        request = new LoanRequest(LoanType.OOO, 10, 10_000,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(RequestStatus.DECLINED, loanResponse.getResponseType(),
                "For request " + request + " should get " + RequestStatus.DECLINED + " and got "
                        + loanResponse.getResponseType());
    }

    @Test
    public void shouldGetApproveWhenOOOApply11_000Amount11MonthsRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        request = new LoanRequest(LoanType.OOO, 11, 11_000,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(RequestStatus.APPROVED, loanResponse.getResponseType(),
                "For request " + request + " should get " + RequestStatus.APPROVED + " and got "
                        + loanResponse.getResponseType());
    }

    @Test
    public void shouldGetDeclineWhenOOOApply11_000Amount12MonthsRequest() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        request = new LoanRequest(LoanType.OOO, 12, 11_000,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertEquals(RequestStatus.DECLINED, loanResponse.getResponseType(),
                "For request " + request + " should get " + RequestStatus.DECLINED + " and got "
                        + loanResponse.getResponseType());
    }

    @Test
    public void checkLoanResponseOverrideEqualsWithSelf() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        request = new LoanRequest(LoanType.OOO, 12, 11_000,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertTrue(loanResponse.equals(loanResponse));
    }

    @Test
    public void checkLoanResponseOverrideEqualsWithNull() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        request = new LoanRequest(LoanType.OOO, 12, 11_000,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertFalse(loanResponse.equals(null));
    }

    @Test
    public void checkLoanResponseOverrideEqualsWithOtherType() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        request = new LoanRequest(LoanType.OOO, 12, 11_000,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        assertFalse(loanResponse.equals(request));
    }

    @Test
    public void checkLoanResponseOverrideEqualsWithOtherLoanResponseSameStatus() {
        sut = new LoanCalcController(new LoanCalcServiceImpl(new LoanCalcRepositoryImpl()));
        request = new LoanRequest(LoanType.OOO, 12, 11_000,"Dummy Name");
        LoanResponse loanResponse = sut.createRequest(request);
        LoanResponse otherResponse = sut.createRequest(new LoanRequest(LoanType.OOO, 12, 11_000,"Dummy Name"));
        assertTrue(loanResponse.equals(otherResponse));
    }
}
