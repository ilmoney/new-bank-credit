package com.hzbank.credit.entity;

import java.util.Date;

public class CashAdvance {

    private String cardNumber;
    private Float preApprovedAmount;
    private Date loanDate;
    private Float dailyLoanAmount;

    public CashAdvance() {
    }

    public CashAdvance(String creditCardID, float preApprovedAmount, Date loanDate, float dailyLoanAmount) {
        this.cardNumber = creditCardID;
        this.preApprovedAmount = preApprovedAmount;
        this.loanDate = loanDate;
        this.dailyLoanAmount = dailyLoanAmount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public float getPreApprovedAmount() {
        return preApprovedAmount;
    }

    public void setPreApprovedAmount(float preApprovedAmount) {
        this.preApprovedAmount = preApprovedAmount;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public float getDailyLoanAmount() {
        return dailyLoanAmount;
    }

    public void setDailyLoanAmount(float dailyLoanAmount) {
        this.dailyLoanAmount = dailyLoanAmount;
    }
}
