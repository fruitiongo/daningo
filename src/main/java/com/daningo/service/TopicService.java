package com.daningo.service;

import com.daningo.DaningoAPI;
import com.daningo.entity.Message;
import com.daningo.entity.Topic;
import com.daningo.entity.User;
import com.daningo.entity.UserTopic;
import com.daningo.serviceImp.MessageServiceImp;
import com.daningo.serviceImp.TopicServiceImp;
import com.daningo.util.APIConstant;
import com.daningo.util.DaningoAPIInjector;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

import java.awt.image.RescaleOp;
import java.sql.Timestamp;
import java.util.List;

public class TopicService extends BaseService {
    @Get("JSON")
    public Representation doGet(Representation entity) {
        String responseJson = "{}";

        if(getRequest().getAttributes().get("topicID") != null) {
            try {
                setResponseHeaders();
                TopicServiceImp topicServiceImp = DaningoAPIInjector.getInjector().getInstance(TopicServiceImp.class);
                int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());
                int topicID = Integer.parseInt(getRequest().getAttributes().get("topicID").toString());

                Topic topic = new Topic();
                if(topicID > 0) {
                    topic = topicServiceImp.get(userID, topicID);
                }else {
                    topic.setUserTopic(new UserTopic());
                    topic.setDescription(new Message());
                    topic.getDescription().setUser(new User());
                }

                responseJson = getResponseJSON(topic, "");
            } catch(Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                setResponseHeaders();
                TopicServiceImp topicServiceImp = DaningoAPIInjector.getInjector().getInstance(TopicServiceImp.class);

                int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());

//            int offset = Integer.parseInt(getRequest().getAttributes().get("offset").toString());

                List<Topic> topics;
                topics = topicServiceImp.getAll(userID);
                responseJson = getResponseJSON(topics, "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }

    @Post("JSON")
    public Representation doPost(Representation entity) {
        String responseJson = "{}";
        try{
            setResponseHeaders();
            String postBody = entity.getText();
            Gson gson = new Gson();

            int userID = Integer.parseInt(getRequest().getAttributes().get("userID").toString());

            Topic topic = gson.fromJson(postBody, Topic.class);
            TopicServiceImp topicServiceImp = DaningoAPIInjector.getInjector().getInstance(TopicServiceImp.class);
            MessageServiceImp messageServiceImp = DaningoAPIInjector.getInjector().getInstance(MessageServiceImp.class);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            topic.setTimestamp(timestamp.getTime()/1000);
            topicServiceImp.add(topic);
            topic.getDescription().setTopicID(topic.getTopicID());
            topic.getDescription().getUser().setUserID(userID);
            topic.getDescription().setTimestamp(timestamp.getTime()/1000);

            topicServiceImp.addTopicUser(topic.getTopicID(), userID, "user");
            topicServiceImp.addTopicUser(topic.getTopicID(), topic.getUserTopic().getUserID(), "adviser");

            messageServiceImp.add(topic.getDescription());

            responseJson = getResponseJSON(topic, APIConstant.TOPIC);

        } catch(Exception e) {
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
            Topic topic = gson.fromJson(postBody, Topic.class);
            TopicServiceImp topicServiceImp = DaningoAPIInjector.getInjector().getInstance(TopicServiceImp.class);
            topicServiceImp.update(topic);
            responseJson = getResponseJSON(topic, APIConstant.TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new StringRepresentation(responseJson, MediaType.APPLICATION_ALL_JSON);
    }
}
