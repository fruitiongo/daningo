package com.daningo.mappers;

import com.daningo.entity.UserTransaction;
import org.apache.ibatis.annotations.*;

public interface TransactionMapper {
    @Select("select payment_id, t.topic_id, amount, transaction_id, p.status, p.timestamp from payments p join Topic t on t.topic_id = p.topic_id join topic_users tp on tu.topic_id = t.topic_id where topic_id = #{topicID}")
    @Results({
            @Result(column = "payment_id" , property = "paymentID"),
            @Result(column = "topic_id" , property = "topicID"),
            @Result(column = "amount" , property = "amount"),
            @Result(column = "status" , property = "paymentStatus"),
            @Result(column = "timestamp" , property = "paymentTimestamp")
    })
    UserTransaction getTopicPayment(UserTransaction payment);

    @Insert("insert into payments (topic_id, amount, payment_method, transaction_id, status, timestamp) values (#{topicID} , #{amount}, #{method}, #{transactionID}, #{paymentStatus}, #{timestamp} ")
    @Options(useGeneratedKeys = true, keyProperty = "paymentID" , keyColumn = "payment_id")
    void createPayment(UserTransaction payment);

    @Update("update payments set status =#{paymentStatus} where payment_id = #{paymentID}")
    void updatePayment(UserTransaction payment);


}
