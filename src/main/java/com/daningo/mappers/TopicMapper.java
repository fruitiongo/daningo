package com.daningo.mappers;

import com.daningo.entity.Message;
import com.daningo.entity.Topic;
import com.daningo.entity.UserTopic;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TopicMapper {
    @Select("select t.topic_id, topic, amount, timestamp, status from topics t join topic_users tu on tu.topic_id = t.topic_id where user_id = #{userID}")
    @Results({
            @Result(column = "topic_id" , property = "topicID"),
            @Result(column = "topic" , property =  "topic"),
            @Result(column = "timestamp" , property = "timestamp"),
            @Result(column = "status" , property = "status"),
            @Result(column = "amount" , property = "amount")
    })
    List<Topic> getTopics(int userID);

    @Select("select user_id,  type from topic_users where topic_id = #{0} and user_id != #{1}")
    @Results({
            @Result(column = "user_id" , property = "userID"),
            @Result(column = "type" , property = "userTopicType")
    })
    UserTopic getTopicUser(int userID, int topicID);

    @Select("select t.topic_id, topic, amount, timestamp, status from topics t join topic_users tu on tu.topic_id = t.topic_id where user_id = #{0} and t.topic_id= #{1}")
    @Results({
            @Result(column = "topic_id" , property = "topicID"),
            @Result(column = "topic" , property =  "topic"),
            @Result(column = "timestamp" , property = "timestamp"),
            @Result(column = "status" , property = "status"),
            @Result(column = "amount" , property = "amount")
    })
    Topic getTopic(int userID,int topicID);

    @Select("select message_id, user_id, topic_id, message, type, timestamp from messages where topic_id=#{topicID} order by topic_id asc limit 1")
    @Results({
            @Result(column = "message_id" , property = "messageID"),
            @Result(column = "user_id" , property = "user.userID"),
            @Result(column = "topic_id" , property = "topicID"),
            @Result(column = "message" , property = "message"),
            @Result(column = "type" , property = "type"),
            @Result(column = "timestamp" , property = "timestamp")
    })
    Message getTopicDescription(int topicID);


    @Insert("insert into topics (topic, amount, timestamp) values (#{topic}, #{amount}, #{timestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "topicID", keyColumn="topic_id")
    void createTopic(Topic topic);

    @Insert("insert into topic_users (topic_id, user_id, type) values ( #{0}, #{1}, #{2})")
    void createTopicUser(int topicID, int userID, String type);

    @Update("update topics set topic=#{topic}, amount=#{amount}, status=#{status} where topic_id=#{topicID}")
    void updateTopic(Topic topic);
}
