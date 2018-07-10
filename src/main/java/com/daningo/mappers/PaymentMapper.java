package com.daningo.mappers;

import com.daningo.entity.UserPayment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PaymentMapper {
    @Select("select user_payment_id, user_id, payment_method, last_four, country, zipcode, bank_name from user_payments where user_id = #{userID}")
    @Results({
            @Result(column = "user_payment_id" , property = "userPaymentID"),
            @Result(column = "user_id", property = "user.userID"),
            @Result(column = "payment_method", property = "paymentMethod"),
            @Result(column = "last_four" , property = "lastFour"),
            @Result(column = "country" , property =  "country"),
            @Result(column = "zipcode" , property = "zipcode"),
            @Result(column = "bank_name" , property = "bankName")
    })
    List<UserPayment> getPayments(int userID);

    @Select("select user_payment_id, user_id, payment_method, last_four, country , zipcode, bank_name from user_payments where user_id = #{userID} and user_payment_id = #{userPaymentID}")
    @Results({
            @Result(column = "user_payment_id" , property = "userPaymentID"),
            @Result(column = "user_id", property = "user.userID"),
            @Result(column = "payment_method", property = "paymentMethod"),
            @Result(column = "last_four" , property = "lastFour"),
            @Result(column = "country" , property =  "country"),
            @Result(column = "zipcode" , property = "zipcode"),
            @Result(column = "bank_name" , property = "bankName")
    })
    UserPayment getPayment(UserPayment userPayment);

    @Select("select user_payment_id, user_id, stripe_id, payment_method, last_four, country , zipcode, bank_name from user_payments where user_id = #{userID} and user_payment_id = #{userPaymentID}")
    @Results({
            @Result(column = "user_payment_id" , property = "userPaymentID"),
            @Result(column = "user_id", property = "user.userID"),
            @Result(column = "payment_method", property = "paymentMethod"),
            @Result(column = "last_four" , property = "lastFour"),
            @Result(column = "country" , property =  "country"),
            @Result(column = "zipcode" , property = "zipcode"),
            @Result(column = "bank_name" , property = "bankName"),
            @Result(column = "stripe_id" , property = "stripeID")
    })
    UserPayment getStripePayment(UserPayment userPayment);

    @Insert("insert into user_payments (user_id, payment_method, last_four, stripe_id, county, zipcode, bank_name) values ( #{userID}, #{paymentMethod}, #{lastFour}, #{stripeID}, #{country}, #{zipcode}, #{bankName}")
    @Options(useGeneratedKeys = true, keyProperty = "userPaymentID", keyColumn = "user_payment_id")
    void addPayment(UserPayment userPayment);

    @Update("update user_payments set last_four = #{lastFour}, country = #{country}, zipcode=#{zipcode}, bank_name= #{bankName}, stripe_id = #{stripeID} where user_id = #{user.userID} and user_payment_id = #{userPaymentID} ")
    void updatePayment(UserPayment userPayment);

}
