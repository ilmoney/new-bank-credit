package com.hzbank.credit.entity;

public class CreditCard {
    //卡号
    private String creditCard;
    //一卡通账号
    private String campusCardNumber;
    //信用卡状态
    private String cardStatus;
    //交易密码
    private String transactionPassword;
    //信用额度
    private Float creditLimit;
    //币种
    private String currency;
    //可透支额度
    private Float availableCredit;
    //年费
    private Float annualFee;
    //消费次数
    private Integer transactionCount;
    //已还款金额
    private Float totalRepaid;
    //已消费金额
    private Float totalSpent;
    //最低还款金额
    private Float minimumRepayment;
    //卡内余额
    private Float cardBalance;
    //应还金额
    private Float amountDue;
    //未还部分（最低）
    private Float unpaidMinmumRepayment;
    //日利息
    private Float dailyInterest;

    public CreditCard() {
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCampusCardNumber() {
        return campusCardNumber;
    }

    public void setCampusCardNumber(String campusCardNumber) {
        this.campusCardNumber = campusCardNumber;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getTransactionPassword() {
        return transactionPassword;
    }

    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }

    public Float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Float creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Float getAvailableCredit() {
        return availableCredit;
    }

    public void setAvailableCredit(Float availableCredit) {
        this.availableCredit = availableCredit;
    }

    public Float getAnnualFee() {
        return annualFee;
    }

    public void setAnnualFee(Float annualFee) {
        this.annualFee = annualFee;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Float getTotalRepaid() {
        return totalRepaid;
    }

    public void setTotalRepaid(Float totalRepaid) {
        this.totalRepaid = totalRepaid;
    }

    public Float getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Float totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Float getMinimumRepayment() {
        return minimumRepayment;
    }

    public void setMinimumRepayment(Float minimumRepayment) {
        this.minimumRepayment = minimumRepayment;
    }

    public Float getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(Float cardBalance) {
        this.cardBalance = cardBalance;
    }

    public Float getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Float amountDue) {
        this.amountDue = amountDue;
    }

    public Float getUnpaidMinmumRepayment() {
        return unpaidMinmumRepayment;
    }

    public void setUnpaidMinmumRepayment(Float unpaidMinmumRepayment) {
        this.unpaidMinmumRepayment = unpaidMinmumRepayment;
    }

    public Float getDailyInterest() {
        return dailyInterest;
    }

    public void setDailyInterest(Float dailyInterest) {
        this.dailyInterest = dailyInterest;
    }
}
