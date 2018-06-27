package com.daningo.entity;

public class UserTopic extends User {
    String userTopicType;
    int topicUserID;

    public int getTopicUserID() {
        return topicUserID;
    }

    public void setTopicUserID(int topicUserID) {
        this.topicUserID = topicUserID;
    }


    public String getUserTopicType() {
        return userTopicType;
    }

    public void setUserTopicType(String userTopicType) {
        this.userTopicType = userTopicType;
    }
}
