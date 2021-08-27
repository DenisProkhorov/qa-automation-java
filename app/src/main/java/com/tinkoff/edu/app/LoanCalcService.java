package com.tinkoff.edu.app;

import com.tinkoff.edu.app.LoanCalcRepository.*;

public class LoanCalcService {
    /**
     *TODO  Loan calculation
     */

    public int createRequest(LoanRequest request){
        LoanCalcRepository loanCalcRepository = new LoanCalcRepository();
        return loanCalcRepository.save(request);
    }
}
