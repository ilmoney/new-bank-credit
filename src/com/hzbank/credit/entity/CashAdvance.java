package com.hzbank.credit.entity;

import java.util.Date;

public class CashAdvance {
    //卡号
    private String creditCardID;
    //预借现金额度
    private float preApprovedAmount;
    //借款日期
    private Date loanDate;
    //每日借款额
    private float dailyLoanAmount;

    public CashAdvance() {
    }

    public CashAdvance(String creditCardID, float preApprovedAmount, Date loanDate, float dailyLoanAmount) {
        this.creditCardID = creditCardID;
        this.preApprovedAmount = preApprovedAmount;
        this.loanDate = loanDate;
        this.dailyLoanAmount = dailyLoanAmount;
    }

    public String getCreditCardID() {
        return creditCardID;
    }

    public void setCreditCardID(String creditCardID) {
        this.creditCardID = creditCardID;
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
