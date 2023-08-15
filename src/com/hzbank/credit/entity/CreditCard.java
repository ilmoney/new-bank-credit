package com.hzbank.credit.entity;

public class CreditCard {
    //卡号
    private String creditCardID;
    //一卡通账号
    private String campusCardNumber;
    //交易密码
    private String transactionPassword;
    //信用额度
    private float creditLimit;
    //币种
    private String currency;
    //可透支额度
    private float availableCredit;
    //年费
    private float annualFee;
    //消费次数
    private int transactionCount;
    //已还款金额
    private float totalRepaid;
    //已消费金额
    private float totalSpent;
    //最低还款金额
    private float minimumRepaymeng;
    //卡内余额
    private float cardBalance;
    //应还金额
    private float amountDue;
    //未还部分（最低）
    private float unpaidMinmumRepayment;
    //日利息
    private float dailyInterest;

    public CreditCard() {
    }



    public String getCreditCardID() {
        return creditCardID;
    }

    public void setCreditCardID(String creditCardID) {
        this.creditCardID = creditCardID;
    }

    public String getCampusCardNumber() {
        return campusCardNumber;
    }

    public void setCampusCardNumber(String campusCardNumber) {
        this.campusCardNumber = campusCardNumber;
    }

    public String getTransactionPassword() {
        return transactionPassword;
    }

    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(float creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getAvailableCredit() {
        return availableCredit;
    }

    public void setAvailableCredit(float availableCredit) {
        this.availableCredit = availableCredit;
    }

    public float getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(float annualFee) {
        this.annualFee = annualFee;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public float getTotalRepaid() {
        return totalRepaid;
    }

    public void setTotalRepaid(float totalRepaid) {
        this.totalRepaid = totalRepaid;
    }

    public float getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(float totalSpent) {
        this.totalSpent = totalSpent;
    }

    public float getMinimumRepaymeng() {
        return minimumRepaymeng;
    }

    public void setMinimumRepaymeng(float minimumRepaymeng) {
        this.minimumRepaymeng = minimumRepaymeng;
    }

    public float getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(float cardBalance) {
        this.cardBalance = cardBalance;
    }

    public float getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(float amountDue) {
        this.amountDue = amountDue;
    }

    public float getUnpaidMinmumRepayment() {
        return unpaidMinmumRepayment;
    }

    public void setUnpaidMinmumRepayment(float unpaidMinmumRepayment) {
        this.unpaidMinmumRepayment = unpaidMinmumRepayment;
    }

    public float getDailyInterest() {
        return dailyInterest;
    }

    public void setDailyInterest(float dailyInterest) {
        this.dailyInterest = dailyInterest;
    }
}
