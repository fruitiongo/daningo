package com.daningo.service;

import com.daningo.DaningoAPI;
import com.daningo.entity.User;
import com.daningo.serviceImp.UserServiceImp;
import com.daningo.util.APIConstant;
import com.daningo.util.DaningoAPIInjector;
import com.google.gson.Gson;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;


public class AuthService extends BaseService{
    @Get("JSON")
    public Representation doGet(Representation entity) {
        String responseJson = "{}";
        try {
            String email = getRequest().getAttributes().get("email").toString();
            String password = getRequest().getAttributes().get("password").toString();

            UserServiceImp userServiceImp = DaningoAPIInjector.getInjector().getInstance(UserServiceImp.class);

            User user = new User();

            user = userServiceImp.auth(email, password);

            responseJson = getResponseJSON(user, APIConstant.USER);

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
            if(user.valid() && user.getPassword() != null) {
                user = userServiceImp.add(user);
                responseJson = getResponseJSON(user, APIConstant.USER);
            } else {
                setStatus(Status.CLIENT_ERROR_BAD_REQUEST,"Error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
