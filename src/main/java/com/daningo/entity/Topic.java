package com.daningo.entity;

import java.util.List;

public class Topic {
    long timestamp;
    String topic;
    double amount;
    String status;
    int topicID;
    UserTopic userTopic;
    Message description;

    //List<Message> messages;

    public Message getDescription() {
        return description;
    }

    public void setDescription(Message description) {
        this.description = description;
    }

    public UserTopic getUserTopic() {
        return userTopic;
    }

    public void setUserTopic(UserTopic userTopic) {
        this.userTopic = userTopic;
    }

    public int getTopicID() {
        return topicID;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    /*
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    */

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
