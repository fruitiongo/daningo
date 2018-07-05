package com.daningo.entity;

import com.sun.istack.internal.NotNull;

/**
 * Created by naing on 6/20/18.
 */
public class User {
    int userID;
    String username;
    String fullName;
    String firstName;
    String lastName;
    String email;
    String profilePicture;
    String password;
    long signupTimestamp;
    long lastLoginTimestamp;
    int isInfluencer;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public boolean valid() {

        if(email == null)
            return false;
        if(firstName == null)
            return false;
        if(lastName == null)
            return false;
        return true;
    }
}
