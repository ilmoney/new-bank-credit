package com.hzbank.credit.entity;

import java.util.Date;

public class Transactions {
    private String creditCard;
    private String expenseSource;
    private float expenseAmount;
    private Date expenseTime;
    private String expenseType;

    public Transactions() {
    }

    public Transactions(String creditCard, String expenseSource, float expenseAmount, Date expenseTime, String expenseType) {
        this.creditCard = creditCard;
        this.expenseSource = expenseSource;
        this.expenseAmount = expenseAmount;
        this.expenseTime = expenseTime;
        this.expenseType = expenseType;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getExpenseSource() {
        return expenseSource;
    }

    public void setExpenseSource(String expenseSource) {
        this.expenseSource = expenseSource;
    }

    public float getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(float expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Date getExpenseTime() {
        return expenseTime;
    }

    public void setExpenseTime(Date expenseTime) {
        this.expenseTime = expenseTime;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }
}
