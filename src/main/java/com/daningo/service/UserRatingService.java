package com.daningo.service;


import com.daningo.entity.User;
import com.daningo.entity.UserRating;
import com.daningo.serviceImp.UserRatingServiceImp;
import com.daningo.util.APIConstant;
import com.daningo.util.DaningoAPIInjector;
import com.google.gson.Gson;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

public class UserRatingService extends BaseService {
    @Get("JSON")
    public Representation doGet (Representation entity) {
        String resposneJson = "{}";

        try{
            UserRating userRating = new UserRating();
            resposneJson = getResponseJSON(userRating, APIConstant.USER);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new StringRepresentation(resposneJson, MediaType.APPLICATION_ALL_JSON);
    }

    @Post("JSON")
    public Representation doPost(Representation entity) {
        String responseJson="{}";
        try {
            setResponseHeaders();
            String postBody = entity.getText();
            Gson gson = new Gson();
            UserRating userRating = gson.fromJson(postBody, UserRating.class);

            UserRatingServiceImp userRatingServiceImp = DaningoAPIInjector.getInjector().getInstance(UserRatingServiceImp.class);
            if(userRating.valid()) {
                userRating = userRatingServiceImp.add(userRating);
                responseJson = getResponseJSON(userRating, APIConstant.USER);
            } else {
                setStatus(Status.CLIENT_ERROR_BAD_REQUEST,"Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
