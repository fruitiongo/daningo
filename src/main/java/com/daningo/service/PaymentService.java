package com.daningo.service;

import com.daningo.DaningoAPI;
import com.daningo.entity.Stripe;
import com.daningo.entity.UserPayment;
import com.daningo.serviceImp.PaymentServiceImp;
import com.daningo.serviceImp.UserServiceImp;
import com.daningo.util.APIConstant;
import com.daningo.util.DaningoAPIInjector;
import com.google.gson.Gson;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.util.List;

public class PaymentService extends BaseService {
    @Get("JSON")
    public Representation doGet(Representation entity) {
        String responseJson = "{}";
        try{
            setResponseHeaders();
            int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());
            PaymentServiceImp paymentServiceImp = DaningoAPIInjector.getInjector().getInstance(PaymentServiceImp.class);
            List<UserPayment> userPayments;
            userPayments = paymentServiceImp.getAll(userID);
            responseJson = getResponseJSON(userPayments, APIConstant.PAYMENT);
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
            int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());

            Stripe stripe = userServiceImp.getStripeCustomer(userID);

            if(stripe != null || stripe.getStripeID() != null) {
                // Stripe adding customer

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
