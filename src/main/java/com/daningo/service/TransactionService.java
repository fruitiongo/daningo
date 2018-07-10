package com.daningo.service;

import com.daningo.DaningoAPI;
import com.daningo.entity.StripeUser;
import com.daningo.entity.User;
import com.daningo.entity.UserTransaction;
import com.daningo.serviceImp.TransactionServiceImp;
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

import javax.jws.soap.SOAPBinding;
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

            UserTransaction transaction = gson.fromJson(postBody, UserTransaction.class);
            TransactionServiceImp transactionServiceImp = DaningoAPIInjector.getInjector().getInstance(TransactionServiceImp.class);
            UserServiceImp userServiceImp = DaningoAPIInjector.getInjector().getInstance(UserServiceImp.class);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            transaction.setPaymentTimestamp(timestamp.getTime()/1000);

            if(transaction.validPayment()) {

                StripeUser stripeUser = userServiceImp.getStripeCustomer(userID);

                if(stripeUser.getStripeID() != null) {
                    // get transaction Id from payment platform

                    User user = new User();
                    user.setUserID(userID);
                    user = userServiceImp.get(user);
                    transaction.getUserPayment().setUser(user);
                    transaction = StripeUtil.createCharge(transaction);

                    // do something with transaction fail

                    transaction.setStripeTransactionID(transaction.getStripeTransactionID());
                    transactionServiceImp.add(transaction);
                }
            }
            responseJson = getResponseJSON(transaction, APIConstant.PAYMENT);

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
            int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
