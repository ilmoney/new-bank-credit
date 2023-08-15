package com.hzbank.credit.entity;

public class CampusCard {

    //身份证号
    private String idNumber;
    //手机号
    private String phoneNumber;
    //一卡通账号
    private String campusCardID;
    //交易密码
    private String transactionPassword;
    //账户余额
    private Float balance;
    public CampusCard() {
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCampusCardID() {
        return campusCardID;
    }

    public void setCampusCardID(String campusCardID) {
        this.campusCardID = campusCardID;
    }

    public String getTransactionPassword() {
        return transactionPassword;
    }

    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
