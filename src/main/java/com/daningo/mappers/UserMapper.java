package com.daningo.mappers;

/**
 * Created by naing on 6/21/18.
 */

import com.daningo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("Select user_id, username, profile_picture, full_name, is_influencer, signup_timestamp, last_login_timestamp where user_id=#{userID}")
    @Results({
            @Result(column = "user_id" , property = "userID"),
            @Result(column = "username" , property = "username"),
            @Result(column = "profile_picture" , property = "profilePicture"),
            @Result(column = "full_name" , property = "fullName"),
            @Result(column = "is_influencer" , property = "isInfluencer"),
            @Result(column = "signup_timestamp" , property = "signupTimestamp"),
            @Result(column = "lastLoginTimestamp" , property = "lastTimestamp")
    })
    User getUser(int userID);

    @Insert("insert into users (username, profile_picture, full_name, is_influencer, signup_timestamp, last_login_timestamp) values (#{username}, #{profilePicture}, #{fullName}, #{isInfluencer}, #{signupTimestamp}, #{lastLoginTimestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "userID", keyColumn="user_id")
    void addUser(User user);

    @Update("update users set username=#{username}, profile_picture=#{profilePicture}, full_name=#{fullName}, is_influencer=#{isInfluencer}, signup_timestamp=#{signupTimestamp}, last_login_timestamp=#{lastLoginTimestamp} where user_id=#{userID}")
    void updateUser(User user);
}
