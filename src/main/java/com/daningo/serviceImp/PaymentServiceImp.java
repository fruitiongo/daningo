package com.daningo.serviceImp;

import com.daningo.entity.UserPayment;
import com.daningo.mappers.PaymentMapper;
import com.daningo.service.UIService;
import org.apache.ibatis.session.SqlSession;

import javax.inject.Inject;
import java.util.List;

public class PaymentServiceImp implements UIService<UserPayment> {
    @Inject
    private SqlSession sqlSession;
    @Inject
    private PaymentMapper paymentMapper;

    @Override
    public List<UserPayment> getAll(UserPayment model) {
        return null;
    }

    @Override
    public void update(UserPayment model) {

    }

    @Override
    public UserPayment add(UserPayment model) {
        return null;
    }

    @Override
    public UserPayment get(UserPayment model) {
        return null;
    }

    @Override
    public List<UserPayment> getAll(int id) {
        return null;
    }

    @Override
    public List<UserPayment> getAll() {
        return null;
    }
}
