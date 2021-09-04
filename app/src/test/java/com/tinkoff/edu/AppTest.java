package com.tinkoff.edu;

import com.tinkoff.edu.app.controllers.LoanCalcController;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.repository.DynamicLoanCalcRepository;
import com.tinkoff.edu.app.repository.StaticLoanCalcRepository;
import com.tinkoff.edu.app.service.StaticLoanCalcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


/**
 */
public class AppTest
{
    private LoanRequest request;
    private LoanCalcController sut;

    @BeforeEach
    public void init(){
        request = new LoanRequest(10,1_000);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldGet1WhenFirstRequest()
    {
            sut = new LoanCalcController(new StaticLoanCalcService(new StaticLoanCalcRepository()));
            int requestId = sut.createRequest(request);
            assertTrue(requestId==1, "requestId must be 1");
    }

    @Test
    public void shouldGetIncrementedBy1WhenAnyRequest()
    {
        sut = new LoanCalcController(new StaticLoanCalcService(new DynamicLoanCalcRepository(3)));
        int requestId = sut.createRequest(request);
        assertTrue(requestId==4, "requestId must be 4");
    }
}
