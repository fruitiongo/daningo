package com.daningo.mappers;

/**
 * Created by naing on 6/21/18.
 */

import com.daningo.entity.Stripe;
import com.daningo.entity.User;
import com.daningo.entity.UserRating;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("Select user_id, username, profile_picture, full_name, is_influencer, stripe_customer_id, signup_timestamp, last_login_timestamp from users where user_id=#{userID}")
    @Results({
            @Result(column = "user_id" , property = "userID"),
            @Result(column = "username" , property = "username"),
            @Result(column = "profile_picture" , property = "profilePicture"),
            @Result(column = "full_name" , property = "fullName"),
            @Result(column = "stripe_customer_id" , property = "stripeCustomerID"),
            @Result(column = "is_influencer" , property = "isInfluencer"),
            @Result(column = "signup_timestamp" , property = "signupTimestamp"),
            @Result(column = "lastLoginTimestamp" , property = "lastTimestamp")
    })
    User getUser(int userID);

    @Select("select u.user_id, username, profile_picture, full_name, is_influencer, stripe_customer_id, signup_timestamp, last_login_timestamp, IFNULL(rating, -1) rating from users  u left  join (select user_id, sum(rating)/count(*) rating from user_ratings group by user_id) r  on r.user_id = u.user_id where is_influencer = 1;")
    @Results({
            @Result(column = "user_id" , property = "userID"),
            @Result(column = "username" , property = "username"),
            @Result(column = "profile_picture" , property = "profilePicture"),
            @Result(column = "full_name" , property = "fullName"),
            @Result(column = "stripe_customer_id" , property = "stripeCustomerID"),
            @Result(column = "is_influencer" , property = "isInfluencer"),
            @Result(column = "signup_timestamp" , property = "signupTimestamp"),
            @Result(column = "lastLoginTimestamp" , property = "lastTimestamp"),
            @Result(column = "rating" , property = "rating")
    })
    List<UserRating> getUsers();

    @Select("Select u.user_id, username, profile_picture, full_name, is_influencer, stripe_customer_id, signup_timestamp, last_login_timestamp, sum(rating) / count(*)  as rating  from users u join user_rating ur on ur.user_id = u.user_id where u.user_id=#{userID}")
    @Results({
            @Result(column = "user_id" , property = "userID"),
            @Result(column = "username" , property = "username"),
            @Result(column = "profile_picture" , property = "profilePicture"),
            @Result(column = "full_name" , property = "fullName"),
            @Result(column = "stripe_customer_id" , property = "stripeCustomerID"),
            @Result(column = "is_influencer" , property = "isInfluencer"),
            @Result(column = "signup_timestamp" , property = "signupTimestamp"),
            @Result(column = "lastLoginTimestamp" , property = "lastTimestamp"),
            @Result(column = "rating" , property = "rating")
    })
    UserRating getUserRating(int userID);

    @Select("Select u.user_id, profile_picture, full_name, is_influencer, signup_timestamp, last_login_timestamp from users where email = #{0} and password = md5(#{1}) ")
    @Results({
            @Result(column = "user_id" , property = "userID"),
            @Result(column = "username" , property = "username"),
            @Result(column = "profile_picture" , property = "profilePicture"),
            @Result(column = "full_name" , property = "fullName"),
            @Result(column = "is_influencer" , property = "isInfluencer"),
            @Result(column = "signup_timestamp" , property = "signupTimestamp"),
            @Result(column = "lastLoginTimestamp" , property = "lastTimestamp")
    })
    User auth(String email, String password);

    @Insert("insert into users (username, profile_picture, full_name, is_influencer, signup_timestamp, last_login_timestamp) values (#{username}, #{profilePicture}, #{fullName}, #{isInfluencer}, #{signupTimestamp}, #{lastLoginTimestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "userID", keyColumn="user_id")
    void addUser(User user);

    @Update("update users set username=#{username}, profile_picture=#{profilePicture}, full_name=#{fullName}, is_influencer=#{isInfluencer}, signup_timestamp=#{signupTimestamp}, last_login_timestamp=#{lastLoginTimestamp} where user_id=#{userID}")
    void updateUser(User user);

    @Insert("insert into user_ratings (user_id, topic_id, rating, timestamp) values ( #{userID}, #{topicID}, #{rating}, #{timestamp}) ")
    @Options(useGeneratedKeys = true, keyProperty = "userRatingID", keyColumn = "user_rating_id")
    void addUserRating(UserRating userRating);

    @Select("select u.stripe_customer_id from user where user_id = #{userID}")
    @Results({
            @Result(column = "stripe_customer_id", property = "stripeCustomerID")
    })
    Stripe getStripeCustomer(int userID);
}
