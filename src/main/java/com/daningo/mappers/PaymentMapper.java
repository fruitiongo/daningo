package com.daningo.mappers;

import com.daningo.entity.UserPayment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PaymentMapper {
    @Select("select user_payment_id, user_id, payment_method, last_four, country, zipcode, bank_name from user_payments where user_id = #{userID}")
    @Results({
            @Result(column = "user_payment_id" , property = "userPaymentID"),
            @Result(column = "user_id", property = "userID"),
            @Result(column = "payment_method", property = "paymentMethod"),
            @Result(column = "last_four" , property = "lastFour"),
            @Result(column = "country" , property =  "country"),
            @Result(column = "zipcode" , property = "zipcode"),
            @Result(column = "bank_name" , property = "bankName")
    })
    List<UserPayment> getPayments(int userID);

    @Insert("insert into user_payments (user_id, payment_method, last_four, county, zipcode, bank_name) values ( #{userID}, #{paymentMethod}, #{lastFour}, #{country}, #{zipcode}, #{bankName}")
    @Options(useGeneratedKeys = true, keyProperty = "userPaymentID", keyColumn = "user_payment_id")
    void addPayment(UserPayment userPayment);

}
