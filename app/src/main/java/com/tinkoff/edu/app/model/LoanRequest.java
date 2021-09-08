package com.tinkoff.edu.app.model;

public class LoanRequest {
    private LoanType loanType;
    private final int months;
    private final int amount;

     public LoanRequest(LoanType loanType, int months, int amount){
         this.loanType = loanType;
         this.months = months;
        this.amount = amount;
     }

    public int getMonths() {
        return months;
    }

    public int getAmount() {
        return amount;
    }

    public LoanType getPresonType() {
        return loanType;
    }

    public String toString(){
         return "RQ: {"+this.getAmount()+" for: "+this.getMonths()+"}";
    }
}
