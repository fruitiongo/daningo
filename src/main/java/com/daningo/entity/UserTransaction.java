package com.daningo.entity;

public class UserTransaction {
    int transactionID;
    UserPayment userPayment;
    int userID;
    int topicID;
    double amount;
    String StripeTransactionID;
    String paymentStatus;
    long paymentTimestamp;

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public void setPaymentStatus(String status) {
        this.paymentStatus = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }


    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public String getStripeTransactionID() {
        return StripeTransactionID;
    }

    public void setStripeTransactionID(String stripeTransactionID) {
        StripeTransactionID = stripeTransactionID;
    }

    public long getPaymentTimestamp() {
        return paymentTimestamp;
    }

    public UserPayment getUserPayment() {
        return userPayment;
    }

    public void setPaymentTimestamp(long paymentTimestamp) {
        this.paymentTimestamp = paymentTimestamp;
    }

    public void setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
    }

    public boolean validPayment(){
        if(this.topicID <= 0)
            return false;
        if(this.amount <= 0)
            return false;
        if(this.paymentStatus == null)
            return false;

        return true;
    }
}
