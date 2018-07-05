package com.daningo.service;

import com.daningo.entity.UserTransaction;
import com.daningo.serviceImp.TransactionServiceImp;
import com.daningo.util.APIConstant;
import com.daningo.util.DaningoAPIInjector;
import com.google.gson.Gson;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.sql.Timestamp;

public class TransactionService extends BaseService {
    @Get("JSON")
    public Representation doGet(Representation entity) {
        String responseJson = "{}";
        try {
            int topicID = Integer.parseInt(getRequest().getAttributes().get("topicID").toString());

            UserTransaction payment = new UserTransaction();
            //payment.setUser(new User());

            responseJson = getResponseJSON(payment, APIConstant.PAYMENT);

        } catch(Exception e) {
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

            int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());

            UserTransaction payment = gson.fromJson(postBody, UserTransaction.class);
            TransactionServiceImp transactionServiceImp = DaningoAPIInjector.getInjector().getInstance(TransactionServiceImp.class);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            if(payment.validPayment()) {

                // get transaction Id from payment platform
                String transactionID = "12131";

                // do something with transaction fail

                payment.setStripeTransactionID(transactionID);

                transactionServiceImp.add(payment);

                responseJson = getResponseJSON(payment, APIConstant.PAYMENT);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
