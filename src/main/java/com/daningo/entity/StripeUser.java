package com.daningo.entity;

public class StripeUser {
    int userID;
    String StripeID;

    UserPayment userPayment;

    public void setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
    }

    public UserPayment getUserPayment() {
        return userPayment;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setStripeID(String stripeID) {
        StripeID = stripeID;
    }

    public String getStripeID() {
        return StripeID;
    }
}
