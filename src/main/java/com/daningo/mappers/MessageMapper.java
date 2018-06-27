package com.daningo.mappers;

import com.daningo.entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MessageMapper {
    @Select("select message_id, user_id, topic_id, message, type, timestamp from messages where topic_id=#{topicID} order by message_id desc limit 15")
    @Results({
            @Result(column = "message_id" , property = "messageID"),
            @Result(column = "user_id" , property = "user.userID"),
            @Result(column = "topic_id" , property = "topicID"),
            @Result(column = "message" , property = "message"),
            @Result(column = "type" , property = "type"),
            @Result(column = "timestamp" , property = "timestamp")
    })

    List<Message> getMessages(int topicID);

    @Select("select message_id, user_id, topic_id, message, type, timestamp from messages where topic_id=#{0} and message_id<#{1} order by message_id desc limit 15")
    @Results({
            @Result(column = "message_id" , property = "messageID"),
            @Result(column = "user_id" , property = "user.userID"),
            @Result(column = "topic_id" , property = "topicID"),
            @Result(column = "message" , property = "message"),
            @Result(column = "type" , property = "type"),
            @Result(column = "timestamp" , property = "timestamp")
    })

    List<Message> getMessagesByMessageID(int topicID, int messageID);


    @Insert("insert into messages (topic_id, user_id, message, type, timestamp) values (#{topicID} , #{user.userID}, #{message}, #{type}, #{timestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "messageID", keyColumn="message_id")
    void createMessage(Message message);
}
