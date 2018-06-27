package com.daningo.entity;

import com.sun.istack.internal.NotNull;

public class Message {
    User user;
    int messageID;
    int topicID;
    String message;
    long timestamp;
    String type;

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getMessageID() {
        return messageID;
    }

    public String getType() {
        return type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
