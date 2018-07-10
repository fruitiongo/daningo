package com.daningo.util;

import com.daningo.entity.StripeUser;
import com.daningo.entity.User;
import com.daningo.entity.UserPayment;
import com.daningo.entity.UserTransaction;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Refund;
import com.stripe.model.Token;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

public class StripeUtil {
    public static final String API_KEY="sk_test_Q0PpHitCQWuvmZbXu7lebyyn";
    public static StripeUser createCustomer(User user, StripeUser stripeUser) {
        Stripe.apiKey = API_KEY;

        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("description", user.getEmail() );
        customerParams.put("source" , stripeUser.getUserPayment().getStripeID());
        try {
            Customer customer = Customer.create(customerParams);
            stripeUser.setStripeID(customer.getId());

        } catch(Exception e){
            e.printStackTrace();
        }
        return stripeUser;
    }

    public static UserPayment createCardToken(UserPayment userPayment) {
        Stripe.apiKey = API_KEY;

        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();

        cardParams.put("number", userPayment.getCard().getCardNumber() );
        cardParams.put("exp_month" , userPayment.getCard().getExpMonth());
        cardParams.put("exp_year", userPayment.getCard().getExpYear());
        cardParams.put("cvc" , userPayment.getCard().getCvc());
        tokenParams.put("card" , cardParams);

        try {
            Token token = Token.create(tokenParams);
            userPayment.setStripeID(token.getId());
            userPayment.setBankName(token.getCard().getBrand());
            userPayment.setLastFour(Integer.parseInt(token.getCard().getLast4()));
        } catch(Exception e){
            e.printStackTrace();
        }

        return userPayment;
    }

    public static UserPayment addCardToCustomer(StripeUser stripeUser) {
        Stripe.apiKey = API_KEY;
        try {
            Customer customer = Customer.retrieve(stripeUser.getStripeID());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("source", stripeUser.getUserPayment().getStripeID());
            customer.getSources().create(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stripeUser.getUserPayment();
    }

    public static UserTransaction createCharge ( UserTransaction userTransaction) {
        Stripe.apiKey = API_KEY;
        try {
            Map<String, Object> chargeParams = new HashMap<String, Object>();
            chargeParams.put("amount", userTransaction.getAmount());
            chargeParams.put("currency" , "usd");
            chargeParams.put("description", "Charge for "+ userTransaction.getUserPayment().getUser().getEmail());
            chargeParams.put("source" , userTransaction.getUserPayment().getStripeID());
            Charge charge = Charge.create(chargeParams);
            userTransaction.setStripeTransactionID(charge.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userTransaction;
    }

    public static UserTransaction refundCharge (UserTransaction userTransaction) {
        Stripe.apiKey = API_KEY;
        try {
            Map<String, Object> refundParams = new HashMap<String, Object>();
            refundParams.put("charge", userTransaction.getStripeTransactionID());
            Refund refund = Refund.create(refundParams);
            if(refund.getId() != null && refund.getStatus().equals("succeeded")) {
                userTransaction.setPaymentStatus("refunded");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return userTransaction;
    }
}
