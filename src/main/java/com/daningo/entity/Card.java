package com.daningo.entity;

public class Card {
    int cardNumber;
    int expMonth;
    int expYear;
    int cvc;

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public int getExpYear() {
        return expYear;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvc() {
        return cvc;
    }

    public int getCardNumber() {
        return cardNumber;
    }

}
