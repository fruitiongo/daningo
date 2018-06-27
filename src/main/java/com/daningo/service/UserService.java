package com.daningo.service;

import com.daningo.DaningoAPI;
import com.daningo.entity.User;
import com.daningo.entity.UserRating;
import com.daningo.serviceImp.UserServiceImp;
import com.daningo.util.APIConstant;
import com.daningo.util.DaningoAPIInjector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

import java.util.List;


/**
 * Created by naing on 6/20/18.
 */
public class UserService extends BaseService {
    @Get("JSON")
    public Representation doGet (Representation entity) {
        String responseJson = "{}";
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        try {
            setResponseHeaders();
            UserServiceImp userServiceImp = DaningoAPIInjector.getInjector().getInstance(UserServiceImp.class);

            if (getRequest().getAttributes().get("userID") == null) {
                List<UserRating> users;
                users = userServiceImp.getAllUsersWithRating();
                responseJson = getResponseJSON(users, "");
            } else {
                int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());

                User user = new User();
                user.setUserID(userID);
                user = userServiceImp.get(user);
                responseJson = getResponseJSON(user, "");
            }
        } catch (Exception e) {
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
            User user = gson.fromJson(postBody, User.class);
            UserServiceImp userServiceImp = DaningoAPIInjector.getInjector().getInstance(UserServiceImp.class);
            if(user.valid()) {
                user = userServiceImp.add(user);
                responseJson = getResponseJSON(user, APIConstant.USER);
            } else {
                setStatus(Status.CLIENT_ERROR_BAD_REQUEST,"Error");
                //responseJson = getResponseJSON(user, APIConstant.NOT_ENOUGH);
            }
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
            User user = gson.fromJson(postBody, User.class);
            UserServiceImp userServiceImp = DaningoAPIInjector.getInjector().getInstance(UserServiceImp.class);
            if(user.valid()) {
                userServiceImp.update(user);
                responseJson = getResponseJSON(user, APIConstant.USER);
            } else {
                responseJson = getResponseJSON(user, APIConstant.NOT_ENOUGH);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
