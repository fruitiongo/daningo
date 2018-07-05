package com.daningo.entity;

public class UserPayment {
    int userPaymentID;
    String paymentMethod;
    String stripeID;
    int lastFour;
    String country;
    String zipcode;
    String bankName;

    User user;
    ACH ach;
    Card card;

    // this is for bank acc


    public ACH getAch() {
        return ach;
    }

    public Card getCard() {
        return card;
    }

    public void setAch(ACH ach) {
        this.ach = ach;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLastFour() {
        return lastFour;
    }

    public int getUserPaymentID() {
        return userPaymentID;
    }

    public String getBankName() {
        return bankName;
    }

    public String getCountry() {
        return country;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStripeID() {
        return stripeID;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLastFour(int lastFour) {
        this.lastFour = lastFour;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStripeID(String stripeID) {
        this.stripeID = stripeID;
    }

    public void setUserPaymentID(int userPaymentID) {
        this.userPaymentID = userPaymentID;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean paymentValid() {
        if(this.stripeID != null)
            return false;
        if(this.paymentMethod != null)
            return false;
        return true;
    }


}
