package com.hzbank.credit.entity;

public class CampusCard {

    private String idNumber;
    private String phoneNumber;
    private String campusCardID;

    private String transactionPassword;
    private float balance;

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

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
