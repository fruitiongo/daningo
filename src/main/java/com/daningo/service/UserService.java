package com.daningo.service;

import com.daningo.DaningoAPI;
import com.daningo.entity.User;
import com.daningo.serviceImp.UserServiceImp;
import com.daningo.util.DaningoAPIInjector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;

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

            int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());

            User user = new User();
            user.setUserID(userID);
            user = userServiceImp.get(user);
            responseJson = getResponseJSON(user, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
