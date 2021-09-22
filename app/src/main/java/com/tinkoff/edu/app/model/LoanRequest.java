package com.tinkoff.edu.app.model;

import java.util.UUID;

public class LoanRequest {
    private LoanType loanType;
    private int months;
    private int amount;
    private String fullName;

    public LoanRequest(LoanType loanType, int months, int amount, String fullName) {
        this.loanType = loanType;
        this.months = months;
        this.amount = amount;
        this.fullName = fullName;
    }

    public int getMonths() {
        return months;
    }

    public int getAmount() {
        return amount;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public String getFullName() {
        return fullName;
    }

    public String toString(){
         return "RQ: {"+this.getAmount()+" for: "+this.getMonths()+"}";
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
