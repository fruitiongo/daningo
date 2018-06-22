package com.daningo.entity;

import com.sun.istack.internal.NotNull;

/**
 * Created by naing on 6/20/18.
 */
public class User {
    int userID;
    String username;
    String fullName;
    String profilePicture;
    long signupTimestamp;
    long lastLoginTimestamp;
    int isInfluencer;

    public int getIsInfluencer() {
        return isInfluencer;
    }

    public long getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    public long getSignupTimestamp() {
        return signupTimestamp;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public int getUserID() {
        return userID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setIsInfluencer(int isInfluencer) {
        this.isInfluencer = isInfluencer;
    }

    public void setLastLoginTimestamp(long lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setSignupTimestamp(long signupTimestamp) {
        this.signupTimestamp = signupTimestamp;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
