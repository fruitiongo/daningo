package com.daningo.entity;

public class UserRating extends User {
    public double rating;
    public int userRatingID;
    public long timestamp;
    public int topicID;

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setUserRatingID(int userRatingID) {
        this.userRatingID = userRatingID;
    }

    public int getUserRatingID() {
        return userRatingID;
    }

    @Override
    public boolean valid(){
        if(this.topicID <= 0 )
            return false;

        return true;
    }
}
