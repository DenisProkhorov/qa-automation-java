package com.tinkoff.edu.app.model;

public class LoanRequest {
    private final int months;
    private final int amount;

     public LoanRequest(int months, int amount){
        this.months = months;
        this.amount = amount;
     }

    public int getMonths() {
        return months;
    }

    public int getAmount() {
        return amount;
    }

    public String toString(){
         return "RQ: {"+this.getAmount()+" for: "+this.getMonths()+"}";
    }
}
