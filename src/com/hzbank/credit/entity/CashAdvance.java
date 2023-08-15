package com.hzbank.credit.entity;

import java.util.Date;

public class CashAdvance {

    //卡号
    private String cardNumber;
    //预借现金额度
    private Float preApprovedAmount;
    //借款日期
    private Date loanDate;
    //每日借款额
    private Float dailyLoanAmount;

    public CashAdvance() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Float getPreApprovedAmount() {
        return preApprovedAmount;
    }

    public void setPreApprovedAmount(Float preApprovedAmount) {
        this.preApprovedAmount = preApprovedAmount;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Float getDailyLoanAmount() {
        return dailyLoanAmount;
    }

    public void setDailyLoanAmount(Float dailyLoanAmount) {
        this.dailyLoanAmount = dailyLoanAmount;
    }
}
