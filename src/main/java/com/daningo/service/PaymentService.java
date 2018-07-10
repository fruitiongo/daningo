package com.daningo.service;

import com.daningo.DaningoAPI;
import com.daningo.entity.*;
import com.daningo.serviceImp.PaymentServiceImp;
import com.daningo.serviceImp.UserServiceImp;
import com.daningo.util.APIConstant;
import com.daningo.util.DaningoAPIInjector;
import com.daningo.util.StripeUtil;
import com.google.gson.Gson;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

import java.util.List;

public class PaymentService extends BaseService {
    @Get("JSON")
    public Representation doGet(Representation entity) {
        String responseJson = "{}";
        try{
            setResponseHeaders();
            int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());
            PaymentServiceImp paymentServiceImp = DaningoAPIInjector.getInjector().getInstance(PaymentServiceImp.class);

            if(getRequest().getAttributes().get("paymentID") == null) {
                UserPayment userPayment = new UserPayment();
                userPayment.setAch(new ACH());
                userPayment.setCard(new Card());
                responseJson = getResponseJSON(userPayment, APIConstant.PAYMENT);
            } else if(getRequest().getAttributes().get("paymentID").equals("-1")) {
                List<UserPayment> userPayments;
                userPayments = paymentServiceImp.getAll(userID);
                responseJson = getResponseJSON(userPayments, APIConstant.PAYMENT);
            } else {
                int paymentID = Integer.parseInt(getRequest().getAttributes().get("paymentID").toString());

                UserPayment userPayment = new UserPayment();
                userPayment.setUser(new User());
                userPayment.getUser().setUserID(userID);
                userPayment.setUserPaymentID(paymentID);

                userPayment = paymentServiceImp.get(userPayment);

                responseJson = getResponseJSON(userPayment, APIConstant.PAYMENT);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }

    @Post("JSON")
    public Representation doPost(Representation entity) {
        String responseJson = "{}";

        try {
            setResponseHeaders();
            String postBody = entity.getText();
            Gson gson = new Gson();
            UserPayment userPayment = gson.fromJson(postBody, UserPayment.class);

            UserServiceImp userServiceImp = DaningoAPIInjector.getInjector().getInstance(UserServiceImp.class);
            PaymentServiceImp paymentServiceImp = DaningoAPIInjector.getInjector().getInstance(PaymentServiceImp.class);

            int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());

            User user =  new User();
            user.setUserID(userID);

            user = userServiceImp.get(user);

            StripeUser stripeUser = userServiceImp.getStripeCustomer(userID);
            //stripeUser.setUserPayment(userPayment);

            // Create Stripe Token first for the new card
            userPayment = StripeUtil.createCardToken(userPayment);
            stripeUser.setUserPayment(userPayment);

            // if customer id is not exist, then create new one with card token
            if(stripeUser == null || stripeUser.getStripeID() == null) {
                // StripeUser adding customer
                stripeUser = StripeUtil.createCustomer(user, stripeUser);
                userServiceImp.updateStripCustomer(stripeUser);
                userPayment = paymentServiceImp.add(stripeUser.getUserPayment());
            } else {
                StripeUtil.addCardToCustomer(stripeUser);
                userPayment = paymentServiceImp.add(stripeUser.getUserPayment());
            }

            responseJson = getResponseJSON(userPayment, APIConstant.PAYMENT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }

    @Put("JSON")
    public Representation doPut(Representation entity) {
        String responseJson = "{}";
        try {
            setResponseHeaders();
            String postBody = entity.getText();
            Gson gson = new Gson();
            UserPayment userPayment = gson.fromJson(postBody, UserPayment.class);
            UserServiceImp userServiceImp = DaningoAPIInjector.getInjector().getInstance(UserServiceImp.class);
            PaymentServiceImp paymentServiceImp = DaningoAPIInjector.getInjector().getInstance(PaymentServiceImp.class);

            int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());
            int paymentID = Integer.parseInt(getRequest().getAttributes().get("paymentID").toString());

            userPayment.setUserPaymentID(paymentID);

            User user =  new User();
            user.setUserID(userID);

            StripeUser stripeUser = userServiceImp.getStripeCustomer(userID);
            userPayment = StripeUtil.createCardToken(userPayment);
            stripeUser.setUserPayment(userPayment);
            if(stripeUser != null || stripeUser.getStripeID() != null) {
                // StripeUser adding customer
                stripeUser.setUserPayment(StripeUtil.addCardToCustomer(stripeUser));
                paymentServiceImp.update(stripeUser.getUserPayment());

            }

            responseJson = getResponseJSON(userPayment, APIConstant.PAYMENT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
