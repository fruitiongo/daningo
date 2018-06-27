package com.daningo.service;

import com.daningo.DaningoAPI;
import com.daningo.entity.Message;
import com.daningo.serviceImp.MessageServiceImp;
import com.daningo.util.APIConstant;
import com.daningo.util.DaningoAPIInjector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

import java.sql.Timestamp;
import java.util.List;

public class MessageService extends BaseService {
    @Get("JSON")
    public Representation doGet(Representation entity) {
        String responseJson = "{}";
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        try{
            setResponseHeaders();
            MessageServiceImp messageServiceImp = DaningoAPIInjector.getInjector().getInstance(MessageServiceImp.class);

            int topicID = Integer.parseInt(getRequest().getAttributes().get("topicID").toString());
            int messageID = Integer.parseInt(getRequest().getAttributes().get("messageID").toString());

            List<Message> messages;
            if(messageID <= 0 )
                messages = messageServiceImp.getAll(topicID);
            else
                messages = messageServiceImp.getAll(topicID,messageID);
            responseJson = getResponseJSON(messages , "");

        }catch (Exception e) {
            e.printStackTrace();
        }
        return  new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }

    @Post("JSON")
    public Representation doPost(Representation entity) {
        String responseJson = "{}";
        try{
            setResponseHeaders();
            String postBody = entity.getText();
            Gson gson = new Gson();
            Message message = gson.fromJson(postBody, Message.class);
            MessageServiceImp messageServiceImp = DaningoAPIInjector.getInjector().getInstance(MessageServiceImp.class);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            message.setTimestamp(timestamp.getTime()/1000);
            message = messageServiceImp.add(message);
            responseJson = getResponseJSON(message, APIConstant.MESSAGE);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
